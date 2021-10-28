package com.gejian.pixel.dto.gamer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-10-28 11:37
 * @description：
 */

@Data
@ApiModel("玩家操作记录Model")
public class GamerLogDTO {

	@ApiModelProperty("记录id")
	private Long id;

	/**
	 *
	 */
	@ApiModelProperty("玩家id")
	private Long gamerId;

	/**
	 *
	 */
	@ApiModelProperty("玩家用户名")
	private String gamerUsername;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;


	@ApiModelProperty("充值金额")
	private BigDecimal consume;

	/**
	 * vip等级
	 */
	@ApiModelProperty("vip等级")
	private Integer vipLevel;

	/**
	 * 0' COMMENT '是否测试账号
	 */
	@ApiModelProperty(value = "是否测试账号",required = true)
	@NotNull
	private Boolean tester;

	/**
	 * 操作人id
	 */
	@ApiModelProperty("操作人id")
	private Integer sysUserId;

	/**
	 * 操作人id
	 */
	@ApiModelProperty("操作员姓名")
	private String sysUserRealName;

	/**
	 * 操作记录
	 */
	@ApiModelProperty("操作记录")
	private String context;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;
}
