package com.gejian.pixel.config;

import com.gejian.pixel.enums.CodeToJsonEnums;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author ：lijianghuai
 * @date ：2021-10-18 10:07
 * @description：暂时不用
 */

public class EnumConvertFactory implements ConverterFactory<String, CodeToJsonEnums> {

	private static final Map<Class<?>, Converter> CONVERTER_MAP = new WeakHashMap<>();

	@Override
	public <T extends CodeToJsonEnums> Converter<String, T> getConverter(Class<T> targetType) {
		Converter result = CONVERTER_MAP.get(targetType);
		if(result == null) {
			result = new IntegerCodeToEnum<>(targetType);
			CONVERTER_MAP.put(targetType, result);
		}
		return result;
	}

	 static class IntegerCodeToEnum<T extends CodeToJsonEnums> implements Converter<String, T> {
		 private final Map<String, T> ENUM_MAP = new HashMap<>();
		 public IntegerCodeToEnum(Class<T> enumType) {
			T[] enums = enumType.getEnumConstants();
			for(T e : enums) {
				ENUM_MAP.put(e.getCode() + "", e);
			}
		}


		@Override
		public T convert(String source) {
			T result = ENUM_MAP.get(source);
			if(result == null) {
				throw new IllegalArgumentException("No element matches " + source);
			}
			return result;
		}
	}

}
