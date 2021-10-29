package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.stage.BackGroundDTO;
import com.gejian.pixel.dto.stage.PreRequestDTO;
import com.gejian.pixel.dto.stage.StageDTO;
import com.gejian.pixel.dto.stage.StageQueryDTO;
import com.gejian.pixel.entity.Stage;
import com.gejian.pixel.proto.ConstStageTableItemExProtobuf;

import java.util.List;

/**
 * Auto created by codeAppend plugin
 */
public interface StageService extends IService<Stage> {

	void deleteById(Integer id);

	ConstStageTableItemExProtobuf.ConstStageTableItemEx getExById(Integer id);

	Stage getById(Integer id);

	ConstStageTableItemExProtobuf.ConstStageTableItemEx getItem(Integer id);

	/**
	 * 关卡管理分页查询
	 * @param stageQueryDTO 分页dto
	 * @return IPage
	 */
	IPage<StageDTO> getPage(StageQueryDTO stageQueryDTO);

	/**
	 * 获取前置关卡
	 * @return List
	 */
	List<PreRequestDTO> getPreRequest(Integer difficulty);

	/**
	 * 获取背景
	 * @return
	 */
	List<BackGroundDTO> getBackground();

	IPage<StageDTO> selectPage(StageQueryDTO stageQueryDTO);

	StageDTO selectById(Integer id);

	void createStage(StageDTO stageDTO);

	void updateStage(StageDTO stageDTO);
}