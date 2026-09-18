// 行军部队：沿抽好的土路折线走，按行进方向换朝向，队伍上方挂本方势力旗。
// 8 个朝向只有 5 张图——正东/东南/东北水平镜像就是正西/西南/西北。
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class MarchUnit
{
    public bool Running { get; private set; }
    public Vector2 Pos { get; private set; }   // 1024 坐标，镜头跟随用
    public System.Action OnArrive;

    readonly RectTransform root, body, flag;
    readonly Image bodyImg, flagImg;
    readonly Dictionary<string, Sprite> sprites = new();
    readonly Dictionary<string, Sprite> flags = new();

    List<Vector2> path;          // 1024 坐标
    float travelled, total;
    float speed = 46f;           // 1024 单位 / 秒
    string curDir = "";

    public MarchUnit(RectTransform mapRoot)
    {
        foreach (var d in new[] { "s", "se", "e", "ne", "n" })
            sprites[d] = Whole(Load("troop/" + d + ".png"));

        root = Node("March", mapRoot);
        root.anchorMin = root.anchorMax = new Vector2(0, 1);
        root.pivot = new Vector2(.5f, 1f);          // 轴心放脚下中点，好贴地
        body = Node("Body", root);
        body.anchorMin = body.anchorMax = new Vector2(.5f, 1f);
        body.pivot = new Vector2(.5f, 1f);
        bodyImg = body.gameObject.AddComponent<Image>();
        bodyImg.raycastTarget = false;
        flag = Node("Flag", root);
        flag.anchorMin = flag.anchorMax = new Vector2(.5f, 1f);
        flag.pivot = new Vector2(.5f, 1f);
        flagImg = flag.gameObject.AddComponent<Image>();
        flagImg.raycastTarget = false;
        root.gameObject.SetActive(false);
    }

    public void SetFlag(string key, MapData.Flag meta)
    {
        if (!flags.ContainsKey(key)) flags[key] = Whole(Load("flags/" + key + ".png"));
        flagImg.sprite = flags[key];
        flagMeta = meta;
    }
    MapData.Flag flagMeta;

    public void Begin(List<Vector2> pts)
    {
        path = pts; travelled = 0; total = 0;
        for (int i = 1; i < pts.Count; i++) total += Vector2.Distance(pts[i-1], pts[i]);
        Running = true;
        root.gameObject.SetActive(true);
        Step(0);
    }

    /// 沿折线按累计长度取点，顺便给出该处的切线方向
    (Vector2 pos, Vector2 dir) Sample(float s)
    {
        s = Mathf.Clamp(s, 0, total);
        float acc = 0;
        for (int i = 1; i < path.Count; i++)
        {
            float seg = Vector2.Distance(path[i-1], path[i]);
            if (acc + seg >= s || i == path.Count - 1)
            {
                float t = seg < 1e-4f ? 0 : (s - acc) / seg;
                return (Vector2.Lerp(path[i-1], path[i], t), (path[i] - path[i-1]).normalized);
            }
            acc += seg;
        }
        return (path[^1], Vector2.right);
    }

    /// 屏幕坐标下 y 轴朝下：北是 dy<0。八个扇区里有三个靠镜像得到。
    static (string key, bool flip) DirOf(Vector2 d)
    {
        float a = Mathf.Atan2(d.y, d.x) * Mathf.Rad2Deg;   // -180..180
        int oct = Mathf.RoundToInt(a / 45f);               // -4..4
        switch (((oct % 8) + 8) % 8)
        {
            case 0: return ("e",  false);   // 东
            case 1: return ("se", false);   // 东南
            case 2: return ("s",  false);   // 南
            case 3: return ("se", true);    // 西南 ← 东南镜像
            case 4: return ("e",  true);    // 西   ← 东镜像
            case 5: return ("ne", true);    // 西北 ← 东北镜像
            case 6: return ("n",  false);   // 北
            default: return ("ne", false);  // 东北
        }
    }

    public void Step(float dt)
    {
        if (!Running) return;
        travelled += speed * dt;
        var (pos, dir) = Sample(travelled);
        var (key, flip) = DirOf(dir);
        if (key + flip != curDir)
        {
            curDir = key + flip;
            bodyImg.sprite = sprites[key];
            body.localScale = new Vector3(flip ? -1 : 1, 1, 1);
        }
        Pos = pos;
        root.anchoredPosition = new Vector2(pos.x, -pos.y);
        if (travelled >= total) { Running = false; root.gameObject.SetActive(false); OnArrive?.Invoke(); }
    }

    /// 队伍大小跟着缩放走，和旗子一样留个屏幕下限，缩远了也看得见。
    /// 按**最长边**归一而不是按高度：正东那张是长条侧面，
    /// 按高度缩的话宽度会顶到 4 倍，比整座城还长。
    public void Layout(float zoom)
    {
        const float MIN_PX = 20f, UNITS = 23f;      // 比第一版小一半
        float span = Mathf.Max(MIN_PX / zoom, UNITS);
        var sp = bodyImg.sprite;
        float ar = sp == null ? 1f : sp.rect.width / sp.rect.height;
        float w = ar >= 1f ? span : span * ar;
        float h = ar >= 1f ? span / ar : span;
        body.sizeDelta = new Vector2(w, h);
        body.anchoredPosition = Vector2.zero;
        if (flagMeta != null)
        {
            /* 旗只做标识，不能盖住队伍：按最长边的三成给，
               再把「旗杆着地点」对到队伍中后部，让旗面从队伍上方飘出去。
               flag 的轴心是顶部中点，所以要从目标点反推 anchoredPosition。 */
            float fh = span * .30f, fw = fh * flagMeta.w / flagMeta.h;
            flag.sizeDelta = new Vector2(fw, fh);
            float tx = -w * .18f, ty = -h * .52f;            // 旗杆着地点（相对队伍顶部中点）
            flag.anchoredPosition = new Vector2(tx + fw * (.5f - flagMeta.ax), ty + fh * flagMeta.ay);
        }
    }
}
