// 字体（设置里选，存 PlayerPrefs）：四套开源中文字体，都是 SIL OFL，按 GB2312 + 游戏用字裁过（Assets/Fonts/）。
//   思源宋体（默认）/ 思源黑体 / 霞鹜文楷 / 站酷小薇。
// 粗体用字体自带的粗字重（UIKit.Label 里换字体、不再让 Unity 假加粗——假加粗会把中文描糊）；
// 站酷小薇只有一个字重，要粗的地方也用常规。原来的 Arial Unicode MS 不能随 App 分发，已移出工程（FontsRetired/）。
using UnityEngine;

public static class FontChoice
{
    public static readonly string[] Names = { "思源宋体", "思源黑体", "霞鹜文楷", "站酷小薇" };
    const string KEY = "font";
    static Font[] all;                                   // Builder.FontFiles 的顺序：每套 [常规, 粗]，小薇只有一个

    static int forced = -2;                              // 截图用：-font <0..3>，只管这一次运行，不写设置
    public static int Level
    {
        get
        {
            if (forced == -2) { var av = System.Environment.GetCommandLineArgs(); int i = System.Array.IndexOf(av, "-font"); forced = i >= 0 ? int.Parse(av[i + 1]) : -1; }
            if (forced >= 0) return Mathf.Clamp(forced, 0, Names.Length - 1);
            try { return Mathf.Clamp(PlayerPrefs.GetInt(KEY, 0), 0, Names.Length - 1); } catch { return 0; }
        }
        set { PlayerPrefs.SetInt(KEY, Mathf.Clamp(value, 0, Names.Length - 1)); PlayerPrefs.Save(); Apply(); }
    }

    public static void Init(Font[] fonts) { all = fonts; Apply(); }

    public static Font Regular(int k) => all == null ? null : all[Mathf.Min(k * 2, all.Length - 1)];
    public static Font Bold(int k) => all == null || k >= 3 ? null : all[k * 2 + 1];

    static void Apply()
    {
        if (all == null || all.Length == 0) return;
        UIKit.UIFont = Regular(Level);
        UIKit.UIFontBold = Bold(Level);
    }
}
