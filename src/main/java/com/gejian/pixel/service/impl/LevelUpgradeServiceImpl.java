package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.LevelUpgrade;
import com.gejian.pixel.mapper.LevelUpgradeMapper;
import com.gejian.pixel.service.LevelUpgradeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class LevelUpgradeServiceImpl extends ServiceImpl<LevelUpgradeMapper, LevelUpgrade> implements LevelUpgradeService {

	private Map<Integer,LevelUpgrade> hash;

	@PostConstruct
    public void init(){
		List<LevelUpgrade> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			hash = list.stream()
					.collect(Collectors.toMap(LevelUpgrade::getId, Function.identity()));
		}
	}

	@Override
	public LevelUpgrade get(Integer id){
		return hash.get(id);
	}
   
}