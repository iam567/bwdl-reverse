import wave, numpy as np
w=wave.open('/root/bgm_war.wav'); sr=w.getframerate(); ch=w.getnchannels()
x=np.frombuffer(w.readframes(w.getnframes()),dtype=np.int16).astype(np.float32).reshape(-1,ch)/32768
m=x.mean(1); N=len(m)
# 频谱特征：2048 窗、1024 跳，24 个对数频带
H=1024; win=np.hanning(2048)
T=(N-2048)//H
S=np.stack([np.abs(np.fft.rfft(m[i*H:i*H+2048]*win)) for i in range(T)])
edges=np.unique(np.geomspace(2,S.shape[1]-1,25).astype(int))
F=np.stack([S[:,edges[k]:edges[k+1]].sum(1) for k in range(len(edges)-1)],1)
F=np.log(F+1e-6); F-=F.mean(1,keepdims=True)
fps=sr/H
def fr(sec): return int(sec*fps)
W1,W2=fr(2.0),fr(4.0)                     # 比较 A、B 前 2 秒到后 4 秒
As=range(fr(6.5),fr(18))
Bs=np.arange(fr(90),T-W2-1)
best=(1e18,0,0)
for A in As:
    D=np.zeros(len(Bs))
    for w_ in range(-W1,W2,2):
        D+=((F[Bs+w_]-F[A+w_])**2).sum(1)
    k=int(np.argmin(D))
    if D[k]<best[0]: best=(D[k],A,int(Bs[k]))
d,A,B=best
print('frame A %.3fs B %.3fs len %.3fs score %.1f'%(A/fps,B/fps,(B-A)/fps,d))
# 采样级对齐：在 B 附近 ±1 拍找波形互相关最大
a0=A*H; b0=B*H; L=8192; R=int(sr*0.45)
seg=m[a0:a0+L]
best2=(-1e9,0)
for off in range(-R,R,8):
    c=float(np.dot(seg,m[b0+off:b0+off+L]))
    if c>best2[0]: best2=(c,off)
off=best2[1]
for o2 in range(off-8,off+8):
    c=float(np.dot(seg,m[b0+o2:b0+o2+L]))
    if c>best2[0]: best2=(c,o2)
bS=b0+best2[1]
print('sample A %d (%.3fs) B %d (%.3fs) loop %.3fs corr %.3f'%(a0,a0/sr,bS,bS/sr,(bS-a0)/sr,best2[0]/np.sqrt(np.dot(seg,seg)*np.dot(m[bS:bS+L],m[bS:bS+L]))))
open('/root/loop_pts.txt','w').write(f'{a0} {bS} {sr}\n')
