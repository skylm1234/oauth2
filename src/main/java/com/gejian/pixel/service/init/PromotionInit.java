package com.gejian.pixel.service.init;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gejian.pixel.entity.Promotion;
import com.gejian.pixel.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Component
public class PromotionInit {

	@Autowired
	private PromotionService promotionService;

	private Map<Integer, Promotion> hash;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@PostConstruct
	public void init(){
		List<Promotion> list = promotionService.list(Wrappers.emptyWrapper());
		if (CollectionUtils.isEmpty(list)){
			return;
		}
		hash =  list.stream()
				.collect(Collectors.toMap(Promotion::getId, Function.identity()));
	}

	public Promotion getById(Integer id){
		return hash.get(id);
	}

}
