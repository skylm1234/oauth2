package com.gejian.pixel.service.process;

import cn.hutool.core.text.StrFormatter;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommDismissHeroRequestProtobuf;
import com.gejian.pixel.proto.CommDismissHeroResponseProtobuf;
import com.gejian.pixel.proto.ConstHeroTableItemExProtobuf;
import com.gejian.pixel.service.HeroService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import com.google.protobuf.ProtocolStringList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 解雇英雄
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.DISMISS_HERO)
public class DismissHeroImpl implements Process<CommDismissHeroRequestProtobuf.CommDismissHeroRequest
		, CommDismissHeroResponseProtobuf.CommDismissHeroResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private HeroService heroService;

	@Override
	public CommDismissHeroResponseProtobuf.CommDismissHeroResponse doProcess(CommDismissHeroRequestProtobuf.CommDismissHeroRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();

		ProtocolStringList herosList = request.getHerosList();

		CommDismissHeroResponseProtobuf.CommDismissHeroResponse.Builder builder =
				CommDismissHeroResponseProtobuf.CommDismissHeroResponse.newBuilder();

		for (String s : herosList) {

			String attributesKey = this.getAttributesKey(identifier, s);

			Boolean existsAttributes = this.redisTemplate.hasKey(attributesKey);
			if (existsAttributes == null || !existsAttributes) {
				return builder.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
			}

			String heroKey = this.getHeroKey(identifier, s);
			Map hero = this.redisTemplate.opsForHash().entries(heroKey);

			String teamsKey = this.getTeamsKey(identifier);
			Boolean existsTeams = this.redisTemplate.hasKey(teamsKey);

			if (existsTeams != null && existsTeams) {
				return builder.setResult(ErrorEnum.ERROR_CAN_NOT_DISMISS_HERO_IN_FORMATION).build();
			}

			if (s.startsWith("hero_")) {

				Integer id = this.parseInt(s.substring("hero_".length()));

				// 获取英雄数据
				ConstHeroTableItemExProtobuf.ConstHeroTableItemEx heroTableItemEx =
						this.heroService.getItem(id);
				if (heroTableItemEx == null) {
					return builder.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
				}

				String type = this.parseString(hero.get("type"));

				this.redisTemplate.delete(this.getAttributesKey(identifier, type));
				this.redisTemplate.delete(this.getSkillsKey(identifier, type));
				this.redisTemplate.delete(this.getHerosKey(identifier));

				Helper.increaseItemValue(redisTemplate, identifier, "private_soulchip_" + id, (long) heroTableItemEx.getChips());
			}
		}
		return builder.setRequest(request).build();
	}

	private String getAttributesKey(Integer identifier, String s) {
		return StrFormatter.format(RedisKeyConstants.USER_HERO_ATTRIBUTES,identifier,s);
	}

	private String getHeroKey(Integer identifier, String s) {
		return StrFormatter.format(RedisKeyConstants.USER_HERO,identifier,s);
	}

	private String getTeamsKey(Integer identifier) {
		return StrFormatter.format(RedisKeyConstants.USER_TEAMS,identifier);
	}

	private String getHerosKey(Integer identifier) {
		return StrFormatter.format(RedisKeyConstants.USER_HEROS,identifier);
	}

	private String getSkillsKey(Integer identifier, String s) {
		return StrFormatter.format(RedisKeyConstants.USER_HERO_SKILLS,identifier,s);
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
