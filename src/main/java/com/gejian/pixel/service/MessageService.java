package com.gejian.pixel.service;

/**
 * @author ：lijianghuai
 * @date ：2021-11-03 13:53
 * @description：
 */
public interface MessageService {

	void sendBoardcastMessage(String message);

	void setNoticeMessage(String message);

	void setMaintenanceMessage(String message);

	String getNoticeMessage();

	String getMaintenanceMessage();

}
