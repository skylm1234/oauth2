package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.proto.PlayerInfoProtobuf;

/**
 *  Auto created by codeAppend plugin
 */
public interface DropService extends IService<Drop> {

	PlayerInfoProtobuf.PlayerInfo dropItem(String key, Integer identifier, Boolean store2backpack, String parameter);
}