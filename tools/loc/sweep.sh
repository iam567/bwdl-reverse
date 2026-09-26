#!/bin/zsh
# 多语言补表：用 -lang en -locdump 把各画面都跑一遍，记下查不到的句式
APP=/Users/taoo/Documents/claude/sg/bwdl-reverse/unity/SangoCityView/Build/SangoCityView.app/Contents/MacOS/SangoCityView
OUT=${1:-/tmp/locdump.txt}; SHOT=${2:-/tmp/locshot}; mkdir -p $SHOT
n=0
run() { n=$((n+1)); perl -e 'alarm shift; exec @ARGV' 90 $APP -screen-width 428 -screen-height 926 -screen-fullscreen 0 -lang ${LANGC:-en} -locdump $OUT "$@" >/dev/null 2>&1; }
shot() { run "$@" -shot $SHOT/$n.png -wait 3; }
shot -title
shot -title -settings
shot -title -settings -help
shot -title -settings -credits
shot -title -album
shot -title -album -detail
shot -newgame
shot -custom
shot -customedit 0
shot -customedit 2 -female
shot -pickcapital -sel
shot -play caocao
shot -play caocao -ai
shot -sys
shot -sys -load
shot -city
shot -pop
shot -cheat
for d in 同盟 离间 招揽 情报; do shot -dp $d; done
for s in 买入粮草 卖出粮草 购买武器 提升智力 治疗武将; do shot -sc $s; done
shot -sc 购买武器 -shop 0 0
for k in 0 1 2 3; do shot -rs $k; shot -rs $k -step 1; shot -js $k; done
shot -polui; shot -polui2; shot -polui3
shot -war
shot -war -wait 12
shot -clashtest -scene 0 -ticks 5
shot -clashtest -scene 3 -ticks 60
for sk in 0 1 2 3 4 5; do shot -clashtest -skill $sk; done
shot -wintest
run -deftest
run -wartest
for f in caocao liuyan sunjian yuanshao lvbu liubiao han; do run -sim 36 -as $f; done
run -customtest
echo done $n
