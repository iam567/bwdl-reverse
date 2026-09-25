// 天下霸图（武将卡收集册，新版加的，原版没有）：
//   三个版本的武将按名字合并成 337 张卡（Art/album/cards.json），分 GOD / SSR / SR / R / N 五级。
//   任何难度、任何一局里，这个武将在玩家麾下连续待满一年（12 个月末都在玩家势力某城的仕官名单里）才解锁——
//   中途被离间 / 招揽走、战死、被俘，或者这一局先结束了（统一天下 / 灭亡），都不算，计数清零（防开局就退、换君主刷卡）。
//   计数在 Game 里按局记（serveMonths，存档里有），这里只管解锁；按名字记，
//   同名的只算一张（三版的关羽、3.2.2 里两个曹操都是一张）。自建武将（229~231）不算。
//   解锁记录单独存 persistentDataPath/album.json，跟存档无关，新开局不丢。
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public static class Album
{
    [System.Serializable] public class Card { public string key, name, tier; public int art; public string[] modes; }
    [System.Serializable] class Book { public List<Card> cards; public List<string> aliasFrom, aliasTo; }
    [System.Serializable] class Saved { public List<string> unlocked = new(); }

    public static readonly string[] Tiers = { "GOD", "SSR", "SR", "R", "N" };
    public static List<Card> Cards { get; private set; }
    static Dictionary<string, Card> byKey;
    static Dictionary<string, string> alias;
    static HashSet<string> unlocked;

    static string SavePath => Path.Combine(Application.persistentDataPath, "album.json");

    static void Ensure()
    {
        if (Cards != null) return;
        Cards = new List<Card>(); byKey = new Dictionary<string, Card>(); alias = new Dictionary<string, string>();
        var path = Path.Combine(UIKit.ArtDir, "album", "cards.json");
        if (File.Exists(path))
        {
            var json = File.ReadAllText(path);
            var b = JsonUtility.FromJson<Book>(json);
            if (b?.cards != null) Cards = b.cards;
            // aliases 是 {异写: 正名} 的对象，JsonUtility 读不了字典，这里手拆
            int i = json.IndexOf("\"aliases\"");
            if (i >= 0)
            {
                int s = json.IndexOf('{', i), e = json.IndexOf('}', s);
                foreach (var kv in json.Substring(s + 1, e - s - 1).Split(','))
                {
                    var p = kv.Split(':');
                    if (p.Length == 2) alias[p[0].Trim().Trim('"')] = p[1].Trim().Trim('"');
                }
            }
        }
        foreach (var c in Cards) byKey[c.key] = c;
        unlocked = new HashSet<string>();
        try { if (File.Exists(SavePath)) foreach (var k in JsonUtility.FromJson<Saved>(File.ReadAllText(SavePath)).unlocked) unlocked.Add(k); }
        catch { }
    }

    /// 武将对应哪张卡（自建武将 / 查不到 → null）
    public static Card Of(Officer o)
    {
        Ensure();
        if (o == null || o.Id >= CustomOfficers.FirstId) return null;
        string k = alias.TryGetValue(o.Name, out var a) ? a : o.Name;
        return byKey.TryGetValue(k, out var c) ? c : null;
    }
    public static bool IsUnlocked(Card c) { Ensure(); return c != null && unlocked.Contains(c.key); }
    public static int Count(string tier = null) { Ensure(); int n = 0; foreach (var c in Cards) if ((tier == null || c.tier == tier) && unlocked.Contains(c.key)) n++; return n; }
    public static int Total(string tier = null) { Ensure(); return tier == null ? Cards.Count : Cards.FindAll(c => c.tier == tier).Count; }

    /// 解锁这员武将的卡（已解锁 / 自建 → null），写盘
    public static Card Unlock(Officer o)
    {
        var card = Of(o);
        if (card == null || !unlocked.Add(card.key)) return null;
        Save(); return card;
    }
    /// 截图自检用：把这一家的人只在内存里点亮，不写盘、不碰玩家的收集记录
    public static void Preview(MapData map, string faction)
    {
        Ensure();
        foreach (var c in map.cities)
            if (c.owner == faction) foreach (var o in Personnel.Of(c.id).A) { var card = Of(o); if (card != null) unlocked.Add(card.key); }
    }
    static void Save()
    {
        try { File.WriteAllText(SavePath, JsonUtility.ToJson(new Saved { unlocked = new List<string>(unlocked) })); } catch { }
    }

    // ── 角标 ──
    static readonly Dictionary<string, Sprite> badges = new();
    public static Sprite Badge(string tier)
    {
        if (string.IsNullOrEmpty(tier)) return null;
        if (badges.TryGetValue(tier, out var s)) return s;
        var t = UIKit.TryLoad(Path.Combine("album", "rank_" + tier + ".png"));
        s = t != null ? UIKit.Whole(t) : null;
        badges[tier] = s; return s;
    }
}
