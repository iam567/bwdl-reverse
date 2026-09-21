# -*- coding: utf-8 -*-
"""把一套主题的地块统一到同一个色板。

   每张单独量化的话，13 张各有各的色板——黄绿草、青绿林、褐土、灰岩
   摆在一起像四套美术。先把整套拼成一张图求一个公共色板，
   再让每张按这个色板重新量化，整套的调子就一致了。

   用法: unify_palette.py <地块目录> <主题> [色数=40]
"""
import sys, os, glob
from PIL import Image

DIR, THEME = sys.argv[1], sys.argv[2]
COLORS = int(sys.argv[3]) if len(sys.argv) > 3 else 40

# 建筑四格是透明底的图标，不进统一色板：一进就被拉进泥土草地的色板里，
# 屋顶的朱红明黄没了，而且 convert('RGB') 会把透明通道抹掉。
OBJECTS = ('castle', 'wuku', 'cang', 'yao')
fs = sorted(f for f in glob.glob(os.path.join(DIR, 'bt_%s_*.png' % THEME))
            if not os.path.basename(f)[:-4].endswith(OBJECTS))
if not fs: raise SystemExit('没找到 %s 的地块' % THEME)
ims = [(f, Image.open(f).convert('RGB')) for f in fs]
N = ims[0][1].width

strip = Image.new('RGB', (N*len(ims), N))
for i, (_, im) in enumerate(ims): strip.paste(im, (i*N, 0))
pal = strip.quantize(colors=COLORS, method=Image.MAXCOVERAGE)

for f, im in ims:
    im.quantize(palette=pal, dither=Image.Dither.NONE).convert('RGB').save(f)
print('%s：%d 张统一到 %d 色公共色板' % (THEME, len(ims), COLORS))
