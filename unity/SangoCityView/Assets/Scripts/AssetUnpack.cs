// 安卓专用：把 StreamingAssets 里的素材解包到可写目录。
//
// 为什么非解不可：Mac / Windows / iOS 上 StreamingAssets 就是磁盘上一个普通文件夹，
// File.ReadAllBytes 直接能读；**安卓上它压在 APK 里**，路径是 jar:file://...!/assets，
// 普通文件接口一律读不到，只能走 UnityWebRequest。
// 全工程读素材的入口只有 UIKit.ArtDir 一处（别处都是经它拼路径），
// 所以解包完把 ArtDir 指过去就行，上层 14 个文件一行都不用改。
//
// 清单 Art/_manifest.txt 是出包时由 Builder.WriteManifest() 生成的：
// 安卓上没法枚举 APK 里的文件，必须事先把文件名列好带进包里。
// 依赖 com.unity.modules.unitywebrequest。Unity 6.6 会隐式带上内置模块，
// 旧一点的编辑器（比如 6000.4）要在 Packages/manifest.json 里显式加这一行，
// 不然这里的 UnityWebRequest 编译不过。音频模块同理。
using System.Collections;
using System.IO;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;

public static class AssetUnpack
{
    public const string MANIFEST = "_manifest.txt";
    /// 解包完会写一个标记文件，内容是清单的行数+总字节；对得上就跳过，不每次都解
    const string STAMP = "_unpacked.txt";

    public static IEnumerator Ensure()
    {
#if UNITY_ANDROID && !UNITY_EDITOR
        string src = Path.Combine(Application.streamingAssetsPath, "Art");
        string dst = Path.Combine(Application.persistentDataPath, "Art");

        // 1) 先把清单读出来
        string list = null;
        yield return Fetch(Path.Combine(src, MANIFEST), t => list = t);
        if (string.IsNullOrEmpty(list))
        {
            Debug.LogError("[解包] 读不到 " + MANIFEST + "，素材会加载失败");
            yield break;
        }
        var rel = list.Split('\n');
        int n = 0;
        for (int i = 0; i < rel.Length; i++) { rel[i] = rel[i].Trim(); if (rel[i].Length > 0) n++; }

        // 2) 解过了就跳过
        string stampPath = Path.Combine(dst, STAMP);
        if (File.Exists(stampPath) && File.ReadAllText(stampPath).Trim() == n.ToString())
        { UIKit.SetArtDir(dst); yield break; }

        // 3) 一个个搬。2797 个文件，第一次启动大概几十秒，所以要有进度
        var ui = Progress();
        int done = 0;
        foreach (var r in rel)
        {
            if (r.Length == 0) continue;
            string outPath = Path.Combine(dst, r);
            Directory.CreateDirectory(Path.GetDirectoryName(outPath));
            byte[] bytes = null;
            yield return FetchBytes(Path.Combine(src, r), b => bytes = b);
            if (bytes != null) File.WriteAllBytes(outPath, bytes);
            else Debug.LogWarning("[解包] 缺文件: " + r);
            done++;
            if (ui != null && done % 25 == 0)
            {
                ui.text = "首次启动，正在准备素材 " + done * 100 / n + "%";
                yield return null;
            }
        }
        File.WriteAllText(stampPath, n.ToString());
        if (ui != null) Object.Destroy(ui.canvas.gameObject);
        UIKit.SetArtDir(dst);
        Debug.Log("[解包] 完成 " + done + " / " + n + " → " + dst);
#else
        yield break;        // 别的平台 StreamingAssets 就是普通文件夹，什么都不用做
#endif
    }

#if UNITY_ANDROID && !UNITY_EDITOR
    static IEnumerator Fetch(string url, System.Action<string> ok)
    {
        using var w = UnityWebRequest.Get(url);
        yield return w.SendWebRequest();
        ok(w.result == UnityWebRequest.Result.Success ? w.downloadHandler.text : null);
    }
    static IEnumerator FetchBytes(string url, System.Action<byte[]> ok)
    {
        using var w = UnityWebRequest.Get(url);
        yield return w.SendWebRequest();
        ok(w.result == UnityWebRequest.Result.Success ? w.downloadHandler.data : null);
    }

    /// 解包时压在最上面的一行字。这时候 UIKit 的素材还读不到，所以纯代码画。
    static Text Progress()
    {
        var go = new GameObject("Unpack", typeof(Canvas), typeof(CanvasScaler));
        var c = go.GetComponent<Canvas>();
        c.renderMode = RenderMode.ScreenSpaceOverlay; c.sortingOrder = 32767;
        var bg = new GameObject("bg", typeof(Image));
        bg.transform.SetParent(go.transform, false);
        var bi = bg.GetComponent<Image>(); bi.color = new Color(.08f, .07f, .05f, 1);
        var brt = (RectTransform)bg.transform;
        brt.anchorMin = Vector2.zero; brt.anchorMax = Vector2.one;
        brt.offsetMin = brt.offsetMax = Vector2.zero;
        var tg = new GameObject("t", typeof(Text));
        tg.transform.SetParent(go.transform, false);
        var t = tg.GetComponent<Text>();
        t.font = UIKit.UIFont; t.fontSize = 28; t.alignment = TextAnchor.MiddleCenter;
        t.color = new Color32(0xF2, 0xE8, 0xD4, 255); t.text = "首次启动，正在准备素材";
        var trt = (RectTransform)tg.transform;
        trt.anchorMin = Vector2.zero; trt.anchorMax = Vector2.one;
        trt.offsetMin = trt.offsetMax = Vector2.zero;
        return t;
    }
#endif
}
