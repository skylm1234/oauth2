package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.activity.ActivityActionDTO;
import com.gejian.pixel.dto.activity.ActivityDetailDTO;
import com.gejian.pixel.dto.activity.ActivityGroupDTO;
import com.gejian.pixel.dto.activity.ActivityPageDTO;
import com.gejian.pixel.dto.activity.ActivityQueryDTO;
import com.gejian.pixel.dto.activity.ActivityRefrenceDTO;
import com.gejian.pixel.entity.Promotion;
import com.gejian.pixel.enums.ActivityKeyEnum;
import com.gejian.pixel.service.PromotionService;
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
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/api/activities")
@Api(value = "activity", tags = "活动管理")
public class ActivityController {

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private PromotionTypeNameService promotionTypeNameService;

	@GetMapping("/types")
	@ApiOperation("活动分类")
	public List<ActivityGroupDTO> groups(){
		return promotionTypeNameService.listToGroup();
	}

	@GetMapping()
	@ApiOperation("活动列表")
	public IPage<ActivityPageDTO> list(ActivityQueryDTO activityQueryDTO){
		return promotionService.selectPage(activityQueryDTO);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("删除活动")
	public ResponseEntity<Void> delete(@ApiParam(value = "活动id",required = true) @PathVariable Integer id){
		promotionService.removeById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/types/{typeId}/behaviours")
	@ApiOperation("活动行为列表")
	public List<ActivityActionDTO> behaviourList(@ApiParam(value = "分类id",required = true) @PathVariable("typeId") Integer typeId){
		List<ActivityKeyEnum> byGroupType = ActivityKeyEnum.findByGroupType(typeId);
		return byGroupType.stream().map(activityKeyEnum -> {
			ActivityActionDTO activityActionDTO = new ActivityActionDTO();
			activityActionDTO.setKey(activityKeyEnum.getKey());
			activityActionDTO.setName(activityKeyEnum.getName());
			activityActionDTO.setCondition(activityKeyEnum.getCondition());
			return activityActionDTO;
		}).collect(Collectors.toList());
	}

	@GetMapping("/types/{typeId}/behaviours/{key}")
	@ApiOperation(value = "行为关联明细",notes = "上一关，下一关列表都从这里获取")
	public List<ActivityRefrenceDTO> behaviour(@ApiParam(value = "分类id",required = true) @PathVariable("typeId") Integer typeId,
											   @ApiParam(value = "行为key",required = true) @PathVariable("key") String key){
		List<Promotion> list = promotionService.lambdaQuery().eq(Promotion::getType,typeId)
				.eq(Promotion::getKey, key).orderByAsc(Promotion::getId).list();
		return list.stream().map(promotion -> {
			ActivityRefrenceDTO activityRefrenceDTO = new ActivityRefrenceDTO();
			activityRefrenceDTO.setId(promotion.getId());
			activityRefrenceDTO.setTitle(promotion.getTitle());
			return activityRefrenceDTO;
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@ApiOperation("活动明细")
	public ActivityDetailDTO id(@ApiParam(value = "活动id",required = true) @PathVariable Integer id){
		return promotionService.selectById(id);
	}

	@PostMapping
	@ApiOperation("新增活动")
	public ResponseEntity<Void> create(@Valid @RequestBody ActivityDetailDTO activityDetailDTO){
		Promotion promotion	 = BeanUtil.copyProperties(activityDetailDTO,Promotion.class);
		promotion.setDesc(activityDetailDTO.getCondition());
		promotion.setPercent("n/m");
		ActivityKeyEnum activityKeyEnum;
		try{
			activityKeyEnum = ActivityKeyEnum.valueOf(activityDetailDTO.getKey().toUpperCase());
		}catch (Exception e){
			throw new RuntimeException("行为key值校验失败");
		}
		promotion.setCompareType(activityKeyEnum.getCompareType());
		if(promotion.getNext() == null){
			promotion.setNext(0);
		}
		if(promotion.getPrecondition() == null){
			promotion.setPrecondition(0);
		}
		promotionService.save(promotion);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	@ApiOperation("修改活动")
	public ResponseEntity<Void> update(@ApiParam(value = "活动id",required = true) @PathVariable Integer id,@Valid @RequestBody ActivityDetailDTO activityDetailDTO){
		activityDetailDTO.setId(id);
		Promotion promotion	 = BeanUtil.copyProperties(activityDetailDTO,Promotion.class);
		promotion.setDesc(activityDetailDTO.getCondition());
		promotion.setPercent("n/m");
		ActivityKeyEnum activityKeyEnum;
		try{
			activityKeyEnum = ActivityKeyEnum.valueOf(activityDetailDTO.getKey().toUpperCase());
		}catch (Exception e){
			throw new RuntimeException("行为key值校验失败");
		}
		promotion.setCompareType(activityKeyEnum.getCompareType());
		if(promotion.getNext() == null){
			promotion.setNext(0);
		}
		if(promotion.getPrecondition() == null){
			promotion.setPrecondition(0);
		}
		promotionService.updateById(promotion);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
