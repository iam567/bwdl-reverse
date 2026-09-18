"""通用切块：源图 → N×N 带重叠的参考块（放大到 1024）。"""
import sys, json
from PIL import Image
src_path, outdir, N, CELL, M = sys.argv[1], sys.argv[2], int(sys.argv[3]), int(sys.argv[4]), int(sys.argv[5])
src = Image.open(src_path).convert('RGB')
side = N*CELL
src = src.crop((0,0,side,side))                       # 裁到整倍数
pad = Image.new('RGB', (side+2*M, side+2*M))
pad.paste(src.resize((side+2*M, side+2*M), Image.LANCZOS), (0,0))   # 边缘用放大底填充
pad.paste(src, (M,M))
meta=[]
for r in range(N):
    for c in range(N):
        x0,y0 = c*CELL, r*CELL
        crop = pad.crop((x0,y0,x0+CELL+2*M,y0+CELL+2*M)).resize((1024,1024), Image.LANCZOS)
        name = f'{outdir}/ref_r{r}c{c}.png'; crop.save(name)
        meta.append({'r':r,'c':c,'src_x0':x0-M,'src_y0':y0-M,'src_size':CELL+2*M,'ref':name})
json.dump({'N':N,'CELL':CELL,'M':M,'tiles':meta}, open(f'{outdir}/grid.json','w'), indent=1)
print(f'{N}x{N}={N*N} 块 | 源 {side}px | 每块 {CELL}+2*{M}={CELL+2*M} → 1024 | 倍率 {1024/(CELL+2*M):.2f}')
