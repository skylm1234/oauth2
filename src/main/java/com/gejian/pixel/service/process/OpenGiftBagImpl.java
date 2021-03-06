package com.gejian.pixel.service.process;

import cn.hutool.core.text.StrFormatter;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.DropService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ζιη€Όε
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.OPEN_GIFTBAG)
public class OpenGiftBagImpl implements Process<CommOpenGiftbagRequestProtobuf.CommOpenGiftbagRequest
		, CommOpenGiftbagResponseProtobuf.CommOpenGiftbagResponse> {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private DropService dropService;

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

		PlayerInfoProtobuf.PlayerInfo playerInfo = dropService.dropItem(this.parseString(giftBag.get("action")), identifier, false, this.parseString(giftBag.get("action")));
		builder.addAllHeros(playerInfo.getHerosList());
		builder.addAllItems(playerInfo.getItemsList());
		builder.addAllTeams(playerInfo.getTeamsList());
		builder.addAllArchives(playerInfo.getArchivesList());
		builder.addAllTeamsPvp(playerInfo.getTeamsPvpList());

		this.redisTemplate.delete(giftBagKey);

		this.redisTemplate.opsForHash().delete(giftBagsKey,request.getIdentifier());

		Long size = this.redisTemplate.opsForHash().size(giftBagsKey);

		PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, this.parseString(identifier), "giftbags", size.intValue());
		builder.addItems(playerItem);

		builder.clearTeams();

		String teamsKey = this.getTeamsKey(identifier);

		Map teams = this.redisTemplate.opsForHash().entries(teamsKey);

		teams.forEach((k, v) -> builder.addTeams(
				PlayerItemProtobuf.PlayerItem.newBuilder().setKey(this.parseString(k))
						.setValue(this.parseInt(v))));

		return builder.setRequest(request).build();
	}

	private String getGiftBagsKey(Integer identifier) {
		return StrFormatter.format(RedisKeyConstants.USER_GIFTBAGS,identifier);
	}

	private String getTeamsKey(Integer identifier) {
		return StrFormatter.format(RedisKeyConstants.USER_TEAMS,identifier);
	}

	private String getGiftBagKey(Integer identifier, String s) {
		return StrFormatter.format(RedisKeyConstants.USER_GIFTBAG,identifier,s);
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
