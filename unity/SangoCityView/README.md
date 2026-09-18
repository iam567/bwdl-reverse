# 城内视图 · Unity 实机渲染

把网页版 `/preview` 的城内那一屏用 uGUI 在 Unity 里原样搭一遍，
验证这套素材和版面在引擎里成不成立。批处理跑，不用开编辑器。

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

## 出一个能点的 macOS 程序

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
