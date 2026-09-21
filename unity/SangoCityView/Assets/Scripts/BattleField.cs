// 战棋层（原版画面 20）。地块用新做的 5 套地域素材，部队用新做的八向精灵。
// 规则照原版：机动力是全军每回合共用的池子，走一格按 b.h(格,兵种) 扣，攻击固定扣 2。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using static UIKit;

public class Unit
{
    public Officer O;
    public int X, Y;
    public bool Ours;
    public bool Acted;          // 这一「日」已经行动过
    public bool Alive = true;
    public string Dir = "s";    // 八向精灵目前只做了 s/se/e/ne/n，朝左靠水平翻转
    public bool Flip;
    public Vector2 Draw;        // 画在哪。向 (X,Y) 插值，走起来是滑过去不是瞬移
    public int Trapped;         // 被困还剩几日（虚兵 / 连环 / 奇门遁甲），0 = 自由
    public bool Cursed;         // 奇门遁甲：被困期间日减 100 兵
}

public class BattleField
{
    public RectTransform Root;
    public System.Action<Unit, Unit, bool> OnClash;   // 我方, 敌方, 我方是不是攻方
    public System.Action OnQuit;

    const int CELL = 44;                            // 一格在屏上多大
    readonly int W, H;
    readonly int[,] grid;
    readonly string theme;
    readonly List<Unit> units = new List<Unit>();
    public List<Unit> Units => units;
    readonly Dictionary<string, Sprite> tileCache = new Dictionary<string, Sprite>();
    readonly Dictionary<string, Sprite> unitCache = new Dictionary<string, Sprite>();

    RectTransform board;          // 棋盘容器，靠 anchoredPosition 滚动
    Image cursorImg;
    Text dayText, mpText, hintText;
    RectTransform cmdBar; Text[] cmdLabels; Image cmdSel;
    RectTransform infoPanel;

    Vector2 dragFrom, boardFrom; bool dragged;
    int curX, curY;               // 光标
    Unit sel;                     // 选中的我方部队
    int mp;                       // 机动力池
    int day = 1;
    string mode = "";             // "" / "move" / "attack" / "info"
    HashSet<int> reach = new HashSet<int>();
    readonly System.Random rng = new System.Random(20260921);
    readonly float refW, refH;

    static readonly string[] CMD = { "移动", "计策", "攻击", "情报", "撤退", "待机" };

    readonly int[] supply;                                  // 我金 我粮 敌金 敌粮
    public BattleField(RectTransform parent, int cityId, string themeKey,
                       List<Unit> roster, float refW, float refH, int[] supply = null)
    {
        this.refW = refW; this.refH = refH; this.supply = supply ?? new int[4];
        theme = themeKey;
        grid = BattleRules.LoadMap(Path.Combine(BattleGame.BattleDir, "maps", cityId + ".map"));
        H = grid.GetLength(0); W = grid.GetLength(1);
        units.AddRange(roster);

        Root = Node("BattleField", parent); Fill(Root);
        BuildBoard();
        BuildHud();
        mp = BattleRules.MoveCap(TotalTroops(true));
        CenterOn(units[0].X, units[0].Y);
        curX = units[0].X; curY = units[0].Y;
        Refresh();
    }

    // ── 搭画面 ─────────────────────────────────────────────────────────
    void BuildBoard()
    {
        var clip = Node("clip", Root);
        Place(clip, 0, 76, refW, refH - 76 - 96);
        var msk = clip.gameObject.AddComponent<Image>();
        msk.color = new Color(.06f, .06f, .07f, 1);
        clip.gameObject.AddComponent<Mask>().showMaskGraphic = true;

        board = Node("board", clip);
        board.anchorMin = board.anchorMax = new Vector2(0, 1);
        board.pivot = new Vector2(0, 1);
        board.sizeDelta = new Vector2(W * CELL, H * CELL);

        // 地形先铺一遍。建筑格（码 1 武库 / 2 城 / 3 粮仓 / 4 药铺）底下铺平地，
        // 建筑本身是透明底的图标，第二遍再放大到 1.3 格压上去——原版的房子图标就是略超出格子的。
        for (int y = 0; y < H; y++)
            for (int x = 0; x < W; x++)
            {
                int code = grid[y, x] & 0xF;
                bool bld = code >= 1 && code <= 4;
                var im = Node("c", board).gameObject.AddComponent<Image>();
                Place((RectTransform)im.transform, x * CELL, y * CELL, CELL, CELL);
                im.sprite = Tile(bld ? 0 : code);
                im.raycastTarget = false;
            }
        const float BLD = 1.3f;
        for (int y = 0; y < H; y++)
            for (int x = 0; x < W; x++)
            {
                int code = grid[y, x] & 0xF;
                if (code < 1 || code > 4) continue;
                var im = Node("b", board).gameObject.AddComponent<Image>();
                float sz = CELL * BLD, off = (sz - CELL) / 2f;
                Place((RectTransform)im.transform, x * CELL - off, y * CELL - off - 4, sz, sz);   // 往上提一点，脚站在格子里
                im.sprite = Tile(code); im.preserveAspect = true;
                im.raycastTarget = false;
            }

        // 棋盘：按下拖动 = 滚地图，没拖动就当点一下格子。
        // 32×19 的战场在手机上一屏放不下，没有滚动的话敌军在屏幕外就找不着。
        msk.raycastTarget = true;        // 遮罩那张 Image 就当接触区，别再加一个
        var trig = clip.gameObject.AddComponent<EventTrigger>();
        void Hook(EventTriggerType t, System.Action<PointerEventData> f)
        {
            var e = new EventTrigger.Entry { eventID = t };
            e.callback.AddListener(d => f((PointerEventData)d));
            trig.triggers.Add(e);
        }
        Hook(EventTriggerType.PointerDown, d => { dragFrom = d.position; boardFrom = board.anchoredPosition; dragged = false; });
        Hook(EventTriggerType.Drag, d => {
            var delta = d.position - dragFrom;
            if (delta.magnitude > 8) dragged = true;
            if (dragged) ScrollTo(boardFrom + new Vector2(delta.x, delta.y));
        });
        Hook(EventTriggerType.PointerUp, d => { if (!dragged) OnBoardClick(d, clip); });

        cursorImg = Node("cursor", board).gameObject.AddComponent<Image>();
        cursorImg.sprite = BattleUi.Get("cursor") ?? CursorSprite();
        cursorImg.raycastTarget = false;
    }

    Image[] cmdImgs;
    void BuildHud()
    {
        // 顶栏：左我方金粮、中间日数与机动力、右敌方金粮；第二行是提示
        var top = Panel(Root, "top", BattleUi.PlateSp());
        Place((RectTransform)top.transform, 8, 8, refW - 16, 66);
        var lab = new Color32(0xC8,0xA8,0x60,255);
        Text Col(float x, float w, string t, int size, Color32 c, TextAnchor a)
        {
            var l = Label(top.transform, t, size, c, a); Place(l.rectTransform, x, 8, w, 22); return l;
        }
        Col(14, 40, "我方", 13, new Color32(0x9C,0xC8,0xFF,255), TextAnchor.MiddleLeft);
        Col(54, 110, "金 " + supply[0] + "  粮 " + supply[1], 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleLeft);
        Col(refW - 16 - 14 - 40, 40, "敌方", 13, new Color32(0xFF,0x98,0x90,255), TextAnchor.MiddleRight);
        Col(refW - 16 - 14 - 40 - 110, 110, "金 " + supply[2] + "  粮 " + supply[3], 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleRight);
        dayText = Col(0, refW - 16, "", 15, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        mpText  = Label(top.transform, "", 13, new Color32(0xF0,0xD0,0x88,255), TextAnchor.MiddleCenter);
        Place(mpText.rectTransform, 0, 30, refW - 16, 18);
        hintText = Label(top.transform, "", 12, new Color32(0xC8,0xC0,0xA8,255), TextAnchor.MiddleCenter);
        Place(hintText.rectTransform, 12, 46, refW - 40, 18);

        cmdBar = Node("cmd", Root);
        Place(cmdBar, 0, refH - 100, refW, 100);
        var bg = Panel(cmdBar, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);

        // 六道令：一排六块木牌按钮，选中的那块换金
        cmdLabels = new Text[CMD.Length]; cmdImgs = new Image[CMD.Length];
        float bw = (refW - 24) / CMD.Length;
        for (int i = 0; i < CMD.Length; i++)
        {
            int k = i;
            var b = Node("b" + i, cmdBar);
            Place(b, 12 + i * bw + 2, 10, bw - 4, 36);
            cmdImgs[i] = b.gameObject.AddComponent<Image>();
            cmdImgs[i].sprite = BattleUi.BtnSp(); cmdImgs[i].type = Image.Type.Sliced;
            var btn = b.gameObject.AddComponent<Button>();
            btn.transition = Selectable.Transition.None;
            btn.onClick.AddListener(() => Command(k));
            cmdLabels[i] = Label(b, CMD[i], 15, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
            Fill(cmdLabels[i].rectTransform);
        }
        cmdSel = Node("sel", cmdBar).gameObject.AddComponent<Image>();   // 旧的高亮块留着占位，不再用
        cmdSel.enabled = false; cmdSel.raycastTarget = false;

        // 下面三枚：铺首大按钮那一套（出阵按钮同款）
        void BigBtn(string txt, float x, float w, System.Action act, float ppu)
        {
            var b = Node("j" + txt, cmdBar);
            Place(b, x, 54, w, 38);
            var i2 = b.gameObject.AddComponent<Image>();
            i2.sprite = BattleUi.Get("go") ?? BattleUi.BtnSp(); i2.type = Image.Type.Sliced;
            // 铺首那张是按 200×56 的出阵按钮量的 border（横 63）。这里按钮只有 100×38，
            // 两枚铺首加起来比按钮还宽，Unity 会把它们撑满整个按钮、字全压在兽面底下。
            // 用 pixelsPerUnitMultiplier 把九宫格的边整体缩小。
            i2.pixelsPerUnitMultiplier = ppu;
            var t2 = b.gameObject.AddComponent<Button>();
            if (BattleUi.Get("go_on") != null)
            {
                t2.transition = Selectable.Transition.SpriteSwap;
                t2.spriteState = new SpriteState { pressedSprite = BattleUi.Get("go_on"), highlightedSprite = BattleUi.Get("go") };
            }
            else t2.transition = Selectable.Transition.None;
            t2.onClick.AddListener(() => act());
            // 标签只在两枚铺首之间的中段居中——铺满整枚按钮的话，字会往兽面底下钻
            var m = BattleUi.Metric("go");
            float inset = (m != null && m.Length > 0 ? m[0] : 0) / ppu + 4;
            var l2 = Label(b, txt, w < 120 ? 13 : 14, new Color32(0xF6,0xE8,0xC0,255), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(l2.rectTransform, inset, 0, w - inset * 2, 38);
            var sh = l2.gameObject.AddComponent<Shadow>(); sh.effectColor = new Color(0, 0, 0, .8f); sh.effectDistance = new Vector2(1, -1);
        }
        BigBtn("看我军", 10, 110, () => JumpTo(true), 3.4f);
        BigBtn("回合结束", refW / 2 - 66, 132, EndDay, 2.2f);
        BigBtn("看敌军", refW - 10 - 110, 110, () => JumpTo(false), 3.4f);
    }

    /// 自检：选中一支我方部队并下一道令（2 = 攻击），好截到目标标记
    /// 自检：选中 u、打开计策菜单（截菜单），再直接对 tgt 放第 plot 种（截结果）
    /// 自检：all 为真时把智力/等级临时顶到 99/8，看 16 种全开的菜单
    public void PlotMenuForTest(Unit u, bool all = false, int hover = 6)
    {
        sel = u; mode = "";
        int i0 = u.O.Int, l0 = u.O.Lv;
        if (all) { u.O.Int = 99; u.O.Lv = 8; }
        OpenPlotMenu(); PlotHover(hover); Refresh();
        u.O.Int = i0; u.O.Lv = l0;
    }
    public void PlotForTest(Unit u, int plot, Unit tgt)
    {
        ClosePlotMenu(); sel = u; plotSel = plot; mode = "plot";
        CenterOn(tgt.X, tgt.Y); curX = tgt.X; curY = tgt.Y; Refresh();
        DoPlot(tgt.X, tgt.Y); Refresh();
    }
    public void InfoForTest(Unit u, Unit tgt) { ClosePlotPop(); sel = u; mode = "info"; ShowInfo(tgt); Refresh(); }   // 先收上一步的计策弹框
    public void CommandForTest(Unit u, int k) { sel = u; mode = ""; Command(k); CenterOn(u.X, u.Y); curX = u.X; curY = u.Y; Refresh(); }

    // ── 素材 ───────────────────────────────────────────────────────────
    // 码 0 平地原本有自己的一格「ground」，和草地（码 12）摆在一起多余，
    // 平地也铺草——只是视觉上合并，行为上 0 和 12 的机动力消耗照旧各算各的。
    static readonly Dictionary<int, string> SLOT = new Dictionary<int, string> {
        {0,"grass"},{1,"wuku"},{2,"castle"},{3,"cang"},{4,"yao"},{5,"ambush"},{6,"water"},
        {7,"rock"},{8,"forest"},{9,"rock"},{10,"forest"},{11,"forest"},{12,"grass"},
    };
    Sprite Tile(int code)
    {
        var slot = SLOT.TryGetValue(code, out var s) ? s : "ground";
        var key = theme + "_" + slot;
        if (!tileCache.TryGetValue(key, out var sp))
        {
            var t = Load(Path.Combine("battle", "tiles", "bt_" + key + ".png"));
            t.filterMode = FilterMode.Point;
            sp = Whole(t); tileCache[key] = sp;
        }
        return sp;
    }
    public Sprite UnitSprite(Unit u)
    {
        string arm = u.O.Kind == Kind.Ping ? "ping" : (u.O.Kind == Kind.Shui ? "shui" : "shan");
        var key = (u.Ours ? "blue" : "red") + "_" + arm + "_" + u.Dir;
        if (!unitCache.TryGetValue(key, out var sp))
        {
            var t = Load(Path.Combine("battle", "units", "u_" + key + ".png"));
            t.filterMode = FilterMode.Point;
            sp = Whole(t); unitCache[key] = sp;
        }
        return sp;
    }
    static Sprite cursorSprite;
    static Sprite CursorSprite()
    {
        if (cursorSprite != null) return cursorSprite;
        const int S = 32;
        var t = new Texture2D(S, S, TextureFormat.RGBA32, false);
        for (int y = 0; y < S; y++)
            for (int x = 0; x < S; x++)
            {
                bool edge = x < 2 || y < 2 || x >= S - 2 || y >= S - 2;
                bool corner = (x < 9 || x >= S - 9) && (y < 9 || y >= S - 9);
                t.SetPixel(x, y, edge && corner ? new Color(1f, .95f, .35f, 1f) : new Color(0, 0, 0, 0));
            }
        t.Apply(); t.filterMode = FilterMode.Point;
        cursorSprite = Sprite.Create(t, new Rect(0, 0, S, S), new Vector2(.5f, .5f), 100);
        return cursorSprite;
    }

    // ── 交互 ───────────────────────────────────────────────────────────
    void OnBoardClick(PointerEventData d, RectTransform clip)
    {
        RectTransformUtility.ScreenPointToLocalPointInRectangle(
            board, d.position, d.pressEventCamera, out var lp);
        int x = Mathf.FloorToInt(lp.x / CELL), y = Mathf.FloorToInt(-lp.y / CELL);
        if (x < 0 || y < 0 || x >= W || y >= H) return;
        curX = x; curY = y;

        var here = At(x, y);
        if (mode == "plot" && sel != null) { DoPlot(x, y); }
        else if (mode == "move" && sel != null && reach.Contains(y * W + x)) { DoMove(x, y); }
        else if (mode == "attack" && sel != null && here != null && !here.Ours && Adjacent(sel, x, y)) { DoAttack(here); }
        else if (mode == "info" && here != null) { ShowInfo(here); }
        else if (here != null && here.Ours && !here.Acted) { sel = here; mode = ""; }
        Refresh();
    }

    void Command(int i)
    {
        if (enemyTurn) { hintText.text = "敌军行动中..."; return; }
        if (sel == null) { hintText.text = "先点一支我方部队"; return; }
        switch (i)
        {
            case 0:                                  // 移动
                mode = "move"; ComputeReach(); break;
            case 1:                                  // 计策：原版 b.d(X) 算出可用几种，弹单选
                mode = ""; OpenPlotMenu(); break;
            case 2:                                  // 攻击
                mode = "attack";
                hintText.text = HasFoeAdjacent(sel) ? "点相邻的敌将" : "邻近无敌将!";
                if (!HasFoeAdjacent(sel)) mode = "";
                break;
            case 3: mode = "info"; hintText.text = "请选择要侦察的武将"; break;   // 原版原话
            case 4:                                  // 撤退
                sel.Alive = false; sel.Acted = true;
                hintText.text = "撤退武将 " + sel.O.Name
                              + "，损失 " + BattleRules.RoutLoss(sel.O.Troops, rng) + " 兵";
                sel = null; mode = ""; break;
            case 5: sel.Acted = true; hintText.text = "我军待机中..."; sel = null; mode = ""; break;
        }
        Refresh();
    }

    void DoMove(int x, int y)
    {
        int cost = costTo.TryGetValue(y * W + x, out var c) ? c : 0;
        mp -= cost;
        int dx = x - sel.X, dy = y - sel.Y;
        sel.Dir = Mathf.Abs(dx) > Mathf.Abs(dy) ? "e" : (dy > 0 ? "s" : "n");
        sel.Flip = dx < 0;
        sel.X = x; sel.Y = y;
        mode = ""; reach.Clear();
        hintText.text = "移动消耗 " + cost + " 点";
        CenterOn(x, y);
    }

    // ── 计策 ───────────────────────────────────────────────────────────
    RectTransform plotMenu; int plotSel = -1, plotHover = -1;
    readonly HashSet<int> ambush = new HashSet<int>();          // 十面埋伏埋下的格（y*W+x）
    Text plotDescT, plotDescTitle; readonly List<Image> plotBtnImgs = new List<Image>();
    void ClosePlotMenu() { if (plotMenu != null) Object.Destroy(plotMenu.gameObject); plotMenu = null; plotHover = -1; plotBtnImgs.Clear(); }

    /// 原版 c.P()：一次一条的走马灯——上半是说明 d.q、下面黄字是当前计策名、确认才施。
    /// 触屏改成 4×4 全列出来，但两段式保留：第一下选中并显示说明，再点同一条才进选落点。
    void OpenPlotMenu()
    {
        ClosePlotMenu();
        int n = BattleRules.PlotCount(sel.O);
        const int COLS = 4, ROW = 34, DESC = 74; int rows = (n + COLS - 1) / COLS;
        float w = refW - 20, h = 34 + rows * ROW + 6 + DESC + 8;
        plotMenu = Node("plot", Root);
        Place(plotMenu, 10, refH - 100 - h - 8, w, h);
        var bg = Panel(plotMenu, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        var t = Label(plotMenu, "计策 · 机动力 " + mp + " · " + n + " 种可用", 14, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 7, w, 22);
        float bw = (w - 16) / COLS;
        for (int i = 0; i < n; i++)
        {
            int k = i, cost = BattleRules.CostOfPlot(i);
            bool ok = cost <= mp && k != 14;                                  // 劫火原版永远用不出去
            var b = Node("p" + i, plotMenu);
            Place(b, 8 + (i % COLS) * bw + 2, 34 + (i / COLS) * ROW, bw - 4, ROW - 4);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = BattleUi.BtnSp(); bi.type = Image.Type.Sliced;
            bi.color = ok ? Color.white : new Color(.55f, .52f, .48f, 1f);
            plotBtnImgs.Add(bi);
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None; bt.interactable = ok;
            bt.onClick.AddListener(() => {
                if (plotHover != k) { PlotHover(k); return; }                 // 第一下：看说明
                plotSel = k; ClosePlotMenu();                                  // 第二下：施
                mode = "plot"; hintText.text = "选择计策实施地点 · " + BattleRules.PlotName[k]; Refresh();
            });
            var l = Label(b, BattleRules.PlotName[i] + " " + cost, 13,
                          ok ? new Color32(0xF0,0xE0,0xB8,255) : new Color32(0x86,0x7E,0x6C,255), TextAnchor.MiddleCenter);
            Fill(l.rectTransform);
        }
        // 说明区：原版 d.q 原话 + 按代码扣的机动力
        float dy = 34 + rows * ROW + 6;
        var line = Node("ln", plotMenu).gameObject.AddComponent<Image>();
        Place((RectTransform)line.transform, 12, dy, w - 24, 1); line.color = new Color(.60f, .47f, .24f, .8f); line.raycastTarget = false;
        plotDescTitle = Label(plotMenu, "点一条看说明，再点一次施展", 13, new Color32(0xF0,0xE0,0x60,255), TextAnchor.MiddleCenter);
        Place(plotDescTitle.rectTransform, 0, dy + 6, w, 20);
        plotDescT = Label(plotMenu, "", 12, new Color32(0xE8,0xDC,0xC0,255), TextAnchor.UpperCenter);
        plotDescT.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(plotDescT.rectTransform, 14, dy + 28, w - 28, DESC - 30);
    }
    void PlotHover(int k)
    {
        plotHover = k;
        for (int i = 0; i < plotBtnImgs.Count; i++)
            if (plotBtnImgs[i] != null && plotBtnImgs[i].color != new Color(.55f, .52f, .48f, 1f))
                plotBtnImgs[i].color = i == k ? new Color(1f, .92f, .55f, 1f) : Color.white;
        plotDescTitle.text = BattleRules.PlotName[k] + " · 需机动力 " + BattleRules.CostOfPlot(k) + " · 再点一次施展";
        plotDescT.text = BattleRules.PlotDesc[k];
    }

    // ── 计策效果弹框（原版 c.java 3194：图在上、结果句在下、按键才收）──
    RectTransform plotPop; Image plotPic; Sprite[] plotFrames; float plotT0;
    const float PLOT_FRAME = 0.14f;
    static readonly Dictionary<string, Sprite> plotCache = new Dictionary<string, Sprite>();
    static Sprite PlotFrame(int plot, int f)
    {
        string k = plot + "_" + f;
        if (plotCache.TryGetValue(k, out var sp)) return sp;
        var p = Path.Combine(ArtDir, "battle", "plotcg", "plot_" + plot + "_f" + f + ".jpg");
        if (!File.Exists(p)) return plotCache[k] = null;
        var t = new Texture2D(2, 2); t.LoadImage(File.ReadAllBytes(p)); t.Apply(); t.filterMode = FilterMode.Point;
        return plotCache[k] = Whole(t);
    }
    void ShowPlotPop(int plot, string result)
    {
        ClosePlotPop();
        plotFrames = new Sprite[8]; int have = 0;
        for (int f = 0; f < 8; f++) { plotFrames[f] = PlotFrame(plot, f); if (plotFrames[f] != null) have++; }
        if (have == 0) plotFrames = null;
        float w = refW - 28, ph = (w - 16) * 384f / 512f, h = 34 + ph + 40;
        plotPop = Node("ppop", Root);
        Place(plotPop, 14, (refH - h) / 2 - 30, w, h);
        var bg = Panel(plotPop, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        bg.raycastTarget = true;                                   // 点一下收，原版是按键
        var bt = plotPop.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
        bt.onClick.AddListener(() => { ClosePlotPop(); Refresh(); });
        var t = Label(plotPop, "使用计策: " + BattleRules.PlotName[plot], 15, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 8, w, 22);
        plotPic = Node("pic", plotPop).gameObject.AddComponent<Image>();
        Place((RectTransform)plotPic.transform, 8, 34, w - 16, ph);
        plotPic.raycastTarget = false;
        if (plotFrames != null) plotPic.sprite = plotFrames[0]; else plotPic.color = new Color(.1f, .08f, .06f, 1);
        var r = Label(plotPop, result, 15, Color.white, TextAnchor.MiddleCenter);
        Place(r.rectTransform, 0, 34 + ph + 8, w, 24);
        plotT0 = Time.time;
    }
    void ClosePlotPop() { if (plotPop != null) Object.Destroy(plotPop.gameObject); plotPop = null; plotFrames = null; }
    /// 每帧：弹框里的图循环播（第八帧接回第一帧）
    public void PlotPopTick()
    {
        if (plotPop == null || plotFrames == null) return;
        var sp = plotFrames[(int)((Time.time - plotT0) / PLOT_FRAME) % 8];
        if (sp != null && plotPic.sprite != sp) plotPic.sprite = sp;
    }

    int TerrainAt(int x, int y) => grid[y, x] & 0x1F;
    bool HasAllyAdjacent(Unit u)
    {
        foreach (var o in units) if (o != u && o.Alive && o.Ours == u.Ours && Adjacent(o, u.X, u.Y)) return true;
        return false;
    }

    /// c.H() 的后半段：落点 → 合法 → roll → 结算 → 扣机动力
    void DoPlot(int x, int y)
    {
        int plot = plotSel; mode = ""; plotSel = -1;
        int cost = BattleRules.CostOfPlot(plot);
        if (plot == 11)                                                        // 十面埋伏：埋在自己脚下的树丛格
        {
            if (TerrainAt(sel.X, sel.Y) == 5 && sel.O.Troops >= 100)
            {
                ambush.Add(sel.Y * W + sel.X); sel.O.Troops -= 100; mp -= cost;
                hintText.text = "使用计策: 十面埋伏"; ShowPlotPop(11, "埋伏完毕");
                sel.Acted = true; sel = null;
            }
            else hintText.text = "此地无法使用该计策";
            return;
        }
        var tgt = At(x, y);
        if (tgt == null || tgt.Ours || tgt.Trapped > 0) { hintText.text = "此地无法使用该计策"; return; }
        if (!BattleRules.PlotLegal(plot, sel.X, sel.Y, x, y, TerrainAt(sel.X, sel.Y), TerrainAt(x, y), HasAllyAdjacent(tgt)))
        { hintText.text = "此地无法使用该计策"; return; }
        var r = BattleRules.PlotRoll(plot, sel.O, tgt.O, supply[3], rng);
        mp -= cost;                                                            // 成不成都扣（原版 4939）
        string msg = r.Ok ? BattleRules.PlotMessage(plot, r) : "计策实施失败!";
        if (r.Ok) ApplyPlot(plot, r, tgt);
        hintText.text = "使用计策: " + BattleRules.PlotName[plot];
        ShowPlotPop(plot, msg);                                                // 原版 P 在 roll 之前就置 true：失败也弹图
        sel.Acted = true; sel = null;
    }

    /// b.a(short,byte[],byte,byte,int,boolean)
    void ApplyPlot(int plot, BattleRules.PlotResult r, Unit tgt)
    {
        void Hurt(Unit u, int n) { u.O.Troops = Mathf.Max(0, u.O.Troops - n); if (u.O.Troops <= 0) u.Alive = false; }
        switch (r.Type)
        {
            case 1: Hurt(tgt, r.Troops); break;
            case 2: tgt.O.Hp = Mathf.Max(0, tgt.O.Hp - r.Hp); break;
            case 3: supply[3] = Mathf.Max(0, supply[3] - r.Troops); break;
            case 4: Hurt(tgt, r.Troops); tgt.O.Hp = Mathf.Max(0, tgt.O.Hp - r.Hp); break;
            case 5: tgt.Ours = true; tgt.Acted = true; tgt.Dir = "s"; break;   // 笼络：整支转到我方
            case 6:
                Hurt(tgt, r.Troops);
                foreach (var o in units) if (o != tgt && o.Alive && !o.Ours && o.Trapped == 0 && Adjacent(o, tgt.X, tgt.Y)) Hurt(o, r.Troops);
                break;
            case 7: tgt.Trapped = TRAP_DAYS; break;
            case 8:
                tgt.Trapped = TRAP_DAYS;
                foreach (var o in units) if (o != tgt && o.Alive && !o.Ours && o.Trapped == 0 && Adjacent(o, tgt.X, tgt.Y)) o.Trapped = TRAP_DAYS;
                break;
            case 9: tgt.Trapped = TRAP_DAYS; tgt.Cursed = true; break;
        }
    }
    // 被困几日：原版说明文案写连环「被困三天」，虚兵和奇门遁甲没写；代码里没找到递减的地方。
    // 三种都按三天算，记在 HOUSE_RULES。
    const int TRAP_DAYS = 3;
    const int AMBUSH_LOSS = 150;   // 十面埋伏踩中扣多少兵：原版走 j() 我没读到数，按共杀那档 150，记 HOUSE_RULES

    void DoAttack(Unit foe)
    {
        if (mp < BattleRules.AttackCost) { hintText.text = "机动力不足"; return; }
        mp -= BattleRules.AttackCost;
        sel.Acted = true;
        var me = sel; sel = null; mode = "";
        OnClash?.Invoke(me, foe, true);
    }

    // ── 情报（原版 c.F() + a(Graphics)）───────────────────────────────
    // 光标点到哪一支（敌我都行）就弹「武将信息」：头像、名、LV、武/智、体/德、兵/忠、武器/防具。
    // 不扣机动力、不算行动——原版 F() 里没有 bk 减法，也不置 Acted。
    RectTransform infoPop;
    void CloseInfo() { if (infoPop != null) Object.Destroy(infoPop.gameObject); infoPop = null; }
    static Sprite AvatarOf(int id)
    {
        var p = Path.Combine(ArtDir, "battle", "avatar", id + ".jpg");
        if (!File.Exists(p)) return null;
        var t = new Texture2D(2, 2); t.LoadImage(File.ReadAllBytes(p)); t.Apply();
        return Whole(t);
    }
    void ShowInfo(Unit u)
    {
        CloseInfo(); mode = "";
        hintText.text = "武将信息";
        var o = u.O;
        float w = 300, h = 262;
        infoPop = Node("info", Root);
        Place(infoPop, (refW - w) / 2, (refH - h) / 2 - 40, w, h);
        var bg = Panel(infoPop, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        bg.raycastTarget = true;
        var bt = infoPop.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
        bt.onClick.AddListener(() => { CloseInfo(); Refresh(); });

        // 头像 + 錾金框（框中间那个洞的位置是量出来的）
        const int PW = 96; float px = 16, py = 16;
        var m = BattleUi.Metric("portrait"); var frame = BattleUi.Get("portrait");
        int fw = m != null && m.Length >= 6 ? m[0] : 192, fh = m != null && m.Length >= 6 ? m[1] : 188;
        float k = PW / (float)fw; int ph = Mathf.RoundToInt(fh * k);
        var av = AvatarOf(o.Id);
        if (av != null)
        {
            var im = Node("av", infoPop).gameObject.AddComponent<Image>();
            float hx = m != null && m.Length >= 6 ? m[2] * k : PW * .18f, hy = m != null && m.Length >= 6 ? m[3] * k : ph * .2f;
            float hw = m != null && m.Length >= 6 ? m[4] * k : PW * .64f, hh = m != null && m.Length >= 6 ? m[5] * k : ph * .6f;
            Place((RectTransform)im.transform, px + hx, py + hy, hw, hh); im.sprite = av; im.raycastTarget = false;
        }
        if (frame != null)
        {
            var fi = Node("fr", infoPop).gameObject.AddComponent<Image>();
            Place((RectTransform)fi.transform, px, py, PW, ph); fi.sprite = frame; fi.raycastTarget = false;
        }
        var nm = Label(infoPop, o.Name, 18, u.Ours ? new Color32(0x9C,0xC8,0xFF,255) : new Color32(0xFF,0x98,0x90,255));
        Place(nm.rectTransform, px + PW + 16, py + 10, 160, 26);
        var lv = Label(infoPop, "LV. " + o.Lv + "   " + (o.Kind == Kind.Ping ? "平军" : o.Kind == Kind.Shui ? "水军" : "山军"), 14, new Color32(0xE8,0xDC,0xC0,255));
        Place(lv.rectTransform, px + PW + 16, py + 42, 160, 22);

        // 原版 a(Graphics)：八格属性两列四行——武/智、体/德、兵/忠、武器/防具
        string[][] rows = {
            new[] { "武", o.Might.ToString(), "智", o.Int.ToString() },
            new[] { "体", o.Hp.ToString(),    "德", o.Virtue.ToString() },
            new[] { "兵", o.Troops.ToString(),"忠", o.Loyal > 99 ? "--" : o.Loyal.ToString() },   // 原版忠诚 >99 画 "--"
            new[] { "武器", BattleRules.WeaponOf(o), "防具", BattleRules.ArmorOf(o) },
        };
        var lab = new Color32(0xC8,0xA8,0x60,255); var val = Color.white;
        float y0 = py + ph + 14;
        for (int r = 0; r < rows.Length; r++)
        {
            float yy = y0 + r * 26;
            var tag = Node("tg", infoPop).gameObject.AddComponent<Image>();      // 标签垫一块小木牌，照原版那八个小方块
            Place((RectTransform)tag.transform, 18, yy, 40, 22); tag.sprite = BattleUi.BtnSp(); tag.type = Image.Type.Sliced; tag.raycastTarget = false;
            var l1 = Label(infoPop, rows[r][0], 13, lab, TextAnchor.MiddleCenter); Place(l1.rectTransform, 18, yy, 40, 22);
            var v1 = Label(infoPop, rows[r][1], 15, val); Place(v1.rectTransform, 64, yy, 84, 22);
            var tag2 = Node("tg", infoPop).gameObject.AddComponent<Image>();
            Place((RectTransform)tag2.transform, 154, yy, 40, 22); tag2.sprite = BattleUi.BtnSp(); tag2.type = Image.Type.Sliced; tag2.raycastTarget = false;
            var l2 = Label(infoPop, rows[r][2], 13, lab, TextAnchor.MiddleCenter); Place(l2.rectTransform, 154, yy, 40, 22);
            var v2 = Label(infoPop, rows[r][3], 15, val); Place(v2.rectTransform, 200, yy, 90, 22);
        }
    }

    void EndDay()
    {
        if (enemyTurn) return;
        enemyTurn = true;
        enemyMp = BattleRules.MoveCap(TotalTroops(false));
        enemyIdx = 0;
        sel = null; mode = ""; reach.Clear();
        hintText.text = "敌军行动中...";
        Refresh();
    }

    void StartOurDay()
    {
        enemyTurn = false;
        day++;
        foreach (var u in units)
        {
            u.Acted = false;
            if (u.Trapped > 0)
            {
                if (u.Cursed) { u.O.Troops = Mathf.Max(0, u.O.Troops - 100); if (u.O.Troops <= 0) u.Alive = false; }
                if (--u.Trapped == 0) u.Cursed = false;
            }
        }
        mp = BattleRules.MoveCap(TotalTroops(true));
        hintText.text = "回合结束，第 " + day + " 日";
        Refresh();
    }

    // ── 敌方回合：一枚一格地走 ──────────────────────────────────────────
    // 原版 b.java:3655 一次只处理一枚部队的一格：
    //   四邻有我方就攻击（扣 2 机动力），否则从路径里取一步、够机动力才走。
    // 我原来是一帧里把所有敌军都挪完，看起来就是「啪一下全到脸上」。
    bool enemyTurn; float stepAt; int enemyMp, enemyIdx;
    Unit acting;                                   // 正在走的那枚，画个圈标出来
    const float STEP = 0.55f;                      // 一格多久。0.22 太快，看不清是谁在动
    const float GLIDE = 7f;                        // 滑行速度：越大越快贴到目标格
    const float PAN   = 6f;                        // 镜头跟随速度

    Vector2 camTarget; bool camInit;

    public void Tick()
    {
        bool moving = false;
        foreach (var u in units)                   // 滑向各自的格子
        {
            if (!u.Alive) continue;
            var want = new Vector2(u.X, u.Y);
            if ((u.Draw - want).sqrMagnitude > 0.0004f)
            {
                u.Draw = Vector2.Lerp(u.Draw, want, 1f - Mathf.Exp(-GLIDE * Time.deltaTime));
                moving = true;
            }
            else u.Draw = want;
        }
        if (camInit)                               // 镜头也是滑过去，不硬跳
        {
            var cur = board.anchoredPosition;
            var nxt = Vector2.Lerp(cur, camTarget, 1f - Mathf.Exp(-PAN * Time.deltaTime));
            if ((cur - nxt).sqrMagnitude > 0.0004f) { board.anchoredPosition = nxt; moving = true; }
            else board.anchoredPosition = camTarget;
        }

        if (enemyTurn && Time.time >= stepAt)
        {
            stepAt = Time.time + STEP;
            if (!EnemyStep()) { acting = null; StartOurDay(); }
            Refresh();
            return;
        }
        if (moving) Refresh();                     // 有东西在动就每帧重绘
    }

    /// 走一步。返回 false 表示敌方这一日到此为止。
    bool EnemyStep()
    {
        if (enemyMp < 2) return false;
        for (int guard = 0; guard < 64; guard++)
        {
            var list = units.FindAll(u => !u.Ours && u.Alive);
            if (list.Count == 0) return false;
            if (enemyIdx >= list.Count) { enemyIdx = 0; return false; }   // 一圈走完就收
            var e = list[enemyIdx];
            if (e.Trapped > 0) { enemyIdx++; continue; }   // 被困：这一日不动也不打

            Unit target = null; int bd = 999;
            foreach (var m in units)
            {
                if (!m.Ours || !m.Alive) continue;
                int d = Mathf.Abs(m.X - e.X) + Mathf.Abs(m.Y - e.Y);
                if (d < bd) { bd = d; target = m; }
            }
            if (target == null) return false;

            if (bd == 1)                                   // 贴上了就打，扣 2
            {
                if (enemyMp < BattleRules.AttackCost) { enemyIdx++; continue; }
                enemyMp -= BattleRules.AttackCost;
                hintText.text = e.O.Name + " 来攻 " + target.O.Name;
                acting = e; enemyIdx++;
                OnClash?.Invoke(target, e, false);          // 我方是守方
                return true;
            }

            int nx = e.X, ny = e.Y;
            if (Mathf.Abs(target.X - e.X) >= Mathf.Abs(target.Y - e.Y))
                nx += System.Math.Sign(target.X - e.X);
            else ny += System.Math.Sign(target.Y - e.Y);

            int cost = (nx < 0 || ny < 0 || nx >= W || ny >= H) ? 100
                     : (At(nx, ny) != null ? 100 : BattleRules.MoveCost(grid[ny, nx] & 0xF, e.O.Kind));
            if (cost > enemyMp || cost >= 100) { enemyIdx++; continue; }

            enemyMp -= cost;
            e.Dir = nx != e.X ? "e" : (ny > e.Y ? "s" : "n");
            e.Flip = nx < e.X;
            e.X = nx; e.Y = ny;
            acting = e; enemyIdx++;
            CenterOn(e.X, e.Y);                            // 镜头跟着走，看得见是谁在动
            if (ambush.Remove(ny * W + nx))                // b.java 3703：踩进埋伏格
            {
                e.O.Troops = Mathf.Max(0, e.O.Troops - AMBUSH_LOSS);
                if (e.O.Troops <= 0) e.Alive = false;
                hintText.text = e.O.Name + " 中计谋十面埋伏";
            }
            return true;
        }
        return false;
    }

    // ── 计算 ───────────────────────────────────────────────────────────
    readonly Dictionary<int, int> costTo = new Dictionary<int, int>();
    /// 原版寻路是贪心逐格逼近、最多 16 步；这里换成按机动力预算的 BFS，
    /// 走一格的消耗仍然是 b.h(格,兵种)。路径形状会和原版略有出入。
    void ComputeReach()
    {
        reach.Clear(); costTo.Clear();
        var q = new Queue<int>();
        int s = sel.Y * W + sel.X;
        costTo[s] = 0; q.Enqueue(s);
        while (q.Count > 0)
        {
            int cur = q.Dequeue(); int cx = cur % W, cy = cur / W, cc = costTo[cur];
            int[] dx = { 0, 0, -1, 1 }, dy = { -1, 1, 0, 0 };
            for (int i = 0; i < 4; i++)
            {
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                if (At(nx, ny) != null) continue;
                int step = BattleRules.MoveCost(grid[ny, nx] & 0xF, sel.O.Kind);
                if (step >= 100) continue;
                int nc = cc + step;
                if (nc > mp) continue;
                int key = ny * W + nx;
                if (costTo.TryGetValue(key, out var old) && old <= nc) continue;
                costTo[key] = nc; reach.Add(key); q.Enqueue(key);
            }
        }
        reach.Remove(s);
        hintText.text = "武将移动 · 亮格可达 · 剩 " + mp + " 点";
    }

    bool Adjacent(Unit u, int x, int y) => Mathf.Abs(u.X - x) + Mathf.Abs(u.Y - y) == 1;
    bool HasFoeAdjacent(Unit u)
    {
        foreach (var o in units) if (o.Alive && !o.Ours && Adjacent(u, o.X, o.Y)) return true;
        return false;
    }
    public Unit At(int x, int y)
    {
        foreach (var u in units) if (u.Alive && u.X == x && u.Y == y) return u;
        return null;
    }
    int TotalTroops(bool ours)
    {
        int s = 0;
        foreach (var u in units) if (u.Ours == ours && u.Alive) s += u.O.Troops;
        return s;
    }

    void JumpTo(bool ours)
    {
        foreach (var u in units)
            if (u.Alive && u.Ours == ours) { CenterOn(u.X, u.Y); curX = u.X; curY = u.Y; Refresh(); return; }
    }

    void ScrollTo(Vector2 p)
    {
        var clip = (RectTransform)board.parent;
        float vw = clip.rect.width, vh = clip.rect.height;
        board.anchoredPosition = new Vector2(
            Mathf.Clamp(p.x, Mathf.Min(0, vw - W * CELL), 0),
            Mathf.Clamp(p.y, 0, Mathf.Max(0, H * CELL - vh)));
        camTarget = board.anchoredPosition;        // 拖完要同步，不然一松手镜头弹回去
    }

    void CenterOn(int x, int y)
    {
        var clip = (RectTransform)board.parent;
        float vw = clip.rect.width > 1 ? clip.rect.width : refW;
        float vh = clip.rect.height > 1 ? clip.rect.height : refH - 172;
        float px = Mathf.Clamp(x * CELL + CELL / 2f - vw / 2f, 0, Mathf.Max(0, W * CELL - vw));
        float py = Mathf.Clamp(y * CELL + CELL / 2f - vh / 2f, 0, Mathf.Max(0, H * CELL - vh));
        camTarget = new Vector2(-px, py);
        if (!camInit) { board.anchoredPosition = camTarget; camInit = true; }   // 开局不滑
    }

    // ── 重绘 ───────────────────────────────────────────────────────────
    readonly List<GameObject> marks = new List<GameObject>();
    public void Refresh()
    {
        foreach (var g in marks) Object.Destroy(g);
        marks.Clear();

        foreach (int k in ambush)                      // 我方埋下的十面埋伏，只有我方看得见
        {
            var im = Node("am", board).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, (k % W) * CELL, (k / W) * CELL, CELL, CELL);
            im.color = new Color(1f, .55f, .2f, .28f); im.raycastTarget = false; marks.Add(im.gameObject);
        }
        if (mode == "plot" && sel != null && plotSel >= 0 && plotSel != 11)   // 计策落点：合法的敌军格亮起
            foreach (var o in units)
                if (o.Alive && !o.Ours && o.Trapped == 0 &&
                    BattleRules.PlotLegal(plotSel, sel.X, sel.Y, o.X, o.Y, TerrainAt(sel.X, sel.Y), TerrainAt(o.X, o.Y), HasAllyAdjacent(o)))
                {
                    var im = Node("pt", board).gameObject.AddComponent<Image>();
                    Place((RectTransform)im.transform, o.X * CELL - 3, o.Y * CELL - 3, CELL + 6, CELL + 6);
                    im.sprite = BattleUi.Get("target") ?? CursorSprite(); im.raycastTarget = false; marks.Add(im.gameObject);
                }
        foreach (int k in reach)                       // 可达格
        {
            var im = Node("r", board).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, (k % W) * CELL, (k / W) * CELL, CELL, CELL);
            var rs = BattleUi.Get("reach");
            if (rs != null) { im.sprite = rs; im.color = new Color(1f, 1f, 1f, .62f); }
            else im.color = new Color(.35f, .75f, 1f, .30f);
            im.raycastTarget = false;
            marks.Add(im.gameObject);
        }
        foreach (var u in units)                       // 部队
        {
            if (!u.Alive) continue;
            float ux = u.Draw.x * CELL, uy = u.Draw.y * CELL;
            var im = Node("u", board).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, ux, uy, CELL, CELL);
            im.sprite = UnitSprite(u); im.raycastTarget = false;
            im.color = u.Acted ? new Color(.62f, .62f, .62f, 1f) : Color.white;
            if (u.Flip)
            {
                // Place() 的轴心在左上角，localScale=-1 是绕左边缘翻的，
                // 整个小人会被推左一格，而名字血条不跟着翻 —— 于是错位一格。
                // 补回一格宽度，等价于绕格子中心翻。
                im.rectTransform.localScale = new Vector3(-1, 1, 1);
                im.rectTransform.anchoredPosition += new Vector2(CELL, 0);
            }
            marks.Add(im.gameObject);

            var back = Node("bk", board).gameObject.AddComponent<Image>();
            Place((RectTransform)back.transform, ux + 2, uy + CELL - 9, CELL - 4, 8);
            back.color = new Color(0, 0, 0, .55f); back.raycastTarget = false;
            marks.Add(back.gameObject);
            var bar = Node("bar", board).gameObject.AddComponent<Image>();
            float f = Mathf.Clamp01(u.O.Troops / 1000f);
            Place((RectTransform)bar.transform, ux + 3, uy + CELL - 8, (CELL - 6) * f, 6);
            bar.color = u.Ours ? new Color(.35f, .65f, 1f) : new Color(1f, .35f, .3f);
            bar.raycastTarget = false; marks.Add(bar.gameObject);

            if (u.Trapped > 0)                             // 被困：脚下压咒缚那套绳索（复用阵型战的特效）
            {
                var tr = Node("trap", board).gameObject.AddComponent<Image>();
                Place((RectTransform)tr.transform, ux - 4, uy - 4, CELL + 8, CELL + 8);
                var sp = BattleUi.Fx("bind", (int)(Time.time / .18f) % 4);
                if (sp != null) tr.sprite = sp; else tr.color = new Color(.6f, .3f, .9f, .45f);
                tr.raycastTarget = false; marks.Add(tr.gameObject);
            }
            var np = BattleUi.Get("nameplate");
            if (np != null)
            {
                var pl = Node("np", board).gameObject.AddComponent<Image>();
                Place((RectTransform)pl.transform, ux - 14, uy + CELL, CELL + 28, 16);
                pl.sprite = np; pl.type = Image.Type.Sliced; pl.raycastTarget = false;
                marks.Add(pl.gameObject);
            }
            var nm = Label(board, u.O.Name + " " + u.O.Troops, 11, Color.white, TextAnchor.MiddleCenter);
            Place(nm.rectTransform, ux - 14, uy + CELL, CELL + 28, 16);
            var ol = nm.gameObject.AddComponent<Outline>();     // 压在地形上不描边看不清
            ol.effectColor = new Color(0, 0, 0, .95f);
            ol.effectDistance = new Vector2(1.2f, 1.2f);
            marks.Add(nm.gameObject);
            if (mode == "attack" && sel != null && !u.Ours && Adjacent(sel, u.X, u.Y))
            {
                var tg = Node("tg", board).gameObject.AddComponent<Image>();
                Place((RectTransform)tg.transform, ux - 3, uy - 3, CELL + 6, CELL + 6);
                tg.sprite = BattleUi.Get("target") ?? CursorSprite();
                if (BattleUi.Get("target") == null) tg.color = new Color(1f, .3f, .25f, 1f);
                tg.raycastTarget = false; marks.Add(tg.gameObject);
            }
            if (sel == u || acting == u)
            {
                var hl = Node("hl", board).gameObject.AddComponent<Image>();
                Place((RectTransform)hl.transform, ux, uy, CELL, CELL);
                hl.color = acting == u ? new Color(1f, .45f, .35f, .35f)
                                       : new Color(1f, .95f, .4f, .25f);
                hl.raycastTarget = false;
                marks.Add(hl.gameObject);
            }
        }

        Place((RectTransform)cursorImg.transform, curX * CELL - 3, curY * CELL - 3, CELL + 6, CELL + 6);
        cursorImg.transform.SetAsLastSibling();

        dayText.text = "第 " + day + " 日";
        mpText.text = "机动力 " + mp + " / " + BattleRules.MoveCap(TotalTroops(true));
        int hi = mode == "move" ? 0 : mode == "attack" ? 2 : mode == "info" ? 3 : -1;
        for (int i = 0; i < CMD.Length; i++)
            if (cmdImgs[i] != null)
                cmdImgs[i].color = i == hi ? new Color(1f, .92f, .55f, 1f) : Color.white;
    }
}
