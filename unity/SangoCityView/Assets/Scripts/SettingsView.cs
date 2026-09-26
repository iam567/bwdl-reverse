// 主界面「游戏设置」（顶了原版主菜单第四项「游戏帮助」的位置）：难度 + 字体 + 音乐 + 音效 + 游戏帮助。
// 难度是家规，见 Difficulty.cs / HOUSE_RULES。
// 难度在开新局（开创霸业）时才生效；读档沿用存档里的城池数值。
using UnityEngine;
using static UIKit;

public class SettingsView : SheetView
{
    public System.Action OnFontChanged;          // 换了字体：Game 整个重建界面

    public SettingsView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("settings", parent, w, h, panelSp, btnSp) { }

    public void Open()
    {
        int n = Difficulty.Names.Length;
        const float ROW = BTN_H + 12;
        const float FROW = BTN_H + 10;
        var box = Sheet("游戏设置", 56 + 26 + n * ROW + 8 + 26 + 2 * FROW + 30 + FROW + 30 + FROW + 22 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        var hd = Label(box, "难度（开新局时生效）", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(hd.rectTransform, 0, 52, w, 22);
        int cur = Difficulty.Level;
        for (int i = 0; i < n; i++)
        {
            int k = i;
            float y = 80 + i * ROW;
            Btn(box, (k == cur ? "◆ " : "") + Difficulty.Names[k], (w - 220) / 2, y, 220, BTN_H, () => { Difficulty.Level = k; Open(); });
        }
        // 字体：四个键排两行，每个键的字就用那套字体写，一眼看出长什么样
        float fy = 80 + n * ROW + 8;
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
        float my = fy + 28 + 2 * FROW + 4;
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
    /// 原版「关于游戏」（c.java 6866），按键说明换成触屏的
    void Help()
    {
        float h = Mathf.Min(refH - 60, 460);
        var box = Sheet("游戏帮助", h);
        float w = box.sizeDelta.x;
        var l = Label(box, "《霸业江山》采用了指令牌游戏系统，君主拥有城数越多，每月可以发布指令的令牌越多。在只有一座城的情况下每月只可以做三件事。" +
            "搜索武将主要取决于君主的德行，德行值越高越容易让武将加入旗下。游戏中还首创了以人望值决定行动力的概念。" +
            "外交策略共有三项，分别为同盟、离间和招揽。同盟是与其它国家签定互不侵犯条约，离间用来降低敌方武将的忠诚度，招揽即将对方武将拉至麾下。" +
            "游戏最大的亮点还是在于其战争系统，阵型的设定和重大作用让战争过程充满策略性。\n\n" +
            "操作：大地图上拖动、双指缩放；点城池选中，再点一次进城。城内点建筑弹出指令，右上角山河舆图徽章回大地图；大地图右上角的齿轮徽章是系统设置（存档、读档、结束本月）。",
            14, Ink, TextAnchor.UpperLeft);
        l.horizontalOverflow = HorizontalWrapMode.Wrap; l.lineSpacing = 1.2f;
        Place(l.rectTransform, 26, 50, w - 52, h - 50 - BTN_H - 36);
        Cjk(l);                                                                   // 句号逗号不上行首
        Btn(box, "返回", (w - 136) / 2, h - BTN_H - 22, 136, BTN_H, Open);
    }
}
