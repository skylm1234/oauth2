package com.gejian.pixel.service.process;

import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.BuyHero;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommBuyHeroRequestProtobuf;
import com.gejian.pixel.proto.CommBuyHeroResponseProtobuf;
import com.gejian.pixel.proto.PlayerInfoProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.BuyHeroService;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Hyb
 * @date : 2021/9/3 14:02
 * @description: 购买英雄请求
 */
@Service(CommandConstants.BUY_HERO)
@Slf4j
public class BuyHeroProcessImpl implements Process<CommBuyHeroRequestProtobuf.CommBuyHeroRequest
		, CommBuyHeroResponseProtobuf.CommBuyHeroResponse> {

	@Autowired
	private BuyHeroService buyHeroService;

	@Autowired
	private DropService dropService;

	@Autowired
	private RedisTemplate redisTemplate;

	private static final String BUY_HERO_TYPE_TIMES = "buy_hero_{}_times";

	private static final String BUY_HERO_TYPE_TIMESTAMP = "buy_hero_{}_timestamp";

	private static final String BUY_HERO_NUM_PRICE = "buy_hero_{}_price";

	private static final String MOSTHIRE = "mosthire";

	private static final String DAILY_BUY_HERO = "daily_buy_hero";


	@Override
	public CommBuyHeroResponseProtobuf.CommBuyHeroResponse doProcess(CommBuyHeroRequestProtobuf.CommBuyHeroRequest commBuyHeroRequest) throws Exception {

		CommBuyHeroResponseProtobuf.CommBuyHeroResponse.Builder response = CommBuyHeroResponseProtobuf.CommBuyHeroResponse.newBuilder();

		int type = commBuyHeroRequest.getType();

		BuyHero typeInfo = buyHeroService.getHero(type);

		if (!(type >= 1 && type <= 3) || null == typeInfo) {
			return response.setResult(ErrorEnum.ERROR_INVALID_BUY_HERO_TYPE).build();
		}


		Integer identifier = UserHolder.get().getIdentifier();

		if (typeInfo.getDayLimit().intValue() != 0 && Helper.itemCount(redisTemplate, identifier,
				StrUtil.format(BUY_HERO_TYPE_TIMES, type)) >= typeInfo.getDayLimit()) {
			return response.setResult(ErrorEnum.ERROR_EXCEED_DAILY_LIMIT).build();
		}


		String buyHeroFf = typeInfo.getDropid();

		List<Integer> resar = Arrays.asList(ErrorEnum.ERROR_NOT_ENOUGH_HONOR, ErrorEnum.ERROR_NOT_ENOUGH_GOLD, ErrorEnum.ERROR_NOT_ENOUGH_STONE);

		if ((typeInfo.getCooldown() != 0 && Helper.currentTimestamp() -
				Helper.itemCount(redisTemplate, identifier,
						StrUtil.format(BUY_HERO_TYPE_TIMESTAMP, type)) >= typeInfo.getCooldown())
				|| buyHeroService.calculation(
				type,
				Helper.itemCount(redisTemplate, identifier,
						StrUtil.format(BUY_HERO_TYPE_TIMES, type))) == 0
		) {
			PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, String.valueOf(identifier), StrUtil.format(BUY_HERO_TYPE_TIMESTAMP, type), (int) Helper
					.currentTimestamp());
			response.addItems(playerItem);
			PlayerInfoProtobuf.PlayerInfo playerInfo = dropService.dropItem(buyHeroFf, identifier, false, String.valueOf(type));
			response.addAllItems(playerInfo.getItemsList())
					.addAllTeams(playerInfo.getTeamsList())
					.addAllTeamsPvp(playerInfo.getTeamsPvpList())
					.addAllArchives(playerInfo.getArchivesList())
					.addAllHeros(playerInfo.getHerosList());
		} else {
			PlayerItemProtobuf.PlayerItem decreaseItem = Helper.decreaseItemValue(redisTemplate, identifier, typeInfo.getConsume()
					, (long) buyHeroService.calculation(
							type,
							Helper.itemCount(redisTemplate, identifier,
									StrUtil.format(BUY_HERO_TYPE_TIMES, type))));
			if (null != decreaseItem) {

				PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, String.valueOf(identifier), StrUtil.format(BUY_HERO_TYPE_TIMES
						, type), Helper.itemCount(redisTemplate, identifier, StrUtil.format(BUY_HERO_TYPE_TIMES,
						type)) + 1);
				response.addItems(playerItem);


				PlayerInfoProtobuf.PlayerInfo playerInfo = dropService.dropItem(buyHeroFf, identifier, false, String.valueOf(type));
				response.addAllItems(playerInfo.getItemsList())
						.addAllTeams(playerInfo.getTeamsList())
						.addAllTeamsPvp(playerInfo.getTeamsPvpList())
						.addAllArchives(playerInfo.getArchivesList())
						.addAllHeros(playerInfo.getHerosList());
				//返回消耗后的剩余物资数量
				response.addItems(decreaseItem);
			} else {
				return response.setResult(resar.get(type-1)).build();
			}
		}

		for (int i = 1; i <= 3; i++) {
			PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, String.valueOf(identifier), StrUtil.format(BUY_HERO_NUM_PRICE, i),
					(int) buyHeroService.calculation(i,
							Helper.itemCount(redisTemplate, identifier, StrUtil.format(BUY_HERO_TYPE_TIMES
									, i))));
			response.addItems(playerItem);
		}

		response.addArchives(Helper.onNotifyEventOfPromotions(redisTemplate, MOSTHIRE, 1, identifier));
		response.addArchives(Helper.onNotifyEventOfPromotions(redisTemplate, DAILY_BUY_HERO, 1, identifier));

		return response.build();
	}

	private static Message toProtoBean(Message.Builder targetBuilder, String json) throws IOException {
		JsonFormat.parser().merge(json, targetBuilder);
		return targetBuilder.build();
	}
}
