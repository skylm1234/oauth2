import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.regexp.RE;

import javax.websocket.*;
import java.io.IOException;
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
		/*try {
			System.out.println(toJson(CommBuyHeroResponseProtobuf.CommBuyHeroResponse.parseFrom(byteString)));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return CommEnterDungeonResponseProtobuf.CommEnterDungeonResponse.parseFrom(byteString);
	}

	public static String toJson(Message sourceMessage)
			throws IOException {
		String json = JsonFormat.printer().print(sourceMessage);
		return json;
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

		/*CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("")
				.setVersion(11)
				.setData("1630986780")
				.setCipher("DBDE9269F4E47C0FC8B111854B43C551C8A58974")
				.build();*/
		CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("11")
				.setVersion(11)
				.setData("1631257889")
				.setCipher("4B56BE6156815AA6EF7BEBA5CAF5671BAF4463A9")
				.build();
		/*CommLoginRequestProtobuf.CommLoginRequest request = CommLoginRequestProtobuf.CommLoginRequest.newBuilder()
				.setIdentifier("159")
				.setVersion(11)
				.setData("1631207340")
				.setCipher("4712B9A202EA8604B10B5C6510FFC45ED4E178C1")
				.build();*/

		byte[] bytes = MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.LOGIN_REQUEST)
				.setData(request.toByteString())
				.build()
				.toByteArray();
		session.getBasicRemote()
				.sendBinary(ByteBuffer.wrap(bytes),true);
		byte[] businessMsg = sendBusinessMsg();
		session.getBasicRemote().sendBinary(ByteBuffer.wrap(businessMsg),true);
		Thread.sleep(500000);

	}

	public static void main1(String[] args) {
		String s = StrFormatter.format("{} {}  {}   {}",
				"1631239629",
				11,
				96,
				NumberUtil.parseLong("1631239629") * 11);
		System.out.println(SecureUtil.sha1(s).toUpperCase());
	}

	/**
	 * 	发送业务消息
	 * @return
	 */
	public static byte[] sendBusinessMsg(){
		// TODO 业务process 请求体

		CommEnterDungeonRequestProtobuf.CommEnterDungeonRequest request = CommEnterDungeonRequestProtobuf.CommEnterDungeonRequest
				.newBuilder()
				.setType(1)
				.setStage(2)
				.build();
		byte[] bytes = MessageBaseProtobuf.MessageBase.newBuilder()
				.setName(CommandConstants.ENTER_DUNGEON)
				.setData(request.toByteString())
				.build()
				.toByteArray();

		return bytes;
	}

}
