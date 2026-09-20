// 单挑（原版画面 21，af=2）。阵型战里双方武将本人互相攻击时跳进来，打完再回去。
// 素材：头像用新做的那套，背景沿用阵型战那块地面。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class DuelView
{
    public RectTransform Root;
    public System.Action OnDone;

    readonly Officer me, foe;
    readonly float refW, refH;
    readonly System.Random rng = new System.Random();
    int myAtk, myDef, foeAtk, foeDef, cc;
    int round = 1;
    string log = "", over;

    Text hpMe, hpFoe, logT, roundT;
    Image barMe, barFoe;
    RectTransform cmdRoot;

    static readonly string[] MOVE = { "普通", "强攻", "必杀", "撤退" };

    public DuelView(RectTransform parent, Officer me, Officer foe, int scene,
                    float refW, float refH)
    {
        this.me = me; this.foe = foe; this.refW = refW; this.refH = refH;
        BattleRules.DuelStats(me, foe, out myAtk, out myDef, out foeAtk, out foeDef);
        cc = BattleRules.Counter(me, foe);          // b.ad()，和阵型战同一套克制

        Root = Node("Duel", parent); Fill(Root);
        var bg = Node("bg", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)bg.transform); bg.color = new Color(.05f, .04f, .04f, 1);

        var t = Label(Root, "单 挑", 22, new Color32(0xE8,0xC0,0x50,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 24, refW, 32);
        roundT = Label(Root, "", 13, new Color32(0xC0,0xB8,0xA0,255), TextAnchor.MiddleCenter);
        Place(roundT.rectTransform, 0, 58, refW, 20);

        var ground = Node("g", Root).gameObject.AddComponent<Image>();
        var g98 = Load(Path.Combine("battle", "orig", "lib98.png")); g98.filterMode = FilterMode.Point;
        ground.sprite = Sprite.Create(g98, new Rect(scene * 64, 0, 64, 64), new Vector2(.5f, .5f), 100);
        Place((RectTransform)ground.transform, 0, 86, refW, 250);

        Portrait(me.Id, 18, 110);
        Portrait(foe.Id, refW - 18 - 120, 200);

        var n1 = Label(Root, me.Name, 16, new Color32(0x9C,0xC8,0xFF,255));
        Place(n1.rectTransform, 18, 234, 140, 22);
        var n2 = Label(Root, foe.Name, 16, new Color32(0xFF,0x98,0x90,255), TextAnchor.MiddleRight);
        Place(n2.rectTransform, refW - 158, 324, 140, 22);

        hpMe  = Bar(out barMe,  18, 258, new Color(.35f, .65f, 1f));
        hpFoe = Bar(out barFoe, refW - 158, 348, new Color(1f, .40f, .35f));

        logT = Label(Root, "", 13, new Color32(0xE8,0xDC,0xB8,255), TextAnchor.UpperLeft);
        Place(logT.rectTransform, 18, 386, refW - 36, 120);

        cmdRoot = Node("cmd", Root);
        Place(cmdRoot, 8, 520, refW - 16, 76);
        var cb = Panel(cmdRoot, "bg", Plate()); Fill((RectTransform)cb.transform);
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            var b = Node("m" + i, cmdRoot);
            Place(b, 10 + i * 87, 22, 80, 34);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = Frame(); bi.type = Image.Type.Sliced;
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.onClick.AddListener(() => Round(k));
            var l = Label(b, MOVE[i], 15, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);
            Fill(l.rectTransform);
        }
        var hint = Label(cmdRoot, "强攻 6–8 成命中、必杀 1–3 成——**落空会自伤 14–22 体力**", 11,
                         new Color32(0xC0,0x98,0x68,255));
        Place(hint.rectTransform, 12, 2, refW - 40, 18);

        Refresh();
    }

    public void RoundForTest(int m) { Round(m); }

    void Portrait(int id, float x, float y)
    {
        var p = Path.Combine(ArtDir, "battle", "avatar", id + ".jpg");
        if (!File.Exists(p)) return;
        var t = new Texture2D(2, 2); t.LoadImage(File.ReadAllBytes(p)); t.Apply();
        var im = Node("av", Root).gameObject.AddComponent<Image>();
        im.sprite = Whole(t); Place((RectTransform)im.transform, x, y, 120, 120);
    }

    Text Bar(out Image fill, float x, float y, Color c)
    {
        var back = Node("bb", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)back.transform, x, y, 140, 12);
        back.color = new Color(0, 0, 0, .6f);
        fill = Node("bf", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)fill.transform, x, y, 140, 12);
        fill.color = c;
        var t = Label(Root, "", 12, Color.white);
        Place(t.rectTransform, x + 146, y - 3, 90, 18);
        return t;
    }

    /// 一个回合：我方按选的招出手，敌方按 b.c(我,敌) 的取舍出手
    void Round(int myMove)
    {
        if (over != null) { OnDone?.Invoke(); return; }
        log = "";
        if (myMove == 3) { over = "我方武将已撤退!"; Refresh(); return; }

        Strike(true, myMove);
        if (over == null)
        {
            int foeMove = BattleRules.DuelDecide(foe, me, rng);
            if (foeMove == 4) { over = "敌将逃窜!"; }
            else Strike(false, foeMove == 2 ? 2 : foeMove);
        }
        round++;
        Refresh();
    }

    void Strike(bool mine, int move)
    {
        var o    = mine ? me : foe;
        int num  = mine ? myAtk : foeAtk;
        int den  = mine ? foeDef : myDef;
        // 「攻方」是发起这场战斗的一方，也就是我方；克制对攻守是反向的
        bool isAttacker = mine;
        int dmg;
        switch (move)
        {
            case 0:  dmg = BattleRules.DuelPlain(o, num, den); break;
            case 1:  dmg = BattleRules.DuelHeavy(o, num, den, cc, isAttacker, rng); break;
            default: dmg = BattleRules.DuelCrit (o, num, den, cc, isAttacker, rng); break;
        }
        string who = o.Name, mv = MOVE[move];

        if (dmg < 0)                       // 必杀落空：L += 负数 = 自伤
        {
            o.Hp = Mathf.Max(0, o.Hp + dmg);
            log += who + " " + mv + "落空，反受 " + (-dmg) + " 点\n";
            if (o.Hp == 0) over = mine ? "我方武将阵亡!" : "击毙敌将!";
            return;
        }
        if (dmg == 0) { log += who + " " + mv + "被闪开\n"; return; }

        var tgt = mine ? foe : me;
        tgt.Hp = Mathf.Max(0, tgt.Hp - dmg);
        log += who + " " + mv + "命中 " + dmg + " 点\n";
        if (tgt.Hp == 0) over = mine ? "击毙敌将!" : "我方武将阵亡!";
    }

    void Refresh()
    {
        barMe.rectTransform.sizeDelta  = new Vector2(140f * me.Hp  / Mathf.Max(1, me.HpMax), 12);
        barFoe.rectTransform.sizeDelta = new Vector2(140f * foe.Hp / Mathf.Max(1, foe.HpMax), 12);
        hpMe.text  = "体力 " + me.Hp;
        hpFoe.text = "体力 " + foe.Hp;
        roundT.text = over ?? ("第 " + round + " 合   兵种克制 "
                     + (cc > 0 ? "占优" : cc < 0 ? "吃亏" : "持平"));
        logT.text = over != null ? log + "\n" + over + "\n（点任意一招收场）" : log;
    }
}
