# -*- coding: utf-8 -*-
"""还原战棋战场（画面 20）。

   战场不是整图，是逐格铺 16×16 的地形块：
   地形块条 = res/lib31.jg（336×16，21 个块），
   格子编号 = res/map/<城ID>.map，纯文本 CSV。

   绘制前有一层重映射，照抄 c.O()：
     0→6 / 1,3,4→16 / 2→17 / 6→13 / 7..12→9 / 5 要叠两层（先 5 再 7）
   其余编号直接当块号用。

   用法: render_battlemap.py <城池ID> <输出png>
"""
import sys, os, csv
from PIL import Image

CID, OUT = sys.argv[1], sys.argv[2]
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))

tiles = Image.open(os.path.join(ROOT, 'extract/images/lib31.png')).convert('RGBA')
N = tiles.width // 16
grid = []
with open(os.path.join(ROOT, 'extract/data/battlemaps/%s.map' % CID)) as f:
    for line in f:
        line = line.strip()
        if line:
            grid.append([int(v) for v in line.split(',') if v != ''])
H, W = len(grid), max(len(r) for r in grid)
print('战场 %s.map: %d 列 × %d 行' % (CID, W, H))

def remap(v):
    """c.O() 里那张 switch 表。返回 (底层块, 叠加块或 None)"""
    if v == 0: return 6, None
    if v in (1, 3, 4): return 16, None
    if v == 2: return 17, None
    if v == 5: return 5, 7          # 两层：先画 5，再盖 7
    if v == 6: return 13, None
    if 7 <= v <= 12: return 9, None
    return v, None

out = Image.new('RGBA', (W*16, H*16), (0, 0, 0, 255))
used = set()
for y, row in enumerate(grid):
    for x, raw in enumerate(row):
        base, over = remap(raw & 0xF)
        used.add(raw & 0xF)
        for t in (base, over):
            if t is None or t >= N: continue
            out.alpha_composite(tiles.crop((t*16, 0, t*16+16, 16)), (x*16, y*16))

out.convert('RGB').save(OUT)
print('用到的原始编号:', sorted(used))
print('输出', OUT, out.size)
