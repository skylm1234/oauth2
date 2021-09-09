package com.gejian.pixel.service.process;

import cn.hutool.core.util.ReUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf;
import com.gejian.pixel.proto.CommCompoundHeroResponseProtobuf;
import com.gejian.pixel.proto.PlayerInfoProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
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
 * @date 2021年09月03日 9:51
 * @description 混合英雄
 */
@Service(CommandConstants.COMPOUND_HERO)
@Slf4j
@RequiredArgsConstructor
public class CompoundHeroProcessImpl implements Process<CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest,
		CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse> {

	private final RedisTemplate redisTemplate;

	@Override
	public CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse doProcess(CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest request) throws Exception {
		CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse.Builder replyBuilder = CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		if (redisTemplate.opsForHash().hasKey("u:"+identifier+":heros", request.getHero())) {
			log.error("FAILED: {}={}:{}",identifier, Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
			replyBuilder.setResult(ErrorEnum.ERROR_HERO_ALREADY_EXISTS);
		}else {
			if (ReUtil.isMatch("^hero_(\\d+)$", request.getHero())) {
				String[] heroSplit = request.getHero().split("_");
				Integer id = Integer.valueOf(heroSplit[1]);
				// TODO: 2021/9/3 后面需完善
				//cell = RUBY_CONST_HERO_TABLE_HASH["X%d" % id]
				Map<String, Object> cell = new HashMap<>();
				if (cell == null) {
					log.info("FAILED: %d=>%s:%d", identifier, Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
					replyBuilder.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND);
				}else {
					if (decreaseItemValue(redisTemplate, identifier, "private_soulchip_"+id, Long.valueOf(cell.get("chips")+""), replyBuilder)) {
						// 2021/9/3 award_hero_for_me(identifier, request.hero, reply, nil)
						PlayerInfoProtobuf.PlayerInfo playerInfo = Helper.awardHeroForMe(redisTemplate, identifier, request.getHero(), null);
						replyBuilder.addAllHeros(playerInfo.getHerosList());
						replyBuilder.addAllTeams(playerInfo.getItemsList());
						replyBuilder.addAllTeamsPvp(playerInfo.getTeamsPvpList());
					}else {
						log.error("FAILED: {}=>{}:{}", identifier, Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber());
						replyBuilder.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND);
					}
				}
			}
		}
		return replyBuilder.build();
	}

	private boolean decreaseItemValue(RedisTemplate redisTemplate, Integer identifier, String name, Long delta, CommCompoundHeroResponseProtobuf.CommCompoundHeroResponse.Builder reply) {
		delta = -1*delta;
		PlayerItemProtobuf.PlayerItem.Builder builder = PlayerItemProtobuf.PlayerItem
				.newBuilder()
				.setKey(name);
		Object result = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
		long current = Long.parseLong(result + "");
		if (current < 0) {
			Long increment = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta * -1);
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
