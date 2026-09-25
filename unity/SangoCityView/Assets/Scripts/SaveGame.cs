// 存档：原版「系统 → 存储 / 读取」，三个档位（c.java:443 aS[3]，空档写「尚无存档」），
// 档名是「君主名 + 年 + 月」（c.java:7654 b(String)）。
// 原版存进 RecordStore；这里是 persistentDataPath 下的 save_<档位>.json。
// 只存会变的东西：36 城数值与归属、武将的可变属性、三份名册、年月与剩余令数。
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public static class SaveGame
{
    public const int Slots = 3;
    public const string Empty = "尚无存档";

    [System.Serializable] public class OfficerState
    {
        public int id, lv, troops, hpMax, hp, iq, might, virtue, loyal, exp, armLv, subLv, city;
        public bool serving;
    }
    [System.Serializable] public class CourtState { public int city; public int[] a, b, c; }
    [System.Serializable] public class OwnerState { public int id; public string owner; }
    [System.Serializable] public class FactionState { public string key; public int ruler; public bool alive; }
    [System.Serializable] public class PactState { public string with; public int months; }

    [System.Serializable] public class Data
    {
        public string label;                 // 「曹操 189年3月」
        public int scenario;
        public List<int> serveIds = new(), serveMonths = new();   // 天下霸图：武将在玩家麾下连续待了几个月                 // 哪一版（0 初级 3.2.2 / 1 中级 3.2.5 / 2 困难 3.1），旧档没有这项就是 0
        public string faction; public int year, month, orders, curCity;
        public List<MapData.Stat> stats = new();
        public List<OwnerState> owners = new();
        public List<OfficerState> officers = new();
        public List<CourtState> courts = new();
        public List<FactionState> factions = new();   // 原版 a[] / b[]
        public List<PactState> pacts = new();         // 原版 W / X[] / Y[]
        public List<string> queue = new();            // 本月还没轮到的势力（原版 aB[]），读档后先接着玩家这一格
    }

    static string PathOf(int slot) => Path.Combine(Application.persistentDataPath, "save_" + slot + ".json");

    public static string Label(int slot)
    {
        try
        {
            var p = PathOf(slot);
            if (!File.Exists(p)) return Empty;
            return JsonUtility.FromJson<Data>(File.ReadAllText(p)).label ?? Empty;
        }
        catch { return Empty; }
    }

    public static bool Has(int slot) => File.Exists(PathOf(slot));

    public static void Write(int slot, Data d) => File.WriteAllText(PathOf(slot), JsonUtility.ToJson(d));

    public static Data Read(int slot)
    {
        try { return Has(slot) ? JsonUtility.FromJson<Data>(File.ReadAllText(PathOf(slot))) : null; }
        catch { return null; }
    }

    // ── 打包 / 还原 ────────────────────────────────────────────────────
    public static Data Capture(MapData map, Dictionary<int, Officer> all, string faction, string lord,
                               int year, int month, int orders, int curCity)
    {
        var d = new Data {
            label = (lord ?? "无名") + " " + year + "年" + month + "月",
            faction = faction, year = year, month = month, orders = orders, curCity = curCity,
            stats = map.stats,
        };
        foreach (var c in map.cities) d.owners.Add(new OwnerState { id = c.id, owner = c.owner });
        foreach (var o in all.Values)
            d.officers.Add(new OfficerState {
                id = o.Id, lv = o.Lv, troops = o.Troops, hpMax = o.HpMax, hp = o.Hp, iq = o.Int,
                might = o.Might, virtue = o.Virtue, loyal = o.Loyal, exp = o.Exp,
                armLv = o.ArmLv, subLv = o.SubLv, city = o.City, serving = o.Serving });
        foreach (var f in Realm.All) d.factions.Add(new FactionState { key = f.Key, ruler = f.RulerId, alive = f.Alive });
        foreach (var p in Realm.Pacts) d.pacts.Add(new PactState { with = p.With, months = p.Months });
        foreach (var c in map.cities)
        {
            var ct = Personnel.Of(c.id);
            d.courts.Add(new CourtState { city = c.id,
                a = ct.A.ConvertAll(o => o.Id).ToArray(),
                b = ct.B.ConvertAll(o => o.Id).ToArray(),
                c = ct.C.ConvertAll(o => o.Id).ToArray() });
        }
        return d;
    }

    /// map / all 必须是刚按开局装好的那一份，这里把存档的值盖上去
    public static void Apply(Data d, MapData map, Dictionary<int, Officer> all)
    {
        map.stats = d.stats;
        foreach (var ow in d.owners) { var c = map.cities.Find(x => x.id == ow.id); if (c != null) c.owner = ow.owner; }
        foreach (var s in d.officers)
        {
            if (!all.TryGetValue(s.id, out var o)) continue;
            o.Lv = s.lv; o.Troops = s.troops; o.HpMax = s.hpMax; o.Hp = s.hp; o.Int = s.iq;
            o.Might = s.might; o.Virtue = s.virtue; o.Loyal = s.loyal; o.Exp = s.exp;
            o.ArmLv = s.armLv; o.SubLv = s.subLv; o.City = s.city; o.Serving = s.serving;
        }
        Personnel.Restore(d.courts, all);
        foreach (var fs in d.factions)
        {
            var f = Realm.Of(fs.key);
            if (f == null) { f = new Faction { Key = fs.key }; Realm.All.Add(f); }          // 自建势力开局时不在表里
            f.RulerId = fs.ruler; f.Alive = fs.alive;
        }
        Realm.Pacts.Clear();
        foreach (var p in d.pacts) Realm.Pacts.Add(new Realm.Pact { With = p.with, Months = p.months });
        Realm.Player = d.faction;
    }
}
