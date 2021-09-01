package com.gejian.pixel.utils;

import io.netty.channel.Channel;

/**
 * @author ZhouQiang
 * @date 2021/9/1$
 */
public class ChannelHolder {

	private final static ThreadLocal<Channel> TL = new ThreadLocal<>();

	public static void put(Channel channel){
		TL.set(channel);
	}

	public static Channel get(){
		return TL.get();
	}

	public static void clear(){
		TL.remove();
	}


}
