# 生成 Art/lang/<code>/names.tsv：武将、城池、兵器、计策。
# 繁中 = OpenCC s2t（+ 校正），日文 = 繁中经 OpenCC t2jp（+ 校正），英文 = 拼音（+ 校正），韩文 = 汉字音（hanja 库，+ 校正）。
# 原版数据里的异写（刑道荣 / 程志远 / 庐植 / 步隙 …）译名一律归到正字。
import os, sys, opencc, pypinyin, hanja
ART = sys.argv[1]
SRC = os.path.dirname(os.path.abspath(__file__))
s2t = opencc.OpenCC('s2t'); t2jp = opencc.OpenCC('t2jp')

officers = open(os.path.join(SRC, 'officers.txt'), encoding='utf-8').read().split()
cities = open(os.path.join(SRC, 'cities.txt'), encoding='utf-8').read().split()

# 异写 → 正字（只影响译名）
ALIAS = {'刑道荣': '邢道荣', '淳干琼': '淳于琼', '程志远': '程远志', '庐植': '卢植', '步隙': '步骘', '朱恒': '朱桓',
         '雷同': '雷铜', '曹睿': '曹叡', '阿哙喃': '阿会喃', '金环结': '金环三结', '祝融': '祝融夫人', '乌丸': '乌丸头目'}

HANT = {'凌统': '凌統', '刘辟': '劉辟', '曹冲': '曹沖', '陈群': '陳群', '并州': '并州', '蒋干': '蔣幹', '高干': '高幹'}
JA = {'豫州': '豫州', '豫章': '豫章', '并州': '并州', '凌统': '凌統', '刘辟': '劉辟', '曹冲': '曹沖', '陈群': '陳羣',
      '献帝': '献帝', '尚龙': '尚竜'}
KO = {'乐进': '악진', '乐琳': '악림', '甘宁': '감녕', '建宁': '건녕', '文丑': '문추', '孙鲁班': '손노반', '夏侯令女': '하후영녀',
      '鲍三娘': '포삼랑', '胡车儿': '호거아', '董茶奴': '동다노', '吕玲绮': '여령기', '孙诩': '손후', '凌统': '능통', '刘辟': '유벽',
      '乌丸头目': '오환 두목', '祝融夫人': '축융 부인', '木鹿大王': '목록대왕', '朵思大王': '타사대왕', '带来洞主': '대래동주',
      '献帝': '헌제', '刘氏': '유씨', '孙氏': '손씨', '徐氏': '서씨', '卞氏': '변씨', '糜氏': '미씨', '蔡氏': '채씨', '陈群': '진군'}
EN = {'乐进': 'Yue Jin', '乐琳': 'Yue Lin', '献帝': 'Emperor Xian', '乌丸头目': 'Wuhuan Chief', '兀突骨': 'Wutugu',
      '木鹿大王': 'King Mulu', '朵思大王': 'King Duosi', '带来洞主': 'Dailai Dongzhu', '祝融夫人': 'Lady Zhurong',
      '金环三结': 'Jinhuan Sanjie', '忙牙长': 'Mangyachang', '阿会喃': 'Ahuinan', '沙摩柯': 'Shamoke', '胡车儿': "Hu Che'er",
      '越吉': 'Yueji', '雅丹': 'Yadan', '北斗': 'Beidou', '南斗': 'Nandou', '夏侯令女': 'Xiahou Lingnü',
      '刘氏': 'Lady Liu', '孙氏': 'Lady Sun', '徐氏': 'Lady Xu', '卞氏': 'Lady Bian', '糜氏': 'Lady Mi', '蔡氏': 'Lady Cai',
      '吴国太': 'Lady Wu', '逢纪': 'Pang Ji', '龚都': 'Gong Du', '大乔': 'Da Qiao', '小乔': 'Xiao Qiao',
      '会稽': 'Kuaiji', '邺城': 'Ye', '长安': "Chang'an", '孙鲁班': 'Sun Luban', '鲍三娘': 'Bao Sanniang', '吕玲绮': 'Lü Lingqi'}

EQUIP = [  # 简, 英, 日, 韩（繁中走 s2t）
 ('短剑', 'Short Sword', '短剣', '단검'), ('飞剑', 'Flying Sword', '飛剣', '비검'), ('长剑', 'Long Sword', '長剣', '장검'),
 ('破斩剑', 'Cleaver Sword', '破斬剣', '파참검'), ('大剑', 'Great Sword', '大剣', '대검'), ('七星剑', 'Seven Star Sword', '七星剣', '칠성검'),
 ('青釭剑', 'Qinggang Sword', '青釭剣', '청강검'), ('倚天剑', 'Yitian Sword', '倚天剣', '의천검'),
 ('雍刀', 'Yong Blade', '雍刀', '옹도'), ('眉尖刀', 'Brow-tip Blade', '眉尖刀', '미첨도'), ('三尖刀', 'Trident Blade', '三尖刀', '삼첨도'),
 ('钩镰刀', 'Hook Sickle', '鉤鎌刀', '구겸도'), ('偃月刀', 'Crescent Glaive', '偃月刀', '언월도'), ('凤嘴刀', 'Phoenix Beak Blade', '鳳嘴刀', '봉취도'),
 ('龙牙刀', 'Dragon Fang Blade', '竜牙刀', '용아도'), ('关王刀', "Guan's Glaive", '関王刀', '관왕도'),
 ('矛', 'Spear', '矛', '모'), ('双戟', 'Twin Halberds', '双戟', '쌍극'), ('单钩枪', 'Hook Spear', '単鉤槍', '단구창'),
 ('战斧', 'Battle Axe', '戦斧', '전부'), ('双钩枪', 'Twin Hook Spear', '双鉤槍', '쌍구창'), ('方天化戟', 'Sky Piercer', '方天画戟', '방천화극'),
 ('狼牙枪', 'Wolf Fang Spear', '狼牙槍', '낭아창'), ('蛇矛', 'Serpent Spear', '蛇矛', '사모'),
 ('纶巾', 'Silk Scarf', '綸巾', '윤건'), ('笼手', 'Gauntlets', '籠手', '농수'), ('兜', 'Helmet', '兜', '투구'),
 ('硬皮', 'Hard Leather', '硬皮', '경피갑'), ('鹤氅', 'Crane Cloak', '鶴氅', '학창의'), ('甲胄', 'Armor', '甲冑', '갑주'),
 ('圣者之衣', "Sage's Robe", '聖者の衣', '성자의 옷'), ('龙鳞宝铠', 'Dragon Scale Mail', '竜鱗宝鎧', '용린보개'),
]
PLOTS = [
 ('火计', 'Fire Attack', '火計', '화계'), ('陷阱', 'Pitfall', '陥穽', '함정'), ('虚兵', 'Feint', '虚兵', '허병'),
 ('要击', 'Waylay', '要撃', '요격'), ('乱水', 'Riptide', '乱水', '난수'), ('火箭', 'Fire Arrows', '火箭', '화전'),
 ('共杀', 'Infighting', '共殺', '공살'), ('伪击转杀', 'Feint & Strike', '偽撃転殺', '위격전살'), ('笼络', 'Win Over', '籠絡', '회유'),
 ('落石', 'Rockslide', '落石', '낙석'), ('连环', 'Chain Trap', '連環', '연환'), ('十面埋伏', 'Tenfold Ambush', '十面埋伏', '십면매복'),
 ('水攻', 'Flood', '水攻', '수공'), ('连弩', 'Repeating Crossbow', '連弩', '연노'), ('劫火', 'Inferno', '劫火', '겁화'),
 ('奇门遁甲', 'Mystic Arts', '奇門遁甲', '기문둔갑'),
]
COMPOUND = ['诸葛', '司马', '夏侯', '公孙', '太史', '淳于']
# 这些词在别的句子里另有意思（地形「平原」、「雷同」、单字兵器），不参与句中抽名，只整段匹配
NOREGEX = {'矛', '兜', '平原', '雷同', '北斗', '南斗'}

def en(n, city):
    if n in EN: return EN[n]
    p = pypinyin.lazy_pinyin(n, style=pypinyin.Style.NORMAL, v_to_u=True)
    p = [x.replace('ü', 'ü') for x in p]
    if city or len(p) == 1: return ''.join(p).capitalize()
    for c in COMPOUND:
        if n.startswith(c) and len(n) > len(c):
            k = len(c); return ''.join(p[:k]).capitalize() + ' ' + ''.join(p[k:]).capitalize()
    s = p[0].capitalize(); g = ''.join(p[1:])
    return (s + ' ' + g[0].upper() + g[1:])

def fix_en(s):
    s = s.replace('Lü', 'Lü').replace('Lv ', 'Lü ')
    return s

def hant(n): return HANT.get(n) or s2t.convert(n)
def ja(n): return JA.get(n) or t2jp.convert(hant(n))
def ko(n): return KO.get(n) or hanja.translate(hant(n), 'substitution')

out = {c: [] for c in ('zh-Hant', 'en', 'ja', 'ko')}
def add(src, h, e, j, k):
    out['zh-Hant'].append((src, h)); out['en'].append((src, e)); out['ja'].append((src, j)); out['ko'].append((src, k))

for n in officers + cities:
    city = n in cities
    m = ALIAS.get(n, n)
    add(n, hant(m), fix_en(en(m, city)), ja(m), ko(m))
for s, e, j, k in EQUIP + PLOTS:
    add(s, s2t.convert(s).replace('鈎', '鉤'), e, j, k)

for code, rows in out.items():
    d = os.path.join(ART, 'lang', code); os.makedirs(d, exist_ok=True)
    with open(os.path.join(d, 'names.tsv'), 'w', encoding='utf-8') as f:
        f.write('# 专有名词：武将 / 城池 / 兵器 / 计策（tools/loc/make_names.py 生成）\n')
        for s, t in rows: f.write(('!' if s in NOREGEX else '') + s + '\t' + t + '\n')
print({k: len(v) for k, v in out.items()})
