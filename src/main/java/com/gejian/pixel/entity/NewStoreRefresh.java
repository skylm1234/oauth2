package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("new_store_refresh")
public class NewStoreRefresh {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 刷新时间名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 商店刷新时间描述
	 */
	@TableField("refresh_time")
	private String refreshTime;

	/**
	 * cron表达式
	 */
	@TableField("cron")
	private String cron;

	/**
	 * 是否选择 0:false  1:true
	 */
	@TableField("check_flag")
	private boolean checkFlag;


}