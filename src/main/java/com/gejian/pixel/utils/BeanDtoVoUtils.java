package com.gejian.pixel.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Dto，Vo，entity 转换工具类
 */
public class BeanDtoVoUtils {

	/**
	 * Page<Entity> 分页对象转 Page<Vo>  ( list 循环)
	 *
	 * @param page
	 * @param v
	 * @param <T>
	 * @param <V>
	 * @return
	 */
	public static <T, V> IPage<V> pageVo(Page<T> page, Class<V> v) {
		return page.convert(item -> {
			try {
				return BeanUtil.copyProperties(item, v);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	public static <T, V> IPage<V> IPageVo(IPage<T> page, Class<V> v) {
		return page.convert(item -> {
			try {
				return BeanUtil.copyProperties(item, v);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
	}
}
