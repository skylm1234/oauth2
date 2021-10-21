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
@ApiModel("关卡分类dto")
public class StageTypeDTO implements Serializable {


	/**
	 * 关卡分类
	 */
	@ApiModelProperty("关卡分类")
	private Integer classType;

	/**
	 * 关卡名字
	 */
	@ApiModelProperty("关卡名字")
	private String name;
}
