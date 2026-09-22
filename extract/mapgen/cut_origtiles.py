# -*- coding: utf-8 -*-
"""原版地块表（lib31 的高清重画，6 列 × 4 行 / 24 格）→ 各主题的 64×64 地块。

   原版战棋层其实只用六张图（c.java 2968 那个 switch）：
     码 0        → 块 6   素土
     码 5        → 块 7   素土上一棵大树 ← 这就是「林地」
     码 6        → 块 13  水
     码 7~12     → 块 9   绿坡上一座暗峰 ← 六个码共用
     码 1/3/4    → 块 16  白墙小屋（我们用自己画的武库/粮仓/药庐）
     码 2        → 块 17  木构大屋（我们用自己画的城楼）
   （原版码 5 是「先画块 5 再画块 7」，但块 7 不透明，第一笔完全被盖住——是段死代码。）

   24 格里多出来的雪景、灌木、灰岩正好分给别的主题，不用再生成。

   满铺的那几张（素土、雪地、水）模型做不到自己接自己，这里用半格位移 + 边缘混合修；
   带特征的那几张（树、山）不能位移——一位移就把树劈成两半——原样保留，
   它们的四边本来就是同一种底，接缝靠那圈底色自然消掉。

   用法: cut_origtiles.py <表.png> [输出目录]
"""
import sys, os
from PIL import Image

SRC = sys.argv[1]
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
OUT = sys.argv[2] if len(sys.argv) > 2 else os.path.join(ROOT, 'assets/battletile/px')
os.makedirs(OUT, exist_ok=True)

N = 64                      # 成品边长，和格子一致
COLS, ROWS, CELL = 6, 4, 256

# 主题 → {槽位: 原块号}。满铺的槽位列在 SEAMLESS 里
THEME_BLOCKS = {
    'zhongyuan': {'ground': 6, 'grass': 0, 'forest': 7,  'rock': 9,  'water': 13, 'ambush': 7},
    'jiangnan':  {'ground': 6, 'grass': 1, 'forest': 7,  'rock': 9,  'water': 13, 'ambush': 7},
    'nanzhong':  {'ground': 6, 'grass': 1, 'forest': 8,  'rock': 10, 'water': 18, 'ambush': 8},
    'saibei':    {'ground': 11,'grass': 15,'forest': 12, 'rock': 14, 'water': 13, 'ambush': 12},
    'xiyu':      {'ground': 6, 'grass': 4, 'forest': 4,  'rock': 19, 'water': 13, 'ambush': 4},
}
SEAMLESS = {'ground', 'grass', 'water'}     # 这几张要修接缝；树和山不动

sheet = Image.open(SRC).convert('RGB')
if sheet.size != (COLS * CELL, ROWS * CELL):
    sheet = sheet.resize((COLS * CELL, ROWS * CELL), Image.LANCZOS)


def cell(block):
    """第 block 号原块在表里的位置：前 21 格按 0..20 顺排"""
    i = block if block < 21 else 0
    r, c = divmod(i, COLS)
    return sheet.crop((c * CELL, r * CELL, c * CELL + CELL, r * CELL + CELL))


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



if __name__ == '__main__':

    n = 0
    for theme, slots in THEME_BLOCKS.items():
        for slot, block in slots.items():
            im = cell(block)
            if slot in SEAMLESS:
                im = seamless(im)
            im.resize((N, N), Image.BOX).save(os.path.join(OUT, 'bt_%s_%s.png' % (theme, slot)))
            n += 1
    print('地块 %d 张（%d 主题 × %d 槽位）→ %s' % (n, len(THEME_BLOCKS), len(next(iter(THEME_BLOCKS.values()))), OUT))
