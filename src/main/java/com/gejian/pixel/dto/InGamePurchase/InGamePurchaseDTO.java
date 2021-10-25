package com.gejian.pixel.dto.InGamePurchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@Data
@ApiModel(value = "充值Model")
public class InGamePurchaseDTO implements Serializable {

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键id")
	private String id;

	/**
	 * 钻石数量
	 */
	@ApiModelProperty(value = "钻石数量",required = true)
	private Integer stone;

	/**
	 * 内容简介
	 */
	@ApiModelProperty(value = "内容简介",required = true)
	private String desc;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标",required = true)
	private String icon;

	/**
	 * 价格
	 */
	@ApiModelProperty(value = "价格",required = true)
	private Integer cost;
}
