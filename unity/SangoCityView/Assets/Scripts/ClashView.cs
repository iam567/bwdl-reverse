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

    public ClashView(RectTransform parent, Officer me, Officer foe,
                     int terrain, bool weAttack, float refW, float refH)
    {
        this.refW = refW; this.refH = refH;
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
    void BuildPick()
    {
        pick = Node("pick", Root); Fill(pick);

        var scroll = Node("scroll", pick).gameObject.AddComponent<Image>();
        scroll.sprite = Whole(Tex("orig/lib302.png"));
        Place((RectTransform)scroll.transform, (refW - 352) / 2, 60, 352, 440);
        scroll.preserveAspect = true;

        var t = Label(pick, "战争", 22, new Color32(0xE0,0x50,0x40,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 74, refW, 30);

        var frame = Node("frame", pick).gameObject.AddComponent<Image>();
        frame.sprite = Whole(Tex("orig/lib90.png"));
        Place((RectTransform)frame.transform, (refW - 292) / 2, 168, 292, 224);

        var a1 = Node("a1", pick).gameObject.AddComponent<Image>();
        a1.sprite = Avatar(me.Id); Place((RectTransform)a1.transform, (refW - 292) / 2 + 14, 182, 88, 88);
        var a2 = Node("a2", pick).gameObject.AddComponent<Image>();
        a2.sprite = Avatar(foe.Id); Place((RectTransform)a2.transform, (refW + 292) / 2 - 102, 182, 88, 88);

        var n1 = Label(pick, me.Name, 16, Color.white, TextAnchor.MiddleCenter);
        Place(n1.rectTransform, (refW - 292) / 2 + 96, 186, 100, 24);
        var vs = Label(pick, "VS", 20, new Color32(0xE0,0x40,0x30,255), TextAnchor.MiddleCenter);
        Place(vs.rectTransform, (refW - 292) / 2 + 96, 212, 100, 26);
        var n2 = Label(pick, foe.Name, 16, Color.white, TextAnchor.MiddleCenter);
        Place(n2.rectTransform, (refW - 292) / 2 + 96, 240, 100, 24);

        // lib49 下半排四个 27×27 的阵型示意
        var icons = Tex("orig/lib49.png");
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            var b = Node("f" + i, pick);
            Place(b, (refW - 292) / 2 + 12 + i * 70, 288, 64, 64);
            var im = b.gameObject.AddComponent<Image>();
            im.sprite = Sprite.Create(icons, new Rect(2 + i * 31, 1, 27, 27), new Vector2(.5f, .5f), 100);
            var btn = b.gameObject.AddComponent<Button>(); btn.transition = Selectable.Transition.None;
            btn.onClick.AddListener(() => { formation = k; MarkPick(); });
        }
        markRoot = Node("mark", pick); Fill(markRoot);   // 不铺满的话 Place 的左上角坐标系对不上

        var title = Label(pick, "阵型选择", 18, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(title.rectTransform, 0, 452, refW, 28);

        var go = Node("go", pick);
        Place(go, refW / 2 - 80, 510, 160, 44);
        var gi = go.gameObject.AddComponent<Image>(); gi.sprite = Frame(); gi.type = Image.Type.Sliced;
        var gb = go.gameObject.AddComponent<Button>(); gb.transition = Selectable.Transition.None;
        gb.onClick.AddListener(StartClash);
        var gl = Label(go, "出阵", 17, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);
        Fill(gl.rectTransform);

        var hint = Label(pick, siege ? "守方在城中——攻城的布阵和野战不一样" :
                                       "我方阵型你选，敌方每战随机取一种", 12,
                         new Color32(0xB8,0xB0,0x98,255), TextAnchor.MiddleCenter);
        Place(hint.rectTransform, 0, 562, refW, 20);
        MarkPick();
    }

    RectTransform markRoot; GameObject markObj;
    void MarkPick()
    {
        if (markObj != null) Object.Destroy(markObj);
        var im = Node("m", markRoot).gameObject.AddComponent<Image>();
        Place((RectTransform)im.transform, (refW - 292) / 2 + 12 + formation * 70, 284, 64, 72);
        im.sprite = Frame(); im.type = Image.Type.Sliced;
        im.color = new Color(1f, .85f, .25f, .9f); im.raycastTarget = false;
        markObj = im.gameObject;
    }

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
    public void AdvanceForTest(int n = 120) { CloseCmd(); for (int i = 0; i < n; i++) { tickAt = 0; Tick(); } }
    public void FinishForTest() { Finish(); }

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
    static int VIEW_H => BattleRules.GridH * C;   // 7 × 64 = 448，占竖屏的五成五
    // 下面三栏挨着场地往下排。写死绝对 Y 的话，C 一改就会撞在一起。
    static int INFO_Y  => VIEW_Y + VIEW_H + 14;
    static int MAP_Y   => INFO_Y + 124 + 10;   // 小地图。底下原来是指令栏和计策栏，都拆了
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
    int plotPts;                           // 计策点 Q[]
    int roundNo;
    float overAt;                          // 分出胜负那一刻，用来延一小会儿再收场
    public int ActingSide = -1, ActingIdx = -1;
    string over;

    public void Tick()
    {
        Glide();                                  // 镜头每帧都动，别卡在下面的节拍判断后面
        GroundTick();
        if (mine == null) return;                 // 还在阵型选择那屏，兵还没摆
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

            // 原版是先 s(cD) 再判断切边，用的是上一轮留下的 cF
            if (idx == 0 && extra >= 1)
            {
                extra = 0;
                turnSide = 1 - turnSide;
                var ns = turnSide == 0 ? mine : theirs;
                turnIdx = ns.Count > 1 ? 1 : 0;              // cD = bI > 1 ? 1 : 0
                if (turnSide == 0 && cd != 0)                // 我方又轮了一整圈
                {
                    cd = (cd & 0xF0) | Mathf.Max(0, (cd & 0xF) - 1);
                    if ((cd & 0xF) == 0) cd = 0;
                }
                if (did) { ActingSide = 1 - turnSide; ActingIdx = idx; FocusOn(p.Col); break; }
                continue;
            }

            // if (!cb[cD] || bU[cD] >= 2 || cF >= 1) { cD++; cF = 0; } else cF++;
            // bU >= 2 是弓兵和步兵 —— 只有武将(0)和骑兵(1)能动第二次。
            bool twice = p.Alive && (p.Arm == Arm.None || p.Arm == Arm.Qi);
            if (!p.Alive || !twice || extra >= 1) { turnIdx++; extra = 0; }
            else extra++;

            if (did) { ActingSide = turnSide; ActingIdx = idx; FocusOn(p.Col); break; }
        }

        if (Formation.TotalTroops(theirs) == 0 || foe.Hp <= 0) over = "敌军溃败";
        else if (Formation.TotalTroops(mine) == 0 || me.Hp <= 0) over = "我军溃败";
        Redraw();
    }

    /// b.s(by)：一枚兵棋的一次行动。返回它是不是真做成了事（原版的 cH）。
    bool Act(List<Piece> side, List<Piece> other, bool ours, int i)
    {
        var p = side[i];
        var self  = ours ? me : foe;
        var enemy = ours ? foe : me;
        // 轮到它了才读令。敌方一律前进。
        int cmd = ours ? orders[(int)p.Arm] : 0;
        // 咒缚（cd 高 4 位 = 1）：敌方**武将本人**改走 b.v()——只打相邻的，不移动
        bool bound = !ours && p.Arm == Arm.None && BattleRules.BuffId(cd) == 1;
        if (cmd == 1) return false;                            // 待机：原地不动，不占节拍

        // b.m(by) 按兵种分派：武将/骑兵/步兵 走近战 m(by,by2)，弓兵走远射 o(by,by2)。
        // o() 沿上下左右四条直线各扫 by3 = 1..by2-1 格，默认 by2 = 5 → 射程 4 格。
        int t = p.Arm == Arm.Gong
              ? FindInRange(other, p, BattleRules.ArrowRange(ours ? cd : 0))
              : FindAdjacent(other, p);
        if (t >= 0 && cmd != 2)                                // 撤退的不恋战
        {
            var q = other[t];
            int dmg = BattleRules.Damage(BattleRules.Atk(self, p.Arm),
                                         BattleRules.Def(enemy), p.Troops, rng,
                                         ours ? cd : 0, p.Arm);
            // b.c(boolean)：打人的和挨打的都是武将本人 → aq=1，跳出去单挑
            // 双方都站着才打得起来；有一方体力已空就不进单挑了，直接算伤害
            if (p.Arm == Arm.None && q.Arm == Arm.None && me.Hp > 0 && foe.Hp > 0)
            { OnDuel?.Invoke(); return true; }
            if (p.Arm == Arm.Gong)                             // 射出去的箭，画一下
                shots.Add(new Shot { X0 = p.Col, Y0 = p.Row, X1 = q.Col, Y1 = q.Row, At = Time.time });
            if (q.Arm == Arm.None)
            {
                if (ours) foe.Hp = Mathf.Max(0, foe.Hp - BattleRules.HpHit(dmg));
                else      me.Hp  = Mathf.Max(0, me.Hp  - BattleRules.HpHit(dmg));
                q.HitAt = Time.time; other[t] = q;
            }
            else
            {
                q.Troops -= dmg;
                q.HitAt = Time.time;
                if (q.Troops <= 0) { q.Troops = 0; q.Alive = false; }
                other[t] = q;
            }
            return true;
        }

        if (bound) return false;                               // 被缚：四周无人就干站着
        var tgt = Nearest(other, p);
        if (tgt.x < 0) return false;
        int dir = cmd == 2 ? -1 : 1;                           // 撤退就反着走
        int dx = dir * System.Math.Sign(tgt.x - p.Col);
        int dy = dir * System.Math.Sign(tgt.y - p.Row);

        // 原版 b.l(byte) 的选向：先横后纵，**首选被挡住或已经对齐时往垂直方向侧移**，
        // 两边都通就掷一次。侧移这一步才是部队会沿走廊绕行、从门挤进去的原因；
        // 我原先写的是「主轴迈一格，撞墙就原地不动」，攻城时全堵在墙外。
        int nx = p.Col, ny = p.Row;
        if (cmd == 3)                                          // 包围：先拉开纵向再压上
        {
            ny += tgt.y >= p.Row ? -1 : 1;
            if (!Free(nx, ny)) { ny = p.Row; nx = p.Col + dx; }
        }
        else if (dx != 0 && Free(p.Col + dx, p.Row)) nx += dx;
        else if (dy != 0 && Free(p.Col, p.Row + dy)) ny += dy;
        else if (dx == 0)                                      // 同列：左右绕
        {
            bool l = Free(p.Col - 1, p.Row), r = Free(p.Col + 1, p.Row);
            if (l && r) nx += rng.Next(2) == 0 ? -1 : 1;
            else if (r) nx += 1;
            else if (l) nx -= 1;
        }
        else if (dy == 0)                                      // 同行：上下绕
        {
            bool u = Free(p.Col, p.Row - 1), d = Free(p.Col, p.Row + 1);
            if (u && d) ny += rng.Next(2) == 0 ? -1 : 1;
            else if (d) ny += 1;
            else if (u) ny -= 1;
        }

        if (nx == p.Col && ny == p.Row) return false;
        if (!Free(nx, ny)) return false;
        // bX 跟着走向更新：b.c/d/e/f 分别置 0 上 / 1 下 / 2 左 / 3 右
        if (nx != p.Col) p.Dir = nx > p.Col ? 3 : 2;
        else if (ny != p.Row) p.Dir = ny > p.Row ? 1 : 0;
        p.Col = nx; p.Row = ny; side[i] = p;
        return true;
    }

    /// 这一格能不能站人。原版判的是 (bK[r][c] & 0x60) == 0 —— 0x60 = 0x20|0x40，
    /// 也就是**只有 32（墙体）挡路**，2（竖栅门）和 16（横栅）是能走过去的。
    /// 我原先把整张 walls 表都当成墙，门也封死，部队根本进不了城。
    bool Free(int x, int y)
    {
        if (x < 0 || y < 0 || x >= BattleRules.GridW || y >= BattleRules.GridH) return false;
        if (walls.TryGetValue(new Vector2Int(x, y), out int v) && v == 32) return false;
        return !Occupied(mine, x, y) && !Occupied(theirs, x, y);
    }

    static bool Occupied(List<Piece> ps, int x, int y)
    {
        foreach (var p in ps) if (p.Alive && p.Col == x && p.Row == y) return true;
        return false;
    }
    /// o()：沿上下左右四条直线扫，碰到的第一个敌人就是目标
    static int FindInRange(List<Piece> other, Piece p, int range)
    {
        int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
        for (int d = 0; d < 4; d++)
            for (int k = 1; k <= range; k++)
            {
                int x = p.Col + dx[d] * k, y = p.Row + dy[d] * k;
                for (int i = 0; i < other.Count; i++)
                    if (other[i].Alive && other[i].Col == x && other[i].Row == y) return i;
            }
        return -1;
    }

    static int FindAdjacent(List<Piece> other, Piece p)
    {
        for (int i = 0; i < other.Count; i++)
        {
            var q = other[i];
            if (q.Alive && Mathf.Abs(q.Col - p.Col) + Mathf.Abs(q.Row - p.Row) == 1) return i;
        }
        return -1;
    }
    static Vector2Int Nearest(List<Piece> other, Piece p)
    {
        var best = new Vector2Int(-1, -1); int bd = 999;
        foreach (var q in other)
        {
            if (!q.Alive) continue;
            int d = Mathf.Abs(q.Col - p.Col) + Mathf.Abs(q.Row - p.Row);
            if (d < bd) { bd = d; best = new Vector2Int(q.Col, q.Row); }
        }
        return best;
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
    void Blaze()
    {
        Piece g = mine.Find(p => p.Arm == Arm.None && p.Alive);
        if (g.Troops == 0 && !g.Alive) return;
        for (int dx = 1; dx <= BattleRules.BlazeCols; dx++)
            for (int dy = -BattleRules.BlazeRows; dy <= BattleRules.BlazeRows; dy++)
            {
                int x = g.Col + dx, y = g.Row + dy;
                for (int k = 0; k < theirs.Count; k++)
                {
                    var q = theirs[k];
                    if (!q.Alive || q.Col != x || q.Row != y) continue;
                    if (q.Arm == Arm.None)
                        foe.Hp = Mathf.Max(0, foe.Hp - BattleRules.BlazeHpDamage(rng));
                    else
                    {
                        q.Troops -= BattleRules.BlazeTroopDamage(rng);
                        if (q.Troops <= 0) { q.Troops = 0; q.Alive = false; }
                    }
                    q.HitAt = Time.time; theirs[k] = q;
                }
            }
    }

    void Finish()
    {
        me.Troops  = Formation.TotalTroops(mine   ?? new List<Piece>());
        foe.Troops = Formation.TotalTroops(theirs ?? new List<Piece>());
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
    struct Shot { public int X0, Y0, X1, Y1; public float At; }
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
        tmp.Clear();

        foreach (var kv in walls)                            // 城墙
        {
            var im = Node("w", gridRoot).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, kv.Key.x * C, kv.Key.y * C, C, C);
            im.sprite = WallTile(kv.Value, kv.Key.x, kv.Key.y);
            im.raycastTarget = false; tmp.Add(im.gameObject);
        }
        DrawSide(mine,   true);
        DrawSide(theirs, false);

        shots.RemoveAll(sh => Time.time - sh.At > .5f);
        foreach (var sh in shots)                            // 箭：从弓兵连到目标
        {
            int dx = System.Math.Sign(sh.X1 - sh.X0), dy = System.Math.Sign(sh.Y1 - sh.Y0);
            int n = Mathf.Max(Mathf.Abs(sh.X1 - sh.X0), Mathf.Abs(sh.Y1 - sh.Y0));
            for (int k = 1; k <= n; k++)
            {
                var im = Node("ar", gridRoot).gameObject.AddComponent<Image>();
                Place((RectTransform)im.transform, (sh.X0 + dx * k) * C, (sh.Y0 + dy * k) * C, C, C);
                im.sprite = Arrow(); im.raycastTarget = false;
                if (dy != 0) im.rectTransform.localEulerAngles = new Vector3(0, 0, dy > 0 ? 90 : -90);
                else if (dx < 0) { im.rectTransform.localScale = new Vector3(-1, 1, 1);
                                   im.rectTransform.anchoredPosition += new Vector2(C, 0); }
                tmp.Add(im.gameObject);
            }
        }

        var a = Formation.ByArm(mine,   me.Hp);
        var b = Formation.ByArm(theirs, foe.Hp);
        for (int i = 0; i < 4; i++) { armCount[i].text = a[i].ToString(); foeCount[i].text = b[i].ToString(); }
        int bid = BattleRules.BuffId(cd);
        buffT.text = "计策点 " + plotPts
                   + (bid > 0 ? "   " + BattleRules.SkillName[bid - 1] + " 余 " + (cd & 0xF) : "");
        dayT.text = (over ?? "交战中") + (siege ? "   · 攻城战" : "   · 野战");
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
    void PortraitAt(Transform p, float x, int id, string name, Color32 c)
    {
        const int W = 68;                                    // 框画多大
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
            Place((RectTransform)im.transform, x + hx, 6 + hy, hw, hh);
            im.sprite = av; im.preserveAspect = true; im.raycastTarget = false;
        }
        if (frame != null)
        {
            var fi = Node("fr", p).gameObject.AddComponent<Image>();
            Place((RectTransform)fi.transform, x, 6, W, h);
            fi.sprite = frame; fi.raycastTarget = false;      // 整框贴，不拉伸
        }
        var n = Label(p, name, 15, c, TextAnchor.MiddleCenter);
        Place(n.rectTransform, x - 6, 6 + h + 2, W + 12, 18);
    }

    void BuildMinimap()
    {
        var panel = Panel(field, "mm", UiPlate());
        int pw = 24 + BattleRules.GridW * MM;
        Place((RectTransform)panel.transform, (refW - pw) / 2, MAP_Y,
              pw, 24 + BattleRules.GridH * MM);

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
            ScrollTo(new Vector2(-(lp.x / MM * C - clip.rect.width / 2f),
                                  -lp.y / MM * C - clip.rect.height / 2f));
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
        float cx = gridRoot.anchoredPosition.x + col * C;
        float cy = VIEW_Y + row * C - gridRoot.anchoredPosition.y;
        float x = cx + C + 6;
        if (x + w > refW - 6) x = cx - 6 - w;
        x = Mathf.Clamp(x, 6, refW - 6 - w);
        float y = Mathf.Clamp(cy + C / 2f - h / 2f, VIEW_Y + 4, VIEW_Y + VIEW_H - 4 - h);
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
        Place(mapView, -gridRoot.anchoredPosition.x / C * MM,
                        gridRoot.anchoredPosition.y / C * MM,
                        clip.rect.width / C * MM, clip.rect.height / C * MM);
    }

    void MapSide(List<Piece> ps, Color32 c)
    {
        foreach (var p in ps)
        {
            if (!p.Alive) continue;
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

    void ScrollTo(Vector2 p)
    {
        var clip = (RectTransform)gridRoot.parent;
        gridRoot.anchoredPosition = new Vector2(
            Mathf.Clamp(p.x, Mathf.Min(0, clip.rect.width  - BattleRules.GridW * C), 0),
            Mathf.Clamp(p.y, 0, Mathf.Max(0, BattleRules.GridH * C - clip.rect.height)));
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
        float vw = clip.rect.width, total = BattleRules.GridW * C;
        float x = col * C + C / 2f;              // 这枚棋在整场里的横坐标
        float lo = -camTarget.x + C, hi = -camTarget.x + vw - C;
        float want = camTarget.x;
        if (x < lo) want = -(x - C);
        else if (x > hi) want = -(x - vw + C);
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
        Place(mapView, -gridRoot.anchoredPosition.x / C * MM,
                        gridRoot.anchoredPosition.y / C * MM,
                        clip.rect.width / C * MM, clip.rect.height / C * MM);
    }

    void DrawSide(List<Piece> ps, bool ours)
    {
        foreach (var p in ps)
        {
            if (!p.Alive) continue;
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
            if (Time.time - p.HitAt < .45f)            // 刚挨打，闪一下爆点
            {
                var hx = Node("hit", gridRoot).gameObject.AddComponent<Image>();
                Place((RectTransform)hx.transform, p.Col * C, p.Row * C - 2, C, C);
                hx.sprite = Hit(); hx.raycastTarget = false; tmp.Add(hx.gameObject);
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
