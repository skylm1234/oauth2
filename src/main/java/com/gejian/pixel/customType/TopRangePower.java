package com.gejian.pixel.customType;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author ljb
 * @date 2021年09月02日 15:26
 * @description
 */
@Data
public class TopRangePower {
	private Integer myrank;
	private List<Map<String,Object>> ranks;
}
