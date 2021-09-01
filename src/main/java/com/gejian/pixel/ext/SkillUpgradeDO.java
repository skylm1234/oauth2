package com.gejian.pixel.ext;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangtao
 * @since 2021/9/1
 */
@Data
public class SkillUpgradeDO implements Serializable {

	private Long gold = 1000L;

	private Long book = 1L;
}
