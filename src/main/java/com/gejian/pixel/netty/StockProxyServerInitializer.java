package com.gejian.pixel.netty;

import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

@Slf4j
@Component
public class StockProxyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${socket.netty.debug:false}")
    private boolean debug;

    @Autowired
    private StockProxyServerHandler stockProxyServerHandler;


//	@Override
//	protected void initChannel(SocketChannel ch) throws Exception {
//		ChannelPipeline pipeline = ch.pipeline();
//
//		// 可以打印出报文的请求和响应细节
//        if(debug) {
//            pipeline.addLast(new LoggingHandler());
//        }
//        //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
//        pipeline.addLast(new ProtobufVarint32FrameDecoder());
//        //服务器端接收的是约定的StockMessage对象，所以这边将接收对象进行解码
//        pipeline.addLast(new ProtobufDecoder(MessageBaseProtobuf.MessageBase.getDefaultInstance()));
//        //Google Protocol Buffers编码器
//        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
//        //Google Protocol Buffers编码器
//        pipeline.addLast(new ProtobufEncoder());
//        // 自定义数据接收处理器
//        pipeline.addLast(stockProxyServerHandler);
//	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		// 在调试期加入日志功能，从而可以打印出报文的请求和响应细节
		if(debug) {
			pipeline.addLast(new LoggingHandler());
		}
		pipeline.addLast(new HttpServerCodec());
		// 支持参数对象解析， 比如POST参数， 设置聚合内容的最大长度
		pipeline.addLast(new HttpObjectAggregator(65536));
		// 支持大数据流写入
		pipeline.addLast(new ChunkedWriteHandler());
		// 支持WebSocket数据压缩
		pipeline.addLast(new WebSocketServerCompressionHandler());
		// Websocket协议配置， 设置访问路径
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true));

		//解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
		pipeline.addLast(new ProtobufVarint32FrameDecoder());
		//Google Protocol Buffers 长度属性编码器
		pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());


		// 协议包解码
		pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
			@Override
			protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objs) throws Exception {
				log.info("received client msg ------------------------");
				if (frame instanceof TextWebSocketFrame) {
					// 文本消息
					TextWebSocketFrame textFrame = (TextWebSocketFrame)frame;
					log.info("MsgType is TextWebSocketFrame");
				} else if (frame instanceof BinaryWebSocketFrame) {
					// 二进制消息
					ByteBuf buf = ((BinaryWebSocketFrame) frame).content();
					objs.add(buf);
					// 自旋累加
					buf.retain();
					log.info("MsgType is BinaryWebSocketFrame");
				} else if (frame instanceof PongWebSocketFrame) {
					// PING存活检测消息
					log.info("MsgType is PongWebSocketFrame ");
				} else if (frame instanceof CloseWebSocketFrame) {
					// 关闭指令消息
					log.info("MsgType is CloseWebSocketFrame");
					ch.close();
				}

			}
		});
		// 协议包编码
		pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
			@Override
			protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
				ByteBuf result = null;
				if (msg instanceof MessageLite) {
					// 没有build的Protobuf消息
					result = wrappedBuffer(((MessageLite) msg).toByteArray());
				}
				if (msg instanceof MessageLite.Builder) {
					// 经过build的Protobuf消息
					result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
				}

				// 将Protbuf消息包装成Binary Frame 消息
				WebSocketFrame frame = new BinaryWebSocketFrame(result);
				out.add(frame);
			}
		});
		// Protobuf消息解码器
		pipeline.addLast(new ProtobufDecoder(MessageBaseProtobuf.MessageBase.getDefaultInstance()));

		// 自定义数据处理器
		pipeline.addLast(stockProxyServerHandler);

	}

}

