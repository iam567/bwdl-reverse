#!/usr/bin/env python3
"""把 res/*.jg 还原成 PNG；非图片的（CSV 数据表）原样另存为 .csv。"""
import sys, os, glob, struct, zlib

PNG_HEAD = bytes.fromhex('89504E470D0A1A0A0000000D49484452')

def convert(src_dir, out_dir):
    os.makedirs(out_dir, exist_ok=True)
    png = csv = 0
    for f in sorted(glob.glob(os.path.join(src_dir, '*.jg'))):
        raw = open(f, 'rb').read()
        base = os.path.splitext(os.path.basename(f))[0]
        fixed = PNG_HEAD + raw[16:]
        ok = (len(fixed) > 33
              and (zlib.crc32(fixed[12:29]) & 0xffffffff)
                  == struct.unpack('>I', fixed[29:33])[0])
        if ok:
            open(os.path.join(out_dir, base + '.png'), 'wb').write(fixed)
            png += 1
        else:
            open(os.path.join(out_dir, base + '.csv'), 'wb').write(raw)
            csv += 1
    print(f'{png} PNG, {csv} CSV -> {out_dir}')

if __name__ == '__main__':
    if len(sys.argv) != 3:
        sys.exit('usage: unjg.py <res_dir> <out_dir>')
    convert(sys.argv[1], sys.argv[2])
