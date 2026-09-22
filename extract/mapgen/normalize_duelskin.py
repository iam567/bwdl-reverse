# -*- coding: utf-8 -*-
"""把每套单挑立绘的各帧**按人物高度对齐**。

   生图时站立那一帧（duel_f5）是当立绘画的，人物占满画布；动作帧是当场景画的，
   人物只占四成多。两种帧在游戏里都拉到同一个 128×128 的框里，
   于是「站着的时候很大，一冲锋突然缩一半」——玩家看到的就是这个。

   对齐用的是**外框宽度**不是高度：马身侧面的长度基本不随姿势变
   （caocao 的动作帧都是 110~125 宽，只有站立那帧 226），而高度会——
   落马那一帧是躺着的，按高度拉就会被抻成一匹巨马。
   目标宽度是**全局固定的**（画布的 88%），不是"本套里最宽的那帧"——
   各套立绘画得有大有小，按本套对齐只能让自己前后一致，
   两套凑到一块（我方 vs 敌方）还是一大一小。
   脚线对齐画布底部、横向居中（马蹄落在同一条地平线上，不然帧与帧之间会上下蹿）。
   放大是在 256 的原图上做的，最终显示 128，等于没有真的丢分辨率。

   用法: normalize_duelskin.py [立绘目录] [输出目录]
"""
import sys, os, glob, shutil
from PIL import Image

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC = sys.argv[1] if len(sys.argv) > 1 else os.path.join(ROOT, 'assets/duelskin/px')
OUT = sys.argv[2] if len(sys.argv) > 2 else SRC
TARGET = 0.88                    # 人物占画布宽度的比例，留一成余量免得贴边

n = m = 0
for d in sorted(glob.glob(os.path.join(SRC, '*'))):
    if not os.path.isdir(d): continue
    files = sorted(glob.glob(os.path.join(d, 'duel_f*.png')))
    if not files: continue
    box = {}
    for f in files:
        im = Image.open(f).convert('RGBA')
        b = im.split()[3].point(lambda v: 255 if v > 16 else 0).getbbox()
        if b: box[f] = (im, b)
    if not box: continue
    W, H = next(iter(box.values()))[0].size
    want = int(W * TARGET)                         # 所有套、所有帧都对到同一个宽度
    od = os.path.join(OUT, os.path.basename(d)); os.makedirs(od, exist_ok=True)
    for f, (im, b) in box.items():
        fig = im.crop(b)
        k = want / fig.width
        if fig.height * k > H:                      # 竖着顶破了（比如举枪那帧）就按高度收
            k = H / fig.height
        w2 = max(1, round(fig.width * k)); h2 = max(1, round(fig.height * k))
        fig = fig.resize((w2, h2), Image.LANCZOS if k < 1 else Image.NEAREST)
        canvas = Image.new('RGBA', (W, H), (0, 0, 0, 0))
        canvas.alpha_composite(fig, ((W - w2) // 2, H - h2))     # 脚线贴底、横向居中
        canvas.save(os.path.join(od, os.path.basename(f)))
        m += 1
    n += 1
print('立绘 %d 套 %d 张，人物高度已对齐 → %s' % (n, m, OUT))
