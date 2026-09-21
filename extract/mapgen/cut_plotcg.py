# -*- coding: utf-8 -*-
"""计策效果动图：一张 1024×1536 切 2 列 × 4 行 = 8 帧，每帧 512×384。
   帧序从左到右、从上到下。顺带量帧间平均差，抖得厉害的报出来。
   用法: cut_plotcg.py <整图目录> <输出目录> [JPEG质量=84]
"""
import sys, os, glob, re, itertools
from PIL import Image, ImageChops
SRC, OUT = sys.argv[1], sys.argv[2]
Q = int(sys.argv[3]) if len(sys.argv) > 3 else 84
os.makedirs(OUT, exist_ok=True)
CW, CH = 512, 384
bad = []
for f in sorted(glob.glob(os.path.join(SRC, 'pca_*.png')), key=lambda p: int(re.search(r'pca_(\d+)', p).group(1))):
    i = int(re.search(r'pca_(\d+)', f).group(1))
    sh = Image.open(f).convert('RGB')
    if sh.size != (CW * 2, CH * 4): sh = sh.resize((CW * 2, CH * 4), Image.LANCZOS)
    fr = [sh.crop((k % 2 * CW, k // 2 * CH, k % 2 * CW + CW, k // 2 * CH + CH)) for k in range(8)]
    worst = 0
    for a, b in itertools.combinations(range(8), 2):
        h = ImageChops.difference(fr[a], fr[b]).convert('L').histogram()
        worst = max(worst, sum(x * n for x, n in enumerate(h)) / sum(h))
    n = 0
    for k, im in enumerate(fr):
        p = os.path.join(OUT, 'plot_%d_f%d.jpg' % (i, k)); im.save(p, 'JPEG', quality=Q, optimize=True); n += os.path.getsize(p)
    flag = '  ← 抖' if worst > 22 else ''
    if worst > 22: bad.append(i)
    print('计策 %-2d 八帧  帧间最大平均差 %5.1f  %4d KB%s' % (i, worst, n // 1024, flag))
if bad: print('抖得厉害、建议重出:', bad)
