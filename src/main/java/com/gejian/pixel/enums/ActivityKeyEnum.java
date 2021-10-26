package com.gejian.pixel.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 10:50
 * @description：
 */

@Getter
public enum ActivityKeyEnum {

	/**
	 *
	 */
	TYPE_1_MONSTER_KILL("type_1_monster_kill","击杀普通小怪个数",1,3,"任意普通关卡，累计击杀%d个小怪。"),
	TYPE_2_MONSTER_KILL("type_2_monster_kill","击杀噩梦小怪个数",2,3,"任意噩梦关卡，累计击杀%d个小怪。"),
	TYPE_3_MONSTER_KILL("type_3_monster_kill","击杀折磨小怪个数",3,3,"任意折磨关卡，累计击杀%d个小怪。"),
	TYPE_1_BOSS_KILL("type_1_boss_kill","击杀普通BOSS个数",4,3,"击杀普通关卡%d个BOSS。"),
	TYPE_2_BOSS_KILL("type_2_boss_kill","击杀噩梦BOSS个数",5,3,"击杀噩梦关卡%d个BOSS。"),
	TYPE_3_BOSS_KILL("type_3_boss_kill","击杀折磨BOSS个数",6,3,"击杀折磨关卡%d个BOSS。"),
	VIP("vip","成为VIP",7,3,"成为%d级VIP达人。"),
	CUTHAND("cuthand","成为剁手党",8,3,"成为【xx】级剁手党（商店消费%d次）。"),
	MOSTHEROS("mostheros","获得英雄个数",9,3,"获得%d个英雄。"),
	MOSTHIRE("mosthire","招募英雄次数",10,3,"完成招募%d次英雄。"),
	KINGOFPVP("kingofpvp","进入PVP排行榜",11,1,"进入PVP TOP"),
	TEMPBACKPACK("tempbackpack","升级临时背包次数",12,3,"升级%d次临时背包。"),
	EXPBOOKS("expbooks","获得经验书本数",13,3,"获得%d本经验之书。"),
	CONSUMEEXPBOOKS("consumeexpbooks","英雄使用经验书本数",14,3,"对英雄使用%d本经验之书"),
	MAXGOLD("maxgold","获得金币个数",15,3,"累计获得%d金币。"),
	MAXHONOR("maxhonor","获得荣誉个数",16,3,"累计获得%d荣誉。"),
	MAXTOPSKILLS("maxtopskills","满级技能个数",17,3,"所有英雄累计满级技能达到%d个。"),
	DAILY_SKILL_UPGRADE("daily_skill_upgrade","升级技能次数",18,3,"升级技能%d次"),
	DAILY_MONSTER_KILL("daily_monster_kill","打过小怪次数",18,3,"打过%d波小怪"),
	DAILY_PVP_TIMES("daily_pvp_times","挑战PVP次数",18,3,"挑战PVP%d次"),
	DAILY_EXP_BOOK_CONSUME("daily_exp_book_consume","使用经验书本数",18,3,"使用%d本经验书"),
	DAILY_BUY_HERO("daily_buy_hero","招募小伙伴次数",18,3,"招募小伙伴%d次"),
	DAILY_TYPE_1_MONSTER_KILL("daily_type_1_monster_kill","击杀普通小怪次数",18,3,"在普通难度关卡击杀%d波小怪"),
	DAILY_TYPE_2_MONSTER_KILL("daily_type_2_monster_kill","击杀噩梦小怪次数",18,3,"在噩梦难度关卡击杀%d波小怪"),
	DAILY_TYPE_3_MONSTER_KILL("daily_type_3_monster_kill","击杀折磨小怪次数",18,3,"在折磨难度关卡击杀%d波小怪"),
	DAILY_STORE_BUY_TIMES("daily_store_buy_times","商店购买次数",18,3,"商店进行%d次购物"),
	;
	private String key;
	private String name;
	private int groupType;
	private int compareType;
	private String condition;
	ActivityKeyEnum(String key,String name,int groupType,int compareType,String condition){
		this.key = key;
		this.name = name;
		this.groupType = groupType;
		this.compareType = compareType;
		this.condition = condition;
	}

	public static List<ActivityKeyEnum> findByGroupType(int groupType){
		return Arrays.stream(ActivityKeyEnum.values()).filter(activityKeyEnum -> activityKeyEnum.groupType == groupType).collect(Collectors.toList());
	}
}
