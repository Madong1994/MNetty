package com.netty.server;

import com.sun.scenario.effect.impl.prism.PrImage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @类名: ${CLASS_NAME}
 * @包名: com.netty.server
 * @描述: ${TODO}(用一句话描述该文件做什么)
 * @日期: 2018/2/26 11:25
 * @版本: V1.0
 * @创建人：马东
 * @修改人：马东
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            if (channel != incoming){
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s + "\n");
            } else {
                channel.writeAndFlush("[you]" + s + "\n");
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------handlerAdded-------");
        Channel incoming = ctx.channel();
        channels.writeAndFlush("[server] - " + incoming.remoteAddress() + "加入\n");
        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------handlerRemoved-------");
        Channel incoming = ctx.channel();
        channels.writeAndFlush("[server] - " + incoming.remoteAddress() + "离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------channelActive-------");
        Channel incoming = ctx.channel();
        System.out.println("[client] - " + incoming.remoteAddress() + "在线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----------channelInactive-------");
        Channel incoming = ctx.channel();
        System.out.println("[client] - " + incoming.remoteAddress() + "掉线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("-----------exceptionCaught-------");
        Channel incoming = ctx.channel();
        System.out.println("[client] - " + incoming.remoteAddress() + "异常\n");
        cause.printStackTrace();
        ctx.close();
    }
}
