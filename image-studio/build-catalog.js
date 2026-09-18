'use strict';
/* 从武将数据表生成「卡牌 + 头像」双份素材目录。
   卡牌 = 独立生成；头像 = 以卡牌为参考图派生，保证同一张脸。 */
const fs = require('node:fs');
const path = require('node:path');
const T = require('./traits.js');
const { ALLUSION, personaPose } = require('./allusions.js');

/* ============ 卡牌风格锚点（B · 高饱和游戏 CG）============
   只锁「画风 / 色系逻辑 / 面孔族裔 / 卡面构图」。
   姿势与光位由典故或性格决定，不写在这里。 */
const CARD_ANCHOR =
  '高品质中式卡牌游戏角色立绘，SSR 级视觉冲击力。厚涂数字绘画，笔触精致收敛。' +
  '强对比高饱和——金属甲片有明确的高光与环境色反射，织物有丝绸的光泽与厚度，皮肤通透有血色。' +
  '人物为东亚面孔，符合汉末中原或相应地域族群的相貌特征，骨相与五官比例真实可信。' +
  '背景是虚化的场景氛围，有纵深、空气感与飘散的微粒，不要空洞的纯色背景。' +
  '竖版卡面构图：人物取景在腰部以上，占画面高度约八成，头部位于画面上部三分之一的中央区域。' +
  '细节密度拉满，甲胄纹样、织物编织、武器配饰都经得起放大看。' +
  '画面中不得出现任何文字、字母、汉字、签名、水印、边框或UI元素。';

/* 头像由卡牌派生（走 images/edits 传参考图） */
const AVATAR_PROMPT =
  '以参考图中的人物为唯一依据，保持完全相同的面部特征、骨相、发型、发饰、' +
  '甲胄与服饰的款式和配色，就像同一位画师为同一个角色画的另一张图。' +
  '重新构图为游戏头像：正方形画面，胸部以上的紧凑半身，头部占画面高度约百分之六十，' +
  '面部清晰居中，略带四分之三侧角，视线朝向观者。' +
  '背景简化为与人物配色协调的单一柔和色块或极简渐变，去掉原有的复杂场景、旗帜与杂物。' +
  '保持完全一致的厚涂画风、光照方向与色调。' +
  '画面中不得出现任何文字、字母、签名、水印、边框或UI元素。';

const NEGATIVE =
  '避免：照片写实、素描稿、线稿、水墨、水彩、平涂动漫、3D渲染感、塑料质感、' +
  '现代服装、多个人物、文字水印、构图呆板的证件照。' +
  '尤其避免：整体欠曝、画面过暗、纯黑或纯色空背景、面部陷入阴影、' +
  '西方面孔、通用动漫脸、以及与其他立绘雷同的角度与表情。';

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
           T.femaleBody(role, oid), T.femaleFace(role, N(de), oid),
           T.gearAccent(bingzhong, role === 'warrior_f' ? 'warrior' : 'scholar')
          ].join('。') + '。';
      negative = NEGATIVE + T.FEMALE_NEGATIVE;
    } else {
      const role = T.roleOf(N(wu), N(zhi), bingli, jice);
      roleName = { mystic:'方士术士', scholar:'文臣谋士', warrior:'武将', officer:'武官' }[role];
      subject = T.ICONIC[name]
        ? `主体：${name}，三国时代人物。${T.ICONIC[name]}。`
        : [`主体：${name}，三国时代${roleName}`,
           T.bodyTrait(role, N(wu)), T.faceTrait(role, N(wu), N(zhi), N(de), oid),
           T.gearAccent(bingzhong, role)
          ].join('。') + '。';
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
