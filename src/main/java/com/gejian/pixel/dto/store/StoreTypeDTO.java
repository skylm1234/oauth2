package com.gejian.pixel.dto.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理商品类型dto
 * @createTime 2021-10-19
 */
@Data
@ApiModel("商品管理商品类型dto")
public class StoreTypeDTO implements Serializable {

	/**
	 * 商品类型码
	 */
	@ApiModelProperty("商品类型码")
	private Integer code;

	/**
	 * 商品类型名字
	 */
	@ApiModelProperty("商品类型名字")
	private String type;
}
