package com.gejian.pixel.utils;

import lombok.Data;

import java.util.Map;

/**
 * @Author:chen
 * @Date: 2021/9/3 10:23
 */
public class RubyConst {

	@Data
	class RubyConstStageTableHash {
		Integer id;
		String name;
		Integer class_type;
		Integer prerequest;
		Integer mode;
		String icon;
		String icon_color;
		String bg;
		String bg_color;
		Integer recommand_power;
		String monsters;
		String monsters_attributes;
		String boss;
		String boss_attributes;
		Integer monsters_max;
		GoblinFomula goblinFomula;
		Map<String, Object> goblin;
		BasicAwardFomula basicAwardFomula;
		MonstersKilledAwardFomula monstersKilledAwardFomula;
	}

	@Data
	class GoblinFomula {
		String pro;
		String monster;
		String hp;
		String dropid;
	}

	@Data
	class BasicAwardFomula {
		Integer time;
		Integer gold;
		Integer exp;
	}

	@Data
	class MonstersKilledAwardFomula{
		String dropid;
		String dropid_bosskilled;
	}

	public static RubyConstStageTableHash getRubyConstStageTableHash(Integer id) {
		return null;
	}

	@Data
	class RubyConstBackpackTable {
		Integer level;
		Integer gold_max;
		Integer exp_max;
		Integer item_max;
		Map<String,Object> prerequests;
	}

	@Data
	class Fomula{
		Integer stone;
	}

	public static RubyConstBackpackTable getRubyConstBackpackTable(Integer id) {
		return null;
	}


}
