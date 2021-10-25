package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.entity.NewStoreDiscount;
import org.apache.ibatis.annotations.Select;

/**
 * Auto created by codeAppend plugin
 */
public interface NewStoreDiscountMapper extends BaseMapper<NewStoreDiscount> {

	@Select("select max(places) from new_store_discount")
	int maxPlaces();

}