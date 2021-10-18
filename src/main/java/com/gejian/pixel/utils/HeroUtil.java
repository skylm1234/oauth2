package com.gejian.pixel.utils;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-10-18 13:46
 * @description：
 */
public class HeroUtil {
	public static final String STAR_PREFIX = "star_";
	public static final String UPGRADE_SUFFIX = "_upgrade";
	public static final String STAR_1_5 = "star_(1-5)";
	public static final String STAR_6_10 = "star_(6-10)";
	public static final String STAR_11_15 = "star_(11-15)";
	public static final String STAR_16_20 = "star_(16-20)";
	public static final String STAR_21_25 = "star_(21-25)";

	public static final String STAR_1_5_REP = "1星-5星：";
	public static final String STAR_6_10_REP = "6星-10星：";
	public static final String STAR_11_15_REP = "10星-15星：";
	public static final String STAR_16_20_REP = "16星-20星：";
	public static final String STAR_21_25_REP = "21星-25星：";
	public static final String BASIC = "basic";
	public static final String BASIC_REP = "基础：";

	public static final String HP = "hp";
	public static final String SPEED = "speed";
	public static final String ATTACK = "attack";
	public static final String DEFENSE = "defense";
	public static final String HP_UPGRADE = HP + UPGRADE_SUFFIX;
	public static final String SPEED_UPGRADE = SPEED + UPGRADE_SUFFIX;
	public static final String ATTACK_UPGRADE = ATTACK + UPGRADE_SUFFIX;
	public static final String DEFENSE_UPGRADE = DEFENSE + UPGRADE_SUFFIX;


	public static final Map<Integer,String> LEVEL_MAP;
	static {
		LEVEL_MAP =  ImmutableMap.<Integer,String>builder().put(1,STAR_1_5_REP).put(6,STAR_6_10_REP).put(11,STAR_11_15_REP)
				.put(16,STAR_16_20_REP).put(21,STAR_21_25_REP).build();
	}

	public static String replaceHeroData(String source){
		return source.replaceAll(STAR_1_5,STAR_1_5_REP).replaceAll(STAR_6_10,STAR_6_10_REP).replaceAll(STAR_11_15,STAR_11_15_REP
				.replaceAll(STAR_16_20,STAR_16_20_REP).replaceAll(STAR_21_25,STAR_21_25));
	}
}
