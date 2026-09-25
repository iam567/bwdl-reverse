// 集市四条指令：金粮 / 武器 / 学院 / 医馆。全部照原版。
// 新版把集市拆成城内四座建筑：常平仓（金粮）/ 武库（武器）/ 郡学（学院）/ 药庐（医馆），
// 建筑在不在就是原版 x[] 的四个位（cities.csv 第 17~20 列），逐城核过，36 城全对。
//
// 原版对应关系（decompiled/b.java、c.java、d.java）：
//   菜单项           d.b[4] = {金粮, 武器, 学院, 医馆}，命令码 15 / 16 / 18 / 19
//   入口             ab(15) 8989 / ac(16) 8999 / ad(18) 9009 / ae(19) 9035
//   步骤表           bR[15]={14,1}    屏 16 买卖粮草 → 扣令
//                    bR[16]={3,15,1}  选将 → 屏 17 挑兵器 → 扣令
//                    bR[18]={3,89,1}  选将（智力 < 80 的）→ bf() 修习 → 扣令
//                    bR[19]={3,88,1}  选将（体力没满的）→ bg() 疗伤 → 扣令
//   金粮             屏 16 上限 c.java 842，成交 c.java 5445 → o(int) 8660「交易成功」
//   武器             屏 17 c.java y()/z()/h()，货架 ag/ah/ai/aj[y[城]][0..3]，价目 ae[] b.java 1440
//   学院             bf() 7754 + n(short) 智力涨 6~10 / 6~12
//   医馆             bg() 7774 → bJ() + bI() 体力涨 35~51，固定 50 金
// 前置不过、钱不够（bQ 跳到步骤表外那个 0）只弹一句话，不扣令。
using System.Collections.Generic;
using UnityEngine;

public static class Market
{
    public enum Cmd { Grain, Arms, Academy, Clinic }
    public static readonly string[] CmdName = { "金粮", "武器", "学院", "医馆" };
    public const int MaxGoldFood = 9999;

    // ── 前置检查 ────────────────────────────────────────────────────
    public static bool Blocked(Cmd c, MapData.Stat s, out string why)
    {
        var a = Personnel.Of(s.id).A;
        switch (c)
        {
            case Cmd.Grain:                                                         // ab() 8989
                if (!s.facil.changping) { why = "本城无粮店,无法买卖粮草"; return true; } break;
            case Cmd.Arms:                                                          // ac() 8999
                if (!s.facil.wuku || s.武库档 < 0) { why = "本城无武器店,无法购入武器"; return true; } break;
            case Cmd.Academy:                                                       // ad() 9009
                if (!s.facil.junxue) { why = "本城无学堂,无法修习智力"; return true; }
                if (Students(s).Count == 0) { why = "本城武将皆才华横溢, 无需学习"; return true; } break;
            case Cmd.Clinic:                                                        // ae() 9035
                if (!s.facil.yaolu) { why = "本城无医院,无法治疗武将"; return true; }
                if (Patients(s).Count == 0) { why = "将士均龙精虎猛!"; return true; }
                if (s.黄金 < ClinicFee) { why = "资财不足"; return true; } break;
        }
        why = null; return false;
    }

    // ── 金粮：屏 16 ───────────────────────────────────────────────────
    /// 两个方向：买粮（出金，得金的 4/3 那么多粮）、卖粮（出粮，得粮的 3/4 那么多金）。
    /// 上限：自家有多少出多少，但换回来的那一项不许超过 9999（c.java 842）。
    public static int BuyCap(MapData.Stat s)  => s.黄金 * 4 / 3 + s.粮草 > MaxGoldFood ? (MaxGoldFood - s.粮草) * 3 / 4 : s.黄金;
    public static int SellCap(MapData.Stat s) => s.粮草 * 3 / 4 + s.黄金 > MaxGoldFood ? (MaxGoldFood - s.黄金) * 4 / 3 : s.粮草;

    /// c.java 5445：出多少不能是 0（原版弹「…不能为0」那格）
    public static int Trade(MapData.Stat s, bool buyFood, int n)
    {
        if (buyFood) { int got = n * 4 / 3; s.黄金 -= n; s.粮草 += got; return got; }
        else         { int got = 3 * n / 4; s.粮草 -= n; s.黄金 += got; return got; }
    }

    // ── 武器：屏 17 ───────────────────────────────────────────────────
    /// ae[] 价目（下标就是兵器号，和 BattleRules.EquipName 一一对应；5/6/7/14/31 原版不卖，价 0）
    public static readonly int[] Price = {
        50, 70, 120, 180, 250, 0, 0, 0,        60, 100, 150, 200, 240, 260, 0, 500,
        80, 100, 120, 200, 250, 300, 500, 500, 50, 80, 100, 150, 200, 250, 500, 0 };
    /// 四个货架（剑 / 刀 / 枪戟 / 防具），每架四样，按城的货架档 y[城] 取一行
    static readonly int[][] Swords = { new[]{0,1,2,3}, new[]{1,2,3,4}, new[]{1,2,3,4}, new[]{0,1,2,3}, new[]{0,1,2,3}, new[]{0,1,2,3} };
    static readonly int[][] Blades = { new[]{8,9,10,11}, new[]{10,11,12,13}, new[]{10,11,12,13}, new[]{8,9,10,11}, new[]{8,9,10,15}, new[]{8,9,10,11} };
    static readonly int[][] Spears = { new[]{16,17,18,19}, new[]{18,19,20,21}, new[]{18,19,20,21}, new[]{16,17,18,23}, new[]{17,18,19,20}, new[]{16,17,18,22} };
    static readonly int[][] Armors = { new[]{24,25,26,27}, new[]{26,27,28,29}, new[]{26,27,28,30}, new[]{24,25,26,27}, new[]{24,25,26,27}, new[]{24,25,26,27} };
    public static readonly string[] ShelfName = { "剑", "刀", "枪戟", "防具" };
    public static int[] Shelf(MapData.Stat s, int shelf) => Shelf(s.武库档, shelf);
    public static int[] Shelf(int tier, int shelf)
    {
        int y = Mathf.Clamp(tier, 0, 5);
        return (shelf == 0 ? Swords : shelf == 1 ? Blades : shelf == 2 ? Spears : Armors)[y];
    }

    /// 四件名品：关王刀 / 蛇矛 要武力 > 95，狼牙枪 武力 > 90，圣者之衣 德 > 90（z() c.java）
    public static bool CanWield(Officer o, int item) => item switch {
        15 or 23 => o.Might > 95,
        22       => o.Might > 90,
        30       => o.Virtue > 90,
        _        => true,
    };
    /// 使用条件的说明（原版 z() 的那几条），没有条件返回 null
    public static string Need(int item) => item switch {
        15 or 23 => "武力 > 95",
        22       => "武力 > 90",
        30       => "德行 > 90",
        _        => null,
    };
    public static bool Rare(int item) => item == 15 || item == 23 || item == 22 || item == 30;

    public enum Buy { Ok, NoMoney, CannotUse }
    /// z() + h()：先看用不用得了，再看钱够不够；买了就换上（防具换 S[]，其余换 R[]）。
    /// 名品卖出一件，这座城的货架档改成 1（y[城] = 1，那件名品从此下架）。
    public static Buy Purchase(MapData.Stat s, Officer o, int item)
    {
        if (!CanWield(o, item)) return Buy.CannotUse;                // p(3)「启禀主公,属下无法使用此武器」
        if (s.黄金 < Price[item]) return Buy.NoMoney;                // p(2)「资财不足」
        s.黄金 -= Price[item];
        if (item >= 24) o.SubLv = item; else o.ArmLv = item;
        if (Rare(item)) s.武库档 = 1;
        return Buy.Ok;                                               // p(0)「成功购得武器」
    }

    // ── 学院 ────────────────────────────────────────────────────────
    public static List<Officer> Students(MapData.Stat s) => Personnel.Of(s.id).A.FindAll(o => o.Int < 80);
    /// bf() 7754：智力越低学费越贵——<30 要 30 金，<60 要 20 金，其余 10 金
    public static int Tuition(Officer o) => o.Int < 30 ? 30 : o.Int < 60 ? 20 : 10;
    /// bf() 7754：先按修习前的智力定学费，金子不够就「资财不足」（不扣令）；
    /// 够了再涨智力——n(short)：智力 <30 或 >70 的涨 6~10，中间那段涨 6~12
    public static bool Study(MapData.Stat s, Officer o, System.Random rng, out int gain)
    {
        gain = 0;
        int fee = Tuition(o);
        if (s.黄金 < fee) return false;
        gain = (o.Int < 30 || o.Int > 70) ? rng.Next(5) + 6 : rng.Next(7) + 6;
        o.Int += gain; s.黄金 -= fee;
        return true;
    }

    // ── 医馆 ────────────────────────────────────────────────────────
    public const int ClinicFee = 50;
    public static List<Officer> Patients(MapData.Stat s) => Personnel.Of(s.id).A.FindAll(o => o.Hp < o.HpMax);
    /// bJ() + bI()：体力加 35~51，过顶（或 byte 溢出成负）就压回上限；固定 50 金
    public static int Heal(MapData.Stat s, Officer o, System.Random rng)
    {
        int before = o.Hp;
        o.Hp += 35 + rng.Next(17);
        if (o.Hp > o.HpMax || o.Hp <= 0) o.Hp = o.HpMax;
        s.黄金 -= ClinicFee;
        return o.Hp - before;
    }
}
