package com.gejian.pixel.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.store.*;
import com.gejian.pixel.entity.NewStoreGoods;
import com.gejian.pixel.enums.StoreTypeEnum;

import java.util.List;
import java.util.Optional;

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
	 * @param type  类型dto
	 * @return List
	 */
	List<StoreGoodsDTO> getListByType(StoreTypeEnum type);

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
	void saveByStore(StoreDTO storeDTO);

	/**
	 * 编辑商店管理
	 * @param storeDTO dto
	 * @return Boolean
	 */
	void updateByStore(StoreDTO storeDTO);

	/**
	 * 删除
	 * @param id 商品id
	 */
	void deleteByStore(String id);

	/**
	 * 获取刷新时间
	 * @return list
	 */
	List<StoreRefreshDTO> getListRefresh();

	/**
	 * 修改商店刷新时间
	 * @param storeRefreshDTO 刷新时间dto
	 * @return boolean
	 */
	Boolean updateRefresh(StoreRefreshDTO storeRefreshDTO);

	Optional<StoreDTO> selectById(String id);
}