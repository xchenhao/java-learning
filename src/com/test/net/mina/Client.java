package com.test.net.mina;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // 创建 NIO 连接
        NioSocketConnector connector = new NioSocketConnector();
        DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
        // filterChain.addLast("myChain", new ProtocolCodecFilter(new TextLineCodecFactory()));
        filterChain.addLast("objectfilter", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connector.setHandler(new MinaClientHandler());
        connector.setConnectTimeoutMillis(1000*10);
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("localhost", 9999));// 连接服务器
        connectFuture.awaitUninterruptibly();  // 等待连接成功
        Scanner scanner = new Scanner(System.in);

        while (true) {
//            System.out.println("请输入：");
//            String info = scanner.nextLine();
//            connectFuture.getSession().write(info);  // 发送消息

            // 以对象方式传输数据
            Message msg = new Message();
            System.out.println("from: ");
            msg.setFrom(scanner.nextLine());
            System.out.println("to: ");
            msg.setTo(scanner.nextLine());
            System.out.println("info: ");
            msg.setInfo(scanner.nextLine());

            msg.setType("send");

            connectFuture.getSession().write(msg);
        }

        // 等待服务器连接关闭，结束长连接
        // connectFuture.getSession().getCloseFuture().awaitUninterruptibly();
        // connector.dispose();  // 释放连接


    }
}
