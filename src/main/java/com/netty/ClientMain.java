package com.netty;

import com.netty.client.ChatClientInitializer;
import com.netty.client.ChatClinetHandler;
import com.netty.server.ChatServerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @类名: ${CLASS_NAME}
 * @包名: com.netty
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @日期: 2018/2/26 11:24
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 */
public class ClientMain {
    private final String host;
    private final int port;

    public ClientMain(String host,int port){
        this.host = host;
        this.port = port;
    }
     public void run() throws Exception{
         EventLoopGroup group = new NioEventLoopGroup();
         try {
             Bootstrap bootstrap = new Bootstrap()
                     .group(group)
                     .channel(NioSocketChannel.class)
                     .handler(new ChatClientInitializer());
             Channel channel = bootstrap.connect(host,port).sync().channel();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             while (true){
                 channel.writeAndFlush(reader.readLine() + "\r\n");
             }
         } catch (Exception e) {
             e.printStackTrace();
         }finally {
             group.shutdownGracefully();
         }
     }

    public static void main(String[] args) throws Exception {
        new ClientMain("localhost",8080).run();
    }
}
