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

    RectTransform map;                              // 1024×1024 的容器
    MapData data;
    readonly Dictionary<int,RectTransform> flagRT = new();
    readonly Dictionary<int,Image> flagImg = new();
    Image ring;
    RectTransform bar; Text barName, barTier, barOwner;
    MapData.City sel;
    float zoom = 3f;                                // 屏幕 UI 单位 / 1024 单位
    const float ZMIN = 375f/1024f, ZMAX = 6f;
    Vector2 drag; bool dragging; float downT; Vector2 downPos;

    public MapView(Transform parent, MapData d, float screenW, float screenH)
    {
        data = d;
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

        ring = Panel(map, "Ring", Ring(), false);
        ring.raycastTarget = false;
        ring.gameObject.SetActive(false);

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
        var bi = bar.gameObject.AddComponent<Image>();
        bi.sprite = Frame(); bi.type = Image.Type.Sliced; bi.raycastTarget = false;
        barName  = Label(bar, "", 22, new Color32(0xF6,0xE9,0xCD,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(barName.rectTransform, 14, 6, 140, 30);
        barTier  = Label(bar, "", 11, new Color32(0xD7,0xB8,0x78,255));
        Place(barTier.rectTransform, 96, 14, 80, 16);
        barOwner = Label(bar, "", 13, new Color32(0xCF,0xC4,0xAB,255), TextAnchor.MiddleRight, FontStyle.Bold);
        Place(barOwner.rectTransform, screenW - 24 - 150, 8, 136, 26);
        Label(bar, "再点一次 进入城中 · 点空白处取消", 11, new Color32(0xA8,0x98,0x76,255))
            .rectTransform.SetParent(bar, false);
        Place((RectTransform)bar.GetChild(bar.childCount-1), 14, 40, 300, 20);
        bar.gameObject.SetActive(false);

        CenterOn(FindByName("陈留"));
        Apply();
    }
    readonly Dictionary<string,Texture2D> flagTex = new();

    MapData.City FindByName(string n) => data.cities.Find(c => c.name == n) ?? data.cities[0];

    void CenterOn(MapData.City c)
    {
        var p = map.parent as RectTransform;
        map.anchoredPosition = new Vector2(p.rect.width/2 - c.x*zoom, -(p.rect.height/2 - c.y*zoom));
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
        if (sel != null)
        {
            float rx = TIER_RX[sel.tier];
            ring.rectTransform.sizeDelta = new Vector2(rx*2.4f, rx*2.4f/1.5f);
            ring.rectTransform.anchoredPosition = new Vector2(sel.x - rx*1.2f, -(sel.y - rx*1.2f/1.5f));
        }
    }

    public void Update(float screenW, float screenH)
    {
        if (!Root.gameObject.activeInHierarchy) return;

        float wheel = Input.mouseScrollDelta.y;
        if (Mathf.Abs(wheel) > .01f)
        {
            var before = ScreenTo1024(Input.mousePosition);
            zoom = Mathf.Clamp(zoom * (1f + wheel * .12f), ZMIN, ZMAX);
            map.localScale = Vector3.one * zoom;
            var after = ScreenTo1024(Input.mousePosition);
            map.anchoredPosition += new Vector2((after.x-before.x)*zoom, -(after.y-before.y)*zoom);
            Apply();
        }
        if (Input.GetMouseButtonDown(0))
        { dragging = true; drag = Input.mousePosition; downPos = Input.mousePosition; downT = Time.time; }
        if (Input.GetMouseButton(0) && dragging)
        {
            Vector2 d = (Vector2)Input.mousePosition - drag;
            drag = Input.mousePosition;
            map.anchoredPosition += d * (screenW / Screen.width);
        }
        if (Input.GetMouseButtonUp(0))
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
        if (best == null) { Select(null); return; }
        if (sel != null && sel.id == best.id) { OnEnter?.Invoke(best); return; }
        Select(best);
    }

    public void Select(MapData.City c)
    {
        sel = c;
        ring.gameObject.SetActive(c != null);
        bar.gameObject.SetActive(c != null);
        if (c == null) return;
        var st = data.stats.Find(s => s.id == c.id);
        barName.text = c.name;
        barTier.text = st != null ? st.tierName : "";
        var f = data.flags.Find(q => q.key == c.owner);
        barOwner.text = "◼ " + (f != null ? f.use : "无主");
        ColorUtility.TryParseHtmlString(f != null ? f.hex : "#8A8578", out var col);
        barOwner.color = col;
        Apply();
    }
}
