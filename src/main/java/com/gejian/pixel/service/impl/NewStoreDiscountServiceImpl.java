package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.NewStoreDiscount;
import com.gejian.pixel.entity.ext.StorePageDO;
import com.gejian.pixel.mapper.NewStoreDiscountMapper;
import com.gejian.pixel.service.NewStoreDiscountService;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class NewStoreDiscountServiceImpl extends ServiceImpl<NewStoreDiscountMapper, NewStoreDiscount> implements NewStoreDiscountService {
	@Override
	public Boolean saveByStore(StorePageDO storePageDO) {
		NewStoreDiscount newStoreDiscount = BeanUtil.copyProperties(storePageDO, NewStoreDiscount.class);
		return this.save(newStoreDiscount);
	}

	@Override
	public Boolean deleteByStore(String id) {
		return this.removeById(id);
	}

	@Override
	public Boolean updateByStore(StorePageDO storePageDO) {
		NewStoreDiscount newStoreDiscount = BeanUtil.copyProperties(storePageDO, NewStoreDiscount.class);
		return this.updateById(newStoreDiscount);
	}

	// use "baseMapper" to call jdbc
    // example: baseMapper.insert(entity);
    // example: baseMapper.selectByPage(params);
   
}