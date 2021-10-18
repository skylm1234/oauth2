package com.gejian.pixel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.HeroListRequestDTO;
import com.gejian.pixel.dto.HeroListResponseDTO;
import com.gejian.pixel.service.HeroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	@ApiOperation("人物列表")
	public IPage<HeroListResponseDTO> list(@RequestBody(required = false) HeroListRequestDTO heroListRequestDTO){
		return heroService.selectPage(heroListRequestDTO);
	}
}
