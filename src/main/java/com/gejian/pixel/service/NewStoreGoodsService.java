package com.gejian.pixel.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchaseDTO;
import com.gejian.pixel.dto.store.*;
import com.gejian.pixel.entity.NewStoreGoods;

import java.util.List;

/**
 *  Auto created by codeAppend plugin
 */
public interface NewStoreGoodsService extends IService<NewStoreGoods>{
	/**
	 * 获取商品类型
	 * @return List
	 */
	List<StoreTypeDTO> getType();

	/**
	 * 通过类型获取具体商品
	 * @param storeTypeDTO  类型dto
	 * @return List
	 */
	List<StoreGoodsDTO> getListByType(StoreTypeDTO storeTypeDTO);

	/**
	 * 分页查询
	 * @param storeQueryDTO 查询条件dto
	 * @return IPage
	 */
	IPage<StorePageDTO> getPage(StoreQueryDTO storeQueryDTO);

	/**
	 * 新增商店管理
	 * @param storeDTO dto
	 * @return Boolean
	 */
	Boolean saveByStore(StoreDTO storeDTO);

	/**
	 * 编辑商店管理
	 * @param storeDTO dto
	 * @return Boolean
	 */
	Boolean updateByStore(StoreDTO storeDTO);

	/**
	 * 删除
	 * @param storeDTO dto
	 * @return Boolean
	 */
	Boolean deleteByStore(StoreDTO storeDTO);
}