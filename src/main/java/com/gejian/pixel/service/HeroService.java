package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Hero;
import com.gejian.pixel.proto.ConstHeroTableItemExProtobuf;

import java.util.Map;

/**
 * Auto created by codeAppend plugin
 */
public interface HeroService extends IService<Hero> {
	Hero getById(Integer id);
	Map<Integer, Hero> getHash();
}