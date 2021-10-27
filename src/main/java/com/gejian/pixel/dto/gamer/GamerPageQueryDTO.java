package com.gejian.pixel.dto.gamer;

import com.gejian.pixel.dto.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 16:21
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("玩家搜索Model")
public class GamerPageQueryDTO extends BasePageQuery {

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String username;


	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

}
