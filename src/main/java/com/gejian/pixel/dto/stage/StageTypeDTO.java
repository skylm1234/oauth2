package com.gejian.pixel.dto.stage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class StageTypeDTO implements Serializable {


	/**
	 * 关卡分类
	 */
	@ApiModelProperty("分类编号")
	private Integer type;

	/**
	 * 关卡名字
	 */
	@ApiModelProperty("分类名称")
	private String name;
}
