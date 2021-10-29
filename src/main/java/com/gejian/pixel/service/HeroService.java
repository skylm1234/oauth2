package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.HeroDTO;
import com.gejian.pixel.dto.HeroListRequestDTO;
import com.gejian.pixel.dto.HeroListResponseDTO;
import com.gejian.pixel.dto.store.HeroTreeDTO;
import com.gejian.pixel.entity.Hero;
import com.gejian.pixel.proto.ConstHeroTableItemExProtobuf;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Auto created by codeAppend plugin
 */
public interface HeroService extends IService<Hero> {
	Hero getById(Integer id);
	Map<Integer, Hero> getHash();

    ConstHeroTableItemExProtobuf.ConstHeroTableItemEx getItem(Integer id);

	IPage<HeroListResponseDTO> selectPage(HeroListRequestDTO heroListRequestDTO);

	Optional<HeroDTO> selectById(Integer id);

	void update(HeroDTO heroDTO);

	List<HeroTreeDTO> groups();
}