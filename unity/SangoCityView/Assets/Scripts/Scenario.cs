// 剧本：难度就是换版本（玩家要求「原汁原味」）。
//   初级 = 3.2.2（map.json + battle/cities.csv + battle/officers.csv，我们一直用的那套）
//   中级 = 3.2.5 的 lib114/115/116（Art/scenario/mid/）
//   困难 = 3.1   的 lib114/115/116（Art/scenario/hard/）
// 三版的武将号互不相通（同一个号在不同版本是不同的人），所以每版一整套武将表；城位 1~36 地理一一对应，
// 城名、归属、君主、太守、七项数值、建筑、预备兵、兵力全照那一版。
// 立绘 / 头像按名字共用：art.csv 记「武将号 → 画号」，3.2.2 有的用它的图，没有的是新画的 268~385。
// 规则代码三版一样的（继承名单 f[][]、单挑掉宝的武将号、210 年彩蛋）不动；三版不同的只有 aE() 那张
// 「子承父业」表（aa/ab/ac），3.2.5 / 3.1 各带一张、都对得上自己的武将表，这里照接（3.2.2 那张对不上，仍不接）。
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public static class Scenario
{
    public static int Level { get; private set; }
    static readonly string[] Sub = { null, "mid", "hard" };
    /// 势力槽 1~7（原版势力号 = rulers.csv 的行序）→ 旗色键。键只管颜色，名字一律取君主
    static readonly string[][] Keys = {
        new[] { null, "han", "caocao", "yuanshao", "sunjian", "lvbu", "liuyan", "liubiao" },       // 3.2.2：献帝 曹操 袁绍 孙坚 吕布 刘焉 刘表
        new[] { null, "liuyan", "caocao", "liubiao", "sunjian", "yuanshao", "han", "lvbu" },       // 3.2.5：刘备 曹操 孟获 孙坚 司马懿 张角(黄) 公孙康
        new[] { null, "liuyan", "caocao", "yuanshao", "sunjian", "lvbu", "liubiao", "han" },       // 3.1  ：刘备 曹操 袁绍 孙策 董卓 刘璋 马腾
    };

    static string Dir => Level == 0 ? null : Path.Combine(UIKit.ArtDir, "scenario", Sub[Level]);
    public static string CitiesCsv => Level == 0 ? Path.Combine(UIKit.ArtDir, "battle", "cities.csv") : Path.Combine(Dir, "cities.csv");
    public static string OfficersCsv => Level == 0 ? Path.Combine(UIKit.ArtDir, "battle", "officers.csv") : Path.Combine(Dir, "officers.csv");

    /// 原版势力号（继承名单 f[势力] 用）；自建势力没有
    public static int SlotOf(string key) => System.Array.IndexOf(Keys[Level], key);

    public static void Use(int level) => Level = Mathf.Clamp(level, 0, 2);

    /// 读进 map.json 之后：换成这一版的城名 / 归属 / 城主 / 太守 / 数值 / 建筑（初级不动）
    public static void ApplyCities(MapData map)
    {
        if (Level == 0) return;
        var slotOfRuler = new Dictionary<int, int>();
        foreach (var f in Rows(Path.Combine(Dir, "rulers.csv")))
            if (f.Length > 1 && int.TryParse(f[0], out int slot) && int.TryParse(f[1], out int rid)) slotOfRuler[rid] = slot;
        var names = new Dictionary<int, string>();
        foreach (var f in Rows(Path.Combine(Dir, "names.csv"))) if (int.TryParse(f[0], out int id)) names[id] = f[1];
        foreach (var f in Rows(CitiesCsv))
        {
            if (f.Length < 20 || !int.TryParse(f[0], out int id)) continue;
            var c = map.cities.Find(x => x.id == id); var s = map.stats.Find(x => x.id == id);
            if (c == null || s == null) continue;
            int I(int i) => int.TryParse(f[i], out int v) ? v : 0;
            string key = slotOfRuler.TryGetValue(I(1), out int sl) ? Keys[Level][sl] : "none";
            c.owner = s.owner = key;
            if (names.TryGetValue(id, out var nm)) c.name = s.name = nm;
            s.ruler = key == "none" ? "" : f[2]; s.governor = key == "none" ? "" : f[4];
            s.治安 = I(5); s.黄金 = I(6); s.粮草 = I(7); s.农业 = I(8); s.商业 = I(9); s.人口 = I(10); s.治水 = I(11);
            s.兵力 = I(13);
            s.facil = new MapData.Facil { wuku = I(16) != 0, junxue = I(17) != 0, yaolu = I(18) != 0, changping = I(19) != 0 };
        }
        Debug.Log("[剧本] " + Difficulty.Names[Level] + " 城池已换");
    }

    /// 武将号 → 画号（初级是同号）
    public static void ApplyFaces()
    {
        BattleRules.Faces.Clear();                             // 上一局别的版本留下的
        if (Level == 0) return;
        foreach (var f in Rows(Path.Combine(Dir, "art.csv")))
            if (f.Length > 1 && int.TryParse(f[0], out int id) && int.TryParse(f[1], out int art)) BattleRules.Faces[id] = art;
    }

    /// aE() 的「子承父业」：年, 子, 父。初级没有
    public static List<int[]> Joins()
    {
        var l = new List<int[]>();
        if (Level == 0) return l;
        foreach (var f in Rows(Path.Combine(Dir, "joins.csv")))
            if (f.Length > 2 && int.TryParse(f[0], out int y) && int.TryParse(f[1], out int c) && int.TryParse(f[2], out int p)) l.Add(new[] { y, c, p });
        return l;
    }

    static IEnumerable<string[]> Rows(string path)
    {
        if (!File.Exists(path)) yield break;
        foreach (var line in File.ReadAllLines(path))
            if (line.Length > 0 && line[0] != '#') yield return line.Trim().Split(',');
    }
}
