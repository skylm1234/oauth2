package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommHarvestTemporaryBackpackRequestProtobuf;
import com.gejian.pixel.proto.CommHarvestTemporaryBackpackResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.TemporaryBackpackHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收获临时背包
 *
 * @author Reese
 * @date 2021/9/1
 */
@Slf4j
@Service(CommandConstants.HARVEST_TEMPORARY_BACKPACK)
public class L26HarvestTemporaryBackpackImpl implements Process<CommHarvestTemporaryBackpackRequestProtobuf.CommHarvestTemporaryBackpackRequest
		, CommHarvestTemporaryBackpackResponseProtobuf.CommHarvestTemporaryBackpackResponse> {

	@Autowired
	private TemporaryBackpackHelper temporaryBackpackHelper;

	@Override
	public CommHarvestTemporaryBackpackResponseProtobuf.CommHarvestTemporaryBackpackResponse doProcess(CommHarvestTemporaryBackpackRequestProtobuf.CommHarvestTemporaryBackpackRequest request) throws Exception {
		CommHarvestTemporaryBackpackResponseProtobuf.CommHarvestTemporaryBackpackResponse.Builder builder =
				CommHarvestTemporaryBackpackResponseProtobuf.CommHarvestTemporaryBackpackResponse.newBuilder();
		this.temporaryBackpackHelper.grabTemporaryBackpack(UserHolder.get().getIdentifier(), request, builder, request.getMonsters(), request.getGoblins());
		return builder.build();
	}


}
