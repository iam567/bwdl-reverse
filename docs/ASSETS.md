# 素材

`assets/` 是生图平台产出的全部素材，从 VPS 打包同步过来。

## 为什么有的是 PNG 有的是 JPEG

原始产出 1.9GB 全是 PNG，直接进普通 git 不现实
（GitHub 超 1GB 开始警告，LFS 免费额度也只有 1GB）。
同步时**逐文件**判断有没有真正用到透明通道：

| | 处理 | 理由 |
|---|---|---|
| 用到 alpha | 留 PNG，只做无损优化 | 城池图标、旗帜、城内建筑、UI 底板都要贴到别的图上，压丢 alpha 就废了 |
| 没用到 alpha | 转 JPEG q92 | 武将卡、头像、地形块是整幅画面，q92 肉眼看不出差别，体积掉八成 |

判断用的是 `a.getextrema()[0] < 250`——只看「最透明的那个像素有多透」，
光看 `mode == RGBA` 会把一堆其实不透明的图误判成要留 PNG。

**原始 PNG 仍在 VPS** `/var/www/sg.areu.pro/output/`。
那台机器是临时的，要长期保存无损原图得另外找地方。

## 目录

| 目录 | 内容 |
|---|---|
| `card/` | 武将卡，1024×1536 |
| `avatar/` | 武将头像，1024×1024，由对应卡牌派生 |
| `city/` | 36 座城的城池图标，透明底，贴到大地图上 |
| `flag/` | 10 面势力旗 + 形状母版；`web/` 是网页用的小图与锚点表 |
| `citybld/` | 城内 8 种建筑、洛阳全景与蒙版、UI 底板（匾额/面板/按钮/背景） |
| `cityview/` | 洛阳城内视图的成品：全景图、洗干净的蒙版、UI 九宫格素材 |
| `map/` | 地形层与合成好的大地图、总览底图、地坪 |
| `terrain/` | 地形分块，拼接大地图的原料 |
| `ui/` | 城名铜牌、地坪等早期 UI 素材 |
| `portrait/` `styletest/` | 定画风阶段的实验图，留档 |

## 脚本要的 `work/` 怎么搭

`extract/mapgen/` 下的脚本都接一个 work 目录参数，里面要有：

```
work/
  city-screen.json      ← extract/data/city-screen.json
  city_latest.json      ← extract/data/city_latest.json   （城池 ID → 图标文件名）
  city/                 ← assets/city/
  ui/                   ← assets/ui/
  sgapp/cities.js       ← image-studio/cities.js          （脚本从里面正则取分级）
  stitched_L1.jpg       ← assets/map/terrain_L1_3277.jpg
  stitched_L2.jpg       ← assets/map/terrain_L2_6528.jpg
```

`work/` 本身没进仓库——里面都是这些文件的副本和中间产物，
照上表软链或拷过去即可。
