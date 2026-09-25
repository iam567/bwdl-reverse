// 集市四条（新版拆成城内四座建筑）的界面流程，照原版步骤表走（规则在 Market.cs）：
//   常平仓·金粮  屏 16：买入粮草（出金）/ 卖出粮草（出粮）→「交易成功」→ 扣令
//   武库·武器    选将 → 屏 17：剑 / 刀 / 枪戟 / 防具 四架，各四样 →「成功购得武器」→ 扣令
//   郡学·学院    选将（智力 < 80）→「武将智力得到提高」→ 扣令
//   药庐·医馆    选将（体力没满）→「体力恢复了」→ 扣令
// 前置不过、钱不够、用不了那件名品：只弹一句话，不扣令。
using System.IO;
using UnityEngine;
using static UIKit;

public class MarketView : SheetView
{
    public static readonly string[] Commands = { "买入粮草", "卖出粮草", "购买武器", "提升智力", "治疗武将" };

    MapData.Stat stat;
    Market.Cmd cmd;

    public MarketView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("market", parent, w, h, panelSp, btnSp) { }

    string Title => Market.CmdName[(int)cmd];
    string Cg => Path.Combine("marketcg", "sc_" + (int)cmd);

    /// label 是城内浮窗里点的那一条（Commands 之一）
    /// 截图用：直接拿这城第 i 个武将进武器店第 sh 个货架
    public void ShopForTest(MapData.Stat s, int i, int sh) { stat = s; cmd = Market.Cmd.Arms; buyer = Personnel.Of(s.id).A[i]; shelf = sh; DrawShop(); }

    public void Begin(string label, MapData.Stat s)
    {
        stat = s;
        int k = System.Array.IndexOf(Commands, label);
        cmd = k <= 1 ? Market.Cmd.Grain : (Market.Cmd)(k - 1);
        if (Market.Blocked(cmd, s, out string why)) { Say(Title, why, false, null, Gov(s)); return; }
        var a = Personnel.Of(s.id).A;
        switch (cmd)
        {
            case Market.Cmd.Grain: buyFood = k == 0; amount = 0; DrawGrain(); break;
            case Market.Cmd.Arms:
                PickGrid("武器　为何人购置", a, o => "武" + o.Might + " " + BattleRules.WeaponOf(o),   // 卡片窄，只写兵器；防具进店再看
                         o => { buyer = o; shelf = 0; DrawShop(); }, Cancel);
                break;
            case Market.Cmd.Academy:
                PickGrid("学院　遣何人修习", Market.Students(s), o => "智" + o.Int + " 费" + Market.Tuition(o),
                         DoStudy, Cancel);
                break;
            case Market.Cmd.Clinic:
                PickGrid("医馆　为何人疗伤（50 金）", Market.Patients(s), o => "体力上限 " + o.HpMax,
                         DoHeal, Cancel);
                break;
        }
    }

    // ── 金粮：屏 16 ───────────────────────────────────────────────────
    bool buyFood; int amount;
    void DrawGrain()
    {
        int cap = buyFood ? Market.BuyCap(stat) : Market.SellCap(stat);
        amount = Mathf.Clamp(amount, 0, cap);
        int got = buyFood ? amount * 4 / 3 : 3 * amount / 4;
        var box = Sheet(buyFood ? "金粮　买入粮草" : "金粮　卖出粮草", 92 + StepperH(5) + 34 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var hdr = Label(box, "城内金子 " + stat.黄金 + "　粮食 " + stat.粮草, 13, Rust, TextAnchor.MiddleCenter);
        Place(hdr.rectTransform, 0, 40, w, 20);
        var rate = Label(box, buyFood ? "每 3 金买 4 粮" : "每 4 粮卖 3 金", 13, Ink, TextAnchor.MiddleCenter);
        Place(rate.rectTransform, 0, 62, w, 20);
        Stepper(box, 92, buyFood ? "出金" : "出粮", amount, new[] { -100, -10, 10, 100, int.MaxValue },
                d => { amount = d == int.MaxValue ? cap : amount + d; DrawGrain(); });
        var res = Label(box, (buyFood ? "可得粮 " : "可得金 ") + got, 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(res.rectTransform, 0, 92 + StepperH(5), w, 22);
        float y = 92 + StepperH(5) + 34;
        Btn(box, "成交", w / 2 - 104, y, 96, BTN_H, () => {
            if (amount <= 0) return;                             // 原版「…不能为0」：出 0 不让成交
            int g = Market.Trade(stat, buyFood, amount);
            Sfx.Play("coin");
            Say(Title, "交易成功\n" + (buyFood ? "出金 " + amount + "　得粮 " + g : "出粮 " + amount + "　得金 " + g),
                true, Cg, Gov(stat));                            // o(int) 8660：d.d[4][0]
        });
        Btn(box, "放弃", w / 2 + 8, y, 96, BTN_H, Cancel);
    }

    // ── 武器：屏 17 ───────────────────────────────────────────────────
    Officer buyer; int shelf;
    void DrawShop()
    {
        const float ROW = 60f;
        var box = Sheet("武器　" + buyer.Name, 50 + 30 + 44 + 4 * ROW + 14 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var cur = Label(box, "现用 " + BattleRules.WeaponOf(buyer) + " / " + BattleRules.ArmorOf(buyer)
                             + "　武 " + buyer.Might + " 德 " + buyer.Virtue + "　城中金子 " + stat.黄金, 12, Rust, TextAnchor.MiddleCenter);
        Place(cur.rectTransform, 0, 42, w, 20);
        float tw = (w - 52 - 3 * 6) / 4;                                 // 四个货架的页签
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            Btn(box, (i == shelf ? "◆" : "") + Market.ShelfName[i], 26 + i * (tw + 6), 68, tw, 36, () => { shelf = k; DrawShop(); }, 14);
        }
        var items = Market.Shelf(stat, shelf);
        for (int i = 0; i < items.Length; i++)
        {
            int it = items[i];
            ItemRow(box, 22, 114 + i * ROW, w - 44, ROW - 4, it, buyer, stat.黄金, i, () => DoBuy(it));   // 图标 · 名 · 属性 · 价
        }
        Btn(box, "放弃", (w - 136) / 2, 114 + 4 * ROW + 10, 136, BTN_H, Cancel);
    }

    void DoBuy(int item)
    {
        switch (Market.Purchase(stat, buyer, item))
        {
            case Market.Buy.CannotUse: Say(Title, "启禀主公,属下无法使用此武器", false, null, buyer); return;   // p(3)
            case Market.Buy.NoMoney:   Say(Title, "资财不足", false, null, Gov(stat)); return;                 // p(2)
            default:
                Sfx.Play("coin");
                Say(Title, "成功购得武器\n" + buyer.Name + " 换上了 " + BattleRules.EquipName[item] + "　金子 −" + Market.Price[item],
                    true, Cg, buyer);                                                                        // p(0)
                return;
        }
    }

    // ── 学院 / 医馆 ─────────────────────────────────────────────────
    void DoStudy(Officer o)
    {
        int fee = Market.Tuition(o);
        if (!Market.Study(stat, o, rng, out int gain)) { Say(Title, "资财不足", false, null, o); return; }   // bf() 7771
        Say(Title, "武将智力得到提高\n" + o.Name + " 智力 +" + gain + "（" + o.Int + "）　金子 −" + fee, true, Cg, o);   // d.d[4][3]
    }

    void DoHeal(Officer o)
    {
        int gain = Market.Heal(stat, o, rng);
        Say(Title, "体力恢复了\n" + o.Name + " 体力 +" + gain + "（" + o.Hp + " / " + o.HpMax + "）　金子 −" + Market.ClinicFee,
            true, Cg, o);                                                                                    // d.d[4][4]
    }
}
