'use strict';
const $ = id => document.getElementById(id);
const NAMES = { idle:'未生成', queued:'排队中', running:'生成中', done:'已完成', failed:'失败' };
const CATS  = { portrait:'武将立绘', cg:'全屏 CG', scene:'战斗场景' };

let page = 1, pages = 1, paused = true, sel = new Set(), cur = null, busy = false;

const el = (t, txt, cls) => { const n = document.createElement(t);
  if (txt !== undefined) n.textContent = txt; if (cls) n.className = cls; return n; };
async function api(path, data) {
  const r = await fetch(path, data ? { method:'POST',
    headers:{'content-type':'application/json'}, body:JSON.stringify(data)} : {});
  if (!r.ok) throw Error((await r.json().catch(()=>({}))).error || '服务器无响应');
  return r.json();
}
function toast(m){ const t=$('toast'); t.textContent=m; t.style.display='block';
  clearTimeout(toast._t); toast._t=setTimeout(()=>t.style.display='none',4000); }

async function refresh() {
  if (busy) return; busy = true;
  try {
    const s = await api('/api/status');
    paused = s.paused;
    $('total').textContent = s.total;
    const done = s.counts.done || 0;
    $('done').textContent = done;
    $('bar').max = s.total; $('bar').value = done;
    $('pct').textContent = s.total ? (done / s.total * 100).toFixed(1) + '%' : '0%';
    $('toggle').textContent = paused ? '启动队列' : '暂停队列';
    $('toggle').className = paused ? '' : 'ghost';
    $('worker').textContent = s.worker + (s.hasKey ? '' : '（尚未设置 API Key）');
    $('conn').textContent = '已连接 · 5 秒刷新';
    $('stats').replaceChildren(...Object.entries(s.counts).map(([k,v]) => {
      const n = el('span'); n.append(document.createTextNode((NAMES[k]||k)+' '), el('b', v)); return n; }));
    $('events').replaceChildren(...s.events.map(e => { const li = el('li');
      li.append(el('time', new Date(e.created*1000).toLocaleTimeString('zh-CN')),
                document.createTextNode(e.message)); return li; }));
    if (!s.events.length) $('events').append(el('li','暂无记录'));
    await list();
  } catch (e) { $('conn').textContent = '连接中断 · 自动重连'; }
  finally { busy = false; }
}

async function list() {
  const p = new URLSearchParams({ page, q:$('q').value, category:$('category').value, status:$('status').value });
  const r = await api('/api/items?' + p);
  pages = r.pages; page = Math.min(page, pages);
  $('results').textContent = `· 共 ${r.total} 项`;
  $('page').textContent = `${r.page} / ${r.pages}`;
  $('prev').disabled = r.page <= 1; $('next').disabled = r.page >= r.pages;
  $('grid').replaceChildren(...r.items.map(it => {
    const card = el('button', undefined, 'tile' + (sel.has(it.id) ? ' sel' : ''));
    const box = el('div', undefined, 'im');
    if (it.file) { const im = el('img'); im.src = '/image/' + it.file; im.loading='lazy'; im.alt=it.label; box.append(im); }
    else if (it.source) { box.style.backgroundImage = `url('${it.source}')`; }
    else box.append(el('span','无预览'));
    const bd = el('div', undefined, 'bd');
    bd.append(el('h3', it.label),
              el('div', it.meta ? `武 ${it.meta.武力} · 智 ${it.meta.智力} · 德 ${it.meta.德}` : (CATS[it.category]||it.category), 'meta'),
              el('span', NAMES[it.status]||it.status, 'badge ' + it.status));
    const pick = el('div','✓','pick');
    pick.onclick = e => { e.stopPropagation();
      sel.has(it.id) ? sel.delete(it.id) : sel.add(it.id); list(); updN(); };
    card.append(box, bd, pick);
    card.onclick = () => detail(it.id).catch(e => toast(e.message));
    return card;
  }));
  if (!r.items.length) $('grid').append(el('p','没有符合条件的素材。'));
  updN();
}
const updN = () => $('nsel').textContent = sel.size;

async function detail(id) {
  cur = await api('/api/item/' + encodeURIComponent(id));
  $('dTitle').textContent = cur.label;
  $('dMeta').textContent = [ CATS[cur.category]||cur.category, NAMES[cur.status],
    cur.meta ? Object.entries(cur.meta).map(([k,v])=>`${k} ${v}`).join(' · ') : '',
    cur.error ? '错误：' + cur.error : '' ].filter(Boolean).join(' · ');
  $('dPrompt').value = cur.prompt;
  const c = $('dCompare'); c.replaceChildren();
  if (cur.source) { const f=el('figure'); f.append(el('figcaption','原始素材'));
    const i=el('img'); i.className='src'; i.src=cur.source; f.append(i); c.append(f); }
  if (cur.file) { const f=el('figure'); f.append(el('figcaption','生成结果'));
    const a=document.createElement('a'); a.href='/image/'+cur.file; a.target='_blank';
    const i=el('img'); i.src='/image/'+cur.file; a.append(i); f.append(a); c.append(f); }
  if (!$('dlg').open) $('dlg').showModal();
}

$('toggle').onclick = async () => {
  try { await api('/api/control', { action: paused ? 'start' : 'pause' });
        toast(paused ? '队列已启动' : '队列已暂停'); await refresh(); }
  catch (e) { toast(e.message); }
};
$('gen').onclick = async () => {
  if (!sel.size) return toast('还没有选中素材');
  if (!confirm(`将 ${sel.size} 个素材加入队列，生成会产生 API 费用。确定？`)) return;
  try { const r = await api('/api/enqueue', { ids:[...sel] });
        sel.clear(); toast(`已入队 ${r.queued} 项`); await refresh(); }
  catch (e) { toast(e.message); }
};
$('genAll').onclick = async () => {
  const body = { q:$('q').value, category:$('category').value, status:$('status').value };
  if (!confirm('把当前筛选结果全部加入队列？生成会产生 API 费用。')) return;
  try { const r = await api('/api/enqueue-filtered', body);
        toast(`已入队 ${r.queued} 项`); await refresh(); }
  catch (e) { toast(e.message); }
};
$('selPage').onclick = () => {
  api('/api/items?' + new URLSearchParams({page,q:$('q').value,category:$('category').value,status:$('status').value}))
    .then(r => { r.items.forEach(i => sel.add(i.id)); list(); }); };
$('selNone').onclick = () => { sel.clear(); list(); };
$('resetQ').onclick = async () => {
  if (!confirm('把所有排队中的素材移出队列？')) return;
  try { const r = await api('/api/reset-queue', {}); toast(`已清空 ${r.cleared} 项`); await refresh(); }
  catch (e) { toast(e.message); }
};
$('dClose').onclick = () => $('dlg').close();
$('dSave').onclick = async () => {
  try { await api('/api/prompt', { id:cur.id, prompt:$('dPrompt').value }); toast('提示词已保存'); }
  catch (e) { toast(e.message); }
};
$('dGen').onclick = async () => {
  if (!confirm('生成会产生 API 费用。确定？')) return;
  try { await api('/api/prompt', { id:cur.id, prompt:$('dPrompt').value });
        await api('/api/enqueue', { ids:[cur.id] });
        $('dlg').close(); toast('已加入队列'); await refresh(); }
  catch (e) { toast(e.message); }
};
for (const id of ['category','status']) $(id).onchange = () => { page = 1; refresh(); };
let deb; $('q').oninput = () => { clearTimeout(deb); deb = setTimeout(()=>{ page=1; refresh(); }, 300); };
$('prev').onclick = () => { if (page>1) { page--; list(); } };
$('next').onclick = () => { if (page<pages) { page++; list(); } };

refresh();
setInterval(() => { if (!document.hidden) refresh(); }, 5000);
