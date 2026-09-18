"""抽出した折れ線を地図に重ねて検証する。"""
import json, sys
from PIL import Image, ImageDraw
ROOT = sys.argv[1] if len(sys.argv) > 1 else '.'
BASE = sys.argv[2] if len(sys.argv) > 2 else f'{ROOT}/work/stitched_L1.jpg'
OUT  = sys.argv[3] if len(sys.argv) > 3 else f'{ROOT}/samples/road_paths.jpg'
d = json.load(open(f'{ROOT}/extract/data/road-paths.json'))
cities = json.load(open(f'{ROOT}/work/city-screen.json'))
im = Image.open(BASE).convert('RGB'); W, H = im.size
k = W / 1024
g = ImageDraw.Draw(im, 'RGBA')
for e in d['edges']:
    pts = [(x * k, y * k) for x, y in e['points']]
    col = (60, 180, 255, 230) if e['sea'] else (255, 50, 50, 210)
    g.line(pts, fill=col, width=max(2, int(k * 1.2)), joint='curve')
    for x, y in pts[1:-1]:                              # 折れ点
        r = max(2, k * 1.4)
        g.ellipse([x-r, y-r, x+r, y+r], fill=(255, 230, 60, 230))
for c in cities:
    x, y = c['x'] * k, c['y'] * k; r = max(4, k * 3)
    g.ellipse([x-r, y-r, x+r, y+r], fill=(20, 20, 20, 200), outline=(255, 255, 255, 240), width=max(1, int(k)))
im.save(OUT, quality=92)
print('出力', im.size, '→', OUT)
