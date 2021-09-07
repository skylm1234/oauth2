package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.Hero;

import java.util.Map;

/**
 *  Auto created by codeAppend plugin
 */
public interface HeroService extends IService<Hero> {

	Map<Integer, Hero> getHash();
}