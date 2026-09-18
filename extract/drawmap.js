const fs = require('fs');
const dir = process.argv[2];
const pts = require(dir + '/map-coords.json');
const A   = require(dir + '/adjacency.json');
const P = {}; for (const p of pts) P[p.id] = p;

const avg = a => a.reduce((x,y)=>x+y,0) / a.length;
let adjD = [], allD = [];
for (let i=1;i<=36;i++) for (let j=i+1;j<=36;j++) {
  const d = Math.hypot(P[i].x-P[j].x, P[i].y-P[j].y);
  allD.push(d);
  if (A[i].adj.includes(j)) adjD.push(d);
}
console.log('邻接城对 平均格距:', avg(adjD).toFixed(1));
console.log('全部城对 平均格距:', avg(allD).toFixed(1));
const long = [];
for (let i=1;i<=36;i++) for (const j of A[i].adj) if (i<j)
  long.push({ a:P[i].name, b:P[j].name, d:Math.hypot(P[i].x-P[j].x, P[i].y-P[j].y) });
long.sort((x,y)=>y.d-x.d);
console.log('最长的邻接边（几何上跨度最大，最可能"不合地理"）:');
for (const e of long.slice(0,5)) console.log('   ' + e.a + ' ↔ ' + e.b + '  ' + e.d.toFixed(1) + ' 格');

const S = 26, PAD = 64, W = 28*S + PAD*2, H = 30*S + PAD*2;
const X = p => PAD + p.x*S, Y = p => PAD + p.y*S;
const cut = new Set([1,4,5,9,11,22,28,31,33,35]);
let s = `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}">`;
s += `<rect width="100%" height="100%" fill="#15120e"/>`;
for (let i=1;i<=36;i++) for (const j of A[i].adj) if (i<j)
  s += `<line x1="${X(P[i])}" y1="${Y(P[i])}" x2="${X(P[j])}" y2="${Y(P[j])}" stroke="#6b5a3e" stroke-width="2"/>`;
for (let i=1;i<=36;i++) {
  const p = P[i], deg = A[i].adj.length;
  const fill = deg === 1 ? '#7a4a3a' : (cut.has(i) ? '#c8a45c' : '#2f2a22');
  const tc   = cut.has(i) ? '#1a1509' : '#efe7d8';
  s += `<circle cx="${X(p)}" cy="${Y(p)}" r="12" fill="${fill}" stroke="#d9c9a8" stroke-width="1.5"/>`;
  s += `<text x="${X(p)}" y="${Y(p)+4}" font-size="11" fill="${tc}" text-anchor="middle" font-family="sans-serif">${i}</text>`;
  s += `<text x="${X(p)}" y="${Y(p)-17}" font-size="14" fill="#cdbfa8" text-anchor="middle" font-family="sans-serif">${p.name}</text>`;
}
s += `<text x="${PAD}" y="30" font-size="17" fill="#c8a45c" font-family="sans-serif">霸王的大陆 · 36 城连通图（金=咽喉城　红=死路）</text>`;
s += '</svg>';
fs.writeFileSync(dir + '/citymap.svg', s);
console.log('SVG 已生成');
