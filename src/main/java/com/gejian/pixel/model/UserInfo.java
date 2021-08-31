package com.gejian.pixel.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
@Data
public class UserInfo implements Serializable {

	/**
	 * 会话session id
	 */
	private String session;

	/**
	 * 身份id
	 */
	private Integer identifier;



}
