// 城内视图（运行时）。素材从 StreamingAssets 读，点击查蒙版取建筑，
// 高亮贴图按需生成并缓存。跑起来能真点。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;

public class CityView : MonoBehaviour
{
    public Font font;
    const int REF_W = 375, REF_H = 812;
    const float PAD = 12f;

    static readonly Dictionary<string,string[]> CMDS = new() {
        {"内政", new[]{"开垦","劝商","治水","巡查"}},
        {"人事", new[]{"搜索","登用","奖赏","任命"}},
        {"军事", new[]{"移动","战争","征兵","输送"}},
        {"外交", new[]{"同盟","离间","招揽","情报"}},
        {"金粮", new[]{"买入粮草","卖出粮草"}},
        {"武器", new[]{"购买武器","购买马匹"}},
        {"学院", new[]{"提升智力"}},
        {"医馆", new[]{"治疗武将"}},
    };

    Texture2D city, mask;
    CityMeta meta; Stats st;
    RectTransform picRT, popRT;
    RawImage hlImg;
    Image popBg;
    Text popName, popFunc;
    Transform popCmds;
    readonly Dictionary<string,Texture2D> hlCache = new();
    readonly Dictionary<string,(Image plate,Text nm,Text fn)> plaques = new();
    string cur;
    float f;                                   // 图像坐标 → UI 单位

    string ART => Path.Combine(Application.streamingAssetsPath, "Art");
    Texture2D Load(string f2){ var t=new Texture2D(2,2,TextureFormat.RGBA32,false);
        t.LoadImage(File.ReadAllBytes(Path.Combine(ART,f2))); t.wrapMode=TextureWrapMode.Clamp; t.Apply(); return t; }

    static Sprite Slice(Texture2D t, float bx, float by, float wx, float wy) =>
        Sprite.Create(t, new Rect(0,0,t.width,t.height), new Vector2(.5f,.5f),
            bx*100f/wx, 0, SpriteMeshType.FullRect, new Vector4(bx,by,bx,by));
    static Sprite Slice(Texture2D t, float b, float w) => Slice(t,b,b,w,w);

    RectTransform Node(string n, Transform p){ var g=new GameObject(n,typeof(RectTransform));
        g.transform.SetParent(p,false); return (RectTransform)g.transform; }
    static void Place(RectTransform rt,float x,float y,float w,float h){
        rt.anchorMin=rt.anchorMax=new Vector2(0,1); rt.pivot=new Vector2(0,1);
        rt.anchoredPosition=new Vector2(x,-y); rt.sizeDelta=new Vector2(w,h); }
    Text Label(Transform p,string s,int size,Color c,TextAnchor a=TextAnchor.MiddleLeft,FontStyle st2=FontStyle.Normal){
        var rt=Node("t",p); var t=rt.gameObject.AddComponent<Text>();
        t.font=font; t.fontSize=size; t.color=c; t.alignment=a; t.text=s; t.fontStyle=st2;
        t.horizontalOverflow=HorizontalWrapMode.Overflow; t.verticalOverflow=VerticalWrapMode.Overflow;
        t.raycastTarget=false; return t; }
    Image Panel(Transform p,string n,Sprite s){ var rt=Node(n,p);
        var im=rt.gameObject.AddComponent<Image>(); im.sprite=s; im.type=Image.Type.Sliced;
        im.raycastTarget=false; return im; }

    void Start()
    {
        city = Load("city.jpg"); mask = Load("mask.png");
        meta = JsonUtility.FromJson<CityMeta>(File.ReadAllText(Path.Combine(ART,"city.json")));
        st   = JsonUtility.FromJson<Stats>(File.ReadAllText(Path.Combine(ART,"stats.json")));

        var canvasGO=new GameObject("Canvas",typeof(Canvas),typeof(CanvasScaler),typeof(GraphicRaycaster));
        var canvas=canvasGO.GetComponent<Canvas>(); canvas.renderMode=RenderMode.ScreenSpaceOverlay;
        var sc=canvasGO.GetComponent<CanvasScaler>();
        sc.uiScaleMode=CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution=new Vector2(REF_W,REF_H); sc.matchWidthOrHeight=0;
        var root=(RectTransform)canvasGO.transform;

        var bg=Node("BG",root); bg.anchorMin=Vector2.zero; bg.anchorMax=Vector2.one;
        bg.offsetMin=bg.offsetMax=Vector2.zero;
        bg.gameObject.AddComponent<RawImage>().texture=Load("bg.jpg");

        float W=REF_W-PAD*2, y=10f;
        Place(Label(root,st.name,30,new Color32(0xF6,0xE9,0xCD,255),TextAnchor.MiddleLeft,FontStyle.Bold).rectTransform,PAD+2,y,110,40);
        Place(Label(root,st.tierName,11,new Color32(0x9A,0x8A,0x68,255)).rectTransform,PAD+88,y+2,90,16);
        Place(Label(root,"◼ "+Ruler(st.owner),11,new Color32(0xCF,0xC4,0xAB,255)).rectTransform,PAD+88,y+20,120,16);
        var tok=Panel(root,"Tok",Frame()); Place(tok.rectTransform,REF_W-PAD-52-8-46,y,52,46);
        Place(Label(tok.transform,st.令牌.ToString(),24,new Color32(0xF0,0xC3,0x4A,255),TextAnchor.MiddleCenter,FontStyle.Bold).rectTransform,0,2,52,28);
        Place(Label(tok.transform,"令牌",10,new Color32(0x9A,0x8A,0x68,255),TextAnchor.MiddleCenter).rectTransform,0,28,52,14);
        var bk=Panel(root,"Back",Frame()); Place(bk.rectTransform,REF_W-PAD-46,y,46,46);
        Place(Label(bk.transform,"地图",13,new Color32(0xE8,0xC0,0x6A,255),TextAnchor.MiddleCenter).rectTransform,0,0,46,46);
        y+=52;

        float panelH=145f;
        var dp=Panel(root,"Stats",Slice(Load("panel.png"),58,11));
        Place(dp.rectTransform,PAD,y,W,panelH);
        string[,] rows={{"君主",st.ruler},{"太守",st.governor},{"武将",st.武将.ToString()},
            {"人口",Wan(st.人口)},{"兵力",Wan(st.兵力)},{"治安",st.治安.ToString()},
            {"农业",st.农业.ToString()},{"商业",st.商业.ToString()},{"治水",st.治水.ToString()},
            {"黄金",st.黄金.ToString()},{"粮草",st.粮草.ToString()},{"规模",st.tierName}};
        float cw=(W-22)/3f, rh=(panelH-22)/4f;
        for(int i=0;i<12;i++){ var cell=Node("c"+i,dp.transform);
            Place(cell,11+(i%3)*cw,11+(i/3)*rh,cw,rh);
            Place(Label(cell,rows[i,0],11,new Color32(0x6B,0x52,0x30,255),TextAnchor.MiddleRight,FontStyle.Bold).rectTransform,0,0,cw*.42f,rh);
            Place(Label(cell,rows[i,1],15,new Color32(0x2E,0x21,0x10,255),TextAnchor.MiddleLeft,FontStyle.Bold).rectTransform,cw*.47f,0,cw*.53f,rh); }
        y+=panelH+8;

        float imgW=W, imgH=W*city.height/(float)city.width;
        picRT=Node("City",root); Place(picRT,PAD,y,imgW,imgH);
        var ri=picRT.gameObject.AddComponent<RawImage>(); ri.texture=city; ri.raycastTarget=true;
        f=imgW/meta.size[0];

        var hl=Node("HL",picRT); hl.anchorMin=Vector2.zero; hl.anchorMax=Vector2.one;
        hl.offsetMin=hl.offsetMax=Vector2.zero;
        hlImg=hl.gameObject.AddComponent<RawImage>(); hlImg.raycastTarget=false; hlImg.enabled=false;

        var plaque=Slice(Load("plaque.png"),264,9);
        foreach(var b in meta.buildings){
            float fs=11f, pw=fs*(b.name.Length+b.func.Length+1)*.82f+16, ph=fs*1.95f;
            var pl=Panel(picRT,"pl_"+b.key,plaque);
            Place(pl.rectTransform,(b.box[0]+b.box[2])/2f*f-pw/2,b.box[3]*f-ph*.62f,pw,ph);
            var nm=Label(pl.transform,b.name,(int)fs,Color.white,TextAnchor.MiddleLeft,FontStyle.Bold);
            Place(nm.rectTransform,8,0,pw,ph);
            var fn=Label(pl.transform,"·"+b.func,(int)(fs*.62f),Color.white,TextAnchor.MiddleRight);
            Place(fn.rectTransform,0,1,pw-8,ph);
            plaques[b.key]=(pl,nm,fn);
        }

        popBg=Panel(picRT,"Pop",Slice(Load("menu.png"),62,11));
        popRT=popBg.rectTransform;
        popName=Label(popBg.transform,"",16,new Color32(0xF7,0xEC,0xD4,255),TextAnchor.MiddleLeft,FontStyle.Bold);
        Place(popName.rectTransform,10,8,80,22);
        popFunc=Label(popBg.transform,"",10,new Color32(0xB9,0xA4,0x7A,255));
        Place(popFunc.rectTransform,58,10,70,20);
        popCmds=Node("Cmds",popBg.transform);
        Place((RectTransform)popCmds,0,0,150,200);
        btnSprite=Slice(Load("btn.png"),30,90,6,9);
        popBg.gameObject.SetActive(false);

        Select(null);
        Debug.Log("城内视图就绪：点建筑看效果，点空白取消");
    }
    Sprite btnSprite;

    void Update()
    {
        if(!Input.GetMouseButtonDown(0)) return;
        if(popBg.gameObject.activeSelf &&
           RectTransformUtility.RectangleContainsScreenPoint(popRT,Input.mousePosition,null)) return;
        if(!RectTransformUtility.ScreenPointToLocalPointInRectangle(picRT,Input.mousePosition,null,out var lp))
            { Select(null); return; }
        float ix=lp.x/f, iy=-lp.y/f;                      // 左上原点
        if(ix<0||iy<0||ix>=meta.size[0]||iy>=meta.size[1]){ Select(null); return; }
        Select(Pick((int)ix,(int)iy));
    }

    B Pick(int x,int y)
    {
        var p=mask.GetPixel(x, mask.height-1-y);          // 贴图 y 轴朝上
        var c=(Mathf.RoundToInt(p.r*255),Mathf.RoundToInt(p.g*255),Mathf.RoundToInt(p.b*255));
        foreach(var b in meta.buildings)
            if(b.mask[0]==c.Item1&&b.mask[1]==c.Item2&&b.mask[2]==c.Item3) return b;
        B best=null; long area=long.MaxValue;             // 院内空地兜底：落在包围盒里也算
        foreach(var b in meta.buildings){
            if(x<b.box[0]||x>b.box[2]||y<b.box[1]||y>b.box[3]) continue;
            long a=(long)(b.box[2]-b.box[0])*(b.box[3]-b.box[1]);
            if(a<area){ area=a; best=b; } }
        return best;
    }

    void Select(B b)
    {
        cur=b?.key;
        foreach(var kv in plaques){
            float a=(b==null||kv.Key==cur)?1f:.38f;
            kv.Value.plate.color=new Color(1,1,1,a);
            kv.Value.nm.color=new Color(.973f,.937f,.847f,a);
            kv.Value.fn.color=new Color(.827f,.706f,.471f,a);
        }
        if(b==null){ hlImg.enabled=false; popBg.gameObject.SetActive(false); return; }

        if(!hlCache.TryGetValue(b.key,out var tex)){ tex=BuildHighlight(b); hlCache[b.key]=tex; }
        hlImg.texture=tex; hlImg.enabled=true;

        popName.text=b.name; popFunc.text="· "+b.func;
        foreach(Transform t in popCmds) Destroy(t.gameObject);
        var cmds=CMDS.TryGetValue(b.func,out var cc)?cc:new string[0];
        float popW=150, popH=9+22+5+Mathf.Ceil(cmds.Length/2f)*32+5+4;
        for(int i=0;i<cmds.Length;i++){
            var bt=Panel(popCmds,"b"+i,btnSprite);
            float bw=cmds.Length==1?(popW-18):(popW-18-5)/2f;
            Place(bt.rectTransform,9+(i%2)*(bw+5),36+(i/2)*32,bw,28);
            var t=Label(bt.transform,cmds[i],13,new Color32(0xFD,0xF3,0xDA,255),TextAnchor.MiddleCenter,FontStyle.Bold);
            Place(t.rectTransform,0,0,bw,28);
            var o=t.gameObject.AddComponent<Outline>();
            o.effectColor=new Color32(0x2E,0x1C,0x04,255); o.effectDistance=new Vector2(1.2f,1.2f);
        }
        float imgW=picRT.sizeDelta.x, imgH=picRT.sizeDelta.y;
        float px=b.box[2]*f+9, py=(b.box[1]+b.box[3])/2f*f-popH/2;
        if(px+popW>imgW-4) px=b.box[0]*f-9-popW;
        Place(popRT,Mathf.Clamp(px,4,imgW-popW-4),Mathf.Clamp(py,4,imgH-popH-4),popW,popH);
        popBg.gameObject.SetActive(true);
    }

    /// 压暗四周 + 按蒙版挖洞 + 沿轮廓金边。只在包围盒附近逐像素算，其余直接铺暗色。
    Texture2D BuildHighlight(B b)
    {
        int w=mask.width,h=mask.height;
        var src=mask.GetPixels32();
        var outp=new Color32[w*h];
        var dim=new Color32(8,6,4,122); var gold=new Color32(255,236,170,255);
        for(int i=0;i<outp.Length;i++) outp[i]=dim;
        const int R=5;
        int x0=Mathf.Max(0,b.box[0]-R-2), y0=Mathf.Max(0,b.box[1]-R-2);
        int x1=Mathf.Min(w-1,b.box[2]+R+2), y1=Mathf.Min(h-1,b.box[3]+R+2);
        bool Sil(int x,int y){ int i=(h-1-y)*w+x;
            return src[i].r==b.mask[0]&&src[i].g==b.mask[1]&&src[i].b==b.mask[2]; }
        for(int y=y0;y<=y1;y++) for(int x=x0;x<=x1;x++){
            int i=(h-1-y)*w+x;
            if(Sil(x,y)){ outp[i]=new Color32(0,0,0,0); continue; }
            bool rim=false;
            for(int dy=-R;dy<=R&&!rim;dy++) for(int dx=-R;dx<=R;dx++){
                int nx=x+dx,ny=y+dy;
                if(nx<0||ny<0||nx>=w||ny>=h) continue;
                if(Sil(nx,ny)){ rim=true; break; } }
            if(rim) outp[i]=gold;
        }
        var t=new Texture2D(w,h,TextureFormat.RGBA32,false);
        t.SetPixels32(outp); t.Apply(); return t;
    }

    Sprite Frame(){ var t=new Texture2D(24,24,TextureFormat.RGBA32,false);
        for(int y=0;y<24;y++) for(int x=0;x<24;x++){
            bool e=x<2||y<2||x>21||y>21;
            t.SetPixel(x,y,e?new Color32(0x6B,0x55,0x2D,255):new Color32(0xC8,0xA4,0x5C,20)); }
        t.Apply();
        return Sprite.Create(t,new Rect(0,0,24,24),new Vector2(.5f,.5f),100,0,SpriteMeshType.FullRect,new Vector4(8,8,8,8)); }

    static string Wan(int v)=>v>=10000?(v/10000f).ToString("0.0")+"万":v.ToString();
    static string Ruler(string k)=>k switch{ "han"=>"汉室·献帝","caocao"=>"曹操","yuanshao"=>"袁绍",
        "sunjian"=>"孙坚","lvbu"=>"吕布","liuyan"=>"刘焉","liubiao"=>"刘表","player"=>"我军",_=>"无主" };

    [System.Serializable] public class B{ public string key,name,func; public int[] mask,box; }
    [System.Serializable] public class CityMeta{ public int[] size; public List<B> buildings; }
    [System.Serializable] public class Stats{ public string name,tierName,owner,ruler,governor;
        public int 治安,黄金,粮草,农业,商业,人口,治水,兵力,武将,令牌; }
}
