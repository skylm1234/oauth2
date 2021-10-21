package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.stage.PreRequestDTO;
import com.gejian.pixel.dto.stage.StageTypeDTO;
import com.gejian.pixel.entity.StageClass;

import java.util.List;

/**
 *  Auto created by codeAppend plugin
 */
public interface StageClassService extends IService<StageClass> {

	/**
	 * 查询关卡分类
	 * @return List
	 */
	List<StageTypeDTO> getType();

}