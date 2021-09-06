package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommPingRequestProtobuf;
import com.gejian.pixel.proto.CommPingResponseProtobuf;
import com.gejian.pixel.proto.CommSetNicknameResponseProtobuf;
import com.gejian.pixel.service.Process;
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

		return response.setTimestamp((int) LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()).build();
	}
}
