# -*- coding: utf-8 -*-
"""还原原版「城内管理」画面（画面 ID 6，176×220）。

   这不是模拟器截图，是按 c.java 的 n() / U() / T() 逐条重放绘制指令：
   外框、城池图、信息面板、指令条、头像都是原版素材，
   只有文字用系统字体重画（原版用的是手机内置点阵字，拿不到）。

   用法: render_city_screen.py [城池ID] [底部选中格 0-5] [展开菜单并选中第几条 0-3]
   例:   render_city_screen.py 19 0 1      # 陈留，军事 → 战争
"""
import csv, json, sys
from PIL import Image, ImageDraw, ImageFont

IMG='extract/images/%s.png'
AV='extract/avatar/%s.png'
CITY_ID=int(sys.argv[1]) if len(sys.argv)>1 else 19        # 默认 陈留
BN=int(sys.argv[2]) if len(sys.argv)>2 else 0              # 底部指令条选中第几格
BO=int(sys.argv[3]) if len(sys.argv)>3 else -1            # >=0 则展开指令菜单(T)，并选中第几条

OUT1, OUT4 = 'work/city_screen.png', 'work/city_screen_4x.png'
rows={int(r[0]):r for r in csv.reader(open('extract/data/cities.csv'))}
offs={int(r[0]):r for r in csv.reader(open('extract/data/officers.csv'))}
names={c['id']:c['name'] for c in json.load(open('extract/data/city-screen.json'))}
r=rows[CITY_ID]
# 文档列号是 1 基，这里换成 0 基索引
col=lambda n: r[n-1]
ruler, gov = col(3), col(5)
vals=dict(k=col(6), l=col(7), m=col(8), n=col(9), o=col(10), p=col(11), q=col(12), u=col(14))
ncity=sum(1 for x in rows.values() if x[2]==ruler)
ngen=sum(1 for o in offs.values() if o[14]==str(CITY_ID) and int(o[13])<=189)
aG=min(max(ncity,3),8)
print('城池', names[CITY_ID], '| 君主', ruler, '| 太守', gov, '| 该势力城池数', ncity, '→ 指令牌', aG)
print('字段:', vals, '| 城内武将', ngen)

W,H=176,220
cv=Image.new('RGBA',(W,H),(0,0,0,255))
put=lambda im,x,y: cv.alpha_composite(im,(x,y))
bh=Image.open(IMG%'lib302').convert('RGBA'); put(bh,0,0)                    # 全屏外框
bg=Image.open(IMG%'lib303').convert('RGBA'); put(bg,88-bg.width//2,40)     # 城池图
b1=Image.open(IMG%'lib10').convert('RGBA');  put(b1,88-b1.width//2,48)     # 信息面板
# 太守头像
gid=[i for i,o in offs.items() if o[1]==gov]
av=Image.open(AV%(gid[0] if gid else 1)).convert('RGBA'); put(av,102,65)
b2=Image.open(IMG%'lib12').convert('RGBA');  put(b2,11,(H-3)-b2.height)    # 底部指令条
b4=Image.open(IMG%'lib15').convert('RGBA')                                  # 图标条取一格
put(b4.crop((120,0,135,15)),110,46)

g=ImageDraw.Draw(cv)
fnt=ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc',11)
def s(t,x,y,c):                                   # anchor 17 = TOP|HCENTER
    g.text((x,y),str(t),font=fnt,fill=c,anchor='ma')
ORANGE=(0xF8,0x59,0x52); WHITE=(255,255,255)
s('189年1月',88,3,(240,225,190))                  # W(): 标题栏
s(aG,133,45,ORANGE)
s(names[CITY_ID],75,45,WHITE)
s(ruler,74,66,WHITE)
s(gov,74,81,WHITE)
s(vals['k'],74,99,WHITE)
s(int(vals['p'])//1000,61,116,WHITE)
s(ngen,96,116,WHITE)                               # r[] = 城内武将数，运行时数出来
s(int(vals['u'])//100,133,116,WHITE)
s(vals['n'],61,131,WHITE); s(vals['o'],96,131,WHITE); s(vals['q'],133,131,WHITE)
s(vals['l'],66,146,WHITE); s(vals['m'],123,146,WHITE)
g.rectangle([9+27*BN,H-16,9+27*BN+25,H-16+14],outline=(255,255,0))          # 黄色选中框

if BO>=0:                                                                   # T(): 指令菜单弹层
    b3=Image.open(IMG%'lib307').convert('RGBA')
    dst=(88-148//2, (H-19)-21)                                              # anchor 33 = BOTTOM|HCENTER
    put(b3.crop((0,0,148,21)),*dst)                                         # 第 0 行 = 底板
    put(b3.crop((0,(BN+1)*21,148,(BN+2)*21)),*dst)                          # 第 bn+1 行 = 该类指令
    g.rectangle([21+36*BO,H-37,21+36*BO+26,H-37+14],outline=(255,255,0))

cv.convert('RGB').save(OUT1)
cv.resize((W*4,H*4),Image.NEAREST).convert('RGB').save(OUT4)
print('→', OUT1, '/', OUT4)
