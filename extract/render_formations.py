# -*- coding: utf-8 -*-
"""还原「阵型选择」与四种阵型在 16×7 阵型格上的实际排布。

   不是抓屏，是照 b.java 的布阵函数逐行复现再画出来：
     b.a(兵棋数, 阵型号)  c.java 菜单选的 0–3 → 我方站位   (b.java:905)
     b.b(兵棋数, 阵型号)  敌方，取 s()%4 随机              (b.java:1006)
     b.S()                只有守方站在城池格(地形码 2)上时才往格子里填城墙 (b.java:3968)
     b.c/a/b(boolean,n)   攻城时守方不按阵型，改成贴墙站    (b.java:855/875/895)

   兵棋数 = (兵力-1)/100 + 2，主将固定在 (列1,行3) / 敌方 (列14,行3)。

   用法: render_formations.py <输出目录> [兵力=1000] [放大=4]
"""
import sys, os
from PIL import Image, ImageDraw, ImageFont

OUT   = sys.argv[1]
TROOP = int(sys.argv[2]) if len(sys.argv) > 2 else 1000
Z     = int(sys.argv[3]) if len(sys.argv) > 3 else 4
HERE  = os.path.dirname(os.path.abspath(__file__))
ROOT  = os.path.normpath(os.path.join(HERE, '..'))
IMG   = os.path.join(ROOT, 'extract/images')
os.makedirs(OUT, exist_ok=True)

BL, BM = 16, 7                       # b.java:1521 阵型格 16 列 × 7 行
def pieces(t): return 1 if t == 0 else (t - 1) // 100 + 2
NB = pieces(TROOP)

def sb(v):                           # 复现 Java 的 byte 截断
    v &= 0xFF
    return v - 256 if v > 127 else v

def deploy_ours(nb, f):
    """b.a(byte,byte) —— 返回 {兵棋序号: (列,行)}"""
    out = {}
    for i in range(1, nb):
        if f == 0:
            q, r = (i-1)//3, (i-1)%3
            col, row = (3, 6-r) if q == 0 else (2, sb(4-r)) if q == 1 else (1, sb(2-r)) if q == 2 else (1, 6)
            out[i] = (col+1, row)
        elif f == 1:
            c = sb(3 - (i-1)//4)
            r = sb(6 - (i+1)//4) if i % 2 == 1 else sb(i//4)
            out[i] = (c+1, r)
        elif f == 2:
            k, n = (sb(i-7), -2) if i > 7 else (i, 0)
            n = sb(n + sb(4 - k//2))
            r = sb(3 - k//2) if k % 2 == 1 else sb(3 + k//2)
            out[i] = (n+1, r)
        else:
            k, n = (sb(i-5), -2) if i > 5 else (i, 0)
            if k > 3: k, n, m = sb(k-3), sb(n-1), -1
            else:     m = 0
            n = sb(n + 4)
            m = sb(m+3) if k == 1 else (sb(m+5) if k == 2 else sb(m+1))
            out[i] = (n+1, m)
    return out

def deploy_enemy(nb, f):
    """b.b(byte,byte) —— 和我方镜像，但不是简单 16-x，各 case 的常数是分别写死的"""
    out = {}
    for i in range(1, nb):
        if f == 0:
            q, r = (i-1)//3, (i-1)%3
            col, row = (12, 6-r) if q == 0 else (13, sb(4-r)) if q == 1 else (14, sb(2-r)) if q == 2 else (14, 6)
            out[i] = (col-1, row)
        elif f == 1:
            c = sb(12 + (i-1)//4)
            r = sb(6 - (i+1)//4) if i % 2 == 1 else sb(i//4)
            out[i] = (c-1, r)
        elif f == 2:
            k, n = (sb(i-7), 2) if i > 7 else (i, 0)
            n = sb(n + sb(11 + k//2))
            r = sb(3 - k//2) if k % 2 == 1 else sb(3 + k//2)
            out[i] = (n-1, r)
        else:
            k, n = (sb(i-5), 2) if i > 5 else (i, 0)
            if k > 3: k, n, m = sb(k-3), sb(n+1), -1
            else:     m = 0
            n = sb(n + 11)
            m = sb(m+3) if k == 1 else (sb(m+5) if k == 2 else sb(m+1))
            out[i] = (n-1, m)
    return out

def deploy_wall(nb, ours):
    """b.c(boolean,n) → a()/b() —— 攻城时守方贴墙站：先上墙线(行0/行6)，站满 6 个
       之后剩下的退到内侧纵列。"""
    out = {}
    n_wall = min(6, nb - 1)
    for i in range(1, n_wall + 1):
        col = 8 - (i-1)//2 if ours else 7 + (i-1)//2
        out[i] = (col, (i-1) % 2 * 6)
    for j in range(1, nb - 1 - n_wall + 1):
        out[nb - j] = (2 if ours else 13, j)
    return out

def walls(attacker_is_us):
    """b.S() 的 bH==2 分支。cl 为真 = 我方是攻方，城墙在右(11列)；
       为假 = 我方守城，城墙在左(4列)。值 32/16/2 照抄，不臆测含义。"""
    g = {}
    if attacker_is_us:
        for r in (0, 6, 1, 5): g[(11, r)] = 32
        g[(9, 3)] = 32
        g[(6, 0)] = 2;  g[(6, 6)] = 2
        g[(10, 1)] = 16; g[(10, 5)] = 16
        for c in range(6, 10): g[(c, 1)] = 32; g[(c, 5)] = 32
    else:
        for r in (0, 6, 1, 5): g[(4, r)] = 32
        g[(6, 3)] = 32
        g[(9, 0)] = 2;  g[(9, 6)] = 2
        g[(5, 1)] = 16; g[(5, 5)] = 16
        for c in range(6, 10): g[(c, 1)] = 32; g[(c, 5)] = 32
    return g

# ── 画一张阵型格 ────────────────────────────────────────
CELL = 16
FONT  = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 13)
SMALL = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 11)
WALLC = {32: (120,120,128), 16: (196,150,60), 2: (150,90,60)}

ICONS = None
def icon(f):
    """lib49 下半排那四个 27×27 的小图就是游戏自己画的阵型示意，拿来比对"""
    global ICONS
    if ICONS is None: ICONS = Image.open(os.path.join(IMG, 'lib49.png')).convert('RGBA')
    x = 2 + f * 31
    return ICONS.crop((x, 41, x + 27, 41 + 27))

def draw_grid(title, ours, enemy, wall=None, note='', f=None):
    W, H = BL*CELL, BM*CELL
    PAD = 76 if f is not None else 0
    im = Image.new('RGB', (W + PAD, H+46), (30,30,34))
    d = ImageDraw.Draw(im)
    d.text((2, 2), title, font=FONT, fill=(240,230,180))
    oy = 20
    for c in range(BL):
        for r in range(BM):
            d.rectangle([c*CELL, oy+r*CELL, c*CELL+CELL-1, oy+r*CELL+CELL-1],
                        fill=(52,58,48), outline=(38,42,36))
    for (c, r), v in (wall or {}).items():
        d.rectangle([c*CELL, oy+r*CELL, c*CELL+CELL-1, oy+r*CELL+CELL-1],
                    fill=WALLC.get(v, (90,90,90)), outline=(20,20,20))
        d.text((c*CELL+3, oy+r*CELL+2), str(v), font=SMALL, fill=(20,20,20))
    def put(pos, col, lab):
        c, r = pos
        if not (0 <= c < BL and 0 <= r < BM): return
        d.rectangle([c*CELL+2, oy+r*CELL+2, c*CELL+CELL-3, oy+r*CELL+CELL-3],
                    fill=col, outline=(15,15,15))
        if lab: d.text((c*CELL+4, oy+r*CELL+2), lab, font=SMALL, fill=(255,255,255))
    put((1, 3), (40,90,220), '主')                       # 我方主将 bS[0][0]=1
    for p in ours.values():  put(p, (70,130,240), '')
    put((BL-2, 3), (200,50,50), '主')                    # 敌方主将 bS[1][0]=bL-2
    for p in enemy.values(): put(p, (225,95,95), '')
    if note: d.text((2, oy+H+4), note, font=SMALL, fill=(170,170,170))
    if f is not None:
        ic = icon(f).resize((54, 54), Image.NEAREST)
        bg = Image.new('RGBA', ic.size, (24,24,30,255)); bg.alpha_composite(ic)
        im.paste(bg.convert('RGB'), (W + 10, oy + 26))
        d.text((W + 10, oy + 8), '原版示意', font=SMALL, fill=(200,200,140))
    return im.resize((im.width*Z//2, im.height*Z//2), Image.NEAREST)

NAMES = ['阵型 0', '阵型 1', '阵型 2', '阵型 3']
cards = []
for f in range(4):
    cards.append(draw_grid('野战 · %s（我方选，敌方另取 s()%%4 随机）'%NAMES[f],
                           deploy_ours(NB, f), deploy_enemy(NB, f), f=f,
                           note='兵棋 %d 个（兵力 %d）。格子无障碍：平原/水战/山林在这一层完全一样'%(NB, TROOP)))
cards.append(draw_grid('攻城 · 我方是攻方（城墙在右）',
                       deploy_ours(NB, 1), deploy_wall(NB, False), wall=walls(True),
                       note='守方不按阵型，贴墙站：先占行0/行6的墙线，站满 6 个后余部退到内侧纵列'))
cards.append(draw_grid('攻城 · 我方守城（城墙在左）',
                       deploy_wall(NB, True), deploy_enemy(NB, 1), wall=walls(False),
                       note='镜像。32/16/2 是 b.S() 里写死的三种格子标记，照抄未臆测含义'))

cw = max(c.width for c in cards)
sheet = Image.new('RGB', (cw+16, sum(c.height+10 for c in cards)+8), (20,20,24))
y = 6
for c in cards: sheet.paste(c, (8, y)); y += c.height + 10
sheet.save(os.path.join(OUT, '_阵型对照.png'))
for i, c in enumerate(cards): c.save(os.path.join(OUT, 'f%d.png' % i))
print('阵型对照 → %s/_阵型对照.png %s' % (OUT, sheet.size))

# ── 阵型选择画面本身 ────────────────────────────────────
def lib(n): return Image.open(os.path.join(IMG, 'lib%d.png' % n)).convert('RGBA')
scr = Image.new('RGBA', (176, 220), (0,0,0,255))
scr.alpha_composite(lib(302))                                  # 底图
f90 = lib(90); scr.alpha_composite(f90, (88 - f90.width//2, 40))
f49 = lib(49); scr.alpha_composite(f49, (88 - f49.width//2, 73))
d = ImageDraw.Draw(scr)
d.rectangle([25, 114, 25+27, 114+27], outline=(248,91,210))     # 25+ch*33, 27×27
d.text((30, 96), '夏侯惇', font=SMALL, fill=(255,255,255))
d.text((108, 96), '颜良',  font=SMALL, fill=(255,255,255))
t = '阵型选择'; w = d.textlength(t, font=FONT)
d.text((88-w/2, 200), t, font=FONT, fill=(255,255,255))
scr.convert('RGB').resize((176*3, 220*3), Image.NEAREST).save(os.path.join(OUT, '_阵型选择画面.png'))
print('阵型选择画面 → %s/_阵型选择画面.png' % OUT)
