package com.gejian.pixel.service.process;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.Stage;
import com.gejian.pixel.entity.StageBossAwardFomula;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.StageService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : zhouqiang
 * @date :
 */
@Service(CommandConstants.STAGE_PASSED)
@Slf4j
public class StagePassedProcessImpl implements Process<CommStagePassedRequestProtobuf.CommStagePassedRequest,
		CommStagePassedResponseProtobuf.CommStagePassedResponse> {


	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StageService stageService;

	@Autowired
	private DropService dropService;



	@Override
	public CommStagePassedResponseProtobuf.CommStagePassedResponse
	doProcess(CommStagePassedRequestProtobuf.CommStagePassedRequest request) throws Exception {
		CommStagePassedResponseProtobuf.CommStagePassedResponse.Builder response = CommStagePassedResponseProtobuf
				.CommStagePassedResponse.newBuilder();
		Stage stage = stageService.getById(request.getStage());
		if (Objects.isNull(stage)){
			response.setResult(ErrorEnum.ERROR_INVALID_STAGE);
			return response.build();
		}
		Integer identifier = UserHolder.get().getIdentifier();
		String name = StrUtil.format("dungeon_{}_not_passed_stage",request.getType());
		Integer count = Helper.itemCount(redisTemplate, identifier,
				name);
		if (request.getStage() != count){
			response.setResult(ErrorEnum.ERROR_INVALID_STAGE);
			return response.build();
		}
		PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, String.valueOf(identifier), name, request.getStage() + 1);
		response.addItems(playerItem);
		String bossAwardFomula = stage.getBossAwardFomula();
		StageBossAwardFomula stageBossAwardFomula = JSONUtil.toBean(bossAwardFomula,
				StageBossAwardFomula.class);
		String dropid = stageBossAwardFomula.getDropid();
		PlayerInfoProtobuf.PlayerInfo playerInfo = dropService.dropItem(dropid, identifier, false, null);
		response.addAllArchives(playerInfo.getArchivesList())
				.addAllItems(playerInfo.getItemsList())
				.addAllHeros(playerInfo.getHerosList());
		String key = StrUtil.format("type_{}_boss_kill",request.getType());
		PlayerItemProtobuf.PlayerItem item = Helper
				.onNotifyEventOfPromotions(redisTemplate, key, request.getStage(), identifier);
		response.addArchives(item);
		String[] types = {"普通", "噩梦", "折磨"};
		String nickname = Helper.stringValue(redisTemplate, identifier, "nickname");
		if (StrUtil.isNotBlank(nickname) && request.getStage() % 1000 >= 50){
			Helper.boardcaseWorldEvent(StrUtil.format("PWBC快讯：祝贺玩家<color=red>{}</color>" +
					"顺利通过<color=red>{}难度第{}关</color>！",nickname,types[request.getType()-1],
					request.getStage() % 1000));
		}
		return response.build();
	}
}
