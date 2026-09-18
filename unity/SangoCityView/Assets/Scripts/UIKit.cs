// uGUI 的几个重复动作，抽出来给地图和城内共用。
using System.IO;
using UnityEngine;
using UnityEngine.UI;

public static class UIKit
{
    public static Font UIFont;
    public static string ArtDir => Path.Combine(Application.streamingAssetsPath, "Art");

    public static Texture2D Load(string rel)
    {
        var t = new Texture2D(2, 2, TextureFormat.RGBA32, false);
        t.LoadImage(File.ReadAllBytes(Path.Combine(ArtDir, rel)));
        t.wrapMode = TextureWrapMode.Clamp; t.Apply();
        return t;
    }

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
        var t = rt.gameObject.AddComponent<Text>();
        t.font = UIFont; t.fontSize = size; t.color = c; t.alignment = a; t.text = s;
        t.fontStyle = st; t.raycastTarget = false;
        t.horizontalOverflow = HorizontalWrapMode.Overflow;
        t.verticalOverflow = VerticalWrapMode.Overflow;
        return t;
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
    /// 椭圆光环：贴地的选中标记
    public static Sprite Ring(int w = 256, int h = 171, float thick = .07f)
    {
        var t = new Texture2D(w, h, TextureFormat.RGBA32, false);
        float cx = w / 2f, cy = h / 2f;
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
            {
                float dx = (x - cx) / (w / 2f), dy = (y - cy) / (h / 2f);
                float d = Mathf.Sqrt(dx * dx + dy * dy);
                float a = Mathf.Clamp01(1f - Mathf.Abs(d - .92f) / thick);
                float lower = Mathf.InverseLerp(0f, 1f, (cy - y) / cy * .5f + .5f); // 下半亮上半淡
                t.SetPixel(x, y, new Color(1f, .94f, .74f, a * a * Mathf.Lerp(.22f, 1f, lower)));
            }
        t.Apply();
        return Sprite.Create(t, new Rect(0,0,w,h), new Vector2(.5f,.5f), 100);
    }
    public static string Wan(int v) => v >= 10000 ? (v / 10000f).ToString("0.0") + "万" : v.ToString();
}
