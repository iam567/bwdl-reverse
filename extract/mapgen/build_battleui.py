# -*- coding: utf-8 -*-
"""阵型战 UI 底图的后处理：裁到实际内容、缩到贴图尺寸、量出九宫格的 border。

   模型出的是 1024 见方、四周留白的整图，直接拿去当 UI 会有两个问题：
   一是贴图白白大了十几倍，二是九宫格的 border 只能靠眼估，估歪了拉伸就糊。
   所以这里把边框厚度和头像框中间那个透明洞**量出来**写进 battleui.json，
   Unity 那边直接读，不在代码里写死魔数。

   用法: build_battleui.py <原图目录> <输出目录>
"""
import sys, os, json
from PIL import Image

SRC, OUT = sys.argv[1], sys.argv[2]
os.makedirs(OUT, exist_ok=True)
TARGET = {'plate': 192, 'btn': 96, 'portrait': 192, 'mapbg': 192}
meta = {}


def trim(im):
    b = im.split()[3].point(lambda v: 255 if v > 24 else 0).getbbox()
    return im.crop(b) if b else im


def edge_thickness(im):
    """量铜边有多厚。

       第一版是「从中线往里扫，亮转暗的那一点」——量出来是 2，
       因为铜边最外圈本身就是一道深色描边，第二个像素就已经暗下去了。
       改成先取板心的亮度当基准，再从外往里走，
       **连着若干个像素都落回板心亮度**才算出了边框。"""
    px = im.convert('RGB').load()
    a  = im.split()[3].load()
    w, h = im.size
    y = h // 2
    lum = lambda p: .299 * p[0] + .587 * p[1] + .114 * p[2]

    mid = [lum(px[x, y]) for x in range(int(w * .42), int(w * .58))]
    wood = sorted(mid)[len(mid) // 2]                  # 板心亮度取中位数

    x = 0
    while x < w // 3 and a[x, y] < 128: x += 1         # 跳过外圈残留的透明像素
    run = 0
    for i in range(x, w // 2):
        if lum(px[i, y]) < wood * 1.30:
            run += 1
            if run >= 5: return max(2, i - 4)
        else:
            run = 0
    return max(2, w // 8)


def hole(im):
    """头像框中间那个全透明的方洞：从中线往外量。"""
    a = im.split()[3].load()
    w, h = im.size
    cx, cy = w // 2, h // 2
    l = cx
    while l > 0 and a[l - 1, cy] < 16: l -= 1
    r = cx
    while r < w - 1 and a[r + 1, cy] < 16: r += 1
    t = cy
    while t > 0 and a[cx, t - 1] < 16: t -= 1
    b = cy
    while b < h - 1 and a[cx, b + 1] < 16: b += 1
    return l, t, r - l + 1, b - t + 1


for k, target in TARGET.items():
    f = os.path.join(SRC, 'bui_%s.png' % k)
    if not os.path.exists(f):
        print('缺', k); continue
    im = Image.open(f).convert('RGBA')
    if k != 'mapbg':
        im = trim(im)

    # 等比缩到目标边长（btn 是横条，按高度缩）
    if k == 'btn':
        s = target / im.height
    else:
        s = target / max(im.width, im.height)
    im = im.resize((max(1, round(im.width * s)), max(1, round(im.height * s))), Image.LANCZOS)

    if k == 'portrait':
        hx, hy, hw, hh = hole(im)
        meta[k] = {'w': im.width, 'h': im.height,
                   'hole': [hx, hy, hw, hh]}
    elif k == 'mapbg':
        meta[k] = {'w': im.width, 'h': im.height}
    else:
        e = edge_thickness(im)
        meta[k] = {'w': im.width, 'h': im.height, 'border': e}

    im.save(os.path.join(OUT, 'ui_%s.png' % k))
    print('%-9s %dx%-4d %s' % (k, im.width, im.height, meta[k]))

with open(os.path.join(OUT, 'battleui.json'), 'w', encoding='utf-8') as fp:
    json.dump(meta, fp, ensure_ascii=False)

# 再写一份扁平的给 Unity 读。量出来的数就该由这里说了算，
# 不要在 C# 里另抄一份魔数——重出一次图两边就对不上了。
with open(os.path.join(OUT, 'battleui.txt'), 'w', encoding='utf-8') as fp:
    for k, v in meta.items():
        if 'border' in v: fp.write('%s %d\n' % (k, v['border']))
        elif 'hole' in v: fp.write('%s %d %d %d %d %d %d\n'
                                   % (k, v['w'], v['h'], *v['hole']))
print('→', os.path.join(OUT, 'battleui.txt'))
