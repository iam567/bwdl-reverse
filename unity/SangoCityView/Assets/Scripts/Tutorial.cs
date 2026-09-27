// 新手教程（v1.02）：第一次进某个画面，就讲这个画面——压暗全屏、镂空高亮要讲的地方，旁边一段说明，点一下看下一条。
// 分段：city 城内 / map 大地图 / list 选将列表 / war 出征 / battle 战棋 / pick 阵型选择 / clash 阵型战 / duel 单挑。
// 看过的段记在 PlayerPrefs，不再弹；设置里可开关（重新开启 = 从头再看一遍）。第一次打开游戏在主界面问要不要。
// 只加提示，不碰任何游戏逻辑；讲的规则都对照过代码（出处写在各段文字旁）。
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public static class Tutorial
{
    const string KEY_ON = "tut_on", KEY_ASKED = "tut_asked", KEY_SEEN = "tut_seen_";
    public static readonly string[] Sections = { "city", "map", "list", "war", "battle", "pick", "clash", "duel" };

    static readonly string[] av = System.Environment.GetCommandLineArgs();
    static bool Has(string a) => System.Array.IndexOf(av, a) >= 0;
    /// 截图 / 自检跑的时候别弹（-tut <段> 截教程那一屏时例外）
    static bool Suppressed => !Has("-tut") && (Application.isBatchMode || Has("-shot") || Has("-sim") || Has("-notut"));
    static string Forced => Has("-tut") ? av[System.Array.IndexOf(av, "-tut") + 1] : null;

    public static bool On
    {
        get { try { return PlayerPrefs.GetInt(KEY_ON, 1) == 1; } catch { return true; } }
        set { PlayerPrefs.SetInt(KEY_ON, value ? 1 : 0); if (value) ResetSeen(); PlayerPrefs.Save(); }
    }
    /// 第一次打开游戏问过没有
    public static bool Asked
    {
        get { if (Has("-tutask")) return false; if (Suppressed) return true; try { return PlayerPrefs.GetInt(KEY_ASKED, 0) == 1; } catch { return true; } }
        set { PlayerPrefs.SetInt(KEY_ASKED, value ? 1 : 0); PlayerPrefs.Save(); }
    }
    static void ResetSeen() { foreach (var s in Sections) PlayerPrefs.DeleteKey(KEY_SEEN + s); }
    static bool Seen(string sec) { try { return PlayerPrefs.GetInt(KEY_SEEN + sec, 0) == 1; } catch { return false; } }

    public struct Step
    {
        public string Text; public System.Func<RectTransform> Target;
        public Step(string text, System.Func<RectTransform> target = null) { Text = text; Target = target; }
    }

    static RectTransform host;
    static TutorialOverlay cur;
    public static void Init(RectTransform root) { host = root; }
    /// 正在讲：实时的阵型战 / 单挑这时候要停下来
    public static bool Active => cur != null;

    /// 该讲就讲（开着、没看过、此刻没在讲别的）。返回是否弹了。
    public static bool Show(string sec, params Step[] steps)
    {
        string f = Forced;
        if (host == null || cur != null || steps.Length == 0) return false;
        if (f != null) { if (f != sec) return false; }
        else if (Suppressed || !On || Seen(sec)) return false;
        PlayerPrefs.SetInt(KEY_SEEN + sec, 1); PlayerPrefs.Save();
        var go = Node("tutorial", host).gameObject;
        Fill((RectTransform)go.transform);
        cur = go.AddComponent<TutorialOverlay>();
        cur.Begin(steps, () => cur = null);
        if (Has("-tutstep")) cur.SkipTo(int.Parse(av[System.Array.IndexOf(av, "-tutstep") + 1]) - 1);   // 截图：直接翻到第 N 条
        return true;
    }

    // ── 文案（简中原文，别的语言走 Loc 表）──────────────────────────────
    public const string AskTitle = "新手教程";
    public const string AskText = "第一次玩？\n要不要开启新手教程？\n（之后可在「游戏设置」里开关）";

    static Step S(string t, System.Func<RectTransform> f = null) => new Step(t, f);

    /// 城内（每回合开始进主公所在的城：GameTurns.PlayerTurn）
    public static void City(System.Func<RectTransform> name, System.Func<RectTransform> tok, System.Func<RectTransform> left,
                            System.Func<RectTransform> right, System.Func<RectTransform> pic, System.Func<RectTransform> map)
        => Show("city",
            S("欢迎，主公！这里是你的城池。\n左上是城名和城的规模，下一行是君主和当前年月。", name),
            S("令牌：本月还能下几道指令。\n每月的令数等于你拥有的城数（最少 3、最多 8）。令用完了，就轮到其他势力行动。", tok),   // Politics.OrdersPerTurn
            S("军政：君主、太守、武将人数、兵力、金子、粮食。\n金子和粮食是几乎所有指令的本钱。", left),
            S("民生：人口、统治、农业、工业、防灾。\n每年 4 月收金、10 月收粮，人口越多、农业工业越高，收得越多。\n统治低于 60 可能发生骚乱；防灾到 90 以上才不怕天灾。", right),   // MonthEnd 4 / 6 / 8
            S("城里的每座建筑就是一类指令：点建筑，弹出指令，再点一次执行。城图可以上下拖动。", pic),
            S("太守府·内政：开垦（农业）、劝商（工业）、治水（防灾）、巡查（人口、统治）\n功曹府·人事：搜索、登用、奖赏、任命\n校场·军事：征兵、移动、输送、战争\n驿馆·外交：同盟、离间、招揽、情报\n常平仓·金粮、武库·武器、郡学·学院、药庐·医馆", pic),
            S("右上的山河舆图：回到大地图，看天下形势、去别的城。", map),
            S("入门建议：先开垦、劝商攒下金粮，搜索、登用招揽人才，征兵练兵，再攻打相邻的弱城。\n占领全部城池就统一了天下。"));

    /// 大地图（从城里出来：Game cityView.OnBack）
    public static void Map(System.Func<RectTransform> year, System.Func<RectTransform> sys)
        => Show("map",
            S("这是大地图。左上是当前年月，下一行是你的君主和城数。", year),
            S("拖动地图查看，双指缩放。\n点城池选中，再点一次进城——只能进自己的城。"),
            S("齿轮是系统菜单：存档、读档、结束本月（剩下的令作废，直接轮到别家）、回主界面。\n每月开始会自动存一档。", sys),
            S("指令里要选城时（移动、输送、战争、外交），也是在这张地图上点金色高亮的城。"));

    /// 选将列表（带推荐评级的那种：SheetView.OfficerTable）
    public static void List(System.Func<RectTransform> table)
        => Show("list",
            S("选武将：每一行是一员武将，右边五列是武力、智力、体力、德行、忠诚。点一行选中。", table),
            S("名字的颜色是这条指令的推荐程度：绿色优选、黄绿推荐、墨色一般、红色不推荐。\n办内政、搜索看智力，登用看德行，打仗看武力和体力。", table));

    /// 出征（校场·战争：MilitaryView）
    public static void War()
        => Show("war",
            S("战争：先在大地图上选一座相邻的敌城，再选出征的武将，最后决定带多少金粮。"),
            S("出征队伍的第一位是主将。主将被击败（战死或被擒），全军撤退。\n君主亲自出征要小心：君主战死，就得另立新君继位。"),
            S("兵力：每员武将最多带 1000 兵。\n兵从「校场·征兵」来：每 100 兵花 20 金征成预备兵，再分给各武将。"),   // MilitaryView 征兵 / Military.MaxTroopsPerOfficer
            S("粮草：战场上每天按兵力消耗粮食，粮尽就全军撤退。\n带去的金子可以在战场上的村庄里治疗武将、买兵器、换粮。"));   // BattleField.EndRoundSupply / OnLanded

    /// 战棋（BattleField，开战时）
    public static void Battle(System.Func<RectTransform> top, System.Func<RectTransform> mp, System.Func<RectTransform> cmds, System.Func<RectTransform> big)
        => Show("battle",
            S("这是战场。顶栏左右是双方带来的金粮，中间是第几天。", top),
            S("机动力是全军共用的：移动按地形扣点（平地少，山地、水面多），攻击扣 2 点，计策各有消耗。没用完的会攒到下一天（有上限）。", mp),   // BattleRules.MoveCap / AttackCost
            S("蓝色是我军，红色是敌军。每天开始会自动选中我方主将；点其他我方武将可以换人。"),
            S("选中武将后下命令：\n移动：点亮格走，可以连着走，按「确定」落定、「取消」退回\n计策：火计、落石等，对敌军使用\n攻击：打相邻的敌将，进入阵型战\n情报：查看武将\n撤退：退回相邻的己方城或空城\n待机：结束今天", cmds),
            S("看我军 / 看敌军：镜头跳到双方部队。\n回合结束：交给敌军行动，然后进入下一天。", big),
            S("击败守城主将或全歼守军，就能拿下城池；敌军粮尽也算赢。\n我方主将倒下、粮尽或打满 30 天，都要撤退。\n走到村庄可以治疗（50 金）、买兵器、换粮。"));   // BattleField.AfterClash / StartOurDay / HealPrompt

    /// 阵型选择（ClashView.BuildPick）
    public static void Pick(System.Func<RectTransform> vs, System.Func<RectTransform> cards, System.Func<RectTransform> go)
        => Show("pick",
            S("阵型战！两员武将带兵对阵。\n这里是双方的武力、智力、体力、兵力和装备。", vs),
            S("选择阵型：红点是武将，白点是士兵的站位。敌军的阵型是随机的。", cards),
            S("选好后按「出阵」开战。", go));

    /// 阵型战（ClashView.StartClash）
    public static void Clash(System.Func<RectTransform> field, System.Func<RectTransform> pts, System.Func<RectTransform> info)
        => Show("clash",
            S("阵型战是实时进行的：双方的兵棋轮流行动，直到一方武将倒下或退出战场。\n拖动场地查看，双指缩放。", field),
            S("点场上任意一枚我方兵棋，就能给它那个兵种（骑兵、弓兵、步兵）下令，同兵种一起行动：\n前进：冲向敌将\n待机：原地不动，只打靠近的\n撤退：向左撤出战场\n包围：围上去，但不攻击敌将", field),   // ClashView 699–707
            S("点我方武将本人，多一项「计策」：咒缚、挑战、连弩、呐喊、火矢、爆炎。", field),
            S("右上是计策点（智力越高越多），点数不够就放不出计策。", pts),   // BattleRules.PlotPoints
            S("下方是双方武将的体力和骑、弓、步兵的兵力。\n两员武将碰上就会单挑。武将体力打光就阵亡；撤出战场算溃走，回到战场接着打。", info));

    /// 单挑（DuelView）
    public static void Duel(System.Func<RectTransform> bars, System.Func<RectTransform> menu)
        => Show("duel",
            S("单挑！上方是双方的体力，打到 0 就阵亡。", bars),
            S("每回合选一招，对方同时出手：\n牵制：必中，伤害小\n攻击：约七成命中，伤害中等\n全力：约两成命中，伤害大，落空会伤到自己", menu),   // BattleRules.DuelPlain / Heavy / Crit
            S("劝降：对方越虚弱、忠诚越低，越容易成功\n逃跑：武力高才跑得掉，成功后全军撤退\n投降：我方武将被擒，这一仗结束", menu));   // BattleRules.Persuade / Flee
}

/// 教程遮罩：四块暗幕围出一个洞 + 洞边一圈金线 + 说明框（第几条 / 跳过 / 下一条）
public class TutorialOverlay : MonoBehaviour
{
    Tutorial.Step[] steps; int i; System.Action onEnd;
    RectTransform me;
    readonly Image[] shade = new Image[4];
    readonly Image[] rim = new Image[4];
    RectTransform box; Text body, count;
    bool dirty;

    static readonly Color Dim = new Color(0, 0, 0, .66f);
    static readonly Color Gold = new Color32(0xF0, 0xC8, 0x70, 255);

    public void Begin(Tutorial.Step[] s, System.Action end)
    {
        steps = s; onEnd = end; me = (RectTransform)transform;
        transform.SetAsLastSibling();
        for (int k = 0; k < 4; k++)
        {
            shade[k] = Node("shade", me).gameObject.AddComponent<Image>();
            shade[k].color = Dim; shade[k].raycastTarget = true;
            var b = shade[k].gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
            b.onClick.AddListener(Next);                                   // 点暗处 = 下一条
        }
        for (int k = 0; k < 4; k++) { rim[k] = Node("rim", me).gameObject.AddComponent<Image>(); rim[k].color = Gold; rim[k].raycastTarget = false; }
        // 洞里也拦住点击：讲解时不许误触底下的按钮
        var hole = Node("hole", me).gameObject.AddComponent<Image>(); hole.color = new Color(0, 0, 0, 0); hole.raycastTarget = true;
        var hb = hole.gameObject.AddComponent<Button>(); hb.transition = Selectable.Transition.None; hb.onClick.AddListener(Next);
        holeRT = hole.rectTransform;

        box = Node("box", me);
        var bg = box.gameObject.AddComponent<Image>(); bg.color = new Color(.10f, .075f, .045f, .96f); bg.raycastTarget = true;
        Hud.CgFrame(box);
        body = Label(box, "", 15, new Color32(0xF4, 0xE6, 0xC4, 255), TextAnchor.UpperLeft);
        body.horizontalOverflow = HorizontalWrapMode.Wrap; body.lineSpacing = 1.15f;
        count = Label(box, "", 12, new Color32(0xC8, 0xA8, 0x60, 255), TextAnchor.MiddleLeft);
        skipB = MakeBtn("跳过", Finish);
        nextB = MakeBtn("下一条", Next);
        dirty = true;
    }
    RectTransform holeRT, skipB, nextB;

    RectTransform MakeBtn(string s, UnityEngine.Events.UnityAction go)
    {
        var rt = Node("b", box);
        var im = rt.gameObject.AddComponent<Image>();
        im.sprite = SheetView.BtnPlate(); im.color = im.sprite == null ? new Color(.36f, .22f, .10f, 1) : Color.white;
        if (im.sprite != null) im.type = Image.Type.Sliced;
        var b = rt.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        b.onClick.AddListener(() => Sfx.Play("ui_click")); b.onClick.AddListener(go);
        var l = Label(rt, s, 14, new Color32(0xF4, 0xDC, 0x9A, 255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Fill(l.rectTransform);
        return rt;
    }

    public void SkipTo(int k) { i = Mathf.Clamp(k, 0, steps.Length - 1); dirty = true; }

    void Next()
    {
        if (++i >= steps.Length) { Finish(); return; }
        dirty = true;
    }
    void Finish() { onEnd?.Invoke(); Destroy(gameObject); }

    // 等一帧再量：刚搭好的界面这一帧可能还没排好
    // 遮罩尺寸变了（开窗、转屏、安全区刚算好）也重排
    Vector2 lastSize;
    void LateUpdate()
    {
        var sz = me.rect.size;
        if (sz != lastSize) { lastSize = sz; dirty = true; }
        if (dirty) { dirty = false; Draw(); }
    }

    void Draw()
    {
        float W = me.rect.width, H = me.rect.height;
        var st = steps[i];
        RectTransform t = null;
        try { t = st.Target?.Invoke(); } catch { t = null; }
        Rect r = t != null && t.gameObject.activeInHierarchy ? Local(t) : Rect.zero;
        bool has = r.width > 1 && r.height > 1;
        if (has) { r.xMin = Mathf.Max(2, r.xMin - 4); r.yMin = Mathf.Max(2, r.yMin - 4); r.xMax = Mathf.Min(W - 2, r.xMax + 4); r.yMax = Mathf.Min(H - 2, r.yMax + 4); }
        else r = new Rect(W / 2, H / 2, 0, 0);
        // 四块暗幕：上 / 下 / 左 / 右
        Place(shade[0].rectTransform, 0, 0, W, r.yMin);
        Place(shade[1].rectTransform, 0, r.yMax, W, H - r.yMax);
        Place(shade[2].rectTransform, 0, r.yMin, r.xMin, r.height);
        Place(shade[3].rectTransform, r.xMax, r.yMin, W - r.xMax, r.height);
        Place(holeRT, r.xMin, r.yMin, r.width, r.height);
        const float T = 2;
        Place(rim[0].rectTransform, r.xMin - T, r.yMin - T, r.width + 2 * T, T);
        Place(rim[1].rectTransform, r.xMin - T, r.yMax, r.width + 2 * T, T);
        Place(rim[2].rectTransform, r.xMin - T, r.yMin, T, r.height);
        Place(rim[3].rectTransform, r.xMax, r.yMin, T, r.height);
        foreach (var k in rim) k.enabled = has;

        // 说明框：宽度铺满（留边），高度按字数；洞在上半屏就放洞下面，否则放上面；没有洞就居中
        float bw = Mathf.Min(W - 28, 420), pad = 16;
        body.text = st.Text;
        Place(body.rectTransform, pad, pad, bw - pad * 2, 10);
        UIKit.Cjk(body);
        float th = body.preferredHeight;
        float bh = pad + th + 12 + 34 + 12;
        float bx = (W - bw) / 2, by;
        if (!has) by = (H - bh) / 2;
        else if (r.center.y < H / 2) by = Mathf.Min(r.yMax + 14, H - bh - 10);
        else by = Mathf.Max(10, r.yMin - 14 - bh);
        Place(box, bx, by, bw, bh);
        Place(body.rectTransform, pad, pad, bw - pad * 2, th + 4);
        count.text = (i + 1) + " / " + steps.Length;
        Place(count.rectTransform, pad, bh - 12 - 34, 80, 34);
        Place(skipB, bw - pad - 96 - 10 - 96, bh - 12 - 34, 96, 34);
        Place(nextB, bw - pad - 96, bh - 12 - 34, 96, 34);
        nextB.GetComponentInChildren<Text>().text = i == steps.Length - 1 ? "知道了" : "下一条";
        skipB.gameObject.SetActive(i < steps.Length - 1);
    }

    /// 目标在本遮罩里的矩形（左上原点、y 向下，跟 Place 一致）
    Rect Local(RectTransform t)
    {
        var c = new Vector3[4]; t.GetWorldCorners(c);
        float x0 = float.MaxValue, y0 = float.MaxValue, x1 = float.MinValue, y1 = float.MinValue;
        foreach (var w in c)
        {
            var p = me.InverseTransformPoint(w);
            float x = p.x - me.rect.xMin, y = me.rect.yMax - p.y;
            x0 = Mathf.Min(x0, x); x1 = Mathf.Max(x1, x); y0 = Mathf.Min(y0, y); y1 = Mathf.Max(y1, y);
        }
        return Rect.MinMaxRect(x0, y0, x1, y1);
    }
}
