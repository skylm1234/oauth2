package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommBuyHeroRequestProtobuf;
import com.gejian.pixel.proto.CommBuyHeroResponseProtobuf;
import com.gejian.pixel.proto.CommSetNicknameRequestProtobuf;
import com.gejian.pixel.proto.CommSetNicknameResponseProtobuf;
import com.gejian.pixel.service.BuyHeroService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public CommBuyHeroResponseProtobuf.CommBuyHeroResponse doProcess(CommBuyHeroRequestProtobuf.CommBuyHeroRequest commBuyHeroRequest) throws Exception {

		CommBuyHeroResponseProtobuf.CommBuyHeroResponse.Builder response = CommBuyHeroResponseProtobuf.CommBuyHeroResponse.newBuilder();

		int type = commBuyHeroRequest.getType();


		// TODO  type_info = RUBY_CONST_BUY_HERO_TABLE[type - 1]

		Object typeInfo = null;

		if (!(type >= 1 && type <= 3) || null == typeInfo) {
			return response.setResult(ErrorEnum.ERROR_INVALID_BUY_HERO_TYPE).build();
		}



		// TODO  buy_hero_ff = type_info['dropid']
		// TODO  buy_hero_f = DROP_ITEMS[buy_hero_ff]

		long currentDay = Helper.currentDay();

		// TODO type_info['day_limit'] != 0 && item_count(identifier, 		  //'buy_hero_%d_times' % type) >= type_info['day_limit']
		if (true) {
			return response.setResult(ErrorEnum.ERROR_EXCEED_DAILY_LIMIT).build();
		}


		// TODO ....

		return null;
	}
}
