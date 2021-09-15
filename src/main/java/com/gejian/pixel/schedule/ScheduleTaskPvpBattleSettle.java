package com.gejian.pixel.schedule;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.entity.PvpAward;
import com.gejian.pixel.proto.CommWorldEventUpdateProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.gejian.pixel.service.PvpAwardService;
import com.gejian.pixel.utils.BroadcastUtil;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljb
 * @date 2021年09月10日 11:50
 * @description PVP竞技场刷新	2个小时刷新一次
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleTaskPvpBattleSettle {

	private final RedisTemplate redisTemplate;

	private final PvpAwardService pvpAwardService;

	@Scheduled(cron = "0 0 */2 * * ?")
	public void refreshPvpBattleSettle(){
		scheduleTaskPvpBattleSettle();
		CommWorldEventUpdateProtobuf.CommWorldEventUpdate event = CommWorldEventUpdateProtobuf.CommWorldEventUpdate
				.newBuilder()
				.setType(1)
				.setInfo("竞技场已经刷新")
				.setFoo("\\0\\0\\0\\0")
				.setBar("\\0\\0\\0")
				.build();
		MessageBaseProtobuf.MessageBase base = MessageBaseProtobuf.MessageBase
				.newBuilder()
				.setName("COMM_WORLD_EVENT_UPDATE")
				.setData(event.toByteString())
				.build();
		BroadcastUtil.broadcast(base);
	}

	public void scheduleTaskPvpBattleSettle(){
		Map<Integer, PvpAward> awardHash = pvpAwardService.getHash();
		Integer maxPlayerId = NumberUtil.parseInt(redisTemplate.opsForValue().get("user:max:player_identifier")+"");
		// TODO: 2021/9/10 源代码是从1000开始的
		for (int identifier = 1; identifier <= maxPlayerId; identifier++) {
			if (redisTemplate.hasKey("u:"+identifier+":items")) {
				List<String> itemsKeys = Arrays.asList("pvp_1_vectory", "pvp_3_vectory", "pvp_9_vectory", "pvp_vectory_times", "pvp_challage_times", "should_refresh_pvp_chanllege_ranklist");
				List itemsValue = redisTemplate.opsForHash().multiGet("u:" + identifier + ":items", itemsKeys);

				Map<String, Integer> items = new HashMap<>();
				for (int i = 0; i < itemsKeys.size(); i++) {
					items.put(itemsKeys.get(i), NumberUtil.parseInt(itemsValue.get(i)==null?"0":String.valueOf(itemsValue.get(i))));
				}

				if (items.get("pvp_vectory_times")>0) {
					if (items.get("pvp_vectory_times")>=9) {
						PvpAward x3 = awardHash.get(3);
						JSONObject awardFomulaObj = JSONUtil.parseObj(x3.getAwardFomula());
						awardCall(identifier, "PVP战场礼包（高级）", items.get("pvp_9_vectory")>0?String.valueOf(awardFomulaObj.get("other")):String.valueOf(awardFomulaObj.get("firstofday")), "gift_package.png");
						items.put("pvp_9_vectory", items.get("pvp_9_vectory")+1);
					}else if (items.get("pvp_vectory_times")>=3) {
						PvpAward x2 = awardHash.get(2);
						JSONObject awardFomulaObj = JSONUtil.parseObj(x2.getAwardFomula());
						awardCall(identifier, "PVP战场礼包（中级）", items.get("pvp_3_vectory")>0?String.valueOf(awardFomulaObj.get("other")):String.valueOf(awardFomulaObj.get("firstofday")), "gift_package.png");
					}else if (items.get("pvp_vectory_times")>=1) {
						PvpAward x1 = awardHash.get(1);
						JSONObject awardFomulaObj = JSONUtil.parseObj(x1.getAwardFomula());
						awardCall(identifier, "PVP战场礼包（初级）", items.get("pvp_1_vectory")>0?String.valueOf(awardFomulaObj.get("other")):String.valueOf(awardFomulaObj.get("firstofday")), "gift_package.png");
					}
				}

				items.put("pvp_vectory_times", 0);
				items.put("pvp_challage_times", 0);
				items.put("should_refresh_pvp_chanllege_ranklist", 1);
				redisTemplate.opsForHash().putAll("u:"+identifier+":items", items);
			}
		}
	}

	private void awardCall(Integer identifier, String desc, String action, String icon){
		Map<String, String> g = new HashMap<>();
		g.put("identifier", Helper.giftbagIdentifier(redisTemplate));
		g.put("icon", Helper.hexEncode(icon));
		g.put("desc", Helper.hexEncode(desc));
		g.put("action", action);

		redisTemplate.opsForHash().putAll("u:"+identifier+":giftbag:"+g.get("identifier"),g);

		Map<String, String> giftbagsDesc = new HashMap<>();
		giftbagsDesc.put(g.get("identifier"),desc);

		redisTemplate.opsForHash().putAll("u:"+identifier+":giftbags",giftbagsDesc);
	}

}
