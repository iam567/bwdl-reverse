# 从代码里静态还原「句式」：把 "字面量" + 表达式 + "字面量" 这类拼接还原成运行时查表用的句式
# （表达式是名字 → 武将名样本，其余 → 数字样本，再走跟 Loc.Abstract 一样的抽象）。
# 猜错类型的句式运行时只是查不到，不会出错。输出：句式\t出处\t代码行
import re, glob, os, sys
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..')
SCR = os.path.join(ROOT, 'unity/SangoCityView/Assets/Scripts')
cjk = re.compile('[㐀-鿿豈-﫿]')
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

NAMEY = re.compile(r'(Name|name\b|\.nm\b|Ruler\(|EquipName|Plot|城名|Realm\.Label|\.Label\b|CityName|RulerName|who\b|lord\b)', re.I)
def unesc(s): return s.replace('\\n', '\n').replace('\\"', '"').replace('\\\\', '\\')

def operands(code, i):
    """从位置 i（一个字面量的开头引号）向左右扩展 + 链，返回 [(kind, text)]"""
    # 向右
    def read_lit(p):
        q = p + 1
        while q < len(code):
            if code[q] == '\\': q += 2; continue
            if code[q] == '"': return code[p+1:q], q + 1
            q += 1
        return None, len(code)
    def read_expr_right(p):
        depth = 0; q = p
        while q < len(code):
            c = code[q]
            if c == '"':
                _, q = read_lit(q); continue
            if c in '([{': depth += 1
            elif c in ')]}':
                if depth == 0: break
                depth -= 1
            elif depth == 0 and (c in '+,;' or code.startswith('??', q) or c == ':' or c == '?' and not code.startswith('?.', q)): break
            q += 1
        return code[p:q].strip(), q
    items = []
    lit, p = read_lit(i); items.append(('L', lit))
    while True:
        m = re.match(r'\s*\+\s*', code[p:])
        if not m: break
        p += m.end()
        if p < len(code) and code[p] == '"':
            lit, p = read_lit(p); items.append(('L', lit))
        elif code.startswith('$"', p):
            lit, p = read_lit(p + 1); items.append(('I', lit))
        else:
            e, p = read_expr_right(p)
            if not e: break
            items.append(('E', e))
    # 向左
    left = []; q = i - 1
    while True:
        m = re.search(r'\+\s*$', code[:q + 1])
        if not m: break
        q = m.start() - 1
        while q >= 0 and code[q] == ' ': q -= 1
        if q >= 0 and code[q] == '"':
            r = q - 1
            while r >= 0 and not (code[r] == '"' and code[r - 1] != '\\'): r -= 1
            left.insert(0, ('L', code[r+1:q])); q = r - 1
            if r > 0 and code[r - 1] == '$': left[0] = ('I', left[0][1]); q -= 1
        else:
            depth = 0; r = q
            while r >= 0:
                c = code[r]
                if c in ')]}': depth += 1
                elif c in '([{':
                    if depth == 0: break
                    depth -= 1
                elif depth == 0 and c in '+,;=?:': break
                r -= 1
            e = code[r+1:q+1].strip()
            if not e or e in ('return',): break
            left.insert(0, ('E', e)); q = r
            while q >= 0 and code[q] == ' ': q -= 1
            if q < 0 or code[q] != '+': q = q; break
            q += 0
            q = q  # 继续循环去匹配 +
            q = q
            # 回到 while 顶部要求 code[:q+1] 以 + 结尾
    return left + items, p

def sample(items):
    out = ''
    for k, t in items:
        if k == 'L': out += unesc(t)
        elif k == 'I': out += re.sub(r'\{([^}]*)\}', lambda m: '关羽' if NAMEY.search(m.group(1)) else '7', unesc(t))
        else:
            e = t.strip('() ')
            if NAMEY.search(e): out += '关羽'
            else: out += '7'
    return out

res = {}
for f in sorted(glob.glob(os.path.join(SCR, '*.cs'))):
    fn = os.path.basename(f)
    if fn == 'Loc.cs': continue
    for n, line in enumerate(open(f, encoding='utf-8'), 1):
        code = line.rstrip('\n')
        # 去掉行尾注释（引号外的 //）
        i = 0; inq = False; cut = len(code)
        while i < len(code):
            c = code[i]
            if c == '\\' and inq: i += 2; continue
            if c == '"': inq = not inq
            elif not inq and code[i:i+2] == '//': cut = i; break
            i += 1
        code = code[:cut]
        if '+' not in code and '$"' not in code: continue
        if re.search(r'Debug\.Log|Console\.', code): continue
        seen = set()
        for m in re.finditer(r'"', code):
            pass
        i = 0
        while i < len(code):
            if code[i] == '"' and (i == 0 or code[i-1] != '\\'):
                j = i + 1
                while j < len(code) and not (code[j] == '"' and code[j-1] != '\\'): j += 1
                lit = code[i+1:j]
                interp = i > 0 and code[i-1] == '$'
                if cjk.search(lit) and i not in seen:
                    try:
                        items, _ = operands(code, i)
                    except Exception:
                        items = [('L', lit)]
                    if interp: items = [('I', lit)]
                    if len(items) > 1 or interp:
                        s = sample(items)
                        for ln in s.split('\n'):
                            ln = ln.strip()
                            if cjk.search(abstract(ln)) and ('{' in abstract(ln)):
                                res.setdefault(abstract(ln), ('%s:%d' % (fn, n), line.strip()[:220]))
                i = j + 1
            else: i += 1
for k, (src, code) in res.items(): print(k + '\t' + src + '\t' + code)
