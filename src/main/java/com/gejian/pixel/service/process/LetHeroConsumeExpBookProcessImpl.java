package com.gejian.pixel.service.process;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.ConsumeExpBookRedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf;
import com.gejian.pixel.proto.CommLetHeroConsumeExpBookResponseProtobuf;
import com.gejian.pixel.proto.CommSetNicknameRequestProtobuf;
import com.gejian.pixel.proto.CommSetNicknameResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Hyb
 * @date : 2021/9/3 15:22
 * @description:
 */
@Service(CommandConstants.LET_HERO_CONSUME_EXP_BOOK)
@Slf4j
public class LetHeroConsumeExpBookProcessImpl implements Process<CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest
		, CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	private static final String TYPE = "type";

	@Override
	public CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse doProcess(CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest request) throws Exception {

		CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse.Builder response = CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse.newBuilder();

		// 用户身份ID
		Integer identifier = UserHolder.get().getIdentifier();
		String hero = request.getHero();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("d", String.valueOf(identifier));
		map.put("s", hero);

		List<String> strings = Arrays.asList(StrUtil.format(ConsumeExpBookRedisKeyConstants.USER_ATTRIBUTES, map));

		if (redisTemplate.countExistingKeys(strings).longValue() == 0) {
			return response.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}

		boolean dirty = false;

		Map<String, Object> hh = redisTemplate.opsForHash().entries(StrUtil.format(ConsumeExpBookRedisKeyConstants.USER_ATTRIBUTES, map));

		HashMap<String, Object> heroMap = new HashMap<String, Object>();

		hh.entrySet().forEach(entry -> {
			heroMap.put(entry.getKey(), ObjectUtil.equal(entry.getKey(), TYPE) ? entry.getValue() :
					Integer.valueOf((String) entry.getValue()));
		});


		/// "consumeexpbooks" request.getBooksCount() identifier response

		return null;
	}
}
