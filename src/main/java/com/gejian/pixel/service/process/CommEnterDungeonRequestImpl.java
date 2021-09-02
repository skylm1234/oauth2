package com.gejian.pixel.service.process;

import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommEnterDungeonRequestProtobuf;
import com.gejian.pixel.proto.CommEnterDungeonResponseProtobuf;
import com.gejian.pixel.service.Process;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author:chen
 * @Date: 2021/9/2 17:41
 */
@Service(CommandConstants.ENTER_DUNGEON)
@CommandResponse(CommandConstants.ENTER_DUNGEON)
@Slf4j
public class CommEnterDungeonRequestImpl implements Process<CommEnterDungeonRequestProtobuf.CommEnterDungeonRequest,
		CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse> {

	@Override
	public CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse doProcess(CommEnterDungeonRequestProtobuf.CommEnterDungeonRequest request) throws Exception {

		CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse.Builder builder = CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse.newBuilder();
		builder.setRequest(request);



		return null;
	}
}
