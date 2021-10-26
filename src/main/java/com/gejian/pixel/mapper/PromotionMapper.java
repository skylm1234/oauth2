package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.dto.activity.ActivityDetailDTO;
import com.gejian.pixel.dto.activity.ActivityPageDTO;
import com.gejian.pixel.dto.activity.ActivityQueryDTO;
import com.gejian.pixel.entity.Promotion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Auto created by codeAppend plugin
 */
public interface PromotionMapper extends BaseMapper<Promotion> {

	List<ActivityPageDTO> selectActivityPage(@Param("q") ActivityQueryDTO activityQueryDTO,@Param("start") int start,@Param("size") int size);

	long selectActivityCount(@Param("q") ActivityQueryDTO activityQueryDTO);

	long selectActivityRefrence(int id);

	ActivityDetailDTO selectByIdToDTO(int id);
}