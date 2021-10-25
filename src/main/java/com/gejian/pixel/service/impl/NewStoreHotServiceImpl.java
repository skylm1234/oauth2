package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.NewStoreHot;
import com.gejian.pixel.entity.ext.StorePageDO;
import com.gejian.pixel.mapper.NewStoreHotMapper;
import com.gejian.pixel.service.NewStoreHotService;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class NewStoreHotServiceImpl extends ServiceImpl<NewStoreHotMapper, NewStoreHot> implements NewStoreHotService {
	@Override
	public Boolean saveByStore(StorePageDO storePageDO) {
		NewStoreHot newStoreHot = BeanUtil.copyProperties(storePageDO, NewStoreHot.class);
		return this.save(newStoreHot);
	}

	@Override
	public Boolean deleteByStore(String id) {
		return this.removeById(id);
	}

	@Override
	public Boolean updateByStore(StorePageDO storePageDO) {
		NewStoreHot newStoreHot = BeanUtil.copyProperties(storePageDO, NewStoreHot.class);
		return this.updateById(newStoreHot);
	}

	@Override
	public int maxPlaces() {
		return baseMapper.maxPlaces();
	}

	// use "baseMapper" to call jdbc
    // example: baseMapper.insert(entity);
    // example: baseMapper.selectByPage(params);
   
}