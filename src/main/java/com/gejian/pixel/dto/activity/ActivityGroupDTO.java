package com.gejian.pixel.dto.activity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author ：lijianghuai
 * @date ：2021-10-25 17:06
 * @description：
 */

@ApiModel("活动分类Model")
@Data
public class ActivityGroupDTO {

	private Integer id;

	private String name;

	private List<ActivityGroupDTO> children;
}
