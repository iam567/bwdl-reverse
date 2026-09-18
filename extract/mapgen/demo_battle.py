# -*- coding: utf-8 -*-
"""在合成好的战场上摆几支部队，带兵力条，看整体效果。

   原版把兵力编在贴图里（lib51 的 11 列 = 兵力÷100，0–10 封顶）；
   这里改成脚下一条血条，能连续显示，也不受 1000 兵封顶的限制。

   用法: demo_battle.py <战场png> <单位目录> <输出png> [格边长=64]
"""
import sys, os, random
from PIL import Image, ImageDraw

BF, UDIR, OUT = sys.argv[1], sys.argv[2], sys.argv[3]
CELL = int(sys.argv[4]) if len(sys.argv) > 4 else 64

bf = Image.open(BF).convert('RGBA')
W, H = bf.width // CELL, bf.height // CELL

# (列, 行, 阵营, 兵种, 朝向, 兵力)
TROOPS = [
    (6, 5,'blue','ping','e', 900), (5, 6,'blue','ping','e', 750),
    (6, 7,'blue','shan','ne',480), (4, 7,'blue','shui','se',620),
    (12,5,'red','ping','s',  880), (13,6,'red','shan','s', 300),
    (12,7,'red','shui','n',  540), (14,7,'red','ping','se',700),
]
BAR = {'blue': (78,150,232), 'red': (222,78,64)}

d = ImageDraw.Draw(bf)
for cx, cy, side, arm, dirk, troops in TROOPS:
    flip = False
    key = dirk
    if dirk in ('w','sw','nw'):                      # 西向靠镜像
        flip = True; key = {'w':'e','sw':'se','nw':'ne'}[dirk]
    f = os.path.join(UDIR, 'u_%s_%s_%s.png' % (side, arm, key))
    if not os.path.exists(f): continue

    # 脚下垫一圈队伍色的椭圆：蓝军站在深色密林上不够跳，
    # 这圈同时也标出「这格被占了」
    ring = Image.new('RGBA', (CELL, CELL), (0,0,0,0))
    rd = ImageDraw.Draw(ring)
    rw, rh = int(CELL*0.62), int(CELL*0.22)
    rx, ry = (CELL-rw)//2, CELL - rh - int(CELL*0.10)
    c = BAR[side]
    rd.ellipse([rx, ry, rx+rw, ry+rh], fill=(c[0], c[1], c[2], 70),
               outline=(c[0], c[1], c[2], 210), width=2)
    bf.alpha_composite(ring, (cx*CELL, cy*CELL))

    u = Image.open(f).convert('RGBA')
    if flip: u = u.transpose(Image.FLIP_LEFT_RIGHT)
    if u.size != (CELL, CELL): u = u.resize((CELL, CELL), Image.NEAREST)
    bf.alpha_composite(u, (cx*CELL, cy*CELL))

    # 兵力条：贴在格子下沿，满格 1000
    bw, bh = int(CELL*0.72), max(3, CELL//14)
    bx, by = cx*CELL + (CELL-bw)//2, cy*CELL + CELL - bh - 2
    d.rectangle([bx-1, by-1, bx+bw, by+bh], fill=(18,14,10,235))
    d.rectangle([bx, by, bx + int(bw*min(troops,1000)/1000) - 1, by+bh-1], fill=BAR[side])

bf.convert('RGB').save(OUT)
print('摆了 %d 支部队 → %s' % (len(TROOPS), OUT))
