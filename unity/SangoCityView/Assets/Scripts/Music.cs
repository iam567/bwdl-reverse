// 背景音乐（新版加的，原版 J2ME 没接音乐）：换曲目时淡出淡入。
//   古地图卷（Suno）：主界面 / 大地图 / 城内共用。
//   铁骑冲锋（Suno）：打仗。前奏（0~8.79s）只放一次，接循环段（8.79s~103.73s，正好 57 小节）无限循环；
//     循环段最后 1.5 秒已经跟「A 点之前那 1.5 秒」等功率交叉淡化过，跳回 A 听不出接缝（tools/loop_find.py、loop_make.py）。
//     前奏→循环用 PlayScheduled 按 DSP 时钟精确接上，不留缝。
//   天下一统 / 势力灭亡：Kevin MacLeod（incompetech.com，CC BY 4.0，发布时要署名）各两首随机放。
// 音量四档存 PlayerPrefs：关 / 低 / 中（默认）/ 高。
using UnityEngine;

public static class Music
{
    public static readonly string[] Levels = { "关", "低", "中", "高" };
    static readonly float[] Vol = { 0f, .3f, .55f, .85f };
    const string KEY = "music";
    static AudioSource src, intro;                 // src 放循环段 / 整首；intro 只放前奏
    static AudioClip want, wantIntro; static bool pending; static float fade = 1f;

    public static int Level
    {
        get { try { return Mathf.Clamp(PlayerPrefs.GetInt(KEY, 2), 0, 3); } catch { return 2; } }
        set { PlayerPrefs.SetInt(KEY, Mathf.Clamp(value, 0, 3)); PlayerPrefs.Save(); }
    }

    public static void Init(GameObject host)
    {
        if (src != null) return;
        src = host.AddComponent<AudioSource>(); intro = host.AddComponent<AudioSource>();
        foreach (var s in new[] { src, intro }) { s.playOnAwake = false; s.spatialBlend = 0f; s.volume = 0f; }
        src.loop = true; intro.loop = false;
    }

    /// 换曲：同一首就不动；不同的先淡出再换上淡入。introClip 给了就先放它一遍再接 clip 循环
    public static void Play(AudioClip clip, AudioClip introClip = null)
    {
        if (src == null || clip == null) return;
        if (src.clip == clip && (src.isPlaying || intro.isPlaying)) { pending = false; return; }
        want = clip; wantIntro = introClip; pending = true;
        if (src.clip == null || (!src.isPlaying && !intro.isPlaying)) { Start(); fade = 0f; }
    }
    static void Start()
    {
        pending = false; src.Stop(); intro.Stop();
        src.clip = want;
        if (wantIntro != null)
        {
            intro.clip = wantIntro;
            double t0 = AudioSettings.dspTime + 0.1;
            intro.PlayScheduled(t0);
            src.PlayScheduled(t0 + (double)wantIntro.samples / wantIntro.frequency);   // 前奏一完，循环段严丝合缝接上
        }
        else src.Play();
    }

    /// Game.Update 每帧调：处理淡入淡出、跟着设置调音量
    public static void Tick()
    {
        if (src == null) return;
        if (pending)
        {
            fade = Mathf.MoveTowards(fade, 0f, Time.unscaledDeltaTime / .8f);
            if (fade <= 0f) Start();
        }
        else fade = Mathf.MoveTowards(fade, 1f, Time.unscaledDeltaTime / 1.2f);
        src.volume = intro.volume = Vol[Level] * fade;
    }
}
