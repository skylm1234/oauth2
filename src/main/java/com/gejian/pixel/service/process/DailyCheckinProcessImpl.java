package com.gejian.pixel.service.process;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.BuyHero;
import com.gejian.pixel.entity.VipDaily;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.BuyHeroService;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.VipDailyService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author : Hyb
 * @date : 2021/9/3 14:02
 * @description: 购买英雄请求
 */
@Service(CommandConstants.DAILY_CHECKIN)
@Slf4j
public class DailyCheckinProcessImpl implements Process<CommDailyCheckinRequestProtobuf.CommDailyCheckinRequest,
		CommDailyCheckinResponseProtobuf.CommDailyCheckinResponse> {


	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private DropService dropService;

	@Autowired
	private VipDailyService vipDailyService;


	@Override
	public CommDailyCheckinResponseProtobuf.CommDailyCheckinResponse doProcess(CommDailyCheckinRequestProtobuf.CommDailyCheckinRequest request) throws Exception {
		CommDailyCheckinResponseProtobuf.CommDailyCheckinResponse.Builder builder =
				CommDailyCheckinResponseProtobuf.CommDailyCheckinResponse.newBuilder();
		long day = Helper.currentDay();
		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();
		Integer dailyCheckInTimestamp =
				Optional.of(Helper.itemCount(redisTemplate, identifier, "daily_check_in_timestamp"))
						.orElse(0);

		if (day == dailyCheckInTimestamp) {
			log.info("今日已签到！id:{}", identifier);
			builder.setResult(ErrorEnum.ERROR_TODAY_ALREADY_CHECK_IN);
			return builder.build();
		}
		Integer vip = Optional.of(Helper.itemCount(redisTemplate, identifier, "vip")).orElse(0);
		PlayerInfoProtobuf.PlayerInfo playerInfo;
		if (dailyCheckInTimestamp == day - 1) {
			PlayerItemProtobuf.PlayerItem playerItem = Helper
					.increaseItemValue(redisTemplate, identifier, "daily_check_in_count", 1L);
			builder.addItems(playerItem);
			int continusDays = Helper.itemCount(redisTemplate, identifier, "daily_check_in_count");
			if (continusDays % 30 == 0) {
				VipDaily vipDaily = vipDailyService.getById(vip);
				String value = (String) BeanUtil.beanToMap(vipDaily).get("daily30");
				playerInfo = dropService.dropItem(value, identifier, false, null);
			} else {
				VipDaily vipDaily = vipDailyService.getById(vip);
				String value = (String) BeanUtil.beanToMap(vipDaily).get("daily" + continusDays % 30);
				playerInfo = dropService.dropItem(value, identifier, false, null);
			}

		} else {
			PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, identifier + "", "daily_check_in_count", 1);
			builder.addItems(playerItem);
			VipDaily vipDaily = vipDailyService.getById(vip);
			String daily1 = vipDaily.getDaily1();
			playerInfo = dropService.dropItem(daily1, identifier, false, null);
		}
		builder.addAllHeros(playerInfo.getHerosList())
				.addAllItems(playerInfo.getItemsList());
		PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, String.valueOf(identifier)
				, "daily_check_in_timestamp", (int) day);
		builder.addItems(playerItem);
		return builder.build();

	}
}
