// 城内视图的 Unity 实机渲染。
// 用 uGUI 把网页版那一屏原样搭出来：九宫格面板/按钮、蒙版高亮、描边文字，
// 渲到 RenderTexture 再存 PNG。批处理跑，不用开编辑器。
using System.Collections.Generic;
using System.IO;
using UnityEditor;
using UnityEditor.SceneManagement;
using UnityEngine;
using UnityEngine.UI;

public static class CityViewRender
{
    const int REF_W = 375, REF_H = 812, SCALE = 2;   // 参考分辨率 375×812，2 倍渲染
    const string ART = "Assets/Art/";
    static Font font;

    // ---- 工具 ----
    static Texture2D Load(string f)
    {
        var t = new Texture2D(2, 2, TextureFormat.RGBA32, false);
        t.LoadImage(File.ReadAllBytes(ART + f));
        t.wrapMode = TextureWrapMode.Clamp;
        t.Apply();
        return t;
    }

    /// 九宫格精灵：borderPx 是源图上的边框厚度，wantPx 是它在 UI 上应显示的厚度。
    /// Image(Sliced) 显示的边 = borderPx × (100 / pixelsPerUnit)，所以反推 PPU。
    static Sprite Slice(Texture2D t, float borderPx, float wantPx)
    {
        float ppu = borderPx * 100f / wantPx;
        return Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f),
            ppu, 0, SpriteMeshType.FullRect,
            new Vector4(borderPx, borderPx, borderPx, borderPx));
    }
    static Sprite Slice(Texture2D t, float bx, float by, float wx, float wy)
    {
        float ppu = bx * 100f / wx;
        return Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f),
            ppu, 0, SpriteMeshType.FullRect, new Vector4(bx, by, bx, by));
    }

    static RectTransform Node(string name, Transform parent)
    {
        var go = new GameObject(name, typeof(RectTransform));
        go.transform.SetParent(parent, false);
        return (RectTransform)go.transform;
    }
    /// 左上角为原点摆放，跟网页的 left/top 一个心智模型
    static void Place(RectTransform rt, float x, float y, float w, float h)
    {
        rt.anchorMin = rt.anchorMax = new Vector2(0, 1);
        rt.pivot = new Vector2(0, 1);
        rt.anchoredPosition = new Vector2(x, -y);
        rt.sizeDelta = new Vector2(w, h);
    }
    static Text Label(Transform p, string s, int size, Color c, TextAnchor a = TextAnchor.MiddleLeft,
                      FontStyle st = FontStyle.Normal)
    {
        var rt = Node("t_" + s, p);
        var t = rt.gameObject.AddComponent<Text>();
        t.font = font; t.fontSize = size; t.color = c; t.alignment = a; t.text = s;
        t.fontStyle = st; t.horizontalOverflow = HorizontalWrapMode.Overflow;
        t.verticalOverflow = VerticalWrapMode.Overflow;
        return t;
    }
    static Image Panel(Transform p, string name, Sprite s)
    {
        var rt = Node(name, p);
        var im = rt.gameObject.AddComponent<Image>();
        im.sprite = s; im.type = Image.Type.Sliced;
        return im;
    }

    [MenuItem("Sango/Render City View")]
    public static void Run()
    {
        var scene = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
        font = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/CJK.ttf");
        if (font == null) { Debug.LogError("字体没加载到"); EditorApplication.Exit(2); return; }

        var city  = Load("city.jpg");
        var maskT = Load("mask.png");
        var meta  = JsonUtility.FromJson<CityMeta>(File.ReadAllText(ART + "city.json"));
        var st    = JsonUtility.FromJson<Stats>(File.ReadAllText(ART + "stats.json"));

        // 相机 + 画布
        var camGO = new GameObject("Cam", typeof(Camera));
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.082f, .067f, .043f);
        cam.orthographic = true;
        var rtx = new RenderTexture(REF_W * SCALE, REF_H * SCALE, 24, RenderTextureFormat.ARGB32);
        rtx.antiAliasing = 4;
        cam.targetTexture = rtx;

        var canvasGO = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = canvasGO.GetComponent<Canvas>();
        canvas.renderMode = RenderMode.ScreenSpaceCamera;
        canvas.worldCamera = cam;
        canvas.planeDistance = 10;
        var sc = canvasGO.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(REF_W, REF_H);
        sc.matchWidthOrHeight = 0;
        var root = (RectTransform)canvasGO.transform;

        // ---- 背景：木板底 ----
        var bg = Node("BG", root);
        bg.anchorMin = Vector2.zero; bg.anchorMax = Vector2.one;
        bg.offsetMin = bg.offsetMax = Vector2.zero;
        var bgi = bg.gameObject.AddComponent<RawImage>();
        bgi.texture = Load("bg.jpg");

        const float PAD = 12f;
        float W = REF_W - PAD * 2;
        float y = 10f;

        // ---- 顶部：城名 / 规模势力 / 令牌 / 返回 ----
        Place(Label(root, st.name, 30, new Color32(0xF6,0xE9,0xCD,255), TextAnchor.MiddleLeft, FontStyle.Bold)
              .rectTransform, PAD + 2, y, 110, 40);
        Place(Label(root, st.tierName, 11, new Color32(0x9A,0x8A,0x68,255)).rectTransform, PAD + 88, y + 2, 90, 16);
        Place(Label(root, "◼ " + RulerName(st.owner), 11, new Color32(0xCF,0xC4,0xAB,255)).rectTransform,
              PAD + 88, y + 20, 120, 16);

        var tok = Panel(root, "Token", SolidFrame());
        Place(tok.rectTransform, REF_W - PAD - 52 - 8 - 46, y, 52, 46);
        Place(Label(tok.transform, st.令牌.ToString(), 24, new Color32(0xF0,0xC3,0x4A,255), TextAnchor.MiddleCenter, FontStyle.Bold)
              .rectTransform, 0, 2, 52, 28);
        Place(Label(tok.transform, "令牌", 10, new Color32(0x9A,0x8A,0x68,255), TextAnchor.MiddleCenter).rectTransform, 0, 28, 52, 14);

        var back = Panel(root, "Back", SolidFrame());
        Place(back.rectTransform, REF_W - PAD - 46, y, 46, 46);
        Place(Label(back.transform, "地图", 13, new Color32(0xE8,0xC0,0x6A,255), TextAnchor.MiddleCenter).rectTransform, 0, 0, 46, 46);
        y += 52;

        // ---- 数据面板：帛书绢面，九宫格 ----
        float panelH = 145f;
        var dp = Panel(root, "Stats", Slice(Load("panel.png"), 58, 11));
        Place(dp.rectTransform, PAD, y, W, panelH);
        string[,] rows = {
            {"君主", st.ruler}, {"太守", st.governor}, {"武将", st.武将.ToString()},
            {"人口", Wan(st.人口)}, {"兵力", Wan(st.兵力)}, {"治安", st.治安.ToString()},
            {"农业", st.农业.ToString()}, {"商业", st.商业.ToString()}, {"治水", st.治水.ToString()},
            {"黄金", st.黄金.ToString()}, {"粮草", st.粮草.ToString()}, {"规模", st.tierName},
        };
        float cw = (W - 22) / 3f, rh = (panelH - 22) / 4f;
        for (int i = 0; i < 12; i++)
        {
            float cx = 11 + (i % 3) * cw, cy = 11 + (i / 3) * rh;
            var cell = Node("cell" + i, dp.transform);
            Place(cell, cx, cy, cw, rh);
            Place(Label(cell, rows[i,0], 11, new Color32(0x6B,0x52,0x30,255), TextAnchor.MiddleRight, FontStyle.Bold)
                  .rectTransform, 0, 0, cw * .42f, rh);
            Place(Label(cell, rows[i,1], 15, new Color32(0x2E,0x21,0x10,255), TextAnchor.MiddleLeft, FontStyle.Bold)
                  .rectTransform, cw * .47f, 0, cw * .53f, rh);
        }
        y += panelH + 8;

        // ---- 城内图 ----
        float imgW = W, imgH = W * city.height / (float)city.width;
        var pic = Node("City", root);
        Place(pic, PAD, y, imgW, imgH);
        pic.gameObject.AddComponent<RawImage>().texture = city;
        float f = imgW / meta.size[0];               // 图像坐标 → UI 单位

        // ---- 蒙版高亮：把「压暗 + 挖洞 + 金边」合成一张贴图，直接盖上去 ----
        const string HL = "xiaochang";
        var hlTex = BuildHighlight(maskT, meta.Find(HL).mask);
        var hl = Node("HL", pic);
        hl.anchorMin = Vector2.zero; hl.anchorMax = Vector2.one; hl.offsetMin = hl.offsetMax = Vector2.zero;
        hl.gameObject.AddComponent<RawImage>().texture = hlTex;

        // ---- 建筑名匾 ----
        var plaque = Slice(Load("plaque.png"), 264, 9);
        foreach (var b in meta.buildings)
        {
            bool dim = b.key != HL;
            float fs = 11f;
            float pw = fs * (b.name.Length + b.func.Length + 1) * .82f + 16;
            float ph = fs * 1.95f;
            var pl = Panel(pic, "pl_" + b.key, plaque);
            Place(pl.rectTransform, (b.box[0] + b.box[2]) / 2f * f - pw / 2, b.box[3] * f - ph * .62f, pw, ph);
            pl.color = dim ? new Color(1,1,1,.38f) : Color.white;
            var tx = Label(pl.transform, b.name, (int)fs, new Color(.973f,.937f,.847f, dim ? .38f : 1f),
                           TextAnchor.MiddleLeft, FontStyle.Bold);
            Place(tx.rectTransform, 8, 0, pw, ph);
            var tf = Label(pl.transform, "·" + b.func, (int)(fs * .62f),
                           new Color(.827f,.706f,.471f, dim ? .38f : 1f), TextAnchor.MiddleRight);
            Place(tf.rectTransform, 0, 1, pw - 8, ph);
        }

        // ---- 指令浮窗 ----
        var target = meta.Find(HL);
        string[] cmds = { "移动", "战争", "征兵", "输送" };
        float popW = 150, popH = 9 + 22 + 5 + 2 * 32 + 5 + 9;
        var pop = Panel(pic, "Pop", Slice(Load("menu.png"), 62, 11));
        float px = target.box[2] * f + 9, py = (target.box[1] + target.box[3]) / 2f * f - popH / 2;
        if (px + popW > imgW - 4) px = target.box[0] * f - 9 - popW;
        Place(pop.rectTransform, Mathf.Clamp(px, 4, imgW - popW - 4), Mathf.Clamp(py, 4, imgH - popH - 4), popW, popH);

        Place(Label(pop.transform, target.name, 16, new Color32(0xF7,0xEC,0xD4,255), TextAnchor.MiddleLeft, FontStyle.Bold)
              .rectTransform, 10, 8, 80, 22);
        Place(Label(pop.transform, "· " + target.func, 10, new Color32(0xB9,0xA4,0x7A,255)).rectTransform, 58, 10, 70, 20);
        var btn = Slice(Load("btn.png"), 30, 90, 6, 9);
        for (int i = 0; i < cmds.Length; i++)
        {
            var b = Panel(pop.transform, "b" + i, btn);
            float bw = (popW - 18 - 5) / 2f;
            Place(b.rectTransform, 9 + (i % 2) * (bw + 5), 36 + (i / 2) * 32, bw, 28);
            var t = Label(b.transform, cmds[i], 13, new Color32(0xFD,0xF3,0xDA,255), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(t.rectTransform, 0, 0, bw, 28);
            var o = t.gameObject.AddComponent<Outline>();          // 铜面偏亮，字要描边
            o.effectColor = new Color32(0x2E,0x1C,0x04,255);
            o.effectDistance = new Vector2(1.2f, 1.2f);
        }

        Canvas.ForceUpdateCanvases();
        cam.Render();
        var shot = new Texture2D(rtx.width, rtx.height, TextureFormat.RGB24, false);
        RenderTexture.active = rtx;
        shot.ReadPixels(new Rect(0, 0, rtx.width, rtx.height), 0, 0);
        shot.Apply();
        RenderTexture.active = null;
        Directory.CreateDirectory("Render");
        File.WriteAllBytes("Render/cityview.png", shot.EncodeToPNG());
        Debug.Log("渲染完成 → Render/cityview.png  " + rtx.width + "x" + rtx.height);
        EditorApplication.Exit(0);
    }

    static Sprite SolidFrame()
    {
        var t = new Texture2D(24, 24, TextureFormat.RGBA32, false);
        for (int yy = 0; yy < 24; yy++)
            for (int xx = 0; xx < 24; xx++)
            {
                bool edge = xx < 2 || yy < 2 || xx > 21 || yy > 21;
                t.SetPixel(xx, yy, edge ? new Color32(0x6B,0x55,0x2D,255) : new Color32(0xC8,0xA4,0x5C,20));
            }
        t.Apply();
        return Sprite.Create(t, new Rect(0,0,24,24), new Vector2(.5f,.5f), 100, 0,
                             SpriteMeshType.FullRect, new Vector4(8,8,8,8));
    }

    /// 压暗四周 + 按蒙版形状挖洞 + 沿轮廓的金边，一次算成贴图
    static Texture2D BuildHighlight(Texture2D mask, int[] rgb)
    {
        int w = mask.width, h = mask.height;
        var src = mask.GetPixels32();
        var sil = new bool[w * h];
        for (int i = 0; i < src.Length; i++)
            sil[i] = src[i].r == rgb[0] && src[i].g == rgb[1] && src[i].b == rgb[2];

        var outp = new Color32[w * h];
        var dim = new Color32(8, 6, 4, 122);                 // 0.48 透明度的暗色
        var gold = new Color32(255, 236, 170, 255);
        const int R = 5;
        for (int yy = 0; yy < h; yy++)
            for (int xx = 0; xx < w; xx++)
            {
                int i = yy * w + xx;
                if (sil[i]) { outp[i] = new Color32(0,0,0,0); continue; }
                bool rim = false;
                for (int dy = -R; dy <= R && !rim; dy++)
                    for (int dx = -R; dx <= R; dx++)
                    {
                        int nx = xx + dx, ny = yy + dy;
                        if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
                        if (sil[ny * w + nx]) { rim = true; break; }
                    }
                outp[i] = rim ? gold : dim;
            }
        var t = new Texture2D(w, h, TextureFormat.RGBA32, false);
        t.SetPixels32(outp); t.Apply();
        return t;
    }

    static string Wan(int v) => v >= 10000 ? (v / 10000f).ToString("0.0") + "万" : v.ToString();
    static string RulerName(string k) => k switch {
        "han" => "汉室·献帝", "caocao" => "曹操", "yuanshao" => "袁绍", "sunjian" => "孙坚",
        "lvbu" => "吕布", "liuyan" => "刘焉", "liubiao" => "刘表", "player" => "我军", _ => "无主" };


    [System.Serializable] public class B { public string key, name, func; public int[] mask, box; }
    [System.Serializable] public class CityMeta {
        public int[] size; public List<B> buildings;
        public B Find(string k) => buildings.Find(x => x.key == k);
    }
    [System.Serializable] public class Stats {
        public string name, tierName, owner, ruler, governor;
        public int 治安, 黄金, 粮草, 农业, 商业, 人口, 治水, 兵力, 武将, 令牌;
    }
}
