package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.sysupgrade.SysUpgradeDTO;
import com.gejian.pixel.dto.sysupgrade.SysUpgradeQueryDTO;
import com.gejian.pixel.entity.SysUpgrade;
import com.gejian.pixel.service.SysUpgradeService;
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

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/api/sys-upgrades")
@Api(value = "sys-upgrade", tags = "更新管理")
public class SysUpgradeController {

	@Autowired
	private SysUpgradeService sysUpgradeService;

	@ApiOperation("更新列表")
	@GetMapping
	public IPage<SysUpgradeDTO> list(SysUpgradeQueryDTO sysUpgradeQueryDTO){
		return sysUpgradeService.selectPage(sysUpgradeQueryDTO);
	}

	@ApiOperation("删除更新")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@ApiParam(value = "id",required = true) @PathVariable Long id){
		sysUpgradeService.removeById(id);
		return ResponseEntity.ok().build();
	}

	@ApiOperation("创建更新")
	@PostMapping()
	public ResponseEntity<Void> create(@RequestBody @Valid SysUpgradeDTO sysUpgradeDTO){
		SysUpgrade sysUpgrade = BeanUtil.copyProperties(sysUpgradeDTO, SysUpgrade.class);
		sysUpgradeService.save(sysUpgrade);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation("编辑更新")
	@PutMapping("/{id}")
	public ResponseEntity<Void> create(@ApiParam(value = "id",required = true) @PathVariable Long id,@RequestBody @Valid SysUpgradeDTO sysUpgradeDTO){
		sysUpgradeDTO.setId(id);
		SysUpgrade sysUpgrade = BeanUtil.copyProperties(sysUpgradeDTO, SysUpgrade.class);
		sysUpgradeService.updateById(sysUpgrade);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
