package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommGetGiftbagListRequestProtobuf;
import com.gejian.pixel.proto.CommGetGiftbagListResponseProtobuf;
import com.gejian.pixel.proto.GiftbagProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 获取礼包清单
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.GET_GIFTBAG_LIST)
public class L35GetGiftBagListImpl implements Process<CommGetGiftbagListRequestProtobuf.CommGetGiftbagListRequest
		, CommGetGiftbagListResponseProtobuf.CommGetGiftbagListResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public CommGetGiftbagListResponseProtobuf.CommGetGiftbagListResponse doProcess(CommGetGiftbagListRequestProtobuf.CommGetGiftbagListRequest request) throws Exception {

		Integer identifier = UserHolder.get().getIdentifier();

		CommGetGiftbagListResponseProtobuf.CommGetGiftbagListResponse.Builder builder =
				CommGetGiftbagListResponseProtobuf.CommGetGiftbagListResponse.newBuilder();

		String giftBagsKey = this.getGiftBagsKey(identifier);

		Map giftBags = this.redisTemplate.opsForHash().entries(giftBagsKey);

		giftBags.forEach((k, v) -> {

			String giftBagKey = this.getGiftBagKey(identifier, this.parseString(k));

			Map giftBag = this.redisTemplate.opsForHash().entries(giftBagKey);

			GiftbagProtobuf.Giftbag.Builder giftBagBuilder = GiftbagProtobuf.Giftbag.newBuilder()
					.setIdentifier(this.parseString(giftBag.get("identifier")))
					.setIcon(Helper.hexDecode(this.parseString(giftBag.get("icon"))))
					.setDesc(Helper.hexDecode(this.parseString(giftBag.get("desc"))))
					.setAction(this.parseString(giftBag.get("action")));

			builder.addGiftbags(giftBagBuilder);
		});

		return builder.setRequest(request).build();
	}


	private String getGiftBagsKey(Integer identifier) {
		return "u:" + identifier + ":giftbags";
	}

	private String getGiftBagKey(Integer identifier, String s) {
		return "u:" + identifier + ":giftbag:" + s;
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
