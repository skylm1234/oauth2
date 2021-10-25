package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.entity.NewStoreHot;
import org.apache.ibatis.annotations.Select;

/**
 * Auto created by codeAppend plugin
 */
public interface NewStoreHotMapper extends BaseMapper<NewStoreHot> {

	@Select("select max(places) from new_store_hot")
	int maxPlaces();

}