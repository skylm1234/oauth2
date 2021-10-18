package com.gejian.pixel.exception;

/**
 * @author ：lijianghuai
 * @date ：2021-10-18 15:02
 * @description：
 */
public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("404 Not Found");
	}
}
