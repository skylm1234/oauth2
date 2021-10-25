package com.gejian.pixel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.store.StoreDTO;
import com.gejian.pixel.dto.store.StoreGoodsDTO;
import com.gejian.pixel.dto.store.StorePageDTO;
import com.gejian.pixel.dto.store.StoreQueryDTO;
import com.gejian.pixel.dto.store.StoreRefreshDTO;
import com.gejian.pixel.dto.store.StoreTypeDTO;
import com.gejian.pixel.enums.StoreTypeEnum;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.service.NewStoreGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.compress.utils.Lists;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : yuanxue
 * @date : 2021/10/18
 */
@RestController
@RequestMapping("/api/stores")
@Api(value = "api-store",tags = "商品管理")
public class StoreController {

	@Autowired
	private NewStoreGoodsService newStoreGoodsService;

	@ApiOperation("商品分类")
	@GetMapping("/type")
	public List<StoreTypeDTO> getType(){
		return newStoreGoodsService.getType();
	}
	
	@ApiOperation("分类明细")
	@GetMapping("/type/details")
	public List<StoreGoodsDTO> getListByType(@ApiParam(value = "分类id",required = true) @RequestParam("type") int type){
		StoreTypeEnum storeType;
		try{
			storeType = StoreTypeEnum.valueOf(type);
			return this.newStoreGoodsService.getListByType(storeType);
		}catch (Exception e){
			return Lists.newArrayList();
		}
	}
	@ApiOperation("商品列表")
	@GetMapping()
	public IPage<StorePageDTO> getPage( @RequestBody(required = false) StoreQueryDTO storeQueryDTO){
		if(storeQueryDTO == null){
			storeQueryDTO = new StoreQueryDTO();
		}
		return this.newStoreGoodsService.getPage(storeQueryDTO);
	}

	@ApiOperation("添加商品")
	@PostMapping("")
	public ResponseEntity<Void> save(@Valid @RequestBody StoreDTO storeDTO){
		 this.newStoreGoodsService.saveByStore(storeDTO);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation("商品详情")
	@GetMapping("/{id}")
	public StoreDTO id(@ApiParam(value = "商品id",required = true) @PathVariable("id") String id){
		return this.newStoreGoodsService.selectById(id).orElseThrow(ResourceNotFoundException::new);
	}

	@ApiOperation("修改商品")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@ApiParam(value = "商品id",required = true) @PathVariable("id") String id,@Valid @RequestBody StoreDTO storeDTO){
		storeDTO.setId(id);
		newStoreGoodsService.updateByStore(storeDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation("删除商品")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@ApiParam(value = "商品id",required = true) @PathVariable("id") String id){
		this.newStoreGoodsService.deleteByStore(id);
		return ResponseEntity.noContent().build();
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
