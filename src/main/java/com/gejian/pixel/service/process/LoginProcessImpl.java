package com.gejian.pixel.service.process;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.Generated;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * 登陆请求
 *
 * @author ZhouQiang
 * @date 2021/8/30$
 */
@Service(CommandConstants.LOGIN_REQUEST)
@Slf4j
@RequiredArgsConstructor
public class LoginProcessImpl implements Process<CommLoginRequestProtobuf.CommLoginRequest
		,CommLoginResponseProtobuf.CommLoginResponse> {

	private final StringRedisTemplate redisTemplate;

	private static final int MIN_VERSION = 10;

	private Generated generated = new Generated();

	@Override
	public CommLoginResponseProtobuf.CommLoginResponse
		doProcess(CommLoginRequestProtobuf.CommLoginRequest request) throws Exception {
		log.info("登陆请求参数：{}",request);
		
		CommLoginResponseProtobuf.CommLoginResponse.Builder replyBuilder = CommLoginResponseProtobuf.CommLoginResponse.newBuilder();

		long currentTimestamp = Helper.currentTimestamp();
		long currentDays = Helper.currentDay();
		replyBuilder.setTimestamp(NumberUtil.parseInt(Helper.currentTimestamp()+""));

		if (request.getVersion() > MIN_VERSION) {
			String s = StrFormatter.format("{} {}  {}   {}",
					request.getData(),
					request.getVersion(),
					request.getIdentifier(),
					Integer.parseInt(request.getData())*request.getVersion());
			log.info("request:");
			log.info(s);
			log.info(SecureUtil.sha1(s));
			if (!SecureUtil.sha1(s).toUpperCase().equals(request.getCipher().toUpperCase())){
				replyBuilder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER);
				return replyBuilder.build();
			}
		}

		if (request.getVersion() < 10) {
			replyBuilder.setResult(ErrorEnum.ERROR_CLIENT_VERSION_TOOOOO_OLD);
			replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setData("http://www.baidu.com").build());
			return replyBuilder.build();
		}

		Object systemBanAnyone = redisTemplate.opsForValue().get("system:ban_anyone");
		if (systemBanAnyone!=null) {
			if (Integer.parseInt(systemBanAnyone+"") == 1) {
				log.error("FAILED: {}=>{}:{}",request.getIdentifier(),CommandConstants.LOGIN_REQUEST,Thread.currentThread().getStackTrace()[1].getLineNumber());
				replyBuilder.setResult(ErrorEnum.ERROR_BANNED);
				replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setData(systemBanAnyone+"").build());
				return replyBuilder.build();
			}
		}

		String hexEncodedIdentifier = request.getIdentifier().length() != 0 ? Helper.hexEncode(request.getIdentifier()) : "";

		if (request.getIdentifier().length() == 0 || redisTemplate.opsForHash().hasKey("user:set:identifier", hexEncodedIdentifier)){
			log.info("new player register");

			String identifier = Helper.generateUserIdentifier(redisTemplate);

			hexEncodedIdentifier = Helper.hexEncode(identifier);
			replyBuilder.setRequest(CommLoginRequestProtobuf.CommLoginRequest.newBuilder().setIdentifier(identifier).build());

			long timestamp = Helper.currentTimestamp();
			long hours = timestamp - timestamp % 3600;
			Map<String,Object> items = new HashMap<>();
			items.put("dungeon_1_not_passed_stage",1);
			items.put("dungeon_2_not_passed_stage",1001);
			items.put("dungeon_3_not_passed_stage",2001);
			items.put("create_at",currentTimestamp);
			items.put("giftbags",0);
			items.put("vip",0);//充得多等级高
			items.put("power",0);//战力，初始为0
			items.put("gold",0);//金币（游戏币）
			items.put("stone",0);//石头（充值币）当前
			items.put("honor",0);//荣誉点
			items.put("total_charged_money",0);
			items.put("total_honor",0);
			items.put("daily_check_in_timestamp",0);
			items.put("daily_check_in_count",0);
			items.put("tianti_challage_times",0);
			items.put("pvp_1_vectory",0);
			items.put("pvp_3_vectory",0);
			items.put("pvp_9_vectory",0);
			items.put("should_refresh_pvp_chanllege_ranklist",1);
			items.put("pvp_vectory_times",0);
			items.put("pvp_challage_times",0);
			items.put("buy_hero_1_timestamp",0);
			items.put("buy_hero_1_times",0);
			items.put("buy_hero_1_price",0);
			items.put("buy_hero_2_timestamp",0);
			items.put("buy_hero_2_times",0);
			items.put("buy_hero_2_price",0);
			items.put("buy_hero_3_timestamp",0);
			items.put("buy_hero_3_times",0);
			items.put("buy_hero_3_price",0);

			Map<String,Object> strings = new HashMap<>();
			strings.put("new_store_refresh_desc",Helper.hexEncode("商店刷新时间：每日09:00、12:00、18:00、22：00"));
			strings.put("finished_promotions",Helper.hexEncode("{}"));
			strings.put("finished_daily_promotions",Helper.hexEncode("{}"));

			JSONArray RUBY_CONST_IN_GAME_PURCHASE_TABLE = generated.getRUBY_CONST_IN_GAME_PURCHASE_TABLE();
			for (Object o : RUBY_CONST_IN_GAME_PURCHASE_TABLE) {
				JSONObject jsonObject = (JSONObject) o;
				items.put(jsonObject.get("id")+"",0);
			}

			if (redisTemplate.opsForHash().putIfAbsent(RedisKeyConstants.USER_IDENTIFIER, hexEncodedIdentifier, identifier)) {
				redisTemplate.opsForHash().putAll(StrUtil.format(RedisKeyConstants.USER_ITEMS,identifier), items);
				redisTemplate.opsForHash().putAll(StrUtil.format(RedisKeyConstants.USER_STRINGS,identifier), strings);
				redisTemplate.opsForHash().putIfAbsent(RedisKeyConstants.USER_CLEAR_TEXT, identifier, identifier);
			}else {
				throw new RuntimeException("failed");
			}

		}
		return replyBuilder.build();
	}

}
