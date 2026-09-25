'use strict';
/* 从武将数据表生成「卡牌 + 头像」双份素材目录。
   卡牌 = 独立生成；头像 = 以卡牌为参考图派生，保证同一张脸。 */
const fs = require('node:fs');
const path = require('node:path');
const T = require('./traits.js');
const { ALLUSION, personaPose } = require('./allusions.js');
const { physique } = require('./appearance.js');

/* 君主与割据诸侯：身份由地位决定，不能用智武差值推成「文臣谋士」 */
const SOVEREIGN = {
  '献帝':'汉室天子', '刘备':'一方之主', '曹操':'一方之主', '孙权':'一方之主',
  '孙坚':'一方之主', '孙策':'一方之主', '袁绍':'一方诸侯', '袁术':'一方诸侯',
  '刘表':'一方诸侯', '刘焉':'一方诸侯', '刘璋':'一方诸侯', '陶谦':'一方诸侯',
  '韩馥':'一方诸侯', '孔融':'一方诸侯', '公孙瓒':'一方诸侯', '公孙渊':'割据之主',
  '张鲁':'一方诸侯', '马腾':'一方诸侯', '韩遂':'一方诸侯', '严白虎':'割据宗帅',
  '孟获':'南中之主', '刘虞':'一方诸侯', '吕布':'一方诸侯', '袁尚':'袁氏公子',
  '袁谭':'袁氏公子', '袁熙':'袁氏公子', '曹丕':'魏王世子', '曹叡':'魏帝',
  '司马炎':'晋帝',
};
/* 君主的服饰由地位决定，不能套用谋士的「羽扇竹简」或武将的扎甲 */
const SOVEREIGN_GARB = {
  '献帝':'身着十二章纹的玄纁冕服，头戴十二旒冕冠，玉带垂绶，一切都显得过于宽大',
  '曹叡':'魏帝冕服，玄色织金，冕旒垂面',
  '司马炎':'晋帝冕服，十二旒冕冠，手捧玉玺',
  '曹丕':'魏王世子的锦袍玉带，纹样繁复而克制',
};
const garbOf = name => SOVEREIGN_GARB[name]
  || (SOVEREIGN[name] ? '着诸侯规制的锦袍深衣，外罩织金大氅，腰佩长剑与印绶，不持文士器物' : null);

/* ============ 卡牌风格锚点（B · 高饱和游戏 CG）============
   只锁「画风 / 色系逻辑 / 面孔族裔 / 卡面构图」。
   姿势与光位由典故或性格决定，不写在这里。 */
const CARD_ANCHOR =
  '中式历史题材游戏卡牌插画——这是一幅叙事画，不是人物证件照。' +
  '画面必须定格一个具体的故事瞬间：这个人正在做一件具体的事，' +
  '处在一个具体的地点、具体的天气、具体的时辰里。' +
  '电影感的竖版卡面构图：人物占画面高度约六成，位于画面中下部，' +
  '其余空间留给能说明这一刻的环境——天光、建筑、器物、火光、远处的人影或队列。' +
  '环境不是虚化的背景板，而是叙事的另一半，要有明确的内容和纵深。' +
  '厚涂数字绘画，笔触精致收敛，强对比高饱和。' +
  '金属甲片有高光与环境色反射，织物有丝绸的光泽与厚度，皮肤通透有血色。' +
  '人物为东亚面孔，符合汉末中原或相应地域族群的相貌特征。' +
  '人体比例遵循真实解剖，头身比、肩宽、胸腰比都取自然写实的数值，不做任何夸张。' +
  '光线要参与叙事：让最重要的东西最亮。' +
  '画面中不得出现任何文字、字母、汉字、签名、水印、边框或UI元素。';

/* 头像由卡牌派生（走 images/edits 传参考图） */
const AVATAR_PROMPT =
  '以参考图中的人物为唯一依据，保持完全相同的面部特征、骨相、发型、发饰、' +
  '甲胄与服饰的款式和配色，就像同一位画师为同一个角色画的另一张图。' +
  '重新构图为游戏头像，正方形画面，取极近距离的头肩特写：' +
  '头顶几乎贴到画面上边缘（上方只留很窄的空隙），画面下边缘切在肩膀或锁骨处，' +
  '脸部水平居中，双眼落在画面上三分之一的高度线上。' +
  '画面里只能看见头、颈和肩，不要出现手、手臂、武器、腰部或腰部以下的任何部分。' +
  '背景替换为与人物配色协调的单一柔和色块或极简渐变，' +
  '去掉原有的全部场景、旗帜、花瓣、烟雾与杂物。' +
  '保持完全一致的厚涂画风、光照方向与色调。' +
  '画面中不得出现任何文字、字母、签名、水印、边框或UI元素。';

const NEGATIVE =
  '避免：照片写实、素描稿、线稿、水墨、水彩、平涂动漫、3D渲染感、塑料质感、' +
  '现代服装、文字水印。' +
  '最要避免的是：人物在虚化背景前呆站着摆姿势的证件照式构图——' +
  '画面里必须有正在发生的事。' +
  '尤其避免：整体欠曝、画面过暗、纯黑或纯色空背景、面部陷入阴影、' +
  '西方面孔、通用动漫脸、以及与其他立绘雷同的角度与表情。' +
  '画面必须保持可用于全年龄游戏的尺度：不出现血迹、血污、伤口、尸体、' +
  '倒地的人、断肢或任何血腥细节；兵器洁净无血，地面无尸骸。' +
  '战斗的激烈程度用姿态、尘烟、旌旗与光影来表现，不用血。';

const readCSV = p => fs.readFileSync(p,'utf8').split(/\r?\n/).filter(Boolean).map(l=>l.split(','));
const N = v => parseInt(v,10) || 0;

function buildPortraits(csvPath) {
  const rows = readCSV(csvPath);
  const seen = new Map();
  const out = [];

  for (const r of rows) {
    const [id, name, , bingzhong, bingli, tili, zhi, wu, de, zhong, jice] = r;
    const oid = N(id);
    const n = seen.get(name) || 0; seen.set(name, n + 1);
    const label = n ? `${name}(变体${n + 1})` : name;
    const isF = T.FEMALE.has(oid);

    /* --- 身份与外貌 --- */
    let subject, negative = NEGATIVE, roleName;
    if (isF) {
      const role = T.femaleRole(N(wu), N(zhi));
      roleName = { warrior_f:'女武将', talent_f:'才女', noble_f:'仕女' }[role];
      subject = T.ICONIC_F[name]
        ? `主体：${name}，三国时代女性人物。${T.ICONIC_F[name]}。`
        : [`主体：${name}，三国时代${roleName}`,
           `体貌：${physique(name, oid, N(r[13]), N(tili), true)}`,
           T.femaleBody(role, oid),
           T.gearAccent(bingzhong, role === 'warrior_f' ? 'warrior' : 'scholar')
          ].filter(Boolean).join('。') + '。';
      negative = NEGATIVE + T.FEMALE_NEGATIVE;
    } else {
      const role = T.roleOf(N(wu), N(zhi), bingli, jice);
      roleName = SOVEREIGN[name]
        || { mystic:'方士术士', scholar:'文臣谋士', warrior:'武将', officer:'武官' }[role];
      subject = T.ICONIC[name]
        ? `主体：${name}，三国时代人物。${T.ICONIC[name]}。`
        : [`主体：${name}，三国时代${roleName}`,
           `体貌：${physique(name, oid, N(r[13]), N(tili), false)}`,
           garbOf(name) || T.bodyTrait(role, N(wu)),
           garbOf(name) ? '' : T.gearAccent(bingzhong, role)
          ].filter(Boolean).join('。') + '。';
    }

    /* --- 姿势：典故优先，否则按性格推导 --- */
    const allu = ALLUSION[name];
    const pp = personaPose(N(wu), N(zhi), N(de), N(zhong), N(tili), isF);
    const scene = allu
      ? `画面情境（依据其生平的著名一刻）：${allu}。`
      : `姿态与神情（依据其性格）：${pp.pose}。`;
    const light = `光线：${pp.light}。`;

    const meta = { 性别: isF?'女':'男', 武力:N(wu), 智力:N(zhi), 德:N(de),
                   忠诚:N(zhong), 体力:N(tili), 登场年:N(r[13]),
                   兵种:['','平原军','水军','山地军'][N(bingzhong)]||'平原军',
                   典故: allu ? '有' : '性格推导' };

    out.push({
      id: `card_${id}`, category:'card', label, meta,
      source: `/source/avatar/${id}.png`, size:'1024x1536',
      prompt: `${CARD_ANCHOR}\n\n${scene}${light}\n\n${subject}\n\n${negative}`
    });
    out.push({
      id: `avatar_${id}`, category:'avatar', label, meta,
      source: `/source/avatar/${id}.png`, size:'1024x1024',
      refItem: `card_${id}`,
      prompt: AVATAR_PROMPT
    });
  }
  return out;
}

/* ============ CG / 场景（沿用，换成卡牌画风）============ */
const CG = [
  ['lib1','世界地图底图','中国汉末十三州的古地图，羊皮纸质感，墨线勾勒山川河流与城池，暗金与土褐，俯视平面视角'],
  ['lib13','标题主视觉','远景为烽烟中的城郭与旌旗，前景大片留白供标题压字，史诗感'],
  ['lib59','君主登基','汉代宫殿大殿内部，玉阶之上空置的主位，两侧列戟，烛火幽微，无人物'],
  ['lib106','战败灭亡','战后废墟，断折的旌旗半埋于焦土，远处城郭冒烟，荒凉肃杀，无人物'],
  ['lib302','内政建设','汉代城池内的市井与农田俯瞰，水渠纵横，谷仓与集市，秋收暖色调'],
];
const SCENE = [
  ['lib120','平原会战','开阔平原两军对垒远景，尘土飞扬，骑兵阵列'],
  ['lib121','山地伏击','狭窄山道，两侧陡壁，滚石与埋伏的旗影'],
  ['lib122','水战','长江江面的楼船与艨艟，浪涌，帆影，水汽'],
  ['lib123','攻城','高大夯土城墙下的云梯与冲车，箭雨'],
  ['lib124','火攻','夜色中的连营大火，火光映红江面与天空'],
  ['lib125','树林遭遇','茂密树林中的行军队列，光线破碎'],
  ['lib126','守城','城楼上俯瞰城下攻城部队'],
  ['lib127','骑兵冲锋','重骑兵正面冲锋，长矛前指，尘浪'],
  ['lib128','弓兵齐射','弓箭手阵列仰角齐射，箭矢离弦瞬间'],
  ['lib129','单挑','两名武将马上交锋的对峙瞬间，周围士兵退开成圆'],
  ['lib130','夜袭','月下潜行的部队，火把星点，营帐轮廓'],
  ['lib131','雨战','大雨中的泥泞战场，旗帜湿重下垂'],
  ['lib132','雪战','雪原行军，脚印与车辙，寒冷'],
  ['lib136','凯旋','得胜部队入城，百姓夹道，旌旗招展，暖色调'],
  ['lib206','粮道','运粮车队穿行山道，牛车与民夫'],
  ['lib207','议事','汉代军帐内的沙盘与将领围立的剪影，烛光'],
];
const buildSimple = (list, category, size, extra) => list.map(([f,label,desc]) => ({
  id:`${category}_${f}`, category, label, source:`/source/lib/${f}.png`, size,
  prompt:`${CARD_ANCHOR}\n\n${extra}\n\n画面内容：${desc}。\n\n${NEGATIVE}`
}));

module.exports = { buildPortraits, SOVEREIGN };
if (require.main === module) {
const csv = process.argv[2] || path.join(__dirname,'officers.csv');
const catalog = [
  ...buildPortraits(csv),
  ...buildSimple(CG,'cg','1024x1536','这是一张全屏场景插画，不是人物立绘。构图要为界面留出可压字的负空间。'),
  ...buildSimple(SCENE,'scene','1536x1024','这是一张横向战斗场景插画。以远景中景为主，人物不需要清晰面孔，重点是气氛与地形。'),
];
fs.writeFileSync(path.join(__dirname,'assets.json'), JSON.stringify(catalog,null,2));
const by={}; for(const a of catalog) by[a.category]=(by[a.category]||0)+1;
console.log('素材条目:', catalog.length);
for(const [k,v] of Object.entries(by)) console.log(`  ${k}: ${v}`);
}
