package com.gejian.pixel.dto.stage;

import com.gejian.pixel.dto.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StoreQueryDTO.java
 * @Description 商品管理条件入参
 * @createTime 2021-10-19 15:22:00
 */
@Data
@ApiModel("商店管理查询条件")
public class StageQueryDTO extends BasePageQuery {
	/**
	 * 关卡分类
	 */
	@ApiModelProperty("关卡分类")
	private Integer classType;

	/**
	 * 关卡名字
	 */
	@ApiModelProperty("关卡名字")
	private String name;
}
