package com.gejian.pixel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.proto.BackpackProtobuf;

import java.util.List;

/**
 *  Auto created by codeAppend plugin
 */
public interface BackpackService extends IService<Backpack> {

	Backpack getByLevel(Integer level);
}