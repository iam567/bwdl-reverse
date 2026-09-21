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
if ANIM or WALLS: ARGS = ARGS[1:]
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
