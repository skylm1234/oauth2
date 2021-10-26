package com.gejian.pixel.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.HeroAttributeDTO;
import com.gejian.pixel.dto.HeroDTO;
import com.gejian.pixel.dto.HeroListRequestDTO;
import com.gejian.pixel.dto.HeroListResponseDTO;
import com.gejian.pixel.dto.SkillDTO;
import com.gejian.pixel.entity.BasicExpand;
import com.gejian.pixel.entity.BasicUpgradeExpand;
import com.gejian.pixel.entity.Hero;
import com.gejian.pixel.entity.Skill;
import com.gejian.pixel.entity.StarUpgradeFomula;
import com.gejian.pixel.enums.HeroLevelColorEnum;
import com.gejian.pixel.enums.HeroRoleEnum;
import com.gejian.pixel.mapper.HeroMapper;
import com.gejian.pixel.proto.ConstHeroTableItemExBasicExpandProtobuf;
import com.gejian.pixel.proto.ConstHeroTableItemExBasicUpgradeExpandProtobuf;
import com.gejian.pixel.proto.ConstHeroTableItemExProtobuf;
import com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaProtobuf;
import com.gejian.pixel.proto.ConstHeroTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.HeroService;
import com.gejian.pixel.service.SkillService;
import com.gejian.pixel.utils.HeroUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class HeroServiceImpl extends ServiceImpl<HeroMapper, Hero> implements HeroService, ConstantsProto {

	@Autowired
	private SkillService skillService;

	private Map<Integer, Hero> hash = new HashMap<>();

	private List<ConstHeroTableItemExProtobuf.ConstHeroTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init() {
		List<Hero> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	@Override
	public Map<Integer, Hero> getHash() {
		return hash;
	}

	@Override
	public Hero getById(Integer id) {
		return hash.get(id);
	}

	private ConstHeroTableItemExProtobuf.ConstHeroTableItemEx convert(Hero item) {
		BasicExpand basicExpand = JSONUtil.toBean(item.getBasicExpand(), BasicExpand.class);
		ConstHeroTableItemExBasicExpandProtobuf.ConstHeroTableItemExBasicExpand basicExpandProto
				= ConstHeroTableItemExBasicExpandProtobuf.ConstHeroTableItemExBasicExpand.newBuilder()
				.setAttack(basicExpand.getAttack())
				.setDefense(basicExpand.getDefense())
				.setHp(basicExpand.getHp())
				.setSpeed(basicExpand.getSpeed()).build();
		BasicUpgradeExpand basicUpgradeExpand = JSONUtil
				.toBean(item.getBasicUpgradeExpand(), BasicUpgradeExpand.class);
		ConstHeroTableItemExBasicUpgradeExpandProtobuf.ConstHeroTableItemExBasicUpgradeExpand basicUpgradeExpandProto
				= ConstHeroTableItemExBasicUpgradeExpandProtobuf.ConstHeroTableItemExBasicUpgradeExpand.newBuilder()
				.setAttack(basicUpgradeExpand.getAttack())
				.setDefense(basicUpgradeExpand.getDefense())
				.setHp(basicUpgradeExpand.getHp())
				.setSpeed(basicUpgradeExpand.getSpeed())
				.build();
		StarUpgradeFomula starUpgradeFomula = JSONUtil.toBean(item.getStarUpgradeFomula(), StarUpgradeFomula.class);
		ConstHeroTableItemExStarUpgradeFomulaProtobuf.ConstHeroTableItemExStarUpgradeFomula starUpgradeFomulaProto
				= ConstHeroTableItemExStarUpgradeFomulaProtobuf.ConstHeroTableItemExStarUpgradeFomula.newBuilder()
				.setStar1(starUpgradeFomula.getStar1().toProto())
				.setStar2(starUpgradeFomula.getStar2().toProto())
				.setStar3(starUpgradeFomula.getStar3().toProto())
				.setStar4(starUpgradeFomula.getStar4().toProto())
				.setStar5(starUpgradeFomula.getStar5().toProto())
				.setStar6(starUpgradeFomula.getStar6().toProto())
				.setStar7(starUpgradeFomula.getStar7().toProto())
				.setStar8(starUpgradeFomula.getStar8().toProto())
				.setStar9(starUpgradeFomula.getStar9().toProto())
				.setStar10(starUpgradeFomula.getStar10().toProto())
				.setStar11(starUpgradeFomula.getStar11().toProto())
				.setStar12(starUpgradeFomula.getStar12().toProto())
				.setStar13(starUpgradeFomula.getStar13().toProto())
				.setStar14(starUpgradeFomula.getStar14().toProto())
				.setStar15(starUpgradeFomula.getStar15().toProto())
				.setStar16(starUpgradeFomula.getStar16().toProto())
				.setStar17(starUpgradeFomula.getStar17().toProto())
				.setStar18(starUpgradeFomula.getStar18().toProto())
				.setStar19(starUpgradeFomula.getStar19().toProto())
				.setStar20(starUpgradeFomula.getStar20().toProto())
				.setStar21(starUpgradeFomula.getStar21().toProto())
				.setStar22(starUpgradeFomula.getStar22().toProto())
				.setStar23(starUpgradeFomula.getStar23().toProto())
				.setStar24(starUpgradeFomula.getStar24().toProto())
				.setStar25(starUpgradeFomula.getStar25().toProto())
				.build();
		return ConstHeroTableItemExProtobuf.ConstHeroTableItemEx.newBuilder()
				.setType(item.getType())
				.setAlive(item.getAlive())
				.setBasic(item.getBasic())
				.setId(item.getId())
				.setName(item.getName())
				.setDesc(item.getDesc())
				.setColor(item.getColor())
				.setBasicUpgrade(item.getBasicUpgrade())
				.setSkill(item.getSkill())
				.setStarUpgrade(item.getStarUpgrade())
				.setChips(item.getChips())
				.setGroup1(item.getGroup1())
				.setGroup1Skill(item.getGroup1Skill())
				.setGroup2(item.getGroup2())
				.setGroup2Skill(item.getGroup2Skill())
				.setSkill1(item.getSkill1())
				.setSkill1Name(item.getSkill1Name())
				.setSkill2(item.getSkill2())
				.setSkill2Name(item.getSkill2Name())
				.setSkill3(item.getSkill3())
				.setSkill3Name(item.getSkill3Name())
				.setSkill4(item.getSkill4())
				.setSkill4Name(item.getSkill4Name())
				.setSkillA(item.getSkillA())
				.setSquad1(item.getSquad1())
				.setSquad2(item.getSquad2())
				.setBasicExpand(basicExpandProto)
				.setBasicUpgradeExpand(basicUpgradeExpandProto)
				.setStarUpgradeFomula(starUpgradeFomulaProto)
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstHeroTableProtobuf.ConstHeroTable build = ConstHeroTableProtobuf.ConstHeroTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setHeros(build);
	}

	@Override
	public ConstHeroTableItemExProtobuf.ConstHeroTableItemEx getItem(Integer id) {
		return this.convert(hash.get(id));
	}

	@Override
	public IPage<HeroListResponseDTO> selectPage(HeroListRequestDTO heroListRequestDTO) {
		LambdaQueryWrapper<Hero> queryWrapper = Wrappers.lambdaQuery();
		if(heroListRequestDTO.getColor() != null){
			queryWrapper.eq(Hero::getColor,heroListRequestDTO.getColor());
		}
		if(heroListRequestDTO.getRole() != null){
			queryWrapper.eq(Hero::getType,heroListRequestDTO.getRole());
		}
		if(StrUtil.isNotBlank(heroListRequestDTO.getName())){
			queryWrapper.like(Hero::getName,heroListRequestDTO.getName());
		}
		Page<Hero> page = this.page(heroListRequestDTO.getPage(), queryWrapper);
		if(page.getRecords().isEmpty()){
			return new Page<>(heroListRequestDTO.getCurrent(),heroListRequestDTO.getSize());
		}
		List<HeroListResponseDTO> responseDTOList = page.getRecords().stream().map(hero -> {
			HeroListResponseDTO heroListResponseDTO = new HeroListResponseDTO();
			heroListResponseDTO.setId(hero.getId());
			heroListResponseDTO.setName(hero.getName());
			heroListResponseDTO.setRole(HeroRoleEnum.valueOf(hero.getType()));
			heroListResponseDTO.setColor(HeroLevelColorEnum.valueOf(hero.getColor()));
			heroListResponseDTO.setSkill1(hero.getSkill1Name());
			heroListResponseDTO.setSkill2(hero.getSkill2Name());
			heroListResponseDTO.setSkill3(hero.getSkill3Name());
			heroListResponseDTO.setSkill4(hero.getSkill4Name());
			heroListResponseDTO.setAlivePrice(hero.getAlive());
			replaceData(heroListResponseDTO,hero);
			return heroListResponseDTO;
		}).collect(Collectors.toList());
		Page<HeroListResponseDTO> resultPage = new Page<>(heroListRequestDTO.getCurrent(), heroListRequestDTO.getSize(), page.getTotal());
		resultPage.setRecords(responseDTOList);
		return resultPage;
	}

	@Override
	public Optional<HeroDTO> selectById(Integer id) {
		Hero hero = getById(id);
		if(hero == null){
			return Optional.empty();
		}else{
			return Optional.of(convertToHeroDTO(hero));
		}
	}

	@Override
	public void update(HeroDTO heroDTO) {
		this.updateById(convertToHero(heroDTO));
	}

	private Hero convertToHero(HeroDTO heroDTO){
		Map<String, String> skills = skillService.list().stream().collect(Collectors.toMap(Skill::getId, Skill::getName));
		Hero hero = new Hero();
		hero.setId(heroDTO.getId());
		hero.setAlive(heroDTO.getAlivePrice());
		hero.setColor(heroDTO.getColor().getCode());
		hero.setType(heroDTO.getRole().getCode());
		hero.setName(heroDTO.getName());
		hero.setSkill1(heroDTO.getSkill1().getId());
		hero.setSkill1Name(skills.get(heroDTO.getSkill1().getId()));
		hero.setSkill2(heroDTO.getSkill2().getId());
		hero.setSkill2Name(skills.get(heroDTO.getSkill2().getId()));
		hero.setSkill3(heroDTO.getSkill3().getId());
		hero.setSkill3Name(skills.get(heroDTO.getSkill3().getId()));
		hero.setSkill4(heroDTO.getSkill4().getId());
		hero.setSkill4Name(skills.get(heroDTO.getSkill4().getId()));
		ImmutableMap<Object, Object> basicExpand = ImmutableMap.builder().put(HeroUtil.HP, heroDTO.getBasicData().getHp())
				.put(HeroUtil.ATTACK, heroDTO.getBasicData().getAttack())
				.put(HeroUtil.DEFENSE, heroDTO.getBasicData().getDefense()).put(HeroUtil.SPEED, heroDTO.getBasicData().getSpeed()).build();
		hero.setBasicExpand(JSONObject.toJSONString(basicExpand));
		ImmutableList<Integer> basic = ImmutableList.of(heroDTO.getBasicData().getHp(), heroDTO.getBasicData().getAttack(),
				heroDTO.getBasicData().getDefense(), heroDTO.getBasicData().getSpeed());
		hero.setBasic(JSONArray.toJSONString(basic));

		ImmutableMap<Object, Object> upgradeExpand = ImmutableMap.builder().put(HeroUtil.HP, heroDTO.getUpgradeData().getHp())
				.put(HeroUtil.ATTACK, heroDTO.getUpgradeData().getAttack())
				.put(HeroUtil.DEFENSE, heroDTO.getUpgradeData().getDefense()).put(HeroUtil.SPEED, heroDTO.getUpgradeData().getSpeed()).build();
		hero.setBasicUpgradeExpand(JSONObject.toJSONString(upgradeExpand));
		ImmutableList<Integer> upgrade = ImmutableList.of(heroDTO.getUpgradeData().getHp(), heroDTO.getUpgradeData().getAttack(),
				heroDTO.getUpgradeData().getDefense(), heroDTO.getUpgradeData().getSpeed());
		hero.setBasicUpgrade(JSONArray.toJSONString(upgrade));
		hero.setStarUpgradeFomula(buildUpgradeFomula(heroDTO));
		hero.setStarUpgrade(buildUpgrade(heroDTO));
		return hero;
	}

	private List<HeroAttributeDTO> allOfAttributes(HeroDTO heroDTO){
		return ImmutableList.<HeroAttributeDTO>builder().add(heroDTO.getBasic1_5Data()).add(heroDTO.getUpgrade1_5Data())
				.add(heroDTO.getBasic6_10Data()).add(heroDTO.getUpgrade6_10Data())
				.add(heroDTO.getBasic11_15Data()).add(heroDTO.getUpgrade11_15Data())
				.add(heroDTO.getBasic16_20Data()).add(heroDTO.getUpgrade16_20Data())
				.add(heroDTO.getBasic21_25Data()).add(heroDTO.getUpgrade21_25Data()).build();
	}

	private String buildUpgradeFomula(HeroDTO heroDTO){
		List<HeroAttributeDTO> list = allOfAttributes(heroDTO);
		Map<String, Map<String,Integer>> map = new TreeMap<>();
		for(int i = 0; i < list.size(); i += 2){
			for(int j = 1; j <= 5; j++){
				HeroAttributeDTO basic = list.get(i);
				HeroAttributeDTO upgrade = list.get(i + 1);
				ImmutableMap<String,Integer> basicMap = ImmutableMap.<String,Integer>builder().put(HeroUtil.HP, basic.getHp()).put(HeroUtil.ATTACK, basic.getAttack())
						.put(HeroUtil.DEFENSE, basic.getDefense()).put(HeroUtil.SPEED, basic.getSpeed())
						.put(HeroUtil.HP_UPGRADE, upgrade.getHp()).put(HeroUtil.ATTACK_UPGRADE, upgrade.getAttack())
						.put(HeroUtil.DEFENSE_UPGRADE, upgrade.getDefense()).put(HeroUtil.SPEED_UPGRADE, upgrade.getSpeed()).build();
				int index =  (i / 2) * 5 +  j;
				map.put(HeroUtil.STAR_PREFIX + index,basicMap);
			}
		}
		return JSONObject.toJSONString(map);
	}

	private String buildUpgrade(HeroDTO heroDTO){
		StringBuilder stringBuilder = new StringBuilder();
		List<HeroAttributeDTO> list = allOfAttributes(heroDTO);
		for(int i = 0; i < list.size(); i += 2){
			int index = (i / 2) * 5 + 1;
			stringBuilder.append("[").append(HeroUtil.LEVEL_EN_MAP.get(index)).append(",").append(HeroUtil.BASIC_FIXED);
			HeroAttributeDTO basic = list.get(i);
			HeroAttributeDTO upgrade = list.get(i + 1);
			ImmutableList<Integer> basicList = ImmutableList.of(basic.getHp(), basic.getAttack(), basic.getDefense(), basic.getSpeed());
			ImmutableList<Integer> upgradeList = ImmutableList.of(upgrade.getHp(),upgrade.getAttack(), upgrade.getDefense(), upgrade.getSpeed());
			stringBuilder.append(JSONArray.toJSONString(basicList)).append(",");
			stringBuilder.append(HeroUtil.UPGRADE_FIXED).append(JSONArray.toJSONString(upgradeList)).append("]");
			if( i != list.size() - 2){
				stringBuilder.append("$");
			}
		}
		return stringBuilder.toString();
	}

	private HeroDTO convertToHeroDTO(Hero hero){
		HeroDTO heroDTO = new HeroDTO();
		heroDTO.setId(hero.getId());
		heroDTO.setName(hero.getName());
		heroDTO.setRole(HeroRoleEnum.valueOf(hero.getType()));
		heroDTO.setColor(HeroLevelColorEnum.valueOf(hero.getColor()));
		JSONObject basic = JSONObject.parseObject(hero.getBasicExpand());
		JSONObject upgrade = JSONObject.parseObject(hero.getBasicUpgradeExpand());
		heroDTO.setBasicData(HeroAttributeDTO.builder().hp(basic.getInteger(HeroUtil.HP)).attack(basic.getInteger(HeroUtil.ATTACK))
				.defense(basic.getInteger(HeroUtil.DEFENSE)).speed(basic.getInteger(HeroUtil.SPEED)).build());
		heroDTO.setUpgradeData(HeroAttributeDTO.builder().hp(upgrade.getInteger(HeroUtil.HP)).attack(upgrade.getInteger(HeroUtil.ATTACK))
				.defense(upgrade.getInteger(HeroUtil.DEFENSE)).speed(upgrade.getInteger(HeroUtil.SPEED)).build());
		JSONObject starUpgrade = JSONObject.parseObject(hero.getStarUpgradeFomula());
		for(int i = 1; i <= 25; i += 5 ){
			JSONObject numerical = starUpgrade.getJSONObject(HeroUtil.STAR_PREFIX + i);
			switch (i){
				case 1:
					heroDTO.setBasic1_5Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP)).attack(numerical.getInteger(HeroUtil.ATTACK))
							.defense(numerical.getInteger(HeroUtil.DEFENSE)).speed(numerical.getInteger(HeroUtil.SPEED)).build());
					heroDTO.setUpgrade1_5Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP_UPGRADE)).attack(numerical.getInteger(HeroUtil.ATTACK_UPGRADE))
							.defense(numerical.getInteger(HeroUtil.DEFENSE_UPGRADE)).speed(numerical.getInteger(HeroUtil.SPEED_UPGRADE)).build());
					break;
				case 6:
					heroDTO.setBasic6_10Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP)).attack(numerical.getInteger(HeroUtil.ATTACK))
							.defense(numerical.getInteger(HeroUtil.DEFENSE)).speed(numerical.getInteger(HeroUtil.SPEED)).build());
					heroDTO.setUpgrade6_10Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP_UPGRADE)).attack(numerical.getInteger(HeroUtil.ATTACK_UPGRADE))
							.defense(numerical.getInteger(HeroUtil.DEFENSE_UPGRADE)).speed(numerical.getInteger(HeroUtil.SPEED_UPGRADE)).build());
					break;
				case 11:
					heroDTO.setBasic11_15Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP)).attack(numerical.getInteger(HeroUtil.ATTACK))
							.defense(numerical.getInteger(HeroUtil.DEFENSE)).speed(numerical.getInteger(HeroUtil.SPEED)).build());
					heroDTO.setUpgrade11_15Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP_UPGRADE)).attack(numerical.getInteger(HeroUtil.ATTACK_UPGRADE))
							.defense(numerical.getInteger(HeroUtil.DEFENSE_UPGRADE)).speed(numerical.getInteger(HeroUtil.SPEED_UPGRADE)).build());
					break;
				case 16:
					heroDTO.setBasic16_20Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP)).attack(numerical.getInteger(HeroUtil.ATTACK))
							.defense(numerical.getInteger(HeroUtil.DEFENSE)).speed(numerical.getInteger(HeroUtil.SPEED)).build());
					heroDTO.setUpgrade16_20Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP_UPGRADE)).attack(numerical.getInteger(HeroUtil.ATTACK_UPGRADE))
							.defense(numerical.getInteger(HeroUtil.DEFENSE_UPGRADE)).speed(numerical.getInteger(HeroUtil.SPEED_UPGRADE)).build());
					break;
				case 21:
					heroDTO.setBasic21_25Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP)).attack(numerical.getInteger(HeroUtil.ATTACK))
							.defense(numerical.getInteger(HeroUtil.DEFENSE)).speed(numerical.getInteger(HeroUtil.SPEED)).build());
					heroDTO.setUpgrade21_25Data(HeroAttributeDTO.builder().hp(numerical.getInteger(HeroUtil.HP_UPGRADE)).attack(numerical.getInteger(HeroUtil.ATTACK_UPGRADE))
							.defense(numerical.getInteger(HeroUtil.DEFENSE_UPGRADE)).speed(numerical.getInteger(HeroUtil.SPEED_UPGRADE)).build());
					break;
				default:break;
			}
		}
		heroDTO.setSkill1(SkillDTO.builder().id(hero.getSkill1()).name(hero.getSkill1Name()).build());
		heroDTO.setSkill2(SkillDTO.builder().id(hero.getSkill2()).name(hero.getSkill2Name()).build());
		heroDTO.setSkill3(SkillDTO.builder().id(hero.getSkill3()).name(hero.getSkill3Name()).build());
		heroDTO.setSkill4(SkillDTO.builder().id(hero.getSkill4()).name(hero.getSkill4Name()).build());
		heroDTO.setAlivePrice(hero.getAlive());
		return heroDTO;
	}

	private void replaceData(HeroListResponseDTO heroListResponseDTO,Hero hero){
		String starUpgradeFomula = hero.getStarUpgradeFomula();
		if(StrUtil.isNotBlank(starUpgradeFomula)){
			StringBuilder basicStrBuilder = new StringBuilder(HeroUtil.BASIC_REP);
			StringBuilder upgradeStrBuilder = new StringBuilder(HeroUtil.BASIC_REP);
			basicStrBuilder.append(hero.getBasic()).append(" ");
			upgradeStrBuilder.append(hero.getBasicUpgrade()).append(" ");
			JSONObject jsonObject = JSONObject.parseObject(starUpgradeFomula);
			for(int i = 1; i <= 25; i += 5 ){
				JSONObject numerical = jsonObject.getJSONObject(HeroUtil.STAR_PREFIX + i);
				basicStrBuilder.append(HeroUtil.LEVEL_CN_MAP.get(i));
				basicStrBuilder.append("[").append(numerical.get(HeroUtil.HP)).append(",").append(numerical.get(HeroUtil.ATTACK))
								.append(",").append(numerical.get(HeroUtil.DEFENSE)).append(",").append(numerical.get(HeroUtil.SPEED))
								.append("]").append(" ");
				upgradeStrBuilder.append(HeroUtil.LEVEL_CN_MAP.get(i));
				upgradeStrBuilder.append("[").append(numerical.get(HeroUtil.HP_UPGRADE)).append(",").append(numerical.get(HeroUtil.ATTACK_UPGRADE))
						.append(",").append(numerical.get(HeroUtil.DEFENSE_UPGRADE)).append(",").append(numerical.get(HeroUtil.SPEED_UPGRADE))
						.append("]").append(" ");
			}
			heroListResponseDTO.setBasicData(basicStrBuilder.toString());
			heroListResponseDTO.setUpgradeData(upgradeStrBuilder.toString());
		}
	}
}