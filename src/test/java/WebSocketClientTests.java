import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommLoginRequestProtobuf;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.websocket.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

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
		//String uri = "ws://110.40.133.173:32011/ws";
		Session session = container.connectToServer(WebSocketClientTests.class, URI.create(uri));

		CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("")
				.setVersion(11)
				.setData("1630986780")
				.setCipher("DBDE9269F4E47C0FC8B111854B43C551C8A58974")
				.build();
		/*CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("28")
				.setVersion(11)
				.setData("1630997451")
				.setCipher("D14B356084853978DB05326E6AFFFDA1EFD76EBB")
				.build();*/
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

	public static void main1(String[] args) {
		/*String s = StrFormatter.format("{} {}  {}   {}",
				"1630986780",
				11,
				"",
				NumberUtil.parseLong("1630986780") * 11);*/
		String s = StrFormatter.format("{} {}  {}   {}",
				"1630986780",
				11,
				"25",
				NumberUtil.parseLong("1630986780") * 11);
		System.out.println("SecureUtil.sha1(s) = " + SecureUtil.sha1(s).toUpperCase());
	}

}
