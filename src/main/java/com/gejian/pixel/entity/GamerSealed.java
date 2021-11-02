package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("gamer_sealed")
public class GamerSealed {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 玩家id
	 */
	@TableField("gamer_id")
	private Long gamerId;

	/**
	 * 封禁开始时间
	 */
	@TableField("start_time")
	private LocalDateTime startTime;

	/**
	 * 封禁结束时间
	 */
	@TableField("terminate_time")
	private LocalDateTime terminateTime;

	/**
	 * 封禁天数
	 */
	@TableField("days")
	private Integer days;

	/**
	 * 封禁原因
	 */
	@TableField("reason")
	private String reason;


	@TableField("enabled")
	private Boolean enabled;


}