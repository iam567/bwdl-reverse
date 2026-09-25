// 校场四条指令的界面流程，照原版步骤表走（规则在 Military.cs，战争的开战 / 善后在 War.cs）：
//   移动  选相邻己城（屏 4）→ 勾选武将（屏 7 多选）→ [全走：「是否放弃此城市？」]
//         → [太守也走了：「请任命新太守」] →「末将即刻出发!」→ 扣令
//   征兵  屏 8：先填征多少（进预备兵）→ 再给各将分兵 → 扣令
//   输送  选相邻己城 → 屏 9 填金 / 粮 / 宝 →「末将即刻出发!」→ 扣令
// 前置条件不过（没有能去的城、人太多）只弹一句话，不扣令。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using static UIKit;

public class MilitaryView : SheetView
{
    public System.Action<MapData.City> OnMovedTo;   // 全城都走了，玩家跟着去了目标城
    public System.Action<War.Sortie> OnWar;         // 战争：人马金粮都点齐了，交给 Game 去开战
    public System.Action<War.Sortie> OnOccupied;    // 战争打的是空城：不开战，直接进城（原版 au = av）
    public string Faction, Lord;                    // 我方势力 / 君主，Game 每次开指令前塞进来

    MapData map;
    MapData.City city; MapData.Stat stat;
    Military.Cmd cmd;

    public MilitaryView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("military", parent, w, h, panelSp, btnSp) { }

    string Title => Military.CmdName[(int)cmd];
    string Cg => Path.Combine("militarycg", "js_" + (int)cmd);
    MapData.Stat StatOf(MapData.City c) => map.stats.Find(x => x.id == c.id);

    public void Begin(Military.Cmd c, MapData m, MapData.City at)
    {
        cmd = c; map = m; city = at; stat = StatOf(at);
        switch (c)
        {
            case Military.Cmd.Move:   StartMove(); break;
            case Military.Cmd.Supply: StartSupply(); break;
            case Military.Cmd.Draft:  DraftAmount(Mathf.Min(100, Military.DraftCap(stat))); break;
            case Military.Cmd.War:    StartWar(); break;
        }
    }

    string CityNote(MapData.City c)
    {
        var s = StatOf(c);
        return "武将 " + Personnel.Of(c.id).A.Count + "　兵 " + s.兵力;
    }

    // ── 移动 ────────────────────────────────────────────────────────
    void StartMove()
    {
        var to = Military.OwnNeighbors(map, city);
        if (to.Count == 0) { Say(Title, "当前无可移动的城!", false, null, Gov(stat)); return; }   // Q(1) 8812
        PickCity("移动　往何处", to, CityNote, PickMovers, Cancel);
    }

    void PickMovers(MapData.City dest)
    {
        PickMany("移动　遣何人前往 " + dest.name, Personnel.Of(city.id).A, o => "兵 " + o.Troops, 0,
                 movers => DoMove(dest, movers), () => StartMove());
    }

    void DoMove(MapData.City dest, List<Officer> movers)
    {
        var ds = StatOf(dest);
        switch (Military.CheckMove(stat, ds, movers))
        {
            case Military.MoveCheck.TooMany:
                Say(Title, "移动武将数过多!", false, null, movers[0]); return;                   // k() 8573
            case Military.MoveCheck.EmptiesCity:
                Ask("是否放弃此城市？", () => {                                                  // c.java 1329
                    Military.Move(map, stat, ds, movers);
                    Military.Abandon(map, stat);
                    Done(movers[0], dest, true);
                }, Cancel);
                return;
            case Military.MoveCheck.NeedGovernor:
                Military.Move(map, stat, ds, movers);
                // 原版 bQ=2 → 屏 7「请任命新太守」，名单是留下来的人；这一步不能作罢
                PickGrid("请任命新太守", Personnel.Of(city.id).A, o => "德 " + o.Virtue + "　忠 " + o.Loyal,
                         g => { Personnel.Appoint(stat, g); Done(movers[0], dest, false); }, null);
                return;
            default:
                Military.Move(map, stat, ds, movers);
                Done(movers[0], dest, false);
                return;
        }
    }

    void Done(Officer lead, MapData.City dest, bool emptied)
    {
        string msg = "末将即刻出发!\n" + lead.Name + " 等已抵达 " + dest.name;           // d.d[0][0]
        if (emptied) msg += "，" + city.name + " 已弃守";
        var from = city;
        Say(Title, msg, true, Cg, lead);
        if (emptied) OnMovedTo?.Invoke(dest);                                            // j(int)：au = av
    }

    // ── 输送 ────────────────────────────────────────────────────────
    MapData.City dest;
    int[] amt = new int[3];
    void StartSupply()
    {
        var to = Military.OwnNeighbors(map, city);
        if (to.Count == 0) { Say(Title, "当前无可输送的城!", false, null, Gov(stat)); return; }  // R(12) 8826
        PickCity("输送　送往何处", to, c => { var s = StatOf(c); return "金 " + s.黄金 + "　粮 " + s.粮草; },
                 c => { dest = c; amt = new int[3]; DrawSupply(); }, Cancel);
    }

    void DrawSupply()
    {
        var ds = StatOf(dest);
        int[] cap = Military.SupplyCap(stat, ds);
        string[] names = { "金子", "粮食", "宝物" };
        float rowH = StepperH(5), gemH = StepperH(3);
        var box = Sheet("输送　" + city.name + " → " + dest.name, 66 + 2 * rowH + gemH + 12 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var hdr = Label(box, "城内金子:" + stat.黄金 + "　粮食:" + stat.粮草 + "　宝:" + stat.宝物, 13, Rust, TextAnchor.MiddleCenter);
        Place(hdr.rectTransform, 0, 40, w, 20);                                          // 屏 9 顶上那行，原话
        for (int i = 0; i < 3; i++)
        {
            int k = i;
            int[] steps = k == 2 ? new[] { -1, 1, int.MaxValue } : new[] { -100, -10, 10, 100, int.MaxValue };
            Stepper(box, 66 + i * rowH, names[i], amt[i], steps, d => {
                amt[k] = d == int.MaxValue ? cap[k] : Mathf.Clamp(amt[k] + d, 0, cap[k]);
                DrawSupply();
            });
        }
        float y = 66 + 2 * rowH + gemH + 12;
        Btn(box, "输送", w / 2 - 104, y, 96, BTN_H, () => {
            Military.Supply(stat, ds, amt[0], amt[1], amt[2]);
            Say(Title, "末将即刻出发!\n送往 " + dest.name + "：金 " + amt[0] + "　粮 " + amt[1] + "　宝 " + amt[2],
                true, Cg, Gov(stat));                                                     // a(int,short[]) 8649
        });
        Btn(box, "放弃", w / 2 + 8, y, 96, BTN_H, Cancel);
    }

    // ── 征兵：屏 8 ───────────────────────────────────────────────────
    int draftN;
    void DraftAmount(int n)
    {
        int cap = Military.DraftCap(stat);
        draftN = Mathf.Clamp(n, 0, cap);
        var box = Sheet("征兵", 92 + StepperH(5) + 34 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var hint = Label(box, "征100兵 需 20金　　城中金子 " + stat.黄金 + "　人口 " + stat.人口, 13, Rust, TextAnchor.MiddleCenter);
        Place(hint.rectTransform, 0, 40, w, 20);
        var pool = Label(box, "现有预备兵 " + stat.预备兵 + "　最多可征 " + cap, 13, Ink, TextAnchor.MiddleCenter);
        Place(pool.rectTransform, 0, 62, w, 20);
        Stepper(box, 92, "征", draftN, new[] { -1000, -100, 100, 1000, int.MaxValue },
                d => DraftAmount(d == int.MaxValue ? cap : draftN + d));
        var cost = Label(box, "需金 " + (draftN + 4) / 5, 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(cost.rectTransform, 0, 92 + StepperH(5), w, 22);
        float y = 92 + StepperH(5) + 34;
        Btn(box, "征兵", w / 2 - 104, y, 96, BTN_H, () => {
            Military.Draft(stat, draftN);                       // b(short,byte)：先进预备兵
            StartAssign();
        });
        Btn(box, "放弃", w / 2 + 8, y, 96, BTN_H, Cancel);      // 这一步作罢不扣令（m(1) → aK）
    }

    Dictionary<Officer, int> give;
    int poolLeft;
    void StartAssign()
    {
        give = new Dictionary<Officer, int>();
        foreach (var o in Personnel.Of(city.id).A) give[o] = o.Troops;
        poolLeft = stat.预备兵;
        DrawAssign();
    }

    /// 第二步：给各将分兵，每人最多 1000，从预备兵里扣（c.java 5650 那一段的上下键）
    void DrawAssign()
    {
        var a = Personnel.Of(city.id).A;
        const float ROW = 42f;
        var box = Sheet("征兵　分派兵力", 56 + 26 + a.Count * ROW + 10 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var pool = Label(box, "预备兵 " + poolLeft, 15, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(pool.rectTransform, 0, 40, w, 22);
        for (int i = 0; i < a.Count; i++)
        {
            var o = a[i];
            Stepper(box, 70 + i * ROW, o.Name, give[o], new[] { int.MinValue, -100, 100, int.MaxValue }, d => {
                int cur = give[o];
                int want = d == int.MaxValue ? Military.MaxTroopsPerOfficer
                         : d == int.MinValue ? 0 : cur + d;
                want = Mathf.Clamp(want, 0, Mathf.Min(Military.MaxTroopsPerOfficer, cur + poolLeft));
                poolLeft -= want - cur; give[o] = want;
                DrawAssign();
            });
        }
        float y = 70 + a.Count * ROW + 10;
        Btn(box, "分派完毕", w / 2 - 124, y, 116, BTN_H, () => {
            Military.Assign(stat, give, poolLeft);
            Say(Title, "征兵完毕\n本城兵力 " + stat.兵力 + "　预备兵 " + stat.预备兵, true, Cg, Gov(stat));
        });
        // 原版第二步按「返回」也走 m(0)：兵已经征了，这道令照扣，只是不改各将的兵
        Btn(box, "暂不分派", w / 2 + 8, y, 116, BTN_H, () => {
            Close(); OnDone?.Invoke("征兵");
        });
    }

    // ── 战争 ────────────────────────────────────────────────────────
    War.Sortie sortie;
    void StartWar()
    {
        var to = War.Targets(map, city);
        if (to.Count == 0) { Say(Title, "当前无可战争城市!", false, null, Gov(stat)); return; }   // af(byte) 9083
        PickCity("战争　欲攻打何城?", to, c => {
            var s = StatOf(c);
            return c.owner == "none" ? "空城" : s.ruler + "　将 " + Personnel.Of(c.id).A.Count + "　兵 " + s.兵力;
        }, PickArmy, Cancel);
    }

    /// 出征 / 主将：阵型战里攻防都是 武力 ×(1+兵器%)，武力高的最顶用；体力不到三成的上阵容易被单挑斩了
    static int WarTier(Officer o) => o.Hp * 10 < o.HpMax * 3 ? 0 : Tier(o.Might, 75, 90, 50);

    void PickArmy(MapData.City target)
    {
        var a = Personnel.Of(city.id).A;
        PickMany("战争　选择执行武将", a, o => "兵 " + o.Troops + "　体 " + o.Hp, 0, army => {
            army.Sort((x, y) => a.IndexOf(x).CompareTo(a.IndexOf(y)));              // 照城内顺序收（k() 8559）
            sortie = new War.Sortie { From = city, To = target, Army = army };
            if (army.Count == a.Count) Ask("是否放弃此城市？", () => Carry(0, 0), Cancel);
            else Carry(0, 0);
        }, () => StartWar(), rate: WarTier);
    }

    /// 屏 14：带多少金粮出征。不超过城里现有；可以带 0，但带 0 粮第一日末就得撤（原版如此）
    void Carry(int g, int f)
    {
        sortie.Gold = Mathf.Clamp(g, 0, stat.黄金); sortie.Food = Mathf.Clamp(f, 0, stat.粮草);
        float rowH = StepperH(5);
        var box = Sheet("战争　携带金粮", 66 + 2 * rowH + 40 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var hdr = Label(box, "城内金子:" + stat.黄金 + "　粮食: " + stat.粮草, 13, Rust, TextAnchor.MiddleCenter);
        Place(hdr.rectTransform, 0, 40, w, 20);
        int[] steps = { -100, -10, 10, 100, int.MaxValue };
        Stepper(box, 66, "金子", sortie.Gold, steps, d => Carry(d == int.MaxValue ? stat.黄金 : sortie.Gold + d, sortie.Food));
        Stepper(box, 66 + rowH, "粮食", sortie.Food, steps, d => Carry(sortie.Gold, d == int.MaxValue ? stat.粮草 : sortie.Food + d));
        var warn = Label(box, "每日耗粮约 " + ((Troops(sortie.Army) - 1) / 250 + 1) + "，粮尽即全军撤退", 12, Ink, TextAnchor.MiddleCenter);
        Place(warn.rectTransform, 0, 66 + 2 * rowH + 6, w, 20);
        float y = 66 + 2 * rowH + 40;
        Btn(box, "出征", w / 2 - 104, y, 96, BTN_H, Depart);
        Btn(box, "放弃", w / 2 + 8, y, 96, BTN_H, Cancel);            // 屏 14 选「否」→ aK()，不扣令
    }
    static int Troops(List<Officer> l) { int t = 0; foreach (var o in l) t += o.Troops; return t; }

    /// a(int,int,int) 8657：人从城里拿掉，从这一步起再也不能作罢（aI = false）
    void Depart()
    {
        bool govGoes = sortie.Army.Exists(o => o.Name == stat.governor);
        War.Depart(map, sortie);
        if (sortie.Abandoned) { Oath(); return; }                         // 全走：主将就是 az[0]，不给挑
        if (govGoes)
        {
            // 太守出征：先任命新太守（「吾誓与此城同命!」），旧太守自动当主将
            var old = sortie.Army.Find(o => o.Name == stat.governor);
            sortie.Army.Remove(old); sortie.Army.Insert(0, old);
            PickGrid("请任命新太守", Personnel.Of(city.id).A, o => "德 " + o.Virtue + "　忠 " + o.Loyal, g => {
                Personnel.Appoint(stat, g);
                Say(Title, "吾誓与此城同命!", false, null, g, Oath);                         // aQ() 7452
            }, null);
        }
        else
            PickGrid("请任命军队主将", sortie.Army, o => "武 " + o.Might + "　兵 " + o.Troops, c => {
                sortie.Army.Remove(c); sortie.Army.Insert(0, c);                          // aU() 7486：主将换到 az[0]
                Oath();
            }, null, rate: WarTier);
    }

    void Oath()
    {
        var cmd = sortie.Army[0];
        Say(Title, "誓死拿下敌军城池!\n" + cmd.Name + " 率 " + sortie.Army.Count + " 将、兵 " + Troops(sortie.Army)
            + " 出征 " + sortie.To.name, false, Cg, cmd, () => {                          // d.d[0][2]
            bool fight = War.Launch(map, sortie, Faction, Lord);                         // bk() 8186
            if (fight) { Close(); OnWar?.Invoke(sortie); return; }
            Say(Title, sortie.Army[0].Name + " 兵不血刃，占领空城 " + sortie.To.name + "!", true, null, sortie.Army[0]);
            OnOccupied?.Invoke(sortie);
        });
    }

    /// 战后战报：打完回城先看这一屏，点「知道了」接着走（扣令 / 选继位）
    public void Report(string title, List<string> lines, System.Action after)
    {
        const float LINE = 22f;
        int n = Mathf.Min(lines.Count, 14);
        var box = Sheet(title, 50 + n * LINE + 16 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        for (int i = 0; i < n; i++)
        {
            var l = Label(box, lines[i], i == 0 ? 16 : 14, i == 0 ? Rust : Ink, TextAnchor.MiddleCenter, i == 0 ? FontStyle.Bold : FontStyle.Normal);
            Place(l.rectTransform, 20, 46 + i * LINE, w - 40, LINE);
        }
        Btn(box, "知道了", (w - 136) / 2, 50 + n * LINE + 16, 136, BTN_H, () => { Close(); after?.Invoke(); });
    }

    /// 我方君主战死、还有城：原版 as() 6289，玩家自己挑继位人（「请选择继位君主」），不能作罢
    public void PickHeir(string oldLord, List<Officer> heirs, System.Action<Officer> pick)
    {
        PickGrid(oldLord + " 君主死亡!　请选择继位君主", heirs, o => "智" + o.Int + " 武" + o.Might + " 德" + o.Virtue,
                 h => { Close(); pick(h); }, null);
    }

    void Ask(string msg, System.Action yes, UnityEngine.Events.UnityAction no)
    {
        var box = Sheet("确　认", 118 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        var l = Label(box, msg, 16, Ink, TextAnchor.MiddleCenter);
        l.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(l.rectTransform, 24, 48, w - 48, 56); Cjk(l);
        Btn(box, "是", w / 2 - 104, 118, 96, BTN_H, () => yes());
        Btn(box, "否", w / 2 + 8, 118, 96, BTN_H, no);
    }
}
