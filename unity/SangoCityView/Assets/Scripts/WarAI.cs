// AI 出兵的执行：原版 t(int) 9685 的三个分支（规格 docs/TURNS_SPEC.md §2）。
//   打空城 a(short[],byte,int,int) 9238；AI 打 AI 算式结算 d() 9636（没有日循环，一掷定胜负和天数）；
//   玩家守城打完后的善后（C() / D() / K() / L() 反过来用）。
// 修掉的原版 bug：B4 占空城携带的金粮凭空消失 → 并进城库；B5 攻方君主兵败无处可退就无声消失、不继位 → 照常继位；
//   B15 守城无人时除零。
using System.Collections.Generic;
using UnityEngine;

public static partial class War
{
    static Officer LordOf(string f) => Realm.Ruler(f);

    /// 打空城：人进城，城归这家；太守是主将（B4 修正：带的金粮并进城库）
    public static void OccupyEmpty(MapData map, MapData.City to, string f, List<Officer> army, int gold = 0, int food = 0)
    {
        var lord = LordOf(f);
        Occupy(map, to, f, lord?.Name, army[0]);
        var a = Personnel.Of(to.id).A;
        foreach (var o in army) { a.Add(o); o.City = to.id; o.Serving = true; }
        var st = map.stats.Find(x => x.id == to.id);
        st.黄金 = Mathf.Min(st.黄金 + gold, MaxGoldFood); st.粮草 = Mathf.Min(st.粮草 + food, MaxGoldFood);
        Military.Recount(st);
    }

    // ── AI 打 AI：d() 9636 ─────────────────────────────────────────────
    static int Tier(int x, int v) => x > 95 ? v * 2 : x > 90 ? v * 18 / 10 : x > 85 ? v * 15 / 10 : x > 50 ? v * 12 / 10 : v;
    static int Power(Officer o) { int v = (o.Int + o.Might) * (o.Troops + 150); v = Tier(o.Int, v); v = Tier(o.Might, v); return v; }
    static int SumJ(List<Officer> l) { int t = 0; foreach (var o in l) t += o.Troops; return t; }

    /// b(short,int) 9277：兵力砍到 [cap−300, cap]（只砍不加），体力掷到 1..L
    static void Casualty(Officer o, int cap, System.Random rng)
    {
        if (o.Troops > 0)
        {
            if (o.Troops < cap) cap = o.Troops;
            int lo = Mathf.Max(0, cap - 300);
            int v = rng.Next(301) + lo;
            if (v < o.Troops) o.Troops = v;
        }
        if (o.Hp > 0) o.Hp = rng.Next(o.Hp) + 1;
    }
    /// c(short[],byte,short) 9307：打掉对方多少兵，按随机份额分给自己这边的人当经验
    static void Share(List<Officer> l, int pts, System.Random rng)
    {
        for (int k = 0; k < l.Count; k++)
        {
            if (k == l.Count - 1) { BattleRules.GainExp(l[k], Mathf.Max(0, pts)); break; }
            if (pts <= 0) { pts = 0; continue; }
            int x = rng.Next(pts); pts -= x; BattleRules.GainExp(l[k], x);
        }
    }

    public class AIResult { public bool Won; public string FallenFaction, EnemySuccession; }

    public static AIResult AutoResolve(MapData map, Dictionary<int, Officer> all, string f, AI.WarPlan p, System.Random rng)
    {
        var res = new AIResult();
        var to = map.cities.Find(x => x.id == p.To); var ts = map.stats.Find(x => x.id == p.To);
        string df = to.owner; var defLord = LordOf(df);
        var defs = new List<Officer>(Personnel.Of(p.To).A);
        var army = p.Army;
        int atk = 0; foreach (var o in army) atk += Power(o);
        int def = 0; foreach (var o in defs) def += Power(o);
        var g = defs.Find(o => o.Name == ts.governor) ?? (defs.Count > 0 ? defs[0] : null);   // 太守加成
        if (g != null) def += Tier(g.Might, g.Might * g.Troops * 3 / 2);
        int diff = atk - def;
        int ratio = diff > 0 ? (def > 0 ? 300 * atk / def : 1000) : (atk > 0 ? 300 * def / atk : 1000);   // B15：守城无人不除零
        if (ratio > 1000) ratio = 1000;
        int days = ratio > 600 ? rng.Next(5) + 6 : ratio > 450 ? rng.Next(10) + 11 : rng.Next(10) + 21;
        ts.粮草 -= ts.兵力 * 4 * days / 1000;
        int food = Mathf.Max(0, p.Food - SumJ(army) * 4 * days / 1000);
        int defBefore = SumJ(defs), atkBefore = SumJ(army);
        bool win;
        if (ts.粮草 < 0) { ts.粮草 = 0; foreach (var o in defs) Casualty(o, 1000, rng); foreach (var o in army) Casualty(o, 1000, rng); win = true; }
        else if (diff > 0) { foreach (var o in defs) Casualty(o, 500, rng); foreach (var o in army) Casualty(o, Mathf.Max(700, ratio), rng); win = true; }
        else { foreach (var o in defs) Casualty(o, Mathf.Max(700, ratio), rng); foreach (var o in army) Casualty(o, 500, rng); win = false; }
        Share(army, defBefore - SumJ(defs), rng);
        Share(defs, atkBefore - SumJ(army), rng);
        res.Won = win;
        var atkLord = LordOf(f);
        if (win)
        {
            // capture 9495：城归攻方，原守军按 retreat 规则撤 / 被擒
            int oldGold = ts.黄金, oldFood = ts.粮草;
            Personnel.Of(p.To).A.Clear();
            Occupy(map, to, f, atkLord?.Name, army[0]);
            foreach (var o in army) { Personnel.Of(p.To).A.Add(o); o.City = p.To; }
            ts.黄金 = p.Gold; ts.粮草 = food;
            bool lost = Retreat(map, defs, df, defLord, p.To, oldFood, oldGold, rng);
            Military.Recount(ts);
            if (lost || (defLord != null && !Personnel.Of(p.To).A.Contains(defLord) && defLord.City == 0))
            {
                if (Realm.CityCount(map, df) > 0) res.EnemySuccession = Succeed(map, all, df, defLord.Name);
            }
            if (Realm.CityCount(map, df) == 0) { var fac = Realm.Of(df); if (fac != null) fac.Alive = false; res.FallenFaction = defLord?.Name; }
        }
        else
        {
            // lose 9585：攻方撤回；B5 修正：攻方君主无处可退消失了照样继位
            bool lost = Retreat(map, army, f, atkLord, p.To, food, p.Gold, rng);
            Military.Recount(ts);
            if (lost && Realm.CityCount(map, f) > 0) res.EnemySuccession = Succeed(map, all, f, atkLord.Name);
        }
        Military.RecountAll(map);
        return res;
    }

    /// 撤退 / 被擒 9420：从战场城 at 往它的邻城撤。能去的：空城，或自家没满 10 人的城。
    /// 无路可退：君主当场没了，其余被胜方收编（满 10 人进在野）；体弱兵少的（体 ≤ 35 且兵 ≤ 150）按智力掷被擒。
    /// 金粮：有人被擒就落在战场城，否则跟着第一个撤走的人。返回：君主没了
    static bool Retreat(MapData map, List<Officer> off, string side, Officer sideLord, int at, int food, int gold, System.Random rng)
    {
        var cand = new List<int>();
        foreach (int c in Military.Adjacent(at))
        {
            var cc = map.cities.Find(x => x.id == c);
            if (cc.owner == "none" || (cc.owner == side && Personnel.Of(c).A.Count < Politics.MaxInCity)) cand.Add(c);
        }
        var win = Personnel.Of(at);
        bool captured = false, lordLost = false; int firstDest = 0;
        for (int idx = 0; idx < off.Count; idx++)
        {
            var o = off[idx];
            if (cand.Count == 0)
            {
                if (idx == 0) { captured = true; if (o == sideLord) { lordLost = true; Vanish(map, o); continue; } }
                Surrender(map, o, at, rng); continue;
            }
            if (win.A.Count >= Politics.MaxInCity) { int d = Pick(map, side, cand, rng); if (idx == 0) firstDest = d; MoveTo(map, o, cand, d, side, sideLord); continue; }
            if (o.Hp <= 35 && o.Troops <= 150)
            {
                int th = o.Int >= 95 ? 10 : o.Int >= 90 ? 20 : o.Int >= 80 ? 40 : 80;
                if (rng.Next(100) <= th)
                {
                    if (idx == 0)
                    {
                        if (o == sideLord) { int d = Pick(map, side, cand, rng); firstDest = d; MoveTo(map, o, cand, d, side, sideLord); continue; }
                        captured = true;
                    }
                    Surrender(map, o, at, rng); continue;
                }
            }
            int dd = Pick(map, side, cand, rng); if (idx == 0) firstDest = dd;
            MoveTo(map, o, cand, dd, side, sideLord);
        }
        int dest = captured || firstDest == 0 ? at : firstDest;
        var ds = map.stats.Find(x => x.id == dest);
        ds.黄金 = Mathf.Min(ds.黄金 + gold, MaxGoldFood); ds.粮草 = Mathf.Min(ds.粮草 + food, MaxGoldFood);
        return lordLost;
    }

    static void Vanish(MapData map, Officer o)
    {
        foreach (var c in map.cities) { var x = Personnel.Of(c.id); x.A.Remove(o); x.B.Remove(o); x.C.Remove(o); }
        o.City = 0; o.Serving = false;
    }

    /// r(o) 9354 + q(o)：被胜方收编，忠诚重掷后再补一点（<70 加 30，再 <40 加 15）；城满 10 人进在野
    static void Surrender(MapData map, Officer o, int at, System.Random rng)
    {
        foreach (var c in map.cities) { var x = Personnel.Of(c.id); x.A.Remove(o); x.B.Remove(o); x.C.Remove(o); }
        var ct = Personnel.Of(at);
        if (ct.A.Count >= Politics.MaxInCity) { ct.B.Add(o); o.Serving = false; o.City = at; return; }
        o.Loyal = BattleRules.Rebind(o.Loyal, rng);
        if (o.Loyal < 70) o.Loyal += 30;
        if (o.Loyal < 40) o.Loyal += 15;
        ct.A.Add(o); o.City = at; o.Serving = true;
    }

    /// c(short,byte[],byte,byte) 10943：先挑自家的城（随机），没有就随机一座空城
    static int Pick(MapData map, string side, List<int> cand, System.Random rng)
    {
        var own = cand.FindAll(c => map.cities.Find(x => x.id == c).owner == side);
        return own.Count > 0 ? own[rng.Next(own.Count)] : cand[rng.Next(cand.Count)];
    }

    /// a(short,byte[],byte,byte,short) 9361：进城；空城就势占了；君主进城当太守；城满 10 人就从候选里划掉
    static void MoveTo(MapData map, Officer o, List<int> cand, int dest, string side, Officer sideLord)
    {
        var c = map.cities.Find(x => x.id == dest);
        foreach (var cc in map.cities) { var x = Personnel.Of(cc.id); x.A.Remove(o); x.B.Remove(o); x.C.Remove(o); }
        var a = Personnel.Of(dest).A;
        var st = map.stats.Find(x => x.id == dest);
        if (c.owner == "none") Occupy(map, c, side, sideLord?.Name, o);
        if (o == sideLord) { a.Insert(0, o); st.governor = o.Name; } else a.Add(o);
        o.City = dest; o.Serving = true;
        if (a.Count >= Politics.MaxInCity) cand.Remove(dest);
        Military.Recount(st);
    }

    // ── 玩家守城打完：C() / D() 或 K() / L() ────────────────────────────
    /// 城丢了（CityFell == -1）：城归攻方，攻方余部进城，被擒的我将归降攻方；
    /// 我方撤走的按战场里选的城走，第一个撤走的带着城库。守住了：攻方余部按 J() 撤回自家邻城，无路可退的被我方收编。
    public static Outcome SettleDefense(MapData map, Dictionary<int, Officer> all, string attacker, AI.WarPlan p,
                                        BattleField f, string me, System.Random rng)
    {
        var r = new Outcome();
        var T = map.cities.Find(x => x.id == p.To); var ts = map.stats.Find(x => x.id == p.To);
        var myLord = LordOf(me); var foeLord = LordOf(attacker);
        var fin = Final(f);
        foreach (var o in f.Dead) Kill(map, o, r);
        bool firstOut = true;
        foreach (var kv in f.RetreatTo)                                    // 我方撤走的
        {
            var o = kv.Key; if (f.Dead.Contains(o)) continue;
            Personnel.Of(p.To).A.Remove(o);
            if (kv.Value == 0) { Personnel.Of(p.To).B.Add(o); o.Serving = false; o.City = p.To; r.Lines.Add(o.Name + " 下野"); continue; }
            var dest = map.cities.Find(x => x.id == kv.Value);
            if (dest.owner == "none") Occupy(map, dest, me, myLord?.Name, o);
            Join(map, dest, o);
            if (firstOut && (f.OurKeptGold > 0 || f.OurKeptFood > 0))      // 第一个撤走的带着城库（原版 i() 2947）
            {
                var ds = map.stats.Find(x => x.id == kv.Value);
                ds.黄金 = Mathf.Min(ds.黄金 + f.OurKeptGold, MaxGoldFood); ds.粮草 = Mathf.Min(ds.粮草 + f.OurKeptFood, MaxGoldFood);
            }
            firstOut = false;
            r.Lines.Add(o.Name + " 退往 " + dest.name);
        }
        var foes = new List<Officer>(); var ours = new List<Officer>();
        foreach (var kv in fin)
        {
            if (f.Dead.Contains(kv.Key) || f.RetreatTo.ContainsKey(kv.Key)) continue;
            (kv.Value.Ours ? ours : foes).Add(kv.Key);
        }
        var tA = Personnel.Of(p.To).A;
        if (f.CityFell == -1)
        {
            tA.Clear();
            var gov = foes.Count > 0 ? foes[0] : p.Army[0];
            Occupy(map, T, attacker, foeLord?.Name, gov);
            foreach (var o in foes) { if (tA.Count < Politics.MaxInCity) { tA.Add(o); o.City = p.To; o.Serving = true; } else { Personnel.Of(p.To).B.Add(o); o.Serving = false; } }
            foreach (var o in ours) Surrender(map, o, p.To, rng);            // 还留在场上的我方（被擒换边的已经在 foes 里）
            ts.黄金 = Mathf.Min(f.Supply[2], MaxGoldFood); ts.粮草 = Mathf.Min(f.Supply[3], MaxGoldFood);
            r.Lines.Insert(0, "敌军占领了 " + T.name + "!");
        }
        else
        {
            // 守住了：攻方余部撤回（K()），第一个撤走的带着攻方剩下的金粮；敌主将倒了 / 无路可退，那份金粮归我方
            tA.Clear();
            foreach (var o in ours) { if (tA.Count < Politics.MaxInCity) { tA.Add(o); o.City = p.To; o.Serving = true; } else { Personnel.Of(p.To).B.Add(o); o.Serving = false; } }
            ts.黄金 = Mathf.Min(f.Supply[0], MaxGoldFood); ts.粮草 = Mathf.Min(f.Supply[1], MaxGoldFood);
            bool first = true;
            foreach (var o in foes)
            {
                var back = FoeRetreat(map, T, attacker);
                if (back == null) { Surrender(map, o, p.To, rng); r.Lines.Add(o.Name + " 无路可退，被擒归降"); continue; }
                if (back.owner == "none") Occupy(map, back, attacker, foeLord?.Name, o);
                Join(map, back, o);
                if (first && (f.Supply[2] > 0 || f.Supply[3] > 0))
                {
                    var bs = map.stats.Find(x => x.id == back.id);
                    bs.黄金 = Mathf.Min(bs.黄金 + f.Supply[2], MaxGoldFood); bs.粮草 = Mathf.Min(bs.粮草 + f.Supply[3], MaxGoldFood);
                }
                first = false;
                r.Lines.Add(o.Name + " 退往 " + back.name);
            }
            r.Lines.Insert(0, (foeLord?.Name ?? "敌") + " 军攻打 " + T.name + " 失败了!");
        }
        foreach (var o in f.Dead) if (o == myLord) r.LordDied = true;
        foreach (var o in f.Dead)
            if (o == foeLord && Realm.CityCount(map, attacker) > 0) r.EnemySuccession = Succeed(map, all, attacker, foeLord.Name);
        if (Realm.CityCount(map, attacker) == 0) { var fac = Realm.Of(attacker); if (fac != null) fac.Alive = false; r.FallenFaction = foeLord?.Name; }
        Military.RecountAll(map);
        r.Won = f.CityFell != -1;
        return r;
    }
}
