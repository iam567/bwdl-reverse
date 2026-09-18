# -*- coding: utf-8 -*-
"""把城内视图素材打进 Unity 工程。

   JSON 要转一道：clean_mask.py 输出的 buildings 是对象字典（网页版直接用），
   而 Unity 的 JsonUtility 不吃字典，必须转成数组并把 key 塞进每项里。
   这个坑当初洛阳踩过一次，这里统一处理掉。

   用法: pack_unity.py <cityview目录> <Unity StreamingAssets/Art 目录>
"""
import sys, os, json, glob, shutil

SRC, DST = sys.argv[1], sys.argv[2]
out = os.path.join(DST, 'cityview')
os.makedirs(out, exist_ok=True)
n = 0
for f in sorted(glob.glob(os.path.join(SRC, '*.json'))):
    key = os.path.splitext(os.path.basename(f))[0]
    if key.startswith('_'): continue
    d = json.load(open(f))
    if isinstance(d.get('buildings'), dict):
        d['buildings'] = [dict(key=k, **v) for k, v in d['buildings'].items()]
    json.dump(d, open(os.path.join(out, key + '.json'), 'w'), ensure_ascii=False)
    for ext in ('.jpg', '_mask.png'):
        s = os.path.join(SRC, key + ext)
        if os.path.exists(s): shutil.copy2(s, os.path.join(out, key + ext))
    n += 1
print('打进 %d 座城 → %s（buildings 已转成数组）' % (n, out))
