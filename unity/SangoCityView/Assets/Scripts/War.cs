// 战争：从城里发兵到打完回城的战略层这一段。战场本身（战棋 / 阵型战 / 单挑）在 BattleField 那一套里。
// 照原版 af(byte) 9067 + 步骤表 bR[3] = {2,3,6,3,83,3,84,85,0,1}：
//   选目标城（相邻、不是自家的，空城也行）→ 勾选出征武将 → [全走：「是否放弃此城市？」]
//   → 屏 14 带多少金粮 → [太守出征：「请任命新太守」/ 太守留守：「请任命军队主将」]
//   → 主将「誓死拿下敌军城池!」→ bk()：扣金粮；空城直接占，否则开战 → 打完 L() / A()+D() 善后 → 扣令
//
// 修掉的原版 bug（docs/HOUSE_RULES.md §战争）：
//   K() 3337 俘虏入列那段 n 不自增会死循环；C() 2877 删城自赋值；bE 不复位导致金粮翻倍；
//   z() 1981 开战不清机动力。
using System.Collections.Generic;
using UnityEngine;

public static partial class War
{
    public const int MaxGoldFood = 9999;

    /// af(byte) 9067：相邻城里至少一座不是自家的、也不是盟友的（空城也算）
    public static List<MapData.City> Targets(MapData map, MapData.City from)
    {
        var list = new List<MapData.City>();
        foreach (int id in Military.Adjacent(from.id))
        {
            var c = map.cities.Find(x => x.id == id);
            if (c != null && c.owner != from.owner && !Realm.Allied(c.owner)) list.Add(c);
        }
        return list;
    }

    /// 出征的一队人马
    public class Sortie
    {
        public MapData.City From, To;
        public List<Officer> Army = new();        // [0] 是主将
        public int Gold, Food;
        public bool Abandoned;                    // 全城都走了，出发城成了空城
    }

    /// 屏 14 确认之后、选主将之前：人从出发城名册里拿掉（bo()），全走就弃城（bq()）
    public static void Depart(MapData map, Sortie s)
    {
        var fs = Stat(map, s.From);
        var a = Personnel.Of(s.From.id).A;
        foreach (var o in s.Army) a.Remove(o);
        if (a.Count == 0) { Military.Abandon(map, fs); s.Abandoned = true; }
        Military.Recount(fs);
    }

    /// bk() 8186：这时才从出发城扣金粮；目标是空城就不打，直接进城
    public static bool Launch(MapData map, Sortie s, string faction, string lord)
    {
        var fs = Stat(map, s.From);
        fs.黄金 -= s.Gold; fs.粮草 -= s.Food;
        if (s.To.owner != "none") return true;                         // 要开战
        var ts = Stat(map, s.To);
        Occupy(map, s.To, faction, lord, s.Army[0]);
        var a = Personnel.Of(s.To.id).A;
        foreach (var o in s.Army) { a.Add(o); o.City = s.To.id; }
        ts.黄金 = Mathf.Min(ts.黄金 + s.Gold, MaxGoldFood);            // 原版这里不封顶，其它地方都封 9999，统一封顶
        ts.粮草 = Mathf.Min(ts.粮草 + s.Food, MaxGoldFood);
        Military.Recount(ts);
        return false;
    }

    static MapData.Stat Stat(MapData map, MapData.City c) => map.stats.Find(x => x.id == c.id);

    internal static void Occupy(MapData map, MapData.City c, string faction, string lord, Officer governor)
    {
        c.owner = faction;
        var st = Stat(map, c);
        st.owner = faction; st.ruler = lord; st.governor = governor.Name;
    }

    // ── 给战场的活数据 ──────────────────────────────────────────────────
    public class World : BattleField.IWorld
    {
        readonly MapData map; readonly Dictionary<string, int> idByName = new();
        public World(MapData m, Dictionary<int, Officer> all)
        {
            map = m;
            foreach (var o in all.Values) if (!idByName.ContainsKey(o.Name)) idByName[o.Name] = o.Id;
        }
        public Dictionary<int, int> Rulers()
        {
            var d = new Dictionary<int, int>();
            foreach (var s in map.stats)                                   // 按势力表取君主武将号（有同名武将，别按名字查）
                d[s.id] = s.owner == "none" || Realm.Of(s.owner) == null ? 0 : Realm.Of(s.owner).RulerId;
            return d;
        }
        public Dictionary<int, int> Counts()
        {
            var d = new Dictionary<int, int>();
            foreach (var s in map.stats) d[s.id] = Personnel.Of(s.id).A.Count;
            return d;
        }
        public int[] Adjacent(int city) => Military.Adjacent(city);
        public string Name(int city) => map.cities.Find(x => x.id == city)?.name ?? "?";
        public int IdOf(string name) => idByName.TryGetValue(name ?? "", out int id) ? id : 0;
        public static int LordId(string faction) => Realm.Of(faction)?.RulerId ?? 0;
    }

    // ── 善后 ────────────────────────────────────────────────────────
    public class Outcome
    {
        public bool Won;                              // 城拿下来了
        public List<string> Lines = new();            // 战报，逐条弹给玩家
        public bool LordDied;                         // 我方君主战死 → 选继位（原版 bG=2 → as()）
        public string FallenFaction;                  // 哪个势力灭亡了（bG=4 / bG=3）
        public string EnemySuccession;                // 敌方君主死了、谁接位（bG=1）
    }

    /// 每员武将最后落在哪一边：战场里被擒会换一个 Unit 重新入列，取最后那个
    static Dictionary<Officer, Unit> Final(BattleField f)
    {
        var d = new Dictionary<Officer, Unit>();
        foreach (var u in f.Units) d[u.O] = u;
        return d;
    }

    /// 打完之后：原版我方胜走 L()（K() 3251 + 3385），败走 A() → 逐将 i() → D() 2889。
    public static Outcome Settle(MapData map, Dictionary<int, Officer> all, Sortie s, BattleField f,
                                 string faction, string lord, System.Random rng)
    {
        var r = new Outcome { Won = f.CityFell == 1 };
        var T = s.To; var ts = Stat(map, T);
        string foeFaction = T.owner, foeLord = ts.ruler;
        var fin = Final(f);
        var tA = Personnel.Of(T.id).A;

        // 战死的：从所有名册里拿掉
        foreach (var o in f.Dead) Kill(map, o, r);

        // 中途撤走 / 下野的（我方主动撤、粮尽撤、主将倒了全军撤……）
        foreach (var kv in f.RetreatTo)
        {
            var o = kv.Key;
            if (f.Dead.Contains(o)) continue;
            tA.Remove(o);
            if (kv.Value == 0) { Personnel.Of(T.id).B.Add(o); o.Serving = false; o.City = T.id; r.Lines.Add(o.Name + " 下野"); continue; }
            var dest = map.cities.Find(x => x.id == kv.Value);
            if (dest.owner == "none") Occupy(map, dest, faction, lord, o);            // 退进空城就势占了
            Join(map, dest, o);
        }

        if (r.Won)
        {
            // 敌方余部：原版 K()，有路就退往自家邻城（或空城），无路可退的全员被擒、归顺我方
            var foes = new List<Officer>();
            foreach (var kv in fin) if (!kv.Value.Ours && !f.Dead.Contains(kv.Key) && !f.RetreatTo.ContainsKey(kv.Key)) foes.Add(kv.Key);
            var ours = new List<Officer>();
            foreach (var kv in fin) if (kv.Value.Ours && !f.Dead.Contains(kv.Key) && !f.RetreatTo.ContainsKey(kv.Key)) ours.Add(kv.Key);
            tA.Clear();
            bool first = true;
            foreach (var o in foes)
            {
                var back = FoeRetreat(map, T, foeFaction);
                if (back == null)                                                       // 无路可退 → 被擒
                {
                    o.Loyal = BattleRules.Rebind(o.Loyal, rng);
                    ours.Add(o); r.Lines.Add(o.Name + " 无路可退，被擒归降");
                    continue;
                }
                if (back.owner == "none") Occupy(map, back, foeFaction, foeLord, o);
                Join(map, back, o);
                var bs = Stat(map, back);
                if (first && (f.KeptGold > 0 || f.KeptFood > 0))                      // 头一个撤走的带着城库
                {
                    bs.黄金 = Mathf.Min(bs.黄金 + f.KeptGold, MaxGoldFood);
                    bs.粮草 = Mathf.Min(bs.粮草 + f.KeptFood, MaxGoldFood);
                }
                first = false;
                r.Lines.Add(o.Name + " 退往 " + back.name);
            }
            // 城归我方：城主换成我方君主、太守是我方主将；幸存者和俘虏进城，满 10 人的进在野
            var gov = ours.Count > 0 ? ours[0] : s.Army[0];
            Occupy(map, T, faction, lord, gov);
            foreach (var o in ours)
            {
                if (tA.Count < Politics.MaxInCity) { tA.Add(o); o.Serving = true; o.City = T.id; }
                else { Personnel.Of(T.id).B.Add(o); o.Serving = false; o.City = T.id; }
            }
            ts.黄金 = Mathf.Min(f.Supply[0], MaxGoldFood); ts.粮草 = Mathf.Min(f.Supply[1], MaxGoldFood);
            Military.Recount(ts);
            r.Lines.Insert(0, "我军攻占了 " + T.name + "!");
            // 敌方君主没了：有城就按 智+武+德 最高的继位（Z=2 的 aq(byte) 11563 兜底规则）
            if (!string.IsNullOrEmpty(foeLord) && foeFaction != "none" && !Alive(all, foeLord, f))
                r.EnemySuccession = Succeed(map, all, foeFaction, foeLord);
            if (foeFaction != "none" && !map.cities.Exists(c => c.owner == foeFaction)) r.FallenFaction = foeLord;
        }
        else
        {
            // 攻城不下：城还是守方的。被敌方擒去的我将归顺守方（D()：满 10 人进在野）
            var foes = new List<Officer>();
            foreach (var kv in fin) if (!kv.Value.Ours && !f.Dead.Contains(kv.Key) && !f.RetreatTo.ContainsKey(kv.Key)) foes.Add(kv.Key);
            tA.Clear();
            foreach (var o in foes)
            {
                if (tA.Count < Politics.MaxInCity) { tA.Add(o); o.Serving = true; o.City = T.id; }
                else { Personnel.Of(T.id).B.Add(o); o.Serving = false; }
            }
            // 我方押在营中的俘虏（被擒的敌将，不在图上）：攻城没拿下，放还这座城（原版只在拿下城时 K() 收编俘虏）
            foreach (var kv in fin)
                if (kv.Value.Ours && !kv.Value.Alive && !f.Dead.Contains(kv.Key) && !f.RetreatTo.ContainsKey(kv.Key))
                {
                    var o = kv.Key;
                    if (tA.Count < Politics.MaxInCity) { tA.Add(o); o.Serving = true; o.City = T.id; }
                    else { Personnel.Of(T.id).B.Add(o); o.Serving = false; }
                    r.Lines.Add(o.Name + " 被放还");
                }
            ts.黄金 = Mathf.Min(f.Supply[2], MaxGoldFood); ts.粮草 = Mathf.Min(f.Supply[3], MaxGoldFood);
            // 我方带去的金粮：主将活着撤走了就跟着他走（i() 2947）；主将战死就落在敌城（bE）。
            // 原版 bE 不复位，后面每场败仗都会再加一遍——这里只算这一场。
            var cmd = s.Army[0];
            if (f.RetreatTo.TryGetValue(cmd, out int to) && to > 0)
            {
                var ds = map.stats.Find(x => x.id == to);
                ds.黄金 = Mathf.Min(ds.黄金 + f.Supply[0], MaxGoldFood); ds.粮草 = Mathf.Min(ds.粮草 + f.Supply[1], MaxGoldFood);
            }
            else if (f.Dead.Contains(cmd))
            {
                ts.黄金 = Mathf.Min(ts.黄金 + f.Supply[0], MaxGoldFood); ts.粮草 = Mathf.Min(ts.粮草 + f.Supply[1], MaxGoldFood);
            }
            Military.Recount(ts);
            r.Lines.Insert(0, "我军攻打 " + T.name + " 失败了!");
        }
        foreach (var o in f.Dead) if (o.Name == lord) r.LordDied = true;
        foreach (var st in map.stats) Military.Recount(st);
        return r;
    }

    static bool Alive(Dictionary<int, Officer> all, string name, BattleField f)
    {
        foreach (var o in f.Dead) if (o.Name == name) return false;
        return true;
    }

    /// 进城：满 10 人就进在野（原版 a(short,byte) 2827 进城前 J() 已经挑的是 r &lt; 10 的城）
    internal static void Join(MapData map, MapData.City c, Officer o)
    {
        var ct = Personnel.Of(c.id);
        foreach (var cc in map.cities) { var x = Personnel.Of(cc.id); x.A.Remove(o); x.B.Remove(o); x.C.Remove(o); }
        if (ct.A.Count < Politics.MaxInCity) { ct.A.Add(o); o.Serving = true; }
        else { ct.B.Add(o); o.Serving = false; }
        o.City = c.id;
        Military.Recount(map.stats.Find(x => x.id == c.id));
    }

    internal static void Kill(MapData map, Officer o, Outcome r)
    {
        foreach (var c in map.cities) { var x = Personnel.Of(c.id); x.A.Remove(o); x.B.Remove(o); x.C.Remove(o); }
        o.City = 0; o.Serving = false; o.Troops = 0;
        r.Lines.Add(o.Name + " 战死");
    }

    /// J() 3198 / c() 10943：战场城的相邻城里，先挑守方自家没满 10 人的，没有再挑空城
    static MapData.City FoeRetreat(MapData map, MapData.City at, string foeFaction)
    {
        MapData.City empty = null;
        foreach (int id in Military.Adjacent(at.id))
        {
            var c = map.cities.Find(x => x.id == id);
            if (c == null) continue;
            if (c.owner == foeFaction && foeFaction != "none" && Personnel.Of(c.id).A.Count < Politics.MaxInCity) return c;
            if (c.owner == "none" && empty == null) empty = c;
        }
        return empty;
    }

    /// aq(byte) 11563：先按写死的继承名单 f[势力][] 顺位找（这家名下还在的第一人），
    /// 一个都不在就立 智 + 武 + 德 最高的；这个势力所有城的城主改成他、忠诚 100。
    /// 名单里多半是别家的人（原版就这样，见 TURNS_SPEC B9），实际多数走兜底。
    /// 按原版势力号 1~7 排（三版的这张表一字不差，号对的是各版自己的武将表）
    static readonly int[][] Heirlist = {
        null,
        new[]{ 161, 5, 6, 181, 200 },
        new[]{ 211, 225, 215, 213, 217, 56, 58, 204, 210, 63, 57 },
        new[]{ 37, 8, 34 },
        new[]{ 191, 138, 201, 155, 208 },
        new[]{ 26, 25, 18, 19, 28, 21 },
        new[]{ 40 },
        new[]{ 140, 2, 172, 190, 160 },
    };
    /// 君主已经不在自家任何一城效力了（被擒 / 被招降 / 无路可退归降——以前只有「战死」才继位）→ 照 Succeed 立新君。
    /// 玩家自己的势力不管（我方君主被擒另有流程）。返回「XX 死亡,新君主 YY 继位!」那样的句子，一家一句
    public static List<string> CheckRulers(MapData map, Dictionary<int, Officer> all)
    {
        var msgs = new List<string>();
        foreach (var f in Realm.All)
        {
            if (!f.Alive || f.Key == Realm.Player || Realm.CityCount(map, f.Key) == 0) continue;
            bool home = false;
            foreach (var c in map.cities)
                if (c.owner == f.Key && Personnel.Of(c.id).A.Exists(o => o.Id == f.RulerId)) { home = true; break; }
            if (home) continue;
            string old = f.Name;
            var line = Succeed(map, all, f.Key, old);
            if (line != null) msgs.Add(old + " 已不在本国," + line.Substring(line.IndexOf("新君主")));
        }
        return msgs;
    }

    public static string Succeed(MapData map, Dictionary<int, Officer> all, string faction, string oldLord)
    {
        var mine = new List<Officer>();
        foreach (var c in map.cities) if (c.owner == faction) mine.AddRange(Personnel.Of(c.id).A);
        if (mine.Count == 0) return null;
        Officer pick = null;
        int slot = Scenario.SlotOf(faction);
        if (slot > 0 && Heirlist[slot] is int[] list)
            foreach (int id in list) { pick = mine.Find(o => o.Id == id); if (pick != null) break; }
        if (pick == null)
        {
            int best = -1;
            foreach (var o in mine) { int sum = o.Int + o.Might + o.Virtue; if (sum > best) { best = sum; pick = o; } }
        }
        SetLord(map, faction, pick);
        return oldLord + "死亡,新君主 " + pick.Name + " 继位!";
    }

    /// a(byte,byte,short) 6196：新君主当他所在那城的太守，全势力城主改成他
    public static void SetLord(MapData map, string faction, Officer lord)
    {
        Realm.SetRuler(map, faction, lord);
        foreach (var c in map.cities)
            if (c.owner == faction && Personnel.Of(c.id).A.Contains(lord)) Personnel.Appoint(map.stats.Find(x => x.id == c.id), lord);
    }

    /// 我方可以选的继位人：我方所有城的仕官
    public static List<Officer> Heirs(MapData map, string faction)
    {
        var l = new List<Officer>();
        foreach (var c in map.cities) if (c.owner == faction) l.AddRange(Personnel.Of(c.id).A);
        return l;
    }
}
