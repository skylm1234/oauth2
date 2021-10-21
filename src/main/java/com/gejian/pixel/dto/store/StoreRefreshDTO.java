package com.gejian.pixel.dto.store;

import com.gejian.pixel.entity.NewStoreGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理刷新时间
 * @createTime 2021-10-19 15:22:00
 */
@Data
@ApiModel("商店管理查询条件")
public class StoreRefreshDTO implements Serializable {
	/**
	 * id
	 */
	@ApiModelProperty("id")
	private Long id;

	/**
	 * 刷新时间名称
	 */
	@ApiModelProperty("刷新时间名称")
	private String name;

	/**
	 * 商店刷新时间描述
	 */
	@ApiModelProperty("商店刷新时间描述")
	private String refreshTime;
}
