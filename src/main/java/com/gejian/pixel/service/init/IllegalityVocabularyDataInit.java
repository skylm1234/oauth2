package com.gejian.pixel.service.init;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gejian.pixel.mapper.IllegalityVocabularyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Hyb
 * @date : 2021/9/1 13:48
 * @description:
 */
@Component
public class IllegalityVocabularyDataInit {

	@Autowired
	private IllegalityVocabularyMapper illegalityVocabularyMapper;

	private List<String> iVList = new ArrayList<>();

	@PostConstruct
	public void init() {
		iVList = illegalityVocabularyMapper.selectList(Wrappers.emptyWrapper()).stream()
				.map(illegalityVocabulary -> {
					return illegalityVocabulary.getName();
				}).collect(Collectors.toList());
	}

	public boolean isLegal(String data) {
		return iVList.contains(data);
	}
}
