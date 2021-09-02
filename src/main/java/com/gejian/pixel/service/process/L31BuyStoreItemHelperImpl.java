package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf;
import com.gejian.pixel.proto.CommBuyStoreItemResponseProtobuf;
import com.gejian.pixel.proto.CommUpdateTemporaryBackpackRequestProtobuf;
import com.gejian.pixel.proto.CommUpdateTemporaryBackpackResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.TemporaryBackpackHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购买商店物品助手
 *
 * @author Reese
 * @date 2021/9/2
 */
@Slf4j
@Service(CommandConstants.BUY_STORE_ITEM_HELPER)
public class L31BuyStoreItemHelperImpl implements Process<CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest
		, CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse> {


	@Override
	public CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse doProcess(CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest commBuyStoreItemRequest) throws Exception {
		return null;
	}
}
