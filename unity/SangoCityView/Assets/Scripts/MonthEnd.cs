// 月末结算：原版 b.aC() 6719，逐步照抄（规格 docs/MONTHEND_SPEC.md）。
//   1 胜负判定（在月份 +1 之前）→ 2 月份 +1 → 3 旱 / 水 / 蝗（新月份 3~10，每城 4/500）
//   → 4 没有这三灾才掷瘟疫（每城 2/1000）→ 6 民变（有主之城，按统治分档）→ 7 AI 城自然增长
//   → 8 新月份 4 收金 / 10 收粮（36 城全收，空城也收）→ 9 武将体力回上限的 1/10
//   → 10 同盟倒计时 → 11 彩蛋（210 年起）
// 灾害的损失随治水**升高**（q·(值/2)/90，q ≥ 90 才免金粮统治）——原版就是这样，照抄。
//
// 修掉的原版 bug：同盟到期删表抄了 X[W]（越界 / 丢掉另一条盟约）；北斗体力上限 120 回血时 byte 溢出成负。
using System.Collections.Generic;
using UnityEngine;

public static class MonthEnd
{
    public struct Ev { public string Key, Title, Text; }

    static int Half(int v, int q, int den) => q * (v / 2) / den;        // a(int,byte,int) 6415

    /// 返回 0 = 照常；1 = 玩家统一（胜）；-1 = 玩家无城（败）。事件按原版顺序追加进 evs
    public static int Run(MapData map, Dictionary<int, Officer> all, ref int month, int year,
                          System.Random rng, List<Ev> evs)
    {
        // 1 胜负（旧月份）az() / aA()
        if (map.cities.TrueForAll(c => c.owner == Realm.Player)) return 1;
        if (Realm.CityCount(map, Realm.Player) == 0) return -1;
        // 2 月份 +1
        month++;
        var hit = new List<MapData.Stat>();
        // 3 旱水蝗 av()：新月份 3..10
        if (month > 2 && month < 11)
            foreach (var s in map.stats) if (rng.Next(500) < 4) hit.Add(s);
        if (hit.Count > 0)
        {
            foreach (var s in hit) Disaster(s);
            int kind = rng.Next(3);                                        // 只决定文案和图，损失一样
            string[] key = { "drought", "flood", "locust" };
            string[] txt = { " 发生旱灾!波及城市:", "发生水灾!波及城市:", "发生蝗灾!波及城市:" };
            evs.Add(new Ev { Key = key[kind], Title = new[] { "旱灾", "水灾", "蝗灾" }[kind], Text = txt[kind] + Names(hit) });
        }
        else
        {
            // 4 瘟疫 aw()
            foreach (var s in map.stats) if (rng.Next(1000) <= 1) hit.Add(s);
            if (hit.Count > 0)
            {
                foreach (var s in hit) Plague(s);
                evs.Add(new Ev { Key = "plague", Title = "瘟疫", Text = "发生瘟疫!波及城市:" + Names(hit) });
            }
        }
        // 6 民变 F() / H()：只看有主之城，先全掷完再一起结算
        hit.Clear();
        foreach (var s in map.stats) if (s.owner != "none" && RiotRoll(s, rng)) hit.Add(s);
        foreach (var s in hit) Riot(s, rng);
        if (hit.Count > 0) evs.Add(new Ev { Key = "riot", Title = "骚乱", Text = "百姓发生骚乱,波及城市:" + Names(hit) });
        // 7 AI 自然增长 aB() → K(c)：活着的非玩家势力，逐城
        foreach (var f in Realm.All)
            if (f.Alive && f.Key != Realm.Player)
                foreach (var c in Realm.Cities(map, f.Key)) Grow(map.stats.Find(x => x.id == c.id), rng);
        // 8 收获 I() / J()：36 城全收
        if (month == 4)
        {
            foreach (var s in map.stats) s.黄金 = Mathf.Min(s.黄金 + Harvest(s.商业, s.治安, s.人口), 9999);
            evs.Add(new Ev { Key = "gold", Title = "收金", Text = "收金的季节到了!" });
        }
        else if (month == 10)
        {
            foreach (var s in map.stats) s.粮草 = Mathf.Min(s.粮草 + Harvest(s.农业, s.治安, s.人口), 9999);
            evs.Add(new Ev { Key = "harvest", Title = "收粮", Text = "收粮的季节到了!" });
        }
        // 9 体力回复：上限的 1/10（北斗上限 120 原版会溢出成负，这里夹住）
        foreach (var o in all.Values) if (o.Hp < o.HpMax) o.Hp = Mathf.Min(o.Hp + o.HpMax / 10, o.HpMax);
        // 10 同盟倒计时（原版删表抄了 X[W]，这里删对那一条）
        for (int i = Realm.Pacts.Count - 1; i >= 0; i--)
        {
            var p = Realm.Pacts[i];
            if (--p.Months > 0) continue;
            Realm.Pacts.RemoveAt(i);
            var f = Realm.Of(p.With);
            if (f != null && f.Alive) evs.Add(new Ev { Key = "breakup", Title = "同盟解除", Text = f.Name + "　同盟解除!" });
        }
        // 11 彩蛋 ay()：210 年起，襄阳 / 云南在玩家手里时，张郃（55）/ 金环三结（193）四维变 99
        if (year >= 210)
        {
            bool a = rng.Next(100) < 2, b = rng.Next(100) < 3;
            Egg(map, all, a, 14, 55); Egg(map, all, b, 2, 193);          // 城 14 / 城 2（三版代码一样，按城号不按城名）
        }
        Military.RecountAll(map);
        return 0;
    }

    static string Names(List<MapData.Stat> l) { var sb = new System.Text.StringBuilder(); foreach (var s in l) sb.Append(" ").Append(s.name); return sb.ToString(); }

    /// D(c) 6419
    static void Disaster(MapData.Stat s)
    {
        int q = s.治水;
        if (q < 90) { s.黄金 -= Half(s.黄金, q, 90); s.粮草 -= Half(s.粮草, q, 90); s.治安 -= Half(s.治安, q, 90); }
        if (q < 99) { s.商业 -= Half(s.商业, q, 99); s.农业 -= Half(s.农业, q, 99); }
        if (q > 0) s.治水 = q - (q / 10 + 1);
    }

    /// E(c) 6445：各将兵力、预备兵、统治、人口
    static void Plague(MapData.Stat s)
    {
        int q = s.治水;
        if (q < 90)
        {
            foreach (var o in Personnel.Of(s.id).A) o.Troops -= Half(o.Troops, q, 90);
            s.预备兵 -= Half(s.预备兵, q, 90);
            s.治安 -= Half(s.治安, q, 90);
        }
        if (q < 99) s.人口 -= Half(s.人口, q, 99);
        if (q > 0) s.治水 = q - (q / 10 + 1);
        Military.Recount(s);
    }

    /// F(c) 6471：统治越低越容易乱；≥ 60 不掷
    static bool RiotRoll(MapData.Stat s, System.Random rng)
    {
        int k = s.治安;
        if (k < 15) return rng.Next(100) >= k * 10 / 15 + 80;
        if (k < 30) return rng.Next(100) >= (k - 15) / 3 + 90;
        if (k < 40) return rng.Next(100) >= (k - 30) / 5 + 98;
        if (k < 60) return rng.Next(1000) >= (k - 40) / 5 + 998;
        return false;
    }
    static int Cut(int v, int num, int den, System.Random rng) { int m = v * num / den; return m > 0 ? v - rng.Next(m) : v; }

    /// H(c) 6512：按进门时的统治分三档砍人口 / 农商 / 金粮 / 预备兵 / 各将兵力，统治再掉 0~14
    static void Riot(MapData.Stat s, System.Random rng)
    {
        int k = s.治安;
        int vn, vd;
        if (k < 15) { s.人口 /= 3; s.农业 /= 3; s.商业 /= 3; s.黄金 /= 2; s.粮草 /= 2; vn = 2; vd = 3; }
        else if (k < 30) { s.人口 /= 2; s.农业 /= 2; s.商业 /= 2; s.黄金 = s.黄金 * 3 / 7; s.粮草 = s.粮草 * 3 / 7; vn = 1; vd = 2; }
        else { s.人口 = s.人口 * 2 / 3; s.农业 = s.农业 * 2 / 3; s.商业 = s.商业 * 2 / 3; s.黄金 = s.黄金 / 7; s.粮草 = s.粮草 / 7; vn = 1; vd = 5; }
        s.治安 = Mathf.Max(0, s.治安 - rng.Next(15));                     // G(c)
        s.预备兵 = Cut(s.预备兵, vn, vd, rng);
        foreach (var o in Personnel.Of(s.id).A) o.Troops = Cut(o.Troops, vn, vd, rng);
        Military.Recount(s);
    }

    /// K(c) 6656：AI 城每月白涨一项，统治低的再补一点
    static void Grow(MapData.Stat s, System.Random rng)
    {
        int n = rng.Next(4), n2 = rng.Next(5) + 1;
        switch (n)
        {
            case 0: s.农业 = Mathf.Min(s.农业 + n2, 999); break;
            case 1: s.商业 = Mathf.Min(s.商业 + n2, 999); break;
            case 2: s.人口 = Mathf.Min(s.人口 + n2 * 500, 990000); break;
            case 3: s.治水 = Mathf.Min(s.治水 + (n2 / 2 + 1), 99); break;
        }
        int r = rng.Next(int.MaxValue);
        if (s.治安 < 30) s.治安 += r % 4 + 2; else if (s.治安 < 40) s.治安 += r % 2 + 1; else if (s.治安 < 60) s.治安 += r % 2;
    }

    /// b(dev,k,p) 6572：人口定底数，开发度加成，统治高再给红利
    public static int Harvest(int dev, int k, int p)
    {
        int b = 125 * p / 20000;
        b += b * dev * 2 / 5 / 125;
        int bonus = k > 90 ? b * 4 / 5 : k > 80 ? b * 3 / 5 : k > 70 ? b * 2 / 5 : k > 50 ? b / 5 : 0;
        return b + bonus;
    }

    static void Egg(MapData map, Dictionary<int, Officer> all, bool roll, int city, int id)
    {
        if (!roll) return;
        var c = map.cities.Find(x => x.id == city);
        if (c == null || c.owner != Realm.Player || !all.TryGetValue(id, out var o)) return;
        if (!Personnel.Of(c.id).A.Contains(o)) return;
        o.Int = o.Virtue = o.Might = o.HpMax = 99;
    }

    // ── 结局评分：b.k() 616 ────────────────────────────────────────────
    static int Interp(int x, int lo, int hi, int a, int b) => (x - lo) * (b - a + 1) / (hi - lo + 1) + a;

    public class Score { public int[] Tier = new int[7]; public int Raw; }
    static readonly string[][] Verdict = {
        new[]{ "极度贫瘠的土地, 根本无法生产出粮食,饿殍遍野,百姓苦不堪言", "土地资源非常匮乏,粮食收成非常低下,各地百姓饿死现象时有发生,无法继续生活下去",
               "农业发展较为落后,粮食产量仍无法满足百姓的生活,各地田里,地里一片菜色", "随着农业地逐步发展,粮食产量基本达到了百姓生活地需求",
               "随着土地地不断开发,粮食产量已较为富裕,百姓吃饭问题已完全解决了", "新的农业工具地不断出现,在土地作业中的逐渐普及,各地粮食已极为充裕",
               "农业发展空前,粮食年年丰产,人民衣食无忧" },
        new[]{ "极度落后的商业,人们最基本的物品交换都无法进行.国家贫苦不堪", "商业资源非常贫乏,人们无法得到最基本的生活用品,生活非常艰辛",
               "商业发展仍较为落后,商务活动较少, 百姓生活水平非常低下", "随着商业地逐步发展,商务活动基本达到了百姓生活地需求",
               "随着官府的不断重视,商业发展已较为发达,国家经济状况有了很大改善", "与外国的经济往来,使得国家商务发展极度发达,百姓生活在了一个富裕的国家中",
               "国家的经济发展举世罕见, 新的领域的经济往来不断增强本国的经济实力" },
        new[]{ "瘟疫,蝗虫,旱涝,各种灾害遍地,民不聊生", "瘟疫等这种灾害仍时有发生,百姓灾难深重", "由于新国家官府的一定重视,灾害在一定程度上得到了遏制",
               "经过官府的不断努力,灾害防御措施逐渐实施,百姓基本远离了灾害痛苦", "大坝建成,各种治理灾害措施井然有序,百姓再无灾害之忧" },
        new[]{ "经过多年的战争,各地一片荒芜,各城市人丁稀少,百里难见一人", "战后恢复仍没有足够的进行,各城市劳动力资源仍稍显不足",
               "战争的硝烟逐渐远去,各城市人口资源已完全满足了劳动的需求", "国家开始重视了城市的建设,城市规模发展稳定,大型城市逐步兴起",
               "经过国家多方面努力,城市向都市化发展开始,各城市欣欣向荣", "各地发展平衡,都市化发展完成,国家发展空前,百姓安居乐业" },
        new[]{ "部下野心勃勃,叛变,反乱事件层出不穷,国家马上将被颠覆", "属下人心浮动,流言四起,某些武将的叛乱似乎已在眼前",
               "大多武将忠于君主,但奸臣当道,叛变的种子仍远未消失", "君主赏罚分明,恩威并施,下属武将对国家较为忠诚",
               "君主善于御人之道, 武将们对君主, 国家忠心耿耿", "以恩义著称的新君主,得到了属下誓死效忠,各武将绝无二心",
               "经过多年的同生共死,臣僚们用鲜血来发誓,子子孙孙誓死效忠君主" },
        new[]{ "新国家的所作所为,百姓深恶痛疾,各地烽烟四起,起义遍地,无法控制", "新国家并没有实施仁政,百姓对新国家非常不满, 起义的种子四处萌发",
               "新国家的仁政思想逐渐传播,逐渐使得百姓对新的国家基本上认可了", "君主一向以仁政而闻名,国家建立以来,得到了各地百姓的支持和欢迎",
               "君主贤名远播,管理国家政策得当,施以百姓的仁政深入人心", "全国百姓坚决拥护新的国家,君主已成为大众的偶像,和精神砥柱" },
        new[]{ "多年战争造成国家兵甲极度不足,远远无法抵御外敌侵略,随时有灭国危险", "战后兵甲仍然不足,无法抵御匈奴等外敌的大规模进攻,国家岌岌可危",
               "匈奴,外族虎视耽耽,全部兵丁已派往前线,与强大的敌人达成微弱的军事平衡", "在战争积累了足够军事经验,国家军事实力稳定,在随后的战争中数次战败外侵略者",
               "武器,阵法的更新,将领的培养,使得军事实力强大,征服了许多周围的小国家", "军事实力空前,对周边国家形成了强大的震慑作用.各国纷纷遣使进贡,或从属.强大的帝国已经产生" },
    };
    public static readonly string[] Aspect = { "农业", "商业", "治水", "人口", "忠诚", "民心", "军事" };
    public static string VerdictOf(Score s, int row) => Verdict[row][Mathf.Clamp(s.Tier[row], 0, Verdict[row].Length - 1)];

    /// b.k() 616：七项（a..g）各定一档并加分，再加 h/i/j 三项只加分。
    /// 原版最后一行 cR = aO*3/2 把分数抹成 0（aO 此时是 0）——这里按本意 cR*3/2 显示
    public static Score Evaluate(MapData map)
    {
        var sc = new Score(); int raw = 0;
        double Avg(System.Func<MapData.Stat, int> f) { long t = 0; foreach (var s in map.stats) t += f(s); return t / 36; }
        int n = (int)Avg(s => s.农业), o = (int)Avg(s => s.商业), q = (int)Avg(s => s.治水), p = (int)Avg(s => s.人口), k = (int)Avg(s => s.治安);
        // a() 农业 / b() 商业
        foreach (var (v, row) in new[] { (n, 0), (o, 1) })
        {
            int t, add;
            if (v >= 999) { t = 6; add = 100; } else if (v >= 900) { t = 5; add = Interp(v, 900, 998, 85, 90); }
            else if (v >= 700) { t = 4; add = Interp(v, 700, 899, 80, 84); } else if (v >= 500) { t = 3; add = Interp(v, 500, 699, 60, 79); }
            else if (v >= 200) { t = 2; add = Interp(v, 200, 499, 30, 59); } else if (v >= 100) { t = 1; add = Interp(v, 100, 199, 1, 29); }
            else { t = 0; add = 0; }
            sc.Tier[row] = t; raw += add;
        }
        // c() 治水
        { int t, add; if (q >= 99) { t = 4; add = 30; } else if (q >= 90) { t = 3; add = Interp(q, 90, 98, 10, 20); }
          else if (q >= 70) { t = 2; add = Interp(q, 70, 89, 5, 9); } else if (q >= 50) { t = 1; add = Interp(q, 50, 69, 1, 4); } else { t = 0; add = 0; }
          sc.Tier[2] = t; raw += add; }
        // d() 人口
        { int t, add; if (p >= 990000) { t = 5; add = 100; } else if (p >= 500000) { t = 4; add = Interp(p, 500000, 989999, 80, 90); }
          else if (p >= 200000) { t = 3; add = Interp(p, 200000, 499999, 70, 79); } else if (p >= 100000) { t = 2; add = Interp(p, 100000, 199999, 50, 69); }
          else if (p >= 50000) { t = 1; add = Interp(p, 50000, 99999, 1, 49); } else { t = 0; add = 0; }
          sc.Tier[3] = t; raw += add; }
        // e() 民心（统治）
        { int t, add; if (k >= 99) { t = 5; add = 70; } else if (k >= 90) { t = 4; add = Interp(k, 90, 98, 40, 60); }
          else if (k >= 80) { t = 3; add = Interp(k, 80, 89, 30, 39); } else if (k >= 60) { t = 2; add = Interp(k, 60, 79, 10, 29); }
          else if (k >= 30) { t = 1; add = Interp(k, 30, 59, 1, 9); } else { t = 0; add = 0; }
          sc.Tier[5] = t; raw += add; }
        // f() 军事：各城除太守（A[0]）外武将兵力和 / 222；g() 忠诚：同样的人（城 1..35）−100 + 忠诚和 / 221
        long troops = 0, loyal = -100, exp = 0, iq = 0; int highIq = 0; int j = 0; int legend14 = 0;
        foreach (var c in map.cities)
        {
            var a = Personnel.Of(c.id).A;
            for (int i = 1; i < a.Count; i++)
            {
                var off = a[i]; troops += off.Troops;
                if (c.id == 36) continue;
                loyal += off.Loyal; exp += off.Exp;
                if (off.Int < 90) iq += off.Int; else highIq++;
                if (off.ArmLv == 15 || off.ArmLv == 23) j += 45; else if (off.ArmLv == 22) j += 55;
                else if (off.ArmLv >= 5 && off.ArmLv <= 7) j += 100; else if (off.ArmLv == 14 && legend14++ < 3) j += 100;
                if (off.SubLv == 30) j += 55; else if (off.SubLv == 31) j += 100;
            }
        }
        { int v = (int)(troops / 222), t, add;
          if (v >= 1000) { t = 5; add = 100; } else if (v >= 900) { t = 4; add = Interp(v, 900, 999, 60, 80); }
          else if (v >= 800) { t = 3; add = Interp(v, 800, 899, 50, 59); } else if (v >= 700) { t = 2; add = Interp(v, 700, 799, 20, 49); }
          else if (v >= 500) { t = 1; add = Interp(v, 500, 699, 1, 19); } else { t = 0; add = 0; }
          sc.Tier[6] = t; raw += add; }
        { int v = (int)(loyal / 221), t, add;
          if (v >= 99) { t = 6; add = 100; } else if (v >= 95) { t = 5; add = Interp(v, 95, 98, 50, 70); }
          else if (v >= 90) { t = 4; add = Interp(v, 90, 94, 30, 49); } else if (v >= 80) { t = 3; add = Interp(v, 80, 899, 29, 29); }
          else if (v >= 60) { t = 2; add = Interp(v, 60, 79, 10, 19); } else if (v >= 36) { t = 1; add = Interp(v, 36, 59, 1, 9); } else { t = 0; add = 0; }
          sc.Tier[4] = t; raw += add; }
        // h() 经验
        { int v = (int)(exp / 222);
          raw += v >= 30000 ? 100 : v >= 20000 ? Interp(v, 20000, 29999, 50, 70) : v >= 15000 ? Interp(v, 15000, 19999, 30, 49)
               : v >= 10000 ? 29 : v >= 7000 ? Interp(v, 7000, 9999, 1, 19) : 0; }
        // i() 智力
        { int v = (int)(iq / 222) - highIq;
          raw += v >= 89 ? 100 : v == 88 ? 80 : v == 87 ? 60 : v == 86 ? 50 : v == 85 ? 41 : v >= 80 ? Interp(v, 80, 84, 20, 40)
               : v >= 70 ? Interp(v, 70, 79, 1, 19) : 0; }
        // j() 名器
        raw += j * 100 / 800;
        sc.Raw = raw;
        return sc;
    }
}
