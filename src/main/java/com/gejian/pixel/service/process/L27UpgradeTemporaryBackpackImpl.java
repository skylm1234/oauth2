package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
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
	private RedisTemplate<String, Object> redisTemplate;


	@Override
	public CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse doProcess(CommUpgradeTemporaryBackpackRequestProtobuf.CommUpgradeTemporaryBackpackRequest request) throws Exception {

		CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse.Builder builder =
				CommUpgradeTemporaryBackpackResponseProtobuf.CommUpgradeTemporaryBackpackResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		String tempBackpackKey = this.getTempBackpackKey(identifier);

		Map<Object, Object> backpack = this.redisTemplate.opsForHash().entries(tempBackpackKey);

		// TODO ConstVipTable
		Integer vip = Helper.itemCount(redisTemplate, identifier, "vip");
		ConstVipTableItemExProtobuf.ConstVipTableItemEx vipTableItemEx =
				ConstVipTableProtobuf.ConstVipTable.getDefaultInstance().getItems(vip);

		if (this.parseLong(backpack.get("level")) >= vipTableItemEx.getLevel()) {
			return builder.setResult(ErrorEnum.ERROR_REACH_LIMIT).build();
		}

		// TODO ConstBackpackTable
		ConstBackpackTableItemExProtobuf.ConstBackpackTableItemEx backpackTableItemEx =
				ConstBackpackTableProtobuf.ConstBackpackTable.getDefaultInstance().getItems(this.parseInt(backpack.get("level")) - 1);

		PlayerItemProtobuf.PlayerItem playerItem = Helper.decreaseItemValue(redisTemplate, identifier, "stone", (long) backpackTableItemEx.getFomula().getStone());
		if (playerItem != null) {

			this.redisTemplate.opsForHash().increment(tempBackpackKey, "level", 1);

			// TODO reply
			Helper.onNotifyEventOfPromotions(redisTemplate, "tempbackpack", identifier, 1, null);
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
