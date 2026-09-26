# 繁中：生成 Art/lang/zh-Hant/chars.tsv（单字简→繁，取 OpenCC STCharacters 第一候选）
# 和 phrases.tsv（OpenCC STPhrases 里、在本游戏文字中出现过的词——一简对多繁的字靠它定）。
# 运行时 Loc.Convert 最长匹配：names/phrases 的词优先，其余逐字。
import os, sys, glob, re, opencc
ART, ROOT = sys.argv[1], sys.argv[2]
D = os.path.join(os.path.dirname(opencc.__file__), 'dictionary')
def load(fn):
    out = {}
    for line in open(os.path.join(D, fn), encoding='utf-8'):
        if '\t' not in line: continue
        k, v = line.rstrip('\n').split('\t', 1)
        out[k] = v.split(' ')[0]
    return out
chars = load('STCharacters.txt'); phrases = load('STPhrases.txt')
# 单字默认值改成三国语境 / 台湾习惯：出征的「征」、台、裡
chars.update({'征': '征', '台': '台', '里': '裡'})
corpus = []
for pat in ['unity/SangoCityView/Assets/Scripts/*.cs', 'unity/SangoCityView/Assets/StreamingAssets/Art/**/*.json',
            'unity/SangoCityView/Assets/StreamingAssets/Art/**/*.csv', 'unity/SangoCityView/Assets/StreamingAssets/Art/**/*.tsv']:
    for f in glob.glob(os.path.join(ROOT, pat), recursive=True):
        if '/lang/' in f: continue
        corpus.append(open(f, encoding='utf-8', errors='ignore').read())
text = '\n'.join(corpus)
used = {k: v for k, v in phrases.items() if len(k) > 1 and k in text}
# 游戏专用的修正（OpenCC 对这些给的不是三国语境）
FIX = {'后退': '後退', '干将': '幹將', '于是': '於是', '面向': '面向', '只有': '只有', '一只': '一隻', '皇后': '皇后',
       '头发': '頭髮', '发生': '發生', '出发': '出發', '征兵': '徵兵', '征得': '徵得', '征粮': '徵糧', '征收': '徵收',
       '系统': '系統', '钟会': '鍾會', '里面': '裡面', '城里': '城裡', '这里': '這裡', '哪里': '哪裡', '千里': '千里', '百里': '百里',
       '余令': '餘令', '剩余': '剩餘', '其余': '其餘', '范围': '範圍', '准备': '準備', '获得': '獲得', '收获': '收穫'}
used.update(FIX)
d = os.path.join(ART, 'lang', 'zh-Hant'); os.makedirs(d, exist_ok=True)
with open(os.path.join(d, 'chars.tsv'), 'w', encoding='utf-8') as f:
    for k, v in sorted(chars.items()):
        if len(k) == 1 and v != k: f.write(k + '\t' + v + '\n')
with open(os.path.join(d, 'phrases.tsv'), 'w', encoding='utf-8') as f:
    f.write('# 繁中：一简对多繁的词（tools/loc/make_hant.py 生成）\n')
    for k, v in sorted(used.items()): f.write(k + '\t' + v + '\n')
print(len(chars), len(used))
