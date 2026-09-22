// 太守府四条指令的界面流程，照原版的步骤表走：
//   选武将（原版屏 7）→「将消耗金子 N」确认（屏 13）→ 执行结果（屏 10/11）→ 回城内
// 原版每一步都是整屏切换；竖屏手机上换成压在城内视图上的浮层，步骤一一对应。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class PoliticsView
{
    public RectTransform Root;
    public System.Action<string> OnDone;        // 走完一条指令（扣一次令），带一句结果
    public System.Action OnCancel;              // 半路作罢，不扣令

    readonly float refW, refH;
    readonly Sprite panel, btn;
    readonly System.Random rng = new System.Random();

    static readonly Dictionary<int, Sprite> avatars = new();
    static readonly Dictionary<string, Sprite> ui = new();

    /// 战斗层那套木牌素材这边也用得上，按 battleui.json 的 border 切九宫格
    static Sprite Ui(string key, float border)
    {
        if (ui.TryGetValue(key, out var sp)) return sp;
        var t = Load(Path.Combine("battle", "battleui", "ui_" + key + ".png"));
        return ui[key] = t == null ? null : Slice(t, border, border);
    }
    static Sprite Avatar(int id)
    {
        if (avatars.TryGetValue(id, out var sp)) return sp;
        var t = Load(Path.Combine("battle", "avatar", id + ".jpg"));
        return avatars[id] = t == null ? null : Whole(t);
    }

    Politics.Cmd cmd;
    MapData.Stat stat;
    List<Officer> roster;
    Officer picked;
    int gold;

    public PoliticsView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
    {
        refW = w; refH = h; panel = panelSp; btn = btnSp;
        Root = Node("politics", parent);
        Place(Root, 0, 0, w, h);
        Root.gameObject.SetActive(false);
    }

    void Clear() { foreach (Transform t in Root) Object.Destroy(t.gameObject); }

    RectTransform Sheet(string title, float h)
    {
        Clear();
        Root.gameObject.SetActive(true);
        var dim = Node("dim", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)dim.transform);
        dim.color = new Color(0, 0, 0, .55f); dim.raycastTarget = true;
        float w = refW - 40;
        var box = Node("box", Root);
        Place(box, 20, (refH - h) / 2, w, h);
        var bg = Panel(box, "bg", panel); Fill((RectTransform)bg.transform); bg.raycastTarget = true;
        var t = Label(box, title, 17, new Color32(0x2E,0x21,0x10,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(t.rectTransform, 0, 14, w, 24);
        return box;
    }

    Button Btn(RectTransform p, string s, float x, float y, float w, float h, UnityEngine.Events.UnityAction go)
    {
        var im = Node("b", p).gameObject.AddComponent<Image>();
        Place((RectTransform)im.transform, x, y, w, h);
        im.sprite = btn; im.type = Image.Type.Sliced; im.raycastTarget = true;
        var lb = Label(p, s, 14, new Color32(0xF8,0xEC,0xD4,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(lb.rectTransform, x, y, w, h);
        var b = im.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        b.onClick.AddListener(go);
        return b;
    }

    /// 入口。前置条件不过就只弹一句话，原版这时候不扣指令。
    public void Begin(Politics.Cmd c, MapData.Stat s, Dictionary<int, Officer> all)
    {
        cmd = c; stat = s;
        if (Politics.Blocked(c, s, out string why)) { Say(why, false); return; }
        roster = Politics.Roster(all, s.id);
        if (roster.Count == 0) { Say("城中无将可遣", false); return; }
        PickOfficer();
    }

    /// 原版屏 7：本城武将列表，一页 8 个。
    /// 这里改成头像卡片三列——竖屏上一行一条的长条堆起来又挤又难认，
    /// 而且这四条指令只看智力，卡片上直接把智力写在名字底下，不用来回翻情报。
    const float CARD_W = 99f, CARD_H = 134f, GAP = 8f;
    void PickOfficer()
    {
        // 一城最多 10 人。硬按三列排，10 个会剩最后一行孤零零一张卡。
        // 改成先定行数，再把人均摊到每行、每行各自居中：
        // 10 人 → 3/3/2/2，7 人 → 3/2/2，5 人 → 3/2，怎么排都不落单。
        const int COLS = 3;
        int n = roster.Count;
        int rowN = Mathf.Max(1, (n + COLS - 1) / COLS);
        int baseN = n / rowN, extra = n % rowN;        // 前 extra 行各多放一个
        float gridH = rowN * CARD_H + (rowN - 1) * GAP;
        float viewH = Mathf.Min(gridH, 4 * CARD_H + 3 * GAP);
        var box = Sheet(Politics.CmdName[(int)cmd] + "　遣何人前往", 56 + viewH + 50);
        float w = refW - 40;

        var dv = Node("dv", box).gameObject.AddComponent<Image>();      // 标题底下压一条纹样
        Place((RectTransform)dv.transform, (w - 150) / 2, 36, 150, 16);
        var dvs = Load(Path.Combine("battle", "battleui", "ui_divider.png"));
        if (dvs != null) { dv.sprite = Whole(dvs); dv.preserveAspect = true; }
        dv.raycastTarget = false;

        float gw = COLS * CARD_W + (COLS - 1) * GAP;
        var view = Node("view", box);
        Place(view, (w - gw) / 2, 54, gw, viewH);
        view.gameObject.AddComponent<RectMask2D>();
        var content = Node("content", view);
        Place(content, 0, 0, gw, gridH);
        if (gridH > viewH)
        {
            var sr = view.gameObject.AddComponent<ScrollRect>();
            sr.content = content; sr.horizontal = false;
            sr.movementType = ScrollRect.MovementType.Clamped; sr.scrollSensitivity = 26;
        }

        int at = 0;
        for (int r = 0; r < rowN; r++)
        {
            int cnt = baseN + (r < extra ? 1 : 0);
            float rowW = cnt * CARD_W + (cnt - 1) * GAP;
            float x0 = (gw - rowW) / 2f;                                   // 这一行自己居中
            for (int c = 0; c < cnt; c++, at++)
            {
                int idx = at;
                Card(content, roster[at], x0 + c * (CARD_W + GAP), r * (CARD_H + GAP),
                     () => { picked = roster[idx]; Confirm(); });
            }
        }
        Btn(box, "作罢", (w - 124) / 2, 56 + viewH + 8, 124, 34, Cancel);
    }

    /// 一张武将卡：木牌底 + 头像 + 姓名 + 智力
    void Card(RectTransform p, Officer o, float x, float y, UnityEngine.Events.UnityAction go)
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
        // 这四条指令只吃智力，所以卡片上只写智力，别的属性写上去反而误导
        var iq = Label(bg.transform, "智 " + o.Int, 13, new Color32(0xE8,0xC0,0x6A,255), TextAnchor.MiddleCenter);
        Place(iq.rectTransform, 0, pad + av + 20, CARD_W, 18);

        var b = bg.gameObject.AddComponent<Button>(); b.transition = Selectable.Transition.None;
        b.onClick.AddListener(go);
    }

    /// 原版屏 13："将消耗金子N"。金额是按武将智力算好的，玩家只能认或者不认。
    void Confirm()
    {
        gold = Politics.Cost(cmd, picked, stat.黄金, rng);
        var box = Sheet(Politics.CmdName[(int)cmd], 188);
        float w = refW - 40;
        Card(box, picked, (w - CARD_W) / 2, 40, () => { });
        var l2 = Label(box, "将消耗金子 " + gold, 16,
                       new Color32(0x7A,0x2E,0x10,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(l2.rectTransform, 0, 40 + CARD_H + 4, w, 24);
        Btn(box, "是", w / 2 - 92, 40 + CARD_H + 30, 84, 32, Execute);
        Btn(box, "否", w / 2 + 8, 40 + CARD_H + 30, 84, 32, Cancel);
    }

    void Execute()
    {
        var r = Politics.Run(cmd, stat, picked, gold, rng);
        string extra = cmd switch {
            Politics.Cmd.Reclaim  => "农业 +" + r.Gain,
            Politics.Cmd.Commerce => "工业 +" + r.Gain,
            Politics.Cmd.Water    => "防灾 +" + r.Gain,
            _                     => "人口 +" + r.Gain,
        };
        if (r.RuleUp) extra += "　统治 +1";
        Say(r.Msg + "\n" + extra + "　金子 −" + r.Gold, true);
    }

    void Cancel() { Root.gameObject.SetActive(false); Clear(); OnCancel?.Invoke(); }

    /// 原版屏 10/11：执行结果，按一下退回城内。done 为真才扣一次指令。
    void Say(string msg, bool done)
    {
        var box = Sheet(Politics.CmdName[(int)cmd], 150);
        float w = refW - 40;
        var l = Label(box, msg, 15, new Color32(0x2E,0x21,0x10,255), TextAnchor.UpperCenter);
        l.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(l.rectTransform, 16, 48, w - 32, 60);
        Btn(box, "知道了", (w - 110) / 2, 108, 110, 32, () => {
            Root.gameObject.SetActive(false); Clear();
            if (done) OnDone?.Invoke(msg); else OnCancel?.Invoke();
        });
    }
}
