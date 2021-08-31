package com.gejian.pixel.utils;

import com.gejian.pixel.constants.AttributeKeyConstants;
import com.gejian.pixel.model.UserInfo;
import io.netty.channel.Channel;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
public class UserHolder {

	private final static ThreadLocal<UserInfo> TL = new ThreadLocal<>();

	public static void put(Channel channel){
		TL.set(channel.attr(AttributeKeyConstants.USER_INFO_ATTRIBUTE_KEY).get());
	}

	public static UserInfo get(){
		return TL.get();
	}

	public static void clear(){
		TL.remove();
	}
}
