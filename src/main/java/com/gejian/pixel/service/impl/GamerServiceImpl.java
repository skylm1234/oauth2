package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.gamer.GamerDTO;
import com.gejian.pixel.dto.gamer.GamerPageQueryDTO;
import com.gejian.pixel.dto.gamer.GamerSealedPatchDTO;
import com.gejian.pixel.entity.Gamer;
import com.gejian.pixel.entity.GamerSealed;
import com.gejian.pixel.mapper.GamerMapper;
import com.gejian.pixel.mapper.GamerSealedMapper;
import com.gejian.pixel.service.GamerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class GamerServiceImpl extends ServiceImpl<GamerMapper, Gamer> implements GamerService {

	@Autowired
	private GamerSealedMapper gamerSealedMapper;

	@Override
	public IPage<GamerDTO> selectPage(GamerPageQueryDTO gamerPageQueryDTO) {
		LambdaQueryWrapper<Gamer> wrapper = new LambdaQueryWrapper<>();
		if(StrUtil.isNotBlank(gamerPageQueryDTO.getMobile())){
			wrapper.like(Gamer::getMobile,gamerPageQueryDTO.getMobile());
		}
		if(StrUtil.isNotBlank(gamerPageQueryDTO.getUsername())){
			wrapper.like(Gamer::getUsername,gamerPageQueryDTO.getUsername());
		}
		return this.page(gamerPageQueryDTO.page(),wrapper).convert(gamer -> BeanUtil.copyProperties(gamer, GamerDTO.class));
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void seal(GamerSealedPatchDTO gamerSealedPatchDTO){
		Gamer gamer	 = new Gamer();
		gamer.setId(gamerSealedPatchDTO.getId());
		gamer.setState(false);
		baseMapper.updateById(gamer);
		GamerSealed gamerSealed = new GamerSealed();
		gamerSealed.setGamerId(gamerSealedPatchDTO.getId());
		gamerSealed.setDays(gamerSealedPatchDTO.getDays());
		gamerSealed.setReason(gamerSealedPatchDTO.getReason());
		LocalDateTime now = LocalDateTime.now();
		gamerSealed.setStartTime(now);
		gamerSealed.setTerminateTime(now.plusDays(gamerSealedPatchDTO.getDays()));
		baseMapper.updateById(gamer);
		gamerSealedMapper.insert(gamerSealed);
	}
}