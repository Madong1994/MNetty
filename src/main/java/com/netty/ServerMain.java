package com.netty;

import com.netty.server.ChatServerInitializer;
import com.sun.deploy.cache.InMemoryLocalApplicationProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @类名: ${CLASS_NAME}
 * @包名: com.netty
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @日期: 2018/2/26 11:24
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 */
public class ServerMain {
    private int port;
    public ServerMain(int port){
        this.port = port;
    }
    public void run() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            System.out.println("ServerMain启动了...");
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new ServerMain(port).run();
    }
}
