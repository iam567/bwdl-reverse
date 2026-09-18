"""把 36 座城贴到第一层拼接图上。城池尺寸按比例尺缩小，按等级分档。"""
import json, sys, re
from PIL import Image
d, out = sys.argv[1], sys.argv[2]
SCALE = float(sys.argv[3]) if len(sys.argv)>3 else 1.0
g = json.load(open(d+'/tiles/grid.json')); k = 1024/(g['CELL']+2*g['M'])   # 源px→画布px = 3.2
scr = json.load(open(d+'/city-screen.json'))
latest = json.load(open(d+'/city_latest.json'))
src = open(d+'/sgapp/cities.js', encoding='utf-8').read()
tiers = {int(m.group(1)): int(m.group(3)) for m in re.finditer(r"(\d+):\['([^']+)',(\d),", src)}
bg = Image.open(d+'/stitched_L1.jpg').convert('RGBA'); W,H = bg.size
# 帝都 240 → 寨堡 120（在 1024/块 的画布尺度上）
TIER_PX = {1:240, 2:210, 3:180, 4:158, 5:138, 6:120}
for c in sorted(scr, key=lambda q: q['y']):
    cid = c['id']; f = latest.get(str(cid))
    if not f: continue
    ic = Image.open(d+'/'+f).convert('RGBA'); ic = ic.crop(ic.getbbox())
    tw = int(TIER_PX[tiers.get(cid,4)] * SCALE); th = int(ic.height*tw/ic.width)
    ic = ic.resize((tw,th), Image.LANCZOS)
    cx = c['x']*k; cy = c['y']*k
    bg.alpha_composite(ic, (int(cx-tw/2), int(cy-th*0.62)))
bg.convert('RGB').save(out, quality=93); print('输出', bg.size)
