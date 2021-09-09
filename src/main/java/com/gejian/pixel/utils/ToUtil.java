package com.gejian.pixel.utils;

import cn.hutool.core.util.NumberUtil;

/**
 * @Author:chen
 * @Date: 2021/9/1 18:02
 */
public class ToUtil {


	/**
	 * 转Integer
	 *
	 * @param obj
	 * @return
	 */
	public static Integer to_i(Object obj) {
		String value = String.valueOf(obj);
		String trim = value.trim();
		StringBuilder result = new StringBuilder("0");
		if (trim.length() > 0) {
			for (int i = 0; i < trim.length(); i++) {
				if (!Character.isDigit(trim.charAt(i))) {
					break;
				}
				result.append(trim.charAt(i));
			}
		}
		return NumberUtil.parseInt(result.toString());
	}

}
