'use strict';
// sg.areu.pro —— 生图系统  纯 Node 22，零 npm 依赖
const http = require('node:http');
const fs   = require('node:fs');
const fsp  = require('node:fs/promises');
const path = require('node:path');
const crypto = require('node:crypto');

const ROOT    = __dirname;
const DATA    = path.join(ROOT, 'data');
const SOURCE  = path.join(ROOT, 'source');
const OUTPUT  = path.join(ROOT, 'output');
const STATIC  = path.join(ROOT, 'static');
const PORT    = 3080;
const HOST    = '127.0.0.1';
const CONFIG_PATH = path.join(DATA, 'config.json');
const JOBS_PATH   = path.join(DATA, 'jobs.json');
const ASSETS_PATH = path.join(DATA, 'assets.json');

const OPENAI_BASE = 'https://api.openai.com/v1';

/* ---------- 小工具 ---------- */
const j = (res, code, obj) => {
  const b = Buffer.from(JSON.stringify(obj));
  res.writeHead(code, { 'content-type': 'application/json; charset=utf-8',
                        'content-length': b.length, 'cache-control': 'no-store' });
  res.end(b);
};
const readJSON = (p, dflt) => { try { return JSON.parse(fs.readFileSync(p, 'utf8')); } catch { return dflt; } };
const writeJSON = (p, o, mode) => {
  const tmp = p + '.tmp';
  fs.writeFileSync(tmp, JSON.stringify(o, null, 2), mode ? { mode } : undefined);
  fs.renameSync(tmp, p);
  if (mode) { try { fs.chmodSync(p, mode); } catch {} }
};
const body = (req, limit = 25 * 1024 * 1024) => new Promise((ok, bad) => {
  let n = 0; const c = [];
  req.on('data', d => { n += d.length; if (n > limit) { bad(new Error('too large')); req.destroy(); } else c.push(d); });
  req.on('end', () => ok(Buffer.concat(c)));
  req.on('error', bad);
});

/* ---------- 配置 ---------- */
function loadConfig() {
  let c = readJSON(CONFIG_PATH, null);
  if (!c) {
    c = { apiKey: '', model: 'gpt-image-2.5-flare', size: '1024x1536', quality: 'high',
          background: 'opaque', passHash: '', passSalt: '',
          secret: crypto.randomBytes(32).toString('hex'), createdAt: new Date().toISOString() };
    writeJSON(CONFIG_PATH, c, 0o600);
  }
  if (!c.secret) { c.secret = crypto.randomBytes(32).toString('hex'); writeJSON(CONFIG_PATH, c, 0o600); }
  return c;
}
const saveConfig = c => writeJSON(CONFIG_PATH, c, 0o600);

const hashPass = (pw, salt) => crypto.scryptSync(pw, salt, 32).toString('hex');
function setPassword(cfg, pw) {
  cfg.passSalt = crypto.randomBytes(16).toString('hex');
  cfg.passHash = hashPass(pw, cfg.passSalt);
}
function checkPass(cfg, pw) {
  if (!cfg.passHash) return false;
  const a = Buffer.from(hashPass(pw, cfg.passSalt), 'hex');
  const b = Buffer.from(cfg.passHash, 'hex');
  return a.length === b.length && crypto.timingSafeEqual(a, b);
}

/* ---------- 会话（HMAC 签名 cookie） ---------- */
function sign(payload, secret) {
  const p = Buffer.from(JSON.stringify(payload)).toString('base64url');
  const s = crypto.createHmac('sha256', secret).update(p).digest('base64url');
  return `${p}.${s}`;
}
function verify(token, secret) {
  if (!token || !token.includes('.')) return null;
  const [p, s] = token.split('.');
  const exp = crypto.createHmac('sha256', secret).update(p).digest('base64url');
  if (s.length !== exp.length || !crypto.timingSafeEqual(Buffer.from(s), Buffer.from(exp))) return null;
  try {
    const o = JSON.parse(Buffer.from(p, 'base64url').toString());
    if (o.exp && Date.now() > o.exp) return null;
    return o;
  } catch { return null; }
}
const cookies = req => Object.fromEntries((req.headers.cookie || '').split(';')
  .map(v => v.trim().split('=')).filter(a => a[0]).map(a => [a[0], decodeURIComponent(a.slice(1).join('='))]));
const authed = (req, cfg) => !!verify(cookies(req).sg_session, cfg.secret);

/* ---------- 任务队列（1 核，串行执行） ---------- */
let jobs = readJSON(JOBS_PATH, []);
let running = false;
const persistJobs = () => { try { writeJSON(JOBS_PATH, jobs.slice(-500)); } catch {} };

async function runJob(job) {
  const cfg = loadConfig();
  if (!cfg.apiKey) throw new Error('未设置 API Key，请先到设置页填写');
  job.status = 'running'; job.startedAt = Date.now(); persistJobs();

  const payload = { model: job.model, prompt: job.prompt, size: job.size, n: 1 };
  if (job.quality)    payload.quality = job.quality;
  if (job.background) payload.background = job.background;

  const ctl = new AbortController();
  const timer = setTimeout(() => ctl.abort(), 10 * 60 * 1000);
  let r;
  try {
    r = await fetch(`${OPENAI_BASE}/images/generations`, {
      method: 'POST', signal: ctl.signal,
      headers: { 'authorization': `Bearer ${cfg.apiKey}`, 'content-type': 'application/json' },
      body: JSON.stringify(payload)
    });
  } finally { clearTimeout(timer); }

  const txt = await r.text();
  if (!r.ok) {
    let msg = txt.slice(0, 500);
    try { msg = JSON.parse(txt).error?.message || msg; } catch {}
    throw new Error(`OpenAI ${r.status}: ${msg}`);
  }
  const data = JSON.parse(txt);
  const item = data.data?.[0];
  if (!item) throw new Error('返回结果为空');

  let buf;
  if (item.b64_json) buf = Buffer.from(item.b64_json, 'base64');
  else if (item.url)  buf = Buffer.from(await (await fetch(item.url)).arrayBuffer());
  else throw new Error('返回既无 b64_json 也无 url');

  const dir = path.join(OUTPUT, job.category);
  await fsp.mkdir(dir, { recursive: true });
  const file = `${job.assetId}__${job.id}.png`;
  await fsp.writeFile(path.join(dir, file), buf);

  job.output = `/output/${job.category}/${file}`;
  job.bytes = buf.length;
  job.usage = data.usage || null;
  job.status = 'done'; job.finishedAt = Date.now();
  persistJobs();
}

async function pump() {
  if (running) return;
  running = true;
  try {
    for (;;) {
      const job = jobs.find(x => x.status === 'queued');
      if (!job) break;
      try { await runJob(job); }
      catch (e) {
        job.status = 'error';
        job.error = String(e.message || e).slice(0, 800);
        job.finishedAt = Date.now();
        persistJobs();
      }
    }
  } finally { running = false; }
}

/* ---------- 静态文件 ---------- */
const MIME = { '.html':'text/html; charset=utf-8', '.js':'text/javascript; charset=utf-8',
  '.css':'text/css; charset=utf-8', '.png':'image/png', '.jpg':'image/jpeg',
  '.webp':'image/webp', '.json':'application/json; charset=utf-8', '.csv':'text/csv; charset=utf-8' };

function serveFile(res, base, rel, cache) {
  const p = path.resolve(base, '.' + decodeURIComponent(rel));
  if (!p.startsWith(base)) { res.writeHead(403).end('forbidden'); return; }
  fs.stat(p, (e, st) => {
    if (e || !st.isFile()) { res.writeHead(404).end('not found'); return; }
    res.writeHead(200, { 'content-type': MIME[path.extname(p).toLowerCase()] || 'application/octet-stream',
                         'content-length': st.size, 'cache-control': cache || 'no-store' });
    fs.createReadStream(p).pipe(res);
  });
}

/* ---------- 路由 ---------- */
const server = http.createServer(async (req, res) => {
  const u = new URL(req.url, 'http://x');
  const p = u.pathname;
  const cfg = loadConfig();
  const isAuthed = authed(req, cfg);

  try {
    /* --- 登录 --- */
    if (p === '/api/login' && req.method === 'POST') {
      const { password } = JSON.parse((await body(req)).toString() || '{}');
      if (!checkPass(cfg, password || '')) {
        await new Promise(r => setTimeout(r, 800));   // 暴力破解减速
        return j(res, 401, { error: '口令错误' });
      }
      const tok = sign({ u: 'owner', exp: Date.now() + 30 * 864e5 }, cfg.secret);
      res.writeHead(200, {
        'set-cookie': `sg_session=${tok}; HttpOnly; Secure; SameSite=Lax; Path=/; Max-Age=${30*86400}`,
        'content-type': 'application/json' });
      return res.end(JSON.stringify({ ok: true }));
    }
    if (p === '/api/logout') {
      res.writeHead(302, { 'set-cookie': 'sg_session=; HttpOnly; Secure; Path=/; Max-Age=0', location: '/login' });
      return res.end();
    }
    if (p.startsWith('/static/')) return serveFile(res, STATIC, p.slice(7), 'public, max-age=300');
    if (p === '/login') return serveFile(res, STATIC, '/login.html');
    if (p === '/healthz') return j(res, 200, { ok: true, jobs: jobs.length });

    /* --- 以下全部需要登录 --- */
    if (!isAuthed) {
      if (p.startsWith('/api/')) return j(res, 401, { error: '未登录' });
      res.writeHead(302, { location: '/login' }); return res.end();
    }

    if (p === '/')          return serveFile(res, STATIC, '/index.html');
    if (p === '/settings')  return serveFile(res, STATIC, '/settings.html');
    if (p === '/prompts')   return serveFile(res, STATIC, '/prompts.html');
    if (p.startsWith('/source/')) return serveFile(res, SOURCE, p.slice(7), 'public, max-age=86400');
    if (p.startsWith('/output/')) return serveFile(res, OUTPUT, p.slice(7), 'public, max-age=86400');

    /* --- 配置 --- */
    if (p === '/api/config' && req.method === 'GET') {
      const k = cfg.apiKey || '';
      return j(res, 200, {
        hasKey: !!k,
        keyMask: k ? `${k.slice(0, 7)}…${k.slice(-4)}` : '',
        model: cfg.model, size: cfg.size, quality: cfg.quality, background: cfg.background
      });
    }
    if (p === '/api/config' && req.method === 'POST') {
      const b = JSON.parse((await body(req)).toString() || '{}');
      if (typeof b.apiKey === 'string' && b.apiKey.trim() && !b.apiKey.includes('…'))
        cfg.apiKey = b.apiKey.trim();
      if (b.clearKey) cfg.apiKey = '';
      for (const f of ['model', 'size', 'quality', 'background'])
        if (typeof b[f] === 'string' && b[f]) cfg[f] = b[f];
      if (b.newPassword && b.newPassword.length >= 8) setPassword(cfg, b.newPassword);
      saveConfig(cfg);
      return j(res, 200, { ok: true });
    }

    /* --- 实时拉取模型列表 --- */
    if (p === '/api/models') {
      if (!cfg.apiKey) return j(res, 400, { error: '请先填写 API Key' });
      const r = await fetch(`${OPENAI_BASE}/models`, { headers: { authorization: `Bearer ${cfg.apiKey}` } });
      const t = await r.text();
      if (!r.ok) { let m = t.slice(0,300); try { m = JSON.parse(t).error?.message || m; } catch {}
                   return j(res, r.status, { error: m }); }
      const all = (JSON.parse(t).data || []).map(x => x.id).sort();
      return j(res, 200, { image: all.filter(x => /image/i.test(x)), all });
    }

    /* --- 素材目录 --- */
    if (p === '/api/assets') {
      const assets = readJSON(ASSETS_PATH, []);
      const byAsset = {};
      for (const jb of jobs) if (jb.status === 'done') byAsset[jb.assetId] = jb.output;
      return j(res, 200, assets.map(a => ({ ...a, latest: byAsset[a.id] || null })));
    }
    if (p === '/api/assets' && req.method === 'POST') {
      writeJSON(ASSETS_PATH, JSON.parse((await body(req)).toString()));
      return j(res, 200, { ok: true });
    }

    /* --- 生成 --- */
    if (p === '/api/generate' && req.method === 'POST') {
      const b = JSON.parse((await body(req)).toString() || '{}');
      const items = Array.isArray(b.items) ? b.items : [b];
      const made = [];
      for (const it of items) {
        if (!it.prompt) continue;
        const job = {
          id: crypto.randomBytes(5).toString('hex'),
          assetId: it.assetId || 'ad-hoc',
          category: (it.category || 'misc').replace(/[^a-z0-9_-]/gi, ''),
          label: it.label || it.assetId || '',
          prompt: it.prompt,
          model: it.model || cfg.model,
          size: it.size || cfg.size,
          quality: it.quality || cfg.quality,
          background: it.background || cfg.background,
          status: 'queued', createdAt: Date.now()
        };
        jobs.push(job); made.push(job.id);
      }
      persistJobs(); pump();
      return j(res, 200, { ok: true, queued: made.length, ids: made });
    }
    if (p === '/api/jobs') {
      const n = Math.min(parseInt(u.searchParams.get('limit') || '80', 10), 300);
      return j(res, 200, { running, jobs: jobs.slice(-n).reverse() });
    }
    if (p === '/api/jobs/clear' && req.method === 'POST') {
      jobs = jobs.filter(x => x.status === 'queued' || x.status === 'running');
      persistJobs(); return j(res, 200, { ok: true });
    }

    res.writeHead(404).end('not found');
  } catch (e) {
    j(res, 500, { error: String(e.message || e) });
  }
});

for (const d of [DATA, SOURCE, OUTPUT, STATIC]) fs.mkdirSync(d, { recursive: true });
loadConfig();
server.listen(PORT, HOST, () => console.log(`sg.areu.pro image studio on http://${HOST}:${PORT}`));
