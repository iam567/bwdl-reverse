// 主界面：封面（平台 title_cover2_0..7（以 title_cover_0..7 为参考改标题）→ Art/title/cover_*.jpg，标题「霸业江山」画在图里，每次启动随机一张）+ 五项菜单。
// 没有封面图时退回旧的 title.jpg + 文字标题。
// 原版开机是 JustGame 的标题画面接「新游戏 / 继续」；图还没到的时候退回城内那张木板底。
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class TitleView : SheetView
{
    public System.Action OnNew, OnLoadMenu, OnCustom, OnSettings, OnAlbum;

    /// 封面：Art/title/cover_0..7.jpg，每次启动随机挑一张，这一次运行里固定（回主界面不换）
    static int coverPick = -1;
    public static Texture2D Cover()
    {
        var dir = System.IO.Path.Combine(ArtDir, "title");
        if (!System.IO.Directory.Exists(dir)) return null;
        var files = System.IO.Directory.GetFiles(dir, "cover_*.jpg");
        if (files.Length == 0) return null;
        System.Array.Sort(files);
        if (coverPick < 0)
        {
            var av = System.Environment.GetCommandLineArgs(); int ci = System.Array.IndexOf(av, "-cover");   // 截图用：-cover <0..7>
            coverPick = ci >= 0 ? int.Parse(av[ci + 1]) : new System.Random().Next(files.Length);
        }
        coverPick = Mathf.Clamp(coverPick, 0, files.Length - 1);
        return TryLoad(System.IO.Path.Combine("title", System.IO.Path.GetFileName(files[coverPick])));
    }

    public TitleView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("title", parent, w, h, panelSp, btnSp)
    {
        var bg = Node("bg", Root).gameObject.AddComponent<RawImage>();
        var tex = Cover();
        bool baked = tex != null;                                   // 新封面：标题「霸业江山」画在图里
        if (!baked) tex = TryLoad("title.jpg") ?? Load("bg.jpg");
        bg.texture = tex;
        // 铺满屏幕、按比例裁边（cover），竖图在瘦长屏上不留黑边
        float s = Mathf.Max(w / tex.width, h / tex.height);
        Place(bg.rectTransform, (w - tex.width * s) / 2, (h - tex.height * s) / 2, tex.width * s, tex.height * s);

        if (!baked)
        {
            var shade = Node("shade", Root).gameObject.AddComponent<Image>();      // 压暗一层，字和按钮才站得住
            Fill((RectTransform)shade.transform); shade.color = new Color(0, 0, 0, .28f); shade.raycastTarget = false;
            var t1 = Label(Root, "霸 业 江 山", 44, new Color32(0xF6,0xE3,0xB0,255), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(t1.rectTransform, 0, h * .14f, w, 60);
            var t2 = Label(Root, "", 24, new Color32(0xE8,0xC0,0x6A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(t2.rectTransform, 0, h * .14f + 62, w, 34);
            foreach (var t in new[] { t1, t2 })
            {
                var o = t.gameObject.AddComponent<Outline>();
                o.effectColor = new Color(0, 0, 0, .8f); o.effectDistance = new Vector2(2, -2);
            }
        }

        // 原版主菜单五项（屏 1）：开创霸业 / 再续征程 / 自建武将 / 游戏帮助 / 卸甲归田。
        // 第四项换成「游戏设置」（难度 + 游戏帮助都在里面，见 SettingsView）；第五项换成「天下霸图」武将卡收集册（AlbumView）
        const float BW = 200f, GAPY = 12f;
        string[] items = { "开创霸业", "再续征程", "自建武将", "游戏设置", "天下霸图" };
        System.Action[] acts = { () => OnNew?.Invoke(), () => OnLoadMenu?.Invoke(), () => OnCustom?.Invoke(), () => OnSettings?.Invoke(), () => OnAlbum?.Invoke() };
        float y = h - FOOT - 24 - items.Length * (BTN_H + GAPY);
        for (int i = 0; i < items.Length; i++)
        {
            int k = i;
            Btn(Root, items[i], (w - BW) / 2, y + i * (BTN_H + GAPY), BW, BTN_H, () => acts[k]());
        }
        Root.gameObject.SetActive(true);
    }


}
