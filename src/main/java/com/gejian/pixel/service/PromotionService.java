package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Promotion;

/**
 *  Auto created by codeAppend plugin
 */
public interface PromotionService extends IService<Promotion> {

	Promotion getById(Integer id);
}