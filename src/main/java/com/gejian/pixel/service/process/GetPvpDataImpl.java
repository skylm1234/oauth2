package com.gejian.pixel.service.process;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.NumberUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.PvpRefreshService;
import com.gejian.pixel.service.VipService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 获取 PVP 数据
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.GET_PVP_DATA)
public class GetPvpDataImpl implements Process<CommGetPvpDataRequestProtobuf.CommGetPvpDataRequest
		, CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private VipService vipService;

	@Autowired
	private PvpRefreshService pvpRefreshService;

	@Override
	public CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse doProcess(CommGetPvpDataRequestProtobuf.CommGetPvpDataRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse.Builder builder =
				CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse.newBuilder();

		int tiantiDataExpected = request.getTiantiDataExpected();
		int agreeBuyPvpTimes = request.getAgreeBuyPvpTimes();
		Integer vip = Helper.itemCount(redisTemplate, identifier, "vip");

		// 获取VIP数据
		ConstVipTableItemExProtobuf.ConstVipTableItemEx vipTableItemEx =
				vipService.getItem(vip+1);

		if (tiantiDataExpected == 1) {
			Integer tiantiChallageTimes = Helper.itemCount(redisTemplate, identifier, "tianti_challage_times");

			if (tiantiChallageTimes >= vipTableItemEx.getTianti()) {

				if (agreeBuyPvpTimes == 1) {

					PlayerItemProtobuf.PlayerItem playerItem =
							Helper.decreaseItemValue(redisTemplate, identifier, "stone", (long) vipTableItemEx.getTiantiReset());
					if (playerItem==null) {
						return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_STONE).build();
					}
					builder.addItems(playerItem);

					PlayerItemProtobuf.PlayerItem playerItem1 =
							Helper.setItemValue(redisTemplate, this.parseString(identifier), "tianti_challage_times", 1);
					builder.addItems(playerItem1);

				} else {
					return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_PVP_TIMES).build();
				}
			}
		} else {

			Integer pvpChallageTimes = Helper.itemCount(redisTemplate, identifier, "pvp_challage_times");

			if (pvpChallageTimes >= vipTableItemEx.getChanllege()) {

				if (agreeBuyPvpTimes == 1) {

					// 获取VIP刷新数据
					ConstPvpRefreshTableItemExProtobuf.ConstPvpRefreshTableItemEx pvpRefreshTableItemEx =
							pvpRefreshService.getItem(1);

					PlayerItemProtobuf.PlayerItem playerItem =
							Helper.decreaseItemValue(redisTemplate, identifier, "stone", (long) pvpRefreshTableItemEx.getConsumeFomula().getStone());
					if (playerItem==null) {
						return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_STONE).build();
					}
					builder.addItems(playerItem);

				} else {
					return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_PVP_TIMES).build();
				}
			}
		}

		String nicknameKey = this.getNicknameKey();

		String nickName = this.parseString(this.redisTemplate.opsForHash().get(nicknameKey, Helper.hexEncode(request.getIdentifier())));

		if (nickName == null) {
			return builder.setResult(ErrorEnum.ERROR_PLAYER_NOT_FOUND).build();
		}

		Integer id = this.parseInt(nickName);

		String teamsKey = this.getTeamsKey(id);

		Map teams = this.redisTemplate.opsForHash().entries(teamsKey);

		teams.forEach((k, v) -> {

			String attributesKey = this.getAttributesKey(id, this.parseString(k));

			Map hero = this.redisTemplate.opsForHash().entries(attributesKey);

			HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBasicInfo = HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder()
					.setId(this.parseInt(hero.get("id")))
					.setType(this.parseString(hero.get("type")))
					.setLevel(this.parseInt(hero.get("level")))
					.setExp(this.parseInt(hero.get("exp")))
					.setQuality(this.parseInt(hero.get("quality")))
					.setStar(this.parseInt(hero.get("star")))
					.setGrowHp(this.parseInt(hero.get("grow_hp")))
					.setHp(this.parseInt(hero.get("hp")))
					.setGrowDef(this.parseInt(hero.get("grow_def")))
					.setDef(this.parseInt(hero.get("def")))
					.setGrowAttack(this.parseInt(hero.get("grow_attack")))
					.setAttack(this.parseInt(hero.get("attack")))
					.setGrowSpeed(this.parseInt(hero.get("grow_speed")))
					.setSpeed(this.parseInt(hero.get("speed")))
					.setNumber(this.parseInt(v));

			String skillsKey = this.getSkillsKey(id, this.parseString(k));

			Map skills = this.redisTemplate.opsForHash().entries(skillsKey);

			skills.forEach((sk, sv) -> heroBasicInfo.addSkills(HeroSkillProtobuf.HeroSkill.newBuilder().setType(this.parseString(sk)).setLevel(this.parseInt(sv))));

			builder.addHeros(heroBasicInfo);
		});

		PlayerItemProtobuf.PlayerItem dailyPvpTimes = Helper.onNotifyEventOfPromotions(redisTemplate, "daily_pvp_times", 1, identifier);
		builder.addArchives(dailyPvpTimes);
		PlayerItemProtobuf.PlayerItem pvpChallageTimes = Helper.increaseItemValue(redisTemplate, identifier, "pvp_challage_times", 1L);
		builder.addItems(pvpChallageTimes);

		return builder.setRequest(request).build();
	}

	private String getNicknameKey() {
		return RedisKeyConstants.USER_SET_NICKNAME;
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
		return o == null ? 0L : NumberUtil.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}

	public String parseString(Object o) {
		return o == null ? "" : o.toString();
	}
}
