package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("gamer")
public class Gamer {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户名
	 */
	@TableField("username")
	private String username;

	/**
	 * 昵称
	 */
	@TableField("nickname")
	private String nickname;

	/**
	 * 手机号
	 */
	@TableField("mobile")
	private String mobile;

	/**
	 * 0' COMMENT '是否vip
	 */
	@TableField("vip")
	private Boolean vip;

	/**
	 * vip等级
	 */
	@TableField("vip_level")
	private Integer vipLevel;

	/**
	 * 战力
	 */
	@TableField("ce")
	private Integer ce;

	/**
	 * 金币
	 */
	@TableField("gold")
	private Integer gold;

	/**
	 * 钻石
	 */
	@TableField("stone")
	private Integer stone;

	/**
	 * 荣誉
	 */
	@TableField("honor")
	private Integer honor;

	/**
	 * 1' COMMENT '状态（正常，封禁）
	 */
	@TableField("state")
	private Boolean state;

	/**
	 * 0' COMMENT '是否测试账号
	 */
	@TableField("tester")
	private Boolean tester;

	@TableField("deleted")
	@TableLogic
	private Boolean deleted;

	/**
	 * 
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 
	 */
	@TableField("update_time")
	private LocalDateTime updateTime;

}