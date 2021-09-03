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

	String USER_HEARO = "u:{}:heros";

}
