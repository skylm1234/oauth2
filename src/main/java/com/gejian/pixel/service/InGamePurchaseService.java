package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.InGamePurchase;

/**
 *  Auto created by codeAppend plugin
 */
public interface InGamePurchaseService extends IService<InGamePurchase> {

	InGamePurchase getById(String id);
}