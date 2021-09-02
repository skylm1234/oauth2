package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Service(CommandConstants.REDUCE_STONE)
@Slf4j
public class ReduceStoneProcessImpl implements
		Process<CommReduceStoneRequestProtobuf.CommReduceStoneRequest,
				CommReduceStoneResponseProtobuf.CommReduceStoneResponse> {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public CommReduceStoneResponseProtobuf.CommReduceStoneResponse
	doProcess(CommReduceStoneRequestProtobuf.CommReduceStoneRequest request) throws Exception {
		log.info(CommandConstants.REDUCE_STONE + "请求参数:{}", request);
		UserInfo userInfo = UserHolder.get();
		PlayerItemProtobuf.PlayerItem stone = Helper.increaseItemValue(redisTemplate,
				userInfo.getIdentifier(), "stone", -1L * request.getAmount());
		return CommReduceStoneResponseProtobuf.CommReduceStoneResponse
				.newBuilder()
				.setRequest(request)
				.setResult(ErrorEnum.ERROR_SUCCESS)
				.addItems(stone).build();
	}
}
