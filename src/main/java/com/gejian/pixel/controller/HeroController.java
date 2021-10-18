package com.gejian.pixel.controller;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 17:44
 * @description：
 */

@RestController
@RequestMapping("/api/heros")
@Api(value = "人物管理",tags = "人物管理API")
public class HeroController {


	@Autowired
	private HeroService heroService;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "人物列表",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public IPage<HeroListResponseDTO> list(@RequestBody(required = false) HeroListRequestDTO heroListRequestDTO){
		return heroService.selectPage(heroListRequestDTO);
	}

	@GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "人物详情",produces = MediaType.APPLICATION_JSON_VALUE)
	public HeroDTO id(@ApiParam(name = "id", value = "人物id", required = true) @PathVariable("id")Integer id){
		return heroService.selectById(id).orElseThrow(ResourceNotFoundException::new);
	}
}
