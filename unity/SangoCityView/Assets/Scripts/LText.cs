// 会翻译的 Text：赋值时过 Loc.T()（多语言，见 Loc.cs）。Raw = true 的（玩家输入的名字、语言名）原样显示。
// 译文比原文长（英文尤甚）：单行、不换行的字放不下框宽时自动缩字号（最小到原字号的 60%），中文原文不动。
public class LText : UnityEngine.UI.Text
{
    public bool Raw;
    int baseSize, fitSize;                                 // 设计字号 / 上次缩成的字号（字号被外面改过就重新当设计字号）

    public override string text
    {
        get => base.text;
        set { base.text = Raw ? value : Loc.T(value); Fit(); }
    }

    protected override void OnEnable()                     // 搭界面时整棵树没激活，量不到框宽；显示出来再量一次
    {
        base.OnEnable();
        Fit();
    }

    protected override void OnRectTransformDimensionsChange()
    {
        base.OnRectTransformDimensionsChange();
        Fit();
    }

    public void Refit() => Fit();

    void Fit()
    {
        if (Loc.Source || font == null) return;
        if (fontSize != fitSize) baseSize = fitSize = fontSize;
        if (horizontalOverflow != UnityEngine.HorizontalWrapMode.Overflow) return;
        var s = base.text;
        if (string.IsNullOrEmpty(s) || s.IndexOf('\n') >= 0) return;
        float w = rectTransform.rect.width;
        if (w <= 1) return;
        if (fontSize != baseSize) fontSize = baseSize;
        float pw = preferredWidth;
        int to = pw <= w + 0.5f ? baseSize
               : UnityEngine.Mathf.Max(UnityEngine.Mathf.CeilToInt(baseSize * 0.6f), UnityEngine.Mathf.FloorToInt(baseSize * w / pw));
        fontSize = fitSize = to;
    }
}
