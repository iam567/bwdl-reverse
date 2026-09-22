# -*- coding: utf-8 -*-
"""战棋层部队贴图：1024 的原图 → 64 见方、带描边的小人。

   之前这一步是手工做的，px/ 里只有成品、没有脚本，想调描边就没法重来。
   这里补上，顺带解决「人物压在深绿林地和灰岩上看不清」：

   人物是深蓝加铁灰，压在深绿林地和灰岩上整个糊掉。
   试过内暗外浅的双层描边，拔得最干净，但外面那圈浅边一上来就是「贴纸」感（阵型战那회
   已经因为这个把白边换掉了）。最后走的是**一圈近黑描边 + 把人物本身提亮一档**：
   暗边负责在亮草地上勾出轮廓，提亮负责让深蓝从暗林地里浮出来，两样都不破坏像素画的质感。

   用法: build_units.py [原图目录] [输出目录]
"""
import sys, os, glob
from PIL import Image, ImageFilter, ImageEnhance

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC = sys.argv[1] if len(sys.argv) > 1 else os.path.join(ROOT, 'assets/battleunit')
OUT = sys.argv[2] if len(sys.argv) > 2 else os.path.join(ROOT, 'assets/battleunit/px')
os.makedirs(OUT, exist_ok=True)

T = 64                      # 成品边长，和格子一致
DARK = (14, 10, 8, 255)     # 描边：近黑，和阵型战小兵那套一致
BRIGHT, SATUR = 1.22, 1.15  # 人物本身提亮 / 略增饱和

# 同一批里共用一个缩放比，各缩各的会让骑马的比步行的大一圈
raw = {}
for f in sorted(glob.glob(os.path.join(SRC, 'u_*.png'))):
    key = '_'.join(os.path.basename(f).split('_')[:4])      # u_blue_ping_s
    im = Image.open(f).convert('RGBA')
    b = im.split()[3].point(lambda v: 255 if v > 24 else 0).getbbox()
    if b is None:
        print('空图', f); continue
    raw[key] = im.crop(b)                                   # 同名多版取后一个（文件名尾是时间戳，排序即新旧）

if not raw:
    print('没找到原图'); sys.exit(1)
wmax = max(i.width for i in raw.values())
hmax = max(i.height for i in raw.values())
s = min((T - 2) / wmax, (T - 2) / hmax)                     # 和旧版一个大小，描边往那 1 像素余量里长

for key, fig in sorted(raw.items()):
    w = max(1, round(fig.width * s)); h = max(1, round(fig.height * s))
    fig = fig.resize((w, h), Image.BOX)
    a = fig.split()[3].point(lambda v: 255 if v >= 110 else 0)   # 缩完的半透明边一刀切硬
    fig.putalpha(a)
    rgb = fig.convert('RGB')
    rgb = ImageEnhance.Brightness(rgb).enhance(BRIGHT)
    rgb = ImageEnhance.Color(rgb).enhance(SATUR)
    fig = Image.merge('RGBA', (*rgb.split(), a))

    canvas = Image.new('RGBA', (T, T), (0, 0, 0, 0))
    ox, oy = (T - w) // 2, T - h                            # 横向居中、脚落在格底
    ring = Image.new('RGBA', fig.size, DARK)
    ring.putalpha(a.filter(ImageFilter.MaxFilter(3)))
    canvas.alpha_composite(ring, (ox, oy))
    canvas.alpha_composite(fig, (ox, oy))
    canvas.save(os.path.join(OUT, key + '.png'))

print('部队 %d 张 → %s' % (len(raw), OUT))
