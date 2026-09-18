"""导出合成时的城池图标框与城名铜牌框（1024 坐标系）。

不重新合成，只把 compose_cities.py 里的几何算一遍——
渲染端要精确避开铜牌，靠猜位置不行：铜牌的「下→上→右→左」避让
会把某些城的标签推到城池上方，正好是旗子要占的地方。

用法: export_boxes.py <work目录> <输出json>
"""
import json, sys, re, os
from PIL import Image, ImageDraw, ImageFont

WORK, OUT = sys.argv[1], sys.argv[2]
K = 6528 / 1024.0                      # 合成用的层级：L2
SCALE = 0.8
TIER_PX = {1:240, 2:210, 3:180, 4:158, 5:138, 6:120}

scr    = json.load(open(os.path.join(WORK,'city-screen.json')))
latest = json.load(open(os.path.join(WORK,'city_latest.json')))
src    = open(os.path.join(WORK,'sgapp','cities.js'), encoding='utf-8').read()
tiers  = {int(m.group(1)): int(m.group(3)) for m in re.finditer(r"(\d+):\['([^']+)',(\d),", src)}

plate = Image.open(os.path.join(WORK,'ui','label_bronze_mu6knuau.png')).convert('RGBA')
plate = plate.crop(plate.split()[3].point(lambda a: 255 if a>128 else 0).getbbox())

W = H = 6528
canvas = Image.new('RGBA',(8,8)); g = ImageDraw.Draw(canvas)
fs = max(14, int(26*(K/3.2)))
fnt = None
for cand in ('/System/Library/Fonts/STHeiti Medium.ttc','/System/Library/Fonts/Hiragino Sans GB.ttc'):
    try:
        t = ImageFont.truetype(cand, fs)
        if g.textbbox((0,0),'洛阳',font=t)[2] > fs: fnt = t; break
    except Exception: pass
assert fnt, '找不到中文字体'

ground = Image.open(os.path.join(WORK,'ui','ground_plate_mu6l1goc.png')).convert('RGBA')
ground = ground.crop(ground.split()[3].point(lambda a: 255 if a>128 else 0).getbbox())

labels, city_boxes = [], {}
for c in sorted(scr, key=lambda q: q['y']):
    f = latest.get(str(c['id']))
    if not f: continue
    ic = Image.open(os.path.join(WORK, f)).convert('RGBA')
    ic = ic.crop(ic.split()[3].point(lambda a: 255 if a>128 else 0).getbbox())
    tw = int(TIER_PX[tiers.get(c['id'],4)] * SCALE * (K/3.2)); th = int(ic.height*tw/ic.width)
    base_row = int(th*0.66)
    pw_ = int(tw*1.35); ph_ = int(ground.height*pw_/ground.width)
    px0, py0 = int(c['x']*K - tw/2), int(c['y']*K - base_row)
    city_boxes[c['id']] = (px0, py0, px0+tw, py0+th)
    plate_bottom = c['y']*K + ph_/2
    labels.append((c['name'], c['id'], c['x']*K,
                   max(c['y']*K + (th - base_row), plate_bottom) + 4*(K/3.2)))

def overlap(a,b):
    return max(0, min(a[2],b[2])-max(a[0],b[0])) * max(0, min(a[3],b[3])-max(a[1],b[1]))

gap = int(6*(K/3.2))
placed, label_boxes = [], {}
forced = []
for name, cid, lx, ly in labels:
    bb = g.textbbox((0,0), name, font=fnt); w, h = bb[2]-bb[0], bb[3]-bb[1]
    ph = int(h*2.4); pw = max(int(plate.width*ph/plate.height), int(w + h*2.4))
    cb = city_boxes[cid]; cyc = (cb[1]+cb[3])/2
    cands = [(lx-pw/2, ly), (lx-pw/2, cb[1]-ph-gap),
             (cb[2]+gap, cyc-ph/2), (cb[0]-gap-pw, cyc-ph/2)]
    others = [b for i,b in city_boxes.items() if i != cid] + placed
    best = None
    for i,(x,y) in enumerate(cands):
        x = max(0, min(W-pw, x)); y = max(0, min(H-ph, y))
        r = (x, y, x+pw, y+ph)
        cost = sum(overlap(r,o) for o in others)
        if best is None or cost < best[0]: best = (cost, r, i)
        if cost == 0: break
    cost, r, which = best
    placed.append(r); label_boxes[cid] = r
    if which != 0: forced.append((name, ['下','上','右','左'][which]))

to1024 = lambda r: [round(v/K, 2) for v in r]
json.dump({'space': 1024,
           'cities': {str(i): to1024(b) for i,b in city_boxes.items()},
           'labels': {str(i): to1024(b) for i,b in label_boxes.items()}},
          open(OUT,'w'), ensure_ascii=False, indent=0)
print('导出 %d 城 / %d 标签 → %s' % (len(city_boxes), len(label_boxes), OUT))
print('标签被推离默认「下」位的：', '、'.join('%s(%s)'%t for t in forced) or '无')
