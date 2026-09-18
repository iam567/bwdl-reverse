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

    static void MakeScene()
    {
        Directory.CreateDirectory("Assets/Scenes");
        var s = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);

        var camGO = new GameObject("Main Camera", typeof(Camera));
        var cam = camGO.GetComponent<Camera>();
        cam.clearFlags = CameraClearFlags.SolidColor;
        cam.backgroundColor = new Color(.082f, .067f, .043f);
        camGO.tag = "MainCamera";

        new GameObject("EventSystem", typeof(EventSystem), typeof(StandaloneInputModule));

        var go = new GameObject("Game");
        var cv = go.AddComponent<Game>();
        cv.font = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/CJK.ttf");
        if (cv.font == null) Debug.LogError("字体没找到：Assets/Fonts/CJK.ttf");

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

    /// 不出包，直接把主地图（选中某城）渲一张 PNG，方便自查
    public static void RenderMap()
    {
        EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
        UIKit.UIFont = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/CJK.ttf");

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
        UIKit.UIFont = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/CJK.ttf");

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
        UIKit.UIFont = AssetDatabase.LoadAssetAtPath<Font>("Assets/Fonts/CJK.ttf");

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
}