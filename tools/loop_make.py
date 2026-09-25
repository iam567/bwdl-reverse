import wave, numpy as np
w=wave.open('/root/bgm_war.wav'); sr=w.getframerate(); ch=w.getnchannels()
x=np.frombuffer(w.readframes(w.getnframes()),dtype=np.int16).astype(np.float32).reshape(-1,ch)/32768
m=x.mean(1)
a0=421888; b0=int(103.701*sr)                  # 帧级找到的 A、B（B-A ≈ 57 小节）
# 高通后的波形在 ±60ms 里做互相关：鼓点对齐，不会整拍错位
def hpf(s):
    y=np.empty_like(s); p=0.0; a=0.97
    d=np.diff(s,prepend=s[0]); return d
seg=hpf(m[a0-4096:a0+sr])
R=int(sr*0.06); best=(-1e9,0)
for off in range(-R,R+1,4):
    t=hpf(m[b0-4096+off:b0+sr+off]); c=float(np.dot(seg,t))
    if c>best[0]: best=(c,off)
for o2 in range(best[1]-4,best[1]+5):
    t=hpf(m[b0-4096+o2:b0+sr+o2]); c=float(np.dot(seg,t))
    if c>best[0]: best=(c,o2)
B=b0+best[1]; A=a0
t=hpf(m[B-4096:B+sr])
print('A %.4fs B %.4fs shift %.1fms loop %.3fs corr %.3f'%(A/sr,B/sr,best[1]/sr*1000,(B-A)/sr,best[0]/np.sqrt(np.dot(seg,seg)*np.dot(t,t))))
F=int(1.5*sr)                                   # 结尾 1.5 秒交叉淡化（等功率）
loop=x[A:B].copy()
th=np.linspace(0,np.pi/2,F)[:,None]
loop[-F:]=x[B-F:B]*np.cos(th)+x[A-F:A]*np.sin(th)
intro=x[:A].copy()
def save(path,a):
    a=np.clip(a,-1,1); ww=wave.open(path,'wb'); ww.setnchannels(ch); ww.setsampwidth(2); ww.setframerate(sr)
    ww.writeframes((a*32767).astype(np.int16).tobytes()); ww.close()
save('/root/war_intro.wav',intro); save('/root/war_loop.wav',loop)
# 自检：把循环段接两遍，看接缝前后 0.2 秒的能量是否平稳
two=np.concatenate([loop,loop]).mean(1); j=len(loop)
e=[np.sqrt(np.mean(two[j+k*2400:j+(k+1)*2400]**2)) for k in range(-4,4)]
print('seam rms:',' '.join('%.3f'%v for v in e))
