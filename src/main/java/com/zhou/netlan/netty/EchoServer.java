package com.zhou.netlan.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
    public static void main(String[] args) {

        int port =6666;
        NioEventLoopGroup parentThreadGroup = new NioEventLoopGroup();
        NioEventLoopGroup childThreadGroup = new NioEventLoopGroup();


        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentThreadGroup, childThreadGroup);
        serverBootstrap.bind(port);
        serverBootstrap.channel(NioServerSocketChannel.class);

    }
}