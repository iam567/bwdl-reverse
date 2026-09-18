using System.Collections.Generic;

[System.Serializable] public class MapData
{
    [System.Serializable] public class City { public int id; public string name,key; public float x,y; public int tier; public string owner; }
    [System.Serializable] public class Flag { public string key; public float w,h,ax,ay; public string hex,use; }
    [System.Serializable] public class Stat {
        public int id; public string name,tierName,owner,ruler,governor;
        public int 治安,黄金,粮草,农业,商业,人口,治水,兵力,武将,令牌; }
    public List<City> cities; public List<Flag> flags; public List<Stat> stats;
}
