# -*- coding: utf-8 -*-
"""把「一城一张」的攻城背景动画表切成四帧。

   一张 1024×1536 是 2 列 × 2 行，每格 512×768（和整幅一样的竖版比例）。
   帧序就是从左到右、从上到下。

   顺带量一下帧间差异：这套做法的全部风险就在「四格是不是同一张画」。
   平均差太大就说明模型把四格画成了四张构图不同的图，
   切出来一播整幅画面都在抖——宁可当场报出来，也别等到进游戏才发现。

   用法: cut_duelanim.py <整图目录> <输出目录> [JPEG质量=86]
"""
import sys, os, glob, re, itertools
from PIL import Image, ImageChops

SRC, OUT = sys.argv[1], sys.argv[2]
Q = int(sys.argv[3]) if len(sys.argv) > 3 else 86
os.makedirs(OUT, exist_ok=True)
CELL_W, CELL_H = 512, 768


def frames(sheet):
    if sheet.size != (CELL_W * 2, CELL_H * 2):
        sheet = sheet.resize((CELL_W * 2, CELL_H * 2), Image.LANCZOS)
    return [sheet.crop((i % 2 * CELL_W, i // 2 * CELL_H,
                        i % 2 * CELL_W + CELL_W, i // 2 * CELL_H + CELL_H))
            for i in range(4)]


def jitter(fs):
    """四帧两两比，返回最大的平均像素差。越小越说明是同一张画。"""
    worst = 0
    for a, b in itertools.combinations(range(4), 2):
        h = ImageChops.difference(fs[a], fs[b]).convert('L').histogram()
        tot = sum(h)
        worst = max(worst, sum(i * n for i, n in enumerate(h)) / tot)
    return worst


total, bad = 0, []
for f in sorted(glob.glob(os.path.join(SRC, 'dba_c*.png')),
                key=lambda p: int(re.search(r'dba_c(\d+)', p).group(1))):
    cid = int(re.search(r'dba_c(\d+)', f).group(1))
    fs = frames(Image.open(f).convert('RGB'))
    j = jitter(fs)
    n = 0
    for i, im in enumerate(fs):
        p = os.path.join(OUT, 'duel_bg_siege_c%d_f%d.jpg' % (cid, i))
        im.save(p, 'JPEG', quality=Q, optimize=True)
        n += os.path.getsize(p)
    total += n
    flag = '  ← 抖' if j > 18 else ''
    if j > 18: bad.append(cid)
    print('城 %-2d  帧间平均差 %5.1f   %4d KB%s' % (cid, j, n // 1024, flag))

print('共 %d 座，%d MB' % (len(glob.glob(os.path.join(SRC, 'dba_c*.png'))), total // 1048576))
if bad: print('帧间差异偏大、建议重出的:', ', '.join(map(str, bad)))
