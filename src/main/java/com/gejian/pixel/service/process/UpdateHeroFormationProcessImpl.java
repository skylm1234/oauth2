package com.gejian.pixel.service.process;

import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.constants.UpdateHeroFoRedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : Hyb
 * @date : 2021/9/3 14:24
 * @description:
 */
@Service(CommandConstants.UPDATE_HERO_FORMATION)
@Slf4j
public class UpdateHeroFormationProcessImpl implements Process<CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest
		, CommUpdateHeroFormationResponseProtobuf.CommUpdateHeroFormationResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommUpdateHeroFormationResponseProtobuf.CommUpdateHeroFormationResponse doProcess(CommUpdateHeroFormationRequestProtobuf.CommUpdateHeroFormationRequest request) throws Exception {

		List<PlayerItemProtobuf.PlayerItem> formationsList = request.getFormationsList();

		CommUpdateHeroFormationResponseProtobuf.CommUpdateHeroFormationResponse.Builder response = CommUpdateHeroFormationResponseProtobuf.CommUpdateHeroFormationResponse.newBuilder();

		// 用户身份ID
		Integer identifier = UserHolder.get().getIdentifier();

		if (formationsList.size() >= 1 &&
				formationsList.size() <= 5) {
			for (PlayerItemProtobuf.PlayerItem playerItem : formationsList) {
				if (!playerItem.getKey().equals("power")){
					if (!redisTemplate.opsForHash().hasKey(StrUtil.format(RedisKeyConstants.USER_HEROS, identifier), playerItem.getKey())) {
						return response.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
					}
				}
			}
			int type = request.getType();

			if (type == 1) {
				redisTemplate.delete(StrUtil.format(UpdateHeroFoRedisKeyConstants.USER_TEAMS_PVP, identifier));

				HashMap<String, Long> teams = new HashMap<>();

				formationsList.forEach(playerItem -> {
					teams.put(playerItem.getKey(), playerItem.getValue());
				});

				redisTemplate.opsForHash().putAll(StrUtil.format(UpdateHeroFoRedisKeyConstants.USER_TEAMS_PVP, identifier), teams);

			} else {
				redisTemplate.delete(StrUtil.format(UpdateHeroFoRedisKeyConstants.USER_TEAMS_PVP, identifier));
				HashMap<String, Long> teams = new HashMap<>();

				formationsList.forEach(playerItem -> {
					teams.put(playerItem.getKey(), playerItem.getValue());
				});
				redisTemplate.opsForHash().putAll(StrUtil.format(RedisKeyConstants.USER_ITEMS, identifier), teams);
				Helper.updateRanklistPower(redisTemplate, identifier, null);
			}

			return response.setResult(ErrorEnum.ERROR_SUCCESS).build();
		} else {
			return response.setResult(ErrorEnum.ERROR_INVALID_TEAM_SIZE).build();
		}


	}
}
