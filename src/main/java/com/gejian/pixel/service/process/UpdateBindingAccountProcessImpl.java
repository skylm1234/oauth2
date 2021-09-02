package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.CommUpdateBindingAccountRequestProtobuf;
import com.gejian.pixel.proto.CommUpdateBindingAccountResponseProtobuf;
import com.gejian.pixel.proto.PlayerStringProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Service(CommandConstants.UPDATE_BINDING_ACCOUNT)
@Slf4j
public class UpdateBindingAccountProcessImpl implements
		Process<CommUpdateBindingAccountRequestProtobuf.CommUpdateBindingAccountRequest,
		CommUpdateBindingAccountResponseProtobuf.CommUpdateBindingAccountResponse> {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public CommUpdateBindingAccountResponseProtobuf.CommUpdateBindingAccountResponse
		doProcess(CommUpdateBindingAccountRequestProtobuf.CommUpdateBindingAccountRequest request) throws Exception {
		log.info(CommandConstants.UPDATE_BINDING_ACCOUNT + "请求参数:{}",request);
		UserInfo userInfo = UserHolder.get();
		// 修改用户名密码
		Helper
				.setStringValue(redisTemplate, userInfo.getIdentifier(), "user", request.getName());
		Helper
				.setStringValue(redisTemplate, userInfo.getIdentifier(), "password", request.getPassword());
		return  CommUpdateBindingAccountResponseProtobuf.CommUpdateBindingAccountResponse
				.newBuilder()
				.setRequest(request)
				.setResult(ErrorEnum.ERROR_SUCCESS).build();
	}
}
