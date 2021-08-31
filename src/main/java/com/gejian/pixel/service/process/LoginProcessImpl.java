package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.service.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 登陆请求
 *
 * @author ZhouQiang
 * @date 2021/8/30$
 */
@Service(CommandConstants.LOGIN)
@Slf4j
public class LoginProcessImpl implements Process<CommLoginRequestProtobuf.CommLoginRequest
		, CommLoginResponseProtobuf.CommLoginResponse> {


	@Override
	public CommLoginResponseProtobuf.CommLoginResponse
	doProcess(CommLoginRequestProtobuf.CommLoginRequest commLoginRequest) throws Exception {
		// TODO 登陆逻辑
		log.info("登陆请求参数：{}", commLoginRequest);
		return CommLoginResponseProtobuf.CommLoginResponse.newBuilder()
				.setRequest(commLoginRequest)
				.setResult(1)
				.build();
	}
}
