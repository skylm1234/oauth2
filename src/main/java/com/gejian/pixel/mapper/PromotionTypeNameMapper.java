package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.dto.activity.type.ActivityGroupTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypePageDTO;
import com.gejian.pixel.entity.PromotionTypeName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Auto created by codeAppend plugin
 */
public interface PromotionTypeNameMapper extends BaseMapper<PromotionTypeName> {

	long countOfView(@Param("typeName")String typeName);

	List<ActivityTypePageDTO> pageOfView(@Param("typeName")String typeName, @Param("start")int start, @Param("size") int size);

	int selectMaxGroupIndex();

	void updateGroupName(PromotionTypeName promotionTypeName);

	List<ActivityGroupTypeDTO> selectGroups();
}