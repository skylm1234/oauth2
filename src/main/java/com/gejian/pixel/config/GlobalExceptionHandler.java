package com.gejian.pixel.config;

import com.gejian.pixel.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 9:59
 * @description：全局异常处理器
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Void> notFound(){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		log.error(e.getMessage(),e);
		ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
		return ResponseEntity.badRequest().body(objectError.getDefaultMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> MethodArgumentNotValidExceptionHandler(Exception e) {
		log.error(e.getMessage(),e);
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
