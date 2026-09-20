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
    Dictionary<int, Officer> book;

    /// 自检模式：命令行给 -shots <目录> 就自动走一遍三屏各截一张再退出。
    /// 我不能把没亲眼看过的画面交出去，但 macOS 的 screencapture 要屏幕录制权限，
    /// 所以让程序自己截。
    static string ShotDir()
    {
        var a = System.Environment.GetCommandLineArgs();
        for (int i = 0; i < a.Length - 1; i++) if (a[i] == "-shots") return a[i + 1];
        return null;
    }

    void Update() { clash?.Tick(); }

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

        // 把我方一支挪到敌方旁边，直接触发攻击，省得模拟点击
        var me = FindUnit(true); var foe = FindUnit(false);
        me.X = foe.X - 1; me.Y = foe.Y;
        field.Refresh();
        yield return Shot(dir, "2_贴住敌军");

        field.OnClash(me, foe);
        yield return Shot(dir, "3_阵型选择");

        clash.PickForTest(1);                      // 选楔形
        yield return Shot(dir, "4_阵型战·野战");

        clash.AdvanceForTest();
        clash.AdvanceForTest();
        yield return Shot(dir, "5_推进两日");

        // 再来一场攻城：把敌方放到城池格上
        clash.FinishForTest();
        yield return null;
        var g = BattleRules.LoadMap(Path.Combine(BattleDir, "maps", CITY + ".map"));
        for (int y = 0; y < g.GetLength(0); y++)
            for (int x = 0; x < g.GetLength(1); x++)
                if ((g[y, x] & 0xF) == 2) { foe.X = x; foe.Y = y; me.X = x - 1; me.Y = y; }
        field.Refresh();
        field.OnClash(me, foe);
        yield return Shot(dir, "6_攻城·阵型选择");
        clash.PickForTest(1);
        yield return Shot(dir, "7_攻城·守方贴墙");

        Application.Quit();
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
                roster.Add(new Unit { O = Clone(o), X = ox[i], Y = oy[i], Ours = true, Dir = "e" });
        int[] fx = { 26, 27, 28, 27 }, fy = { 8, 9, 10, 11 };
        for (int i = 0; i < FOES.Length; i++)
            if (book.TryGetValue(FOES[i], out var o))
                roster.Add(new Unit { O = Clone(o), X = fx[i], Y = fy[i], Ours = false, Dir = "e", Flip = true });
        foreach (var u in roster) if (u.O.Troops == 0) u.O.Troops = 800;   // 管辂之流开局没兵，给点

        field = new BattleField(root, CITY, ThemeOf(CITY), roster, REF_W, REF_H);
        field.OnClash = (me, foe) =>
        {
            field.Root.gameObject.SetActive(false);
            int terrain = TerrainUnder(foe);
            clash = new ClashView(root, me.O, foe.O, terrain, true, REF_W, REF_H);
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
