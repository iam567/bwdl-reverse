using System.Collections.Generic;
using UnityEngine;

[System.Serializable] public class RoadData
{
    /* JsonUtility 不吃嵌套数组，所以 points 在打包时被拍平成 [x1,y1,x2,y2,…]，
       这里两两取回。见 pack 步骤里的 flat 处理。 */
    [System.Serializable] public class Edge {
        public int a, b; public string aName, bName;
        public List<float> flat;
        public float length; public bool straight, sea;
        public List<Vector2> Points() {
            var r = new List<Vector2>();
            for (int i = 0; i + 1 < flat.Count; i += 2) r.Add(new Vector2(flat[i], flat[i+1]));
            return r;
        }
    }
    public int coordSpace; public List<Edge> edges;

    public Edge Find(int a, int b) =>
        edges.Find(e => (e.a == a && e.b == b) || (e.a == b && e.b == a));
    public List<int> Neighbors(int id) {
        var r = new List<int>();
        foreach (var e in edges) { if (e.a == id) r.Add(e.b); else if (e.b == id) r.Add(e.a); }
        return r;
    }
}
