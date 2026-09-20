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
    int day = 1;
    readonly int[] orders = new int[4];             // 武将/骑/弓/步 各自的令
    static readonly string[] ORDER = { "前进", "待机", "撤退", "包围", "计策" };
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
    public void AdvanceForTest() { NextDay(); }
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
        Place(dayT.rectTransform, 14, 14, 200, 22);

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
            bt.onClick.AddListener(() => { orders[k] = (orders[k] + 1) % ORDER.Length; Redraw(); });
            orderT[i] = Label(b, "", 14, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);
            Fill(orderT[i].rectTransform);
        }
        var go = Node("go", orderRoot);
        Place(go, refW - 130, 66, 110, 30);
        var gi = go.gameObject.AddComponent<Image>(); gi.sprite = Frame(); gi.type = Image.Type.Sliced;
        var gb = go.gameObject.AddComponent<Button>(); gb.transition = Selectable.Transition.None;
        gb.onClick.AddListener(NextDay);
        var gl = Label(go, "推进一日", 13, new Color32(0xF0,0xE0,0xB8,255), TextAnchor.MiddleCenter);
        Fill(gl.rectTransform);

        noteT = Label(field, BattleRules.AttritionNote, 11, new Color32(0xC0,0x90,0x70,255));
        Place(noteT.rectTransform, 14, ORDER_Y + 112, refW - 28, 18);

        var quit = Node("quit", field);
        Place(quit, 14, ORDER_Y + 136, 110, 26);
        var qi = quit.gameObject.AddComponent<Image>(); qi.sprite = Frame(); qi.type = Image.Type.Sliced;
        var qb = quit.gameObject.AddComponent<Button>(); qb.transition = Selectable.Transition.None;
        qb.onClick.AddListener(Finish);
        var ql = Label(quit, "收兵回战场", 12, new Color32(0xE0,0xD0,0xA8,255), TextAnchor.MiddleCenter);
        Fill(ql.rectTransform);
    }

    void NextDay()
    {
        // 参战的兵棋：下「前进」或「包围」的才算接战
        int myM = 0, myT = 0, foeM = 0, foeT = 0;
        foreach (var p in mine)
            if (p.Alive && p.Arm != Arm.None && (orders[(int)p.Arm] == 0 || orders[(int)p.Arm] == 3))
            { myM += BattleRules.PieceMight(me, p.Arm); myT += p.Troops; }
        foreach (var p in theirs)
            if (p.Alive && p.Arm != Arm.None) { foeM += BattleRules.PieceMight(foe, p.Arm); foeT += p.Troops; }

        BattleRules.Attrition(myM, myT, foeM, foeT, rng, out int myLoss, out int foeLoss);
        Bleed(mine,   myLoss,  p => orders[(int)p.Arm] == 0 || orders[(int)p.Arm] == 3);
        Bleed(theirs, foeLoss, p => true);

        int cc = BattleRules.Counter(me, foe);             // 兵种克制只影响体力消耗的方向
        me.Hp  = Mathf.Max(0, me.Hp  - (3 - cc));
        foe.Hp = Mathf.Max(0, foe.Hp - (3 + cc));

        day++;
        if (Formation.TotalTroops(mine) == 0 || Formation.TotalTroops(theirs) == 0
            || me.Hp == 0 || foe.Hp == 0) { Redraw(); Finish(); return; }
        Redraw();
    }

    void Bleed(List<Piece> ps, int loss, System.Func<Piece, bool> engaged)
    {
        var idx = new List<int>();
        for (int i = 0; i < ps.Count; i++)
            if (ps[i].Alive && ps[i].Arm != Arm.None && engaged(ps[i])) idx.Add(i);
        if (idx.Count == 0) return;
        // 从最前面（最靠近敌方）的兵棋开始掉，和「前排先死」的观感一致
        idx.Sort((a, b) => ps[b].Col.CompareTo(ps[a].Col));
        foreach (int i in idx)
        {
            if (loss <= 0) break;
            var p = ps[i];
            int d = Mathf.Min(p.Troops, loss);
            p.Troops -= d; loss -= d;
            if (p.Troops <= 0) { p.Troops = 0; p.Alive = false; }
            ps[i] = p;
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
    static Sprite WallTile(int v)
    {
        if (wallCache.TryGetValue(v, out var sp)) return sp;
        if (wallTex == null) wallTex = Tex("orig/lib306.png");
        int i = v == 32 ? 0 : v == 16 ? 1 : 2;
        sp = Sprite.Create(wallTex, new Rect(i * 16, 0, 16, 16), new Vector2(.5f, .5f), 100);
        wallCache[v] = sp; return sp;
    }

    // lib56 是 12 列 × 2 行的 16×16 小兵：上排红、下排蓝。
    // 列按兵种取：武将 0、骑兵 3、弓兵 6、步兵 9（同一兵种的几列是动作帧）。
    static Texture2D fig; static readonly Dictionary<string, Sprite> figCache = new Dictionary<string, Sprite>();
    static Sprite Soldier(Arm a, bool blue)
    {
        int col = a == Arm.None ? 0 : a == Arm.Qi ? 3 : a == Arm.Gong ? 6 : 9;
        var key = col + (blue ? "b" : "r");
        if (figCache.TryGetValue(key, out var sp)) return sp;
        if (fig == null) fig = Tex("orig/lib56.png");
        sp = Sprite.Create(fig, new Rect(col * 16, blue ? 0 : 16, 16, 16), new Vector2(.5f, .5f), 100);
        figCache[key] = sp; return sp;
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
        dayT.text = "第 " + day + " 日" + (siege ? "   攻城战" : "");
    }

    void DrawSide(List<Piece> ps, bool ours)
    {
        foreach (var p in ps)
        {
            if (!p.Alive) continue;
            var im = Node("p", gridRoot).gameObject.AddComponent<Image>();
            Place((RectTransform)im.transform, p.Col * C - 2, p.Row * C - 4, C + 4, C + 4);
            im.sprite = Soldier(p.Arm, ours);          // 原版小兵图，这屏还没做新素材
            im.raycastTarget = false; tmp.Add(im.gameObject);
            var l = Label(gridRoot, p.Arm == Arm.None ? "将" : (p.Troops / 100).ToString(),
                          10, Color.white, TextAnchor.MiddleCenter);
            Place(l.rectTransform, p.Col * C, p.Row * C, C, C);
            tmp.Add(l.gameObject);
        }
    }
}
