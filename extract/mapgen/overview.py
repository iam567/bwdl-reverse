"""大地图总览的底图：只出纯地形，不画任何城池标记。

   标记（势力色圆点 + 城名）由 preview.html 在运行时画到覆盖层上。
   原先是烤进图里的，但那样城池易主后小地图还是旧颜色——
   游戏里势力范围一直在变，标记必须是动态的。

   底图用 L1（3277）降采样而不是 L2（6528）：降到 1024 时 L1 的土路更连贯，
   L2 会把路压得又细又碎。

   用法: overview.py <work目录> <输出jpg> [底图(默认 stitched_L1.jpg)]
"""
import sys, os
from PIL import Image
Image.MAX_IMAGE_PIXELS = None

WORK, OUT = sys.argv[1], sys.argv[2]
TERR = sys.argv[3] if len(sys.argv) > 3 else os.path.join(WORK, 'stitched_L1.jpg')
SIDE = 1024

im = Image.open(TERR).convert('RGB').resize((SIDE, SIDE), Image.LANCZOS)
im.save(OUT, quality=94)
print('输出', OUT, im.size, '（纯地形，标记由前端运行时绘制）')
