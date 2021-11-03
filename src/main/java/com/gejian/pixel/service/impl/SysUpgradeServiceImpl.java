package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.sysupgrade.SysUpgradeDTO;
import com.gejian.pixel.dto.sysupgrade.SysUpgradeQueryDTO;
import com.gejian.pixel.entity.SysUpgrade;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.mapper.SysUpgradeMapper;
import com.gejian.pixel.service.SysUpgradeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class SysUpgradeServiceImpl extends ServiceImpl<SysUpgradeMapper, SysUpgrade> implements SysUpgradeService {

	@Override
	public IPage<SysUpgradeDTO> selectPage(SysUpgradeQueryDTO sysUpgradeQueryDTO) {
		LambdaQueryWrapper<SysUpgrade> queryWrapper	 = new LambdaQueryWrapper<>();
		if(StrUtil.isNotBlank(sysUpgradeQueryDTO.getTitle())){
			queryWrapper.like(SysUpgrade::getTitle,sysUpgradeQueryDTO.getTitle());
		}
		return this.page(sysUpgradeQueryDTO.page(),queryWrapper).convert(record -> BeanUtil.copyProperties(record,SysUpgradeDTO.class));
	}

	@Override
	public boolean updateById(SysUpgrade entity) {
		SysUpgrade sysUpgrade = Optional.ofNullable(this.getById(entity.getId())).orElseThrow(ResourceNotFoundException::new);
		if(sysUpgrade.getStatus()){
			throw new RuntimeException("不能修改已经更新的内容！");
		}
		return super.updateById(entity);
	}
}