# -*- coding: utf-8 -*-
"""把生成的单挑素材做成能直接用的精灵。

   15 帧是各生成各的，画布上的位置必然对不齐——直接播会看到人在抖。
   所以要对位：**按地面线和马腿的水平中心**归一化，而不是按整张图的包围盒
   （枪伸出去的帧包围盒会往右跑，落马帧整个形状都不一样）。
   取最底下那 30% 的不透明像素算水平中心，这部分是马的四条腿，姿势之间最稳。

   用法: build_duel.py <原图目录=work/duel> <成品目录=work/duel/px> [边长=256]
"""
import sys, os, glob, json
from PIL import Image

SRC  = sys.argv[1] if len(sys.argv) > 1 else 'work/duel'
DST  = sys.argv[2] if len(sys.argv) > 2 else 'work/duel/px'
N    = int(sys.argv[3]) if len(sys.argv) > 3 else 256
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC, DST = os.path.join(ROOT, SRC), os.path.join(ROOT, DST)
os.makedirs(DST, exist_ok=True)

def newest(pat):
    fs = glob.glob(os.path.join(SRC, pat))
    return sorted(fs, key=os.path.getmtime)[-1] if fs else None

def solid_bbox(im, thr=100):
    """alpha 噪点会骗过 getbbox()，按阈值自己算"""
    a = im.getchannel('A')
    return a.point(lambda v: 255 if v >= thr else 0).getbbox()

def foot_center(im, box, frac=0.30, thr=100):
    """最底下 frac 那一条里，不透明像素的水平中心 —— 这条是马腿"""
    l, t, r, b = box
    y0 = int(b - (b - t) * frac)
    a = im.getchannel('A').crop((l, y0, r, b))
    bb = a.point(lambda v: 255 if v >= thr else 0).getbbox()
    if bb is None: return (l + r) // 2
    return l + (bb[0] + bb[2]) // 2

FRAMES = [5, 0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14]
KEY = {5: 'duel_seed'}          # 母版就是第 5 帧
for f in FRAMES:
    if f != 5: KEY[f] = 'duel_f%d' % f

srcs, out = {}, {}
for f in FRAMES:
    p = newest(KEY[f] + '_*.png')
    if p: srcs[f] = p

if not srcs:
    raise SystemExit('一张都没有，先去平台跑')

# 先量所有帧，取一个统一的缩放比：以「马身高度」最大的那帧为准，留 8% 余白
metrics = {}
for f, p in srcs.items():
    im = Image.open(p).convert('RGBA')
    bb = solid_bbox(im)
    if bb is None: continue
    metrics[f] = (im, bb, foot_center(im, bb))
hmax = max(bb[3] - bb[1] for _, bb, _ in metrics.values())
scale = (N * 0.92) / hmax

for f, (im, bb, cx) in sorted(metrics.items()):
    w, h = int(im.width * scale), int(im.height * scale)
    small = im.resize((w, h), Image.BOX)
    sb = tuple(int(v * scale) for v in bb)
    scx = int(cx * scale)
    canvas = Image.new('RGBA', (N, N), (0, 0, 0, 0))
    # 水平：马腿中心对齐画布中心；垂直：地面线（包围盒底）贴到画布底往上 4%
    dx = N // 2 - scx
    dy = int(N * 0.96) - sb[3]
    canvas.alpha_composite(small, (dx, dy))
    canvas.save(os.path.join(DST, 'duel_f%d.png' % f))
    out[f] = (sb[2] - sb[0], sb[3] - sb[1])

print('对位输出 %d 帧 → %s（画布 %d²）' % (len(out), os.path.relpath(DST, ROOT), N))
for f in sorted(out): print('  帧 %-2d  原尺寸 %dx%d' % (f, *out[f]))

# 背景与 UI 直接缩放，不用对位
for key, size in (('duel_bg_plain', None), ('duel_bg_water', None),
                  ('duel_bg_forest', None), ('duel_bg_siege', None),
                  ('duel_panel', (512, 256)), ('duel_cursor', (64, 64))):
    p = newest(key + '_*.png')
    if not p: print('  缺', key); continue
    im = Image.open(p).convert('RGBA')
    if size:
        bb = solid_bbox(im, 60)
        if bb: im = im.crop(bb)
        im = im.resize(size, Image.BOX)
    im.save(os.path.join(DST, key + '.png'))
    print('  %s → %s' % (key, im.size))
