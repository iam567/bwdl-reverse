# -*- coding: utf-8 -*-
"""把战斗要用的素材投进 Unity 工程的 StreamingAssets。

   原则：做过新素材的用新的，没做过的先用原版 jar 里抽出来的那份。
     新  战场地块（5 套地域 × 10 槽位，64px）、战场部队（蓝红 × 3 兵种 × 5 方位）、武将头像
     旧  阵型选择的卷轴与 VS 框、阵型战的地面/小兵/兵力条/城墙、属性图标、指令条

   用法: stage_battle_assets.py [工程目录=unity/SangoCityView]
"""
import sys, os, shutil, glob, json, csv
from PIL import Image

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
PROJ = os.path.join(ROOT, sys.argv[1] if len(sys.argv) > 1 else 'unity/SangoCityView')
DST  = os.path.join(PROJ, 'Assets/StreamingAssets/Art/battle')

for sub in ('tiles', 'units', 'maps', 'orig', 'avatar', 'duel', 'duelskin', 'clash', 'battleui'):
    os.makedirs(os.path.join(DST, sub), exist_ok=True)

n = 0
for f in glob.glob(os.path.join(ROOT, 'assets/battletile/px/*.png')):
    shutil.copy2(f, os.path.join(DST, 'tiles', os.path.basename(f))); n += 1
print('地块  %d 张（新）' % n)

n = 0
for f in glob.glob(os.path.join(ROOT, 'assets/battleunit/px/*.png')):
    shutil.copy2(f, os.path.join(DST, 'units', os.path.basename(f))); n += 1
print('部队  %d 张（新）' % n)

n = 0
for f in glob.glob(os.path.join(ROOT, 'extract/data/battlemaps/*.map')):
    shutil.copy2(f, os.path.join(DST, 'maps', os.path.basename(f))); n += 1
print('战场  %d 张 .map' % n)

# 原版素材：这几样还没做新的
ORIG = {302: '阵型选择的卷轴底', 90: 'VS 框', 49: '四个阵型图标',
        98: '阵型战四种地面', 56: '阵型战小兵', 11: '兵力条',
        306: '城墙', 15: '属性图标', 52: '指令条', 51: '战棋层旧单位(备用)',
        58: '被击中的爆点', 305: '阵型战信息栏底', 63: '箭',
        31: '旧地形块(备用)', 2: '光标'}
for k, why in ORIG.items():
    src = os.path.join(ROOT, 'extract/images/lib%d.png' % k)
    if os.path.exists(src): shutil.copy2(src, os.path.join(DST, 'orig', 'lib%d.png' % k))
    else: print('  缺 lib%d（%s）' % (k, why))
print('原版  %d 张' % len(ORIG))

# 单挑：15 帧骑将（已对位）、四张背景、面板与光标
n = 0
for f in glob.glob(os.path.join(ROOT, 'assets/duel/px/*')):
    shutil.copy2(f, os.path.join(DST, 'duel', os.path.basename(f))); n += 1
# 一城一张的攻城背景动画，四帧一组，和别的单挑背景放一起
for f in glob.glob(os.path.join(ROOT, 'assets/duelanim/px/*.jpg')):
    shutil.copy2(f, os.path.join(DST, 'duel', os.path.basename(f))); n += 1
print('单挑  %d 张（新，含攻城背景动画）' % n)

# 阵型战小兵：4 兵种 × 2 阵营 × 3 向（朝左是朝右镜像，绘制时翻）
n = 0
for f in glob.glob(os.path.join(ROOT, 'assets/clash/px/*.png')):
    shutil.copy2(f, os.path.join(DST, 'clash', os.path.basename(f))); n += 1
print('小兵  %d 张（新）' % n)

# 战斗界面的底图：九宫格面板 / 按钮 / 头像框 / 小地图内衬
n = 0
for f in glob.glob(os.path.join(ROOT, 'assets/battleui/px/*')):
    shutil.copy2(f, os.path.join(DST, 'battleui', os.path.basename(f))); n += 1
print('界面  %d 张（新）' % n)

# 62 套单挑立绘，一套一个目录
n = 0
for d in sorted(glob.glob(os.path.join(ROOT, 'assets/duelskin/px/*'))):
    if not os.path.isdir(d): continue
    dst = os.path.join(DST, 'duelskin', os.path.basename(d))
    os.makedirs(dst, exist_ok=True)
    for f in glob.glob(os.path.join(d, '*.png')):
        shutil.copy2(f, os.path.join(dst, os.path.basename(f))); n += 1
print('立绘  %d 张（新，%d 套）' % (n, len(glob.glob(os.path.join(DST, 'duelskin', '*')))))
shutil.copy2(os.path.join(ROOT, 'extract/data/duel-skin.json'), os.path.join(DST, 'duel-skin.json'))

shutil.copy2(os.path.join(ROOT, 'extract/data/city-theme.json'), os.path.join(DST, 'city-theme.json'))
shutil.copy2(os.path.join(ROOT, 'extract/data/officers.csv'),    os.path.join(DST, 'officers.csv'))

# 头像按武将 ID 重命名，运行时好查。
# 原图 1024²，一张 250KB，265 张就是 65MB；战斗界面里只显示 60–90px，
# 缩到 256² 够用，整体掉到 5MB 左右。
rows = list(csv.reader(open(os.path.join(ROOT, 'extract/data/officers.csv'), encoding='utf-8')))
ids = {r[0] for r in rows}
n = 0
for f in glob.glob(os.path.join(ROOT, 'assets/avatar/avatar_*.jpg')):
    oid = os.path.basename(f).split('_')[1]
    if oid in ids:
        im = Image.open(f).convert('RGB')
        if max(im.size) > 256: im = im.resize((256, 256), Image.LANCZOS)
        im.save(os.path.join(DST, 'avatar', '%s.jpg' % oid), quality=88)
        n += 1
print('头像  %d 张（新）' % n)

size = sum(os.path.getsize(os.path.join(dp, f))
           for dp, _, fs in os.walk(DST) for f in fs)
print('合计 %.1f MB → %s' % (size/1e6, os.path.relpath(DST, ROOT)))
