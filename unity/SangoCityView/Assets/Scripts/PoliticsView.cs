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
    static Sprite Ui(string key, float border) => Ui(key, border, border, border, border);
    /// bx/by 是素材里那圈边的像素数，wx/wy 是希望它在屏幕上占多厚
    static Sprite Ui(string key, float bx, float by, float wx, float wy)
    {
        string k = key + "|" + wx;
        if (ui.TryGetValue(k, out var sp)) return sp;
        var t = Load(Path.Combine("battle", "battleui", "ui_" + key + ".png"));
        return ui[k] = t == null ? null : Slice(t, bx, by, wx, wy);
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

    const float SHEET_MAX_W = 420f;
    const float BTN_H = 40f, FOOT = 20f;    // 底边到按钮留 20，别贴着木框那圈铜边         // 横屏上 refW 会很大，弹框不封顶就拉成一条长木板

    RectTransform Sheet(string title, float h)
    {
        h = Mathf.Min(h, refH - 32);            // 再矮的屏也不许顶出画布
        Clear();
        Root.gameObject.SetActive(true);
        var dim = Node("dim", Root).gameObject.AddComponent<Image>();
        Fill((RectTransform)dim.transform);
        dim.color = new Color(0, 0, 0, .55f); dim.raycastTarget = true;
        float w = Mathf.Min(refW - 40, SHEET_MAX_W);
        var box = Node("box", Root);
        Place(box, (refW - w) / 2, (refH - h) / 2, w, h);
        var bg = Panel(box, "bg", panel); Fill((RectTransform)bg.transform); bg.raycastTarget = true;
        var t = Label(box, title, 17, new Color32(0x2E,0x21,0x10,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(t.rectTransform, 0, 14, w, 24);
        return box;
    }

    /// 城内那张 btn.png 是一条 1467×365 的长铜牌，压到八十来宽两头的花纹就撞一起了。
    /// 改用战斗层那张金边木牌（200×40，边 14/4），小尺寸下边框还是细的。
    Button Btn(RectTransform p, string s, float x, float y, float w, float h, UnityEngine.Events.UnityAction go)
    {
        var im = Node("b", p).gameObject.AddComponent<Image>();
        Place((RectTransform)im.transform, x, y, w, h);
        im.sprite = Ui("nameplate", 14, 4, 11, 3.5f) ?? btn;
        im.type = Image.Type.Sliced; im.raycastTarget = true;
        var lb = Label(p, s, 16, new Color32(0xF4,0xDC,0x9A,255), TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(lb.rectTransform, x, y - 1, w, h);
        var sh = lb.gameObject.AddComponent<Shadow>();
        sh.effectColor = new Color(0, 0, 0, .85f); sh.effectDistance = new Vector2(1, -1);
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

    /// 自检：直接跳到确认屏，好截图看排版
    public void ConfirmForTest(Politics.Cmd c, MapData.Stat st0, Dictionary<int, Officer> all,
                               int who, bool run = false)
    {
        cmd = c; stat = st0; roster = Politics.Roster(all, st0.id);
        if (roster.Count == 0) return;
        picked = roster[Mathf.Clamp(who, 0, roster.Count - 1)];
        if (run) Say("本城农业水平提高了!\n农业 +16　金子 −22", true);   // 只画，不改数值
        else Confirm();
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
        // 一城最多 10 人、四行装得下；屏幕再矮就按屏幕来，多出来的滚
        float room = refH - 32 - 56 - 50;
        float viewH = Mathf.Min(gridH, Mathf.Max(CARD_H, room));
        var box = Sheet(Politics.CmdName[(int)cmd] + "　遣何人前往", 56 + viewH + 14 + BTN_H + FOOT);
        float w = box.sizeDelta.x;

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
        Btn(box, "作罢", (w - 136) / 2, 56 + viewH + 14, 136, BTN_H, Cancel);
    }

    /// 只有头像和那圈金框，不带木牌底——确认屏和结果屏放在左边用
    void Face(RectTransform p, Officer o, float x, float y, float size)
    {
        var face = Node("face", p).gameObject.AddComponent<Image>();
        Place((RectTransform)face.transform, x, y, size, size);
        face.sprite = Avatar(o.Id); face.raycastTarget = false;
        if (face.sprite == null) face.color = new Color(.12f, .10f, .08f, 1);
        var frm = Node("frm", p).gameObject.AddComponent<Image>();
        Place((RectTransform)frm.transform, x - 3, y - 3, size + 6, size + 6);
        frm.sprite = Ui("portrait", 6); frm.type = Image.Type.Sliced; frm.raycastTarget = false;
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
        const float FACE = 92f, MARGIN = 24f;
        var box = Sheet(Politics.CmdName[(int)cmd], 150 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        Face(box, picked, MARGIN, 48, FACE);                   // 左边：办这件事的人
        float tx = MARGIN + FACE + 18, tw = w - tx - MARGIN;   // 右边：原来那两行字照留
        var l1 = Label(box, picked.Name + "　智 " + picked.Int, 17,
                       new Color32(0x2E,0x21,0x10,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(l1.rectTransform, tx, 62, tw, 26);
        var l2 = Label(box, "将消耗金子 " + gold, 17,
                       new Color32(0x7A,0x2E,0x10,255), TextAnchor.MiddleLeft, FontStyle.Bold);
        Place(l2.rectTransform, tx, 96, tw, 26);
        Btn(box, "是", w / 2 - 104, 150, 96, BTN_H, Execute);
        Btn(box, "否", w / 2 + 8, 150, 96, BTN_H, Cancel);
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

    void Cancel() { cgImg = null; cgFrames = null; Root.gameObject.SetActive(false); Clear(); OnCancel?.Invoke(); }

    /// 原版屏 10/11：执行结果，按一下退回城内。done 为真才扣一次指令。
    // ── 执行结果：上面一段八帧动画，下面那段文字 ──────────────────────
    // 帧图是 politicscg/zc_<指令>_f<0..7>.jpg，512×384，和计策动图一个规格。
    Image cgImg; Sprite[] cgFrames; float cgT0;
    const float CG_FRAME = 0.14f;                      // 和计策动图同一个节拍

    static readonly Dictionary<string, Sprite> cgCache = new();
    static Sprite CgFrame(int cmd, int f)
    {
        string k = cmd + "_" + f;
        if (cgCache.TryGetValue(k, out var sp)) return sp;
        var t = Load(Path.Combine("politicscg", "zc_" + cmd + "_f" + f + ".jpg"));
        return cgCache[k] = t == null ? null : Whole(t);
    }

    public void Tick()
    {
        if (cgImg == null || cgFrames == null) return;
        var sp = cgFrames[(int)((Time.time - cgT0) / CG_FRAME) % 8];
        if (sp != null && cgImg.sprite != sp) cgImg.sprite = sp;
    }

    void Say(string msg, bool done)
    {
        // panel.png 那圈绿铜边本身就占十来像素，动画还得再往里收一截，
        // 不然贴着边框看着像溢出来了。
        const float MARGIN = 26f;
        cgImg = null; cgFrames = null;
        bool withCg = done;
        if (withCg)
        {
            cgFrames = new Sprite[8]; int have = 0;
            for (int f = 0; f < 8; f++) { cgFrames[f] = CgFrame((int)cmd, f); if (cgFrames[f] != null) have++; }
            if (have == 0) { cgFrames = null; withCg = false; }
        }
        float w0 = Mathf.Min(refW - 40, SHEET_MAX_W);
        float ph = withCg ? (w0 - MARGIN * 2) * 384f / 512f : 0f;
        var box = Sheet(Politics.CmdName[(int)cmd],
                        (withCg ? 46 + ph + 68 : 106) + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        float ty = 48;
        if (withCg)
        {
            cgImg = Node("cg", box).gameObject.AddComponent<Image>();
            Place((RectTransform)cgImg.transform, MARGIN, 40, w - MARGIN * 2, ph);
            cgImg.sprite = cgFrames[0]; cgImg.raycastTarget = false;
            cgT0 = Time.time;
            ty = 40 + ph + 6;
        }
        var l = Label(box, msg, 15, new Color32(0x2E,0x21,0x10,255), TextAnchor.UpperCenter);
        l.horizontalOverflow = HorizontalWrapMode.Wrap;
        Place(l.rectTransform, MARGIN, ty, w - MARGIN * 2, 60);
        Btn(box, "知道了", (w - 136) / 2, withCg ? ty + 62 : 106, 136, BTN_H, () => {
            cgImg = null; cgFrames = null;
            Root.gameObject.SetActive(false); Clear();
            if (done) OnDone?.Invoke(msg); else OnCancel?.Invoke();
        });
    }
}
