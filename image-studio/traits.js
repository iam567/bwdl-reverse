'use strict';
/* 身份与外貌特征模块（女性 / 方士 / 文武倾向）——从 build-catalog.js 抽出 */
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
  if (matron) return '着深色曲裾深衣，不着甲，发髻插玉饰';
  if (role === 'warrior_f')
    return '着合身的女式轻甲（护肩、护臂），衣甲之下是行动便利的战裙';
  if (role === 'talent_f')
    return '不着甲，着广袖交领深衣，衣料柔软有垂坠感';
  return '不着甲，着曲裾深衣或襦裙，衣饰精致';
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
  '严禁：夸张放大的胸部、强调胸腰臀曲线的镜头角度、低胸或敞开的领口、' +
  '露出的大腿或腰腹、紧身贴体的轮廓、任何性暗示的姿态或表情。' +
  '着装必须完整保守，领口闭合至锁骨以上，符合汉末服制。' +
  '同时避免：现代服饰、比基尼、动漫萌系画风。';

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
    return '着道家鹤氅或宽大法袍，不着任何甲胄，手持卜筮用的蓍草、罗盘或拂尘';
  if (role === 'scholar')
    return '不着甲，着宽袖交领深衣，手持羽扇或竹简';
  if (role === 'warrior') {
    if (wu >= 110) return '重甲在身，握持长柄重兵';
    if (wu >= 95)  return '着明光重甲，护肩厚重';
    return '着扎甲';
  }
  return '着轻便鳞甲外罩官袍';
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


module.exports = { FEMALE, ELDER_F, MATRON_F, ICONIC_F, MYSTIC_YOUNG, ICONIC,
  femaleRole, femaleBody, femaleFace, FEMALE_NEGATIVE,
  roleOf, bodyTrait, faceTrait, gearAccent, moodTrait };
