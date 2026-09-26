// iOS 出包后改 Info.plist：声明不用非豁免加密（TestFlight / App Store 每次上传就不用手动答出口合规）；
// 多语言（v1.01）：主屏上的 App 名跟着系统语言走（各语言的 InfoPlist.strings），并声明支持的语言。
#if UNITY_IOS
using UnityEditor;
using UnityEditor.Callbacks;
using UnityEditor.iOS.Xcode;
using System.IO;

public static class IOSPostBuild
{
    /// lproj 目录名 → 主屏显示名
    static readonly (string lproj, string name)[] Names = {
        ("zh-Hans", "霸业江山"), ("zh-Hant", "霸業江山"), ("en", "Realm of Hegemony"), ("ja", "三国！やばいね"), ("ko", "패업강산"),
    };

    [PostProcessBuild]
    public static void OnPostprocessBuild(BuildTarget target, string path)
    {
        if (target != BuildTarget.iOS) return;
        string plistPath = Path.Combine(path, "Info.plist");
        var plist = new PlistDocument(); plist.ReadFromFile(plistPath);
        plist.root.SetBoolean("ITSAppUsesNonExemptEncryption", false);
        var langs = plist.root.CreateArray("CFBundleLocalizations");
        foreach (var n in Names) langs.AddString(n.lproj);
        plist.WriteToFile(plistPath);

        string projPath = PBXProject.GetPBXProjectPath(path);
        var proj = new PBXProject(); proj.ReadFromFile(projPath);
        string app = proj.GetUnityMainTargetGuid();
        foreach (var n in Names)
        {
            string dir = Path.Combine(path, n.lproj + ".lproj");
            Directory.CreateDirectory(dir);
            File.WriteAllText(Path.Combine(dir, "InfoPlist.strings"),
                "\"CFBundleDisplayName\" = \"" + n.name + "\";\n\"CFBundleName\" = \"" + n.name + "\";\n", new System.Text.UTF8Encoding(false));
            string rel = n.lproj + ".lproj";                       // 整个目录按「文件夹引用」拷进包（五个同名 InfoPlist.strings 不能平铺）
            if (!proj.ContainsFileByProjectPath(rel))
                proj.AddFileToBuild(app, proj.AddFolderReference(rel, rel, PBXSourceTree.Source));
        }
        proj.WriteToFile(projPath);
    }
}
#endif
