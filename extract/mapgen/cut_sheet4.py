# -*- coding: utf-8 -*-
"""四格地块表（2 列 × 2 行）→ 某个主题的 64×64 地块。

   四格约定（左上→右上→左下→右下）：
     1 底（满铺）  2 水（满铺或池塘）  3 山（只占中间）  4 树（只占中间）

   满铺的那两张模型接不上自己，这里用半格位移 + 边缘混合修；
   带特征的两张不能位移（一位移就把山/树劈成两半），原样保留——
   它们的四边本来就是第 1 格的底，接缝靠那圈底色自然消掉。

   写出 ground / grass / water / rock / forest / ambush 六个槽位
   （战棋层只读 ground/water/rock/forest，另两个给策略层留着）。

   用法: cut_sheet4.py <表.png> <主题名> [输出目录]
"""
import sys, os
from PIL import Image

SRC, THEME = sys.argv[1], sys.argv[2]
ROOT = os.path.normpath(os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..'))
OUT = sys.argv[3] if len(sys.argv) > 3 else os.path.join(ROOT, 'assets/battletile/px')
os.makedirs(OUT, exist_ok=True)

N = 64                      # 成品边长，和格子一致
CELL = 512


def seamless(im):
    """半格位移 + 边缘混合：位移版的四边正好是原图中心，天然连续"""
    W, H = im.size
    rolled = Image.new('RGB', (W, H))
    rolled.paste(im.crop((W // 2, H // 2, W, H)), (0, 0))
    rolled.paste(im.crop((0, H // 2, W // 2, H)), (W - W // 2, 0))
    rolled.paste(im.crop((W // 2, 0, W, H // 2)), (0, H - H // 2))
    rolled.paste(im.crop((0, 0, W // 2, H // 2)), (W - W // 2, H - H // 2))
    mask = Image.new('L', (W, H)); mp = mask.load()
    for y in range(H):
        dy = abs(2.0 * y / (H - 1) - 1.0)
        for x in range(W):
            d = max(abs(2.0 * x / (W - 1) - 1.0), dy)
            t = 0.0 if d < 0.55 else min(1.0, (d - 0.55) / 0.45)
            mp[x, y] = int(255 * (t * t * (3 - 2 * t)))
    return Image.composite(rolled, im, mask)


sheet = Image.open(SRC).convert('RGB')
if sheet.size != (2 * CELL, 2 * CELL):
    sheet = sheet.resize((2 * CELL, 2 * CELL), Image.LANCZOS)

def cell(i):
    c, r = i % 2, i // 2
    return sheet.crop((c * CELL, r * CELL, c * CELL + CELL, r * CELL + CELL))

def ring(im, w=24):
    """取四边那一圈的平均色——用来看这格是满铺还是「四边同底、中间放东西」"""
    px = im.load(); W, H = im.size; acc = [0, 0, 0]; n = 0
    for y in range(H):
        inner = w <= y < H - w
        for x in range(0, W, 2):
            if inner and w <= x < W - w:
                continue
            r, g, b = px[x, y]; acc[0] += r; acc[1] += g; acc[2] += b; n += 1
    return [c / n for c in acc]


base_ring = ring(cell(0))


def is_feature(i):
    """边缘和底格一样 → 特征格，位移会把山/树/池塘劈开，不能动"""
    d = sum(abs(a - b) for a, b in zip(ring(cell(i)), base_ring)) / 3.0
    return d < 18.0


def zoom_center(im, z):
    """以中心放大再切回原尺寸——山和树在格子里占得太小时用。
       边缘放大后还是底色，所以四边照样能和底格接上。"""
    W, H = im.size
    big = im.resize((int(W * z), int(H * z)), Image.LANCZOS)
    x = (big.width - W) // 2; y = (big.height - H) // 2
    return big.crop((x, y, x + W, y + H))


def edge_blend(im, base, w=40):
    """把最外一圈渐变回底格，保证四边严丝合缝"""
    W, H = im.size
    mask = Image.new('L', (W, H)); mp = mask.load()
    for y in range(H):
        dy = min(y, H - 1 - y)
        for x in range(W):
            d = min(x, W - 1 - x, dy)
            t = 1.0 if d >= w else d / float(w)
            mp[x, y] = int(255 * (t * t * (3 - 2 * t)))
    return Image.composite(im, base, mask)


def fill_from_center(im, keep=0.42):
    """只留中间那块（池塘的水面），放大到满格再修接缝——
       一格一个小池塘铺开像麻点，水要连成片"""
    W, H = im.size
    k = int(min(W, H) * keep)
    c = im.crop(((W - k) // 2, (H - k) // 2, (W + k) // 2, (H + k) // 2))
    return seamless(c.resize((W, H), Image.LANCZOS))


ZOOM = float(os.environ.get('SHEET4_ZOOM', '1.0'))      # 山/树放大倍数
WATER_CENTER = os.environ.get('SHEET4_WATER') == 'center'

# 槽位 → 第几格（要不要修接缝按边缘自动判）
SLOTS = {'ground': 0, 'grass': 0, 'water': 1, 'rock': 2, 'forest': 3, 'ambush': 3}

ground = seamless(cell(0))

for slot, i in SLOTS.items():
    im = cell(i)
    fix = (i == 0) or not is_feature(i)
    if slot == 'water' and WATER_CENTER and not fix:
        im = fill_from_center(im); fix = True
    elif fix:
        im = seamless(im)
    elif ZOOM > 1.0:
        im = edge_blend(zoom_center(im, ZOOM), ground)
    im.resize((N, N), Image.LANCZOS).save(os.path.join(OUT, 'bt_%s_%s.png' % (THEME, slot)))
    print('bt_%s_%s.png  ← 第 %d 格%s' % (THEME, slot, i + 1,
          '（修接缝）' if fix else ('（放大 %.2f×）' % ZOOM if ZOOM > 1 else '')))
