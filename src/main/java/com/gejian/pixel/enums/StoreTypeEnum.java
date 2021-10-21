package com.gejian.pixel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuanxue
 * @Description
 * @createTime 2021年10月18日 17:18:00
 */
@AllArgsConstructor
@Getter
public enum StoreTypeEnum {
	/**
	 *  0：手动技能 1：自动技能 2：被动技能 3：一阶经验书 4：二阶经验书 5：三阶经验书  6：四阶经验书 7：绿色英雄碎片 8：蓝色英雄碎片 9：紫色英雄碎片 10：金色英雄碎片 11：金币
	 */
	BOOK_SKILL_HAND(0,"手动技能","book_skill","1001-1047"),
	BOOK_SKILL_AUTO(1,"自动技能","book_skill","2001-2047"),
	BOOK_SKILL_PASSIVITY(2,"被动技能","book_skill","3001-3094"),
	EXP_BOOK_1(3,"一阶经验书","exp_book","1-1"),
	EXP_BOOK_2 (4,"二阶经验书","exp_book","2-2"),
	EXP_BOOK_3(5,"三阶经验书","exp_book","3-3"),
	EXP_BOOK_4(6,"四阶经验书","exp_book","4-4"),
	PRIVATE_SOULCHIP_GREEN(7,"绿色英雄碎片","private_soulchip","10001-10011"),
	PRIVATE_SOULCHIP_BLUE(8,"蓝色英雄碎片","private_soulchip","20001-20012"),
	PRIVATE_SOULCHIP_PURPLE(9,"紫色英雄碎片","private_soulchip","30001-30021"),
	PRIVATE_SOULCHIP_GOLDEN(10,"金色英雄碎片","private_soulchip","40001-40003"),
	GOLD(11,"金币","gold",null);




	private Integer code;
	private String type;
	private String prefix;
	private String range;

	private static final Map<Integer,StoreTypeEnum> VALUE_MAP = new HashMap<>();
	private static final Map<String,StoreTypeEnum> PREFIX_MAP = new HashMap<>();

	static {
		for(StoreTypeEnum storeTypeEnum : StoreTypeEnum.values()){
			VALUE_MAP.put(storeTypeEnum.code,storeTypeEnum);
		}
		for(StoreTypeEnum storeTypeEnum : StoreTypeEnum.values()){
			if (!Objects.equals(storeTypeEnum.getCode(), GOLD.code)){
				PREFIX_MAP.put(storeTypeEnum.prefix + "_" + storeTypeEnum.range.charAt(0),storeTypeEnum);
			} else {
				PREFIX_MAP.put(storeTypeEnum.prefix, storeTypeEnum);
			}
		}
	}

	@JsonCreator
	public static StoreTypeEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

	@JsonCreator
	public static StoreTypeEnum valueOfByPrefix(String prefix) {
		return PREFIX_MAP.get(prefix);
	}

}
