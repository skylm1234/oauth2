package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.ActiveConsume;
import com.gejian.pixel.entity.Skill;
import com.gejian.pixel.entity.UpgradeConsume;
import com.gejian.pixel.mapper.SkillMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.SkillService;
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
public class SkillServiceImpl extends ServiceImpl<SkillMapper, Skill> implements SkillService
	, ConstantsProto {

	private Map<String, Skill> hash = new HashMap<>();

	private List<ConstSkillTableItemExProtobuf.ConstSkillTableItemEx>
			table = new ArrayList<>();

	@Override
	public void init() {
		List<Skill> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	private ConstSkillTableItemExProtobuf.ConstSkillTableItemEx convert(Skill item) {
		ActiveConsume activeConsume = JSONUtil.toBean(item.getActiveConsumeFomula(), ActiveConsume.class);
		ConstSkillTableItemExActiveConsumeFomulaProtobuf.ConstSkillTableItemExActiveConsumeFomula activeConsumeFomulaProto
				= ConstSkillTableItemExActiveConsumeFomulaProtobuf.ConstSkillTableItemExActiveConsumeFomula.newBuilder()
				.setGold(activeConsume.getGold())
				.setBook(activeConsume.getBook())
				.build();
		UpgradeConsume upgradeConsume = JSONUtil.toBean(item.getUpgradeConsumeFomula(), UpgradeConsume.class);
		ConstSkillTableItemExUpgradeConsumeFomulaProtobuf.ConstSkillTableItemExUpgradeConsumeFomula build = ConstSkillTableItemExUpgradeConsumeFomulaProtobuf.ConstSkillTableItemExUpgradeConsumeFomula.newBuilder()
				.setX1(upgradeConsume.getX1().toProto())
				.setX2(upgradeConsume.getX2().toProto())
				.setX3(upgradeConsume.getX3().toProto())
				.setX4(upgradeConsume.getX4().toProto())
				.setX5(upgradeConsume.getX5().toProto())
				.setX6(upgradeConsume.getX6().toProto())
				.setX7(upgradeConsume.getX7().toProto())
				.setX8(upgradeConsume.getX8().toProto())
				.setX9(upgradeConsume.getX9().toProto())
				.build();
		return ConstSkillTableItemExProtobuf.ConstSkillTableItemEx
				.newBuilder()
				.setDesc(item.getDesc())
				.setId(item.getId())
				.setName(item.getName())
				.setSkillIcon(item.getSkillIcon())
				.setActiveConsume(item.getActiveConsume())
				.setUpgradeConsume(item.getUpgradeConsume())
				.setCoolwn(item.getCooldown())
				.setLevelvalue(item.getLevelvalue())
				.setLogic(item.getLogic())
				.setSelecter(item.getSelecter())
				.setActiveConsumeFomula(activeConsumeFomulaProto)
				.setUpgradeConsumeFomula(build)
				.build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstSkillTableProtobuf.ConstSkillTable build =
				ConstSkillTableProtobuf.ConstSkillTable.newBuilder()
				.addAllItems(table)
				.build();
		builder.setSkills(build);
	}
}