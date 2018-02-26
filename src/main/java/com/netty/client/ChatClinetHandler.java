package com.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @类名: ${CLASS_NAME}
 * @包名: com.netty.client
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @日期: 2018/2/26 11:48
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 */
public class ChatClinetHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }
}
