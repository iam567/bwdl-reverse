"""把分块生成的地形拼成整图。重叠区用线性羽化做加权融合。"""
import json, sys, glob, os
from PIL import Image
d, tiledir, out = sys.argv[1], sys.argv[2], sys.argv[3]
g = json.load(open(d+'/tiles/grid.json'))
N, CELL, M = g['N'], g['CELL'], g['M']
OUT = 1024                                    # 每块生成尺寸
k = OUT / (CELL + 2*M)                        # 源 px → 输出 px
canvas_w = round((N*CELL + 2*M) * k)          # 含最外圈重叠
acc = [[[0.0,0.0,0.0] for _ in range(canvas_w)] for _ in range(canvas_w)]
wsum = [[0.0]*canvas_w for _ in range(canvas_w)]

# 羽化权重：核心区 1，重叠区从 1 线性降到 0
mk = round(M*k)
def weight(i, n):
    if i < mk: return (i+1)/mk
    if i >= n-mk: return (n-i)/mk
    return 1.0
wrow = [weight(i, OUT) for i in range(OUT)]

for t in g['tiles']:
    f = sorted(glob.glob(f"{tiledir}/tile_r{t['r']}c{t['c']}_*.png"), key=os.path.getmtime)
    if not f: print('缺块', t['r'], t['c']); continue
    im = Image.open(f[-1]).convert('RGB').resize((OUT,OUT), Image.LANCZOS)
    px = im.load()
    ox = round((t['src_x0'] + M) * k); oy = round((t['src_y0'] + M) * k)
    for y in range(OUT):
        wy = wrow[y]; Y = oy + y
        if not (0 <= Y < canvas_w): continue
        row = acc[Y]; wr = wsum[Y]
        for x in range(OUT):
            X = ox + x
            if not (0 <= X < canvas_w): continue
            w = wy * wrow[x]
            r,gg,b = px[x,y]
            row[X][0] += r*w; row[X][1] += gg*w; row[X][2] += b*w
            wr[X] += w

res = Image.new('RGB', (canvas_w, canvas_w))
rp = res.load()
for y in range(canvas_w):
    for x in range(canvas_w):
        w = wsum[y][x]
        if w > 0:
            c = acc[y][x]; rp[x,y] = (int(c[0]/w), int(c[1]/w), int(c[2]/w))
# 裁掉最外圈重叠，只留 N*CELL*k
core = round(N*CELL*k)
res = res.crop((mk, mk, mk+core, mk+core))
res.save(out, quality=94)
print('拼接完成', res.size, '| 每块', OUT, '| 重叠', mk, 'px')
