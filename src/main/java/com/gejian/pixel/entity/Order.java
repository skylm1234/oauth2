package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`order`")
public class Order {
	/**
	 * 订单编号
	 */
	@TableField("order_id")
	private Integer orderId;

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 充值金额
	 */
	@TableField("money")
	private Integer money;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Date updateTime;
	/**
	 * 用户名
	 */
	@TableField("user")
	private String user;
	/**
	 * 充值状态 0-未支付 1-支付成功
	 */
	@TableField("status")
	private Boolean status;
}