package com.gejian.pixel.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.entity.LevelUpgrade;
import com.gejian.pixel.ext.DropExt;
import com.gejian.pixel.mapper.DropMapper;
import com.gejian.pixel.model.DropItem;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.ConstantsProto;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.LevelUpgradeService;
import com.gejian.pixel.utils.Helper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class DropServiceImpl extends ServiceImpl<DropMapper, Drop> implements DropService, ConstantsProto {

	private Map<String, DropExt> hash = new HashMap<>();

	private List<ConstDropTableItemExProtobuf.ConstDropTableItemEx> table = new ArrayList();

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private LevelUpgradeService levelUpgradeService;

	@Autowired
	private BackpackService backpackService;

	@Override
	public void init() {
		List<Drop> list = this.list();
		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(drop -> {
				DropExt dropExt = new DropExt();
				dropExt.setId(drop.getId());
				dropExt.setContent(drop.getContent());
				dropExt.setDesc(drop.getDesc());
				dropExt.setContents(convertContentStr(drop.getContent()));
				hash.put(drop.getId(), dropExt);
				table.add(convert(drop));
			});
		}
	}

	private ConstDropTableItemExProtobuf.ConstDropTableItemEx convert(Drop drop) {
		return ConstDropTableItemExProtobuf.ConstDropTableItemEx.newBuilder()
				.setContent(drop.getContent())
				.setDesc(drop.getDesc())
				.setId(drop.getId())
				.setDesc2(drop.getDesc2())
				.build();
	}


	/**
	 * 物品掉落
	 *
	 * @param key
	 */
	@Override
	public PlayerInfoProtobuf.PlayerInfo dropItem(String key, Integer identifier, Boolean store2backpack, String parameter) {
		PlayerInfoProtobuf.PlayerInfo.Builder builder = PlayerInfoProtobuf.PlayerInfo.newBuilder();
		DropExt dropExt = hash.get(key);
		if (Objects.isNull(dropExt)) {
			return builder.build();
		}
		List<List<DropItem>> contents = dropExt.getContents();
		for (List<DropItem> first : contents) {

			// 物品掉落几率
			Integer factor = selectFromMultipleAward(first);
			if (factor > first.size()-1) {
				//如果几率超出范围，那么就重新计算几率
				factor = RandomUtil.randomInt(first.size());
			}
			DropItem dropItem = first.get(factor);
			//获得奖励
			int size = dropItem.getElements().size();
			// 随机获得物品
			String type = size > 1 ? dropItem.getElements().get(RandomUtil.randomInt(size))
					: dropItem.getElements().get(0);
			int numSize = dropItem.getNumbers().size();
			// 物品数量
			int num = numSize <= 1 ? dropItem.getNumbers().get(0)
					: RandomUtil.randomInt(dropItem.getNumbers().get(1)) + dropItem.getNumbers().get(0);
			if (Objects.nonNull(store2backpack) && store2backpack) {
				store2BackPack(type, num, identifier, parameter);
			} else {
				notStore2BackPack(builder, type, num, identifier, parameter);
			}
		}
		return builder.build();
	}


	private void notStore2BackPack(PlayerInfoProtobuf.PlayerInfo.Builder playerInfo, String type, int num, Integer identifier, String parameter) {
		if (type.matches("^box_monry_.*$") ||
				type.matches("^box_exp_book_.*$") ||
				type.matches("^box_private_soulchip_.*$") ||
				type.matches(" ^box_skillbook_.*$") ||
				type.matches("^box_stone_.*$")) {
			PlayerInfoProtobuf.PlayerInfo rpInfo = dropItem(type, identifier, false, parameter);
			playerInfo.addAllHeros(rpInfo.getHerosList());
			playerInfo.addAllItems(rpInfo.getItemsList());
			playerInfo.addAllTeamsPvp(rpInfo.getTeamsPvpList());
			playerInfo.addAllTeams(rpInfo.getTeamsList());
			playerInfo.addAllArchives(rpInfo.getArchivesList());
		} else {
			if ("gold".equals(type) || "stone".equals(type) || "honor".equals(type) ||
					type.matches("^exp_book_.*$") || type.matches("^private_soulchip_.*$")
					|| type.matches("^book_skill_.*$")) {
				PlayerItemProtobuf.PlayerItem playerItem = Helper
						.increaseItemValue(stringRedisTemplate, identifier, type, (long) num);
				playerInfo.addItems(playerItem);
				if ("honor".equals(type)) {
					if (StrUtil.isNotBlank(parameter) && "archives".equals(parameter)) {

					} else {
						PlayerItemProtobuf.PlayerItem totalHonorItem = Helper
								.increaseItemValue(stringRedisTemplate, identifier, "total_honor", (long) num);
						PlayerItemProtobuf.PlayerItem updateRanklistHonor = Helper
								.updateRanklistHonor(stringRedisTemplate, identifier);
						PlayerItemProtobuf.PlayerItem maxhonor = Helper
								.onNotifyEventOfPromotions(stringRedisTemplate, "maxhonor", num, identifier);
						playerInfo.addItems(totalHonorItem);
						if (updateRanklistHonor!=null) {
							playerInfo.addItems(updateRanklistHonor);
						}
						playerInfo.addArchives(maxhonor);

					}
				}
				if (type.startsWith("exp_book_")) {
					PlayerItemProtobuf.PlayerItem expbooks = Helper.onNotifyEventOfPromotions(stringRedisTemplate, "expbooks", num, identifier);
					playerInfo.addArchives(expbooks);
				} else if ("gold".equals(type)) {
					PlayerItemProtobuf.PlayerItem maxgold = Helper.onNotifyEventOfPromotions(stringRedisTemplate, "maxgold", num, identifier);
					playerInfo.addArchives(maxgold);
				}
			} else if ("exp".equals(type)) {
				//上阵角色都加经验
				Long nbHerosInTeam = stringRedisTemplate
						.opsForHash().size("u:" + identifier + ":teams");
				Map<Object, Object> teams = stringRedisTemplate
						.opsForHash().entries("u:" + identifier + ":heros");
				teams.forEach((k, v) -> {
					Map<Object, Object> attributes = stringRedisTemplate
							.opsForHash().entries("u:" + identifier + ":" + k + ":attributes");
					Map skills = stringRedisTemplate.opsForHash().entries("u:" + identifier + ":" + k + ":skills");
					Map<String, Integer> newMap = new HashMap<>();
					attributes.forEach((k1, v1) -> {
						if (!"type".equals(k1)) {
							newMap.put(String.valueOf(k1), Integer.parseInt(v1 + ""));
						}
					});
					int exp = newMap.get("exp");
					newMap.put("exp", exp + (Integer.parseInt(v + "") / Integer.parseInt(nbHerosInTeam + "")));
					while (newMap.get("level") < 99) {
						Integer level = newMap.get("level");
						LevelUpgrade levelUpgrade = levelUpgradeService.get(level);
						Integer start = newMap.get("start");
						Integer expNeed = ReflectUtil.invoke(levelUpgrade, "getStart" + start);
						if (newMap.get("exp") >= expNeed) {
							newMap.put("exp", newMap.get("exp") - expNeed);
							newMap.put("level", newMap.get("level") + 1);
							newMap.put("hp", newMap.get("hp") + newMap.get("grow_hp"));
							newMap.put("def", newMap.get("def") + newMap.get("grow_def"));
							newMap.put("attack", newMap.get("attack") + newMap.get("grow_attack"));
							newMap.put("speed", newMap.get("speed") + newMap.get("grow_speed"));
							if (attributes.get("exp") == expNeed) {
								newMap.put("level", 99);
							}
						} else {
							break;
						}
					}
					attributes.putAll(newMap);

					stringRedisTemplate.opsForHash()
							.putAll("u:" + identifier + ":" + k + ":attributes", attributes);

					HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBuilder = HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder();

					heroBuilder.setExp(newMap.get("exp"));
					heroBuilder.setLevel(newMap.get("level"));
					heroBuilder.setHp(newMap.get("hp"));
					heroBuilder.setHp(newMap.get("hp"));
					heroBuilder.setGrowHp(newMap.get("grow_hp"));
					heroBuilder.setDef(newMap.get("def"));
					heroBuilder.setGrowDef(newMap.get("grow_def"));
					heroBuilder.setAttack(newMap.get("attack"));
					heroBuilder.setGrowAttack(newMap.get("grow_attack"));
					heroBuilder.setSpeed(newMap.get("speed"));
					heroBuilder.setGrowSpeed(newMap.get("grow_speed"));

					skills.forEach((kk, vv) -> {
						HeroSkillProtobuf.HeroSkill heroSkill = HeroSkillProtobuf.HeroSkill
								.newBuilder()
								.setType(kk + "")
								.setLevel(Integer.parseInt(vv + ""))
								.build();
						heroBuilder.addSkills(heroSkill);
					});
					HeroBasicInfoProtobuf.HeroBasicInfo hero = heroBuilder.build();
					playerInfo.addHeros(hero);
				});
			} else if (type.startsWith("hero_")) {
				PlayerInfoProtobuf.PlayerInfo pi = Helper
						.awardHeroForMe(stringRedisTemplate, identifier, type, parameter);
				playerInfo
						.addAllItems(pi.getItemsList())
						.addAllHeros(pi.getHerosList())
						.addAllTeams(pi.getItemsList())
						.addAllTeamsPvp(pi.getTeamsPvpList());
			}
		}
	}


	private void store2BackPack(String type, int num, Integer identifier, String parameter) {
		if (type.matches("^hero_.*$")) {
			throw new RuntimeException("can not handle award hero");
		}
		if (type.matches("^box_monry_.*$") ||
				type.matches("^box_exp_book_.*$") ||
				type.matches("^box_private_soulchip_.*$") ||
				type.matches(" ^box_skillbook_.*$") ||
				type.matches("^box_stone_.*$")) {
			dropItem(type, identifier, true, parameter);
		} else {
			if ("gold".equals(type) || "exp".equals(type) || "stone".equals(type) || "honor".equals(type) ||
					type.matches("^exp_book_.*$") || type.matches("^private_soulchip_.*$")
					|| type.matches("^book_skill_.*$")) {
				Map<Object, Object> entries = stringRedisTemplate
						.opsForHash()
						.entries(StrUtil.format(RedisKeyConstants.USER_TEMP_PACK, identifier));
				int level = Integer.parseInt(String.valueOf(entries.get("level")));
				//level -= 1;
				int max = 0;
				Backpack backpack = backpackService.getByLevel(level);
				if ("gold".equals(type)) {
					max = backpack.getGoldMax();
				} else if ("exp".equals(type)) {
					max = backpack.getExpMax();
				} else {
					max = backpack.getItemMax();
				}
				long result = stringRedisTemplate.opsForHash()
						.increment(StrUtil.format(RedisKeyConstants.USER_TEMP_PACK_ITEMS, String.valueOf(identifier))
								, type, num);
				if (result > max) {
					stringRedisTemplate.opsForHash()
							.put(StrUtil.format(RedisKeyConstants.USER_TEMP_PACK_ITEMS, String.valueOf(identifier))
									, type, String.valueOf(max));
				}
			}
		}
	}


	/**
	 * @param list
	 * @return
	 */
	private Integer selectFromMultipleAward(List<DropItem> list) {
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				int probability = list.get(i).getProbability()
						+ list.get(i - 1).getProbability();
				list.get(i).setProbability(probability);
			}
		}
		/**
		 * factor = 0
		 *     if ar[ar.length - 1][1] < 100 then
		 *         factor = rand(100)
		 *     else
		 *         factor = rand(ar[ar.length - 1][1])
		 *     end
		 *
		 *     for i in 0...ar.length do
		 *         if factor <= ar[i][1] then return i end
		 *     end
		 */
		// 掉落几率
		Integer randomNum = 0;
		Integer probability = list.get(list.size() - 1).getProbability();
		if (probability < 100) {
			randomNum = RandomUtil.randomInt(100);
		} else {
			randomNum = RandomUtil.randomInt(probability);
		}

		for (int i = 0; i < list.size(); i++) {
			if (randomNum <= list.get(i).getProbability()) {
				return i;
			}
		}

		return randomNum;
	}

	/**
	 * [gold*100*(20000)][hero_( 20001) * 100 * 1]
	 * [exp_book_(4) * 100 * 1][private_soulchip_20001*100*25][book_skill_2012*100*11]
	 * 先按[] 切 再按 "，"切割 再按照"*"切割 最后切割 "#"
	 *
	 * @param contentStr
	 * @return
	 */
	private static List<List<DropItem>> convertContentStr(String contentStr) {
		String str = StrUtil.replace(contentStr, "[", "");
		List<String> contentList = StrUtil.split(str.trim(), "]");
		List<List<DropItem>> one = new ArrayList<>();
		contentList.forEach(s -> {
			if (StrUtil.isBlank(s.trim())) {
				return;
			}
			List<String> two = StrUtil.split(s, ",");
			List<DropItem> twoList = new ArrayList<>();
			two.forEach(twos -> {
				List<String> third = StrUtil.split(twos, "*");
				DropItem dropItem = new DropItem();
				List<String> lastItem = new ArrayList<>();
				for (int i = 0; i < third.size(); i++) {
					String item = third.get(i);
					switch (i) {
						case 0:
							String replace = StrUtil.replace(item, ")", "");
							List<String> split = StrUtil.split(replace, "(");
							String s1 = StrUtil.trim(split.get(0));
							dropItem.setType(s1);
							if (split.size() > 1) {
								String s2 = split.get(1);
								List<String> lastList = StrUtil.split(s2, "#");
								if (CollectionUtils.isEmpty(lastList)) {
									lastItem.add(s1);
								}
								lastList.forEach(num -> {
									num = StrUtil.trim(num);
									if (num.contains("-")){
										List<String> numStartEnd = StrUtil.split(num, "-");
										int start = Integer.parseInt(numStartEnd.get(0).trim());
										int end = Integer.parseInt(numStartEnd.get(1).trim());
										while (start <= end){
											lastItem.add(StrUtil.trim(s1 + start));
											start ++;
										}
									} else {
										lastItem.add(StrUtil.trim(s1 + num));
									}
								});
							}
							break;
						case 1:
							dropItem.setProbability(Integer.parseInt(item.replace(" ", "")));
							break;
						case 2:
							String tt = StrUtil.replace(item, ")", "")
									.replace("(", "");
							List<String> nums = StrUtil.split(tt, "#");
							List<Integer> numList = new ArrayList<>();
							nums.forEach(num -> {
								numList.add(NumberUtil.parseInt(Calculator.conversion(num) + ""));
							});
							dropItem.setNumbers(numList);
							break;
						default:
							break;


					}
				}
				if (CollectionUtils.isEmpty(lastItem)) {
					lastItem.add(dropItem.getType());
				}
				dropItem.setElements(lastItem);
				twoList.add(dropItem);
			});
			one.add(twoList);
		});
		return one;
	}







	@Override
	public void build(ConstTablesProtobuf.ConstTables.Builder builder) {
		ConstDropTableProtobuf.ConstDropTable build = ConstDropTableProtobuf.ConstDropTable
				.newBuilder()
				.addAllItems(table)
				.build();
		builder.setDrops(build);
	}
}