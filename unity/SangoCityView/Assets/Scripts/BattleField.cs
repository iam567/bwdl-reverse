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

    public BattleField(RectTransform parent, int cityId, string themeKey,
                       List<Unit> roster, float refW, float refH)
    {
        this.refW = refW; this.refH = refH;
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

        for (int y = 0; y < H; y++)
            for (int x = 0; x < W; x++)
            {
                var im = Node("c", board).gameObject.AddComponent<Image>();
                Place((RectTransform)im.transform, x * CELL, y * CELL, CELL, CELL);
                im.sprite = Tile(grid[y, x] & 0xF);
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
        cursorImg.sprite = CursorSprite();
        cursorImg.raycastTarget = false;
    }

    void BuildHud()
    {
        var top = Panel(Root, "top", Plate());
        Place((RectTransform)top.transform, 8, 8, refW - 16, 60);
        dayText = Label(top.transform, "", 15, new Color32(0xF2,0xE8,0xD4,255));
        Place(dayText.rectTransform, 12, 8, 160, 22);
        mpText = Label(top.transform, "", 14, new Color32(0xF0,0xD0,0x88,255), TextAnchor.MiddleRight);
        Place(mpText.rectTransform, refW - 190, 8, 170, 22);
        hintText = Label(top.transform, "", 12, new Color32(0xC8,0xC0,0xA8,255));
        Place(hintText.rectTransform, 12, 32, refW - 40, 20);

        cmdBar = Node("cmd", Root);
        Place(cmdBar, 0, refH - 88, refW, 88);
        var bg = Panel(cmdBar, "bg", Plate()); Fill((RectTransform)bg.transform);

        cmdSel = Node("sel", cmdBar).gameObject.AddComponent<Image>();
        cmdSel.color = new Color(1f, .85f, .2f, .22f);
        cmdSel.raycastTarget = false;

        cmdLabels = new Text[CMD.Length];
        float bw = (refW - 16) / CMD.Length;
        for (int i = 0; i < CMD.Length; i++)
        {
            int k = i;
            var b = Node("b" + i, cmdBar);
            Place(b, 8 + i * bw, 10, bw, 40);
            var img = b.gameObject.AddComponent<Image>();
            img.color = new Color(0, 0, 0, 0.01f);
            var btn = b.gameObject.AddComponent<Button>();
            btn.transition = Selectable.Transition.None;
            btn.onClick.AddListener(() => Command(k));
            cmdLabels[i] = Label(b, CMD[i], 15, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
            Fill(cmdLabels[i].rectTransform);
        }

        // 战场比一屏大，给两个跳转按钮，省得一路拖
        void JumpBtn(string txt, float x, System.Action act)
        {
            var b = Node("j" + txt, cmdBar);
            Place(b, x, 52, 78, 28);
            var i2 = b.gameObject.AddComponent<Image>(); i2.sprite = Frame(); i2.type = Image.Type.Sliced;
            var t2 = b.gameObject.AddComponent<Button>(); t2.transition = Selectable.Transition.None;
            t2.onClick.AddListener(() => act());
            var l2 = Label(b, txt, 12, new Color32(0xE0,0xD0,0xA8,255), TextAnchor.MiddleCenter);
            Fill(l2.rectTransform);
        }
        JumpBtn("看我军", 10, () => JumpTo(true));
        JumpBtn("看敌军", refW - 88, () => JumpTo(false));

        var end = Node("end", cmdBar);
        Place(end, refW / 2 - 70, 52, 140, 28);
        var eimg = end.gameObject.AddComponent<Image>(); eimg.sprite = Frame(); eimg.type = Image.Type.Sliced;
        var ebtn = end.gameObject.AddComponent<Button>(); ebtn.transition = Selectable.Transition.None;
        ebtn.onClick.AddListener(EndDay);
        var el = Label(end, "结束这一日", 13, new Color32(0xE8,0xD8,0xB0,255), TextAnchor.MiddleCenter);
        Fill(el.rectTransform);
    }

    // ── 素材 ───────────────────────────────────────────────────────────
    static readonly Dictionary<int, string> SLOT = new Dictionary<int, string> {
        {0,"ground"},{1,"wuku"},{2,"castle"},{3,"cang"},{4,"yao"},{5,"ambush"},{6,"water"},
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
        if (mode == "move" && sel != null && reach.Contains(y * W + x)) { DoMove(x, y); }
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
            case 1:                                  // 计策
                hintText.text = "计策：" + BattleRules.PlotName[0] + " 等 "
                              + BattleRules.PlotCount(sel.O) + " 种可用（试玩版未接效果）";
                mode = ""; break;
            case 2:                                  // 攻击
                mode = "attack";
                hintText.text = HasFoeAdjacent(sel) ? "点相邻的敌将" : "邻近无敌将!";
                if (!HasFoeAdjacent(sel)) mode = "";
                break;
            case 3: mode = "info"; hintText.text = "点一支部队看属性"; break;
            case 4:                                  // 撤退
                sel.Alive = false; sel.Acted = true;
                hintText.text = "撤退武将 " + sel.O.Name
                              + "（损失 " + BattleRules.RoutLoss(sel.O.Troops, rng) + " 兵）";
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

    void DoAttack(Unit foe)
    {
        if (mp < BattleRules.AttackCost) { hintText.text = "机动力不足"; return; }
        mp -= BattleRules.AttackCost;
        sel.Acted = true;
        var me = sel; sel = null; mode = "";
        OnClash?.Invoke(me, foe, true);
    }

    void ShowInfo(Unit u)
    {
        hintText.text = string.Format("{0} LV{1} 武力{2} 智力{3} 体力{4} 兵力{5} {6}",
            u.O.Name, u.O.Lv, u.O.Might, u.O.Int, u.O.Hp, u.O.Troops,
            u.O.Kind == Kind.Ping ? "平军" : u.O.Kind == Kind.Shui ? "水军" : "山军");
        mode = "";
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
        foreach (var u in units) u.Acted = false;
        mp = BattleRules.MoveCap(TotalTroops(true));
        hintText.text = "战争回合结束！第 " + day + " 日";
        Refresh();
    }

    // ── 敌方回合：一枚一格地走 ──────────────────────────────────────────
    // 原版 b.java:3655 一次只处理一枚部队的一格：
    //   四邻有我方就攻击（扣 2 机动力），否则从路径里取一步、够机动力才走。
    // 我原来是一帧里把所有敌军都挪完，看起来就是「啪一下全到脸上」。
    bool enemyTurn; float stepAt; int enemyMp, enemyIdx;
    const float STEP = 0.22f;

    public void Tick()
    {
        if (!enemyTurn || Time.time < stepAt) return;
        stepAt = Time.time + STEP;
        if (!EnemyStep()) StartOurDay();
        Refresh();
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
                enemyIdx++;
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
            enemyIdx++;
            CenterOn(e.X, e.Y);                            // 镜头跟着走，看得见是谁在动
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
        hintText.text = "武将移动 · 亮格可达（剩 " + mp + " 点）";
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
    }

    void CenterOn(int x, int y)
    {
        var clip = (RectTransform)board.parent;
        float vw = clip.rect.width > 1 ? clip.rect.width : refW;
        float vh = clip.rect.height > 1 ? clip.rect.height : refH - 172;
        float px = Mathf.Clamp(x * CELL + CELL / 2f - vw / 2f, 0, Mathf.Max(0, W * CELL - vw));
        float py = Mathf.Clamp(y * CELL + CELL / 2f - vh / 2f, 0, Mathf.Max(0, H * CELL - vh));
        board.anchoredPosition = new Vector2(-px, py);
    }

    // ── 重绘 ───────────────────────────────────────────────────────────
    readonly List<GameObject> marks = new List<GameObject>();
    public void Refresh()
    {
        foreach (var g in marks) Object.Destroy(g);
        marks.Clear();

        foreach (int k in reach)                       // 可达格
        {
            var im = Node("r", board).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, (k % W) * CELL, (k / W) * CELL, CELL, CELL);
            im.color = new Color(.35f, .75f, 1f, .30f); im.raycastTarget = false;
            marks.Add(im.gameObject);
        }
        foreach (var u in units)                       // 部队
        {
            if (!u.Alive) continue;
            var im = Node("u", board).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, u.X * CELL, u.Y * CELL, CELL, CELL);
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
            Place((RectTransform)back.transform, u.X * CELL + 2, u.Y * CELL + CELL - 9, CELL - 4, 8);
            back.color = new Color(0, 0, 0, .55f); back.raycastTarget = false;
            marks.Add(back.gameObject);
            var bar = Node("bar", board).gameObject.AddComponent<Image>();
            float f = Mathf.Clamp01(u.O.Troops / 1000f);
            Place((RectTransform)bar.transform, u.X * CELL + 3, u.Y * CELL + CELL - 8, (CELL - 6) * f, 6);
            bar.color = u.Ours ? new Color(.35f, .65f, 1f) : new Color(1f, .35f, .3f);
            bar.raycastTarget = false; marks.Add(bar.gameObject);

            var nm = Label(board, u.O.Name + " " + u.O.Troops, 11, Color.white, TextAnchor.UpperCenter);
            Place(nm.rectTransform, u.X * CELL - 14, u.Y * CELL + CELL, CELL + 28, 14);
            var ol = nm.gameObject.AddComponent<Outline>();     // 压在地形上不描边看不清
            ol.effectColor = new Color(0, 0, 0, .95f);
            ol.effectDistance = new Vector2(1.2f, 1.2f);
            marks.Add(nm.gameObject);
            if (sel == u)
            {
                var hl = Node("hl", board).gameObject.AddComponent<Image>();
                Place((RectTransform)hl.transform, u.X * CELL, u.Y * CELL, CELL, CELL);
                hl.color = new Color(1f, .95f, .4f, .25f); hl.raycastTarget = false;
                marks.Add(hl.gameObject);
            }
        }

        Place((RectTransform)cursorImg.transform, curX * CELL, curY * CELL, CELL, CELL);
        cursorImg.transform.SetAsLastSibling();

        dayText.text = "第 " + day + " 日";
        mpText.text = "机动力 " + mp + " / " + BattleRules.MoveCap(TotalTroops(true));
        float bw = (refW - 16) / CMD.Length;
        int hi = mode == "move" ? 0 : mode == "attack" ? 2 : mode == "info" ? 3 : -1;
        cmdSel.enabled = hi >= 0;
        if (hi >= 0) Place((RectTransform)cmdSel.transform, 8 + hi * bw, 10, bw, 40);
    }
}
