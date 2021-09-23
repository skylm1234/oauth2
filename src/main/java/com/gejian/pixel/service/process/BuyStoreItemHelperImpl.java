package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf;
import com.gejian.pixel.proto.CommBuyStoreItemResponseProtobuf;
import com.gejian.pixel.service.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 购买商店物品助手
 *
 * @author Reese
 * @date 2021/9/2
 */
@Slf4j
@Service(CommandConstants.BUY_STORE_ITEM_HELPER)
public class BuyStoreItemHelperImpl implements Process<CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest
		, CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse> {


	@Override
	public CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse doProcess(CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest commBuyStoreItemRequest) throws Exception {
		// nothing
		return null;
	}
}
