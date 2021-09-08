import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.proto.CommUpdateTemporaryBackpackRequestProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
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
		String name = messageBase.getName();
		if (name.contains("LOGIN")){
			CommLoginResponseProtobuf.CommLoginResponse commLoginResponse = CommLoginResponseProtobuf.CommLoginResponse.parseFrom(messageBase.getData());
			log.info("回复登陆消息:{}{}",messageBase,commLoginResponse);
		} else {
			ByteString data = messageBase.getData();
			MessageLite messageLite = reply2Proto(data);
			log.info("回复业务消息:{}\n{}",name,messageLite);
		}

	}

	/**
	 * 回复转proto实体
	 * @param byteString
	 * @return
	 * @throws InvalidProtocolBufferException
	 */
	private  static  MessageLite reply2Proto(ByteString byteString) throws InvalidProtocolBufferException {
		return null;
	}

	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}


	public static void main(String[] args) throws Exception {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri = "ws://127.0.0.1:19999/ws";
		//String uri = "ws://110.40.133.173:32011/ws";
		Session session = container.connectToServer(WebSocketClientTests.class, URI.create(uri));

		CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("155")
				.setVersion(11)
				.setData("1631069209")
				.setCipher("CE09BA8B1F1C4C57CAB355C2B379E037145F8650")
				.build();

		byte[] bytes = MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.LOGIN_REQUEST)
				.setData(request.toByteString())
				.build()
				.toByteArray();
		session.getBasicRemote()
				.sendBinary(ByteBuffer.wrap(bytes),true);
		byte[] businessMsg = sendBusinessMsg();
		session.getBasicRemote()
						.sendBinary(ByteBuffer.wrap(businessMsg),true);
		Thread.sleep(500000);

	}

	/**
	 * 发送业务消息
	 *
	 * @return
	 */
	public static byte[] sendBusinessMsg() {
		CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest request = CommUpdateTemporaryBackpackRequestProtobuf.CommUpdateTemporaryBackpackRequest.newBuilder()
				.setGoblins(0)
				.setMonsters(0)
				.build();

		return MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.UPDATE_TEMPORARY_BACKPACK)
				.setData(request.toByteString())
				.build()
				.toByteArray();
	}

}
