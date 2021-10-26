package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.activity.ActivityGroupDTO;
import com.gejian.pixel.entity.PromotionTypeName;

import java.util.List;

/**
 *  Auto created by codeAppend plugin
 */
public interface PromotionTypeNameService extends IService<PromotionTypeName> {

	List<ActivityGroupDTO> listToGroup();

}