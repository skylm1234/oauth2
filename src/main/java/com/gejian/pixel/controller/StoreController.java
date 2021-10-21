package com.gejian.pixel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.store.*;
import com.gejian.pixel.service.NewStoreGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@RestController
@RequestMapping("/api/store")
@Api(value = "api-store",tags = "商品管理api")
public class StoreController {

	@Autowired
	private NewStoreGoodsService newStoreGoodsService;

	@ApiOperation("查询商品类型")
	@PostMapping("/type")
	public List<StoreTypeDTO> getType(){
		return this.newStoreGoodsService.getType();
	}
	
	@ApiOperation("查询具体商品")
	@PostMapping("/typeList")
	public List<StoreGoodsDTO> getListByType(@Valid @RequestBody StoreTypeDTO storeTypeDTO){
		return this.newStoreGoodsService.getListByType(storeTypeDTO);
	}

	@ApiOperation("商品管理列表")
	@PostMapping("/page")
	public IPage<StorePageDTO> getPage(@Valid @RequestBody StoreQueryDTO storeQueryDTO){
		return this.newStoreGoodsService.getPage(storeQueryDTO);
	}

	@ApiOperation("新增商品管理")
	@PostMapping("/save")
	public Boolean save(@Valid @RequestBody StoreDTO storeDTO){
		return this.newStoreGoodsService.saveByStore(storeDTO);
	}

	@ApiOperation("商品管理编辑")
	@PostMapping("/update")
	public Boolean update(@Valid @RequestBody StoreDTO storeDTO){
		return this.newStoreGoodsService.updateByStore(storeDTO);
	}

	@ApiOperation("商品管理删除")
	@PostMapping("/delete")
	public Boolean delete(@Valid @RequestBody StoreDTO storeDTO){
		return this.newStoreGoodsService.deleteByStore(storeDTO);
	}

	@ApiOperation("商品刷新时间下拉")
	@PostMapping("/refresh")
	public List<StoreRefreshDTO> getListRefresh(){
		return this.newStoreGoodsService.getListRefresh();
	}

	@ApiOperation("修改商店刷新")
	@PostMapping("/updateRefresh")
	public Boolean updateRefresh(@Valid @RequestBody StoreRefreshDTO storeRefreshDTO){
		return this.newStoreGoodsService.updateRefresh(storeRefreshDTO);
	}

}
