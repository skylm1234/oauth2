package com.gejian.pixel.service;

/**
 *
 * service name 必须和command name 对应
 *
 * @author ZhouQiang
 * @date 2021/8/30$
 */
public interface Process<Request,Response>{

	/**
	 * 请求处理
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Response doProcess(Request request) throws Exception;

}
