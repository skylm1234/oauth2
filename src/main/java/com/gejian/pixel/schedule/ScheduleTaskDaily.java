package com.gejian.pixel.schedule;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.gejian.pixel.entity.RanklistHonor;
import com.gejian.pixel.service.RanklistHonorService;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
		Map<String,Integer> items = new HashMap<>();
		items.put("buy_hero_1_times", 0);
		items.put("buy_hero_2_times", 0);
		items.put("buy_hero_3_times", 0);
		items.put("pvp_1_vectory", 0);
		items.put("pvp_3_vectory", 0);
		items.put("pvp_9_vectory", 0);

		Map<String,Integer> archives = new HashMap<>();
		archives.put("daily_skill_upgrade", 0);
		archives.put("daily_monster_kill", 0);
		archives.put("daily_pvp_times", 0);
		archives.put("daily_exp_book_consume", 0);
		archives.put("daily_buy_hero", 0);
		archives.put("daily_type_1_monster_kill", 0);
		archives.put("daily_type_2_monster_kill", 0);
		archives.put("daily_type_3_monster_kill", 0);
		archives.put("daily_store_buy_times", 0);

		Map<String,String> strings = new HashMap<>();
		strings.put("finished_daily_promotions", Helper.hexEncode("{}"));

		int maxPlayerId = NumberUtil.parseInt(String.valueOf(redisTemplate.opsForValue().get("user:max:player_identifier")));
		// TODO: 2021/9/10 源代码是从1001开始的
		for (int identifier = 1; identifier <= maxPlayerId; identifier++) {
			if (redisTemplate.hasKey("u:"+identifier+":items")) {
				redisTemplate.opsForHash().putAll("u:"+identifier+":items",items);
				redisTemplate.opsForHash().putAll("u:"+identifier+":strings",strings);
				redisTemplate.opsForHash().putAll("u:"+identifier+":archives",archives);
			}
		}
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
		int max = NumberUtil.parseInt(String.valueOf(redisTemplate.opsForValue().get("user:max:player_identifier")));

		Map nicknames = redisTemplate.opsForHash().entries("user:set:nickname");

		Set ranklistHonor = redisTemplate.opsForZSet().reverseRange("ranklist:honor", 1, max);
		Set ranklistPower = redisTemplate.opsForZSet().reverseRange("ranklist:power", 1, max);
		Set ranklistRich = redisTemplate.opsForZSet().reverseRange("ranklist:rich", 1, max);

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
