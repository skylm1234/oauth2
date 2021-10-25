package com.gejian.pixel.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author ：lijianghuai
 * @date ：2021-10-18 9:49
 * @description：
 */
public interface CodeToJsonEnums {

	@JsonValue
	default Integer toJson(){
		return getCode();
	}

	Integer getCode();

	@Getter
	@ApiModel("枚举结果")
	class EnumResult{
		@ApiModelProperty("编号")
		private final Integer code;
		@ApiModelProperty("名称")
		private final String name;
		EnumResult(Integer code,String name){
			this.code = code;
			this.name = name;
		}
	}
}
