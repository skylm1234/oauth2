package com.gejian.pixel.service.process;

import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.StageService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:chen
 * @Date: 2021/9/2 17:41
 */
@Service(CommandConstants.ENTER_DUNGEON)
@CommandResponse(CommandConstants.ENTER_DUNGEON)
@Slf4j
public class CommEnterDungeonRequestImpl implements Process<CommEnterDungeonRequestProtobuf.CommEnterDungeonRequest,
		CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse> {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private DropService dropService;
	@Autowired
	private BackpackService backpackService;
	@Autowired
	private StageService stageService;

	@Override
	public CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse doProcess(CommEnterDungeonRequestProtobuf.CommEnterDungeonRequest request) throws Exception {

		CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse.Builder builder = CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse.newBuilder();
		builder.setRequest(request);

		int type = request.getType();
		int stage = request.getStage();
		if (type == 1) {
			if (stage < 1 || stage > 80) {
				return builder.setResult(ErrorEnum.ERROR_INVALID_STAGE).build();
			}
		} else if (type == 2) {
			if (stage < 1001 || stage > 1080) {
				return builder.setResult(ErrorEnum.ERROR_INVALID_STAGE).build();
			}
		} else if (type == 3) {
			if (stage < 2001 || stage > 2080) {
				return builder.setResult(ErrorEnum.ERROR_INVALID_STAGE).build();
			}
		} else {
			return builder.setResult(ErrorEnum.ERROR_INVALID_STAGE).build();
		}

		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();
		PlayerInfoProtobuf.PlayerInfo playerInfo = Helper
				.updateTemporaryBackpack(dropService, stageService, backpackService, redisTemplate, identifier, 0, 0);
		builder.addAllItems(playerInfo.getItemsList());

		Map<String, Object> backpack = new HashMap<>(3);
		backpack.put("type", request.getType());
		backpack.put("stage", request.getStage());
		backpack.put("dungeon_enter_timestamp", Helper.currentTimestamp());

		redisTemplate.opsForHash().putAll(String.format(RedisKeyConstants.USER_TEMP_PACK, identifier), backpack);
		builder.setResult(ErrorEnum.ERROR_SUCCESS);
		return builder.build();
	}
}
