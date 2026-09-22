# -*- coding: utf-8 -*-
"""阵型战 UI 底图的后处理：裁到实际内容、缩到贴图尺寸、量出九宫格的 border。

   模型出的是 1024 见方、四周留白的整图，直接拿去当 UI 会有两个问题：
   一是贴图白白大了十几倍，二是九宫格的 border 只能靠眼估，估歪了拉伸就糊。
   所以这里把边框厚度和头像框中间那个透明洞**量出来**写进 battleui.json，
   Unity 那边直接读，不在代码里写死魔数。

   用法: build_battleui.py <原图目录> <输出目录>
"""
import sys, os, json
from PIL import Image

SRC, OUT = sys.argv[1], sys.argv[2]
os.makedirs(OUT, exist_ok=True)
TARGET = {'plate': 192, 'btn': 96, 'portrait': 192, 'mapbg': 192,
          'bar': 40, 'barfill': 24, 'cityplate': 40}
WIDE = ('btn', 'bar', 'barfill', 'cityplate')   # 横条按高度缩，别按最长边
meta = {}


def trim(im):
    b = im.split()[3].point(lambda v: 255 if v > 24 else 0).getbbox()
    return im.crop(b) if b else im


def edge_thickness(im):
    """量铜边有多厚。

       第一版是「从中线往里扫，亮转暗的那一点」——量出来是 2，
       因为铜边最外圈本身就是一道深色描边，第二个像素就已经暗下去了。
       改成先取板心的亮度当基准，再从外往里走，
       **连着若干个像素都落回板心亮度**才算出了边框。"""
    px = im.convert('RGB').load()
    a  = im.split()[3].load()
    w, h = im.size
    y = h // 2
    lum = lambda p: .299 * p[0] + .587 * p[1] + .114 * p[2]

    mid = [lum(px[x, y]) for x in range(int(w * .42), int(w * .58))]
    wood = sorted(mid)[len(mid) // 2]                  # 板心亮度取中位数

    x = 0
    while x < w // 3 and a[x, y] < 128: x += 1         # 跳过外圈残留的透明像素
    run = 0
    for i in range(x, w // 2):
        if lum(px[i, y]) < wood * 1.30:
            run += 1
            if run >= 5: return max(2, i - 4)
        else:
            run = 0
    return max(2, w // 8)


def hole(im):
    """头像框中间那个全透明的方洞：从中线往外量。"""
    a = im.split()[3].load()
    w, h = im.size
    cx, cy = w // 2, h // 2
    l = cx
    while l > 0 and a[l - 1, cy] < 16: l -= 1
    r = cx
    while r < w - 1 and a[r + 1, cy] < 16: r += 1
    t = cy
    while t > 0 and a[cx, t - 1] < 16: t -= 1
    b = cy
    while b < h - 1 and a[cx, b + 1] < 16: b += 1
    return l, t, r - l + 1, b - t + 1


def wood_settle(im, vertical=False):
    """从边往里走，走到「板心木色」稳定下来为止，返回走了多少像素。

       匾额两端是挑出去的云纹翘头，中间还夹着一道铜线和一道内阴影；
       bronze_extent 只认连续的铜色，翘头的暗部一来就断了，量出来是 6（下限）。
       edge_thickness 找的是「亮转暗」，会停在翘头内部的暗卷纹上。
       这里反过来判：连着 10 个像素都落在板心亮度的 ±35% 里，才算进了板面。"""
    px = im.convert('RGB').load(); a = im.split()[3].load()
    w, h = im.size
    lum = lambda p: .299 * p[0] + .587 * p[1] + .114 * p[2]
    if vertical:
        cx = w // 2
        mid = [lum(px[cx, y]) for y in range(int(h * .42), int(h * .58))]
        get = lambda i: (lum(px[cx, i]), a[cx, i]); n = h
    else:
        cy = h // 2
        mid = [lum(px[x, cy]) for x in range(int(w * .42), int(w * .58))]
        get = lambda i: (lum(px[i, cy]), a[i, cy]); n = w
    wood = sorted(mid)[len(mid) // 2]
    lo, hi = wood * .65, wood * 1.35
    run = 0
    for i in range(n // 2):
        v, al = get(i)
        if al > 128 and lo <= v <= hi:
            run += 1
            if run >= 10: return max(2, i - 9)
        else:
            run = 0
    return max(2, n // 8)


def bronze_extent(im):
    """两端的青铜铺首占多宽：中线从左往右扫，最后一个「铜色」(g >= r*0.85 且不透明) 像素的 x。
       横向九宫格的 border 必须盖住整个铺首，不然拉伸时兽面会被抻长。"""
    px = im.convert('RGB').load(); a = im.split()[3].load()
    w, h = im.size; y = h // 2; last = 0
    for x in range(int(w * .35)):
        r, g, b = px[x, y]
        if a[x, y] > 128 and g >= r * .85 and r < 200: last = x
    # 灰态整张都低饱和，铜色判定会把木面也算进去——铺首再宽也不会超过两成
    return max(6, min(last + 2, int(w * .2)))

for k, target in TARGET.items():
    f = os.path.join(SRC, 'bui_%s.png' % k)
    if not os.path.exists(f):
        print('缺', k); continue
    im = Image.open(f).convert('RGBA')
    if k != 'mapbg':
        im = trim(im)

    # 等比缩到目标边长（btn 是横条，按高度缩）
    if k in WIDE:
        s = target / im.height
    else:
        s = target / max(im.width, im.height)
    im = im.resize((max(1, round(im.width * s)), max(1, round(im.height * s))), Image.LANCZOS)

    if k == 'portrait':
        hx, hy, hw, hh = hole(im)
        meta[k] = {'w': im.width, 'h': im.height,
                   'hole': [hx, hy, hw, hh]}
    elif k == 'mapbg':
        meta[k] = {'w': im.width, 'h': im.height}
    elif k == 'cityplate':
        # 匾额两端是挑出去的云纹翘头，横向 border 得把翘头整个盖住（跟出阵按钮的铺首一个道理），
        # 竖向按铜边量就行。bronze_extent 是从中线扫的，翘头在中线上，量得到。
        meta[k] = {'w': im.width, 'h': im.height,
                   'border': wood_settle(im), 'border_y': wood_settle(im, True)}
    elif k == 'barfill':
        # 填充条整条都是亮的，没有「亮转暗」可量。给 2 像素让两头保住自己的边缘。
        meta[k] = {'w': im.width, 'h': im.height, 'border': 2}
    else:
        e = edge_thickness(im)
        meta[k] = {'w': im.width, 'h': im.height, 'border': e}

    im.save(os.path.join(OUT, 'ui_%s.png' % k))
    print('%-9s %dx%-4d %s' % (k, im.width, im.height, meta[k]))

# 兵种图标：一张 1024 切 2×2。四个共用一个缩放比，
# 各缩各的会让马头比将盔大一圈，摆成一列就参差不齐。
ico = os.path.join(SRC, 'bui_icons.png')
if os.path.exists(ico):
    ICO = 64
    sheet = Image.open(ico).convert('RGBA')
    if sheet.size != (1024, 1024):
        sheet = sheet.resize((1024, 1024), Image.LANCZOS)
    keys = ['ti', 'qi', 'gong', 'bu']
    cells = {}
    for i, k in enumerate(keys):
        c = sheet.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512))
        b = c.split()[3].point(lambda v: 255 if v > 24 else 0).getbbox()
        if b is None: continue
        cells[k] = (c, b)
    if cells:
        sc = min((ICO - 2) / max(b[2] - b[0] for _, b in cells.values()),
                 (ICO - 2) / max(b[3] - b[1] for _, b in cells.values()))
        for k, (c, b) in cells.items():
            fig = c.crop(b)
            w = max(1, round(fig.width * sc)); h = max(1, round(fig.height * sc))
            fig = fig.resize((w, h), Image.BOX)
            canvas = Image.new('RGBA', (ICO, ICO), (0, 0, 0, 0))
            canvas.paste(fig, ((ICO - w) // 2, (ICO - h) // 2), fig)
            canvas.save(os.path.join(OUT, 'ui_icon_%s.png' % k))
            print('icon %-5s %2dx%-2d' % (k, w, h))
        meta['icon'] = {'w': ICO, 'h': ICO}

# 阵型选择构件：一张 1024 切 2×2 → 横幅 / 朱砂印 / 卡片 / 卡片·选中。
# 横幅和两张卡片要量 border（九宫格），印记整张贴不量。
pk = os.path.join(SRC, 'bui_pick.png')
if os.path.exists(pk):
    sh = Image.open(pk).convert('RGBA')
    if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
    for i, k in enumerate(['pick_banner', 'pick_seal', 'pick_card', 'pick_card_on']):
        c = trim(sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512)))
        tgt = 96 if k == 'pick_banner' else 160
        sc = tgt / (c.height if k == 'pick_banner' else max(c.width, c.height))
        c = c.resize((max(1, round(c.width * sc)), max(1, round(c.height * sc))), Image.LANCZOS)
        if k == 'pick_seal':
            meta[k] = {'w': c.width, 'h': c.height}
        else:
            meta[k] = {'w': c.width, 'h': c.height, 'border': edge_thickness(c)}
        c.save(os.path.join(OUT, 'ui_%s.png' % k))
        print('%-13s %dx%-4d %s' % (k, c.width, c.height, meta[k]))



# 出阵按钮：2×2 → 常态 / 按下 / 不可用 / 铜纹分隔条。三态按钮横向九宫格，
# 横向 border 量到铺首外沿、竖向按铜边量。
gp = os.path.join(SRC, 'bui_go.png')
if os.path.exists(gp):
    sh = Image.open(gp).convert('RGBA')
    if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
    for i, k in enumerate(['go', 'go_on', 'go_off', 'divider']):
        c = trim(sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512)))
        tgt = 112 if k != 'divider' else 24
        sc = tgt / c.height
        c = c.resize((max(1, round(c.width * sc)), max(1, round(c.height * sc))), Image.LANCZOS)
        meta[k] = {'w': c.width, 'h': c.height} if k == 'divider' else \
                  {'w': c.width, 'h': c.height, 'border': bronze_extent(c), 'border_y': edge_thickness(c)}
        c.save(os.path.join(OUT, 'ui_%s.png' % k))
        print('%-8s %dx%-4d %s' % (k, c.width, c.height, meta[k]))

# 战棋层构件：2×2 → 光标 / 攻击目标标记 / 部队名牌 / 可达格底纹。
# 光标和标记整格贴（四角括弧，中间透明），名牌横向九宫格，可达底整格贴。
fd = os.path.join(SRC, 'bui_field.png')
if os.path.exists(fd):
    sh = Image.open(fd).convert('RGBA')
    if sh.size != (1024, 1024): sh = sh.resize((1024, 1024), Image.LANCZOS)
    for i, k in enumerate(['cursor', 'target', 'nameplate', 'reach']):
        c = trim(sh.crop((i % 2 * 512, i // 2 * 512, i % 2 * 512 + 512, i // 2 * 512 + 512)))
        if k == 'nameplate':
            sc = 40 / c.height
        else:
            sc = 96 / max(c.width, c.height)
        c = c.resize((max(1, round(c.width * sc)), max(1, round(c.height * sc))), Image.LANCZOS)
        if k == 'nameplate':
            # 竖向按亮转暗量出来是 18（40 高的图上下各 18，中段只剩 4），
            # 名牌在游戏里只有 16 高，竖向 border 就钉成 4；横向盖住左端那枚铜钉
            meta[k] = {'w': c.width, 'h': c.height, 'border': max(bronze_extent(c), 14), 'border_y': 4}
        else:
            meta[k] = {'w': c.width, 'h': c.height}
        c.save(os.path.join(OUT, 'ui_%s.png' % k))
        print('%-9s %dx%-4d %s' % (k, c.width, c.height, meta[k]))

with open(os.path.join(OUT, 'battleui.json'), 'w', encoding='utf-8') as fp:
    json.dump(meta, fp, ensure_ascii=False)

# 再写一份扁平的给 Unity 读。量出来的数就该由这里说了算，
# 不要在 C# 里另抄一份魔数——重出一次图两边就对不上了。
with open(os.path.join(OUT, 'battleui.txt'), 'w', encoding='utf-8') as fp:
    for k, v in meta.items():
        if 'border_y' in v: fp.write('%s %d %d\n' % (k, v['border'], v['border_y']))
        elif 'border' in v: fp.write('%s %d\n' % (k, v['border']))
        elif 'hole' in v: fp.write('%s %d %d %d %d %d %d\n'
                                   % (k, v['w'], v['h'], *v['hole']))
print('→', os.path.join(OUT, 'battleui.txt'))
