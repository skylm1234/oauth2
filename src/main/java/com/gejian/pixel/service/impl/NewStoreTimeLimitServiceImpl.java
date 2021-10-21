package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.NewStoreTimeLimit;
import com.gejian.pixel.entity.ext.StorePageDO;
import com.gejian.pixel.mapper.NewStoreTimeLimitMapper;
import com.gejian.pixel.service.NewStoreTimeLimitService;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class NewStoreTimeLimitServiceImpl extends ServiceImpl<NewStoreTimeLimitMapper, NewStoreTimeLimit> implements NewStoreTimeLimitService {
	@Override
	public Boolean saveByStore(StorePageDO storePageDO) {
		NewStoreTimeLimit newStoreTimeLimit = BeanUtil.copyProperties(storePageDO, NewStoreTimeLimit.class);
		return this.save(newStoreTimeLimit);
	}

	@Override
	public Boolean deleteByStore(String id) {
		return this.removeById(id);
	}

	@Override
	public Boolean updateByStore(StorePageDO storePageDO) {
		NewStoreTimeLimit newStoreTimeLimit = BeanUtil.copyProperties(storePageDO, NewStoreTimeLimit.class);
		return this.updateById(newStoreTimeLimit);
	}

	// use "baseMapper" to call jdbc
    // example: baseMapper.insert(entity);
    // example: baseMapper.selectByPage(params);
   
}