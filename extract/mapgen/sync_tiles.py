# -*- coding: utf-8 -*-
"""把战场地块同步进仓库。

   每个槽位只留一张原图——最新的那张，或 pick.json 钉住的那张。
   中间那些被否掉的版本不进仓库：留着只是占地方，
   真要追溯，提交信息里写了每一版为什么被否。

   原图转成 JPEG（1024px 的 PNG 一张一兆多，50 张六十兆）。
   它只是留档，真正进游戏的是 px/ 下那份 64×64——那份保持 PNG 无损。
   就算日后拿 JPEG 重新过一遍 build_theme，BOX 降到 64 再限到 12 色，
   压缩痕迹也剩不下什么。

   用法: sync_tiles.py [原图目录=work/tiles] [仓库目录=assets/battletile]
"""
import sys, os, glob, json, shutil
from PIL import Image

HERE = os.path.dirname(os.path.abspath(__file__))
ROOT = os.path.normpath(os.path.join(HERE, '..', '..'))
SRC  = os.path.join(ROOT, sys.argv[1] if len(sys.argv) > 1 else 'work/tiles')
DST  = os.path.join(ROOT, sys.argv[2] if len(sys.argv) > 2 else 'assets/battletile')

THEMES = ['zhongyuan', 'jiangnan', 'saibei', 'xiyu', 'nanzhong']
SLOTS  = ['ground','water','forest','rock','grass','ambush','castle','wuku','cang','yao']
PICKS  = json.load(open(os.path.join(SRC,'pick.json'))) if os.path.exists(os.path.join(SRC,'pick.json')) else {}

os.makedirs(os.path.join(DST,'px'), exist_ok=True)
keep, total = set(), 0
for t in THEMES:
    for s in SLOTS:
        pin = PICKS.get(t, {}).get(s)
        if pin:
            src = os.path.join(SRC, pin)
        else:
            fs = glob.glob(os.path.join(SRC, 'bt_%s_%s_*.png' % (t, s)))
            if not fs: print('缺原图:', t, s); continue
            src = sorted(fs, key=os.path.getmtime)[-1]
        name = os.path.splitext(os.path.basename(src))[0] + '.jpg'
        Image.open(src).convert('RGB').save(os.path.join(DST, name), quality=92, subsampling=0)
        keep.add(name)
        px = 'bt_%s_%s.png' % (t, s)
        shutil.copy2(os.path.join(SRC,'px',px), os.path.join(DST,'px',px))
        total += 1

# 仓库里以前留过被否掉的版本，清掉
gone = 0
for f in glob.glob(os.path.join(DST, 'bt_*.png')) + glob.glob(os.path.join(DST, 'bt_*.jpg')):
    if os.path.basename(f) not in keep:
        os.remove(f); gone += 1

seed = os.path.join(SRC, 'seed_castle.png')
if os.path.exists(seed): shutil.copy2(seed, os.path.join(DST,'seed_castle.png'))
shutil.copy2(os.path.join(SRC,'pick.json'), os.path.join(DST,'pick.json')) if PICKS else None
print('同步 %d 组（原图+64px），清掉旧版本 %d 张' % (total, gone))
