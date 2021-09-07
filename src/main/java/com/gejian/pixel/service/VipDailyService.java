package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.VipDaily;

/**
 *  Auto created by codeAppend plugin
 */
public interface VipDailyService extends IService<VipDaily> {

	VipDaily getById(Integer id);
}