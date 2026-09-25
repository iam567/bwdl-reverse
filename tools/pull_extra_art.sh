#!/bin/bash
# 把平台上画号 268~385（3.2.5 / 3.1 新增武将）已完成的卡面 / 头像拉进游戏：
#   卡面 1024×1536 → 192×288，头像 1024×1024 → 256×256，jpg，放 StreamingAssets/Art/battle/{card,avatar}/<画号>.jpg，
#   仓库 assets/{card,avatar}/ 各留一份。用法：tools/pull_extra_art.sh [起 止]
set -e
R=$(cd "$(dirname "$0")/.." && pwd)
A="$R/unity/SangoCityView/Assets/StreamingAssets/Art/battle"
LO=${1:-268}; HI=${2:-385}
TMP=$(mktemp -d)
ssh -o BatchMode=yes root@130.94.66.231 "python3 -c \"
import json
for i in json.load(open('/var/www/sg.areu.pro/data/items.json')):
    k,_,n=i['id'].partition('_')
    if k in ('card','avatar') and n.isdigit() and $LO<=int(n)<=$HI and i['status']=='done' and i.get('file'): print(k,n,i['file'])
\"" > "$TMP/list"
mkdir -p "$R/assets/avatar" "$R/assets/card" "$TMP/src"
# 一次打包拉回来（逐个 scp 太慢）
awk '{print $3}' "$TMP/list" | ssh -o BatchMode=yes root@130.94.66.231 "cd /var/www/sg.areu.pro/output && tar cf - -T -" | tar xf - -C "$TMP/src"
n=0
while read -r k id f; do
  if [ "$k" = card ]; then W=192; H=288; else W=256; H=256; fi
  sips -s format jpeg -s formatOptions 82 -z $H $W "$TMP/src/$f" --out "$A/$k/$id.jpg" >/dev/null
  cp "$A/$k/$id.jpg" "$R/assets/$k/$id.jpg"
  n=$((n+1))
done < "$TMP/list"
echo "pulled $n"
