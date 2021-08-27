package com.gejian.pixel;

import com.gejian.pixel.netty.StockProxyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ljb
 * @date 2021年08月27日 9:39
 * @description
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.gejian.pixel.mapper")
public class MainApp implements ApplicationRunner {

	@Value("${socket.netty.port}")
	private int nettyPort;

	@Autowired
	private StockProxyServerInitializer stockProxyServerInitializer;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

	/**
	 * 启动Netty服务
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {

		// 创建网络服务器
		EventLoopGroup boss = new NioEventLoopGroup();
		// 创建Worker线程
		EventLoopGroup worker = new NioEventLoopGroup();

		try {
			// Netty服务启动类
			ServerBootstrap boot = new ServerBootstrap();
			// 采用NIO通道，自定义stockProxyServerInitializer初始化启动器
			boot.group(boss, worker).channel(NioServerSocketChannel.class)
					.childHandler(stockProxyServerInitializer);
			// 绑定端口， 同步阻塞监听
			ChannelFuture f = boot.bind(nettyPort).sync();
			// 开启channel监听器， 监听关闭动作
			log.info("netty start success,{}",nettyPort);
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			log.error("Can't start Netty Server Process", e);
			return;
		} finally {
			// 采用优雅的关闭方式
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}

	}
}
