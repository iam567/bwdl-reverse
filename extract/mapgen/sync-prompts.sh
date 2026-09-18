#!/bin/bash
# 把 battletiles.js 里某套主题的提示词推给正在跑的服务端。
# items 是常驻内存的，直接改 items.json 会被 persistItems 覆盖掉，
# 必须走 /api/prompt 这个接口。
set -e
THEME=$1; TOK=$(cat /tmp/sgtok)
cd "$(dirname "$0")/../../image-studio"
node -e "
const m=require('./battletiles.js');
for(const i of m.buildThemeTiles('$THEME'))
  console.log(JSON.stringify({id:i.id,prompt:i.prompt}));
" | while read -r line; do
  id=$(python3 -c "import json,sys;print(json.loads(sys.argv[1])['id'])" "$line")
  r=$(ssh root@130.94.66.231 "curl -s localhost:3080/api/prompt -X POST \
      -H 'content-type: application/json' -H 'cookie: sg_session=$TOK' \
      -d @-" <<< "$line")
  echo "$id $r"
done
