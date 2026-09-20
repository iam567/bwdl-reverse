# -*- coding: utf-8 -*-
"""还原原版单挑画面（画面 22）。

   不是抓屏，是照 c.C(Graphics) / c.A(Graphics) 的坐标用原版素材重画。
   坐标全部照抄，标了出处行号：

     lib59  整屏背景 176×220                    C():  drawImage(bb[0], 0, 0)
     名字   我方 x=44、敌方 x=132，y=1，居中      C():  bk>>2 与 bk*3>>2
     体力条 y=24..29，每点 3/4 px，共 100 点      C():  drawLine(10 + by*3/4, 24, ...)
     体力数 左 (0,29)、右 (176,29)                C()
     两员   lib304 第 5 帧，(5,110) 与 (115,110)  aB = 110，右边那个水平镜像
     菜单   lib60 面板 (5,37)                     A():  drawImage(bb[1], 5, 37)
     立绘   /res/avatar/<武将ID>.png  (12,42)     A():  drawImage(bb[3], 12, 42)
     光标   lib109，(60+37n, 52+24n2)             A():  3 列 × 2 行

   六招写死在 lib60 那张图里：牵制 / 攻击 / 全力 / 劝降 / 逃跑 / 投降。

   用法: render_duel.py <输出目录> [我方武将ID=7] [敌方ID=196] [放大=4]
"""
import sys, os
from PIL import Image, ImageDraw, ImageFont

OUT  = sys.argv[1]
MY   = sys.argv[2] if len(sys.argv) > 2 else '7'      # 关羽
FOE  = sys.argv[3] if len(sys.argv) > 3 else '196'    # 吕布
Z    = int(sys.argv[4]) if len(sys.argv) > 4 else 4
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..'))
IMG  = os.path.join(ROOT, 'extract/images')
AV   = os.path.join(ROOT, 'extract/avatar')
os.makedirs(OUT, exist_ok=True)

W, H, AB = 176, 220, 110
FONT  = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 11)
SMALL = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 10)
HOT   = (248, 91, 210)                       # C() 里的 16275794

def lib(n): return Image.open(os.path.join(IMG, 'lib%d.png' % n)).convert('RGBA')
def frame(sheet, i): return sheet.crop((i*60, 0, i*60+60, 60))

bg, menu, cur, duel = lib(59), lib(60), lib(109), lib(304)

def screen(my_hp, foe_hp, my_name, foe_name, my_frame=5, foe_frame=5,
           show_menu=False, cursor=0, caption=''):
    im = Image.new('RGBA', (W, H), (0, 0, 0, 255))
    im.alpha_composite(bg, (0, 0))
    d = ImageDraw.Draw(im)

    for nm, cx in ((my_name, 44), (foe_name, 132)):        # 居中锚点
        w = d.textlength(nm, font=FONT)
        d.text((cx - w/2, 1), nm, font=FONT, fill=(255, 255, 255))

    for i in range(100):                                    # 体力条，100 点 × 3/4 px
        d.line([(10 + i*3//4, 24), (10 + i*3//4, 29)],
               fill=HOT if i < my_hp else (255, 255, 255))
        d.line([(166 - i*3//4, 24), (166 - i*3//4, 29)],
               fill=HOT if i < foe_hp else (255, 255, 255))
    d.text((1, 20), str(my_hp), font=SMALL, fill=HOT)
    w = d.textlength(str(foe_hp), font=SMALL)
    d.text((175 - w, 20), str(foe_hp), font=SMALL, fill=HOT)

    a = frame(duel, my_frame)
    b = frame(duel, foe_frame).transpose(Image.FLIP_LEFT_RIGHT)
    im.alpha_composite(a, (5, AB)); im.alpha_composite(b, (115, AB))

    if show_menu:
        im.alpha_composite(menu, (5, 37))
        p = os.path.join(AV, MY + '.png')
        if os.path.exists(p): im.alpha_composite(Image.open(p).convert('RGBA'), (12, 42))
        n, n2 = cursor % 3, cursor // 3
        im.alpha_composite(cur, (60 + 37*n, 52 + 24*n2))

    if caption:
        w = d.textlength(caption, font=FONT)
        d.text((W/2 - w/2, H - 17), caption, font=FONT, fill=(60, 40, 20))
    return im.convert('RGB').resize((W*Z, H*Z), Image.NEAREST)

shots = [
    ('1_选招',   screen(97, 99, '关羽', '吕布', show_menu=True, cursor=1,
                        caption='牵制 攻击 全力 劝降 逃跑 投降')),
    ('2_交手',   screen(97, 99, '关羽', '吕布', my_frame=3, foe_frame=5, caption='关羽 全力一击')),
    ('3_对方反击', screen(71, 99, '关羽', '吕布', my_frame=5, foe_frame=2, caption='吕布 攻击')),
    ('4_落马',   screen(71, 0, '关羽', '吕布', my_frame=5, foe_frame=13, caption='吕布 战亡沙场.')),
]
for name, im in shots:
    im.save(os.path.join(OUT, name + '.png')); print(name)

cw, ch = shots[0][1].size
F = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 18)
sheet = Image.new('RGB', (cw*len(shots) + 8*(len(shots)+1), ch + 34), (22, 22, 26))
sd = ImageDraw.Draw(sheet)
for i, (n, im) in enumerate(shots):
    x = 8 + i*(cw+8); sheet.paste(im, (x, 6)); sd.text((x, ch+10), n, font=F, fill=(230, 225, 200))
sheet.save(os.path.join(OUT, '_单挑全流程.png'))
print('总览 →', sheet.size)
