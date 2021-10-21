package com.gejian.pixel.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 9:59
 * @description：全局异常处理器
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@Autowired
	private ObjectMapper objectMapper;

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<String> notFound(HttpServletRequest req) throws JsonProcessingException {
		Map<String,Object> result = ImmutableMap.of("timestamp", LocalDateTime.now(),"status", HttpStatus.NOT_FOUND.value(),
				"error","Not Found","path",req.getRequestURI());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(objectMapper.writeValueAsString(result),headers,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		log.error(e.getMessage(),e);
		ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
		return ResponseEntity.badRequest().body(objectError.getDefaultMessage());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> badCredentialsHandler(BadCredentialsException e) {
		log.error(e.getMessage(),e);
		return ResponseEntity.badRequest().body("用户名或密码错误");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> MethodArgumentNotValidExceptionHandler(Exception e) {
		log.error(e.getMessage(),e);
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
