package com.gejian.pixel.dto.stage;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	 * 关卡难度
	 */
	@JsonIgnore
	private Integer stageId;

	@JsonIgnore
	private Integer stageClassType;

	@ApiModelProperty("type id")
	private String id;

	/**
	 * 关卡难度名称
	 */
	@ApiModelProperty("名字")
	private String name;

	/**
	 * 前置关卡
	 */
	@ApiModelProperty("前置关卡")
	private List<PreRequestDTO> items;

	public PreRequestDTO(){

	}

	public PreRequestDTO(String id,String name){
		this.id = id;
		this.name = name;
	}

	public PreRequestDTO(Integer stageId,Integer stageClassType,String id,String name){
		this.id = id;
		this.name = name;
		this.stageId = stageId;
		this.stageClassType = stageClassType;
	}

}
