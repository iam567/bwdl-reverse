// 建场景 + 出 macOS 独立程序。批处理跑，不用开编辑器。
using System.IO;
using UnityEditor;
using UnityEditor.Build.Reporting;
using UnityEditor.SceneManagement;
using UnityEngine;
using UnityEngine.EventSystems;

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
}
