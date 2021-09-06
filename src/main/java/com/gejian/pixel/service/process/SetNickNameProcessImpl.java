package com.gejian.pixel.service.process;

import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.NicknameRedisKeyConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.init.IllegalityVocabularyDataInit;
import com.gejian.pixel.utils.BroadcastUtil;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Hyb
 * @date : 2021/9/1 10:46
 * @description: 设置昵称请求
 */
@Service(CommandConstants.SET_NICKNAME)
@Slf4j
public class SetNickNameProcessImpl implements Process<CommSetNicknameRequestProtobuf.CommSetNicknameRequest
		, CommSetNicknameResponseProtobuf.CommSetNicknameResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private IllegalityVocabularyDataInit illegalityVocabularyDataInit;

	private static final String NICK_NAME = "nickname";

	private static final String POWER = "power";

	private static final String HONOR = "honor";

	private static final String TOTAL_HONOR = "total_honor";

	private static final String KINGOFPVP = "kingofpvp";

	private static final String TOTAL_STONE_PURCHASED = "total_stone_purchased";

	@Override
	public CommSetNicknameResponseProtobuf.CommSetNicknameResponse doProcess(CommSetNicknameRequestProtobuf.CommSetNicknameRequest commSetNicknameRequest) throws Exception {

		CommSetNicknameResponseProtobuf.CommSetNicknameResponse.Builder response = CommSetNicknameResponseProtobuf.CommSetNicknameResponse.newBuilder();

		String nickname = commSetNicknameRequest.getNickname();

		if (illegalityVocabularyDataInit.isLegal(nickname)) {
			return response.setResult(ErrorEnum.ERROR_INVALID_NICKNAME).build();
		}

		// 用户身份ID
		Integer identifier = UserHolder.get().getIdentifier();

		String hexNickname = Helper.hexEncode(nickname);

		redisTemplate.opsForHash().putIfAbsent(NicknameRedisKeyConstants.USER_SET_NICKNAME_CLEARTEXT, nickname, identifier);

		if (!redisTemplate.opsForHash().putIfAbsent(NicknameRedisKeyConstants.USER_SET_NICKNAME, hexNickname, identifier)) {
			return response.setResult(ErrorEnum.ERROR_NICKNAME_ALREADY_EXISTS).build();
		}

		redisTemplate.opsForHash().put(StrUtil.format(RedisKeyConstants.USER_STRINGS, identifier), NICK_NAME, hexNickname);

		updateRanklistPower(identifier);
		updateRanklistHonor(identifier);
		updateRanklistRich(identifier);

		return response.setResult(ErrorEnum.ERROR_SUCCESS).build();
	}


	private String StringValue(Integer identifier) {
		Object result = redisTemplate.opsForHash().get(StrUtil.format(RedisKeyConstants.USER_STRINGS, identifier), NICK_NAME);
		return null != result ? Helper.hexEncode((String) result) : null;
	}

	private void updateRanklistPower(Integer identifier) {
		String nickname = StringValue(identifier);

		long omyRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_POWER, NICK_NAME);

		List<Long> powers = (List<Long>) redisTemplate.opsForHash().entries(StrUtil.format(RedisKeyConstants.USER_HEARO, identifier))
				.values().stream().sorted().collect(Collectors.toList());

		int totalPower = 0;

		for (int i = 0; i < (powers.size() < 5 ? powers.size() : 5); i++) {
			totalPower += powers.get(i);
		}

		totalPower = (totalPower / 100);

		Helper.setItemValue(redisTemplate, String.valueOf(identifier), POWER, totalPower);

		redisTemplate.opsForZSet().add(NicknameRedisKeyConstants.RANKLIST_POWER, Helper.hexEncode(StringValue(identifier)), totalPower);

		long myRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_POWER, Helper.hexEncode(StringValue(identifier)));

		if (myRank != omyRank && myRank <= 100 && StringValue(identifier) != null) {
			Helper.boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + StringValue(identifier) + "</color>战力榜提升至第" + myRank + 1 + "名"
			);
		}
	}

	private void updateRanklistHonor(Integer identifier) {

		long omyRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_HONOR, Helper.hexEncode(StringValue(identifier)));

		redisTemplate.opsForZSet().add(NicknameRedisKeyConstants.RANKLIST_HONOR, Helper.hexEncode(StringValue(identifier)), Helper.itemCount(redisTemplate, identifier, TOTAL_HONOR));

		long myRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_HONOR, Helper.hexEncode(StringValue(identifier)));

		if (omyRank != myRank && myRank <= 100 && ObjectUtils.isEmpty(StringValue(identifier))) {
			Helper.boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + StringValue(identifier) + "</color>荣誉榜提升至第" + myRank + 1 + "名");
		} else {

			Object archives = redisTemplate.opsForHash().get(StrUtil.format(NicknameRedisKeyConstants.USER_ARCHIVES, identifier), KINGOFPVP);

			if (null == archives) {
				redisTemplate.opsForHash().put(StrUtil.format(NicknameRedisKeyConstants.USER_ARCHIVES, identifier), KINGOFPVP, myRank + 1);
			} else {
				if ((myRank + 1) < (long) archives) {
					archives = myRank + 1;
					redisTemplate.opsForHash().put(StrUtil.format(NicknameRedisKeyConstants.USER_ARCHIVES, identifier), KINGOFPVP, archives);
				}
			}
		}
	}

	private void updateRanklistRich(Integer identifier) {

		long omyRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_HONOR, Helper.hexEncode(StringValue(identifier)));

		redisTemplate.opsForZSet().add(NicknameRedisKeyConstants.RANKLIST_RICH, Helper.hexEncode(StringValue(identifier)), Helper.itemCount(redisTemplate, identifier, TOTAL_STONE_PURCHASED));

		long myRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_RICH, Helper.hexEncode(StringValue(identifier)));

		if (myRank != omyRank && myRank <= 100 && StringValue(identifier) != null) {
			Helper.boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + StringValue(identifier) + "</color>战力榜提升至第" + myRank + 1 + "名"
			);
		}

	}


}
