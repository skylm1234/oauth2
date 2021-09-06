package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Vip;

/**
 *  Auto created by codeAppend plugin
 */
public interface VipService extends IService<Vip> {

	Vip getById(Integer id);
}