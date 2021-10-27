package com.gejian.pixel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.activity.type.ActivityGroupTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypeDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypePageDTO;
import com.gejian.pixel.dto.activity.type.ActivityTypeQueryDTO;
import com.gejian.pixel.service.PromotionTypeNameService;
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
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/api/activity-types")
@Api(value = "activity-types", tags = "活动分类管理")
public class ActivityTypeController {


	@Autowired
	private PromotionTypeNameService promotionTypeNameService;

	@GetMapping()
	@ApiOperation("活动分类列表")
	public IPage<ActivityTypePageDTO> list(ActivityTypeQueryDTO activityTypeQueryDTO){
		return promotionTypeNameService.selectPage(activityTypeQueryDTO);
	}

	@PostMapping()
	@ApiOperation("创建活动分类")
	public ResponseEntity<Void> save(@Valid @RequestBody ActivityTypeDTO activityTypeQueryDTO){
		promotionTypeNameService.save(activityTypeQueryDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation("修改活动分类")
	public ResponseEntity<Void> update(@ApiParam(value = "活动分类id",required = true) @PathVariable String id, @Valid @RequestBody ActivityTypeDTO activityTypeQueryDTO){
		activityTypeQueryDTO.setTypeId(id);
		promotionTypeNameService.update(activityTypeQueryDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("删除活动分类")
	public ResponseEntity<Void> delete(@ApiParam(value = "活动分类id",required = true) @PathVariable String id){
		promotionTypeNameService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/groups")
	@ApiOperation("一级分类列表")
	public List<ActivityGroupTypeDTO> groups(){
		return promotionTypeNameService.groups();
	}
}
