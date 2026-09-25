// 回合引擎：原版 aF() → aD() → au() 6344 / t() 1714 / aC() 6719 / aE() 6894。
//   每月 8 个势力槽（含玩家）随机排个顺序各行动一遍：玩家那一格发 clamp(城数,3,8) 道令，用完才轮下一家；
//   AI 那一格大地图上写「XX 战略中.....」，至少停 2 秒，期间跑 AI.Turn()；
//   8 格都轮完 → 月末结算（MonthEnd.Run）→ 过了 12 月走年末（年份 +1、这一年登场的武将进搜索池）。
// 修掉的原版 bug：AI 打完玩家后同一家白捡一整轮新令（AI.cs 头注 B1）。
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public partial class Game
{
    AI ai;
    public static bool Sim;                     // -sim 自检：玩家一格直接跳过、AI 不停 2 秒、守城随机判胜负
    EventView events;
    RulerPickView rulerPick;
    CustomView custom;
    readonly System.Random turnRng = new System.Random();
    List<string> monthQueue = new();           // 本月还没轮到的势力槽（原版 aB[] 标记）
    bool playerTurnActive, gameOver, loopRunning;
    Coroutine loopCo;
    string aiNow;                              // 正在「战略中」的那家（重建界面后把遮罩补回来）

    // ── 开局 ────────────────────────────────────────────────────────
    /// 主界面「开创霸业」→ 君主选择 → 从这家开始
    void NewGame()
    {
        ResetState();
        atTitle = true; Build();
        rulerPick.Open(data);
    }

    void BeginAs(Faction f)
    {
        Realm.Player = f.Key; playerFaction = f.Key;
        atTitle = false; gameOver = false; monthQueue = new List<string>();
        curCity = Capital();
        Build();
        StartLoop(false);
    }

    /// 自建建都 ap(city) 10805：势力 8 起家——城归自建势力，随从全进城，君主当太守、忠诚 100，其余 99；城里原有金粮开发都照旧
    void FoundCustom(Officer lord, List<Officer> team, MapData.City city)
    {
        var f = new Faction { Key = "player", RulerId = lord.Id };
        Realm.All.RemoveAll(x => x.Key == "player");
        Realm.All.Add(f);
        var st = data.stats.Find(x => x.id == city.id);
        city.owner = "player"; st.owner = "player"; st.ruler = lord.Name; st.governor = lord.Name;
        var a = Personnel.Of(city.id).A; a.Clear();
        team.Remove(lord); team.Insert(0, lord);
        foreach (var o in team) { a.Add(o); o.City = city.id; o.Serving = true; o.Loyal = 99; }
        lord.Loyal = 100;
        Military.Recount(st);
        BeginAs(f);
    }

    void StartLoop(bool resumePlayer)
    {
        if (loopCo != null) StopCoroutine(loopCo);
        loopCo = StartCoroutine(Loop(resumePlayer));
    }

    /// 原版 u() 1736：本家城里第一座「太守就是君主」的城；没有就第一座
    MapData.City Capital()
    {
        var lord = Realm.Ruler(Realm.Player);
        var mine = Realm.Cities(data, Realm.Player);
        if (mine.Count == 0) return null;
        foreach (var c in mine) if (lord != null && data.stats.Find(x => x.id == c.id).governor == lord.Name) return c;   // 原版 u()：君主亲领的城
        foreach (var c in mine) if (lord != null && Personnel.Of(c.id).A.Contains(lord)) return c;              // 君主移驻到别城、不当太守：进他人在的那城
        return mine[0];
    }

    IEnumerator Loop(bool resumePlayer)
    {
        loopRunning = true;
        if (resumePlayer) yield return PlayerTurn(true);
        while (!gameOver)
        {
            if (monthQueue.Count == 0)                                  // 新的一月：8 格洗一遍顺序
            {
                monthQueue = new List<string>();
                foreach (var f in Realm.All) monthQueue.Add(f.Key);
                for (int i = monthQueue.Count - 1; i > 0; i--) { int j = turnRng.Next(i + 1); (monthQueue[i], monthQueue[j]) = (monthQueue[j], monthQueue[i]); }
            }
            while (monthQueue.Count > 0 && !gameOver)
            {
                string k = monthQueue[0]; monthQueue.RemoveAt(0);
                var f = Realm.Of(k);
                if (f == null || !f.Alive) continue;                     // 灭了的、空槽：这一格什么也不发生
                if (k == Realm.Player) yield return PlayerTurn(false);
                else yield return AITurn(k);
            }
            if (gameOver) break;
            yield return MonthEndRoutine();
        }
        loopRunning = false;
    }

    // ── 玩家这一格 ──────────────────────────────────────────────────
    IEnumerator PlayerTurn(bool resume)
    {
        if (!resume) orders = Politics.OrdersPerTurn(Realm.CityCount(data, Realm.Player));   // ao()：e(ar)
        var cap = Capital();
        if (cap == null) yield break;
        if (!resume || curCity == null || curCity.owner != Realm.Player) curCity = cap;
        mapView.Root.gameObject.SetActive(false);
        cityView.Orders = orders;
        cityView.Show(data.stats.Find(x => x.id == curCity.id), curCity.key);
        UpdateDate();
        if (!resume) Toast(Hud.Date(year, month) + "　主公可下 " + orders + " 道令");
        playerTurnActive = true;
        if (Sim) playerTurnActive = false;
        while (playerTurnActive && !gameOver) yield return null;
    }

    /// 令用完了，或者在设置里「结束本月」
    void EndPlayerTurn() { playerTurnActive = false; }

    // ── AI 这一格 ───────────────────────────────────────────────────
    AI.WarPlan defense; string defenseBy;
    IEnumerator AITurn(string k)
    {
        aiNow = k;
        cityView.Root.gameObject.SetActive(false); mapView.Root.gameObject.SetActive(true);
        var f = Realm.Of(k);
        events.Council(f.Name + " 战略中.....");
        events.Hold = true;                                              // 这家回合里出的事件先攒着
        float t0 = Time.time;
        defense = null;
        ai.Month = month;
        ai.Turn(k);
        CheckLords();                                                    // AI 打仗 / 招揽可能擒走别家君主
        mapView.RefreshFlags();
        while (!Sim && Time.time - t0 < 2f) yield return null;            // 原版每家至少停 2 秒
        events.Council(null); aiNow = null;
        events.Hold = false;                                             // 遮罩收了，攒下的事件一条条弹
        while (events.Busy) yield return null;
        if (defense != null)                                             // 这家打到玩家头上来了
        {
            var plan = defense; defense = null;
            yield return DefenseRoutine(k, plan);
        }
        AfterWarChecks();
        while (events.Busy) yield return null;
    }

    /// AI 出兵的执行（原版 t(int) 9685 的三个分支）。返回真 = 打的是玩家（这家本回合到此为止）
    bool ExecuteAIWar(string f, AI.WarPlan p)
    {
        var to = data.cities.Find(x => x.id == p.To); var from = data.cities.Find(x => x.id == p.From);
        var lord = Realm.Ruler(f);
        if (to.owner == Realm.Player) { defense = p; defenseBy = f; return true; }
        if (to.owner == "none")
        {
            War.OccupyEmpty(data, to, f, p.Army);                       // 带的金粮原版就丢了
            events.Push("empty", "占领空城", lord.Name + " 占领空城 " + to.name + "!", p.Army[0]);
            return false;
        }
        events.Push("attack", "出兵", lord.Name + " 军攻打 " + to.name + "...", p.Army[0]);
        var res = War.AutoResolve(data, officers, f, p, turnRng);
        events.Push(res.Won ? "capture" : "repelled", res.Won ? "城池易主" : "攻城失败",
                    lord.Name + (res.Won ? " 军攻占了 " + to.name + "!" : " 军攻打 " + to.name + "失败了!"), p.Army[0]);
        if (res.FallenFaction != null) events.Push("fall", "势力灭亡", res.FallenFaction + " 势力灭亡了!");
        if (res.EnemySuccession != null) events.Push("succeed", "新君继位", res.EnemySuccession);
        return false;
    }

    // ── 守城：AI 打到玩家头上（t(int) 分支 a → am() / an()）──────────────
    bool defenseDone; AI.WarPlan defensePlan; string defenseFaction;
    IEnumerator DefenseRoutine(string k, AI.WarPlan plan)
    {
        var to = data.cities.Find(x => x.id == plan.To);
        var lord = Realm.Ruler(k);
        events.Push("attack", "敌军来犯", lord.Name + " 军攻打 " + to.name + "...", plan.Army[0]);   // aO 1
        while (events.Busy) yield return null;
        defensePlan = plan; defenseFaction = k; defenseDone = false;
        var ts = data.stats.Find(x => x.id == plan.To);
        var roster = new List<Unit>();
        // 守方：城里全体（太守在前，站城池格）；攻方：AI 出征队（[0] 主将）
        var mine = new List<Officer>(Personnel.Of(plan.To).A);
        var gov = mine.Find(o => o.Name == ts.governor);
        if (gov != null) { mine.Remove(gov); mine.Insert(0, gov); }
        foreach (var o in mine) roster.Add(new Unit { O = o, Ours = true });
        foreach (var o in plan.Army) roster.Add(new Unit { O = o, Ours = false });
        curCity = to;
        OpenBattle(plan.To, roster, false, new[] { ts.黄金, ts.粮草, plan.Gold, plan.Food }, EndDefense);   // x() 摆位
        field.FoeRulerId = lord.Id;                                    // 来犯那家：它撤不撤得走按它自己的城算
        if (Sim) { field.ForceDefenseForTest(turnRng.Next(2) == 0); EndDefense(); }
        while (!defenseDone) yield return null;
        while (events.Busy) yield return null;
    }

    void EndDefense()
    {
        string lordName = Lord;
        var r = War.SettleDefense(data, officers, defenseFaction, defensePlan, field, Realm.Player, turnRng);
        foreach (var m in War.CheckRulers(data, officers)) r.Lines.Add(m);
        Destroy(field.Root.gameObject); field = null; inBattle = false; BattleField.World = null;
        Music.Play(bgmMap);
        if (village != null) { Destroy(village.Root.gameObject); village = null; }
        curCity = null;
        Build();
        cityView.Root.gameObject.SetActive(false); mapView.Root.gameObject.SetActive(true);
        var lines = new List<string>(r.Lines);
        if (r.EnemySuccession != null) lines.Add(r.EnemySuccession);
        if (r.FallenFaction != null) lines.Add(r.FallenFaction + " 势力灭亡了!");
        System.Action<string, List<string>, System.Action> report = Sim
            ? (t, l, a) => { Debug.Log("[守城] " + string.Join(" / ", l)); a(); }
            : (System.Action<string, List<string>, System.Action>)military.Report;
        report("守城", lines, () => {
            if (Realm.CityCount(data, Realm.Player) == 0) { defenseDone = true; Ending(false); return; }
            if (r.LordDied && Sim) { War.SetLord(data, Realm.Player, War.Heirs(data, Realm.Player)[0]); defenseDone = true; }
            else if (r.LordDied)
                military.PickHeir(lordName, War.Heirs(data, Realm.Player), h => {
                    War.SetLord(data, Realm.Player, h);
                    events.Push("succeed", "新君继位", lordName + "死亡,新君主 " + h.Name + " 继位!", h);
                    defenseDone = true;
                });
            else defenseDone = true;
        });
    }

    void AfterWarChecks()
    {
        foreach (var fac in Realm.All)
        {
            bool was = fac.Alive;
            if (was && Realm.CityCount(data, fac.Key) == 0) { fac.Alive = false; }
        }
        if (Realm.CityCount(data, Realm.Player) == 0 && !gameOver) Ending(false);
    }

    // ── 月末 / 年末 ─────────────────────────────────────────────────
    IEnumerator MonthEndRoutine()
    {
        var evs = new List<MonthEnd.Ev>();
        int r = MonthEnd.Run(data, officers, ref month, year, turnRng, evs);
        CheckLords();                                                    // 兜底；读旧档时已经错位的也在这里修正
        if (r != 0) { Ending(r > 0); yield break; }
        cityView.Root.gameObject.SetActive(false); mapView.Root.gameObject.SetActive(true);
        foreach (var e in evs) events.Push(e.Key, e.Title, e.Text);
        while (events.Busy) yield return null;
        AlbumMonthTick();                                                // 天下霸图：麾下连续满一年才解锁
        if (month > 12)                                                  // 年末 aE()
        {
            month = 1; year++;
            var came = Personnel.NewYear(year, officers);
            if (came.Count > 0) Toast(year + "年　" + came.Count + " 位新人物登场");
            foreach (var (child, parent, cid) in Personnel.ScriptedJoins(year, officers))      // 事件 12（中级 / 困难）
            {
                if (Sim) Debug.Log("[子承父业] " + year + " " + child.Name + " → " + parent.Name + " @" + cid);
                if (data.cities.Find(x => x.id == cid)?.owner == playerFaction)
                    events.Push("follow", "武将来投", child.Name + "跟随 " + parent.Name + " 加入我军为君主效力!", child);
            }
            Military.RecountAll(data);
            while (events.Busy) yield return null;
        }
        UpdateDate();
        mapView.RefreshFlags();
        if (Sim)
        {
            var sb = new System.Text.StringBuilder("[月报] " + Hud.Date(year, month) + " ");
            foreach (var f in Realm.All) sb.Append(f.Name).Append(f.Alive ? "" : "(亡)").Append(':').Append(Realm.CityCount(data, f.Key)).Append(' ');
            sb.Append("空:").Append(Realm.CityCount(data, "none"));
            int troops = 0; foreach (var st in data.stats) troops += st.兵力;
            sb.Append(" 总兵:").Append(troops);
            Debug.Log(sb.ToString());
            if (--simMonths <= 0) { Debug.Log("[月报] 结束"); Application.Quit(0); }
        }
    }
    int simMonths;

    // ── 结局：屏 23 ─────────────────────────────────────────────────
    void Ending(bool win)
    {
        Sfx.Play(win ? "gong_win" : "gong_lose");
        var set = win ? bgmWin : bgmLose;                                            // 天下一统 / 势力灭亡：两首里随机一首
        if (set != null && set.Length > 0) Music.Play(set[Random.Range(0, set.Length)]);
        if (gameOver) return;
        gameOver = true; playerTurnActive = false;
        cityView.Root.gameObject.SetActive(false); mapView.Root.gameObject.SetActive(true);
        var lord = Realm.Ruler(Realm.Player);
        string name = lord != null ? lord.Name : "";
        if (!win)
        {
            events.Push("defeat", "势力覆灭", name + " 势力被消灭了,全国局势仍处于混乱之中...", lord, BackToTitle);
            return;
        }
        var sc = MonthEnd.Evaluate(data);
        events.Push("victory", "天下一统", name + " 经过多年征战,终于实现了宇内混一!", lord);
        for (int i = 0; i < 7; i++) events.Push(null, MonthEnd.Aspect[i], MonthEnd.VerdictOf(sc, i));
        // 原版最后一行把分数抹成了 0（cR = aO*3/2），这里按本意 cR*3/2
        events.Push(null, "统一", "统一度:" + sc.Raw / 10 + " 分数:" + sc.Raw * 3 / 2, lord, BackToTitle);
    }

    void BackToTitle()
    {
        Music.Play(bgmMap);
        if (Sim) { Debug.Log("[月报] 结局，结束"); Application.Quit(0); return; }
        if (loopCo != null) StopCoroutine(loopCo);
        loopRunning = false; gameOver = true;
        atTitle = true; ResetState(); Build();
    }
}
