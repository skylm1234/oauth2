package com.gejian.pixel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.entity.PvpAward;

import java.util.Map;

/**
 *  Auto created by codeAppend plugin
 */
public interface PvpAwardService extends IService<PvpAward> {

	Map<Integer, PvpAward> getHash();
}