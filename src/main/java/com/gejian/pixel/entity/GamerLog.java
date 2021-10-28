package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("gamer_log")
public class GamerLog {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("gamer_id")
	private Long gamerId;

	/**
	 * 操作人id
	 */
	@TableField("sys_user_id")
	private Integer sysUserId;

	/**
	 * 操作记录
	 */
	@TableField("context")
	private String context;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;


}