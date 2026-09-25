using System.Collections.Generic;

[System.Serializable] public class MapData
{
    [System.Serializable] public class City { public int id; public string name,key; public float x,y; public int tier; public string owner; }
    [System.Serializable] public class Flag { public string key; public float w,h,ax,ay; public string hex,use; }
    [System.Serializable] public class Stat {
        public int id; public string name,tierName,owner,ruler,governor;
        public int 治安,黄金,粮草,农业,商业,人口,治水,兵力,武将,令牌;
        public int 宝物;          // 原版 w[]，开局 36 城全是 0，靠搜索攒，奖赏时可以拿来赏
        public int 武库档;        // 原版 y[]：武器店货架档 0~5（cities.csv 第 21 列，-1 = 无武器店），卖出名品后改成 1
        public Facil facil = new(); // 原版 x[] 四个位：武库 / 郡学 / 药庐 / 常平仓
        public int 村档;          // 原版 z[]：这座城战场上武器村的货架档（cities.csv 第 22 列，-1 = 战场图里没有武器村）
        public int 预备兵;        // 原版 v[]：征来还没分给武将的兵（cities.csv 第 15 列），征兵时再分下去
    }
    [System.Serializable] public class Facil { public bool wuku, junxue, yaolu, changping; }
    public List<City> cities; public List<Flag> flags; public List<Stat> stats;
}
