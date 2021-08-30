package com.gejian.pixel.netty;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author ZhouQiang
 * @date 2021/8/30$
 */
@Configuration
public class SocketExecutorConfig {

	@Value("${ws.work.thread.num:50}")
	private Integer workThreads;

	@Bean
	public EventExecutorGroup eventLoopGroup(){
		return new DefaultEventExecutorGroup(
				workThreads,
				new ThreadFactory() {

					private AtomicInteger threadIndex = new AtomicInteger(0);

					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "NettyServerProcessThread_"
								+ this.threadIndex.incrementAndGet());
					}
				});
	}

}
