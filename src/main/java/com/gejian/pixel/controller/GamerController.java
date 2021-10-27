package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.gamer.GamerDTO;
import com.gejian.pixel.dto.gamer.GamerPageQueryDTO;
import com.gejian.pixel.dto.gamer.GamerSealedPatchDTO;
import com.gejian.pixel.entity.Gamer;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.service.GamerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/api/gamers")
@Api(value = "gamers", tags = "玩家管理")
public class GamerController {

	@Autowired
	private GamerService gamerService;

	@GetMapping()
	@ApiOperation("玩家列表")
	public IPage<GamerDTO> list(GamerPageQueryDTO gamerPageQueryDTO){
		return gamerService.selectPage(gamerPageQueryDTO);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("删除玩家")
	public ResponseEntity<Void> delete(@ApiParam(value = "玩家id",required = true) @PathVariable Long id){
		gamerService.removeById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	@ApiOperation("玩家明细")
	public GamerDTO id(@ApiParam(value = "活动id",required = true) @PathVariable Long id){
		return Optional.ofNullable(gamerService.getById(id)).map(gamer -> BeanUtil.copyProperties(gamer,GamerDTO.class)).orElseThrow(ResourceNotFoundException::new);
	}

	@PostMapping
	@ApiOperation("新增玩家")
	public ResponseEntity<Void> create(@Valid @RequestBody GamerDTO gamerDTO){
		Gamer gamer = BeanUtil.copyProperties(gamerDTO, Gamer.class);
		gamerDTO.setVip(gamerDTO.getVipLevel() != null);
		gamerService.save(gamer);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	@ApiOperation("修改玩家")
	public ResponseEntity<Void> update(@ApiParam(value = "玩家id",required = true) @PathVariable Long id,@Valid @RequestBody GamerDTO gamerDTO){
		Gamer gamer = BeanUtil.copyProperties(gamerDTO, Gamer.class);
		gamer.setId(id);
		gamer.setVip(gamerDTO.getVipLevel() != null);
		gamerService.updateById(gamer);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PatchMapping("/{id}/sealed")
	@ApiOperation("封禁玩家")
	public ResponseEntity<Void> state(@ApiParam(value = "玩家id",required = true) @PathVariable Long id, @Valid @RequestBody GamerSealedPatchDTO gamerSealedPatchDTO){
		gamerSealedPatchDTO.setId(id);
		gamerService.seal(gamerSealedPatchDTO);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
