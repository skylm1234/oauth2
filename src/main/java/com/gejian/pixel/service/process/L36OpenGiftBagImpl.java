package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommOpenGiftbagRequestProtobuf;
import com.gejian.pixel.proto.CommOpenGiftbagResponseProtobuf;
import com.gejian.pixel.proto.ConstDropTableItemExProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 打開礼包
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.OPEN_GIFTBAG)
public class L36OpenGiftBagImpl implements Process<CommOpenGiftbagRequestProtobuf.CommOpenGiftbagRequest
		, CommOpenGiftbagResponseProtobuf.CommOpenGiftbagResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommOpenGiftbagResponseProtobuf.CommOpenGiftbagResponse doProcess(CommOpenGiftbagRequestProtobuf.CommOpenGiftbagRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();

		CommOpenGiftbagResponseProtobuf.CommOpenGiftbagResponse.Builder builder =
				CommOpenGiftbagResponseProtobuf.CommOpenGiftbagResponse.newBuilder();

		String giftBagKey = this.getGiftBagKey(identifier, request.getIdentifier());

		String giftBagsKey = this.getGiftBagsKey(identifier);

		Boolean existsGiftBag = this.redisTemplate.hasKey(giftBagKey);

		if (existsGiftBag == null || !existsGiftBag) {
			return builder.setResult(ErrorEnum.ERROR_OBJECT_NOT_FOUND).build();
		}

		Map giftBag = this.redisTemplate.opsForHash().entries(giftBagKey);

		// TODO 獲取數據
		String action = this.parseString(giftBag.get("action"));

		ConstDropTableItemExProtobuf.ConstDropTableItemEx dropTableItemEx =
				ConstDropTableItemExProtobuf.ConstDropTableItemEx.newBuilder().build();

		if (dropTableItemEx == null) {
			return builder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER_GIFTBAG_ACTION).build();
		}

		// TODO f.call(identifier, reply, false, g['content'])

		this.redisTemplate.delete(giftBagKey);

		this.redisTemplate.opsForHash().delete(giftBagsKey);

		Long size = this.redisTemplate.opsForHash().size(giftBagsKey);

		Helper.setItemValue(redisTemplate, this.parseString(identifier), "giftbags", size.intValue());

		builder.clearTeams();

		String teamsKey = this.getTeamsKey(identifier);

		Map teams = this.redisTemplate.opsForHash().entries(teamsKey);

		teams.forEach((k, v) -> builder.addTeams(
				PlayerItemProtobuf.PlayerItem.newBuilder().setKey(this.parseString(k))
						.setValue(this.parseInt(v))));

		return builder.build();
	}

	private String getGiftBagsKey(Integer identifier) {
		return "u:" + identifier + ":giftbags";
	}

	private String getTeamsKey(Integer identifier) {
		return "u:" + identifier + ":teams";
	}

	private String getGiftBagKey(Integer identifier, String s) {
		return "u:" + identifier + ":giftbag:" + s;
	}

	public Long parseLong(Object o) {
		return o == null ? 0L : Long.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}

	public String parseString(Object o) {
		return o == null ? "" : o.toString();
	}
}
