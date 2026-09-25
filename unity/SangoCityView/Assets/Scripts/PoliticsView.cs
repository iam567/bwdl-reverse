// 太守府四条指令的界面流程，照原版的步骤表走：
//   选武将（原版屏 7）→「将消耗金子 N」确认（屏 13）→ 执行结果（屏 10/11）→ 回城内
// 弹框零件在 SheetView 里，和功曹府（PersonnelView）共用。
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public class PoliticsView : SheetView
{
    Politics.Cmd cmd;
    MapData.Stat stat;
    List<Officer> roster;
    Officer picked;
    int gold;

    public PoliticsView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("politics", parent, w, h, panelSp, btnSp) { }

    string Title => Politics.CmdName[(int)cmd];

    /// 入口。前置条件不过就只弹一句话，原版这时候不扣指令。
    public void Begin(Politics.Cmd c, MapData.Stat s, Dictionary<int, Officer> all)
    {
        cmd = c; stat = s;
        if (Politics.Blocked(c, s, out string why)) { Say(Title, why, false, null, Gov(s)); return; }
        roster = Politics.Roster(all, s.id);
        if (roster.Count == 0) { Say(Title, "城中无将可遣", false, null); return; }
        // 这四条指令只吃智力，所以卡片上只写智力
        PickGrid(Title + "　遣何人前往", roster, o => "智 " + o.Int, o => { picked = o; Confirm(); }, Cancel,
                 rate: o => Tier(o.Int, 70, 90, 40));                           // 产出 ∝ 智力（Politics.Yield*）
    }

    /// 自检：直接跳到确认屏，好截图看排版
    public void ConfirmForTest(Politics.Cmd c, MapData.Stat st0, Dictionary<int, Officer> all,
                               int who, bool run = false)
    {
        cmd = c; stat = st0; roster = Politics.Roster(all, st0.id);
        if (roster.Count == 0) return;
        picked = roster[Mathf.Clamp(who, 0, roster.Count - 1)];
        if (run) Say(Title, "本城农业水平提高了!\n农业 +16　金子 −22", true, Cg);   // 只画，不改数值
        else Confirm();
    }

    string Cg => Path.Combine("politicscg", "zc_" + (int)cmd);

    /// 原版屏 13："将消耗金子N"。金额是按武将智力算好的，玩家只能认或者不认。
    void Confirm()
    {
        gold = Politics.Cost(cmd, picked, stat.黄金, rng);
        var box = FaceSheet(Title, picked, new[] { picked.Name + "　智 " + picked.Int, "将消耗金子 " + gold });
        float w = box.sizeDelta.x;
        Btn(box, "是", w / 2 - 104, 150, 96, BTN_H, Execute);
        Btn(box, "否", w / 2 + 8, 150, 96, BTN_H, Cancel);
    }

    void Execute()
    {
        var r = Politics.Run(cmd, stat, picked, gold, rng);
        string extra = cmd switch {
            Politics.Cmd.Reclaim  => "农业 +" + r.Gain,
            Politics.Cmd.Commerce => "工业 +" + r.Gain,
            Politics.Cmd.Water    => "防灾 +" + r.Gain,
            _                     => "人口 +" + r.Gain,
        };
        if (r.RuleUp) extra += "　统治 +1";
        Say(Title, r.Msg + "\n" + extra + "　金子 −" + r.Gold, true, Cg);
    }
}
