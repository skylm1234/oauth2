package com.gejian.pixel.service.process;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommGetPromotionAwardRequestProtobuf;
import com.gejian.pixel.proto.CommGetPromotionAwardResponseProtobuf;
import com.gejian.pixel.proto.PlayerStringProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ljb
 * @date 2021年09月03日 9:14
 * @description 获取升级奖励
 */
@Service(CommandConstants.GET_PROMOTION_AWARD)
@Slf4j
@RequiredArgsConstructor
public class GetPromotionAwardProcessImpl implements Process<CommGetPromotionAwardRequestProtobuf.CommGetPromotionAwardRequest,
		CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse> {

	private final RedisTemplate redisTemplate;

	@Override
	public CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse doProcess(CommGetPromotionAwardRequestProtobuf.CommGetPromotionAwardRequest request) throws Exception {
		CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse.Builder replyBuilder = CommGetPromotionAwardResponseProtobuf.CommGetPromotionAwardResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		int p = request.getPromotionId();

		// TODO: 2021/9/3 后面要修改成从常量中获取
		//row = RUBY_CONST_PROMOTION_TABLE_HASH['X%d' % p]
		Map<String, Object> row = new HashMap<>();
		if (row == null) {
			replyBuilder.setResult(ErrorEnum.ERROR_PROMOTION_NOT_EXIST);
			return replyBuilder.build();
		}

		String key = (String) row.get("key");
		Map<String, Object> archives = new HashMap<>();
		archives.put(key, redisTemplate.opsForHash().get("u:"+identifier+":archives", key));

		boolean finished;

		Integer parameter = (Integer) row.get("parameter");

		Integer compareType = (Integer) row.get("compare_type");
		if (compareType>=0 && compareType<=4) {
			finished = (Integer.parseInt(archives.get(key)+"") < parameter);
		}else {
			throw new RuntimeException("unknow compare type "+compareType+", should be 0-4");
		}

		if (!finished) {
			replyBuilder.setResult(ErrorEnum.ERROR_PROMOTION_NOT_FINISHED);
			return replyBuilder.build();
		}

		String skname = "finished_promotions";
		if (p>1000) {
			skname = "finished_daily_promotions";
		}

		String s = Helper.stringValue(redisTemplate, identifier, skname);

		JSONObject j = JSONUtil.parseObj(s);

		if (j.get(p+"") != null) {
			replyBuilder.setResult(ErrorEnum.ERROR_PROMOTION_ALREADY_FINISHED);
			return replyBuilder.build();
		}else {
			j.set(p+"", 1);
		}

		log.info("{}",row);

		// TODO: 2021/9/3 掉落待完善
		//foo = DROP_ITEMS[row['award']]
		//foo.call(identifier, reply, false, 'archives')

		s = JSONUtil.toJsonStr(j);
		log.info("{}",s);

		PlayerStringProtobuf.PlayerString item = Helper.setStringValue(redisTemplate, identifier, skname, s);
		replyBuilder.addStrings(item);

		return replyBuilder.build();
	}
}
