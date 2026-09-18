# -*- coding: utf-8 -*-
"""把生成的地块图做成真正能无缝平铺的像素块。

   提示词里要求「四边无缝」模型做不到——3×3 一铺就能看见接缝。
   程序修：把图循环位移半格，位移版的四边正好是原图的中心（连续的），
   再用「边缘权重高、中心权重低」的掩码把两版混合，边界就接上了。
   最后降采样 + 限色板，回到像素画。

   用法: make_tile.py <输入png> <输出png> [边长=64] [色数=12]
"""
import sys
from PIL import Image

SRC, DST = sys.argv[1], sys.argv[2]
N = int(sys.argv[3]) if len(sys.argv) > 3 else 64
COLORS = int(sys.argv[4]) if len(sys.argv) > 4 else 12

im = Image.open(SRC).convert('RGB')
W, H = im.size

# 循环位移半格：位移版的四边 = 原图的中心，天然连续
rolled = Image.new('RGB', (W, H))
rolled.paste(im.crop((W//2, H//2, W, H)), (0, 0))
rolled.paste(im.crop((0, H//2, W//2, H)), (W - W//2, 0))
rolled.paste(im.crop((W//2, 0, W, H//2)), (0, H - H//2))
rolled.paste(im.crop((0, 0, W//2, H//2)), (W - W//2, H - H//2))

# 掩码：越靠边越偏向位移版
mask = Image.new('L', (W, H))
mp = mask.load()
for y in range(H):
    dy = abs(2.0 * y / (H - 1) - 1.0)
    for x in range(W):
        dx = abs(2.0 * x / (W - 1) - 1.0)
        d = max(dx, dy)
        t = 0.0 if d < 0.55 else min(1.0, (d - 0.55) / 0.45)
        mp[x, y] = int(255 * (t * t * (3 - 2 * t)))      # smoothstep
merged = Image.composite(rolled, im, mask)

px = merged.resize((N, N), Image.BOX)                     # 整数倍降采样
out = px.quantize(colors=COLORS, method=Image.MAXCOVERAGE).convert('RGB')
out.save(DST)
print('%s → %s  %dx%d  %d 色' % (SRC.split('/')[-1], DST.split('/')[-1], N, N, COLORS))
