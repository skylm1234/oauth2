package com.gejian.pixel.dto.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ：lijianghuai
 * @date ：2021-10-29 11:33
 * @description：
 */

@Data
@ApiModel("怪物分类DTO")
public class HeroTreeDTO {

	@ApiModelProperty("id")
	private Integer id;

	@ApiModelProperty("名称")
	private String name;

	@ApiModelProperty("子集")
	private List<HeroTreeDTO> children;

	public HeroTreeDTO(){

	}

	public HeroTreeDTO(Integer id,String name){
		this.id = id;
		this.name = name;
	}
}
