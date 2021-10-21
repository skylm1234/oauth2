package com.gejian.pixel.dto.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StoreDTO.java
 * @Description 商品管理dto
 * @createTime 2021-10-19
 */
@Data
@ApiModel("商品管理dto")
public class StoreDTO implements Serializable {

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	private String id;

	/**
	 * 物品
	 */
	@ApiModelProperty("物品范围开始")
	private String form;

	/**
	 * 物品
	 */
	@ApiModelProperty("物品范围结束")
	private String to;

	/**
	 * 物品类型
	 */
	@ApiModelProperty("物品类型 0：手动技能 1：自动技能 2：被动技能 3：一阶经验书 4：二阶经验书 5：三阶经验书  6：四阶经验书 " +
			"7：绿色英雄碎片 8：蓝色英雄碎片 9：紫色英雄碎片 10：金色英雄碎片 11：金币")
	private Integer type;

	/**
	 * 商店
	 */
	@ApiModelProperty("商店 1:折扣 2：热卖 3：限时")
	private Integer shop;

	/**
	 * 修改前商店
	 */
	@ApiModelProperty("修改前商店 1:折扣 2：热卖 3：限时")
	private Integer oldShop;

	/**
	 * 数量
	 */
	@ApiModelProperty("数量")
	private Long number;

	/**
	 * 价格
	 */
	@ApiModelProperty("价格")
	private Long costNumber;

	/**
	 * 价格单位
	 */
	@ApiModelProperty("价格单位 0: stone-钻石  1：honor-荣耀  2：gold-金币")
	private Integer cost;

	/**
	 * 金币个数
	 */
	@ApiModelProperty("金币个数")
	private Long goldNumber;
}
