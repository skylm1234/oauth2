package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Stage;
import com.gejian.pixel.proto.ConstStageTableItemExProtobuf;

/**
 * Auto created by codeAppend plugin
 */
public interface StageService extends IService<Stage> {
	ConstStageTableItemExProtobuf.ConstStageTableItemEx getExById(Integer id);
	Stage getById(Integer id);
}