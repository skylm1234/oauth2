package com.gejian.pixel.dto.stage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StoreGoodsDTO.java
 * @Description 商品管理商品类型详情dto
 * @createTime 2021-10-19
 */
@Data
@ApiModel("前置关卡dto")
public class PreRequestTypeDTO implements Serializable {

	/**
	 * 关卡id
	 */
	@ApiModelProperty("关卡id")
	private Integer id;

	/**
	 * 关卡名字
	 */
	@ApiModelProperty("关卡名字")
	private String name;

}
