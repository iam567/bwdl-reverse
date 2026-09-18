# -*- coding: utf-8 -*-
"""把一套地域主题的原图（1024）做成能直接铺进战场的 64×64 像素块。

   两类槽位走两条路：
     地形（ground/water/forest/rock/grass/ambush）——整格都是可重复的地面，
       要过 make_tile 的半格位移接缝处理，不然 3×3 一铺就看得见缝；
     物件（wuku/cang/yao/castle）——中间是一栋建筑，位移会把它撕成两半，
       所以只做降采样 + 限色。

   最后整套过一遍 unify_palette，十张共用一个色板，摆在一起才像一套美术。

   用法: build_theme.py <主题> [原图目录=work/tiles] [成品目录=work/tiles/px]
"""
import sys, os, glob, subprocess
from PIL import Image

THEME = sys.argv[1]
SRC   = sys.argv[2] if len(sys.argv) > 2 else 'work/tiles'
DST   = sys.argv[3] if len(sys.argv) > 3 else 'work/tiles/px'
HERE  = os.path.dirname(os.path.abspath(__file__))
ROOT  = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC   = os.path.join(ROOT, SRC); DST = os.path.join(ROOT, DST)
os.makedirs(DST, exist_ok=True)

SEAMLESS = ['ground', 'water', 'forest', 'rock', 'grass', 'ambush']
OBJECTS  = ['wuku', 'cang', 'yao', 'castle']
N, COLORS = 64, 12

def newest(slot):
    fs = glob.glob(os.path.join(SRC, 'bt_%s_%s_*.png' % (THEME, slot)))
    return sorted(fs, key=os.path.getmtime)[-1] if fs else None

missing = []
for slot in SEAMLESS + OBJECTS:
    src = newest(slot)
    if not src:
        missing.append(slot); continue
    dst = os.path.join(DST, 'bt_%s_%s.png' % (THEME, slot))
    if slot in SEAMLESS:
        subprocess.check_call(['python3', os.path.join(HERE, 'make_tile.py'),
                               src, dst, str(N), str(COLORS)])
    else:
        im = Image.open(src)
        rgba = im.mode == 'RGBA'
        im = im.convert('RGBA' if rgba else 'RGB')
        if im.size != (im.width, im.width):            # 城池那张曾经是 1536×1024
            side = min(im.size)
            l, t = (im.width - side)//2, (im.height - side)//2
            im = im.crop((l, t, l+side, t+side))
        px = im.resize((N, N), Image.BOX)
        # RGBA 不支持 MAXCOVERAGE，只能走 FASTOCTREE
        px = px.quantize(colors=COLORS, method=Image.FASTOCTREE)
        px.convert('RGBA' if rgba else 'RGB').save(dst)
        print('%s → %s  %dx%d  %d 色' % (os.path.basename(src), os.path.basename(dst), N, N, COLORS))

if missing:
    raise SystemExit('缺原图，先去生成：%s' % '、'.join(missing))

subprocess.check_call(['python3', os.path.join(HERE, 'unify_palette.py'), DST, THEME])
print('%s 一套 %d 张做完' % (THEME, len(SEAMLESS) + len(OBJECTS)))
