// 音效（新版加的）：界面点击、弹窗、成交、开战、计策、交锋、单挑、胜负、解锁。
// 来源：Kenney 的 CC0 音效包（coin / page / open / clash / slash / hit），其余是 tools/synth_sfx.py 程序合成的（木鱼、铃、锣、战鼓、箭、火、水、落石…）。
// 音量四档存 PlayerPrefs：关 / 低 / 中（默认）/ 高。同一个音 40ms 内不叠放（阵型战一拍好几下）。
using System.Collections.Generic;
using UnityEngine;

public static class Sfx
{
    public static readonly string[] Levels = { "关", "低", "中", "高" };
    static readonly float[] Vol = { 0f, .35f, .65f, 1f };
    const string KEY = "sfx";
    static AudioSource src;
    static readonly Dictionary<string, AudioClip> clips = new();
    static readonly Dictionary<string, float> last = new();

    public static int Level
    {
        get { try { return Mathf.Clamp(PlayerPrefs.GetInt(KEY, 2), 0, 3); } catch { return 2; } }
        set { PlayerPrefs.SetInt(KEY, Mathf.Clamp(value, 0, 3)); PlayerPrefs.Save(); }
    }

    public static void Init(GameObject host, AudioClip[] all)
    {
        if (src != null || all == null) return;
        src = host.AddComponent<AudioSource>(); src.playOnAwake = false; src.spatialBlend = 0f;
        foreach (var c in all) if (c != null) clips[c.name] = c;
        Debug.Log("[音效] 载入 " + clips.Count + " 个，音量档 " + Levels[Level]);
    }

    public static void Play(string key, float gain = 1f)
    {
        if (src == null || Level == 0 || key == null || !clips.TryGetValue(key, out var c)) return;
        float now = Time.unscaledTime;
        if (last.TryGetValue(key, out var t) && now - t < .04f) return;
        last[key] = now;
        src.PlayOneShot(c, Vol[Level] * gain);
    }

    /// 计策：火类 / 水类 / 落石各有专门的声，其余用通用的
    public static void Plot(int plot, bool ok)
    {
        if (!ok) { Play("plot_fail"); return; }
        string n = plot >= 0 && plot < BattleRules.PlotName.Length ? BattleRules.PlotName[plot] : "";
        if (n.Contains("火")) Play("plot_fire");
        else if (n.Contains("水")) Play("plot_water");
        else if (n.Contains("石")) Play("plot_rock");
        else if (n.Contains("箭") || n.Contains("弩")) Play("arrow");
        else Play("plot_magic");
    }
}
