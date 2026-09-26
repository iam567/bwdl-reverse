// 天下霸图：主菜单第五项（顶了原版「卸甲归田」的位置）。武将卡收集册，规则在 Album.cs。
// 上面是等级页签（全部 / GOD / SSR / SR / R / N，带已收集数），中间一页卡，下面翻页和返回。
// 没解锁的卡压暗、打问号，名字也写成「？？？」；点已解锁的卡看大图。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class AlbumView : SheetView
{
    public System.Action OnBack;
    string tab;                                  // null = 全部
    int page;
    readonly List<Texture2D> loaded = new();     // 这一页读进来的卡面，翻页时释放

    public AlbumView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("album", parent, w, h, panelSp, btnSp) { Root.gameObject.SetActive(false); }

    public void Open() { tab = null; page = 0; Draw(); }
    /// 截图用：点开第一张已解锁的卡
    public void DetailForTest() { var c = Album.Cards.Find(Album.IsUnlocked); if (c != null) Detail(c); }

    void Release() { foreach (var t in loaded) if (t != null) Object.Destroy(t); loaded.Clear(); }
    void Leave() { Release(); Clear(); Root.gameObject.SetActive(false); OnBack?.Invoke(); }

    static readonly Color32 Gold = new Color32(0xF0, 0xD0, 0x8A, 255), Pale = new Color32(0xF8, 0xEC, 0xD6, 255);

    void Draw()
    {
        Release(); Clear();
        Root.gameObject.SetActive(true);
        var bg = Node("bg", Root).gameObject.AddComponent<RawImage>();
        var tex = TitleView.Cover() ?? TryLoad("title.jpg") ?? Load("bg.jpg");   // 跟主界面同一张封面（缓存里的，别 Destroy）
        bg.texture = tex;
        float s = Mathf.Max(refW / tex.width, refH / tex.height);
        Place(bg.rectTransform, (refW - tex.width * s) / 2, (refH - tex.height * s) / 2, tex.width * s, tex.height * s);
        var shade = Node("shade", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)shade.transform); shade.color = new Color(0, 0, 0, .72f); shade.raycastTarget = true;

        // 标题 + 总进度
        var t1 = Label(Root, "天 下 霸 图", 30, Gold, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(t1.rectTransform, 0, 22, refW, 40);
        t1.gameObject.AddComponent<Outline>().effectColor = new Color(0, 0, 0, .85f);
        var t2 = Label(Root, "已收集 " + Album.Count() + " / " + Album.Total(), 14, Pale, TextAnchor.MiddleCenter);
        Place(t2.rectTransform, 0, 62, refW, 20);

        // 等级页签
        string[] tabs = { null, "GOD", "SSR", "SR", "R", "N" };
        float tw = Mathf.Min(64f, (refW - 24) / tabs.Length), tx = (refW - tw * tabs.Length) / 2, ty = 92;
        for (int i = 0; i < tabs.Length; i++)
        {
            string k = tabs[i];
            string lab = (k ?? "全部") + "\n" + Album.Count(k) + "/" + Album.Total(k);
            var b = Btn(Root, (k == tab ? "◆" : "") + lab, tx + i * tw + 2, ty, tw - 4, 44, () => { tab = k; page = 0; Draw(); }, 11);
            if (k != tab) b.image.color = new Color(.75f, .75f, .75f, 1);
        }

        // 卡格：按屏宽定列数、按剩下的高度定行数
        var list = tab == null ? Album.Cards : Album.Cards.FindAll(c => c.tier == tab);
        int cols = Mathf.Clamp((int)((refW - 24) / 118), 3, 8);
        float gap = 10, cw = Mathf.Min(150, (refW - 24 - gap * (cols - 1)) / cols), ch = cw * 1.5f, cellH = ch + 24;
        float top = ty + 56, bottom = refH - 16 - BTN_H - 12;
        int rows = Mathf.Max(1, (int)((bottom - top + gap) / (cellH + gap)));
        int per = cols * rows, pages = Mathf.Max(1, (list.Count + per - 1) / per);
        page = Mathf.Clamp(page, 0, pages - 1);
        float gx = (refW - (cw * cols + gap * (cols - 1))) / 2;
        for (int i = 0; i < per; i++)
        {
            int n = page * per + i;
            if (n >= list.Count) break;
            DrawCard(list[n], gx + (i % cols) * (cw + gap), top + (i / cols) * (cellH + gap), cw, ch);
        }

        // 翻页 + 返回
        float by = refH - 16 - BTN_H;
        if (page > 0) Btn(Root, "上一页", 12, by, 96, BTN_H, () => { page--; Draw(); }, 14);
        if (page < pages - 1) Btn(Root, "下一页", refW - 12 - 96, by, 96, BTN_H, () => { page++; Draw(); }, 14);
        Btn(Root, "返回", (refW - 110) / 2, by, 110, BTN_H, Leave);
        var pl = Label(Root, (page + 1) + " / " + pages, 12, Pale, TextAnchor.MiddleCenter);
        Place(pl.rectTransform, 0, by - 20, refW, 18);
    }

    void DrawCard(Album.Card c, float x, float y, float w, float h)
    {
        bool on = Album.IsUnlocked(c);
        var frame = Node("c_" + c.key, Root).gameObject.AddComponent<Image>();
        Place(frame.rectTransform, x - 3, y - 3, w + 6, h + 6);
        frame.sprite = BattleUi.PlateSp(); frame.type = Image.Type.Sliced;
        frame.color = on ? new Color(1.4f, 1.25f, .9f, 1) : new Color(.45f, .42f, .38f, 1);
        var img = Node("img", frame.transform).gameObject.AddComponent<Image>();
        Place(img.rectTransform, 3, 3, w, h); img.raycastTarget = false;
        var tex = TryLoadFresh(Path.Combine("battle", "card", c.art + ".jpg"));
        if (tex != null) { loaded.Add(tex); img.sprite = Whole(tex); }
        img.color = on ? Color.white : new Color(.07f, .07f, .09f, 1);               // 没解锁：压成一片暗影
        if (!on)
        {
            var q = Label(img.transform, "?", (int)(w * .45f), new Color(.55f, .5f, .42f, .9f), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(q.rectTransform, 0, 0, w, h);
        }
        Corner(frame.rectTransform, c.tier, w + 6, w * .44f, on);
        var nm = Label(Root, on ? c.name : "？？？", 13, on ? Pale : new Color32(0x90, 0x88, 0x78, 255), TextAnchor.MiddleCenter, FontStyle.Bold);   // 没解锁不露名字
        Place(nm.rectTransform, x - 4, y + h + 4, w + 8, 18);
        if (on)
        {
            var b = frame.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
            b.onClick.AddListener(() => Detail(c));
        }
    }

    /// 右上角的评级角标（徽章略微探出卡边）
    static void Corner(RectTransform card, string tier, float cardW, float size, bool on)
    {
        var sp = Album.Badge(tier); if (sp == null) return;
        var bi = Node("rank", card).gameObject.AddComponent<Image>();
        bi.sprite = sp; bi.preserveAspect = true; bi.raycastTarget = false;
        Place(bi.rectTransform, cardW - size * .78f, -size * .22f, size, size);
        if (!on) bi.color = new Color(.55f, .55f, .55f, .85f);
    }

    /// 点开大图：整张卡 + 角标 + 名字 + 出场难度，点哪都收
    void Detail(Album.Card c)
    {
        var dim = Node("detail", Root).gameObject.AddComponent<Image>();
        Fill(dim.rectTransform); dim.color = new Color(0, 0, 0, .82f); dim.raycastTarget = true;
        var db = dim.gameObject.AddComponent<Button>(); db.transition = Selectable.Transition.None;
        db.onClick.AddListener(() => Object.Destroy(dim.gameObject));
        float w = Mathf.Min(refW - 60, 300), h = w * 1.5f, x = (refW - w) / 2, y = Mathf.Max(40, (refH - h - 70) / 2);
        var frame = Node("f", dim.transform).gameObject.AddComponent<Image>();
        Place(frame.rectTransform, x - 5, y - 5, w + 10, h + 10);
        frame.sprite = BattleUi.PlateSp(); frame.type = Image.Type.Sliced; frame.color = new Color(1.4f, 1.25f, .9f, 1); frame.raycastTarget = false;
        var img = Node("img", frame.transform).gameObject.AddComponent<Image>();
        Place(img.rectTransform, 5, 5, w, h); img.raycastTarget = false;
        var tex = TryLoadFresh(Path.Combine("battle", "card", c.art + ".jpg"));
        if (tex != null) { loaded.Add(tex); img.sprite = Whole(tex); }
        Corner(frame.rectTransform, c.tier, w + 10, w * .36f, true);
        var nm = Label(dim.transform, c.name, 26, Gold, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(nm.rectTransform, 0, y + h + 14, refW, 34);
        nm.gameObject.AddComponent<Outline>().effectColor = new Color(0, 0, 0, .85f);
        var md = Label(dim.transform, c.tier + "　·　登场：" + string.Join(" / ", c.modes), 13, Pale, TextAnchor.MiddleCenter);
        Place(md.rectTransform, 0, y + h + 48, refW, 20);
    }

    /// 别处的整张武将卡（战场武将信息）也贴角标：card 是卡面那张 Image，cardW 是它的宽
    public static void BadgeOn(RectTransform card, Officer o, float cardW)
    {
        var c = Album.Of(o); if (c == null) return;
        Corner(card, c.tier, cardW, cardW * .36f, true);
    }
}
