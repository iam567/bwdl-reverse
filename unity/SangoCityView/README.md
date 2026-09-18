# Unity 原型：主地图 + 城内视图

把网页版 `/preview` 用 uGUI 在 Unity 里搭一遍，验证这套素材和版面在引擎里成不成立。
批处理构建，不用开编辑器。

跑起来是主地图：拖动平移、滚轮缩放、点城池选中、再点一次进城。
洛阳能进城内（其余 35 座的城内图还没生成），城内右上角「地图」返回。

| 文件 | 作用 |
|---|---|
| `Game.cs` | 入口，建画布，管两屏切换 |
| `MapView.cs` | 主地图：平移缩放、势力旗、选中环、点选 |
| `CityView.cs` | 城内：数据栏、建筑名匾、蒙版高亮、指令浮窗 |
| `UIKit.cs` | 九宫格 / 摆放 / 文字这些重复动作 |
| `Editor/Builder.cs` | 建场景 + 出 macOS 包 |
| `Editor/CityViewRender.cs` | 另一条路：不出包，直接渲一张 PNG |

## 地图的坐标怎么处理

地图容器的 `sizeDelta` 恒为 **1024×1024**，缩放只动 `localScale`。
这样「1024 坐标系」和「容器局部坐标」就是同一套数，
点击判定、旗子摆位、选中环都不用来回换算——
`ScreenPointToLocalPointInRectangle` 出来的 `lp` 取 `(lp.x, -lp.y)` 就是 1024 坐标。

容器的子物体（旗子、选中环）**都要把锚点和轴心设成左上角 `(0,1)`**。
`Node()` 给的是默认的居中锚点，Apply() 里那套「左上为原点」的坐标配上居中锚点，
算出来的位置会整体甩偏——选中环就这么消失过一次。

镜头居中不能读 `parent.rect.width`：构造时画布还没布局，那会儿宽高是 0，
按它算的居中会偏掉半屏。用构造函数传进来的参考分辨率。

旗子和选中环是容器的子物体，会跟着 `localScale` 一起缩。
要保住「捏到最小也看得清」，每次缩放后按 `max(26/zoom, 地坪半径×0.62)` 重算它们的
`sizeDelta`——单位是 1024 空间，乘上 zoom 之后屏幕尺寸就不低于 26px。

## 跑一次

字体没进仓库（macOS 自带的 Arial Unicode，23MB，也不该随仓库分发），先补上：

```bash
cp "/Library/Fonts/Arial Unicode.ttf" Assets/Fonts/CJK.ttf
```

然后：

```bash
/Applications/Unity/Hub/Editor/6000.4.1f1/Unity.app/Contents/MacOS/Unity \
  -batchmode -projectPath "$PWD" -executeMethod CityViewRender.Run \
  -logFile "$PWD/unity.log" -quit
```

输出 `Render/cityview.png`，750×1624（375×812 的 2 倍）。

## 出包并运行

```bash
/Applications/Unity/Hub/Editor/6000.4.1f1/Unity.app/Contents/MacOS/Unity \
  -batchmode -projectPath "$PWD" -executeMethod Builder.BuildMac \
  -logFile "$PWD/build.log" -quit
open Build/SangoCityView.app
```

约 10 秒出包，500×1084 的竖屏窗口。点建筑看高亮与指令浮窗，点空白取消。
运行时逻辑在 `Assets/Scripts/CityView.cs`，素材从 `StreamingAssets/Art` 读。

点击判定：屏幕坐标 → `RectTransformUtility.ScreenPointToLocalPointInRectangle`
→ 图像坐标 → `mask.GetPixel` 查色表。
注意贴图的 y 轴朝上而图像坐标朝下，取像素时要 `mask.height-1-y`。
读到黑色（院内空地，比如校场）就退回按包围盒兜底，取面积最小的那个。

高亮贴图**按需生成并缓存**：整张 1024×1536 逐像素算 R=5 的邻域太慢，
所以只在建筑包围盒外扩 7px 的范围里算，其余直接铺暗色。

## 几处引擎侧的做法

**九宫格**用 `Sprite.Create(..., border)` + `Image.Type.Sliced`。
Sliced 显示的边框厚度 = `borderPx × (100 / pixelsPerUnit)`，
所以要让源图 58px 的铜框显示成 11pt，得反推 `PPU = 58×100/11`。
`Slice()` 就是干这个的。按钮的铜钉在横向 90px 以内，
所以它的边框传的是非对称的 `(30, 90)`，否则铜钉会被拉成椭圆。

**蒙版高亮**没有走 shader，而是 CPU 把「压暗四周 + 按蒙版挖洞 + 沿轮廓金边」
合成成一张 RGBA 贴图直接盖上去（`BuildHighlight`）。
一次算完就不用每帧做，正式做的时候再换 shader 也不迟。

**素材不走 Unity 的导入管线**，直接 `File.ReadAllBytes` + `Texture2D.LoadImage`。
省掉一堆 TextureImporter 设置（可读、Sprite 模式、边框），
批处理里也不会因为导入设置没配好而渲出错东西。

数据来自 `Assets/Art/stats.json`（洛阳），建筑框来自 `city.json`
（`buildings` 已从对象字典转成数组——`JsonUtility` 不吃字典）。
