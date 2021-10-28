package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchaseDTO;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchasePageDTO;
import com.gejian.pixel.dto.InGamePurchase.InGamePurchaseUpdateDTO;
import com.gejian.pixel.dto.InGamePurchase.OrderDTO;
import com.gejian.pixel.dto.InGamePurchase.OrderPageDTO;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.service.InGamePurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@RestController
@RequestMapping("/api/purchases")
@Api(value = "purchases",tags = "充值管理")
public class InGamePurchaseController {

	@Autowired
	private InGamePurchaseService inGamePurchaseService;

	@ApiOperation("充值列表")
	@GetMapping()
	public IPage<InGamePurchaseDTO> getPage( @RequestBody(required = false) InGamePurchasePageDTO inGamePurchasePageDTO){
		if(inGamePurchasePageDTO == null){
			inGamePurchasePageDTO = new InGamePurchasePageDTO();
		}
		return this.inGamePurchaseService.getPage(inGamePurchasePageDTO);
	}

	@ApiOperation("修改充值")
	@PutMapping("/{id}")
	public Boolean update(@ApiParam(value = "充值id",required = true) @PathVariable String id, @Valid @RequestBody InGamePurchaseUpdateDTO inGamePurchaseUpdateDTO){
		inGamePurchaseUpdateDTO.setId(id);
		InGamePurchase inGamePurchase = BeanUtil.copyProperties(inGamePurchaseUpdateDTO, InGamePurchase.class);
		return this.inGamePurchaseService.updateById(inGamePurchase);
	}

	@ApiOperation("充值详情")
	@GetMapping("/{id}")
	public InGamePurchaseDTO id(@ApiParam(value = "充值id",required = true) @PathVariable String id){
		InGamePurchase gamePurchase = inGamePurchaseService.selectById(id).orElseThrow(ResourceNotFoundException::new);
		return BeanUtil.copyProperties(gamePurchase,InGamePurchaseDTO.class);
	}

	@ApiOperation("充值记录")
	@GetMapping("/orders")
	public IPage<OrderDTO> getPageOrder(OrderPageDTO orderPageDTO){
		return this.inGamePurchaseService.getPageOrder(orderPageDTO);
	}

}
