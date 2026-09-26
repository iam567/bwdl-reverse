# 繁 / 日 / 韩字体裁剪：常用字全集 + 本游戏该语言实际用到的字（译表 + 原文经转换后的字）。
#   繁：Big5 常用字（A440–C67E，5401 字）；日：JIS 第一水准（2965 字）+ 假名；韩：KS X 1001 韩文 2350 字。
# 用法：subset_fonts.py <下载的 Noto *CJK SubsetOTF 目录> <Assets/Fonts>
import os, sys, glob, opencc
from fontTools import subset
SRC, DST = sys.argv[1], sys.argv[2]
ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..')
ART = os.path.join(ROOT, 'unity/SangoCityView/Assets/StreamingAssets/Art')
base = set(chr(c) for c in range(0x20, 0x7F)) | set(chr(c) for c in range(0xA0, 0x180))
base |= set(chr(c) for c in range(0x2000, 0x2070)) | set(chr(c) for c in range(0x2190, 0x2200)) | set('■□◆◇●○★☆※→←↑↓×÷±…')
base |= set(chr(c) for c in range(0x3000, 0x3040)) | set(chr(c) for c in range(0xFF01, 0xFF5F))
def table_chars(code):
    s = set()
    for f in glob.glob(os.path.join(ART, 'lang', code, '*.tsv')):
        for line in open(f, encoding='utf-8'):
            if '\t' in line: s |= set(line.split('\t', 1)[1])
    return s
corpus = ''
for pat in ['unity/SangoCityView/Assets/Scripts/*.cs', 'unity/SangoCityView/Assets/StreamingAssets/Art/**/*.json', 'unity/SangoCityView/Assets/StreamingAssets/Art/**/*.csv']:
    for f in glob.glob(os.path.join(ROOT, pat), recursive=True): corpus += open(f, encoding='utf-8', errors='ignore').read()
# 代码 / 数据里用到的所有符号（◀▶、■、①…），三套都要有
base |= set(c for c in corpus if ord(c) >= 0x80 and not ('\u3400' <= c <= '\u9fff' or '\uf900' <= c <= '\ufaff' or '\uac00' <= c <= '\ud7af'))
base |= set(chr(c) for c in range(0x25A0, 0x2600))
def enc_range(enc, lo1, hi1, lo2=0xA1, hi2=0xFE):
    out = set()
    for a in range(lo1, hi1 + 1):
        for b in range(lo2, hi2 + 1):
            try: out.add(bytes([a, b]).decode(enc))
            except Exception: pass
    return out
big5 = enc_range('big5', 0xA4, 0xC6, 0x40, 0xFE)
jis1 = enc_range('euc_jp', 0xB0, 0xCF)
kana = set(chr(c) for c in range(0x3040, 0x3100)) | set(chr(c) for c in range(0x31F0, 0x3200)) | set(chr(c) for c in range(0xFF61, 0xFFA0))
hangul = enc_range('euc_kr', 0xB0, 0xC8)
s2t = opencc.OpenCC('s2t'); t2jp = opencc.OpenCC('t2jp')
tc_corpus = set(s2t.convert(corpus))
sets = {
    'TC': base | big5 | table_chars('zh-Hant') | tc_corpus,
    'JP': base | jis1 | kana | table_chars('ja') | set(t2jp.convert(s2t.convert(corpus))),
    'KR': base | hangul | table_chars('ko') | set(chr(c) for c in range(0x3130, 0x3190)),
}
for L, chars in sets.items():
    text = ''.join(sorted(c for c in chars if c.isprintable() or c == ' '))
    for st in ('Serif', 'Sans'):
        for w in ('Regular', 'Bold'):
            fn = 'Noto%s%s-%s.otf' % (st, L, w)
            opts = subset.Options(); opts.layout_features = ['*']; opts.name_IDs = ['*']; opts.name_languages = ['*']
            opts.notdef_outline = True; opts.drop_tables += ['DSIG']
            font = subset.load_font(os.path.join(SRC, fn), opts)
            sub = subset.Subsetter(opts); sub.populate(text=text); sub.subset(font)
            subset.save_font(font, os.path.join(DST, fn), opts)
            print(fn, len(text), os.path.getsize(os.path.join(DST, fn)) // 1024, 'KB')
