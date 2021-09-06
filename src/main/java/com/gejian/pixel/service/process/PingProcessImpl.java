package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommPingRequestProtobuf;
import com.gejian.pixel.proto.CommPingResponseProtobuf;
import com.gejian.pixel.proto.CommSetNicknameResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author : Hyb
 * @date : 2021/9/3 11:34
 * @description: 心跳请求
 */
@Service(CommandConstants.PING)
@Slf4j
public class PingProcessImpl implements Process<CommPingRequestProtobuf.CommPingRequest
		, CommPingResponseProtobuf.CommPingResponse> {


	@Override
	public CommPingResponseProtobuf.CommPingResponse doProcess(CommPingRequestProtobuf.CommPingRequest commPingRequest) throws Exception {

		CommPingResponseProtobuf.CommPingResponse.Builder response = CommPingResponseProtobuf.CommPingResponse.newBuilder();

		response.setTimestamp((int) Helper.currentTimestamp());
		return response.build();
	}
}
