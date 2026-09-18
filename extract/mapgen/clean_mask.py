# -*- coding: utf-8 -*-
"""把模型生成的语义蒙版洗成干净的点击蒙版。

   模型能把建筑轮廓填对，但有三个毛病，全部程序修掉，不用再跟提示词较劲：
   1. 边缘抗锯齿 → 最近色量化，每个像素强制归到色表里某一色
   2. 院落内部留黑洞（院子地面没填）→ 从画面外围反向洪泛，够不到的就是洞，填上
   3. 零星杂点 → 先闭运算接上断口，补洞后再开运算去渣

   用法: clean_mask.py <蒙版png> <输出前缀> [色表json]
"""
import sys, os, json
from PIL import Image, ImageChops, ImageDraw, ImageFilter

SRC, OUT = sys.argv[1], sys.argv[2]
PAL = {'taishou':('太守府','内政',(255,0,0)), 'gongcao':('功曹府','人事',(0,255,0)),
       'xiaochang':('校场','军事',(0,0,255)), 'yiguan':('驿馆','外交',(255,255,0)),
       'changping':('常平仓','金粮',(255,0,255)), 'wuku':('武库','武器',(0,255,255)),
       'junxue':('郡学','学院',(255,128,0)), 'yaolu':('药庐','医馆',(128,0,255))}
KEYS = list(PAL)
COLORS = [(0,0,0)] + [PAL[k][2] for k in KEYS]

img = Image.open(SRC).convert('RGB')
W, H = img.size

# 1) 最近色量化：抗锯齿的过渡像素被强行归到最近的色表色
palimg = Image.new('P', (1,1))
flat = [v for c in COLORS for v in c]
palimg.putpalette(flat + [0]*(768-len(flat)))
q = img.quantize(palette=palimg, dither=Image.Dither.NONE)

def components(m, keep_ratio=0.15, keep_min=1200):
    """只留成块的连通域。模型偶尔会把远处一栋民房也涂成同色，
       不滤掉的话包围盒会横跨大半张图，热区就废了。
       用 tobytes().find() 在 C 层找下一个前景点，再用 floodfill 打标，够快。"""
    w, h = m.size
    lab = m.copy()
    marks, v = [], 1
    while v < 254:
        i = lab.tobytes().find(b'\xff')
        if i < 0: break
        ImageDraw.floodfill(lab, (i % w, i // w), v)
        marks.append(v); v += 1
    if not marks: return m, 0
    hist = lab.histogram()
    areas = {mk: hist[mk] for mk in marks}
    top = max(areas.values())
    keep = {mk for mk, a in areas.items() if a >= max(keep_min, top * keep_ratio)}
    return lab.point([255 if j in keep else 0 for j in range(256)], mode='L'), (len(marks), len(keep))

def fill_holes(m):
    """从画面外围反向洪泛：够不到的黑区就是院内空地，填成前景。"""
    w, h = m.size
    pad = Image.new('L', (w+2, h+2), 0); pad.paste(m, (1,1))
    inv = ImageChops.invert(pad)
    ImageDraw.floodfill(inv, (0,0), 0)          # 外部背景抹掉，剩下的 255 就是洞
    return ImageChops.lighter(pad, inv).crop((1,1,w+1,h+1))

out = Image.new('RGB', (W,H), (0,0,0))
meta = {}
for i, k in enumerate(KEYS, start=1):
    band = q.point([255 if j == i else 0 for j in range(256)], mode='L')
    if band.getbbox() is None: continue
    band = band.filter(ImageFilter.MinFilter(5)).filter(ImageFilter.MaxFilter(5))  # 开：先去杂点
    band = band.filter(ImageFilter.MaxFilter(9)).filter(ImageFilter.MinFilter(9))  # 闭：再接断口
    band = band.point(lambda v: 255 if v > 127 else 0)
    band, nc = components(band)                                                    # 只留成块的
    band = fill_holes(band)
    band = band.point(lambda v: 255 if v > 127 else 0)
    bb = band.getbbox()
    if bb is None: continue
    out.paste(Image.new('RGB', (W,H), PAL[k][2]), (0,0), band)
    px = sum(band.histogram()[128:])
    meta[k] = {'name': PAL[k][0], 'func': PAL[k][1], 'mask': list(PAL[k][2]),
               'box': list(bb), 'pixels': px, 'parts': nc}

os.makedirs(os.path.dirname(OUT) or '.', exist_ok=True)
out.save(OUT + '_mask.png')
json.dump({'size':[W,H], 'buildings':meta}, open(OUT + '.json','w'), ensure_ascii=False, indent=1)
tot = W*H
print('洗出 %d 个建筑，覆盖 %.1f%% 画面:' % (len(meta), sum(m['pixels'] for m in meta.values())*100/tot))
for k, m in sorted(meta.items(), key=lambda x:-x[1]['pixels']):
    print('  %-5s %-4s %5.2f%%  连通域 %s  box=%s' % (m['name'], m['func'], m['pixels']*100/tot, m['parts'], m['box']))
