# -*- coding: utf-8 -*-
"""把 36 座城的全景与蒙版批量洗成可用的城内视图。

   逐城核对建筑数：本城该有几个（4 个必有 + x[] 位掩码里的集市设施），
   蒙版里实际洗出几个。对不上的列出来，好单独重跑。

   用法: batch_cityview.py <cityscene目录> <输出目录>
"""
import sys, os, re, glob, json, csv, subprocess

CN = {'taishou':'太守府','gongcao':'功曹府','xiaochang':'校场','yiguan':'驿馆',
      'changping':'常平仓','wuku':'武库','junxue':'郡学','yaolu':'药庐'}
meta_name = CN.get

SRC, OUT = sys.argv[1], sys.argv[2]
HERE = os.path.dirname(os.path.abspath(__file__))
DATA = os.path.normpath(os.path.join(HERE, '..', 'data'))
os.makedirs(OUT, exist_ok=True)

# 城池 key ↔ ID ↔ 中文名：从平台的定义文件里读，别在两处各写一份
js = open(os.path.join(HERE, '..', '..', 'image-studio', 'cities-detail.js'), encoding='utf-8').read()
CITY = {}
for m in re.finditer(r"^\s*(\d+):\s*\['([a-z]+)','([^']+)'", js, re.M):
    CITY[int(m.group(1))] = (m.group(2), m.group(3))

rows = {int(r[0]): r for r in csv.reader(open(os.path.join(DATA, 'cities.csv')))}
FACIL_COL = {'wuku':16, 'junxue':17, 'yaolu':18, 'changping':19}
UNIVERSAL = ['taishou','gongcao','xiaochang','yiguan']

def latest(pat):
    fs = glob.glob(os.path.join(SRC, pat))
    return sorted(fs, key=os.path.getmtime)[-1] if fs else None

bad = []
print('%-5s %-4s %-4s %s' % ('城名', '应有', '洗出', '差异'))
for cid in sorted(CITY):
    key, name = CITY[cid]
    scene = latest('scene_%s_*.png' % key)
    mask  = latest('mask_%s_*.png' % key)
    if not scene or not mask:
        bad.append((name, '缺素材')); print('%-5s %-4s %-4s %s' % (name,'-','-','缺素材')); continue
    want = set(UNIVERSAL) | {k for k, c in FACIL_COL.items() if rows[cid][c].strip() == '1'}
    pre = os.path.join(OUT, key)
    subprocess.run([sys.executable, os.path.join(HERE, 'clean_mask.py'), mask, pre],
                   check=True, capture_output=True)
    meta = json.load(open(pre + '.json'))
    got = set(meta['buildings'])
    # 全景转 jpg，和蒙版、json 放一起
    from PIL import Image
    Image.open(scene).convert('RGB').save(pre + '.jpg', quality=92)
    extra, miss = got - want, want - got
    note = ''
    if miss:  note += '漏:' + '、'.join(meta_name(m) for m in miss)
    if extra: note += ' 多:' + '、'.join(meta_name(m) for m in extra)
    if note: bad.append((name, note.strip()))
    print('%-5s %-4d %-4d %s' % (name, len(want), len(got), note.strip() or 'OK'))

print()
if bad:
    print('需要处理的 %d 座：' % len(bad))
    for n, r in bad: print('  %s  %s' % (n, r))
else:
    print('36 座全部对上')
