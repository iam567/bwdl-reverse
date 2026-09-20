// 阵型选择（原版 F(Graphics)）与阵型战（原版 21/22）。
// 这两屏还没做新素材，先用原版 jar 里抽出来的：卷轴底 lib302、VS 框 lib90、
// 四个阵型图标 lib49、四种地面 lib98、小兵 lib56、兵力条 lib11、城墙 lib306。
// 头像用新做的那套。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class ClashView
{
    public RectTransform Root;
    public System.Action<int, int> OnDone;        // 我方剩余兵力, 敌方剩余兵力

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

    Text dayT, noteT;
    RectTransform gridRoot, orderRoot;
    Text[] armCount = new Text[4], foeCount = new Text[4], orderT = new Text[4];

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
    public void AdvanceForTest() { for (int i = 0; i < 120; i++) { tickAt = 0; Tick(); } }
    public void FinishForTest() { Finish(); }

    // ── 阵型战 ─────────────────────────────────────────────────────────
    void StartClash()
    {
        Object.Destroy(pick.gameObject);
        mine   = Formation.Build(me,  formation,      true,  siege, weAttack);
        theirs = Formation.Build(foe, rng.Next(4),     false, siege, weAttack);
        walls  = siege ? Formation.Walls(weAttack) : new Dictionary<Vector2Int, int>();
        BuildField();
        Redraw();
    }

    const int C = 22;                      // 阵型格一格多大
    const int ORDER_Y = 392;               // 指令栏位置。钉屏幕底会在中间留一大块黑
    void BuildField()
    {
        field = Node("field", Root); Fill(field);

        dayT = Label(field, "", 15, new Color32(0xF2,0xE8,0xD4,255));
        Place(dayT.rectTransform, 14, 14, 300, 22);

        var ground = Node("g", field).gameObject.AddComponent<Image>();
        var g98 = Tex("orig/lib98.png");
        ground.sprite = Sprite.Create(g98, new Rect(scene * 64, 0, 64, 64), new Vector2(.5f, .5f), 100);
        Place((RectTransform)ground.transform, 0, 44, refW, BattleRules.GridH * C + 16);

        gridRoot = Node("grid", field);
        Place(gridRoot, (refW - BattleRules.GridW * C) / 2, 52, BattleRules.GridW * C, BattleRules.GridH * C);

        // 信息栏：体 / 骑 / 弓 / 步，和原版底部那一栏一样
        var info = Panel(field, "info", Plate());
        Place((RectTransform)info.transform, 8, 44 + BattleRules.GridH * C + 26, refW - 16, 116);
        var an1 = Label(info.transform, me.Name, 14, new Color32(0x8C,0xC0,0xFF,255));
        Place(an1.rectTransform, 14, 8, 110, 20);
        var an2 = Label(info.transform, foe.Name, 14, new Color32(0xFF,0x90,0x88,255), TextAnchor.MiddleRight);
        Place(an2.rectTransform, refW - 140, 8, 110, 20);
        string[] lab = { "体", "骑", "弓", "步" };
        for (int i = 0; i < 4; i++)
        {
            var l = Label(info.transform, lab[i], 13, new Color32(0xD0,0xC0,0x98,255), TextAnchor.MiddleCenter);
            Place(l.rectTransform, refW / 2 - 24, 30 + i * 21, 32, 20);
            armCount[i] = Label(info.transform, "", 14, Color.white, TextAnchor.MiddleRight);
            Place(armCount[i].rectTransform, refW / 2 - 130, 30 + i * 21, 100, 20);
            foeCount[i] = Label(info.transform, "", 14, Color.white);
            Place(foeCount[i].rectTransform, refW / 2 + 22, 30 + i * 21, 100, 20);
        }

        // 逐日指令：武将 / 骑兵 / 弓兵 / 步兵 各下一道
        orderRoot = Node("orders", field);
        Place(orderRoot, 8, ORDER_Y, refW - 16, 100);
        var ob = Panel(orderRoot, "bg", Plate()); Fill((RectTransform)ob.transform);
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            var l = Label(orderRoot, ARM[i], 13, new Color32(0xD0,0xC0,0x98,255));
            Place(l.rectTransform, 14 + i * 88, 8, 60, 20);
            var b = Node("o" + i, orderRoot);
            Place(b, 10 + i * 88, 30, 80, 32);
            var bi = b.gameObject.AddComponent<Image>(); bi.sprite = Frame(); bi.type = Image.Type.Sliced;
            var bt = b.gameObject.AddComponent<Button>(); bt.transition = Selectable.Transition.None;
            bt.onClick.AddListener(() => { orders[k] = (orders[k] + 1) % OrderCount(k); Redraw(); });
            orderT[i] = Label(b, "", 14, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);
            Fill(orderT[i].rectTransform);
        }

        noteT = Label(field, "兵棋一秒动一枚。轮到谁才读它那一兵种当前的令——中途改令只影响还没动的", 11, new Color32(0xB0,0xA8,0x90,255));
        Place(noteT.rectTransform, 14, ORDER_Y + 112, refW - 28, 18);

        var quit = Node("quit", field);
        Place(quit, 14, ORDER_Y + 136, 110, 26);
        var qi = quit.gameObject.AddComponent<Image>(); qi.sprite = Frame(); qi.type = Image.Type.Sliced;
        var qb = quit.gameObject.AddComponent<Button>(); qb.transition = Selectable.Transition.None;
        qb.onClick.AddListener(Finish);
        var ql = Label(quit, "收兵回战场", 12, new Color32(0xE0,0xD0,0xA8,255), TextAnchor.MiddleCenter);
        Fill(ql.rectTransform);
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
    public int ActingSide = -1, ActingIdx = -1;
    string over;

    public void Tick()
    {
        if (mine == null) return;                 // 还在阵型选择那屏，兵还没摆
        if (over != null || Time.time < tickAt) return;
        tickAt = Time.time + TICK;

        var side  = turnSide == 0 ? mine : theirs;
        var other = turnSide == 0 ? theirs : mine;
        // 跳过已阵亡的
        int guard = 0;
        while (turnIdx < side.Count && !side[turnIdx].Alive && guard++ < 64) turnIdx++;

        if (turnIdx < side.Count)
        {
            ActingSide = turnSide; ActingIdx = turnIdx;
            Act(side, other, turnSide == 0, turnIdx);
            turnIdx++;
        }
        else { turnSide = 1 - turnSide; turnIdx = 0; ActingSide = -1; }

        if (Formation.TotalTroops(theirs) == 0 || foe.Hp <= 0) over = "敌军溃败";
        else if (Formation.TotalTroops(mine) == 0 || me.Hp <= 0) over = "我军溃败";
        Redraw();
    }

    /// b.s(by)：一枚兵棋的一次行动
    void Act(List<Piece> side, List<Piece> other, bool ours, int i)
    {
        var p = side[i];
        var self  = ours ? me : foe;
        var enemy = ours ? foe : me;
        // 轮到它了才读令。敌方一律前进。
        int cmd = ours ? orders[(int)p.Arm] : 0;
        if (cmd == 1) return;                                  // 待机：原地不动

        int t = FindAdjacent(other, p);
        if (t >= 0 && cmd != 2)                                // 撤退的不恋战
        {
            var q = other[t];
            int dmg = BattleRules.Damage(BattleRules.Atk(self, p.Arm),
                                         BattleRules.Def(enemy), p.Troops, rng);
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
            return;
        }

        var tgt = Nearest(other, p);
        if (tgt.x < 0) return;
        int dir = cmd == 2 ? -1 : 1;                           // 撤退就反着走
        int nx = p.Col, ny = p.Row;
        if (cmd == 3)                                          // 包围：先拉开纵向再压上
            ny += tgt.y >= p.Row ? -1 : 1;
        else if (Mathf.Abs(tgt.x - p.Col) >= Mathf.Abs(tgt.y - p.Row))
            nx += dir * System.Math.Sign(tgt.x - p.Col);
        else
            ny += dir * System.Math.Sign(tgt.y - p.Row);

        if (nx < 0 || ny < 0 || nx >= BattleRules.GridW || ny >= BattleRules.GridH) return;
        if (walls.ContainsKey(new Vector2Int(nx, ny))) return;
        if (Occupied(mine, nx, ny) || Occupied(theirs, nx, ny)) return;
        // bX 跟着走向更新：b.c/d/e/f 分别置 0 上 / 1 下 / 2 左 / 3 右
        if (nx != p.Col) p.Dir = nx > p.Col ? 3 : 2;
        else if (ny != p.Row) p.Dir = ny > p.Row ? 1 : 0;
        p.Col = nx; p.Row = ny; side[i] = p;
    }

    static bool Occupied(List<Piece> ps, int x, int y)
    {
        foreach (var p in ps) if (p.Alive && p.Col == x && p.Row == y) return true;
        return false;
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

    void Finish()
    {
        me.Troops  = Formation.TotalTroops(mine   ?? new List<Piece>());
        foe.Troops = Formation.TotalTroops(theirs ?? new List<Piece>());
        OnDone?.Invoke(me.Troops, foe.Troops);
    }

    // lib306 是 48×16 的三块，拿来当墙体 / 门 / 角楼
    static Texture2D wallTex; static readonly Dictionary<int, Sprite> wallCache = new Dictionary<int, Sprite>();
    static Sprite WallTile(int v)
    {
        if (wallCache.TryGetValue(v, out var sp)) return sp;
        if (wallTex == null) wallTex = Tex("orig/lib306.png");
        int i = v == 32 ? 0 : v == 16 ? 1 : 2;
        sp = Sprite.Create(wallTex, new Rect(i * 16, 0, 16, 16), new Vector2(.5f, .5f), 100);
        wallCache[v] = sp; return sp;
    }

    // lib56 的 12 列不是动作帧，是 **3 组朝向 × 4 兵种**。照抄 c.a(Graphics,byte,byte,int)：
    //   朝上 → 列 = 兵种 + 4     朝下 → 列 = 兵种 + 8
    //   朝右 → 列 = 兵种         朝左 → 同一列水平镜像
    // 兵种编号就是 bU：0 武将 1 骑兵 2 弓兵 3 步兵；
    // 行 = 阵营，**上排(红)是我方、下排(蓝)是敌方**——和战棋层反过来，原版就这样。
    static Texture2D fig; static readonly Dictionary<string, Sprite> figCache = new Dictionary<string, Sprite>();
    static Sprite Soldier(Arm a, int dir, bool ours)
    {
        int col = (int)a + (dir == 0 ? 4 : dir == 1 ? 8 : 0);
        var key = col + (ours ? "o" : "e");
        if (figCache.TryGetValue(key, out var sp)) return sp;
        if (fig == null) fig = Tex("orig/lib56.png");
        sp = Sprite.Create(fig, new Rect(col * 16, ours ? 16 : 0, 16, 16), new Vector2(.5f, .5f), 100);
        figCache[key] = sp; return sp;
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
            im.sprite = WallTile(kv.Value);     // lib306 的三段：墙体 / 门 / 角
            im.raycastTarget = false; tmp.Add(im.gameObject);
        }
        DrawSide(mine,   true);
        DrawSide(theirs, false);

        var a = Formation.ByArm(mine,   me.Hp);
        var b = Formation.ByArm(theirs, foe.Hp);
        for (int i = 0; i < 4; i++) { armCount[i].text = a[i].ToString(); foeCount[i].text = b[i].ToString(); }
        for (int i = 0; i < 4; i++) orderT[i].text = ORDER[orders[i]];
        dayT.text = (over ?? "交战中") + (siege ? "   · 攻城战" : "   · 野战");
    }

    void DrawSide(List<Piece> ps, bool ours)
    {
        foreach (var p in ps)
        {
            if (!p.Alive) continue;
            var im = Node("p", gridRoot).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, p.Col * C - 2, p.Row * C - 4, C + 4, C + 4);
            im.sprite = Soldier(p.Arm, p.Dir, ours);   // 原版小兵图，这屏还没做新素材
            im.raycastTarget = false; tmp.Add(im.gameObject);
            if (p.Dir == 2)                            // 朝左＝把朝右那张水平镜像
            {
                im.rectTransform.localScale = new Vector3(-1, 1, 1);
                im.rectTransform.anchoredPosition += new Vector2(C + 4, 0);
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
                Place((RectTransform)hl.transform, p.Col * C - 2, p.Row * C - 4, C + 4, C + 4);
                hl.color = new Color(1f, .95f, .3f, .30f); hl.raycastTarget = false;
                tmp.Add(hl.gameObject); hl.transform.SetSiblingIndex(im.transform.GetSiblingIndex());
            }
            var l = Label(gridRoot, p.Arm == Arm.None ? "将" : (p.Troops / 100).ToString(),
                          10, Color.white, TextAnchor.MiddleCenter);
            Place(l.rectTransform, p.Col * C, p.Row * C, C, C);
            tmp.Add(l.gameObject);
        }
    }
}
