"""把 36 座城贴到任意层的拼接图上。
   用法: compose_cities.py <stitched.jpg> <k源px→画布px> <scale> <out.jpg>
   城池尺寸基准（scale=1 时，画布按 1024/块 计）：帝都 240 → 寨堡 120，再乘 k/3.2 归一到当前层。"""
import json, sys, re
from PIL import Image, ImageDraw, ImageFont, ImageFilter
d = '/private/tmp/claude-501/-Users-jingtaoliang-Library-Application-Support-Claude-scratch-workspaces-f957231f-5fbc-4d4a-963a-340bcb0af660-00815451-9bc4-4860-9586-919b36cb44d7-scratch-2026-09-18-ecf554/3967dfb5-862b-435e-accf-a939a7154915/scratchpad'
stitched, k, scale, out = sys.argv[1], float(sys.argv[2]), float(sys.argv[3]), sys.argv[4]
plate_path = sys.argv[5] if len(sys.argv) > 5 else None
ground_path = sys.argv[6] if len(sys.argv) > 6 else None
ground = None
if ground_path:
    ground = Image.open(ground_path).convert('RGBA')
    ground = ground.crop(ground.split()[3].point(lambda a: 255 if a > 128 else 0).getbbox())
plate = None
if plate_path:
    plate = Image.open(plate_path).convert('RGBA')
    solid = plate.split()[3].point(lambda a: 255 if a > 128 else 0)
    plate = plate.crop(solid.getbbox())
scr = json.load(open(d+'/city-screen.json')); latest = json.load(open(d+'/city_latest.json'))
ADJ = json.load(open(d+'/adjacency.json'))
POS = {c['id']: (c['x']*k, c['y']*k) for c in scr}
import math
src = open(d+'/sgapp/cities.js', encoding='utf-8').read()
tiers = {int(m.group(1)): int(m.group(3)) for m in re.finditer(r"(\d+):\['([^']+)',(\d),", src)}
TIER_PX = {1:240, 2:210, 3:180, 4:158, 5:138, 6:120}
bg = Image.open(stitched).convert('RGBA')
# 在地形上找一块 ≥95% 是路面的 96×96 窗口，作为地坪纹理源
_rgb = bg.convert('RGB'); _px = _rgb.load(); _W, _H = _rgb.size
def _is_road(x, y):
    r, g, b = _px[x, y]; return r>170 and g>135 and b<150 and (r-b)>50 and abs(r-g)<55
road_patch = None
for _y in range(200, _H-300, 48):
    for _x in range(200, _W-300, 48):
        _n = sum(1 for _dx in range(0, 96, 6) for _dy in range(0, 96, 6) if _is_road(_x+_dx, _y+_dy))
        if _n >= 243:                                   # 256 采样点中 ≥95%
            road_patch = _rgb.crop((_x, _y, _x+96, _y+96)).convert('RGBA'); break
    if road_patch: break
if road_patch is None:
    road_patch = Image.new('RGBA', (96, 96), (214, 180, 120, 255))     # 兜底：平均土路色
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
    # 落地锚点：底座菱形中心（含护城河）≈ 图高 76%。「最宽行」会被护城河骗到底边，弃用。
    base_row = int(th * 0.66)                            # 城压低，底座坐在地坪中心
    # 地坪：路口被城的地坪整个盖住，路走到地坪边缘即止，玩家不会以为能绕城而过
    pw_ = int(tw * 1.35)
    # 先把每条邻接边的路头"接"进地坪：从城心沿边方向铺一段土路纹理，盖住路头与地坪之间的草地
    cx_, cy_ = c['x']*k, c['y']*k
    road_w = int(tw * 0.22)                              # 路宽 ≈ 城宽 22%，与地形上的土路相当
    reach  = int(pw_ * 0.62)                             # 铺到地坪半径外一点，保证与真实路头重叠
    for nb in ADJ[str(c['id'])]['adj']:
        if nb not in POS: continue
        ang = math.atan2(POS[nb][1]-cy_, POS[nb][0]-cx_)
        seg_len = reach
        seg = road_patch.resize((seg_len, road_w), Image.LANCZOS)
        # 沿路方向羽化：靠城一端实心，远端渐隐，避免在真实路面上留硬边
        m = Image.new('L', (seg_len, road_w), 0); md = ImageDraw.Draw(m)
        for i in range(seg_len):
            a = 255 if i < seg_len*0.55 else int(255*(1-(i-seg_len*0.55)/(seg_len*0.45)))
            md.line([(i,0),(i,road_w)], fill=a)
        m = m.filter(ImageFilter.GaussianBlur(road_w*0.18))
        seg.putalpha(m)
        seg = seg.rotate(-math.degrees(ang), expand=True, resample=Image.BICUBIC)
        # 段的起点在城心，沿 ang 方向延伸：把旋转后图的中心放到 城心 + 半段长
        mx = cx_ + (seg_len/2)*math.cos(ang); my = cy_ + (seg_len/2)*math.sin(ang)
        bg.alpha_composite(seg, (int(mx - seg.width/2), int(my - seg.height/2)))
    if ground is not None:
        ph_ = int(ground.height * pw_ / ground.width)
        plate_g = ground.resize((pw_, ph_), Image.LANCZOS)
    else:
        ph_ = int(pw_ * 0.5)
        plate_g = road_patch.resize((pw_, ph_), Image.LANCZOS)
        mask = Image.new('L', (pw_, ph_), 0)
        ImageDraw.Draw(mask).ellipse([0, 0, pw_-1, ph_-1], fill=255)
        plate_g.putalpha(mask.filter(ImageFilter.GaussianBlur(pw_ * 0.06)))
    bg.alpha_composite(plate_g, (int(c['x']*k - pw_/2), int(c['y']*k - ph_/2)))
    bg.alpha_composite(ic, (int(c['x']*k - tw/2), int(c['y']*k - base_row)))
    plate_bottom = c['y']*k + ph_/2
    labels.append((c['name'], c['x']*k, max(c['y']*k + (th - base_row), plate_bottom) + 4*(k/3.2)))   # 地坪下缘之下
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
