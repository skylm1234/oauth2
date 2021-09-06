package com.gejian.pixel.service.process;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.InGamePurchase;
import com.gejian.pixel.entity.Vip;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.CommPurchaseVerifyRequestProtobuf;
import com.gejian.pixel.proto.CommPurchaseVerifyResponseProtobuf;
import com.gejian.pixel.proto.InGamePurchaseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.InGamePurchaseService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.VipService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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

	@Value("${config-url.MACOSX-IAP-VERIFY-URL}")
	private String macosx;
	@Value("${config-url.CENTOS-IAP-VERIFY-URL}")
	private String centos;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private InGamePurchaseService inGamePurchaseService;

	@Autowired
	private VipService vipService;

	@Override
	public CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse doProcess(CommPurchaseVerifyRequestProtobuf.CommPurchaseVerifyRequest request) throws Exception {


		CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse.Builder builder = CommPurchaseVerifyResponseProtobuf.CommPurchaseVerifyResponse.newBuilder();
		builder.setRequest(request);

		String pattern = "^https://sandbox\u002E*$";
		Pattern compile = Pattern.compile(pattern);
		Matcher m = compile.matcher(centos);

		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();

		if (m.find() && request.getType() != 0) {
			int type = request.getType();
			if (type >= 1 && type <= 7) {
				String productId = "item_" + type;
				InGamePurchase inGamePurchase = inGamePurchaseService.getById(productId);
				if (Helper.itemCount(redisTemplate, identifier, productId) == 0) {
					Helper.increaseItemValue(redisTemplate, identifier, productId, 1L);
					Map<String, Object> gb = new HashMap<>(4);
					gb.put("identifier", "giftbag_identifier_" + redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
					//转16进制
					gb.put("icon", HexUtil.encodeHexStr("gift_package.png", CharsetUtil.CHARSET_UTF_8));
					gb.put("desc", HexUtil.encodeHexStr("初次购买" + inGamePurchase.getDesc() + "，赠送" + inGamePurchase.getDesc() + "。", CharsetUtil.CHARSET_UTF_8));
					gb.put("action", productId);

					redisTemplate.opsForHash().putAll(String.format("u:%d:giftbag:%s", identifier, gb.get("identifier")), gb);
				}

				Helper.increaseItemValue(redisTemplate, identifier, "stone", (long) inGamePurchase.getStone());
				Helper.increaseItemValue(redisTemplate, identifier, "total_stone_purchased", (long) inGamePurchase.getStone());
				Helper.updateRanklistRich(redisTemplate, identifier);

				Helper.increaseItemValue(redisTemplate, identifier, "total_charged_money", (long) inGamePurchase.getCost());

				Integer origin_vip = Helper.itemCount(redisTemplate, identifier, "vip");
				Integer total_charged_money = Helper.itemCount(redisTemplate, identifier, "total_charged_money");

				Boolean dirty = false;

				while (true) {
					Vip vip = vipService.getById(origin_vip);
					if (vip == null) {
						log.error("unknow vip level");
						throw new RuntimeException("unknow vip level");
					}
					if (total_charged_money >= vip.getCharge()) {
						origin_vip = origin_vip + 1;

						Vip vip2 = vipService.getById(origin_vip);

						Map<String, Object> gb = new HashMap<>();
						gb.put("identifier", "giftbag_identifier_" + redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
						gb.put("icon", HexUtil.encodeHexStr("gift_package.png", CharsetUtil.CHARSET_UTF_8));
						gb.put("desc", HexUtil.encodeHexStr("达到vip等级" + vip2.getLevel() + "，好礼相送。"));
						gb.put("action", vip2.getItemid());

						redisTemplate.opsForHash().putAll(String.format("u:%d:giftbag:%s", identifier, gb.get("identifier")), gb);

						Helper.increaseItemValue(redisTemplate, identifier, "giftbags", 1L);
						redisTemplate.opsForHash().put(String.format("u:%d:giftbags", identifier),
								gb.get("identifier"),
								String.format("达到vip等级%d，好礼相送。", vip2.getLevel()));
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


}
