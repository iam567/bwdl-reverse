// 驿馆（外交）四条指令，照原版步骤表（公式在 Diplomacy.cs）：
//   同盟 bR[5]={9,3,91,1,0}  挑势力（屏 15）→ 挑使者（屏 7）→ bd()「成功结盟」/「同盟失败」→ 扣令
//   离间 bR[20]={2,3,3,92,1} 挑外家城（屏 4）→ 挑对方武将 → 挑本城执行者 → bc()「离间成功」/「离间失败」→ 扣令
//   招揽 bR[21]={2,3,3,90,1} 同上 → be()「招揽成功」/「招揽失败」→ 扣令；本城满 10 人进不来
//   情报 bR[4]={2,8}         挑任一城 → 屏 5 城池数值 + 武将名册，不扣令
// 四条都不花钱。成败都扣令（情报除外）。说话的是执行的那员武将。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using static UIKit;

public class DiplomacyView : SheetView
{
    public static readonly string[] Commands = { "同盟", "离间", "招揽", "情报" };
    MapData map; MapData.City city; MapData.Stat stat;
    int cmd;

    public DiplomacyView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("diplomacy", parent, w, h, panelSp, btnSp) { }

    string Title => Commands[cmd];
    static readonly string[] CgKey = { "ally", "sow", "recruit", "scout" };
    string Cg => Path.Combine("eventcg", "ev_" + CgKey[cmd]);
    MapData.Stat StatOf(MapData.City c) => map.stats.Find(x => x.id == c.id);

    public void Begin(string label, MapData m, MapData.City at)
    {
        map = m; city = at; stat = StatOf(at); cmd = System.Array.IndexOf(Commands, label);
        switch (cmd)
        {
            case 0: StartAlly(); break;
            case 1: case 2: StartIntrigue(); break;
            case 3: StartScout(); break;
        }
    }

    // ── 同盟 ────────────────────────────────────────────────────────
    void StartAlly()
    {
        var cand = Realm.All.FindAll(f => f.Alive && f.Key != Realm.Player && !Realm.Allied(f.Key));
        // 原版这里少了 return，「已与所有势力同盟」一闪就没了；我们照弹这句话
        if (cand.Count == 0) { Say(Title, "已与所有势力同盟", false, null, Gov(stat)); return; }
        var lords = cand.ConvertAll(f => Realm.Ruler(f.Key));
        PickGrid("同盟　请选择势力进行操作", lords, o => "城 " + Realm.CityCount(map, Realm.RulerOf(o).Key), lord =>
            PickGrid("同盟　遣何人出使", Personnel.Of(city.id).A, o => "德 " + o.Virtue, envoy => {
                var f = Realm.RulerOf(lord);
                bool ok = Diplomacy.Ally(envoy, lord, rng);
                if (ok) Realm.Pacts.Add(new Realm.Pact { With = f.Key, Months = Diplomacy.PactMonths });
                Say(Title, ok ? "成功结盟\n与 " + lord.Name + " 结为同盟，为期一年" : "同盟失败\n" + lord.Name + " 不肯结盟",
                    true, ok ? Cg : null, envoy);
            }, () => StartAlly(), rate: o => {                                     // Ally：德²/100 − 对方君主智力 > rand(100)
                int p = o.Virtue * o.Virtue / 100 - lord.Int;
                return p >= 60 ? 3 : p >= 30 ? 2 : p > 0 ? 1 : 0;
            }), Cancel);
    }

    // ── 离间 / 招揽 ─────────────────────────────────────────────────
    void StartIntrigue()
    {
        if (cmd == 2 && Personnel.Of(city.id).A.Count >= Politics.MaxInCity)
        { Say(Title, "本城置吏已足!", false, null, Gov(stat)); return; }
        var cities = map.cities.FindAll(c => c.owner != Realm.Player && c.owner != "none" && Personnel.Of(c.id).A.Count > 0);
        PickCityScroll(Title + "　请选择目的城池", cities, c => StatOf(c).ruler + "　将 " + Personnel.Of(c.id).A.Count, c =>
            PickGrid(cmd == 1 ? "请选择离间的武将" : "请选择要招揽武将", Personnel.Of(c.id).A, o => "智 " + o.Int + "　忠 " + o.Loyal, tgt =>
                PickGrid(Title + "　选择执行武将", Personnel.Of(city.id).A, o => "智 " + o.Int, me => {
                    if (cmd == 1)
                    {
                        int d = Diplomacy.Sow(me, tgt, rng);
                        Say(Title, d > 0 ? "离间成功\n" + tgt.Name + " 忠诚 −" + d + "（" + tgt.Loyal + "）" : "离间失败",
                            true, d > 0 ? Cg : null, me);
                    }
                    else
                    {
                        bool ok = Diplomacy.Poach(map, city.id, c.id, me, tgt, rng);
                        Say(Title, ok ? "招揽成功\n" + tgt.Name + " 率兵 " + tgt.Troops + " 投入我军" : "招揽失败",
                            true, ok ? Cg : null, me);
                    }
                }, () => StartIntrigue(), rate: o => IntrigueTier(o.Int, tgt)),
                () => StartIntrigue(), rate: t => IntrigueTier(BestInt(), t)), Cancel);
    }

    int BestInt() { int b = 0; foreach (var m in Personnel.Of(city.id).A) b = Mathf.Max(b, m.Int); return b; }
    /// 离间（SowAmount）：智力 − 对方有效智力 > rand(30) 才成，忠诚越高对方越难（>89 +20、>96 +50、100 不可能）；
    /// 招揽（PoachOk）：对方忠诚 > 35 招不动；否则 智力差 + (35 − 忠诚) ≥ rand(20) 就成
    int IntrigueTier(int myInt, Officer t)
    {
        if (cmd == 1)
        {
            if (t.Loyal > 99) return 0;
            int dN = t.Int + (t.Loyal > 96 ? 50 : t.Loyal > 89 ? 20 : 0), m = myInt - dN;
            return m >= 30 ? 3 : m > 15 ? 2 : m > 0 ? 1 : 0;
        }
        if (t.Loyal > 35) return 0;
        int k = myInt - t.Int + (35 - t.Loyal);
        return k >= 19 ? 3 : k >= 10 ? 2 : k >= 0 ? 1 : 0;
    }

    // ── 情报：屏 5 ───────────────────────────────────────────────────
    void StartScout()
    {
        PickCityScroll("情报　请选择侦查的城池", map.cities, c => c.owner == "none" ? "空城" : StatOf(c).ruler, ShowCity, Cancel);
    }

    void ShowCity(MapData.City c)
    {
        var s = StatOf(c);
        var box = Sheet("情报　" + c.name, 56 + 7 * 24 + 16 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        string[] rows = c.owner == "none" ? new[] { "城市基本信息", "空城", "人口 " + s.人口, "开 " + s.农业 + "　商 " + s.商业 + "　治 " + s.治水,
                                                    "金 " + s.黄金 + "　粮 " + s.粮草, "", "" }
            : new[] { "君主 " + s.ruler + "　太守 " + s.governor, "统治 " + s.治安 + "　人口 " + s.人口,
                      "武将 " + Personnel.Of(c.id).A.Count + "　兵 " + s.兵力, "开垦 " + s.农业 + "　商业 " + s.商业,
                      "治水 " + s.治水, "金 " + s.黄金 + "　粮 " + s.粮草, "" };
        for (int i = 0; i < rows.Length; i++)
        {
            var l = Label(box, rows[i], 15, i == 0 ? Rust : Ink, TextAnchor.MiddleCenter, i == 0 ? FontStyle.Bold : FontStyle.Normal);
            Place(l.rectTransform, 20, 44 + i * 24, w - 40, 24);
        }
        float y = 56 + 7 * 24 + 16;
        if (Personnel.Of(c.id).A.Count > 0)
            Btn(box, "武将信息", w / 2 - 124, y, 116, BTN_H, () => Roster(c));
        Btn(box, "知道了", Personnel.Of(c.id).A.Count > 0 ? w / 2 + 8 : (w - 116) / 2, y, 116, BTN_H, () => Say(Title, "侦查完毕", false, Cg, Gov(stat)));
    }

    /// 屏 5 第二页「该城武将信息,上下键翻页」：LV 武 智 体 德 兵 忠 武器 防具 —— 卡片上写两行
    void Roster(MapData.City c)
    {
        PickGrid("该城武将信息", Personnel.Of(c.id).A, o => "武" + o.Might + " 智" + o.Int + " 忠" + o.Loyal, o => {
            var box = FaceSheet("武将信息", o, new[] {
                o.Name + "　LV " + o.Lv + "　兵 " + o.Troops,
                "武 " + o.Might + "　智 " + o.Int + "　体 " + o.Hp + "　德 " + o.Virtue,
                "忠 " + o.Loyal + "　" + BattleRules.WeaponOf(o) + " / " + BattleRules.ArmorOf(o) }, 40);
            float w = box.sizeDelta.x;
            Btn(box, "返回", (w - 116) / 2, 190, 116, BTN_H, () => Roster(c));
        }, () => ShowCity(c));
    }

    /// 城很多（情报可以挑 36 城）：列表放进能上下滚的视口
    void PickCityScroll(string title, List<MapData.City> cities, System.Func<MapData.City, string> note,
                        System.Action<MapData.City> pick, UnityEngine.Events.UnityAction cancel)
    {
        if (MapPick != null) { PickCity(title, cities, note, pick, cancel); return; }    // 照原版去大地图上点
        const float ROW = 46f;
        float listH = cities.Count * ROW;
        float viewH = Mathf.Min(listH, refH - 32 - 56 - 16 - BTN_H - FOOT - 20);
        var box = Sheet(title, 56 + viewH + 16 + BTN_H + FOOT);
        float w = box.sizeDelta.x, bw = Mathf.Min(w - 56, 300);
        Divider(box, 36);
        var view = Node("view", box);
        Place(view, (w - bw) / 2, 56, bw, viewH);
        view.gameObject.AddComponent<UnityEngine.UI.RectMask2D>();
        var content = Node("content", view);
        Place(content, 0, 0, bw, listH);
        if (listH > viewH)
        {
            var sr = view.gameObject.AddComponent<UnityEngine.UI.ScrollRect>();
            sr.content = content; sr.horizontal = false;
            sr.movementType = UnityEngine.UI.ScrollRect.MovementType.Clamped; sr.scrollSensitivity = 26;
        }
        for (int i = 0; i < cities.Count; i++)
        {
            var c = cities[i];
            string tag = Realm.Allied(c.owner) ? "(盟)" : "";
            Btn(content, c.name + tag + "　" + note(c), 0, i * ROW, bw, ROW - 8, () => pick(c), 15);
        }
        Btn(box, "放弃", (w - 136) / 2, 56 + viewH + 16, 136, BTN_H, cancel);
    }
}
