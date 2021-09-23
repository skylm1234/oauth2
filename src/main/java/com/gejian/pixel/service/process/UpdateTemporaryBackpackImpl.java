package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommUpdateTemporaryBackpackRequestProtobuf;
import com.gejian.pixel.proto.CommUpdateTemporaryBackpackResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.TemporaryBackpackHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 更新临时背包
 *
 * @author Reese
 * @date 2021/9/1
 */
@Slf4j
@Service(CommandConstants.UPDATE_TEMPORARY_BACKPACK)
public class UpdateTemporaryBackpackImpl implements Process<CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest
		, CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse> {

	@Autowired
	private TemporaryBackpackHelper temporaryBackpackHelper;

	@Override
	public CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse doProcess(CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest request) throws Exception {
		CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.Builder builder =
				CommUpdateTemporaryBackpackResponseProtobuf.CommUpdateTemporaryBackpackResponse.newBuilder();
		this.temporaryBackpackHelper.updateTemporaryBackpack(UserHolder.get().getIdentifier(), request, builder, request.getMonsters(), request.getGoblins());
		return builder.setRequest(request).build();
	}

}
