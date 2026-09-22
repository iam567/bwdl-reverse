// 太守府的四条内政指令：开垦 / 劝商 / 治水 / 巡查。全部照原版。
//
// 原版对应关系（decompiled/b.java、c.java）：
//   菜单项           d.b[2] = {开垦, 劝商, 治水, 巡查}
//   入口             V(10) / W(11) / X(13) / Y(14)   b.java 8892-8950
//   步骤表           bR[10]={3,12,96,1} bR[11]={3,12,95,1}
//                    bR[13]={3,13,94,1} bR[14]={3,12,93,1}
//                    3=选武将 12=算花费(t) 13=算花费(u) 93~96=执行 1=收尾
//   执行             aY/aZ/ba/bb → a/b/c/d(byte,short,int)  b.java 7517-7595
//   产出公式         c/d/e(short,int)                       b.java 10520-10566
//   花费公式         t/u(short)                             b.java 10509-10541
//   每回合指令数     aG = e(势力) = 城数，夹在 3..8         b.java 1726 / 6156
//
// 城池字段（cities.csv 第 5~11 列，和原版数组一一对应）：
//   k[] 统治  l[] 金子  m[] 粮食  n[] 农业  o[] 工业  p[] 人口  q[] 防灾
// map.json 里这几个字段叫 治安/黄金/粮草/农业/商业/人口/治水——只是名字不同，值是对的。
using System.Collections.Generic;
using UnityEngine;

public static class Politics
{
    public const int MaxFarm = 999;        // 农业 n[]
    public const int MaxWork = 999;        // 工业 o[]
    public const int MaxFlood = 99;        // 防灾 q[]
    public const int MaxPeople = 990000;   // 人口 p[]
    public const int MaxRule = 99;         // 统治 k[]

    public enum Cmd { Reclaim, Commerce, Water, Patrol }
    public static readonly string[] CmdName = { "开垦", "劝商", "治水", "巡查" };

    // ── 花费：武将一选定就算出来，玩家只能确认或作罢，不能自己填数 ──────────
    /// b.t(short) 10509：开垦 / 劝商 / 巡查。10~26 金
    public static int CostT(Officer o, System.Random rng)
    {
        int n = 10;
        if (o.Int > 70) n += 8; else if (o.Int > 50) n += 4;
        return n + 2 * rng.Next(5);
    }
    /// b.u(short) 10532：治水。10~19 金，比上面那条便宜
    public static int CostU(Officer o, System.Random rng)
    {
        int n = 10;
        if (o.Int > 70) n += 6; else if (o.Int > 50) n += 3;
        return n + rng.Next(4);
    }
    /// b.aV() 7500：花费不能超过城里的金子
    public static int Cost(Cmd c, Officer o, int cityGold, System.Random rng)
        => Mathf.Min(c == Cmd.Water ? CostU(o, rng) : CostT(o, rng), cityGold);

    // ── 产出：都吃智力，武力德行忠诚一概不参与 ────────────────────────────
    /// b.c(short,int) 10520：开垦 / 劝商。智力低于 12 的只出 1 点
    public static int YieldWork(Officer o, int gold, System.Random rng)
    {
        if (o.Int < 12) return 1;
        int hi = o.Int * gold / 100, lo = hi * 7 / 10;
        return lo + rng.Next(hi - lo + 1);
    }
    /// b.d(short,int) 10543：治水。下限是一半，波动比开垦大
    public static int YieldFlood(Officer o, int gold, System.Random rng)
    {
        if (o.Int < 12) return 1;
        int hi = o.Int * gold / 100, lo = hi / 2;
        return lo + rng.Next(hi - lo + 1);
    }
    /// b.e(short,int) 10555：巡查涨人口。门槛是智力 17，不到就固定 1000 人
    /// 原版这里 hi = base-1，base 为 0 时会 `% 0` 抛异常（城里金子不足 10 且智力够
    /// 的时候真能撞上）。我们把空区间当 0 处理，见 docs/HOUSE_RULES.md。
    public static int YieldPeople(Officer o, int gold, System.Random rng)
    {
        if (o.Int < 17) return 1000;
        int b = o.Int * gold / 100;
        int lo = b / 2, hi = b - 1;
        if (hi < lo) return lo * 100;
        return (lo + rng.Next(hi - lo + 1)) * 100;
    }

    /// 一次内政的结果
    public struct Result
    {
        public bool Ok;            // 真的执行了吗（假 = 被前置条件挡下，不扣指令）
        public string Msg;         // 弹给玩家的那句话，原话照抄 d.d[3][]
        public int Gold;           // 花掉的金子
        public int Gain;           // 涨了多少（人口那条是人数）
        public bool RuleUp;        // 统治是不是跟着 +1
    }

    /// 前置检查。原版 bt() 先看金子，再各自看自家那条到顶没有。
    /// 两种都只弹一句话就退回城内，**不消耗指令**（bQ=4 是弹框，aG 没动）。
    public static bool Blocked(Cmd c, MapData.Stat s, out string why)
    {
        if (s.黄金 == 0) { why = "资财不足"; return true; }           // b.bt() 8883
        switch (c)                                                    // b.V/W/X/Y 的上限检查
        {
            case Cmd.Reclaim:  if (s.农业 >= MaxFarm)   { why = "此城田土广辟"; return true; } break;
            case Cmd.Commerce: if (s.商业 >= MaxWork)   { why = "此城人民丰赡"; return true; } break;
            case Cmd.Water:    if (s.治水 >= MaxFlood)  { why = "此城无惧水旱"; return true; } break;
            case Cmd.Patrol:   if (s.人口 >= MaxPeople) { why = "此城户口殷盛"; return true; } break;
        }
        why = null; return false;
    }

    /// 执行。gold 是上一步算好、玩家确认过的花费。
    /// 原版 a/b/c/d(byte,short,int) 7517-7595：先扣金，再加本项，最后统治 +1。
    /// **治水那条不涨统治**——原版 c(byte,short,int) 里就没这一段。
    public static Result Run(Cmd c, MapData.Stat s, Officer o, int gold, System.Random rng)
    {
        var r = new Result { Ok = true, Gold = gold };
        s.黄金 -= gold;
        switch (c)
        {
            case Cmd.Reclaim:
                r.Gain = YieldWork(o, gold, rng);
                s.农业 = Mathf.Min(s.农业 + r.Gain, MaxFarm);
                r.Msg = "本城农业水平提高了!";                         // d.d[3][0]
                break;
            case Cmd.Commerce:
                r.Gain = YieldWork(o, gold, rng);
                s.商业 = Mathf.Min(s.商业 + r.Gain, MaxWork);
                r.Msg = "城市的经济迅猛发展!";                         // d.d[3][1]
                break;
            case Cmd.Water:
                r.Gain = YieldFlood(o, gold, rng);
                s.治水 = Mathf.Min(s.治水 + r.Gain, MaxFlood);
                r.Msg = "本城抗灾能力提高";                            // d.d[3][3]
                break;
            case Cmd.Patrol:
                r.Gain = YieldPeople(o, gold, rng);
                s.人口 = Mathf.Min(s.人口 + r.Gain, MaxPeople);
                r.Msg = "城市秩序更加稳定";                            // d.d[3][4]
                break;
        }
        if (c != Cmd.Water && s.治安 < MaxRule) { s.治安++; r.RuleUp = true; }
        return r;
    }

    /// b.e(byte) 1726 / ao() 6156：一回合能下几道令 = 本势力城数，夹在 3..8
    public static int OrdersPerTurn(int cityCount) => Mathf.Clamp(cityCount, 3, 8);

    /// 城里能派出去办事的武将。原版 b.java:7227 读武将表时两道门：
    ///   第 13 列（登场年份 T[]）必须是 189，190~193 的这个剧本还没出场；
    ///   第 15 列 1 才进城池的 A[]（仕官），0 进 B[]（在野，得靠搜索才挖得到）。
    /// 164 人过第一道门，其中 162 人仕官；每城最多 10 人，正好顶着原版 A[城] 的数组长度。
    public const int MaxInCity = 10;          // b.java:7057 A[s] = new short[10]
    public const int ScenarioYear = 189;      // 中平六年
    public static List<Officer> Roster(Dictionary<int, Officer> all, int cityId)
    {
        var list = new List<Officer>();
        foreach (var kv in all)
        {
            var o = kv.Value;
            if (o.Year == ScenarioYear && o.Serving && o.City == cityId) list.Add(o);
        }
        list.Sort((a, b) => a.Id.CompareTo(b.Id));
        if (list.Count > MaxInCity) list.RemoveRange(MaxInCity, list.Count - MaxInCity);
        return list;
    }
}
