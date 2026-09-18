import json, sys, glob, os, re
from PIL import Image, ImageDraw, ImageFont
d, out = sys.argv[1], sys.argv[2]
SCALE = float(sys.argv[3]) if len(sys.argv)>3 else 1.0
LABEL = len(sys.argv)>4 and sys.argv[4]=='label'

pts    = json.load(open(d+'/map-coords.json'))
latest = json.load(open(d+'/city_latest.json'))
fit    = json.load(open(d+'/map-fit.json'))
src    = open(d+'/sgapp/cities.js', encoding='utf-8').read()
tiers  = {int(m.group(1)): int(m.group(3)) for m in re.finditer(r"(\d+):\['([^']+)',(\d),", src)}

terr = sorted(glob.glob(d+'/terrain/terrain_main_*.png'), key=os.path.getmtime)[-1]
bg = Image.open(terr).convert('RGBA'); W,H = bg.size
sx = W/(fit['x1']-fit['x0']); sy = H/(fit['y1']-fit['y0'])
TIER_W = {1:.105, 2:.090, 3:.076, 4:.064, 5:.054, 6:.046}

placed=[]
for p in sorted(pts, key=lambda q: q['y']):
    f = latest.get(str(p['id']))
    if not f: continue
    ic = Image.open(d+'/'+f).convert('RGBA'); ic = ic.crop(ic.getbbox())
    tw = max(8, int(W*TIER_W.get(tiers.get(p['id'],4),.064)*SCALE))
    th = int(ic.height*tw/ic.width)
    ic = ic.resize((tw,th), Image.LANCZOS)
    cx = (p['px']+4-fit['x0'])*sx; cy = (p['py']+4-fit['y0'])*sy
    bg.alpha_composite(ic, (int(cx-tw/2), int(cy-th*0.60)))
    placed.append((p, cx, cy))

if LABEL:
    g = ImageDraw.Draw(bg)
    fnt = None
    for cand in ('/System/Library/Fonts/STHeiti Medium.ttc',
                 '/System/Library/Fonts/Hiragino Sans GB.ttc',
                 '/System/Library/Fonts/PingFang.ttc'):
        try:
            t = ImageFont.truetype(cand, 30)
            if g.textbbox((0,0), '洛阳', font=t)[2] > 30:   # 确认真的能渲染中文
                fnt = t; break
        except Exception:
            pass
    if fnt is None: fnt = ImageFont.load_default()
    for p,cx,cy in placed:
        t = p['name']
        bb = g.textbbox((0,0), t, font=fnt)
        w,h = bb[2]-bb[0], bb[3]-bb[1]
        g.rectangle([cx-w/2-8, cy+8, cx+w/2+8, cy+16+h], fill=(24,18,10,215))
        g.text((cx, cy+11), t, font=fnt, fill=(245,235,215), anchor='ma')
bg.convert('RGB').save(out, quality=93)
print('输出', bg.size)
