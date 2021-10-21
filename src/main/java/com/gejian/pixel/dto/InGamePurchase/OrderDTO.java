package com.gejian.pixel.dto.InGamePurchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@Data
@ApiModel(value = "充值管理响应dto")
public class OrderDTO implements Serializable {

	/**
	 * 主键id
	 */
	@ApiModelProperty(value = "主键id")
	private Integer orderId;

	/**
	 * 用户id
	 */
	@ApiModelProperty("user")
	private String user;

	/**
	 * 充值金额
	 */
	@ApiModelProperty("充值金额")
	private Integer money;

	/**
	 * 充值状态 0-未支付 1-支付成功
	 */
	@ApiModelProperty("充值状态 0-未支付 1-支付成功")
	private Boolean status;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty("修改时间")
	private LocalDateTime updateTime;
}
