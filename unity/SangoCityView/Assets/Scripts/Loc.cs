// 多语言（v1.01）：简中（源文字）/ 繁中 / 英 / 日 / 韩。
// 做法：**显示时翻译**。所有文字都走 LText（UIKit.Label 建的就是它），赋值那一刻过 Loc.T()：
//   1. 整句查表；
//   2. 查不到：把句子里的专有名词（武将、城、兵器、计策…）换成 {0}{1}…、数字换成 {#0}{#1}…，按「句式」查表再填回；
//   3. 还查不到：按空白 / 标点切段，逐段查（段里也先抽名词），标点换成该语言的写法。
// 代码里的中文字面量原样不动（很多同时是程序里比对用的标识，改源头容易坏逻辑）。
// 表在 Art/lang/<code>/phrases.tsv（句子 / 句式）和 names.tsv（专有名词），一行「原文\t译文」。
// 繁中不走句式表：简繁是逐字 / 逐词对应的，直接整句转（chars.tsv 单字 + phrases/names 里的词，最长匹配优先），动态拼出来的句子也不会漏。
// -lang <code> 临时指定；-locdump <文件> 把查不到的句式记下来（补表用）。
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using UnityEngine;

public static class Loc
{
    public static readonly string[] Codes = { "zh-Hans", "zh-Hant", "en", "ja", "ko" };
    public static readonly string[] Names = { "简体中文", "繁體中文", "English", "日本語", "한국어" };
    const string KEY = "lang";

    static int level = -1;
    public static int Level
    {
        get
        {
            if (level >= 0) return level;
            var av = System.Environment.GetCommandLineArgs(); int i = System.Array.IndexOf(av, "-lang");
            if (i >= 0) { level = Mathf.Max(0, System.Array.IndexOf(Codes, av[i + 1])); return level; }
            int saved = -1;
            try { saved = PlayerPrefs.GetInt(KEY, -1); } catch { }
            level = saved >= 0 && saved < Codes.Length ? saved : FromSystem();
            return level;
        }
        set { level = Mathf.Clamp(value, 0, Codes.Length - 1); PlayerPrefs.SetInt(KEY, level); PlayerPrefs.Save(); Load(); FontChoice.Apply(); }
    }
    public static string Code => Codes[Level];
    public static bool Source => Level == 0;
    /// 按字断行（中 / 日）；英、韩按词断行
    public static bool CjkWrap => Level == 0 || Level == 1 || Level == 3;

    /// 第一次打开：跟系统语言。繁中按系统的地区 / 脚本判断（台港澳 → 繁）
    static int FromSystem()
    {
        switch (Application.systemLanguage)
        {
            case SystemLanguage.ChineseTraditional: return 1;
            case SystemLanguage.ChineseSimplified: return 0;
            case SystemLanguage.Chinese:
                var c = System.Globalization.CultureInfo.CurrentUICulture.Name;
                return c.Contains("Hant") || c.EndsWith("TW") || c.EndsWith("HK") || c.EndsWith("MO") ? 1 : 0;
            case SystemLanguage.Japanese: return 3;
            case SystemLanguage.Korean: return 4;
            case SystemLanguage.English: return 2;
            default: return 2;                                     // 其他语言的玩家给英语
        }
    }

    // ── 表 ──
    static Dictionary<string, string> phrases = new(), names = new();
    static readonly HashSet<string> outputs = new();                 // 已经是译文的字符串（拼接后再赋值时别再翻一遍）
    static Regex nameRx; static readonly Dictionary<string, string> cache = new();
    static readonly Regex cjk = new(@"[㐀-鿿豈-﫿]");
    static readonly Regex num = new(@"[-−+]?\d+(\.\d+)?");
    static bool loaded;
    static readonly Regex wan = new(@"(\d+(?:\.\d+)?)\s*万");
    static string K(string v)
    {
        double d = double.Parse(v, System.Globalization.CultureInfo.InvariantCulture) * 10;
        return (d >= 1000 ? (d / 1000).ToString("0.#", System.Globalization.CultureInfo.InvariantCulture) + "M"
                          : d.ToString("0.#", System.Globalization.CultureInfo.InvariantCulture) + "k");
    }
    static readonly Dictionary<char, char> chars = new();           // 繁中：单字简 → 繁
    static int maxWord;                                               // 繁中：词表里最长的词

    public static void Load()
    {
        loaded = true; cache.Clear(); outputs.Clear(); phrases.Clear(); names.Clear(); chars.Clear(); nameRx = null; maxWord = 0;
        if (Source) return;
        string dir = Path.Combine(UIKit.ArtDir, "lang", Code);
        Read(Path.Combine(dir, "phrases.tsv"), phrases);
        Read(Path.Combine(dir, "names.tsv"), names);
        var cp = Path.Combine(dir, "chars.tsv");
        if (File.Exists(cp))
        {
            foreach (var line in File.ReadAllLines(cp)) if (line.Length >= 3 && line[1] == '\t') chars[line[0]] = line[2];
            foreach (var k in phrases.Keys) maxWord = Mathf.Max(maxWord, k.Length);
            foreach (var k in names.Keys) maxWord = Mathf.Max(maxWord, k.Length);
            maxWord = Mathf.Min(maxWord, 12);
            return;                                                     // 逐字转，用不着抽名字
        }
        if (names.Count > 0)
        {
            var keys = new List<string>(names.Keys);
            keys.Sort((a, b) => b.Length.CompareTo(a.Length));      // 长的先配：「司马懿」不会被「司马」截掉
            var sb = new StringBuilder();
            foreach (var k in keys) { if (sb.Length > 0) sb.Append('|'); sb.Append(Regex.Escape(k)); }
            nameRx = new Regex(sb.ToString());
        }
    }
    static void Read(string path, Dictionary<string, string> d)
    {
        if (!File.Exists(path)) return;
        foreach (var line in File.ReadAllLines(path))
        {
            if (line.Length == 0 || line[0] == '#') continue;
            int t = line.IndexOf('\t'); if (t <= 0) continue;
            string k = Unesc(line.Substring(0, t)), v = Unesc(line.Substring(t + 1));
            if (k[0] == '!') { phrases[k.Substring(1)] = v; continue; }   // 「!」开头：只整段匹配，不在句子里抽（平原 / 雷同 / 单字兵器）
            d[k] = v;
        }
    }
    static string Unesc(string s) => s.Replace("\\n", "\n").Replace("\\t", "\t");

    /// 专有名词（武将 / 城 / 兵器…）单独翻，没有就原样
    public static string Name(string s)
    {
        if (!loaded) Load();
        if (Source || string.IsNullOrEmpty(s)) return s;
        return names.TryGetValue(s, out var t) || phrases.TryGetValue(s, out t) ? t : s;
    }

    // ── 翻译 ──
    public static string T(string s)
    {
        if (!loaded) Load();
        if (Source || string.IsNullOrEmpty(s) || !cjk.IsMatch(s)) return s;
        if (cache.TryGetValue(s, out var hit)) return hit;
        if (outputs.Contains(s)) return s;
        string r, key = s;
        if (Code == "en" && s.IndexOf('万') >= 0) s = wan.Replace(s, m => K(m.Groups[1].Value));   // 英文没有「万」：3.8万 → 38k
        if (chars.Count > 0) r = Convert(s);
        else if (phrases.TryGetValue(s, out var whole)) r = whole;
        else
        {
            var lines = s.Split('\n');
            for (int i = 0; i < lines.Length; i++) lines[i] = Line(lines[i]);
            r = string.Join("\n", lines);
        }
        if (cache.Count > 4000) { cache.Clear(); outputs.Clear(); }
        cache[key] = r; if (r != key) outputs.Add(r);
        return r;
    }

    /// 繁中：最长匹配的词优先，其余逐字
    static string Convert(string s)
    {
        var sb = new StringBuilder(s.Length);
        int i = 0;
        while (i < s.Length)
        {
            bool hit = false;
            for (int L = Mathf.Min(maxWord, s.Length - i); L >= 2; L--)
            {
                var w = s.Substring(i, L);
                if (names.TryGetValue(w, out var t) || phrases.TryGetValue(w, out t)) { sb.Append(t); i += L; hit = true; break; }
            }
            if (hit) continue;
            char c = s[i++];
            sb.Append(chars.TryGetValue(c, out var tc) ? tc : c);
        }
        return sb.ToString();
    }

    static string Line(string line)
    {
        if (!cjk.IsMatch(line)) return line;
        if (phrases.TryGetValue(line, out var p)) return p;
        string lead = line.Substring(0, line.Length - line.TrimStart().Length), trail = line.Substring(line.TrimEnd().Length);
        string core = line.Trim();
        if (phrases.TryGetValue(core, out p)) return lead + p + trail;
        // 句式：专有名词 → {0}，数字 → {#0}
        var ns = new List<string>(); var ds = new List<string>();
        string tpl = Abstract(core, ns, ds);
        if (phrases.TryGetValue(tpl, out p)) return lead + Fill(p, ns, ds) + trail;
        if (!cjk.IsMatch(tpl)) return lead + Fill(tpl, ns, ds) + trail;   // 只剩名字和数字
        Miss(tpl);
        // 切段逐段翻
        return lead + Fill(Chunks(tpl), ns, ds) + trail;
    }

    static string Abstract(string s, List<string> ns, List<string> ds)
    {
        // 先抽数字再抽名字：反过来的话「{0}」里的 0 会被当成数字再抽一遍
        s = num.Replace(s, m => { ds.Add(m.Value); return "{#" + (ds.Count - 1) + "}"; });
        if (nameRx != null) s = nameRx.Replace(s, m => { ns.Add(m.Value); return "{" + (ns.Count - 1) + "}"; });
        return s;
    }
    static string Fill(string p, List<string> ns, List<string> ds)
    {
        for (int i = ns.Count - 1; i >= 0; i--) p = p.Replace("{" + i + "}", Name(ns[i]));
        for (int i = ds.Count - 1; i >= 0; i--) p = p.Replace("{#" + i + "}", ds[i].Replace('−', '-'));
        return p;
    }

    // 分隔：空白、全角空格、各种标点
    static readonly Regex split = new(@"(\{#?\d+\}|[\s　·•、，,。：:；;！!？?（）()《》「」【】\[\]/|～~—\-]+)");
    static readonly Regex ph = new(@"^\{#?\d+\}$");
    static string Chunks(string tpl)
    {
        var parts = split.Split(tpl);
        var sb = new StringBuilder();
        foreach (var part in parts)
        {
            if (part.Length == 0) continue;
            if (ph.IsMatch(part)) { sb.Append(part); continue; }                 // 占位符原样，最后 Fill
            if (split.IsMatch(part) && split.Match(part).Length == part.Length) { sb.Append(Punct(part)); continue; }
            if (phrases.TryGetValue(part, out var p)) { sb.Append(p); continue; }
            if (cjk.IsMatch(part)) Miss(part);
            sb.Append(part);
        }
        return sb.ToString();
    }
    static string Punct(string s)
    {
        if (Code == "ja" || Code == "zh-Hant") return s;               // 日文 / 繁中照用全角标点
        var sb = new StringBuilder();
        foreach (char c in s)
            switch (c)
            {
                case '，': case '、': sb.Append(", "); break;
                case '。': sb.Append(". "); break;
                case '：': sb.Append(": "); break;
                case '；': sb.Append("; "); break;
                case '！': sb.Append("! "); break;
                case '？': sb.Append("? "); break;
                case '（': sb.Append(" ("); break;
                case '）': sb.Append(") "); break;
                case '《': case '》': case '「': case '」': sb.Append('"'); break;
                case '　': sb.Append("  "); break;
                default: sb.Append(c); break;
            }
        return sb.ToString();
    }

    // ── 查不到的记下来（-locdump） ──
    static HashSet<string> misses; static string dumpPath;
    static void Miss(string s)
    {
        if (dumpPath == null)
        {
            var av = System.Environment.GetCommandLineArgs(); int i = System.Array.IndexOf(av, "-locdump");
            dumpPath = i >= 0 ? av[i + 1] : "";
            if (dumpPath.Length > 0) { misses = new HashSet<string>(); if (File.Exists(dumpPath)) foreach (var l in File.ReadAllLines(dumpPath)) misses.Add(l); }
        }
        if (misses == null || !misses.Add(s.Replace("\n", "\\n"))) return;
        try { File.AppendAllText(dumpPath, s.Replace("\n", "\\n") + "\n"); } catch { }
    }
}
