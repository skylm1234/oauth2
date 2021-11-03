package com.gejian.pixel.service.impl;

import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.service.MessageService;
import com.gejian.pixel.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-11-03 13:57
 * @description：
 */

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private RedisTemplate<String,String>  redisTemplate;

	@Override
	public void sendBoardcastMessage(String message) {
		Helper.boardcaseWorldEvent(message);
	}

	@Override
	public void setNoticeMessage(String message) {
		redisTemplate.opsForValue().set(RedisKeyConstants.SYSTEM_BOARDCAST11,message);
	}

	@Override
	public void setMaintenanceMessage(String message) {
		redisTemplate.opsForValue().set(RedisKeyConstants.SYSTEM_BAN_ANYONE_REASON,message);
	}

	@Override
	public String getNoticeMessage() {
		return redisTemplate.opsForValue().get(RedisKeyConstants.SYSTEM_BOARDCAST11);
	}

	@Override
	public String getMaintenanceMessage() {
		return redisTemplate.opsForValue().get(RedisKeyConstants.SYSTEM_BAN_ANYONE_REASON);
	}
}
