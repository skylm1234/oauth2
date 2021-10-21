package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.entity.GoodsFomula;
import com.gejian.pixel.entity.NewStoreGoods;
import com.gejian.pixel.entity.ext.StorePageDO;
import com.gejian.pixel.entity.ext.StoreQueryDO;

import java.util.List;

/**
 * Auto created by codeAppend plugin
 */
public interface NewStoreGoodsMapper  extends BaseMapper<NewStoreGoods> {

	/**
	 * 商店管理多表查询
	 * @param storeQueryDO 查询条件
	 * @return  List
	 */
	List<StorePageDO> selectPageByQuery(StoreQueryDO storeQueryDO);

	/**
	 *
	 * @param storeQueryDO
	 * @return
	 */
	Integer selectCountByQuery(StoreQueryDO storeQueryDO);

	/**
	 *
	 * @param goodsFomula
	 * @return
	 */
	List<NewStoreGoods> selectnewStoreGoodsList(GoodsFomula goodsFomula);
}