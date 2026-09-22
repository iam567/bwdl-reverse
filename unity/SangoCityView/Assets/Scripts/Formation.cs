// 阵型布阵。照 b.java:905(我方) / 1006(敌方) / 3986(城墙) / 855-900(贴墙站) 逐行复现，
// 复现结果和游戏自己画在 lib49 里的四张示意图逐格一致（见 assets/formations/_阵型对照.png）。
using System.Collections.Generic;
using UnityEngine;

public struct Piece
{
    public int Col, Row;
    public Arm Arm;
    public int Troops;        // bZ[][]，这枚兵棋还剩多少兵
    public bool Alive;
    public bool Off;          // cb=false 且坐标 -1：撤退出了场。兵还算数，只是不在盘上
    public int Dir;           // bX[][]：0 上 1 下 2 左 3 右
    public int HitArm;        // 最近一次被谁打的：0 武将 1 骑 2 弓 3 步 4 爆炎——决定命中特效用哪套
    public float HitAt;       // 刚挨打的时刻，用来闪那一下爆点
}

public static class Formation
{
    // Java 的 byte 会截断，照抄时得跟着截，不然个别格子会跑到棋盘外
    static int SB(int v) { v &= 0xFF; return v > 127 ? v - 256 : v; }

    /// b.a(兵棋数, 阵型号)：我方站位
    public static List<Vector2Int> Ours(int nb, int f)
    {
        var o = new List<Vector2Int>();
        for (int i = 1; i < nb; i++)
        {
            int col, row;
            if (f == 0)
            {
                int q = (i - 1) / 3, r = (i - 1) % 3;
                if (q == 0)      { col = 3; row = 6 - r; }
                else if (q == 1) { col = 2; row = SB(4 - r); }
                else if (q == 2) { col = 1; row = SB(2 - r); }
                else             { col = 1; row = 6; }
                o.Add(new Vector2Int(col + 1, row));
            }
            else if (f == 1)
            {
                int c = SB(3 - (i - 1) / 4);
                int r = (i % 2 == 1) ? SB(6 - (i + 1) / 4) : SB(i / 4);
                o.Add(new Vector2Int(c + 1, r));
            }
            else if (f == 2)
            {
                int k = i > 7 ? SB(i - 7) : i, n = i > 7 ? -2 : 0;
                n = SB(n + SB(4 - k / 2));
                int r = (k % 2 == 1) ? SB(3 - k / 2) : SB(3 + k / 2);
                o.Add(new Vector2Int(n + 1, r));
            }
            else
            {
                int k = i > 5 ? SB(i - 5) : i, n = i > 5 ? -2 : 0, m;
                if (k > 3) { k = SB(k - 3); n = SB(n - 1); m = -1; } else m = 0;
                n = SB(n + 4);
                m = k == 1 ? SB(m + 3) : (k == 2 ? SB(m + 5) : SB(m + 1));
                o.Add(new Vector2Int(n + 1, m));
            }
        }
        return o;
    }

    /// b.b(兵棋数, 阵型号)：敌方站位。不是简单的 16-x 镜像，各 case 的常数是分别写死的
    public static List<Vector2Int> Theirs(int nb, int f)
    {
        var o = new List<Vector2Int>();
        for (int i = 1; i < nb; i++)
        {
            if (f == 0)
            {
                int q = (i - 1) / 3, r = (i - 1) % 3, col, row;
                if (q == 0)      { col = 12; row = 6 - r; }
                else if (q == 1) { col = 13; row = SB(4 - r); }
                else if (q == 2) { col = 14; row = SB(2 - r); }
                else             { col = 14; row = 6; }
                o.Add(new Vector2Int(col - 1, row));
            }
            else if (f == 1)
            {
                int c = SB(12 + (i - 1) / 4);
                int r = (i % 2 == 1) ? SB(6 - (i + 1) / 4) : SB(i / 4);
                o.Add(new Vector2Int(c - 1, r));
            }
            else if (f == 2)
            {
                int k = i > 7 ? SB(i - 7) : i, n = i > 7 ? 2 : 0;
                n = SB(n + SB(11 + k / 2));
                int r = (k % 2 == 1) ? SB(3 - k / 2) : SB(3 + k / 2);
                o.Add(new Vector2Int(n - 1, r));
            }
            else
            {
                int k = i > 5 ? SB(i - 5) : i, n = i > 5 ? 2 : 0, m;
                if (k > 3) { k = SB(k - 3); n = SB(n + 1); m = -1; } else m = 0;
                n = SB(n + 11);
                m = k == 1 ? SB(m + 3) : (k == 2 ? SB(m + 5) : SB(m + 1));
                o.Add(new Vector2Int(n - 1, m));
            }
        }
        return o;
    }

    /// b.c(boolean,n) → a()/b()：攻城时守方不按阵型，贴着墙线站。
    /// 先沿行 0 与行 6 排，站满 6 个之后余部退到内侧纵列。
    public static List<Vector2Int> HugWall(int nb, bool ours)
    {
        var o = new List<Vector2Int>();
        int onWall = Mathf.Min(6, nb - 1);
        for (int i = 1; i <= onWall; i++)
            o.Add(new Vector2Int(ours ? 8 - (i - 1) / 2 : 7 + (i - 1) / 2, (i - 1) % 2 * 6));
        for (int j = 1; j <= nb - 1 - onWall; j++)
            o.Add(new Vector2Int(ours ? 2 : 13, j));
        return o;
    }

    /// b.S() 的 bH==2 分支：只有守方站在城池格上才往棋盘里填城墙。
    /// weAttack 为真 = 我方是攻方，墙在右(11 列)；为假 = 我方守城，墙在左(4 列)。
    /// 32 / 16 / 2 三种标记照抄，含义没追。
    public static Dictionary<Vector2Int, int> Walls(bool weAttack)
    {
        var g = new Dictionary<Vector2Int, int>();
        int main = weAttack ? 11 : 4, inner = weAttack ? 9 : 6;
        int gateCol = weAttack ? 6 : 9, sideCol = weAttack ? 10 : 5;
        foreach (int r in new[] { 0, 1, 5, 6 }) g[new Vector2Int(main, r)] = 32;
        g[new Vector2Int(inner, 3)] = 32;
        g[new Vector2Int(gateCol, 0)] = 2;  g[new Vector2Int(gateCol, 6)] = 2;
        g[new Vector2Int(sideCol, 1)] = 16; g[new Vector2Int(sideCol, 5)] = 16;
        for (int c = 6; c < 10; c++) { g[new Vector2Int(c, 1)] = 32; g[new Vector2Int(c, 5)] = 32; }
        return g;
    }

    /// 按等级把兵力摊到各兵棋上：[1,x) 骑、[x,y) 弓、[y,n) 步，段内均分
    public static List<Piece> Build(Officer o, int formation, bool ours,
                                    bool siege, bool weAttack)
    {
        int nb = BattleRules.PieceCount(o.Troops);
        bool hug = siege && (ours ? !weAttack : weAttack);       // 守城那一方贴墙
        var pos = hug ? HugWall(nb, ours)
                      : (ours ? Ours(nb, formation) : Theirs(nb, formation));

        BattleRules.ArmSplit(o.Lv, out int x, out int y);
        var list = new List<Piece> {
            // 0 号是武将本人，固定在最左/最右的中间一行
            // bX[0][0]=3 朝右、bX[1][0]=2 朝左 —— 开局双方就是面对面的
            new Piece { Col = ours ? 1 : BattleRules.GridW - 2, Row = 3,
                        Arm = Arm.None, Troops = 100, Alive = true, Dir = ours ? 3 : 2 }
        };
        int left = o.Troops;
        for (int i = 1; i < nb; i++)
        {
            var arm = i < x ? Arm.Qi : (i < y ? Arm.Gong : Arm.Bu);
            int t = left / (nb - i);
            left -= t;
            var p = pos[i - 1];
            list.Add(new Piece {
                Col = Mathf.Clamp(p.x, 0, BattleRules.GridW - 1),
                Row = Mathf.Clamp(p.y, 0, BattleRules.GridH - 1),
                Arm = arm, Troops = t, Alive = true, Dir = ours ? 3 : 2 });
        }
        return list;
    }

    /// n()：按兵种归并现有兵力。[0] 位放体力，和原版一致。
    public static int[] ByArm(List<Piece> ps, int hp)
    {
        var a = new int[4]; a[0] = hp;
        foreach (var p in ps)
            if (p.Alive && p.Arm != Arm.None) a[(int)p.Arm] += p.Troops;
        return a;
    }
    public static int TotalTroops(List<Piece> ps)
    {
        int s = 0;
        foreach (var p in ps) if (p.Alive && p.Arm != Arm.None) s += p.Troops;
        return s;
    }
}
