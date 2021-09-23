package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommRefreshStoreRequestProtobuf;
import com.gejian.pixel.proto.CommRefreshStoreResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.StoreHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 刷新商店
 *
 * @author Reese
 * @date 2021/9/2
 */
@Slf4j
@Service(CommandConstants.REFRESH_STORE)
public class RefreshStoreImpl implements Process<CommRefreshStoreRequestProtobuf.CommRefreshStoreRequest
		, CommRefreshStoreResponseProtobuf.CommRefreshStoreResponse> {

	@Autowired
	private StoreHelper storeHelper;

	@Override
	public CommRefreshStoreResponseProtobuf.CommRefreshStoreResponse doProcess(CommRefreshStoreRequestProtobuf.CommRefreshStoreRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommRefreshStoreResponseProtobuf.CommRefreshStoreResponse.Builder builder
				= CommRefreshStoreResponseProtobuf.CommRefreshStoreResponse.newBuilder();
		this.storeHelper.refreshStore(identifier, request, builder);
		return builder.setRequest(request).build();
	}
}
