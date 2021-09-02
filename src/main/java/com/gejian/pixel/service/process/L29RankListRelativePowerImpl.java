package com.gejian.pixel.service.process;

import cn.hutool.core.bean.BeanUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommRanklistRelativePowerRequestProtobuf;
import com.gejian.pixel.proto.CommRanklistRelativePowerResponseProtobuf;
import com.gejian.pixel.proto.CommRanklistResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.RankListHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 排行榜权力
 *
 * @author Reese
 * @date 2021/9/1
 */
@Slf4j
@Service(CommandConstants.RANKLIST_RELATIVE_POWER)
public class L29RankListRelativePowerImpl implements Process<CommRanklistRelativePowerRequestProtobuf.CommRanklistRelativePowerRequest
		, CommRanklistRelativePowerResponseProtobuf.CommRanklistRelativePowerResponse> {


	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RankListHelper rankListHelper;

	@Override
	public CommRanklistRelativePowerResponseProtobuf.CommRanklistRelativePowerResponse doProcess(CommRanklistRelativePowerRequestProtobuf.CommRanklistRelativePowerRequest request) throws Exception {

		Integer identifier = UserHolder.get().getIdentifier();

		CommRanklistRelativePowerResponseProtobuf.CommRanklistRelativePowerResponse.Builder builder =
				CommRanklistRelativePowerResponseProtobuf.CommRanklistRelativePowerResponse.newBuilder();

		int dummy = request.getDummy();
		if (dummy == 2) {
			CommRanklistResponseProtobuf.CommRanklistResponse commRanklistResponse =
					CommRanklistResponseProtobuf.CommRanklistResponse.getDefaultInstance();
			this.rankListHelper.rankListHelper(this.rankListHelper.topRangeTianti(identifier, 10L, true), commRanklistResponse);
			BeanUtil.copyProperties(commRanklistResponse, builder);
		} else {
			if (dummy == 1) {
				String itemsKey = getItemsKey(identifier);
				this.redisTemplate.opsForHash().put(itemsKey, "should_refresh_pvp_chanllege_ranklist", 1);
			}
			builder.setResult(this.rankListHelper.refreshPvp(identifier, true, builder));
		}
		return builder.build();
	}

	private String getItemsKey(Integer identifier) {
		return "u:#{identifier}:items".replace("#{identifier}", identifier.toString());

	}


}
