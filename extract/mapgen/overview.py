"""大地图总览：地形 + 土路 + 势力色城池标记。
   标记职责是「一眼看清归属与规模」，不是展示城池美术。"""
import json, sys, glob, os, re
from PIL import Image, ImageDraw, ImageFont
d, out = sys.argv[1], sys.argv[2]
UP = 2                                    # 超采样倍数，边缘更干净

pts  = json.load(open(d+'/map-coords.json'))
fit  = json.load(open(d+'/map-fit.json'))
src  = open(d+'/sgapp/cities.js', encoding='utf-8').read()
tiers= {int(m.group(1)): int(m.group(3)) for m in re.finditer(r"(\d+):\['([^']+)',(\d),", src)}
rows = [l.split(',') for l in open(d+'/sango/res/lib115.jg', encoding='utf-8').read().splitlines() if l]
ruler= {int(r[0]): r[2] for r in rows}
RULERS = sorted({v for v in ruler.values() if v})

# 源码 c[] 势力色板
PAL = [0xFFFFFF,0xFFFF00,16741219,3250840,10232295,11339784,16753920,2181863,
       15147367,2211047,9299745,0x636363]
rgb = lambda v: ((v>>16)&255,(v>>8)&255,v&255)

terr = sorted(glob.glob(d+'/terrain/terrain_main_*.png'), key=os.path.getmtime)[-1]
bg = Image.open(terr).convert('RGB')
bg = bg.resize((bg.width*UP, bg.height*UP), Image.LANCZOS)
W,H = bg.size
g = ImageDraw.Draw(bg, 'RGBA')
sx = W/(fit['x1']-fit['x0']); sy = H/(fit['y1']-fit['y0'])

fnt=None
for cand in ('/System/Library/Fonts/STHeiti Medium.ttc',
             '/System/Library/Fonts/Hiragino Sans GB.ttc'):
    try:
        t=ImageFont.truetype(cand, 15*UP)
        if g.textbbox((0,0),'洛阳',font=t)[2] > 15*UP: fnt=t; break
    except Exception: pass

TIER_R = {1:13, 2:11, 3:9, 4:8, 5:7, 6:6}     # 标记半径（UP=1 时）
for p in sorted(pts, key=lambda q: q['y']):
    cx = (p['px']+4-fit['x0'])*sx; cy = (p['py']+4-fit['y0'])*sy
    tier = tiers.get(p['id'], 4)
    R = TIER_R[tier]*UP
    who = ruler.get(p['id'])
    ci = (RULERS.index(who)+1) if who in RULERS else 0
    col = rgb(PAL[ci % len(PAL)])
    g.ellipse([cx-R-3*UP, cy-R-3*UP, cx+R+3*UP, cy+R+3*UP], fill=(20,16,10,110))   # 柔和投影
    g.ellipse([cx-R, cy-R, cx+R, cy+R], fill=col, outline=(250,248,242), width=int(2.2*UP))
    if tier <= 2:                                          # 帝都/州都 加内环强调
        g.ellipse([cx-R+4*UP, cy-R+4*UP, cx+R-4*UP, cy+R-4*UP],
                  outline=(250,248,242), width=int(1.6*UP))
    if fnt:
        t = p['name']
        bb = g.textbbox((0,0), t, font=fnt); w,h = bb[2]-bb[0], bb[3]-bb[1]
        ty = cy + R + 4*UP
        g.rounded_rectangle([cx-w/2-5*UP, ty, cx+w/2+5*UP, ty+h+7*UP],
                            radius=4*UP, fill=(22,17,10,205))
        g.text((cx, ty+3*UP), t, font=fnt, fill=(246,238,222), anchor='ma')

bg = bg.resize((W//UP, H//UP), Image.LANCZOS)
bg.save(out, quality=94)
print('输出', bg.size)
