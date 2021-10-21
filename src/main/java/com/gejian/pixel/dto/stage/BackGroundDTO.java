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
@ApiModel("背景dto")
public class BackGroundDTO implements Serializable {


	/**
	 * 背景
	 */
	@ApiModelProperty("背景")
	private Integer bg;

	/**
	 * 背景名字
	 */
	@ApiModelProperty("背景名字")
	private String name;
}
