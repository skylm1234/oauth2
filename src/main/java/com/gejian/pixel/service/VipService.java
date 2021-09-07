package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Vip;
import com.gejian.pixel.proto.ConstVipTableItemExProtobuf;

/**
 *  Auto created by codeAppend plugin
 */
public interface VipService extends IService<Vip> {

	Vip getById(Integer id);

	ConstVipTableItemExProtobuf.ConstVipTableItemEx getItem(Integer id);

}