# -*- coding: utf-8 -*-
"""把 62 套单挑立绘各自对位成可用的精灵。

   和 build_duel.py 同一套对位办法，但**每套单独算缩放**：
   关羽的马和貂蝉的马本来就不一样高，用全局统一的比例会把某些人压扁。
   同一套内部的 8 帧共用一个比例，这样播起来才不跳。

   对位仍然按**地面线 + 最底下 30%（马腿）的水平中心**——
   不能用整张图的包围盒：兵器挥出去的帧会往右跑，落马帧形状完全不同。

   用法: build_duelskins.py [原图目录=work/duelskin] [成品目录=work/duelskin/px] [边长=256]
"""
import sys, os, glob, re
from PIL import Image

SRC  = sys.argv[1] if len(sys.argv) > 1 else 'work/duelskin'
DST  = sys.argv[2] if len(sys.argv) > 2 else 'work/duelskin/px'
N    = int(sys.argv[3]) if len(sys.argv) > 3 else 256
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC, DST = os.path.join(ROOT, SRC), os.path.join(ROOT, DST)

def solid_bbox(im, thr=100):
    a = im.getchannel('A')
    return a.point(lambda v: 255 if v >= thr else 0).getbbox()

def foot_center(im, box, frac=0.30, thr=100):
    l, t, r, b = box
    y0 = int(b - (b - t) * frac)
    a = im.getchannel('A').crop((l, y0, r, b))
    bb = a.point(lambda v: 255 if v >= thr else 0).getbbox()
    return l + ((bb[0] + bb[2]) // 2 if bb else (r - l) // 2)

# ds_<skin>_f<N>_<hash>.png  —— skin 里本身带下划线（male_a），所以要从右边切
PAT = re.compile(r'^ds_(.+)_f(\d+)_[a-z0-9]+\.png$')
skins = {}
for f in glob.glob(os.path.join(SRC, 'ds_*.png')):
    m = PAT.match(os.path.basename(f))
    if not m: continue
    skin, frame = m.group(1), int(m.group(2))
    cur = skins.setdefault(skin, {})
    if frame not in cur or os.path.getmtime(f) > os.path.getmtime(cur[frame]):
        cur[frame] = f                      # 同一帧重跑过就取最新的

if not skins:
    raise SystemExit('一张都没有，先去平台跑')

ok = short = 0
for skin, frames in sorted(skins.items()):
    metrics = {}
    for fr, path in frames.items():
        im = Image.open(path).convert('RGBA')
        if im.mode != 'RGBA' or im.getchannel('A').getextrema()[0] >= 250:
            print('  %s 帧 %d 没有透明底，跳过' % (skin, fr)); continue
        bb = solid_bbox(im)
        if bb is None: continue
        metrics[fr] = (im, bb, foot_center(im, bb))
    if not metrics: continue

    out = os.path.join(DST, skin)
    os.makedirs(out, exist_ok=True)
    hmax = max(bb[3] - bb[1] for _, bb, _ in metrics.values())
    scale = (N * 0.92) / hmax               # 这一套自己的比例
    for fr, (im, bb, cx) in sorted(metrics.items()):
        small = im.resize((int(im.width * scale), int(im.height * scale)), Image.BOX)
        sb = tuple(int(v * scale) for v in bb)
        canvas = Image.new('RGBA', (N, N), (0, 0, 0, 0))
        canvas.alpha_composite(small, (N // 2 - int(cx * scale), int(N * 0.96) - sb[3]))
        canvas.save(os.path.join(out, 'duel_f%d.png' % fr))
    ok += 1
    if len(metrics) < 8: short += 1
    print('%-14s %d 帧' % (skin, len(metrics)) + ('   ← 不足 8 帧' if len(metrics) < 8 else ''))

print('\n对位完成 %d 套，其中 %d 套帧数不全 → %s' % (ok, short, os.path.relpath(DST, ROOT)))
