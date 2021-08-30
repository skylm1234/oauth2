package com.gejian.pixel.service.process;

import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.service.Process;
import org.springframework.stereotype.Service;

/**
 *
 *
 * 登陆请求
 *
 * @author ZhouQiang
 * @date 2021/8/30$
 */
@Service("COMM_LOGIN_REQUEST")
public class LoginProcessImpl implements Process<CommLoginRequestProtobuf.CommLoginRequest
		,CommLoginRequestProtobuf.CommLoginRequest> {


	@Override
	public CommLoginRequestProtobuf.CommLoginRequest
		doProcess(CommLoginRequestProtobuf.CommLoginRequest commLoginRequest) throws Exception {
		// TODO 登陆逻辑

		return null;
	}
}
