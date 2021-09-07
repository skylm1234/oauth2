package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Stage;

/**
 *  Auto created by codeAppend plugin
 */
public interface StageService extends IService<Stage> {

	Stage getById(Integer id);
}