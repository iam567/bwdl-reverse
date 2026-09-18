# 生图工作台 (sg.areu.pro)

纯 Node 22 实现，**零 npm 依赖**（用原生 fetch + node:crypto）。
部署在 1 核 / 2GB 的 VPS 上，内存上限 320MB。

## 部署位置

```
/var/www/sg.areu.pro/          应用根目录
  server.js                    HTTP 服务（127.0.0.1:3080）
  build-catalog.js             从武将 CSV 生成 prompt 目录
  static/                      前端三页
  data/config.json             配置（0600，含 API Key）
  data/assets.json             素材目录 + prompt
  data/jobs.json               任务记录
  source/                      原始素材（只读参照）
  output/                      生成结果
/etc/systemd/system/sg-imagestudio.service
/etc/caddy/sg.areu.pro.caddy
```

## 运维

```bash
systemctl status sg-imagestudio
systemctl restart sg-imagestudio
tail -f /var/www/sg.areu.pro/logs/app.log
```

## Prompt 体系

`build-catalog.js` 的核心是 **STYLE_ANCHOR 全局固定**，只有主体描述随属性变化。
这是 239 张头像保持风格一致的唯一手段 —— 风格词一旦随机漂移整套图就散了。

身份判定（文士 / 猛将 / 武官）由 `智力 - 武力` 的差值决定，**先于兵种**；
兵种只做配饰点缀，不决定服装主体。9 个既定形象过强的名将走 `ICONIC` 显式覆盖。

重新生成目录：

```bash
node build-catalog.js officers.csv   # 输出 assets.json
```
