// 入口：建画布，管地图与城内两屏的切换。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public partial class Game : MonoBehaviour
{
    public Font font;
    public Font[] fonts;
    public AudioClip bgmMap;              // 「古地图卷」：主界面 / 大地图 / 城内共用
    public AudioClip bgmWarIntro, bgmWarLoop;      // 「铁骑冲锋」：前奏放一次，循环段无限循环（Music.cs）
    public AudioClip[] bgmWin, bgmLose;            // 天下一统 / 势力灭亡，各两首随机
    public AudioClip[] sfx;               // Assets/Audio/sfx/*.ogg，按文件名当键（Sfx.cs）                  // Builder.FontFiles：四套字体 × [常规, 粗]
    // 排版一律按 ScreenFit 算出来的逻辑尺寸，不写死 375×812。
    static float RefW => ScreenFit.RefW;
    static float RefH => ScreenFit.RefH;

    RectTransform root;
    MapView mapView; CityView cityView; MapData data;
    MapData.City curCity;
    Text toast; float toastUntil;

    PoliticsView politics;
    PersonnelView personnel;
    MilitaryView military;
    MarketView market;
    DiplomacyView diplomacy;
    bool pickingCapital;                // 自建：正在大地图上挑建都的空城
    string pendingDp;
    string pendingSc;                 // 重建时开着的集市指令（浮窗里那条的原话）
    // 战争进行中：战棋 / 阵型战 / 单挑三层，打完由 War.Settle 善后
    BattleField field; ClashView clash; DuelView duel; War.Sortie sortieNow; bool inBattle;
    VillageView village;
    int pendingJs = -1;               // 重建时开着的军事指令
    SystemView sys;
    TitleView title; SettingsView settings; AlbumView album;
    bool atTitle;                     // 停在主界面
    Text mapYear, mapSub;             // 大地图顶栏：年月 / 君主 · 城数
    RectTransform mapSysRT, mapYearRT;
    Dictionary<int, Officer> officers;
    int orders;                       // 本回合还剩几道令（原版 aG）
    int year = Politics.ScenarioYear, month = 1;   // 原版 aD / aC：一回合一个月，开局 189 年 1 月
    int pendingCmd = -1;              // 重建时开着的内政指令，重建完接着开
    int pendingRs = -1;               // 同上，人事指令
    System.Action afterBuild;         // 自检钩子：每次重建完重放一遍
    float resizeAt = -1f;             // 窗口尺寸变动的时刻，用来防抖

    void Start()
    {
        Application.targetFrameRate = 60;                                   // 手机默认 30 帧，拖地图发涩
        UIKit.UIFont = font;
        if (fonts != null && fonts.Length > 0) FontChoice.Init(fonts);
        if (!Sim && System.Array.IndexOf(System.Environment.GetCommandLineArgs(), "-batchmode") < 0)
        { Music.Init(gameObject); Music.Play(bgmMap); Sfx.Init(gameObject, sfx); }

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>(); canvas.renderMode = RenderMode.ScreenSpaceOverlay;
        ScreenFit.Attach(go.GetComponent<CanvasScaler>());
        root = UIKit.Node("Safe", go.transform);                   // 安全区：手机上避开刘海 / 灵动岛 / 底部横条
        ScreenFit.AttachSafe(root);

        ResetState();
        // 自检 / 截图钩子直接进游戏；正常启动停在主界面
        var argv = System.Environment.GetCommandLineArgs();
        atTitle = true;
        foreach (var a in argv) if (a.StartsWith("-") && a != "-shot" && a != "-logFile" && a != "-batchmode" && a != "-nographics") atTitle = false;
        if (System.Array.IndexOf(argv, "-title") >= 0) atTitle = true;
        Build();

        Debug.Log("就绪：地图上点城池选中，再点一次进城；城内点建筑出指令，右上角「地图」返回");
        foreach (var a in System.Environment.GetCommandLineArgs())
            if (a == "-politics") { PoliticsSelfCheck(); break; }
            else if (a == "-city")                        // 只进城，不开浮层
            {
                afterBuild = () => {
                    if (curCity == null) mapView.OnEnter(data.cities.Find(x => x.name == "洛阳"));
                };
                afterBuild();
                break;
            }
            else if (a == "-pop")                         // 截图用：进洛阳，直接点开太守府的指令浮窗
            {
                afterBuild = () => {
                    if (curCity == null) mapView.OnEnter(data.cities.Find(x => x.name == "洛阳"));
                    cityView.SelectByKey("taishou");
                };
                afterBuild();
                break;
            }
            else if (a == "-personnel") { PersonnelSelfCheck(); break; }
            else if (a == "-js")                          // 截图用：-js <0..3> [-at 城名]，直接开一条军事指令
            {
                var av = System.Environment.GetCommandLineArgs();
                int k = int.Parse(av[System.Array.IndexOf(av, "-js") + 1]);
                int ai = System.Array.IndexOf(av, "-at");
                string at = ai >= 0 ? av[ai + 1] : "陈留";
                afterBuild = () => {
                    var c0 = data.cities.Find(x => x.name == at);
                    if (curCity == null) mapView.OnEnter(c0);
                    military.Begin((Military.Cmd)k, data, c0);
                };
                afterBuild();
                break;
            }
            else if (a == "-clashtest")                    // 自检：野战阵型战，我方先动手，看敌兵动不动（-trace 记每一步）
            {
                var c0 = data.cities.Find(x => x.name == "陈留");
                var l = Personnel.Of(c0.id).A;
                ClashView.Trace = true;
                var av = System.Environment.GetCommandLineArgs();
                int ft = System.Array.IndexOf(av, "-foetroops"); if (ft >= 0) l[1].Troops = int.Parse(av[ft + 1]);   // 敌方兵很少时撤不撤
                int mt = System.Array.IndexOf(av, "-mytroops"); if (mt >= 0) l[0].Troops = int.Parse(av[mt + 1]);
                int sc = System.Array.IndexOf(av, "-scene");                                                        // 0 野战 / 3 攻城
                var cv = new ClashView(root, l[0], l[1], sc >= 0 ? int.Parse(av[sc + 1]) : 0, true, RefW, RefH, BattleGame.ThemeOf(c0.id), c0.id);
                cv.PickForTest(0);
                int sk = System.Array.IndexOf(av, "-skill"); if (sk >= 0) { cv.SkillForTest(int.Parse(av[sk + 1])); if (System.Array.IndexOf(av, "-shot") >= 0) break; }
                int adv = System.Array.IndexOf(av, "-ticks");
                cv.AdvanceForTest(adv >= 0 ? int.Parse(av[adv + 1]) : 40);
                Debug.Log("[阵型自检] 结果：" + cv.OverForTest);
                if (System.Array.IndexOf(av, "-shot") < 0) Application.Quit();
                break;
            }
            else if (a == "-lordtest")                     // 自检：把一家君主挪进玩家城，看是否继位（-diff 2 孙策）
            {
                afterBuild = () => {
                    afterBuild = null; playerFaction = Realm.Player = "caocao"; atTitle = false;
                    var sun = Realm.All.Find(f => officers[f.RulerId].Name == "孙策");
                    var lord = officers[sun.RulerId];
                    foreach (var c in data.cities) Personnel.Of(c.id).A.Remove(lord);
                    Personnel.Of(data.cities.Find(c => c.owner == "caocao").id).A.Add(lord);
                    Debug.Log("[君主自检] 之前 " + sun.Key + " 君主 " + officers[sun.RulerId].Name);
                    foreach (var m in War.CheckRulers(data, officers)) Debug.Log("[君主自检] " + m);
                    Debug.Log("[君主自检] 之后 " + sun.Key + " 君主 " + officers[sun.RulerId].Name + "，孙策 RulerOf=" + (Realm.RulerOf(lord)?.Key ?? "无"));
                    Application.Quit();
                };
                afterBuild(); break;
            }
            else if (a == "-deftest")                      // 自检：AI 打上门，跑几日看敌军往前压没有（-as 势力）
            {
                afterBuild = () => { afterBuild = null; StartCoroutine(DefenseSelfCheck()); };
                afterBuild();
                break;
            }
            else if (a == "-war" || a == "-wartest")        // 截图 / 自检：陈留出兵打一座相邻的敌城
            {
                bool test = a == "-wartest";
                afterBuild = () => {
                    if (inBattle) return;
                    var c0 = data.cities.Find(x => x.name == "陈留");
                    if (curCity == null) mapView.OnEnter(c0);
                    if (test) { afterBuild = null; WarSelfCheck(c0); return; }   // 自检里自己会重建，别让钩子再进来
                    var tgt = War.Targets(data, c0).Find(x => x.owner != "none");
                    var army = new List<Officer>(Personnel.Of(c0.id).A.GetRange(1, 3));
                    var st = new War.Sortie { From = c0, To = tgt, Army = army, Gold = 100, Food = 300 };
                    War.Depart(data, st); War.Launch(data, st, playerFaction, Lord);
                    StartBattle(st);
                };
                afterBuild();
                break;
            }
            else if (a == "-dp")                          // 截图用：-dp <同盟|离间|招揽|情报> [-at 城名]
            {
                var av = System.Environment.GetCommandLineArgs();
                string label = av[System.Array.IndexOf(av, "-dp") + 1];
                int ai = System.Array.IndexOf(av, "-at");
                string at = ai >= 0 ? av[ai + 1] : "陈留";
                afterBuild = () => {
                    var c0 = data.cities.Find(x => x.name == at);
                    if (curCity == null) mapView.OnEnter(c0);
                    diplomacy.Begin(label, data, c0);
                };
                afterBuild();
                break;
            }
            else if (a == "-sc")                          // 截图用：-sc <浮窗里那条> [-at 城名]
            {
                var av = System.Environment.GetCommandLineArgs();
                string label = av[System.Array.IndexOf(av, "-sc") + 1];
                int ai = System.Array.IndexOf(av, "-at");
                string at = ai >= 0 ? av[ai + 1] : "洛阳";
                afterBuild = () => {
                    var c0 = data.cities.Find(x => x.name == at);
                    if (curCity == null) mapView.OnEnter(c0);
                    int bi = System.Array.IndexOf(av, "-shop");                    // -shop <武将序> <货架>：直接进武器店
                    if (bi >= 0) market.ShopForTest(data.stats.Find(x => x.id == c0.id), int.Parse(av[bi + 1]), int.Parse(av[bi + 2]));
                    else market.Begin(label, data.stats.Find(x => x.id == c0.id));
                };
                afterBuild();
                break;
            }
            else if (a == "-newgame") { NewGame(); break; }                 // 截图：君主选择
            else if (a == "-play")                                           // 截图：-play <势力键> [-ai]，开局后那一屏 / 敌方回合遮罩
            {
                var av = System.Environment.GetCommandLineArgs();
                string key = av[System.Array.IndexOf(av, "-play") + 1];
                BeginAs(Realm.Of(key));
                if (System.Array.IndexOf(av, "-ai") >= 0) { EndPlayerTurn(); }
                break;
            }
            else if (a == "-customtest")                  // 自检：内存里造两名自建武将，在桂阳建都，无人值守跑 12 个月
            {
                Sim = true; EventView.Auto = true; simMonths = 12;
                var r1 = new CustomOfficers.Rec { id = 229, name = "测甲", might = 80, iq = 70, hp = 80, virtue = 90, kind = 1, face = 233 };
                var r2 = new CustomOfficers.Rec { id = 230, name = "测乙", might = 40, iq = 95, hp = 60, virtue = 80, kind = 2, face = 234 };
                foreach (var r in new[] { r1, r2 }) { officers[r.id] = CustomOfficers.ToOfficer(r); BattleRules.Faces[r.id] = r.face; }
                FoundCustom(officers[229], new List<Officer> { officers[229], officers[230] }, data.cities.Find(c => c.name == "桂阳"));
                break;
            }
            else if (a == "-wintest")                     // 截图：统一天下的结局屏
            {
                BeginAs(Realm.Of("caocao"));
                foreach (var c in data.cities) { c.owner = "caocao"; data.stats.Find(x => x.id == c.id).owner = "caocao"; }
                var evs = new List<MonthEnd.Ev>(); int mm = month;
                if (MonthEnd.Run(data, officers, ref mm, year, turnRng, evs) == 1) Ending(true);
                break;
            }
            else if (a == "-pickcapital")                 // 截图：自建选城建都（内存里造一名自建武将）
            {
                var r1 = new CustomOfficers.Rec { id = 229, name = "测甲", might = 80, iq = 70, hp = 80, virtue = 90, kind = 1, face = 233 };
                officers[229] = CustomOfficers.ToOfficer(r1); BattleRules.Faces[229] = 233;
                atTitle = true; Build();
                rulerPick.OnPickCapital(officers[229], new List<Officer> { officers[229] }, data.cities.FindAll(c => c.owner == "none"));
                if (System.Array.IndexOf(System.Environment.GetCommandLineArgs(), "-sel") >= 0) mapView.Select(data.cities.Find(c => c.name == "长沙"));
                if (System.Array.IndexOf(System.Environment.GetCommandLineArgs(), "-zoomout") >= 0) mapView.ZoomForTest(0.01f);
                break;
            }
            else if (a == "-custom") { atTitle = true; Build(); custom.Open(officers); break; }   // 截图：自建武将菜单
            else if (a == "-customedit")                                          // 截图：-customedit <模板 0/1/2> [-female]
            {
                var av = System.Environment.GetCommandLineArgs();
                atTitle = true; Build();
                custom.EditForTest(int.Parse(av[System.Array.IndexOf(av, "-customedit") + 1]), System.Array.IndexOf(av, "-female") >= 0);
                break;
            }
            else if (a == "-sim")                         // 自检：-sim <月数> [-as 势力键]，无人值守跑若干个月，打月报
            {
                var av = System.Environment.GetCommandLineArgs();
                Sim = true; EventView.Auto = true;
                simMonths = int.Parse(av[System.Array.IndexOf(av, "-sim") + 1]);
                int ai2 = System.Array.IndexOf(av, "-as");
                string key = ai2 >= 0 ? av[ai2 + 1] : "han";
                BeginAs(Realm.Of(key));
                break;
            }
            else if (a == "-albumtest")                   // 自检：扮曹操，逐月末计数；第 5 个月把一员武将挪走再放回，看清零
            {
                afterBuild = () => {
                    afterBuild = null; albumDry = true; playerFaction = "caocao";
                    var c0 = data.cities.Find(c => c.owner == "caocao"); var a0 = Personnel.Of(c0.id).A; var gone = a0[a0.Count - 1];
                    for (int m = 1; m <= 13; m++)
                    {
                        if (m == 5) a0.Remove(gone);
                        if (m == 6) a0.Add(gone);
                        AlbumMonthTick();
                        Debug.Log("[霸图自检] 第 " + m + " 月末：" + gone.Name + " " + (serveMonths.TryGetValue(gone.Id, out int k) ? k : 0) + " 月，" + a0[0].Name + " " + serveMonths[a0[0].Id] + " 月");
                    }
                    Application.Quit();
                };
                afterBuild(); break;
            }
            else if (a == "-album") { afterBuild = () => { Album.Preview(data, data.cities[12].owner); album.Open(); if (System.Array.IndexOf(argv, "-detail") >= 0) album.DetailForTest(); }; afterBuild(); break; }   // 截图用：配 -title，先把洛阳那家的人算进去
            else if (a == "-settings") { afterBuild = () => { if (System.Array.IndexOf(argv, "-help") >= 0) settings.HelpForTest(); else settings.Open(); }; afterBuild(); break; }   // 截图用：配 -title
            else if (a == "-sys") { afterBuild = () => { sys.InGame = true; sys.Open(); }; afterBuild(); break; }
            else if (a == "-savetest") { SaveSelfCheck(); break; }
            else if (a == "-rs")                          // 截图用：-rs <0..3> [-at 城名]，直接开一条人事指令
            {
                var av = System.Environment.GetCommandLineArgs();
                int k = int.Parse(av[System.Array.IndexOf(av, "-rs") + 1]);
                int ai = System.Array.IndexOf(av, "-at");
                string at = ai >= 0 ? av[ai + 1] : "洛阳";
                afterBuild = () => {
                    var c0 = data.cities.Find(x => x.name == at);
                    if (curCity == null) mapView.OnEnter(c0);
                    int si2 = System.Array.IndexOf(av, "-step");
                    var st = data.stats.Find(x => x.id == c0.id);
                    if (si2 >= 0) personnel.ForTest((Personnel.Cmd)k, st, int.Parse(av[si2 + 1]));
                    else personnel.Begin((Personnel.Cmd)k, st);
                };
                afterBuild();
                break;
            }
            else if (a == "-polui" || a == "-polui2" || a == "-polui3")
            {
                // 截图用：直接开到某一屏。窗口尺寸一变整棵树就重建，所以挂成
                // afterBuild，每次重建完重放一遍，不然刚开窗那阵抖动就把它冲没了。
                int step = a == "-polui" ? 0 : a == "-polui2" ? 1 : 2;
                afterBuild = () => {
                    var c0 = data.cities.Find(x => x.name == "洛阳");
                    if (curCity == null) mapView.OnEnter(c0);
                    var st0 = data.stats.Find(x => x.id == c0.id);
                    if (step == 0) politics.Begin(Politics.Cmd.Reclaim, st0, officers);
                    else politics.ConfirmForTest(Politics.Cmd.Reclaim, st0, officers, 0, step == 2);
                };
                afterBuild();
                break;
            }

        {
            var args = System.Environment.GetCommandLineArgs();   // -shot <png>：等窗口稳定后自存一张图并退出
            int si = System.Array.IndexOf(args, "-shot");
            if (si >= 0 && si + 1 < args.Length) StartCoroutine(ShotAndQuit(args[si + 1]));
        }
    }

    /// 开局状态：36 城数值、武将表、三份名册、189 年 1 月。新游戏和读档都从这里起步
    /// scenario：-1 = 按设置里的难度（-diff 0/1/2 自检用，盖过设置）；读档传存档里记的那一版
    void ResetState(int scenario = -1)
    {
        if (scenario < 0)
        {
            var av = System.Environment.GetCommandLineArgs(); int di = System.Array.IndexOf(av, "-diff");
            scenario = di >= 0 ? int.Parse(av[di + 1]) : Difficulty.Level;
        }
        Scenario.Use(scenario);                       // 难度 = 版本：城池、武将整套换（Scenario.cs）
        data = JsonUtility.FromJson<MapData>(File.ReadAllText(Path.Combine(ArtDir, "map.json")));
        Scenario.ApplyCities(data);
        officers = BattleRules.LoadOfficers(Scenario.OfficersCsv);
        Scenario.ApplyFaces();                        // 武将号 → 立绘 / 头像（按名字共用）
        CustomOfficers.Register(officers);            // 自建武将（229~231）进武将表，不在任何城里
        Personnel.Init(officers);                     // 每城三份名册：仕官 / 在野未发现 / 已发现
        foreach (var st in data.stats) st.武将 = Personnel.Of(st.id).A.Count;
        if (Scenario.Level > 0) Military.RecountAll(data);   // 别的版本：兵力 / 武将数按那一版的武将表现算
        // 预备兵 v[] 不在 map.json 里，从原版城表第 15 列读
        foreach (var line in File.ReadAllLines(Scenario.CitiesCsv))
        {
            var f = line.Split(',');
            if (f.Length < 15 || !int.TryParse(f[0], out int cid)) continue;
            var st = data.stats.Find(x => x.id == cid);
            if (st != null) { int.TryParse(f[14], out st.预备兵); if (f.Length > 20) int.TryParse(f[20], out st.武库档); if (f.Length > 21) int.TryParse(f[21], out st.村档); }
        }
        year = Politics.ScenarioYear; month = 1; orders = 0;
        playerFaction = null; curCity = null; pendingCmd = pendingRs = -1;
        serveMonths.Clear();
        Realm.Init(data, officers);                   // 势力表：7 家君主（按武将号记）、同盟表清空
        if (loopCo != null) { StopCoroutine(loopCo); loopCo = null; }
        loopRunning = false; gameOver = false; playerTurnActive = false; aiNow = null; defense = null;
        if (events != null) { events.Hold = false; events.Council(null); }
        monthQueue = new List<string>();
        ai = new AI(data, turnRng);
        ai.Event = (k, t, x, w) => events.Push(k, t, x, w);
        ai.ExecuteWar = ExecuteAIWar;
    }

    string Lord => playerFaction == null ? null : (Realm.Ruler(playerFaction)?.Name ?? data.stats.Find(x => x.owner == playerFaction)?.ruler);

    void SaveTo(int slot)
    {
        var d = SaveGame.Capture(data, officers, playerFaction, Lord, year, month, orders, curCity != null ? curCity.id : 0);
        d.queue = new List<string>(monthQueue);
        d.scenario = Scenario.Level;
        foreach (var kv in serveMonths) { d.serveIds.Add(kv.Key); d.serveMonths.Add(kv.Value); }
        SaveGame.Write(slot, d);
    }

    void LoadFrom(int slot)
    {
        var d = SaveGame.Read(slot);
        if (d == null) { Toast("存档读取失败"); return; }
        ResetState(d.scenario);                          // 存档是哪一版就按哪一版的武将表读
        SaveGame.Apply(d, data, officers);
        for (int i = 0; i < d.serveIds.Count && i < d.serveMonths.Count; i++) serveMonths[d.serveIds[i]] = d.serveMonths[i];
        year = d.year; month = d.month; orders = d.orders; playerFaction = d.faction;
        curCity = d.curCity > 0 ? data.cities.Find(x => x.id == d.curCity) : null;
        monthQueue = d.queue ?? new List<string>();
        atTitle = false; gameOver = false;
        Build();
        CheckLords();                                    // 旧档里君主已经被擒 / 招降、却没继位的，读进来就补上
        Toast("读取完毕　" + d.label);
        StartLoop(true);                                // 存档只能在玩家那一格存：接着玩家这一格，剩下的令照旧
    }

    /// 大地图顶栏第二行：玩家君主 · 城数（选势力之前空着）
    void MapSub()
    {
        if (mapSub == null) return;
        var lord = string.IsNullOrEmpty(playerFaction) ? null : Realm.Ruler(playerFaction);
        mapSub.text = lord == null ? "" : lord.Name + "　·　" + Realm.CityCount(data, playerFaction) + " 城";
    }

    void UpdateDate()
    {
        string t = Hud.Date(year, month);
        if (mapYear != null) mapYear.text = t;
        MapSub();
        cityView.YearText = t;
    }

    /// 两屏都是在构造里按尺寸摆好的，所以窗口一变就整个重建。
    /// 重建很便宜（贴图都有缓存），而且这样不用在每个界面里各写一份 Relayout。
    void Build()
    {
        foreach (Transform t in root) if (t.name != "events" && t.name != "council") Destroy(t.gameObject);   // 事件弹窗跨重建保留，排着的队不丢

        mapView  = new MapView(root, data, RefW, RefH);
        cityView = new CityView(root, RefW, RefH);
        cityView.Orders = orders;
        cityView.Root.gameObject.SetActive(false);

        // 大地图顶栏：年份牌居中（原版标题栏写「189年1月」），右上角设置
        // 大地图顶栏（2026-09-26 重做）：一条上深下透的渐隐底，左边大字年月、下一行「君主 · 几城」，右边圆形设置徽章
        var fade = Node("topfade", mapView.Root).gameObject.AddComponent<Image>();
        fade.sprite = Hud.FadeDown(); fade.raycastTarget = false; Place(fade.rectTransform, 0, 0, RefW, 92);
        mapYear = Label(mapView.Root, "", 22, new Color32(0xF4,0xD4,0x8A,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(mapYear.rectTransform, 16, 10, 200, 30);
        var ysh = mapYear.gameObject.AddComponent<Shadow>(); ysh.effectColor = new Color(0,0,0,.9f); ysh.effectDistance = new Vector2(1.5f,-1.5f);
        mapSub = Label(mapView.Root, "", 12, new Color32(0xE0,0xCC,0xA0,255), TextAnchor.MiddleLeft);
        Place(mapSub.rectTransform, 17, 40, 260, 18);
        mapSub.gameObject.AddComponent<Shadow>().effectColor = new Color(0,0,0,.9f);
        mapYearRT = mapYear.rectTransform;
        var sysImg = Node("Sys", mapView.Root).gameObject.AddComponent<Image>();
        sysImg.sprite = Hud.Icon("hud_setbtn.png") ?? Hud.Icon("icon_settings.png"); sysImg.preserveAspect = true; sysImg.raycastTarget = true;
        Place(sysImg.rectTransform, RefW - 12 - 46, 8, 46, 46);
        mapSysRT = sysImg.rectTransform;
        var sb = sysImg.gameObject.AddComponent<Button>(); sb.transition = Selectable.Transition.None;
        sb.onClick.AddListener(() => { sys.InGame = true; sys.Open(); });
        mapView.Blocked = p => RectTransformUtility.RectangleContainsScreenPoint(mapSysRT, p, null)
                            || RectTransformUtility.RectangleContainsScreenPoint(mapYearRT, p, null);
        UpdateDate();

        mapView.CanEnter = c => playerFaction == null || c.owner == playerFaction;   // 原版：只进自己的城
        mapView.OnEnter = c => {
            curCity = c;
            if (playerFaction == null) NewTurn(c.owner);     // 还没有选势力那一屏，先认头一座进的城
            mapView.Root.gameObject.SetActive(false);
            cityView.Show(data.stats.Find(s => s.id == c.id), c.key);
        };
        politics = new PoliticsView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        politics.OnDone = _ => { pendingCmd = -1; OrderSpent(); };
        politics.OnCancel = () => { pendingCmd = -1; cityView.Refresh(); };
        personnel = new PersonnelView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        personnel.OnDone = _ => { pendingRs = -1; OrderSpent(); };
        personnel.OnCancel = () => { pendingRs = -1; cityView.Refresh(); };   // 任命不扣令，但太守换了要刷新
        military = new MilitaryView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        military.OnDone = _ => { pendingJs = -1; OrderSpent(); };
        military.OnCancel = () => { pendingJs = -1; cityView.Refresh(); };
        military.OnMovedTo = c => {                    // 全城都走了：城空了，玩家跟着去目标城（原版 au = av）
            curCity = c;
            cityView.Show(data.stats.Find(x => x.id == c.id), c.key);
            mapView.RefreshFlags();
        };
        military.OnWar = s => { pendingJs = -1; StartBattle(s); };
        market = new MarketView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        market.OnDone = _ => { pendingSc = null; OrderSpent(); };
        market.OnCancel = () => { pendingSc = null; cityView.Refresh(); };
        diplomacy = new DiplomacyView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        diplomacy.OnDone = _ => { pendingDp = null; OrderSpent(); };
        diplomacy.OnCancel = () => { pendingDp = null; cityView.Refresh(); };
        military.OnOccupied = s => {                   // 空城：兵不血刃，玩家跟着进城（bk() 里 au = av）
            curCity = s.To;
            cityView.Show(data.stats.Find(x => x.id == s.To.id), s.To.key);
            mapView.RefreshFlags();
        };


        cityView.OnCommand = cmd => {
            int k = System.Array.IndexOf(Politics.CmdName, cmd);
            int p = System.Array.IndexOf(Personnel.CmdName, cmd);
            int j = System.Array.IndexOf(Military.CmdName, cmd);
            int m = System.Array.IndexOf(MarketView.Commands, cmd);
            int w = System.Array.IndexOf(DiplomacyView.Commands, cmd);
            // 原版只能在自家的城里下令（城池轮换只走 d[势力][]），别人的城只能看
            if ((k >= 0 || p >= 0 || j >= 0 || m >= 0 || w >= 0) && curCity.owner != playerFaction) { Toast("此城不归我方管辖"); return; }
            if (w >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
                pendingDp = cmd;
                diplomacy.Begin(cmd, data, curCity);
                return;
            }
            if (m >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
                pendingSc = cmd;
                market.Begin(cmd, data.stats.Find(x => x.id == curCity.id));
                return;
            }
            if (j >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
                pendingJs = j;
                military.Faction = playerFaction; military.Lord = Lord;
                military.Begin((Military.Cmd)j, data, curCity);
                return;
            }
            if (k >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
                pendingCmd = k;
                politics.Begin((Politics.Cmd)k, data.stats.Find(x => x.id == curCity.id), officers);
                return;
            }
            if (p >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
                pendingRs = p;
                personnel.Begin((Personnel.Cmd)p, data.stats.Find(x => x.id == curCity.id));
                return;
            }
            Toast(cmd + " 还没做");
        };
        cityView.OnBack = () => {
            cityView.Root.gameObject.SetActive(false);
            mapView.Root.gameObject.SetActive(true);
        };

        if (atTitle)
        {
            title = new TitleView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
            title.OnNew = NewGame;
            title.OnLoadMenu = () => { sys.InGame = false; sys.Open(); };
            title.OnCustom = () => custom.Open(officers);
            title.OnSettings = () => settings.Open();
            title.OnAlbum = () => album.Open();
        }
        rulerPick = new RulerPickView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        rulerPick.OnPick = BeginAs;
        rulerPick.OnFound = FoundCustom;
        rulerPick.OnPickCapital = (lord, team, empty) => {
            // 大地图上挑空城：主界面先藏起来，地图接点击（pickingCapital），挑好了建都，作罢就回君主选择
            pickingCapital = true;
            if (title != null) title.Root.gameObject.SetActive(false);
            cityView.Root.gameObject.SetActive(false); mapView.Root.gameObject.SetActive(true);
            mapView.BeginPick(empty, "选城建都　选择一个空城建都", "请选择空城!", "再点一次 在此建都 · 点空白处取消",
                c => { pickingCapital = false; FoundCustom(lord, team, c); },
                () => { pickingCapital = false; if (title != null) title.Root.gameObject.SetActive(true); rulerPick.Open(data); });
        };
        rulerPick.Officers = officers;
        custom = new CustomView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        settings = new SettingsView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        album = new AlbumView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        settings.OnFontChanged = () => { Build(); settings.Open(); };        // 换字体：整个界面按新字体重建，设置页再开回来
        sys = new SystemView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        sys.OnSave = SaveTo;
        sys.OnLoad = LoadFrom;
        sys.OnTitle = BackToTitle;
        sys.OnEndTurn = () => { if (playerTurnActive) { Toast("本月余令作废，轮到下一家"); EndPlayerTurn(); } };
        // 所有「选城」都去大地图上点（原版屏 4）：能选的城金环高亮，点别的城提示；选完 / 作罢回城内，指令接着走
        SheetView.MapPick = (cities, title, pick, cancel) => {
            var back = curCity;
            cityView.Root.gameObject.SetActive(false); mapView.Root.gameObject.SetActive(true);
            System.Action home = () => {
                mapView.Root.gameObject.SetActive(false);
                if (back != null && back.owner == playerFaction) cityView.Show(data.stats.Find(x => x.id == back.id), back.key);
                else mapView.Root.gameObject.SetActive(true);
            };
            mapView.BeginPick(cities, title, "请选择高亮的城池", "再点一次 确定 · 点空白处取消",
                c => { home(); pick(c); }, () => { home(); cancel(); }, back);
        };
        if (events == null) events = new EventView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        events.Root.SetAsLastSibling();

        toast = Label(root, "", 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(toast.rectTransform, 0, RefH - 150, RefW, 30);
        toast.gameObject.SetActive(false);

        if (curCity != null)                      // 重建前在城里，那就还回城里
        {
            mapView.Root.gameObject.SetActive(false);
            cityView.Show(data.stats.Find(x => x.id == curCity.id), curCity.key);
            if (pendingCmd >= 0)                  // 浮层也接着开，别让拖一下窗口就白点一次
                politics.Begin((Politics.Cmd)pendingCmd, data.stats.Find(x => x.id == curCity.id), officers);
            if (pendingRs >= 0)
                personnel.Begin((Personnel.Cmd)pendingRs, data.stats.Find(x => x.id == curCity.id));
            if (pendingSc != null) market.Begin(pendingSc, data.stats.Find(x => x.id == curCity.id));
            if (pendingJs >= 0)
            { military.Faction = playerFaction; military.Lord = Lord; military.Begin((Military.Cmd)pendingJs, data, curCity); }
        }
        afterBuild?.Invoke();
    }

    /// 原版 aL()：一道令走完，aG 减一；减到 0 玩家这一格就轮完了，回合引擎接着走下一家（GameTurns.cs）
    /// 各家君主还在不在自家效力，不在了就立新君、弹「新君继位」
    void CheckLords()
    {
        if (data == null || atTitle) return;
        foreach (var m in War.CheckRulers(data, officers)) { if (Sim) Debug.Log("[继位] " + m); else events.Push("succeed", "新君继位", m); }
        mapView?.RefreshFlags();
    }

    void OrderSpent()
    {
        CheckLords();                                                    // 招揽 / 离间之后
        orders--;
        cityView.Orders = orders;
        cityView.Refresh();
        if (orders <= 0) EndPlayerTurn();
    }

    /// 一回合能下几道令 = 本势力城数，夹在 3..8（原版 b.e(byte) 1726、ao() 6156）。
    /// 这个数就是城内顶栏那个「令牌」——原版数据里每座城的令牌值正好等于它，
    /// 所以剩余指令直接显示在那一格，不另开一处。
    /// 月末结算（粮饷、民心、灾荒）和战略层 AI 还没接，翻篇时中间那一段是空的。
    string playerFaction;

    void NewTurn(string faction)
    {
        playerFaction = faction; Realm.Player = faction;
        orders = Politics.OrdersPerTurn(data.cities.FindAll(c => c.owner == faction).Count);
        cityView.Orders = orders;
    }

    /// 自检：把四条内政各跑一遍，打出前后值，好对着原版公式核。
    void PoliticsSelfCheck()
    {
        var rng = new System.Random(20260923);
        foreach (var s0 in new[] { data.stats.Find(x => x.name == "洛阳"),
                                   data.stats.Find(x => x.name == "西平") })
        {
            var men = Politics.Roster(officers, s0.id);
            Debug.Log(string.Format("[内政] {0} 武将 {1} 人：{2}", s0.name, men.Count,
                string.Join("、", men.ConvertAll(o => o.Name + "(智" + o.Int + ")"))));
            if (men.Count == 0) continue;
            for (int k = 0; k < 4; k++)
            {
                var c = (Politics.Cmd)k;
                if (Politics.Blocked(c, s0, out string why))
                { Debug.Log("[内政] " + s0.name + " " + Politics.CmdName[k] + " 被挡：" + why); continue; }
                var o = men[0];
                int g = Politics.Cost(c, o, s0.黄金, rng);
                string before = string.Format("统治{0} 金{1} 农{2} 工{3} 人{4} 防{5}",
                    s0.治安, s0.黄金, s0.农业, s0.商业, s0.人口, s0.治水);
                var r = Politics.Run(c, s0, o, g, rng);
                Debug.Log(string.Format("[内政] {0} {1} 由 {2}(智{3}) 花 {4} 金 → {5}  | 前 {6} | 后 统治{7} 金{8} 农{9} 工{10} 人{11} 防{12}",
                    s0.name, Politics.CmdName[k], o.Name, o.Int, g, r.Msg, before,
                    s0.治安, s0.黄金, s0.农业, s0.商业, s0.人口, s0.治水));
            }
        }
        Application.Quit(0);
    }

    /// 自检：人事四条各跑一批，打出分布，好对着原版公式核。
    void PersonnelSelfCheck()
    {
        var rng = new System.Random(20260925);
        var s0 = data.stats.Find(x => x.name == "洛阳");
        var ct = Personnel.Of(s0.id);
        Debug.Log(string.Format("[人事] {0} 仕官 {1}：{2}｜在野未发现 {3}：{4}", s0.name, ct.A.Count,
            string.Join("、", ct.A.ConvertAll(o => o.Name)), ct.B.Count, string.Join("、", ct.B.ConvertAll(o => o.Name))));
        int total = 0, found = 0; var byKind = new int[5];
        foreach (var st in data.stats) { total += Personnel.Of(st.id).B.Count; }
        Debug.Log("[人事] 全图在野未发现 " + total + " 人");
        // 搜索：智力决定成不成，挑城里智力最高的去搜 200 次
        var best = ct.A.Count > 0 ? ct.A[0] : null;
        foreach (var o in ct.A) if (o.Int > best.Int) best = o;
        int g0 = s0.黄金, f0 = s0.粮草;
        for (int i = 0; i < 200; i++) { var r = Personnel.Search(s0, best, rng); byKind[(int)r.Kind]++; if (r.Kind == Personnel.Find.Talent) found++; }
        Debug.Log(string.Format("[人事] 搜索×200 由 {0}(智{1} 德{2})：无{3} 粮{4} 金{5} 贤{6} 宝{7}｜金 {8}→{9} 粮 {10}→{11} 宝物 {12}",
            best.Name, best.Int, best.Virtue, byKind[0], byKind[1], byKind[2], byKind[3], byKind[4], g0, s0.黄金, f0, s0.粮草, s0.宝物));
        // 登用：把搜出来的挨个请
        foreach (var t in new List<Officer>(ct.C))
        {
            if (ct.A.Count >= Politics.MaxInCity) { Debug.Log("[人事] 登用：本城置吏已足!"); break; }
            var by = ct.A[0]; foreach (var o in ct.A) if (o.Virtue > by.Virtue) by = o;
            int tries = 0; while (!Personnel.Recruit(s0, by, t, rng) && ++tries < 50) { }
            Debug.Log(string.Format("[人事] 登用 {0}({1}) 由 {2}(德{3}) 试了 {4} 次 → 仕官 {5} 人 兵力 {6}",
                t.Name, Mathf.Max(t.Int, Mathf.Max(t.Might, t.Virtue)), by.Name, by.Virtue, tries + 1, ct.A.Count, s0.兵力));
        }
        // 奖赏：赏 100 金 与 赏宝物 各看一眼涨幅分布
        int[] g100 = new int[20], gem = new int[20];
        for (int i = 0; i < 1000; i++) { g100[Personnel.LoyalGain(100, rng)]++; gem[Personnel.LoyalGain(Personnel.Treasure, rng)]++; }
        Debug.Log("[人事] 赏100金涨忠诚分布 " + string.Join(" ", System.Array.ConvertAll(new[]{11,12,13,14}, v => v + ":" + g100[v])));
        Debug.Log("[人事] 赏宝物涨忠诚分布 " + string.Join(" ", System.Array.ConvertAll(new[]{11,12,13,14,15,16,17,18}, v => v + ":" + gem[v])));
        Debug.Log("[人事] 任命 洛阳：" + (Personnel.Blocked(Personnel.Cmd.Appoint, s0, out string why) ? why : "可以"));
        Application.Quit(0);
    }

    /// 自检：改几样东西 → 存第三档 → 回开局 → 读回来，逐项比对
    void SaveSelfCheck()
    {
        var c0 = data.cities.Find(x => x.name == "洛阳");
        mapView.OnEnter(c0);
        var s0 = data.stats.Find(x => x.id == c0.id);
        var o = Personnel.Of(s0.id).A[1];
        s0.黄金 = 123; s0.宝物 = 7; o.Loyal = 42; month = 5; year = 190; orders = 2;
        var moved = Personnel.Of(s0.id).A[9]; Personnel.Of(s0.id).A.RemoveAt(9); Personnel.Of(s0.id).C.Add(moved);
        SaveTo(2);
        ResetState(); Build();
        Debug.Log("[存档] 回到开局：洛阳金 " + data.stats.Find(x => x.id == c0.id).黄金 + "，档名 " + SaveGame.Label(2));
        LoadFrom(2);
        var s1 = data.stats.Find(x => x.id == c0.id);
        var ct = Personnel.Of(s1.id);
        bool ok = s1.黄金 == 123 && s1.宝物 == 7 && officers[o.Id].Loyal == 42 && year == 190 && month == 5
                  && orders == 2 && ct.A.Count == 9 && ct.C.Count == 1 && ct.C[0].Id == moved.Id && curCity != null;
        Debug.Log(string.Format("[存档] 读回：金 {0} 宝 {1} {2}忠 {3} {4}年{5}月 令 {6} 仕官 {7} 已发现 {8} 所在城 {9} → {10}",
            s1.黄金, s1.宝物, o.Name, officers[o.Id].Loyal, year, month, orders, ct.A.Count, ct.C.Count,
            curCity?.name, ok ? "全部一致" : "有出入!"));
        Application.Quit(0);
    }

    /// 自检：同一路人马各打一场胜仗、一场败仗，打出善后前后的名册与城池
    void WarSelfCheck(MapData.City c0)
    {
        string Dump(MapData.City c) { var st = data.stats.Find(x => x.id == c.id); var a = Personnel.Of(c.id).A;
            return c.name + "[" + c.owner + " 主" + st.ruler + " 守" + st.governor + " 金" + st.黄金 + " 粮" + st.粮草 + " 兵" + st.兵力 + " 将"
                   + string.Join("、", a.ConvertAll(o => o.Name)) + "]"; }
        foreach (bool win in new[] { true, false })
        {
            ResetState(); curCity = null; Build(); mapView.OnEnter(data.cities.Find(x => x.name == "陈留"));
            var from = curCity;
            var tgt = War.Targets(data, from).Find(x => x.owner != "none");
            var army = new List<Officer>(Personnel.Of(from.id).A.GetRange(1, 3));
            Debug.Log("[战争] ===== " + (win ? "胜" : "败") + " ===== 出征 " + string.Join("、", army.ConvertAll(o => o.Name)));
            Debug.Log("[战争] 前 " + Dump(from) + " → " + Dump(tgt));
            var st = new War.Sortie { From = from, To = tgt, Army = army, Gold = 100, Food = 300 };
            War.Depart(data, st); War.Launch(data, st, playerFaction, Lord);
            StartBattle(st);
            field.ForceOutcomeForTest(win);
            var r = War.Settle(data, officers, st, field, playerFaction, Lord, new System.Random(1));
            Destroy(field.Root.gameObject); field = null; inBattle = false; BattleField.World = null;
        Music.Play(bgmMap);                                                          // 打完回城池层：换回古地图卷
            Debug.Log("[战争] 战报 " + string.Join(" / ", r.Lines));
            Debug.Log("[战争] 后 " + Dump(from) + " → " + Dump(tgt));
            foreach (int id in Military.Adjacent(tgt.id)) { var c = data.cities.Find(x => x.id == id); if (c.owner == playerFaction || c.owner == tgt.owner) Debug.Log("[战争]    邻 " + Dump(c)); }
        }
        Application.Quit(0);
    }

    void Toast(string s)
    {
        toast.text = s; toast.gameObject.SetActive(true); toastUntil = Time.time + 2f;
    }

    System.Collections.IEnumerator DefenseSelfCheck()
    {
        var av = System.Environment.GetCommandLineArgs();
        int ai = System.Array.IndexOf(av, "-as");
        string me = ai >= 0 ? av[ai + 1] : "caocao";
        Realm.Player = me; playerFaction = me; atTitle = false; EventView.Auto = true;
        foreach (var c in data.cities)
        {
            if (c.owner != me) continue;
            foreach (int n in Military.Adjacent(c.id))
            {
                var nc = data.cities.Find(x => x.id == n);
                if (nc == null || nc.owner == me || nc.owner == "none" || Personnel.Of(n).A.Count < 3) continue;
                var army = Personnel.Of(n).A.GetRange(0, 3);
                var plan = new AI.WarPlan { From = n, To = c.id, Army = army, Gold = 200, Food = 800 };
                Debug.Log("[守城自检] " + nc.name + " 攻 " + c.name + "：" + string.Join("、", army.ConvertAll(o => o.Name)));
                StartCoroutine(DefenseRoutine(nc.owner, plan));
                while (field == null) yield return null;
                yield return null;
                field.DefenseDaysForTest(8);
                Application.Quit();
                yield break;
            }
        }
        Debug.Log("[守城自检] 找不到相邻的敌城"); Application.Quit();
    }

    System.Collections.IEnumerator ShotAndQuit(string path)
    {
        var av = System.Environment.GetCommandLineArgs();                // -wait <秒>：截图前等多久，默认 3
        int wi = System.Array.IndexOf(av, "-wait");
        yield return new WaitForSeconds(wi >= 0 ? float.Parse(av[wi + 1]) : 3f);
        yield return new WaitForEndOfFrame();
        ScreenCapture.CaptureScreenshot(path);
        yield return new WaitForSeconds(1f);
        Application.Quit();
    }

    // ── 战争：开战 / 打完回城 ──────────────────────────────────────────
    void StartBattle(War.Sortie s)
    {
        sortieNow = s;
        var ts = data.stats.Find(x => x.id == s.To.id);
        var roster = new List<Unit>();
        foreach (var o in s.Army) roster.Add(new Unit { O = o, Ours = true });              // [0] 主将
        foreach (var o in Personnel.Of(s.To.id).A) roster.Add(new Unit { O = o, Ours = false });   // [0] 太守，站城池格
        OpenBattle(s.To.id, roster, true, new[] { s.Gold, s.Food, ts.黄金, ts.粮草 }, EndBattle);   // 原版 z() + w()
    }

    /// 开一场仗：攻城（我方出征队 vs 城里全体，w() 摆位）和守城（城里全体 vs AI 出征队，x() 摆位）共用
    void OpenBattle(int cityId, List<Unit> roster, bool weAttack, int[] supply, System.Action onQuit)
    {
        inBattle = true;
        Music.Play(bgmWarLoop, bgmWarIntro);                                         // 开战：铁骑冲锋
        var world = new War.World(data, officers);
        BattleField.World = world;
        var grid = BattleRules.LoadMap(Path.Combine(BattleGame.BattleDir, "maps", cityId + ".map"));
        BattleField.Deploy(grid, roster, weAttack, new System.Random());
        var ts = data.stats.Find(x => x.id == cityId);
        mapView.Root.gameObject.SetActive(false); cityView.Root.gameObject.SetActive(false);
        string theme = BattleGame.ThemeOf(cityId);
        field = new BattleField(root, cityId, theme, roster, RefW, RefH, supply, War.World.LordId(Realm.Player));
        field.OnQuit = onQuit;
        // 战场村庄：地形 1 武器村 / 3 金粮村，用这一仗的战场金粮（原版 aj() / ak()）
        field.VillageTier = ts.村档;
        village = new VillageView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        village.OnClosed = msg => field.VillageDone(msg);
        village.OnRareSold = () => ts.村档 = 1;
        field.OnVillage = (u, code) => { if (code == 1) village.OpenShop(u, ts.村档, field.Supply); else village.OpenExchange(u, field.Supply); };
        field.OnClash = (me, foe, atk) =>
        {
            Sfx.Play("war_drum");                                                      // 进阵型战：三通鼓
            field.Root.gameObject.SetActive(false);
            int terrain = TerrainUnder(grid, atk ? foe : me);                           // 场景看守方脚下那格
            clash = new ClashView(root, me.O, foe.O, terrain, atk, RefW, RefH, theme, cityId);
            clash.OnDuel = () =>
            {
                clash.Root.gameObject.SetActive(false);
                duel = new DuelView(root, me.O, foe.O, clash.Scene, RefW, RefH, theme, cityId);
                duel.OnDone = () =>
                {
                    int oc = duel.Outcome, fs = duel.FleeSide;
                    Destroy(duel.Root.gameObject); duel = null;
                    clash.Root.gameObject.SetActive(true);
                    clash.AfterDuel(oc, fs);
                };
                duel.OnSurrender = () =>
                {
                    Destroy(duel.Root.gameObject); duel = null;
                    clash.Root.gameObject.SetActive(true);
                    clash.Surrender();
                };
            };
            clash.OnDone = (mine, theirs) =>
            {
                int cc = clash.Cc, killed = clash.Killed, lost = clash.Lost; string ccText = cc != 0 ? clash.CcText() : null;
                Destroy(clash.Root.gameObject); clash = null;
                field.Root.gameObject.SetActive(true);
                field.AfterClash(me, foe, cc, killed, lost, ccText);
            };
        };
    }

    static int TerrainUnder(int[,] g, Unit u)
    {
        if (u.Y < 0 || u.Y >= g.GetLength(0) || u.X < 0 || u.X >= g.GetLength(1)) return 0;
        return g[u.Y, u.X] & 0xF;
    }

    /// 结局框点「返回」：善后、回城、扣令（原版 at() → ao() → aL()）
    void EndBattle()
    {
        string lord = Lord;
        var r = War.Settle(data, officers, sortieNow, field, playerFaction, lord, new System.Random());
        foreach (var m in War.CheckRulers(data, officers)) r.Lines.Add(m);             // 敌方君主被擒 → 新君继位
        Destroy(field.Root.gameObject); field = null; inBattle = false; BattleField.World = null;
        Music.Play(bgmMap);                                                          // 打完回城池层：换回古地图卷
        if (village != null) { Destroy(village.Root.gameObject); village = null; }
        // 回出发城；出发城已经不是我方的了（弃城出征），就回君主坐镇的城（原版 u() 1736）
        if (curCity == null || curCity.owner != playerFaction)
            curCity = data.cities.Find(c => c.owner == playerFaction && data.stats.Find(x => x.id == c.id).governor == Lord)
                   ?? data.cities.Find(c => c.owner == playerFaction);
        if (curCity == null) { GameOver(lord + " 势力灭亡了!"); return; }                   // bG=3
        Build();
        var lines = new List<string>(r.Lines);
        if (r.EnemySuccession != null) lines.Add(r.EnemySuccession);
        if (r.FallenFaction != null) lines.Add(r.FallenFaction + " 势力灭亡了!");
        military.Report("战争", lines, () => {
            if (r.LordDied)                                                              // bG=2 → as()：玩家自己挑继位人
                military.PickHeir(lord, War.Heirs(data, playerFaction), h => {
                    War.SetLord(data, playerFaction, h);
                    Toast(lord + "死亡,新君主 " + h.Name + " 继位!");
                    OrderSpent();
                });
            else OrderSpent();
        });
    }

    void GameOver(string msg) => Ending(false);        // 城数归零：走原版屏 23 败北结局

    /// 天下霸图：这一局里每员武将在玩家麾下连续待了几个月末（武将号 → 月数）。不在麾下就清掉
    readonly Dictionary<int, int> serveMonths = new();
    const int ALBUM_MONTHS = 12;
    bool albumDry;                                     // 自检：只记数、打日志，不真解锁
    /// 每个月末（MonthEnd 跑完、没分出结局才走到这里）：还在玩家各城仕官名单里的 +1，不在的清零；满 12 解锁
    void AlbumMonthTick()
    {
        if (Sim || string.IsNullOrEmpty(playerFaction)) return;
        var now = new HashSet<int>();
        foreach (var c in data.cities)
            if (c.owner == playerFaction) foreach (var o in Personnel.Of(c.id).A) now.Add(o.Id);
        foreach (var id in new List<int>(serveMonths.Keys)) if (!now.Contains(id)) serveMonths.Remove(id);
        var fresh = new List<Album.Card>();
        foreach (int id in now)
        {
            int n = serveMonths.TryGetValue(id, out int m) ? m + 1 : 1;
            serveMonths[id] = n;
            if (n < ALBUM_MONTHS || !officers.TryGetValue(id, out var o)) continue;
            if (albumDry) { if (n == ALBUM_MONTHS) Debug.Log("[霸图自检] 满一年可解锁 " + o.Name); continue; }
            if (Album.Unlock(o) is Album.Card card) fresh.Add(card);
        }
        if (fresh.Count > 0) Sfx.Play("chime");
        if (fresh.Count == 1) Toast("天下霸图　解锁武将卡「" + fresh[0].name + "」" + fresh[0].tier);
        else if (fresh.Count > 1) Toast("天下霸图　解锁 " + fresh.Count + " 张武将卡");
    }

    float mapSubAt;
    void Update()
    {
        Music.Tick();
        if (Time.unscaledTime > mapSubAt) { mapSubAt = Time.unscaledTime + .5f; MapSub(); }   // 打下 / 丢了城随时变
        if (inBattle)                                   // 打仗的时候城池层整个停下，只跑战场
        {
            if (ScreenFit.Changed() && duel == null) { if (clash != null) clash.Relayout(RefW, RefH); else field?.Relayout(RefW, RefH); }
            duel?.Tick(); if (duel == null) clash?.Tick(); if (clash == null && duel == null) { field?.Tick(); field?.PlotPopTick(); }
            village?.Tick();
            TickToast();
            return;
        }
        // 桌面拖窗口、手机转屏：重新算一遍逻辑尺寸再把两屏搭回去。
        // 拖动过程中尺寸每帧都在变（开窗那一下系统自己就要改十几次），
        // 所以等它停稳 0.25 秒再重建，不然一路重建到底，浮层也会被冲掉。
        if (ScreenFit.Changed()) resizeAt = Time.unscaledTime;
        if (resizeAt > 0 && Time.unscaledTime - resizeAt > 0.25f) { resizeAt = -1f; Build(); return; }
        events?.Tick(); events?.TickCouncil();
        // 点击穿透：弹窗的「知道了」松手那一下弹窗就关了，同一帧城内 / 地图又读到这次松手，会把下面的按钮也点了。
        // 所以按下那一刻只要有弹窗或浮层开着，这一整次按下→松手都归弹窗，底下一律不接。
        bool overlay = (atTitle && !pickingCapital) || sys.Root.gameObject.activeSelf || rulerPick.Root.gameObject.activeSelf
            || custom.Root.gameObject.activeSelf || aiNow != null || (events != null && events.Busy)
            || politics.Root.gameObject.activeSelf || personnel.Root.gameObject.activeSelf || military.Root.gameObject.activeSelf
            || market.Root.gameObject.activeSelf || diplomacy.Root.gameObject.activeSelf;
        if (Input.GetMouseButtonDown(0)) swallowTap = overlay;
        bool swallowing = swallowTap && (Input.GetMouseButton(0) || Input.GetMouseButtonUp(0));
        if (Input.GetMouseButtonUp(0)) swallowTap = false;
        if (swallowing)
        {
            politics.Tick(); personnel.Tick(); military.Tick(); market.Tick(); diplomacy.Tick();
            TickToast(); return;
        }
        // 主界面 / 设置 / 君主选择 / 事件弹窗 / 敌方回合：底下的地图和城内都不接点击
        if ((atTitle && !pickingCapital) || sys.Root.gameObject.activeSelf || rulerPick.Root.gameObject.activeSelf
            || custom.Root.gameObject.activeSelf
            || aiNow != null || (events != null && events.Busy)) { TickToast(); return; }
        mapView.Update(RefW, RefH);
        // 内政浮层开着的时候别让点击透到城内视图去，不然选建筑会跟着变
        if (!politics.Root.gameObject.activeSelf && !personnel.Root.gameObject.activeSelf
            && !military.Root.gameObject.activeSelf && !market.Root.gameObject.activeSelf
            && !diplomacy.Root.gameObject.activeSelf) cityView.Update();
        politics.Tick();                          // 结果屏那段八帧动画
        personnel.Tick();
        military.Tick();
        market.Tick();
        diplomacy.Tick();
        TickToast();
    }

    bool swallowTap;                    // 这一次按下是冲着弹窗去的，松手前底下都别接

    void TickToast()
    {
        if (toast.gameObject.activeSelf && Time.time > toastUntil) toast.gameObject.SetActive(false);
    }
}
