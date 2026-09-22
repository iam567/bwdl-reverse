# -*- coding: utf-8 -*-
"""用 assets/battletile/px 里的成品地块铺一张战场预览图，看拼接效果。

   格子编号 → 槽位，照 BattleField.cs 的 SLOT（也就是原版 c.O() 那张表）：
     0 → ground   1 → wuku   2 → castle   3 → cang   4 → yao
     5 → forest   6 → water  7~12 → rock

   用法: preview_theme.py <主题> <城池ID> <输出png> [每格像素]
"""
import sys, os
from PIL import Image

THEME, CID, OUT = sys.argv[1], sys.argv[2], sys.argv[3]
N = int(sys.argv[4]) if len(sys.argv) > 4 else 32
ROOT = os.path.normpath(os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..'))
PX = os.path.join(ROOT, 'assets/battletile/px')

SLOT = {0: 'ground', 1: 'wuku', 2: 'castle', 3: 'cang', 4: 'yao',
        5: 'forest', 6: 'water'}
for v in range(7, 13):
    SLOT[v] = 'rock'

cache = {}
def tile(slot):
    if slot not in cache:
        im = Image.open(os.path.join(PX, 'bt_%s_%s.png' % (THEME, slot))).convert('RGBA')
        cache[slot] = im.resize((N, N), Image.LANCZOS)
    return cache[slot]

grid = []
with open(os.path.join(ROOT, 'extract/data/battlemaps/%s.map' % CID)) as f:
    for line in f:
        line = line.strip()
        if line:
            grid.append([int(v) for v in line.split(',') if v != ''])
H, W = len(grid), max(len(r) for r in grid)

out = Image.new('RGBA', (W * N, H * N))
for y, row in enumerate(grid):
    for x, v in enumerate(row):
        slot = SLOT.get(v, 'ground')
        if slot in ('wuku', 'castle', 'cang', 'yao'):
            out.paste(tile('ground'), (x * N, y * N))
            t = tile(slot)
            out.paste(t, (x * N, y * N), t)
        else:
            out.paste(tile(slot), (x * N, y * N))
out.save(OUT)
print('%s / %s → %s  (%d×%d 格)' % (THEME, CID, OUT, W, H))
