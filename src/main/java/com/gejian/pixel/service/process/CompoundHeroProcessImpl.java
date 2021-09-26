package com.gejian.pixel.service.process;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ReUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.Hero;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.HeroService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljb
 * @date 2021年09月03日 9:51
 * @description 碎片召唤英雄
 */
@Service(CommandConstants.COMPOUND_HERO)
@Slf4j
@RequiredArgsConstructor
public class CompoundHeroProcessImpl implements Process<CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest,
		CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse> {

	private final RedisTemplate redisTemplate;

	private final HeroService heroService;

	@Override
	public CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse doProcess(CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest request) throws Exception {
		CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse.Builder replyBuilder = CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		if (redisTemplate.opsForHash().hasKey(StrFormatter.format(RedisKeyConstants.USER_HEROS,identifier), request.getHero())) {
			log.error("FAILED: {}={}:{}",identifier, Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
			replyBuilder.setResult(ErrorEnum.ERROR_HERO_ALREADY_EXISTS);
		}else {
			if (ReUtil.isMatch("^hero_(\\d+)$", request.getHero())) {
				String[] heroSplit = request.getHero().split("_");
				Integer id = Integer.valueOf(heroSplit[1]);
				Hero cell = heroService.getById(id);
				if (cell == null) {
					log.info("FAILED: %d=>%s:%d", identifier, Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
					replyBuilder.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND);
				}else {
					if (decreaseItemValue(identifier, "private_soulchip_"+id, Long.valueOf(cell.getChips()), replyBuilder)) {
						PlayerInfoProtobuf.PlayerInfo playerInfo = Helper.awardHeroForMe(redisTemplate, identifier, request.getHero(), null);
						List<HeroBasicInfoProtobuf.HeroBasicInfo> herosList = playerInfo.getHerosList();
						List<PlayerItemProtobuf.PlayerItem> itemsList = playerInfo.getItemsList();
						List<PlayerItemProtobuf.PlayerItem> teamsList = playerInfo.getTeamsList();
						List<PlayerItemProtobuf.PlayerItem> teamsPvpList = playerInfo.getTeamsPvpList();
						if (herosList.size()>0) {
							replyBuilder.addAllHeros(herosList);
						}
						if (itemsList.size()>0) {
							replyBuilder.addAllItems(itemsList);
						}
						if (teamsList.size()>0) {
							replyBuilder.addAllTeams(teamsList);
						}
						if (teamsPvpList.size()>0) {
							replyBuilder.addAllTeamsPvp(teamsPvpList);
						}
					}else {
						log.error("FAILED: {}=>{}:{}", identifier, Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
						replyBuilder.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND);
					}
				}
			}
		}
		return replyBuilder.build();
	}

	private boolean decreaseItemValue(Integer identifier, String name, Long delta, CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse.Builder reply) {
		delta = -1*delta;
		PlayerItemProtobuf.PlayerItem.Builder builder = PlayerItemProtobuf.PlayerItem
				.newBuilder()
				.setKey(name);
		Object result = redisTemplate.opsForHash().increment(StrFormatter.format(RedisKeyConstants.USER_ITEMS,identifier), name, delta);
		long current = Long.parseLong(result + "");
		if (current < 0) {
			Long increment = redisTemplate.opsForHash().increment(StrFormatter.format(RedisKeyConstants.USER_ITEMS,identifier), name, delta * -1);
			builder.setValue(increment);
			PlayerItemProtobuf.PlayerItem item = builder.build();
			reply.addItems(item);
			return false;
		}else {
			builder.setValue(current);
			PlayerItemProtobuf.PlayerItem item = builder.build();
			reply.addItems(item);
			return true;
		}
	}
}
