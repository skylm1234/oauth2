import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.net.URI;
import java.nio.ByteBuffer;

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
		log.info("reply:{}",messages);
		MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase.parseFrom(messages);
		CommLoginResponseProtobuf.CommLoginResponse commLoginResponse = CommLoginResponseProtobuf.CommLoginResponse.parseFrom(messageBase.getData());

		log.info("回复消息:{}{}",messageBase,commLoginResponse);
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
				.setIdentifier("104")
				.setVersion(11)
				.setData("1630488085")
				.setCipher("ff56ce1dd084f89ea67c4db077dc21fa0c7d4d2d")
				.build();
		byte[] bytes = MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.LOGIN_REQUEST)
				.setData(request.toByteString())
				.build()
				.toByteArray();
		session.getBasicRemote()
				.sendBinary(ByteBuffer.wrap(bytes),true);
		session.getBasicRemote()
						.sendBinary(ByteBuffer.wrap(bytes),true);
		Thread.sleep(500000);

	}

}
