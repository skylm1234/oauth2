package com.gejian.pixel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Data
@ApiModel("用户Model")
public class SysUserDTO implements Serializable {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键id")
	private Integer userId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 密码
	 */
	@JsonIgnore
	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	/**
	 * 锁定标记
	 */
	@ApiModelProperty(value = "锁定标记")
	private Boolean lockFlag;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;

	/**
	 * 0-正常，1-删除
	 */
	@ApiModelProperty(value = "删除标记")
	private Boolean delFlag;

}
