package com.gejian.pixel.schedule;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.RanklistHonor;
import com.gejian.pixel.service.RanklistHonorService;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ljb
 * @date 2021年09月10日 11:20
 * @description 每日任务刷新
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleTaskDaily {

	private final RedisTemplate redisTemplate;

	private final RanklistHonorService ranklistHonorService;

	@Scheduled(cron = "0 0 0 * * ?")
	public void scheduleTaskDaily(){
		RedisSerializer<String> serializer = redisTemplate.getStringSerializer();

		Map<byte[],byte[]> items = new HashMap<>();
		items.put(serializer.serialize("buy_hero_1_times"), serializer.serialize("0"));
		items.put(serializer.serialize("buy_hero_2_times"), serializer.serialize("0"));
		items.put(serializer.serialize("buy_hero_3_times"), serializer.serialize("0"));
		items.put(serializer.serialize("pvp_1_vectory"), serializer.serialize("0"));
		items.put(serializer.serialize("pvp_3_vectory"), serializer.serialize("0"));
		items.put(serializer.serialize("pvp_9_vectory"), serializer.serialize("0"));

		Map<byte[],byte[]> archives = new HashMap<>();
		archives.put(serializer.serialize("daily_skill_upgrade"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_monster_kill"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_pvp_times"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_exp_book_consume"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_buy_hero"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_type_1_monster_kill"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_type_2_monster_kill"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_type_3_monster_kill"), serializer.serialize("0"));
		archives.put(serializer.serialize("daily_store_buy_times"), serializer.serialize("0"));

		Map<byte[],byte[]> strings = new HashMap<>();
		strings.put(serializer.serialize("finished_daily_promotions"), serializer.serialize(Helper.hexEncode("{}")));

		int maxPlayerId = NumberUtil.parseInt(String.valueOf(redisTemplate.opsForValue().get(RedisKeyConstants.USER_MAX_PLAYER_IDENTIFIER)));
		//用户是否存在集合
		List existsList = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			for (int identifier = 1; identifier <= maxPlayerId; identifier++) {
				connection.keyCommands().exists(serializer.serialize(StrFormatter.format(RedisKeyConstants.USER_ITEMS,identifier)));
			}
			return null;
		});

		redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			for (int i = 0; i < existsList.size(); i++) {
				if (BooleanUtil.toBoolean(existsList.get(i).toString())) {
					Integer identifier = i+1;
					connection.hashCommands().hMSet(serializer.serialize(StrFormatter.format(RedisKeyConstants.USER_ITEMS,identifier)),items);
					connection.hashCommands().hMSet(serializer.serialize(StrFormatter.format(RedisKeyConstants.USER_STRINGS)),strings);
					connection.hashCommands().hMSet(serializer.serialize(StrFormatter.format(RedisKeyConstants.USER_ARCHIVES,identifier)),archives);
				}
			}
			return null;
		});

		log.info("每日任务已刷新,当前时间:{}", DateUtil.now());
	}

	public void awardRanklist(){
		/*

        for i in 0...RUBY_CONST_RANKLIST_HONOR_TABLE.length - 1 do

            award = RUBY_CONST_RANKLIST_HONOR_TABLE[i]['award_fomula']
            for j in (RUBY_CONST_RANKLIST_HONOR_TABLE[i]['section_fomula'] - 1)..(RUBY_CONST_RANKLIST_HONOR_TABLE[i + 1]['section_fomula'] - 2) do
                if ranklist_honor.length > j then
                    if j >= 1000 then
                        har[j] = [nicknames[ranklist_honor[j]], award]
                    end
                else
                    break
                end
            end

        end

        puts har


        lambda do |ar|


        end
		 */
		int max = NumberUtil.parseInt(String.valueOf(redisTemplate.opsForValue().get(RedisKeyConstants.USER_MAX_PLAYER_IDENTIFIER)));

		Map nicknames = redisTemplate.opsForHash().entries(RedisKeyConstants.USER_SET_NICKNAME);

		Set ranklistHonor = redisTemplate.opsForZSet().reverseRange(StrFormatter.format(RedisKeyConstants.RANKLIST,"honor"), 1, max);
		Set ranklistPower = redisTemplate.opsForZSet().reverseRange(StrFormatter.format(RedisKeyConstants.RANKLIST,"power"), 1, max);
		Set ranklistRich = redisTemplate.opsForZSet().reverseRange(StrFormatter.format(RedisKeyConstants.RANKLIST,"rich"), 1, max);

		String[] har = {};
		String[] par = {};
		String[] rar = {};

		List<RanklistHonor> ranklistHonors = ranklistHonorService.list();

		for (int i = 0; i < ranklistHonors.size(); i++) {
			RanklistHonor honor = ranklistHonors.get(i);
			RanklistHonor nextHonor = ranklistHonors.get(i + 1);
			String awardFomula = honor.getAwardFomula();
			for (int j = honor.getSectionFomula()-1; j < nextHonor.getSectionFomula()-2; j++) {
				if (ranklistHonor.size()>j) {
					if (j>=1000) {
						// TODO: 2021/9/10
					}
				}
			}
		}
	}
}
