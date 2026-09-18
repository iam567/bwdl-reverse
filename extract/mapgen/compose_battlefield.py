# -*- coding: utf-8 -*-
"""用高清地块铺一张战场。

   格数与地形码一条不动，照抄原版 .map；换的只是每个码画成什么。
   码 → 槽位的对应（见 GAME_LOGIC §8，括号是移动消耗 平/水/山）：

     0            → ground  平地   (2/3/3)
     1            → wuku    武库   (2/3/3)   ← 其他版本里是武器店，本版未接效果
     3            → cang    常平仓 (2/3/3)   ← 同上，金粮店
     4            → yao     药庐   (2/3/3)   ← 疗伤点，本版已实现
     2            → castle  城池   (6/6/6)  每张图正好一个，是攻城的目标；
                                              站在这格上的部队不画（守军在城里）
     5            → ambush  埋伏点 (5/5/5)
     6            → water   水     (5/3/6)
     7, 9         → rock    山岩   (5/6/3)
     8            → forest  密林   (5/6/3)
     10, 11       → forest  密林   (5/6/3)   ← 灌木并进密林
     12           → grass   草坡   (5/6/3)

   用法: compose_battlefield.py <城池ID> <主题> <地块目录> <输出png> [格边长=64]
"""
import sys, os, glob
from PIL import Image

CID, THEME, TILEDIR, OUT = sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4]
CELL = int(sys.argv[5]) if len(sys.argv) > 5 else 64
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))

# 7–12 六个码消耗完全一样，可以共用同一张图。
# 灌木只留一种（码 10、11 共用），山岩一种（码 7、9 共用）。
SLOT = {0:'ground', 1:'wuku', 2:'castle', 3:'cang', 4:'yao', 5:'ambush', 6:'water',
        7:'rock', 8:'forest', 9:'rock', 10:'forest', 11:'forest', 12:'grass'}

# 城就占一格——原版如此，画大了和「一图一个格子」的数据对不上。
CASTLE_W, CASTLE_H = 1, 1

def load(slot, cells=(1, 1)):
    fs = glob.glob(os.path.join(TILEDIR, 'bt_%s_%s*.png' % (THEME, slot)))
    if not fs: raise SystemExit('缺地块: %s / %s' % (THEME, slot))
    im = Image.open(sorted(fs, key=os.path.getmtime)[-1])
    want = (CELL*cells[0], CELL*cells[1])
    im = im.convert('RGBA') if slot == 'castle' else im.convert('RGB')
    return im if im.size == want else im.resize(want, Image.NEAREST)

tiles = {s: load(s) for s in set(SLOT.values()) if s != 'castle'}
castle = load('castle', (CASTLE_W, CASTLE_H))

grid = []
with open(os.path.join(ROOT, 'extract/data/battlemaps/%s.map' % CID)) as f:
    for line in f:
        line = line.strip()
        if line: grid.append([int(v) for v in line.split(',') if v != ''])
H, W = len(grid), max(len(r) for r in grid)

out = Image.new('RGBA', (W*CELL, H*CELL))
miss = set()
castle_at = None
for y, row in enumerate(grid):
    for x in range(W):
        # 有的行比最长行短，末尾按平地补——不补的话那几格会留成黑块
        raw = row[x] if x < len(row) else 0
        code = raw & 0xF
        s = SLOT.get(code)
        if s is None: miss.add(code); s = 'ground'
        if s == 'castle':
            castle_at = (x, y); s = 'ground'      # 城的那一格先铺地面，城随后整体贴上
        out.paste(tiles[s], (x*CELL, y*CELL))

if castle_at:
    cx, cy = castle_at
    # 城门对准那一格：横向居中，纵向让城的底边压在该格底边
    px = (cx - (CASTLE_W - 1)//2) * CELL
    py = (cy - (CASTLE_H - 1)) * CELL
    out.alpha_composite(castle, (max(0, px), max(0, py)))
    print('城池在格 (%d, %d)，画成 %d×%d 格' % (cx, cy, CASTLE_W, CASTLE_H))
out = out.convert('RGB')
out.save(OUT)
print('战场 %s · %s 主题 → %s  %d×%d 格 / %s' % (CID, THEME, OUT, W, H, out.size))
if miss: print('⚠ 出现了没有对应槽位的地形码:', sorted(miss))
