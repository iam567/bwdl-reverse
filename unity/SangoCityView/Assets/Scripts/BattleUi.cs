// 战斗界面的底图。阵型战和单挑两屏共用，所以单独拎出来，
// 不放在 ClashView 里让 DuelView 去够。
using System.Collections.Generic;
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public static class BattleUi
{
    // battleui.txt 是 build_battleui.py 量出来的：铜边多厚、头像框中间那个洞在哪。
    // 这些数不在 C# 里另抄一份——重出一次图，两边就对不上了。
    static Dictionary<string, int[]> metric;
    public static int[] Metric(string k)
    {
        if (metric == null)
        {
            metric = new Dictionary<string, int[]>();
            var f = Path.Combine(ArtDir, "battle", "battleui", "battleui.txt");
            if (File.Exists(f))
                foreach (var line in File.ReadAllLines(f))
                {
                    var a = line.Split(' ');
                    if (a.Length < 2) continue;
                    var v = new int[a.Length - 1];
                    for (int i = 1; i < a.Length; i++) int.TryParse(a[i], out v[i - 1]);
                    metric[a[0]] = v;
                }
        }
        return metric.TryGetValue(k, out var r) ? r : null;
    }

    static readonly Dictionary<string, Sprite> cache = new Dictionary<string, Sprite>();
    /// 九宫格底图。没做出来的话回落到 UIKit 里那个程序画的框，界面不至于开天窗。
    public static Sprite Get(string k)
    {
        if (cache.TryGetValue(k, out var sp)) return sp;
        var rel = Path.Combine("battle", "battleui", "ui_" + k + ".png");
        if (!File.Exists(Path.Combine(ArtDir, rel))) return cache[k] = null;
        var t = Load(rel); t.filterMode = FilterMode.Point;
        int b = Metric(k) is int[] m && m.Length > 0 ? m[0] : 0;
        sp = Sprite.Create(t, new Rect(0, 0, t.width, t.height), new Vector2(.5f, .5f), 100, 0,
                           SpriteMeshType.FullRect, new Vector4(b, b, b, b));
        return cache[k] = sp;
    }

    public static Sprite PlateSp() => Get("plate") ?? Plate();
    public static Sprite BtnSp()   => Get("btn")   ?? Frame();
}
