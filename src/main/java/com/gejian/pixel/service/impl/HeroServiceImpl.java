package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.BasicExpand;
import com.gejian.pixel.entity.BasicUpgradeExpand;
import com.gejian.pixel.entity.Hero;
import com.gejian.pixel.entity.StarUpgradeFomula;
import com.gejian.pixel.mapper.HeroMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.HeroService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class HeroServiceImpl extends ServiceImpl<HeroMapper, Hero> implements HeroService, ConstantsProto {

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
}