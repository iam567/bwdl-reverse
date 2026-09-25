// 校场四条军事指令：移动 / 战争 / 征兵 / 输送。规则照原版，战争的开战与善后在 War.cs。
//
// 原版对应关系（decompiled/b.java、c.java）：
//   菜单项           d.b[0] = {移动, 战争, 征兵, 输送}，命令码 1 / 3 / 2 / 12
//   入口             Q(1) 8801 / af() / P(2) / R(12) 8816
//   步骤表           bR[1]  = {2,3,3,99,4,1}  选城 → 多选武将 k() → [带走了太守就补任新太守] → aP() → 结果屏 → 扣令
//                    bR[2]  = {5,1}           屏 8：先征进预备兵，再分给各将 → 扣令
//                    bR[12] = {2,16,1}        选城 → 屏 9 填金 / 粮 / 宝 → 扣令
//   移动             k(int) 8546、j(int) 8520、bn()/bo()/bp()/bq() 8458-8520
//   征兵             b(short,byte) 7385；屏 8 输入 c.java 5650-5800
//   输送             a(short[]) 8629；屏 9 上限 c.java 758
//
// 城池字段：兵力 = u[]（各将 J[] 之和），预备兵 = v[]（cities.csv 第 15 列，征来还没分下去的兵）。
using System.Collections.Generic;
using UnityEngine;

public static class Military
{
    public enum Cmd { Move, War, Draft, Supply }
    public static readonly string[] CmdName = { "移动", "战争", "征兵", "输送" };

    public const int MaxTroopsPerOfficer = 1000;   // bC()：一员武将最多带 1000 兵
    public const int MaxReserve = 9900;            // 屏 8：预备兵最多 9900
    public const int GoldPer100 = 20;              // 「征100兵 需 20金」
    public const int MaxGoldFood = 9999, MaxTreasure = 99;

    /// 相邻城（原版 D[城][] / E[城]）
    static Dictionary<int, int[]> adj;
    public static int[] Adjacent(int city)
    {
        if (adj == null) adj = new Dictionary<int, int[]>();
        if (!adj.TryGetValue(city, out var a))
            adj[city] = a = BattleRules.AdjacentCities(System.IO.Path.Combine(BattleGame.BattleDir, "city-adjacency.json"), city);
        return a;
    }

    /// Q(1) / R(12)：相邻城里有没有**同一个主公**的城（i[邻] == i[本]）。空城、盟友都不算。
    public static List<MapData.City> OwnNeighbors(MapData map, MapData.City from)
    {
        var list = new List<MapData.City>();
        foreach (int id in Adjacent(from.id))
        {
            var c = map.cities.Find(x => x.id == id);
            if (c != null && c.owner == from.owner && c.owner != "none") list.Add(c);
        }
        return list;
    }

    /// 城里兵力 = 各将所带兵之和（原版 O(byte) 8452）
    public static void Recount(MapData.Stat s)
    {
        var a = Personnel.Of(s.id).A;
        int u = 0; foreach (var o in a) u += o.Troops;
        s.兵力 = u; s.武将 = a.Count;
    }

    public static void RecountAll(MapData map) { foreach (var s in map.stats) Recount(s); }

    // ── 移动 ────────────────────────────────────────────────────────
    public enum MoveCheck { Ok, TooMany, EmptiesCity, NeedGovernor }

    /// k(int) 8546：先查人数（移过去超过 10 人 →「移动武将数过多!」），
    /// 再看是不是全城都走（→「是否放弃此城市？」），
    /// 最后看太守在不在走的人里（在 → 先补任新太守）。
    public static MoveCheck CheckMove(MapData.Stat from, MapData.Stat to, List<Officer> movers)
    {
        var a = Personnel.Of(from.id).A;
        if (movers.Count + Personnel.Of(to.id).A.Count > Politics.MaxInCity) return MoveCheck.TooMany;
        if (movers.Count == a.Count) return MoveCheck.EmptiesCity;
        if (movers.Exists(o => o.Name == from.governor)) return MoveCheck.NeedGovernor;
        return MoveCheck.Ok;
    }

    /// bp() = bn() + bo()：人带着兵走，金粮留在原城。
    /// bn() 8458：进了目标城；**君主本人走进自己的城就顺手当太守**（i[目标] == az[0] → d()）。
    public static void Move(MapData map, MapData.Stat from, MapData.Stat to, List<Officer> movers)
    {
        var src = Personnel.Of(from.id).A; var dst = Personnel.Of(to.id).A;
        foreach (var o in movers) { src.Remove(o); dst.Add(o); o.City = to.id; }
        if (movers.Count > 0 && movers[0].Name == to.ruler) Personnel.Appoint(to, movers[0]);
        Recount(from); Recount(to);
    }

    /// bq() 8515：全城都走了 → 这座城成了空城：太守、城主清零，从本势力的城里除名。金粮原样留在城里。
    public static void Abandon(MapData map, MapData.Stat s)
    {
        s.ruler = ""; s.governor = ""; s.兵力 = 0; s.武将 = 0; s.owner = "none";
        var c = map.cities.Find(x => x.id == s.id); if (c != null) c.owner = "none";
    }

    // ── 征兵 ────────────────────────────────────────────────────────
    /// 屏 8 第一步能征多少：预备兵顶 9900、每 5 兵 1 金、不能超过人口
    public static int DraftCap(MapData.Stat s)
        => Mathf.Max(0, Mathf.Min(MaxReserve - s.预备兵, Mathf.Min(s.黄金 * 5, s.人口)));

    /// b(short,byte) 7385：征进预备兵，金子扣 (n+4)/5，人口减 n
    public static void Draft(MapData.Stat s, int n)
    {
        s.预备兵 += n;
        s.黄金 -= (n + 4) / 5;
        s.人口 -= n;
    }

    /// 屏 8 第二步确认：各将的兵照填的来，剩下的留作预备兵（c.java 5780）
    public static void Assign(MapData.Stat s, Dictionary<Officer, int> troops, int reserveLeft)
    {
        foreach (var kv in troops) kv.Key.Troops = kv.Value;
        s.预备兵 = reserveLeft;
        Recount(s);
    }

    // ── 输送 ────────────────────────────────────────────────────────
    /// 屏 9 各项上限（c.java 758）：不超过本城现有，也不能把对面顶过 9999 / 99
    public static int[] SupplyCap(MapData.Stat from, MapData.Stat to) => new[] {
        from.黄金 + to.黄金 > MaxGoldFood ? MaxGoldFood - to.黄金 : from.黄金,
        from.粮草 + to.粮草 > MaxGoldFood ? MaxGoldFood - to.粮草 : from.粮草,
        from.宝物 + to.宝物 > MaxTreasure ? MaxTreasure - to.宝物 : from.宝物,
    };

    /// a(short[]) 8629：金、粮、宝物一起从本城搬到目标城
    public static void Supply(MapData.Stat from, MapData.Stat to, int gold, int food, int gem)
    {
        from.黄金 -= gold; from.粮草 -= food; from.宝物 -= gem;
        to.黄金 += gold;   to.粮草 += food;   to.宝物 += gem;
    }
}
