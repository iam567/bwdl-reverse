// 全局屏幕适配。所有界面都走这一份，新加的界面也一样。
//
// 基准 375×812（iPhone X 那一档的竖屏点数）。CanvasScaler 只能在宽和高之间
// 挑一边贴合，贴宽则矮屏幕的下半截被挤出去，贴高则窄屏幕的左右被切掉。
// 这里按屏幕的瘦长程度二选一：
//   屏幕比基准更瘦长 → 贴宽，多出来的高度算进 RefH；
//   屏幕比基准更矮胖 → 贴高，多出来的宽度算进 RefW。
// 两种情况下 RefW ≥ 375、RefH ≥ 812 同时成立，排版里的东西就不会被挤出屏幕。
// 代价是多出来的那一条是「白捡的空间」，界面按 RefW/RefH 摆，不要写死 375/812。
using UnityEngine;
using UnityEngine.UI;

public static class ScreenFit
{
    public const float BaseW = 375f, BaseH = 812f;

    /// 逻辑画布尺寸。界面一律按这两个数排版。
    public static float RefW { get; private set; } = BaseW;
    public static float RefH { get; private set; } = BaseH;

    static CanvasScaler scaler;
    static RectTransform safeRT;                 // 刘海 / 灵动岛 / 底部横条以内的那块，所有界面都放在它里面
    static int lastW, lastH; static Rect lastSafe;

    /// 建完画布调一次。之后每帧调 Changed() 看要不要重排。
    public static void Attach(CanvasScaler cs)
    {
        scaler = cs;
        scaler.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        scaler.referenceResolution = new Vector2(BaseW, BaseH);
        Apply();
    }

    /// 手机上要避开刘海 / 灵动岛 / 底部横条：按安全区算缩放——安全区宽高都不小于 375×812 个逻辑单位，
    /// 多出来的那一边照旧算进 RefW / RefH（桌面上安全区就是整个窗口，跟以前一样）。
    static void Apply()
    {
        lastW = Screen.width; lastH = Screen.height; lastSafe = Screen.safeArea;
        var sa = Screen.safeArea;
        if (sa.width < 1 || sa.height < 1) sa = new Rect(0, 0, Screen.width, Screen.height);
        float s = Mathf.Min(sa.width / BaseW, sa.height / BaseH);
        scaler.uiScaleMode = CanvasScaler.ScaleMode.ConstantPixelSize;
        scaler.scaleFactor = s;
        RefW = sa.width / s; RefH = sa.height / s;
        if (safeRT != null)
        {
            safeRT.anchorMin = new Vector2(sa.x / Screen.width, sa.y / Screen.height);
            safeRT.anchorMax = new Vector2((sa.x + sa.width) / Screen.width, (sa.y + sa.height) / Screen.height);
            safeRT.offsetMin = safeRT.offsetMax = Vector2.zero; safeRT.pivot = new Vector2(.5f, .5f);
        }
    }
    /// Game 建完画布后挂上安全区容器
    public static void AttachSafe(RectTransform safe) { safeRT = safe; Apply(); }

    /// 屏幕尺寸变了吗（桌面拖窗口、手机转屏）。变了就顺手把 RefW/RefH 更新掉。
    public static bool Changed()
    {
        if (scaler == null || (Screen.width == lastW && Screen.height == lastH && Screen.safeArea == lastSafe)) return false;
        Apply();
        return true;
    }
}
