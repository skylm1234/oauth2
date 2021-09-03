package com.gejian.pixel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.entity.Drop;

/**
 *  Auto created by codeAppend plugin
 */
public interface BackpackService extends IService<Backpack> {

	Backpack getByLevel(String level);
}