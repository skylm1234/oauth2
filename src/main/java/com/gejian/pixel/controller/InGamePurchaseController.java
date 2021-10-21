package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.InGamePurchase.*;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.service.InGamePurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@RestController
@RequestMapping("/api/in-game-purchase")
@Api(value = "api-in-game-purchase",tags = "充值管理api")
public class InGamePurchaseController {

	@Autowired
	private InGamePurchaseService inGamePurchaseService;

	@ApiOperation("充值管理列表")
	@PostMapping("/page")
	public IPage<InGamePurchaseDTO> getPage(@Valid @RequestBody InGamePurchasePageDTO inGamePurchasePageDTO){
		return this.inGamePurchaseService.getPage(inGamePurchasePageDTO);
	}

	@ApiOperation("充值管理编辑")
	@PostMapping("/update")
	public Boolean update(@Valid @RequestBody InGamePurchaseUpdateDTO inGamePurchaseUpdateDTO){
		InGamePurchase inGamePurchase = BeanUtil.copyProperties(inGamePurchaseUpdateDTO, InGamePurchase.class);
		return this.inGamePurchaseService.updateById(inGamePurchase);
	}

	@ApiOperation("充值记录查询")
	@PostMapping("/pageOrder")
	public IPage<OrderDTO> getPageOrder(@Valid @RequestBody OrderPageDTO orderPageDTO){
		return this.inGamePurchaseService.getPageOrder(orderPageDTO);
	}

}
