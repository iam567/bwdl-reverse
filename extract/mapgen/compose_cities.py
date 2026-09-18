"""把 36 座城贴到任意层的拼接图上。
   用法: compose_cities.py <stitched.jpg> <k源px→画布px> <scale> <out.jpg>
   城池尺寸基准（scale=1 时，画布按 1024/块 计）：帝都 240 → 寨堡 120，再乘 k/3.2 归一到当前层。"""
import json, sys, re
from PIL import Image, ImageDraw, ImageFont
d = '/private/tmp/claude-501/-Users-jingtaoliang-Library-Application-Support-Claude-scratch-workspaces-f957231f-5fbc-4d4a-963a-340bcb0af660-00815451-9bc4-4860-9586-919b36cb44d7-scratch-2026-09-18-ecf554/3967dfb5-862b-435e-accf-a939a7154915/scratchpad'
stitched, k, scale, out = sys.argv[1], float(sys.argv[2]), float(sys.argv[3]), sys.argv[4]
plate_path = sys.argv[5] if len(sys.argv) > 5 else None
plate = None
if plate_path:
    plate = Image.open(plate_path).convert('RGBA')
    solid = plate.split()[3].point(lambda a: 255 if a > 128 else 0)
    plate = plate.crop(solid.getbbox())
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
    ic = Image.open(d+'/'+f).convert('RGBA')
    ic = ic.crop(ic.split()[3].point(lambda a: 255 if a > 128 else 0).getbbox())
    tw = int(TIER_PX[tiers.get(c['id'],4)] * scale * (k/3.2)); th = int(ic.height*tw/ic.width)
    ic = ic.resize((tw,th), Image.LANCZOS)
    # 落地锚点：等距建筑底座菱形最宽的一行 ≈ 地面接触中心线。逐城计算，不用固定比例。
    am = ic.split()[3]
    widths = [sum(1 for x in range(0, tw, 2) if am.getpixel((x, y)) > 128) for y in range(0, th, 2)]
    lo = int(len(widths)*0.45)                            # 只在下半部分找，避开高塔
    base_row = (lo + max(range(lo, len(widths)), key=lambda i: widths[i])) * 2 if len(widths) > lo else int(th*0.7)
    base_row = max(base_row, int(th*0.55))                # 保底不高于 55%
    bg.alpha_composite(ic, (int(c['x']*k - tw/2), int(c['y']*k - base_row)))
    labels.append((c['name'], c['x']*k, c['y']*k + (th - base_row) + 6*(k/3.2)))   # 城池底边下方
# 标签最后统一画，避免被后画的城池盖住
if fnt:
    pad = int(6*(k/3.2)); rad = int(5*(k/3.2))
    for name, lx, ly in labels:
        bb = g.textbbox((0,0), name, font=fnt); w, h = bb[2]-bb[0], bb[3]-bb[1]
        if plate is not None:
            # 底图按文字高度定高（留 1.9 倍余量），宽度不小于文字 + 两侧留白
            ph = int(h*2.4); pw = max(int(plate.width*ph/plate.height), int(w + h*2.4))
            pl = plate.resize((pw, ph), Image.LANCZOS)
            bg.alpha_composite(pl, (int(lx-pw/2), int(ly)))
            g = ImageDraw.Draw(bg, 'RGBA')
            g.text((lx, ly + ph/2), name, font=fnt, fill=(248,236,214), anchor='mm')
        else:
            g.rounded_rectangle([lx-w/2-pad, ly, lx+w/2+pad, ly+h+pad*1.6], radius=rad,
                                fill=(28,20,12,215), outline=(214,176,110,235), width=max(1,int(1.5*(k/3.2))))
            g.text((lx, ly+pad*0.7), name, font=fnt, fill=(248,236,214), anchor='ma')
bg.convert('RGB').save(out, quality=93); print('输出', bg.size, '| k', k, '| scale', scale)
