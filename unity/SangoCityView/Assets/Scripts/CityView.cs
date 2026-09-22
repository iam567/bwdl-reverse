// 城内视图。素材从 StreamingAssets 读，点击查蒙版取建筑，高亮贴图按需生成并缓存。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class CityView
{
    static readonly Dictionary<string,string[]> CMDS = new() {
        {"内政", new[]{"开垦","劝商","治水","巡查"}}, {"人事", new[]{"搜索","登用","奖赏","任命"}},
        {"军事", new[]{"移动","战争","征兵","输送"}}, {"外交", new[]{"同盟","离间","招揽","情报"}},
        {"金粮", new[]{"买入粮草","卖出粮草"}},      {"武器", new[]{"购买武器","购买马匹"}},
        {"学院", new[]{"提升智力"}},                 {"医馆", new[]{"治疗武将"}},
    };

    public RectTransform Root;
    public System.Action OnBack;
    public System.Action<string> OnCommand;         // 点了浮窗里的某条指令
    readonly List<(RectTransform rt, string cmd)> cmdRects = new();

    Texture2D city, mask;
    CityMeta meta; MapData.Stat st;
    RectTransform picRT, popRT;
    RawImage hlImg, cityImg;
    Image popBg; Transform popCmds;
    Text hName, hTier, hOwner, hTok;
    RectTransform statGrid; Sprite panelSprite, plaqueSprite, btnSprite;
    /// 本回合还剩几道令。原版那个「令牌」数就是每回合的指令数
    /// （= 势力城数夹在 3..8，b.java 1726），所以直接拿这一格显示剩余，不另开一处。
    public int Orders = -1;
    public Sprite PanelSprite => panelSprite;
    public Sprite BtnSprite => btnSprite;
    readonly Dictionary<string,Texture2D> hlCache = new();
    readonly Dictionary<string,(Image plate,Text nm,Text fn)> plaques = new();
    string cur; float f; float screenW, screenH;

    public CityView(Transform parent, float sw, float sh)
    {
        screenW = sw; screenH = sh;
        Root = Node("CityView", parent); Fill(Root);
        var bg = Node("BG", Root); Fill(bg);
        bg.gameObject.AddComponent<RawImage>().texture = Load("bg.jpg");

        float PAD = 12f, W = sw - PAD*2, y = 10f;
        hName  = Label(Root, "", 30, new Color32(0xF6,0xE9,0xCD,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(hName.rectTransform, PAD+2, y, 110, 40);
        hTier  = Label(Root, "", 11, new Color32(0x9A,0x8A,0x68,255));
        Place(hTier.rectTransform, PAD+88, y+2, 90, 16);
        hOwner = Label(Root, "", 11, new Color32(0xCF,0xC4,0xAB,255));
        Place(hOwner.rectTransform, PAD+88, y+20, 120, 16);

        var tok = Panel(Root, "Tok", Frame());
        Place(tok.rectTransform, sw-PAD-52-8-46, y, 52, 46);
        hTok = Label(tok.transform, "", 24, new Color32(0xF0,0xC3,0x4A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(hTok.rectTransform, 0, 2, 52, 28);
        Place(Label(tok.transform,"令牌",10,new Color32(0x9A,0x8A,0x68,255),TextAnchor.MiddleCenter).rectTransform,0,28,52,14);

        var bk = Panel(Root, "Back", Frame());
        bk.raycastTarget = true;
        Place(bk.rectTransform, sw-PAD-46, y, 46, 46);
        Place(Label(bk.transform,"地图",13,new Color32(0xE8,0xC0,0x6A,255),TextAnchor.MiddleCenter).rectTransform,0,0,46,46);
        backRT = bk.rectTransform;
        y += 52;

        panelSprite = Slice(Load("panel.png"), 58, 11);
        var dp = Panel(Root, "Stats", panelSprite);
        Place(dp.rectTransform, PAD, y, W, 145);
        statGrid = dp.rectTransform;
        y += 153;

        // 城图是一张固定比例的整图，按屏宽铺开之后往往比剩下的高度还高
        // （横屏尤其明显，下半截整个看不见）。所以套一个视口，图在里面上下拖。
        picW = W; picY = y;
        float availH = Mathf.Max(120f, sh - y - 8f);
        picView = Node("CityView", Root);
        Place(picView, PAD, y, W, availH);
        picView.gameObject.AddComponent<RectMask2D>();
        picScroll = picView.gameObject.AddComponent<ScrollRect>();
        picScroll.horizontal = false; picScroll.vertical = true;
        picScroll.movementType = ScrollRect.MovementType.Clamped;
        picScroll.scrollSensitivity = 30;
        picScroll.viewport = picView;

        picRT = Node("City", picView); Place(picRT, 0, 0, W, W*1.5f);
        picScroll.content = picRT;
        cityImg = picRT.gameObject.AddComponent<RawImage>();
        cityImg.raycastTarget = true;

        var hl = Node("HL", picRT); Fill(hl);
        hlImg = hl.gameObject.AddComponent<RawImage>(); hlImg.raycastTarget = false; hlImg.enabled = false;

        plaqueSprite = Slice(Load("plaque.png"), 264, 9);
        plaqueRoot = Node("Plaques", picRT); Fill(plaqueRoot);

        popBg = Panel(picRT, "Pop", Slice(Load("menu.png"), 62, 11));
        popRT = popBg.rectTransform;
        /* 浮窗里不再写建筑名——旁边的名匾已经写着「功曹府·人事」，重复，
           而且 16px 的标题在 150 宽的框里排不下，会溢出木框。 */
        popCmds = Node("Cmds", popBg.transform);
        Place((RectTransform)popCmds, 0, 0, 150, 220);
        btnSprite = Slice(Load("btn.png"), 30, 90, 6, 9);
        popBg.gameObject.SetActive(false);
    }
    RectTransform backRT, plaqueRoot, picView;
    ScrollRect picScroll;
    float picW, picY;
    string curKey;

    /// 换城：旧贴图立刻销毁——一张 1024×1536 的 RGBA 就是 6MB，
    /// 36 座全缓存着要 200 多 MB，没必要。
    void LoadCity(string key)
    {
        if (curKey == key) return;
        if (city) Object.Destroy(city);
        if (mask) Object.Destroy(mask);
        foreach (var t in hlCache.Values) if (t) Object.Destroy(t);
        hlCache.Clear();
        curKey = key;

        city = Load("cityview/" + key + ".jpg");
        mask = Load("cityview/" + key + "_mask.png");
        meta = JsonUtility.FromJson<CityMeta>(
            File.ReadAllText(Path.Combine(ArtDir, "cityview/" + key + ".json")));
        cityImg.texture = city;

        float imgH = picW * city.height / (float)city.width;
        Place(picRT, 0, 0, picW, imgH);
        picScroll.vertical = imgH > picView.sizeDelta.y;      // 装得下就别让它能拖
        picScroll.verticalNormalizedPosition = 1f;            // 每次进城从图的顶上看起
        f = picW / meta.size[0];

        foreach (Transform t in plaqueRoot) Object.Destroy(t.gameObject);
        plaques.Clear();
        foreach (var b in meta.buildings)
        {
            float fs = 11f, pw = fs*(b.name.Length+b.func.Length+1)*.82f+16, ph = fs*1.95f;
            var pl = Panel(plaqueRoot, "pl_"+b.key, plaqueSprite);
            Place(pl.rectTransform, (b.box[0]+b.box[2])/2f*f-pw/2, b.box[3]*f-ph*.62f, pw, ph);
            var nm = Label(pl.transform, b.name, (int)fs, Color.white, TextAnchor.MiddleLeft, FontStyle.Bold);
            Place(nm.rectTransform, 8, 0, pw, ph);
            var fn = Label(pl.transform, "·"+b.func, (int)(fs*.62f), Color.white, TextAnchor.MiddleRight);
            Place(fn.rectTransform, 0, 1, pw-8, ph);
            plaques[b.key] = (pl, nm, fn);
        }
        popBg.rectTransform.SetAsLastSibling();
    }

    public void Show(MapData.Stat s, string key)
    {
        LoadCity(key);
        st = s;
        hName.text = s.name; hTier.text = s.tierName;
        hOwner.text = "◼ " + Ruler(s.owner);
        Refresh();
        Select(null);
        Root.gameObject.SetActive(true);
    }

    /// 内政改完数值就地重画。字段名沿用 map.json 的，显示用原版的叫法：
    /// 治安→统治 k[]、商业→工业 o[]、治水→防灾 q[]。
    public void Refresh()
    {
        var s = st;
        if (s == null) return;
        hTok.text = (Orders >= 0 ? Orders : s.令牌).ToString();
        foreach (Transform t in statGrid) Object.Destroy(t.gameObject);
        string[,] rows = {{"君主",s.ruler},{"太守",s.governor},{"武将",s.武将.ToString()},
            {"人口",Wan(s.人口)},{"兵力",Wan(s.兵力)},{"统治",s.治安.ToString()},
            {"农业",s.农业.ToString()},{"工业",s.商业.ToString()},{"防灾",s.治水.ToString()},
            {"金子",s.黄金.ToString()},{"粮食",s.粮草.ToString()},{"规模",s.tierName}};
        float W = statGrid.sizeDelta.x, cw = (W-22)/3f, rh = (145-22)/4f;
        for (int i = 0; i < 12; i++)
        {
            var cell = Node("c"+i, statGrid);
            Place(cell, 11+(i%3)*cw, 11+(i/3)*rh, cw, rh);
            Place(Label(cell,rows[i,0],11,new Color32(0x6B,0x52,0x30,255),TextAnchor.MiddleRight,FontStyle.Bold).rectTransform,0,0,cw*.42f,rh);
            Place(Label(cell,rows[i,1],15,new Color32(0x2E,0x21,0x10,255),TextAnchor.MiddleLeft,FontStyle.Bold).rectTransform,cw*.47f,0,cw*.53f,rh);
        }
    }

    Vector2 downAt; bool dragged;

    public void Update()
    {
        if (!Root.gameObject.activeInHierarchy) return;
        // 城图能上下拖了，所以选建筑改判在松手那一下：按下到松手之间挪了几像素就算拖，不算点。
        if (Input.GetMouseButtonDown(0)) { downAt = Input.mousePosition; dragged = false; return; }
        if (Input.GetMouseButton(0))
        { if (((Vector2)Input.mousePosition - downAt).sqrMagnitude > 64f) dragged = true; return; }
        if (!Input.GetMouseButtonUp(0) || dragged) return;
        if (RectTransformUtility.RectangleContainsScreenPoint(backRT, Input.mousePosition, null))
        { OnBack?.Invoke(); return; }
        if (popBg.gameObject.activeSelf &&
            RectTransformUtility.RectangleContainsScreenPoint(popRT, Input.mousePosition, null))
        {
            foreach (var (rt, cmd) in cmdRects)
                if (RectTransformUtility.RectangleContainsScreenPoint(rt, Input.mousePosition, null))
                { OnCommand?.Invoke(cmd); return; }
            return;                                  // 点在浮窗空白处：什么都不做，别把浮窗关掉
        }
        if (!RectTransformUtility.ScreenPointToLocalPointInRectangle(picRT, Input.mousePosition, null, out var lp))
        { Select(null); return; }
        float ix = lp.x/f, iy = -lp.y/f;
        if (ix<0||iy<0||ix>=meta.size[0]||iy>=meta.size[1]) { Select(null); return; }
        Select(Pick((int)ix,(int)iy));
    }

    B Pick(int x,int y)
    {
        var p = mask.GetPixel(x, mask.height-1-y);          // 贴图 y 轴朝上，图像坐标朝下
        int r=Mathf.RoundToInt(p.r*255), g=Mathf.RoundToInt(p.g*255), b2=Mathf.RoundToInt(p.b*255);
        foreach (var b in meta.buildings)
            if (b.mask[0]==r && b.mask[1]==g && b.mask[2]==b2) return b;
        B best=null; long area=long.MaxValue;               // 院内空地兜底
        foreach (var b in meta.buildings) {
            if (x<b.box[0]||x>b.box[2]||y<b.box[1]||y>b.box[3]) continue;
            long a=(long)(b.box[2]-b.box[0])*(b.box[3]-b.box[1]);
            if (a<area) { area=a; best=b; } }
        return best;
    }

    /// 给编辑器渲染用：直接点中某座建筑，不用真去点
    public void SelectByKey(string key) => Select(meta.buildings.Find(b => b.key == key));

    void Select(B b)
    {
        cur = b?.key;
        foreach (var kv in plaques) {
            float a = (b==null || kv.Key==cur) ? 1f : .38f;
            kv.Value.plate.color = new Color(1,1,1,a);
            kv.Value.nm.color = new Color(.973f,.937f,.847f,a);
            kv.Value.fn.color = new Color(.827f,.706f,.471f,a); }
        if (b == null) { hlImg.enabled=false; popBg.gameObject.SetActive(false); return; }

        if (!hlCache.TryGetValue(b.key, out var tex)) { tex = BuildHighlight(b); hlCache[b.key]=tex; }
        hlImg.texture = tex; hlImg.enabled = true;

        foreach (Transform t in popCmds) Object.Destroy(t.gameObject);
        cmdRects.Clear();
        var cmds = CMDS.TryGetValue(b.func, out var cc) ? cc : new string[0];
        /* Sliced 的铜框是画在 RectTransform 范围内的（11px），
           子物体按 Place 摆是从外沿算起——内边距必须大于框宽，否则按钮压在框上。 */
        const float IN = 26f, BH = 32f, GAP = 8f;
        float popW = 204, popH = IN*2 + Mathf.Ceil(cmds.Length/2f)*(BH+GAP) - GAP;
        for (int i = 0; i < cmds.Length; i++) {
            var bt = Panel(popCmds, "b"+i, btnSprite);
            float bw = cmds.Length==1 ? (popW-IN*2) : (popW-IN*2-GAP)/2f;
            Place(bt.rectTransform, IN+(i%2)*(bw+GAP), IN+(i/2)*(BH+GAP), bw, BH);
            var t = Label(bt.transform, cmds[i], 14, new Color32(0xFD,0xF3,0xDA,255), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(t.rectTransform, 0, 0, bw, BH);
            var o = t.gameObject.AddComponent<Outline>();
            o.effectColor = new Color32(0x2E,0x1C,0x04,255); o.effectDistance = new Vector2(1.2f,1.2f);
            cmdRects.Add((bt.rectTransform, cmds[i])); }
        float imgW = picRT.sizeDelta.x, imgH = picRT.sizeDelta.y;
        float px = b.box[2]*f+9, py = (b.box[1]+b.box[3])/2f*f-popH/2;
        if (px+popW > imgW-4) px = b.box[0]*f-9-popW;
        Place(popRT, Mathf.Clamp(px,4,imgW-popW-4), Mathf.Clamp(py,4,imgH-popH-4), popW, popH);
        popBg.gameObject.SetActive(true);
    }

    /// 压暗四周 + 按蒙版挖洞 + 沿轮廓金边。只在包围盒附近逐像素算，其余直接铺暗色。
    Texture2D BuildHighlight(B b)
    {
        int w = mask.width, h = mask.height;
        var src = mask.GetPixels32();
        var outp = new Color32[w*h];
        var dim = new Color32(8,6,4,122); var gold = new Color32(255,236,170,255);
        for (int i = 0; i < outp.Length; i++) outp[i] = dim;
        const int R = 5;
        int x0=Mathf.Max(0,b.box[0]-R-2), y0=Mathf.Max(0,b.box[1]-R-2);
        int x1=Mathf.Min(w-1,b.box[2]+R+2), y1=Mathf.Min(h-1,b.box[3]+R+2);
        bool Sil(int x,int y){ int i=(h-1-y)*w+x;
            return src[i].r==b.mask[0]&&src[i].g==b.mask[1]&&src[i].b==b.mask[2]; }
        for (int y=y0;y<=y1;y++) for (int x=x0;x<=x1;x++) {
            int i=(h-1-y)*w+x;
            if (Sil(x,y)) { outp[i]=new Color32(0,0,0,0); continue; }
            bool rim=false;
            for (int dy=-R;dy<=R&&!rim;dy++) for (int dx=-R;dx<=R;dx++) {
                int nx=x+dx,ny=y+dy;
                if (nx<0||ny<0||nx>=w||ny>=h) continue;
                if (Sil(nx,ny)) { rim=true; break; } }
            if (rim) outp[i]=gold; }
        var t = new Texture2D(w,h,TextureFormat.RGBA32,false);
        t.SetPixels32(outp); t.Apply(); return t;
    }

    static string Ruler(string k) => k switch {
        "han"=>"汉室·献帝", "caocao"=>"曹操", "yuanshao"=>"袁绍", "sunjian"=>"孙坚",
        "lvbu"=>"吕布", "liuyan"=>"刘焉", "liubiao"=>"刘表", "player"=>"我军", _=>"无主" };

    [System.Serializable] public class B { public string key,name,func; public int[] mask,box; }
    [System.Serializable] public class CityMeta { public int[] size; public List<B> buildings; }
}
