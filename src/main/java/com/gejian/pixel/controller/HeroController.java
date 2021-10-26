package com.gejian.pixel.controller;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.HeroDTO;
import com.gejian.pixel.dto.HeroListRequestDTO;
import com.gejian.pixel.dto.HeroListResponseDTO;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.service.HeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 17:44
 * @description：
 */

@RestController
@RequestMapping("/api/heros")
@Api(value = "人物管理",tags = "人物管理")
public class HeroController {


	@Autowired
	private HeroService heroService;

	@GetMapping()
	@ApiOperation(value = "人物列表")
	public IPage<HeroListResponseDTO> list(HeroListRequestDTO heroListRequestDTO){
		return heroService.selectPage(heroListRequestDTO);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "人物详情")
	public HeroDTO id(@ApiParam(name = "id", value = "人物id", required = true) @PathVariable("id")Integer id){
		return heroService.selectById(id).orElseThrow(ResourceNotFoundException::new);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "修改人物",code = HttpStatus.HTTP_NO_CONTENT)
	public ResponseEntity<Void> update(@ApiParam(name = "id", value = "人物id", required = true) @PathVariable("id")Integer id,
								 @Valid @RequestBody HeroDTO heroDTO){
		heroDTO.setId(id);
		heroService.update(heroDTO);
		return ResponseEntity.noContent().build();
	}
}
