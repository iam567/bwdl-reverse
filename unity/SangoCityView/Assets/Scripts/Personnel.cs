// 功曹府四条人事指令：搜索 / 登用 / 奖赏 / 任命。全部照原版。
//
// 原版对应关系（decompiled/b.java、c.java、d.java）：
//   菜单项           d.b[1] = {搜索, 登用, 奖赏, 任命}，命令码 6 / 7 / 8 / 9
//   入口             P(6) / T(7) / S(8) / U(9)             b.java 8796-8881
//   步骤表           bR[6]={3,10,1}      选将 → aR() 搜索 → aL() 扣令
//                    bR[7]={3,3,98,1}    选在野贤才 → 选说客 → aS() 登用 → 扣令
//                    bR[8]={3,11,1}      选将 → 屏 12 定赏额 → 扣令
//                    bR[9]={3,97,0}      选将 → aT() 任命 → aK()：**不扣令**
//   搜索             f(short,byte) 10413 + c(byte,short) 10438
//   登用             j(short,short) 10371 + c(byte,short,short) 10398
//   奖赏             屏 12 c.java 1157 / 3732，o(short) 与 w(int) 10155
//   任命             c(short,byte)（把新太守挪到 A[] 队首，j[] 改成他）
//
// 每城三份名册（原版数组长度都是 10）：
//   A[] 仕官（r[] 人）  B[] 未发现的在野（s[] 人，搜索池）  C[] 已发现未登用（t[] 人）
using System.Collections.Generic;
using UnityEngine;

public static class Personnel
{
    public enum Cmd { Search, Recruit, Reward, Appoint }
    public static readonly string[] CmdName = { "搜索", "登用", "奖赏", "任命" };

    public const int MaxGoldFood = 9999;      // c(byte,short)：金、粮搜到顶 9999
    public const int MaxTreasure = 99;        // w[] 宝物
    public const int RewardCap = 100;         // 屏 12：赏金最多 100，且不超过城里的金子
    public const int LoyalLine = 90;          // S(8)：忠诚 90 以上的不进奖赏名单
    public const short Treasure = 200;        // 原版拿 200 这个数表示「赏宝物」

    public class Court
    {
        public readonly List<Officer> A = new();   // 仕官
        public readonly List<Officer> B = new();   // 未发现的在野
        public readonly List<Officer> C = new();   // 已发现、还没登用
    }

    static Dictionary<int, Court> courts;

    /// 开局名册。原版 b.java:7227 读武将表时两道门：
    ///   登场年份 T[] 必须是 189，190~193 的这个剧本还没出场；
    ///   第 15 列 1 进仕官 A[]，0 进在野 B[]（得靠搜索才挖得到）。
    /// A[] 数组只有 10 格，超出的这一版照旧丢掉（和 Politics.Roster 以前的做法一致）。
    public static void Init(Dictionary<int, Officer> all)
    {
        courts = new Dictionary<int, Court>();
        var ids = new List<int>(all.Keys); ids.Sort();
        foreach (int id in ids)
        {
            var o = all[id];
            if (o.Year != Politics.ScenarioYear || o.City <= 0) continue;
            var c = Of(o.City);
            if (o.Serving) { if (c.A.Count < Politics.MaxInCity) c.A.Add(o); }
            else c.B.Add(o);
        }
    }

    /// 年度结算里跟人事有关的那一段：aE() 6894 → l(年) → k(武将)。
    /// 登场年份到了的武将进所在城的**搜索池 B[]**（不是直接仕官），兵力清零；
    /// 没有城（U[]=0）的永不登场。返回这一年新进搜索池的人。
    /// 原版 aE() 后半段还有 26 条写死的「子承父业」事件（aa/ab/ac 三张表），见 ScriptedJoins。
    /// 3.2.2 的那张表和它自己的武将表对不上（程普成了北斗之子，且多数人开局已仕官），初级不接；
    /// 3.2.5 / 3.1 各带一张、对得上，中级 / 困难照接。
    public static List<Officer> NewYear(int year, Dictionary<int, Officer> all)
    {
        var came = new List<Officer>();
        var ids = new List<int>(all.Keys); ids.Sort();
        foreach (int id in ids)
        {
            var o = all[id];
            if (o.Year != year) continue;
            o.Troops = 0;                              // k()：J[武将] = 0
            if (o.City <= 0) continue;
            o.Serving = false;
            Of(o.City).B.Add(o); came.Add(o);
        }
        return came;
    }

    /// aE() 6894 后半段：这一年到了的「子承父业」——父亲在哪座城仕官（按城号 1~36 找第一座），儿子就去那儿：
    /// 城里不满 10 人直接仕官（在玩家城就弹事件 12「X跟随 Y 加入我军为君主效力!」），满了进那城的在野名单 B[]。
    /// 父亲不在任何城仕官（死了 / 在野）就作罢。返回直接仕官的（子, 父, 城）。
    public static List<(Officer child, Officer parent, int city)> ScriptedJoins(int year, Dictionary<int, Officer> all)
    {
        var res = new List<(Officer, Officer, int)>();
        foreach (var j in Scenario.Joins())
        {
            if (j[0] != year || !all.TryGetValue(j[1], out var child) || !all.TryGetValue(j[2], out var parent)) continue;
            int city = 0;
            for (int c = 1; c <= 36 && city == 0; c++) if (Of(c).A.Contains(parent)) city = c;
            if (city == 0) continue;
            var ct = Of(city);
            if (ct.A.Count < Politics.MaxInCity)
            {
                ct.A.Add(child); child.City = city; child.Serving = true;
                res.Add((child, parent, city));
            }
            else ct.B.Add(child);
        }
        return res;
    }

    /// 读档：三份名册按存档里的武将号重建
    public static void Restore(List<SaveGame.CourtState> saved, Dictionary<int, Officer> all)
    {
        courts = new Dictionary<int, Court>();
        foreach (var cs in saved)
        {
            var c = Of(cs.city);
            foreach (int id in cs.a) if (all.TryGetValue(id, out var o)) c.A.Add(o);
            foreach (int id in cs.b) if (all.TryGetValue(id, out var o)) c.B.Add(o);
            foreach (int id in cs.c) if (all.TryGetValue(id, out var o)) c.C.Add(o);
        }
    }

    public static Court Of(int cityId)
    {
        if (courts == null) courts = new Dictionary<int, Court>();
        if (!courts.TryGetValue(cityId, out var c)) courts[cityId] = c = new Court();
        return c;
    }

    // ── 前置检查：不过就弹一句话退回城内，不扣令 ─────────────────────────
    /// T(7) 8851：先看仕官满没满，再看有没有搜出来的在野
    /// U(9) 8873：君主亲领的城不能换太守（这句话是君主说的）
    /// S(8) 8830：人人忠诚都到 90 了就不用赏
    public static bool Blocked(Cmd c, MapData.Stat s, out string why)
    {
        var ct = Of(s.id);
        switch (c)
        {
            case Cmd.Recruit:
                if (ct.A.Count >= Politics.MaxInCity) { why = "本城置吏已足!"; return true; }
                if (ct.C.Count == 0) { why = "野无遗贤!"; return true; }
                break;
            case Cmd.Reward:
                if (RewardList(s).Count == 0) { why = "吏士皆忠心不贰!"; return true; }
                break;
            case Cmd.Appoint:
                if (s.ruler == s.governor) { why = "此城乃君主治所!"; return true; }   // d.d[2][4]
                break;
        }
        why = null; return false;
    }

    public static List<Officer> RewardList(MapData.Stat s)
        => Of(s.id).A.FindAll(o => o.Loyal < LoyalLine);

    // ── 搜索 ────────────────────────────────────────────────────────
    public enum Find { Nothing, Food, Gold, Talent, Treasure }
    public struct SearchResult { public Find Kind; public int Amount; public Officer Talent; public string Msg; }

    /// f(short,byte) 10413 + c(byte,short) 10438。
    /// **智力决定能不能有收获，德决定收获多少。**
    ///   roll = s()%120 > 智力 → 一无所获
    ///   roll > 79            → 宝物 1~4 件
    ///   roll > 59 且池子不空  → 偶遇贤才（从 B[] 随机挪一个到 C[]）
    ///   其余                  → 金或粮，数量 (德×1.2 ~ 德×1.7) × 0.6，roll 奇数给金、偶数给粮
    public static SearchResult Search(MapData.Stat s, Officer o, System.Random rng)
    {
        var ct = Of(s.id);
        var r = new SearchResult();
        int roll = rng.Next(120);
        if (roll > o.Int) { r.Kind = Find.Nothing; }
        else if (roll > 79)
        {
            int k = rng.Next(100);
            r.Amount = k < 5 ? 4 : k < 15 ? 3 : k < 40 ? 2 : 1;
            r.Kind = Find.Treasure;
            s.宝物 = Mathf.Min(s.宝物 + r.Amount, MaxTreasure);
        }
        else if (roll > 59 && ct.B.Count != 0)
        {
            int i = rng.Next(ct.B.Count);
            r.Talent = ct.B[i]; r.Kind = Find.Talent;
            ct.B.RemoveAt(i); ct.C.Add(r.Talent);
        }
        else
        {
            // 原版 hi-lo 为 0（德 0~1）时 `% 0` 会抛异常；这里把空区间当 0，见 HOUSE_RULES
            int lo = o.Virtue * 12 / 10, hi = o.Virtue * 17 / 10;
            r.Amount = (lo + (hi > lo ? rng.Next(hi - lo) : 0)) * 6 / 10;
            r.Kind = roll % 2 == 1 ? Find.Gold : Find.Food;
            if (r.Kind == Find.Food) s.粮草 = Mathf.Min(s.粮草 + r.Amount, MaxGoldFood);
            else                     s.黄金 = Mathf.Min(s.黄金 + r.Amount, MaxGoldFood);
        }
        // 屏 11 的文案 d.c[]；只有金粮两项后面跟数字
        r.Msg = r.Kind switch {
            Find.Food     => "征得粮草:" + r.Amount,
            Find.Gold     => "获得黄金" + r.Amount,
            Find.Talent   => "偶遇贤才",
            Find.Treasure => "寻得宝物.",
            _             => "一无所获!",
        };
        return r;
    }

    // ── 登用 ────────────────────────────────────────────────────────
    /// j(short,short) 10371：对方三项里最高的那项 − 说客的德 < s()%40 就成。
    /// 原版这里有一次结果被丢掉的 nextInt()，是没清干净的死代码，照 GAME_LOGIC §7 不抄。
    public static bool RecruitOk(Officer by, Officer target, System.Random rng)
    {
        int best = Mathf.Max(target.Int, Mathf.Max(target.Might, target.Virtue));
        return best - by.Virtue < rng.Next(40);
    }

    /// c(byte,short,short) 10398：成了就从 C[] 挪进 A[]，城里总兵力加上他带的兵。
    /// 成与不成都扣一道令（步骤表最后那个 1）。
    public static bool Recruit(MapData.Stat s, Officer by, Officer target, System.Random rng)
    {
        if (!RecruitOk(by, target, rng)) return false;
        var ct = Of(s.id);
        ct.C.Remove(target); ct.A.Add(target);
        target.Serving = true; target.City = s.id;
        s.兵力 += target.Troops;
        s.武将 = ct.A.Count;
        return true;
    }

    // ── 奖赏 ────────────────────────────────────────────────────────
    /// w(int) 10155：赏 n 金涨 n/8 点忠诚，余数按概率进一；超过 7 点再随机 −1/0。
    /// 赏宝物（n=200）固定 11~18 点。
    public static int LoyalGain(int n, System.Random rng)
    {
        if (n == Treasure) return 11 + rng.Next(8);
        int g = n / 8;
        if (rng.Next(8) < n % 8) g++;
        if (g > 7) g += rng.Next(2) - 1;
        return g;
    }

    /// o(short) 10136：忠诚加上去，过 99 就压回 99（原版 byte 溢出成负数也压回 99）。
    /// 赏宝物扣一件宝物，赏金扣金子。
    public static int Reward(MapData.Stat s, Officer o, int n, System.Random rng)
    {
        int before = o.Loyal;
        o.Loyal += LoyalGain(n, rng);
        if (o.Loyal > 99 || o.Loyal <= 0) o.Loyal = 99;
        if (n == Treasure) s.宝物--; else s.黄金 -= n;
        return o.Loyal - before;
    }

    // ── 任命 ────────────────────────────────────────────────────────
    /// c(short,byte)：新太守挪到 A[] 队首，太守 j[] 改成他。原版这条不扣令。
    public static void Appoint(MapData.Stat s, Officer o)
    {
        var a = Of(s.id).A;
        a.Remove(o); a.Insert(0, o);
        s.governor = o.Name;
    }
}
