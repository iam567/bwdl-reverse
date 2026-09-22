// 战斗试玩版的入口：战棋层 ↔ 阵型选择/阵型战 两屏来回。
// 素材：地块与部队用新做的，阵型那两屏还没做新的，先用原版 jar 抽出来的。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class BattleGame : MonoBehaviour
{
    public Font font;
    const int REF_W = 375, REF_H = 812;
    public static string BattleDir => Path.Combine(ArtDir, "battle");

    // 试玩用的一场：洛阳（中原），曹营四将 对 袁营四将
    const int CITY = 13;
    static readonly int[] OURS = { 7, 8, 3, 178 };        // 关羽 张飞 刘备 诸葛亮
    static readonly int[] FOES = { 196, 61, 2, 1 };       // 吕布 曹操 献帝 管辂

    RectTransform root;
    BattleField field;
    ClashView clash;
    DuelView duel;
    Dictionary<int, Officer> book;

    /// 自检模式：命令行给 -shots <目录> 就自动走一遍三屏各截一张再退出。
    /// 我不能把没亲眼看过的画面交出去，但 macOS 的 screencapture 要屏幕录制权限，
    /// 所以让程序自己截。
    static bool HasArg(string k) => System.Array.IndexOf(System.Environment.GetCommandLineArgs(), k) >= 0;
    void Awake() { ClashView.Trace = HasArg("-trace"); }

    static string ShotDir()
    {
        var a = System.Environment.GetCommandLineArgs();
        for (int i = 0; i < a.Length - 1; i++) if (a[i] == "-shots") return a[i + 1];
        return null;
    }

    void Update() { duel?.Tick(); if (duel == null) clash?.Tick(); if (clash == null && duel == null) { field?.Tick(); field?.PlotPopTick(); } }

    void Start()
    {
        UIFont = font;
        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>(); canvas.renderMode = RenderMode.ScreenSpaceOverlay;
        var sc = go.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(REF_W, REF_H); sc.matchWidthOrHeight = 0;
        root = (RectTransform)go.transform;

        book = BattleRules.LoadOfficers(Path.Combine(BattleDir, "officers.csv"));
        OpenField();
        var dir = ShotDir();
        if (dir != null) StartCoroutine(SelfCheck(dir));
    }

    System.Collections.IEnumerator SelfCheck(string dir)
    {
        Directory.CreateDirectory(dir);
        yield return Shot(dir, "1_战棋层");

        field.MoveForTest(FindUnit(true));        // 按「移动」：亮出按原版消耗算出来的可达范围
        yield return Shot(dir, "1b_移动范围");

        // 把我方一支挪到敌方旁边，直接触发攻击，省得模拟点击
        var me = FindUnit(true); var foe = FindUnit(false);
        me.X = foe.X - 1; me.Y = foe.Y;
        field.PlotMenuForTest(me);                 // 计策菜单
        yield return Shot(dir, "2a_计策菜单");
        field.PlotMenuForTest(me, true, 15);       // 16 种全开，选中奇门遁甲
        yield return Shot(dir, "2a2_计策16种");
        field.PlotForTest(me, 4, foe);             // 敌军站在水格上，用乱水（火计不许打水，原版 2330）
        yield return Shot(dir, "2b_乱水");
        field.InfoForTest(me, foe);                // 情报：点敌将弹武将信息
        yield return Shot(dir, "2c_情报");
        field.LandForTest(me, 4, 9);               // 关羽放回原位，再跑 14 步敌军回合看 AI 走位
        field.EnemyTurnForTest(14);
        yield return Shot(dir, "2h_敌军回合");
        field.LandForTest(me, foe.X - 1, foe.Y);
        field.LandForTest(me, 5, 5);               // 站到粮仓(码3)上：看压不压住、弹「是否进入村庄?」
        yield return Shot(dir, "2e_村庄");
        field.LandForTest(me, 19, 17);             // 药庐(码4)：进村 → 治疗提示
        field.AskYesForTest();
        field.HealForTest(me);
        yield return Shot(dir, "2f_治疗问");
        field.AskYesForTest();
        yield return Shot(dir, "2g_治疗后");
        field.LandForTest(me, foe.X - 1, foe.Y);   // 放回原位
        field.RetreatMenuForTest(me);              // 撤退：弹相邻城 + 下野（洛阳四邻都不是刘备的 → 只剩下野）
        yield return Shot(dir, "2d_撤退");
        var lord = field.UnitOf(3);                // 刘备是主公：不能下野、无城可退 → 背水一战
        if (lord != null) { field.RetreatMenuForTest(lord); yield return Shot(dir, "2d2_主公无城"); }
        field.CommandForTest(me, 2);               // 选中我方、切到攻击模式，看目标标记
        yield return Shot(dir, "2_贴住敌军");

        field.OnClash(me, foe, true);
        yield return Shot(dir, "3_阵型选择");

        clash.PickForTest(1);                      // 选楔形
        yield return Shot(dir, "4_阵型战·野战");

        clash.SkillPanelForTest();                 // 武将下「计策」，看六个能力
        yield return Shot(dir, "4b_计策点能力");

        clash.CmdPopForTest(Arm.Qi);               // 点一枚骑兵，看它旁边弹出来的令菜单
        yield return Shot(dir, "4c_点兵种下令");

        clash.AdvanceForTest(45);                  // 120 拍会一路打到武将碰面进单挑，看不到阵型战
        yield return Shot(dir, "5_打了一阵");
        clash.ZoomForTest(0.2f);                   // 捏到最小：整场 16 列都在视窗里
        yield return Shot(dir, "5h_缩到整场");
        clash.ZoomForTest(1f);
        // 火矢：点了之后再推几拍，趁箭还在飞连抓几帧——Shot() 要等 0.35 秒，箭早到了
        // 特效四组：呐喊圈 / 咒缚绳 / 爆炎 / 命中（推一拍后连抓，命中特效只活 0.46 秒）
        clash.BuffForTest(3);
        for (int i = 0; i < 3; i++) { ScreenCapture.CaptureScreenshot(Path.Combine(dir, "5d_呐喊_" + i + ".png")); yield return new WaitForSeconds(.18f); }
        clash.BuffForTest(0);
        for (int i = 0; i < 3; i++) { ScreenCapture.CaptureScreenshot(Path.Combine(dir, "5e_咒缚_" + i + ".png")); yield return new WaitForSeconds(.18f); }
        clash.BuffForTest(-1);
        clash.BlazeForTest();
        for (int i = 0; i < 5; i++) { ScreenCapture.CaptureScreenshot(Path.Combine(dir, "5f_爆炎_" + i + ".png")); yield return new WaitForSeconds(.1f); }
        // 命中三种：骑劈 / 箭着 / 突刺，各标一次、连抓四帧
        for (int a = 1; a <= 3; a++)
        {
            clash.HitForTest(a);
            for (int i = 0; i < 4; i++) { ScreenCapture.CaptureScreenshot(Path.Combine(dir, "5g_命中" + a + "_" + i + ".png")); yield return new WaitForSeconds(.1f); }
            yield return new WaitForSeconds(.3f);
        }
        clash.FireForTest();
        clash.ShotForTest();                       // 注入一支火矢，逐帧抓，不靠 AI 轮到弓兵
        for (int i = 0; i < 10; i++)
        {
            ScreenCapture.CaptureScreenshot(Path.Combine(dir, "5c_火矢_" + i + ".png"));
            yield return new WaitForSeconds(.05f);
        }
        yield return new WaitForSeconds(.4f);

        clash.OnDuel();                            // 双方武将碰上的那一屏
        yield return Shot(dir, "5b_单挑");
        // 这一屏拍完必须销毁。之前它一直留在底下，后面每段招式演示销毁自己的那一帧，
        // 底下这张落日背景就漏出来几毫秒——就是「莫名其妙插一下落日」的来源。
        if (duel != null) { Object.Destroy(duel.Root.gameObject); duel = null; }
        yield return null;
        // 三种招式各演一遍：攻击 / 全力 / 投降
        // 调阵型战的时候不用每次都把五段单挑演示跑一遍：命令行给 -skipduel 就跳过
        if (!HasArg("-skipduel"))
        {
            field.Root.gameObject.SetActive(false);
            if (clash != null) clash.Root.gameObject.SetActive(false);
            yield return DuelDemo(dir, "a攻击", 1, 34);
            yield return DuelDemo(dir, "b全力", 2, 34);
            yield return DuelDemo(dir, "c投降", 5, 10);
            yield return DuelDemo(dir, "d落马", 0, 16, 3);   // 牵制必中，体力 3 一击必死
            yield return DuelDemo(dir, "e招降", 3, 14, 10, 35);   // 体力低、忠诚低才招得动
            field.Root.gameObject.SetActive(true);
            if (clash != null) clash.Root.gameObject.SetActive(true);
        }

        // 再来一场攻城：把敌方放到城池格上
        clash.FinishForTest();
        yield return null;
        var g = BattleRules.LoadMap(Path.Combine(BattleDir, "maps", CITY + ".map"));
        for (int y = 0; y < g.GetLength(0); y++)
            for (int x = 0; x < g.GetLength(1); x++)
                if ((g[y, x] & 0xF) == 2) { foe.X = x; foe.Y = y; me.X = x - 1; me.Y = y; }
        field.Refresh();
        field.OnClash(me, foe, true);
        yield return Shot(dir, "6_攻城·阵型选择");
        clash.PickForTest(1);
        yield return Shot(dir, "7_攻城·守方贴墙");
        clash.AdvanceForTest(100);                 // 看攻方是不是沿着走廊从门里挤进去
        yield return Shot(dir, "7b_攻城·打了一阵");

        Application.Quit();
    }

    /// 开一场干净的单挑，下某一招，连拍。关羽 对 吕布，水岸场景。
    System.Collections.IEnumerator DuelDemo(string dir, string tag, int move, int shots,
                                            int foeHp = 0, int foeLoyal = 0)
    {
        var a = Clone(book[7]); var b = Clone(book[196]);
        if (foeHp > 0) b.Hp = foeHp;            // 压低体力，好让这一击就能看到落马
        // 吕布忠诚 100，而 b.k() 第一行就是 if (忠诚 > 99) return false —— 他永远招不动。
        // 演示时压低忠诚，才看得到归顺那一段。
        if (foeLoyal > 0) b.Loyal = foeLoyal;
        // 演招式这几屏改用攻城场景：那一档才有一城一套的动态背景，顺带一起看了
        duel = new DuelView(root, a, b, 3, REF_W, REF_H, ThemeOf(CITY), CITY);
        yield return new WaitForSeconds(.4f);
        duel.PickForTest(move);
        for (int i = 0; i < shots; i++)
        {
            yield return new WaitForSeconds(.25f);
            ScreenCapture.CaptureScreenshot(Path.Combine(dir, tag + "_" + i.ToString("00") + ".png"));
        }
        yield return new WaitForSeconds(.3f);
        Object.Destroy(duel.Root.gameObject); duel = null;
        yield return null;
    }

    Unit FindUnit(bool ours)
    {
        foreach (var u in field.Units) if (u.Ours == ours && u.Alive) return u;
        return null;
    }

    System.Collections.IEnumerator Shot(string dir, string name)
    {
        yield return new WaitForSeconds(.35f);
        ScreenCapture.CaptureScreenshot(Path.Combine(dir, name + ".png"));
        yield return new WaitForSeconds(.45f);
        Debug.Log("截图 " + name);
    }

    string ThemeOf(int city)
    {
        var j = File.ReadAllText(Path.Combine(BattleDir, "city-theme.json"));
        // 只有 36 个 "id":"主题" 的扁平表，不值得拖一个 JSON 库进来
        int i = j.IndexOf("\"" + city + "\"");
        if (i < 0) return "zhongyuan";
        int a = j.IndexOf('"', j.IndexOf(':', i) + 1) + 1;
        return j.Substring(a, j.IndexOf('"', a) - a);
    }

    void OpenField()
    {
        var roster = new List<Unit>();
        int[] ox = { 4, 5, 3, 4 }, oy = { 9, 10, 11, 12 };
        for (int i = 0; i < OURS.Length; i++)
            if (book.TryGetValue(OURS[i], out var o))
                roster.Add(new Unit { O = Clone(o), X = ox[i], Y = oy[i], Ours = true, Dir = "e",
                                      Draw = new Vector2(ox[i], oy[i]) });
        int[] fx = { 26, 27, 28, 27 }, fy = { 8, 9, 10, 11 };
        for (int i = 0; i < FOES.Length; i++)
            if (book.TryGetValue(FOES[i], out var o))
                roster.Add(new Unit { O = Clone(o), X = fx[i], Y = fy[i], Ours = false, Dir = "e", Flip = true,
                                      Draw = new Vector2(fx[i], fy[i]) });
        foreach (var u in roster) if (u.O.Troops == 0) u.O.Troops = 800;   // 管辂之流开局没兵，给点

        // 顶栏两侧的金粮：守方就是这座城；攻方的补给城原版是出兵的那座，
        // 演示里没有出兵城，取它的第一座邻城顶上——数据里来的，不是编的。
        var supply = BattleRules.LoadCitySupply(Path.Combine(BattleDir, "cities.csv"));
        var adj = BattleRules.AdjacentCities(Path.Combine(BattleDir, "city-adjacency.json"), CITY);
        int atkCity = adj.Length > 0 ? adj[0] : CITY;
        supply.TryGetValue(atkCity, out var mine); supply.TryGetValue(CITY, out var theirs);
        field = new BattleField(root, CITY, ThemeOf(CITY), roster, REF_W, REF_H,
                                new[] { mine.gold, mine.food, theirs.gold, theirs.food }, 3);   // 我方君主刘备
        field.OnClash = (me, foe, weAttack) =>
        {
            field.Root.gameObject.SetActive(false);
            int terrain = TerrainUnder(weAttack ? foe : me);   // 场景看守方脚下那格
            clash = new ClashView(root, me.O, foe.O, terrain, weAttack, REF_W, REF_H, ThemeOf(CITY), CITY);
            clash.OnDuel = () =>
            {
                clash.Root.gameObject.SetActive(false);
                duel = new DuelView(root, me.O, foe.O, clash.Scene, REF_W, REF_H, ThemeOf(CITY), CITY);
                duel.OnDone = () =>
                {
                    Object.Destroy(duel.Root.gameObject); duel = null;
                    clash.Root.gameObject.SetActive(true);   // 单挑打完回阵型战接着打
                };
                // 投降：原版 b.b(s,true) 置 aq=2 整场结束、主将 bu=2 退出，其余部队逐一选城撤退。
                // 我们改成：整场结束，回战棋层，给投降的这支部队弹「退往何处」（城 / 下野），记 HOUSE_RULES。
                duel.OnSurrender = () =>
                {
                    Object.Destroy(duel.Root.gameObject); duel = null;
                    clash.Root.gameObject.SetActive(true);
                    clash.Surrender();                       // → clash.OnDone → 回战棋层
                    field.OpenRetreatFor(me);
                };
            };
            clash.OnDone = (mine, theirs) =>
            {
                Object.Destroy(clash.Root.gameObject); clash = null;
                if (me.O.Troops  <= 0 || me.O.Hp  <= 0) me.Alive = false;
                if (foe.O.Troops <= 0 || foe.O.Hp <= 0) foe.Alive = false;
                field.Root.gameObject.SetActive(true);
                field.Refresh();
            };
        };
    }

    int TerrainUnder(Unit u)
    {
        var g = BattleRules.LoadMap(Path.Combine(BattleDir, "maps", CITY + ".map"));
        if (u.Y < 0 || u.Y >= g.GetLength(0) || u.X < 0 || u.X >= g.GetLength(1)) return 0;
        return g[u.Y, u.X] & 0xF;
    }

    static Officer Clone(Officer o) => new Officer {
        Id = o.Id, Name = o.Name, Lv = o.Lv, Kind = o.Kind, Troops = o.Troops,
        HpMax = o.HpMax, Hp = o.Hp, Int = o.Int, Might = o.Might, Virtue = o.Virtue,
        Loyal = o.Loyal, Plots = o.Plots, ArmLv = o.ArmLv, SubLv = o.SubLv,
    };
}
