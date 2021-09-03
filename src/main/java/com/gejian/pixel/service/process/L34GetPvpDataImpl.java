package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
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
public class L34GetPvpDataImpl implements Process<CommGetPvpDataRequestProtobuf.CommGetPvpDataRequest
		, CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse doProcess(CommGetPvpDataRequestProtobuf.CommGetPvpDataRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse.Builder builder =
				CommGetPvpDataResponseProtobuf.CommGetPvpDataResponse.newBuilder();

		int tiantiDataExpected = request.getTiantiDataExpected();
		int agreeBuyPvpTimes = request.getAgreeBuyPvpTimes();
		Integer vip = Helper.itemCount(redisTemplate, identifier, "vip");

		// TODO 获取VIP数据
		ConstVipTableItemExProtobuf.ConstVipTableItemEx vipTableItemEx =
				ConstVipTableProtobuf.ConstVipTable.getDefaultInstance().getItems(vip);


		if (tiantiDataExpected == 1) {
			Integer tiantiChallageTimes = Helper.itemCount(redisTemplate, identifier, "tianti_challage_times");

			if (tiantiChallageTimes >= vipTableItemEx.getTianti()) {

				if (agreeBuyPvpTimes == 1) {

					PlayerItemProtobuf.PlayerItem playerItem =
							Helper.decreaseItemValue(redisTemplate, identifier, "stone", (long) vipTableItemEx.getTiantiReset());

					if (playerItem == null) {
						return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_STONE).build();
					}

					Helper.setItemValue(redisTemplate, identifier.toString(), "tianti_challage_times", 1);

				} else {
					return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_PVP_TIMES).build();
				}
			}
		} else {

			Integer tiantiChallageTimes = Helper.itemCount(redisTemplate, identifier, "tianti_challage_times");

			if (tiantiChallageTimes >= vipTableItemEx.getChanllege()) {

				if (agreeBuyPvpTimes == 1) {

					// TODO 获取VIP刷新数据
					ConstPvpRefreshTableItemExProtobuf.ConstPvpRefreshTableItemEx pvpRefreshTableItemEx =
							ConstPvpRefreshTableProtobuf.ConstPvpRefreshTable.getDefaultInstance().getItems(0);

					PlayerItemProtobuf.PlayerItem playerItem =
							Helper.decreaseItemValue(redisTemplate, identifier, "stone", (long) pvpRefreshTableItemEx.getConsumeFomula().getStone());

					if (playerItem == null) {
						return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_STONE).build();
					}

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

			skills.forEach((sk, sv) -> heroBasicInfo.addSkills(HeroSkillProtobuf.HeroSkill.newBuilder().setType(sk.toString()).setLevel(this.parseInt(sv))));

			builder.addHeros(heroBasicInfo);
		});

		return builder.build();
	}

	private String getNicknameKey() {
		return "user:set:nickname";
	}

	private String getAttributesKey(Integer identifier, String s) {
		return "u:" + identifier + ":" + s + ":attributes";
	}

	private String getHeroKey(Integer identifier, String s) {
		return "u:" + identifier + ":" + s;
	}

	private String getTeamsKey(Integer identifier) {
		return "u:" + identifier + ":teams";
	}

	private String getHerosKey(Integer identifier) {
		return "u:" + identifier + ":heros";
	}

	private String getSkillsKey(Integer identifier, String s) {
		return "u:" + identifier + ":" + s + ":skills";
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
