// 战略层 AI：原版 b.bw() 9747（只有 Z==2 那一套在跑，Z 写死 2）。逐函数照原版，
// 规格见 docs/AI_SPEC.md（从反编译逐行抄出来的）。每家每回合 e(f) = clamp(城数, 3, 8) 道令，
// 一令一步，优先级：战争 > 征兵 > 调将 > 医馆 > 奖赏 > 输送 > 杂项（集市 / 离间招揽 / 搜索登用 / 内政）。
//
// 修掉的原版 bug（docs/HOUSE_RULES.md §AI）：
//   B1 打完玩家后同一家白捡一整轮新令（可无限连打）→ 打完玩家这家本回合就结束（和原版 break 丢掉余令一致）
//   B3 AI 学院学费拿城号当武将号查智力 → 按那员武将的智力
//   B4 调将按人数搬时没有「至少留一人」的门 → 补上，不会把城搬空成 -1 人
//   B5 部分征兵后没分到的武将兵力残留 → 按总兵从头分，余下的清零
//   B7 招揽挖走玩家城最后一人时不弹「投奔」→ 照弹
//   B10 调将不改太守 → 搬走的若是太守，改由留下的第一人
using System.Collections.Generic;
using UnityEngine;

public class AI
{
    readonly MapData map;
    readonly System.Random rng;
    public System.Action<string, string, string, Officer> Event;   // (动图 key, 标题, 文案, 说话人)

    public AI(MapData m, System.Random r) { map = m; rng = r; }

    int Rnd(int n) => n <= 0 ? 0 : rng.Next(n);                    // 原版 s() % n
    MapData.Stat S(int c) => map.stats.Find(x => x.id == c);
    string Owner(int c) => map.cities.Find(x => x.id == c)?.owner ?? "none";
    List<Officer> A(int c) => Personnel.Of(c).A;
    int R(int c) => A(c).Count;

    // ── 本家城分前线 / 腹地：as(f) 11699，ai(c) 9952 ─────────────────────
    List<int> front = new(), inner = new();
    bool IsFront(int c) { var o = Owner(c); foreach (int nb in Military.Adjacent(c)) if (Owner(nb) != o) return true; return false; }
    void Partition(string f)
    {
        front.Clear(); inner.Clear();
        foreach (var c in map.cities) if (c.owner == f) (IsFront(c.id) ? front : inner).Add(c.id);
    }

    /// 出兵的计划：交给回合引擎去执行（打玩家要进战场，打别家走算式，打空城直接占）
    public class WarPlan { public int From, To; public List<Officer> Army; public int Gold, Food; }
    public System.Func<string, WarPlan, bool> ExecuteWar;        // 返回真 = 这一仗打的是玩家（这家本回合到此为止）

    string me;                                                   // 正在行动的势力（原版 as）
    int au, av; Officer ax, ay;

    /// 一家的一整个回合：bw()
    public void Turn(string f)
    {
        me = f;
        int tokens = Mathf.Clamp(Realm.CityCount(map, f), 3, 8);   // e(f) 1726，开头算一次
        for (int t = 0; t < tokens; t++)
        {
            if (Realm.CityCount(map, f) == 0) return;
            Partition(f);
            au = av = 0;
            int score = WarTarget();                              // cb()
            if (score >= 1000)
            {
                int k = GoNoGo(score);                            // C()
                if (k > 0)
                {
                    var plan = MakeArmy(k);                        // t(n) 前半：e() 选将 + c() 带粮 + 金
                    if (plan != null && ExecuteWar != null && ExecuteWar(f, plan)) return;   // 打玩家：B1 修正，本回合结束
                    if (plan != null) continue;
                }
            }
            if (Draft()) continue;                                 // cc() + bD()
            int n = Shift(); if (n > 0) { DoShift(n); continue; } // cd() + D()
            if (Clinic()) continue;                                // ch() + bJ()
            if (Reward()) continue;                                // ce() + bM()
            int mode = Convoy(); if (mode >= 0) { DoConvoy(mode); continue; }   // cf() + x()
            Misc();                                                // cg()
        }
    }

    // ── 战争：cb() 11723 / z() 11717 / C() 11593 / e() 11670 / c() 9225 ───────
    int WarTarget()
    {
        int best = 999;
        bool allied = Realm.Allied(me);                            // bx()：这家和玩家结了盟，就不打玩家的城
        foreach (int src in front)
        {
            if (R(src) <= 2) continue;
            foreach (int dst in Military.Adjacent(src))
            {
                if (Owner(dst) == me) continue;
                if (allied && Owner(dst) == Realm.Player) continue;
                int sc = Score(src, dst);
                if (sc > best) { best = sc; au = src; av = dst; }
            }
        }
        return best;
    }
    int Score(int src, int dst)
    {
        var s = S(src); var d = S(dst);
        int cap = s.粮草 / 4 * 1000 / 45;
        int v = (cap >= s.兵力 ? s.兵力 - d.兵力 : cap - d.兵力);
        return v + (R(src) - R(dst)) * 200;
    }
    int GoNoGo(int score)
    {
        int n = score / 1000;
        int p = n == 1 ? 20 : n == 2 ? 50 : n == 3 ? 70 : 80;
        if (Rnd(100) > p) return 0;
        return n;
    }
    WarPlan MakeArmy(int n)
    {
        var home = A(au); var s = S(au); var d = S(av);
        var army = new List<Officer>();
        int acc = -(d.兵力 + R(av) * 200);
        int thr = Rnd(n) * 1000;
        var avail = new List<Officer>(home);
        while (avail.Count > 1)                                    // 至少留一人在家
        {
            var o = avail[Rnd(avail.Count)];
            avail.Remove(o); army.Add(o);
            acc += o.Troops;
            if ((acc += 200) >= thr) break;
        }
        if (army.Count == 0) return null;
        foreach (var o in army) home.Remove(o);
        // ar(au)：太守被抽走了，忠诚最高的留守者当太守
        if (!home.Exists(o => o.Name == s.governor) && home.Count > 0)
        {
            var g = home[0]; foreach (var o in home) if (o.Loyal > g.Loyal) g = o;
            Personnel.Appoint(s, g);
        }
        Military.Recount(s);
        // e(army,cnt)：君主随军就当主将，否则智力最高的
        var lord = Realm.Ruler(me);
        var lead = army.Contains(lord) ? lord : army[0];
        if (lead != lord) foreach (var o in army) if (o.Int > lead.Int) lead = o;
        army.Remove(lead); army.Insert(0, lead);
        // c()：粮 30t..60t，至少带城里一半
        int t = 0; foreach (var o in army) t += o.Troops;
        t = t * 4 / 1000 + 1;
        int food = Rnd(t * 30) + t * 30;
        if (food < s.粮草 / 2) food = s.粮草 / 2; else if (food >= s.粮草) food = s.粮草 * 2 / 3;
        int gold = s.黄金 < 2 ? 0 : Rnd(s.黄金 / 2);
        s.黄金 -= gold; s.粮草 -= food;
        return new WarPlan { From = au, To = av, Army = army, Gold = gold, Food = food };
    }

    // ── 征兵：cc() 11751 + bD() 9923 + bC() 9912 ─────────────────────────
    bool Draft()
    {
        if (Rnd(3) > 1) return false;
        int best = 0; int pick = 0;
        foreach (int c in front)
        {
            var s = S(c);
            int need = R(c) * 1000 - s.兵力;
            if (need > 0 && (s.黄金 >= 50 || s.预备兵 > 0))
            {
                int afford = (s.黄金 - 50) * 5;
                if (need > afford) need = afford;
                if (need >= best) { pick = c; best = need; }
            }
        }
        if (best <= 0) return false;
        var st = S(pick);
        int cap = R(pick) * 1000, def = cap - st.兵力, rest = def - st.预备兵;
        int u;
        if (rest <= 0) { u = cap; st.预备兵 -= def; }
        else
        {
            u = st.兵力 + st.预备兵;
            int cost = ((rest - 1) / 100 + 1) * 20;
            if (st.黄金 >= cost) { st.黄金 -= cost; u = cap; st.预备兵 = cost * 5 - rest; }
            else { st.预备兵 = 0; cost = st.黄金 / 20 * 20; u += cost * 5; st.黄金 -= cost; }
        }
        // bC()：前 u/1000 员满 1000，下一员拿余数；其后的清零（B5：原版留着旧数，总兵对不上）
        var a = A(pick);
        for (int i = 0; i < a.Count; i++) a[i].Troops = i < u / 1000 ? 1000 : i == u / 1000 ? u % 1000 : 0;
        Military.Recount(st);
        return true;
    }

    // ── 调将：cd() 11824 + d() 11791 + at() 11777 + D() 11853 ─────────────
    int Threat(int c)
    {
        int n = -20000; var s = S(c);
        foreach (int nb in Military.Adjacent(c)) n = Mathf.Max(n, S(nb).兵力 + R(nb) * 200 - s.兵力 - R(c) * 200);
        return n;
    }
    int Donor(int c, int th, out int donor)
    {
        donor = 0; int most = 1, lim = th - 1999;
        foreach (int nb in Military.Adjacent(c))
        {
            if (Owner(nb) != Owner(c) || R(nb) <= 1) continue;
            if (!IsFront(nb)) { if (R(nb) > most) { donor = nb; most = R(nb); } }
            else if (most == 1 && th > 0) { int t = Threat(nb); if (t < lim) { donor = nb; lim = t; } }
        }
        if (most > 1) return most - 1;
        if (lim < th - 1999) { int t = (th - lim) / 2; return t > th ? th : t; }
        return 0;
    }
    int shiftFrom;
    int Shift()
    {
        au = av = 0;
        if (Rnd(3) > 1) return 0;
        int best = -20000, amt = 0;
        foreach (int c in front)
        {
            if (R(c) == Politics.MaxInCity) continue;
            int th = Threat(c);
            if (th > best) { int t = Donor(c, th, out int dn); if (t != 0) { au = c; best = th; amt = t; shiftFrom = dn; } }
        }
        return amt;
    }
    void DoShift(int n)
    {
        var to = A(au); var from = A(shiftFrom); var fs = S(shiftFrom); var ts = S(au);
        void MoveOne()
        {
            var o = from[from.Count - 1];                          // 从名册尾巴上搬
            from.RemoveAt(from.Count - 1); to.Add(o); o.City = au;
            if (o.Name == fs.governor && from.Count > 0) Personnel.Appoint(fs, from[0]);   // B10
        }
        if (n < 50)
            for (int k = 0; k < n && from.Count > 1 && to.Count < Politics.MaxInCity; k++) MoveOne();   // B4：至少留一人
        else
        {
            int acc = 0;
            while (acc < n && from.Count > 1 && to.Count < Politics.MaxInCity)
            { acc += 200 + from[from.Count - 1].Troops; MoveOne(); }
        }
        Military.Recount(fs); Military.Recount(ts);
    }

    // ── 医馆：ch() 12175 + bJ() ───────────────────────────────────────────
    bool Clinic()
    {
        if (Rnd(3) > 1) return false;
        foreach (int c in front)
        {
            var s = S(c);
            if (!s.facil.yaolu || s.黄金 < 100) continue;
            foreach (var o in A(c))
                if (o.HpMax - 40 > o.Hp) { Market.Heal(s, o, rng); return true; }
        }
        return false;
    }

    // ── 奖赏：ce() 11915 + bM() 10170 ─────────────────────────────────────
    bool Reward()
    {
        if (Rnd(2) > 0) return false;
        int minP = 100; MapData.Stat at = null; Officer who = null;
        foreach (var c in Realm.Cities(map, me))
        {
            var s = S(c.id);
            if (s.黄金 < 200 && s.宝物 == 0) continue;
            foreach (var o in A(c.id)) if (o.Loyal < minP) { at = s; who = o; minP = o.Loyal; }
        }
        if (who == null || minP >= 70 || (minP > 35 && Rnd(2) > 0)) return false;
        Personnel.Reward(at, who, at.宝物 > 0 ? Personnel.Treasure : 100, rng);
        return true;
    }

    // ── 输送：cf() 12086 + au/av/aw/ax(byte) + x() 10235 ──────────────────
    int convoyFrom;
    int Pick(int c, System.Func<MapData.Stat, int> val, int rich, int fGap, int fMin, int iMin, bool vsSelf)
    {
        int bestI = -1, bestF = -1, di = 0, df = 0;
        foreach (int nb in Military.Adjacent(c))
        {
            if (Owner(nb) != Owner(c)) continue;
            int v = val(S(nb));
            if (!IsFront(nb)) { if (v > bestI) { bestI = v; di = nb; } }
            else if (bestI < rich && v > bestF) { bestF = v; df = nb; }
        }
        if (bestI >= rich) { convoyFrom = di; return 0; }
        int baseline = vsSelf ? val(S(c)) : bestI;
        if (bestF - baseline >= fGap && bestF >= fMin) { convoyFrom = df; return 1; }
        if (bestI >= iMin) { convoyFrom = di; return 0; }
        return -1;
    }
    int Convoy()
    {
        int mode = 0, key = 10000, n = 0, from = 0;
        au = 0;
        Rnd(2);                                                     // B2：原版这道门永远不关，只白耗一次随机数
        foreach (int c in front)
        {
            var s = S(c); int t;
            if (s.粮草 < 200 && s.粮草 < key && (t = Pick(c, x => x.粮草, 1000, 500, 600, 400, false)) >= 0)
            { au = c; key = s.粮草; n = 4; mode = t; from = convoyFrom; }
            else if (n < 4 && s.黄金 < 200 && s.黄金 < key && (t = Pick(c, x => x.黄金, 1000, 500, 600, 400, false)) >= 0)
            { au = c; key = s.黄金; n = 3; mode = t; from = convoyFrom; }
            else
            {
                int sum = s.黄金 + s.粮草;
                if (sum < key)
                {
                    if (n < 3 && sum < 1000 && (t = Pick(c, x => x.黄金 + x.粮草, 1500, 800, 1500, 800, false)) >= 0)
                    { au = c; key = sum; n = 2; mode = t; from = convoyFrom; }
                    else if (n < 2 && sum < 4000 && (t = Pick(c, x => x.黄金 + x.粮草, 2000, 1000, 3000, 1000, true)) >= 0)
                    { au = c; key = sum; n = 1; mode = t; from = convoyFrom; }
                }
            }
        }
        convoyFrom = from;
        if (n > 2 || (n == 2 && Rnd(2) > 0) || (n == 1 && Rnd(5) > 3)) return mode;
        return -1;
    }
    void DoConvoy(int mode)
    {
        var to = S(au); var fr = S(convoyFrom);
        int g, f;
        if (mode == 0) { g = fr.黄金 - 100; f = fr.粮草 - 100; }
        else { g = fr.黄金 - (to.黄金 + fr.黄金) / 2; f = fr.粮草 - (to.粮草 + fr.粮草) / 2; }
        if (g > 0) { to.黄金 += g; fr.黄金 -= g; }
        if (f >= 0) { to.粮草 += f; fr.粮草 -= f; }
    }

    // ── 杂项：cg() 12136 ─────────────────────────────────────────────────
    void Misc()
    {
        int c;
        if ((Rnd(10) > 6 && inner.Count > 0) || front.Count == 0)
        { if (inner.Count == 0) return; c = inner[Rnd(inner.Count)]; }
        else c = front[Rnd(front.Count)];
        var s = S(c);
        if (R(c) == 0) return;
        int n = (s.黄金 >= 50 && (s.农业 < 999 || s.商业 < 999 || s.人口 < 990000 || s.治水 < 99)) ? 460 : 220;
        for (int k = 0; k < 10; k++)
        {
            int r2 = Rnd(n);
            if (r2 < 40) { if (Bazaar(c)) return; }
            else if (r2 < 120) { if (Intrigue(c)) return; }
            else if (r2 < 220) { SearchOrHire(c); return; }
            else { Develop(c); return; }
        }
        SearchOrHire(c);
    }

    /// aj(c) 10274：有粮店就按固定价买卖粮；否则有学院就送武力最高、智力 < 80 的去修习
    bool Bazaar(int c)
    {
        var s = S(c);
        if (s.facil.changping)
        {
            if (s.黄金 < 100 && s.粮草 > 500) { s.粮草 -= 200; s.黄金 += 150; return true; }
            if (s.粮草 < 200 && s.黄金 > 500) { s.黄金 -= 200; s.粮草 += 266; return true; }
        }
        if (s.黄金 < 50 || !s.facil.junxue) return false;
        Officer who = null; int bestM = 0;
        foreach (var o in A(c)) if (bestM < o.Might && o.Int < 80) { who = o; bestM = o.Might; }
        if (who == null) return false;
        int fee = Market.Tuition(who);                            // B3：原版拿城号当武将号查智力
        s.黄金 -= fee;
        who.Int += (who.Int < 30 || who.Int > 70) ? Rnd(5) + 6 : Rnd(7) + 6;
        return true;
    }

    /// ak(c) 10331：全图找忠诚最低的外家武将；自家城没满且对方忠诚 < 15（或 < 35 对半）就招揽，否则离间
    bool Intrigue(int c)
    {
        int minP = 100; int tc = 0; Officer tgt = null;
        foreach (var cc in map.cities)
        {
            if (cc.owner == Owner(c) || cc.owner == "none") continue;
            foreach (var o in A(cc.id)) if (minP > o.Loyal) { tc = cc.id; tgt = o; minP = o.Loyal; }
        }
        if (tgt == null || minP >= 100) return false;
        var a = A(c); var meO = a[0];
        foreach (var o in a) if (meO.Int < o.Int) meO = o;
        bool vsPlayer = Owner(tc) == Realm.Player;
        if (R(c) < Politics.MaxInCity && (minP < 15 || (minP < 35 && Rnd(2) > 0)))
        {
            if (Diplomacy.Poach(map, c, tc, meO, tgt, rng) && vsPlayer)
                Event?.Invoke("defect", "情报", "糟了," + tgt.Name + "投奔了" + Realm.Ruler(me)?.Name, tgt);   // aO 17（B7：弃城也弹）
        }
        else
        {
            if (vsPlayer) Event?.Invoke("talks", "情报", tgt.Name + "正与敌方会谈中...", tgt);                  // aO 16
            Diplomacy.Sow(meO, tgt, rng);
        }
        return true;
    }

    /// am(c) 10499 / al(c) 10476：有已发现的在野就登用，否则搜索；办事的是智力 ≥ 70 里随机一人（没有就取智力最高）
    void SearchOrHire(int c)
    {
        var a = A(c); var s = S(c);
        Officer best = a[0]; var pool = new List<Officer>();
        foreach (var o in a) { if (best.Int < o.Int) best = o; if (o.Int >= 70) pool.Add(o); }
        var who = pool.Count > 0 ? pool[Rnd(pool.Count)] : best;
        var ct = Personnel.Of(c);
        if (a.Count < Politics.MaxInCity && ct.C.Count > 0)
        {
            var t = ct.C[Rnd(ct.C.Count)];
            rng.Next();                                            // j() 里那次被丢掉的随机数，AI 这边照耗
            Personnel.Recruit(s, who, t, rng);
            return;
        }
        Personnel.Search(s, who, rng);
    }

    /// ao(c) 10618 / an(c) 10588：治水 < 60 先治水；否则对半开垦 / 劝商（1~3、10~12 月开垦），再巡查，最后治水
    void Develop(int c)
    {
        var s = S(c); var a = A(c);
        Officer best = a[0]; var pool = new List<Officer>();
        foreach (var o in a) { if (best.Int < o.Int) best = o; if (o.Int >= 70) pool.Add(o); }
        var who = pool.Count > 0 ? pool[Rnd(pool.Count)] : best;
        Politics.Cmd cmd;
        if (s.治水 < 60) cmd = Politics.Cmd.Water;
        else if (Rnd(2) > 0 && (s.农业 < 999 || s.商业 < 999))
        {
            if (s.农业 < 999 && s.商业 < 999) cmd = (Month < 4 || Month >= 10) ? Politics.Cmd.Reclaim : Politics.Cmd.Commerce;
            else cmd = s.农业 < 999 ? Politics.Cmd.Reclaim : Politics.Cmd.Commerce;
        }
        else cmd = s.人口 < Politics.MaxPeople ? Politics.Cmd.Patrol : Politics.Cmd.Water;
        int gold = Mathf.Min(cmd == Politics.Cmd.Water ? Politics.CostU(who, rng) : Politics.CostT(who, rng), s.黄金);
        Politics.Run(cmd, s, who, gold, rng);
    }

    public int Month;                                              // 原版 aC，内政挑开垦还是劝商要看月份
}
