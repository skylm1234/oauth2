package com.gejian.pixel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gejian.pixel.dto.stage.BackGroundDTO;
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
public enum BackgroundEnum implements CodeToJsonEnums{
	/**
	 *
	 */
	BG01(1,"bg01"),
	BG02(2,"bg02"),
	BG03(3,"bg03"),
	BG04(4,"bg04"),
	BG05(5,"bg05"),
	BG06(6,"bg06"),
	BG07(7,"bg07"),
	BG08(8,"bg08"),
	BG09(9,"bg09");



	private Integer code;
	private String type;

	private static final Map<Integer, BackgroundEnum> VALUE_MAP = new HashMap<>();

	static {
		for(BackgroundEnum backgroundEnum : BackgroundEnum.values()){
			VALUE_MAP.put(backgroundEnum.code,backgroundEnum);
		}
	}

	@JsonCreator
	public static BackgroundEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

	public static List<BackGroundDTO> toBackGroundDTO(){
		return Arrays.stream(BackgroundEnum.values())
				.map(background -> new BackGroundDTO(background.type,background.type)).collect(Collectors.toList());
	}

}
