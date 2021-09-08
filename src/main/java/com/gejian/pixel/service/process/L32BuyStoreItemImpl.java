package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf;
import com.gejian.pixel.proto.CommBuyStoreItemResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.StoreHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购买商店物品
 *
 * @author Reese
 * @date 2021/9/2
 */
@Slf4j
@Service(CommandConstants.BUY_STORE_ITEM)
public class L32BuyStoreItemImpl implements Process<CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest
		, CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse> {

	@Autowired
	private StoreHelper storeHelper;

	@Override
	public CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse doProcess(CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse.Builder builder =
				CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse.newBuilder();
		this.storeHelper.buyStoreItem(identifier, request, builder);
		return builder.setRequest(request).build();
	}
}
