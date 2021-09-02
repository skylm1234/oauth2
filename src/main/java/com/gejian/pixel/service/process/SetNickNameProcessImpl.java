package com.gejian.pixel.service.process;

import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.NicknameRedisKeyConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.proto.CommSetNicknameRequestProtobuf;
import com.gejian.pixel.proto.CommSetNicknameResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.init.IllegalityVocabularyDataInit;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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


	private void updateRanklistPower(Integer identifier) {
		String nickname;

		Object result = redisTemplate.opsForHash().get(StrUtil.format(RedisKeyConstants.USER_STRINGS, identifier), NICK_NAME);
		if (null != result) {
			nickname = Helper.hexEncode((String) result);
		}

		Long myRank = redisTemplate.opsForZSet().reverseRank(NicknameRedisKeyConstants.RANKLIST_POWER, NICK_NAME);

		List<Long> powers = (List<Long>) redisTemplate.opsForHash().entries(StrUtil.format(RedisKeyConstants.USER_HEARO, identifier))
				.values();



	}

	private void updateRanklistHonor(Integer identifier) {

	}

	private void updateRanklistRich(Integer identifier) {

	}

}
