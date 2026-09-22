// 入口：建画布，管地图与城内两屏的切换。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class Game : MonoBehaviour
{
    public Font font;
    const int REF_W = 375, REF_H = 812;

    MapView mapView; CityView cityView; MapData data;
    MapData.City curCity;
    Text toast; float toastUntil;

    PoliticsView politics;
    Dictionary<int, Officer> officers;
    Text orderText;
    int orders;                       // 本回合还剩几道令（原版 aG）

    void Start()
    {
        UIKit.UIFont = font;
        data = JsonUtility.FromJson<MapData>(File.ReadAllText(Path.Combine(ArtDir, "map.json")));

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>(); canvas.renderMode = RenderMode.ScreenSpaceOverlay;
        var sc = go.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(REF_W, REF_H); sc.matchWidthOrHeight = 0;
        var root = (RectTransform)go.transform;

        officers = BattleRules.LoadOfficers(Path.Combine(ArtDir, "battle", "officers.csv"));

        mapView  = new MapView(root, data, REF_W, REF_H);
        cityView = new CityView(root, REF_W, REF_H);
        cityView.Root.gameObject.SetActive(false);

        mapView.OnEnter = c => {
            curCity = c;
            if (playerFaction == null) NewTurn(c.owner);     // 还没有选势力那一屏，先认头一座进的城
            mapView.Root.gameObject.SetActive(false);
            cityView.Show(data.stats.Find(s => s.id == c.id), c.key);
        };
        politics = new PoliticsView(root, REF_W, REF_H, cityView.PanelSprite, cityView.BtnSprite);
        politics.OnDone = _ => {                     // 原版 aL()：一道令走完，aG 减一
            orders--; RefreshOrders();
            cityView.Refresh();
            if (orders <= 0) Toast("本回合指令已用尽");
        };
        politics.OnCancel = () => { };

        orderText = Label(root, "", 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleRight);
        Place(orderText.rectTransform, 0, 6, REF_W - 92, 22);
        var nt = Node("newturn", root).gameObject.AddComponent<Image>();
        Place((RectTransform)nt.transform, REF_W - 84, 4, 72, 24);
        nt.sprite = cityView.BtnSprite; nt.type = Image.Type.Sliced;
        var ntl = Label(root, "新回合", 12, new Color32(0xF8,0xEC,0xD4,255), TextAnchor.MiddleCenter);
        Place(ntl.rectTransform, REF_W - 84, 4, 72, 24);
        var ntb = nt.gameObject.AddComponent<Button>(); ntb.transition = Selectable.Transition.None;
        ntb.onClick.AddListener(() => { if (playerFaction != null) NewTurn(playerFaction); });

        cityView.OnCommand = cmd => {
            int k = System.Array.IndexOf(Politics.CmdName, cmd);
            if (k >= 0)
            {
                if (orders <= 0) { Toast("本回合指令已用尽"); return; }
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
        Place(toast.rectTransform, 0, REF_H - 150, REF_W, 30);
        toast.gameObject.SetActive(false);

        Debug.Log("就绪：地图上点城池选中，再点一次进城；城内点建筑出指令，右上角「地图」返回");
        foreach (var a in System.Environment.GetCommandLineArgs())
            if (a == "-politics") { PoliticsSelfCheck(); break; }
            else if (a == "-polui")                       // 直接开到内政浮层，好截图看排版
            {
                var c0 = data.cities.Find(x => x.name == "洛阳");
                mapView.OnEnter(c0);
                politics.Begin(Politics.Cmd.Reclaim, data.stats.Find(x => x.id == c0.id), officers);
                break;
            }
    }

    /// 一回合能下几道令 = 本势力城数，夹在 3..8（原版 b.e(byte) 1726、ao() 6156）。
    /// 回合循环、月末结算（粮饷、民心、灾荒）和战略层 AI 都还没接，
    /// 所以令用尽之后只能点「新回合」手动续，好把这四条指令走通。
    string playerFaction;

    void NewTurn(string faction)
    {
        playerFaction = faction;
        orders = Politics.OrdersPerTurn(data.cities.FindAll(c => c.owner == faction).Count);
        RefreshOrders();
    }

    void RefreshOrders()
    {
        orderText.text = "剩余指令 " + Mathf.Max(0, orders);
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
        mapView.Update(REF_W, REF_H);
        // 内政浮层开着的时候别让点击透到城内视图去，不然选建筑会跟着变
        if (!politics.Root.gameObject.activeSelf) cityView.Update();
        if (toast.gameObject.activeSelf && Time.time > toastUntil) toast.gameObject.SetActive(false);
    }
}
