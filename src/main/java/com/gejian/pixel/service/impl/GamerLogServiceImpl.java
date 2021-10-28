package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.gamer.GamerLogDTO;
import com.gejian.pixel.dto.gamer.GamerLogQueryDTO;
import com.gejian.pixel.entity.GamerLog;
import com.gejian.pixel.mapper.GamerLogMapper;
import com.gejian.pixel.service.GamerLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class GamerLogServiceImpl extends ServiceImpl<GamerLogMapper, GamerLog> implements GamerLogService {

	@Override
	public IPage<GamerLogDTO> selectPage(GamerLogQueryDTO gamerLogQueryDTO) {
		long count = baseMapper.countOfLog(gamerLogQueryDTO);
		if(count <= 0){
			return new Page<>();
		}
		int start = (gamerLogQueryDTO.getCurrent() - 1) * gamerLogQueryDTO.getSize();
		List<GamerLogDTO> result = baseMapper.pageOfLog(gamerLogQueryDTO, start, gamerLogQueryDTO.getSize());
		return new Page<GamerLogDTO>(gamerLogQueryDTO.getCurrent(),gamerLogQueryDTO.getSize(),count).setRecords(result);
	}
}