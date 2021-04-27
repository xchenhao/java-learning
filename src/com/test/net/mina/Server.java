package com.test.net.mina;
// http://mina.apache.org/


import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    // 可以用 telnet localhost 9999 测试
    public static void main(String[] args) {
        // 创建一个非阻塞的 Server 端 Socket NIO
        SocketAcceptor acceptor = new NioSocketAcceptor();
        DefaultIoFilterChainBuilder filterChain = acceptor.getFilterChain();
        // 设定一个过滤器，一行一行地读取数据（\r\n）
        filterChain.addLast("myChain", new ProtocolCodecFilter(new TextLineCodecFactory()));
        // 设置服务器端的消息处理器
        acceptor.setHandler(new MinaServerHandler());
        int port = 9999;  // 服务器的端口号
        try {
            // 绑定端口，启动服务器（不会阻塞，立即返回）
            acceptor.bind(new InetSocketAddress(port));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("MINA Server is running, listening on: " + port);


    }
}
