package com.gejian.pixel.entity.ext;

import com.gejian.pixel.dto.BasePageQuery;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理
 * @createTime 2021-10-19 15:22:00
 */
@Data
public class StoreQueryDO extends BasePageQuery {
	private String shop;
	private List<String> skill;
	private List<String> soul;
	private List<String> exp;
	private Long count;

	@Override
	public Integer getSize() {
		return super.getSize() * (super.getCurrent() - 1);
	}

	@Override
	public Integer getCurrent() {
		return super.getSize() * super.getCurrent();
	}
}
