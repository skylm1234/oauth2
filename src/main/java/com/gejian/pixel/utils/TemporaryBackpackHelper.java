package com.gejian.pixel.utils;

import cn.hutool.core.bean.BeanUtil;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.entity.LevelUpgrade;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.LevelUpgradeService;
import com.gejian.pixel.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Reese
 * @date 2021/9/2
 */
@Component
public class TemporaryBackpackHelper {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StageService stageService;

	@Autowired
	private BackpackService backpackService;

	@Autowired
	private DropService dropService;

	@Autowired
	private LevelUpgradeService levelUpgradeService;

	/**
	 * 修改临时背包
	 */
	public void updateTemporaryBackpack(Integer identifier, CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest request,
										CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.Builder builder, int monsters, int goblins) {

		String tempBackpackKey = this.getTempBackpackKey(identifier);

		Map<Object, Object> pack = this.redisTemplate.opsForHash().entries(tempBackpackKey);

		String type = this.parseString(pack.get("type"));
		PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "type_" + type + "_monster_kill", monsters, identifier);
		builder.addArchives(playerItem);

		PlayerItemProtobuf.PlayerItem playerItem1 = Helper.onNotifyEventOfPromotions(redisTemplate, "daily_monster_kill", monsters, identifier);
		builder.addArchives(playerItem1);

		PlayerItemProtobuf.PlayerItem playerItem2 = Helper.onNotifyEventOfPromotions(redisTemplate, "daily_type_" + type + "_monster_kill", monsters, identifier);
		builder.addArchives(playerItem2);

		int vip = Helper.itemCount(redisTemplate, identifier, "vip");

		long duration = Helper.currentTimestamp() - this.parseLong(pack.get("dungeon_enter_timestamp"));
		if (duration<0) {
			duration = 1L;
		}

		Integer stage = this.parseInt(pack.get("stage"));

		ConstStageTableItemExProtobuf.ConstStageTableItemEx constStageTableItemEx =
				this.stageService.getItem(stage);

		Integer level = this.parseInt(pack.get("level"));

		String itemKey = this.getItemKey(identifier);
		String tempBackpackItemsKey = getTempBackpackItemsKey(identifier);

		// 设置背包中的经验值
		long expDelta = duration * constStageTableItemEx.getBasicAwardFomula().getExp();
		if (this.redisTemplate.opsForHash().hasKey(itemKey, "double_exp_card_2")) {
			double ratio = 1.0;

			if (vip != 0) {
				ratio = 2 + vip / 10.0;
			}
			expDelta *= ratio;
		}

		// 获取背包数据
		Backpack backpack = backpackService.getByLevel(level);

		Long currentDeltaExp = this.redisTemplate.opsForHash().increment(tempBackpackItemsKey, "exp", expDelta);
		Integer expMax = backpack.getExpMax();
		if (currentDeltaExp > expMax) {
			this.redisTemplate.opsForHash().put(tempBackpackItemsKey, "exp", expMax);
		}

		// 设置背包中的金币值
		long goldDelta = duration * constStageTableItemEx.getBasicAwardFomula().getGold();
		if (this.redisTemplate.opsForHash().hasKey(itemKey, "double_gold_card_2")) {
			double ratio = 1.0;

			if (vip != 0) {
				ratio = 2 + vip / 10.0;
			}
			goldDelta *= ratio;
		}

		Long currentDeltaGold = this.redisTemplate.opsForHash().increment(tempBackpackItemsKey, "gold", goldDelta);
		if (currentDeltaGold<0) {
			this.redisTemplate.opsForHash().put(tempBackpackItemsKey, "gold", "0");
		}
		Integer goldMax = backpack.getGoldMax();
		if (currentDeltaGold > goldMax) {
			this.redisTemplate.opsForHash().put(tempBackpackItemsKey, "gold", String.valueOf(goldMax));
		}

		// 计算小怪收益
		if (monsters > 0) {

			Integer itemCount = Helper.itemCount(redisTemplate, identifier, String.format("dungeon_%s_not_passed_stage", type));

			String dropAction = itemCount.equals(stage) ? constStageTableItemEx.getMonstersKilledAwardFomula().getDropid() :
					constStageTableItemEx.getMonstersKilledAwardFomula().getDropidBosskilled();

			PlayerInfoProtobuf.PlayerInfo playerInfo;
			for (int i = 0; i < monsters; i++) {
				playerInfo = this.dropService.dropItem(dropAction, identifier, true, null);
				builder.addAllItems(playerInfo.getItemsList());
				builder.addAllArchives(playerInfo.getArchivesList());
			}

		}

		// 计算哥布林收益
		if (goblins > 0) {

			String dropAction = constStageTableItemEx.getGoblinFomula().getDropid();

			PlayerInfoProtobuf.PlayerInfo playerInfo;
			for (int i = 0; i < goblins; i++) {
				playerInfo = this.dropService.dropItem(dropAction, identifier, true, null);
				builder.addAllItems(playerInfo.getItemsList());
				builder.addAllArchives(playerInfo.getArchivesList());
			}

		}

		Map<Object, Object> items = this.redisTemplate.opsForHash().entries(tempBackpackItemsKey);
		items.forEach((k, v) -> builder.addItems(PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey(k.toString())
				.setValue((k.toString().equals("gold"))&&Long.parseLong(v.toString())<0?0:Long.parseLong(v.toString()))
				.build()));

		this.redisTemplate.opsForHash().put(tempBackpackKey, "dungeon_enter_timestamp", Helper.currentTimestamp());
	}


	/**
	 * 临时背包物品收割
	 */
	public void grabTemporaryBackpack(Integer identifier, CommHarvestTemporaryBackpackRequestProtobuf.CommHarvestTemporaryBackpackRequest request,
									  CommHarvestTemporaryBackpackResponseProtobuf.CommHarvestTemporaryBackpackResponse.Builder builder, int monsters, int goblins) throws Exception {

		// # 更新临时背包
		CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest updateTemporaryBackpackRequest =
				CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest.newBuilder().setMonsters(monsters).setGoblins(goblins).build();

		CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.Builder updateTemporaryBackpackResponseBuilder =
				CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.newBuilder();

		this.updateTemporaryBackpack(identifier, updateTemporaryBackpackRequest, updateTemporaryBackpackResponseBuilder, monsters, goblins);
		BeanUtil.copyProperties(updateTemporaryBackpackResponseBuilder, builder);

		String tempBackpackItemsKey = this.getTempBackpackItemsKey(identifier);

		Map<Object, Object> tempBackpackItemsMap = this.redisTemplate.opsForHash().entries(tempBackpackItemsKey);

		String teamsKey = this.getTeamsKey(identifier);
		tempBackpackItemsMap.forEach((item, number) -> {
			if ("exp".equals(item.toString())) {
				Map<Object, Object> teams = this.redisTemplate.opsForHash().entries(teamsKey);

				teams.forEach((hero, dummy) -> {
					String heroAttributesKey = this.getHeroAttributesKey(identifier, hero.toString());

					Map<Object, Object> attributes = this.redisTemplate.opsForHash().entries(heroAttributesKey);

					attributes.forEach((ak, av) -> {
						attributes.put(ak, "type".equals(ak) ? av : this.parseLong(av));
					});

					attributes.put("exp", this.parseLong(attributes.get("exp")) + this.parseLong(number) / teams.size());

					LevelUpgrade levelUpgrade;
					while (this.parseLong(attributes.get("level")) < 99L) {

						levelUpgrade = levelUpgradeService.get(this.parseInt(attributes.get("level")));

						try {

							Method method = levelUpgrade.getClass().getMethod("getStart" + this.parseString(attributes.get("star")));
							Long expNeed = this.parseLong(method.invoke(levelUpgrade));

							if (this.parseLong(attributes.get("exp")) >= expNeed) {

								attributes.put("exp", this.parseLong(attributes.get("exp")) - expNeed);
								attributes.put("level", this.parseLong(attributes.get("level")) + 1);
								attributes.put("hp", this.parseLong(attributes.get("hp")) + this.parseLong(attributes.get("grow_hp")));
								attributes.put("def", this.parseLong(attributes.get("def")) + this.parseLong(attributes.get("grow_def")));
								attributes.put("attack", this.parseLong(attributes.get("attack")) + this.parseLong(attributes.get("grow_attack")));
								attributes.put("speed", this.parseLong(attributes.get("speed")) + this.parseLong(attributes.get("grow_speed")));

								if (this.parseLong(attributes.get("level")) == 99L) {
									levelUpgrade = levelUpgradeService.get(this.parseInt(attributes.get("level")));
									method = levelUpgrade.getClass().getMethod("getStart" + attributes.get("star").toString());
									expNeed = this.parseLong(method.invoke(levelUpgrade));
									attributes.put("exp", expNeed);
								}
							} else {
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					this.redisTemplate.opsForHash().putAll(heroAttributesKey, attributes);

					Long power = this.parseLong(attributes.get("hp")) + this.parseLong(attributes.get("def"))
							+ this.parseLong(attributes.get("attack")) + this.parseLong(attributes.get("speed"));

					String herosKey = this.getHerosKey(identifier);

					this.redisTemplate.opsForHash().put(herosKey, attributes.get("type"), power);

					PlayerItemProtobuf.PlayerItem playerItem = Helper.updateRanklistHonor(redisTemplate, identifier);
					if (playerItem!=null) {
						builder.addArchives(playerItem);
					}


					// 设置属性
					HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBasicInfoBuilder = this.heroBasicInfoBuilder(attributes);

					String skillsKey = this.getSkillsKey(identifier, hero.toString());
					Map<Object, Object> skills = this.redisTemplate.opsForHash().entries(skillsKey);

					skills.forEach((sk, sv) -> heroBasicInfoBuilder.addSkills(HeroSkillProtobuf.HeroSkill.newBuilder()
							.setType(sk.toString()).setLevel(this.parseInt(sv)).build()));

					builder.addHeros(heroBasicInfoBuilder.build());
				});

			} else {

				PlayerItemProtobuf.PlayerItem playerItem1 = Helper.increaseItemValue(redisTemplate, identifier, item.toString(), this.parseLong(number));
				builder.addItems(playerItem1);

				if (item.toString().startsWith("exp_book_")) {

					PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "expbooks", this.parseInt(number), identifier);
					builder.addArchives(playerItem);

				} else if ("gold".equals(item.toString())) {

					PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "maxgold", this.parseInt(number), identifier);
					builder.addArchives(playerItem);

				}
			}
		});

		this.redisTemplate.delete(tempBackpackItemsKey);
		Map<Object, Object> zeroMap = new HashMap<>();
		zeroMap.put("gold", 0);
		zeroMap.put("exp", 0);
		this.redisTemplate.opsForHash().putAll(tempBackpackItemsKey, zeroMap);
	}

	private HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBasicInfoBuilder(Map<Object, Object> attributes) {
		return HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder()
				.setId(this.parseInt(attributes.get("id")))
				.setType(this.parseString(attributes.get("type")))
				.setLevel(this.parseInt(attributes.get("level")))
				.setExp(this.parseInt(attributes.get("exp")))
				.setQuality(this.parseInt(attributes.get("quality")))
				.setStar(this.parseInt(attributes.get("star")))
				.setGrowHp(this.parseInt(attributes.get("grow_hp")))
				.setHp(this.parseInt(attributes.get("hp")))
				.setGrowDef(this.parseInt(attributes.get("grow_def")))
				.setDef(this.parseInt(attributes.get("def")))
				.setGrowAttack(this.parseInt(attributes.get("grow_attack")))
				.setAttack(this.parseInt(attributes.get("attack")))
				.setGrowSpeed(this.parseInt(attributes.get("grow_speed")))
				.setSpeed(this.parseInt(attributes.get("speed")))
				.setNumber(this.parseInt(attributes.get("number")));
	}


	private String getHeroAttributesKey(Integer identifier, String hero) {
		return "u:#{identifier}:#{k_hero}:attributes"
				.replace("#{identifier}", identifier.toString())
				.replace("#{k_hero}", hero);
	}

	private String getHerosKey(Integer identifier) {
		return "u:#{identifier}:heros".replace("#{identifier}", identifier.toString());
	}

	private String getSkillsKey(Integer identifier, String hero) {
		return "u:#{identifier}:#{k_hero}:skills"
				.replace("#{identifier}", identifier.toString())
				.replace("#{k_hero}", hero);
	}

	private String getTeamsKey(Integer identifier) {
		return "u:#{identifier}:teams".replace("#{identifier}", identifier.toString());
	}

	private String getItemKey(Integer identifier) {
		return "u:#{identifier}:items".replace("#{identifier}", identifier.toString());
	}

	private String getTempBackpackKey(Integer identifier) {
		return "u:#{identifier}:temp_backpack".replace("#{identifier}", identifier.toString());
	}

	private String getTempBackpackItemsKey(Integer identifier) {
		return "u:#{identifier}:temp_backpack_items".replace("#{identifier}", identifier.toString());
	}

	public Long parseLong(Object o) {
		return o == null ? 0L : Long.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}

	public String parseString(Object o) {
		return o == null ? "" : o.toString();
	}

}
