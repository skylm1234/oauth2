package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.dto.gamer.GamerLogDTO;
import com.gejian.pixel.dto.gamer.GamerLogQueryDTO;
import com.gejian.pixel.entity.GamerLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Auto created by codeAppend plugin
 */
public interface GamerLogMapper extends BaseMapper<GamerLog> {

	List<GamerLogDTO> pageOfLog(@Param("q")GamerLogQueryDTO q,@Param("start")int start,@Param("size")int size);

	long countOfLog(@Param("q")GamerLogQueryDTO q);
}