# 汇总待译原文：代码里的中文字面量（及其切段）+ 运行时 -locdump 记下的句式（及其切段）。
# 用法：collect.py <locdump 文件...>  → 打印 TSV：原文\t出处
import re, sys, glob, os
ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..')
SCR = os.path.join(ROOT, 'unity/SangoCityView/Assets/Scripts')
cjk = re.compile('[㐀-鿿豈-﫿]')
split = re.compile(r'(\{#?\d+\}|[\s　·•、，,。：:；;！!？?（）()《》「」【】\[\]/|～~—\-]+)')
num = re.compile(r'[-−+]?\d+(\.\d+)?')
names = set()
for line in open(os.path.join(ROOT, 'unity/SangoCityView/Assets/StreamingAssets/Art/lang/en/names.tsv'), encoding='utf-8'):
    if '\t' in line and not line.startswith('#') and not line.startswith('!'): names.add(line.split('\t')[0])
nameRx = re.compile('|'.join(re.escape(n) for n in sorted(names, key=len, reverse=True)))
def abstract(s):
    k = [0]
    def dn(m): r = '{#%d}' % k[0]; k[0] += 1; return r
    s = num.sub(dn, s)
    j = [0]
    def nn(m): r = '{%d}' % j[0]; j[0] += 1; return r
    return nameRx.sub(nn, s)
SKIP_FILES = {'Loc.cs'}
SKIP = re.compile(r'^\d+[a-z]?\d?_|^[a-e][一-鿿]|^\[|^测|自检|cN=|<color|\\s\*|\[武智')
out = {}
def add(s, src):
    s = s.strip()
    if not s or not cjk.search(s) or SKIP.search(s): return
    if s in names: return
    out.setdefault(s, src)
lit = re.compile(r'\$?@?"((?:[^"\\\n]|\\.)*)"')
for f in sorted(glob.glob(os.path.join(SCR, '*.cs'))):
    fn = os.path.basename(f)
    if fn in SKIP_FILES: continue
    for n, line in enumerate(open(f, encoding='utf-8'), 1):
        i = 0; inq = False; cut = len(line)
        while i < len(line):
            c = line[i]
            if c == '\\' and inq: i += 2; continue
            if c == '"': inq = not inq
            elif not inq and line[i:i+2] == '//': cut = i; break
            i += 1
        code = line[:cut]
        if re.search(r'Debug\.Log|Console\.|Fail\(|Check\(', code): continue
        for m in lit.finditer(code):
            t = m.group(1).replace('\\n', '\n')
            if not cjk.search(t): continue
            src = '%s:%d' % (fn, n)
            for ln in t.split('\n'):
                a = abstract(ln.strip())
                add(a, src)
                for part in split.split(a): add(part, src)
for d in sys.argv[1:]:
    for ln in open(d, encoding='utf-8'):
        ln = ln.rstrip('\n').replace('\\n', '\n')
        for l2 in ln.split('\n'):
            add(l2, 'runtime')
            for part in split.split(l2): add(part, 'runtime')
for k, v in out.items(): print(k.replace('\t', ' ') + '\t' + v)
