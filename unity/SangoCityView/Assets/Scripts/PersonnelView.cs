// 功曹府四条指令的界面流程，照原版的步骤表 bR[6..9] 走（规则在 Personnel.cs）：
//   搜索  选将（屏 7）→ 结果（屏 11）
//   登用  选在野贤才（屏 7，名单是 C[]）→ 选说客（屏 7，名单是 A[]）→ 成败
//   奖赏  选将（屏 7，名单只有忠诚 < 90 的）→ 定赏额（屏 12：金子或宝物）→ 忠诚提高
//   任命  选将（屏 7）→「吾誓与此城同命!」——这条原版不扣令
// 弹框零件在 SheetView 里，和太守府（PoliticsView）共用。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using static UIKit;

public class PersonnelView : SheetView
{
    Personnel.Cmd cmd;
    MapData.Stat stat;
    Officer target;          // 登用：要招的那个在野；奖赏 / 任命：选中的那个

    public PersonnelView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("personnel", parent, w, h, panelSp, btnSp) { }

    string Title => Personnel.CmdName[(int)cmd];
    string Cg => Path.Combine("personnelcg", "rs_" + (int)cmd);

    static string Best(Officer o) => "智" + o.Int + " 武" + o.Might + " 德" + o.Virtue;

    /// 入口。前置条件不过就只弹一句话，原版这时候不扣指令。
    public void Begin(Personnel.Cmd c, MapData.Stat s)
    {
        cmd = c; stat = s;
        if (Personnel.Blocked(c, s, out string why)) { Say(Title, why, false, null, Gov(s)); return; }
        var court = Personnel.Of(s.id);
        switch (c)
        {
            case Personnel.Cmd.Search:      // 搜索只吃智力（能不能有收获）和德（收获多少）
                PickGrid("搜索　遣何人前往", court.A, o => "智 " + o.Int + "　德 " + o.Virtue,
                         o => DoSearch(o), Cancel);
                break;
            case Personnel.Cmd.Recruit:     // 先挑人：成不成看他三项里最高的那项
                PickGrid("登用　招揽何人", court.C, o => Best(o), o => { target = o; PickEnvoy(); }, Cancel);
                break;
            case Personnel.Cmd.Reward:
                PickGrid("奖赏　赏赐何人", Personnel.RewardList(s), o => "忠 " + o.Loyal,
                         o => { target = o; RewardAmount(); }, Cancel);
                break;
            case Personnel.Cmd.Appoint:
                PickGrid("任命　何人任太守", court.A, o => "德 " + o.Virtue + "　忠 " + o.Loyal,
                         o => DoAppoint(o), Cancel);
                break;
        }
    }

    /// 自检：直接跳到第二步，好截图看排版。会真的改数值，只给 -rs … -step 用。
    public void ForTest(Personnel.Cmd c, MapData.Stat s, int step)
    {
        cmd = c; stat = s;
        var a = Personnel.Of(s.id).A;
        if (step <= 0 || a.Count == 0) { Begin(c, s); return; }
        switch (c)
        {
            case Personnel.Cmd.Search:  DoSearch(a[a.Count - 1]); break;
            case Personnel.Cmd.Reward:  target = a[0]; target.Loyal = Mathf.Min(target.Loyal, 70); stat.宝物 = 2; RewardAmount(); break;
            case Personnel.Cmd.Appoint: DoAppoint(a[a.Count - 1]); break;
            case Personnel.Cmd.Recruit:
                target = a[a.Count - 1];                        // 借一个现成的人当在野，看选说客那屏
                if (step == 1) PickEnvoy();
                else Say(Title, "登用成功\n" + target.Name + " 愿效犬马之劳，带兵 " + target.Troops, true, Cg);
                break;
        }
    }

    // ── 搜索 ────────────────────────────────────────────────────────
    void DoSearch(Officer o)
    {
        var r = Personnel.Search(stat, o, rng);
        string msg = r.Msg;
        if (r.Kind == Personnel.Find.Talent)
            msg += "\n" + r.Talent.Name + "（" + Best(r.Talent) + "）隐居于此，可遣人登用";
        else if (r.Kind == Personnel.Find.Treasure)
            msg += "\n宝物 +" + r.Amount + "（现有 " + stat.宝物 + " 件，可用于奖赏）";
        // 五种结果各一段动画 rs_s<结果>；万一那段帧图还没到，就退回头像 + 文字
        Say(Title, o.Name + "：" + msg, true, Path.Combine("personnelcg", "rs_s" + (int)r.Kind), o);
    }

    // ── 登用 ────────────────────────────────────────────────────────
    /// 第二次选将：派谁去说。只看说客的德，德越高越容易请得动。
    void PickEnvoy()
    {
        PickGrid("登用　遣何人往说 " + target.Name, Personnel.Of(stat.id).A,
                 o => "德 " + o.Virtue, DoRecruit, () => Begin(cmd, stat));
    }

    void DoRecruit(Officer by)
    {
        bool ok = Personnel.Recruit(stat, by, target, rng);
        string msg = ok ? "登用成功\n" + target.Name + " 愿效犬马之劳，带兵 " + target.Troops
                        : "登用失败\n" + target.Name + " 婉拒了 " + by.Name;   // d.d[2][0] / [2][1]
        Say(Title, msg, true, ok ? Cg : null, by);                              // 成败都扣令
    }

    // ── 奖赏：屏 12 ──────────────────────────────────────────────────
    /// 原版两个选项：赏金（1~min(城中金子,100)，四位数字键入）或赏一件宝物。
    /// 触屏上把键入改成加减按钮，默认给到上限，数额不能为 0。
    int amount;
    void RewardAmount()
    {
        int cap = Mathf.Min(stat.黄金, Personnel.RewardCap);
        amount = cap;
        DrawReward(cap);
    }

    void DrawReward(int cap)
    {
        bool hasGold = cap > 0, hasGem = stat.宝物 > 0;
        var box = FaceSheet(Title, target, new[] {
            target.Name + "　忠 " + target.Loyal,
            hasGold ? "赏金 " + amount : "城中已无金子可赏",
            "城中金子 " + stat.黄金 + (hasGem ? "　宝物 " + stat.宝物 : ""),
        }, 30 + 44 + (hasGem ? BTN_H + 10 : 0));
        float w = box.sizeDelta.x, y = 180;

        if (hasGold)
        {
            // −10 −1 +1 +10，一排四个小钮
            int[] steps = { -10, -1, 1, 10 };
            float bw = 62, gap = 8, x0 = (w - (bw * 4 + gap * 3)) / 2;
            for (int i = 0; i < 4; i++)
            {
                int d = steps[i];
                Btn(box, (d > 0 ? "+" : "−") + Mathf.Abs(d), x0 + i * (bw + gap), y, bw, 34, () => {
                    amount = Mathf.Clamp(amount + d, 1, cap); DrawReward(cap);
                });
            }
        }
        y += 44;
        if (hasGem)
        {
            Btn(box, "赏宝物一件（现有 " + stat.宝物 + "）", (w - 250) / 2, y, 250, BTN_H,
                () => DoReward(Personnel.Treasure));
            y += BTN_H + 10;
        }
        if (hasGold) Btn(box, "赏金", w / 2 - 104, y, 96, BTN_H, () => DoReward(amount));
        Btn(box, "放弃", hasGold ? w / 2 + 8 : (w - 96) / 2, y, 96, BTN_H, Cancel);
    }

    void DoReward(int n)
    {
        int gain = Personnel.Reward(stat, target, n, rng);
        string cost = n == Personnel.Treasure ? "宝物 −1" : "金子 −" + n;
        Say(Title, "武将的忠诚度提高!\n" + target.Name + " 忠诚 +" + gain + "（" + target.Loyal + "）　" + cost,
            true, Cg);                                                         // d.d[2][2]
    }

    // ── 任命 ────────────────────────────────────────────────────────
    void DoAppoint(Officer o)
    {
        Personnel.Appoint(stat, o);
        // 步骤表 bR[9]={3,97,0}：aT() 之后走 aK()，不扣令
        Say(Title, o.Name + "：吾誓与此城同命!\n" + stat.name + " 太守改由 " + o.Name + " 担任", false, Cg);
    }
}
