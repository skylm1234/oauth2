package com.gejian.pixel.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 17:47
 * @description：
 */
@Data
public class BasePageQuery implements Serializable {

	@ApiModelProperty("页码")
	protected Integer current = 1;

	@ApiModelProperty("分页size")
	protected Integer size = 10;

	/**
	 * 获取分页对象
	 *
	 * @return
	 */
	@JsonIgnore
	@ApiIgnore
	@ApiModelProperty(hidden = true)
	public <T> Page<T> getPage(){
		return new Page<>(current, size);
	}
}
