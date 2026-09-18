'use strict';
/* 从武将数据表生成素材目录 + 每张图的 prompt。
   核心设计：STYLE_ANCHOR 全局固定不变，只有「主体描述」随武将属性变化。
   这是保证 239 张头像风格一致的唯一手段 —— 风格词一旦随机漂移，整套图就散了。 */
const fs = require('node:fs');
const path = require('node:path');

/* ============ 全局风格锚点（所有图共用，逐字不变）============ */
const STYLE_ANCHOR =
  '中国三国时代题材的角色立绘。写实厚涂油画风格，笔触厚重可见，' +
  '统一采用土褐、青灰、暗金的低饱和配色，背景为单一深色（近黑的墨绿或深褐），' +
  '戏剧性的single-source侧逆光从画面左上打下，人物边缘有冷调轮廓光。' +
  '严格半身构图，人物占画面高度约百分之七十，视线朝向画面左前方。' +
  '画面中不得出现任何文字、字母、汉字、签名、水印、边框或UI元素。';

const NEGATIVE =
  '避免：动漫风、赛璐璐平涂、3D渲染感、塑料质感、过曝高饱和、现代服装、' +
  '多个人物、全身像、文字水印、对称正面证件照构图。';


/* ============ 女性武将 ============
   原表没有性别字段，且靠名字会误判（如「董茶奴」实为南蛮男将董荼那）。
   下列 ID 是逐张比对 228 张原始头像后确认的，共 20 人。 */
const MYSTIC_YOUNG = new Set([4]);   // 南斗：原画是年轻男子，不是白须老者
const FEMALE = new Set([30,33,41,74,82,130,131,132,134,141,149,179,187,188,197,198,214,216,225,226]);

/* 女性细分：女将 / 才女 / 贵妇 */
function femaleRole(wu, zhi) {
  if (wu >= 75) return 'warrior_f';
  if (zhi >= 85) return 'talent_f';
  return 'noble_f';
}
/* 年龄段（原画里明确是长者/中年的，单独标出） */
const ELDER_F  = new Set([132]);              // 吴国太
const MATRON_F = new Set([74,216,225,226]);   // 卞氏、蔡氏、刘氏

/* 既定形象过强的女性，显式覆盖 */
const ICONIC_F = {
  '貂蝉':   '绝色舞姬，柳眉杏目，肌肤胜雪，云鬓高绾插步摇，着绯红广袖舞衣，披帛垂落，神情含愁带媚',
  '小乔':   '江东美人，清丽婉约，鹅蛋脸，发髻简洁插碧玉簪，着淡青色曲裾深衣，气质温婉',
  '大乔':   '江东美人，端庄沉静，较小乔更显成熟，发髻端整，着月白曲裾深衣，眉宇间有轻愁',
  '孙尚香': '弓腰姬，英气勃勃，高马尾束发，着轻便女式鳞甲外罩绯色战裙，腰佩双剑，眼神锐利自信',
  '祝融夫人': '南中蛮族女王，深色肌肤，编发缀骨饰与羽毛，着兽皮与青铜片拼接的战装，手持飞刀，神情剽悍',
  '花鬘':   '南中蛮族少女，深色肌肤，散发编小辫缀彩珠，着轻便皮甲与藤编护具，年轻矫健',
  '黄月英': '才女，相貌平实但眼神极为聪慧专注，发髻朴素，着素色深衣，手边有精巧木制机关器械',
  '蔡琰':   '才女，气质清冷孤高，长发半束，着素色广袖深衣，怀抱古琴或胡笳，眉间有流离之苦',
  '吕玲绮': '将门之女，银白色女式明光甲，高束马尾，面容冷峻英挺，手按长枪',
  '马云禄': '西凉女将，健美英挺，着西凉样式轻甲外罩赭色披风，发辫束起，肤色微深，神情爽利',
  '鲍三娘': '女武将，红色抹额束发，着朴素扎甲，身形精悍，神情坚毅',
  '孙鲁班': '权门贵女，容貌明艳但眼神凌厉多疑，华贵发饰，着织锦深衣，气场逼人'
};

function femaleBody(role, id) {
  const elder  = ELDER_F.has(id), matron = MATRON_F.has(id);
  if (elder)  return '年长妇人，body 端坐雍容，银发梳作端整高髻插金饰，着深色织锦广袖礼服，不着甲'
                     .replace('body ', '');
  if (matron) return '中年贵妇，仪态端严，发髻梳整插玉饰，着深色曲裾深衣，不着甲';
  if (role === 'warrior_f')
    return '身形挺拔矫健而不魁梧，着合身的女式轻甲（收腰、护肩、护臂），衣甲之下露出行动便利的战裙';
  if (role === 'talent_f')
    return '身形纤秀，不着甲，着广袖交领深衣，衣料柔软有垂坠感';
  return '身形纤柔，不着甲，着曲裾深衣或襦裙，衣饰精致';
}
function femaleFace(role, de, id) {
  if (ELDER_F.has(id))  return '面容慈和而有威仪，眼角有细纹，神情沉稳';
  if (MATRON_F.has(id)) return '面容端丽，神情持重内敛';
  let m = role === 'warrior_f' ? '眉目英挺，眼神明亮果决，肤色健康'
        : role === 'talent_f'  ? '眉目清秀，眼神沉静聪慧，气质书卷'
        :                        '眉目柔美，肌肤细腻，神情温婉';
  if (de >= 90)      m += '，气度端方';
  else if (de <= 35) m += '，眼神微冷，带一丝算计';
  return m;
}
const FEMALE_NEGATIVE =
  '严禁：胡须、络腮胡、男性化面部轮廓、男性体格。' +
  '同时避免：现代服饰、暴露或性化的着装、比基尼、过度夸张的身材比例、动漫萌系画风。';

/* ============ 属性 → 外貌特征 ============
   身份判定先于兵种：兵种是战斗属性，不能决定服饰主体。
   文武倾向由 智力 - 武力 的差值决定。 */
function roleOf(wu, zhi, bingli, jice) {
  // 方士/术士组：全表仅 管辂/南斗/于吉/北斗 四人满足（兵力 0 且计策点 300）。
  // 他们武智被人为拉平到 105-120，会骗过下面的 智-武 差值判定，必须先拦。
  if (Number(bingli) === 0 && Number(jice) === 300) return 'mystic';
  if (zhi - wu >= 25) return 'scholar';   // 谋士
  if (wu - zhi >= 25) return 'warrior';   // 猛将
  return 'officer';                       // 均衡型武官
}
function bodyTrait(role, wu) {
  if (role === 'mystic')
    return '身着道家鹤氅或宽大法袍，不着任何甲胄，手持卜筮用的蓍草、罗盘或拂尘';
  if (role === 'scholar')
    return '身形清癯修长，肩窄，不着甲，着宽袖交领深衣，手持羽扇或竹简';
  if (role === 'warrior') {
    if (wu >= 110) return '体格魁梧异常，肩背如铁塔，颈项粗壮，重甲在身，握持长柄重兵';
    if (wu >= 95)  return '身形健硕，肌肉线条分明，双手骨节粗大，着明光重甲';
    return '体格结实，军人体态，着扎甲';
  }
  return '身形匀称，武官体态，着轻便鳞甲外罩官袍';
}
function faceTrait(role, wu, zhi, de, id) {
  // 只取最强的一条智力/武力特征 + 最多一条德行特征，避免堆叠矛盾
  let main;
  if (role === 'mystic')
    main = MYSTIC_YOUNG.has(id)
      ? '面容清俊年轻，几近中性，双目如有星光，不似凡人，长发未束，神情超然物外'
      : '面容清奇脱俗，双目如有星光，不似凡人，须发花白，长须及胸，神情超然物外';
  else if (role === 'scholar')
    main = zhi >= 110 ? '眉目疏朗，眼神澄澈深远，额高，神情从容不迫'
         : '目光内敛沉静，眼尾细长，似在权衡';
  else if (role === 'warrior')
    main = wu >= 110 ? '浓眉虬髯，面部轮廓刚硬如刀削，颊侧有旧伤疤，目光灼人'
         : '面部棱角分明，短须，眼神沉稳锐利';
  else
    main = '五官端正，神情沉静，短须修整';

  let moral = '';
  if (de >= 90)      moral = '，整体气度仁厚雍容';
  else if (de <= 25) moral = '，眼神阴鸷，嘴角微沉，带压迫感';
  else if (de <= 50) moral = '，神情倨傲冷淡';
  return main + moral;
}
function gearAccent(bingzhong, role) {
  // 兵种只做配饰点缀，不改服装主体；文士与方士不挂兵器
  const n = Number(bingzhong);
  if (role === 'mystic')
    return '袍服有暗纹星斗图样，色调为玄青与素白，背景光线带一丝非自然的冷调';
  if (role === 'scholar')
    return n === 2 ? '衣料与配色带江南水乡气息，青碧色系'
         : n === 3 ? '衣料略厚重，配色偏山地土褐'
         :           '衣料为中原素色麻葛，配色土黄';
  switch (n) {
    case 2:  return '配饰点缀带南方水军痕迹：蓑领、湿润的布料质感、腰间短刀';
    case 3:  return '配饰点缀带山地部队痕迹：毛皮护肩、厚重护腕';
    default: return '配饰点缀带中原平原军痕迹：披膊、环首刀';
  }
}
function moodTrait(de, zhong) {
  if (de >= 90 && zhong >= 95) return '氛围庄重，带有可托付的沉稳感';
  if (de <= 25) return '氛围压抑危险，光线更硬，阴影更重';
  if (zhong <= 60) return '氛围疏离，眼神游移，似有二心';
  return '氛围肃穆';
}

/* 名将特例：既定形象太强的，属性推不出来，必须显式指定 */
const ICONIC = {
  '诸葛亮': '头戴纶巾，手执白羽扇，面如冠玉，三绺长须，不着甲胄，氅衣宽大',
  '关羽':   '面如重枣，丹凤眼卧蚕眉，五绺长髯及胸，绿锦战袍外罩鳞甲',
  '张飞':   '豹头环眼，燕颔虎须，声势外放，黑面浓髯，重甲',
  '赵云':   '面容俊朗年轻，白袍银甲，手持长枪，英气逼人',
  '曹操':   '身量不高但气场极强，细目长髯，眼神多疑锐利，玄色锦袍',
  '刘备':   '双耳垂肩，面白无须或短须，神情仁厚，着朴素战袍',
  '司马懿': '鹰视狼顾之相，颈项可回转，眼神深沉阴鸷，玄色深衣',
  '周瑜':   '姿容俊美，气度儒雅而带锋芒，江东轻甲',
  '吕布':   '极高大健硕，头戴三叉束发紫金冠，面容英俊而桀骜，手持方天画戟'
};

/* ============ 读 CSV ============ */
function readCSV(p) {
  return fs.readFileSync(p, 'utf8').split(/\r?\n/).filter(Boolean).map(l => l.split(','));
}

function buildPortraits(csvPath) {
  const rows = readCSV(csvPath);
  const seen = new Map();
  const out = [];
  for (const r of rows) {
    const [id, name, , bingzhong, , tili, zhi, wu, de, zhong] = r;
    const N = s => parseInt(s, 10) || 0;
    // 同名武将（原表有重复）加后缀区分
    const n = seen.get(name) || 0; seen.set(name, n + 1);
    const label = n ? `${name}(变体${n + 1})` : name;

    const oid = N(id);
    const isF = FEMALE.has(oid);
    let subject, negative = NEGATIVE, role, roleName;

    if (isF) {
      /* ---- 女性路径：绝不复用男性特征词（那里有「短须」「虬髯」） ---- */
      role = femaleRole(N(wu), N(zhi));
      roleName = { warrior_f:'女武将', talent_f:'才女', noble_f:'仕女' }[role];
      subject = ICONIC_F[name]
        ? `主体：${name}，三国时代女性人物。${ICONIC_F[name]}。${moodTrait(N(de), N(zhong))}。`
        : [
            `主体：${name}，三国时代${roleName}`,
            femaleBody(role, oid),
            femaleFace(role, N(de), oid),
            gearAccent(bingzhong, role === 'warrior_f' ? 'warrior' : 'scholar'),
            moodTrait(N(de), N(zhong))
          ].join('。') + '。';
      negative = NEGATIVE + FEMALE_NEGATIVE;
    } else {
      role = roleOf(N(wu), N(zhi), r[4], r[10]);
      roleName = { mystic:'方士术士', scholar:'文臣谋士', warrior:'武将', officer:'武官' }[role];
      subject = ICONIC[name]
        ? `主体：${name}，三国时代人物。${ICONIC[name]}。${moodTrait(N(de), N(zhong))}。`
        : [
            `主体：${name}，三国时代${roleName}`,
            bodyTrait(role, N(wu)),
            faceTrait(role, N(wu), N(zhi), N(de), oid),
            gearAccent(bingzhong, role),
            moodTrait(N(de), N(zhong))
          ].join('。') + '。';
    }

    out.push({
      id: `portrait_${id}`,
      category: 'portrait',
      label,
      meta: { 性别: isF ? '女' : '男', 武力: N(wu), 智力: N(zhi), 德: N(de), 忠诚: N(zhong), 体力: N(tili),
              兵种: ['', '平原军', '水军', '山地军'][N(bingzhong)] || '平原军' },
      source: `/source/avatar/${id}.png`,
      size: '1024x1536',
      prompt: `${STYLE_ANCHOR}\n\n${subject}\n\n${negative}`
    });
  }
  return out;
}

/* ============ CG / 场景 / 单位 ============ */
const CG = [
  ['lib1',   '世界地图底图',  '中国汉末十三州的古地图，羊皮纸质感，墨线勾勒山川河流与城池，暗金与土褐配色，边角磨损，俯视平面视角'],
  ['lib13',  '标题主视觉',    '三国题材游戏标题背景，远景为烽烟中的城郭与旌旗，前景大片留白供标题压字，史诗感，低饱和'],
  ['lib59',  '君主登基场景',  '汉代宫殿大殿内部，玉阶之上空置的主位，两侧列戟，烛火幽微，尘埃在光柱中浮动，无人物'],
  ['lib106', '战败/灭亡',     '战后废墟，断折的旌旗半埋于焦土，远处城郭冒烟，天空铅灰，荒凉肃杀，无人物'],
  ['lib302', '内政建设',      '汉代城池内的市井与农田俯瞰，水渠纵横，谷仓与集市，秋收时节的暖褐色调，安定繁盛']
];
const SCENE = [
  ['lib120','平原会战','开阔平原上两军对垒的远景，尘土飞扬，骑兵阵列，低角度'],
  ['lib121','山地伏击','狭窄山道，两侧陡壁，滚石与埋伏的旗影'],
  ['lib122','水战','长江江面上的楼船与艨艟，浪涌，帆影，水汽'],
  ['lib123','攻城','高大夯土城墙下的云梯与冲车，箭雨'],
  ['lib124','火攻','夜色中的连营大火，火光映红江面与天空'],
  ['lib125','树林遭遇','茂密树林中的行军队列，光线破碎，视野受阻'],
  ['lib126','守城','城楼上的守军视角，俯瞰城下攻城部队'],
  ['lib127','骑兵冲锋','重骑兵正面冲锋的动势，长矛前指，尘浪'],
  ['lib128','弓兵齐射','弓箭手阵列仰角齐射，箭矢离弦的瞬间'],
  ['lib129','单挑','两名武将马上交锋的对峙瞬间，周围士兵退开成圆'],
  ['lib130','夜袭','月下潜行的部队，火把星点，营帐轮廓'],
  ['lib131','雨战','大雨中的泥泞战场，能见度低，旗帜湿重下垂'],
  ['lib132','雪战','雪原上的行军，白茫茫，脚印与车辙，寒冷'],
  ['lib136','凯旋','得胜部队入城，百姓夹道，旌旗招展，暖色调'],
  ['lib206','粮道','运粮车队穿行于山道，牛车与民夫，尘土'],
  ['lib207','议事','汉代军帐内的沙盘与将领围立的剪影，烛光，无清晰面孔']
];

function buildSimple(list, category, sizeDefault, styleExtra) {
  return list.map(([file, label, desc]) => ({
    id: `${category}_${file}`,
    category, label,
    source: `/source/lib/${file}.png`,
    size: sizeDefault,
    prompt: `${STYLE_ANCHOR}\n\n${styleExtra}\n\n画面内容：${desc}。\n\n${NEGATIVE}`
  }));
}

/* ============ 主流程 ============ */
const csv = process.argv[2] || path.join(__dirname, 'officers.csv');
const catalog = [
  ...buildPortraits(csv),
  ...buildSimple(CG, 'cg', '1024x1536',
    '这是一张游戏用的全屏场景插画，不是人物立绘。构图要为界面留出可压字的负空间。'),
  ...buildSimple(SCENE, 'scene', '1536x1024',
    '这是一张横向的战斗场景插画。以远景/中景为主，人物不需要清晰面孔，重点是气氛与地形。')
];
fs.writeFileSync(path.join(__dirname, 'assets.json'), JSON.stringify(catalog, null, 2));
const byCat = {};
for (const a of catalog) byCat[a.category] = (byCat[a.category] || 0) + 1;
console.log('生成素材条目:', catalog.length);
for (const [k, v] of Object.entries(byCat)) console.log(`  ${k}: ${v}`);
