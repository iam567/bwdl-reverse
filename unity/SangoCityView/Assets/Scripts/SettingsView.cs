// 主界面「游戏设置」（顶了原版主菜单第四项「游戏帮助」的位置）：语言 + 难度 + 字体 + 音乐 + 音效 + 游戏帮助。
// 难度是家规，见 Difficulty.cs / HOUSE_RULES。
// 难度在开新局（开创霸业）时才生效；读档沿用存档里的城池数值。
using UnityEngine;
using static UIKit;

public class SettingsView : SheetView
{
    public System.Action OnFontChanged;          // 换了字体 / 语言：Game 整个重建界面

    public SettingsView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("settings", parent, w, h, panelSp, btnSp) { }

    public void Open()
    {
        int n = Difficulty.Names.Length;
        const float ROW = BTN_H + 12;
        const float FROW = BTN_H + 10;
        int fr = (FontChoice.Names.Length + 1) / 2;                          // 字体键排几行（繁日韩只有两套 → 一行）
        const float LANG = 26 + BTN_H + 14;
        var box = Sheet("游戏设置", 56 + LANG + 26 + n * ROW + 8 + 26 + fr * FROW + 30 + FROW + 30 + FROW + 22 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        // 语言：一个键写着当前语言（用该语言自己的写法），点开挑
        var lh = Label(box, "语言 · Language", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(lh.rectTransform, 0, 52, w, 22);
        Btn(box, Loc.Names[Loc.Level], (w - 220) / 2, 52 + 26, 220, BTN_H, Languages);
        RawLast(box);
        float top = 52 + LANG;
        var hd = Label(box, "难度（开新局时生效）", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(hd.rectTransform, 0, top, w, 22);
        int cur = Difficulty.Level;
        for (int i = 0; i < n; i++)
        {
            int k = i;
            float y = top + 28 + i * ROW;
            Btn(box, (k == cur ? "◆ " : "") + Difficulty.Names[k], (w - 220) / 2, y, 220, BTN_H, () => { Difficulty.Level = k; Open(); });
        }
        // 字体：四个键排两行，每个键的字就用那套字体写，一眼看出长什么样
        float fy = top + 28 + n * ROW + 8;
        var fh = Label(box, "字体", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(fh.rectTransform, 0, fy, w, 22);
        int fc = FontChoice.Level; float fw = 116;
        for (int i = 0; i < FontChoice.Names.Length; i++)
        {
            int k = i;
            Btn(box, (k == fc ? "◆ " : "") + FontChoice.Names[k], w / 2 + (i % 2 == 0 ? -fw - 8 : 8), fy + 28 + (i / 2) * FROW, fw, BTN_H,
                () => { if (k != FontChoice.Level) { FontChoice.Level = k; OnFontChanged?.Invoke(); } }, 15);
            var lbl = box.GetChild(box.childCount - 1).GetComponent<UnityEngine.UI.Text>();       // Btn 最后加的就是它的字
            if (lbl != null && FontChoice.Regular(k) != null) lbl.font = FontChoice.Regular(k);
        }
        // 音乐：关 / 低 / 中 / 高，一排四个
        float my = fy + 28 + fr * FROW + 4;
        var mh = Label(box, "音乐", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(mh.rectTransform, 0, my, w, 22);
        int ml = Music.Level; float mw = (fw * 2 + 16 - 3 * 8) / 4;
        for (int i = 0; i < Music.Levels.Length; i++)
        {
            int k = i;
            Btn(box, (k == ml ? "◆" : "") + Music.Levels[k], w / 2 - fw - 8 + i * (mw + 8), my + 28, mw, BTN_H, () => { Music.Level = k; Open(); }, 15);
        }
        // 音效：同样四档
        float sy = my + 28 + FROW + 4;
        var sh = Label(box, "音效", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(sh.rectTransform, 0, sy, w, 22);
        int sl = Sfx.Level;
        for (int i = 0; i < Sfx.Levels.Length; i++)
        {
            int k = i;
            Btn(box, (k == sl ? "◆" : "") + Sfx.Levels[k], w / 2 - fw - 8 + i * (mw + 8), sy + 28, mw, BTN_H, () => { Sfx.Level = k; Open(); }, 15);
        }
        float by = sy + 28 + FROW + 14;
        Btn(box, "游戏帮助", w / 2 - 124, by, 116, BTN_H, Help);
        Btn(box, "返回", w / 2 + 8, by, 116, BTN_H, Close);
    }

    public void HelpForTest() => Help();

    /// 键上的字不翻译（语言名就用它自己的写法）
    static void RawLast(RectTransform box)
    {
        var t = box.GetChild(box.childCount - 1).GetComponent<LText>();
        if (t != null) { t.Raw = true; t.text = Loc.Names[Loc.Level]; t.font = FontChoice.ForLang(Loc.Level) ?? t.font; }
    }

    /// 挑语言：五个键，每个写该语言自己的名字；换了就整个界面重建
    void Languages()
    {
        int n = Loc.Names.Length;
        var box = Sheet("语言 · Language", 56 + n * (BTN_H + 12) + 8 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        for (int i = 0; i < n; i++)
        {
            int k = i;
            Btn(box, "", (w - 220) / 2, 56 + i * (BTN_H + 12), 220, BTN_H, () =>
            {
                if (k == Loc.Level) { Open(); return; }
                Loc.Level = k; TitleView.ResetCover(); OnFontChanged?.Invoke();
            });
            var t = box.GetChild(box.childCount - 1).GetComponent<LText>();
            if (t != null) { t.Raw = true; t.text = (k == Loc.Level ? "◆ " : "") + Loc.Names[k]; t.font = FontChoice.ForLang(k) ?? t.font; }
        }
        Btn(box, "返回", (w - 136) / 2, 56 + n * (BTN_H + 12) + 8, 136, BTN_H, Open);
    }

    /// 致谢：开源字体（SIL OFL 1.1，许可原文随包在 Art/licenses/）、Kevin MacLeod 的音乐（CC BY 4.0 要求署名）、Kenney 音效（CC0）
    public void Credits()
    {
        float h = Mathf.Min(refH - 60, Loc.Source ? 560 : 640);
        var box = Sheet("致　谢", h);
        float w = box.sizeDelta.x;
        var l = Label(box,
            "霸业江山　Made by Liang\n" +
            "玩法源自 J2ME 游戏《三国志霸王的大陆》（JustGame Studio），向原作致敬。\n\n" +
            "【字体】SIL Open Font License 1.1\n" +
            "思源宋体 / 思源黑体（Noto Serif / Sans SC · TC · JP · KR）© Adobe，Reserved Font Name 'Source'\n" +
            "楷体取自霞鹜文楷 LXGW WenKai © LXGW（已裁剪改名为 BYJS Kai）\n" +
            "站酷小薇 ZCOOL XiaoWei © The ZCOOL XiaoWei Project Authors\n\n" +
            "【音乐】\n" +
            "古地图卷、铁骑冲锋：Suno 创作\n" +
            "Heroic Age、Five Armies、Heartbreaking、Sad Trio\n" +
            "Kevin MacLeod (incompetech.com)\n" +
            "Licensed under Creative Commons: By Attribution 4.0\n" +
            "http://creativecommons.org/licenses/by/4.0/\n\n" +
            "【音效】Kenney (kenney.nl)，CC0；其余为程序合成\n" +
            "【美术】AI 生成",
            12, Ink, TextAnchor.UpperLeft);
        l.horizontalOverflow = HorizontalWrapMode.Wrap; l.lineSpacing = 1.15f;
        Place(l.rectTransform, 24, 50, w - 48, h - 50 - BTN_H - 30); Cjk(l);
        Btn(box, "返回", (w - 136) / 2, h - BTN_H - 20, 136, BTN_H, Close);
    }
    /// 原版「关于游戏」（c.java 6866），按键说明换成触屏的
    void Help()
    {
        float h = Mathf.Min(refH - 60, Loc.Source ? 460 : 660);                  // 译文比中文长，框加高
        var box = Sheet("游戏帮助", h);
        float w = box.sizeDelta.x;
        var l = Label(box, "《霸业江山》采用了指令牌游戏系统，君主拥有城数越多，每月可以发布指令的令牌越多。在只有一座城的情况下每月只可以做三件事。" +
            "搜索武将主要取决于君主的德行，德行值越高越容易让武将加入旗下。游戏中还首创了以人望值决定行动力的概念。" +
            "外交策略共有三项，分别为同盟、离间和招揽。同盟是与其它国家签定互不侵犯条约，离间用来降低敌方武将的忠诚度，招揽即将对方武将拉至麾下。" +
            "游戏最大的亮点还是在于其战争系统，阵型的设定和重大作用让战争过程充满策略性。\n\n" +
            "操作：大地图上拖动、双指缩放；点城池选中，再点一次进城。城内点建筑弹出指令，右上角山河舆图徽章回大地图；大地图右上角的齿轮徽章是系统设置（存档、读档、结束本月）。",
            Loc.Source ? 14 : 13, Ink, TextAnchor.UpperLeft);
        l.horizontalOverflow = HorizontalWrapMode.Wrap; l.lineSpacing = Loc.Source ? 1.2f : 1.1f;
        Place(l.rectTransform, 26, 50, w - 52, h - 50 - BTN_H - 36);
        Cjk(l);                                                                   // 句号逗号不上行首
        Btn(box, "返回", (w - 136) / 2, h - BTN_H - 22, 136, BTN_H, Open);
    }
}
