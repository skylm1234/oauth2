package com.gejian.pixel.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.gamer.GamerDTO;
import com.gejian.pixel.dto.gamer.GamerPageQueryDTO;
import com.gejian.pixel.dto.gamer.GamerSealedPatchDTO;
import com.gejian.pixel.entity.Gamer;

/**
 *  Auto created by codeAppend plugin
 */
public interface GamerService extends IService<Gamer> {

	IPage<GamerDTO> selectPage(GamerPageQueryDTO gamerPageQueryDTO);

	void seal(GamerSealedPatchDTO gamerSealedPatchDTO);

	void unSeal(Long id);
}