package com.gejian.pixel.constants;

import cn.hutool.json.JSONArray;
import com.gejian.pixel.utils.Helper;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ljb
 * @date 2021年08月30日 10:14
 * @description 生成的常量
 */
@Component
@Data
@Slf4j
public class Generated {
	private JSONArray RUBY_CONST_SKILL_SCRIPTS_SKILL_TABLE;
	private JSONArray RUBY_CONST_SKILL_SCRIPTS_ROLE_TABLE;
	private JSONArray RUBY_CONST_DROP_TABLE;
	private JSONArray RUBY_CONST_VIP_DAILY_TABLE;
	private JSONArray RUBY_CONST_RANKLIST_HONOR_TABLE;
	private JSONArray RUBY_CONST_RANKLIST_POWER_TABLE;
	private JSONArray RUBY_CONST_RANKLIST_RICH_TABLE;
	private JSONArray RUBY_CONST_BACKPACK_TABLE;
	private JSONArray RUBY_CONST_BUY_HERO_TABLE;
	private JSONArray RUBY_CONST_STAGE_TABLE;
	private JSONArray RUBY_CONST_STAGE_CLASS_TABLE;
	private JSONArray RUBY_CONST_SKILL2_TABLE;
	private JSONArray RUBY_CONST_PROMOTION_TABLE;
	private JSONArray RUBY_CONST_PROMOTION_TYPE_NAME_TABLE;
	private JSONArray RUBY_CONST_NAMED_TABLE;
	private JSONArray RUBY_CONST_SKILL_TABLE;
	private JSONArray RUBY_CONST_HERO_TABLE;
	private JSONArray RUBY_CONST_MONSTER_TABLE;
	private JSONArray RUBY_CONST_LEVEL_UPGRADE_TABLE;
	private JSONArray RUBY_CONST_STAR_UPGRADE_TABLE;
	private JSONArray RUBY_CONST_QUALITY_UPGRADE_TABLE;
	private JSONArray RUBY_CONST_QUALITY_UPGRADE_RATE_TABLE;
	private JSONArray RUBY_CONST_EXPBOOK_TABLE;
	private JSONArray RUBY_CONST_VIP_TABLE;
	private JSONArray RUBY_CONST_NEW_STORE_TABLE;
	private JSONArray RUBY_CONST_NEW_STORE_REFRESH_TABLE;
	private JSONArray RUBY_CONST_NEW_STORE_HOT_TABLE;
	private JSONArray RUBY_CONST_NEW_STORE_DISCOUNT_TABLE;
	private JSONArray RUBY_CONST_NEW_STORE_TIME_LIMIT_TABLE;
	private JSONArray RUBY_CONST_IN_GAME_PURCHASE_TABLE;
	private JSONArray RUBY_CONST_EXCHANGE_TABLE;
	private JSONArray RUBY_CONST_BLACK_NICKNAME_TABLE;
	private JSONArray RUBY_CONST_PVP_BASIC_TABLE;
	private JSONArray RUBY_CONST_PVP_REFRESH_TABLE;
	private JSONArray RUBY_CONST_PVP_AWARD_TABLE;
	private HashMap RUBY_CONST_HERO_TABLE_HASH;


	/*
	def drop_item_newbie(identifier, reply, store2backpack, parameter)
		puts('drop_item_newbie')
		ar = []
		ar[ar.length] = ['gold', 100, [20000, ], ['gold', ]]
		result = select_from_multiple_award(ar)
		if result != nil then
			get_award(identifier, ar[result], reply, store2backpack, parameter)
		end
		ar = []
		ar[ar.length] = ['hero_', 100, [1, ], ['hero_20001', ]]
		result = select_from_multiple_award(ar)
		if result != nil then
			get_award(identifier, ar[result], reply, store2backpack, parameter)
		end
		ar = []
		ar[ar.length] = ['exp_book_', 100, [1, ], ['exp_book_4', ]]
		result = select_from_multiple_award(ar)
		if result != nil then
			get_award(identifier, ar[result], reply, store2backpack, parameter)
		end
		ar = []
		ar[ar.length] = ['private_soulchip_20001', 100, [25, ], ['private_soulchip_20001', ]]
		result = select_from_multiple_award(ar)
		if result != nil then
			get_award(identifier, ar[result], reply, store2backpack, parameter)
		end
		ar = []
		ar[ar.length] = ['book_skill_2012', 100, [11, ], ['book_skill_2012', ]]
		result = select_from_multiple_award(ar)
		if result != nil then
			get_award(identifier, ar[result], reply, store2backpack, parameter)
		end
	end
	 */


}
