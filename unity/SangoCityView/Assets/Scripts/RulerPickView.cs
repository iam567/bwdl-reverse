// 君主选择：原版主菜单「新游戏」→ 屏 1 的 bB=2 那一页（c.S() 6477，「君主选择」/「请选择扮演君主」）。
// 原版上下键逐个翻，右边一栏写 LV / 武 / 智 / 体 / 德 / 兵 / 经验 / 兵器 / 防具 和城数；
// 竖屏上改成君主头像卡片一屏排开，点一张看那一栏，再「确定」。
using System.Collections.Generic;
using UnityEngine;
using static UIKit;

public class RulerPickView : SheetView
{
    public System.Action<Faction> OnPick;
    public System.Action<Officer, List<Officer>, MapData.City> OnFound;   // 自建：君主、随从、建都的空城
    public System.Action<Officer, List<Officer>, List<MapData.City>> OnPickCapital;   // 自建：去大地图上挑空城
    public Dictionary<int, Officer> Officers;
    static readonly Officer SelfMade = new Officer { Id = -1, Name = "自建" };
    public System.Action OnBack;
    MapData map;

    public RulerPickView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("rulerpick", parent, w, h, panelSp, btnSp) { }

    public void Open(MapData m)
    {
        map = m;
        var rulers = new List<Officer>();
        foreach (var f in Realm.All) if (f.Alive && Realm.Ruler(f.Key) is Officer o) rulers.Add(o);
        rulers.Add(SelfMade);                                                  // 第 8 行「自建」
        PickGrid("君主选择　请选择扮演君主", rulers, o => o == SelfMade ? "自建势力" : "城 " + Realm.CityCount(map, Realm.RulerOf(o).Key),
                 o => { if (o == SelfMade) SelfStart(); else Detail(o); }, () => { Close(); OnBack?.Invoke(); });
    }

    /// 自建：bR() 10745 → 选随从（112「选择自建武将」）→ 任命君主（111「任命自建君主」）→ 选空城建都（ap() 10805）
    void SelfStart()
    {
        var made = CustomOfficers.All().ConvertAll(r => Officers.TryGetValue(r.id, out var x) ? x : null);
        made.RemoveAll(x => x == null);
        if (made.Count == 0) { Say("君主选择", "武将尚未创建!", false, null, null, () => Open(map)); return; }
        PickMany("选择自建武将", made, o => "武" + o.Might + " 智" + o.Int, 0, team =>
            PickGrid("任命自建君主", team, o => "德" + o.Virtue + " 智" + o.Int, lord => {
                var empty = map.cities.FindAll(c => c.owner == "none");
                if (empty.Count == 0) { Say("选城建都", "请选择空城!", false, null, null, () => Open(map)); return; }
                // 原版切到大地图屏 4「选城建都 / 选择一个空城建都」，点的不是空城就「请选择空城!」（c.java 6119）
                Close(); OnPickCapital?.Invoke(lord, team, empty);
            }, () => Open(map)), () => Open(map));
    }

    void Detail(Officer o)
    {
        var f = Realm.RulerOf(o);
        var box = FaceSheet("君主选择", o, new[] {
            o.Name + "　LV " + o.Lv + "　城 " + Realm.CityCount(map, f.Key),
            "武 " + o.Might + "　智 " + o.Int + "　体 " + o.HpMax + "　德 " + o.Virtue,
            "兵 " + o.Troops + "　经验 " + o.Exp / 100 + "　" + BattleRules.WeaponOf(o) + " / " + BattleRules.ArmorOf(o),
        }, 40);
        float w = box.sizeDelta.x;
        Btn(box, "确定", w / 2 - 104, 190, 96, BTN_H, () => { Close(); OnPick?.Invoke(f); });
        Btn(box, "返回", w / 2 + 8, 190, 96, BTN_H, () => Open(map));
    }
}
