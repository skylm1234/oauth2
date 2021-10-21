package com.gejian.pixel.dto.InGamePurchase;

import com.gejian.pixel.dto.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName InGamePurchasePageDTO.java
 * @Description 充值管理分页条件入参
 * @createTime 2021-08-19 15:22:00
 */
@Data
public class OrderPageDTO extends BasePageQuery {

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "订单号")
	private Integer orderId;

	/**
	 * 用户id
	 */
	@ApiModelProperty("user")
	private String user;

}
