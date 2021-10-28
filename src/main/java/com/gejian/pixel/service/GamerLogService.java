package com.gejian.pixel.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.gamer.GamerLogDTO;
import com.gejian.pixel.dto.gamer.GamerLogQueryDTO;
import com.gejian.pixel.entity.GamerLog;

/**
 *  Auto created by codeAppend plugin
 */
public interface GamerLogService extends IService<GamerLog> {

	IPage<GamerLogDTO> selectPage(GamerLogQueryDTO gamerLogQueryDTO);

}