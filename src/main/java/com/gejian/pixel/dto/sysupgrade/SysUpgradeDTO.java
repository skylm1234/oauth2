package com.gejian.pixel.dto.sysupgrade;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-11-03 14:37
 * @description：
 */
@Data
@ApiModel("更新管理Model")
public class SysUpgradeDTO {

	@ApiModelProperty("id")
	private Long id;

	@NotBlank
	@ApiModelProperty(value = "标题",required = true)
	private String title;

	@NotNull
	@ApiModelProperty(value = "更新时间",required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime upgradeTime;

	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty("状态。false:待更新，true：已更新")
	private Boolean status;
}
