// 设置：原版底栏「系统」格的 存储 / 读取 / 环境 / 退出（d.b[5]）。
// 「退出」在手机上改成回主界面；「环境」（音量等）等有了声音再接。
// 三个档位，空档写「尚无存档」，照原版 c.java:443。
using UnityEngine;

public class SystemView : SheetView
{
    public System.Action<int> OnSave, OnLoad;   // 档位 0..2
    public System.Action OnTitle, OnEndTurn;
    public bool InGame = true;                  // 主界面上打开时只给「读取」

    public SystemView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("system", parent, w, h, panelSp, btnSp) { }

    const float BW = 220f, GAP = 12f;

    public void Open()
    {
        if (!InGame) { Slots(false); return; }
        // 「结束本月」：原版没有，令没用完只能一道道用掉；触屏上补一个，余令作废（HOUSE_RULES）
        string[] names = { "存储进度", "读取进度", "结束本月", "返回主界面", "继续游戏" };
        var box = Sheet("系　统", 56 + names.Length * (BTN_H + GAP) - GAP + 20 + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        for (int i = 0; i < names.Length; i++)
        {
            int k = i;
            Btn(box, names[i], (w - BW) / 2, 58 + i * (BTN_H + GAP), BW, BTN_H, () => {
                switch (k)
                {
                    case 0: Slots(true); break;
                    case 1: Slots(false); break;
                    case 2: Ask("结束本月？\n没用完的令就作废了", () => { Close(); OnEndTurn?.Invoke(); }, Open); break;
                    case 3: ConfirmTitle(); break;
                    default: Close(); break;
                }
            });
        }
    }

    /// 三个档位。存：空档直接写，有档先问一句；读：空档点了没反应
    void Slots(bool save)
    {
        var box = Sheet(save ? "存储进度" : "读取进度", 56 + SaveGame.Slots * (BTN_H + GAP) + BTN_H + 12 + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        float sw = Mathf.Min(w - 60, 300);
        for (int i = 0; i < SaveGame.Slots; i++)
        {
            int k = i;
            string label = "第" + "一二三"[i] + "档　" + SaveGame.Label(i);
            Btn(box, label, (w - sw) / 2, 58 + i * (BTN_H + GAP), sw, BTN_H, () => {
                if (save)
                {
                    if (SaveGame.Has(k)) Ask("覆盖第" + "一二三"[k] + "档？\n" + SaveGame.Label(k), () => DoSave(k), () => Slots(true));
                    else DoSave(k);
                }
                else if (SaveGame.Has(k)) { Close(); OnLoad?.Invoke(k); }
            });
        }
        Btn(box, "返回", (w - 136) / 2, 58 + SaveGame.Slots * (BTN_H + GAP) + 4, 136, BTN_H,
            () => { if (InGame) Open(); else Close(); });
    }

    void DoSave(int k)
    {
        OnSave?.Invoke(k);
        Say("存储进度", "已存入第" + "一二三"[k] + "档\n" + SaveGame.Label(k), false, null);
    }

    void ConfirmTitle() => Ask("返回主界面？\n未存储的进度将会丢失", () => { Close(); OnTitle?.Invoke(); }, Open);

    void Ask(string msg, System.Action yes, System.Action no)
    {
        var box = Sheet("确　认", 118 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var l = UIKit.Label(box, msg, 16, Ink, TextAnchor.MiddleCenter);
        l.horizontalOverflow = HorizontalWrapMode.Wrap;
        UIKit.Place(l.rectTransform, 24, 48, w - 48, 56); UIKit.Cjk(l);
        Btn(box, "是", w / 2 - 104, 118, 96, BTN_H, () => yes());
        Btn(box, "否", w / 2 + 8, 118, 96, BTN_H, () => no());
    }
}
