package com.gejian.pixel.controller;

import com.gejian.pixel.dto.SkillDTO;
import com.gejian.pixel.service.SkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 17:44
 * @description：
 */

@RestController
@RequestMapping("/api/skills")
@Api(value = "技能管理",tags = "技能管理")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@GetMapping()
	@ApiOperation(value = "全部技能")
	public List<SkillDTO> list(){
		return skillService.list().stream().map(skill -> SkillDTO.builder().id(skill.getId()).name(skill.getName()).build()).collect(Collectors.toList());
	}
}
