package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.BackpackService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.VipService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 升级临时背包
 *
 * @author Reese
 * @date 2021/9/1
 */
@Slf4j
@Service(CommandConstants.UPGRADE_TEMPORARY_BACKPACK)
public class L27UpgradeTemporaryBackpackImpl implements Process<CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest
		, CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private VipService vipService;

	@Autowired
	private BackpackService backpackService;


	@Override
	public CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse doProcess(CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest request) throws Exception {

		CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse.Builder builder =
				CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		String tempBackpackKey = this.getTempBackpackKey(identifier);

		Map<Object, Object> backpackMap = this.redisTemplate.opsForHash().entries(tempBackpackKey);

		// ConstVipTable
		Integer vip = Helper.itemCount(redisTemplate, identifier, "vip");
		ConstVipTableItemExProtobuf.ConstVipTableItemEx vipTableItemEx =
				vipService.getItem(vip);

		if (this.parseLong(backpackMap.get("level")) >= vipTableItemEx.getLevel()) {
			return builder.setResult(ErrorEnum.ERROR_REACH_LIMIT).build();
		}

		// ConstBackpackTable
		ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx backpackTableItemEx =
//				backpackService.getItem(this.parseInt(backpackMap.get("level")) - 1);
				backpackService.getItem(this.parseInt(backpackMap.get("level")));

		PlayerItemProtobuf.PlayerItem playerItem = Helper.decreaseItemValue(redisTemplate, identifier, "stone", (long) backpackTableItemEx.getFomula().getStone());
		builder.addItems(playerItem);
		if (playerItem != null) {

			this.redisTemplate.opsForHash().increment(tempBackpackKey, "level", 1);

			PlayerItemProtobuf.PlayerItem playerItem1 = Helper
					.onNotifyEventOfPromotions(redisTemplate, "tempbackpack", identifier, 1);
			builder.addItems(playerItem1);
		} else {
			return builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_STONE).build();
		}

		return builder.build();
	}

	public Long parseLong(Object o) {
		return o == null ? 0L : Long.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}

	private String getTempBackpackKey(Integer identifier) {
		return "u:#{identifier}:temp_backpack".replace("#{identifier}", identifier.toString());
	}

}
