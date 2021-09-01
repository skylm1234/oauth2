package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf;
import com.gejian.pixel.proto.CommLetHeroConsumeExpBookResponseProtobuf;
import com.gejian.pixel.service.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tangtao
 * @since 2021/9/1
 */
@Service(CommandConstants.LET_HERO_CONSUME_EXP_BOOK)
@Slf4j
public class HeroConsumeExpBookProcessImpl implements Process<CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest
		, CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse> {
	@Override
	public CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse doProcess(
			CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest request) throws Exception {


		return CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse.newBuilder()
				.setRequest(request)
				.setResult(1)
				.build();
	}
}
