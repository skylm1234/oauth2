package com.gejian.pixel.utils;

import com.gejian.pixel.constants.AttributeKeyConstants;
import com.gejian.pixel.model.UserInfo;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.Optional;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
public class UserHolder {

	public static UserInfo get(){
		Channel channel = ChannelHolder.get();
		return Optional.ofNullable(channel)
				.map(item->item.attr(AttributeKeyConstants.USER_INFO_ATTRIBUTE_KEY))
				.map(Attribute::get).orElse(null);
	}
}
