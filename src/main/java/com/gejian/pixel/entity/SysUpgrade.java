package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_upgrade")
public class SysUpgrade {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("title")
	private String title;

	/**
	 * 
	 */
	@TableField("upgrade_time")
	private LocalDateTime upgradeTime;

	/**
	 * 
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 0
	 */
	@TableField("status")
	private Boolean status;

	@TableField("deleted")
	@TableLogic
	private Boolean deleted;

}