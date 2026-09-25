// 外交的公式：玩家和 AI 共用（原版 b.java 7596~7738）。玩家那四条的界面在 DiplomacyView。
//   同盟 g() 7640：执行者德² / 100 − 对方君主智 > s()%100 就成，盟约 12 个月
//   离间 e() 7597 / f() 7618：智力差过门槛才掉忠诚，掉 ((s()%(差)) / 10 · 9 / 5) + 1
//   招揽 h() 7678 / a() 7693：对方忠诚 ≤ 35 才挖得动；成了重掷忠诚 m()，带兵换城
using System.Collections.Generic;
using UnityEngine;

public static class Diplomacy
{
    public const int PactMonths = 12;

    /// g() 7640：成功率只看执行者的德和对方君主的智
    public static bool Ally(Officer envoy, Officer foeLord, System.Random rng)
        => envoy.Virtue * envoy.Virtue / 100 - foeLord.Int > rng.Next(100);

    /// e() 7597：离间掉多少忠诚（0 = 失败）。忠诚 100 的（君主）免疫
    public static int SowAmount(Officer me, Officer tgt, System.Random rng)
    {
        int a = me.Int, dN = tgt.Int;
        if (tgt.Loyal > 99) dN = 10000; else if (tgt.Loyal > 96) dN += 50; else if (tgt.Loyal > 89) dN += 20;
        if (a - dN <= rng.Next(30)) return 0;
        int x = rng.Next(a - dN); x /= 10; x *= 9; x /= 5; return x + 1;
    }
    /// f() 7618
    public static int Sow(Officer me, Officer tgt, System.Random rng)
    {
        int d = SowAmount(me, tgt, rng);
        if (d > 0) { tgt.Loyal -= d; if (tgt.Loyal < 0) tgt.Loyal = 0; }
        return d;
    }

    /// h() 7678：招揽判定；成了就按 m() 7665 重掷忠诚
    public static bool PoachOk(Officer me, Officer tgt, System.Random rng)
    {
        if (tgt.Loyal > 35) return false;
        if (me.Int - tgt.Int + (35 - tgt.Loyal) >= rng.Next(20)) { tgt.Loyal = BattleRules.Rebind(tgt.Loyal, rng); return true; }
        return false;
    }

    /// a() 7693：人带着兵从对方城换到本城；对方城被挖空就成空城，否则太守改由留下的第一人
    public static bool Poach(MapData map, int myCity, int theirCity, Officer me, Officer tgt, System.Random rng)
    {
        if (!PoachOk(me, tgt, rng)) return false;
        var from = Personnel.Of(theirCity).A; var to = Personnel.Of(myCity).A;
        from.Remove(tgt); to.Add(tgt); tgt.City = myCity;
        var fs = map.stats.Find(x => x.id == theirCity); var ts = map.stats.Find(x => x.id == myCity);
        if (from.Count == 0) Military.Abandon(map, fs);
        else if (fs.governor == tgt.Name) Personnel.Appoint(fs, from[0]);
        Military.Recount(fs); Military.Recount(ts);
        return true;
    }
}
