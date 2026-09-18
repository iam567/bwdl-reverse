"""通用拼接：grid.json + 块目录 + 文件名前缀 → 羽化拼接整图。
   用法: stitch.py <grid.json> <tiledir> <prefix> <out.jpg>
   块文件名形如 <prefix>r{r}c{c}_*.png"""
import json, sys, glob, os
from PIL import Image
grid_path, tiledir, prefix, out = sys.argv[1:5]
g = json.load(open(grid_path)); N, CELL, M = g['N'], g['CELL'], g['M']
OUT = 1024; k = OUT/(CELL+2*M); mk = round(M*k)
canvas = round((N*CELL + 2*M)*k)
acc = [[[0.0,0.0,0.0] for _ in range(canvas)] for _ in range(canvas)]
wsum = [[0.0]*canvas for _ in range(canvas)]
def weight(i,n):
    if i < mk: return (i+1)/mk
    if i >= n-mk: return (n-i)/mk
    return 1.0
wrow = [weight(i,OUT) for i in range(OUT)]
missing = 0
for t in g['tiles']:
    f = sorted(glob.glob(f"{tiledir}/{prefix}r{t['r']}c{t['c']}_*.png"), key=os.path.getmtime)
    if not f: missing += 1; continue
    px = Image.open(f[-1]).convert('RGB').resize((OUT,OUT), Image.LANCZOS).load()
    ox = round((t['src_x0']+M)*k); oy = round((t['src_y0']+M)*k)
    for y in range(OUT):
        Y = oy+y
        if not (0 <= Y < canvas): continue
        wy = wrow[y]; row = acc[Y]; wr = wsum[Y]
        for x in range(OUT):
            X = ox+x
            if not (0 <= X < canvas): continue
            w = wy*wrow[x]; r,gg,b = px[x,y]
            row[X][0] += r*w; row[X][1] += gg*w; row[X][2] += b*w; wr[X] += w
res = Image.new('RGB', (canvas,canvas)); rp = res.load()
for y in range(canvas):
    for x in range(canvas):
        w = wsum[y][x]
        if w > 0: c = acc[y][x]; rp[x,y] = (int(c[0]/w), int(c[1]/w), int(c[2]/w))
core = round(N*CELL*k)
res = res.crop((mk,mk,mk+core,mk+core)); res.save(out, quality=94)
print(f'拼接 {res.size} | 缺块 {missing} | 重叠 {mk}px')
