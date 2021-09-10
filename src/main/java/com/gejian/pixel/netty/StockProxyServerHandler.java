package com.gejian.pixel.netty;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.AttributeKeyConstants;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.CommLoginResponseProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.UserInterceptor;
import com.gejian.pixel.utils.ChannelHolder;
import com.gejian.pixel.utils.ChannelManager;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@ChannelHandler.Sharable
@Log4j2
public class StockProxyServerHandler extends SimpleChannelInboundHandler<MessageBaseProtobuf.MessageBase> {


	@Autowired
	private Map<String, Process> processInstances;

	@Autowired
	private UserInterceptor userInterceptor;

	@Autowired
	private StringRedisTemplate redisTemplate;


	/**
	 * 接收处理客户端发送数据
	 *
	 * @param channelHandlerContext
	 * @param messageBase
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void channelRead0(ChannelHandlerContext channelHandlerContext
			, MessageBaseProtobuf.MessageBase messageBase) throws Exception {
		String name = messageBase.getName();
		Process<AbstractMessageLite, AbstractMessageLite> process = processInstances.get(name);
		if (Objects.isNull(process)) {
			log.error("请求:{}没有对应的Process处理器。", name);
			return;
		}
		try {
			ChannelHolder.put(channelHandlerContext.channel());
			ChannelManager.add(channelHandlerContext.channel());
			Type[] messageType = getMessageType(process.getClass());
			Class<AbstractMessageLite> paramClazz = (Class<AbstractMessageLite>) messageType[0];
			Class<AbstractMessageLite> resultClazz = (Class<AbstractMessageLite>) messageType[1];
			byte[] dataBytes = messageBase.getData().toByteArray();
			AbstractMessageLite abstractMessageLite = ReflectUtil.newInstance(paramClazz);
			AbstractMessageLite messageLite = (AbstractMessageLite) abstractMessageLite
					.getParserForType().parseFrom(dataBytes);
			// 用户统一拦截
			boolean flag = userInterceptor.doFilter(channelHandlerContext.channel(), name);
			AbstractMessageLite resultObj;
			if (flag) {
				log.info("{}请求入参:{}",name,messageLite);
				resultObj = process.doProcess(messageLite);
				log.info("{}请求出参:{}",name,resultObj);
				if (resultObj instanceof CommLoginResponseProtobuf.CommLoginResponse) {
					CommLoginResponseProtobuf.CommLoginResponse loginRes = (CommLoginResponseProtobuf.CommLoginResponse)
							resultObj;
					createUserSession(channelHandlerContext.channel(), loginRes);
				}
			} else {
				resultObj = ReflectUtil.newInstance(resultClazz);
				MessageLite.Builder builder = resultObj.toBuilder();
				ReflectUtil.invoke(builder, "setResult", ErrorEnum.ERROR_SESSION_EXPIRED);
				resultObj = (AbstractMessageLite) builder.build();
			}
			MessageBaseProtobuf.MessageBase.Builder builder = MessageBaseProtobuf.MessageBase.newBuilder()
					.setName(name.replace("_REQUEST","_RESPONSE"));
			if (Objects.nonNull(resultObj)) {
				MessageLite.Builder resultBuilder = resultObj.toBuilder();
				if (!CommandConstants.LOGIN_REQUEST.equals(name)){
					ReflectUtil.invoke(resultBuilder, "setRequest", messageLite);
					resultObj = (AbstractMessageLite) resultBuilder.build();
				}
				ByteString bytes = resultObj.toByteString();
				builder.setData(bytes);
			}
			MessageBaseProtobuf.MessageBase reply = builder.build();

			channelHandlerContext.channel()
					.writeAndFlush(reply);
		} catch (Exception e) {
			log.error("处理请求时发生错误！", e);
			channelHandlerContext.channel().close();
		} finally {
			ChannelHolder.clear();
		}


	}

	private void createUserSession(Channel channel, CommLoginResponseProtobuf.CommLoginResponse loginRes) {
		if (loginRes.getResult()==0) {
			String identifier = loginRes.getPlayer().getIdentifier();
			String session = loginRes.getPlayer().getSession();
			UserInfo userInfo = new UserInfo();
			userInfo.setIdentifier(Integer.valueOf(identifier));
			userInfo.setSession(session);
			channel.attr(AttributeKeyConstants.USER_INFO_ATTRIBUTE_KEY).set(userInfo);
			String key = StrUtil.format(RedisKeyConstants.USER,userInfo.getIdentifier());
			redisTemplate.opsForValue().set(key,session, 1, TimeUnit.DAYS);
		}
	}

	private Type[] getMessageType(Class<?> targetClass) {
		Type matchedGenericInterface = null;
		while (Objects.nonNull(targetClass)) {
			Type[] interfaces = targetClass.getGenericInterfaces();
			for (Type type : interfaces) {
				if (type instanceof ParameterizedType &&
						(Objects.equals(((ParameterizedType) type).getRawType(), Process.class))) {
					matchedGenericInterface = type;
					break;
				}
			}
			targetClass = targetClass.getSuperclass();
		}
		if (Objects.isNull(matchedGenericInterface)) {
			return new Type[]{Object.class, Object.class};
		}

		Type[] actualTypeArguments = ((ParameterizedType) matchedGenericInterface).getActualTypeArguments();
		if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
			return actualTypeArguments;
		}
		return new Type[]{Object.class, Object.class};
	}

}

