// 事件弹窗：原版屏 19（c.R() 3226 / e() 3242）。每个 aO 码配一张静图（lib120~136）——这里换成八帧小动画，
// 文案照原版。一次可能攒下好几条（月末一口气出旱灾、收粮……），排队一条一条弹，点「知道了」出下一条。
// 动图在 Art/eventcg/ev_<key>_f<0..7>.jpg（平台 eventcg.js，id eva_<key>）；帧图不在就只出文字。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using static UIKit;

public class EventView : SheetView
{
    public struct Ev { public string Key, Title, Text; public Officer Who; public System.Action After; }
    readonly Queue<Ev> q = new();
    public bool Busy => Root.gameObject.activeSelf || q.Count > 0;
    public System.Action OnIdle;                 // 队列清空、最后一条也点掉了

    public EventView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("events", parent, w, h, panelSp, btnSp)
    {
        OnDone = _ => Next();
        OnCancel = Next;
    }

    public static bool Auto;
    /// 敌方「战略中」遮罩开着的时候先攒着不弹，遮罩收了再一条条出（不然弹窗和遮罩叠在一起）
    bool hold;
    public bool Hold { get => hold; set { hold = value; if (!hold && !Root.gameObject.activeSelf && q.Count > 0) Next(); } }                     // 自检：不弹，直接当点过了，只记日志
    /// 内存自检：把 eventcg 里每一组动画都弹一次（不等点「知道了」）
    public void ShowAllForTest()
    {
        var keys = new System.Collections.Generic.HashSet<string>();
        foreach (var f in System.IO.Directory.GetFiles(System.IO.Path.Combine(UIKit.ArtDir, "eventcg"), "ev_*_f0.jpg"))
            keys.Add(System.IO.Path.GetFileName(f).Replace("ev_", "").Replace("_f0.jpg", ""));
        foreach (var k in keys) { Say("测", "测", false, System.IO.Path.Combine("eventcg", "ev_" + k)); }
        Root.gameObject.SetActive(false);
    }
    public void Push(string key, string title, string text, Officer who = null, System.Action after = null)
    {
        if (Auto) { Debug.Log("[事件] " + title + "：" + text.Replace("\n", " ")); after?.Invoke(); return; }
        q.Enqueue(new Ev { Key = key, Title = title, Text = text, Who = who, After = after });
        if (!Root.gameObject.activeSelf && !Hold) Next();
    }

    Ev cur; bool has;
    void Next()
    {
        if (has) { has = false; cur.After?.Invoke(); }
        if (q.Count == 0) { Root.gameObject.SetActive(false); OnIdle?.Invoke(); return; }
        cur = q.Dequeue(); has = true;
        Sfx.Play(cur.Key == "attack" ? "war_drum" : "gong_small");             // 事件弹窗一声小锣；敌军来犯是战鼓
        string stem = cur.Key == null ? null : Path.Combine("eventcg", "ev_" + cur.Key);
        Say(cur.Title, cur.Text, false, stem, cur.Who);
    }

    /// 敌方回合的「XX 战略中.....」：压一层半透明，中间一段议事动画和那行字，不接点击
    RectTransform council; UnityEngine.UI.Text councilText;
    public void Council(string text)
    {
        if (council == null)
        {
            council = Node("council", Root.parent);
            Place(council, 0, 0, refW, refH);
            var dim = council.gameObject.AddComponent<UnityEngine.UI.Image>();
            dim.color = new Color(0, 0, 0, .45f); dim.raycastTarget = true;
            float w = SheetW - 40, h = w * 384f / 512f;
            var img = Node("cg", council).gameObject.AddComponent<UnityEngine.UI.Image>();
            Place(img.rectTransform, (refW - w) / 2, refH / 2 - h / 2 - 30, w, h);
            img.raycastTarget = false;
            Hud.CgFrame(img.rectTransform);                                   // 动画外沿的细画框
            councilImg = img;
            for (int f = 0; f < 8; f++) councilFrames[f] = CouncilFrame(f);
            councilText = Label(council, "", 20, new Color32(0xF4,0xDC,0x9A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
            Place(councilText.rectTransform, 0, refH / 2 + h / 2 - 20, refW, 40);
            councilText.gameObject.AddComponent<UnityEngine.UI.Outline>().effectColor = new Color(0, 0, 0, .9f);
        }
        council.gameObject.SetActive(text != null);
        council.SetAsLastSibling();
        if (text != null) { councilText.text = text; councilT0 = Time.time; }
    }
    UnityEngine.UI.Image councilImg; readonly Sprite[] councilFrames = new Sprite[8]; float councilT0;
    static Sprite CouncilFrame(int f)
    {
        var t = TryLoad(Path.Combine("eventcg", "ev_council_f" + f + ".jpg"));
        return t == null ? null : Whole(t);
    }
    public void TickCouncil()
    {
        if (council == null || !council.gameObject.activeSelf || councilImg == null) return;
        var sp = councilFrames[(int)((Time.time - councilT0) / .14f) % 8];
        if (sp != null) councilImg.sprite = sp; else councilImg.color = new Color(0, 0, 0, 0);
    }
}
