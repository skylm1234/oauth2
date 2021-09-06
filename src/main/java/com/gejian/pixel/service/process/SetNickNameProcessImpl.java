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

		Helper.updateRanklistPower(redisTemplate, identifier);
		Helper.updateRanklistHonor(redisTemplate, identifier);
		Helper.updateRanklistRich(redisTemplate, identifier);

		return response.setResult(ErrorEnum.ERROR_SUCCESS).setRequest(commSetNicknameRequest).build();
	}

}
