package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.NewStoreDiscount;
import com.gejian.pixel.entity.ext.StorePageDO;

/**
 *  Auto created by codeAppend plugin
 */
public interface NewStoreDiscountService extends IService<NewStoreDiscount> {

	/**
	 * 新增商店管理
	 * @param storePageDO do
	 * @return Boolean
	 */
	Boolean saveByStore(StorePageDO storePageDO);

	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	Boolean deleteByStore(String id);

	/**
	 * 修改
	 * @param storePageDO do
	 * @return Booleanc
	 */
	Boolean updateByStore(StorePageDO storePageDO);

	int maxPlaces();
}