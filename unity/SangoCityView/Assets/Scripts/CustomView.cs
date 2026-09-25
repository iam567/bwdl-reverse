// 自建武将的界面：原版主菜单「自建武将」子菜单（创建武将 / 删除武将，c.java 1440）+ 屏 27 创建页。
// 规则在 CustomOfficers.cs。原版加减点一次 1 点；触屏上另给 ±5 两个钮，省得点七十下。
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class CustomView : SheetView
{
    Dictionary<int, Officer> all;
    public CustomView(Transform parent, float w, float h, Sprite panelSp, Sprite btnSp)
        : base("custom", parent, w, h, panelSp, btnSp) { }

    public void Open(Dictionary<int, Officer> officers)
    {
        all = officers;
        var l = CustomOfficers.All();
        var box = Sheet("自建武将", 56 + 30 + 3 * (BTN_H + 12) + FOOT + 20);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        var t = Label(box, l.Count == 0 ? "尚未创建武将" : "已创建：" + string.Join("、", l.ConvertAll(r => r.name)), 14, Rust, TextAnchor.MiddleCenter);
        Place(t.rectTransform, 0, 56, w, 24);
        string[] names = { "创建武将", "删除武将", "返回" };
        for (int i = 0; i < 3; i++)
        {
            int k = i;
            Btn(box, names[i], (w - 200) / 2, 90 + i * (BTN_H + 12), 200, BTN_H, () => {
                if (k == 0) StartCreate();
                else if (k == 1) StartDelete();
                else Close();
            });
        }
    }

    // ── 创建：屏 27 ───────────────────────────────────────────────────
    int tpl, face, left; int[] st = new int[4], bas = new int[4]; string nm = ""; bool female;
    void StartCreate()
    {
        if (CustomOfficers.FreeId() == 0) { Say("自建武将", "自建武将数量已满!", false, null, null, () => Open(all)); return; }
        var box = Sheet("创建武将　选择类型", 56 + 3 * (BTN_H + 12) + BTN_H + 20 + FOOT);
        float w = box.sizeDelta.x;
        Divider(box, 36);
        string[] desc = { "文　武20 智80 体60 德60　可加 70 点", "武　武80 智20 体80 德50　可加 50 点", "随机　四项各 1~99　不可加点" };
        for (int i = 0; i < 3; i++)
        {
            int k = i;
            Btn(box, desc[i], (w - 300) / 2, 58 + i * (BTN_H + 12), 300, BTN_H, () => Template(k), 14);
        }
        Btn(box, "返回", (w - 136) / 2, 58 + 3 * (BTN_H + 12) + 8, 136, BTN_H, () => Open(all));
    }

    public void EditForTest(int k, bool fem) { all = all ?? new Dictionary<int, Officer>(); Template(k); if (fem) { female = true; face = CustomOfficers.Female[3]; DrawEdit(); } }

    void Template(int k)
    {
        tpl = k; nm = ""; female = false;
        var faces = CustomOfficers.Faces(female);
        face = faces[rng.Next(faces.Length)];
        if (k < 2) { bas = (int[])CustomOfficers.Base[k].Clone(); st = (int[])bas.Clone(); left = CustomOfficers.Pool[k]; }
        else Roll();
        DrawEdit();
    }

    /// ad() c.java 6893：四项各 1~99。原版只在进页时掷一次；新版给个骰子，想重掷就点
    void Roll() { for (int i = 0; i < 4; i++) st[i] = bas[i] = rng.Next(99) + 1; left = 0; }

    InputField nameField;
    void DrawEdit()
    {
        string[] lab = { "武力", "智力", "体力", "德行" };
        float rowH = 40;
        var box = Sheet("创建武将　" + CustomOfficers.TplName[tpl], 60 + 40 + 104 + 4 * rowH + 30 + 50 + BTN_H + FOOT);
        float w = box.sizeDelta.x;
        // 男 / 女：换一组头像
        Btn(box, (female ? "" : "◆") + "男", w / 2 - 84, 42, 76, 32, () => { if (female) { female = false; face = CustomOfficers.Male[0]; KeepName(); DrawEdit(); } }, 15);
        Btn(box, (female ? "◆" : "") + "女", w / 2 + 8, 42, 76, 32, () => { if (!female) { female = true; face = CustomOfficers.Female[0]; KeepName(); DrawEdit(); } }, 15);
        const float TOP = 40;
        // 头像 + 换一张
        var o = new Officer { Id = -face, Name = "" };
        var img = Node("face", box).gameObject.AddComponent<Image>();
        Place(img.rectTransform, (w - 92) / 2, 44 + TOP, 92, 92);
        var tex = TryLoad(System.IO.Path.Combine("battle", "avatar", face + ".jpg"));
        if (tex != null) img.sprite = Whole(tex); else img.color = new Color(.12f, .1f, .08f);
        var frm = Node("frm", box).gameObject.AddComponent<Image>();
        Place(frm.rectTransform, (w - 92) / 2 - 3, 41 + TOP, 98, 98); frm.sprite = Ui("portrait", 6); frm.type = Image.Type.Sliced; frm.raycastTarget = false;
        var faces = CustomOfficers.Faces(female);
        var idxL = Label(box, (System.Array.IndexOf(faces, face) + 1) + " / " + faces.Length, 12, Rust, TextAnchor.MiddleCenter);
        Place(idxL.rectTransform, 0, 138 + TOP, w, 16);
        Btn(box, "◀", (w - 92) / 2 - 56, 70 + TOP, 44, 40, () => { face = faces[(System.Array.IndexOf(faces, face) + faces.Length - 1) % faces.Length]; KeepName(); DrawEdit(); });
        Btn(box, "▶", (w + 92) / 2 + 12, 70 + TOP, 44, 40, () => { face = faces[(System.Array.IndexOf(faces, face) + 1) % faces.Length]; KeepName(); DrawEdit(); });
        float y = 160 + TOP;
        for (int i = 0; i < 4; i++)
        {
            int k = i;
            if (tpl < 2)
                Stepper(box, y + i * rowH, lab[i], st[i], new[] { -5, -1, 1, 5 }, d => {
                    int v = Mathf.Clamp(st[k] + d, bas[k], 99);
                    int spend = v - st[k];
                    if (spend > left) { v = st[k] + left; spend = left; }
                    st[k] = v; left -= spend; KeepName(); DrawEdit();
                });
            else
            {
                var l = Label(box, lab[i] + "  " + st[i], 16, Ink, TextAnchor.MiddleCenter, FontStyle.Bold);
                Place(l.rectTransform, 0, y + i * rowH, w, 32);
            }
        }
        y += 4 * rowH;
        var pts = Label(box, tpl < 2 ? "剩余点数 " + left : "随机生成，不可加点", 14, Rust, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(pts.rectTransform, 0, y, w, 24);
        if (tpl == 2)                                                          // 骰子：重掷四项
        {
            var dice = Hud.IconButton(box, "dice", w - 26 - 52, 160 + TOP + rowH, 52, 52, "icon_dice", "掷", out _);
            var db = dice.gameObject.AddComponent<Button>(); db.transition = Selectable.Transition.None;
            db.onClick.AddListener(() => { Roll(); KeepName(); DrawEdit(); });
            var dl = Label(box, "重掷", 12, Rust, TextAnchor.MiddleCenter);
            Place(dl.rectTransform, w - 26 - 52, 160 + TOP + rowH + 54, 52, 16);
        }
        y += 30;
        nameField = MakeInput(box, (w - 200) / 2, y, 200, 38, nm);
        y += 50;
        Btn(box, "确定", w / 2 - 104, y, 96, BTN_H, Confirm);
        Btn(box, "返回", w / 2 + 8, y, 96, BTN_H, StartCreate);
    }
    void KeepName() { if (nameField != null) nm = nameField.text; }

    InputField MakeInput(RectTransform p, float x, float y, float w, float h, string text)
    {
        var bg = Node("name", p).gameObject.AddComponent<Image>();
        Place(bg.rectTransform, x, y, w, h);
        bg.color = new Color(1f, .97f, .88f, .9f);
        var t = Label(bg.transform, "", 17, Ink, TextAnchor.MiddleCenter, FontStyle.Bold);
        Place(t.rectTransform, 6, 0, w - 12, h);
        t.supportRichText = false;
        var ph = Label(bg.transform, "点此输入武将名（4 字内）", 13, new Color32(0x9A,0x8A,0x68,255), TextAnchor.MiddleCenter);
        Place(ph.rectTransform, 6, 0, w - 12, h);
        var f = bg.gameObject.AddComponent<InputField>();
        f.textComponent = t; f.placeholder = ph; f.characterLimit = 4;       // 原版 TextField 限 4 字（c.java 421）
        f.text = text;
        return f;
    }

    void Confirm()
    {
        KeepName();
        string err = CustomOfficers.CheckName(nm, all);
        if (tpl < 2 && left > 0 && err == null) err = null;             // 原版不强制点数用完
        if (err != null) { Say("创建武将", err, false, null, null, DrawEdit); return; }
        int id = CustomOfficers.FreeId();
        var r = new CustomOfficers.Rec { id = id, name = nm.Trim(), might = st[0], iq = st[1], hp = st[2], virtue = st[3],
                                         kind = rng.Next(3) + 1, face = face };
        CustomOfficers.Add(r);
        all[id] = CustomOfficers.ToOfficer(r); BattleRules.Faces[id] = face;
        Say("创建武将", r.name + " 创建成功!", false, null, all[id], () => Open(all));
    }

    // ── 删除 ────────────────────────────────────────────────────────
    void StartDelete()
    {
        var l = CustomOfficers.All();
        if (l.Count == 0) { Say("自建武将", "尚未创建武将", false, null, null, () => Open(all)); return; }
        var offs = l.ConvertAll(r => { BattleRules.Faces[r.id] = r.face; return all.TryGetValue(r.id, out var o) ? o : CustomOfficers.ToOfficer(r); });
        PickMany("删除自建武将", offs, o => "武" + o.Might + " 智" + o.Int, 0, del => {
            foreach (var o in del) { CustomOfficers.Remove(o.Id); all.Remove(o.Id); }
            Say("自建武将", "删除成功!", false, null, null, () => Open(all));
        }, () => Open(all));
    }
}
