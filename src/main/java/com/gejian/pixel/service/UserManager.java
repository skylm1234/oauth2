package com.gejian.pixel.service;

import com.gejian.pixel.model.UserInfo;
import io.netty.channel.ChannelId;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
public class UserManager {

	private static ConcurrentHashMap<ChannelId, UserInfo> POOL = new ConcurrentHashMap<>(256);

	public static void addUserInfo(ChannelId channelId,UserInfo userInfo){
		POOL.put(channelId,userInfo);
	}

	public static UserInfo getUserInfo(ChannelId channelId){
		if (Objects.isNull(channelId)){
			return null;
		}
		return POOL.get(channelId);
	}

	public static void remove(ChannelId channelId){
		POOL.remove(channelId);
	}

}
