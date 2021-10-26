package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.activity.ActivityDetailDTO;
import com.gejian.pixel.dto.activity.ActivityPageDTO;
import com.gejian.pixel.dto.activity.ActivityQueryDTO;
import com.gejian.pixel.entity.Promotion;

/**
 *  Auto created by codeAppend plugin
 */
public interface PromotionService extends IService<Promotion> {

	Promotion getById(Integer id);

	IPage<ActivityPageDTO> selectPage(ActivityQueryDTO activityQueryDTO);

	ActivityDetailDTO selectById(int id);
}