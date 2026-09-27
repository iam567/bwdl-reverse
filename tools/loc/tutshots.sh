#!/bin/zsh
# 新手教程各段截图：tutshots.sh <语言> <输出目录> [第几条，默认 1]
# 截第 N 条：-tutstep N（遮罩出来后自动点 N-1 次「下一条」）
APP=/Users/taoo/Documents/claude/sg/bwdl-reverse/unity/SangoCityView/Build/SangoCityView.app/Contents/MacOS/SangoCityView
L=$1; OUT=$2; ST=${3:-1}; mkdir -p $OUT
s() { local name=$1; shift; perl -e 'alarm shift; exec @ARGV' 60 $APP -screen-width 428 -screen-height 926 -screen-fullscreen 0 -diff 0 -lang $L -tutstep $ST "$@" -shot $OUT/$name.png -wait 4 >/dev/null 2>&1; }
s city -play caocao -tut city
s map -tutshot map -tut map
s list -rs 0 -tut list
s war -js 1 -tut war
s battle -war -tut battle
s pick -tutshot pick -tut pick
s clash -clashtest -scene 0 -ticks 0 -tut clash
s duel -tutshot duel -tut duel
s ask -title -tutask
