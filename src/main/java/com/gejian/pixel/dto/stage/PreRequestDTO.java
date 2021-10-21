package com.gejian.pixel.dto.stage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StoreGoodsDTO.java
 * @Description 商品管理商品类型详情dto
 * @createTime 2021-10-19
 */
@Data
@ApiModel("前置关卡dto")
public class PreRequestDTO implements Serializable {

	/**
	 * 关卡分类
	 */
	@ApiModelProperty("关卡难度 1:普通 2：噩梦 3：折磨")
	private Integer classType;

	/**
	 * 前置关卡
	 */
	@ApiModelProperty("前置关卡")
	private List<PreRequestTypeDTO> PreRequests;

}
