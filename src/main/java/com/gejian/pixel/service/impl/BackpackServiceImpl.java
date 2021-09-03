package com.gejian.pixel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.entity.Backpack;
import com.gejian.pixel.mapper.BackpackMapper;
import com.gejian.pixel.service.BackpackService;
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
public class BackpackServiceImpl extends ServiceImpl<BackpackMapper, Backpack> implements BackpackService {

	private Map<String, Backpack> hash;

	@PostConstruct
	public void init(){
		List<Backpack> list = this.list();
		if (!CollectionUtils.isEmpty(list)){
			hash = list.stream().collect(Collectors.toMap(Backpack::getLevel, Function.identity()));
		}
	}

	@Override
	public Backpack getByLevel(String level){
		return hash.get(level);
	}
}