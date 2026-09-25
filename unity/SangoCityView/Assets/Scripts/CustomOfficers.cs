// 自建武将：原版主菜单「自建武将」（屏 27，c.java Y() 7265 / ai() 7070），最多 3 名，占武将号 229~231，
// 存进 RecordStore "newPs"（b.bV() 10872），跨局保留——这里存 persistentDataPath/custom_officers.json。
// 三个模板（c.java 438 aR）：文 武20 智80 体60 德60 可加 70 点；武 80/20/80/50 可加 50 点；随机 各 1~99 不能加。
// 加点每项封顶 99，减不下模板底数。名字最多 4 个字，不能空、不能和现有武将重名。
// 新武将：LV 1、兵种随机、兵 500、体力上下限 = 体、忠 99、经验 0、短剑、纶巾（c.java 7236）。
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public static class CustomOfficers
{
    public const int FirstId = 229, Max = 3;
    public static readonly int[][] Base = { new[] { 20, 80, 60, 60 }, new[] { 80, 20, 80, 50 } };   // 武 智 体 德
    public static readonly int[] Pool = { 70, 50 };
    public static readonly string[] TplName = { "文", "武", "随机" };
    /// 可选头像。原版 10 张按模板分（文 234~238、武 229~233，aQ[]）；
    /// 新版按玩家要求改成按性别分、男补到 15 张、女补到 24 张（239~267 是新画的），模板只管数值不管脸（HOUSE_RULES）
    public static readonly int[] Male = { 230, 231, 232, 233, 234, 235, 238, 239, 240, 241, 242, 243, 244, 245, 246 };
    public static readonly int[] Female = { 229, 236, 237, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258,
                                            259, 260, 261, 262, 263, 264, 265, 266, 267 };   // 259~267：玩家又要的九张
    public static int[] Faces(bool female) => female ? Female : Male;

    [System.Serializable] public class Rec { public int id, might, iq, hp, virtue, kind, face; public string name; }
    [System.Serializable] class Store { public List<Rec> list = new(); }

    static string PathOf => Path.Combine(Application.persistentDataPath, "custom_officers.json");
    public static List<Rec> All()
    {
        try { return File.Exists(PathOf) ? JsonUtility.FromJson<Store>(File.ReadAllText(PathOf)).list : new List<Rec>(); }
        catch { return new List<Rec>(); }
    }
    static void Save(List<Rec> l) => File.WriteAllText(PathOf, JsonUtility.ToJson(new Store { list = l }));

    /// ae() c.java 6930：第一个空着的号；满了返回 0（「自建武将数量已满!」）
    public static int FreeId()
    {
        var l = All();
        for (int id = FirstId; id < FirstId + Max; id++) if (!l.Exists(r => r.id == id)) return id;
        return 0;
    }

    /// 起名检查（c.java 6914）：空名 / 重名
    public static string CheckName(string name, Dictionary<int, Officer> all)
    {
        if (string.IsNullOrWhiteSpace(name)) return "武将名不能为空!";
        foreach (var o in all.Values) if (o.Name == name) return "武将名重复!";
        foreach (var r in All()) if (r.name == name) return "武将名重复!";
        return null;
    }

    public static void Add(Rec r) { var l = All(); l.RemoveAll(x => x.id == r.id); l.Add(r); Save(l); }
    public static void Remove(int id) { var l = All(); l.RemoveAll(x => x.id == id); Save(l); }   // bU() 10826

    /// 开局时把自建武将放进武将表（不在任何城里，只有扮演「自建」势力时才出场）
    public static void Register(Dictionary<int, Officer> all)
    {
        foreach (var r in All())
        {
            all[r.id] = ToOfficer(r);
            BattleRules.Faces[r.id] = r.face;
        }
    }

    public static Officer ToOfficer(Rec r) => new Officer {
        Id = r.id, Name = r.name, Lv = 1, Kind = (Kind)r.kind, Troops = 500,
        HpMax = r.hp, Hp = r.hp, Might = r.might, Int = r.iq, Virtue = r.virtue,
        Loyal = 99, Exp = 0, ArmLv = 0, SubLv = 24, City = 0, Year = Politics.ScenarioYear, Serving = false,
    };
}
