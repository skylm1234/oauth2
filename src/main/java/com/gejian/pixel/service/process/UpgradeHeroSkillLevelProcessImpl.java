package com.gejian.pixel.service.process;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.UpgradeConsumeFomula;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommUnlockHeroSkillResponseProtobuf;
import com.gejian.pixel.proto.CommUpgradeHeroSkillLevelRequestProtobuf;
import com.gejian.pixel.proto.CommUpgradeHeroSkillLevelResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.SkillService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author tangtao
 * @since 2021/9/7
 */
@Service(CommandConstants.UPGRADE_HERO_SKILL_LEVEL)
@Slf4j
public class UpgradeHeroSkillLevelProcessImpl implements Process<CommUpgradeHeroSkillLevelRequestProtobuf.CommUpgradeHeroSkillLevelRequest
		, CommUpgradeHeroSkillLevelResponseProtobuf.CommUpgradeHeroSkillLevelResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private SkillService skillService;

	@Override
	public CommUpgradeHeroSkillLevelResponseProtobuf.CommUpgradeHeroSkillLevelResponse doProcess(
			CommUpgradeHeroSkillLevelRequestProtobuf.CommUpgradeHeroSkillLevelRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommUpgradeHeroSkillLevelResponseProtobuf.CommUpgradeHeroSkillLevelResponse.Builder result
				= CommUpgradeHeroSkillLevelResponseProtobuf.CommUpgradeHeroSkillLevelResponse.newBuilder()
				.setRequest(request).setResult(1);
		String hero = request.getHero();
		if (!redisTemplate.hasKey(String.format("u:%s:%s:attributes", identifier, hero))) {
			result.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND);
			return result.build();
		}
		Map<String, Object> skillMap = redisTemplate.opsForHash().entries(String.format("u:%s:%s:skills", identifier, hero));
		String skill = request.getSkill();
		if (!skillMap.containsKey(skill)) {
			return result.setResult(ErrorEnum.ERROR_SKILL_NOT_FOUND).build();
		}

		if ((int) skillMap.get(skill) == 0) {
			return result.setResult(ErrorEnum.ERROR_SKILL_NOT_UNLOCK_YET).build();
		}

		if ((int) skillMap.get(skill) >= 10) {
			return result.setResult(ErrorEnum.ERROR_REACH_LIMIT).build();
		}

		UpgradeConsumeFomula upgradeConsumeFomula = JSONUtil.toBean(skillService.getById(skill).getUpgradeConsumeFomula()
				, UpgradeConsumeFomula.class);
		if (Objects.isNull(upgradeConsumeFomula)) {
			return result.setResult(ErrorEnum.ERROR_UNKNOW_SKILL).build();
		}

		Integer goldCount = Helper.itemCount(redisTemplate, identifier, "gold");
		Integer skillBookCount = Helper.itemCount(redisTemplate, identifier, String.format("book_%s", skill));
		Integer commonCount = Helper.itemCount(redisTemplate, identifier, "book_skill_0000");
		Integer skillNeedGold = upgradeConsumeFomula.getGold();
		Integer skillNeedBook = upgradeConsumeFomula.getBook();
		if (goldCount >= skillNeedGold
				&& (skillBookCount >= skillNeedBook
				|| (skillBookCount + commonCount) >= skillNeedBook)) {

			Helper.decreaseItemValue(redisTemplate, identifier, "gold", Long.valueOf(skillNeedGold));
			if (skillBookCount >= skillNeedBook) {
				Helper.decreaseItemValue(redisTemplate, identifier, "book", Long.valueOf(skillNeedBook));
			} else {
				Helper.decreaseItemValue(redisTemplate, identifier, "book", Long.valueOf(skillBookCount));
				Helper.decreaseItemValue(redisTemplate, identifier, "book_skill_0000", (long)(skillNeedBook - skillBookCount));
			}
			Long level = redisTemplate.opsForHash().increment(String.format("u:%s:%s:skills", identifier, hero), skill, 1);
			if (level > 10) {
				Helper.onNotifyEventOfPromotions(redisTemplate, "maxtopskills", 1, identifier);
			}
			Helper.onNotifyEventOfPromotions(redisTemplate, "daily_skill_upgrade", 1, identifier);
		}else{
			return result.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES).build();
		}
		return result.build();
	}

}
