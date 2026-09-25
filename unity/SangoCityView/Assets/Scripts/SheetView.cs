// 压在城内视图上的那层弹框：太守府（内政）和功曹府（人事）共用。
// 原版每一步都是整屏切换；竖屏手机上换成浮层，一步一屏，步骤和原版一一对应。
// 这里只放搭界面的零件——木框弹框、按钮、武将卡、结果屏的八帧动画，
// 走哪几步、每步算什么由子类照原版的步骤表来定。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public abstract class SheetView
{
    public RectTransform Root;
    public System.Action<string> OnDone;        // 走完一条指令（扣一次令），带一句结果
    public System.Action OnCancel;              // 半路作罢，或者这条指令原版本来就不扣令

    protected readonly float refW, refH;
    protected readonly Sprite panel, btn;
    protected readonly System.Random rng = new System.Random();

    static readonly Dictionary<int, Sprite> avatars = new();
    static readonly Dictionary<string, Sprite> ui = new();

    /// 战斗层那套木牌素材这边也用得上，按 battleui.json 的 border 切九宫格
    protected static Sprite Ui(string key, float border) => Ui(key, border, border, border, border);
    /// bx/by 是素材里那圈边的像素数，wx/wy 是希望它在屏幕上占多厚
    protected static Sprite Ui(string key, float bx, float by, float wx, float wy)
    {
        string k = key + "|" + wx;
        if (ui.TryGetValue(k, out var sp)) return sp;
        var t = Load(Path.Combine("battle", "battleui", "ui_" + key + ".png"));
        return ui[k] = t == null ? null : Slice(t, bx, by, wx, wy);
    }
    protected static Sprite Avatar(int id)
    {
        if (avatars.TryGetValue(id, out var sp)) return sp;
        var t = TryLoad(Path.Combine("battle", "avatar", BattleRules.Face(id) + ".jpg"));
        return avatars[id] = t == null ? null : Whole(t);
    }

    protected SheetView(string name, Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
    {
        refW = w; refH = h; panel = panelSp; btn = btnSp;
        Root = Node(name, parent);
        Place(Root, 0, 0, w, h);
        Root.gameObject.SetActive(false);
    }

    protected void Clear() { foreach (Transform t in Root) Object.Destroy(t.gameObject); }

    protected const float SHEET_MAX_W = 420f;   // 横屏上 refW 会很大，弹框不封顶就拉成一条长木板
    protected const float BTN_H = 40f, FOOT = 20f;    // 底边到按钮留 20，别贴着木框那圈铜边
    protected static readonly Color32 Ink = new Color32(0x2E,0x21,0x10,255);
    protected static readonly Color32 Rust = new Color32(0x7A,0x2E,0x10,255);

    protected float SheetW => Mathf.Min(refW - 40, SHEET_MAX_W);

    // 标题下的分隔纹统一由 Sheet 画（原来只有部分弹框自己画，看着不齐）。
    // 没自己画过的弹框：内容整体下移 DIV、框加高 DIV，给分隔纹让位；
    // 原来自己在 36 画过的（内容已经排在分隔纹下面）：调 Divider(box, 36) 时把内容区挪回原位、框缩回去，排版不变。
    const float DIV = 16f;
    RectTransform sheetBox, sheetInner; float sheetH, sheetW;
    protected RectTransform Sheet(string title, float h)
    {
        bool div = !string.IsNullOrEmpty(title);
        h = Mathf.Min(h, refH - 32 - (div ? DIV : 0));   // 再矮的屏也不许顶出画布
        Clear();
        Root.gameObject.SetActive(true);
        var dim = Node("dim", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)dim.transform);
        dim.color = new Color(0, 0, 0, .55f); dim.raycastTarget = true;
        // 点弹框外面的暗处 = 按这个框的「作罢 / 否 / 返回 / 知道了」；必须选的那几屏（补任太守、选继位）没有这类键，点了不动
        dimAct = null;
        var db = dim.gameObject.AddComponent<Button>(); db.transition = Selectable.Transition.None;
        db.onClick.AddListener(() => dimAct?.Invoke());
        float w = SheetW;
        var box = Node("box", Root);
        float bh = h + (div ? DIV : 0);
        Place(box, (refW - w) / 2, (refH - bh) / 2, w, bh);
        // 弹框底：原来 panel.png 的青铜边 + 旧纸（皱、脏）换成干净的浅米色宣纸 + 细描金框（2026-09-26 玩家要求）
        var bg = Node("bg", box).gameObject.AddComponent<Image>(); Fill((RectTransform)bg.transform); bg.raycastTarget = true;
        bg.sprite = Hud.Paper();
        Hud.CgFrame((RectTransform)bg.transform, 16, 3);
        var t = Label(box, title, 17, Ink, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(t.rectTransform, 0, 14, w, 24);
        if (div) DrawDivider(box, 36);
        var inner = Node("inner", box);
        Place(inner, 0, div ? DIV : 0, w, h);
        sheetBox = box; sheetInner = inner; sheetH = h; sheetW = w;
        return inner;
    }

    /// 城内那张 btn.png 是一条长牌，压到八十来宽两头的花纹就撞一起了。
    /// 改用战斗层那张金边木牌（200×40，边 14/4），小尺寸下边框还是细的。
    protected Button Btn(RectTransform p, string s, float x, float y, float w, float h, UnityEngine.Events.UnityAction go, int fs = 16)
    {
        var im = Node("b", p).gameObject.AddComponent<Image>();
        Place((RectTransform)im.transform, x, y, w, h);
        im.sprite = Ui("nameplate", 14, 4, 11, 3.5f) ?? btn;
        im.type = Image.Type.Sliced; im.raycastTarget = true;
        var lb = Label(p, s, fs, new Color32(0xF4,0xDC,0x9A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(lb.rectTransform, x, y - 1, w, h);
        var sh = lb.gameObject.AddComponent<Shadow>();
        sh.effectColor = new Color(0, 0, 0, .45f); sh.effectDistance = new Vector2(1, -1);   // 原来 .85 太重，字显得糊
        var b = im.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        bool dismiss = System.Array.IndexOf(DismissLabels, s) >= 0;
        string snd = dismiss ? "ui_back" : (s == "上一页" || s == "下一页") ? "page" : "ui_click";
        b.onClick.AddListener(() => Sfx.Play(snd));                            // 木鱼一声；放弃 / 返回低一点；翻页是翻书声
        b.onClick.AddListener(go);
        if (dismiss) dimAct = go;       // 取消类的键：点外面也算它
        return b;
    }
    UnityEngine.Events.UnityAction dimAct;

    // ── 兵器列表的一行（武器店 / 战场村庄共用）：像素图标 · 名字 · 属性 / 条件 · 价格 ──────────
    static readonly Dictionary<int, Sprite> itemIcons = new();
    protected static Sprite ItemIcon(int item)
    {
        if (itemIcons.TryGetValue(item, out var sp)) return sp;
        var t = TryLoad(Path.Combine("items", "item_" + item + ".png"));
        if (t != null) t.filterMode = FilterMode.Point;                       // 像素图标：放大不糊
        return itemIcons[item] = t != null ? Whole(t) : null;
    }
    /// 已装备的整行泛金；用不了的条件标红；钱不够的价格标红。点整行就买
    protected void ItemRow(RectTransform p, float x, float y, float w, float h, int item, Officer o, int gold, int idx,
                           UnityEngine.Events.UnityAction go)
    {
        bool worn = item >= 24 ? o.SubLv == item : o.ArmLv == item;
        bool can = Market.CanWield(o, item), rich = gold >= Market.Price[item];
        var bg = Node("item_" + item, p).gameObject.AddComponent<Image>();
        Place(bg.rectTransform, x, y, w, h);
        bg.color = worn ? new Color(.86f, .62f, .18f, .40f) : new Color(.18f, .12f, .05f, idx % 2 == 0 ? .12f : .05f);
        var b = bg.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        b.onClick.AddListener(() => Sfx.Play("ui_click")); b.onClick.AddListener(go);
        // 图标槽：深色方块 + 头像框那圈金线
        float s = h - 8;
        var slot = Node("slot", bg.transform).gameObject.AddComponent<Image>();
        Place(slot.rectTransform, 4, 4, s, s); slot.color = new Color(.16f, .12f, .08f, .92f); slot.raycastTarget = false;
        var ic = Node("icon", slot.transform).gameObject.AddComponent<Image>();
        Place(ic.rectTransform, 3, 3, s - 6, s - 6); ic.preserveAspect = true; ic.raycastTarget = false;
        ic.sprite = ItemIcon(item); if (ic.sprite == null) ic.color = new Color(0, 0, 0, 0);
        var frm = Node("frm", bg.transform).gameObject.AddComponent<Image>();
        Place(frm.rectTransform, 2, 2, s + 4, s + 4); frm.sprite = Ui("portrait", 6); frm.type = Image.Type.Sliced; frm.raycastTarget = false;
        // 名字 + 属性 / 条件
        float tx = s + 14;
        var nm = Label(bg.transform, BattleRules.EquipName[item] + (worn ? "  已装备" : ""), 16, Ink, TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(nm.rectTransform, tx, 5, w - tx - 90, 24);
        string stat = (item >= 24 ? "防御 +" : "攻击 +") + BattleRules.ArmBonus(item) + "%";
        string need = Market.Need(item);
        var at = Label(bg.transform, stat + (need != null ? "　需" + need : ""), 12,
                       can ? Rust : new Color32(0xC0, 0x28, 0x18, 255), TextAnchor.MiddleLeft);
        Place(at.rectTransform, tx, h - 26, w - tx - 90, 20);
        // 价格
        var pr = Label(bg.transform, Market.Price[item] + " 金", 16, rich ? Ink : new Color32(0xC0, 0x28, 0x18, 255), TextAnchor.MiddleRight, FontStyle.Bold);
        Place(pr.rectTransform, w - 96, 0, 86, h);
    }
    static readonly string[] DismissLabels = { "放弃", "否", "返回", "知道了", "退出村庄", "继续游戏" };

    /// 标题底下压一条纹样
    protected void Divider(RectTransform box, float y)
    {
        if (box == sheetInner && Mathf.Approximately(y, 36))
        {   // 标题下那条 Sheet 已经画了；这个弹框本来就把内容排在 36 那条下面 → 内容区挪回原位、框缩回原高
            Place(sheetInner, 0, 0, sheetW, sheetH);
            Place(sheetBox, (refW - sheetW) / 2, (refH - sheetH) / 2, sheetW, sheetH);
            return;
        }
        DrawDivider(box, y);
    }
    void DrawDivider(RectTransform box, float y)
    {
        float w = box.sizeDelta.x;
        var dv = Node("dv", box).gameObject.AddComponent<Image>();
        Place((RectTransform)dv.transform, (w - 200) / 2, y, 200, 16);
        // 新的描金云头分隔纹（hud.js ui_divider2）；没到就退回战斗层那条青铜回纹
        var dvs = TryLoad(Path.Combine("hud", "ui_divider2.png")) ?? Load(Path.Combine("battle", "battleui", "ui_divider.png"));
        if (dvs != null) { dv.sprite = Whole(dvs); dv.preserveAspect = true; }
        dv.raycastTarget = false;
    }

    /// 原版屏 7：武将列表。玩家要求回到列表：小头像、姓名、武 智 体 德 忠，一页 10 条，多了翻页。
    /// 姓名下面一行小字写这条指令真正在意的那项（stat 给出：学费、兵力、剩余体力……）。
    protected const float CARD_W = 99f, CARD_H = 134f, GAP = 8f;
    const int PAGE = 10;
    const float ROW_H = 44f, HEAD_H = 22f;
    static readonly string[] Cols = { "武", "智", "体", "德", "忠" };

    /// 一页武将列表：表头 + 最多 10 行 + 翻页。onRow 点一行，marked 判断这行是不是勾上了（多选用）
    float OfficerTable(RectTransform box, float y, List<Officer> list, System.Func<Officer, string> stat, int page,
                       System.Action<Officer> onRow, System.Func<Officer, bool> marked, System.Action<int> turn)
    {
        float w = box.sizeDelta.x, x0 = 22, tw = w - 44;
        float colW = 34, colsX = x0 + tw - Cols.Length * colW;
        // 表头（有推荐评级时，「武将」后面跟一行颜色图例）
        var hd = Label(box, "武将", 12, Rust, TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(hd.rectTransform, x0 + 46, y, 100, HEAD_H);
        for (int c = 0; c < Cols.Length; c++)
        {
            var hl = Label(box, Cols[c], 12, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(hl.rectTransform, colsX + c * colW, y, colW, HEAD_H);
        }
        y += HEAD_H;
        int pages = Mathf.Max(1, (list.Count + PAGE - 1) / PAGE);
        page = Mathf.Clamp(page, 0, pages - 1);
        for (int i = page * PAGE, r = 0; i < list.Count && r < PAGE; i++, r++)
        {
            var o = list[i];
            float ry = y + r * ROW_H;
            bool on = marked != null && marked(o);
            var bg = Node("row", box).gameObject.AddComponent<Image>();
            Place(bg.rectTransform, x0, ry + 2, tw, ROW_H - 4);
            bg.color = on ? new Color(.86f, .62f, .18f, .45f) : new Color(.18f, .12f, .05f, r % 2 == 0 ? .10f : .04f);
            bg.raycastTarget = true;
            var b = bg.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
            b.onClick.AddListener(() => { Sfx.Play("ui_click"); onRow(o); });
            // 小头像
            var face = Node("face", bg.transform).gameObject.AddComponent<Image>();
            Place(face.rectTransform, 4, 3, ROW_H - 10, ROW_H - 10);
            face.sprite = Avatar(o.Id); face.raycastTarget = false;
            if (face.sprite == null)
            {
                face.color = new Color(.30f, .20f, .10f, .85f);
                var q = Label(face.transform, o.Id < 0 ? "新" : "?", 16, new Color(1f, .9f, .7f), TextAnchor.MiddleCenter, FontStyle.Bold);
                Place(q.rectTransform, 0, 0, ROW_H - 10, ROW_H - 10);
            }
            // 姓名 + 这条指令关心的那项
            string sub = stat != null ? Extra(stat(o)) : null;           // 跟右边五列重复的数去掉，只留额外信息（费、城数、兵器…）
            int tier = curRate == null ? 1 : curRate(o);
            var nm = Label(bg.transform, (on ? "✓ " : "") + o.Name, 15, curRate == null ? Ink : RateColor(tier), TextAnchor.MiddleLeft, FontStyle.Bold);
            if (curRate != null && tier != 1)                                  // 名字后面挂个小字：优选 / 推荐 / 不推荐（光看颜色容易认错）
            {
                var tg = Label(bg.transform, tier >= 3 ? "优选" : tier == 2 ? "推荐" : "不推荐", 10, RateColor(tier), TextAnchor.MiddleLeft, FontStyle.Bold);
                Place(tg.rectTransform, ROW_H - 2 + nm.preferredWidth + 5, 3, 44, 18);
            }
            Place(nm.rectTransform, ROW_H - 2, string.IsNullOrEmpty(sub) ? 0 : 2, colsX - x0 - ROW_H, string.IsNullOrEmpty(sub) ? ROW_H - 4 : 20);
            if (!string.IsNullOrEmpty(sub))
            {
                var sl = Label(bg.transform, sub, 11, Rust, TextAnchor.MiddleLeft);
                Place(sl.rectTransform, ROW_H - 2, 21, colsX - x0 - ROW_H + 10, 16);
            }
            // 武 智 体 德 忠（体力写当前值；君主忠诚原版显示 --）
            string[] v = o.Id < 0 ? new[] { "", "", "", "", "" }            // 「自建」这种占位行：没有数值
                : new[] { o.Might.ToString(), o.Int.ToString(), o.Hp.ToString(), o.Virtue.ToString(),
                          Realm.RulerOf(o) != null ? "--" : o.Loyal.ToString() };
            for (int c = 0; c < v.Length; c++)
            {
                var vl = Label(bg.transform, v[c], 14, Ink, TextAnchor.MiddleCenter);
                Place(vl.rectTransform, colsX - x0 + c * colW, 0, colW, ROW_H - 4);
            }
        }
        float used = HEAD_H + Mathf.Min(PAGE, Mathf.Max(1, list.Count)) * ROW_H;
        if (curRate != null)                                               // 图例：表格底下单独一行
        {
            var lg = Label(box, "<color=#1E7B34>■ 优选</color>　<color=#7E9A12>■ 推荐</color>　<color=#2E2110>■ 一般</color>　<color=#B22A1A>■ 不推荐</color>",
                           11, Ink, TextAnchor.MiddleCenter);
            Place(lg.rectTransform, 0, y + Mathf.Min(PAGE, Mathf.Max(1, list.Count)) * ROW_H + 2, w, 16);
            used += 18; y += 18;
        }
        if (pages > 1)                                                     // 翻页
        {
            float py = y + Mathf.Min(PAGE, list.Count) * ROW_H + 4;
            var pl = Label(box, (page + 1) + " / " + pages, 13, Rust, TextAnchor.MiddleCenter);
            Place(pl.rectTransform, 0, py, w, 30);
            if (page > 0) Btn(box, "上一页", w / 2 - 130, py, 90, 30, () => turn(page - 1), 13);
            if (page < pages - 1) Btn(box, "下一页", w / 2 + 40, py, 90, 30, () => turn(page + 1), 13);
            used += 38;
        }
        return used;
    }
    /// 名字下面那行：「武 / 智 / 体 / 德 / 忠 + 数字」右边五列已经有了，删掉；剩下的（费10、城 4、短剑…）才写
    static readonly System.Text.RegularExpressions.Regex Dup = new(@"[武智体德忠][力行诚]?\s*\d+");
    static string Extra(string s)
    {
        if (string.IsNullOrEmpty(s)) return s;
        s = Dup.Replace(s, "");
        s = System.Text.RegularExpressions.Regex.Replace(s, @"[\s　]+", " ").Trim();
        return s.Length == 0 ? null : s;
    }
    // ── 推荐评级（按原版公式看这条指令吃哪项数值）：3 优选 深绿 / 2 推荐 黄绿 / 1 一般 墨色 / 0 不推荐 红 ──
    System.Func<Officer, int> curRate;
    static Color32 RateColor(int t) => t >= 3 ? new Color32(0x1E, 0x7B, 0x34, 255) : t == 2 ? new Color32(0x7E, 0x9A, 0x12, 255)
                                     : t <= 0 ? new Color32(0xB2, 0x2A, 0x1A, 255) : Ink;
    /// 数值越高越好：≥ great 优选、≥ good 推荐、< bad 不推荐
    protected static int Tier(int v, int good, int great, int bad) => v >= great ? 3 : v >= good ? 2 : v < bad ? 0 : 1;

    float TableH(int n) => HEAD_H + Mathf.Min(PAGE, Mathf.Max(1, n)) * ROW_H + (n > PAGE ? 38 : 0) + (curRate != null ? 18 : 0);

    protected void PickGrid(string title, List<Officer> list, System.Func<Officer, string> stat,
                            System.Action<Officer> pick, UnityEngine.Events.UnityAction cancel, int page = 0,
                            System.Func<Officer, int> rate = null)
    {
        curRate = rate;
        var box = Sheet(title, 54 + TableH(list.Count) + 14 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        float used = OfficerTable(box, 54, list, stat, page, pick, null, pg => PickGrid(title, list, stat, pick, cancel, pg, rate));
        if (cancel != null) Btn(box, "放弃", (w - 136) / 2, 54 + used + 14, 136, BTN_H, cancel);   // 必选的那几屏（补任太守）不给作罢
    }

    /// 多选武将（原版屏 7 带勾选 V[]）：点一行打勾 / 取消，底下「确定」交出勾上的人。max 为 0 不限
    protected void PickMany(string title, List<Officer> list, System.Func<Officer, string> stat, int max,
                            System.Action<List<Officer>> done, UnityEngine.Events.UnityAction cancel,
                            List<Officer> preset = null, int page = 0, System.Func<Officer, int> rate = null)
    {
        curRate = rate;
        var picked = new List<Officer>(preset ?? new List<Officer>());
        var box = Sheet(title + (picked.Count > 0 ? "　已选 " + picked.Count : ""), 54 + TableH(list.Count) + 14 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        float used = OfficerTable(box, 54, list, stat, page, o => {
            if (picked.Contains(o)) picked.Remove(o);
            else if (max <= 0 || picked.Count < max) picked.Add(o);
            PickMany(title, list, stat, max, done, cancel, picked, page, rate);    // 就地重画（勾选状态 / 已选人数）
        }, o => picked.Contains(o), pg => PickMany(title, list, stat, max, done, cancel, picked, pg, rate));
        float by = 54 + used + 14;
        Btn(box, "确定", w / 2 - 104, by, 96, BTN_H, () => { if (picked.Count > 0) done(new List<Officer>(picked)); });
        Btn(box, "放弃", w / 2 + 8, by, 96, BTN_H, cancel);
    }

    /// 大地图选城：Game 塞进来。(能选的城, 标题, 选中, 作罢)
    public static System.Action<List<MapData.City>, string, System.Action<MapData.City>, System.Action> MapPick;

    /// 选目标城：原版切到大地图屏 4 去点。有 MapPick 就照原版去大地图上点（能选的城金环高亮），
    /// 没有（自检）才退回列表
    protected void PickCity(string title, List<MapData.City> cities, System.Func<MapData.City, string> note,
                            System.Action<MapData.City> pick, UnityEngine.Events.UnityAction cancel)
    {
        if (MapPick != null)
        {
            Root.gameObject.SetActive(false);
            MapPick(cities, title, pick, () => cancel?.Invoke());
            return;
        }
        const float ROW = 50f;
        var box = Sheet(title, 56 + cities.Count * ROW + 8 + BTN_H + FOOT);
        float w = box.sizeDelta.x, bw = Mathf.Min(w - 56, 300);
        Divider(box, 36);
        for (int i = 0; i < cities.Count; i++)
        {
            var c = cities[i];
            Btn(box, c.name + "　" + note(c), (w - bw) / 2, 58 + i * ROW, bw, ROW - 8, () => pick(c));
        }
        Btn(box, "放弃", (w - 136) / 2, 58 + cities.Count * ROW + 8, 136, BTN_H, cancel);
    }

    /// 数字调节：「名目 数值」+ 一排加减钮。change 收到增量（int.MaxValue = 满，int.MinValue = 零），
    /// 自己夹上下限再重画。钮少（≤4）名目和钮同一行；钮多就名目一行、钮另起一行铺满，免得挤到名目上。
    protected static float StepperH(int buttons) => buttons >= 5 ? 72f : 40f;
    protected void Stepper(RectTransform box, float y, string label, int value, int[] steps, System.Action<int> change)
    {
        float w = box.sizeDelta.x, gap = 5;
        bool twoLine = steps.Length >= 5;
        var l = Label(box, label + "  " + value, 16, Ink, twoLine ? TextAnchor.MiddleCenter : TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(l.rectTransform, twoLine ? 0 : 28, y, twoLine ? w : 110, 32);
        float x0 = twoLine ? 26 : 140, by = twoLine ? y + 34 : y;
        float bw = Mathf.Min(64, (w - 26 - x0 - gap * (steps.Length - 1)) / steps.Length);
        float x = w - 26 - steps.Length * bw - (steps.Length - 1) * gap;
        foreach (int d in steps)
        {
            int dd = d;
            string t = d == int.MaxValue ? "满" : d == int.MinValue ? "零" : (d > 0 ? "+" : "−") + Mathf.Abs(d);
            Btn(box, t, x, by, bw, 32, () => change(dd), 13);
            x += bw + gap;
        }
    }

    /// 只有头像和那圈金框，不带木牌底——确认屏和结果屏放在左边用
    protected void Face(RectTransform p, Officer o, float x, float y, float size)
    {
        var face = Node("face", p).gameObject.AddComponent<Image>();
        Place((RectTransform)face.transform, x, y, size, size);
        face.sprite = Avatar(o.Id); face.raycastTarget = false;
        if (face.sprite == null) face.color = new Color(.12f, .10f, .08f, 1);
        var frm = Node("frm", p).gameObject.AddComponent<Image>();
        Place((RectTransform)frm.transform, x - 3, y - 3, size + 6, size + 6);
        frm.sprite = Ui("portrait", 6); frm.type = Image.Type.Sliced; frm.raycastTarget = false;
    }

    /// 一张武将卡：木牌底 + 头像 + 姓名 + 一行属性
    protected void Card(RectTransform p, Officer o, string stat, float x, float y, UnityEngine.Events.UnityAction go)
    {
        var bg = Node("card", p).gameObject.AddComponent<Image>();
        Place((RectTransform)bg.transform, x, y, CARD_W, CARD_H);
        bg.sprite = Ui("pick_card", 6); bg.type = Image.Type.Sliced; bg.raycastTarget = true;
        if (bg.sprite == null) bg.color = new Color(.28f, .18f, .10f, 1);

        float pad = 7f, av = CARD_W - pad * 2;
        var face = Node("face", (RectTransform)bg.transform).gameObject.AddComponent<Image>();
        Place((RectTransform)face.transform, pad, pad, av, av);
        face.sprite = Avatar(o.Id); face.raycastTarget = false;
        if (face.sprite == null) face.color = new Color(.12f, .10f, .08f, 1);
        var frm = Node("frm", (RectTransform)bg.transform).gameObject.AddComponent<Image>();
        Place((RectTransform)frm.transform, pad - 2, pad - 2, av + 4, av + 4);
        frm.sprite = Ui("portrait", 6); frm.type = Image.Type.Sliced; frm.raycastTarget = false;

        var nm = Label(bg.transform, o.Name, 15, new Color32(0xF8,0xEC,0xD4,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(nm.rectTransform, 0, pad + av + 2, CARD_W, 20);
        var sh = nm.gameObject.AddComponent<Shadow>();
        sh.effectColor = new Color(0, 0, 0, .9f); sh.effectDistance = new Vector2(1, -1);
        var iq = Label(bg.transform, stat, 13, new Color32(0xE8,0xC0,0x6A,255), TextAnchor.MiddleCenter);
        Place(iq.rectTransform, 0, pad + av + 20, CARD_W, 18);

        var b = bg.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        b.onClick.AddListener(go);
    }

    /// 左头像、右两三行字、底下一排按钮——确认屏的版式
    protected RectTransform FaceSheet(string title, Officer o, string[] lines, float extraH = 0)
    {
        const float FACE = 92f, MARGIN = 24f;
        var box = Sheet(title, 150 + extraH + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Face(box, o, MARGIN, 48, FACE);
        float tx = MARGIN + FACE + 18, tw = w - tx - MARGIN;
        for (int i = 0; i < lines.Length; i++)
        {
            var l = Label(box, lines[i], 17, i == 0 ? Ink : Rust, TextAnchor.MiddleLeft, FontStyle.Bold);
            Place(l.rectTransform, tx, 62 + i * 30, tw, 26);
        }
        return box;
    }

    void SayWithFace(string title, string msg, bool done, Officer who)
    {
        const float FACE = 92f, MARGIN = 24f;
        var box = Sheet(title, 150 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Face(box, who, MARGIN, 48, FACE);
        float tx = MARGIN + FACE + 18, tw = w - tx - MARGIN;
        var nm = Label(box, who.Name, 17, Ink, TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(nm.rectTransform, tx, 52, tw, 24);
        var l = Label(box, msg, 15, Rust, TextAnchor.UpperLeft);
        l.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(l.rectTransform, tx, 80, tw, 62); Cjk(l);
        Btn(box, "知道了", (w - 136) / 2, 150, 136, BTN_H, () => Finish(done, msg));
    }

    System.Action next;             // 这一屏之后还有下一步（出征台词 → 开战）：不收尾，接着走
    void Finish(bool done, string msg)
    {
        var n = next; next = null;
        if (n != null) { cgImg = null; cgFrames = null; n(); return; }
        Close();
        if (done) OnDone?.Invoke(msg); else OnCancel?.Invoke();
    }

    protected void Close() { cgImg = null; cgFrames = null; Root.gameObject.SetActive(false); Clear(); }
    protected void Cancel() { Close(); OnCancel?.Invoke(); }

    // ── 执行结果：上面一段八帧动画，下面那段文字 ──────────────────────
    // 帧图 512×384，和计策动图一个规格。
    Image cgImg; Sprite[] cgFrames; float cgT0;
    const float CG_FRAME = 0.14f;                      // 和计策动图同一个节拍

    static readonly Dictionary<string, Sprite> cgCache = new();
    /// dir/prefix 例：politicscg/zc_0 → politicscg/zc_0_f3.jpg
    static Sprite CgFrame(string stem, int f)
    {
        string k = stem + "_" + f;
        if (cgCache.TryGetValue(k, out var sp)) return sp;
        var t = Load(stem + "_f" + f + ".jpg");
        return cgCache[k] = t == null ? null : Whole(t);
    }

    public void Tick()
    {
        if (cgImg == null || cgFrames == null) return;
        var sp = cgFrames[(int)((Time.time - cgT0) / CG_FRAME) % 8];
        if (sp != null && cgImg.sprite != sp) cgImg.sprite = sp;
    }

    /// 城里的太守——原版前置条件不过时那句话是太守说的：a(j[au], …)
    protected static Officer Gov(MapData.Stat s) => Personnel.Of(s.id).A.Find(o => o.Name == s.governor);

    /// 原版屏 10/11：执行结果，按一下退回城内。done 为真才扣一次指令。
    /// cgStem 为空或帧图不在，就只出文字；这时如果给了 who，左边放说话人的头像——
    /// 原版这类话都走 a(武将, 文字)，本来就带着说话人的脸。
    protected void Say(string title, string msg, bool done, string cgStem, Officer who = null, System.Action after = null)
    {
        next = after;
        if (done && cgStem != null) Sfx.Play("ui_confirm");                      // 指令做成了：一声小铃
        // panel.png 那圈绿铜边本身就占十来像素，动画还得再往里收一截，
        // 不然贴着边框看着像溢出来了。
        const float MARGIN = 20f;
        cgImg = null; cgFrames = null;
        bool withCg = cgStem != null;
        if (withCg)
        {
            cgFrames = new Sprite[8]; int have = 0;
            for (int f = 0; f < 8; f++) { cgFrames[f] = CgFrame(cgStem, f); if (cgFrames[f] != null) have++; }
            if (have == 0) { cgFrames = null; withCg = false; }
        }
        if (!withCg && who != null) { SayWithFace(title, msg, done, who); return; }
        float w0 = SheetW;
        float ph = withCg ? (w0 - MARGIN * 2) * 384f / 512f : 0f;
        var box = Sheet(title, (withCg ? 46 + ph + 68 : 106) + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        float ty = 48;
        if (withCg)
        {
            cgImg = Node("cg", box).gameObject.AddComponent<Image>();
            Place((RectTransform)cgImg.transform, MARGIN, 40, w - MARGIN * 2, ph);
            cgImg.sprite = cgFrames[0]; cgImg.raycastTarget = false;
            Hud.CgFrame(cgImg.rectTransform);                                 // 动画外沿的细画框
            cgT0 = Time.time;
            ty = 40 + ph + 6;
        }
        var l = Label(box, msg, 15, Ink, TextAnchor.UpperCenter);
        l.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(l.rectTransform, MARGIN, ty, w - MARGIN * 2, 60); Cjk(l);
        Btn(box, "知道了", (w - 136) / 2, withCg ? ty + 62 : 106, 136, BTN_H, () => Finish(done, msg));
    }
}
