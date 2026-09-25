// 难度（家规，见 HOUSE_RULES）：主界面「游戏设置」里选，开新局时生效。难度就是版本，换法见 Scenario.cs。
//   初级 = 3.2.2 原版（我们一直用的那套）  中级 = 3.2.5 原版  困难 = 3.1 原版
using System.IO;
using UnityEngine;

public static class Difficulty
{
    public static readonly string[] Names = { "初级", "中级", "困难" };
    static readonly string[] File_ = { null, "mid.csv", "hard.csv" };
    const string KEY = "difficulty";

    public static int Level
    {
        get { try { return Mathf.Clamp(PlayerPrefs.GetInt(KEY, 0), 0, 2); } catch { return 0; } }
        set { PlayerPrefs.SetInt(KEY, Mathf.Clamp(value, 0, 2)); PlayerPrefs.Save(); }
    }
}
