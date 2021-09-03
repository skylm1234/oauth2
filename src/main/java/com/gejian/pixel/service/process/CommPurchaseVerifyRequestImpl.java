package com.gejian.pixel.service.process;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommPurchaseVerifyRequestProtobuf;
import com.gejian.pixel.proto.CommPurchaseVerifyResponseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.ChannelHolder;
import com.gejian.pixel.utils.Helper;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:chen
 * @Date: 2021/9/2 13:58
 */
@Service(CommandConstants.PURCHASE_VERIFY)
@CommandResponse(CommandConstants.PURCHASE_VERIFY)
@Slf4j
public class CommPurchaseVerifyRequestImpl implements Process<CommPurchaseVerifyRequestProtobuf.CommPurchaseVerifyRequest,
		CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse> {

	//TODO settings.IAP_VERIFY_URL
	private final String url = "";
	private final Integer identifier = 0;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse doProcess(CommPurchaseVerifyRequestProtobuf.CommPurchaseVerifyRequest request) throws Exception {


		CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse.Builder builder = CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse.newBuilder();
		builder.setRequest(request);

		String pattern = "^https://sandbox\u002E*$";
		Pattern compile = Pattern.compile(pattern);
		Matcher m = compile.matcher(url);

		Formatter formatter = new Formatter();

		if (m.find() && request.getType() != 0) {
			int type = request.getType();
			if (type >= 1 && type <= 7) {
				String productId = "item_" + type;
				RubyConstInGamePurchaseTableHash cell = getRubyConstInGamePurchaseTableHash(productId);
				if (Helper.itemCount(redisTemplate, identifier, productId) == 0) {
					Helper.increaseItemValue(redisTemplate, identifier, productId, 1L);
					Map<String, Object> gb = new HashMap<>(4);
					gb.put("identifier", "giftbag_identifier_" + redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
					//转16进制
					gb.put("icon", HexUtil.encodeHexStr("gift_package.png", CharsetUtil.CHARSET_UTF_8));
					gb.put("desc", HexUtil.encodeHexStr("初次购买" + cell.desc + "，赠送" + cell.desc + "。", CharsetUtil.CHARSET_UTF_8));
					gb.put("action", productId);

					redisTemplate.opsForHash().putAll(formatter.format("u:%d:giftbag:%s", identifier, gb.get("identifier")).toString(), gb);
				}

				Helper.increaseItemValue(redisTemplate, identifier, "stone", (long) cell.stone);
				Helper.increaseItemValue(redisTemplate, identifier, "total_stone_purchased", (long) cell.stone);
				Helper.updateRanklistRich(redisTemplate, identifier);

				Helper.increaseItemValue(redisTemplate, identifier, "total_charged_money", (long) cell.cost);

				Integer origin_vip = Helper.itemCount(redisTemplate, identifier, "vip");
				Integer total_charged_money = Helper.itemCount(redisTemplate, identifier, "total_charged_money");

				Boolean dirty = false;

				while (true) {
					RubyConstVipTable row = getRubyConstVipTable(origin_vip);
					if (row == null) {
						log.error("unknow vip level");
						throw new RuntimeException("unknow vip level");
					}
					if (total_charged_money >= row.charge) {
						origin_vip = origin_vip + 1;

						RubyConstVipTable row2 = getRubyConstVipTable(origin_vip);

						Map<String, Object> gb = new HashMap<>();
						gb.put("identifier", "giftbag_identifier_" + redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
						gb.put("icon", HexUtil.encodeHexStr("gift_package.png", CharsetUtil.CHARSET_UTF_8));
						gb.put("desc", HexUtil.encodeHexStr("达到vip等级" + row2.getLevel() + "，好礼相送。"));
						gb.put("action", row2.getItemid());

						redisTemplate.opsForHash().putAll(formatter.format("u:%d:giftbag:%s", identifier, gb.get("identifier")).toString(), gb);

						Helper.increaseItemValue(redisTemplate, identifier, "giftbags", 1L);
						redisTemplate.opsForHash().put(formatter.format("u:%d:giftbags", identifier).toString(),
								gb.get("identifier"),
								formatter.format("达到vip等级%d，好礼相送。", row2.getLevel()).toString());
						dirty = true;
					} else {
						break;
					}
				}

				if (dirty) {
					PlayerItemProtobuf.PlayerItem playerItem = Helper.setItemValue(redisTemplate, String.valueOf(identifier), "vip", origin_vip);
					PlayerItemProtobuf.PlayerItem playerItem1 = Helper.onNotifyEventOfPromotions(redisTemplate, "vip", origin_vip, identifier);
					builder.addItems(playerItem)
							.addItems(playerItem1);
				}
			}

		} else {
			builder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER);
			return builder.build();
		}
		return builder.build();
	}

	@Data
	class RubyConstInGamePurchaseTableHash {
		private String id;
		private Integer stone;
		private String desc;
		private String icon;
		private Integer cost;
	}

	@Data
	class RubyConstVipTable {
		private Integer id;
		private Integer level;
		private Integer charge;
		private String itemid;
		private String award;
		private Integer chanllege;
		private Integer backpack_max;
		private Integer freerefresh_times_stone;
		private Integer freerefresh_times_honor;
		private Integer freerefresh_times_gold;
		private Integer timeval_offline_kill_monster_rate;
		private Integer skip_timeval;
		private String des;
		private Integer tianti;
		private Integer tianti_reset;
	}

	private RubyConstInGamePurchaseTableHash getRubyConstInGamePurchaseTableHash(String id) {
		return null;
	}

	private RubyConstVipTable getRubyConstVipTable(Integer id) {
		return null;
	}

	public static void main(String[] args) {

	}

}
