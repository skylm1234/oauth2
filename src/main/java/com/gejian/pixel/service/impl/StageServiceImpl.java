package com.gejian.pixel.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.*;
import com.gejian.pixel.mapper.StageMapper;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.StageService;
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
public class StageServiceImpl extends ServiceImpl<StageMapper, Stage> implements StageService, ConstantsProto {

	private Map<Integer, Stage> hash = new HashMap<>();

	private List<ConstStageTableItemExProtobuf.ConstStageTableItemEx> table = new ArrayList<>();

	@Override
	public Stage getById(Integer id){
		return hash.get(id);
	}

	@Override
	public ConstStageTableItemExProtobuf.ConstStageTableItemEx getItem(Integer id){
		return this.convert(hash.get(id));
	}

	@Override
	public void init() {
		List<Stage> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(item -> {
				hash.put(item.getId(), item);
				table.add(convert(item));
			});
		}
	}

	@Override
	public ConstStageTableItemExProtobuf.ConstStageTableItemEx getExById(Integer id){
		Stage stage = hash.get(id);
		return convert(stage);
	}

	private ConstStageTableItemExProtobuf.ConstStageTableItemEx convert(Stage item) {
		StageBasicAwardFomula stageBasicAwardFomula = JSONUtil
				.toBean(item.getBasicAwardFomula(), StageBasicAwardFomula.class);
		ConstStageTableItemExBasicAwardFomulaProtobuf.ConstStageTableItemExBasicAwardFomula awardFomula
				= ConstStageTableItemExBasicAwardFomulaProtobuf.ConstStageTableItemExBasicAwardFomula
				.newBuilder()
				.setExp(stageBasicAwardFomula.getExp())
				.setGold(stageBasicAwardFomula.getGold())
				.setTime(stageBasicAwardFomula.getTime())
				.build();
		StageBossAwardFomula stageBossAwardFomula = JSONUtil.toBean(item.getBossAwardFomula(), StageBossAwardFomula.class);
		ConstStageTableItemExBossAwardFomulaProtobuf.ConstStageTableItemExBossAwardFomula.Builder boss
				= ConstStageTableItemExBossAwardFomulaProtobuf.ConstStageTableItemExBossAwardFomula.newBuilder()
				.setDropid(stageBossAwardFomula.getDropid());
		StageMonsterKillFomula stageMonsterKillFomula = JSONUtil.toBean(item.getMonstersKilledAwardFomula()
				,StageMonsterKillFomula.class);
		ConstStageTableItemExMonstersKilledAwardFomulaProtobuf
				.ConstStageTableItemExMonstersKilledAwardFomula monstersKilledAwardFomula =
				ConstStageTableItemExMonstersKilledAwardFomulaProtobuf.ConstStageTableItemExMonstersKilledAwardFomula.newBuilder()
				.setDropid(stageMonsterKillFomula.getDropid())
				.setDropidBosskilled(stageMonsterKillFomula.getDropidBosskilled())
				.build();
		StageGoblinFomula stageGoblinFomula = JSONUtil.toBean(item.getGoblinFomula(),StageGoblinFomula.class);
		ConstStageTableItemExGoblinFomulaProtobuf.ConstStageTableItemExGoblinFomula goblinFomula =
				ConstStageTableItemExGoblinFomulaProtobuf.ConstStageTableItemExGoblinFomula.newBuilder()
				.setDropid(stageGoblinFomula.getDropid())
				.setHp(stageGoblinFomula.getHp())
				.setMonster(stageGoblinFomula.getMonster())
				.setPro(stageGoblinFomula.getPro())
				.build();
		return ConstStageTableItemExProtobuf.ConstStageTableItemEx.newBuilder()
				.setBasicAward(item.getBasicAward())
				.setBasicAwardFomula(awardFomula)
				.setBoss(item.getBoss())
				.setClassType(item.getClassType())
				.setId(item.getId())
				.setMode(item.getMode())
				.setMonsters(item.getMonsters())
				.setGoblin(item.getGoblin())
				.setWave(item.getWave())
				.setName(item.getName())
				.setIcon(item.getIcon())
				.setIconColor(item.getIconColor())
				.setBg(item.getBg())
				.setBgColor(item.getBgColor())
				.setRecommandPower(item.getRecommandPower())
				.setPrerequest(item.getPrerequest())
				.setBossSkillLevel(item.getBossSkillLevel())
				.setBossAward(item.getBossAward())
				.setBossAttributes(item.getBossAttributes())
				.setMonstersMax(item.getMonstersMax())
				.setMonstersAttributes(item.getMonstersAttributes())
				.setMonstersKilledAward(item.getMonstersKilledAward())
				.setBossAwardFomula(boss)
				.setMonstersKilledAwardFomula(monstersKilledAwardFomula)
				.setMonsterSkillLevel(item.getMonsterSkillLevel())
				.setGoblinFomula(goblinFomula).build();
	}

	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstStageTableProtobuf.ConstStageTable build = ConstStageTableProtobuf.ConstStageTable
				.newBuilder()
				.addAllItems(table).build();
		builder.setStages(build);
	}
}