# -*- coding: utf-8 -*-
"""把阵型战小兵的整图切成 64×64 的单格。

   一张 1536×1024 = 3 列 × 2 行、每格 512×512：
     上排 红·朝右 / 红·朝上 / 红·朝下      （红 = 我方，和原版 lib56 的上排一致）
     下排 蓝·朝右 / 蓝·朝上 / 蓝·朝下
   朝左不出图，绘制时把朝右那张水平镜像——原版 lib56 就是这么省的。

   两件事必须在这里做掉：
   1. **去跨格的碎块**。模型再三叮嘱也会让侧面那杆矛的矛尖捅进隔壁格，
      隔壁格切出来就多一小截铁片。按连通域只留最大的那块，碎渣自然没了。
   2. **四个兵种的二十四格共用一个缩放比**。各切各的会让背面（兵器竖着、包围盒高）
      缩得比侧面小；一张张各自缩到满格，则步兵会长得和骑将一样高。
      所以一次把四张整图全读进来，取二十四格里最大的宽和高算一个公共比例。

   用法: cut_unitsheet.py <输出目录> <兵种key>=<整图.png> ... [--size 64]
"""
import sys, os
from collections import deque
from PIL import Image

args = sys.argv[1:]
T = 64
if '--size' in args:
    i = args.index('--size'); T = int(args[i + 1]); del args[i:i + 2]
OUT, SHEETS = args[0], [a.split('=', 1) for a in args[1:]]
CELL, ALPHA = 512, 40
SIDES = ['red', 'blue']
DIRS  = ['e', 'n', 's']
# 模型是一格一个人地画的，不管骑马还是步行都把方格填满，
# 光靠公共比例，步兵会长得和骑将一样高。步行的再按这个系数压一档。
ARM_SCALE = {'jiang': 1.0, 'qi': 1.0, 'gong': .82, 'bu': .84}
DOWN  = 4                       # 连通域在 1/4 分辨率上找，纯 Python 跑得动


def largest_blob(mask, w, h):
    """返回一张与 mask 等大的保留表：只留面积最大的连通域。"""
    seen = [False] * (w * h)
    best, bestn = None, 0
    for s0 in range(w * h):
        if not mask[s0] or seen[s0]:
            continue
        q, cells = deque([s0]), []
        seen[s0] = True
        while q:
            i = q.popleft(); cells.append(i)
            x, y = i % w, i // w
            for nx, ny in ((x-1, y), (x+1, y), (x, y-1), (x, y+1)):
                if 0 <= nx < w and 0 <= ny < h:
                    j = ny * w + nx
                    if mask[j] and not seen[j]:
                        seen[j] = True; q.append(j)
        if len(cells) > bestn:
            best, bestn = cells, len(cells)
    keep = [False] * (w * h)
    for i in (best or []):
        keep[i] = True
    return keep


def clean(cell):
    """按连通域剔掉跨格进来的碎块，返回清理后的图与它的包围盒。"""
    a = cell.split()[3].point(lambda v: 255 if v > ALPHA else 0)
    sw = sh = CELL // DOWN
    small = a.resize((sw, sh), Image.BOX).point(lambda v: 1 if v > 0 else 0)
    keep = largest_blob(list(small.getdata()), sw, sh)
    km = Image.frombytes('L', (sw, sh), bytes(255 if k else 0 for k in keep))
    km = km.resize((CELL, CELL), Image.NEAREST)
    out = cell.copy()
    out.putalpha(Image.composite(a, Image.new('L', (CELL, CELL), 0), km))
    return out, out.split()[3].point(lambda v: 255 if v > ALPHA else 0).getbbox()


os.makedirs(OUT, exist_ok=True)
cells = {}
for arm, src in SHEETS:
    sheet = Image.open(src).convert('RGBA')
    if sheet.size != (CELL * 3, CELL * 2):
        sheet = sheet.resize((CELL * 3, CELL * 2), Image.LANCZOS)
    for r, side in enumerate(SIDES):
        for c, d in enumerate(DIRS):
            cell = sheet.crop((c * CELL, r * CELL, (c + 1) * CELL, (r + 1) * CELL))
            img, box = clean(cell)
            if box is None:
                raise SystemExit('%s 第 %d 行第 %d 列是空的' % (arm, r, c))
            cells[(arm, side, d)] = (img, box)

wmax = max(b[2] - b[0] for _, b in cells.values())
hmax = max(b[3] - b[1] for _, b in cells.values())
s = min((T - 2) / wmax, (T - 2) / hmax)

for (arm, side, d), (img, box) in sorted(cells.items()):
    fig = img.crop(box)
    k = s * ARM_SCALE.get(arm, 1.0)
    w = max(1, round(fig.width * k)); h = max(1, round(fig.height * k))
    fig = fig.resize((w, h), Image.BOX)
    # 缩完边上会出一圈半透明，像素画不要这个，一刀切硬
    a = fig.split()[3].point(lambda v: 255 if v >= 110 else 0)
    fig.putalpha(a)
    canvas = Image.new('RGBA', (T, T), (0, 0, 0, 0))
    canvas.paste(fig, ((T - w) // 2, T - 1 - h), fig)
    f = os.path.join(OUT, 'c_%s_%s_%s.png' % (side, arm, d))
    canvas.save(f)
    print('%-24s %2dx%-2d' % (os.path.basename(f), w, h))
print('公共比例 %.4f（宽上限 %d，高上限 %d）' % (s, wmax, hmax))
