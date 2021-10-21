package com.gejian.pixel.controller;

import com.gejian.pixel.dto.stage.BackGroundDTO;
import com.gejian.pixel.dto.stage.PreRequestDTO;
import com.gejian.pixel.dto.stage.StageTypeDTO;
import com.gejian.pixel.service.StageClassService;
import com.gejian.pixel.service.StageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@RestController
@RequestMapping("/api/stage")
@Api(value = "api-stage",tags = "关卡管理api")
public class StageController {

	@Autowired
	private StageClassService stageClassService;
	@Autowired
	private StageService stageService;

	@ApiOperation("查询关卡分类")
	@PostMapping("/type")
	public List<StageTypeDTO> getType(){
		return this.stageClassService.getType();
	}

	@ApiOperation("查询前置关卡")
	@PostMapping("/preRequest")
	public List<PreRequestDTO> getPreRequest(){
		return this.stageService.getPreRequest();
	}

	@ApiOperation("查询背景")
	@PostMapping("/background")
	public List<BackGroundDTO> getBackground(){
		return this.stageService.getBackground();
	}


}
