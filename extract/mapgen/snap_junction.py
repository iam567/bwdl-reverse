"""把城池吸附到路口：路口的分叉数须与邻接表的度数一致。
   在第一层拼接图（3277）的半分辨率（1638）上做，输出 1024 坐标系的 city-screen.json。"""
import json, math, sys
from PIL import Image
d = sys.argv[1]
S = 1638; K = S/1024                      # 1024 坐标 → 工作分辨率
im = Image.open(d+'/stitched_L1.jpg').convert('RGB').resize((S,S), Image.LANCZOS); px = im.load()
road = [[False]*S for _ in range(S)]
for y in range(S):
    for x in range(S):
        r,g,b = px[x,y]
        if r>170 and g>135 and b<150 and (r-b)>50 and abs(r-g)<55: road[y][x] = True
A = json.load(open(d+'/adjacency.json'))
old = json.load(open(d+'/city-screen.json'))
RAYS = 32; RLEN = 46; BAND = 6            # 射线数 / 长度 / 判定带宽（工作像素）
def sectors(cx, cy):
    """返回被路命中的射线扇区数（连续命中合并为一簇）"""
    hit = []
    for i in range(RAYS):
        a = 2*math.pi*i/RAYS; h = False
        for t in range(14, RLEN, 2):       # 跳过中心附近（城池自身占据）
            X = int(cx + t*math.cos(a)); Y = int(cy + t*math.sin(a))
            if 0<=X<S and 0<=Y<S and road[Y][X]: h = True; break
        hit.append(h)
    # 合并连续簇（环形）
    if all(hit): return 1
    clusters = 0
    for i in range(RAYS):
        if hit[i] and not hit[i-1]: clusters += 1
    return clusters
def density(cx, cy, R=10):
    c = t = 0
    for dx in range(-R,R+1,2):
        for dy in range(-R,R+1,2):
            X,Y = cx+dx, cy+dy
            if 0<=X<S and 0<=Y<S: t += 1; c += road[Y][X]
    return c/max(t,1)
out = []; report = []
for c in old:
    deg = len(A[str(c['id'])]['adj'])
    ox, oy = c['x']*K, c['y']*K
    best = None
    R = 42
    for dx in range(-R, R+1, 3):
        for dy in range(-R, R+1, 3):
            X, Y = int(ox+dx), int(oy+dy)
            if not (0<=X<S and 0<=Y<S) or not road[Y][X]: continue
            sc = sectors(X, Y)
            dist = math.hypot(dx, dy)
            score = -abs(sc-deg)*12 + density(X,Y)*20 - dist*0.22
            if best is None or score > best[0]: best = (score, X, Y, sc)
    if best is None:
        out.append(c); report.append((c['name'], deg, '-', 0)); continue
    _, X, Y, sc = best
    moved = math.hypot(X-ox, Y-oy)/K
    out.append({'id':c['id'], 'name':c['name'], 'x':round(X/K,1), 'y':round(Y/K,1), 'moved':round(moved,1), 'deg':deg, 'found':sc})
    report.append((c['name'], deg, sc, moved))
json.dump(out, open(d+'/city-screen.json','w'), ensure_ascii=False, indent=1)
ok = sum(1 for r in report if r[1]==r[2])
print(f'分叉数与邻接度一致: {ok}/36')
print('不一致的：', ', '.join(f"{n}(度{dg}/找到{f})" for n,dg,f,m in report if dg!=f) or '无')
big = [r for r in report if r[3] > 12]
print('移动>12px(1024尺度)：', ', '.join(f"{n}({m:.0f})" for n,dg,f,m in big) or '无')
