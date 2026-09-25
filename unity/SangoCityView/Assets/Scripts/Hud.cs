// 顶栏零件：年份牌、方形图标按钮。大地图和城内共用。
// 素材在 Art/hud/（平台 hud.js 生成、裁掉透明边后缩小），不在就退回代码画的框。
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public static class Hud
{
    static readonly Dictionary<string, Sprite> cache = new();

    /// bx/by 是素材里那圈边的像素数，onScreen 是希望横向那条边在屏幕上占多宽
    static Sprite Get(string file, float bx, float by, float onScreen)
    {
        string k = file + "|" + onScreen;
        if (cache.TryGetValue(k, out var sp)) return sp;
        var t = TryLoad(System.IO.Path.Combine("hud", file));
        return cache[k] = t == null ? null : (bx > 0 ? Slice(t, bx, by, onScreen, onScreen) : Whole(t));
    }

    /// 弹框底：干净平整的浅米色宣纸——中间透亮、四周略压暗（程序生成的径向渐变，不带褶皱污渍）
    static Sprite paper;
    public static Sprite Paper()
    {
        if (paper != null) return paper;
        const int N = 96;
        var t = new Texture2D(N, N, TextureFormat.RGBA32, false) { wrapMode = TextureWrapMode.Clamp };
        var c0 = new Color(.978f, .958f, .905f, 1); var c1 = new Color(.905f, .862f, .775f, 1);
        for (int y = 0; y < N; y++) for (int x = 0; x < N; x++)
        {
            float dx = (x - (N - 1) / 2f) / (N / 2f), dy = (y - (N - 1) / 2f) / (N / 2f);
            float d = Mathf.Clamp01(Mathf.Max(Mathf.Abs(dx), Mathf.Abs(dy)) * .55f + Mathf.Sqrt(dx * dx + dy * dy) * .45f);
            t.SetPixel(x, y, Color.Lerp(c0, c1, Mathf.SmoothStep(0, 1, Mathf.Pow(d, 2.2f))));
        }
        t.Apply(); return paper = Sprite.Create(t, new Rect(0, 0, N, N), new Vector2(.5f, .5f));
    }

    /// 顶栏底：上深下透的竖向渐隐（程序生成，1×64）
    static Sprite fade;
    public static Sprite FadeDown()
    {
        if (fade != null) return fade;
        var t = new Texture2D(1, 64, TextureFormat.RGBA32, false) { wrapMode = TextureWrapMode.Clamp };
        for (int y = 0; y < 64; y++) { float a = Mathf.Pow(y / 63f, 1.6f) * .82f; t.SetPixel(0, y, new Color(.05f, .035f, .02f, a)); }
        t.Apply(); return fade = Sprite.Create(t, new Rect(0, 0, 1, 64), new Vector2(.5f, .5f));
    }
    /// 深漆底 + 细描金框（城内数据框、建筑浮窗、大地图底栏同一套）
    public static Image DarkPanel(Transform p, string name, float x, float y, float w, float h, float alpha = .86f)
    {
        var im = Node(name, p).gameObject.AddComponent<Image>();
        Place(im.rectTransform, x, y, w, h); im.color = new Color(.085f, .06f, .035f, alpha); im.raycastTarget = false;
        CgFrame(im.rectTransform);
        return im;
    }

    /// 整张图（不切九宫格），hud/ 下的图标用
    public static Sprite Icon(string file) => Get(file, 0, 0, 0);

    /// 动画外沿的细画框（平台 ui_cgframe：描金双线夹赭红漆带、四角如意云纹，中间镂空）。
    /// 裁过的图 1429×826，框线在距边 16~38px，四角云纹在 80px 以内；九宫格边 80 → 屏上 12，框线落在外沿往里 2.4~5.7。
    /// 套在动画那张 Image 上当子物体，四边各外扩 5，框线正好贴着画面外沿。
    public static void CgFrame(RectTransform img) => CgFrame(img, 12, 5);
    /// onScreen：九宫格边在屏上多宽（框线约占它的 0.2~0.48、角花占满）；outset：往外扩多少，让框线贴着内容外沿
    public static void CgFrame(RectTransform img, float onScreen, float outset)
    {
        var sp = Get("ui_cgframe.png", 80, 80, onScreen); if (sp == null) return;
        var fr = Node("cgframe", img).gameObject.AddComponent<Image>();
        fr.sprite = sp; fr.type = Image.Type.Sliced; fr.raycastTarget = false;
        var rt = fr.rectTransform;
        rt.anchorMin = Vector2.zero; rt.anchorMax = Vector2.one; rt.pivot = new Vector2(.5f, .5f);
        rt.offsetMin = new Vector2(-outset, -outset); rt.offsetMax = new Vector2(outset, outset);
    }

    /// 「189年1月」。原版写在大地图标题栏（c.java W() 6627：aD + "年" + aC%13 + "月"）
    public static string Date(int year, int month) => year + "年" + month + "月";

    /// 年份牌。ui_yearplate 是 800×156 的如意云头长匾，两端云头在横向 125px 以内，上下金线在 30px 以内。
    public static Text YearPlate(Transform p, float x, float y, float w, float h, string text, int size = 16)
    {
        var im = Node("year", p).gameObject.AddComponent<Image>();
        Place(im.rectTransform, x, y, w, h);
        im.sprite = Get("ui_yearplate.png", 125, 30, h * .6f) ?? Plate();
        im.type = Image.Type.Sliced; im.raycastTarget = false;
        var t = Label(im.transform, text, size, new Color32(0xF4,0xDC,0x9A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(t.rectTransform, 0, 0, w, h);
        var sh = t.gameObject.AddComponent<Shadow>();
        sh.effectColor = new Color(0, 0, 0, .85f); sh.effectDistance = new Vector2(1, -1);
        return t;
    }

    /// 方形图标按钮：ui_iconbtn 底 + 图标。图标没到时写 fallback 那两个字。
    /// 返回按钮本体（raycast 开着），调用方自己挂点击。
    public static Image IconButton(Transform p, string name, float x, float y, float w, float h,
                                   string icon, string fallback, out RectTransform iconRT)
    {
        var bg = Node(name, p).gameObject.AddComponent<Image>();
        Place(bg.rectTransform, x, y, w, h);
        bg.sprite = Get("ui_iconbtn.png", 22, 22, 7f) ?? Frame();   // 128×128，四角云纹在 22px 以内
        bg.type = Image.Type.Sliced; bg.raycastTarget = true;
        iconRT = null;
        var sp = Get(icon + ".png", 0, 0, 0);
        if (sp != null)
        {
            var ic = Node("icon", bg.transform).gameObject.AddComponent<Image>();
            ic.sprite = sp; ic.preserveAspect = true; ic.raycastTarget = false;
            iconRT = ic.rectTransform;
            float s = Mathf.Min(w, h) - 14;
            Place(iconRT, (w - s) / 2, (h - s) / 2, s, s);
        }
        else if (fallback != null)
        {
            var t = Label(bg.transform, fallback, 13, new Color32(0xE8,0xC0,0x6A,255), TextAnchor.MiddleCenter);
            Place(t.rectTransform, 0, 0, w, h);
        }
        return bg;
    }
}
