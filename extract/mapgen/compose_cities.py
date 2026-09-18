"""把 36 座城贴到任意层的拼接图上。
   用法: compose_cities.py <stitched.jpg> <k源px→画布px> <scale> <out.jpg>
   城池尺寸基准（scale=1 时，画布按 1024/块 计）：帝都 240 → 寨堡 120，再乘 k/3.2 归一到当前层。"""
import json, sys, re
from PIL import Image, ImageDraw, ImageFont
d = '/private/tmp/claude-501/-Users-jingtaoliang-Library-Application-Support-Claude-scratch-workspaces-f957231f-5fbc-4d4a-963a-340bcb0af660-00815451-9bc4-4860-9586-919b36cb44d7-scratch-2026-09-18-ecf554/3967dfb5-862b-435e-accf-a939a7154915/scratchpad'
stitched, k, scale, out = sys.argv[1], float(sys.argv[2]), float(sys.argv[3]), sys.argv[4]
scr = json.load(open(d+'/city-screen.json')); latest = json.load(open(d+'/city_latest.json'))
src = open(d+'/sgapp/cities.js', encoding='utf-8').read()
tiers = {int(m.group(1)): int(m.group(3)) for m in re.finditer(r"(\d+):\['([^']+)',(\d),", src)}
TIER_PX = {1:240, 2:210, 3:180, 4:158, 5:138, 6:120}
bg = Image.open(stitched).convert('RGBA')
g = ImageDraw.Draw(bg, 'RGBA')
fs = max(14, int(26 * (k/3.2)))                    # 标签字号随层级放大
fnt = None
for cand in ('/System/Library/Fonts/STHeiti Medium.ttc','/System/Library/Fonts/Hiragino Sans GB.ttc'):
    try:
        t = ImageFont.truetype(cand, fs)
        if g.textbbox((0,0),'洛阳',font=t)[2] > fs: fnt = t; break
    except Exception: pass
labels = []
for c in sorted(scr, key=lambda q: q['y']):
    f = latest.get(str(c['id']))
    if not f: continue
    ic = Image.open(d+'/'+f).convert('RGBA'); ic = ic.crop(ic.getbbox())
    tw = int(TIER_PX[tiers.get(c['id'],4)] * scale * (k/3.2)); th = int(ic.height*tw/ic.width)
    ic = ic.resize((tw,th), Image.LANCZOS)
    bg.alpha_composite(ic, (int(c['x']*k - tw/2), int(c['y']*k - th*0.62)))
    labels.append((c['name'], c['x']*k, c['y']*k + th*0.38 + 6*(k/3.2)))   # 城池底边下方
# 标签最后统一画，避免被后画的城池盖住
if fnt:
    pad = int(6*(k/3.2)); rad = int(5*(k/3.2))
    for name, lx, ly in labels:
        bb = g.textbbox((0,0), name, font=fnt); w, h = bb[2]-bb[0], bb[3]-bb[1]
        g.rounded_rectangle([lx-w/2-pad, ly, lx+w/2+pad, ly+h+pad*1.6], radius=rad,
                            fill=(28,20,12,215), outline=(214,176,110,235), width=max(1,int(1.5*(k/3.2))))
        g.text((lx, ly+pad*0.7), name, font=fnt, fill=(248,236,214), anchor='ma')
bg.convert('RGB').save(out, quality=93); print('输出', bg.size, '| k', k, '| scale', scale)
