package com.gejian.pixel.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 17:47
 * @description：
 */
@Data
public class BasePageQuery implements Serializable {

	@Min(1)
	@ApiModelProperty("页码")
	private Integer current = 1;

	@ApiModelProperty("分页size")
	private Integer size = 10;

	/**
	 * 获取分页对象
	 *
	 * @return
	 */
	public <T> Page<T> getPage(){
		return new Page<T>(current,size);
	}


}
