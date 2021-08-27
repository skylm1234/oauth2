package com.gejian.pixel.netty;

import com.gejian.pixel.proto.MessageBaseProtobuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
// 代表可以被多个channel线程安全共享
@ChannelHandler.Sharable
@Log4j2
public class StockProxyServerHandler extends SimpleChannelInboundHandler<MessageBaseProtobuf.MessageBase> {

	@Autowired
    private TaskExecutor taskExecutor;

    /**
     * 负责客户端Channel管理(线程安全)
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

 	/**
     * 接收处理客户端发送数据
     * @param channelHandlerContext
     * @param messageBase
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBaseProtobuf.MessageBase messageBase) throws Exception {
		// 异步处理
        taskExecutor.execute(() ->{
            try {
            	// 异步线程处理业务逻辑
				System.out.println("messageBase = " + messageBase);
				String name = messageBase.getName();

			}catch(Exception e){
            	log.error(e);
            }
    	});
	}

}

