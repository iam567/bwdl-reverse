# -*- coding: utf-8 -*-
"""战棋层部队贴图：1024 的原图 → 64 见方、带描边的小人。

   之前这一步是手工做的，px/ 里只有成品、没有脚本，想调描边就没法重来。
   这里补上，顺带解决「人物压在深绿林地和灰岩上看不清」：

   人物是深蓝加铁灰，压在深绿林地和灰岩上整个糊掉。
   先试过近黑描边 + 提亮，在暗林地上还是不够跳。战棋层的地块比阵型战花得多
   （林地是一团团亮绿、水面是亮蓝、岩石是灰），暗边在亮底上有用、在暗底上等于没有。
   最后按玩家的意思换成**白描边**：亮草地、暗林地、灰岩、蓝水四种底都能把轮廓勾出来。
   人物本身仍然提亮一档，免得白边一圈里裹着一坨黑。

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
EDGE = (246, 244, 238, 255) # 描边：近白。阵型战那边底色浅、用的是暗边，这边反过来
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
s = min((T - 4) / wmax, (T - 4) / hmax)                     # 留 2 像素给白边

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
    ox, oy = (T - w) // 2, T - h - 2                        # 横向居中、脚落在格底（下面留 2 像素给描边）
    ring = Image.new('RGBA', fig.size, EDGE)
    ring.putalpha(a.filter(ImageFilter.MaxFilter(5)))      # 2 像素，1 像素在 44 的格子上看不见
    canvas.alpha_composite(ring, (ox, oy))
    canvas.alpha_composite(fig, (ox, oy))
    canvas.save(os.path.join(OUT, key + '.png'))

print('部队 %d 张 → %s' % (len(raw), OUT))
