# -*- coding: utf-8 -*-
"""用高清地块铺一张战场。

   格数与地形码一条不动，照抄原版 .map；换的只是每个码画成什么。
   码 → 槽位的对应（见 GAME_LOGIC §8，括号是移动消耗 平/水/山）：

     0            → ground  平地   (2/3/3)
     1            → wuku    武库   (2/3/3)   ← 其他版本里是武器店，本版未接效果
     3            → cang    常平仓 (2/3/3)   ← 同上，金粮店
     4            → yao     药庐   (2/3/3)   ← 疗伤点，本版已实现
     2            → block   障碍   (6/6/6)
     5            → ambush  埋伏点 (5/5/5)
     6            → water   水     (5/3/6)
     7…12         → r1…r6   山林   (5/6/3)  六个码消耗相同，给六种长相

   用法: compose_battlefield.py <城池ID> <主题> <地块目录> <输出png> [格边长=64]
"""
import sys, os, glob
from PIL import Image

CID, THEME, TILEDIR, OUT = sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4]
CELL = int(sys.argv[5]) if len(sys.argv) > 5 else 64
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))

SLOT = {0:'ground', 1:'wuku', 2:'block', 3:'cang', 4:'yao', 5:'ambush', 6:'water'}
for c in range(7, 13): SLOT[c] = 'r%d' % (c - 6)

def load(slot):
    fs = glob.glob(os.path.join(TILEDIR, 'bt_%s_%s*.png' % (THEME, slot)))
    if not fs: raise SystemExit('缺地块: %s / %s' % (THEME, slot))
    im = Image.open(sorted(fs, key=os.path.getmtime)[-1]).convert('RGB')
    return im if im.size == (CELL, CELL) else im.resize((CELL, CELL), Image.NEAREST)

tiles = {s: load(s) for s in set(SLOT.values())}

grid = []
with open(os.path.join(ROOT, 'extract/data/battlemaps/%s.map' % CID)) as f:
    for line in f:
        line = line.strip()
        if line: grid.append([int(v) for v in line.split(',') if v != ''])
H, W = len(grid), max(len(r) for r in grid)

out = Image.new('RGB', (W*CELL, H*CELL))
miss = set()
for y, row in enumerate(grid):
    for x in range(W):
        # 有的行比最长行短，末尾按平地补——不补的话那几格会留成黑块
        raw = row[x] if x < len(row) else 0
        code = raw & 0xF
        s = SLOT.get(code)
        if s is None: miss.add(code); s = 'ground'
        out.paste(tiles[s], (x*CELL, y*CELL))
out.save(OUT)
print('战场 %s · %s 主题 → %s  %d×%d 格 / %s' % (CID, THEME, OUT, W, H, out.size))
if miss: print('⚠ 出现了没有对应槽位的地形码:', sorted(miss))
