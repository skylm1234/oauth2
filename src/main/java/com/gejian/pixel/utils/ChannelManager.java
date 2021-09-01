package com.gejian.pixel.utils;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author ZhouQiang
 * @date 2021/9/1$
 */
public class ChannelManager {


	/**
	 * 负责客户端Channel管理(线程安全) 断开
	 */
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


	public static void add(Channel channel) {
		channels.add(channel);
	}

	public static void remove(Channel channel){
		channels.remove(channel);
	}

	/**
	 * 广播消息
	 * @param msg
	 */
	public static void broadcast(Object msg){
		channels.writeAndFlush(msg);
	}


}
