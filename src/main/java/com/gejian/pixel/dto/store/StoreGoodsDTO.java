package com.gejian.pixel.dto.store;

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
@ApiModel("商品管理商品类型详情dto")
public class StoreGoodsDTO implements Serializable {

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private String id;

	/**
	 * 商品名称
	 */
	@ApiModelProperty("商品名称")
	private String name;

	/**
	 * 商品描述
	 */
	@ApiModelProperty("商品描述")
	private String desc;
}
