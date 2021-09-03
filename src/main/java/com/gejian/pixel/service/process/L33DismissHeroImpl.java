package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommBuyStoreItemRequestProtobuf;
import com.gejian.pixel.proto.CommBuyStoreItemResponseProtobuf;
import com.gejian.pixel.proto.CommDismissHeroRequestProtobuf;
import com.gejian.pixel.proto.CommDismissHeroResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.StoreHelper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 解雇英雄
 *
 * @author Reese
 * @date 2021/9/3
 */
@Slf4j
@Service(CommandConstants.DISMISS_HERO)
public class L33DismissHeroImpl implements Process<CommDismissHeroRequestProtobuf.CommDismissHeroRequest
		, CommDismissHeroResponseProtobuf.CommDismissHeroResponse> {

	@Override
	public CommDismissHeroResponseProtobuf.CommDismissHeroResponse doProcess(CommDismissHeroRequestProtobuf.CommDismissHeroRequest commDismissHeroRequest) throws Exception {
		return null;
	}
}
