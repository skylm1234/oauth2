package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.entity.NewStoreTimeLimit;
import org.apache.ibatis.annotations.Select;

/**
 * Auto created by codeAppend plugin
 */
public interface NewStoreTimeLimitMapper extends BaseMapper<NewStoreTimeLimit> {

	@Select("select max(places) from new_store_time_limit")
	int maxPlaces();
}