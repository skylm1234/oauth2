import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import io.undertow.websockets.client.WebSocketClient;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author ZhouQiang
 * @date 2021/8/30$
 */
@ClientEndpoint
@Slf4j
public class WebSocketClientTests {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("open ... ");
	}

	@OnMessage
	public void onMessage(byte[] messages, Session session) throws InvalidProtocolBufferException {

		MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase.parseFrom(messages);
		log.info("回复消息:{}",messageBase);
	}

	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}


	public static void main(String[] args) throws Exception {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri = "ws://127.0.0.1:19999/ws";
		Session session = container.connectToServer(WebSocketClientTests.class, URI.create(uri));
		CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("12312312")
				.build();
		byte[] bytes = MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.LOGIN)
				.setData(request.toByteString())
				.build()
				.toByteArray();
		session.getBasicRemote()
				.sendBinary(ByteBuffer.wrap(bytes));
		Thread.sleep(100000);
	}

}
