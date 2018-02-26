package com.netty.client;

import com.netty.server.ChatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * @类名: ${CLASS_NAME}
 * @包名: com.netty.client
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @日期: 2018/2/26 11:49
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("handler",new ChatClinetHandler());

//        System.out.println("client:" + socketChannel.remoteAddress() + "连接上");
    }
}
