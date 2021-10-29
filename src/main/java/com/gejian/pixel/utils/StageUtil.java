package com.gejian.pixel.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gejian.pixel.enums.HeroLevelColorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ：lijianghuai
 * @date ：2021-10-29 10:17
 * @description：
 */

@Slf4j
public class StageUtil {

	public static final String MONSTER_STR = "monster_(%d-%d)";
	public static final String BOSS_STR = "[monster_(%d)]";
	public static final String MONSTER_DROP_STR = "[dropid=%s]$[dropid=%s]*bosskilled";
	public static final String BOSS_DROP_STR = "[dropid=%s]";
	public static final String GOBLIN_STR = "[pro=1,monste_(100),hp=%d,dropid=%s]";
	public static final String BASIC_STR = "[time=%d,gold=%d,exp=%d]";

	public static List<HeroLevelColorEnum> range(String monsters){
		List<Integer[]> ids = regMonsters(monsters);
		return ids.stream().map(HeroLevelColorEnum::rangeOf).collect(Collectors.toList());
	}

	public static List<Integer[]> regMonsters(String monsters){
		String reg = "([\\d]{5})-([\\d]{5})";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(monsters);
		List<Integer[]> result =  new ArrayList<>();
		if(matcher.find()){
			matcher.reset();
			while (matcher.find()) {
				Integer [] monster = new Integer[]{Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2))};
				result.add(monster);
			}
		}
		return result;
	}

	public static String formatBoss(Integer bossId){
		return String.format(BOSS_STR,bossId);
	}

	public static String formatMonsterDrop(String mobDrop, String monsterDrop){
		return String.format(MONSTER_DROP_STR,mobDrop,monsterDrop);
	}

	public static String formatBossDrop(String drop){
		return String.format(BOSS_DROP_STR,drop);
	}

	public static String formatGoblinDrop(Integer hp, String drop){
		return String.format(GOBLIN_STR,hp,drop);
	}

	public static String formatBasic(Integer time, Integer gold, Integer exp){
		return String.format(BASIC_STR,time,gold,exp);
	}

	public static Integer regBoss(String boss){
		String reg = "([\\d]{5})";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(boss);
		if(matcher.find()){
			return Integer.parseInt(matcher.group(0));
		}else {
			return null;
		}
	}

	public static String buildMonsters(List<HeroLevelColorEnum> colors){
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> list = colors.stream().map(color -> String.format(MONSTER_STR, color.getStart(), color.getEnd())).collect(Collectors.toList());
		try {
			return objectMapper.writeValueAsString(list).replaceAll("\"","");
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(),e);
			return StringUtils.EMPTY;
		}
	}

}
