package com.gejian.pixel.dto.store;

import com.gejian.pixel.dto.BasePageQuery;
import com.gejian.pixel.enums.ShopTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StoreQueryDTO.java
 * @Description 商品管理条件入参
 * @createTime 2021-10-19 15:22:00
 */
@Data
@ApiModel("商店管理查询条件")
public class StoreQueryDTO extends BasePageQuery {
	/**
	 * 商品类型名字
	 */
	@ApiModelProperty("商品类型名字")
	private String type;

	/**
	 * 商品类型名字
	 */
	@ApiModelProperty("商店名字  1:折扣 2：热卖 3：限时")
	private Integer shop;
}
