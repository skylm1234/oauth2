package com.gejian.pixel.utils;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gejian.pixel.constants.Generated;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.proto.PlayerStringProtobuf;
import com.google.protobuf.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author ljb
 * @date 2021年08月30日 10:29
 * @description 公共工具类
 */
@Component
@RequiredArgsConstructor
public class Helper {

	private final Generated generated;

	private JSONArray skillToHero = new JSONArray();

	private void heroNameToSkillBookHash(){
		JSONArray rubyConstHeroTable = generated.getRUBY_CONST_HERO_TABLE();
		if (rubyConstHeroTable!=null){
			for (Object o : rubyConstHeroTable) {
				JSONObject constHero = (JSONObject) o;
				JSONObject hero = new JSONObject();
				hero.putOnce("skilla",constHero.get("name"));
				hero.putOnce("skill1",constHero.get("name"));
				hero.putOnce("skill2",constHero.get("name"));
				hero.putOnce("skill3",constHero.get("name"));
				hero.putOnce("skill4",constHero.get("name"));
				skillToHero.add(hero);
			}
		}
	}

	@PostConstruct
	public void init(){
		heroNameToSkillBookHash();
	}

	/**
	 * 字符串转换成十六进制字符串
	 * @param s 需要转换的字符串
	 * @return 十六进制字符串
	 */
	public static String hexEncode(String s){
		return HexUtil.encodeHexStr(s.getBytes());
	}

	/**
	 * 将十六进制字符数组转换为字符串，默认编码UTF-8
	 * @param hexStr 十六进制字符串
	 * @return 字符串
	 */
	public static String hexDecode(String hexStr){
		return HexUtil.decodeHexStr(hexStr);
	}

	/**
	 * 生成礼物背包标识符
	 * @param redisTemplate redis
	 * @return 背包标识符
	 */
	public static String giftbagIdentifier(RedisTemplate redisTemplate){
		return StrFormatter.format("giftbag_identifier_{}",redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
	}

	/**
	 * 生成用户标识符
	 * @param redisTemplate redis
	 * @return 用户标识符
	 */
	public static String generateUserIdentifier(RedisTemplate redisTemplate){
		return redisTemplate.opsForValue().increment("user:max:player_identifier") + "";
	}

	/**
	 * 生成英雄标识符
	 * @param redisTemplate redis
	 * @return 英雄标识符
	 */
	public static String generateHeroIdentifier(RedisTemplate redisTemplate){
		return redisTemplate.opsForValue().increment("user:max:hero_identifier") + "";
	}

	/**
	 * 生成IAP标识符
	 * @param redisTemplate redis
	 * @return IAP标识符
	 */
	public static String generateIapIdentifier(RedisTemplate redisTemplate){
		return redisTemplate.opsForValue().increment("user:max:iap_identifier") + "";
	}

	/**
	 * 得到字符串值
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @return
	 */
	public static  String stringValue(RedisTemplate redisTemplate, String identifier, String name){
		Object result = redisTemplate.opsForHash().get("u:" + identifier + ":strings", name);
		if (result!=null){
			return hexDecode(result.toString());
		}
		return null;
	}

	/**
	 * 得到字符串值
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param value 值
	 * @param reply 回复的消息
	 * @return
	 */
	public static PlayerStringProtobuf.PlayerString setStringValue(RedisTemplate redisTemplate, String identifier, String name, String value, ChannelHandlerContext reply){
		redisTemplate.opsForHash().put("u:" + identifier + ":strings", name, hexEncode(value));
		PlayerStringProtobuf.PlayerString item = PlayerStringProtobuf.PlayerString
				.newBuilder()
				.setKey(name)
				.setValue(value)
				.build();
		if (reply!=null){
			MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
					.newBuilder()
					.setData(item.toByteString())
					.build();
			reply.channel().write(messageBase);
		}

		return item;
	}

	/**
	 * 获得物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @return
	 */
	public static Integer itemCount(RedisTemplate redisTemplate, String identifier, String name){
		Object result = redisTemplate.opsForHash().get("u:" + identifier + ":items", name);
		if (result!=null){
			return Integer.valueOf(result+"");
		}
		return 0;
	}

	/**
	 * 设置物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param delta 值
	 * @param reply 回复的消息
	 */
	public static void setItemValue(RedisTemplate redisTemplate, String identifier, String name, Integer delta, ChannelHandlerContext reply){
		redisTemplate.opsForHash().put("u:" + identifier + ":items", name, delta);
		if (reply!=null){
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(name)
					.setValue(delta)
					.build();
			MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
					.newBuilder()
					.setData(item.toByteString())
					.build();
			reply.channel().write(messageBase);
		}
	}

	/**
	 * 增加物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param delta 值
	 * @param reply 回复的消息
	 * @return
	 */
	public static Boolean increaseItemValue(RedisTemplate redisTemplate, String identifier, String name, Integer delta, ChannelHandlerContext reply){
		if (delta>0) {
			Object current = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
			if (reply!=null){
				PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
						.newBuilder()
						.setKey(name)
						.setValue(Long.parseLong(current+""))
						.build();
				MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
						.newBuilder()
						.setData(item.toByteString())
						.build();
				reply.channel().write(messageBase);
			}

		}else if (delta<0 ){
			redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
			/*
				if current < 0 then
					redis.hincrby("u:#{identifier}:items", name, delta * -1)
					return false
				else
					if reply != nil then
						item = PLAYER_ITEM.new
						item.key = name
						item.value = current.to_i
						reply.items.push(item)
					end
				end
			 */

		}
		return Boolean.TRUE;
	}

}
