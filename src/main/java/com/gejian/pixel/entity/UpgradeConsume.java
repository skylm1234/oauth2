package com.gejian.pixel.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhouQiang
 * @date 2021/9/6$
 */
@Data
public class UpgradeConsume implements Serializable {

	@Alias("X1")
	private UpgradeConsumeFomula x1;
	@Alias("X2")
	private UpgradeConsumeFomula x2;
	@Alias("X3")
	private UpgradeConsumeFomula x3;
	@Alias("X4")
	private UpgradeConsumeFomula x4;
	@Alias("X5")
	private UpgradeConsumeFomula x5;
	@Alias("X6")
	private UpgradeConsumeFomula x6;
	@Alias("X7")
	private UpgradeConsumeFomula x7;
	@Alias("X8")
	private UpgradeConsumeFomula x8;
	@Alias("X9")
	private UpgradeConsumeFomula x9;

}
