package com.gejian.pixel.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * JacksonConfig 配置时间转换规则
 * {@link PigxJavaTimeModule}、默认时区等
 *
 * @author L.cm
 * @author lengleng
 * @author lishangbu
 * @date 2020-06-15
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JacksonConfig implements WebMvcConfigurer {

	private static final String ASIA_SHANGHAI = "Asia/Shanghai";

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer(ObjectMapper objectMapper,JavaTimeModule javaTimeModule) {
		return builder -> {
			builder.locale(Locale.CHINA);
			builder.timeZone(TimeZone.getTimeZone(ASIA_SHANGHAI));
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			builder.serializerByType(Long.class, ToStringSerializer.instance);
			builder.modules(javaTimeModule);
			builder.configure(objectMapper);
		};
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
		return new MappingJackson2HttpMessageConverter(objectMapper);
	}

	@Bean
	public JavaTimeModule javaTimeModule(){
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		// ======================= 时间序列化规则 ===============================
		// yyyy-MM-dd HH:mm:ss
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
		// yyyy-MM-dd
		javaTimeModule.addSerializer(LocalDate.class,
				new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
		// HH:mm:ss
		javaTimeModule.addSerializer(LocalTime.class,
				new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));

		// ======================= 时间反序列化规则 ==============================
		// yyyy-MM-dd HH:mm:ss
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
		// yyyy-MM-dd
		javaTimeModule.addDeserializer(LocalDate.class,
				new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
		// HH:mm:ss
		javaTimeModule.addDeserializer(LocalTime.class,
				new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
		return javaTimeModule;
	}

	@Bean
	public ObjectMapper objectMapper(JavaTimeModule javaTimeModule) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
		objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
		objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
		return objectMapper;
	}

	/**
	 * 增加GET请求参数中时间类型转换
	 * <ul>
	 * <li>HH:mm:ss -> LocalTime</li>
	 * <li>yyyy-MM-dd -> LocalDate</li>
	 * <li>yyyy-MM-dd HH:mm:ss -> LocalDateTime</li>
	 * </ul>
	 * @param registry
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
		registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
		registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
		registrar.registerFormatters(registry);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
		converters.add(new MappingJackson2HttpMessageConverter(objectMapper(javaTimeModule())));
	}

}
