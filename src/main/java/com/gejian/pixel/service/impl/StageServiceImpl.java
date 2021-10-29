package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.stage.BackGroundDTO;
import com.gejian.pixel.dto.stage.PreRequestDTO;
import com.gejian.pixel.dto.stage.PreRequestTypeDTO;
import com.gejian.pixel.dto.stage.StageDTO;
import com.gejian.pixel.dto.stage.StageQueryDTO;
import com.gejian.pixel.entity.Stage;
import com.gejian.pixel.entity.StageBasicAwardFomula;
import com.gejian.pixel.entity.StageBossAwardFomula;
import com.gejian.pixel.entity.StageGoblinFomula;
import com.gejian.pixel.entity.StageMonsterKillFomula;
import com.gejian.pixel.enums.BackgroundEnum;
import com.gejian.pixel.enums.HeroLevelColorEnum;
import com.gejian.pixel.enums.ModeTypeEnum;
import com.gejian.pixel.mapper.StageMapper;
import com.gejian.pixel.proto.ConstStageTableItemExBasicAwardFomulaProtobuf;
import com.gejian.pixel.proto.ConstStageTableItemExBossAwardFomulaProtobuf;
import com.gejian.pixel.proto.ConstStageTableItemExGoblinFomulaProtobuf;
import com.gejian.pixel.proto.ConstStageTableItemExMonstersKilledAwardFomulaProtobuf;
import com.gejian.pixel.proto.ConstStageTableItemExProtobuf;
import com.gejian.pixel.proto.ConstStageTableProtobuf;
import com.gejian.pixel.proto.ConstTablesProtobuf;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.StageService;
import com.gejian.pixel.utils.StageUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class StageServiceImpl extends ServiceImpl<StageMapper, Stage> implements StageService, ConstantsProto {

	private static final int ATTR_SIZE = 4;

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
	public IPage<StageDTO> getPage(StageQueryDTO stageQueryDTO) {
		return null;
	}

	@Override
	public List<PreRequestDTO> getPreRequest(Integer difficulty) {

		LambdaQueryWrapper<Stage> wrapper = Wrappers.<Stage>lambdaQuery()
				.select(Stage::getId,Stage::getName, Stage::getMode)
				.le(Stage::getMode, difficulty);
		List<Stage> list = this.list(wrapper);
		Map<PreRequestDTO, List<PreRequestTypeDTO>> collect = list.stream().collect(Collectors.groupingBy(stage -> new PreRequestDTO(stage.getMode(), ModeTypeEnum.valueOf(stage.getMode()).getType()),
				Collectors.mapping(stage -> new PreRequestTypeDTO(stage.getId(), stage.getName()), Collectors.toList())));
		return collect.keySet().stream().peek(preRequestDTO -> preRequestDTO.setItems(collect.get(preRequestDTO))).collect(Collectors.toList());
	}

	@Override
	public List<BackGroundDTO> getBackground() {
		return BeanUtil.copyToList(Arrays.asList(BackgroundEnum.values()), BackGroundDTO.class);
	}

	@Override
	public IPage<StageDTO> selectPage(StageQueryDTO stageQueryDTO) {
		LambdaQueryWrapper<Stage> wrapper = new LambdaQueryWrapper<>();
		if(StrUtil.isNotBlank(stageQueryDTO.getName())){
			wrapper.like(Stage::getName,stageQueryDTO.getName());
		}
		if(Objects.nonNull(stageQueryDTO.getClassType())){
			wrapper.eq(Stage::getClassType,stageQueryDTO.getClassType());
		}
		Page<Stage> stagePage = this.page(stageQueryDTO.page(), wrapper);
		return stagePage.convert(this::convertToDTO);
	}

	@Override
	public StageDTO selectById(Integer id) {
		Stage stage = baseMapper.selectById(id);
		return convertToDTO(stage);
	}

	@Override
	public void createStage(StageDTO stageDTO) {
		this.save(convertToEntity(stageDTO));
	}

	@Override
	public void updateStage(StageDTO stageDTO) {
		this.updateById(convertToEntity(stageDTO));
	}

	@Override
	public void deleteById(Integer id){
		List<Stage> list = this.list(Wrappers.<Stage>lambdaQuery().eq(Stage::getPrerequest, id));
		if(!CollectionUtils.isEmpty(list)){
			throw new RuntimeException("此关卡已作为前置关卡，无法删除");
		}
		baseMapper.deleteById(id);
	}

	private Stage convertToEntity(StageDTO stageDTO){
		Stage stage	 = BeanUtil.copyProperties(stageDTO,Stage.class);
		buildMonster(stageDTO,stage);
		buildBoss(stageDTO,stage);
		buildGoblin(stageDTO,stage);
		buildBasic(stageDTO,stage);
		return stage;
	}

	private StageDTO convertToDTO(Stage stage){
		StageDTO stageDTO = BeanUtil.copyProperties(stage,StageDTO.class);
		buildMonster(stage,stageDTO);
		buildBoss(stage,stageDTO);
		buildGoblin(stage,stageDTO);
		buildBasic(stage,stageDTO);
		return stageDTO;
	}

	private void buildMonster(Stage stage,StageDTO stageDTO){
		List<Integer> monsterTypes = StageUtil.range(stage.getMonsters()).stream().map(HeroLevelColorEnum::getCode).collect(Collectors.toList());
		stageDTO.setMonsterTypes(monsterTypes);
		JSONArray monsterAttr = JSONArray.parseArray(stage.getMonstersAttributes());
		if(monsterAttr != null && monsterAttr.size() == ATTR_SIZE){
			stageDTO.setMonsterHp(monsterAttr.getInteger(0));
			stageDTO.setMonsterAttack(monsterAttr.getInteger(1));
			stageDTO.setMonsterDefense(monsterAttr.getInteger(2));
			stageDTO.setMonsterSpeed(monsterAttr.getInteger(3));
		}
		JSONArray monsterSkillsLevel = JSONArray.parseArray(stage.getMonsterSkillLevel());
		if(monsterSkillsLevel != null && monsterSkillsLevel.size() == ATTR_SIZE){
			stageDTO.setMonsterSkil1Level(monsterSkillsLevel.getInteger(0));
			stageDTO.setMonsterSkil2Level(monsterSkillsLevel.getInteger(1));
			stageDTO.setMonsterSkil3Level(monsterSkillsLevel.getInteger(2));
			stageDTO.setMonsterSkil4Level(monsterSkillsLevel.getInteger(3));
		}

		JSONObject monsterFomula = JSONObject.parseObject(stage.getMonstersKilledAwardFomula());
		stageDTO.setMobDropIncome(monsterFomula.getString("dropid"));
		stageDTO.setMonsterDropIncome(monsterFomula.getString("dropid_bosskilled"));
	}

	private void buildMonster(StageDTO stageDTO,Stage stage){
		List<HeroLevelColorEnum> colorEnums = stageDTO.getMonsterTypes().stream().map(HeroLevelColorEnum::valueOf).collect(Collectors.toList());
		stage.setMonsters(StageUtil.buildMonsters(colorEnums));
		stage.setMonstersAttributes(JSONArray.toJSONString(ImmutableList.of(stageDTO.getMonsterHp(),
				stageDTO.getMonsterAttack(),stageDTO.getMonsterDefense(),stageDTO.getMonsterSpeed())));
		stage.setMonsterSkillLevel(JSONArray.toJSONString(ImmutableList.of(stageDTO.getMonsterSkil1Level(),
				stageDTO.getMonsterSkil2Level(),stageDTO.getMonsterSkil3Level(),stageDTO.getMonsterSkil4Level())));
		stage.setMonstersKilledAwardFomula(JSONObject.toJSONString(ImmutableMap.of("dropid",stageDTO.getMobDropIncome(),
				"dropid_bosskilled",stageDTO.getMonsterDropIncome())));
		stage.setMonstersKilledAward(StageUtil.formatMonsterDrop(stageDTO.getMobDropIncome(),stageDTO.getMonsterDropIncome()));
	}

	private void buildGoblin(StageDTO stageDTO,Stage stage){
		stage.setGoblinFomula(JSONObject.toJSONString(ImmutableMap.of("hp",stageDTO.getGoblinHp().toString(),"pro","1",
				"monster",100,"dropid",stageDTO.getGoblinDrop())));
		stage.setGoblin(StageUtil.formatGoblinDrop(stageDTO.getGoblinHp(),stageDTO.getGoblinDrop()));
	}
	private void buildBoss(StageDTO stageDTO,Stage stage){
		stage.setBoss(StageUtil.formatBoss(stageDTO.getBossId()));
		stage.setBossAttributes(JSONArray.toJSONString(ImmutableList.of(stageDTO.getBossHp(),
				stageDTO.getBossAttack(),stageDTO.getBossDefense(),stageDTO.getBossSpeed())));
		stage.setBossSkillLevel(JSONArray.toJSONString(ImmutableList.of(stageDTO.getBossSkil1Level(),
				stageDTO.getBossSkil2Level(),stageDTO.getBossSkil3Level(),stageDTO.getBossSkil4Level())));
		stage.setBossAwardFomula(JSONObject.toJSONString(ImmutableMap.of("dropid",stageDTO.getBossDropIncome())));
		stage.setBossAward(StageUtil.formatBossDrop(stageDTO.getBossDropIncome()));
	}
	private void buildBasic(StageDTO stageDTO,Stage stage){
		stage.setBasicAwardFomula(JSONObject.toJSONString(ImmutableMap.of("exp",stageDTO.getBasicExpIncome(),
				"gold",stageDTO.getBasicGoldIncome(),"time",stageDTO.getBasicTimeIncome())));
		stage.setBasicAward(StageUtil.formatBasic(stageDTO.getBasicTimeIncome(),stageDTO.getBasicGoldIncome(),
				stageDTO.getBasicExpIncome()));
	}

	private void buildBoss(Stage stage,StageDTO stageDTO){
		stageDTO.setBossId(StageUtil.regBoss(stage.getBoss()));
		JSONArray bossAttr = JSONArray.parseArray(stage.getBossAttributes());
		if(bossAttr != null && bossAttr.size() == ATTR_SIZE){
			stageDTO.setBossHp(bossAttr.getInteger(0));
			stageDTO.setBossAttack(bossAttr.getInteger(1));
			stageDTO.setBossDefense(bossAttr.getInteger(2));
			stageDTO.setBossSpeed(bossAttr.getInteger(3));
		}
		JSONArray bossSkillsLevel = JSONArray.parseArray(stage.getBossSkillLevel());
		if(bossSkillsLevel != null && bossSkillsLevel.size() == ATTR_SIZE){
			stageDTO.setBossSkil1Level(bossSkillsLevel.getInteger(0));
			stageDTO.setBossSkil2Level(bossSkillsLevel.getInteger(1));
			stageDTO.setBossSkil3Level(bossSkillsLevel.getInteger(2));
			stageDTO.setBossSkil4Level(bossSkillsLevel.getInteger(3));
		}
		JSONObject bossFomula = JSONObject.parseObject(stage.getBossAwardFomula());
		stageDTO.setBossDropIncome(bossFomula.getString("dropid"));
	}

	private void buildGoblin(Stage stage,StageDTO stageDTO){
		JSONObject goblinFomula = JSONObject.parseObject(stage.getGoblinFomula());
		stageDTO.setGoblinHp(Integer.parseInt(goblinFomula.getString("hp")));
		stageDTO.setGoblinDrop(goblinFomula.getString("dropid"));
	}

	private void buildBasic(Stage stage,StageDTO stageDTO){
		JSONObject basicFomula = JSONObject.parseObject(stage.getBasicAwardFomula());
		stageDTO.setBasicTimeIncome(basicFomula.getInteger("time"));
		stageDTO.setBasicExpIncome(basicFomula.getInteger("exp"));
		stageDTO.setBasicGoldIncome(basicFomula.getInteger("gold"));
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