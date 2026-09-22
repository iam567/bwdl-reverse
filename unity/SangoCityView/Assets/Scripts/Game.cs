// 入口：建画布，管地图与城内两屏的切换。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class Game : MonoBehaviour
{
    public Font font;
    // 排版一律按 ScreenFit 算出来的逻辑尺寸，不写死 375×812。
    static float RefW => ScreenFit.RefW;
    static float RefH => ScreenFit.RefH;

    RectTransform root;
    MapView mapView; CityView cityView; MapData data;
    MapData.City curCity;
    Text toast; float toastUntil;

    PoliticsView politics;
    Dictionary<int, Officer> officers;
    int orders;                       // 本回合还剩几道令（原版 aG）
    int pendingCmd = -1;              // 重建时开着的内政指令，重建完接着开
    System.Action afterBuild;         // 自检钩子：每次重建完重放一遍
    float resizeAt = -1f;             // 窗口尺寸变动的时刻，用来防抖

    void Start()
    {
        UIKit.UIFont = font;
        data = JsonUtility.FromJson<MapData>(File.ReadAllText(Path.Combine(ArtDir, "map.json")));

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>(); canvas.renderMode = RenderMode.ScreenSpaceOverlay;
        ScreenFit.Attach(go.GetComponent<CanvasScaler>());
        root = (RectTransform)go.transform;

        officers = BattleRules.LoadOfficers(Path.Combine(ArtDir, "battle", "officers.csv"));
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
    }

    /// 两屏都是在构造里按尺寸摆好的，所以窗口一变就整个重建。
    /// 重建很便宜（贴图都有缓存），而且这样不用在每个界面里各写一份 Relayout。
    void Build()
    {
        foreach (Transform t in root) Destroy(t.gameObject);

        mapView  = new MapView(root, data, RefW, RefH);
        cityView = new CityView(root, RefW, RefH);
        cityView.Orders = orders;
        cityView.Root.gameObject.SetActive(false);

        mapView.OnEnter = c => {
            curCity = c;
            if (playerFaction == null) NewTurn(c.owner);     // 还没有选势力那一屏，先认头一座进的城
            mapView.Root.gameObject.SetActive(false);
            cityView.Show(data.stats.Find(s => s.id == c.id), c.key);
        };
        politics = new PoliticsView(root, RefW, RefH, cityView.PanelSprite, cityView.BtnSprite);
        politics.OnDone = _ => {                     // 原版 aL()：一道令走完，aG 减一
            orders--; pendingCmd = -1;
            // 原版 aG 归零就直接翻篇（aq=3 → 下一回合）。月末结算和战略层 AI 还没接，
            // 这里先照着翻，只是中间那一段什么都不算。
            if (orders <= 0) { NewTurn(playerFaction); Toast("本月指令已尽，进入下月"); }
            cityView.Orders = orders;
            cityView.Refresh();
        };
        politics.OnCancel = () => { pendingCmd = -1; };


        cityView.OnCommand = cmd => {
            int k = System.Array.IndexOf(Politics.CmdName, cmd);
            if (k >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
                pendingCmd = k;
                politics.Begin((Politics.Cmd)k, data.stats.Find(x => x.id == curCity.id), officers);
                return;
            }
            if (cmd != "移动" && cmd != "输送") { Toast(cmd + " 还没做"); return; }
            cityView.Root.gameObject.SetActive(false);
            mapView.Root.gameObject.SetActive(true);
            mapView.BeginMove(curCity);
        };
        cityView.OnBack = () => {
            cityView.Root.gameObject.SetActive(false);
            mapView.Root.gameObject.SetActive(true);
        };

        toast = Label(root, "", 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(toast.rectTransform, 0, RefH - 150, RefW, 30);
        toast.gameObject.SetActive(false);

        if (curCity != null)                      // 重建前在城里，那就还回城里
        {
            mapView.Root.gameObject.SetActive(false);
            cityView.Show(data.stats.Find(x => x.id == curCity.id), curCity.key);
            if (pendingCmd >= 0)                  // 浮层也接着开，别让拖一下窗口就白点一次
                politics.Begin((Politics.Cmd)pendingCmd, data.stats.Find(x => x.id == curCity.id), officers);
        }
        afterBuild?.Invoke();
    }

    /// 一回合能下几道令 = 本势力城数，夹在 3..8（原版 b.e(byte) 1726、ao() 6156）。
    /// 这个数就是城内顶栏那个「令牌」——原版数据里每座城的令牌值正好等于它，
    /// 所以剩余指令直接显示在那一格，不另开一处。
    /// 月末结算（粮饷、民心、灾荒）和战略层 AI 还没接，翻篇时中间那一段是空的。
    string playerFaction;

    void NewTurn(string faction)
    {
        playerFaction = faction;
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

    void Toast(string s)
    {
        toast.text = s; toast.gameObject.SetActive(true); toastUntil = Time.time + 2f;
    }

    void Update()
    {
        // 桌面拖窗口、手机转屏：重新算一遍逻辑尺寸再把两屏搭回去。
        // 拖动过程中尺寸每帧都在变（开窗那一下系统自己就要改十几次），
        // 所以等它停稳 0.25 秒再重建，不然一路重建到底，浮层也会被冲掉。
        if (ScreenFit.Changed()) resizeAt = Time.unscaledTime;
        if (resizeAt > 0 && Time.unscaledTime - resizeAt > 0.25f) { resizeAt = -1f; Build(); return; }
        mapView.Update(RefW, RefH);
        // 内政浮层开着的时候别让点击透到城内视图去，不然选建筑会跟着变
        if (!politics.Root.gameObject.activeSelf) cityView.Update();
        politics.Tick();                          // 结果屏那段八帧动画
        if (toast.gameObject.activeSelf && Time.time > toastUntil) toast.gameObject.SetActive(false);
    }
}
