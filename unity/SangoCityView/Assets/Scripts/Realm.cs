// 势力表：原版 a[f] 存活、b[f] 君主武将号、c[f]/d[f][] 城数与城列表（这里由 MapData.cities[].owner 现算）。
// 势力用 map.json 里的 owner 键（caocao / yuanshao …），君主按武将号记——武将表里有同名的（两个曹操、两个蔡氏），
// 只按名字查会查错人。开局君主取 cities.csv 第 2 列（原版 i[城]）。
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public class Faction
{
    public string Key;          // map.json 的 owner 键
    public int RulerId;         // b[f]
    public bool Alive = true;   // a[f] != 0
    public string Name => Realm.Officers.TryGetValue(RulerId, out var o) ? o.Name : Key;
}

public static class Realm
{
    public static List<Faction> All = new();
    public static Dictionary<int, Officer> Officers;
    public static string Player;                        // 玩家扮演的势力（原版 ar）

    /// 同盟表（原版 W 个数 / X[] 盟友势力 / Y[] 剩余月数）。只有玩家和别家结盟，AI 之间不结盟
    public class Pact { public string With; public int Months; }
    public static List<Pact> Pacts = new();
    public static bool Allied(string f) => f != null && Pacts.Exists(p => p.With == f);

    public static void Init(MapData map, Dictionary<int, Officer> all)
    {
        Officers = all; All = new List<Faction>(); Pacts = new List<Pact>(); Player = null;
        var ruler = new Dictionary<int, int>();
        foreach (var line in File.ReadAllLines(Scenario.CitiesCsv))
        {
            var f = line.Split(',');
            if (f.Length > 2 && int.TryParse(f[0], out int cid) && int.TryParse(f[1], out int rid)) ruler[cid] = rid;
        }
        foreach (var c in map.cities)
        {
            if (c.owner == "none" || All.Exists(x => x.Key == c.owner)) continue;
            All.Add(new Faction { Key = c.owner, RulerId = ruler.TryGetValue(c.id, out int r) ? r : 0 });
        }
    }

    public static Faction Of(string key) => All.Find(f => f.Key == key);
    public static Officer Ruler(string key) => Of(key) is Faction f && Officers.TryGetValue(f.RulerId, out var o) ? o : null;
    public static List<MapData.City> Cities(MapData map, string key) => map.cities.FindAll(c => c.owner == key);
    public static int CityCount(MapData map, string key) => map.cities.FindAll(c => c.owner == key).Count;

    /// 换君主：这个势力所有城的城主改成他，忠诚 100（原版 a(f, 城, 武将) 6196）
    public static void SetRuler(MapData map, string key, Officer o)
    {
        var f = Of(key); if (f == null) return;
        f.RulerId = o.Id; o.Loyal = 100;
        foreach (var st in map.stats) if (st.owner == key) st.ruler = o.Name;
    }

    /// 城数归零就灭亡（原版 L() 里 c[败方]==0 → a[f]=0）
    public static void CheckAlive(MapData map)
    {
        foreach (var f in All) if (f.Alive && CityCount(map, f.Key) == 0) f.Alive = false;
    }

    /// 某员武将是不是某家的君主
    public static Faction RulerOf(Officer o) => All.Find(f => f.RulerId == o.Id && f.Alive);
}
