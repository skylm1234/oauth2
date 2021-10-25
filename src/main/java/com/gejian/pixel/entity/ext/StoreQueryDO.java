package com.gejian.pixel.entity.ext;

import com.gejian.pixel.dto.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理
 * @createTime 2021-10-19 15:22:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StoreQueryDO extends BasePageQuery {
	private Integer type;
	private List<Integer> skill;
	private List<Integer> soul;
	private List<Integer> exp;
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
