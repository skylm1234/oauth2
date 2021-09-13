package com.gejian.pixel.service.process;

import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.CommGetPvpVictoryAwardRequestProtobuf;
import com.gejian.pixel.proto.CommGetPvpVictoryAwardResponseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author:chen
 * @Date: 2021/9/2 13:45
 */
@Service(CommandConstants.GET_PVP_VICTORY_AWARD)
@CommandResponse(CommandConstants.GET_PVP_VICTORY_AWARD)
@Slf4j
public class CommGetPvpVictoryAwardRequestImpl implements Process<CommGetPvpVictoryAwardRequestProtobuf.CommGetPvpVictoryAwardRequest,
		CommGetPvpVictoryAwardResponseProtobuf.CommGetPvpVictoryAwardResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommGetPvpVictoryAwardResponseProtobuf.CommGetPvpVictoryAwardResponse
	doProcess(CommGetPvpVictoryAwardRequestProtobuf.CommGetPvpVictoryAwardRequest request) throws Exception {

		CommGetPvpVictoryAwardResponseProtobuf.CommGetPvpVictoryAwardResponse.Builder builder
				= CommGetPvpVictoryAwardResponseProtobuf
				.CommGetPvpVictoryAwardResponse.newBuilder();
		int type = request.getType();

		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();

		if (type == 1) {
			PlayerItemProtobuf.PlayerItem pvpVectoryTimes = Helper.increaseItemValue(redisTemplate, identifier, "pvp_vectory_times", 1L);
			PlayerItemProtobuf.PlayerItem honor = Helper.increaseItemValue(redisTemplate, identifier, "honor", 1L);
			PlayerItemProtobuf.PlayerItem totalHonor = Helper.increaseItemValue(redisTemplate, identifier, "total_honor", 1L);
			builder.addItems(pvpVectoryTimes);
			builder.addItems(honor);
			builder.addItems(totalHonor);

			PlayerItemProtobuf.PlayerItem playerItem = Helper.updateRanklistHonor(redisTemplate, identifier);
			PlayerItemProtobuf.PlayerItem playerItem1 = Helper.onNotifyEventOfPromotions(redisTemplate, "maxhonor", 1, identifier);

			builder.addArchives(playerItem)
					.addArchives(playerItem1);

		} else if (type == 3) {
			//TODO
		}
		return builder.build();
	}
}
