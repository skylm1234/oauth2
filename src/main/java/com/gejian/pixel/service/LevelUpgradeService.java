package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.LevelUpgrade;

/**
 *  Auto created by codeAppend plugin
 */
public interface LevelUpgradeService extends IService<LevelUpgrade> {

	LevelUpgrade get(Integer id);
}