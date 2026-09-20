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
    // 一人一套立绘：48 名将各有专属，其余按 ID 散列进 9 个男池 / 5 个女池。
    // 对照表是 extract/data/duel-skin.json 生成的，扁平的「武将ID: 套名」。
    static Dictionary<int, string> skinMap;
    static readonly Dictionary<string, Sprite> frames = new Dictionary<string, Sprite>();
    readonly string mySkin, foeSkin;

    static string SkinOf(int officerId)
    {
        if (skinMap == null)
        {
            skinMap = new Dictionary<int, string>();
            var p = Path.Combine(ArtDir, "battle", "duel-skin.json");
            if (File.Exists(p))
                foreach (var seg in File.ReadAllText(p).Trim(' ', '{', '}').Split(','))
                {
                    var kv = seg.Split(':');
                    if (kv.Length == 2 && int.TryParse(kv[0].Trim(' ', '"'), out var id))
                        skinMap[id] = kv[1].Trim(' ', '"');
                }
        }
        return skinMap.TryGetValue(officerId, out var k) ? k : "male_a";
    }
    float baseYMe, baseYFoe;
    // 帧号沿用原版 lib304 的编号
    const int F_IDLE = 5, F_THRUST = 0, F_HIT = 4, F_CHARGE = 6, F_BURST = 7,
              F_RAISE = 8, F_RECOIL = 9, F_FALL = 13;
    // 打击段不是一张定格。原版 c.a(Graphics,boolean) 里 n = ao<6 ? ao+5 : ao-6，
    // 也就是**先放 5,6,7,8,9,10（蓄力发光），再放 0,1,2,3,4（突刺）**。
    // 我们手上只有这几帧，按同样的次序排：
    static readonly int[][] HIT_SEQ = {
        new[]{ F_THRUST, F_HIT },                                  // 牵制
        new[]{ F_THRUST, F_HIT },                                  // 攻击
        new[]{ F_CHARGE, F_BURST, F_BURST, F_THRUST, F_HIT },      // 全力：先发光再出手
    };
    // 奔驰的两帧：马腿姿势不同，交替播才有步频。
    // 原版冲锋段就是 `300 + ao%2 * 540`，在第 5 帧和第 14 帧之间来回切。
    const int F_RUN_A = 11, F_RUN_B = 14;
    const float GROUND = 470f;                 // 地面线，两员的脚都落在这儿
    // 站位全部照原版按比例换算（原版屏宽 176，这里 375）：
    //   立绘 60 → 128；我方 5 → 81 折合 11 → 173；敌方 115 → 47 折合 245 → 100
    // 之前我把冲锋落点写成 refW/2 - FIG + 14 = 33，离本阵 28 只差 5 像素，等于没冲；
    // 而且 FIG 取 168 时两边站位本身就重叠了。
    const float FIG    = 128f;


    public DuelView(RectTransform parent, Officer me, Officer foe, int scene,
                    float refW, float refH)
    {
        this.me = me; this.foe = foe; this.refW = refW; this.refH = refH;
        mySkin = SkinOf(me.Id); foeSkin = SkinOf(foe.Id);
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

        figMe  = Fighter(refW *   5f / 176f, false);
        figFoe = Fighter(refW * 115f / 176f, true);
        baseYMe  = figMe.rectTransform.anchoredPosition.y;
        baseYFoe = figFoe.rectTransform.anchoredPosition.y;
        homeMe  = refW *   5f / 176f;
        homeFoe = refW * 115f / 176f;
        // 原版立绘 60×60 里留白多，照坐标换算过来看着不挤；我的图裁到贴边（占画布 92%），
        // 同样的落点就真的从对方身上穿过去了。改成按实际图宽停在对方身前，只叠一点点。
        const float BITE = 24f;                     // 允许交叠多少，兵器够得着即可
        atkMe  = homeFoe - FIG + BITE;
        atkFoe = homeMe  + FIG - BITE;
        SetFrame(figMe, F_IDLE, false); SetFrame(figFoe, F_IDLE, true);

        Menu();

        reportT = Label(Root, "", 14, new Color32(0xE8,0xDC,0xB8,255), TextAnchor.UpperLeft);
        Place(reportT.rectTransform, 20, 660, refW - 40, 130);

        Refresh();
    }

    // Tex 只管 battle/duel/ 下的（背景、面板、光标）；
    // 立绘在 battle/duelskin/<套>/，走 TexB，别让两条路径混在一起——
    // 混了会拼出 battle/duel/duelskin/…，而存在性检查用的又是另一条，回退也兜不住。
    static Texture2D Tex(string f) => TexB(Path.Combine("duel", f));
    static Texture2D TexB(string rel)
    {
        var t = Load(Path.Combine("battle", rel));
        t.filterMode = FilterMode.Point; return t;
    }
    static bool HasB(string rel) => File.Exists(Path.Combine(ArtDir, "battle", rel));
    static Sprite Frame(string skin, int i)
    {
        var key = skin + "/" + i;
        if (frames.TryGetValue(key, out var s)) return s;
        var rel = Path.Combine("duelskin", skin, "duel_f" + i + ".png");
        // 缺帧先在**同一套内部**找相近的替身，实在没有才退到通用套——
        // 退到别人身上会当场换脸，比姿势不对更糟。
        if (!HasB(rel))
        {
            int[] alt = i == F_THRUST ? new[] { F_HIT, F_IDLE }
                      : i == F_RAISE  ? new[] { F_RECOIL, F_IDLE }
                      : i == F_RUN_A || i == F_RUN_B ? new[] { F_RUN_B, F_RUN_A, F_IDLE }
                      : new[] { F_IDLE };
            foreach (var j in alt)
            {
                var t2 = Path.Combine("duelskin", skin, "duel_f" + j + ".png");
                if (HasB(t2)) { rel = t2; break; }
            }
        }
        if (!HasB(rel)) rel = Path.Combine("duelskin", "male_a", "duel_f" + i + ".png");
        if (!HasB(rel)) rel = Path.Combine("duel", "duel_f" + i + ".png");   // 最后退回最早那一套
        s = Whole(TexB(rel)); frames[key] = s; return s;
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
        // 两边长得一样时最难分敌我，脚下垫一个阵营色的椭圆当锚
        var ring = Node("ring", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)ring.transform, x + 18, GROUND - 26, FIG - 36, 30);
        ring.sprite = Ring(256, 96, .16f);
        ring.color = mirror ? new Color(1f, .40f, .34f, .55f) : new Color(.40f, .68f, 1f, .55f);
        ring.raycastTarget = false;

        var im = Node("f", Root).gameObject.AddComponent<Image>();
        Place((RectTransform)im.transform, x, GROUND - FIG, FIG, FIG);
        im.raycastTarget = false;
        // 朝向统一交给 Put() 管：我方默认朝右、敌方默认朝左（翻转）
        Put(im, x, im.rectTransform.anchoredPosition.y, 0, !mirror);
        return im;
    }
    /// foeSide 决定用哪一套立绘，和朝向无关——朝向由 Put() 管。
    /// 两者混用过一次：归顺时让敌将朝右（传 false），结果连立绘也换成了我方的。
    void SetFrame(Image im, int f, bool foeSide) => im.sprite = Frame(foeSide ? foeSkin : mySkin, f);

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

    // ── 一个回合：一来一回的冲阵 ────────────────────────────────────────
    // 原版 c.d/e(Graphics,boolean)：出手方从自己那侧冲到对方跟前，打完退回，
    // 然后换对方冲过来。一来一去算一合，约 6 秒。
    // 我原来做成双方站着不动出招，少了「冲」和「退」这两段。

    class Blow                                     // 先算好，演到那一步再落地
    {
        public bool Mine; public int Move; public int Dmg; public bool SelfHurt;
        public string Line; public string Over; public bool Recruit;
        public bool Landed;                    // 结算过没有。别拿时间判断——
                                               // 段间隙把 stepT 设成负数，时间判断会连触发十几次
    }

    readonly List<Blow> queue = new List<Blow>();
    int step = -1; float stepT;
    // 原版单挑循环是 sleep(100)，10 帧/秒；一次出手分三段，帧数写死在绘制函数里：
    //   冲过去 c() 19 帧 → 打击 d/e/a() 3/5/11 帧（牵制/攻击/全力）→ 退回来 b() 17 帧
    // 单边 3.9 / 4.1 / 4.7 秒，双方一来一回 7.8 / 8.2 / 9.4 秒。
    const float FRAME = 0.1f;
    const float T_CHARGE = 19 * FRAME, T_BACK = 17 * FRAME, T_GAP = 0.15f;
    static readonly float[] T_HIT = { 3 * FRAME, 5 * FRAME, 11 * FRAME };   // 牵制/攻击/全力
    float homeMe, homeFoe, atkMe, atkFoe;

    // 原版 case 5 投降会置 au=2 走一段离场；我原来只弹一行字，看不出发生了什么。
    bool exiting; float exitT; bool exitMine;
    bool joining; float joinT;                    // 招降成功：降将骑到我方这边来
    const float T_EXIT = 1.6f, T_JOIN = 1.8f;

    void StartExit(bool mine) { exiting = true; exitT = 0; exitMine = mine; menu.gameObject.SetActive(false); }
    void StartJoin() { joining = true; joinT = 0; menu.gameObject.SetActive(false); }

    void Pick(int move)
    {
        if (over != null) { OnDone?.Invoke(); return; }
        if (step >= 0) return;                     // 正在演，别插队
        report = "";

        switch (move)
        {
            case 3:                                // 劝降
                if (BattleRules.Persuade(me, foe, rng))
                {
                    // 原版这里用帧 11（立枪）配帧 12（平端），而这两个帧号我做新素材时
                    // 挪用成了奔驰的两帧，手上没有那两个姿势。改成演「归顺」这件事本身：
                    // 降将掉头骑到我方一侧，停下后和我方同向站着。
                    over = "成功招降!"; StartJoin(); OnRecruit?.Invoke(foe); Refresh(); return;
                }
                report += "招降失败!\n"; break;
            case 4:                                // 逃跑：走的是单挑胜负那个公式
                if (BattleRules.Flee(me, foe, rng))
                { over = "自军成功撤离!"; StartExit(true); Refresh(); return; }
                report += "撤退失败...\n"; break;
            case 5: over = "我方投降!"; StartExit(true); Refresh(); return;
            default: queue.Add(Plan(true, move)); break;
        }

        if (over == null)
        {
            int m = BattleRules.DuelDecide(foe, me, rng);
            if (m == 4) { over = "敌将逃窜!"; StartExit(false); }
            else queue.Add(Plan(false, Mathf.Clamp(m, 0, 2)));
        }
        if (queue.Count > 0) { step = 0; stepT = 0; menu.gameObject.SetActive(false); }
        Refresh();
    }

    /// 只算不落地。伤害在冲到跟前那一刻才结算到血条上。
    Blow Plan(bool mine, int move)
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
        var b = new Blow { Mine = mine, Move = move, Dmg = dmg, SelfHurt = dmg < 0 };
        string nm = o.Name, mv = BattleRules.DuelMove[move];
        b.Line = dmg < 0 ? nm + " " + mv + "落空，反受 " + (-dmg) + " 点"
               : dmg == 0 ? nm + " " + mv + "被闪开"
               : nm + " " + mv + "命中 " + dmg + " 点";
        return b;
    }

    void Land(Blow b)
    {
        report += b.Line + "\n";
        var o = b.Mine ? me : foe;
        if (b.SelfHurt)                            // 「全力」落空：自伤
        {
            o.Hp = Mathf.Max(0, o.Hp + b.Dmg);
            if (o.Hp == 0) over = b.Mine ? "我方武将阵亡!" : "击毙敌将!";
            return;
        }
        if (b.Dmg == 0) return;
        var tgt = b.Mine ? foe : me;
        tgt.Hp = Mathf.Max(0, tgt.Hp - b.Dmg);
        if (tgt.Hp == 0) over = b.Mine ? "击毙敌将!" : "我方武将阵亡!";
    }

    /// 每帧推进：冲锋 → 命中 → 退回，双方各一遍
    public void Tick()
    {
        float t = Time.time;
        if (joining)                               // 归顺：骑过来，停在我方身侧
        {
            joinT += Time.deltaTime;
            float e = Mathf.Clamp01(joinT / T_JOIN);
            float dest = homeMe + FIG * 0.62f;     // 停在我方右后方，同向并肩
            SetFrame(figFoe, e < 1f
                ? ((int)(joinT / 0.2f) % 2 == 0 ? F_RUN_A : F_RUN_B) : F_IDLE, true);
            Put(figFoe, Mathf.Lerp(homeFoe, dest, Ease(e)), baseYFoe,
                e < 1f ? Mathf.Sin(t * 22f) * 2.5f : 0, true);   // 朝右＝和我方同向
            Put(figMe, homeMe, baseYMe, 0, true);
            if (e >= 1f) joining = false;
            return;
        }
        if (exiting)                               // 掉头骑出画面
        {
            exitT += Time.deltaTime;
            float e = Mathf.Clamp01(exitT / T_EXIT);
            var go2 = exitMine ? figMe : figFoe;
            var stay = exitMine ? figFoe : figMe;
            float from = exitMine ? homeMe : homeFoe;
            float to   = exitMine ? -FIG - 20f : refW + 20f;      // 从自己那侧退出画面
            SetFrame(go2, (int)(exitT / 0.2f) % 2 == 0 ? F_RUN_A : F_RUN_B, !exitMine);
            // 往哪边跑就朝哪边：我方向左退 → 翻转；敌方向右退 → 翻转
            Put(go2, Mathf.Lerp(from, to, Ease(e)), exitMine ? baseYMe : baseYFoe,
                Mathf.Sin(t * 22f) * 2.5f, !exitMine);
            Put(stay, exitMine ? homeFoe : homeMe, exitMine ? baseYFoe : baseYMe, 0, !exitMine);
            if (e >= 1f) { exiting = false; go2.gameObject.SetActive(false); }
            return;
        }
        if (step < 0)                              // 没在演：两边轻微起伏
        {
            if (over != null) return;              // 已经分出结果，保持倒地那一帧
            Put(figMe,  homeMe,  baseYMe,  Mathf.Sin(t * 1.7f) * 2.2f, true);
            Put(figFoe, homeFoe, baseYFoe, Mathf.Sin(t * 1.7f + 1.1f) * 2.2f, false);
            return;
        }

        var b = queue[step / 3];
        int ph = step % 3;                          // 0 冲锋 1 命中 2 退回
        float dur = ph == 0 ? T_CHARGE : ph == 1 ? T_HIT[Mathf.Clamp(b.Move, 0, 2)] : T_BACK;
        stepT += Time.deltaTime;
        float k = Mathf.Clamp01(stepT / dur);

        var att  = b.Mine ? figMe : figFoe;
        var def  = b.Mine ? figFoe : figMe;
        float h  = b.Mine ? homeMe : homeFoe;
        float a  = b.Mine ? atkMe  : atkFoe;
        float by = b.Mine ? baseYMe : baseYFoe;
        float dy = b.Mine ? baseYFoe : baseYMe;
        float dh = b.Mine ? homeFoe : homeMe;

        if (ph == 0)                                 // 冲过去
        {
            // 两帧交替，一步 0.2 秒 —— 原版是每 2 个 100ms 帧换一次
            SetFrame(att, (int)(stepT / 0.2f) % 2 == 0 ? F_RUN_A : F_RUN_B, !b.Mine);
            SetFrame(def, F_IDLE, b.Mine);
            Put(att, Mathf.Lerp(h, a, Ease(k)), by, Mathf.Sin(t * 22f) * 2.5f, b.Mine);
            Put(def, dh, dy, 0, !b.Mine);
        }
        else if (ph == 1)                            // 交手那一下
        {
            var seq = HIT_SEQ[Mathf.Clamp(b.Move, 0, 2)];
            int si = Mathf.Clamp((int)(k * seq.Length), 0, seq.Length - 1);
            SetFrame(att, seq[si], !b.Mine);
            // 伤害在这一段的**最后一帧**落地，也就是真正递出兵器的那一下
            if (!b.Landed && si == seq.Length - 1)
            {
                b.Landed = true;
                Land(b);
                SetFrame(def, b.Dmg > 0 ? F_RECOIL : F_RAISE, b.Mine);
                if (over != null && b.Dmg > 0 && !b.SelfHurt) SetFrame(def, F_FALL, b.Mine);
                if (over != null && b.SelfHurt) SetFrame(att, F_FALL, !b.Mine);
                Refresh();
            }
            Put(att, a, by, 0, b.Mine);
            Put(def, dh, dy, Mathf.Sin(t * 30f) * 3f, !b.Mine);                   // 挨打的抖
        }
        else                                          // 退回去：掉头，图左右翻转
        {
            SetFrame(att, (int)(stepT / 0.2f) % 2 == 0 ? F_RUN_B : F_RUN_A, !b.Mine);
            Put(att, Mathf.Lerp(a, h, Ease(k)), by, Mathf.Sin(t * 22f) * 2.5f, !b.Mine);
            Put(def, dh, dy, 0, !b.Mine);
        }

        if (k >= 1f)
        {
            stepT = -T_GAP;                           // 步与步之间留个间隙
            step++;
            if (over != null || step >= queue.Count * 3)
            {
                queue.Clear(); step = -1; stepT = 0;
                if (over == null)
                {
                    round++;
                    // 只有还打得下去才归位。分出结果就别动了——
                    // 否则刚设好的落马帧会被这里的「摆回本阵 + 待机」抹掉。
                    Put(figMe, homeMe, baseYMe, 0, true); Put(figFoe, homeFoe, baseYFoe, 0, false);
                    SetFrame(figMe, F_IDLE, false); SetFrame(figFoe, F_IDLE, true);
                }
                menu.gameObject.SetActive(true);
                Refresh();
            }
        }
    }

    static float Ease(float k) => k * k * (3f - 2f * k);
    /// 摆位 + 朝向。绕左上角做水平翻转会把图整体推左一个身位，要补回来。
    void Put(Image im, float x, float baseY, float dy, bool faceRight)
    {
        im.rectTransform.localScale = new Vector3(faceRight ? 1f : -1f, 1f, 1f);
        im.rectTransform.anchoredPosition = new Vector2(faceRight ? x : x + FIG, baseY + dy);
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
