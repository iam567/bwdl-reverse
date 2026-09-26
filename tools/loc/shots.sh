#!/bin/zsh
# 多语言看版面：shots.sh <语言> <输出目录> —— 几个主要画面各截一张
APP=/Users/taoo/Documents/claude/sg/bwdl-reverse/unity/SangoCityView/Build/SangoCityView.app/Contents/MacOS/SangoCityView
L=$1; OUT=$2; mkdir -p $OUT
s() { local name=$1; shift; perl -e 'alarm shift; exec @ARGV' 40 $APP -screen-width 428 -screen-height 926 -screen-fullscreen 0 -lang $L -locdump $OUT/miss.txt "$@" -shot $OUT/$name.png -wait 3 >/dev/null 2>&1; }
s title -title
s settings -title -settings
s help -title -settings -help
s album -title -album
s newgame -newgame
s city -city
s pop -pop
s map -play caocao
s sys -sys
s dp -dp 离间
s shop -sc 购买武器 -shop 0 0
s rs -rs 1
s js -js 3
s pol -polui2
s war -war -wait 8
s clash -clashtest -scene 0 -ticks 5
s custom -customedit 0
