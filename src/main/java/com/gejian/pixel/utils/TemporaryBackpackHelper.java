package com.gejian.pixel.utils;

import cn.hutool.core.bean.BeanUtil;
import com.gejian.pixel.proto.*;
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

	/**
	 * 修改临时背包
	 */
	public void updateTemporaryBackpack(Integer identifier, CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest request,
										CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse response, int monsters, int goblins) {
		String tempBackpackKey = this.getTempBackpackKey(identifier);
		Map<Object, Object> pack = this.redisTemplate.opsForHash().entries(tempBackpackKey);
		CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.Builder builder = response.toBuilder();

		PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "type_" + pack.get("type").toString() + "_monster_kill", monsters, identifier);
		builder.addItems(playerItem);
		PlayerItemProtobuf.PlayerItem playerItem1 = Helper.onNotifyEventOfPromotions(redisTemplate, "daily_monster_kill", monsters, identifier);
		builder.addItems(playerItem1);
		PlayerItemProtobuf.PlayerItem playerItem2 = Helper.onNotifyEventOfPromotions(redisTemplate, "daily_type_" + pack.get("type").toString() + "_monster_kill", monsters, identifier);
		builder.addItems(playerItem2);

		int vip = Helper.itemCount(redisTemplate, identifier, "vip");

		long duration = Helper.currentTimestamp() - Long.parseLong(pack.get("dungeon_enter_timestamp").toString());

		// TODO RUBY_CONST_STAGE_TABLE_HASH["X#{pack['stage']}"]
		ConstStageClassTableProtobuf.ConstStageClassTable constStageClassTable =
				ConstStageClassTableProtobuf.ConstStageClassTable.getDefaultInstance();
		ConstStageClassTableItemExProtobuf.ConstStageClassTableItemEx stageClassTableItemEx =
				constStageClassTable.getItems(this.parseInt(pack.get("stage")));

		Map<String, Map<String, Long>> constStageTable = new HashMap<>(16);

		long level = Long.parseLong(pack.get("level").toString()) - 1;

		String itemKey = this.getItemKey(identifier);
		String tempBackpackItemsKey = getTempBackpackItemsKey(identifier);

		// 设置背包中的经验值
		long expDelta = duration * constStageTable.get("basic_award_fomula").get("exp");
		if (this.redisTemplate.opsForHash().hasKey(itemKey, "double_exp_card_2")) {
			int ratio = 1;

			if (vip != 0) {
				ratio = 2 + vip / 10;
			}
			expDelta *= ratio;
		}


		Long currentDeltaExp = this.redisTemplate.opsForHash().increment(tempBackpackItemsKey, "exp", expDelta);
		Long expMax = constStageTable.get(level).get("exp_max");
		if (currentDeltaExp > expMax) {
			this.redisTemplate.opsForHash().put(tempBackpackItemsKey, "exp", expMax);
		}

		// 设置背包中的金币值
		long goldDelta = duration * constStageTable.get("basic_award_fomula").get("gold");
		if (this.redisTemplate.opsForHash().hasKey(itemKey, "double_gold_card_2")) {
			int ratio = 1;

			if (vip != 0) {
				ratio = 2 + vip / 10;
			}
			goldDelta *= ratio;
		}

		Long currentDeltaGold = this.redisTemplate.opsForHash().increment(tempBackpackItemsKey, "gold", goldDelta);
		Long goldMax = constStageTable.get(level).get("gold_max");
		if (currentDeltaGold > goldMax) {
			this.redisTemplate.opsForHash().put(tempBackpackItemsKey, "gold", goldMax);
		}

		// 计算小怪收益
		if (monsters > 0) {
			/**
			 drop_action = (item_count(identifier,  "dungeon_#{pack['type']}_not_passed_stage") == pack['stage'].to_i) ? const['monsters_killed_award_fomula']['dropid'] : const['monsters_killed_award_fomula']['dropid_bosskilled']

			 for i in 0...monsters do
			 DROP_ITEMS[drop_action].call(identifier,  reply, true, nil)
			 end
			 */

			for (int i = 0; i < monsters; i++) {
				// TODO something
			}

		}

		// 计算哥布林收益
		if (goblins > 0) {
			/**
			 for i in 0...goblins do
			 drop_goblin = DROP_ITEMS[const['goblin_fomula']['dropid']]
			 drop_goblin.call(identifier,  reply, true, nil)
			 end
			 */

			for (int i = 0; i < goblins; i++) {
				// TODO something
			}

		}

		Map<Object, Object> items = this.redisTemplate.opsForHash().entries(tempBackpackItemsKey);
		items.forEach((k, v) -> {
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem.newBuilder()
					.setKey(k.toString())
					.setValue(Long.parseLong(v.toString()))
					.build();
			response.getItemsList().add(item);
		});
		this.redisTemplate.opsForHash().put(tempBackpackKey, "dungeon_enter_timestamp", Helper.currentTimestamp());
	}


	/**
	 * 临时背包物品收割
	 */
	public void grabTemporaryBackpack(Integer identifier, CommHarvestTemporaryBackpackRequestProtobuf.CommHarvestTemporaryBackpackRequest request,
									   CommHarvestTemporaryBackpackResponseProtobuf.CommHarvestTemporaryBackpackResponse response, int monsters, int goblins) throws Exception {

		// # 更新临时背包
		CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest updateTemporaryBackpackRequest =
				CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest.newBuilder().setMonsters(monsters).setGoblins(goblins).build();
		CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse updateTemporaryBackpackResponse =
				CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.getDefaultInstance();
		this.updateTemporaryBackpack(identifier, updateTemporaryBackpackRequest, updateTemporaryBackpackResponse, monsters, goblins);
		BeanUtil.copyProperties(updateTemporaryBackpackResponse, response);

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

					// TODO 获取ConstLevelUpgradeTable值
					ConstLevelUpgradeTableProtobuf.ConstLevelUpgradeTable constLevelUpgradeTable =
							ConstLevelUpgradeTableProtobuf.ConstLevelUpgradeTable.getDefaultInstance();
					ConstLevelUpgradeTableItemExProtobuf.ConstLevelUpgradeTableItemEx levelUpgradeTableItemEx;
					while (this.parseLong(attributes.get("level")) < 99L) {
						levelUpgradeTableItemEx = constLevelUpgradeTable.getItems(this.parseInt(attributes.get("level")) - 1);

						try {
							Method method = levelUpgradeTableItemEx.getClass().getMethod("start" + attributes.get("star").toString());
							Long expNeed = this.parseLong(method.invoke(levelUpgradeTableItemEx));
							if (this.parseLong(attributes.get("exp")) >= expNeed) {

								attributes.put("exp", this.parseLong(attributes.get("exp")) - expNeed);
								attributes.put("level", this.parseLong(attributes.get("level")) + 1);
								attributes.put("hp", this.parseLong(attributes.get("hp")) + this.parseLong(attributes.get("grow_hp")));
								attributes.put("def", this.parseLong(attributes.get("def")) + this.parseLong(attributes.get("grow_def")));
								attributes.put("attack", this.parseLong(attributes.get("attack")) + this.parseLong(attributes.get("grow_attack")));
								attributes.put("speed", this.parseLong(attributes.get("speed")) + this.parseLong(attributes.get("grow_speed")));

								if (this.parseLong(attributes.get("level")) == 99L) {
									levelUpgradeTableItemEx = constLevelUpgradeTable.getItems(this.parseInt(attributes.get("level")) - 1);
									method = levelUpgradeTableItemEx.getClass().getMethod("start" + attributes.get("star").toString());
									expNeed = this.parseLong(method.invoke(levelUpgradeTableItemEx));
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

					// TODO reply
					PlayerItemProtobuf.PlayerItem playerItem = Helper.updateRanklistHonor(redisTemplate, identifier);


					// TODO 设置属性
					HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBasicInfoBuilder = this.heroBasicInfoBuilder(attributes);

					String skillsKey = this.getSkillsKey(identifier, hero.toString());
					Map<Object, Object> skills = this.redisTemplate.opsForHash().entries(skillsKey);
					skills.forEach((sk, sv) -> heroBasicInfoBuilder.addSkills(HeroSkillProtobuf.HeroSkill.newBuilder()
							.setType(sk.toString()).setLevel(this.parseInt(sv)).build()));

					response.getHerosList().add(heroBasicInfoBuilder.build());
				});

			} else {
				Helper.increaseItemValue(redisTemplate, identifier, item.toString(), this.parseLong(number));
				if (item.toString().startsWith("exp_book_")) {
					// TODO  reply
					PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "expbooks", this.parseInt(number), identifier);
				} else if ("gold".equals(item.toString())) {
					// TODO reply
					PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "maxgold", this.parseInt(number), identifier);
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
		HeroBasicInfoProtobuf.HeroBasicInfo.Builder builder = HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder();
		Method[] declaredMethods = builder.getClass().getDeclaredMethods();
		for (Method method : declaredMethods) {
			String name = method.getName();
			if (!name.startsWith("set")) {
				continue;
			}
			String ak = name.substring(3).toLowerCase();
			Object av = attributes.get(ak);
			try {
				method.invoke(builder, av);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return builder;
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


}
