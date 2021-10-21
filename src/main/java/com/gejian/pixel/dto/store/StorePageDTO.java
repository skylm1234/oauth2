package com.gejian.pixel.dto.store;

import com.gejian.pixel.entity.NewStoreGoods;
import com.gejian.pixel.enums.ShopTypeEnum;
import com.gejian.pixel.enums.StoreTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理条件入参
 * @createTime 2021-10-19 15:22:00
 */
@Data
@ApiModel("商店管理查询条件")
public class StorePageDTO implements Serializable {
	/**
	 * id
	 */
	@ApiModelProperty("id")
	private Long id;

	/**
	 * 物品
	 */
	@ApiModelProperty("物品")
	private List<NewStoreGoods> goods;

	/**
	 * 物品类型
	 */
	@ApiModelProperty("物品类型 0：手动技能 1：自动技能 2：被动技能 3：一阶经验书 4：二阶经验书 5：三阶经验书  6：四阶经验书 " +
			"7：绿色英雄碎片 8：蓝色英雄碎片 9：紫色英雄碎片 10：金色英雄碎片 11：金币")
	private Integer storeType;

	/**
	 * 商店 1:折扣 2：热卖 3：限时
	 */
	@ApiModelProperty("商店 1:折扣 2：热卖 3：限时")
	private Integer shop;

	/**
	 * 数量
	 */
	@ApiModelProperty("数量")
	private Long count;

	/**
	 * 价格
	 */
	@ApiModelProperty("价格")
	private Long price;
}
