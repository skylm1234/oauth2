package com.gejian.pixel.service.process;

import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommGetPvpVictoryAwardRequestProtobuf;
import com.gejian.pixel.proto.CommGetPvpVictoryAwardResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.ChannelHolder;
import com.gejian.pixel.utils.Helper;
import io.netty.channel.Channel;
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

	private final static Integer identifier = 0;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommGetPvpVictoryAwardResponseProtobuf.CommGetPvpVictoryAwardResponse doProcess(CommGetPvpVictoryAwardRequestProtobuf.CommGetPvpVictoryAwardRequest request) throws Exception {

		//获取当前channel
		Channel channel = ChannelHolder.get();

		int type = request.getType();
		if (type == 1) {
			Helper.increaseItemValue(redisTemplate, identifier, "pvp_vectory_times", 1L);
			Helper.increaseItemValue(redisTemplate, identifier, "honor", 1L);
			Helper.increaseItemValue(redisTemplate, identifier, "total_honor", 1L);

			Helper.updateRanklistHonor(redisTemplate, identifier, channel);
			Helper.onNotifyEventOfPromotions(redisTemplate, "maxhonor", 1, identifier, channel);

		} else if (type == 3) {
			//TODO
		}
		return null;
	}
}
