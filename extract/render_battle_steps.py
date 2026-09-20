# -*- coding: utf-8 -*-
"""把原版战斗从头到尾逐步还原成截图。

   没有 J2ME 模拟器，所以这些不是抓屏，是**照着反编译的绘制代码用原版素材重画**。
   凡是代码里写死的坐标都照抄（下面每步标了出处行号），
   只有兵力/武将名这类运行时数据是我填的示例值。

   屏幕 176×220（c.java:456 `bk=176` / 457 `bl=220`），
   地图区 11 格 × 13 格 × 16px = 176×208，底部指令条 176×19 贴底。

   用法: render_battle_steps.py <输出目录> [城池ID=13] [放大=3]
"""
import sys, os
from PIL import Image, ImageDraw, ImageFont

OUT  = sys.argv[1]
CID  = sys.argv[2] if len(sys.argv) > 2 else '13'
ZOOM = int(sys.argv[3]) if len(sys.argv) > 3 else 3
HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..'))
IMG  = os.path.join(ROOT, 'extract/images')
os.makedirs(OUT, exist_ok=True)

W, H = 176, 220
COLS, ROWS = 11, 13
# PingFang.ttc PIL 打不开，用黑体
FONT = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 12)
SMALL = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 10)

def lib(n): return Image.open(os.path.join(IMG, 'lib%d.png' % n)).convert('RGBA')

tiles = lib(31); units = lib(51); bar = lib(52); cursor = lib(2)
ground = lib(98); wall = lib(306); figs = lib(56); hpbar = lib(11)

grid = []
with open(os.path.join(ROOT, 'extract/data/battlemaps/%s.map' % CID)) as f:
    for line in f:
        line = line.strip()
        if line: grid.append([int(v) for v in line.split(',') if v != ''])

# c.O() 的重映射：存的数字不是块号
def remap(code):
    if code == 0: return [6]
    if code in (1, 3, 4): return [16]
    if code == 2: return [17]
    if code == 6: return [13]
    if code == 5: return [5, 7]          # 这一格要叠两层
    if 7 <= code <= 12: return [9]
    return [code]

def blit_map(im, ox, oy):
    """c.O() c.java:2963 —— 逐格铺，视口 11×13"""
    for r in range(ROWS):
        for c in range(COLS):
            y, x = r + oy, c + ox
            if y >= len(grid) or x >= len(grid[y]): continue
            for t in remap(grid[y][x] & 0xF):
                im.paste(tiles.crop((t*16, 0, t*16+16, 16)), (c*16, r*16), tiles.crop((t*16,0,t*16+16,16)))

def unit(im, col, row, side, arm, troops):
    """c.Q() c.java:2996 —— 列 = 兵力/100（0..10 封顶），行 = 兵种，敌方 +3"""
    n = min(10, troops // 100)
    ry = (arm - 1) if side == 'blue' else (arm + 2)
    s = units.crop((n*15, ry*15, n*15+16, ry*15+16))
    im.paste(s, (col*16, row*16), s)

def bottom(im, title, menu=None, sel=0):
    """c.Q() 尾部：bb[6]=lib52 贴底；开菜单时换第二行并画黄框"""
    d = ImageDraw.Draw(im)
    row = 19 if menu else 0
    im.paste(bar.crop((0, row, 176, row+19)), (0, H-19))
    if menu is not None:
        # drawRect(9 + 27*O - 1, bl-16, 25, 13) 黄框
        d.rectangle([9+27*sel-1, H-16, 9+27*sel-1+25, H-16+13], outline=(255,255,0))
    if title:
        w = d.textlength(title, font=FONT)
        d.text((88 - w/2, H-19-14), title, font=FONT, fill=(255,255,255))

def hud(im, mp, food, gold):
    """机动力/粮食/金子，右对齐在 x=176，y=5/25/45"""
    d = ImageDraw.Draw(im)
    for y, s in ((5,'机动力:%d'%mp), (25,'粮食: %d'%food), (45,'金子: %d'%gold)):
        w = d.textlength(s, font=SMALL)
        d.rectangle([176-w-3, y-1, 175, y+11], fill=(0,0,0))
        d.text((176-w-2, y), s, font=SMALL, fill=(255,255,255))

def newscreen():
    return Image.new('RGBA', (W, H), (0,0,0,255))

steps = []
def save(name, im, caption):
    im = im.convert('RGB').resize((W*ZOOM, H*ZOOM), Image.NEAREST)
    p = os.path.join(OUT, name + '.png'); im.save(p)
    steps.append((name, caption)); print('%-28s %s' % (name+'.png', caption))

OX, OY = 8, 4        # 视口偏移，取一块有城池和水的地方
ARMY = [(3,7,'blue',1,900,'夏侯惇'), (2,8,'blue',3,700,'乐进'), (4,8,'blue',2,500,'李典')]
ENEMY= [(6,5,'red',1,1000,'颜良'), (7,6,'red',3,800,'张郃')]

def battlefield(cur=None, moving=False):
    im = newscreen(); blit_map(im, OX, OY)
    for c,r,s,a,t,_ in ARMY + ENEMY: unit(im, c, r, s, a, t)
    if cur:
        d = ImageDraw.Draw(im)
        if moving:
            d.rectangle([cur[0]*16, cur[1]*16, cur[0]*16+15, cur[1]*16+15], outline=(248,91,210))
        else:
            im.paste(cursor, (cur[0]*16+8, cur[1]*16+8), cursor)
    return im

# ── 1 开战：逐个摆初始位置 ────────────────────────────────
im = battlefield((3,7), moving=True); hud(im, 24, 2400, 1800)
bottom(im, '初始位置(敌:左上)')
save('01_初始位置', im, 'c.j() —— 我方逐个武将摆初始站位，标题写着敌军来向')

# ── 2 选择武将下命令 ─────────────────────────────────────
im = battlefield((3,7)); hud(im, 24, 2400, 1800)
bottom(im, None, menu=True, sel=0)
save('02_指令条', im, 'lib52 第二行就是「移动 计策 攻击 情报 撤退 待机」，黄框是光标')

# ── 3 移动 ──────────────────────────────────────────────
im = battlefield((5,6), moving=True); hud(im, 18, 2400, 1800)
bottom(im, '武将移动')
save('03_武将移动', im, 'i(0) —— 机动力按地形逐格扣，b.h(格,兵种) 平/水/山各有主场')

# ── 4 选目标 ────────────────────────────────────────────
im = battlefield((6,5)); hud(im, 18, 2400, 1800)
bottom(im, '选择欲攻击敌将')
save('04_选择目标', im, 'i(2) —— 必须相邻(I()判定)，攻击本身再扣 2 点机动力')

# ── 5/6 战斗演出：地面按地形选 ─────────────────────────
def clash(kind, msg, siege=False):
    """e(地形码)→0平原/1水/2山林/3攻城；lib98 四块 64×64 地面，攻城再叠 lib306 城墙"""
    im = newscreen()
    g = ground.crop((kind*64, 0, kind*64+64, 64)).resize((176, 132), Image.NEAREST)
    im.paste(g, (0, 22))
    if siege:
        w = wall.crop((0,0,16,16)).resize((176, 34), Image.NEAREST)
        im.paste(w, (0, 40))
    for i in range(5):                      # 我方一列
        f = figs.crop((16*(i%6)+96, 16, 16*(i%6)+112, 32))
        im.paste(f, (16 + i*18, 128), f)
    for i in range(5):                      # 敌方一列
        f = figs.crop((16*(i%6), 0, 16*(i%6)+16, 16))
        im.paste(f, (16 + i*18, 58), f)
    im.paste(hpbar, (0, 44), hpbar); im.paste(hpbar, (0, 150), hpbar)
    d = ImageDraw.Draw(im)
    d.text((6, 4), '颜良  1000', font=SMALL, fill=(255,120,120))
    d.text((6, 172), '夏侯惇  900', font=SMALL, fill=(140,180,255))
    w = d.textlength(msg, font=FONT)
    d.text((88-w/2, 190), msg, font=FONT, fill=(255,255,0))
    return im

save('05_野战', clash(0, '攻击敌方中'), 'ID 22 —— 地面取 lib98 第 1 块（平原）；上红下蓝，两条 lib11 是兵力')
save('06_攻城', clash(3, '攻击敌方中', siege=True), '守方站在地形码 2 上时 e()=3，阵型格 bK 会加城墙（b.java:3986）')

# ── 7 单挑 ──────────────────────────────────────────────
im = newscreen()
g = ground.crop((0,0,64,64)).resize((176, 150), Image.NEAREST); im.paste(g, (0, 30))
a = figs.crop((0,0,16,16)).resize((48,48), Image.NEAREST)
b = figs.crop((96,16,112,32)).resize((48,48), Image.NEAREST)
im.paste(a, (26, 60), a); im.paste(b, (102, 108), b)
im.paste(hpbar, (0, 44), hpbar); im.paste(hpbar, (0, 166), hpbar)
d = ImageDraw.Draw(im)
d.text((6, 4), '颜良   体力 62', font=SMALL, fill=(255,120,120))
d.text((6, 186), '夏侯惇 体力 71', font=SMALL, fill=(140,180,255))
msg = '击毙敌将! 体力减少18'
w = d.textlength(msg, font=FONT); d.text((88-w/2, 204), msg, font=FONT, fill=(255,255,0))
save('07_单挑', im, 'ID 21 —— 打的是体力 L[]，不是兵力；结果文案取自 d.m[]')

# ── 8 武将信息面板 ──────────────────────────────────────
im = battlefield()
d = ImageDraw.Draw(im)
d.rectangle([23,25,153,160], fill=(162,160,136), outline=(153,102,0))   # c.a(Graphics) 原坐标
icons = lib(15)
for n in range(8):
    ic = icons.crop((n*15,0,n*15+15,15))
    im.paste(ic, (35 + n%2*50, 89 + n//2*16), ic)
d.text((95, 37), '颜良', font=FONT, fill=(255,255,255))
d.text((105, 58), 'LV. 3', font=SMALL, fill=(255,255,255))
for x,y,v in ((55,88,'94'),(111,88,'48'),(55,105,'62'),(111,105,'55'),
              (55,122,'1000'),(111,122,'88'),(55,138,'骑兵'),(105,138,'弓')):
    d.text((x,y), v, font=SMALL, fill=(255,255,255))
bottom(im, '武将信息')
save('08_武将信息', im, 'c.a(Graphics) c.java:3020 —— 130×135 面板，8 个属性图标来自 lib15')

# ── 9 计策选择 ──────────────────────────────────────────
im = battlefield()
d = ImageDraw.Draw(im)
d.rectangle([12,27,164,157], fill=(162,160,136), outline=(153,102,0))
d.line([12,110,164,110], fill=(153,102,0))
name = '十面埋伏'
w = d.textlength(name, font=FONT); d.text((88-w/2, 118), name, font=FONT, fill=(255,255,0))
txt = ('我军在树林中,施用此计,我军自损100兵,'
       '但如果敌军进入此树林,则伤亡惨重.需机动力:10')
y = 35
while txt:
    cut = 12
    d.text((15, y), txt[:cut], font=SMALL, fill=(255,255,255)); txt = txt[cut:]; y += 13
d.text((150, 132), '水攻', font=SMALL, fill=(200,200,200))
bottom(im, '请选择使用计策')
save('09_计策选择', im, 'c.P(Graphics) c.java:3060 —— 计策名取 d.e[]，说明取 d.q[]，共 16 条')

# ── 拼总览 ──────────────────────────────────────────────
ims = [Image.open(os.path.join(OUT, n + '.png')) for n, _ in steps]
cw, ch = ims[0].size
cols = 5; rows = (len(ims) + cols - 1) // cols
sheet = Image.new('RGB', (cols*(cw+8)+8, rows*(ch+30)+8), (22,22,26))
sd = ImageDraw.Draw(sheet)
F = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 16)
for i, (im, (n, _)) in enumerate(zip(ims, steps)):
    x, y = 8 + (i%cols)*(cw+8), 8 + (i//cols)*(ch+30)
    sheet.paste(im, (x, y)); sd.text((x+2, y+ch+4), n, font=F, fill=(230,230,230))
sheet.save(os.path.join(OUT, '_全流程.png'))
print('\n总览 → %s/_全流程.png  %s' % (OUT, sheet.size))
