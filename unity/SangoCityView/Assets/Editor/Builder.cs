// 建场景 + 出 macOS 独立程序。批处理跑，不用开编辑器。
using System.IO;
using UnityEditor;
using UnityEditor.Build.Reporting;
using UnityEditor.SceneManagement;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public static class Builder
{
    const string SCENE = "Assets/Scenes/City.unity";

    /// 顺序跟 FontChoice 对应：每套 [常规, 粗]，站酷小薇只有一个粗细
    public static readonly string[] FontFiles = {
        "NotoSerifSC-Regular.otf", "NotoSerifSC-Bold.otf",
        "NotoSansSC-Regular.otf", "NotoSansSC-Bold.otf",
        "LXGWWenKai-Regular.ttf", "LXGWWenKai-Medium.ttf",
        "ZCOOLXiaoWei-Regular.ttf",
        // 繁中 / 日 / 韩（v1.01 多语言）：各 [宋常规, 宋粗, 黑常规, 黑粗]
        "NotoSerifTC-Regular.otf", "NotoSerifTC-Bold.otf", "NotoSansTC-Regular.otf", "NotoSansTC-Bold.otf",
        "NotoSerifJP-Regular.otf", "NotoSerifJP-Bold.otf", "NotoSansJP-Regular.otf", "NotoSansJP-Bold.otf",
        "NotoSerifKR-Regular.otf", "NotoSerifKR-Bold.otf", "NotoSansKR-Regular.otf", "NotoSansKR-Bold.otf",
    };
    /// 站酷小薇缺 11 个汉字和一些符号：缺字时退到思源宋体（打进包里的后备字体）
    static void SetupFonts()
    {
        var wei = (TrueTypeFontImporter)AssetImporter.GetAtPath("Assets/Fonts/ZCOOLXiaoWei-Regular.ttf");
        var serif = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/NotoSerifSC-Regular.otf");
        if (wei != null && serif != null && (wei.fontReferences == null || wei.fontReferences.Length == 0))
        {
            wei.fontNames = new[] { "Noto Serif SC" };
            wei.fontReferences = new[] { serif };
            wei.SaveAndReimport();
        }
    }

    static void MakeScene()
    {
        Directory.CreateDirectory("Assets/Scenes");
        var s = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);

        var camGO = new GameObject("Main Camera", typeof(Camera), typeof(AudioListener));   // 没有 AudioListener 整个游戏没声音
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.082f, .067f, .043f);
        camGO.tag = "MainCamera";

        new GameObject("EventSystem", typeof(EventSystem), typeof(StandaloneInputModule));

        var go = new GameObject("Game");
        var cv = go.AddComponent<Game>();
        // 字体：四套开源中文字体（SIL OFL，GB2312 + 游戏用字裁过），设置里切。旧的 Arial Unicode MS 不能随 App 分发，已移出工程
        SetupFonts();
        cv.fonts = System.Array.ConvertAll(FontFiles, f => AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/" + f));
        cv.font = cv.fonts[0];
        // 背景音乐：mp3 进来按 Vorbis 压、流式读（三分多钟整首解码进内存不值）
        const string BGM = "Assets/Audio/bgm_map.mp3";
        if (AssetImporter.GetAtPath(BGM) is AudioImporter ai)
        {
            var st = ai.defaultSampleSettings;
            st.loadType = AudioClipLoadType.Streaming; st.compressionFormat = AudioCompressionFormat.Vorbis; st.quality = .6f;
            ai.defaultSampleSettings = st; ai.SaveAndReimport();
        }
        cv.bgmMap = AssetDatabase.LoadAssetAtPath<AudioClip>(BGM);
        // 战争（前奏 + 循环段，要按 DSP 时钟精确排程，所以整段压在内存里，不走流式）/ 结局
        AudioClip Clip(string p, AudioClipLoadType lt)
        {
            if (AssetImporter.GetAtPath(p) is AudioImporter imp)
            {
                var st2 = imp.defaultSampleSettings;
                if (st2.loadType != lt || st2.compressionFormat != AudioCompressionFormat.Vorbis)
                { st2.loadType = lt; st2.compressionFormat = AudioCompressionFormat.Vorbis; st2.quality = .6f; imp.defaultSampleSettings = st2; imp.SaveAndReimport(); }
            }
            var c = AssetDatabase.LoadAssetAtPath<AudioClip>(p);
            if (c == null) Debug.LogError("音乐没找到：" + p);
            return c;
        }
        cv.bgmWarIntro = Clip("Assets/Audio/war_intro.ogg", AudioClipLoadType.CompressedInMemory);
        cv.bgmWarLoop  = Clip("Assets/Audio/war_loop.ogg",  AudioClipLoadType.CompressedInMemory);
        cv.bgmWin  = new[] { Clip("Assets/Audio/end_win_1.ogg", AudioClipLoadType.Streaming),  Clip("Assets/Audio/end_win_2.ogg", AudioClipLoadType.Streaming) };
        cv.bgmLose = new[] { Clip("Assets/Audio/end_lose_1.ogg", AudioClipLoadType.Streaming), Clip("Assets/Audio/end_lose_2.ogg", AudioClipLoadType.Streaming) };
        // 音效：Assets/Audio/sfx/*.ogg，短音解码进内存（Sfx.cs 按文件名取）
        var sfx = new System.Collections.Generic.List<AudioClip>();
        foreach (var g in AssetDatabase.FindAssets("t:AudioClip", new[] { "Assets/Audio/sfx" }))
        {
            string p = AssetDatabase.GUIDToAssetPath(g);
            if (AssetImporter.GetAtPath(p) is AudioImporter si)
            {
                var ss = si.defaultSampleSettings;
                if (ss.loadType != AudioClipLoadType.DecompressOnLoad) { ss.loadType = AudioClipLoadType.DecompressOnLoad; si.defaultSampleSettings = ss; si.SaveAndReimport(); }
            }
            sfx.Add(AssetDatabase.LoadAssetAtPath<AudioClip>(p));
        }
        cv.sfx = sfx.ToArray();
        Debug.Log("音效 " + cv.sfx.Length + " 个");
        if (cv.bgmMap == null) Debug.LogError("背景音乐没找到：" + BGM);
        foreach (var f in cv.fonts) if (f == null) Debug.LogError("字体没找到");

        EditorSceneManager.SaveScene(s, SCENE);
    }

    public static void BuildMac()
    {
        MakeScene();
        PlayerSettings.companyName = "sango";
        PlayerSettings.productName = "SangoCityView";
        PlayerSettings.defaultScreenWidth = 500;          // 竖屏窗口，笔记本上放得下
        PlayerSettings.defaultScreenHeight = 1083;
        PlayerSettings.defaultIsNativeResolution = false;
        PlayerSettings.resizableWindow = true;
        PlayerSettings.fullScreenMode = FullScreenMode.Windowed;
        PlayerSettings.runInBackground = true;

        var opt = new BuildPlayerOptions {
            scenes = new[] { SCENE },
            locationPathName = "Build/SangoCityView.app",
            target = BuildTarget.StandaloneOSX,
            targetGroup = BuildTargetGroup.Standalone,
            options = BuildOptions.None,
        };
        var r = BuildPipeline.BuildPlayer(opt);
        Debug.Log("构建结果: " + r.summary.result + " 用时 " + r.summary.totalTime);
        EditorApplication.Exit(r.summary.result == BuildResult.Succeeded ? 0 : 3);
    }

    /// 出 iOS 的 Xcode 工程（Build/iOS），自动签名用玩家自己的开发者团队。
    /// 桌面名「霸业江山」；productName 只在这里改，BuildMac 会改回 SangoCityView（Mac 版存档位置靠它）。
    public const string IOS_TEAM = "N4SK575992", IOS_BUNDLE = "com.jingtaoliang.sgwzdl";
    public static void BuildIOS()
    {
        MakeScene();
        PlayerSettings.companyName = "sango";
        PlayerSettings.productName = "霸业江山";
        PlayerSettings.SetApplicationIdentifier(UnityEditor.Build.NamedBuildTarget.iOS, IOS_BUNDLE);
        PlayerSettings.bundleVersion = "1.02";                            // v1.01 多语言；v1.02 新手教程
        PlayerSettings.iOS.buildNumber = System.DateTime.Now.ToString("yyMMddHHmm");
        PlayerSettings.iOS.appleEnableAutomaticSigning = true;
        PlayerSettings.iOS.appleDeveloperTeamID = IOS_TEAM;
        PlayerSettings.iOS.targetOSVersionString = "15.0";
        PlayerSettings.defaultInterfaceOrientation = UIOrientation.Portrait;
        PlayerSettings.statusBarHidden = true;
        PlayerSettings.runInBackground = false;
        var icon = AssetDatabase.LoadAssetAtPath<Texture2D>("Assets/Icons/icon.png");
        if (icon != null) PlayerSettings.SetIcons(UnityEditor.Build.NamedBuildTarget.Unknown, new[] { icon }, IconKind.Any);

        var opt = new BuildPlayerOptions {
            scenes = new[] { SCENE },
            locationPathName = "Build/iOS",
            target = BuildTarget.iOS,
            targetGroup = BuildTargetGroup.iOS,
            options = BuildOptions.None,
        };
        var r = BuildPipeline.BuildPlayer(opt);
        Debug.Log("iOS 构建结果: " + r.summary.result + " 用时 " + r.summary.totalTime);
        EditorApplication.Exit(r.summary.result == BuildResult.Succeeded ? 0 : 3);
    }

    // ── Windows / 安卓 / 未签名 iPA ────────────────────────────────────

    public static void BuildWindows()
    {
        MakeScene();
        PlayerSettings.companyName = "sango";
        PlayerSettings.productName = "霸业江山";
        PlayerSettings.bundleVersion = "1.02";
        PlayerSettings.defaultScreenWidth = 500;          // 和 Mac 一样的竖屏窗口
        PlayerSettings.defaultScreenHeight = 1083;
        PlayerSettings.defaultIsNativeResolution = false;
        PlayerSettings.resizableWindow = true;
        PlayerSettings.fullScreenMode = FullScreenMode.Windowed;
        PlayerSettings.runInBackground = true;

        var opt = new BuildPlayerOptions {
            scenes = new[] { SCENE },
            locationPathName = "Build/Windows/霸业江山.exe",
            target = BuildTarget.StandaloneWindows64,
            targetGroup = BuildTargetGroup.Standalone,
            options = BuildOptions.None,
        };
        var r = BuildPipeline.BuildPlayer(opt);
        Debug.Log("Windows 构建结果: " + r.summary.result + " 用时 " + r.summary.totalTime);
        EditorApplication.Exit(r.summary.result == BuildResult.Succeeded ? 0 : 3);
    }

    /// 安卓上 StreamingAssets 压在 APK 里、普通文件接口读不到，运行时要解包到可写目录；
    /// 而 APK 里的文件又没法枚举，所以出包前先把文件名列成清单带进去（见 AssetUnpack）。
    public static void WriteManifest()
    {
        const string root = "Assets/StreamingAssets/Art";
        var sb = new System.Text.StringBuilder();
        int n = 0;
        foreach (var f in Directory.GetFiles(root, "*", SearchOption.AllDirectories))
        {
            var rel = f.Substring(root.Length + 1).Replace('\\', '/');
            if (rel.EndsWith(".meta") || rel == AssetUnpack.MANIFEST) continue;
            sb.Append(rel).Append('\n'); n++;
        }
        File.WriteAllText(Path.Combine(root, AssetUnpack.MANIFEST), sb.ToString());
        Debug.Log("素材清单: " + n + " 个文件");
    }

    public static void BuildAndroid()
    {
        WriteManifest();
        MakeScene();
        PlayerSettings.companyName = "sango";
        PlayerSettings.productName = "霸业江山";
        PlayerSettings.SetApplicationIdentifier(UnityEditor.Build.NamedBuildTarget.Android, IOS_BUNDLE);
        PlayerSettings.bundleVersion = "1.02";
        PlayerSettings.Android.bundleVersionCode = 102;
        PlayerSettings.Android.minSdkVersion = AndroidSdkVersions.AndroidApiLevel26;
        PlayerSettings.defaultInterfaceOrientation = UIOrientation.Portrait;
        PlayerSettings.statusBarHidden = true;
        PlayerSettings.runInBackground = false;
        // 用 Unity 自带的 debug keystore 签名：能直接装，但不能上 Google Play。
        // 以后要上架得换成自己的正式密钥，而且一旦用了就不能再换。
        PlayerSettings.Android.useCustomKeystore = false;
        EditorUserBuildSettings.buildAppBundle = false;         // 出 APK，不出 AAB
        var icon = AssetDatabase.LoadAssetAtPath<Texture2D>("Assets/Icons/icon.png");
        if (icon != null) PlayerSettings.SetIcons(UnityEditor.Build.NamedBuildTarget.Android, new[] { icon }, IconKind.Any);

        var opt = new BuildPlayerOptions {
            scenes = new[] { SCENE },
            locationPathName = "Build/Android/bayejiangshan.apk",
            target = BuildTarget.Android,
            targetGroup = BuildTargetGroup.Android,
            options = BuildOptions.None,
        };
        var r = BuildPipeline.BuildPlayer(opt);
        Debug.Log("安卓构建结果: " + r.summary.result + " 用时 " + r.summary.totalTime);
        EditorApplication.Exit(r.summary.result == BuildResult.Succeeded ? 0 : 3);
    }

    /// 不签名的 iOS 工程：谁要玩自己签。和 BuildIOS 的差别只在关掉自动签名。
    public static void BuildIOSUnsigned()
    {
        MakeScene();
        PlayerSettings.companyName = "sango";
        PlayerSettings.productName = "霸业江山";
        PlayerSettings.SetApplicationIdentifier(UnityEditor.Build.NamedBuildTarget.iOS, IOS_BUNDLE);
        PlayerSettings.bundleVersion = "1.02";
        PlayerSettings.iOS.buildNumber = System.DateTime.Now.ToString("yyMMddHHmm");
        PlayerSettings.iOS.appleEnableAutomaticSigning = false;
        PlayerSettings.iOS.appleDeveloperTeamID = "";
        PlayerSettings.iOS.targetOSVersionString = "15.0";
        PlayerSettings.defaultInterfaceOrientation = UIOrientation.Portrait;
        PlayerSettings.statusBarHidden = true;
        PlayerSettings.runInBackground = false;
        var icon = AssetDatabase.LoadAssetAtPath<Texture2D>("Assets/Icons/icon.png");
        if (icon != null) PlayerSettings.SetIcons(UnityEditor.Build.NamedBuildTarget.Unknown, new[] { icon }, IconKind.Any);

        var opt = new BuildPlayerOptions {
            scenes = new[] { SCENE },
            locationPathName = "Build/iOS",
            target = BuildTarget.iOS,
            targetGroup = BuildTargetGroup.iOS,
            options = BuildOptions.None,
        };
        var r = BuildPipeline.BuildPlayer(opt);
        Debug.Log("iOS(未签名) 构建结果: " + r.summary.result + " 用时 " + r.summary.totalTime);
        EditorApplication.Exit(r.summary.result == BuildResult.Succeeded ? 0 : 3);
    }

    /// 不出包，直接把主地图（选中某城）渲一张 PNG，方便自查
    public static void RenderMap()
    {
        EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
        UIKit.UIFont = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/NotoSerifSC-Regular.otf");

        var camGO = new GameObject("Cam", typeof(Camera));
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.082f, .067f, .043f);
        cam.orthographic = true;
        var rt = new RenderTexture(750, 1624, 24, RenderTextureFormat.ARGB32) { antiAliasing = 4 };
        cam.targetTexture = rt;

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>();
        canvas.renderMode = RenderMode.ScreenSpaceCamera;
        canvas.worldCamera = cam; canvas.planeDistance = 10;
        var sc = go.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(375, 812); sc.matchWidthOrHeight = 0;

        var data = JsonUtility.FromJson<MapData>(
            File.ReadAllText(Path.Combine(UIKit.ArtDir, "map.json")));
        var mv = new MapView((RectTransform)go.transform, data, 375, 812);
        mv.Select(data.cities.Find(c => c.name == "陈留"));

        Canvas.ForceUpdateCanvases();
        cam.Render();
        var shot = new Texture2D(rt.width, rt.height, TextureFormat.RGB24, false);
        RenderTexture.active = rt;
        shot.ReadPixels(new Rect(0, 0, rt.width, rt.height), 0, 0);
        shot.Apply(); RenderTexture.active = null;
        Directory.CreateDirectory("Render");
        File.WriteAllBytes("Render/mapview.png", shot.EncodeToPNG());
        Debug.Log("地图渲染完成 → Render/mapview.png");
        EditorApplication.Exit(0);
    }

    /// 用运行时那套 CityView 渲某座城，验证按城加载这条路通不通。
    /// 城名从 -cityKey 参数取，默认建业。
    public static void RenderCity()
    {
        string key = "jianye", bld = null;
        var argv = System.Environment.GetCommandLineArgs();
        for (int i = 0; i < argv.Length - 1; i++) {
            if (argv[i] == "-cityKey") key = argv[i + 1];
            if (argv[i] == "-bld")     bld = argv[i + 1];
        }

        EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
        UIKit.UIFont = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/NotoSerifSC-Regular.otf");

        var camGO = new GameObject("Cam", typeof(Camera));
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.082f, .067f, .043f);
        cam.orthographic = true;
        var rt = new RenderTexture(750, 1624, 24, RenderTextureFormat.ARGB32) { antiAliasing = 4 };
        cam.targetTexture = rt;

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>();
        canvas.renderMode = RenderMode.ScreenSpaceCamera;
        canvas.worldCamera = cam; canvas.planeDistance = 10;
        var sc = go.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(375, 812); sc.matchWidthOrHeight = 0;

        var data = JsonUtility.FromJson<MapData>(File.ReadAllText(Path.Combine(UIKit.ArtDir, "map.json")));
        var city = data.cities.Find(c => c.key == key);
        var cv = new CityView((RectTransform)go.transform, 375, 812);
        cv.Show(data.stats.Find(s => s.id == city.id), key);
        if (bld != null) cv.SelectByKey(bld);

        Canvas.ForceUpdateCanvases();
        cam.Render();
        var shot = new Texture2D(rt.width, rt.height, TextureFormat.RGB24, false);
        RenderTexture.active = rt;
        shot.ReadPixels(new Rect(0, 0, rt.width, rt.height), 0, 0);
        shot.Apply(); RenderTexture.active = null;
        Directory.CreateDirectory("Render");
        File.WriteAllBytes("Render/city_" + key + ".png", shot.EncodeToPNG());
        Debug.Log("渲染完成 → Render/city_" + key + ".png");
        EditorApplication.Exit(0);
    }

    /// 渲一帧行军画面：-from 陈留 -to 濮阳 -t 0.45
    public static void RenderMarch()
    {
        string from = "陈留", to = "濮阳"; float t = .45f;
        var argv = System.Environment.GetCommandLineArgs();
        for (int i = 0; i < argv.Length - 1; i++) {
            if (argv[i] == "-from") from = argv[i + 1];
            if (argv[i] == "-to")   to   = argv[i + 1];
            if (argv[i] == "-t")    float.TryParse(argv[i + 1], out t);
        }
        EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
        UIKit.UIFont = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/NotoSerifSC-Regular.otf");

        var camGO = new GameObject("Cam", typeof(Camera));
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.082f, .067f, .043f);
        cam.orthographic = true;
        var rt = new RenderTexture(750, 1624, 24, RenderTextureFormat.ARGB32) { antiAliasing = 4 };
        cam.targetTexture = rt;

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>();
        canvas.renderMode = RenderMode.ScreenSpaceCamera;
        canvas.worldCamera = cam; canvas.planeDistance = 10;
        var sc = go.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(375, 812); sc.matchWidthOrHeight = 0;

        var data = JsonUtility.FromJson<MapData>(File.ReadAllText(Path.Combine(UIKit.ArtDir, "map.json")));
        var mv = new MapView((RectTransform)go.transform, data, 375, 812);
        mv.DebugMarch(from, to, t);

        Canvas.ForceUpdateCanvases();
        cam.Render();
        var shot = new Texture2D(rt.width, rt.height, TextureFormat.RGB24, false);
        RenderTexture.active = rt;
        shot.ReadPixels(new Rect(0, 0, rt.width, rt.height), 0, 0);
        shot.Apply(); RenderTexture.active = null;
        Directory.CreateDirectory("Render");
        File.WriteAllBytes("Render/march.png", shot.EncodeToPNG());
        Debug.Log("行军渲染完成 → Render/march.png");
        EditorApplication.Exit(0);
    }

    // ── 战斗试玩版 ──────────────────────────────────────────────────
    const string BATTLE_SCENE = "Assets/Scenes/Battle.unity";

    static void MakeBattleScene()
    {
        Directory.CreateDirectory("Assets/Scenes");
        var s = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);

        var camGO = new GameObject("Main Camera", typeof(Camera), typeof(AudioListener));   // 没有 AudioListener 整个游戏没声音
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.04f, .04f, .05f);
        camGO.tag = "MainCamera";

        new GameObject("EventSystem", typeof(EventSystem), typeof(StandaloneInputModule));

        var go = new GameObject("BattleGame");
        var bg = go.AddComponent<BattleGame>();
        bg.font = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/NotoSerifSC-Regular.otf");
        if (bg.font == null) Debug.LogError("字体没找到：Assets/Fonts/NotoSerifSC-Regular.otf");

        EditorSceneManager.SaveScene(s, BATTLE_SCENE);
    }

    public static void BuildBattleMac()
    {
        MakeBattleScene();
        PlayerSettings.companyName = "sango";
        PlayerSettings.productName = "SangoBattle";
        PlayerSettings.defaultScreenWidth = 500;
        PlayerSettings.defaultScreenHeight = 1083;
        PlayerSettings.defaultIsNativeResolution = false;
        PlayerSettings.resizableWindow = true;
        PlayerSettings.fullScreenMode = FullScreenMode.Windowed;
        PlayerSettings.runInBackground = true;

        var opt = new BuildPlayerOptions {
            scenes = new[] { BATTLE_SCENE },
            locationPathName = "Build/SangoBattle.app",
            target = BuildTarget.StandaloneOSX,
            targetGroup = BuildTargetGroup.Standalone,
            options = BuildOptions.None,
        };
        var r = BuildPipeline.BuildPlayer(opt);
        Debug.Log("战斗试玩版构建: " + r.summary.result + " 用时 " + r.summary.totalTime);
        EditorApplication.Exit(r.summary.result == BuildResult.Succeeded ? 0 : 3);
    }

}