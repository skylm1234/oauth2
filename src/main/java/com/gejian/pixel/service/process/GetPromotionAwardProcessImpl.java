package com.gejian.pixel.service.process;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.Promotion;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.PromotionService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Service(CommandConstants.GET_PROMOTION_AWARD)
@Slf4j
public class GetPromotionAwardProcessImpl implements
		Process<CommGetPromotionAwardRequestProtobuf.CommGetPromotionAwardRequest,
				CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse> {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private DropService dropService;

	@Override
	public CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse
	doProcess(CommGetPromotionAwardRequestProtobuf.CommGetPromotionAwardRequest request) throws Exception {
		CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse.Builder response =
				CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse.newBuilder();
		Promotion promotion = promotionService.getById(request.getPromotionId());
		if (Objects.isNull(promotion)) {
			response.setResult(ErrorEnum.ERROR_PROMOTION_NOT_EXIST);
			response.setRequest(request);
			return response.build();
		}
		String key = promotion.getKey();
		Integer identifier = UserHolder.get().getIdentifier();
		List<Object> objects = redisTemplate.opsForHash()
				.multiGet(StrUtil.format(RedisKeyConstants.USER_ARCHIVES, identifier), ListUtil.toList(key));
		if (CollectionUtils.isEmpty(objects)) {
			log.info("archives 不存在！id:{}", identifier);
			response.setResult(ErrorEnum.ERROR_PROMOTION_NOT_EXIST);
			response.setRequest(request);
			return response.build();
		}
		int value = Integer.parseInt(String.valueOf(objects.get(0)));
		Integer compareType = promotion.getCompareType();
		boolean finished = false;
		Integer parameter = promotion.getParameter();
		switch (compareType) {
			case 0:
				finished = value < parameter;
				break;
			case 1:
				finished = value <= parameter;
				break;
			case 2:
				finished = value == parameter;
				break;
			case 3:
				finished = value >= parameter;
				break;
			case 4:
				finished = value > parameter;
				break;
			default:
				break;
		}
		if (!finished){
			log.info("晋升未完成！id:{}",identifier);
			return response.setResult(ErrorEnum.ERROR_PROMOTION_NOT_FINISHED)
					.setRequest(request)
					.build();
		}
		String skName = "finished_promotions";
		if (request.getPromotionId() >= 10000){
			skName = "finished_daily_promotions";
		}
		String jsonStr = getStringValue(identifier, skName);
		JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
		Object obj = jsonObject.getObj("#{p}");
		if (Objects.nonNull(obj)){
			return response.setResult(ErrorEnum.ERROR_PROMOTION_ALREADY_FINISHED)
					.setRequest(request)
					.build();
		}
		jsonObject.putOnce("#{p}",1);
		String award = promotion.getAward();
		// 获取DROP ITEMS对应的数据
		PlayerInfoProtobuf.PlayerInfo playerInfo = dropService
				.dropItem(award, identifier, false, "archives");
		response.addAllHeros(playerInfo.getHerosList());
		response.addAllArchives(playerInfo.getArchivesList());
		response.addAllItems(playerInfo.getItemsList());
		response.addAllTeams(playerInfo.getTeamsList())
				.addAllTeamsPvp(playerInfo.getTeamsPvpList());
		String s = jsonObject.toString();
		PlayerStringProtobuf.PlayerString playerString = Helper.setStringValue(redisTemplate, identifier, skName, s);
		return response.addStrings(playerString)
				.build();
	}

	private String getStringValue(Integer identifier,String name){
		Object val = redisTemplate.opsForHash()
				.get(StrUtil.format(RedisKeyConstants.USER_STRINGS, identifier), name);
		if (Objects.isNull(val)){
			return null;
		}
		return Helper.hexDecode(String.valueOf(val));
	}
}
