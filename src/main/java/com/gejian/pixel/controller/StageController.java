package com.gejian.pixel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.stage.BackGroundDTO;
import com.gejian.pixel.dto.stage.PreRequestDTO;
import com.gejian.pixel.dto.stage.StageDTO;
import com.gejian.pixel.dto.stage.StageDifficultyDTO;
import com.gejian.pixel.dto.stage.StageQueryDTO;
import com.gejian.pixel.dto.stage.StageTypeDTO;
import com.gejian.pixel.enums.BackgroundEnum;
import com.gejian.pixel.enums.ModeTypeEnum;
import com.gejian.pixel.service.StageClassService;
import com.gejian.pixel.service.StageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : lijianghuai
 */
@RestController
@RequestMapping("/api/stages")
@Api(value = "api-stage",tags = "关卡管理")
public class StageController {

	@Autowired
	private StageClassService stageClassService;
	@Autowired
	private StageService stageService;

	@ApiOperation("关卡分类")
	@GetMapping("/types")
	public List<StageTypeDTO> getType(){
		return this.stageClassService.getType();
	}


	@ApiOperation("关卡难度")
	@GetMapping("/difficulties")
	public List<StageDifficultyDTO> difficulties(){
		return ModeTypeEnum.toDifficultyDTO();
	}

	@ApiOperation("前置关卡")
	@GetMapping("/precondition")
	public List<PreRequestDTO> getPreRequest(){
		return this.stageService.getPreRequest();
	}

	@ApiOperation("查询背景")
	@GetMapping("/background")
	public List<BackGroundDTO> getBackground(){
		return BackgroundEnum.toBackGroundDTO();
	}

	@GetMapping()
	@ApiOperation("关卡列表")
	public IPage<StageDTO> list(StageQueryDTO stageQueryDTO){
		return stageService.selectPage(stageQueryDTO);
	}

	@GetMapping("/{id}")
	@ApiOperation("关卡详情")
	public StageDTO id(@ApiParam(value = "关卡id",required = true) @PathVariable Integer id){
		return stageService.selectById(id);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("删除关卡")
	public ResponseEntity<Void> delete(@ApiParam(value = "关卡id",required = true) @PathVariable Integer id){
		 stageService.deleteById(id);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping()
	@ApiOperation("创建关卡")
	public ResponseEntity<Void> create(@Valid @RequestBody StageDTO stageDTO){
		stageService.createStage(stageDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation("编辑关卡")
	public ResponseEntity<Void> update(@ApiParam(value = "关卡id",required = true) @PathVariable Integer id,@Valid @RequestBody StageDTO stageDTO){
		stageDTO.setId(id);
		stageService.updateStage(stageDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
