using System.Collections.Generic;
// uGUI 的几个重复动作，抽出来给地图和城内共用。
using System.IO;
using UnityEngine;
using UnityEngine.UI;

public static class UIKit
{
    public static Font UIFont;
    public static Font UIFontBold;                      // 当前字体的粗字重；没有（站酷小薇）就用常规，不假加粗
    public static string ArtDir => Path.Combine(Application.streamingAssetsPath, "Art");

    /// 素材可能还没生成的地方用这个：文件不在就回 null，调用方退回代码画的样式
    public static Texture2D TryLoad(string rel)
        => File.Exists(Path.Combine(ArtDir, rel)) ? Load(rel) : null;

    // ── 贴图缓存（2026-09-26 手机上内存涨到 600 多 MB 被系统杀掉）──
    // 以前每次 Load 都新建一张：重建界面时大地图（3277² 那张）、城图、弹框底等整套重读、旧的不放；
    // 读进来的还留着一份 CPU 可读副本，内存翻倍。现在：
    //   Load / TryLoad —— 按路径缓存、共用，读完丢掉 CPU 副本（只有 *_mask.png 要 GetPixel，留着）。**不要 Destroy 它们**。
    //   LoadFresh / TryLoadFresh —— 不进缓存，调用方用完自己 Destroy（城图、天下霸图卡面、动画帧的 LRU）。
    static readonly Dictionary<string, Texture2D> texCache = new();
    public static Texture2D Load(string rel)
    {
        if (texCache.TryGetValue(rel, out var c) && c != null) return c;
        return texCache[rel] = LoadFresh(rel, rel.EndsWith("_mask.png"));
    }
    public static Texture2D LoadFresh(string rel, bool readable = false)
    {
        var t = new Texture2D(2, 2, TextureFormat.RGBA32, false);
        t.LoadImage(File.ReadAllBytes(Path.Combine(ArtDir, rel)));
        t.wrapMode = TextureWrapMode.Clamp; t.Apply(false, !readable);
        return t;
    }
    public static Texture2D TryLoadFresh(string rel, bool readable = false)
        => File.Exists(Path.Combine(ArtDir, rel)) ? LoadFresh(rel, readable) : null;

    /// 九宫格。Sliced 显示的边 = borderPx × (100 / PPU)，所以按想要的厚度反推 PPU。
    public static Sprite Slice(Texture2D t, float bx, float by, float wx, float wy) =>
        Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f),
            bx * 100f / wx, 0, SpriteMeshType.FullRect, new Vector4(bx, by, bx, by));
    public static Sprite Slice(Texture2D t, float b, float w) => Slice(t, b, b, w, w);

    public static Sprite Whole(Texture2D t) =>
        Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f), 100);

    public static RectTransform Node(string n, Transform p)
    {
        var g = new GameObject(n, typeof(RectTransform));
        g.transform.SetParent(p, false);
        return (RectTransform)g.transform;
    }
    /// 左上角为原点摆放，和网页的 left/top 一个心智模型
    public static void Place(RectTransform rt, float x, float y, float w, float h)
    {
        rt.anchorMin = rt.anchorMax = new Vector2(0, 1);
        rt.pivot = new Vector2(0, 1);
        rt.anchoredPosition = new Vector2(x, -y);
        rt.sizeDelta = new Vector2(w, h);
        if (rt.TryGetComponent<LText>(out var lt)) lt.Refit();          // 译文放不下就缩字（框宽现在才定）
    }
    public static void Fill(RectTransform rt)
    {
        rt.anchorMin = Vector2.zero; rt.anchorMax = Vector2.one;
        rt.offsetMin = rt.offsetMax = Vector2.zero;
    }
    public static Text Label(Transform p, string s, int size, Color c,
        TextAnchor a = TextAnchor.MiddleLeft, FontStyle st = FontStyle.Normal)
    {
        var rt = Node("t", p);
        var t = rt.gameObject.AddComponent<LText>();             // 显示时自动翻译（多语言）
        bool bold = st == FontStyle.Bold || st == FontStyle.BoldAndItalic;
        t.font = bold && UIFontBold != null ? UIFontBold : UIFont;
        t.fontSize = size; t.color = c; t.alignment = a; t.text = s;
        t.fontStyle = bold ? FontStyle.Normal : st; t.raycastTarget = false;           // 粗靠字重，不让 Unity 把中文描糊
        t.horizontalOverflow = HorizontalWrapMode.Overflow;
        t.verticalOverflow = VerticalWrapMode.Overflow;
        return t;
    }
    // ── 中文断行（Unity 自带的 Wrap 不懂标点规则，会把「。」「，」甩到行首）──
    // 按字体实际字宽算每行放几个字；句读、右括号引号不上行首（挂到上一行尾），左括号引号不留行尾（挪到下一行）。
    const string NoStart = "，。、；：？！…—）》」』】〉”’%％·,.;:?!)]}";
    const string NoEnd = "（《「『【〈“‘([{";
    /// 摆好位置（Place）之后调：按这个框的宽度把 t.text 重新断好行，关掉 Unity 自己的换行
    public static void Cjk(Text t)
    {
        if (t == null || string.IsNullOrEmpty(t.text) || t.font == null) return;
        if (!Loc.CjkWrap) { t.horizontalOverflow = HorizontalWrapMode.Wrap; return; }   // 英文 / 韩文按词换行，Unity 自带的 Wrap 就对
        float width = t.rectTransform.rect.width; if (width <= 1) width = t.rectTransform.sizeDelta.x;
        if (width <= 1) return;
        var f = t.font; int size = t.fontSize; var st = t.fontStyle;
        f.RequestCharactersInTexture(t.text, size, st);
        float Adv(char c) => f.GetCharacterInfo(c, out var ci, size, st) ? ci.advance : size;
        var sb = new System.Text.StringBuilder(); var line = new System.Text.StringBuilder(); float lw = 0;
        void Flush() { sb.Append(line).Append('\n'); line.Clear(); lw = 0; }
        foreach (char c in t.text)
        {
            if (c == '\n') { Flush(); continue; }
            float a = Adv(c);
            if (lw + a > width && line.Length > 0)
            {
                if (NoStart.IndexOf(c) >= 0) { line.Append(c); lw += a; Flush(); continue; }   // 标点挂在行尾
                string carry = "";
                if (NoEnd.IndexOf(line[line.Length - 1]) >= 0) { carry = line[line.Length - 1].ToString(); line.Length--; }   // 左括号挪下去
                Flush();
                foreach (char k in carry) { line.Append(k); lw += Adv(k); }
            }
            line.Append(c); lw += a;
        }
        sb.Append(line);
        t.horizontalOverflow = HorizontalWrapMode.Overflow;
        if (t is LText lt) { bool raw = lt.Raw; lt.Raw = true; t.text = sb.ToString(); lt.Raw = raw; }   // 已经是译文了
        else t.text = sb.ToString();
    }

    public static Image Panel(Transform p, string n, Sprite s, bool sliced = true)
    {
        var im = Node(n, p).gameObject.AddComponent<Image>();
        im.sprite = s; if (sliced) im.type = Image.Type.Sliced;
        im.raycastTarget = false;
        return im;
    }
    /// 程序生成的简易描边框，给令牌/返回这类小按钮打底
    public static Sprite Frame()
    {
        var t = new Texture2D(24, 24, TextureFormat.RGBA32, false);
        for (int y = 0; y < 24; y++)
            for (int x = 0; x < 24; x++)
            {
                bool e = x < 2 || y < 2 || x > 21 || y > 21;
                t.SetPixel(x, y, e ? new Color32(0x6B,0x55,0x2D,255) : new Color32(0xC8,0xA4,0x5C,20));
            }
        t.Apply();
        return Sprite.Create(t, new Rect(0,0,24,24), new Vector2(.5f,.5f), 100, 0,
                             SpriteMeshType.FullRect, new Vector4(8,8,8,8));
    }
    /// 深色实底 + 金边，给信息条这类要压住背景的面板用。
    /// Frame() 的中心几乎透明，压在地图上字会看不清。
    public static Sprite Plate()
    {
        const int S = 32, R = 8;
        var t = new Texture2D(S, S, TextureFormat.RGBA32, false);
        for (int y = 0; y < S; y++)
            for (int x = 0; x < S; x++)
            {
                float dx = Mathf.Max(R - x, x - (S - 1 - R), 0);
                float dy = Mathf.Max(R - y, y - (S - 1 - R), 0);
                float d = Mathf.Sqrt(dx * dx + dy * dy);       // 到圆角矩形边界的距离
                float a = Mathf.Clamp01(R - d);
                bool edge = d > R - 2.2f;
                var c = edge ? new Color(.60f, .47f, .24f, 1f) : new Color(.086f, .075f, .055f, .95f);
                t.SetPixel(x, y, new Color(c.r, c.g, c.b, c.a * a));
            }
        t.Apply();
        return Sprite.Create(t, new Rect(0,0,S,S), new Vector2(.5f,.5f), 100, 0,
                             SpriteMeshType.FullRect, new Vector4(R+2,R+2,R+2,R+2));
    }

    /// 椭圆光环：贴地的选中标记
    /// 实心的软边椭圆：战棋层拿它当脚下的投影，把小人从花地块上垫起来。
    /// Ring() 画的是环，中间是空的，垫不住。
    static Sprite disc;
    public static Sprite Disc(int w = 192, int h = 72)
    {
        if (disc != null) return disc;
        var t = new Texture2D(w, h, TextureFormat.RGBA32, false);
        float cx = w / 2f, cy = h / 2f;
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
            {
                float dx = (x - cx) / (w / 2f), dy = (y - cy) / (h / 2f);
                float d = Mathf.Sqrt(dx * dx + dy * dy);
                t.SetPixel(x, y, new Color(0f, 0f, 0f, Mathf.Clamp01(1f - d) * Mathf.Clamp01(1f - d)));
            }
        t.Apply(); t.filterMode = FilterMode.Bilinear;
        return disc = Sprite.Create(t, new Rect(0, 0, w, h), new Vector2(.5f, .5f), 100);
    }

    public static Sprite Ring(int w = 384, int h = 256, float thick = .085f)
    {
        var t = new Texture2D(w, h, TextureFormat.RGBA32, false);
        float cx = w / 2f, cy = h / 2f;
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
            {
                float dx = (x - cx) / (w / 2f), dy = (y - cy) / (h / 2f);
                float d = Mathf.Sqrt(dx * dx + dy * dy);
                float a = Mathf.Clamp01(1f - Mathf.Abs(d - .90f) / thick);
                float glow = Mathf.Clamp01(1f - Mathf.Abs(d - .90f) / .34f);        // 环外一圈柔光
                float lower = Mathf.InverseLerp(0f, 1f, (cy - y) / cy * .5f + .5f); // 下半亮上半淡
                float dir = Mathf.Lerp(.25f, 1f, lower);
                t.SetPixel(x, y, new Color(1f, .95f, .76f,
                    Mathf.Clamp01(a * a * dir + glow * glow * .16f * dir)));
            }
        t.Apply();
        return Sprite.Create(t, new Rect(0,0,w,h), new Vector2(.5f,.5f), 100);
    }
    public static string Wan(int v) => v >= 10000 ? (v / 10000f).ToString("0.0") + "万" : v.ToString();
}
