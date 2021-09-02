package com.gejian.pixel.service.process;

import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommChatRequestProtobuf;
import com.gejian.pixel.proto.CommChatResponseProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.BroadcastUtil;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ljb
 * @date 2021年09月02日 17:34
 * @description 发送聊天消息
 */
@Service(CommandConstants.CHAT_MESSAGE)
@Slf4j
@RequiredArgsConstructor
public class ChatMessageProcessImpl implements Process<CommChatRequestProtobuf.CommChatRequest
		, CommChatResponseProtobuf.CommChatResponse> {

	private final RedisTemplate redisTemplate;

	@Override
	public CommChatResponseProtobuf.CommChatResponse doProcess(CommChatRequestProtobuf.CommChatRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();

		CommChatRequestProtobuf.CommChatRequest.Builder requestBuilder = CommChatRequestProtobuf.CommChatRequest.newBuilder();
		requestBuilder.setTimestamp(Integer.parseInt(Helper.currentTimestamp()+""));
		requestBuilder.setSender(identifier);
		requestBuilder.setSenderNickname(Helper.stringValue(redisTemplate, identifier, "nickname"));
		request = requestBuilder.build();

		MessageBaseProtobuf.MessageBase base = MessageBaseProtobuf.MessageBase
				.newBuilder()
				.setName("COMM_CHAT_MESSAGE")
				// TODO: 2021/9/2 原方法 base.data = request.to_proto 有疑问
				.setData(request.getMessageBytes())
				.build();
		BroadcastUtil.broadcast(base);

		return null;
	}
}
