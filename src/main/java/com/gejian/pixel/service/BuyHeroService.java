package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.BuyHero;

import javax.script.ScriptException;

/**
 *  Auto created by codeAppend plugin
 */
public interface BuyHeroService extends IService<BuyHero> {

	double calculation(Integer id, Integer count) throws ScriptException;
}