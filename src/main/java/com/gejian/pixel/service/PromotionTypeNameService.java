package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.activity.ActivityGroupDTO;
import com.gejian.pixel.dto.activity.type.ActivityGroupTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypePageDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypeQueryDTO;
import com.gejian.pixel.entity.PromotionTypeName;

import java.util.List;
import java.util.Optional;

/**
 *  Auto created by codeAppend plugin
 */
public interface PromotionTypeNameService extends IService<PromotionTypeName> {

	List<ActivityGroupDTO> listToGroup();

    IPage<ActivityTypePageDTO> selectPage(ActivityTypeQueryDTO activityTypeQueryDTO);

	void save(ActivityTypeDTO activityTypeDTO);

	void update(ActivityTypeDTO activityTypeDTO);

	void delete(String id);

	List<ActivityGroupTypeDTO> groups();

	Optional<ActivityTypePageDTO> selectById(String id);
}