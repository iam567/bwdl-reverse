# -*- coding: utf-8 -*-
"""把「一张整图六格」的立绘切开，按帧号落到各套目录里。

   这是取代逐帧生成的做法——原版 lib304 本来就是一张整图。
   切完仍然要走对位（build_duelskins.py 那套：按地面线和马腿中心归一化），
   因为模型在六个格子里画的马高低仍有几像素出入。

   用法: cut_duelsheet.py [整图目录=work/duelsheet] [输出目录=work/duelskin] [格数=3x2]
"""
import sys, os, glob, re, json, subprocess
from PIL import Image

SRC  = sys.argv[1] if len(sys.argv) > 1 else 'work/duelsheet'
DST  = sys.argv[2] if len(sys.argv) > 2 else 'work/duelskin'
GRID = sys.argv[3] if len(sys.argv) > 3 else '3x2'
COLS, ROWS = (int(v) for v in GRID.split('x'))
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC, DST = os.path.join(ROOT, SRC), os.path.join(ROOT, DST)
os.makedirs(DST, exist_ok=True)

# 格子顺序（帧号）跟 duelsheet.js 的 CELLS 保持一致，从那边读，别两处各写一份
cells = json.loads(subprocess.run(
    ['node', '-e', 'console.log(JSON.stringify(require("./image-studio/duelsheet.js").CELLS.map(c=>c[0])))'],
    cwd=ROOT, capture_output=True, text=True).stdout or '[]')
if len(cells) != COLS * ROWS:
    raise SystemExit('格数对不上：图是 %dx%d，CELLS 有 %d 项' % (COLS, ROWS, len(cells)))

PAT = re.compile(r'^dsh_(.+?)_[a-z0-9]+\.png$')
newest = {}
for f in glob.glob(os.path.join(SRC, 'dsh_*.png')):
    m = PAT.match(os.path.basename(f))
    if not m: continue
    k = m.group(1)
    if k not in newest or os.path.getmtime(f) > os.path.getmtime(newest[k]): newest[k] = f

n = 0
for skin, path in sorted(newest.items()):
    im = Image.open(path).convert('RGBA')
    cw, ch = im.width // COLS, im.height // ROWS
    for i, frame in enumerate(cells):
        cell = im.crop(((i % COLS) * cw, (i // COLS) * ch,
                        (i % COLS + 1) * cw, (i // COLS + 1) * ch))
        # 空格子（模型少画了一格）就跳过，别写一张全透明的进去
        a = cell.getchannel('A')
        if a.point(lambda v: 255 if v >= 100 else 0).getbbox() is None:
            print('  %s 第 %d 格是空的，跳过' % (skin, i + 1)); continue
        cell.save(os.path.join(DST, 'ds_%s_f%d_cut.png' % (skin, frame)))
        n += 1
    print('%-14s 切出 %d 格' % (skin, len(cells)))

print('\n共切出 %d 张 → %s（接着跑 build_duelskins.py 对位）' % (n, os.path.relpath(DST, ROOT)))
