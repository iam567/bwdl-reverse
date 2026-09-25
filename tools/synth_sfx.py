# 程序合成的中国风 / 计策音效（CC0，自己写的）。输出 44.1k 单声道 wav，再由 ffmpeg 转 ogg。
import math, random, struct, wave, sys
SR = 44100
out = sys.argv[1]
def save(name, s):
    peak = max(1e-6, max(abs(x) for x in s)); g = 0.89 / peak
    w = wave.open(f'{out}/{name}.wav', 'wb'); w.setnchannels(1); w.setsampwidth(2); w.setframerate(SR)
    w.writeframes(b''.join(struct.pack('<h', int(max(-1, min(1, x * g)) * 32767)) for x in s)); w.close()
def n(sec): return int(SR * sec)
def partials(sec, parts, att=0.002):
    # parts: [(频率, 振幅, 衰减时间常数)]
    s = []
    for i in range(n(sec)):
        t = i / SR; a = min(1, t / att)
        s.append(a * sum(amp * math.exp(-t / tau) * math.sin(2 * math.pi * f * t) for f, amp, tau in parts))
    return s
def noise(sec, color='white'):
    s = []; b = 0; p = [0] * 3
    for i in range(n(sec)):
        w = random.uniform(-1, 1)
        if color == 'brown': b = (b + 0.02 * w) / 1.02; s.append(b * 3.5)
        elif color == 'pink': p[0] = .99 * p[0] + .1 * w; p[1] = .96 * p[1] + .3 * w; p[2] = .57 * p[2] + .5 * w; s.append((p[0] + p[1] + p[2] + .2 * w) * .3)
        else: s.append(w)
    return s
def lowpass(s, fc):
    a = 1 - math.exp(-2 * math.pi * fc / SR); y = 0; o = []
    for x in s: y += a * (x - y); o.append(y)
    return o
def lp_sweep(s, f0, f1):
    y = 0; o = []; L = len(s)
    for i, x in enumerate(s):
        fc = f0 + (f1 - f0) * i / L; a = 1 - math.exp(-2 * math.pi * fc / SR); y += a * (x - y); o.append(y)
    return o
def hp(s, fc):
    lo = lowpass(s, fc); return [a - b for a, b in zip(s, lo)]
def env(s, att, rel, hold=None):
    L = len(s); o = []
    for i, x in enumerate(s):
        t = i / SR; T = L / SR
        a = min(1, t / att) if att > 0 else 1
        r = min(1, (T - t) / rel) if rel > 0 else 1
        o.append(x * a * max(0, r))
    return o
def mix(*tracks):
    L = max(len(t) for t, _ in tracks); o = [0.0] * L
    for t, off in tracks:
        for i, x in enumerate(t):
            j = i + n(off)
            if j < L: o[j] += x
            else: o.append(x); L += 1
    return o
def pad(s, sec): return s + [0.0] * n(sec)

# 木鱼：两个非整数倍的泛音，极快衰减（普通点击）
save('ui_click', partials(0.12, [(880, 1, .018), (1370, .5, .012), (2410, .15, .006)]))
# 低木鱼（放弃 / 返回）
save('ui_back', partials(0.14, [(560, 1, .022), (870, .45, .014)]))
# 小铃（确认 / 成交）
save('ui_confirm', partials(0.7, [(1760, 1, .22), (2640, .5, .16), (4180, .25, .09), (1766, .6, .25)]))
# 解锁武将卡：两声铃，第二声高五度
b1 = partials(0.9, [(1318, 1, .3), (1977, .5, .2), (3130, .2, .1)]); b2 = partials(1.2, [(1976, 1, .35), (2964, .5, .22), (4700, .2, .1)])
save('chime', mix((b1, 0), (b2, 0.14)))
# 锣：一堆不成倍的泛音，带一点音高下滑的感觉（用两组很近的频率做拍频）
def gong(sec, base, bright=1.0):
    parts = [(base, 1, sec * .45), (base * 1.003, .8, sec * .4), (base * 1.47, .6 * bright, sec * .3), (base * 2.09, .45 * bright, sec * .22),
             (base * 2.76, .3 * bright, sec * .15), (base * 3.9, .18 * bright, sec * .1), (base * 5.3, .1 * bright, sec * .06)]
    s = partials(sec, parts, att=0.004)
    hit = lowpass(env(noise(0.05), 0.001, 0.04), 3000)
    return mix((s, 0), ([x * .25 for x in hit], 0))
save('gong_small', gong(1.4, 420, .8))
save('gong_win', mix((gong(3.2, 190, 1.0), 0), (gong(2.2, 285, .7), 0.02)))
save('gong_lose', gong(3.0, 120, .5))
# 战鼓：音高从 95 滑到 55，指数衰减 + 鼓皮噪声；三连击
def drum(vol=1.0):
    s = []; ph = 0
    for i in range(n(0.7)):
        t = i / SR; f = 55 + 40 * math.exp(-t / .05); ph += 2 * math.pi * f / SR
        s.append(vol * math.sin(ph) * math.exp(-t / .18))
    skin = lowpass(env(noise(0.06), 0.001, 0.05), 1800)
    return mix((s, 0), ([x * .35 * vol for x in skin], 0))
save('war_drum', mix((drum(.8), 0), (drum(.85), 0.32), (drum(1.0), 0.64)))
# 箭：高通噪声的破空声（音高往下扫）+ 末尾一下闷响
whoosh = env(hp(lp_sweep(noise(0.35), 6000, 1500), 700), 0.08, 0.2)
thud = partials(0.15, [(140, 1, .04), (230, .4, .02)])
save('arrow', mix((whoosh, 0), ([x * .7 for x in thud], 0.3)))
# 火：棕噪声低频轰 + 噼啪（稀疏的短脉冲）+ 起势
roar = env(lowpass(noise(1.8, 'brown'), 900), 0.35, 0.9)
crk = [0.0] * n(1.8)
for _ in range(90):
    p = random.randint(0, len(crk) - 400); a = random.uniform(.3, 1)
    for k in range(random.randint(40, 180)): crk[p + k] += a * random.uniform(-1, 1) * math.exp(-k / 40)
crk = hp(crk, 1200)
save('plot_fire', mix((roar, 0), ([x * .35 for x in crk], 0)))
# 水：粉噪声低通来回扫（浪）+ 几个上滑的气泡
wave1 = env(lp_sweep(noise(1.6, 'pink'), 400, 2200), 0.3, 0.8)
wave2 = env(lp_sweep(noise(1.2, 'pink'), 2000, 500), 0.2, 0.7)
bub = []
for _ in range(8):
    f0 = random.uniform(300, 700); L = n(random.uniform(.05, .09)); b = []
    for i in range(L): t = i / SR; b.append(math.sin(2 * math.pi * (f0 + 6000 * t) * t) * math.exp(-t / .03))
    bub.append((b, random.uniform(0.2, 1.3)))
save('plot_water', mix((wave1, 0), (wave2, 0.5), *[([x * .25 for x in b], o) for b, o in bub]))
# 落石：一串低沉的撞击（随机音高、越来越密）+ 碎石噪声
rocks = []
t0 = 0
for k in range(9):
    f = random.uniform(60, 110); r = partials(0.4, [(f, 1, .09), (f * 2.3, .5, .05), (f * 3.7, .25, .03)])
    grit = lowpass(env(noise(0.12), 0.001, 0.1), 2500)
    rocks.append((mix((r, 0), ([x * .4 for x in grit], 0)), t0)); t0 += random.uniform(0.06, 0.16)
save('plot_rock', mix(*rocks))
# 通用计策：上扫的空灵音 + 一声铃
sw = []
ph = 0
for i in range(n(0.9)):
    t = i / SR; f = 300 + 900 * (t / 0.9) ** 1.5; ph += 2 * math.pi * f / SR
    sw.append(math.sin(ph) * .5 * math.sin(math.pi * t / 0.9) + .25 * math.sin(ph * 2.01) * math.sin(math.pi * t / 0.9))
save('plot_magic', mix((sw, 0), (partials(0.8, [(1568, .6, .25), (2350, .3, .15)]), 0.55)))
# 计策失败：闷一声往下
fl = []; ph = 0
for i in range(n(0.45)):
    t = i / SR; f = 180 - 100 * t; ph += 2 * math.pi * f / SR; fl.append(math.sin(ph) * math.exp(-t / .15))
save('plot_fail', fl)
print('done')
# 追加的战斗细节音效（CC0，自己合成）：爆炎、空挥、火矢、连弩
import math, random, sys
sys.argv=[sys.argv[0], sys.argv[1]]
exec(open('/root/synth_lib.py').read())
out=sys.argv[1]
# 爆炎：低频冲击（音高急降）+ 宽频爆裂噪声 + 翻滚的低频尾巴 + 碎屑噼啪
thump=[]; ph=0
for i in range(n(1.2)):
    t=i/SR; f=35+90*math.exp(-t/.06); ph+=2*math.pi*f/SR; thump.append(math.sin(ph)*math.exp(-t/.35))
burst=env(lowpass(noise(0.5),4000),0.002,0.45)
rumble=env(lowpass(noise(2.2,'brown'),300),0.05,1.6)
deb=[0.0]*n(1.6)
for _ in range(60):
    p=random.randint(n(0.1),len(deb)-300); a=random.uniform(.2,.8)*math.exp(-p/SR/0.6)
    for k in range(random.randint(30,120)): deb[p+k]+=a*random.uniform(-1,1)*math.exp(-k/30)
deb=hp(deb,1500)
save('boom', mix((thump,0),([x*.8 for x in burst],0),([x*1.2 for x in rumble],0.02),([x*.3 for x in deb],0.05)))
# 空挥：短促的高通噪声，音高往下扫
save('swing', env(hp(lp_sweep(noise(0.28),7000,1800),900),0.03,0.2))
# 火矢：箭的破空 + 一小段火焰
whoosh=env(hp(lp_sweep(noise(0.35),6000,1500),700),0.08,0.2)
roar=env(lowpass(noise(0.9,'brown'),900),0.1,0.6)
crk=[0.0]*n(0.9)
for _ in range(40):
    p=random.randint(0,len(crk)-300); a=random.uniform(.3,1)
    for k in range(random.randint(30,120)): crk[p+k]+=a*random.uniform(-1,1)*math.exp(-k/35)
crk=hp(crk,1200)
save('fire_arrow', mix((whoosh,0),(roar,0.22),([x*.3 for x in crk],0.22)))
# 连弩：三支箭连发，最后一下扎中
def arrow1(): return env(hp(lp_sweep(noise(0.22),6500,1800),800),0.03,0.15)
tw=partials(0.12,[(310,1,.03),(620,.5,.02)])
save('crossbow', mix(([x*.6 for x in tw],0),(arrow1(),0.02),([x*.6 for x in tw],0.13),(arrow1(),0.15),([x*.6 for x in tw],0.26),(arrow1(),0.28),(partials(0.15,[(140,1,.04),(230,.4,.02)]),0.46)))
print('done')
