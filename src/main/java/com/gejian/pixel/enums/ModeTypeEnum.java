package com.gejian.pixel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gejian.pixel.dto.stage.StageDifficultyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuanxue
 * @Description
 * @createTime 2021年10月18日 17:18:00
 */
@AllArgsConstructor
@Getter
public enum ModeTypeEnum implements CodeToJsonEnums{
	/**
	 * 1:普通 2：噩梦 3：折磨
	 */
	ORDINARY(1,"普通关卡"),
	NIGHTMARE(2,"噩梦关卡"),
	TORMENT(3,"折磨关卡");

	private Integer code;
	private String type;

	private static final Map<Integer, ModeTypeEnum> VALUE_MAP = new HashMap<>();

	static {
		for(ModeTypeEnum storeTypeEnum : ModeTypeEnum.values()){
			VALUE_MAP.put(storeTypeEnum.code,storeTypeEnum);
		}
	}

	@JsonCreator
	public static ModeTypeEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

	public static List<StageDifficultyDTO> toDifficultyDTO(){
		return Arrays.stream(ModeTypeEnum.values()).map(modeType -> new StageDifficultyDTO(modeType.code,modeType.type))
				.collect(Collectors.toList());
	}

}
