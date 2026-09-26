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
    public System.Action OnQuit;                    // 结局框上点「返回」

    /// 接进正式游戏时由 Game 塞进来：城主 / 城里人数 / 邻城 / 城名都读活的数据。
    /// 不塞（独立的 SangoBattle 演示）就照旧读 cities.csv 那份开局表。
    public interface IWorld
    {
        Dictionary<int, int> Rulers();          // 城 → 城主武将号（0 = 空城），原版 i[]
        Dictionary<int, int> Counts();          // 城 → 仕官人数，原版 r[]
        int[] Adjacent(int city);
        string Name(int city);
    }
    public static IWorld World;

    // ── 结局记录：打完之后战略层照这个善后 ────────────────────────────
    public readonly Dictionary<Officer, int> RetreatTo = new();   // 撤到哪座城（0 = 下野）
    public readonly HashSet<Officer> Dead = new();                 // 战死
    public int CityFell;                                            // 1 我方攻占 / -1 敌方攻占 / 0 城没易主
    public int[] Supply => supply;                                  // 我金 我粮 敌金 敌粮（打完的余数）
    public bool IsOver => over;
    public int KeptGold, KeptFood;
    public int OurKeptGold, OurKeptFood;                            // 我方守城失守时，撤走的人带走的城库                                  // 城破时守军带走的金粮（K() 3322）

    const int CELL = 44;                            // 一格在屏上多大
    const int NP_H = 15;                            // 名牌高度：上面 11 排字、下面 4 留给兵力条
    const int FIG  = CELL - NP_H + 3;               // 人物：名牌以上的那块地方，脚搭在名牌上沿
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
    float refW, refH;

    static readonly string[] CMD = { "移动", "计策", "攻击", "情报", "撤退", "待机" };

    readonly int[] supply;                                  // 我金 我粮 敌金 敌粮
    readonly int rulerId;                                   // 我方君主（原版 aZ），和他有没有城无关
    public BattleField(RectTransform parent, int cityId, string themeKey,
                       List<Unit> roster, float refW, float refH, int[] supply = null, int rulerId = -1)
    {
        this.refW = refW; this.refH = refH; this.supply = supply ?? new int[4]; this.cityId = cityId; this.rulerId = rulerId;
        supply0 = (int[])this.supply.Clone();                      // 自检复位用
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
        SelectLead();                                  // 开战第一日也直接选中主将
        Refresh();
    }

    // ── 开场摆位：原版 b.w()（我攻）/ b.x()（我守）──────────────────────
    //   守方第一员（太守）站在城池格（码 2）上；其余守军在城旁 2 列 × 5 行里随机落（不重叠）；
    //   攻方在同一侧 8–9 列开外、同样 5 行里随机落。方向：城在 x>15 往左摆（bw=-1），y>10 往上摆（bx=-1）。
    //   原版扫格是"最后一列有码 2 的、那列最上面那格"，照抄。
    public static void Deploy(int[,] grid, List<Unit> roster, bool weAttack, System.Random rng)
    {
        int H = grid.GetLength(0), W = grid.GetLength(1), cx = -1, cy = -1;
        for (int x = 0; x < W; x++)
            for (int y = 0; y < H; y++)
                if ((grid[y, x] & 0xF) == 2) { cx = x; cy = y; break; }
        if (cx < 0) { cx = W / 2; cy = H / 2; }
        int bw = cx > 15 ? -1 : 1, bx = cy > 10 ? -1 : 1;
        var used = new HashSet<int> { cy * W + cx };
        void Drop(Unit u, int colBase, int colSpan, bool faceEast)
        {
            int x = cx, y = cy;
            for (int t = 0; t < 200; t++)
            {
                x = cx + (rng.Next(colSpan) + colBase) * bw; y = cy + rng.Next(5) * bx;
                if (x >= 0 && y >= 0 && x < W && y < H && !used.Contains(y * W + x)) break;
            }
            used.Add(y * W + x);
            u.X = x; u.Y = y; u.Draw = new Vector2(x, y); u.Dir = "e"; u.Flip = !faceEast;
        }
        bool first = true;
        foreach (var u in roster)
        {
            bool defender = u.Ours != weAttack;
            bool faceEast = defender ? bw > 0 : bw < 0;       // 守军朝攻军来的那边，攻军朝城
            if (defender && first)
            {
                first = false;
                u.X = cx; u.Y = cy; u.Draw = new Vector2(cx, cy); u.Dir = "e"; u.Flip = !faceEast;
            }
            else if (defender) Drop(u, 0, 2, faceEast);
            else Drop(u, 8, 2, faceEast);
        }
    }

    // ── 搭画面 ─────────────────────────────────────────────────────────
    void BuildBoard()
    {
        var clip = Node("clip", Root);
        Place(clip, 0, TOP_H + 2, refW, refH - TOP_H - 2 - 96);
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
    /// 桌面窗口被拖动改了大小：整屏重排一次。棋子、日程、机动力这些状态都在 units / 字段里，
    /// 只有画面节点要重建，所以直接把 Root 整棵拆了重搭。手机上只有转屏会走到这儿。
    public void Relayout(float w, float h)
    {
        if (Mathf.Abs(w - refW) < .5f && Mathf.Abs(h - refH) < .5f) return;
        refW = w; refH = h;
        askPop = retreatPop = infoPop = overPop = plotPop = null;
        plotMenu = null; plotFrames = null; cmdSel = null;
        marks.Clear();
        var parent = (RectTransform)Root.parent;
        Object.Destroy(Root.gameObject);
        Root = Node("BattleField", parent); Fill(Root);
        BuildBoard(); BuildHud();
        RefreshTop(); CenterOn(curX, curY); Refresh();
    }

    const float TOP_Y = 24, TOP_H = TOP_Y + 66;      // 顶栏往下挪，空出上沿那一条挂城名匾额
    void BuildHud()
    {
        // 顶栏：左我方金粮、中间日数与机动力、右敌方金粮；第二行是提示
        var top = Panel(Root, "top", BattleUi.PlateSp());
        Place((RectTransform)top.transform, 8, TOP_Y, refW - 16, 66);
        var lab = new Color32(0xC8,0xA8,0x60,255);
        Text Col(float x, float w, string t, int size, Color32 c, TextAnchor a)
        {
            var l = Label(top.transform, t, size, c, a); Place(l.rectTransform, x, 8, w, 22); return l;
        }
        Col(14, 40, "我方", 13, new Color32(0x9C,0xC8,0xFF,255), TextAnchor.MiddleLeft);
        float gw = Loc.Source ? 110 : 96;                                   // 译文（Gold / Food）长，给中间的「第 N 日」让出地方，放不下自动缩字
        goldMe = Col(54, gw, "金 " + supply[0] + "  粮 " + supply[1], 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleLeft);
        Col(refW - 16 - 14 - 40, 40, "敌方", 13, new Color32(0xFF,0x98,0x90,255), TextAnchor.MiddleRight);
        goldFoe = Col(refW - 16 - 14 - 40 - gw, gw, "金 " + supply[2] + "  粮 " + supply[3], 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleRight);
        dayText = Col(0, refW - 16, "", 15, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        mpText  = Label(top.transform, "", 13, new Color32(0xF0,0xD0,0x88,255), TextAnchor.MiddleCenter);
        Place(mpText.rectTransform, 0, 30, refW - 16, 18);
        hintText = Label(top.transform, "", 12, new Color32(0xC8,0xC0,0xA8,255), TextAnchor.MiddleCenter);
        Place(hintText.rectTransform, 12, 46, refW - 40, 18);

        // 顶上那条：暗青铜的云雷纹饰带，横向平铺铺满整屏，匾额压在它上头，
        // 顺手把顶栏上沿那条黑边填掉。沿用素材自己的高度，别拉伸变形。
        var bandSp = BattleUi.Get("topband");
        if (bandSp != null)
        {
            var band = Node("topband", Root);
            Place(band, 0, 0, refW, bandSp.rect.height);
            var bim = band.gameObject.AddComponent<Image>();
            bim.sprite = bandSp; bim.type = Image.Type.Tiled; bim.raycastTarget = false;
        }

        // 城名匾额：挂在顶栏上沿，像钉在城门上的那块匾。两端的云纹翘头是九宫格的「角」，
        // 中段跟着字数拉伸——两个字和三个字的城名都能兜住。
        string cn = CityNameNow();
        float pw = Mathf.Max(112, 58 + cn.Length * 26), ph = 34;
        var plate = Node("cityplate", Root);
        Place(plate, (refW - pw) / 2, 2, pw, ph);
        var pi = plate.gameObject.AddComponent<Image>();
        pi.sprite = BattleUi.Get("cityplate") ?? BattleUi.PlateSp();
        pi.type = Image.Type.Sliced; pi.raycastTarget = false;
        var pt = Label(plate, cn, 19, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        Place(pt.rectTransform, 0, 1, pw, ph - 4);
        pt.raycastTarget = false;

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
    /// 自检：只进落点选择态，不真的放——好截到红框上那圈成功率
    public void PlotAimForTest(Unit u, int plot)
    {
        ClosePlotMenu(); sel = u; plotSel = plot; mode = "plot";
        CenterOn(u.X, u.Y); curX = u.X; curY = u.Y; Refresh();
    }
    public void PlotForTest(Unit u, int plot, Unit tgt)
    {
        ClosePlotMenu(); sel = u; plotSel = plot; mode = "plot";
        CenterOn(tgt.X, tgt.Y); curX = tgt.X; curY = tgt.Y; Refresh();
        if (ClashView.Trace)
            Debug.Log(string.Format("[plot] {0}({1},{2}) 地形{3} → {4}({5},{6}) 地形{7} 第{8}种",
                u.O.Name, u.X, u.Y, TerrainAt(u.X, u.Y),
                tgt.O.Name, tgt.X, tgt.Y, TerrainAt(tgt.X, tgt.Y), plot));
        DoPlot(tgt.X, tgt.Y); Refresh();
    }
    public Unit UnitOf(int officerId) => units.Find(x => x.O.Id == officerId);
    /// 自检：同步跑 n 步敌军回合（不等 0.55 秒一步的动画）
    public void EnemyTurnForTest(int n)
    {
        CloseAsk(); CloseInfo(); CloseRetreatMenu(); ClosePlotMenu(); ClosePlotPop();
        enemyTurn = true; enemyMp = BattleRules.MoveCap(TotalTroops(false)); enemyIdx = 0; plans = null; roundStartMp = -1;
        for (int i = 0; i < n; i++) if (!EnemyStep()) break;
        foreach (var u in units) u.Draw = new Vector2(u.X, u.Y);
        Refresh();
    }
    /// 自检：守城时连跑 n 个敌军日，每日记下敌军站位（贴上就打的不开阵型战，只记一笔）
    public void DefenseDaysForTest(int n)
    {
        var keep = OnClash; OnClash = (m, e, _) => Debug.Log("[守城自检] " + e.O.Name + " 攻打 " + m.O.Name);
        for (int d = 0; d < n && !over; d++)
        {
            EndDay();
            for (int g = 0; g < 400 && enemyTurn; g++) { ClosePlotPop(); if (!EnemyStep()) break; }
            var sb = new System.Text.StringBuilder("[守城自检] 第 " + day + " 日 cN=" + foeCalls + "：");
            foreach (var u in units) if (!u.Ours && u.Alive) sb.Append(u.O.Name + "(" + u.X + "," + u.Y + ") ");
            var head = units.Find(u => u.Ours && u.Alive);
            if (head != null) sb.Append("｜我方头将 " + head.O.Name + "(" + head.X + "," + head.Y + ")");
            Debug.Log(sb.ToString());
            StartOurDay();
        }
        OnClash = keep;
    }
    /// 单挑投降回来：这支部队退出战场，选附近的城或下野
    public void OpenRetreatFor(Unit u) { sel = u; mode = ""; hintText.text = u.O.Name + " 投降，退出战场"; OpenRetreatMenu(); Refresh(); }
    public void LandForTest(Unit u, int x, int y) { CloseAsk(); CloseInfo(); CloseRetreatMenu(); sel = u; u.X = x; u.Y = y; u.Draw = new Vector2(x, y); CenterOn(x, y); curX = x; curY = y; Refresh(); OnLanded(u); }
    public void HealForTest(Unit u) { CloseAsk(); u.O.Hp = Mathf.Max(1, u.O.HpMax - 40); HealPrompt(u); }
    public void AskYesForTest() { var b = askPop != null ? askPop.Find("b是") : null; b?.GetComponent<Button>().onClick.Invoke(); }
    public void RetreatMenuForTest(Unit u) { CloseInfo(); sel = u; mode = ""; OpenRetreatMenu(); Refresh(); }
    public void InfoForTest(Unit u, Unit tgt) { ClosePlotPop(); sel = u; mode = "info"; ShowInfo(tgt); Refresh(); }   // 先收上一步的计策弹框
    /// 自检：按「移动」，亮出可达范围
    public void MoveForTest(Unit u) { CloseAsk(); sel = u; BeginMove(); curX = u.X; curY = u.Y; Refresh(); }
    /// 自检：走一格再按取消，看位置和机动力是不是都还原了
    public void MoveCancelForTest(Unit u, int x, int y)
    {
        CloseAsk(); sel = u; BeginMove();
        if (reach.Contains(y * W + x)) DoMove(x, y);
        Refresh();
    }
    public void DoCancelForTest() { CancelMove(); }
    public int MpForTest => mp;
    public Vector2Int PosForTest(Unit u) => new Vector2Int(u.X, u.Y);
    public void CommandForTest(Unit u, int k) { sel = u; mode = ""; Command(k); CenterOn(u.X, u.Y); curX = u.X; curY = u.Y; Refresh(); }

    // ── 素材 ───────────────────────────────────────────────────────────
    // 码 0 平地原本有自己的一格「ground」，和草地（码 12）摆在一起多余，
    // 平地也铺草——只是视觉上合并，行为上 0 和 12 的机动力消耗照旧各算各的。
    /// 照原版 c.java 2968 那个 switch：码 0 素土、码 5 一棵大树（这才是「林地」）、
    /// 码 6 水、**码 7~12 六个全是山**、码 1/3/4 房屋、码 2 城。
    /// 我们原先把 8/10/11 画成密林、12 画成草坡，于是山只剩三十格、林子多出一大片——
    /// 这是我们的地图和原版长得不一样的主因，比贴图本身影响还大。
    /// （原版码 5 写的是「先画块 5 再画块 7」，但块 7 不透明，第一笔完全被盖住，是段死代码。）
    static readonly Dictionary<int, string> SLOT = new Dictionary<int, string> {
        {0,"ground"},{1,"wuku"},{2,"castle"},{3,"cang"},{4,"yao"},{5,"forest"},{6,"water"},
        {7,"rock"},{8,"rock"},{9,"rock"},{10,"rock"},{11,"rock"},{12,"rock"},
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
        if (x < 0 || y < 0 || x >= W || y >= H || over) return;
        curX = x; curY = y;

        var here = At(x, y);
        if (mode == "plot" && sel != null) { DoPlot(x, y); }
        else if (mode == "move" && sel != null && reach.Contains(y * W + x)) { DoMove(x, y); }
        else if (mode == "attack" && sel != null && here != null && !here.Ours && Adjacent(sel, x, y)) { DoAttack(here); }
        else if (mode == "info" && here != null) { ShowInfo(here); }
        else if (here != null && here.Ours && here.Trapped > 0) hintText.text = "武将中计,无法动弹!";   // W == 19
        else if (here != null && here.Ours)
        {
            if (mode == "move" && here != sel) EndMove();                // 移动到一半改点别人：先照原版 E() 落定
            sel = here; mode = ""; reach.Clear();                        // 原版 4631：只过滤 bu 状态，没有"已行动"锁
        }
        Refresh();
    }

    void Command(int i)
    {
        if (over) return;
        if (enemyTurn) { hintText.text = "敌军行动中..."; return; }
        if (sel == null) { hintText.text = "先点一支我方部队"; return; }
        if (mode == "move")                          // 移动态下六个键只剩「确定」「取消」两个意思
        {
            if (i == 0) { EndMove(true); Refresh(); }
            else if (i == 1) CancelMove();
            return;
        }
        switch (i)
        {
            case 0:                                  // 移动
                BeginMove(); break;
            case 1:                                  // 计策：原版 b.d(X) 算出可用几种，弹单选
                mode = ""; OpenPlotMenu(); break;
            case 2:                                  // 攻击
                mode = "attack";
                hintText.text = HasFoeAdjacent(sel) ? "点相邻的敌将" : "邻近无敌将!";
                if (!HasFoeAdjacent(sel)) mode = "";
                break;
            case 3: mode = "info"; hintText.text = "请选择要侦察的武将"; break;   // 原版原话
            case 4:                                  // 撤退：原版 b.g() → 弹相邻己方城/空城，非主公多一项下野
                mode = ""; OpenRetreatMenu(); break;
            case 5: hintText.text = "我军待机中..."; sel = null; mode = ""; EndDay(); break;   // 原版 4713：aq=1，整军交回合
        }
        Refresh();
    }

    // ── 落到建筑格：原版 c.java 4766 j(格) → 「是否进入村庄?」→ G() 按格分派 ──
    //   4 疗伤点：C() 体力满→「当前武将毋需治疗!」，金 <50→「金子不够,无法进行治疗!」，
    //             否则「是否对武将进行治疗?」→ 金 −50、体力 += bI() = 35 + rnd(17)，钳到上限，「武将体力恢复了」
    //   1 武器村：aq=5 → al()/an() 外层 aj() → 屏 17 战斗版武器店（货架档 z[战场城]，从战场金 bc 付，装给进村那员）
    //   3 金粮村：aq=6 → ak() → 屏 16 战斗版金粮兑换（战场金 bc / 粮 bd，每进一次成交一笔）
    //   没有次数限制、不耗机动力、不结束这一日（关掉结果回「选择武将下命令」）。界面由 OnVillage 交给外面开。
    public System.Action<Unit, int> OnVillage;          // (进村那员, 地形码 1 / 3)
    public int VillageTier = -1;                        // 原版 z[战场城]，cities.csv 第 22 列；<0 = 这张图没有武器村
    void OnLanded(Unit u)
    {
        int code = TerrainAt(u.X, u.Y) & 0xF;
        if (code != 1 && code != 3 && code != 4) return;
        Ask("是否进入村庄?", () => {
            if (code == 4) { HealPrompt(u); return; }
            if (OnVillage != null) OnVillage(u, code);
            else hintText.text = "退出村庄!";
        });
    }
    /// 村里买完 / 换完：顶栏金粮刷新，底栏提示原版那句话（W 10~14）
    public void VillageDone(string msg) { hintText.text = msg; RefreshTop(); Refresh(); }
    void HealPrompt(Unit u)
    {
        if (u.O.Hp >= u.O.HpMax) { hintText.text = "当前武将毋需治疗!"; return; }
        if (supply[0] < 50) { hintText.text = "金子不够,无法进行治疗!"; return; }
        Ask("是否对武将进行治疗?", () => {
            supply[0] -= 50;
            u.O.Hp = Mathf.Clamp(u.O.Hp + 35 + rng.Next(17), 0, u.O.HpMax);
            hintText.text = "武将体力恢复了";
            RefreshTop(); Refresh();
        });
    }
    // 是 / 否 小弹框（原版底栏 V 那一行 + 左右软键）
    RectTransform askPop;
    /// 弹窗背后的透明全屏垫子：点到弹窗外面 = 关掉（等于按「否」/ 不施展）
    RectTransform Shade(string n, System.Action onTap)
    {
        var sh = Node(n, Root); Fill(sh);
        var im = sh.gameObject.AddComponent<Image>(); im.color = new Color(0, 0, 0, 0); im.raycastTarget = true;
        var b = sh.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        b.onClick.AddListener(() => onTap());
        return sh;
    }
    RectTransform askShade, plotShade, infoShade;
    void CloseAsk() { if (askPop != null) Object.Destroy(askPop.gameObject); askPop = null; if (askShade != null) Object.Destroy(askShade.gameObject); askShade = null; }
    void Ask(string q, System.Action yes)
    {
        CloseAsk();
        float w = 280, h = 96;
        askShade = Shade("askShade", () => { CloseAsk(); hintText.text = "选择武将下命令"; });
        askPop = Node("ask", Root);
        Place(askPop, (refW - w) / 2, refH - 100 - h - 10, w, h);
        var bg = Panel(askPop, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        var t = Label(askPop, q, 15, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 10, w, 26);
        void Btn(string txt, float x, System.Action act)
        {
            var b = Node("b" + txt, askPop); Place(b, x, 50, 110, 34);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = BattleUi.BtnSp(); bi.type = Image.Type.Sliced;
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.onClick.AddListener(() => { CloseAsk(); act?.Invoke(); });
            var l = Label(b, txt, 15, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter); Fill(l.rectTransform);
        }
        Btn("是", 22, yes); Btn("否", w - 22 - 110, null);
        hintText.text = q;
    }
    Text goldMe, goldFoe;
    void RefreshTop()
    {
        if (goldMe  != null) goldMe.text  = "金 " + supply[0] + "  粮 " + supply[1];
        if (goldFoe != null) goldFoe.text = "金 " + supply[2] + "  粮 " + supply[3];
    }


    // ── 计策 ───────────────────────────────────────────────────────────
    RectTransform plotMenu; int plotSel = -1, plotHover = -1;
    readonly HashSet<int> ambush = new HashSet<int>();          // 十面埋伏埋下的格（y*W+x）
    Text plotDescT, plotDescTitle; readonly List<Image> plotBtnImgs = new List<Image>();
    void ClosePlotMenu() { if (plotMenu != null) Object.Destroy(plotMenu.gameObject); plotMenu = null; plotHover = -1; plotBtnImgs.Clear();
                           if (plotShade != null) Object.Destroy(plotShade.gameObject); plotShade = null; }

    /// 原版 c.P()：一次一条的走马灯——上半是说明 d.q、下面黄字是当前计策名、确认才施。
    /// 触屏改成 4×4 全列出来，但两段式保留：第一下选中并显示说明，再点同一条才进选落点。
    void OpenPlotMenu()
    {
        ClosePlotMenu();
        int n = BattleRules.PlotCount(sel.O);
        const int COLS = 4, ROW = 34, DESC = 74; int rows = (n + COLS - 1) / COLS;
        float w = refW - 20, h = 34 + rows * ROW + 6 + DESC + 8;
        plotShade = Shade("plotShade", () => { ClosePlotMenu(); hintText.text = "选择武将下命令"; });   // 点菜单外面 = 不施展
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
                // 射程 / 地形内一个能施的敌将都没有：直接说，不进选落点（十面埋伏是埋自己脚下，另算）
                if (k != 11 && !HasPlotTarget(k))
                { plotSel = -1; mode = ""; hintText.text = BattleRules.PlotName[k] + "：施展范围内没有敌军!"; Refresh(); return; }
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
        plotDescT.text = BattleRules.PlotDesc[k]; Cjk(plotDescT);
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
        if (plotCache.Count >= 24)                                  // 只留最近三条计策的帧，旧的释放
        {
            foreach (var s in plotCache.Values) if (s != null) { Object.Destroy(s.texture); Object.Destroy(s); }
            plotCache.Clear();
        }
        var t = new Texture2D(2, 2, TextureFormat.RGBA32, false); t.LoadImage(File.ReadAllBytes(p)); t.filterMode = FilterMode.Point; t.Apply(false, true);
        return plotCache[k] = Whole(t);
    }
    /// caster / target 是我们加的：原版弹框只写「使用计策: ○○」，
    /// 看不出是谁对谁放的——一屏十几支部队时根本认不出来。
    /// 目标为空（十面埋伏埋在自己脚下）就只写施计者。
    void ShowPlotPop(int plot, string result, Unit caster = null, Unit target = null)
    {
        ClosePlotPop();
        Sfx.Plot(plot, result == null || !result.Contains("失败"));             // 火 / 水 / 落石 / 箭 / 通用，失败是一声闷响
        plotFrames = new Sprite[8]; int have = 0;
        for (int f = 0; f < 8; f++) { plotFrames[f] = PlotFrame(plot, f); if (plotFrames[f] != null) have++; }
        if (have == 0) plotFrames = null;
        string who = null;
        if (caster != null)
            who = target != null ? caster.O.Name + "  ▶  " + target.O.Name
                                 : caster.O.Name;
        float top = who != null ? 52 : 34;
        float w = refW - 28, ph = (w - 16) * 384f / 512f, h = top + ph + 40;
        plotPop = Node("ppop", Root);
        Place(plotPop, 14, (refH - h) / 2 - 30, w, h);
        var bg = Panel(plotPop, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        bg.raycastTarget = true;                                   // 点一下收，原版是按键
        var bt = plotPop.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
        bt.onClick.AddListener(() => { ClosePlotPop(); Refresh(); });
        var t = Label(plotPop, "使用计策: " + BattleRules.PlotName[plot], 15, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 8, w, 22);
        if (who != null)
        {
            // 施计者一律我方色、目标一律敌方色说不通（笼络能反向），所以这行统一用浅色，
            // 只靠箭头表达方向。
            var wl = Label(plotPop, who, 13, new Color32(0xD8,0xD0,0xC0,255), TextAnchor.MiddleCenter);
            Place(wl.rectTransform, 0, 30, w, 18);
        }
        plotPic = Node("pic", plotPop).gameObject.AddComponent<Image>();
        Place((RectTransform)plotPic.transform, 8, top, w - 16, ph);
        plotPic.raycastTarget = false;
        if (plotFrames != null) plotPic.sprite = plotFrames[0]; else plotPic.color = new Color(.1f, .08f, .06f, 1);
        Hud.CgFrame((RectTransform)plotPic.transform);                          // 动画外沿的细画框
        var r = Label(plotPop, result, 15, Color.white, TextAnchor.MiddleCenter);
        Place(r.rectTransform, 0, top + ph + 8, w, 24);
        plotT0 = Time.time;
    }
    void ClosePlotPop() { if (plotPop != null) Object.Destroy(plotPop.gameObject); plotPop = null; plotFrames = null; }
    /// 每帧：弹框里的图循环播（第八帧接回第一帧）
    /// 原版放完计策是弹一句 bD 停在那儿等按键（W=100 → aq=1）。我们照着停，
    /// 但触屏上不能指望玩家知道要点一下，所以到点自己收；想提前跳过就点一下。
    const float PLOT_POP_HOLD = 2.6f;
    public void PlotPopTick()
    {
        if (plotPop == null) return;
        if (plotFrames != null)
        {
            var sp = plotFrames[(int)((Time.time - plotT0) / PLOT_FRAME) % 8];
            if (sp != null && plotPic.sprite != sp) plotPic.sprite = sp;
        }
        if (Time.time - plotT0 > PLOT_POP_HOLD) { ClosePlotPop(); Refresh(); }
    }

    int TerrainAt(int x, int y) => grid[y, x] & 0x1F;
    bool HasAllyAdjacent(Unit u)
    {
        foreach (var o in units) if (o != u && o.Alive && o.Ours == u.Ours && Adjacent(o, u.X, u.Y)) return true;
        return false;
    }

    /// c.H() 的后半段：落点 → 合法 → roll → 结算 → 扣机动力
    /// 这条计策现在能不能落到任何一个敌将头上（和落点态亮红框用同一套判定）
    bool HasPlotTarget(int plot)
    {
        foreach (var o in units)
            if (!o.Ours && o.Alive && o.Trapped == 0 &&
                BattleRules.PlotLegal(plot, sel.X, sel.Y, o.X, o.Y, TerrainAt(sel.X, sel.Y), TerrainAt(o.X, o.Y), HasAllyAdjacent(o)))
                return true;
        return false;
    }

    void DoPlot(int x, int y)
    {
        int plot = plotSel; mode = ""; plotSel = -1;
        int cost = BattleRules.CostOfPlot(plot);
        if (plot == 11)                                                        // 十面埋伏：埋在自己脚下的树丛格
        {
            if (TerrainAt(sel.X, sel.Y) == 5 && sel.O.Troops >= 100)
            {
                ambush.Add(sel.Y * W + sel.X); sel.O.Troops -= 100; mp -= cost;
                hintText.text = sel.O.Name + " 使用计策: 十面埋伏"; ShowPlotPop(11, "埋伏完毕", sel);
                sel.Acted = true; curX = sel.X; curY = sel.Y;                  // 施计的人留着选中，好接着连施
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
        int before = tgt.O.Troops;                                             // 原版 4931 先记下目标兵力
        if (r.Ok) ApplyPlot(plot, r, tgt);
        BattleRules.GainExp(sel.O, before - tgt.O.Troops);                     // 4934：差额进施计者的经验，再 f() 升级
        hintText.text = sel.O.Name + " 对 " + tgt.O.Name + " 使用计策: " + BattleRules.PlotName[plot];
        ShowPlotPop(plot, msg, sel, tgt);                                      // 原版 P 在 roll 之前就置 true：失败也弹图
        // 施计的人留着选中、光标回到他身上：原版没有「一日一动」的锁，机动力够就能直接再按「计策」连施
        sel.Acted = true; curX = sel.X; curY = sel.Y;
    }

    /// b.a(short,byte[],byte,byte,int,boolean)
    void ApplyPlot(int plot, BattleRules.PlotResult r, Unit tgt, bool casterOurs = true)
    {
        void Hurt(Unit u, int n) { u.O.Troops = Mathf.Max(0, u.O.Troops - n); if (u.O.Troops <= 0) u.Alive = false; }
        bool tgtSide = tgt.Ours;                                   // 波及的是目标同一边的相邻部队
        int foodIdx = casterOurs ? 3 : 1;                          // 火箭减的是对方的粮
        switch (r.Type)
        {
            case 1: Hurt(tgt, r.Troops); break;
            case 2: tgt.O.Hp = Mathf.Max(0, tgt.O.Hp - r.Hp); break;
            case 3: supply[foodIdx] = Mathf.Max(0, supply[foodIdx] - r.Troops); RefreshTop(); break;
            case 4: Hurt(tgt, r.Troops); tgt.O.Hp = Mathf.Max(0, tgt.O.Hp - r.Hp); break;
            // 笼络：整支转到施计的一边。原版 h() 成功之后还要调 m(s2) 把忠诚重掷一遍
            case 5: tgt.Ours = casterOurs; tgt.Dir = "s"; tgt.O.Loyal = BattleRules.Rebind(tgt.O.Loyal, rng); break;
            case 6:
                Hurt(tgt, r.Troops);
                foreach (var o in units) if (o != tgt && o.Alive && o.Ours == tgtSide && o.Trapped == 0 && Adjacent(o, tgt.X, tgt.Y)) Hurt(o, r.Troops);
                break;
            case 7: tgt.Trapped = TRAP_DAYS; break;
            case 8:
                tgt.Trapped = TRAP_DAYS;
                foreach (var o in units) if (o != tgt && o.Alive && o.Ours == tgtSide && o.Trapped == 0 && Adjacent(o, tgt.X, tgt.Y)) o.Trapped = TRAP_DAYS;
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

    // ── 撤退（原版 b.g / j / i + c.c(Graphics)）───────────────────────
    // 候选 = 相邻城里「己方且城内武将不满 10」或「空城」；末尾一格红字「下野」只给非主公；
    // 主公无城可退 → 「无城可退，背水一战吧」。选城后武将带着兵进城（空城顺手占了），没有兵力损失。
    RectTransform retreatPop;
    Dictionary<int, int> cityRuler; Dictionary<int, int> cityCount; int[] adjCities; HashSet<int> rulerIds;
    readonly int cityId;
    void LoadCityData()
    {
        if (cityRuler != null) return;
        if (World != null)
        {
            cityRuler = World.Rulers(); cityCount = World.Counts(); adjCities = World.Adjacent(cityId);
            rulerIds = new HashSet<int>(cityRuler.Values); rulerIds.Remove(0);
            cityRuler.TryGetValue(cityId, out origOwner);
            return;
        }
        cityRuler = BattleRules.LoadCityRulers(Path.Combine(BattleGame.BattleDir, "cities.csv"));
        adjCities = BattleRules.AdjacentCities(Path.Combine(BattleGame.BattleDir, "city-adjacency.json"), cityId);
        rulerIds = new HashSet<int>(cityRuler.Values);
        cityCount = new Dictionary<int, int>();
        foreach (var o in BattleRules.LoadOfficers(Path.Combine(BattleGame.BattleDir, "officers.csv")).Values)
            if (o.City > 0) cityCount[o.City] = cityCount.TryGetValue(o.City, out int n) ? n + 1 : 1;
        cityRuler.TryGetValue(cityId, out origOwner);                 // 开战时的城主：城易主之后撤退判断还按它
    }
    /// 敌方主公：我方攻城时 = 这座城开战时的城主；守城时 = 来犯那家（Game 开战时塞进 FoeRulerId）
    public int FoeRulerId;
    int origOwner = -1;
    int FoeRuler()
    {
        LoadCityData();
        return FoeRulerId > 0 ? FoeRulerId : origOwner;
    }
    int OurRuler()
    {
        LoadCityData();
        if (rulerId > 0) return rulerId;                    // 明传的优先：189 年刘备一座城都没有，靠城主表推不出来
        foreach (var u in units) if (u.Ours && rulerIds.Contains(u.O.Id)) return u.O.Id;
        return -1;
    }
    List<int> RetreatCities()
    {
        LoadCityData(); int ruler = OurRuler();
        var list = new List<int>();
        foreach (int c in adjCities)
        {
            cityRuler.TryGetValue(c, out int owner); cityCount.TryGetValue(c, out int cnt);
            if ((owner == ruler && cnt < 10) || owner == 0) list.Add(c);      // b.j()：aZ == i[城] && r[城] < 10 || i[城] == 0
        }
        return list;
    }
    void CloseRetreatMenu() { if (retreatPop != null) Object.Destroy(retreatPop.gameObject); retreatPop = null; }
    void OpenRetreatMenu()
    {
        CloseRetreatMenu(); CloseInfo(); ClosePlotMenu();
        var cs = RetreatCities();
        bool isRuler = sel.O.Id == OurRuler();
        if (cs.Count == 0 && isRuler)
        {
            if (captured)                                                       // W == 9：城没了、主公也无处可去 → 这支就没了
            { hintText.text = "君主所在城市被攻占,无可退城市!"; Dead.Add(sel.O); sel.Alive = false; sel = null; Refresh(); AfterRetreat(); }
            else hintText.text = "无城可退，背水一战吧";                        // W == 18
            return;
        }
        hintText.text = "撤退武将 " + sel.O.Name;
        int n = cs.Count + (isRuler ? 0 : 1);                                  // 下野那格：P != 100 才有
        const int COLS = 3, ROW = 36; int rows = (n + COLS - 1) / COLS;
        float w = 300, h = 38 + rows * ROW + 8;
        retreatPop = Node("rt", Root);
        Place(retreatPop, (refW - w) / 2, refH - 100 - h - 8, w, h);
        var bg = Panel(retreatPop, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        var t = Label(retreatPop, "退往何处", 14, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 8, w, 22);
        float bw = (w - 16) / COLS;
        for (int i = 0; i < n; i++)
        {
            bool xy = i == cs.Count; int city = xy ? -1 : cs[i];
            var b = Node("c" + i, retreatPop);
            Place(b, 8 + (i % COLS) * bw + 2, 38 + (i / COLS) * ROW, bw - 4, ROW - 6);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = BattleUi.BtnSp(); bi.type = Image.Type.Sliced;
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.onClick.AddListener(() => DoRetreat(city));
            cityRuler.TryGetValue(city, out int owner);
            string nm = xy ? "下野" : NameOf(city) + (owner == 0 ? "(空)" : "");
            var l = Label(b, nm, 14, xy ? new Color32(0xF8,0x5A,0x52,255) : new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);   // 下野原版画红
            Fill(l.rectTransform);
        }
    }
    void DoRetreat(int city)
    {
        CloseRetreatMenu();
        if (city > 0)
        {
            cityRuler.TryGetValue(city, out int owner);
            string nm = NameOf(city);
            hintText.text = sel.O.Name + (owner == 0 ? " 退入空城 " + nm + "，就势占了" : " 退往 " + nm);
            cityCount[city] = cityCount.TryGetValue(city, out int c) ? c + 1 : 1;             // b.a(short,byte,short)：r[城]++
            if (owner == 0) cityRuler[city] = OurRuler();                                       // 空城 → i[城] = 我方主公
        }
        else hintText.text = sel.O.Name + " 下野";
        RetreatTo[sel.O] = city > 0 ? city : 0;
        sel.Alive = false; sel.Acted = true;                                                  // 兵跟着人走，没有损失
        sel = null; Refresh();
        AfterRetreat();
    }

    // ── 情报（原版 c.F() + a(Graphics)）───────────────────────────────
    // 光标点到哪一支（敌我都行）就弹「武将信息」：头像、名、LV、武/智、体/德、兵/忠、武器/防具。
    // 不扣机动力、不算行动——原版 F() 里没有 bk 减法，也不置 Acted。
    RectTransform infoPop;
    void CloseInfo() { if (infoPop != null) Object.Destroy(infoPop.gameObject); infoPop = null; if (infoShade != null) Object.Destroy(infoShade.gameObject); infoShade = null;
                       if (infoCardTex != null) { Object.Destroy(infoCardTex); infoCardTex = null; } }
    static Sprite AvatarOf(int id)
    {
        var t = TryLoad(Path.Combine("battle", "avatar", BattleRules.Face(id) + ".jpg"));     // 缓存：以前每点一次「情报」新读一张
        return t != null ? Whole(t) : null;
    }
    /// 武将信息里那张整卡：用 576×864 高清卡面（小卡只有 192×288，放到半屏发虚）；不进缓存，关框就放掉
    Texture2D infoCardTex;
    Sprite CardOf(int id)
    {
        string f = BattleRules.Face(id) + ".jpg";                                             // 自建武将按他挑的那张脸找卡面
        if (infoCardTex != null) { Object.Destroy(infoCardTex); infoCardTex = null; }
        infoCardTex = TryLoadFresh(Path.Combine("battle", "card_hd", f)) ?? TryLoadFresh(Path.Combine("battle", "card", f));
        return infoCardTex != null ? Whole(infoCardTex) : null;
    }
    void ShowInfo(Unit u)
    {
        CloseInfo(); mode = "";
        hintText.text = "武将信息";
        var o = u.O;
        float w = 340, h = 514;                       // 卡牌整张立在左上，属性条在下（经验多占一行）
        infoShade = Shade("infoShade", () => { CloseInfo(); Refresh(); });
        infoPop = Node("info", Root);
        Place(infoPop, (refW - w) / 2, (refH - h) / 2 - 36, w, h);
        var bg = Panel(infoPop, "bg", BattleUi.PlateSp()); Fill((RectTransform)bg.transform);
        bg.raycastTarget = true;
        var bt = infoPop.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
        bt.onClick.AddListener(() => { CloseInfo(); Refresh(); });
        // 点卡面任何地方都能收，但没人一眼看得出来，右上角再放一个明的关闭键
        var cb = Node("close", infoPop).gameObject.AddComponent<Image>();
        Place((RectTransform)cb.transform, w - 40, 8, 32, 32);
        cb.sprite = BattleUi.BtnSp(); cb.type = Image.Type.Sliced; cb.raycastTarget = true;
        var cl = Label(infoPop, "×", 22, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(cl.rectTransform, w - 40, 6, 32, 32);
        var cbt = cb.gameObject.AddComponent<Button>(); cbt.transition = Selectable.Transition.None;
        cbt.onClick.AddListener(() => { CloseInfo(); Refresh(); });

        // 上半：整张卡牌不裁，立在左边（150×225，1024×1536 的比例），錾金框套在卡外；
        // 右边是名、LV、兵种、兵力。原来 cover 裁上半身，看不全。
        float ax = 16, ay = 16, cwid = 150, chei = 225;
        var card = CardOf(o.Id);
        if (card != null)
        {
            var ci = Node("card", infoPop).gameObject.AddComponent<Image>();
            Place((RectTransform)ci.transform, ax, ay, cwid, chei); ci.sprite = card; ci.raycastTarget = false;
            AlbumView.BadgeOn((RectTransform)ci.transform, o, cwid);                  // 天下霸图的评级角标
        }
        // 卡外一圈细金边：头像框那张没有九宫格 border（量的是洞不是边），拉伸会拼成十字纹；
        // 用木牌底图（border 3，四边是金线脚）垫在卡下面、比卡大一圈，露出的就是那圈金线。
        var cf = Node("cf", infoPop).gameObject.AddComponent<Image>();
        Place((RectTransform)cf.transform, ax - 4, ay - 4, cwid + 8, chei + 8);
        cf.sprite = BattleUi.PlateSp(); cf.type = Image.Type.Sliced; cf.color = new Color(1.4f, 1.25f, .9f, 1); cf.raycastTarget = false;
        cf.transform.SetSiblingIndex(1);                                              // 压在面板底之上、卡之下
        float ah = chei;
        float tx = ax + cwid + 16;
        var nm = Label(infoPop, o.Name, 24, u.Ours ? new Color32(0x9C,0xC8,0xFF,255) : new Color32(0xFF,0x98,0x90,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(nm.rectTransform, tx, ay + 8, 150, 34);
        var ns = nm.gameObject.AddComponent<Shadow>(); ns.effectColor = new Color(0, 0, 0, .9f); ns.effectDistance = new Vector2(1.5f, -1.5f);
        string[] side = { "LV. " + o.Lv, (o.Kind == Kind.Ping ? "平军" : o.Kind == Kind.Shui ? "水军" : "山军"), "兵力 " + o.Troops };
        for (int i = 0; i < side.Length; i++)
        {
            var lv = Label(infoPop, side[i], 15, new Color32(0xF0,0xE0,0xC0,255));
            Place(lv.rectTransform, tx, ay + 54 + i * 28, 150, 24);
        }

        // 下半：五项属性做成条——槽 + 染色填充，数字压在槽里右端
        // 经验这一行的条画的是「本级已走了多少」，不是 0..30000——
        // 满级 30000 的条上，一级的跨度只有几个像素，看不出进度。
        int expFloor = BattleRules.ExpFloor(o.Lv), expNext = BattleRules.ExpToNext(o.Exp, o.Lv);
        string[] tags = { "武", "智", "体", "德", "忠", "经" };
        int[] vals = { o.Might, o.Int, o.Hp, o.Virtue, o.Loyal,
                       expNext > 0 ? o.Exp - expFloor : 1 };               // 满级条拉满
        int[] maxs = { 100, 100, Mathf.Max(o.HpMax, 1), 100, 100,
                       expNext > 0 ? Mathf.Max(1, o.Exp - expFloor + expNext) : 1 };
        Color[] cols = { new Color(1f, .45f, .35f), new Color(.45f, .7f, 1f), new Color(.5f, .9f, .5f),
                         new Color(1f, .85f, .45f), new Color(.85f, .6f, 1f), new Color(1f, .72f, .30f) };
        float y0 = ay + ah + 20; const float BW = 206f, BH = 20f, ROW = 32f;
        var gm = BattleUi.Metric("bar"); float pad = gm != null && gm.Length > 0 ? Mathf.Max(2, gm[0] * .6f) : 3f;
        for (int r = 0; r < 6; r++)
        {
            float yy = y0 + r * ROW;
            var tag = Node("tg", infoPop).gameObject.AddComponent<Image>();
            Place((RectTransform)tag.transform, 18, yy - 2, 36, BH + 4); tag.sprite = BattleUi.BtnSp(); tag.type = Image.Type.Sliced; tag.raycastTarget = false;
            var tl = Label(infoPop, tags[r], 14, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter); Place(tl.rectTransform, 18, yy - 2, 36, BH + 4);
            float bx = 64;
            var groove = Node("gv", infoPop).gameObject.AddComponent<Image>();
            Place((RectTransform)groove.transform, bx, yy, BW, BH);
            var gs = BattleUi.Get("bar"); if (gs != null) { groove.sprite = gs; groove.type = Image.Type.Sliced; } else groove.color = new Color(0, 0, 0, .6f);
            groove.raycastTarget = false;
            var fill = Node("fl", infoPop).gameObject.AddComponent<Image>();
            float frac = Mathf.Clamp01(vals[r] / (float)maxs[r]);
            Place((RectTransform)fill.transform, bx + pad, yy + pad, (BW - pad * 2) * frac, BH - pad * 2);
            var fs = BattleUi.Get("barfill"); if (fs != null) { fill.sprite = fs; fill.type = Image.Type.Sliced; }
            fill.color = cols[r]; fill.raycastTarget = false;
            string vs2 = r == 2 ? o.Hp + "/" + o.HpMax
                       : r == 5 ? (expNext > 0 ? o.Exp + "   升级还差 " + expNext : o.Exp + "   已满级")
                       : vals[r].ToString();
            var vt = Label(infoPop, vs2, 14, Color.white, TextAnchor.MiddleRight);
            Place(vt.rectTransform, bx, yy, BW - 8, BH);
            var vs = vt.gameObject.AddComponent<Shadow>(); vs.effectColor = new Color(0, 0, 0, .9f); vs.effectDistance = new Vector2(1, -1);
        }
        // 武器 / 防具
        float ey = y0 + 6 * ROW + 6;
        string[][] eq = { new[] { "武器", BattleRules.WeaponOf(o) }, new[] { "防具", BattleRules.ArmorOf(o) } };
        for (int i = 0; i < 2; i++)
        {
            float ex = 18 + i * 154;
            var tag = Node("eq", infoPop).gameObject.AddComponent<Image>();
            Place((RectTransform)tag.transform, ex, ey, 44, 24); tag.sprite = BattleUi.BtnSp(); tag.type = Image.Type.Sliced; tag.raycastTarget = false;
            var tl = Label(infoPop, eq[i][0], 13, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter); Place(tl.rectTransform, ex, ey, 44, 24);
            var vt = Label(infoPop, eq[i][1], 15, Color.white); Place(vt.rectTransform, ex + 52, ey, 100, 24);
        }
    }

    // ── 攻城的胜负：原版是不对称的 ──────────────────────────────────────
    //   攻方赢 = 守军一个不剩（死 / 俘 / 退，b.java 2984：bj==0 → D() 城易主，"X 军攻占了 Y!"）。
    //   玩家攻城**没有**"踩上城池格就赢"这一说（c.java 里没查过码 2），得把太守打掉。
    //   守方输 = 敌军任一支踩上城池格（b.k(by) 3834，只在敌军回合查）→「敌军占领我方城池!」→
    //            我方余将逐一「撤退武将 X」选相邻城 / 下野（A()），主公无城可退 →「君主所在城市被攻占,无可退城市!」。
    bool WeAttack { get { LoadCityData(); cityRuler.TryGetValue(cityId, out int o); return forceDefend ? false : o != OurRuler(); } }
    bool forceDefend;                      // 自检用：演示局里我们是攻方，硬当守方试失城那条路
    readonly int[] supply0;
    bool over, captured, retreating;
    string endMsg;                         // 逐将撤完之后报哪一句
    bool loseCityAfterRetreat;
    readonly Queue<Unit> retreatQ = new Queue<Unit>();
    RectTransform overPop;
    int AliveCount(bool ours) { int n = 0; foreach (var u in units) if (u.Ours == ours && u.Alive) n++; return n; }
    // 家规：主公一旦打到旗下一个武将都不剩，忠诚自动掉到 60。
    //   原版主公忠诚恒 100，Y() 围捕和 k() 劝降都卡 P > 99，等于永远擒不住也劝不动。
    //   玩家的设定是「孤家寡人就该动摇」——这样原版那两道门原样留着，家规自己把它打开：
    //   降到 60 之后照样吃「忠诚 ≥ 80 减 10」那档惩罚，体力也还得先打到 40 以下，不算好抓。
    const int LONE_RULER_LOYAL = 60;
    void UpdateLoneRuler()
    {
        LoadCityData();
        for (int side = 0; side < 2; side++)
        {
            bool ours = side == 0;
            Unit ruler = null; int alive = 0;
            foreach (var u in units)
            {
                if (u.Ours != ours || !u.Alive) continue;
                alive++;
                if (u.O.Id == rulerId || rulerIds.Contains(u.O.Id)) ruler = u;
            }
            if (ruler == null || alive != 1 || ruler.O.Loyal <= LONE_RULER_LOYAL) continue;
            ruler.O.Loyal = LONE_RULER_LOYAL;
            hintText.text = ruler.O.Name + " 众叛亲离,军心动摇!";
        }
    }

    void CheckOver()
    {
        UpdateLoneRuler();
        if (over || retreating) return;
        string cn = NameOf(cityId);
        if (AliveCount(false) == 0)
        {
            if (WeAttack) SettleCity(true, "我军攻占了 " + cn + "!");     // 攻方全歼守军 → L()
            else ShowOver("敌军攻打 " + cn + " 失败了!");                 // 守住了，城本来就是我方的
        }
        else if (AliveCount(true) == 0)
        {
            if (WeAttack) ShowOver("我军攻打 " + cn + " 失败了!");
            else SettleCity(false, "敌军占领了 " + cn + "!");              // 我方守军打光 → D()
        }
    }
    void ShowOver(string msg)
    {
        // 胜负：我方丢城 / 粮尽 / 全军撤退是败，其余（攻下、守住、敌军逃窜）是胜
        bool lost = CityFell == -1 || msg.Contains("我军粮草") || msg.Contains("敌军占领") || msg.Contains("我军攻打") || msg.Contains("全军撤退");
        Sfx.Play(lost ? "gong_lose" : "gong_win");
        over = true; enemyTurn = false; sel = null; mode = ""; reach.Clear();
        CloseAsk(); CloseInfo(); CloseRetreatMenu(); ClosePlotMenu();
        hintText.text = msg;
        // 「敌方全军逃窜! 我军攻占了 洛阳!  入库 金 1650 粮 1640」这种一行摆不下，
        // 得让它自己折行、框再跟着内容长高，不能写死 320×70。
        float w = Mathf.Min(refW - 48, 340), pad = 14;
        overPop = Node("over", Root);
        var bg = Panel(overPop, "bg", BattleUi.PlateSp()); bg.raycastTarget = true;
        var t = Label(overPop, msg, 17, new Color32(0xF0,0xC8,0x70,255), TextAnchor.MiddleCenter);
        t.horizontalOverflow = HorizontalWrapMode.Wrap;
        t.verticalOverflow = VerticalWrapMode.Truncate;
        t.lineSpacing = 1.15f;
        Place(t.rectTransform, pad, pad, w - pad * 2, 1000); Cjk(t);
        float th = Mathf.Ceil(t.preferredHeight);
        float h = Mathf.Max(64, th + pad * 2);
        Place(overPop, (refW - w) / 2, (refH - h) / 2 - 40, w, h);
        Fill((RectTransform)bg.transform);
        Place(t.rectTransform, pad, pad, w - pad * 2, h - pad * 2);
        if (OnQuit != null)                              // 接在正式游戏里：点结局框回城
        {
            var bt = bg.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.onClick.AddListener(() => OnQuit());
            var hint = Label(overPop, "点此返回", 12, new Color32(0xC8,0xB0,0x80,255), TextAnchor.MiddleCenter);
            Place(hint.rectTransform, 0, h - 18, w, 16);
        }
        Refresh();
    }
    /// 敌军踩上城池格：城易主，我方余将一个一个选退路
    void Captured()
    {
        captured = true; enemyTurn = false; plans = null;   // 城的归属等余将撤完了由 SettleCity 一并办
        ForceRetreatAll("敌军占领我方城池!", "敌军占领了 " + CityNameNow() + "!");      // W == 21
    }
    string CityNameNow() => NameOf(cityId);
    string NameOf(int c) => World != null ? World.Name(c) : BattleRules.CityName(Path.Combine(BattleGame.BattleDir, "city-adjacency.json"), c);
    /// 原版 c.java 4476（W = 7 / 9 / 20 / 21 共用）：A() 起头，我方余将一个一个「撤退武将 X」选退路
    void ForceRetreatAll(string reason, string end)
    {
        retreating = true; enemyTurn = false; plans = null; endMsg = end;
        loseCityAfterRetreat = !WeAttack || captured;     // 我方是守方、或城已被踩：人走光了城就归对面
        sel = null; mode = ""; reach.Clear();
        CloseAsk(); CloseInfo(); ClosePlotMenu();
        hintText.text = reason;
        retreatQ.Clear();
        foreach (var u in units) if (u.Ours && u.Alive) retreatQ.Enqueue(u);
        NextRetreat();
    }
    void NextRetreat()
    {
        while (retreatQ.Count > 0)
        {
            var u = retreatQ.Dequeue();
            if (!u.Alive) continue;
            sel = u; mode = ""; CenterOn(u.X, u.Y); curX = u.X; curY = u.Y;
            OpenRetreatMenu(); Refresh();
            return;
        }
        retreating = false;
        if (loseCityAfterRetreat) { loseCityAfterRetreat = false; SettleCity(false, endMsg); return; }
        ShowOver(endMsg ?? "敌军占领了 " + CityNameNow() + "!");
    }
    void AfterRetreat() { if (retreating) NextRetreat(); }
    /// 自检：把阵型战的某个结局直接喂给战棋层
    public void AfterClashForTest(int cc, bool commanders)
    {
        CloseAsk(); CloseInfo(); ClosePlotPop(); CloseRetreatMenu();
        var c0 = OurCommander0(); var f0 = FoeCommander0();
        Unit ours = c0, foe = f0;
        if (!commanders)
        {
            foreach (var u in units) if (u.Ours  && u.Alive && u != c0) { ours = u; break; }
            foreach (var u in units) if (!u.Ours && u.Alive && u != f0) { foe  = u; break; }
        }
        CenterOn(foe.X, foe.Y); curX = foe.X; curY = foe.Y;
        AfterClash(ours, foe, cc, 2500, 900);
    }
    public void TrapForTest(Unit u) { u.Trapped = 3; sel = null; mode = ""; OnBoardClickForTest(u.X, u.Y); }
    public void OnBoardClickForTest(int x, int y)
    {
        var here = At(x, y);
        if (here != null && here.Ours && here.Trapped > 0) hintText.text = "武将中计,无法动弹!";
        CenterOn(x, y); curX = x; curY = y; Refresh();
    }
    /// 自检：把敌方除主公外全部打掉，看忠诚是不是自动掉到 60
    readonly List<Unit> loneSaved = new List<Unit>();
    public void LoneRulerForTest()
    {
        LoadCityData(); CloseAsk(); CloseInfo(); ClosePlotPop();
        int r = -1;
        foreach (var u in units) if (!u.Ours && u.Alive && rulerIds.Contains(u.O.Id)) { r = u.O.Id; break; }
        loneSaved.Clear();
        foreach (var u in units)
            if (!u.Ours && u.Alive && u.O.Id != r) { loneSaved.Add(u); u.Alive = false; }
        UpdateLoneRuler();
        foreach (var u in units) if (!u.Ours && u.O.Id == r) { CenterOn(u.X, u.Y); ShowInfo(u); }
        Refresh();
    }
    public void LoneRulerResetForTest()
    {
        CloseInfo();
        foreach (var u in loneSaved) u.Alive = true;
        loneSaved.Clear(); Refresh();
    }
    /// 自检：直接判胜负，看战略层善后对不对。胜 = 守军全灭；败 = 我方全员撤往第一座能退的城（没有就下野）
    public void ForceOutcomeForTest(bool weWin)
    {
        LoadCityData(); CloseAsk(); CloseInfo(); ClosePlotPop();
        if (weWin)
        {
            bool first = true;
            foreach (var u in units) if (!u.Ours && u.Alive) { u.Alive = false; if (first) { Dead.Add(u.O); first = false; } }
            SettleCity(true, "我军攻占了 " + CityNameNow() + "!");
            return;
        }
        var cs = RetreatCities();
        foreach (var u in units) if (u.Ours && u.Alive) { RetreatTo[u.O] = cs.Count > 0 ? cs[0] : 0; u.Alive = false; }
        ShowOver("我军攻打 " + CityNameNow() + " 失败了!");
    }
    /// 自检：守城直接判——守住（攻方全灭）或失守（我方全员撤往第一座能退的城）
    public void ForceDefenseForTest(bool hold)
    {
        LoadCityData(); CloseAsk(); CloseInfo(); ClosePlotPop();
        if (hold) { foreach (var u in units) if (!u.Ours) u.Alive = false; ShowOver("敌军攻打 " + CityNameNow() + " 失败了!"); return; }
        var cs = RetreatCities();
        foreach (var u in units) if (u.Ours && u.Alive) { RetreatTo[u.O] = cs.Count > 0 ? cs[0] : 0; u.Alive = false; }
        SettleCity(false, "敌军占领了 " + CityNameNow() + "!");
    }
    public void CaptureForTest() { CloseAsk(); CloseInfo(); ClosePlotPop(); forceDefend = true; Captured(); }
    public void CaptureResetForTest() { captured = retreating = over = false; forceDefend = false; retreatQ.Clear(); System.Array.Copy(supply0, supply, 4); RefreshTop(); CloseRetreatMenu(); if (overPop != null) Object.Destroy(overPop.gameObject); overPop = null; sel = null; Refresh(); }

    // ── 阵型战散场之后：原版 ai() → a()/b()，再加 aa() 的计策点 ───────────
    //   cc 1/2 阵亡（体力被打光）、3/4 被擒、5/6 溃走（人还在，带着残兵回战棋层）。
    //   主将（双方各自的第一员）一旦阵亡或被擒：
    //     敌方主将没了 → W=5「敌方全军逃窜!」→ L() 城归我方，整场结束
    //     我方主将没了 → 余将逐一「撤退武将 X」选退路，都走光 → D() 城归敌方
    //   而且谁的主将倒了，谁手里的俘虏就地放还（原版 a()/b() 里把对方的状态 2 抹回 0）。
    public Unit OurCommander0()  { foreach (var u in units) if (u.Ours) return u; return null; }
    public Unit FoeCommander0()  { foreach (var u in units) if (!u.Ours) return u; return null; }
    readonly List<Unit> tookByUs = new List<Unit>(), tookByFoe = new List<Unit>();

    public void AfterClash(Unit ours, Unit foe, int cc, int killed, int lost, string ccText = null)
    {
        BattleRules.GainExp(ours.O, killed);                        // b.aa() 5463 → f()：对方掉的兵进 Q[]，够档位就升 Lv
        BattleRules.GainExp(foe.O, lost);
        var ourCmd = OurCommander0(); var foeCmd = FoeCommander0();
        string msg = null;
        switch (cc)
        {
            case 1: foe.Alive = false;  Dead.Add(foe.O);  msg = foe.O.Name + " 战死"; break;
            case 2: ours.Alive = false; Dead.Add(ours.O); msg = ours.O.Name + " 战死"; break;
            case 3: foe.Alive = false;  msg = Capture(foe, ours, true);  break;
            case 4: ours.Alive = false; msg = Capture(ours, foe, false); break;
            case 5: msg = foe.O.Name + " 溃走"; break;                // 人还在战棋层，只是这一仗打不下去了
            case 6: msg = ours.O.Name + " 溃走"; break;
        }
        if (ccText != null && (cc == 5 || cc == 6)) msg = ccText;               // 溃走带上「体力减少 N」
        if (msg != null) { hintText.text = msg; Banner(ccText != null && cc <= 2 ? ccText : msg); }   // 屏幕中间横幅，停两秒
        Refresh();
        if (cc == 1 || cc == 3)                                     // 敌方谁倒了
        {
            Release(tookByFoe, true);                               // 敌方手里的我方俘虏放还
            if (foe == foeCmd)
            {
                // 敌方主将倒了 → 敌方全军逃窜。我方攻城：城拿下；我方守城：守住了（以前两边都写成「我军攻占了 X」）
                if (WeAttack) TakeCity();
                else ShowOver("敌方全军逃窜! " + CityNameNow() + " 守住了!");
                return;
            }
        }
        if (cc == 2 || cc == 4)
        {
            Release(tookByUs, false);
            if (ours == ourCmd)
            {
                if (ours.O.Id == OurRuler()) { ShowOver("君主战亡!"); return; }   // W == 8
                ForceRetreatAll("主将战殁,全军撤退!", "敌军占领了 " + CityNameNow() + "!");
                return;
            }
        }
        CheckOver();
    }
    /// 被擒：原版 b.b(s, bl) 5864——状态记 2、从战棋图上拿掉（格子清空），**押在营中，不再上阵**；
    /// 仗打完才处理（K() 3353：进我方拿下的城，满 10 人进在野，忠诚按 b.m() 重掷）。
    /// 以前这里把俘虏换一边、立刻放回图上接着打，不对。
    /// 做法：换到擒方一边，但 Alive=false（不上图、不行动、不算兵力），结算时 Final() 取到的就是擒方这一边。
    string Capture(Unit taken, Unit captor, bool toUs)
    {
        taken.Alive = false;
        var o = taken.O;
        o.Loyal = BattleRules.Rebind(o.Loyal, rng);
        var held = new Unit { O = o, Ours = toUs, Alive = false, Dir = "e", Flip = !toUs, Trapped = 0, X = -1, Y = -1 };
        held.Draw = new Vector2(-1, -1);
        units.Add(held);
        (toUs ? tookByUs : tookByFoe).Add(held);
        return toUs ? "擒获敌将 " + o.Name + "!　押入营中" : "我方武将 " + o.Name + " 被俘!";
    }
    /// 主将倒了，手里的俘虏放还原主（原版把对方的状态 2 抹回 0）：回到原来那边，在原主将身边重新上阵
    void Release(List<Unit> took, bool backToUs)
    {
        var lead = backToUs ? OurCommander() : EnemyCommander();
        foreach (var u in took)
        {
            u.Ours = backToUs; u.Flip = !backToUs; u.Alive = true;
            u.O.Loyal = BattleRules.Rebind(u.O.Loyal, rng);
            var spot = lead != null ? FreeNear(lead.X, lead.Y) : new Vector2Int(0, 0);
            u.X = spot.x; u.Y = spot.y; u.Draw = new Vector2(spot.x, spot.y);
        }
        took.Clear();
    }

    // ── 屏幕中间的横幅：阵型战结果（擒获 / 击毙 / 逃窜 · 体力减少 N）停两秒 ──
    RectTransform banner; float bannerUntil;
    void Banner(string msg)
    {
        if (banner != null) Object.Destroy(banner.gameObject);
        float w = Mathf.Min(refW - 40, 340);
        banner = Node("banner", Root);
        var bg = Panel(banner, "bg", BattleUi.PlateSp()); bg.raycastTarget = false;
        var t = Label(banner, msg, 18, new Color32(0xF4, 0xD4, 0x8A, 255), TextAnchor.MiddleCenter, FontStyle.Bold);
        t.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(t.rectTransform, 14, 10, w - 28, 1000); Cjk(t);
        float h = Mathf.Max(56, Mathf.Ceil(t.preferredHeight) + 24);
        Place(banner, (refW - w) / 2, refH * .36f, w, h); Fill((RectTransform)bg.transform);
        Place(t.rectTransform, 14, 0, w - 28, h);
        bannerUntil = Time.time + 2.2f;
    }
    Vector2Int FreeNear(int x, int y)
    {
        for (int r = 1; r < 6; r++)
            for (int d = 0; d < 4; d++)
            {
                int nx = x + DX[d] * r, ny = y + DY[d] * r;
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                if (At(nx, ny) == null && BattleRules.MoveCost(grid[ny, nx] & 0xF, Kind.Ping) < 100)
                    return new Vector2Int(nx, ny);
            }
        return new Vector2Int(x, y);
    }
    /// 原版 L() + K()：城易主的善后。
    ///   L()：城主改成得城一方的主公、太守 j[城] 记成他家第一员；原势力城数 −1、得城方 +1，
    ///        原势力归零 → bG = 4（势力灭亡）。势力的城数组我们没有，只改城主和太守。
    ///   K() 3322：守军**第一个撤走的把城库里的金粮一起带走**，但只在 bl 为假时——
    ///        也就是守将还活着、而且真有城可退；守将倒了或无城可退，金粮就留给了攻方。
    ///   K() 3262：活着的部队进城，算这座城的驻军 r[城]。
    void SettleCity(bool oursTakes, string msg)
    {
        LoadCityData();
        bool kept;                                       // 守方把库里的金粮带走了没有
        CityFell = oursTakes ? 1 : -1;
        if (oursTakes)
        {
            int r = OurRuler(); if (r > 0) cityRuler[cityId] = r;
            var c0 = OurCommander0(); governorId = c0 != null ? c0.O.Id : 0;
            kept = AliveCount(false) > 0 && EnemyCanRetreat();
            if (!kept) { supply[0] += supply[2]; supply[1] += supply[3]; }
            else { KeptGold = supply[2]; KeptFood = supply[3]; }        // 守将带着撤走，战略层把它加进他退去的城
            supply[2] = supply[3] = 0;
            cityCount[cityId] = Mathf.Max(1, AliveCount(true));
        }
        else
        {
            int foeRuler = 0;
            foreach (var u in units) if (!u.Ours && u.Alive && rulerIds.Contains(u.O.Id)) foeRuler = u.O.Id;
            if (foeRuler != 0) cityRuler[cityId] = foeRuler;
            var f0 = FoeCommander0(); governorId = f0 != null ? f0.O.Id : 0;
            kept = AliveCount(true) > 0 && RetreatCities().Count > 0;
            if (!kept) { supply[2] += supply[0]; supply[3] += supply[1]; }
            else { OurKeptGold = supply[0]; OurKeptFood = supply[1]; }    // 第一个撤走的带着城库（i() 2947）
            supply[0] = supply[1] = 0;
            cityCount[cityId] = Mathf.Max(1, AliveCount(false));
        }
        RefreshTop();
        // 善后那一句另起一行：前一句是战果，后一句是账，挤在一行又长又难读
        ShowOver(msg + "\n" + (kept ? "(守军带走了库中金粮)"
                                     : "入库 金 " + (oursTakes ? supply[0] : supply[2])
                                       + "  粮 " + (oursTakes ? supply[1] : supply[3])));
    }
    int governorId;                                      // j[城]：这座城的太守
    void TakeCity() => SettleCity(true, "敌方全军逃窜! 我军攻占了 " + CityNameNow() + "!");

    void EndDay()
    {
        if (enemyTurn) return;
        enemyTurn = true;
        enemyMp = BattleRules.MoveCap(TotalTroops(false), enemyMp);   // 上回合没用完的接着攒
        enemyIdx = 0; plans = null; roundStartMp = -1; foeCalls++;
        sel = null; mode = ""; reach.Clear();
        hintText.text = "敌军行动中...";
        Refresh();
    }

    // ── 粮草与回合上限：原版 b.R() 3951 ────────────────────────────────
    //   每回合末双方各扣「存活总兵力 − 1 ⁄ 250 + 1」；先查我方、再查敌方。
    //   我方粮尽 → W=7「我军粮草耗竭,全军撤退!」→ 余将逐一选退路（这场就输了）
    //   敌方粮尽 → W=6「敌方粮草耗竭,已全军逃窜!」→ L() + aq=4（这场赢了）
    //   打满 30 回合（bA == 30）→ W=20「战争回合结束！」→ 同样余将逐一撤退
    const int MAX_DAY = 30;
    static int Upkeep(int troops) => troops <= 0 ? 1 : (troops - 1) / 250 + 1;
    bool EndRoundSupply()
    {
        supply[1] = Mathf.Max(0, supply[1] - Upkeep(TotalTroops(true)));
        RefreshTop();
        if (supply[1] <= 0)
        {
            ForceRetreatAll("我军粮草耗竭,全军撤退!", "我军粮草耗竭,全军撤退!");
            return true;
        }
        supply[3] = Mathf.Max(0, supply[3] - Upkeep(TotalTroops(false)));
        RefreshTop();
        if (supply[3] <= 0)
        {
            // 原版 W=6 → L()：我方攻城就拿下这座城；我方守城就是守住了
            if (WeAttack) SettleCity(true, "敌方粮草耗竭,已全军逃窜! 我军攻占了 " + CityNameNow() + "!");
            else ShowOver("敌方粮草耗竭,已全军逃窜!");
            return true;
        }
        return false;
    }
    public void SupplyForTest(int ourFood, int foeFood) { supply[1] = ourFood; supply[3] = foeFood; RefreshTop(); StartOurDay(); }

    void StartOurDay()
    {
        enemyTurn = false;
        ClosePlotPop();                                // 兜底：别把敌军回合的弹框留到我方回合
        if (EndRoundSupply()) return;                  // 粮尽：这场到此为止，不再开新的一日
        day++;
        if (day > MAX_DAY && !WeAttack)                // 守城撑满 30 日：原版 B10 也判守方撤退丢城，这里算守住，敌军撤退
        {
            ShowOver("战争回合结束！敌军撤退了");
            return;
        }
        if (day > MAX_DAY)                             // 原版 while (bA != 30)
        {
            ForceRetreatAll("战争回合结束！", "战争回合结束！");
            return;
        }
        foreach (var u in units)
        {
            u.Acted = false;
            if (u.Trapped > 0)
            {
                if (u.Cursed) { u.O.Troops = Mathf.Max(0, u.O.Troops - 100); if (u.O.Troops <= 0) u.Alive = false; }
                if (--u.Trapped == 0) u.Cursed = false;
            }
        }
        mp = BattleRules.MoveCap(TotalTroops(true), mp);              // 同上：机动力是攒的，不是每回合重置
        hintText.text = "回合结束，第 " + day + " 日";
        SelectLead();                                  // 新的一日：光标直接落在我方主将身上，不用先点他
        Refresh();
    }
    /// 选中我方主将（出征队伍第一位；他不在场了就挑下一位还在场的），镜头跟过去
    void SelectLead()
    {
        Unit lead = null;
        foreach (var u in units) if (u.Ours && u.Alive) { lead = u; break; }
        if (lead == null) return;
        sel = lead; mode = ""; reach.Clear();
        curX = lead.X; curY = lead.Y; CenterOn(lead.X, lead.Y);
        hintText.text = "第 " + day + " 日　已选中 " + lead.O.Name + "，请下命令";
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
        if (banner != null && Time.time > bannerUntil) { Object.Destroy(banner.gameObject); banner = null; }
        CheckOver();
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

        // 计策弹框还在，敌军就停着等——不然它一边放计策一边继续走，
        // 玩家既看不清结果，框还会一直挂到我方回合。原版是停在消息上等按键。
        if (plotPop != null) { stepAt = Time.time + STEP; return; }
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
    // ── 敌方回合 AI：照原版 b.bX()（Z=2 默认那套）─────────────────────
    // 一轮里每支敌军各走**一步**（贴上就打 / 走一格 / 放一条计策 / 撤），
    // 轮与轮之间重新规划，直到这一轮机动力没变化或池子 < 2 才收。
    // 规划（每轮一次）：
    //   主将(0)：F() 兵<100 且体力<40 且有退路 → 整军撤；否则朝我方主将压过去
    //   其余：y() 有退路且兵<400 且低于全军均值(总兵*2/33) → 这支撤
    //         我方主将 武≤它 且 兵≤它 且 智<它 → 直冲我方主将
    //         我方有人进它主将 3 格内 → 扑离自己最近的那支（守主将）
    //         5 格内有智力比它低、且有合法付得起的计策的我方部队 → 放计策（随机挑）
    //         都不是 → 原地不动
    class EnemyPlan { public int Type; public Unit Target; }
    const int GUARD = 6;                                  // 守方警戒圈（原版 3，家规放大到 6）
    Dictionary<Unit, EnemyPlan> plans; List<Unit> enemyOrder; int roundStartMp = -1;

    Unit EnemyCommander() { foreach (var u in units) if (!u.Ours && u.Alive) return u; return null; }
    Unit OurCommander()
    {
        int r = OurRuler();
        foreach (var u in units) if (u.Ours && u.Alive && u.O.Id == r) return u;
        foreach (var u in units) if (u.Ours && u.Alive) return u;
        return null;
    }
    bool EnemyCanRetreat()
    {
        LoadCityData();
        int er = FoeRuler();                                       // 原版 a(bb, ba)：ba = 敌方主公（守城时是来犯那家）
        foreach (int c in adjCities)
        {
            cityRuler.TryGetValue(c, out int owner); cityCount.TryGetValue(c, out int cnt);
            if ((owner == er && cnt < 10) || owner == 0) return true;
        }
        return false;
    }
    int EnemyArmyTotal() { int t = 0; foreach (var u in units) if (!u.Ours && u.Alive) t += 500 + u.O.Troops; return t; }   // b.H()
    int OurArmyTotal()   { int t = 0; foreach (var u in units) if (u.Ours  && u.Alive) t += 500 + u.O.Troops; return t; }   // b.G()

    void BuildEnemyPlans()
    {
        plans = new Dictionary<Unit, EnemyPlan>();
        enemyOrder = units.FindAll(u => !u.Ours && u.Alive && u.Trapped == 0);
        var cmd = EnemyCommander(); var ours = OurCommander();
        if (cmd == null || ours == null) return;
        bool canRetreat = EnemyCanRetreat();
        int total = EnemyArmyTotal();
        if (!WeAttack) { BuildAttackerPlans(cmd, canRetreat, total); return; }
        foreach (var e in enemyOrder)
        {
            var pl = new EnemyPlan();
            if (e == cmd)
            {
                // 原版 b.a(byte[],…) 3173 + bW() 10913：太守只有三种计划，**没有"冲上来"这一条**——
                //   有退路 且（敌方总战力 < 我方总战力 ⁄ 4  或  兵 < 100 且体力 < 40）→ 整军逃窜（我方赢）
                //   否则 5 格内有能放的计策 → 放计策
                //   否则 计划 0 —— 原地不动。太守就是这么守在城里的。
                if (canRetreat && (total < OurArmyTotal() / 4 || (e.O.Troops < 100 && e.O.Hp < 40))) pl.Type = 5;
                else if (EnemyPlotCandidates(e).Count > 0) pl.Type = 2;
                else pl.Type = 0;
            }
            else if (canRetreat && e.O.Troops < 400 && total * 2 / 33 > e.O.Troops) pl.Type = 3;
            else if (ours.O.Might <= e.O.Might && ours.O.Troops <= e.O.Troops && ours.O.Int < e.O.Int)
            { pl.Type = 1; pl.Target = ours; }
            else
            {
                // 家规（守城太被动，玩家嫌）：原版 a(int,…) 3589 的警戒圈是主将 3×3 以内，扩到 6；
                // 另外守方总战力（500+兵，b.H）比攻方多三成以上时，不等人靠近，直接出城扑最近的那支
                bool strong = total > OurArmyTotal() * 13 / 10;
                Unit near = null; int bd = 100;
                foreach (var m in units)
                    if (m.Ours && m.Alive && (strong || (Mathf.Abs(m.X - cmd.X) <= GUARD && Mathf.Abs(m.Y - cmd.Y) <= GUARD)))
                    {
                        int d = Mathf.Abs(m.X - e.X) + Mathf.Abs(m.Y - e.Y);
                        if (d < bd) { bd = d; near = m; }
                    }
                if (near != null) { pl.Type = 1; pl.Target = near; }
                else if (EnemyPlotCandidates(e).Count > 0) pl.Type = 2;
                else pl.Type = 0;
            }
            plans[e] = pl;
        }
    }
    /// 敌军来攻我城：原版 b.bY() 11287（b.java 3943 bp 为真时走这套，am() 6064 置 bp）。
    ///   主将：F() 有退路且（敌总战力 < 我方 ⁄ 4 或 兵<100 且体力<40）→ 整军逃窜；
    ///         5 格内有能放的计策 → 放计策；前 3 次（cN<4）原地不动，第 4 次起朝我方 [0]（太守）压过去
    ///         （原版 c() 11278 这里把「放计策」误写成 byArray3[0]=2，主将有计策可放时反而站着不动——修掉，见 HOUSE_RULES）
    ///   其余：y() 撤 → 放计策 → 都不是就朝我方 [0] 压过去。
    int foeCalls;                                                  // 原版 cN：每进一次 bY() +1（开一日 / 打完一仗回来）
    void BuildAttackerPlans(Unit cmd, bool canRetreat, int total)
    {
        Unit head = units.Find(u => u.Ours && u.Alive);                // 原版 bg[0]
        foreach (var e in enemyOrder)
        {
            var pl = new EnemyPlan();
            if (e == cmd)
            {
                if (canRetreat && (total < OurArmyTotal() / 4 || (e.O.Troops < 100 && e.O.Hp < 40))) pl.Type = 5;
                else if (EnemyPlotCandidates(e).Count > 0) pl.Type = 2;
                else if (foeCalls >= 4 && head != null) { pl.Type = 1; pl.Target = head; }
                else pl.Type = 0;
            }
            else if (canRetreat && e.O.Troops < 400 && total * 2 / 33 > e.O.Troops) pl.Type = 3;
            else if (EnemyPlotCandidates(e).Count > 0) pl.Type = 2;
            else if (head != null) { pl.Type = 1; pl.Target = head; }
            plans[e] = pl;
        }
    }
    /// 原版 b.c(int,int)：5 格内、智力比它低、有合法且付得起的计策（不含笼络/埋伏；对方没兵时不用火计/要击/乱水）
    List<(Unit t, int plot)> EnemyPlotCandidates(Unit e)
    {
        var list = new List<(Unit, int)>();
        int n = BattleRules.PlotCount(e.O);
        foreach (var m in units)
        {
            if (!m.Ours || !m.Alive || m.Trapped > 0) continue;
            if (Mathf.Abs(m.X - e.X) > 5 || Mathf.Abs(m.Y - e.Y) > 5) continue;
            if (e.O.Int <= m.O.Int) continue;
            for (int p = 0; p < n; p++)
            {
                if (p == 8 || p == 11 || p == 14) continue;
                if (m.O.Troops == 0 && (p == 0 || p == 3 || p == 4)) continue;
                if (BattleRules.CostOfPlot(p) > enemyMp) continue;
                if (BattleRules.PlotLegal(p, e.X, e.Y, m.X, m.Y, TerrainAt(e.X, e.Y), TerrainAt(m.X, m.Y), HasAllyAdjacent(m)))
                    list.Add((m, p));
            }
        }
        return list;
    }

    bool EnemyStep()
    {
        for (int guard = 0; guard < 4; guard++)
        {
            if (enemyMp < 2) return false;
            if (plans == null || enemyIdx >= enemyOrder.Count)
            {
                if (plans != null && enemyMp == roundStartMp) return false;     // 这一轮谁都没花机动力：收
                BuildEnemyPlans(); enemyIdx = 0; roundStartMp = enemyMp;
                if (enemyOrder.Count == 0) return false;
                var cmd = EnemyCommander();
                if (cmd != null && plans.TryGetValue(cmd, out var cp) && cp.Type == 4)   // 整军撤
                {
                    foreach (var u in units) if (!u.Ours) u.Alive = false;
                    hintText.text = "敌军撤退!"; Refresh(); return false;
                }
            }
            while (enemyIdx < enemyOrder.Count)
            {
                var e = enemyOrder[enemyIdx++];
                if (!e.Alive || !plans.TryGetValue(e, out var pl)) continue;
                if (ExecuteEnemy(e, pl)) return true;
            }
        }
        return false;
    }

    bool ExecuteEnemy(Unit e, EnemyPlan pl)
    {
        switch (pl.Type)
        {
            case 5:                                            // 太守整军逃窜：原版 W=5 → L()，整场我方赢
                if (WeAttack) SettleCity(true, "敌方全军逃窜! 我军攻占了 " + CityNameNow() + "!");
                else ShowOver("敌方全军逃窜!");
                return true;
            case 3:                                            // 这支遁逃（原版 W=3）
                e.Alive = false; hintText.text = "敌将遁逃!"; acting = null; CenterOn(e.X, e.Y);
                return true;
            case 2:                                            // 放计策：原版 c()——随机挑一对（目标, 计策）
            {
                var cs = EnemyPlotCandidates(e);
                if (cs.Count == 0) return false;
                var (t, plot) = cs[rng.Next(cs.Count)];
                enemyMp -= BattleRules.CostOfPlot(plot);
                var r = BattleRules.PlotRoll(plot, e.O, t.O, supply[1], rng);
                string msg = r.Ok ? BattleRules.PlotMessage(plot, r) : "计策失败！";
                int before = t.O.Troops;                                       // 原版 3727
                if (r.Ok) ApplyPlot(plot, r, t, false);
                BattleRules.GainExp(e.O, before - t.O.Troops);                 // 3730

                hintText.text = e.O.Name + " 对 " + t.O.Name + " 使用计策: " + BattleRules.PlotName[plot];
                ShowPlotPop(plot, msg, e, t);
                acting = e; CenterOn(t.X, t.Y);
                return true;
            }
            case 1:
            {
                // 贴上任何一支我方就打（原版 3659：扫全部我方，不限定目标）
                foreach (var m in units)
                    if (m.Ours && m.Alive && m.Trapped == 0 && Adjacent(e, m.X, m.Y))   // bu 1–3 被困的不算
                    {
                        if (enemyMp < BattleRules.AttackCost) return false;
                        enemyMp -= BattleRules.AttackCost;
                        foeCalls++;                                // 原版 aq=3 退出 bY()，打完再进来 ++cN
                        hintText.text = "敌军攻打我军!";           // W == 2
                        acting = e; OnClash?.Invoke(m, e, false);
                        return true;
                    }
                if (pl.Target == null || !pl.Target.Alive) return false;
                // 原版 3652：每轮只走最短路的第一步，下一轮从新位置重算；机动力不够走那一格就这轮不动
                int dir = WindowStep(e, pl.Target.X, pl.Target.Y);
                if (dir < 0) return false;
                var nxt = new Vector2Int(e.X + DX[dir], e.Y + DY[dir]);
                int cost = StepCost(e, nxt.x, nxt.y);
                if (cost > enemyMp) return false;
                enemyMp -= cost;
                e.Dir = nxt.x != e.X ? "e" : (nxt.y > e.Y ? "s" : "n");
                e.Flip = nxt.x < e.X;
                e.X = nxt.x; e.Y = nxt.y;
                Sfx.Play("step", .5f);                                                // 敌军走一步，轻一点
                acting = e; CenterOn(e.X, e.Y);
                if (ambush.Remove(e.Y * W + e.X))              // b.java 3703：踩进埋伏格
                {
                    e.O.Troops = Mathf.Max(0, e.O.Troops - AMBUSH_LOSS);
                    if (e.O.Troops <= 0) e.Alive = false;
                    hintText.text = e.O.Name + " 中计谋十面埋伏";
                }
                if (e.Alive && !WeAttack && (grid[e.Y, e.X] & 0xF) == 2) { Captured(); }   // b.k(by)：踩上城池格 → aq=4
                return true;
            }
        }
        return false;
    }

    // ── 计算 ───────────────────────────────────────────────────────────
    // ── 移动 ──
    //   原版 c.java 5180-5262 O/P/Q/R：方向键按一下走一格，那格没人且 h(格,兵种) ≤ 机动力才走，
    //   按确定 E() 收尾（落到建筑格问进村），机动力还剩就还能再动。
    //   iPhone 没方向键，一格一格点太累，所以把"方向键能走到的所有格"一次算出来亮给玩家：
    //   规则还是原版的（每格 b.h 消耗、有人的格过不去、总消耗 ≤ 机动力），点哪格就按最省的路走过去扣机动力。
    static readonly int[] DX = { 0, 0, -1, 1 }, DY = { -1, 1, 0, 0 };   // 0 上 1 下 2 左 3 右
    readonly Dictionary<int, int> costTo = new Dictionary<int, int>();
    // 原版 c.java 4556：按「移动」那一刻把位置和机动力记下来（x/y 和 R），
    // 中途按取消键就整个还原 —— 走错了不用赔上一回合的机动力。
    int fromX, fromY, fromMp;
    void BeginMove()
    {
        fromX = sel.X; fromY = sel.Y; fromMp = mp; moved = false;
        mode = "move"; ComputeReach();
    }
    void CancelMove()
    {
        if (sel != null && moved)
        {
            sel.X = fromX; sel.Y = fromY; sel.Draw = new Vector2(fromX, fromY);
            mp = fromMp; CenterOn(fromX, fromY); curX = fromX; curY = fromY;
        }
        moved = false; mode = ""; reach.Clear();
        hintText.text = "选择武将下命令";
        RefreshTop(); Refresh();
    }
    void ComputeReach()
    {
        reach.Clear(); costTo.Clear();
        var q = new Queue<int>();
        int s = sel.Y * W + sel.X;
        costTo[s] = 0; q.Enqueue(s);
        while (q.Count > 0)
        {
            int cur = q.Dequeue(); int cx = cur % W, cy = cur / W, cc = costTo[cur];
            for (int d = 0; d < 4; d++)
            {
                int nx = cx + DX[d], ny = cy + DY[d];
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                int nc = cc + StepCost(sel, nx, ny);
                if (nc > mp) continue;
                int key = ny * W + nx;
                if (costTo.TryGetValue(key, out var old) && old <= nc) continue;
                costTo[key] = nc; reach.Add(key); q.Enqueue(key);
            }
        }
        reach.Remove(s);
        hintText.text = "武将移动 · 点亮格走 · 确定落定 / 取消还原";
    }
    /// b.h(格,兵种)：有人站着算 100（走不了）
    int StepCost(Unit u, int nx, int ny) => At(nx, ny) != null ? 100 : BattleRules.MoveCost(grid[ny, nx] & 0xF, u.O.Kind);
    void DoMove(int x, int y)
    {
        int cost = costTo.TryGetValue(y * W + x, out var c) ? c : 0;
        mp -= cost;
        Sfx.Play("step");                                                            // 行军一步
        int dx = x - sel.X, dy = y - sel.Y;
        sel.Dir = Mathf.Abs(dx) > Mathf.Abs(dy) ? "e" : (dy > 0 ? "s" : "n");
        sel.Flip = dx < 0;
        sel.X = x; sel.Y = y;
        CenterOn(x, y);
        moved = true;
        // 原版走完一格还留在移动态，能接着走、也能按取消还原；按「确定」才落定。
        ComputeReach();
        hintText.text = "移动消耗 " + cost + " 点 · 剩 " + mp + " · 可继续走 / 确定 / 取消";
    }
    /// 原版 E()：确定。落到建筑格问「是否进入村庄?」，回到「选择武将下命令」
    bool moved;
    void EndMove(bool confirm = false)
    {
        mode = ""; reach.Clear();
        hintText.text = "选择武将下命令";
        if (moved || confirm) OnLanded(sel);      // 原版按「确定」不挪也问；只是改点别人（隐式落定）时不问
        moved = false;
    }

    /// 原版 Z=2 的寻路（b.c(5 参) 11214 → b.a(by,by2,…) 11195）：以自己和目标为界取一块 8×8 的窗（b() 10964），
    /// 窗里每格的进入代价按兵种查（a(by,by2,by3,…) 11026：有人站着 100、平地 2/3、山 5/6、水 3/5/6、城 6），
    /// 在窗里做一遍最短路（63 轮取最小，11145），目标太远就沿直线取 6 格外那点当终点（a(int,int,…) 11090）。
    /// 返回第一步的方向（0 上 1 下 2 左 3 右），走不通返回 -1。地图层每轮只走第一步，下一轮从新位置重算。
    /// 以前这里照的是 Z=1 的贪心寻路（b.a 3436），撞上树 / 水就原地不动——原版默认是 Z=2，改回这套。
    int WindowStep(Unit e, int tx, int ty)
    {
        int ww = Mathf.Min(8, W), wh = Mathf.Min(8, H);
        int Org(int f, int t, int dim)
        {
            int n;
            if (t - f > 6) n = Mathf.Max(0, f - 1);
            else if (t - f >= 0) n = (f + t + 1) / 2 - 4;
            else if (t - f > -7) n = (f + t) / 2 - 3;
            else n = f - 6;
            return Mathf.Clamp(n, 0, Mathf.Max(0, dim - 8));
        }
        int ox = Org(e.X, tx, W), oy = Org(e.Y, ty, H);
        int dx = tx - e.X, dy = ty - e.Y, gx = tx, gy = ty;
        if (Mathf.Abs(dx) > 6 || Mathf.Abs(dy) > 6)
        {
            if (Mathf.Abs(dx) >= Mathf.Abs(dy)) { gx = e.X + (dx > 0 ? 6 : -6); gy = e.Y + (dx > 0 ? 6 * dy / dx : -6 * dy / dx); }
            else { gy = e.Y + (dy > 0 ? 6 : -6); gx = e.X + (dy > 0 ? 6 * dx / dy : -6 * dx / dy); }
        }
        gx = Mathf.Clamp(gx - ox, 0, ww - 1); gy = Mathf.Clamp(gy - oy, 0, wh - 1);
        int sx = e.X - ox, sy = e.Y - oy;
        if (sx < 0 || sy < 0 || sx >= ww || sy >= wh) return -1;
        int N = ww * wh, start = sy * ww + sx, goal = gy * ww + gx;
        if (start == goal) return -1;
        var dist = new int[N]; var prev = new int[N]; var done = new bool[N];
        for (int i = 0; i < N; i++) { dist[i] = int.MaxValue; prev[i] = -1; }
        dist[start] = 0;
        int Enter(int c) { int x = ox + c % ww, y = oy + c / ww; return At(x, y) != null ? 100 : BattleRules.MoveCost(grid[y, x], e.O.Kind); }
        for (int k = 0; k < N; k++)
        {
            int cur = -1;
            for (int i = 0; i < N; i++) if (!done[i] && dist[i] != int.MaxValue && (cur < 0 || dist[i] < dist[cur])) cur = i;
            if (cur < 0) break;
            done[cur] = true;
            int cx = cur % ww, cy = cur / ww;
            int[] nb = { cx < ww - 1 ? cur + 1 : -1, cx > 0 ? cur - 1 : -1, cy < wh - 1 ? cur + ww : -1, cy > 0 ? cur - ww : -1 };   // 右 左 下 上（原版松弛次序）
            foreach (int n in nb)
            {
                if (n < 0 || done[n]) continue;
                int nd = dist[cur] + Enter(n);
                if (nd < dist[n]) { dist[n] = nd; prev[n] = cur; }
            }
        }
        if (prev[goal] < 0) return -1;
        int step = goal;
        while (prev[step] != start) step = prev[step];
        int ddx = step % ww - sx, ddy = step / ww - sy;
        return ddy < 0 ? 0 : ddy > 0 ? 1 : ddx < 0 ? 2 : 3;
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
                    // 红框上沿挂一个成功率——原版不给，玩家只能凭感觉赌，
                    // 一条计策十几点机动力，赌错一次这一天就没了。
                    int pct = Mathf.RoundToInt(BattleRules.PlotSuccess(plotSel, sel.O, o.O) * 100);
                    var pt = Label(board, pct + "%", 12,
                        pct >= 70 ? new Color32(0x7C, 0xE8, 0x6A, 255) :        // 稳
                        pct >= 35 ? new Color32(0xF5, 0xD0, 0x50, 255)          // 悬
                                  : new Color32(0xFF, 0x7A, 0x6A, 255),         // 别赌
                        TextAnchor.MiddleCenter, FontStyle.Bold);
                    Place(pt.rectTransform, o.X * CELL - 6, o.Y * CELL - 15, CELL + 12, 14);
                    var po = pt.gameObject.AddComponent<Outline>();             // 压在地形上不描边看不清
                    po.effectColor = new Color(0, 0, 0, .95f); po.effectDistance = new Vector2(1.2f, 1.2f);
                    marks.Add(pt.gameObject);
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
        // 分两遍画。名牌摆在自己格子的下沿，正好落在下一排武将的身上；人物又画得比格子高一截，
        // 头探进上一排的格子里。一遍画完必然有一方被盖：先画人就压住名牌，先画名牌就盖住脸。
        //   第一遍：按行从上往下画人（靠下的后画，前后叠压才对）
        //   第二遍：所有名牌统一盖在最上层
        // 因为人物往上长了 16 像素，脸已经抬到了格子上方，名牌只会压到下一排的胸口，不碰脸。
        var order = units.FindAll(u => u.Alive);
        order.Sort((p, q) => p.Draw.y != q.Draw.y ? p.Draw.y.CompareTo(q.Draw.y)
                                                  : p.Draw.x.CompareTo(q.Draw.x));
        foreach (var u in order)                       // 第一遍：人
        {
            float ux = u.Draw.x * CELL, uy = u.Draw.y * CELL;

            // 脚下垫一圈阵营色的椭圆。阵型战和单挑早就这么干了（「两边长得一样时最难分敌我」），
            // 战棋层一直没接——地块又花又暗，光靠描边还是认不出来。
            float footY = uy + FIG;                                           // 脚线
            var sh = Node("sh", board).gameObject.AddComponent<Image>();      // 深色投影：把人从花地块上垫起来
            Place((RectTransform)sh.transform, ux + 3, footY - 10, CELL - 6, 11);
            sh.sprite = Disc(); sh.color = new Color(0, 0, 0, .5f);
            sh.raycastTarget = false; marks.Add(sh.gameObject);
            var ring = Node("ring", board).gameObject.AddComponent<Image>();   // 亮色阵营环：认敌我
            Place((RectTransform)ring.transform, ux + 1, footY - 12, CELL - 2, 13);
            ring.sprite = Ring(192, 72, .22f);
            // 白描边已经把人勾出来了，这圈只管认敌我，别跟描边抢
            ring.color = u.Ours ? new Color(.45f, .85f, 1f, .55f) : new Color(1f, .52f, .34f, .55f);
            ring.raycastTarget = false; marks.Add(ring.gameObject);

            // 小人画得比格子大一圈：源图就是 64，缩到 44 等于白丢分辨率，
            // 放到 60 差不多一比一，脚仍落在格底、横向居中（阵型战的 FIG = C + 14 也是这个路子）
            // 人物、名牌、兵力条**全部收在自己这一格里**，谁也不越界，于是谁也盖不住谁。
            // 名牌占格底 14 像素（压住自己的小腿，不碰别人），人物按整格画、脚落在格底。
            var im = Node("u", board).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, ux + (CELL - FIG) / 2f, uy, FIG, FIG);
            im.sprite = UnitSprite(u); im.raycastTarget = false;
            im.color = Color.white;                    // 原版没有"已行动"变灰：一个武将只要机动力够可以一直用
            if (u.Flip)
            {
                // Place() 的轴心在左上角，localScale=-1 是绕左边缘翻的，
                // 整个小人会被推左一格，而名字血条不跟着翻 —— 于是错位一格。
                // 补回一格宽度，等价于绕格子中心翻。
                im.rectTransform.localScale = new Vector3(-1, 1, 1);
                im.rectTransform.anchoredPosition += new Vector2(FIG, 0);
            }
            marks.Add(im.gameObject);

            // 兵力条不再单占一行：画成名牌底下的一道下划线（在第二遍里压在名牌上）

            if (u.Trapped > 0)                             // 被困：脚下压咒缚那套绳索（复用阵型战的特效）
            {
                var tr = Node("trap", board).gameObject.AddComponent<Image>();
                Place((RectTransform)tr.transform, ux - 4, uy - 4, CELL + 8, CELL + 8);
                var sp = BattleUi.Fx("bind", (int)(Time.time / .18f) % 4);
                if (sp != null) tr.sprite = sp; else tr.color = new Color(.6f, .3f, .9f, .45f);
                tr.raycastTarget = false; marks.Add(tr.gameObject);
            }
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

        foreach (var u in order)                       // 第二遍：名牌一律盖在最上层
        {
            float ux = u.Draw.x * CELL, uy = u.Draw.y * CELL;
            // 原版战棋层不画名字（176×208 的屏放不下，名字只在底部状态行显示），
            // 这块名牌是我们加的，所以怎么排由我们定。一格才 44 宽，「诸葛亮 1000」怎么摆都塞不下，
            // 牌子一加宽又会和左右邻居叠在一起。**兵力数从牌上拿掉**——上面那根条已经表示多少兵，
            // 精确值看情报——只留名字，牌子正好一格宽，横着挨着站也不再互相压。
            // 素材两端各有 14 像素的铜钉（battleui.txt 的 border），ppuMultiplier 把九宫格的边缩一半。
            const float NPW = CELL, NPX = 0, NPPAD = 6;
            // 名牌收进本格底部，不再探到下一格去
            var np = BattleUi.Get("nameplate");
            if (np != null)
            {
                var pl = Node("np", board).gameObject.AddComponent<Image>();
                Place((RectTransform)pl.transform, ux + NPX, uy + CELL - NP_H, NPW, NP_H);
                pl.sprite = np; pl.type = Image.Type.Sliced;
                pl.pixelsPerUnitMultiplier = 2f; pl.raycastTarget = false;
                marks.Add(pl.gameObject);
            }
            // 三字名（诸葛亮、司马懿…）按 11 号排会顶到铜钉上，缩一号正好
            var nm = Label(board, u.O.Name, u.O.Name.Length >= 3 ? 10 : 11, Color.white, TextAnchor.MiddleCenter);
            Place(nm.rectTransform, ux + NPX + NPPAD, uy + CELL - NP_H, NPW - NPPAD * 2, NP_H - 4);
            var ol = nm.gameObject.AddComponent<Outline>();     // 压在地形上不描边看不清
            ol.effectColor = new Color(0, 0, 0, .95f);
            ol.effectDistance = new Vector2(1.2f, 1.2f);
            marks.Add(nm.gameObject);

            const float BW = CELL - 14;                         // 兵力条：名牌下沿的一道细线
            var bar = Node("bar", board).gameObject.AddComponent<Image>();
            float f = Mathf.Clamp01(u.O.Troops / 1000f);
            Place((RectTransform)bar.transform, ux + 7, uy + CELL - 4, BW * f, 3);   // 名牌下沿那一条，不压字
            bar.color = u.Ours ? new Color(.35f, .75f, 1f) : new Color(1f, .40f, .34f);
            bar.raycastTarget = false; marks.Add(bar.gameObject);
        }

        Place((RectTransform)cursorImg.transform, curX * CELL - 3, curY * CELL - 3, CELL + 6, CELL + 6);
        cursorImg.transform.SetAsLastSibling();

        dayText.text = "第 " + day + " 日";
        mpText.text = "机动力 " + mp + " / " + BattleRules.MoveCapMax(TotalTroops(true));
        // 移动态把这排键换成「确定 / 取消」，其余四个压暗——原版那会儿软键也只有这两个意思
        bool mv = mode == "move";
        for (int i = 0; i < CMD.Length; i++)
        {
            if (cmdLabels[i] != null)
            {
                cmdLabels[i].text = mv ? (i == 0 ? "确定" : i == 1 ? "取消" : CMD[i]) : CMD[i];
                cmdLabels[i].color = mv && i > 1 ? new Color32(0x78,0x70,0x60,255)
                                                 : new Color32(0xF0,0xE0,0xB8,255);
            }
            if (cmdImgs[i] == null) continue;
            bool on = mv ? i <= 1 : i == (mode == "attack" ? 2 : mode == "info" ? 3 : -1);
            cmdImgs[i].color = on ? new Color(1f, .92f, .55f, 1f)
                                  : (mv && i > 1 ? new Color(.62f, .62f, .62f, 1f) : Color.white);
        }
    }
}
