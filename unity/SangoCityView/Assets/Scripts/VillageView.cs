// 战场村庄：原版 al()/an() 外层的 aj()（地形 1 武器村，屏 17 战斗版）和 ak()（地形 3 金粮村，屏 16 战斗版）。
// 钱粮都是这一仗的战场金粮（BattleField.Supply[0] / [1]，原版 bc / bd），战后写回城库。
// 结果那句话照原版 W 10~14：退出村庄! / 武器购买成功! / 金子不足,无法购买兵器! / 阁下无能力购买此兵器! / 购买成功!
// 修掉的原版 bug：名品的资格判定拿的是「本场最近一次交战的我方武将」b.ax（开战时是 0 号，谁都不够格），
// 这里改看进村买东西的那员。
using System.IO;
using UnityEngine;
using static UIKit;

public class VillageView : SheetView
{
    public System.Action<string> OnClosed;          // 那句结果话，交回战棋层写底栏
    public System.Action OnRareSold;                // 名品卖出：z[战场城] = 1

    int[] sup; Unit who; int tier, shelf;

    public VillageView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("village", parent, w, h, panelSp, btnSp) { }

    void End(string msg, bool anim, string cg = null)
    {
        if (anim) Say("村庄", msg, false, cg, who.O, () => { Close(); OnClosed?.Invoke(msg); });
        else { Close(); OnClosed?.Invoke(msg); }
    }

    // ── 武器村：屏 17 ────────────────────────────────────────────────
    public void OpenShop(Unit u, int zTier, int[] supply)
    {
        who = u; tier = zTier; sup = supply; shelf = 0;
        if (tier < 0) { End("退出村庄!", false); return; }
        DrawShop();
    }
    void DrawShop()
    {
        const float ROW = 60f;
        var box = Sheet("武器　请选择购买的兵器", 50 + 30 + 44 + 4 * ROW + 14 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var cur = Label(box, who.O.Name + "　现用 " + BattleRules.WeaponOf(who.O) + " / " + BattleRules.ArmorOf(who.O)
                             + "　战场金 " + sup[0], 12, Rust, TextAnchor.MiddleCenter);
        Place(cur.rectTransform, 0, 42, w, 20);
        float tw = (w - 52 - 3 * 6) / 4;
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            Btn(box, (i == shelf ? "◆" : "") + Market.ShelfName[i], 26 + i * (tw + 6), 68, tw, 36, () => { shelf = k; DrawShop(); }, 14);
        }
        var items = Market.Shelf(tier, shelf);
        for (int i = 0; i < items.Length; i++)
        {
            int it = items[i];
            ItemRow(box, 22, 114 + i * ROW, w - 44, ROW - 4, it, who.O, sup[0], i, () => Buy(it));      // 图标 · 名 · 属性 · 价
        }
        Btn(box, "退出村庄", (w - 136) / 2, 114 + 4 * ROW + 10, 136, BTN_H, () => End("退出村庄!", false));
    }
    void Buy(int item)
    {
        if (!Market.CanWield(who.O, item)) { End("阁下无能力购买此兵器!", true); return; }       // 先资格
        if (sup[0] < Market.Price[item]) { End("金子不足,无法购买兵器!", true); return; }         // 后金钱
        sup[0] -= Market.Price[item];
        if (item >= 24) who.O.SubLv = item; else who.O.ArmLv = item;                          // 无条件换上
        if (Market.Rare(item)) OnRareSold?.Invoke();
        End("武器购买成功!", true, Path.Combine("marketcg", "sc_1"));
    }

    // ── 金粮村：屏 16 ────────────────────────────────────────────────
    bool buyFood; int amount;
    public void OpenExchange(Unit u, int[] supply)
    {
        who = u; sup = supply; buyFood = true; amount = 0;
        DrawExchange();
    }
    int Cap() => buyFood
        ? (sup[0] * 4 / 3 + sup[1] > 9999 ? (9999 - sup[1]) * 3 / 4 : sup[0])
        : (sup[1] * 3 / 4 + sup[0] > 9999 ? (9999 - sup[0]) * 4 / 3 : sup[1]);
    void DrawExchange()
    {
        int cap = Mathf.Max(0, Cap());
        amount = Mathf.Clamp(amount, 0, cap);
        var box = Sheet("金粮　" + (buyFood ? "购买粮食" : "购买黄金"), 92 + StepperH(5) + 34 + 50 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var hdr = Label(box, "战场金 " + sup[0] + "　粮 " + sup[1] + "　（100米 可易 75金）", 13, Rust, TextAnchor.MiddleCenter);
        Place(hdr.rectTransform, 0, 40, w, 20);
        Btn(box, (buyFood ? "◆" : "") + "购买粮食", w / 2 - 124, 64, 116, 32, () => { buyFood = true; amount = 0; DrawExchange(); }, 14);
        Btn(box, (!buyFood ? "◆" : "") + "购买黄金", w / 2 + 8, 64, 116, 32, () => { buyFood = false; amount = 0; DrawExchange(); }, 14);
        Stepper(box, 102, buyFood ? "出金" : "出粮", amount, new[] { -100, -10, 10, 100, int.MaxValue },
                d => { amount = d == int.MaxValue ? cap : amount + d; DrawExchange(); });
        var res = Label(box, "= " + (buyFood ? amount * 4 / 3 : 3 * amount / 4), 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(res.rectTransform, 0, 102 + StepperH(5), w, 22);
        float y = 102 + StepperH(5) + 34;
        Btn(box, "成交", w / 2 - 104, y, 96, BTN_H, () => {
            if (amount <= 0) { Say("金粮", "购买数量不能为0!", false, null, who.O, DrawExchange); return; }
            if (buyFood) { sup[0] -= amount; sup[1] += amount * 4 / 3; }
            else         { sup[1] -= amount; sup[0] += 3 * amount / 4; }
            End("购买成功!", true, Path.Combine("marketcg", "sc_0"));
        });
        Btn(box, "退出村庄", w / 2 + 8, y, 96, BTN_H, () => End("退出村庄!", false));
    }
}
