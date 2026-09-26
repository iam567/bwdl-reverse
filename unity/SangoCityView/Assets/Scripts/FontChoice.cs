// 字体（设置里选，存 PlayerPrefs）：开源字体都是 SIL OFL，按用字裁过（Assets/Fonts/）。
//   简中 / 英文：思源宋体（默认）/ 思源黑体 / 霞鹜文楷 / 站酷小薇。
//   繁中 / 日文 / 韩文：各自那套字形的思源宋体 / 思源黑体（Noto Serif / Sans TC、JP、KR）——同一个汉字各地写法不同，不能混用。
// 粗体用字体自带的粗字重（UIKit.Label 里换字体、不再让 Unity 假加粗——假加粗会把中文描糊）；
// 站酷小薇只有一个字重，要粗的地方也用常规。原来的 Arial Unicode MS 不能随 App 分发，已移出工程（FontsRetired/）。
using UnityEngine;

public static class FontChoice
{
    static readonly string[] names4 = { "思源宋体", "思源黑体", "霞鹜文楷", "站酷小薇" };
    static readonly string[] names2 = { "思源宋体", "思源黑体" };
    /// 繁中 / 日 / 韩只有宋、黑两套
    static bool Full => Loc.Code == "zh-Hans" || Loc.Code == "en";
    public static string[] Names => Full ? names4 : names2;
    const string KEY = "font";
    static Font[] all;                                   // Builder.FontFiles 的顺序：简中四套（每套 [常规, 粗]，小薇只有一个），再是繁 / 日 / 韩各 [宋常规, 宋粗, 黑常规, 黑粗]

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

    /// 繁 / 日 / 韩那一组在 all 里的起点（7 = 简中四套占的位置）
    static int LangBase => Loc.Code == "zh-Hant" ? 7 : Loc.Code == "ja" ? 11 : 15;
    static Font At(int i) => all != null && i < all.Length ? all[i] : null;
    public static Font Regular(int k)
    {
        if (all == null) return null;
        if (Full) return all[Mathf.Min(k * 2, 6)];
        return At(LangBase + Mathf.Min(k, 1) * 2) ?? all[0];
    }
    public static Font Bold(int k)
    {
        if (all == null) return null;
        if (Full) return k >= 3 ? null : all[k * 2 + 1];
        return At(LangBase + Mathf.Min(k, 1) * 2 + 1);
    }

    /// 某种语言自己的字库（设置里语言键上写该语言的名字要用它的字形）
    public static Font ForLang(int lv)
    {
        int i = lv == 1 ? 8 : lv == 3 ? 12 : lv == 4 ? 16 : 1;          // 各组的粗体
        return At(i) ?? (all != null && all.Length > 0 ? all[0] : null);
    }

    /// 换了语言也要调一次：字库跟着换
    public static void Apply()
    {
        if (all == null || all.Length == 0) return;
        UIKit.UIFont = Regular(Level);
        UIKit.UIFontBold = Bold(Level);
    }
}
