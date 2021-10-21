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
@ApiModel(value = "充值管理响应dto")
public class InGamePurchaseDTO implements Serializable {

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键id")
	private String id;

	/**
	 * 钻石数量
	 */
	@ApiModelProperty("钻石数量")
	private Integer stone;

	/**
	 * 内容简介
	 */
	@ApiModelProperty("内容简介")
	private String desc;

	/**
	 * 图标
	 */
	@ApiModelProperty("图标")
	private String icon;

	/**
	 * 价格
	 */
	@ApiModelProperty("价格")
	private Integer cost;
}
