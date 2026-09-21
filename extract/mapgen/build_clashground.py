# -*- coding: utf-8 -*-
"""阵型战地面：从 2×2 的材质表切出四种，做成真正能无缝平铺的贴图。

   模型答应「四边无缝」但做不到，3×3 一铺就能看见接缝——
   所以每一格都过一遍 make_tile 的半格位移 + 边缘混合。

   水战那格单独出（连环船阵），若存在就顶掉表里的第 2 格：
   整张重出会把已经对的三格也再赌一次，单出一格同样一张图的钱。

   用法:
     静态  build_clashground.py <表.png> <输出目录> [水战单图.png]
     动画  build_clashground.py --anim <动画表目录> <输出目录>
     城墙  build_clashground.py --walls <城墙表.png> <输出目录>
     箭矢  build_clashground.py --arrows <箭矢表.png> <输出目录>
     特效  build_clashground.py --fx <特效表目录> <输出目录>

   城墙那支**不做**半格位移混合——那一步会把垛口和栅门的结构糊成一团，
   墙块本来就是一块块摆的，边缘对不对得上靠提示词里「四边平直、能拼接」。

   动画那支：一种地形一张 1024（2×2 = 四帧），切出来每一帧**各自**过一遍
   无缝处理。半格位移+混合是确定性的，四帧本来就近乎一样，处理完还是近乎一样，
   动的那点东西不会被抹掉。
"""
import sys, os, subprocess
from PIL import Image

ARGS = sys.argv[1:]
ANIM = ARGS and ARGS[0] == '--anim'
WALLS = ARGS and ARGS[0] == '--walls'
ARROWS = ARGS and ARGS[0] == '--arrows'
FXS = ARGS and ARGS[0] == '--fx'
if ANIM or WALLS or ARROWS or FXS: ARGS = ARGS[1:]
SHEET, OUT = ARGS[0], ARGS[1]
SHUI = ARGS[2] if len(ARGS) > 2 else None
N, COLORS = 256, 16
KEYS = ['ping', 'shui', 'shan', 'gong']
HERE = os.path.dirname(os.path.abspath(__file__))
os.makedirs(OUT, exist_ok=True)

def seamless(img, dst):
    raw = dst + '.raw.png'
    img.save(raw)
    subprocess.run([sys.executable, os.path.join(HERE, 'make_tile.py'),
                    raw, dst, str(N), str(COLORS)], check=True)
    os.remove(raw)


if FXS:
    # 特效：每张 2×2 四帧，透明底。四帧**共用同一个缩放与同一个中心**——
    # 各帧按自己的包围盒居中的话，火球一大一小之间中心会跳。
    import glob
    F = 128
    for f in sorted(glob.glob(os.path.join(SHEET, 'cfx_*.png'))):
        k = os.path.basename(f)[4:].split('.')[0]
        k = k.rsplit('_', 1)[0] if k.count('_') >= 2 and k.split('_')[-1][:2] == 'mu' else k
        sh = Image.open(f).convert('RGBA')
        if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
        fr = [sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512)) for i in range(4)]
        boxes = [c.split()[3].point(lambda v: 255 if v > 24 else 0).getbbox() for c in fr]
        boxes = [b for b in boxes if b]
        if not boxes: print('空表', k); continue
        # 四帧包围盒的并集决定缩放，居中点用整格中心（提示词要求四帧中心一致）
        w = max(b[2] - b[0] for b in boxes); h = max(b[3] - b[1] for b in boxes)
        sc = min((F - 2) / max(w, 1), (F - 2) / max(h, 1), F / 512 * 1.6)
        for i, c in enumerate(fr):
            small = c.resize((round(512 * sc), round(512 * sc)), Image.BOX)
            small.putalpha(small.split()[3].point(lambda v: 255 if v >= 90 else 0))
            cv = Image.new('RGBA', (F, F), (0, 0, 0, 0))
            cv.paste(small, ((F - small.width) // 2, (F - small.height) // 2), small)
            cv.save(os.path.join(OUT, 'cg_fx_%s_f%d.png' % (k, i)))
        print('%-9s 四帧  缩放 %.3f' % (k, sc))
    raise SystemExit

if ARROWS:
    # 四格共用一个缩放比、按包围盒居中——火矢三帧只有火焰不同，
    # 各缩各的会让箭本身在三帧之间跳动。透明底，缩完把半透明边一刀切硬。
    A = 128
    sh = Image.open(SHEET).convert('RGBA')
    if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
    keys = ['arrow', 'fire_a', 'fire_b', 'fire_c']
    cells = []
    for i, k in enumerate(keys):
        c = sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512))
        b = c.split()[3].point(lambda v: 255 if v > 24 else 0).getbbox()
        cells.append((k, c, b))
    wmax = max(b[2] - b[0] for _, _, b in cells); hmax = max(b[3] - b[1] for _, _, b in cells)
    sc = min((A - 2) / wmax, (A - 2) / hmax)
    for k, c, b in cells:
        fig = c.crop(b)
        w = max(1, round(fig.width * sc)); h = max(1, round(fig.height * sc))
        fig = fig.resize((w, h), Image.BOX)
        fig.putalpha(fig.split()[3].point(lambda v: 255 if v >= 110 else 0))
        cv = Image.new('RGBA', (A, A), (0, 0, 0, 0))
        cv.paste(fig, ((A - w) // 2, (A - h) // 2), fig)
        cv.save(os.path.join(OUT, 'cg_%s.png' % k))
        print('%-7s → cg_%s.png  %dx%d' % (k, k, w, h))
    raise SystemExit

if WALLS:
    W = 128
    sh = Image.open(SHEET).convert('RGB')
    if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
    for i, k in enumerate(['wall_a', 'gate_v', 'gate_h', 'wall_b']):
        c = sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512))
        c = c.resize((W, W), Image.BOX).quantize(colors=COLORS, method=Image.MAXCOVERAGE,
                                                  dither=Image.Dither.NONE).convert('RGB')
        c.save(os.path.join(OUT, 'cg_%s.png' % k))
        print('%-7s → cg_%s.png  %d×%d' % (k, k, W, W))
    raise SystemExit

if ANIM:
    import glob, itertools
    from PIL import ImageChops
    for f in sorted(glob.glob(os.path.join(SHEET, 'cga_*.png'))):
        k = os.path.basename(f).split('_')[1].split('.')[0]
        sh = Image.open(f).convert('RGB')
        if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
        fr = [sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512))
              for i in range(4)]
        worst = 0
        for a, b in itertools.combinations(range(4), 2):
            h = ImageChops.difference(fr[a], fr[b]).convert('L').histogram()
            worst = max(worst, sum(i * n for i, n in enumerate(h)) / sum(h))
        for i, im in enumerate(fr):
            seamless(im, os.path.join(OUT, 'cg_%s_f%d.png' % (k, i)))
        print('%-5s 四帧  帧间平均差 %5.1f%s' % (k, worst, '   ← 抖' if worst > 18 else ''))
    raise SystemExit

sheet = Image.open(SHEET).convert('RGB')
if sheet.size != (1024, 1024):
    sheet = sheet.resize((1024, 1024), Image.LANCZOS)

tmp = os.path.join(OUT, '_raw')
os.makedirs(tmp, exist_ok=True)
for i, k in enumerate(KEYS):
    if k == 'shui' and SHUI:
        src = Image.open(SHUI).convert('RGB')
    else:
        src = sheet.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512))
    raw = os.path.join(tmp, k + '.png')
    src.save(raw)
    dst = os.path.join(OUT, 'cg_%s.png' % k)
    subprocess.run([sys.executable, os.path.join(HERE, 'make_tile.py'),
                    raw, dst, str(N), str(COLORS)], check=True)
    print('%-5s → %s  %d×%d' % (k, os.path.basename(dst), N, N))

# 拼一张 3×3 的平铺检查图：接缝有没有接上，一看便知
chk = Image.new('RGB', (N * 3 * 4 // 2, N * 3 // 2))
for i, k in enumerate(KEYS):
    t = Image.open(os.path.join(OUT, 'cg_%s.png' % k)).convert('RGB')
    blk = Image.new('RGB', (N * 3, N * 3))
    for y in range(3):
        for x in range(3):
            blk.paste(t, (x * N, y * N))
    chk.paste(blk.resize((N * 3 // 2, N * 3 // 2), Image.LANCZOS), (i * N * 3 // 2, 0))
chk.save(os.path.join(OUT, '_tilecheck.png'))
print('平铺检查图 →', os.path.join(OUT, '_tilecheck.png'))
