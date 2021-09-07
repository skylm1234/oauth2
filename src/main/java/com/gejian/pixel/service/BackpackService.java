package com.gejian.pixel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.entity.Drop;
import com.gejian.pixel.proto.BackpackProtobuf;
import com.gejian.pixel.proto.ConstBackpackTableItemExProtobuf;

import java.util.List;

/**
 *  Auto created by codeAppend plugin
 */
public interface BackpackService extends IService<Backpack> {

	Backpack getByLevel(Integer level);

	ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx getItem(Integer level);

}