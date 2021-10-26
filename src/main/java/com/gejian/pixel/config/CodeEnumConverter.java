package com.gejian.pixel.config;

import com.gejian.pixel.enums.CodeToJsonEnums;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 16:21
 * @description：
 */

public class CodeEnumConverter implements ConverterFactory<String, CodeToJsonEnums> {

	@Override
	public <T extends CodeToJsonEnums> Converter<String, T> getConverter(Class<T> target) {
		return new IntegerCodeToEnum<>(target);
	}

	private static class IntegerCodeToEnum<T extends CodeToJsonEnums> implements Converter<String, T> {
		private final T[] values;
		public IntegerCodeToEnum(Class<T> targetType) {
			values = targetType.getEnumConstants();
		}

		@Override
		public T convert(String source) {
			for (T t : values) {
				if (t.getCode().equals(Integer.valueOf(source))) {
					return t;
				}
			}
			return null;
		}
	}
}
