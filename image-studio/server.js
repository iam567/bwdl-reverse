'use strict';
/* sg.areu.pro 生图工作台 —— 纯 Node 22，零 npm 依赖
   数据模型：每个素材(item)自带状态，队列 = status==='queued' 的素材。 */
const http = require('node:http');
const fs   = require('node:fs');
const fsp  = require('node:fs/promises');
const path = require('node:path');
const crypto = require('node:crypto');

const ROOT   = __dirname;
const DATA   = path.join(ROOT, 'data');
const SOURCE = path.join(ROOT, 'source');
const OUTPUT = path.join(ROOT, 'output');
const STATIC = path.join(ROOT, 'static');
const CONFIG_PATH = path.join(DATA, 'config.json');
const ITEMS_PATH  = path.join(DATA, 'items.json');
const EVENTS_PATH = path.join(DATA, 'events.json');
const PORT = 3080, HOST = '127.0.0.1', PAGE = 36;
const OPENAI_BASE = 'https://api.openai.com/v1';

/* ---------- 工具 ---------- */
const j = (res, code, obj) => {
  const b = Buffer.from(JSON.stringify(obj));
  res.writeHead(code, { 'content-type':'application/json; charset=utf-8',
                        'content-length':b.length, 'cache-control':'no-store' });
  res.end(b);
};
const readJSON = (p, d) => { try { return JSON.parse(fs.readFileSync(p,'utf8')); } catch { return d; } };
const writeJSON = (p, o, mode) => {
  const t = p + '.tmp';
  fs.writeFileSync(t, JSON.stringify(o), mode ? { mode } : undefined);
  fs.renameSync(t, p);
  if (mode) { try { fs.chmodSync(p, mode); } catch {} }
};
const body = (req, limit = 2*1024*1024) => new Promise((ok,bad) => {
  let n=0; const c=[];
  req.on('data',d=>{ n+=d.length; if(n>limit){bad(new Error('too large'));req.destroy();} else c.push(d); });
  req.on('end',()=>ok(Buffer.concat(c))); req.on('error',bad);
});

/* ---------- 配置 ---------- */
function loadConfig() {
  let c = readJSON(CONFIG_PATH, null);
  if (!c) c = {};
  c.model      = c.model      || 'gpt-image-2.5-flare';
  c.size       = c.size       || '1024x1536';
  c.quality    = c.quality    || 'high';
  c.background = c.background || 'opaque';
  c.apiKey     = c.apiKey     || '';
  c.paused     = c.paused     !== undefined ? c.paused : true;
  c.concurrency = Math.min(Math.max(parseInt(c.concurrency,10) || 4, 1), 8);   // 默认暂停，避免误触发计费
  if (!c.secret) c.secret = crypto.randomBytes(32).toString('hex');
  if (!fs.existsSync(CONFIG_PATH)) writeJSON(CONFIG_PATH, c, 0o600);
  return c;
}
const saveConfig = c => writeJSON(CONFIG_PATH, c, 0o600);
const hashPass = (pw,s) => crypto.scryptSync(pw,s,32).toString('hex');
function setPassword(c,pw){ c.passSalt=crypto.randomBytes(16).toString('hex'); c.passHash=hashPass(pw,c.passSalt); }
function checkPass(c,pw){
  if(!c.passHash) return false;
  const a=Buffer.from(hashPass(pw,c.passSalt),'hex'), b=Buffer.from(c.passHash,'hex');
  return a.length===b.length && crypto.timingSafeEqual(a,b);
}

/* ---------- 会话 ---------- */
const sign=(p,s)=>{const x=Buffer.from(JSON.stringify(p)).toString('base64url');
  return x+'.'+crypto.createHmac('sha256',s).update(x).digest('base64url');};
function verify(t,s){ if(!t||!t.includes('.'))return null;
  const [p,g]=t.split('.'); const e=crypto.createHmac('sha256',s).update(p).digest('base64url');
  if(g.length!==e.length||!crypto.timingSafeEqual(Buffer.from(g),Buffer.from(e)))return null;
  try{const o=JSON.parse(Buffer.from(p,'base64url').toString()); return (o.exp&&Date.now()>o.exp)?null:o;}catch{return null;} }
const cookies=req=>Object.fromEntries((req.headers.cookie||'').split(';').map(v=>v.trim().split('='))
  .filter(a=>a[0]).map(a=>[a[0],decodeURIComponent(a.slice(1).join('='))]));

/* ---------- 素材与事件 ---------- */
let items = readJSON(ITEMS_PATH, null);
if (!items) {                                  // 首次从 assets.json 迁移
  items = (readJSON(path.join(DATA,'assets.json'), []) || []).map(a => ({
    ...a, status:'idle', file:null, error:null, startedAt:null, finishedAt:null, attempts:0
  }));
  writeJSON(ITEMS_PATH, items);
}
const byId = new Map(items.map(i => [i.id, i]));
/* 反向依赖：card_N -> [avatar_N, ...]。卡牌重画后派生图必须跟着重画，
   否则卡牌换了脸、头像还是旧的那张，两张图会变成两个人。 */
const dependents = new Map();
for (const i of items) if (i.refItem) {
  if (!dependents.has(i.refItem)) dependents.set(i.refItem, []);
  dependents.get(i.refItem).push(i.id);
}
const persistItems = () => { try { writeJSON(ITEMS_PATH, items); } catch {} };

let events = readJSON(EVENTS_PATH, []);
function logEvent(msg){
  events.unshift({ created: Date.now()/1000, message: msg });
  events = events.slice(0, 120);
  try { writeJSON(EVENTS_PATH, events); } catch {}
}

/* ---------- 生成 worker ---------- */
let workerMsg = loadConfig().paused ? '已暂停' : '空闲';
async function generate(it) {
  const cfg = loadConfig();
  if (!cfg.apiKey) throw new Error('未设置 API Key');
  it.error=null; it.attempts++;   // status/startedAt 已由 claimNext 占坑
  workerMsg = `并发生成中（${activeWorkers} 个）· 最新：${it.label}`; persistItems();

  const size    = it.size    || cfg.size;
  const quality = it.quality || cfg.quality;
  const model   = it.model   || cfg.model;

  const ctl = new AbortController();
  const timer = setTimeout(()=>ctl.abort(), 12*60*1000);
  let r;
  try {
    if (it.refItem) {
      /* ---- 派生图：以参考图约束人脸一致性，走 /images/edits ---- */
      const ref = byId.get(it.refItem);
      if (!ref || !ref.file)
        throw new Error(`参考图尚未生成：${ref ? ref.label : it.refItem}`);
      const buf = await fsp.readFile(path.join(OUTPUT, ref.file));
      const fd = new FormData();
      fd.append('model', model);
      fd.append('prompt', it.prompt);
      fd.append('size', size);
      fd.append('n', '1');
      if (quality && quality !== 'auto') fd.append('quality', quality);
      fd.append('image[]', new Blob([buf], { type:'image/png' }), 'ref.png');
      /* 派生图也可能需要透明底（势力旗要贴到地图上），
         头像不需要所以之前没传，这里补上 */
      const bgE = it.background || cfg.background;
      if (bgE && bgE !== 'auto') {
        fd.append('background', bgE);
        if (bgE === 'transparent') fd.append('output_format', 'png');
      }
      r = await fetch(`${OPENAI_BASE}/images/edits`, {
        method:'POST', signal:ctl.signal,
        headers:{ authorization:`Bearer ${cfg.apiKey}` },
        body: fd });
    } else {
      const payload = { model, prompt: it.prompt, size, n:1 };
      if (quality && quality !== 'auto') payload.quality = quality;
      /* 素材自带 background 时优先（城池图标需要 transparent 才能贴到地图上） */
      const bg = it.background || cfg.background;
      if (bg && bg !== 'auto') payload.background = bg;
      if (bg === 'transparent') payload.output_format = 'png';
      r = await fetch(`${OPENAI_BASE}/images/generations`, {
        method:'POST', signal:ctl.signal,
        headers:{ authorization:`Bearer ${cfg.apiKey}`, 'content-type':'application/json' },
        body: JSON.stringify(payload) });
    }
  } finally { clearTimeout(timer); }

  if (!r.ok) {
    const txt = await r.text();
    let m = txt.slice(0,400);
    try { m = JSON.parse(txt).error?.message || m; } catch {}
    throw new Error(`OpenAI ${r.status}: ${m}`);
  }
  /* 成功路径直接走 json()，不再额外持有一份完整文本；
     取出 Buffer 后立刻断开对 base64 大字符串的引用，让 V8 尽早回收。
     8 并发时每份 base64 约 3.4MB，不释放会把堆顶到上限。 */
  let d = await r.json();
  const o = d.data?.[0];
  if (!o) throw new Error('返回结果为空');
  let buf;
  if (o.b64_json) { buf = Buffer.from(o.b64_json,'base64'); o.b64_json = null; }
  else            { buf = Buffer.from(await (await fetch(o.url)).arrayBuffer()); }
  d = null;

  const dir = path.join(OUTPUT, it.category);
  await fsp.mkdir(dir, { recursive:true });
  const file = `${it.id}_${Date.now().toString(36)}.png`;
  await fsp.writeFile(path.join(dir,file), buf);
  it.file = `${it.category}/${file}`;
  it.bytes = buf.length;
  it.status='done'; it.finishedAt=Date.now();

  /* 参考图变了，已生成的派生图立刻作废并重新入队 */
  for (const depId of dependents.get(it.id) || []) {
    const dep = byId.get(depId);
    if (dep && dep.status === 'done') {
      dep.status = 'queued'; dep.file = null; dep.error = null;
      logEvent(`${it.label} 卡牌已更新，其头像重新入队`);
    }
  }
  persistItems();
}

const sleep = ms => new Promise(r => setTimeout(r, ms));
let activeWorkers = 0;
let rateLimitUntil = 0;          // 429 退避截止时间

function claimNext() {
  const ready = x => !x.refItem || byId.get(x.refItem)?.file;
  const it = items.find(x => x.status === 'queued' && ready(x));
  if (it) { it.status = 'running'; it.startedAt = Date.now(); }   // 同步占坑，避免两个 worker 抢同一项
  return it;
}

async function worker(tag) {
  for (;;) {
    const cfg = loadConfig();
    if (cfg.paused) break;

    if (Date.now() < rateLimitUntil) { await sleep(2000); continue; }

    const it = claimNext();
    if (!it) {
      /* 没有可取的：可能是派生图在等在途的参考图，等一下再看 */
      const stillQueued = items.some(x => x.status === 'queued');
      const inflight    = items.some(x => x.status === 'running');
      if (stillQueued && inflight) { await sleep(3000); continue; }
      break;
    }
    persistItems();
    try {
      await generate(it);
      logEvent(`✅ ${it.label}（${it.category}）完成`);
    } catch (e) {
      const msg = String(e.message || e).slice(0, 500);
      if (/\b429\b|rate limit|too many requests/i.test(msg)) {
        /* 限流：本项退回队列，全体退避 */
        it.status = 'queued'; it.startedAt = null;
        rateLimitUntil = Date.now() + 30000;
        logEvent('⚠️ 触发速率限制，全体退避 30 秒');
        persistItems();
        continue;
      }
      it.status = 'failed'; it.error = msg; it.finishedAt = Date.now();
      persistItems();
      logEvent(`❌ ${it.label}：${msg}`);
      if (/401|invalid_api_key|api key|insufficient|quota|billing/i.test(msg)) {
        const c = loadConfig(); c.paused = true; saveConfig(c);
        logEvent('⚠️ API Key 或额度异常，队列已自动暂停');
        break;
      }
    }
  }
}

function pump() {
  const cfg = loadConfig();
  if (cfg.paused) return;
  const want = cfg.concurrency;
  while (activeWorkers < want && items.some(x => x.status === 'queued')) {
    activeWorkers++;
    const n = activeWorkers;
    worker(n).catch(()=>{}).finally(() => {
      activeWorkers--;
      workerMsg = activeWorkers > 0
        ? `并发生成中（${activeWorkers} 个）`
        : (loadConfig().paused ? '已暂停' : '队列已清空');
    });
  }
}

/* ---------- 静态 ---------- */
const MIME={'.html':'text/html; charset=utf-8','.js':'text/javascript; charset=utf-8',
  '.css':'text/css; charset=utf-8','.png':'image/png','.jpg':'image/jpeg','.json':'application/json; charset=utf-8'};
function serveFile(res, base, rel, cache){
  const p=path.resolve(base,'.'+decodeURIComponent(rel));
  if(!p.startsWith(base)){res.writeHead(403).end('forbidden');return;}
  fs.stat(p,(e,st)=>{ if(e||!st.isFile()){res.writeHead(404).end('not found');return;}
    res.writeHead(200,{'content-type':MIME[path.extname(p).toLowerCase()]||'application/octet-stream',
      'content-length':st.size,'cache-control':cache||'no-store'});
    fs.createReadStream(p).pipe(res); });
}

/* ---------- 路由 ---------- */
const server = http.createServer(async (req,res) => {
  const u = new URL(req.url,'http://x'), p = u.pathname;
  const cfg = loadConfig();
  const isAuthed = !!verify(cookies(req).sg_session, cfg.secret);
  try {
    if (p.startsWith('/static/')) return serveFile(res,STATIC,p.slice(7),'public, max-age=60');
    if (p === '/login')   return serveFile(res,STATIC,'/login.html');
    if (p === '/healthz') return j(res,200,{ok:true});
    if (p === '/api/login' && req.method==='POST') {
      const {password}=JSON.parse((await body(req)).toString()||'{}');
      if(!checkPass(cfg,password||'')){ await new Promise(r=>setTimeout(r,800));
        return j(res,401,{error:'口令错误'}); }
      res.writeHead(200,{'set-cookie':`sg_session=${sign({u:'owner',exp:Date.now()+30*864e5},cfg.secret)}; HttpOnly; Secure; SameSite=Lax; Path=/; Max-Age=${30*86400}`,
        'content-type':'application/json'});
      return res.end('{"ok":true}');
    }
    if (p === '/api/logout') {
      res.writeHead(302,{'set-cookie':'sg_session=; HttpOnly; Secure; Path=/; Max-Age=0',location:'/login'});
      return res.end();
    }
    if (!isAuthed) {
      if (p.startsWith('/api/')) return j(res,401,{error:'未登录'});
      res.writeHead(302,{location:'/login'}); return res.end();
    }

    if (p === '/')         return serveFile(res,STATIC,'/index.html');
    if (p === '/settings') return serveFile(res,STATIC,'/settings.html');
    if (p === '/edit' || p === '/edit/') return serveFile(res,STATIC,'/edit.html');
    if (p === '/preview' || p === '/preview/') return serveFile(res,STATIC,'/preview.html');

    /* ---- 城池坐标编辑器 ---- */
    if (p === '/api/edit/cities' && req.method==='GET') {
      const mt = f => { try { return Math.floor(fs.statSync(path.join(OUTPUT,'map',f)).mtimeMs); } catch { return 0; } };
      const mtf = () => { try { return Math.floor(fs.statSync(path.join(OUTPUT,'flag','web','anchors.json')).mtimeMs); } catch { return 0; } };
      return j(res,200,{ cities: readJSON(path.join(DATA,'city-screen.json'),[]),
                         adjacency: readJSON(path.join(DATA,'adjacency.json'),{}),
                         owners: readJSON(path.join(DATA,'city-owner.json'),{}),
                         flags: readJSON(path.join(DATA,'flag-anchors.json'),{}),
                         boxes: readJSON(path.join(DATA,'map-boxes.json'),{}),
                         mapVersion: mt('map_L2_with_cities.jpg'), overviewVersion: mt('overview_1024.jpg'),
                         flagVersion: mtf() });
    }
    if (p === '/api/edit/cities' && req.method==='POST') {
      const b=JSON.parse((await body(req)).toString()||'{}');
      if(!Array.isArray(b.cities)) return j(res,400,{error:'cities 须为数组'});
      const file=path.join(DATA,'city-screen.json');
      const cur=readJSON(file,[]);
      try { fs.copyFileSync(file, path.join(DATA,`city-screen.bak-${Date.now()}.json`)); } catch {}
      const byId=new Map(cur.map(c=>[c.id,c])); let n=0;
      for(const c of b.cities){ const e=byId.get(c.id);
        if(e && Number.isFinite(+c.x) && Number.isFinite(+c.y)){ e.x=+c.x; e.y=+c.y; e.edited=true; n++; } }
      writeJSON(file,cur);
      logEvent(`地图编辑器保存了 ${n} 座城池坐标`);
      return j(res,200,{ok:true,saved:n});
    }
    if (p.startsWith('/source/')) return serveFile(res,SOURCE,p.slice(7),'public, max-age=86400');
    if (p.startsWith('/image/'))  return serveFile(res,OUTPUT,p.slice(6),'public, max-age=86400');

    /* 总览 */
    if (p === '/api/status') {
      const counts={};
      for(const it of items) counts[it.status]=(counts[it.status]||0)+1;
      return j(res,200,{ total:items.length, counts, running: activeWorkers>0,
        concurrency: cfg.concurrency, active: activeWorkers, paused:cfg.paused,
        worker:workerMsg, model:cfg.model, hasKey:!!cfg.apiKey,
        events: events.slice(0,12) });
    }
    /* 分页列表 */
    if (p === '/api/items') {
      const q=(u.searchParams.get('q')||'').trim();
      const cat=u.searchParams.get('category')||'';
      const st=u.searchParams.get('status')||'';
      const page=Math.max(1,parseInt(u.searchParams.get('page')||'1',10));
      let f=items;
      if(cat) f=f.filter(x=>x.category===cat);
      if(st)  f=f.filter(x=>x.status===st);
      if(q)   f=f.filter(x=>x.label.includes(q)||x.prompt.includes(q));
      const start=(page-1)*PAGE;
      return j(res,200,{ total:f.length, page, pages:Math.max(1,Math.ceil(f.length/PAGE)),
        items: f.slice(start,start+PAGE).map(x=>({ id:x.id, label:x.label, category:x.category,
          status:x.status, file:x.file, source:x.source, meta:x.meta })) });
    }
    if (p.startsWith('/api/item/')) {
      const it=byId.get(decodeURIComponent(p.slice(10)));
      return it ? j(res,200,it) : j(res,404,{error:'素材不存在'});
    }
    /* 操作 */
    if (p === '/api/prompt' && req.method==='POST') {
      const b=JSON.parse((await body(req)).toString()||'{}');
      const it=byId.get(b.id); if(!it) return j(res,404,{error:'素材不存在'});
      it.prompt=String(b.prompt||''); persistItems();
      return j(res,200,{ok:true});
    }
    if (p === '/api/enqueue' && req.method==='POST') {
      const b=JSON.parse((await body(req)).toString()||'{}');
      const ids=Array.isArray(b.ids)?b.ids:[];
      let n=0;
      for(const id of ids){ const it=byId.get(id);
        if(it && it.status!=='running' && it.status!=='queued'){ it.status='queued'; it.error=null; n++; } }
      persistItems();
      if(n) logEvent(`加入队列 ${n} 项`);
      pump();
      return j(res,200,{ok:true,queued:n});
    }
    if (p === '/api/enqueue-filtered' && req.method==='POST') {
      const b=JSON.parse((await body(req)).toString()||'{}');
      let f=items;
      if(b.category) f=f.filter(x=>x.category===b.category);
      if(b.status)   f=f.filter(x=>x.status===b.status);
      if(b.q)        f=f.filter(x=>x.label.includes(b.q)||x.prompt.includes(b.q));
      let n=0;
      for(const it of f){ if(it.status!=='running'&&it.status!=='queued'){ it.status='queued'; it.error=null; n++; } }
      persistItems(); if(n) logEvent(`批量加入队列 ${n} 项`); pump();
      return j(res,200,{ok:true,queued:n});
    }
    if (p === '/api/control' && req.method==='POST') {
      const b=JSON.parse((await body(req)).toString()||'{}');
      cfg.paused = b.action==='pause'; saveConfig(cfg);
      logEvent(cfg.paused?'队列已暂停':'队列已启动');
      if(!cfg.paused) pump();
      return j(res,200,{ok:true,paused:cfg.paused});
    }
    if (p === '/api/reset-queue' && req.method==='POST') {
      let n=0; for(const it of items) if(it.status==='queued'){ it.status='idle'; n++; }
      persistItems(); logEvent(`清空队列 ${n} 项`);
      return j(res,200,{ok:true,cleared:n});
    }

    /* 配置 */
    if (p === '/api/config' && req.method==='GET') {
      const k=cfg.apiKey||'';
      return j(res,200,{ hasKey:!!k, keyMask:k?`${k.slice(0,7)}…${k.slice(-4)}`:'',
        model:cfg.model, size:cfg.size, quality:cfg.quality,
        background:cfg.background, concurrency:cfg.concurrency });
    }
    if (p === '/api/config' && req.method==='POST') {
      const b=JSON.parse((await body(req)).toString()||'{}');
      if(typeof b.apiKey==='string'&&b.apiKey.trim()&&!b.apiKey.includes('…')) cfg.apiKey=b.apiKey.trim();
      if(b.clearKey) cfg.apiKey='';
      for(const f of ['model','size','quality','background'])
        if(typeof b[f]==='string'&&b[f]) cfg[f]=b[f];
      if(b.concurrency!==undefined){
        cfg.concurrency = Math.min(Math.max(parseInt(b.concurrency,10)||4,1),8);
      }
      if(b.newPassword&&b.newPassword.length>=8) setPassword(cfg,b.newPassword);
      saveConfig(cfg); return j(res,200,{ok:true});
    }
    if (p === '/api/models') {
      if(!cfg.apiKey) return j(res,400,{error:'请先填写 API Key'});
      const r=await fetch(`${OPENAI_BASE}/models`,{headers:{authorization:`Bearer ${cfg.apiKey}`}});
      const t=await r.text();
      if(!r.ok){ let m=t.slice(0,300); try{m=JSON.parse(t).error?.message||m;}catch{}
                 return j(res,r.status,{error:m}); }
      const all=(JSON.parse(t).data||[]).map(x=>x.id).sort();
      return j(res,200,{ image:all.filter(x=>/image/i.test(x)), all });
    }

    res.writeHead(404).end('not found');
  } catch(e){ j(res,500,{error:String(e.message||e)}); }
});

for(const d of [DATA,SOURCE,OUTPUT,STATIC]) fs.mkdirSync(d,{recursive:true});
loadConfig();

/* 崩溃/重启恢复：进程退出时处于 running 的任务不可能完成，复位为 queued，
   否则它会永远卡住（pump 只捞 queued）。 */
{
  let n = 0;
  for (const it of items) if (it.status === 'running') { it.status = 'queued'; n++; }
  if (n) { persistItems(); logEvent(`重启恢复：${n} 项中断任务已重新入队`); }
}
if (!loadConfig().paused) pump();
server.listen(PORT,HOST,()=>console.log(`image studio on http://${HOST}:${PORT} · ${items.length} items`));
