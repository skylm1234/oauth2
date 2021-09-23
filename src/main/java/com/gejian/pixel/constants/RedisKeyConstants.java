package com.gejian.pixel.constants;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
public interface RedisKeyConstants {

	String USER = "u:{}:session";

	String USER_IDENTIFIER = "user:set:identifier";

	String USER_ITEMS = "u:{}:items";

	String USER_ARCHIVES = "u:{}:archives";

	String USER_STRINGS = "u:{}:strings";

	String USER_CLEAR_TEXT = "user:set:identifier_cleartext";

	String USER_TEMP_PACK = "u:{}:temp_backpack";

	String USER_TEMP_PACK_ITEMS = "u:{}:temp_backpack_items";

	String USER_HEROS = "u:{}:heros";

	String USER_HERO = "u:{}:{}";

	String USER_HERO_ATTRIBUTES = "u:{}:{}:attributes";

	String USER_HERO_SKILLS = "u:{}:{}:skills";

	String USER_GIFTBAGS = "u:{}:giftbags";

	String USER_GIFTBAG = "u:{}:giftbag:{}";

	String USER_TEAMS = "u:{}:teams";

	String USER_TEAMS_PVP = "u:{}:teams_pvp";

	String USER_SET_BAN = "user:set:ban";

	String USER_SET_IDENTIFIER = "user:set:identifier";

	String RANKLIST = "ranklist:{}";

	String USER_STORE = "u:{}:store:{}";

	String SYSTEM_BAN_ANYONE = "system:ban_anyone";

	String SYSTEM_BAN_ANYONE_REASON = "system:ban_anyone_reason";

	String SYSTEM_BOARDCAST = "system:boardcast";

	String SYSTEM_BOARDCAST11 = "system:boardcast11";

	String BUY_HERO_TIMES = "buy_hero_{}_times";

	String BUY_HERO_TIMESTAMP = "buy_hero_{}_timestamp";

	String BUY_HERO_PRICE = "buy_hero_{}_price";

	String USER_MAX_PLAYER_IDENTIFIER = "user:max:player_identifier";

	String USER_MAX_HERO_IDENTIFIER = "user:max:hero_identifier";

	String USER_MAX_IAP_IDENTIFIER = "user:max:iap_identifier";

	String USER_MAX_GIFTBAG_IDENTIFIER = "user:max:giftbag_identifier";

	String GIFTBAG_IDENTIFIER_PREFIX = "giftbag_identifier_{}";

	String USER_SET_NICKNAME = "user:set:nickname";

	String USER_SET_NICKNAME_CLEARTEXT = "user:set:nickname_cleartext";


}
