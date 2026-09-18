"""52 本の辺について、土路に沿った実際の折れ線を抽出する。

都市は路口に座っているので、始点・終点間を道路画素上で探索すれば実際の道筋が得られる。

二つの工夫が要る:
1. 木の影が道をまたぐ箇所で厳しいマスクは途切れる。緩いマスクを併用し、
   厳しい方をコスト1、緩い方のみをコスト4 とした重み付き Dijkstra で、
   道の中心を通しつつ切れ目だけ渡らせる。
2. 他都市を経由して遠回りしないよう、対象 2 都市以外の周囲を通行止めにする。

出力: extract/data/road-paths.json（座標は 1024 系）
"""
import json, math, sys
from collections import deque
from PIL import Image

ROOT = sys.argv[1] if len(sys.argv) > 1 else '.'
S = 1638                      # 作業解像度
BLOCK_R = 18                  # 他都市の通行止め半径（作業px）
EPS = 2.5                     # 折れ線簡略化の許容誤差（作業px）

im = Image.open(f'{ROOT}/work/stitched_L1.jpg').convert('RGB').resize((S, S), Image.LANCZOS)
px = im.load()
COST_CORE, COST_BRIDGE = 1, 4
cost = bytearray(S * S)          # 0 = 通行不可
road = bytearray(S * S)          # 厳しいマスク（端点探索用）
for y in range(S):
    base = y * S
    for x in range(S):
        r, g, b = px[x, y]
        if r > 170 and g > 135 and b < 150 and (r - b) > 50 and abs(r - g) < 55:
            cost[base + x] = COST_CORE; road[base + x] = 1
        elif r > 155 and g > 120 and b < 165 and (r - b) > 35 and abs(r - g) < 70:
            cost[base + x] = COST_BRIDGE

cities = json.load(open(f'{ROOT}/work/city-screen.json'))
adj = json.load(open(f'{ROOT}/work/adjacency.json'))
K = S / 1024
P = {c['id']: (c['x'] * K, c['y'] * K) for c in cities}
NAME = {c['id']: c['name'] for c in cities}

def nearest_road(cx, cy, maxr=40):
    """通行可能画素のうち最寄りを返す。厳しいマスクだけだと孤立片に落ちる。"""
    cx, cy = int(cx), int(cy)
    if 0 <= cx < S and 0 <= cy < S and cost[cy * S + cx]: return cx, cy
    for R in range(1, maxr):
        for dx in range(-R, R + 1):
            for dy in (-R, R):
                for X, Y in ((cx + dx, cy + dy), (cx + dy, cy + dx)):
                    if 0 <= X < S and 0 <= Y < S and cost[Y * S + X]: return X, Y
    return None

NB = [(-1,0),(1,0),(0,-1),(0,1),(-1,-1),(-1,1),(1,-1),(1,1)]

def bfs(a, b, blocked):
    """a から b へ重み付き Dijkstra。blocked は通行止め画素の set。"""
    import heapq
    sa, sb = nearest_road(*P[a]), nearest_road(*P[b])
    if not sa or not sb: return None
    start, goal = sa[1] * S + sa[0], sb[1] * S + sb[0]
    dist = {start: 0}
    prev = {start: -1}
    pq = [(0, start)]
    while pq:
        d, cur = heapq.heappop(pq)
        if cur == goal: break
        if d > dist.get(cur, 1 << 30): continue
        cy, cx = divmod(cur, S)
        for dx, dy in NB:
            nx, ny = cx + dx, cy + dy
            if not (0 <= nx < S and 0 <= ny < S): continue
            ni = ny * S + nx
            c = cost[ni]
            if not c or ni in blocked: continue
            step = c * (14 if dx and dy else 10)     # 斜めは √2 倍
            nd = d + step
            if nd < dist.get(ni, 1 << 30):
                dist[ni] = nd; prev[ni] = cur
                heapq.heappush(pq, (nd, ni))
    if goal not in prev: return None
    path, cur = [], goal
    while cur != -1:
        cy, cx = divmod(cur, S)
        path.append((cx, cy))
        cur = prev[cur]
    return path[::-1]

def simplify(pts, eps):
    """Douglas-Peucker"""
    if len(pts) < 3: return pts
    a, b = pts[0], pts[-1]
    dx, dy = b[0] - a[0], b[1] - a[1]
    den = math.hypot(dx, dy) or 1
    imax, dmax = 0, 0
    for i in range(1, len(pts) - 1):
        d = abs(dy * (pts[i][0] - a[0]) - dx * (pts[i][1] - a[1])) / den
        if d > dmax: imax, dmax = i, d
    if dmax <= eps: return [a, b]
    return simplify(pts[:imax + 1], eps)[:-1] + simplify(pts[imax:], eps)

SEA_ROUTES = {(33, 36)}          # 山越-荑州: 荑州は島（夷洲）。陸路なし
edges, failed = [], []
for sid, info in adj.items():
    a = int(sid)
    for b in info['adj']:
        if a >= b: continue
        blocked = set()
        for oid, (ox, oy) in P.items():
            if oid in (a, b): continue
            oxi, oyi = int(ox), int(oy)
            for dx in range(-BLOCK_R, BLOCK_R + 1):
                for dy in range(-BLOCK_R, BLOCK_R + 1):
                    if dx * dx + dy * dy <= BLOCK_R * BLOCK_R:
                        X, Y = oxi + dx, oyi + dy
                        if 0 <= X < S and 0 <= Y < S: blocked.add(Y * S + X)
        sea = (a, b) in SEA_ROUTES
        path = None if sea else bfs(a, b, blocked)
        if not path:
            if not sea: failed.append((NAME[a], NAME[b]))
            path = [(int(P[a][0]), int(P[a][1])), (int(P[b][0]), int(P[b][1]))]
            straight = True
        else:
            straight = False
        sp = simplify(path, EPS)
        # 端点は都市中心に寄せる
        sp[0] = (P[a][0], P[a][1]); sp[-1] = (P[b][0], P[b][1])
        length = sum(math.hypot(sp[i+1][0]-sp[i][0], sp[i+1][1]-sp[i][1]) for i in range(len(sp)-1))
        edges.append({
            'a': a, 'b': b, 'aName': NAME[a], 'bName': NAME[b],
            'points': [[round(x / K, 1), round(y / K, 1)] for x, y in sp],
            'length': round(length / K, 1),
            'straight': straight,
            'sea': sea,
        })

json.dump({'coordSpace': 1024, 'edges': edges},
          open(f'{ROOT}/extract/data/road-paths.json', 'w'), ensure_ascii=False, indent=1)

pts = [len(e['points']) for e in edges]
det = [e for e in edges if not e['straight']]
sea = [e for e in edges if e['sea']]
print(f'辺 {len(edges)} 本 | 道路追跡成功 {len(det)} | 海路 {len(sea)} | 失敗 {len(failed)}')
print(f'折れ線の点数: 平均 {sum(pts)/len(pts):.1f} / 最多 {max(pts)}')
if failed: print('失敗:', ', '.join(f'{a}-{b}' for a, b in failed))
# 直線距離との比（道が曲がっているほど大きい）
rat = []
for e in edges:
    ax, ay = P[e['a']]; bx, by = P[e['b']]
    d = math.hypot(ax-bx, ay-by) / K
    if d > 1: rat.append((e['length']/d, e['aName'], e['bName']))
rat.sort(reverse=True)
print('迂回率が高い辺:', ', '.join(f'{a}-{b}({r:.2f})' for r, a, b in rat[:5]))
