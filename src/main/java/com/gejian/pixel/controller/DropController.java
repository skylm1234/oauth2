package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.constants.DropConstant;
import com.gejian.pixel.dto.DropDTO;
import com.gejian.pixel.dto.DropQueryDTO;
import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.service.DropService;
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
 * @date 2017/10/31
 */
@RestController
@RequestMapping("/api/drops")
@Api(value = "drop", tags = "掉落管理")
public class DropController {

	@Autowired
	private DropService dropService;

	/**
	 * 掉落列表
	 * @param dropQueryDTO
	 * @return
	 */
	@GetMapping("")
	@ApiOperation("掉落列表")
	public IPage<DropDTO> list(DropQueryDTO dropQueryDTO) {
		return dropService.selectPage(dropQueryDTO);
	}


	/**
	 * 通过ID查询掉落的详细信息
	 * @return 掉落详细信息
	 */
	@GetMapping("/{id}")
	@ApiOperation("掉落明细")
	public DropDTO getById(@ApiParam(name = "id", value = "掉落id", required = true) @PathVariable String id) {
		Drop drop = dropService.selectById(id).orElseThrow(ResourceNotFoundException::new);
		return BeanUtil.copyProperties(drop, DropDTO.class);
	}

	/**
	 * 新增掉落
	 * @param dropDTO 掉落信息
	 * @return
	 */
	@PostMapping("")
	@ApiOperation("新增掉落")
	public ResponseEntity<Void> save(@Valid @RequestBody DropDTO dropDTO) {
		dropService.save( BeanUtil.copyProperties(dropDTO,Drop.class));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 删除掉落
	 * @param id 掉落id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation("删除掉落")
	public ResponseEntity<Void> removeById(@ApiParam(name = "id", value = "掉落id", required = true) @PathVariable String id) {
		dropService.removeById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 更新掉落
	 * @param dropDTO
	 * @return
	 */
	@PutMapping("/{id}")
	@ApiOperation("更新掉落")
	public ResponseEntity<Void> update(@ApiParam(name = "id", value = "掉落id", required = true) @PathVariable String id, @Valid @RequestBody DropDTO dropDTO) {
		dropDTO.setDropId(id);
		dropService.updateById(BeanUtil.copyProperties(dropDTO,Drop.class));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping("/activities")
	@ApiOperation("活动掉落")
	public List<DropDTO> activities() {
		return dropService.lambdaQuery().likeRight(Drop::getId, DropConstant.DROP_ACTIVITY).list()
				.stream().map(drop ->{
					DropDTO dropDTO = BeanUtil.copyProperties(drop, DropDTO.class);
					dropDTO.setDropId(drop.getId());
					return dropDTO;}).collect(Collectors.toList());
	}

	@GetMapping("/boss")
	@ApiOperation("Boss收益")
	public List<DropDTO> boss() {
		return dropService.lambdaQuery().likeRight(Drop::getId, DropConstant.DROP_PVE_BOSS).list()
				.stream().map(drop ->{
					DropDTO dropDTO = BeanUtil.copyProperties(drop, DropDTO.class);
					dropDTO.setDropId(drop.getId());
					return dropDTO;}).collect(Collectors.toList());
	}

	@GetMapping("/pvemon")
	@ApiOperation("小怪收益")
	public List<DropDTO> pvemon() {
		return dropService.lambdaQuery().likeRight(Drop::getId, DropConstant.DROP_PVE_MON).list()
				.stream().map(drop ->{
					DropDTO dropDTO = BeanUtil.copyProperties(drop, DropDTO.class);
					dropDTO.setDropId(drop.getId());
					return dropDTO;}).collect(Collectors.toList());
	}

	@GetMapping("/pvebk")
	@ApiOperation("怪物击杀收益")
	public List<DropDTO> pvebk() {
		return dropService.lambdaQuery().likeRight(Drop::getId, DropConstant.DROP_PVE_BK).list()
				.stream().map(drop ->{
					DropDTO dropDTO = BeanUtil.copyProperties(drop, DropDTO.class);
					dropDTO.setDropId(drop.getId());
					return dropDTO;}).collect(Collectors.toList());
	}

	@GetMapping("/goblins")
	@ApiOperation("goblins掉落")
	public List<DropDTO> goblins() {
		return dropService.lambdaQuery().likeRight(Drop::getId, DropConstant.DROP_GOBLINS).list()
				.stream().map(drop ->{
					DropDTO dropDTO = BeanUtil.copyProperties(drop, DropDTO.class);
					dropDTO.setDropId(drop.getId());
					return dropDTO;}).collect(Collectors.toList());
	}
}
