// 阵型选择（原版 F(Graphics)）与阵型战（原版 21/22）。
// 这两屏还没做新素材，先用原版 jar 里抽出来的：卷轴底 lib302、VS 框 lib90、
// 四个阵型图标 lib49、四种地面 lib98、小兵 lib56、兵力条 lib11、城墙 lib306。
// 头像用新做的那套。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using static UIKit;

public class ClashView
{
    public RectTransform Root;
    public System.Action<int, int> OnDone;        // 我方剩余兵力, 敌方剩余兵力
    public System.Action OnDuel;                  // 双方武将碰上了
    public int Scene => scene;

    readonly float refW, refH;
    readonly Officer me, foe;
    readonly bool siege, weAttack;
    readonly int scene;                            // 0 平原 1 水 2 山林 3 攻城
    readonly System.Random rng = new System.Random();

    RectTransform pick, field;
    int formation;                                 // 光标 0–3
    List<Piece> mine, theirs;
    Dictionary<Vector2Int, int> walls;
    int round;
    readonly int[] orders = new int[4];             // 武将/骑/弓/步 各自的令
    // d.f[5] = 前进/待机/撤退/包围/计策。**计策只有武将那一行有**，兵种没有这一项。
    static readonly string[] ORDER = { "前进", "待机", "撤退", "包围", "计策" };
    static int OrderCount(int arm) => arm == 0 ? 5 : 4;
    static readonly string[] ARM = { "武将", "骑兵", "弓兵", "步兵" };

    Text dayT, buffT;
    RectTransform gridRoot, cmdPop;        // cmdPop：点兵种弹出来的那一竖条
    Text[] armCount = new Text[4], foeCount = new Text[4];

    readonly string theme; readonly int city;      // 阵型选择那屏的底图按这场仗取

    public ClashView(RectTransform parent, Officer me, Officer foe,
                     int terrain, bool weAttack, float refW, float refH,
                     string theme = null, int city = 0)
    {
        this.refW = refW; this.refH = refH; this.theme = theme; this.city = city;
        this.me = me; this.foe = foe; this.weAttack = weAttack;
        scene = BattleRules.SceneOf(terrain);
        siege = scene == 3;

        Root = Node("Clash", parent); Fill(Root);
        var bg = Node("bg", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)bg.transform); bg.color = new Color(.04f, .04f, .05f, 1);
        BuildPick();
    }

    // 底图与量出来的尺寸都在 BattleUi 里，单挑那屏也要用。
    static Sprite Ui(string k) => BattleUi.Get(k);
    static int[] Metric(string k) => BattleUi.Metric(k);
    static readonly string[] IconKey = { "ti", "qi", "gong", "bu" };
    static Sprite UiPlate() => BattleUi.PlateSp();
    static Sprite UiBtn()   => BattleUi.BtnSp();

    static Texture2D Tex(string rel)
    {
        var t = Load(Path.Combine("battle", rel));
        t.filterMode = FilterMode.Point; return t;
    }
    static Sprite Avatar(int id)
    {
        var p = Path.Combine(ArtDir, "battle", "avatar", id + ".jpg");
        if (!File.Exists(p)) return null;
        var t = new Texture2D(2, 2);
        t.LoadImage(File.ReadAllBytes(p)); t.Apply();
        return Whole(t);
    }

    // ── 阵型选择 ───────────────────────────────────────────────────────
    // 原版 c.N()：卷轴底、红框面板里一个大红「VS」、两个名字、四张阵型示意图
    // （红点武将、白点兵，lib49 那四格）。这里改成整屏：
    // 底图就用这场仗的单挑背景（攻城 = 那座城的动图第 0 帧），压一层暗；
    // 面板走木牌 + 錾金头像框；四张阵型图**按 Formation.Ours() 的真实坐标程序画**，
    // 和进场后的布阵一模一样，不用另出素材。
    readonly Image[] cardImgs = new Image[4];
    void BuildPick()
    {
        pick = Node("pick", Root); Fill(pick);

        // 底图：和 DuelView 同一套取法
        string[] key = { "plain", "water", "forest", "siege" };
        string bgf = "duel_bg_" + key[scene] + ".jpg";
        if (siege)
        {
            if (city > 0 && HasDuel("duel_bg_siege_c" + city + "_f0.jpg")) bgf = "duel_bg_siege_c" + city + "_f0.jpg";
            else if (city > 0 && HasDuel("duel_bg_siege_c" + city + ".jpg")) bgf = "duel_bg_siege_c" + city + ".jpg";
            else if (!string.IsNullOrEmpty(theme) && HasDuel("duel_bg_siege_" + theme + ".jpg")) bgf = "duel_bg_siege_" + theme + ".jpg";
        }
        if (HasDuel(bgf))
        {
            var bt = Tex(Path.Combine("duel", bgf));
            var bg = Node("bg", pick).gameObject.AddComponent<Image>();
            bg.sprite = Whole(bt); bg.raycastTarget = false;
            float k = Mathf.Max(refW / bt.width, refH / bt.height);       // cover 裁切，不拉伸
            Place((RectTransform)bg.transform, (refW - bt.width * k) / 2f, (refH - bt.height * k) / 2f,
                  bt.width * k, bt.height * k);
        }
        var dim = Node("dim", pick).gameObject.AddComponent<Image>();
        Fill((RectTransform)dim.transform); dim.color = new Color(0, 0, 0, .52f); dim.raycastTarget = false;

        // 整块内容垂直居中：顶栏 46 + 16 + 面板 300 + 16 + 卡 104 + 24 + 按钮 56 = 562
        float oy = (refH - 562) / 2f;

        // 顶栏
        var top = Panel(pick, "top", Ui("pick_banner") ?? UiPlate());
        Place((RectTransform)top.transform, 8, oy, refW - 16, 46);
        var t = Label(pick, "战争 · " + (siege ? "攻城战" : "野战"), 19,
                      new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, oy, refW, 46);

        // VS 面板
        var panel = Panel(pick, "vs", UiPlate());
        Place((RectTransform)panel.transform, 12, oy + 62, refW - 24, 300);
        const int PW = 132;
        PortraitAt(panel.transform, 18,                 me.Id,  me.Name,  new Color32(0x9C,0xC8,0xFF,255), PW, 16);
        PortraitAt(panel.transform, refW - 24 - 18 - PW, foe.Id, foe.Name, new Color32(0xFF,0x98,0x90,255), PW, 16);
        // 名字底下四行：武力 智力 / 体力 兵力 / 武器 / 防具
        StatsAt(panel.transform, 18 - 6,                 me,  16 + PW + 30);
        StatsAt(panel.transform, refW - 24 - 18 - PW - 6, foe, 16 + PW + 30);
        // 「VS」压在一方朱砂印上；有印就白字，没印就照原版红字
        var seal = Ui("pick_seal");
        if (seal != null)
        {
            var si = Node("seal", panel.transform).gameObject.AddComponent<Image>();
            Place((RectTransform)si.transform, (refW - 24) / 2f - 34, 66, 68, 66);
            si.sprite = seal; si.raycastTarget = false;
        }
        var vs = Label(panel.transform, "VS", 34, seal != null ? new Color32(0xFF,0xF4,0xE0,255) : new Color32(0xE0,0x40,0x30,255),
                       TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(vs.rectTransform, 0, 74, refW - 24, 50);
        var vsh = vs.gameObject.AddComponent<UnityEngine.UI.Shadow>();
        vsh.effectColor = new Color(0, 0, 0, .8f); vsh.effectDistance = new Vector2(2, -2);

        // 四张阵型卡
        const int CW = 80, CH = 104, GAP = 12;
        float x0 = (refW - (4 * CW + 3 * GAP)) / 2f;
        int nb = BattleRules.PieceCount(me.Troops);
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            var b = Node("f" + i, pick);
            Place(b, x0 + i * (CW + GAP), oy + 378, CW, CH);
            // 卡底要不透明——按钮底那张面上带一点透明，压在城墙上底下会透出来。
            // 有专门出的卡片底就用它，没有就用木牌面板那张（实心）。
            cardImgs[i] = b.gameObject.AddComponent<Image>();
            cardImgs[i].sprite = Ui("pick_card") ?? UiPlate(); cardImgs[i].type = Image.Type.Sliced;
            var btn = b.gameObject.AddComponent<Button>(); btn.transition = Selectable.Transition.None;
            btn.onClick.AddListener(() => { formation = k; MarkPick(); });
            DrawFormationCard(b, nb, i, CW, CH);
        }

        var go = Node("go", pick);
        Place(go, refW / 2 - 100, oy + 486, 200, 56);
        var gi = go.gameObject.AddComponent<Image>();
        gi.sprite = Ui("go") ?? UiBtn(); gi.type = Image.Type.Sliced;
        var gb = go.gameObject.AddComponent<Button>();
        if (Ui("go_on") != null)                                // 有按下态就换图
        {
            gb.transition = Selectable.Transition.SpriteSwap;
            gb.spriteState = new SpriteState { pressedSprite = Ui("go_on"), highlightedSprite = Ui("go"), disabledSprite = Ui("go_off") ?? Ui("go") };
        }
        else gb.transition = Selectable.Transition.None;
        gb.onClick.AddListener(StartClash);
        var gl = Label(go, "出阵", 21, new Color32(0xF6,0xE8,0xC0,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Fill(gl.rectTransform);
        var gsh = gl.gameObject.AddComponent<UnityEngine.UI.Shadow>();
        gsh.effectColor = new Color(0, 0, 0, .8f); gsh.effectDistance = new Vector2(1, -1);

        // 底下原来有一行「我方阵型你选，敌方每战随机取一种」和一根分隔条——
        // 那行字是我自己写的说明，原版没有，对玩的人也没有信息量，去掉。
        MarkPick();
    }

    /// 阵型示意：原版 lib49 那种红点武将、白点兵。这里按真实站位画——
    /// 兵在 1~5 列、0~6 行，武将固定在 (1,3)。格子 10 像素，卡内居中。
    void DrawFormationCard(RectTransform card, int nb, int f, int cw, int ch)
    {
        const int CELL = 10, COLS = 5, ROWS = 7, DOT = 7;
        float ox = (cw - COLS * CELL) / 2f, oy = (ch - ROWS * CELL) / 2f;
        void Dot(int col, int row, Color c)
        {
            var d = Node("d", card).gameObject.AddComponent<Image>();
            Place((RectTransform)d.transform, ox + (col - 1) * CELL + (CELL - DOT) / 2f,
                  oy + row * CELL + (CELL - DOT) / 2f, DOT, DOT);
            d.color = c; d.raycastTarget = false;
        }
        foreach (var p in Formation.Ours(nb, f))
            if (p.x >= 1 && p.x <= COLS && p.y >= 0 && p.y < ROWS) Dot(p.x, p.y, Color.white);
        Dot(1, 3, new Color32(0xF0,0x40,0x30,255));                       // 武将
    }

    void MarkPick()
    {
        var on = Ui("pick_card_on"); var off = Ui("pick_card");
        for (int i = 0; i < 4; i++)
        {
            if (cardImgs[i] == null) continue;
            if (on != null && off != null)
            {
                cardImgs[i].sprite = i == formation ? on : off; cardImgs[i].color = Color.white;
            }
            else
                cardImgs[i].color = i == formation ? new Color(1f, .92f, .55f, 1f) : new Color(.62f, .60f, .56f, 1f);
        }
    }

    /// 头像下的四行小字。左边一列标签暗金、右边数值米白，两列并排省高度。
    void StatsAt(Transform p, float x, Officer o, float y, int w = 144)
    {
        string[][] rows = {
            new[] { "武力", o.Might.ToString(), "智力", o.Int.ToString() },
            new[] { "体力", o.Hp + "/" + o.HpMax, "兵力", o.Troops.ToString() },
            new[] { "武器", BattleRules.WeaponOf(o), null, null },
            new[] { "防具", BattleRules.ArmorOf(o), null, null },
        };
        var lab = new Color32(0xC8,0xA8,0x60,255); var val = new Color32(0xF2,0xE8,0xD4,255);
        for (int r = 0; r < rows.Length; r++)
        {
            float yy = y + r * 19;
            var l1 = Label(p, rows[r][0], 12, lab); Place(l1.rectTransform, x, yy, 30, 18);
            var v1 = Label(p, rows[r][1], 13, val); Place(v1.rectTransform, x + 30, yy, rows[r][2] == null ? w - 30 : 44, 18);
            if (rows[r][2] == null) continue;
            var l2 = Label(p, rows[r][2], 12, lab); Place(l2.rectTransform, x + 76, yy, 30, 18);
            var v2 = Label(p, rows[r][3], 13, val); Place(v2.rectTransform, x + 106, yy, w - 106, 18);
        }
    }

    static bool HasDuel(string f) => File.Exists(Path.Combine(ArtDir, "battle", "duel", f));

    // 自检模式用的三个口子，正常玩走的是按钮
    public void PickForTest(int f) { formation = f; StartClash(); }
    public void SkillPanelForTest()
    {
        int i = mine.FindIndex(q => q.Alive && q.Arm == Arm.None);
        if (i < 0) return;
        FocusOn(mine[i].Col); gridRoot.anchoredPosition = camTarget;
        OpenSkillCmd(mine[i].Col, mine[i].Row);
    }
    /// 自检用：挑一枚指定兵种的我方棋子，把它滚进画面再弹出它的令菜单。
    public void CmdPopForTest(Arm arm)
    {
        int i = mine.FindIndex(q => q.Alive && q.Arm == arm);
        if (i < 0) return;
        FocusOn(mine[i].Col);
        gridRoot.anchoredPosition = camTarget;        // 自检不等镜头滑，直接到位
        OpenCmd((int)arm, mine[i].Col, mine[i].Row);
    }
    /// keepPop = true 时不收菜单：用来验「菜单开着推几拍，谁都不动」
    public void AdvanceForTest(int n = 120, bool keepPop = false)
    {
        if (!keepPop) CloseCmd();
        for (int i = 0; i < n; i++) { tickAt = 0; Tick(); }
    }
    public void FinishForTest() { Finish(); }
    /// 单挑里投降：原版 b.b(s,true) 置 aq=2，整场阵型战到此为止
    public void Surrender() { Cc = 4; over = me.Name + " 投降"; Finish(); }
    /// 自检：把我方四枚兵棋挪到敌将四周、把他打成残血，看围捕判定
    public void SurroundForTest()
    {
        CloseCmd();
        var g = theirs[0];
        foe.Hp = 30; foe.Loyal = 60;
        int k = 0;
        for (int i = 1; i < mine.Count && k < 4; i++)
        {
            var p = mine[i];
            if (!p.Alive || p.Off) continue;
            while (k < 4)
            {
                int nx = g.Col + DX[k], ny = g.Row + DY[k];
                k++;
                if (nx < 0 || ny < 0 || nx >= BattleRules.GridW || ny >= BattleRules.GridH) continue;
                p.Col = nx; p.Row = ny; mine[i] = p; break;
            }
        }
        FocusOn(g.Col);
        if (CaptureCheck()) { Cc = 3; over = CcText(); }
        Redraw();
    }
    /// 自检：把上面两个演示的结局抹掉，接着往下截图
    public void ClashResetForTest(int hp = 0)
    {
        Cc = 0; over = null; overAt = 0f; idleTicks = 0;
        if (hp > 0) { foe.Hp = hp; me.Hp = hp; }
        var g = theirs[0]; g.Alive = true; g.Off = false; theirs[0] = g;
        var m0 = mine[0];
        if (m0.Off) { m0.Off = false; m0.Col = 1; m0.Row = BattleRules.GridH / 2; }
        m0.Alive = true; mine[0] = m0;
        Redraw();
    }
    /// 自检：让我方武将的棋子走出战场（撤退令的结局），看 Z() 判溃走还是阵亡
    public void RoutForTest()
    {
        CloseCmd();
        var p = mine[0]; p.Off = true; p.Col = -1; p.Row = -1; mine[0] = p;
        RoutCheck(); over = CcText(); Redraw();
    }
    public void FireForTest() { UseSkill(4); }        // 自检：点火矢，看箭带不带火
    /// 自检：直接挂上咒缚/呐喊。咒缚的绳子在敌将脚下，镜头得滚过去才截得到。
    public void BuffForTest(int skill)
    {
        cd = BattleRules.CdOf(skill);
        if (skill == 0 && theirs.Count > 0) { FocusOn(theirs[0].Col); gridRoot.anchoredPosition = camTarget; }
        Redraw();
    }
    /// 自检：不等两军接上，直接给第一枚敌军标一次「被 arm 打中」，看命中特效。
    public void HitForTest(int arm)
    {
        int i = theirs.FindIndex(q => q.Alive && q.Arm != Arm.None);
        if (i < 0) return;
        var q = theirs[i]; q.HitAt = Time.time; q.HitArm = arm; theirs[i] = q;
        FocusOn(q.Col); gridRoot.anchoredPosition = camTarget; Redraw();
    }
    public void BlazeForTest() { plotPts = 99; UseSkill(5); }                        // 自检：不管点数够不够，放爆炎
    /// 自检：不等 AI 轮到弓兵，直接从第一个弓兵向右射一支（火矢），看渲染。
    public void ShotForTest()
    {
        int i = mine.FindIndex(q => q.Alive && q.Arm == Arm.Gong);
        if (i < 0) return;
        var p = mine[i];
        FocusOn(p.Col + 2); gridRoot.anchoredPosition = camTarget;
        shots.Add(new Shot { X0 = p.Col, Y0 = p.Row, X1 = p.Col + 4, Y1 = p.Row,
                             At = Time.time, Fire = BattleRules.BuffId(cd) == 5 });
    }

    // ── 阵型战 ─────────────────────────────────────────────────────────
    void StartClash()
    {
        Object.Destroy(pick.gameObject);
        plotPts = BattleRules.PlotPoints(me);   // 智力×13/100，不是武将身上的 Q[]
        turnIdx = 1;          // cD = bI > 1 ? 1 : 0 —— 0 号是武将，压阵最后动
        mine   = Formation.Build(me,  formation,      true,  siege, weAttack);
        theirs = Formation.Build(foe, rng.Next(4),     false, siege, weAttack);
        walls  = siege ? Formation.Walls(weAttack) : new Dictionary<Vector2Int, int>();
        // b.S() 守城那一支末尾：for (n=0;n<4;n++) cj[n] = 1；
        // 守方四个兵种开局全是「待机」——守城本来就该守在原位，不是冲出去。
        if (siege && !weAttack) for (int i = 0; i < 4; i++) orders[i] = 1;
        roundNo = 0; Cc = 0; idleTicks = 0;
        troops0Me = me.Troops; troops0Foe = foe.Troops;
        enemyPts = BattleRules.PlotPoints(foe);
        RefreshEnemyOrders();
        BuildField();
        Redraw();
    }

    // 场地是 16 列 × 7 行（bK = byte[7][16]）。原版屏宽 176，一次只显示 11 列，
    // 靠 aa 左右滚；我们不再迁就「一屏看全」，改成放大到半屏、拖着看。
    // 按参考分辨率 375 × 812 算。底下的兵种指令栏、计策栏、说明文字都拆了，
    // 小地图铺满整宽（一格 20，面板 344）：
    // 44(顶栏) + 7C + 14 + 124(信息) + 10 + 164(小地图) = 804，下边还剩 8 → C = 64。
    // 再往上顶就没意义了——C=78 时七行吃掉 67% 屏高，横向只剩不到五列，
    // 一屏大半是空地面，那不是放大是放过头。
    // 小兵图跟着按 FIG 重切（整图一格 512，分辨率够，重切不花钱），
    // 贴上去是一比一，不是把 64 的图放大成糊块。
    const int C = 64;                      // 阵型格一格多大
    const int FIG = C + 14;                // 棋子画多大：比格子高出一截，脚仍落在格底
    const int VIEW_Y = 44;
    // 底下两栏（双方兵数 124、小地图 24+7×20）贴着屏幕下沿往上排，
    // **场地视窗吃掉中间剩下的全部高度** —— 屏幕矮就少看几行（能拖能缩，不影响操作），
    // 屏幕高就多留一点余量，场地在视窗里居中。写死 448 的话矮屏上小地图会被挤出屏幕。
    int MAP_H  => 24 + BattleRules.GridH * MM;
    int MAP_Y  => (int)refH - MAP_H - 8;
    int INFO_Y => MAP_Y - 10 - 124;
    int VIEW_H => Mathf.Max(120, INFO_Y - 14 - VIEW_Y);
    const int MM = 20;                         // 小地图一格多大（原版是 3 像素）
    void BuildField()
    {
        field = Node("field", Root); Fill(field);

        var topBar = Panel(field, "top", UiPlate());
        Place((RectTransform)topBar.transform, 4, 3, refW - 8, 38);

        dayT = Label(field, "", 17, new Color32(0xF2,0xE8,0xD4,255));
        Place(dayT.rectTransform, 20, 13, 200, 20);   // 20 是让开左边那道铜边
        buffT = Label(field, "", 13, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleRight);
        Place(buffT.rectTransform, refW - 22 - 200, 13, 200, 20);

        // 场地比视窗宽（16 × 64 = 1024 对 375），所以套一层遮罩，里面那层拖着走。
        var clip = Node("clip", field);
        Place(clip, 0, VIEW_Y, refW, VIEW_H);
        var msk = clip.gameObject.AddComponent<Image>();
        msk.color = new Color(.06f, .06f, .07f, 1);
        clip.gameObject.AddComponent<Mask>().showMaskGraphic = true;

        gridRoot = Node("grid", clip);
        gridRoot.anchorMin = gridRoot.anchorMax = new Vector2(0, 1);
        gridRoot.pivot = new Vector2(0, 1);
        gridRoot.sizeDelta = new Vector2(BattleRules.GridW * C, BattleRules.GridH * C);

        // 地面照原版 L() 那样一块块铺开，一张图拉满近千像素只会糊成大色块。
        // 新素材是四张 256 的无缝贴图（草原 / 连环船 / 山地 / 城郭），铺到 128 一块；
        // 没做出来就退回原版 lib98 那条 256×64 的四格图，按 64 铺。
        string[] gk = { "ping", "shui", "shan", "gong" };
        var grel = Path.Combine("clashground", "cg_" + gk[Mathf.Clamp(scene, 0, 3)] + ".png");
        bool newGround = File.Exists(Path.Combine(ArtDir, "battle", grel));
        int tile = newGround ? 128 : 64;
        Sprite gsp;
        if (newGround)
        {
            // 有四帧动图就读四帧：草在摆、船间的水在动。静态那张当第 0 帧的兜底。
            var arel = Path.Combine("clashground", "cg_" + gk[Mathf.Clamp(scene, 0, 3)] + "_f0.png");
            if (File.Exists(Path.Combine(ArtDir, "battle", arel)))
            {
                groundFrames = new Sprite[4];
                for (int i = 0; i < 4; i++)
                {
                    var ft = Tex(Path.Combine("clashground",
                        "cg_" + gk[Mathf.Clamp(scene, 0, 3)] + "_f" + i + ".png"));
                    groundFrames[i] = Sprite.Create(ft, new Rect(0, 0, ft.width, ft.height),
                                                    new Vector2(.5f, .5f), 100);
                }
                gsp = groundFrames[0];
            }
            else
            {
                var gt0 = Tex(grel);
                gsp = Sprite.Create(gt0, new Rect(0, 0, gt0.width, gt0.height), new Vector2(.5f, .5f), 100);
            }
        }
        else
        {
            // lib98 是四种地面并排的一张图，画布不是整数倍缩放时贴到边上会采到隔壁那一列，
            // 铺开后就是一条条竖缝——所以往里收半个像素。
            var g98 = Tex("orig/lib98.png");
            gsp = Sprite.Create(g98, new Rect(scene * 64 + .5f, .5f, 63f, 63f), new Vector2(.5f, .5f), 100);
        }
        for (int y = 0; y * tile < BattleRules.GridH * C; y++)
            for (int x = 0; x * tile < BattleRules.GridW * C; x++)
            {
                var t = Node("gt", gridRoot).gameObject.AddComponent<Image>();
                Place((RectTransform)t.transform, x * tile, y * tile, tile, tile);
                t.sprite = gsp; t.raycastTarget = false;
                if (groundFrames != null) groundTiles.Add(t);
            }

        // 按下拖动 = 滚场地。遮罩那张 Image 就当接触区，别再叠一个。
        msk.raycastTarget = true;
        var trig = clip.gameObject.AddComponent<EventTrigger>();
        void Hook(EventTriggerType t, System.Action<PointerEventData> f)
        {
            var e = new EventTrigger.Entry { eventID = t };
            e.callback.AddListener(d => f((PointerEventData)d));
            trig.triggers.Add(e);
        }
        Hook(EventTriggerType.PointerDown, d => {
            dragFrom = d.position; gridFrom = gridRoot.anchoredPosition; dragged = false;
        });
        Hook(EventTriggerType.Drag, d => {
            if ((d.position - dragFrom).magnitude > 8) dragged = true;
            if (!dragged) return;
            ScrollTo(gridFrom + (d.position - dragFrom));
            camTarget = gridRoot.anchoredPosition;   // 手动拖过就别再被镜头拽回去
        });
        Hook(EventTriggerType.PointerUp, d => { if (!dragged) TapField(d, clip); });
        ScrollTo(Vector2.zero);   // 我方摆在左边（Formation：Col = ours ? 1 : GridW-2）

        // 信息栏：体 / 骑 / 弓 / 步，和原版底部那一栏一样
        var info = Panel(field, "info", UiPlate());
        Place((RectTransform)info.transform, 8, INFO_Y, refW - 16, 124);
        // 两侧各摆一个头像：先贴头像，再把錾金框压在上面。
        // 框中间那个洞的位置是量出来的，写在 battleui.txt 里。
        float pw = refW - 16;                                // 信息栏自己的宽
        PortraitAt(info.transform, 12,          me.Id,  me.Name,  new Color32(0x8C,0xC0,0xFF,255));
        PortraitAt(info.transform, pw - 12 - 68, foe.Id, foe.Name, new Color32(0xFF,0x90,0x88,255));
        // 中间这一列原来是「体 骑 弓 步」四个字，压不住整块面板，换成图标。
        // 没切出图标的话退回文字，界面不至于开天窗。
        string[] lab = { "体", "骑", "弓", "步" };
        const int IC = 28, ROW = 28, Y0 = 8;
        // 这一列一直是按 refW/2 定的，可面板自己从 x=8 起算，
        // 于是整列比面板中线偏右 8 像素。按面板自己的中线来。
        float cx = pw / 2;
        for (int i = 0; i < 4; i++)
        {
            int y = Y0 + i * ROW;
            var ico = Ui("icon_" + IconKey[i]);
            if (ico != null)
            {
                var im = Node("ic", info.transform).gameObject.AddComponent<Image>();
                Place((RectTransform)im.transform, cx - IC / 2f, y, IC, IC);
                im.sprite = ico; im.raycastTarget = false;
            }
            else
            {
                var l = Label(info.transform, lab[i], 15,
                              new Color32(0xD0,0xC0,0x98,255), TextAnchor.MiddleCenter);
                Place(l.rectTransform, cx - 16, y + 4, 32, 20);
            }
            armCount[i] = Label(info.transform, "", 17, Color.white, TextAnchor.MiddleRight);
            Place(armCount[i].rectTransform, cx - 30 - 100, y + 4, 100, 20);
            foeCount[i] = Label(info.transform, "", 17, Color.white);
            Place(foeCount[i].rectTransform, cx + 30, y + 4, 100, 20);
        }

        BuildMinimap();
        // 这儿原来有个「收兵」按钮，是我加的。原版 b.ah() 的循环只有两个出口：
        // c(boolean) 里双方武将相邻置 aq=1 跳去单挑，和 Z() 判出的溃败；
        // 没有任何一处是玩家按键触发的。想脱离交战走的是兵种令里的「撤退」。
        // 所以去掉，分出胜负后自己收场（见 Tick）。
    }

    // ── 实时自动战 ─────────────────────────────────────────────────────
    // 原版 b.java:5480 那个主循环：每 100ms 一帧，攒够 10 帧（≈1 秒）才动**一枚**兵棋，
    // cD 从 1 开始逐枚往后推，一方走完换另一方。
    // 关键在 b.s(by) 的第一行 —— bY[0][by] = cj[bU[0][by]]：
    // **轮到这枚棋时才去读它那一兵种当前的令**。
    // 所以你中途把骑兵从「前进」切成「撤退」，已经动过的那枚照样往前，
    // 还没轮到的三枚就掉头 —— 这正是原版能微操的地方，没有开打/暂停这回事。
    const float TICK = 1.0f;              // 10 帧 × 100ms
    float tickAt;
    int turnSide;                          // 0 我方 1 敌方
    int turnIdx;
    int extra;                             // cF：这枚棋是不是已经用掉了第二次行动
    int cd;                                // 生效中的增益：高 4 位能力号、低 4 位剩余回合
    int ce;                                // 敌方的增益，同样的编码（原版 ce）
    int plotPts;                           // 能力点 bN = 智力 × 13 / 100
    int enemyPts;                          // 敌方的 bO
    int roundNo;
    int troops0Me, troops0Foe;             // 开打前双方的兵，散场时用来算歼敌数 → 计策点
    /// 原版 cc：0 还没分出 / 1 敌将阵亡 / 2 我将阵亡 / 3 敌将被擒 / 4 我将被擒 / 5 敌将溃走 / 6 我将溃走
    public int Cc;
    public int Killed, Lost;               // 这一仗歼敌、损兵各多少（b.aa() 换成计策点）
    int idleTicks;
    bool wasPaused;
    float overAt;                          // 分出胜负那一刻，用来延一小会儿再收场
    public int ActingSide = -1, ActingIdx = -1;
    public static bool Trace;                  // -trace：每一步写进 Player.log，自检用
    string over;

    public void Tick()
    {
        Glide();                                  // 镜头每帧都动，别卡在下面的节拍判断后面
        ZoomTick();
        GroundTick();
        ShotTick();
        FxTick();
        if (mine == null) return;                 // 还在阵型选择那屏，兵还没摆
        // 原版 b.ci：1 = 推进中、2 = 暂停。c.B() 里按确定就把 ci 置 2，改完令再按确定或取消才回 1，
        // 主循环 5515 行 `if (ci == 2) continue;` 把这一拍整个跳过 —— 也就是**菜单一开，全场停住**。
        // 我们这边菜单是点出来的，所以菜单在就等于暂停；节拍一并往后推，收起来时重新走满一拍。
        bool paused = cmdPop != null;
        if (paused != wasPaused) { wasPaused = paused; Redraw(); }   // 进出暂停各重画一次，表头才跟着变
        if (paused) { tickAt = Time.time + TICK; return; }
        if (over != null)
        {
            // 原版 b.ah()：ac() 的循环一 break 就直接 ab() 结算回战棋层，
            // 没有「收兵」这种玩家按键。留一小会儿让人看清结果，然后自己收场。
            if (overAt == 0f) overAt = Time.time;
            else if (Time.time - overAt > 2.5f) { overAt = -1e9f; Finish(); }
            return;
        }
        if (Time.time < tickAt) return;
        tickAt = Time.time + TICK;

        // 原版的 do { cH = s(cD); ... } while (!cH)：
        // 没真正做成事的棋（阵亡、待机、被挡住）不占节拍，直接轮下一枚。
        ActingSide = -1;
        for (int guard = 0; guard < 128; guard++)
        {
            var side  = turnSide == 0 ? mine : theirs;
            var other = turnSide == 0 ? theirs : mine;
            if (turnIdx >= side.Count) turnIdx = 0;          // 走完一圈回绕到 0（武将）

            var p = side[turnIdx];
            int idx = turnIdx;
            bool did = p.Alive && Act(side, other, turnSide == 0, turnIdx);
            if (Trace && did) Debug.Log($"[clash] r{roundNo} side{turnSide} #{idx} {p.Arm} ({p.Col},{p.Row}) -> ({side[idx].Col},{side[idx].Row}) dir{side[idx].Dir}");

            // 原版是先 s(cD) 再判断切边，用的是上一轮留下的 cF
            if (idx == 0 && extra >= 1)
            {
                extra = 0;
                turnSide = 1 - turnSide;
                var ns = turnSide == 0 ? mine : theirs;
                turnIdx = ns.Count > 1 ? 1 : 0;              // cD = bI > 1 ? 1 : 0
                if (turnSide == 1)                           // 我方轮完了一圈（原版 cI 0→1）
                {
                    if (Cc == 0 && CaptureCheck()) Cc = 3;   // Y()：围住了就擒
                    RefreshEnemyOrders();                    // h(cG)，里头连着 bZ() 给敌方上能力
                    if (ce != 0)
                    {
                        ce = (ce & 0xF0) | Mathf.Max(0, (ce & 0xF) - 1);
                        if ((ce & 0xF) == 0) ce = 0;
                    }
                }
                else                                         // 敌方也轮完了：cG++
                {
                    roundNo++;
                    if (cd != 0)
                    {
                        cd = (cd & 0xF0) | Mathf.Max(0, (cd & 0xF) - 1);
                        if ((cd & 0xF) == 0) cd = 0;
                    }
                }
                if (did) { ActingSide = 1 - turnSide; ActingIdx = idx; FocusOn(p.Col); idleTicks = 0; break; }
                continue;
            }

            // if (!cb[cD] || bU[cD] >= 2 || cF >= 1) { cD++; cF = 0; } else cF++;
            // bU >= 2 是弓兵和步兵 —— 只有武将(0)和骑兵(1)能动第二次。
            bool twice = p.Alive && !p.Off && (p.Arm == Arm.None || p.Arm == Arm.Qi);
            if (!p.Alive || p.Off || !twice || extra >= 1) { turnIdx++; extra = 0; }
            else extra++;

            if (did) { ActingSide = turnSide; ActingIdx = idx; FocusOn(p.Col); idleTicks = 0; break; }
        }
        if (ActingSide < 0) idleTicks++;

        // 原版没有「兵死光了就收」这一说：小兵打完武将自己还在盘上接着打，
        // 一直打到他的棋子离盘（体力被打光，或者下了撤退令走出边界）——这才有 cc。
        RoutCheck();
        if (Cc != 0) over = CcText();
        else if (AllOff(theirs)) { Cc = 5; over = CcText(); }
        else if (AllOff(mine))   { Cc = 6; over = CcText(); }
        else if (idleTicks > 20) over = "双方罢兵";            // 兜底：谁都动不了（原版没有，见 HOUSE_RULES）
        Redraw();
    }

    /// b.s(by)：一枚兵棋的一次行动。返回它是不是真做成了事（原版的 cH）。
    ///
    /// 原版按令分派（bY = cj[兵种]）：0 前进 m() / 1 待机 p() / 2 撤退 q() / 3 包围 r()。
    /// 敌方是 u() / x() / y()，逻辑镜像，令由 h(cG) 每轮刷新（见 RefreshEnemyOrders）。
    ///  · 前进：先用 l()/t() 朝**对方武将**选一个方向（不是离得最近的兵），
    ///          再看四邻有没有敌人：选定方向上有、或者我是武将且邻着对方武将 → 必打；
    ///          其余相邻敌人 70% 打、30% 不理继续走；四邻没人就迈一步。
    ///          弓兵是沿四条直线扫射程内第一个敌人，80% 射。
    ///  · 待机：只打相邻（弓兵射直线上的），从不挪窝。
    ///  · 撤退：我方一路向左、敌方向右，走到边上出场；前面有墙/有人，我方往上下侧移，敌方干等。
    ///  · 包围：和前进一样走位，但**不打对方武将也不往他身上走**，弓兵射线到他那格就停。
    bool Act(List<Piece> side, List<Piece> other, bool ours, int i)
    {
        var p = side[i];
        if (!p.Alive || p.Off) return false;
        int cmd = ours ? orders[(int)p.Arm] : enemyOrders[(int)p.Arm];
        // 咒缚（cd 高 4 位 = 1）：敌方**武将本人**改走 x(0)——待机那一支，只打相邻的，不移动
        if (!ours && p.Arm == Arm.None && BattleRules.BuffId(cd) == 1) cmd = 1;
        int range = BattleRules.ArrowRange(ours ? cd : ce);

        switch (cmd)
        {
            case 1:                                            // 待机 n()/o()、v()/w()
            {
                int d = p.Arm == Arm.Gong ? LineTarget(p, -1, ours, false, range)
                                          : AdjTarget(p, -1, ours, false);
                if (d < 0) return false;
                return Attack(side, other, ours, i, d, range);
            }
            case 2:                                            // 撤退 q() / y()
                return Retreat(side, ours, i);
            default:                                           // 前进 m() / 包围 r()
            {
                bool surround = cmd == 3;
                int dir = Toward(p, ours);
                if (dir >= 0) { p.Dir = dir; side[i] = p; }   // bX 跟着走向更新
                int d = p.Arm == Arm.Gong ? LineTarget(p, dir, ours, surround, range)
                                          : AdjTarget(p, dir, ours, surround);
                if (d >= 0) return Attack(side, other, ours, i, d, range);
                if (dir >= 0 && d != -2) return Move(side, i, dir);
                return false;
            }
        }
    }

    static readonly int[] DX = { 0, 0, -1, 1 }, DY = { -1, 1, 0, 0 };   // 0 上 1 下 2 左 3 右

    /// 一格上有什么，按原版 bK 的位：0x20 墙（32）/ 2、16 栅门 / 0x40 我方兵棋 / 0x80 敌方兵棋。
    /// 出界当作三样全有，省得每处都写边界判断。
    int Cell(int x, int y)
    {
        if (x < 0 || y < 0 || x >= BattleRules.GridW || y >= BattleRules.GridH) return 0xE0;
        int v = walls.TryGetValue(new Vector2Int(x, y), out int w) ? w : 0;
        if (Occupied(mine, x, y)) v |= 0x40;
        if (Occupied(theirs, x, y)) v |= 0x80;
        return v;
    }

    /// b.l(by) / b.t(by)：朝对方武将选向。
    /// 先把"目标"从武将本人挪到他旁边一格：不同行时优先他**身后**那格（我方看是右边、敌方看是左边），
    /// 同行时上/下/前。然后横向优先：目标在右且右边能走 → 右 …… 已经同列就上下随机侧移，同行就左右侧移。
    /// "能走"只看 (格 & 0x60)==0（我方）—— 墙和自己人挡路，**敌人不挡**，走过去就是打。
    int Toward(Piece p, bool ours)
    {
        var g = ours ? theirs[0] : mine[0];
        if (!g.Alive || g.Off) return -1;
        int blk = 0x20 | (ours ? 0x40 : 0x80);
        bool Free(int x, int y) => (Cell(x, y) & blk) == 0;
        int gx = g.Col, gy = g.Row, px = p.Col, py = p.Row, n = -1;
        if (ours)
        {
            if (gx < 15 && Free(gx + 1, gy) && gy != py) gx++;
            else if (gy - py <= 0)
            {
                if (gy > 0 && Free(gx, gy - 1)) gy--;
                else if (gy < 6 && Free(gx, gy + 1)) gy++;
                else if (gx > 0 && Free(gx - 1, gy)) gx--;
            }
            else if (gy < 6 && Free(gx, gy + 1)) gy++;
            else if (gy > 0 && Free(gx, gy - 1)) gy--;
            else if (gx > 0 && Free(gx - 1, gy)) gx--;
        }
        else
        {
            // 原版 t() 镜像的时候把查的格写反了：查 gx+1 却往 gx-1 挪、查 gx-1 却往 gx+1 挪。照抄。
            if (gx > 0 && Free(gx + 1, gy) && gy != py) gx--;
            else if (gy - py <= 0)
            {
                if (gy > 0 && Free(gx, gy - 1)) gy--;
                else if (gy < 6 && Free(gx, gy + 1)) gy++;
                else if (gx < 15 && Free(gx - 1, gy)) gx++;
            }
            else if (gy < 6 && Free(gx, gy + 1)) gy++;
            else if (gy > 0 && Free(gx, gy - 1)) gy--;
            else if (gx < 15 && Free(gx - 1, gy)) gx++;
        }
        if (px < 15 && gx - px > 0 && Free(px + 1, py)) { if (gx == px + 1 && gy == py) n = 3; else return 3; }
        if (px > 0 && gx - px < 0 && Free(px - 1, py)) { if (gx == px - 1 && gy == py) n = 2; else return 2; }
        if (py < 6 && gy - py > 0 && Free(px, py + 1)) return 1;
        if (py > 0 && gy - py < 0 && Free(px, py - 1)) return 0;
        if (gx == px)
        {
            if (px < 15 && px > 0 && Free(px + 1, py) && Cell(px - 1, py) == 0) return rng.Next(2) + 2;
            if (px < 15 && Free(px + 1, py)) return 3;
            if (px > 0 && Free(px - 1, py)) return 2;
        }
        if (gy == py)
        {
            if (py < 6 && py > 0 && Free(px, py + 1) && Free(px, py - 1)) return rng.Next(2);
            if (py < 6 && Free(px, py + 1)) return 1;
            if (py > 0 && Free(px, py - 1)) return 0;
        }
        return n;
    }

    /// b.l(by,by2) / t(by,by2)，包围令走 p(by,by2)：四邻里挑一个打，返回方向；-1 不打；-2（包围）连走都别走。
    int AdjTarget(Piece p, int dir, bool ours, bool surround)
    {
        int foe = ours ? 0x80 : 0x40;
        var g = ours ? theirs[0] : mine[0];
        var cand = new List<int>(4);
        for (int d = 0; d < 4; d++)
        {
            int x = p.Col + DX[d], y = p.Row + DY[d];
            bool isGen = g.Alive && !g.Off && g.Col == x && g.Row == y;
            if (surround)
            {
                if (dir == d && isGen) { dir = -1; continue; }         // 选的方向上是对方武将：不打、也不走
                if ((Cell(x, y) & foe) != 0 && !isGen) { if (dir == d) return d; cand.Add(d); }
            }
            else if ((Cell(x, y) & foe) != 0)
            {
                if (dir == d || (p.Arm == Arm.None && isGen)) return d;
                cand.Add(d);
            }
        }
        if (cand.Count > 0 && (dir < 0 || rng.Next(10) >= 3)) return cand[rng.Next(cand.Count)];
        return surround && dir < 0 ? -2 : -1;
    }

    /// 弓兵 n(by,by2) / v(by,by2)，包围令 r(by,by2)：相邻且正是选定方向 → 射；
    /// 否则四条直线各扫 range 格找第一个敌人，80% 射。包围令扫到对方武将那格就停。
    int LineTarget(Piece p, int dir, bool ours, bool surround, int range)
    {
        int foe = ours ? 0x80 : 0x40;
        var g = ours ? theirs[0] : mine[0];
        for (int d = 0; d < 4; d++)
        {
            int x = p.Col + DX[d], y = p.Row + DY[d];
            if (dir == d && (Cell(x, y) & foe) != 0)
            {
                if (surround && g.Col == x && g.Row == y) dir = -1;
                else return d;
            }
        }
        var cand = new List<int>(4);
        for (int d = 0; d < 4; d++)
            for (int k = 1; k <= range; k++)
            {
                int x = p.Col + DX[d] * k, y = p.Row + DY[d] * k;
                if (x < 0 || y < 0 || x >= BattleRules.GridW || y >= BattleRules.GridH) break;
                if (surround && g.Col == x && g.Row == y) break;
                if ((Cell(x, y) & foe) != 0) { cand.Add(d); break; }
            }
        if (cand.Count > 0 && (dir < 0 || rng.Next(10) >= 2)) return cand[rng.Next(cand.Count)];
        return surround && dir < 0 ? -2 : -1;
    }

    /// b.c/d/e/f：往 dir 迈一格。选向函数已经保证那格没墙没自己人；这里再兜一道底，有人就不动。
    bool Move(List<Piece> side, int i, int dir)
    {
        var p = side[i];
        int nx = p.Col + DX[dir], ny = p.Row + DY[dir];
        if ((Cell(nx, ny) & 0xE0) != 0) return false;
        p.Dir = dir; p.Col = nx; p.Row = ny; side[i] = p;
        return true;
    }

    /// b.q(by)（我方，向左）/ b.y(by)（敌方，向右）
    bool Retreat(List<Piece> side, bool ours, int i)
    {
        var p = side[i];
        int edge = ours ? 0 : 15, ahead = ours ? 2 : 3;
        if (p.Col != edge && (Cell(p.Col + DX[ahead], p.Row) & 0xE0) != 0)
        {
            if (!ours) return false;                               // y()：被挡就等
            int n = 0;
            if (p.Row > 0 && (Cell(p.Col, p.Row - 1) & 0xE0) == 0) n += 1;
            if (p.Row < 6 && (Cell(p.Col, p.Row + 1) & 0xE0) == 0) n += 2;
            if (n == 0) return false;
            return Move(side, i, n == 3 ? rng.Next(2) : n - 1);
        }
        if (p.Col == edge)                                         // 走到边上：出场（兵还在，回武将身边）
        {
            p.Off = true; p.Col = -1; p.Row = -1; side[i] = p;
            return true;
        }
        return Move(side, i, ahead);
    }

    /// b.b(by,dir,·)（近战打相邻）/ b.c(by,dir,·)（弓兵沿直线射第一个）。武将撞武将 → 单挑。
    bool Attack(List<Piece> side, List<Piece> other, bool ours, int i, int dir, int range)
    {
        var p = side[i];
        var self  = ours ? me : foe;
        var enemy = ours ? foe : me;
        int t = -1;
        int reach = p.Arm == Arm.Gong ? range : 1;
        for (int k = 1; k <= reach && t < 0; k++)
        {
            int x = p.Col + DX[dir] * k, y = p.Row + DY[dir] * k;
            for (int j = 0; j < other.Count; j++)
                if (other[j].Alive && !other[j].Off && other[j].Col == x && other[j].Row == y) { t = j; break; }
        }
        if (t < 0) return false;
        var q = other[t];
        int dmg = BattleRules.Damage(BattleRules.Atk(self, p.Arm),
                                     BattleRules.Def(enemy), p.Troops, rng,
                                     ours ? cd : ce, p.Arm);
        // b.a(0,by,1,by6,·)：打人的和挨打的都是武将本人 → aq=1，跳出去单挑
        // 双方都站着才打得起来；有一方体力已空就不进单挑了，直接算伤害
        if (p.Arm == Arm.None && q.Arm == Arm.None && me.Hp > 0 && foe.Hp > 0)
        { OnDuel?.Invoke(); return true; }
        if (p.Arm == Arm.Gong)                             // 射出去的箭，画一下
            shots.Add(new Shot { X0 = p.Col, Y0 = p.Row, X1 = q.Col, Y1 = q.Row, At = Time.time,
                                 Fire = ours && BattleRules.BuffId(cd) == 5 });   // 火矢生效时箭带火
        if (q.Arm == Arm.None)
        {
            // 原版 c(boolean) 5238：砍在武将身上的是体力，扣的是「伤害 ⁄ 4 + 1」；
            // 体力归零 → cb 置 false，**武将的棋子离盘**，这一场就由 Z() 定收场。
            if (ours) foe.Hp = Mathf.Max(0, foe.Hp - BattleRules.HpHit(dmg));
            else      me.Hp  = Mathf.Max(0, me.Hp  - BattleRules.HpHit(dmg));
            q.HitAt = Time.time; q.HitArm = (int)p.Arm;
            if ((ours ? foe.Hp : me.Hp) <= 0) q.Alive = false;
            other[t] = q;
        }
        else
        {
            q.Troops -= dmg;
            q.HitAt = Time.time; q.HitArm = (int)p.Arm;
            if (q.Troops <= 0) { q.Troops = 0; q.Alive = false; }
            other[t] = q;
        }
        return true;
    }

    // ── 敌方的令：b.h(cG) 每轮刷新 ──────────────────────────────────────
    // 我方主动开打（cl）→ z(n)：敌方全军待机；被敌方打 → A(n)：敌方武将待机、小兵前进。
    // 两者共用的先手判断 U()：第 4 轮起，我方小兵合计 >300 且敌方小兵 <100 → 敌方全军撤退（撤退令一旦下了就不改）。
    readonly int[] enemyOrders = new int[4];
    void RefreshEnemyOrders()
    {
        bool U()
        {
            if (enemyOrders[0] == 2) return true;
            int n = 0, n2 = 0;
            foreach (var p in mine)   if (p.Alive && p.Arm != Arm.None) n  += p.Troops;
            foreach (var p in theirs) if (p.Alive && p.Arm != Arm.None) n2 += p.Troops;
            return n > 300 && n2 < 100;
        }
        if (U() && roundNo >= 4) { for (int i = 0; i < 4; i++) enemyOrders[i] = 2; return; }
        EnemyAbility();                                   // 原版 z(n)/A(n) 第一件事就是 bZ()
        if (weAttack) for (int i = 0; i < 4; i++) enemyOrders[i] = 1;
        else { enemyOrders[0] = 1; for (int i = 1; i < 4; i++) enemyOrders[i] = 0; }
    }
    /// b.Z() 5409：武将的棋子离盘之后，定这一场怎么收。
    ///   体力 ≤ 0（被打光的）→ 阵亡；体力还在（下了撤退令走出边界的）→ 溃走，
    ///   不过 80% 还要在退路上挨一记 2~13，扣死了照样算阵亡。
    void RoutCheck()
    {
        if (Cc != 0 || mine == null || theirs == null) return;
        var g = mine[0];
        if (!g.Alive || g.Off)
        {
            if (me.Hp <= 0) { Cc = 2; return; }
            Cc = 6;
            if (rng.Next(10) > 1)
            {
                int cf = rng.Next(12) + 2;
                if (cf >= me.Hp) { me.Hp = 0; Cc = 2; } else me.Hp -= cf;
            }
            return;
        }
        var f = theirs[0];
        if (!f.Alive || f.Off)
        {
            if (foe.Hp <= 0) { Cc = 1; return; }
            Cc = 5;
            if (rng.Next(10) > 1)
            {
                int cf = rng.Next(12) + 2;
                if (cf >= foe.Hp) { foe.Hp = 0; Cc = 1; } else foe.Hp -= cf;
            }
        }
    }
    string CcText()
    {
        switch (Cc)
        {
            case 1: return foe.Name + " 战死";
            case 2: return me.Name  + " 战死";
            case 3: return foe.Name + " 被擒";
            case 4: return me.Name  + " 被擒";
            case 5: return foe.Name + " 溃走";
            default: return me.Name + " 溃走";
        }
    }

    /// b.Y() 5363：我方轮完一圈查一次围捕。敌将体力 ≤ 40 且忠诚 ≤ 99 才够条件，
    /// 四邻被我方兵棋堵住几面就是几成把握——四面 100 / 三面 90 / 两面 70 / 一面 50，
    /// 再按他的武力和忠诚加减：硬骨头难擒，本来就三心二意的反倒好擒。
    bool CaptureCheck()
    {
        var g = theirs[0];
        if (!g.Alive || g.Off) return false;
        if (foe.Hp > 40 || foe.Loyal > 99) return false;
        int n = 0;
        for (int d = 0; d < 4; d++)
            if ((Cell(g.Col + DX[d], g.Row + DY[d]) & 0x40) != 0) n++;
        int p = n == 4 ? 100 : n == 3 ? 90 : n == 2 ? 70 : n == 1 ? 50 : 0;
        if (p == 0) return false;
        if (foe.Might > 95 || foe.Loyal > 95) p -= 30;
        else if (foe.Might >= 90 || foe.Loyal >= 90) p -= 20;
        else if (foe.Might >= 80 || foe.Loyal >= 80) p -= 10;
        else if (foe.Loyal < 15) p += 40;
        else if (foe.Loyal < 35) p += 20;
        else if (foe.Loyal < 50) p += 10;
        return rng.Next(100) < p;
    }

    /// b.bZ() 11353：敌方也会拿点数上能力（bO = 敌将智力 × 13 / 100）。
    ///   有 7 点：某个弓兵正好在我方某枚棋子右边 5~6 格 → 连弩（射程 6，三回合）
    ///   有 8 点：同样的站位但对面也得是弓兵才上连弩；否则只要有谁贴上了我方 → 呐喊（四回合）
    void EnemyAbility()
    {
        if (enemyPts < 7) return;
        bool Look(bool needGong)
        {
            for (int i = 1; i < theirs.Count; i++)
            {
                var p = theirs[i];
                if (!p.Alive || p.Off || p.Arm != Arm.Gong) continue;
                for (int k = 5; k < 7; k++)
                {
                    int x = p.Col - k, y = p.Row;
                    if (x < 0) break;
                    foreach (var q in mine)
                        if (q.Alive && !q.Off && q.Col == x && q.Row == y && (!needGong || q.Arm == Arm.Gong))
                            return true;
                }
            }
            return false;
        }
        if (enemyPts < 8) { if (Look(false)) { enemyPts -= 7; ce = 0x33; } return; }
        if (Look(true)) { enemyPts -= 7; ce = 0x33; return; }
        foreach (var p in theirs)
        {
            if (!p.Alive || p.Off) continue;
            for (int d = 0; d < 4; d++)
                if ((Cell(p.Col + DX[d], p.Row + DY[d]) & 0x40) != 0) { enemyPts -= 8; ce = 0x44; return; }
        }
    }

    /// 一边的兵棋全都出了场（撤退走光）→ 这场也就散了
    static bool AllOff(List<Piece> ps)
    {
        bool any = false;
        foreach (var p in ps) { if (!p.Alive) continue; if (!p.Off) return false; any = true; }
        return any;
    }

    static bool Occupied(List<Piece> ps, int x, int y)
    {
        foreach (var p in ps) if (p.Alive && !p.Off && p.Col == x && p.Row == y) return true;
        return false;
    }

    void UseSkill(int i)
    {
        if (BattleRules.SkillCost[i] > plotPts) return;
        plotPts -= BattleRules.SkillCost[i];              // b.a(byte)：先扣点，再看成不成
        orders[0] = 0;                                    // 下完计策，武将回到前进
        switch (i)
        {
            case 0:                                       // 咒缚：定住敌将四回合
                if (BattleRules.Bind(me, foe, rng)) cd = BattleRules.CdOf(0);
                break;
            case 1:                                       // 挑战：请对方单挑
                if (BattleRules.Challenge(me, foe, rng)) { Redraw(); OnDuel?.Invoke(); return; }
                break;
            case 5: Blaze(); break;                       // 爆炎：一次性范围伤害
            default: cd = BattleRules.CdOf(i); break;     // 连弩/呐喊/火矢：设增益
        }
        Redraw();
    }

    /// b.W()：以我方武将为中心，向前 3 列 × 上下各 1 行
    float blazeAt = -1e9f; readonly List<Vector2Int> blazeCells = new List<Vector2Int>();
    void Blaze()
    {
        Piece g = mine.Find(p => p.Arm == Arm.None && p.Alive);
        if (g.Troops == 0 && !g.Alive) return;
        blazeAt = Time.time; blazeCells.Clear();
        for (int dx = 1; dx <= BattleRules.BlazeCols; dx++)
            for (int dy = -BattleRules.BlazeRows; dy <= BattleRules.BlazeRows; dy++)
            {
                int x = g.Col + dx, y = g.Row + dy;
                if (x >= 0 && x < BattleRules.GridW && y >= 0 && y < BattleRules.GridH)
                    blazeCells.Add(new Vector2Int(x, y));           // 整片范围都起火，不只有站人的格
                for (int k = 0; k < theirs.Count; k++)
                {
                    var q = theirs[k];
                    if (!q.Alive || q.Col != x || q.Row != y) continue;
                    if (q.Arm == Arm.None)
                    {
                        foe.Hp = Mathf.Max(0, foe.Hp - BattleRules.BlazeHpDamage(rng));
                        if (foe.Hp <= 0) q.Alive = false;      // 原版 5291：体力归零，武将棋子一样离盘
                    }
                    else
                    {
                        q.Troops -= BattleRules.BlazeTroopDamage(rng);
                        if (q.Troops <= 0) { q.Troops = 0; q.Alive = false; }
                    }
                    q.HitAt = Time.time; q.HitArm = 4; theirs[k] = q;
                }
            }
    }

    void Finish()
    {
        me.Troops  = Formation.TotalTroops(mine   ?? new List<Piece>());
        foe.Troops = Formation.TotalTroops(theirs ?? new List<Piece>());
        Killed = Mathf.Max(0, troops0Foe - foe.Troops);        // b.aa() 5463：歼敌数进计策点
        Lost   = Mathf.Max(0, troops0Me  - me.Troops);
        OnDone?.Invoke(me.Troops, foe.Troops);
    }

    // lib306 是 48×16 的三块，拿来当墙体 / 门 / 角楼
    static Texture2D wallTex; static readonly Dictionary<int, Sprite> wallCache = new Dictionary<int, Sprite>();
    /// 城墙一格。新素材在 battle/clashground/cg_wall_a|wall_b|gate_v|gate_h.png：
    /// 墙体按 (x+y) 奇偶两式交替铺，免得一整条墙像复制粘贴。没有就退回 lib306。
    static Sprite WallTile(int v, int x, int y)
    {
        string k = v == 32 ? ((x + y) % 2 == 0 ? "wall_a" : "wall_b") : v == 2 ? "gate_v" : "gate_h";
        var rel = Path.Combine("clashground", "cg_" + k + ".png");
        if (File.Exists(Path.Combine(ArtDir, "battle", rel)))
        {
            if (wallCache.TryGetValue(k.GetHashCode(), out var ns)) return ns;
            var t = Tex(rel);
            ns = Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f), 100);
            wallCache[k.GetHashCode()] = ns; return ns;
        }
        if (wallCache.TryGetValue(v, out var sp)) return sp;
        if (wallTex == null) wallTex = Tex("orig/lib306.png");
        // 原版 c.g(byte)：32→0(墙体) 2→1(竖栅) 16→2(横栅)。
        // 我原先把 2 和 16 写反了。
        int i = v == 32 ? 0 : v == 2 ? 1 : 2;
        sp = Sprite.Create(wallTex, new Rect(i * 16, 0, 16, 16), new Vector2(.5f, .5f), 100);
        wallCache[v] = sp; return sp;
    }

    // lib56 的 12 列不是动作帧，是 **3 组朝向 × 4 兵种**。照抄 c.a(Graphics,byte,byte,int)：
    //   朝上 → 列 = 兵种 + 4     朝下 → 列 = 兵种 + 8
    //   朝右 → 列 = 兵种         朝左 → 同一列水平镜像
    // 兵种编号就是 bU：0 武将 1 骑兵 2 弓兵 3 步兵；
    // 行 = 阵营，**上排(红)是我方、下排(蓝)是敌方**——和战棋层反过来，原版就这样。
    // 新素材在 battle/clash/：c_{red|blue}_{jiang|qi|gong|bu}_{e|n|s}.png，64×64。
    // 红＝我方、蓝＝敌方，和上面 lib56 的排法对齐；朝左仍旧是朝右那张镜像，没单独出图。
    static readonly string[] ArmKey = { "jiang", "qi", "gong", "bu" };
    static Texture2D fig; static readonly Dictionary<string, Sprite> figCache = new Dictionary<string, Sprite>();
    static Sprite Soldier(Arm a, int dir, bool ours)
    {
        var key = (int)a + "_" + dir + (ours ? "o" : "e");
        if (figCache.TryGetValue(key, out var sp)) return sp;

        var rel = Path.Combine("clash",
            "c_" + (ours ? "red" : "blue") + "_" + ArmKey[(int)a] + "_"
            + (dir == 0 ? "n" : dir == 1 ? "s" : "e") + ".png");
        if (File.Exists(Path.Combine(ArtDir, "battle", rel)))
        {
            var t = Tex(rel);
            sp = Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f), 100);
        }
        else                                    // 还没做新素材的，退回原版 lib56
        {
            int col = (int)a + (dir == 0 ? 4 : dir == 1 ? 8 : 0);
            if (fig == null) fig = Tex("orig/lib56.png");
            sp = Sprite.Create(fig, new Rect(col * 16, ours ? 16 : 0, 16, 16), new Vector2(.5f, .5f), 100);
        }
        figCache[key] = sp; return sp;
    }
    struct Shot { public int X0, Y0, X1, Y1; public float At; public bool Fire; public Image Im, Halo; }
    readonly List<Shot> shots = new List<Shot>();
    static Texture2D arrowTex; static Sprite arrowSp;
    static Sprite Arrow()
    {
        if (arrowSp != null) return arrowSp;
        arrowTex = Tex("orig/lib63.png");
        arrowSp = Sprite.Create(arrowTex, new Rect(0, 0, 16, 16), new Vector2(.5f, .5f), 100);
        return arrowSp;
    }

    // 被击中的爆点
    // ── 特效 ───────────────────────────────────────────────────────────
    // 一种特效四帧，文件 clashground/cg_fx_<key>_f<n>.png。
    // 命中 / 爆炎是一次性的（0.11 秒一帧、播完消失），咒缚 / 呐喊是循环的、
    // 效果持续期间一直在脚下转。Redraw 一秒一次会把它们重建，帧序按全局时间算，重建也不跳。
    const float FX_FRAME = 0.11f, FX_HIT_LIFE = 0.46f;
    static readonly Dictionary<string, Sprite> fxCache = new Dictionary<string, Sprite>();
    static Sprite FxSprite(string key, int f)
    {
        string k = key + "_" + f;
        if (fxCache.TryGetValue(k, out var sp)) return sp;
        var rel = Path.Combine("clashground", "cg_fx_" + key + "_f" + f + ".png");
        if (!File.Exists(Path.Combine(ArtDir, "battle", rel))) return fxCache[k] = null;
        var t = Tex(rel);
        return fxCache[k] = Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f), 100);
    }
    class Fx { public Image Im; public string Key; public float T0; public bool Loop; }
    readonly List<Fx> fxList = new List<Fx>();
    /// 在 (col,row) 那格压一个特效；scale 是相对格子的倍数。没素材返回 false。
    bool AddFx(string key, int col, int row, float scale, bool loop, float t0, float dy = 0)
    {
        if (FxSprite(key, 0) == null) return false;
        var im = Node("fx", gridRoot).gameObject.AddComponent<Image>();
        float sz = C * scale, off = (sz - C) / 2f;
        Place((RectTransform)im.transform, col * C - off, row * C - off + dy, sz, sz);
        im.raycastTarget = false; tmp.Add(im.gameObject);
        var fx = new Fx { Im = im, Key = key, T0 = t0, Loop = loop };
        fxList.Add(fx); ApplyFx(fx);
        return true;
    }
    void ApplyFx(Fx fx)
    {
        float t = Time.time - fx.T0;
        int f = fx.Loop ? (int)(t / (FX_FRAME * 1.6f)) % 4 : Mathf.Min(3, (int)(t / FX_FRAME));
        var sp = FxSprite(fx.Key, f);
        if (sp != null && fx.Im.sprite != sp) fx.Im.sprite = sp;
        if (!fx.Loop && t > FX_HIT_LIFE) fx.Im.enabled = false;
    }
    void FxTick()
    {
        for (int i = fxList.Count - 1; i >= 0; i--)
        {
            if (fxList[i].Im == null) { fxList.RemoveAt(i); continue; }
            ApplyFx(fxList[i]);
        }
    }

    static Texture2D hitTex; static Sprite hitSp;
    static Sprite Hit()
    {
        if (hitSp != null) return hitSp;
        hitTex = Tex("orig/lib58.png");
        hitSp = Whole(hitTex); return hitSp;
    }

    readonly List<GameObject> tmp = new List<GameObject>();
    void Redraw()
    {
        foreach (var g in tmp) Object.Destroy(g);
        tmp.Clear(); fxList.Clear();

        foreach (var kv in walls)                            // 城墙
        {
            var im = Node("w", gridRoot).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, kv.Key.x * C, kv.Key.y * C, C, C);
            im.sprite = WallTile(kv.Value, kv.Key.x, kv.Key.y);
            im.raycastTarget = false; tmp.Add(im.gameObject);
        }
        // 呐喊：我方全军脚下一圈仙气（压在兵底下），效果持续期间一直转
        if (BattleRules.BuffId(cd) == 4)
            foreach (var p in mine) if (p.Alive && !p.Off) AddFx("shout", p.Col, p.Row, 1.5f, true, 0f, C * .18f);
        DrawSide(mine,   true);
        DrawSide(theirs, false);
        // 咒缚：敌将脚下钻出绳索（压在兵上面），效果持续期间一直在
        if (BattleRules.BuffId(cd) == 1 && theirs.Count > 0 && theirs[0].Alive)
            AddFx("bind", theirs[0].Col, theirs[0].Row, 1.3f, true, 0f, C * .12f);
        // 爆炎：整片范围腾起火柱，一次性
        if (Time.time - blazeAt < FX_HIT_LIFE)
            foreach (var c in blazeCells) AddFx("blaze", c.x, c.y, 1.5f, false, blazeAt);

        // 箭不在这儿画了——Redraw 一秒一次，箭要一格一格地飞，走 ShotTick 每帧摆位置

        var a = Formation.ByArm(mine,   me.Hp);
        var b = Formation.ByArm(theirs, foe.Hp);
        for (int i = 0; i < 4; i++) { armCount[i].text = a[i].ToString(); foeCount[i].text = b[i].ToString(); }
        int bid = BattleRules.BuffId(cd);
        buffT.text = "计策点 " + plotPts
                   + (bid > 0 ? "   " + BattleRules.SkillName[bid - 1] + " 余 " + (cd & 0xF) : "");
        dayT.text = (over ?? (cmdPop != null ? "暂停" : "交战中")) + (siege ? "   · 攻城战" : "   · 野战");
        DrawMinimap();
    }

    // ── 小地图 ─────────────────────────────────────────────────────────
    // 原版 c.a(Graphics, byte, byte)：每枚棋子除了画小兵图，还往小地图点一个点——
    //   我方 0xFFFFFF，敌方 0xF85A52；武将 fillRect 3×3 实心，其余 drawRect 2×2 空心。
    // 原版一格 3 像素，我们放大到 14，顺带加两样原版没有的：
    // 视窗框（原版整屏就是视窗，不需要）和点一下跳过去。
    RectTransform mapRoot; RectTransform mapView;
    readonly List<GameObject> mapTmp = new List<GameObject>();

    /// 一个头像：洞里放脸，框压在上面，底下写名字。
    void PortraitAt(Transform p, float x, int id, string name, Color32 c, int W = 68, float y0 = 6)
    {
        var m = Metric("portrait");
        var frame = Ui("portrait");
        int fw = m != null && m.Length >= 6 ? m[0] : 192, fh = m != null && m.Length >= 6 ? m[1] : 188;
        float k = W / (float)fw;
        int h = Mathf.RoundToInt(fh * k);

        var av = Avatar(id);
        if (av != null)
        {
            var im = Node("av", p).gameObject.AddComponent<Image>();
            // 没量到洞就退回「整框内缩一圈」，别让脸糊到框外面去
            float hx = m != null && m.Length >= 6 ? m[2] * k : W * .18f;
            float hy = m != null && m.Length >= 6 ? m[3] * k : h * .20f;
            float hw = m != null && m.Length >= 6 ? m[4] * k : W * .64f;
            float hh = m != null && m.Length >= 6 ? m[5] * k : h * .60f;
            Place((RectTransform)im.transform, x + hx, y0 + hy, hw, hh);
            im.sprite = av; im.preserveAspect = true; im.raycastTarget = false;
        }
        if (frame != null)
        {
            var fi = Node("fr", p).gameObject.AddComponent<Image>();
            Place((RectTransform)fi.transform, x, y0, W, h);
            fi.sprite = frame; fi.raycastTarget = false;      // 整框贴，不拉伸
        }
        var n = Label(p, name, W > 80 ? 18 : 15, c, TextAnchor.MiddleCenter);
        Place(n.rectTransform, x - 10, y0 + h + 4, W + 20, 22);
    }

    /// 小地图上那个「大图正看着哪一块」的框。
    /// 缩小到整场能看全时，战场比视窗小，ScrollTo 会把它居中摆——偏移是**负的**；
    /// 直接拿这个负偏移算框的位置，框就顶到小地图外面去了。先算框多大，再夹回小地图里。
    void PlaceMapView()
    {
        if (mapView == null || gridRoot == null) return;
        var clip = (RectTransform)gridRoot.parent;
        float mw = BattleRules.GridW * MM, mh = BattleRules.GridH * MM;
        float w = Mathf.Min(mw, clip.rect.width  / (C * zoom) * MM);
        float h = Mathf.Min(mh, clip.rect.height / (C * zoom) * MM);
        Place(mapView, Mathf.Clamp(-gridRoot.anchoredPosition.x / (C * zoom) * MM, 0, mw - w),
                       Mathf.Clamp( gridRoot.anchoredPosition.y / (C * zoom) * MM, 0, mh - h), w, h);
    }

    void BuildMinimap()
    {
        var panel = Panel(field, "mm", UiPlate());
        int pw = 24 + BattleRules.GridW * MM;
        Place((RectTransform)panel.transform, (refW - pw) / 2, MAP_Y, pw, MAP_H);

        mapRoot = Node("mmg", panel.transform);
        Place(mapRoot, 12, 12, BattleRules.GridW * MM, BattleRules.GridH * MM);
        var bg = mapRoot.gameObject.AddComponent<Image>();
        var lea = Ui("mapbg");
        if (lea != null) { bg.sprite = lea; bg.color = new Color(.62f, .62f, .66f, 1f); }
        else bg.color = new Color(.10f, .11f, .13f, 1f);

        mapView = Node("vp", mapRoot);                       // 大图正看着哪一块
        var vi = mapView.gameObject.AddComponent<Image>();
        vi.sprite = Outline(); vi.type = Image.Type.Sliced;
        vi.color = new Color(1f, .92f, .45f, .85f); vi.raycastTarget = false;

        var trig = mapRoot.gameObject.AddComponent<EventTrigger>();
        var e = new EventTrigger.Entry { eventID = EventTriggerType.PointerClick };
        e.callback.AddListener(d => {                        // 点一下小地图，大图跳过去
            var pd = (PointerEventData)d;
            if (!RectTransformUtility.ScreenPointToLocalPointInRectangle(
                    mapRoot, pd.position, null, out var lp)) return;
            var clip = (RectTransform)gridRoot.parent;
            ScrollTo(new Vector2(-(lp.x / MM * C * zoom - clip.rect.width / 2f),
                                  -lp.y / MM * C * zoom - clip.rect.height / 2f));
        });
        trig.triggers.Add(e);
    }

    /// 点了「计策」之后的二级菜单：六个能力，点数不够的挑不动。
    void OpenSkillCmd(int col, int row)
    {
        int w = 122, rowH = 34, h = 30 + 6 * rowH + 8;
        PlacePop(col, row, w, h);
        var t = Label(cmdPop, "计策 · 余 " + plotPts, 14,
                      new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 7, w, 20);

        for (int i = 0; i < 6; i++)
        {
            int k = i;
            bool ok = BattleRules.SkillCost[i] <= plotPts;
            var b = Node("k" + i, cmdPop);
            Place(b, 8, 30 + i * rowH, w - 16, rowH - 5);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = UiBtn(); bi.type = Image.Type.Sliced;
            bi.color = ok ? Color.white : new Color(.55f, .52f, .48f, 1f);
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.interactable = ok;
            bt.onClick.AddListener(() => { CloseCmd(); UseSkill(k); });
            var l = Label(b, BattleRules.SkillName[i] + " " + BattleRules.SkillCost[i], 14,
                          ok ? new Color32(0xF0,0xE0,0xB8,255) : new Color32(0x86,0x7E,0x6C,255),
                          TextAnchor.MiddleCenter);
            Fill(l.rectTransform);
        }
    }

    /// 两级菜单共用的落位：贴在那枚棋子旁边，右边放不下就翻到左边，上下夹在视窗里。
    void PlacePop(int col, int row, int w, int h)
    {
        // 关旧的放在这儿，两级菜单都走这一条路。
        // 放在 OpenCmd 里的话，绕开它直接开菜单就会把 cmdPop 覆盖掉，
        // 旧的那个成了没人管的孤儿，永远赖在画面上。
        CloseCmd();
        float cx = gridRoot.anchoredPosition.x + col * C * zoom;
        float cy = VIEW_Y + row * C * zoom - gridRoot.anchoredPosition.y;
        float x = cx + C * zoom + 6;
        if (x + w > refW - 6) x = cx - 6 - w;
        x = Mathf.Clamp(x, 6, refW - 6 - w);
        float y = Mathf.Clamp(cy + C * zoom / 2f - h / 2f, VIEW_Y + 4, VIEW_Y + VIEW_H - 4 - h);
        cmdPop = Node("cmd", field);
        Place(cmdPop, x, y, w, h);
        var bg = Panel(cmdPop, "bg", UiPlate()); Fill((RectTransform)bg.transform);
    }

    /// 1 像素描边的空心方框。空心棋子和视窗框都用它。
    static Sprite outlineSp;
    static Sprite Outline()
    {
        if (outlineSp != null) return outlineSp;
        const int S = 8;
        var t = new Texture2D(S, S, TextureFormat.RGBA32, false);
        for (int y = 0; y < S; y++)
            for (int x = 0; x < S; x++)
                t.SetPixel(x, y, x == 0 || y == 0 || x == S - 1 || y == S - 1
                                 ? Color.white : new Color(1, 1, 1, 0));
        t.Apply(); t.filterMode = FilterMode.Point;
        return outlineSp = Sprite.Create(t, new Rect(0, 0, S, S), new Vector2(.5f, .5f),
                                         100, 0, SpriteMeshType.FullRect, new Vector4(2, 2, 2, 2));
    }

    void DrawMinimap()
    {
        foreach (var g in mapTmp) Object.Destroy(g);
        mapTmp.Clear();
        if (mapRoot == null) return;
        MapSide(mine,   new Color32(0xFF, 0xFF, 0xFF, 255));
        MapSide(theirs, new Color32(0xF8, 0x5A, 0x52, 255));
        mapView.SetAsLastSibling();

        var clip = (RectTransform)gridRoot.parent;
        PlaceMapView();
    }

    void MapSide(List<Piece> ps, Color32 c)
    {
        foreach (var p in ps)
        {
            if (!p.Alive || p.Off) continue;
            var im = Node("d", mapRoot).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, p.Col * MM + 2, p.Row * MM + 2, MM - 4, MM - 4);
            // 武将实心、其余空心 —— 原版就是靠这个在小地图上认武将
            if (p.Arm != Arm.None) { im.sprite = Outline(); im.type = Image.Type.Sliced; }
            im.color = c; im.raycastTarget = false;
            mapTmp.Add(im.gameObject);
        }
    }

    // 地面动画：四帧来回播。直接 0123 循环的话第 3 帧接回第 0 帧会跳一下。
    Sprite[] groundFrames; readonly List<Image> groundTiles = new List<Image>();
    static readonly int[] G_SEQ = { 0, 1, 2, 3, 2, 1 };
    const float G_FRAME = 0.28f;
    int gFrame = -1;

    // 原版 o()：箭是一支、一格一格往前挪，不是沿路摆一排。
    // 每格 0.07 秒，射四格约 0.3 秒；到目标格停一下再消失。
    const float SHOT_STEP = 0.07f, SHOT_HOLD = 0.18f;
    static readonly Dictionary<string, Sprite> arrowCache = new Dictionary<string, Sprite>();
    static Sprite ArrowSp(bool fire, float t)
    {
        string k = !fire ? "arrow" : "fire_" + "abc"[(int)(t / 0.08f) % 3];
        if (arrowCache.TryGetValue(k, out var sp)) return sp;
        var rel = Path.Combine("clashground", "cg_" + k + ".png");
        if (File.Exists(Path.Combine(ArtDir, "battle", rel)))
        {
            var tx = Tex(rel);
            sp = Sprite.Create(tx, new Rect(0, 0, tx.width, tx.height), new Vector2(.5f, .5f), 100);
        }
        else sp = Arrow();                                    // 没做新素材就用原版 lib63
        return arrowCache[k] = sp;
    }

    void ShotTick()
    {
        if (gridRoot == null || shots.Count == 0) return;
        for (int i = shots.Count - 1; i >= 0; i--)
        {
            var sh = shots[i];
            int dx = System.Math.Sign(sh.X1 - sh.X0), dy = System.Math.Sign(sh.Y1 - sh.Y0);
            int n = Mathf.Max(Mathf.Abs(sh.X1 - sh.X0), Mathf.Abs(sh.Y1 - sh.Y0));
            float t = Time.time - sh.At;
            int k = Mathf.Min(n, 1 + (int)(t / SHOT_STEP));   // 现在飞到第几格（整格，不插值）
            if (t > n * SHOT_STEP + SHOT_HOLD)
            {
                if (sh.Im != null) Object.Destroy(sh.Im.gameObject);
                if (sh.Halo != null) Object.Destroy(sh.Halo.gameObject);
                shots.RemoveAt(i); continue;
            }
            if (sh.Im == null)
            {
                // 箭杆是褐色的，落在船板、夯土上和底色顺色，看不见。
                // 底下垫一张同图放大一圈的亮色「光晕」，等于给箭描一圈浅边，什么地面上都跳得出来。
                float ang = dy != 0 ? (dy > 0 ? -90 : 90) : (dx < 0 ? 180 : 0);   // 朝右画的，按飞向转
                Image Mk(string n, float size, Color c)
                {
                    var im = Node(n, gridRoot).gameObject.AddComponent<Image>();
                    im.raycastTarget = false; im.color = c;
                    var rt = im.rectTransform;
                    rt.anchorMin = rt.anchorMax = new Vector2(0, 1); rt.pivot = new Vector2(.5f, .5f);
                    rt.sizeDelta = new Vector2(size, size);
                    rt.localEulerAngles = new Vector3(0, 0, ang);
                    return im;
                }
                // 箭画得比格子大一圈：一格 64 里一根 3 像素粗的杆太细，放大到 1.25 格才看得清
                sh.Halo = Mk("arh", C * 1.45f, new Color(1f, .97f, .80f, .85f));
                sh.Im   = Mk("ar",  C * 1.25f, Color.white);
                shots[i] = sh;
            }
            var sp = ArrowSp(sh.Fire, t);
            sh.Im.sprite = sp; sh.Halo.sprite = sp;
            var pos = new Vector2((sh.X0 + dx * k) * C + C / 2f, -((sh.Y0 + dy * k) * C + C / 2f));
            sh.Im.rectTransform.anchoredPosition = pos; sh.Halo.rectTransform.anchoredPosition = pos;
            sh.Halo.transform.SetAsLastSibling(); sh.Im.transform.SetAsLastSibling();   // 压在兵和墙上面
        }
    }

    void GroundTick()
    {
        if (groundFrames == null) return;
        int i = G_SEQ[(int)(Time.time / G_FRAME) % G_SEQ.Length];
        if (i == gFrame) return;
        gFrame = i;
        foreach (var im in groundTiles) im.sprite = groundFrames[i];
    }

    Vector2 dragFrom, gridFrom, camTarget; bool dragged;

    // ── 点兵种下令 ─────────────────────────────────────────────────────
    // 原版的令是**按兵种**下的（b.s 第一行 bY[0][by] = cj[bU[0][by]]），
    // 底下摆一排四个兵种的按钮也对，但场地放大到半屏之后那一排离战况太远。
    // 改成点场上任意一枚我方棋子，就地弹一竖条：标题是它的兵种，下面是那一兵种的令。
    void TapField(PointerEventData d, RectTransform clip)
    {
        CloseCmd();
        if (over != null || mine == null) return;
        if (!RectTransformUtility.ScreenPointToLocalPointInRectangle(
                gridRoot, d.position, null, out var lp)) return;
        int col = Mathf.FloorToInt(lp.x / C), row = Mathf.FloorToInt(-lp.y / C);
        int i = mine.FindIndex(q => q.Alive && q.Col == col && q.Row == row);   // Piece 是结构体
        if (i < 0) return;
        OpenCmd((int)mine[i].Arm, col, row);
    }

    void CloseCmd()
    {
        if (cmdPop != null) Object.Destroy(cmdPop.gameObject);
        cmdPop = null;
    }

    void OpenCmd(int arm, int col, int row)
    {
        int n = OrderCount(arm), w = 106, rowH = 34, h = 30 + n * rowH + 8;
        PlacePop(col, row, w, h);
        var t = Label(cmdPop, ARM[arm], 15, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 7, w, 20);

        for (int i = 0; i < n; i++)
        {
            int k = i;
            var b = Node("c" + i, cmdPop);
            Place(b, 8, 30 + i * rowH, w - 16, rowH - 5);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = UiBtn(); bi.type = Image.Type.Sliced;
            bi.color = orders[arm] == i ? new Color(1f, .92f, .60f, 1f) : Color.white;
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            // 武将那条「计策」不是一道令，是开二级菜单挑具体哪一招
            bt.onClick.AddListener(() => {
                if (arm == 0 && k == 4) { OpenSkillCmd(col, row); return; }
                orders[arm] = k; CloseCmd(); Redraw();
            });
            var l = Label(b, ORDER[i], 15, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);
            Fill(l.rectTransform);
        }
    }

    // ── 缩放：双指捏合（手机）/ 滚轮（桌面），最小缩到整场 16 列都在视窗里 ──
    float zoom = 1f; float pinchD0 = -1f, zoom0 = 1f;
    float MinZoom() { var clip = (RectTransform)gridRoot.parent; return Mathf.Min(1f, clip.rect.width / (BattleRules.GridW * C)); }
    void SetZoom(float z, Vector2 pivotLocal)
    {
        z = Mathf.Clamp(z, MinZoom(), 1f);
        if (Mathf.Approximately(z, zoom)) return;
        // 以手指中点为轴：轴那一点在场地里的坐标缩放前后不变
        var before = gridRoot.anchoredPosition;
        float k = z / zoom;
        var np = new Vector2(pivotLocal.x - (pivotLocal.x - before.x) * k, pivotLocal.y - (pivotLocal.y - before.y) * k);
        zoom = z; gridRoot.localScale = new Vector3(zoom, zoom, 1);
        ScrollTo(np);
    }
    void ZoomTick()
    {
        if (gridRoot == null) return;
        var clip = (RectTransform)gridRoot.parent;
        if (Input.touchCount == 2)
        {
            var a = Input.GetTouch(0); var b = Input.GetTouch(1);
            float d = (a.position - b.position).magnitude;
            RectTransformUtility.ScreenPointToLocalPointInRectangle(clip, (a.position + b.position) / 2f, null, out var mid);
            var pv = new Vector2(mid.x + clip.rect.width / 2f, mid.y - clip.rect.height / 2f);   // 转成 clip 左上原点
            if (pinchD0 < 0) { pinchD0 = d; zoom0 = zoom; }
            else SetZoom(zoom0 * d / pinchD0, pv);
            dragged = true;                                    // 捏合期间不要当成点格子
            return;
        }
        pinchD0 = -1f;
        float wheel = Input.mouseScrollDelta.y;
        if (Mathf.Abs(wheel) > .01f && RectTransformUtility.RectangleContainsScreenPoint(clip, Input.mousePosition))
        {
            RectTransformUtility.ScreenPointToLocalPointInRectangle(clip, Input.mousePosition, null, out var m);
            SetZoom(zoom * (1f + wheel * .08f), new Vector2(m.x + clip.rect.width / 2f, m.y - clip.rect.height / 2f));
        }
    }
    public void ZoomForTest(float z) { SetZoom(z, Vector2.zero); ScrollTo(Vector2.zero); camTarget = gridRoot.anchoredPosition; }

    void ScrollTo(Vector2 p)
    {
        var clip = (RectTransform)gridRoot.parent;
        float cw = BattleRules.GridW * C * zoom, ch = BattleRules.GridH * C * zoom;
        // 缩到比视窗小时居中，别贴左上角留一大块黑
        float x = cw < clip.rect.width  ? (clip.rect.width - cw) / 2f  : Mathf.Clamp(p.x, clip.rect.width - cw, 0);
        float y = ch < clip.rect.height ? -(clip.rect.height - ch) / 2f : Mathf.Clamp(p.y, 0, ch - clip.rect.height);
        gridRoot.anchoredPosition = new Vector2(x, y);
        camTarget = gridRoot.anchoredPosition;
    }

    /// 只看得见半个战场，正在动的那枚要是在视窗外就等于没演。
    /// 但**不能每次都居中**：我方在 1~4 列、敌方在 11~14 列，
    /// 一枚一秒地轮流动，镜头会左右来回甩。留一格余量当死区，
    /// 只在这枚棋滚出视窗时才动，而且只滚刚好够的那一点。
    void FocusOn(int col)
    {
        if (gridRoot == null) return;
        var clip = (RectTransform)gridRoot.parent;
        float vw = clip.rect.width, total = BattleRules.GridW * C * zoom, cz = C * zoom;
        float x = (col * C + C / 2f) * zoom;     // 这枚棋在整场里的横坐标（已缩放）
        float lo = -camTarget.x + cz, hi = -camTarget.x + vw - cz;
        float want = camTarget.x;
        if (x < lo) want = -(x - cz);
        else if (x > hi) want = -(x - vw + cz);
        camTarget = new Vector2(Mathf.Clamp(want, Mathf.Min(0, vw - total), 0), camTarget.y);
    }

    void Glide()
    {
        if (gridRoot == null) return;
        var now = gridRoot.anchoredPosition;
        if ((now - camTarget).sqrMagnitude <= .01f) return;
        gridRoot.anchoredPosition = Vector2.Lerp(now, camTarget, Time.deltaTime * 6f);
        if (mapView == null) return;                    // 镜头在滑，小地图那个框要跟着走
        var clip = (RectTransform)gridRoot.parent;
        PlaceMapView();
    }

    void DrawSide(List<Piece> ps, bool ours)
    {
        foreach (var p in ps)
        {
            if (!p.Alive || p.Off) continue;
            var im = Node("p", gridRoot).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform,
                  p.Col * C - (FIG - C) / 2, p.Row * C - (FIG - C), FIG, FIG);
            im.sprite = Soldier(p.Arm, p.Dir, ours);
            im.raycastTarget = false; tmp.Add(im.gameObject);
            if (p.Dir == 2)                            // 朝左＝把朝右那张水平镜像
            {
                im.rectTransform.localScale = new Vector3(-1, 1, 1);
                im.rectTransform.anchoredPosition += new Vector2(FIG, 0);
            }
            if (Time.time - p.HitAt < FX_HIT_LIFE)     // 刚挨打：按打它的兵种放命中特效
            {
                // 0 武将 / 1 骑兵 都是骑马劈砍；2 弓兵箭着；3 步兵突刺；4 爆炎
                string key = p.HitArm == 2 ? "hit_gong" : p.HitArm == 3 ? "hit_bu" : p.HitArm == 4 ? "blaze" : "hit_qi";
                if (!AddFx(key, p.Col, p.Row, 1.4f, false, p.HitAt))
                {
                    var hx = Node("hit", gridRoot).gameObject.AddComponent<Image>();   // 没特效素材就用原版爆点
                    Place((RectTransform)hx.transform, p.Col * C, p.Row * C - 2, C, C);
                    hx.sprite = Hit(); hx.raycastTarget = false; tmp.Add(hx.gameObject);
                }
            }
            if (ActingSide == (ours ? 0 : 1) && ps.IndexOf(p) == ActingIdx)
            {
                var hl = Node("hl", gridRoot).gameObject.AddComponent<Image>();
                Place((RectTransform)hl.transform,
                      p.Col * C - (FIG - C) / 2, p.Row * C - (FIG - C), FIG, FIG);
                hl.color = new Color(1f, .95f, .3f, .30f); hl.raycastTarget = false;
                tmp.Add(hl.gameObject); hl.transform.SetSiblingIndex(im.transform.GetSiblingIndex());
            }
            // 原版 c.b() 一枚棋子只画三样：小兵图、挨打时的爆点、小地图上的一个点，
            // 棋子上一个字都不写。我早先在这儿压了「将」和兵力数字，是因为当时还用
            // 16×16 的原版小兵图，四个兵种糊成一团分不出谁是谁；
            // 换上新素材后武将有披风红缨偃月刀，认得出来了，标签就是挡脸的。
        }
    }
}
