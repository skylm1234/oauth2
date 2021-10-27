package com.gejian.pixel.dto.gamer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 16:21
 * @description：
 */

@Data
@ApiModel("玩家Model")
public class GamerDTO {

	@ApiModelProperty("玩家id")
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名",required = true)
	@NotBlank
	private String username;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	private String nickname;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * 0' COMMENT '是否vip
	 */
	@ApiModelProperty("是否vip")
	private Boolean vip;

	/**
	 * vip等级
	 */
	@ApiModelProperty("vip等级")
	private Integer vipLevel;

	/**
	 * 战力
	 */
	@ApiModelProperty("战力")
	private Integer ce;

	/**
	 * 金币
	 */
	@ApiModelProperty("黄金")
	private Integer gold;

	/**
	 * 钻石
	 */
	@ApiModelProperty("钻石")
	private Integer stone;

	/**
	 * 荣誉
	 */
	@ApiModelProperty("荣誉")
	private Integer honor;

	/**
	 * 1' COMMENT '状态（正常，封禁）
	 */
	@ApiModelProperty("状态")
	private Boolean state;

	/**
	 * 0' COMMENT '是否测试账号
	 */
	@ApiModelProperty(value = "是否测试账号",required = true)
	@NotNull
	private Boolean tester;

	/**
	 *
	 */
	@ApiModelProperty("注册时间")
	private LocalDateTime createTime;

	/**
	 *
	 */
	@ApiModelProperty("更新时间")
	private LocalDateTime updateTime;

}
