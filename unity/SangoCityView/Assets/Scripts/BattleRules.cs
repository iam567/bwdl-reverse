// 原版战斗规则。能从反编译里读出来的照抄，读不出来的集中放在文件末尾并标明。
// 出处标注 b.java / c.java 的行号，改之前先回去对一眼。
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using UnityEngine;

public enum Arm { None = 0, Qi = 1, Gong = 2, Bu = 3 }        // bU[][]：0 武将 1 骑 2 弓 3 步
public enum Kind { Ping = 1, Shui = 2, Shan = 3 }             // I[]：平军 / 水军 / 山军

public class Officer
{
    public int Id; public string Name;
    public int Lv;          // H[]  1–8，决定骑/弓/步的配比
    public Kind Kind;       // I[]
    public int Troops;      // J[]  兵力
    public int HpMax;       // K[]
    public int Hp;          // L[]
    public int Int;         // N[]  智力
    public int Might;       // M[]  武力
    public int Virtue;      // O[]  德
    public int Loyal;       // P[]  忠诚
    public int Plots;       // Q[]  计策点
    public int ArmLv;       // R[]  兵种×等级 0–23
    public int SubLv;       // S[]  副属性 24–31
}

public static class BattleRules
{
    /// 原版 b.ad[]：R[] 是武器（0–23）、S[] 是防具（24–31）的下标。
    /// 单挑和列表画面都用 ad[R[x]] / ad[S[x]] 直接取名。
    public static readonly string[] EquipName = {
        "短剑","飞剑","长剑","破斩剑","大剑","七星剑","青釭剑","倚天剑",
        "雍刀","眉尖刀","三尖刀","钩镰刀","偃月刀","凤嘴刀","龙牙刀","关王刀",
        "矛","双戟","单钩枪","战斧","双钩枪","方天化戟","狼牙枪","蛇矛",
        "纶巾","笼手","兜","硬皮","鹤氅","甲胄","圣者之衣","龙鳞宝铠",
    };
    public static string WeaponOf(Officer o) => o.ArmLv >= 0 && o.ArmLv < 24 ? EquipName[o.ArmLv] : "—";
    public static string ArmorOf(Officer o)  => o.SubLv >= 24 && o.SubLv < 32 ? EquipName[o.SubLv] : "—";

    // ── 地形 ───────────────────────────────────────────────────────────
    /// b.h(cell, 兵种)：走一格扣多少机动力。高两位非 0 表示这格有人，过不去。
    public static int MoveCost(int cell, Kind k)
    {
        if ((cell & 0xC0) != 0) return 100;
        int t = cell & 0x1F;
        switch (t)
        {
            case 0: case 1: case 3: case 4:
                return k == Kind.Ping ? 2 : 3;                 // 平地·房屋
            case 6:
                return k == Kind.Shui ? 3 : (k == Kind.Ping ? 5 : 6);   // 深水
            case 7: case 8: case 9: case 10: case 11: case 12:
                return k == Kind.Shan ? 3 : (k == Kind.Ping ? 5 : 6);   // 山峰
            case 5:  return 5;                                 // 埋伏点
            case 2:  return 6;                                 // 城池，全图最贵
            default: return 100;
        }
    }

    public const int AttackCost = 2;        // c.java:4982 攻击固定扣 2

    /// c.e(地形码) → 战斗场景：0 平原 / 1 水战 / 2 山林 / 3 攻城
    public static int SceneOf(int terrain)
    {
        switch (terrain)
        {
            case 0: case 1: case 3: case 4: return 0;
            case 6: return 1;
            case 2: return 3;
            default: return 2;                                 // 5, 7–12
        }
    }

    /// b.g(计策号)：施展要多少机动力。
    /// 注意游戏自己的说明文案有四条和这里对不上（虚兵/落石/水攻/连弩），以本表为准。
    static readonly int[] PlotCost = { 6,5,4,6,7,8,8,8,10,9,10,10,10,8,12,10 };
    public static readonly string[] PlotName = {
        "火计","陷阱","虚兵","要击","乱水","火箭","共杀","伪击转杀",
        "笼络","落石","连环","十面埋伏","水攻","连弩","劫火","奇门遁甲" };
    public static int CostOfPlot(int i) => (i >= 0 && i < 16) ? PlotCost[i] : 100;
    /// d.q[16]：原版菜单里的计策说明（原话）。文案里的机动力数有四条和代码不符，
    /// 显示时把「需机动力:N」那一截去掉、按代码扣的数另标。
    public static readonly string[] PlotDesc = {
        "用于平原,树林的敌军.", "用于山地,树林的敌军.减少敌将的体力.",
        "用于山地,树林的敌军,限制敌军移动.", "用于山地,树林的敌军,武力低的武将使用效果不好.",
        "用于水中的敌军.", "用于主将,减少军粮.",
        "用于两支以上相邻的敌军,而且一支必须在山地,我军必须与山地军相邻.",
        "用于城中的敌军,但必须与敌军相邻.", "笼络敌军中忠诚低的武将.",
        "我军在山地或城中,敌军相邻.杀伤敌兵,减少敌将体力.",
        "用于两支以上的相邻敌军,而且一支必须在水中.限制复数敌军的行动.",
        "我军在树林中,施用此计,我军自损100兵,但如果敌军进入此树林,则伤亡惨重.",
        "用于两支以上相邻敌军,而且一支必须在水中.", "适用于守城,对敌军造成大伤害.",
        "用于两支以上相邻敌军,而且一支在平原或树林.", "用于山地,树林,平原的敌军.限制敌军移动,且日减100兵.",
    };

    // ── 战棋层的 16 种计策（b.java 2285 / 2355 / 2519，逐段移植）──────────
    /// 计策效果的打包格式，和原版 b.a(short,short,byte,short) 的返回值一致：
    ///   类型 << 24 | 体力伤害 << 16 | 兵力伤害。返回 -1 = 施展失败。
    /// 类型：1 减兵 2 减体力 3 减粮 4 减兵+体力 5 笼络(俘为我方) 6 减兵并波及相邻敌军
    ///       7 虚兵(被困) 8 连环(相邻敌军一起被困) 9 奇门遁甲 10 埋伏 11 十面埋伏结算
    public struct PlotResult { public int Type, Hp, Troops; public bool Ok => Type > 0; }

    /// b.a(int,int,byte,boolean)：这一格能不能对它用这个计策。
    /// casterT / targetT 是地形码低 5 位；targetHasAlly = 目标旁边还贴着另一支敌军（原版 i()&1）。
    public static bool PlotLegal(int plot, int cx, int cy, int tx, int ty, int casterT, int targetT, bool targetHasAlly)
    {
        if (Mathf.Abs(cx - tx) > 5 || Mathf.Abs(cy - ty) > 5) return false;     // a(...,5)
        bool adj = Mathf.Abs(cx - tx) <= 1 && Mathf.Abs(cy - ty) <= 1;           // a(...,1)
        switch (targetT)
        {
            case 0: case 1: case 3: case 4:
                if (plot == 0 || plot == 8 || plot == 15) return true; break;
            case 7: case 8: case 9: case 10: case 11: case 12:
                if ((plot >= 1 && plot <= 3) || plot == 8 || plot == 15) return true;
                if (plot == 6 && targetHasAlly && adj) return true; break;
            case 6:
                if (plot == 4 || plot == 8) return true;
                if ((plot == 10 || plot == 12) && targetHasAlly) return true; break;
            case 5:
                if ((plot >= 0 && plot <= 3) || plot == 5 || plot == 8 || plot == 15) return true; break;
            case 2:
                if (plot == 7 && adj) return true; break;
        }
        if (((plot == 13 && casterT == 2) || (plot == 9 && casterT >= 7 && casterT <= 12 && targetT != 2)) && adj) return true;
        // 劫火(14)：原版 2349 那两行两个分支都 return false——这条计策在原版里永远用不出去
        return false;
    }

    /// b.a(short,short,byte,short)：成功判定 + 伤害。
    /// foodPool 只给火箭(5)封顶用：伤害不能超过对方现有粮草。
    public static PlotResult PlotRoll(int plot, Officer me, Officer foe, int foodPool, System.Random rng)
    {
        var r = new PlotResult();
        if (plot == 8)                                                          // 笼络：h(s,s2)
        {
            if (foe.Loyal > 35) return r;
            int n = me.Int - foe.Int + (35 - foe.Loyal);
            if (n >= rng.Next(20)) { r.Type = 5; }
            return r;
        }
        if (plot == 11) { r.Type = 10; return r; }                              // 埋伏：直接成功
        int need = plot <= 1 ? 40 : (plot == 2 || plot == 7 || plot == 9) ? 56
                 : (plot >= 3 && plot <= 5) ? 48 : (plot == 6 || plot == 10 || plot == 12 || plot == 13) ? 64 : 72;
        int n3 = me.Lv >= foe.Lv ? me.Int + me.Int * (me.Lv - foe.Lv) / 10 - foe.Int
                                 : me.Int - foe.Int - foe.Int * (foe.Lv - me.Lv) / 10;
        if (n3 < need && rng.Next(need) >= n3) return r;                        // 失败
        int hp = 20 + rng.Next(-3, 4);                                          // ap.nextInt()%4 可为负
        if (hp >= foe.Hp) hp = foe.Hp - 1;
        if (plot == 1) { r.Type = 2; r.Hp = hp; return r; }                     // 陷阱：只减体力
        if (plot == 2) { r.Type = 7; return r; }                                // 虚兵
        if (plot == 10) { r.Type = 8; return r; }                               // 连环
        if (plot == 15) { r.Type = 9; return r; }                               // 奇门遁甲
        int dmg, type;
        switch (plot)
        {
            case 0: case 3: case 4: case 7: dmg = 100; type = 1; break;
            case 5: dmg = 160; type = 3; break;
            case 6: dmg = 150; type = 6; break;
            case 9: dmg = 150; type = 4; break;
            case 12: dmg = 180; type = 6; break;
            default: dmg = 200; type = 6; break;                                // 13 连弩 / 14 劫火
        }
        if (me.Lv > foe.Lv) dmg += dmg * (1 + (me.Lv - foe.Lv) / 10);
        dmg += rng.Next(-29, 30);                                               // ap.nextInt()%30
        if (plot == 5) { if (dmg > foodPool) dmg = foodPool; }
        else if (dmg > foe.Troops) dmg = foe.Troops;
        r.Type = type; r.Troops = Mathf.Max(0, dmg);
        if (plot == 9) r.Hp = hp;                                               // 落石：兵和体力一起扣
        return r;
    }

    /// b.a(int,byte) 的那一行提示
    public static string PlotMessage(int plot, PlotResult r)
    {
        switch (plot)
        {
            case 1: return "武将体力减少 " + r.Hp;
            case 2: return "军队中计被困";
            case 5: return "粮食减少 " + r.Troops;
            case 8: return "成功笼络武将!";
            case 9: return "将体力减少 " + r.Hp + ",兵数减少 " + r.Troops;
            case 10: return "军队中计, 被困三天";
            case 11: return "军队埋伏完毕!";
            case 15: return "奇门遁甲施展成功!";
            default: return "士兵数减少 " + r.Troops;
        }
    }

    /// b.d(武将)：智力与等级决定能用几种计策
    public static int PlotCount(Officer o)
    {
        if (o.Int >= 99 && o.Lv >= 8) return 16;
        if (o.Int >= 98 && o.Lv >= 7) return 15;
        if (o.Int >= 97 && o.Lv >= 5) return 14;
        if (o.Int >= 93 && o.Lv >= 4) return 12;
        if (o.Int >= 85) return 10;
        if (o.Int >= 80) return 8;
        return 6;
    }

    /// b.c(总兵力)：每回合机动力的上限，按千位查表
    static readonly int[] MpCap = { 20,26,32,36,42,46,51,53,55,57,59 };
    public static int MoveCap(int totalTroops)
    {
        int i = Mathf.Clamp(totalTroops / 1000, 0, MpCap.Length - 1);
        return MpCap[i];
    }

    // ── 兵种克制 ───────────────────────────────────────────────────────
    /// b.ad()：R/8 分三大类，循环相克。cC 同时作用于攻守，
    /// 所以强攻命中率在 60%↔80% 摆动、必杀在 10%↔30%。
    public static int Counter(Officer atk, Officer def)
    {
        int d = atk.ArmLv / 8 - def.ArmLv / 8;
        if (d == 0) return 0;
        return (d == 1 || d == -1) ? -1 : 1;
    }

    /// af[]：兵种等级加成表，四组各 8 级
    static readonly int[][] Af = {
        new[]{  5, 7,12,18,25,50,50,50 },     // 组 0  R 0–7
        new[]{  6,10,15,20,24,26,45,50 },     // 组 1  R 8–15
        new[]{  8,10,12,20,25,30,50,50 },     // 组 2  R 16–23
        new[]{  5, 8,10,15,20,25,50,60 },     // 副属性 S 24–31
    };
    public static int ArmBonus(int r)
    {
        if (r >= 24) return Af[3][Mathf.Clamp(r - 24, 0, 7)];
        return Af[Mathf.Clamp(r / 8, 0, 2)][Mathf.Clamp(r % 8, 0, 7)];
    }

    // ── 阵型战 ─────────────────────────────────────────────────────────
    public const int GridW = 16, GridH = 7;          // bK = byte[7][16]

    /// 兵棋数 = (兵力-1)/100 + 2，兵力为 0 时只剩武将本人
    public static int PieceCount(int troops) => troops == 0 ? 1 : (troops - 1) / 100 + 2;

    /// b.a(boolean) 里按 H[]（等级）查出的两个分界：
    /// 兵棋 [1,x) 是骑兵、[x,y) 是弓兵、[y,兵棋数) 是步兵。等级越高骑兵占比越大。
    static readonly int[,] Split = {
        {3,6},{3,6},{3,7},{4,7},{5,8},{6,9},{6,10},{7,10},{7,10}
    };
    public static void ArmSplit(int lv, out int x, out int y)
    {
        int i = Mathf.Clamp(lv, 0, Split.GetLength(0) - 1);
        x = Split[i, 0]; y = Split[i, 1];
    }

    /// b.d(boolean,byte)：每个兵棋的战力
    public static int PieceMight(Officer o, Arm a)
    {
        switch (a)
        {
            case Arm.None: return o.Might + o.Might * ArmBonus(o.ArmLv) / 100;   // 武将本人
            case Arm.Qi:   return o.Might;                                       // 骑兵
            default:       return o.Might * 3 / 5;                               // 弓兵 / 步兵
        }
    }

    // ── 单挑 ───────────────────────────────────────────────────────────
    // 触发：阵型战里**双方武将本人**贴上并互相攻击时，b.c(boolean) 走这一支：
    //   if (bU[被打] == 0 && bU[打人] == 0) { aq = 1; return; }   // 不扣体力，进单挑
    // 然后 ah() 跑 ae() 算战力、进画面 21 演出，打完回阵型战接着打。

    /// b.ae()：单挑的四个战力。攻用兵种等级 R 的加成，防用副属性 S 的。
    public static void DuelStats(Officer me, Officer foe,
                                 out int myAtk, out int myDef, out int foeAtk, out int foeDef)
    {
        myAtk  = me.Might  + me.Might  * ArmBonus(me.ArmLv)  / 100;
        myDef  = me.Might  + me.Might  * ArmBonus(me.SubLv)  / 100;
        foeAtk = foe.Might + foe.Might * ArmBonus(foe.ArmLv) / 100;
        foeDef = foe.Might + foe.Might * ArmBonus(foe.SubLv) / 100;
    }

    /// b.a(武将, 分子, 分母)：单挑的基础伤害
    public static int DuelBase(Officer o, int num, int den)
    {
        int n = o.Might * num / Mathf.Max(1, den);
        n = Mathf.Min(n, o.Might * 2);
        n = Mathf.Max(n, o.Might / 3);
        return n + n / 10;                       // 固定 +10%
    }
    /// b.b()：普通攻击，必中，最低 1
    public static int DuelPlain(Officer o, int num, int den)
        => Mathf.Max(1, DuelBase(o, num, den) / 20);
    /// b.c()：强攻。命中率 7±cC 成 —— 克制把它在 60%↔80% 之间摆
    public static int DuelHeavy(Officer o, int num, int den, int cc, bool isAttacker, System.Random rng)
        => rng.Next(10) < (isAttacker ? 7 + cc : 7 - cc) ? DuelBase(o, num, den) / 5 : 0;
    /// b.d()：必杀。命中率 2±cC 成；**落空返回负数，调用点做 L += 负数，等于自伤 14–22 点**
    public static int DuelCrit(Officer o, int num, int den, int cc, bool isAttacker, System.Random rng)
        => rng.Next(10) < (isAttacker ? 2 + cc : 2 - cc)
           ? DuelBase(o, num, den) / 2
           : -(14 + rng.Next(9));

    /// 单挑的六招（画死在原版 lib60 那张菜单图里）
    public static readonly string[] DuelMove = { "牵制", "攻击", "全力", "劝降", "逃跑", "投降" };

    /// b.k(我,敌)：劝降。敌越虚弱、忠诚越低越容易；说服力是德七智三。
    /// 这就是原版收将的正路——先把体力磨下去再劝。
    public static bool Persuade(Officer me, Officer foe, System.Random rng)
    {
        // 原版 b.k() 第一行 if (P > 99) return false：主公永远劝不动。
        // 我们的设定是主公到最后也能被劝降/笼络/俘虏（见 HOUSE_RULES），这一行去掉。
        // 忠诚 100 仍然进公式：L + 100 − n + rng < 100 ⇒ 只有体力打到个位数才可能，够苛刻。
        int n = (me.Virtue * 7 + me.Int * 3) * 10 / 99;
        n = (n - 70) / 5;
        // 原版只特判 4→6、5→8；德智都到 100 时 x=101 落进 6 那格、没特判，反而比 95 档低。
        // 把 6 也抹平成 8（HOUSE_RULES），别让满值吃亏。
        if (n == 4) n = 6; else if (n >= 5) n = 8;
        return foe.Hp + foe.Loyal - n + rng.Next(4) < 100;
    }

    /// 逃跑：**用的就是单挑胜负那个公式**，武力高才跑得掉
    public static bool Flee(Officer me, Officer foe, System.Random rng) => DuelWin(me, foe, rng);

    /// b.c(我,敌)：单挑 AI 的取舍。0/1 = 普通或强攻，2 = 拼必杀，4 = 撤
    public static int DuelDecide(Officer me, Officer foe, System.Random rng)
    {
        if (me.Hp < 20 && rng.Next(2) == 0) return 4;
        if (me.Hp < me.HpMax / 2 && foe.Hp - me.Hp > 10 && rng.Next(10) < 3) return 2;
        if (me.Hp < foe.Hp) return rng.Next(10) < 6 ? 1 : 0;
        return rng.Next(10) < 6 ? 0 : 1;
    }

    /// b.b(我,敌)：基准 50%，每 20 点武力差折 10%
    public static bool DuelWin(Officer me, Officer foe, System.Random rng)
        => rng.Next(10) < 5 + (me.Might - foe.Might) / 20;

    /// b.j(部队)：溃败损失
    public static int RoutLoss(int troops, System.Random rng)
    {
        int loss = troops / 2 + rng.Next(100);
        if (loss < 200) loss = 200 + rng.Next(100);
        return Mathf.Min(loss, troops);
    }

    // ── 阵型战的实际结算 ──────────────────────────────────────────────
    // 阵型战不是回合制，是 b.T() 里 sleep(100) + repaint 的实时循环：
    // 兵棋自动走位、贴上就打。下面是打一次的伤害，出处 b.V() b.java:5211。

    /// b.d(boolean,byte)：攻击力。**挂在武将的武力上**，按兵种打折。
    public static int Atk(Officer o, Arm a)
    {
        int v;
        switch (a)
        {
            case Arm.None: v = o.Might + o.Might * ArmBonus(o.ArmLv) / 100; break;
            case Arm.Qi:   v = o.Might; break;
            default:       v = o.Might * 3 / 5; break;
        }
        return Mathf.Max(1, v);
    }
    /// 防御力用的是**副属性** S 的加成，不是兵种等级 R
    public static int Def(Officer o) => o.Might + o.Might * ArmBonus(o.SubLv) / 100;

    /// b.a(short)：整数平方根，封顶 15
    public static int ISqrt(int s)
    {
        for (int i = 15; i > 0; i--) if (i * i <= s) return i;
        return 0;
    }

    // ── 计策点能力 ─────────────────────────────────────────────────────
    // 原版把生效中的增益编在 cd 里：高 4 位 = 能力号(1..6)，低 4 位 = 剩余回合。
    // 每过一回合低 4 位减 1，减到 0 就清空（b.java:5517）。
    public static readonly string[] SkillName = { "咒缚", "挑战", "连弩", "呐喊", "火矢", "爆炎" };
    public static readonly int[] SkillCost    = {    3,     5,     7,     8,     10,    12 };
    public static readonly string[] SkillDesc = {
        "限制敌将行动",   "向敌将发出单挑邀请", "箭手射程增加",
        "全军攻击力增加", "箭手攻击力大大增加", "利用火器攻击敌军" };
    /// 持续回合数**每个技能不一样**，是写死在 b.a(byte) 里的：
    ///   咒缚 cd=0x14(4 回合)  连弩 0x33(3)  呐喊 0x44(4)  火矢 0x53(3)
    ///   挑战与爆炎不设 cd —— 挑战直接进单挑，爆炎是一次性的范围伤害
    static readonly int[] SkillCd = { 0x14, 0, 0x33, 0x44, 0x53, 0 };
    public static int CdOf(int skill) => (skill >= 0 && skill < 6) ? SkillCd[skill] : 0;

    /// 计策点 = 智力 × 13 / 100（b.o()），**每战重算，不是武将身上的 Q[]**。
    /// 截图里华雄智力 77 → 10 点，对得上。
    public static int PlotPoints(Officer o) => o.Int * 13 / 100;

    /// 咒缚：用的是标准计策成功判定
    public static bool Bind(Officer me, Officer foe, System.Random rng) => PlotHits(me, foe, rng);

    /// 挑战 b.m()：**敌方越强越容易请动**，我方远强于对方时几乎请不动
    public static bool Challenge(Officer me, Officer foe, System.Random rng)
    {
        int n = foe.Might + foe.Hp - me.Might - me.Hp + 30;
        return rng.Next(51) <= ISqrt(n) * 10;
    }

    /// 爆炎 b.W()：以我方武将为中心，**向前 3 列 × 上下各 1 行**
    public const int BlazeCols = 3, BlazeRows = 1;
    public static int BlazeHpDamage(System.Random rng)    => rng.Next(21) + 30;   // 打武将，扣体力
    public static int BlazeTroopDamage(System.Random rng) => rng.Next(21) + 60;   // 打兵棋，扣兵
    public static int BuffId(int cd) => (cd & 0xF0) >> 4;      // 1..6，0 = 没有

    /// ⚠ 这里和原版不一样，是有意改的。
    /// 原版 b.V() 里呐喊(cd&0xF0==64)和火矢(==80)两处乘的都是 **1**——
    /// 不是反编译丢了乘数，javap 看字节码就是 iconst_1; imul，等于没效果。
    /// 按玩家的决定接成：呐喊 全军 ×1.5、火矢 弓兵 ×2。
    /// 想回到原版行为，把这个函数的返回值全改回 1f 即可。
    public const string HouseRuleNote = "呐喊×1.5 / 火矢×2 是我们加的，原版这两处乘的是 1";
    public static float BuffMul(int cd, Arm arm)
    {
        switch (BuffId(cd))
        {
            case 4: return 1.5f;                                   // 呐喊：全军
            case 5: return arm == Arm.Gong ? 2f : 1f;              // 火矢：只加弓兵
            default: return 1f;
        }
    }
    /// 连弩把射程从 4 格抬到 6 格 —— **这条是原版真有的**（o() 里 by2 由 5 变 7）
    public static int ArrowRange(int cd) => BuffId(cd) == 3 ? 6 : 4;

    /// b.V()：一枚兵棋打一次造成多少伤害
    public static int Damage(int atk, int def, int attackerTroops, System.Random rng,
                             int cd = 0, Arm arm = Arm.None)
    {
        def = Mathf.Max(1, def);
        atk = Mathf.RoundToInt(atk * BuffMul(cd, arm));     // ⚠ 见 BuffMul 的说明
        int n4 = atk * atk / def;
        int n3 = atk >= def ? Mathf.Min(n4, atk * 3 / 2) : Mathf.Max(n4, atk * 2 / 3);
        n3 = ISqrt(n3) * 5;
        n3 = n3 * attackerTroops / 100;            // 按这枚兵棋还剩多少兵缩放
        n3 += rng.Next(n3 / 10 + 1);
        return Mathf.Min(110, n3);
    }
    /// 打到武将本人身上是扣体力，不是扣兵
    public static int HpHit(int damage) => damage / 4 + 1;

    /// b.a(short,short)：计策成功率。挂了很久的那条，终于对上了。
    public static bool PlotHits(Officer me, Officer foe, System.Random rng)
    {
        int n = me.Int + 20 - foe.Int;
        if (n < 0) return false;
        return rng.Next(100) <= ISqrt(n) * 10;
    }

    // ── 数据装载 ───────────────────────────────────────────────────────
    /// cities.csv：1 起数 col7 = l[] 黄金、col8 = m[] 粮草（GAME_LOGIC 已验证），即下标 6 / 7。
    public static Dictionary<int, (int gold, int food)> LoadCitySupply(string csvPath)
    {
        var d = new Dictionary<int, (int, int)>();
        if (!File.Exists(csvPath)) return d;
        foreach (var line in File.ReadAllLines(csvPath))
        {
            var a = line.Split(',');
            if (a.Length < 8 || !int.TryParse(a[0], out int id)) continue;
            int.TryParse(a[6], out int g); int.TryParse(a[7], out int f);
            d[id] = (g, f);
        }
        return d;
    }
    /// city-adjacency.json 是 { "13": {"adj":[..]} } 这种字典，JsonUtility 吃不下，用正则抠。
    public static int[] AdjacentCities(string jsonPath, int city)
    {
        if (!File.Exists(jsonPath)) return new int[0];
        var m = System.Text.RegularExpressions.Regex.Match(File.ReadAllText(jsonPath),
                    "\"" + city + "\"\\s*:\\s*\\{[^}]*?\"adj\"\\s*:\\s*\\[([^\\]]*)\\]");
        if (!m.Success) return new int[0];
        var list = new List<int>();
        foreach (var t in m.Groups[1].Value.Split(',')) if (int.TryParse(t.Trim(), out int v)) list.Add(v);
        return list.ToArray();
    }

    public static Dictionary<int, Officer> LoadOfficers(string csvPath)
    {
        var d = new Dictionary<int, Officer>();
        foreach (var line in File.ReadAllLines(csvPath))
        {
            var c = line.Split(',');
            if (c.Length < 16) continue;
            int I(int i) => int.TryParse(c[i], NumberStyles.Integer,
                                         CultureInfo.InvariantCulture, out var v) ? v : 0;
            var o = new Officer {
                Id = I(0), Name = c[1], Lv = I(2), Kind = (Kind)Mathf.Clamp(I(3), 1, 3),
                Troops = I(4), HpMax = I(5), Hp = I(5), Int = I(6), Might = I(7),
                Virtue = I(8), Loyal = I(9), Plots = I(10), ArmLv = I(11), SubLv = I(12),
            };
            if (o.Id > 0) d[o.Id] = o;
        }
        return d;
    }

    /// .map 是纯文本 CSV，一行一排
    public static int[,] LoadMap(string path)
    {
        var lines = new List<int[]>();
        foreach (var raw in File.ReadAllLines(path))
        {
            var s = raw.Trim();
            if (s.Length == 0) continue;
            var parts = s.Split(',');
            var row = new List<int>();
            foreach (var p in parts) if (p.Length > 0) row.Add(int.Parse(p));
            lines.Add(row.ToArray());
        }
        int h = lines.Count, w = 0;
        foreach (var r in lines) w = Mathf.Max(w, r.Length);
        var g = new int[h, w];
        for (int y = 0; y < h; y++)
            for (int x = 0; x < lines[y].Length; x++) g[y, x] = lines[y][x];
        return g;
    }
}
