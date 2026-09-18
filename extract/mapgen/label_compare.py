import sys, json, glob, os, subprocess
from PIL import Image, ImageDraw, ImageFont
d, out = sys.argv[1], sys.argv[2]
scr = {c['name']: c for c in json.load(open(d+'/city-screen.json'))}
k = 3.2; c = scr['陈留']; cx, cy = int(c['x']*k), int(c['y']*k)
panels = []
for key, title in (('wood','木牌'),('bronze','铜牌'),('silk','绢帛')):
    f = sorted(glob.glob(f'{d}/ui/label_{key}_*.png'), key=os.path.getmtime)
    if not f: continue
    tmp = f'{d}/L1_label_{key}.jpg'
    subprocess.run(['python3', d+'/compose_cities.py', d+'/stitched_L1.jpg', '3.2', '0.8', tmp, f[-1]], check=True, capture_output=True)
    im = Image.open(tmp).crop((cx-500, cy-380, cx+500, cy+380))
    g = ImageDraw.Draw(im, 'RGBA'); fnt = ImageFont.truetype('/System/Library/Fonts/STHeiti Medium.ttc', 34)
    g.rectangle([0,0,150,54], fill=(0,0,0,170)); g.text((14,8), title, font=fnt, fill=(255,240,200))
    panels.append(im)
W = sum(p.width for p in panels)+8*(len(panels)+1); H = panels[0].height+16
cv = Image.new('RGB', (W,H), (18,17,15)); x = 8
for p in panels: cv.paste(p,(x,8)); x += p.width+8
cv.save(out, quality=92); print(cv.size, len(panels), '种')
