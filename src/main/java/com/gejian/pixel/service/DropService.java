package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.DropDTO;
import com.gejian.pixel.dto.DropQueryDTO;
import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.proto.PlayerInfoProtobuf;

import java.util.Optional;

/**
 *  Auto created by codeAppend plugin
 */
public interface DropService extends IService<Drop> {

	PlayerInfoProtobuf.PlayerInfo dropItem(String key, Integer identifier, Boolean store2backpack, String parameter);

	IPage<DropDTO> selectPage(DropQueryDTO dropQueryDTO);

	Optional<Drop> selectById(String id);
}