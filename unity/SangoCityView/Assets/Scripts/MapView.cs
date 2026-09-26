// 主地图：拖动平移、滚轮缩放、势力旗、点选城池。
// 坐标一律用 1024 空间——地图容器的 sizeDelta 就是 1024×1024，靠 localScale 做缩放，
// 所以「1024 坐标」和「容器局部坐标」是同一套数，不用来回换算。
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class MapView
{
    public const float MIN_FLAG_PX = 26f, FLAG_OY = -0.30f;
    static readonly Dictionary<int,float> TIER_RX = new(){
        {1,40.50f},{2,35.44f},{3,30.38f},{4,26.66f},{5,23.29f},{6,20.25f} };

    public RectTransform Root;
    public System.Action<MapData.City> OnEnter;     // 再点一次已选中的城 → 进城
    /// 这座城能不能进（原版 c.java 6132：光标城的城主 i[城] 是玩家君主才进，别人的城 / 空城按确定没反应）
    public System.Func<MapData.City, bool> CanEnter;
    public System.Func<Vector2, bool> Blocked;       // 这块屏幕上有别的按钮，别当成点地图

    RoadData roads; MarchUnit march;
    MapData.City moveFrom;                          // 非空 = 正在等玩家点目的地
    Text hint;

    RectTransform map;                              // 1024×1024 的容器
    MapData data;
    readonly Dictionary<int,RectTransform> flagRT = new();
    readonly Dictionary<int,Image> flagImg = new();
    Image ring;
    RectTransform bar; Text barName, barTier, barOwner; Image ownerChip, barSeal;
    MapData.City sel;
    float zoom = 2.2f;                              // 屏幕 UI 单位 / 1024 单位
    const float ZMAX = 6f;
    // 最小缩放：地图刚好铺满视口（宽高都不露边）。原先按宽度 375 算，竖屏上下会露出大片黑边
    float ZMIN => Mathf.Max(vw, vh) / 1024f;
    Vector2 drag; bool dragging; float downT; Vector2 downPos;

    public MapView(Transform parent, MapData d, float screenW, float screenH)
    {
        data = d; vw = screenW; vh = screenH;
        Root = Node("MapView", parent); Fill(Root);
        var clip = Node("Clip", Root); Fill(clip);
        clip.gameObject.AddComponent<RectMask2D>();
        var hit = clip.gameObject.AddComponent<Image>();      // 透明的收点击面
        hit.color = new Color(0,0,0,0);

        map = Node("Map", clip);
        map.anchorMin = map.anchorMax = new Vector2(0,1);
        map.pivot = new Vector2(0,1);
        map.sizeDelta = new Vector2(1024,1024);
        var ri = Node("Img", map); Fill(ri);
        ri.gameObject.AddComponent<RawImage>().texture = Load("map.jpg");

        RenameplatesOnMap();

        ring = Panel(map, "Ring", Ring(), false);
        ring.raycastTarget = false;
        // 和旗子一样用左上角锚点：Apply() 里的坐标全按「左上为原点」算，
        // 留着默认的居中锚点会把环甩到画面外。
        ring.rectTransform.anchorMin = ring.rectTransform.anchorMax = new Vector2(0, 1);
        ring.rectTransform.pivot = new Vector2(0, 1);
        ring.gameObject.SetActive(false);
        // 选城高亮：每城预建一片柔光 + 一圈粗金环，平时藏着，BeginPick 时点亮能点的那几座
        foreach (var c in d.cities)
        {
            // 两圈同一张环图、大小错开一点叠成粗金环（另画粗环 / 柔光贴图在这里显示不出来，只能复用选中环那张）
            var hd = Panel(map, "halo" + c.id, ring.sprite, false); hd.raycastTarget = false;
            var gl = Panel(map, "glow" + c.id, ring.sprite, false); gl.raycastTarget = false;
            foreach (var im in new[] { hd, gl })
            {
                im.rectTransform.anchorMin = im.rectTransform.anchorMax = new Vector2(0, 1);
                im.rectTransform.pivot = new Vector2(0, 1);
                im.gameObject.SetActive(false);
            }
            haloOf[c.id] = hd; glowOf[c.id] = gl;
        }

        foreach (var f in d.flags) flagTex[f.key] = Load("flags/" + f.key + ".png");
        foreach (var c in d.cities)
        {
            var rt = Node("flag_" + c.id, map);
            rt.anchorMin = rt.anchorMax = new Vector2(0,1);
            rt.pivot = new Vector2(0,1);
            var im = rt.gameObject.AddComponent<Image>();
            im.sprite = Whole(flagTex[c.owner]); im.raycastTarget = false;
            flagRT[c.id] = rt; flagImg[c.id] = im;
        }

        // 底部信息条
        bar = Node("Bar", Root);
        Place(bar, 12, screenH - 84, screenW - 24, 72);
        var bi = bar.gameObject.AddComponent<Image>();                        // 深漆底 + 细描金框（跟城内数据框一套）
        bi.color = new Color(.085f, .06f, .035f, .88f); bi.raycastTarget = false;
        Hud.CgFrame(bar);
        barName  = Label(bar, "", 22, new Color32(0xF6,0xE9,0xCD,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(barName.rectTransform, 14, 6, 140, 30);
        barSeal = Node("seal", bar).gameObject.AddComponent<Image>(); barSeal.raycastTarget = false;     // 规模印章，Select 里跟在城名后面
        barSeal.sprite = Hud.Icon("hud_seal.png"); if (barSeal.sprite == null) barSeal.color = new Color(.72f,.16f,.10f,1);
        barTier  = Label(barSeal.transform, "", 10, new Color32(0xFF,0xF3,0xDE,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        // 势力用「色块 + 米色字」，不用纯色字——曹操的靛蓝压在暗底上读不出来
        ownerChip = Node("chip", bar).gameObject.AddComponent<Image>();
        ownerChip.raycastTarget = false;
        Place(ownerChip.rectTransform, screenW - 24 - 76, 18, 11, 11);
        barOwner = Label(bar, "", 13, new Color32(0xE6,0xDC,0xC4,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(barOwner.rectTransform, screenW - 24 - 60, 8, 60, 26);
        barHint = Label(bar, "再点一次 进入城中 · 点空白处取消", 11, new Color32(0xA8,0x98,0x76,255));
        Place(barHint.rectTransform, 14, 40, 300, 20);
        bar.gameObject.SetActive(false);

        roads = JsonUtility.FromJson<RoadData>(
            System.IO.File.ReadAllText(System.IO.Path.Combine(ArtDir, "road-paths.json")));
        march = new MarchUnit(map);

        hint = Label(Root, "", 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(hint.rectTransform, 0, 92, screenW, 26);
        hint.gameObject.SetActive(false);

        CenterOn(FindByName("陈留"));
        Apply();
    }
    readonly Dictionary<string,Texture2D> flagTex = new();
    Text barHint;

    // ── 在地图上挑一座城（原版屏 4 带条件的选城：建都只能点空城）──────────────
    //   能点的城套一圈会呼吸的金环；点一下选中、再点一次确定；点了不许点的城就提示 bad 那句话
    HashSet<int> pickAllowed; System.Action<MapData.City> onPick; string pickBad, pickConfirm;
    readonly List<(Image img, MapData.City c)> glows = new(), halos = new();
    readonly Dictionary<int, Image> haloOf = new(), glowOf = new();
    RectTransform pickBar;
    public bool Picking => pickAllowed != null;
    public void BeginPick(List<MapData.City> allowed, string title, string bad, string confirm,
                          System.Action<MapData.City> pick, System.Action cancel, MapData.City focus = null)
    {
        EndPick();
        pickAllowed = new HashSet<int>(); onPick = pick; pickBad = bad; pickConfirm = confirm;
        foreach (var c in allowed)
        {
            pickAllowed.Add(c.id);
            // 一片柔光垫底 + 一圈粗金环，两层一起呼吸；细的选中环压在黄绿地面上看不出来
            var d = haloOf[c.id]; d.color = new Color(1f, .8f, .25f, .5f); d.gameObject.SetActive(true);
            halos.Add((d, c));
            var g = glowOf[c.id]; g.color = new Color(1f, .82f, .3f, 1f); g.gameObject.SetActive(true);
            glows.Add((g, c));
        }
        if (focus != null) CenterOn(focus); else if (allowed.Count > 0) CenterOn(allowed[0]);
        Select(null);
        // 顶上一条提示 + 右下「作罢」
        pickBar = Node("pickbar", Root);
        Place(pickBar, 12, 66, vw - 24, 34);
        var bi = pickBar.gameObject.AddComponent<Image>(); bi.color = new Color(.085f, .06f, .035f, .88f); bi.raycastTarget = false;
        Hud.CgFrame(pickBar);
        var t = Label(pickBar, title, 15, new Color32(0xF4,0xDC,0x9A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Fill(t.rectTransform);
        var cb = Node("cancel", Root);
        Place(cb, vw - 12 - 96, 112, 96, 36);
        var ci = cb.gameObject.AddComponent<Image>(); ci.color = new Color(.085f, .06f, .035f, .88f);
        Hud.CgFrame(cb);
        var cl = Label(cb, "放弃", 15, new Color32(0xF4,0xDC,0x9A,255), TextAnchor.MiddleCenter, FontStyle.Bold); Fill(cl.rectTransform);
        var btn = cb.gameObject.AddComponent<Button>(); btn.transition = Selectable.Transition.None;
        btn.onClick.AddListener(() => { EndPick(); cancel?.Invoke(); });
        cancelRT = cb;
        Apply();
    }
    RectTransform cancelRT;
    public bool OverPickUi(Vector2 sp) => cancelRT != null && RectTransformUtility.RectangleContainsScreenPoint(cancelRT, sp, null);
    public void EndPick()
    {
        pickAllowed = null; onPick = null;
        foreach (var g in glows) if (g.img != null) g.img.gameObject.SetActive(false);
        foreach (var g in halos) if (g.img != null) g.img.gameObject.SetActive(false);
        glows.Clear(); halos.Clear();
        if (pickBar != null) Object.Destroy(pickBar.gameObject);
        if (cancelRT != null) Object.Destroy(cancelRT.gameObject);
        pickBar = null; cancelRT = null;
        if (barHint != null) barHint.text = "再点一次 进入城中 · 点空白处取消";
        Select(null);
    }

    // 城名是画在 map.jpg 铜牌上的（3.2.2 的名字）。换了版本、城名不同的，在那块铜牌内框上盖一块同色底、写新名。
    [System.Serializable] class Plaque { public int id; public float x, y; public string name; }
    [System.Serializable] class Plaques { public int[] fill; public float w, h; public List<Plaque> plaques; }
    void RenameplatesOnMap()
    {
        var path = System.IO.Path.Combine(ArtDir, "map-plaques.json");
        if (!System.IO.File.Exists(path)) return;
        var pl = JsonUtility.FromJson<Plaques>(System.IO.File.ReadAllText(path));
        const float K = 3277f / 1024f;                                     // map.jpg 像素 → 1024 空间
        var fill = new Color32((byte)pl.fill[0], (byte)pl.fill[1], (byte)pl.fill[2], 255);
        foreach (var p in pl.plaques)
        {
            var c = data.cities.Find(q => q.id == p.id);
            if (c == null || (Loc.Source && c.name == p.name)) continue;          // 别的语言：图上画的是简体字，全盖
            float w = pl.w / K, h = pl.h / K;
            var bg = Node("plaque_" + p.id, map).gameObject.AddComponent<Image>();
            bg.color = fill; bg.raycastTarget = false;
            var rt = bg.rectTransform;
            rt.anchorMin = rt.anchorMax = new Vector2(0, 1); rt.pivot = new Vector2(0.5f, 0.5f);
            rt.sizeDelta = new Vector2(w, h); rt.anchoredPosition = new Vector2(p.x / K, -p.y / K);
            // 字按 10 倍字号排、再缩 10 倍：地图放大六倍也不糊
            var t = Label(rt, c.name, 70, new Color32(0xF8, 0xEC, 0xD6, 255), TextAnchor.MiddleCenter);
            t.raycastTarget = false; t.resizeTextForBestFit = true; t.resizeTextMinSize = 20; t.resizeTextMaxSize = 70;   // 英文城名长，缩字
            var tr = t.rectTransform;
            tr.anchorMin = tr.anchorMax = new Vector2(0.5f, 0.5f); tr.pivot = new Vector2(0.5f, 0.5f);
            tr.sizeDelta = new Vector2(w * 10, h * 10); tr.anchoredPosition = Vector2.zero; tr.localScale = Vector3.one * 0.1f;
        }
    }

    MapData.City FindByName(string n) => data.cities.Find(c => c.name == n) ?? data.cities[0];

    float vw, vh;                                   // 视口尺寸（参考分辨率），构造时传进来

    /// 用传进来的参考分辨率算，不能读 parent.rect——构造时画布还没布局，那会儿宽高是 0，
    /// 拿它算出来的居中会整体偏掉半屏。
    /// 镜头对准某城（从城里回大地图、重建界面后用）
    public void Focus(MapData.City c) { if (c != null) CenterOn(c); }
    void CenterOn(MapData.City c)
    {
        // 城在地图边上时别把镜头推出图外（下边会露出一条黑）
        float x = Mathf.Clamp(vw/2 - c.x*zoom, vw - 1024*zoom, 0);
        float y = Mathf.Clamp(-(vh/2 - c.y*zoom), 0, 1024*zoom - vh);
        map.anchoredPosition = new Vector2(x, y);
    }

    void ZoomAt(Vector2 screenPt, float factor)
    {
        var before = ScreenTo1024(screenPt);
        zoom = Mathf.Clamp(zoom * factor, ZMIN, ZMAX);
        map.localScale = Vector3.one * zoom;
        var after = ScreenTo1024(screenPt);
        map.anchoredPosition += new Vector2((after.x - before.x) * zoom, -(after.y - before.y) * zoom);
        ClampPan();
        Apply();
    }
    public void ZoomForTest(float z) { ZoomAt(new Vector2(Screen.width / 2f, Screen.height / 2f), z / zoom); }

    /// 拖动 / 缩放之后把地图框回视口里，四边都不露黑
    void ClampPan()
    {
        var p = map.anchoredPosition;
        map.anchoredPosition = new Vector2(Mathf.Clamp(p.x, vw - 1024 * zoom, 0), Mathf.Clamp(p.y, 0, Mathf.Max(0, 1024 * zoom - vh)));
    }

    /// 城池易主 / 弃守之后换旗（旗子贴图开局就按势力全载进来了）
    public void RefreshFlags()
    {
        foreach (var c in data.cities)
            if (flagImg.TryGetValue(c.id, out var im) && flagTex.TryGetValue(c.owner ?? "none", out var t) && t != null)
                im.sprite = Whole(t);
    }

    void Apply()
    {
        map.localScale = Vector3.one * zoom;
        foreach (var c in data.cities)
        {
            var f = data.flags.Find(q => q.key == c.owner);
            float rx = TIER_RX[c.tier];
            float hUnits = Mathf.Max(MIN_FLAG_PX / zoom, rx * .62f);
            float wUnits = hUnits * f.w / f.h;
            var rt = flagRT[c.id];
            rt.sizeDelta = new Vector2(wUnits, hUnits);
            rt.anchoredPosition = new Vector2(c.x - wUnits*f.ax, -(c.y + FLAG_OY*rx - hUnits*f.ay));
        }
        foreach (var g in halos)
        {
            if (g.img == null) continue;
            float hx = TIER_RX[g.c.tier] * 1.18f;
            g.img.rectTransform.pivot = new Vector2(.5f, .5f);
            g.img.rectTransform.sizeDelta = new Vector2(hx * 2.4f, hx * 2.4f / 1.5f);
            g.img.rectTransform.anchoredPosition = new Vector2(g.c.x, -g.c.y);
        }
        foreach (var g in glows)
        {
            if (g.img == null) continue;
            float gx = TIER_RX[g.c.tier] * 1.3f;
            g.img.rectTransform.pivot = new Vector2(.5f, .5f);
            g.img.rectTransform.sizeDelta = new Vector2(gx * 2.4f, gx * 2.4f / 1.5f);
            g.img.rectTransform.anchoredPosition = new Vector2(g.c.x, -g.c.y);
        }
        if (sel != null)
        {
            float rx = TIER_RX[sel.tier];
            ring.rectTransform.sizeDelta = new Vector2(rx*2.4f, rx*2.4f/1.5f);
            ring.rectTransform.anchoredPosition = new Vector2(sel.x - rx*1.2f, -(sel.y - rx*1.2f/1.5f));
        }
    }

    void StartMarch(MapData.City from, MapData.City to, RoadData.Edge e)
    {
        var pts = e.Points();
        if (e.a != from.id) pts.Reverse();          // 折线是按 a→b 存的，反向出征要倒过来
        march.SetFlag(from.owner, data.flags.Find(f => f.key == from.owner));
        march.OnArrive = () => { hint.text = "已抵达 " + to.name; Select(to); EndMove(); };
        march.Begin(pts);
        hint.text = from.name + " → " + to.name + " 行军中";
        Select(null);
        ring.gameObject.SetActive(false);
    }

    /// 给编辑器渲染用：直接开一段行军并推进到 t（0..1），不用真去点
    public void DebugMarch(string fromName, string toName, float t)
    {
        var from = FindByName(fromName); var to = FindByName(toName);
        var e = roads.Find(from.id, to.id);
        if (e == null) { Debug.LogError(fromName + " 与 " + toName + " 不相邻"); return; }
        StartMarch(from, to, e);
        march.Layout(zoom);
        march.Step(e.length / 46f * t);              // 一次跨到 t 处
        var p = march.Pos;
        map.anchoredPosition = new Vector2(vw/2 - p.x*zoom, -(vh/2 - p.y*zoom));
        march.Layout(zoom);
    }

    float hintUntil;
    public void Update(float screenW, float screenH)
    {
        if (!Root.gameObject.activeInHierarchy) return;
        if (glows.Count > 0)                                   // 可选城那圈金环慢慢呼吸
        {
            // 亮度一直保持高位（暗下去压在黄绿地面上就看不见了），只让环一胀一缩
            float k = 1f + .06f * Mathf.Sin(Time.time * 4f);
            foreach (var g in glows) { g.img.color = new Color(1f, .9f, .45f, 1f); g.img.rectTransform.localScale = new Vector3(k, k, 1); }
            foreach (var g in halos) { g.img.color = new Color(1f, .8f, .25f, .9f); g.img.rectTransform.localScale = new Vector3(k, k, 1); }
        }
        if (hintUntil > 0 && Time.time > hintUntil && moveFrom == null) { hint.gameObject.SetActive(false); hintUntil = 0; }

        if (march != null && march.Running)
        {
            march.Layout(zoom);
            march.Step(Time.deltaTime);
            var p = march.Pos;                       // 镜头跟着部队走
            map.anchoredPosition = new Vector2(vw/2 - p.x*zoom, -(vh/2 - p.y*zoom));
            return;                                  // 行军途中不接受拖动与点击
        }

        // 双指捏合（手机）：两指距离变化多少倍就缩放多少倍，以两指中点为轴
        if (Input.touchCount == 2)
        {
            var t0 = Input.GetTouch(0); var t1 = Input.GetTouch(1);
            float now = (t0.position - t1.position).magnitude;
            float was = ((t0.position - t0.deltaPosition) - (t1.position - t1.deltaPosition)).magnitude;
            if (was > 1f) ZoomAt((t0.position + t1.position) / 2f, now / was);
            dragging = false;
            return;
        }
        float wheel = Input.mouseScrollDelta.y;
        if (Mathf.Abs(wheel) > .01f) ZoomAt(Input.mousePosition, 1f + wheel * .12f);
        if (false)
        {
            var before = ScreenTo1024(Input.mousePosition);
            zoom = Mathf.Clamp(zoom * (1f + wheel * .12f), ZMIN, ZMAX);
            map.localScale = Vector3.one * zoom;
            var after = ScreenTo1024(Input.mousePosition);
            map.anchoredPosition += new Vector2((after.x-before.x)*zoom, -(after.y-before.y)*zoom);
            ClampPan();
            Apply();
        }
        // 顶栏的年份牌 / 设置钮压在地图上：按在那上面的不算拖图，也不算点城
        if (Input.GetMouseButtonDown(0) && !(Blocked != null && Blocked(Input.mousePosition)) && !OverPickUi(Input.mousePosition))
        { dragging = true; drag = Input.mousePosition; downPos = Input.mousePosition; downT = Time.time; }
        if (Input.GetMouseButton(0) && dragging)
        {
            Vector2 d = (Vector2)Input.mousePosition - drag;
            drag = Input.mousePosition;
            map.anchoredPosition += d * (screenW / Screen.width);
            ClampPan();
        }
        if (Input.GetMouseButtonUp(0) && dragging)
        {
            dragging = false;
            bool tap = ((Vector2)Input.mousePosition - downPos).magnitude < 10f && Time.time - downT < .5f;
            if (tap) Tap(Input.mousePosition);
        }
    }

    Vector2 ScreenTo1024(Vector2 sp)
    {
        RectTransformUtility.ScreenPointToLocalPointInRectangle(map, sp, null, out var lp);
        return new Vector2(lp.x, -lp.y);
    }

    void Tap(Vector2 sp)
    {
        var p = ScreenTo1024(sp);
        MapData.City best = null; float bd = 0;
        foreach (var c in data.cities)
        {
            float rx = Mathf.Max(TIER_RX[c.tier], 30f/zoom), ry = rx/1.5f;
            float d = Mathf.Sqrt(Mathf.Pow((c.x-p.x)/rx,2) + Mathf.Pow((c.y-p.y)/ry,2));
            if (d <= 1.12f && (best == null || d < bd)) { best = c; bd = d; }
        }
        if (moveFrom != null)
        {
            if (best == null) { EndMove(); Select(null); return; }
            var edge = roads.Find(moveFrom.id, best.id);
            if (edge == null || best.id == moveFrom.id) { hint.text = best.name + " 不与 " + moveFrom.name + " 相邻"; return; }
            StartMarch(moveFrom, best, edge);
            return;
        }
        if (pickAllowed != null)
        {
            if (best == null) { Select(null); return; }
            if (!pickAllowed.Contains(best.id)) { Select(null); hint.text = pickBad; hint.gameObject.SetActive(true); hintUntil = Time.time + 1.6f; return; }
            if (sel != null && sel.id == best.id) { var p2 = onPick; var c2 = best; EndPick(); p2?.Invoke(c2); return; }
            Select(best); barHint.text = pickConfirm;
            return;
        }
        if (best == null) { Select(null); return; }
        if (sel != null && sel.id == best.id) { if (CanEnter == null || CanEnter(best)) { Sfx.Play("open"); OnEnter?.Invoke(best); } return; }
        Sfx.Play("ui_click");
        Select(best);
    }

    public MapData.City Selected => sel;

    /// 城内点了「移动」：回到地图，等玩家点一座相邻城
    public void BeginMove(MapData.City from)
    {
        moveFrom = from;
        Select(from);
        bar.gameObject.SetActive(false);
        var ns = roads.Neighbors(from.id);
        var names = new List<string>();
        foreach (var n in ns) { var c = data.cities.Find(q => q.id == n); if (c != null) names.Add(c.name); }
        hint.text = "选择目的地：" + string.Join("、", names);
        hint.gameObject.SetActive(true);
    }
    void EndMove() { moveFrom = null; hint.gameObject.SetActive(false); }

    public void Select(MapData.City c)
    {
        sel = c;
        ring.gameObject.SetActive(c != null);
        bar.gameObject.SetActive(c != null);
        if (c == null) return;
        var st = data.stats.Find(s => s.id == c.id);
        barName.text = c.name + (Realm.Allied(c.owner) ? "(盟)" : "");      // 原版 c.java 6617：盟友的城名后缀「(盟)」
        barTier.text = st != null ? st.tierName : "";
        float nw = Mathf.Min(barName.preferredWidth, 140);
        Place(barSeal.rectTransform, 14 + nw + 8, 9, 26, 26); Place(barTier.rectTransform, 0, 0, 26, 26);
        var f = data.flags.Find(q => q.key == c.owner);
        barOwner.text = c.owner == "none" ? "无主" : (Realm.Ruler(c.owner)?.Name ?? (f != null ? f.use : ""));   // 继位后换人
        ColorUtility.TryParseHtmlString(f != null ? f.hex : "#8A8578", out var col);
        ownerChip.color = col;
        if (pickAllowed == null && barHint != null)
            barHint.text = CanEnter == null || CanEnter(c) ? "再点一次 进入城中 · 点空白处取消" : "非我方城池 · 点空白处取消";
        Apply();
    }
}
