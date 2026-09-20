// 单挑（原版画面 22）。阵型战里双方武将本人互相攻击时跳进来，打完再回去。
// 布局照原版：上方双方姓名与体力条、中间两员骑将面对面、下方六招菜单、底部战报。
// 素材全是新做的高清像素：背景按地形分四种，骑将 15 帧（敌方水平镜像，和原版一样）。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class DuelView
{
    public RectTransform Root;
    public System.Action OnDone;
    /// 劝降成功要把人收过来，交给外面处理
    public System.Action<Officer> OnRecruit;

    readonly Officer me, foe;
    readonly float refW, refH;
    readonly System.Random rng = new System.Random();
    int myAtk, myDef, foeAtk, foeDef, cc;
    int round = 1;
    string report = "", over;

    Image figMe, figFoe, barMe, barFoe;
    Text hpMe, hpFoe, roundT, reportT;
    RectTransform menu;
    static readonly Dictionary<int, Sprite> frames = new Dictionary<int, Sprite>();

    // 原版 lib304 的帧号，这套新素材沿用同一套编号
    const int F_IDLE = 5, F_THRUST = 0, F_HIT = 4, F_CHARGE = 6, F_BURST = 7,
              F_RAISE = 8, F_RECOIL = 9, F_FALL = 13;

    const float GROUND = 470f;                 // 地面线，两员的脚都落在这儿
    const float FIG = 168f;

    public DuelView(RectTransform parent, Officer me, Officer foe, int scene,
                    float refW, float refH)
    {
        this.me = me; this.foe = foe; this.refW = refW; this.refH = refH;
        BattleRules.DuelStats(me, foe, out myAtk, out myDef, out foeAtk, out foeDef);
        cc = BattleRules.Counter(me, foe);

        Root = Node("Duel", parent); Fill(Root);
        var black = Node("bk", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)black.transform); black.color = new Color(.04f, .04f, .05f, 1);

        // 背景按地形选，和阵型战同一个 c.e(地形码)
        string[] key = { "plain", "water", "forest", "siege" };
        var bg = Node("bg", Root).gameObject.AddComponent<Image>();
        bg.sprite = Whole(Tex("duel_bg_" + key[Mathf.Clamp(scene, 0, 3)] + ".jpg"));  // 背景不透明，存的 JPEG
        Place((RectTransform)bg.transform, 0, 74, refW, 400);
        bg.preserveAspect = false;

        Header();

        figMe  = Fighter(28, false);
        figFoe = Fighter(refW - 28 - FIG, true);
        SetFrame(figMe, F_IDLE, false); SetFrame(figFoe, F_IDLE, true);

        Menu();

        reportT = Label(Root, "", 14, new Color32(0xE8,0xDC,0xB8,255), TextAnchor.UpperLeft);
        Place(reportT.rectTransform, 20, 660, refW - 40, 130);

        Refresh();
    }

    static Texture2D Tex(string f)
    {
        var t = Load(Path.Combine("battle", "duel", f));
        t.filterMode = FilterMode.Point; return t;
    }
    static Sprite Frame(int i)
    {
        if (!frames.TryGetValue(i, out var s)) { s = Whole(Tex("duel_f" + i + ".png")); frames[i] = s; }
        return s;
    }

    void Header()
    {
        var plate = Panel(Root, "hd", Plate());
        Place((RectTransform)plate.transform, 8, 8, refW - 16, 68);

        var n1 = Label(plate.transform, me.Name, 16, new Color32(0x9C,0xC8,0xFF,255));
        Place(n1.rectTransform, 14, 6, 130, 22);
        var n2 = Label(plate.transform, foe.Name, 16, new Color32(0xFF,0x98,0x90,255), TextAnchor.MiddleRight);
        Place(n2.rectTransform, refW - 160, 6, 130, 22);

        barMe  = Bar(out hpMe, 14, 34, false);
        barFoe = Bar(out hpFoe, refW - 16 - 14 - 140, 34, true);

        roundT = Label(Root, "", 12, new Color32(0xC8,0xC0,0xA8,255), TextAnchor.MiddleCenter);
        Place(roundT.rectTransform, 0, 52, refW, 18);
    }

    /// 原版那两条是 100 格竖线拼的，这里用一个条代替，右侧那条从右往左长
    Image Bar(out Text label, float x, float y, bool rightToLeft)
    {
        var back = Node("bb", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)back.transform, x, y, 140, 12); back.color = new Color(0, 0, 0, .65f);
        var fill = Node("bf", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)fill.transform, x, y, 140, 12);
        fill.color = rightToLeft ? new Color(1f, .42f, .36f) : new Color(.38f, .68f, 1f);
        if (rightToLeft)
        {
            var rt = fill.rectTransform;
            rt.anchorMin = rt.anchorMax = new Vector2(0, 1); rt.pivot = new Vector2(1, 1);
            rt.anchoredPosition = new Vector2(x + 140, -y);
        }
        // 两个数字原来都摆在条的内侧，375 宽的屏上会叠在一起。各自挪到外侧。
        label = Label(Root, "", 13, Color.white, rightToLeft ? TextAnchor.MiddleLeft : TextAnchor.MiddleRight);
        Place(label.rectTransform, rightToLeft ? x + 146 : x - 50, y - 4, 46, 20);
        return fill;
    }

    Image Fighter(float x, bool mirror)
    {
        var im = Node("f", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)im.transform, x, GROUND - FIG, FIG, FIG);
        im.raycastTarget = false;
        if (mirror)                       // 敌方是水平镜像，原版就是同一张图翻过来
        {
            im.rectTransform.localScale = new Vector3(-1, 1, 1);
            im.rectTransform.anchoredPosition += new Vector2(FIG, 0);
        }
        return im;
    }
    void SetFrame(Image im, int f, bool mirror) => im.sprite = Frame(f);

    void Menu()
    {
        menu = Node("menu", Root);
        Place(menu, 12, 500, refW - 24, 146);
        var bg = Node("p", menu).gameObject.AddComponent<Image>();
        bg.sprite = Whole(Tex("duel_panel.png")); Fill((RectTransform)bg.transform);
        bg.type = Image.Type.Sliced;

        for (int i = 0; i < 6; i++)
        {
            int k = i;
            var b = Node("m" + i, menu);
            Place(b, 18 + (i % 3) * 106, 18 + (i / 3) * 58, 96, 46);
            var bi = b.gameObject.AddComponent<Image>();
            bi.color = new Color(0, 0, 0, .18f);
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.onClick.AddListener(() => Pick(k));
            var l = Label(b, BattleRules.DuelMove[i], 17, new Color32(0xF4,0xE6,0xC0,255),
                          TextAnchor.MiddleCenter);
            Fill(l.rectTransform);
            var ol = l.gameObject.AddComponent<Outline>();
            ol.effectColor = new Color(.15f, .09f, .04f, .95f);
            ol.effectDistance = new Vector2(1.4f, 1.4f);
        }
    }

    // ── 一个回合 ───────────────────────────────────────────────────────
    void Pick(int move)
    {
        if (over != null) { OnDone?.Invoke(); return; }
        report = "";

        switch (move)
        {
            case 3:                                        // 劝降
                if (BattleRules.Persuade(me, foe, rng))
                {
                    over = "成功招降!"; SetFrame(figFoe, F_RAISE, true);
                    OnRecruit?.Invoke(foe); Refresh(); return;
                }
                report += "招降失败!\n";
                break;
            case 4:                                        // 逃跑：用的是单挑胜负那个公式
                if (BattleRules.Flee(me, foe, rng)) { over = "自军成功撤离!"; Refresh(); return; }
                report += "撤退失败...\n";
                break;
            case 5: over = "我方投降!"; Refresh(); return;   // 投降没有判定
            default: Strike(true, move); break;
        }

        if (over == null)
        {
            int m = BattleRules.DuelDecide(foe, me, rng);
            if (m == 4) over = "敌将逃窜!";
            else Strike(false, Mathf.Clamp(m, 0, 2));
        }
        round++;
        Refresh();
    }

    void Strike(bool mine, int move)
    {
        var o   = mine ? me : foe;
        int num = mine ? myAtk : foeAtk;
        int den = mine ? foeDef : myDef;
        int dmg;
        switch (move)
        {
            case 0:  dmg = BattleRules.DuelPlain(o, num, den); break;
            case 1:  dmg = BattleRules.DuelHeavy(o, num, den, cc, mine, rng); break;
            default: dmg = BattleRules.DuelCrit (o, num, den, cc, mine, rng); break;
        }
        var self  = mine ? figMe : figFoe;
        var other = mine ? figFoe : figMe;
        SetFrame(self,  move == 2 ? F_BURST : F_THRUST, !mine);
        SetFrame(other, F_IDLE, mine);

        string name = o.Name, mv = BattleRules.DuelMove[move];
        if (dmg < 0)                       // 「全力」落空：L += 负数 = 自伤
        {
            o.Hp = Mathf.Max(0, o.Hp + dmg);
            report += name + " " + mv + "落空，反受 " + (-dmg) + " 点\n";
            SetFrame(self, F_RECOIL, !mine);
            if (o.Hp == 0) { over = mine ? "我方武将阵亡!" : "击毙敌将!"; SetFrame(self, F_FALL, !mine); }
            return;
        }
        if (dmg == 0) { report += name + " " + mv + "被闪开\n"; return; }

        var tgt = mine ? foe : me;
        tgt.Hp = Mathf.Max(0, tgt.Hp - dmg);
        report += name + " " + mv + "命中 " + dmg + " 点\n";
        SetFrame(self, F_HIT, !mine);
        if (tgt.Hp == 0)
        {
            over = mine ? "击毙敌将!" : "我方武将阵亡!";
            SetFrame(other, F_FALL, mine);
        }
    }

    void Refresh()
    {
        barMe.rectTransform.sizeDelta  = new Vector2(140f * me.Hp  / Mathf.Max(1, me.HpMax), 12);
        barFoe.rectTransform.sizeDelta = new Vector2(140f * foe.Hp / Mathf.Max(1, foe.HpMax), 12);
        hpMe.text = me.Hp.ToString(); hpFoe.text = foe.Hp.ToString();
        roundT.text = over ?? ("第 " + round + " 合 · 兵种"
                    + (cc > 0 ? "占优" : cc < 0 ? "吃亏" : "持平"));
        reportT.text = over != null ? report + "\n" + over + "\n（点任意一招收场）" : report;
        menu.gameObject.SetActive(true);
    }

    public void PickForTest(int m) { Pick(m); }
}
