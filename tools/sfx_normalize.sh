#!/bin/bash
# 按峰值归一：先量 max_volume，再补到目标峰值（短音效不能用 loudnorm，会被压到听不见）
set -e
mkdir -p /root/sfx_final
for src in /root/sfx/*.wav /root/sfx/*.ogg /root/sfx2/*.wav /root/sfx2/*.ogg; do
  b=$(basename "${src%.*}")
  case $b in
    ui_click|ui_back|ui_confirm|chime) T=-4;;
    page|open|coin) T=-8;;
    step) T=-12;;
    *) T=-3;;
  esac
  mx=$(ffmpeg -v info -i "$src" -af volumedetect -f null - 2>&1 | grep -oE "max_volume: [-0-9.]+" | awk '{print $2}')
  g=$(awk -v t=$T -v m=$mx 'BEGIN{printf "%.2f", t-m}')
  ffmpeg -v error -y -i "$src" -af "volume=${g}dB" -ar 44100 -ac 1 -c:a libvorbis -q:a 5 /root/sfx_final/$b.ogg
  printf "%-12s max %6s -> %s dB (gain %s)\n" $b $mx $T $g
done
