import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import io.undertow.websockets.WebSocketExtension;
import io.undertow.websockets.jsr.ExtensionImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.tomcat.websocket.WsExtension;

import javax.websocket.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouQiang
 * @date 2021/8/30$
 */
@ClientEndpoint
@Slf4j
public class WebSocketClientTests extends Endpoint{


	@OnMessage
	public void onMessage(byte[] messages, Session session) throws InvalidProtocolBufferException {

		MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase.parseFrom(messages);
		CommLoginResponseProtobuf.CommLoginResponse commLoginResponse = CommLoginResponseProtobuf.CommLoginResponse.parseFrom(messageBase.getData());

		log.info("回复消息:{}{}",messageBase,commLoginResponse);
	}

	@Override
	public void onError(Session session, Throwable thr) {
		super.onError(session, thr);
	}

	public static void main(String[] args) throws Exception {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri = "ws://127.0.0.1:19999/ws";
		WebSocketExtension.Parameter http_identifier = new WebSocketExtension.Parameter("HTTP_IDENTIFIER", "1");
		WebSocketExtension.Parameter http_session = new WebSocketExtension.Parameter("HTTP_SESSION", "1");
		ArrayList<WebSocketExtension.Parameter> parameters = Lists.newArrayList();
		parameters.add(http_identifier);
		parameters.add(http_session);
		WebSocketExtension extension = new WebSocketExtension("HTTP_IDENTIFIER",parameters);
		ArrayList<Extension> extensions = Lists.newArrayList();
		Extension wsExtension = ExtensionImpl.create(extension);
		extensions.add(wsExtension);
		ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder
				.create()
				.extensions(extensions)
				.build();
		URI wsUri = URI.create(uri);
		Session session = container.connectToServer(WebSocketClientTests.class,clientEndpointConfig,wsUri);

		CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("12312312")
				.build();
		byte[] bytes = MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.LOGIN_REQUEST)
				.setData(request.toByteString())
				.build()
				.toByteArray();
		session.getBasicRemote()
				.sendBinary(ByteBuffer.wrap(bytes));
		Thread.sleep(100000);
	}

	@Override
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		log.info("onOpen:{}",endpointConfig);
	}
}
