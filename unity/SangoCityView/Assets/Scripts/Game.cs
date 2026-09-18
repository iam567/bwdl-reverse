// 入口：建画布，管地图与城内两屏的切换。
using System.IO;
using UnityEngine;
using UnityEngine.UI;
using static UIKit;

public class Game : MonoBehaviour
{
    public Font font;
    const int REF_W = 375, REF_H = 812;

    MapView mapView; CityView cityView; MapData data;
    Text toast; float toastUntil;

    void Start()
    {
        UIKit.UIFont = font;
        data = JsonUtility.FromJson<MapData>(File.ReadAllText(Path.Combine(ArtDir, "map.json")));

        var go = new GameObject("Canvas", typeof(Canvas), typeof(CanvasScaler), typeof(GraphicRaycaster));
        var canvas = go.GetComponent<Canvas>(); canvas.renderMode = RenderMode.ScreenSpaceOverlay;
        var sc = go.GetComponent<CanvasScaler>();
        sc.uiScaleMode = CanvasScaler.ScaleMode.ScaleWithScreenSize;
        sc.referenceResolution = new Vector2(REF_W, REF_H); sc.matchWidthOrHeight = 0;
        var root = (RectTransform)go.transform;

        mapView  = new MapView(root, data, REF_W, REF_H);
        cityView = new CityView(root, REF_W, REF_H);
        cityView.Root.gameObject.SetActive(false);

        mapView.OnEnter = c => {
            mapView.Root.gameObject.SetActive(false);
            cityView.Show(data.stats.Find(s => s.id == c.id), c.key);
        };
        cityView.OnBack = () => {
            cityView.Root.gameObject.SetActive(false);
            mapView.Root.gameObject.SetActive(true);
        };

        toast = Label(root, "", 13, new Color32(0xF2,0xE8,0xD4,255), TextAnchor.MiddleCenter);
        Place(toast.rectTransform, 0, REF_H - 150, REF_W, 30);
        toast.gameObject.SetActive(false);

        Debug.Log("就绪：地图上点城池选中，再点一次进城；城内点建筑出指令，右上角「地图」返回");
    }

    void Toast(string s)
    {
        toast.text = s; toast.gameObject.SetActive(true); toastUntil = Time.time + 2f;
    }

    void Update()
    {
        mapView.Update(REF_W, REF_H);
        cityView.Update();
        if (toast.gameObject.activeSelf && Time.time > toastUntil) toast.gameObject.SetActive(false);
    }
}
