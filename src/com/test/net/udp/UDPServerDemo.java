package com.test.net.udp;

import java.io.IOException;
import java.net.*;

public class UDPServerDemo {

    public static void main(String[] args) {
        String info = "good good 学习，天天 UP";
        byte[] bytes = info.getBytes();

        try {
            // 封装一个数据报包
            DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName("127.0.0.1"), 8000);  // 目标端口
            DatagramSocket socket = new DatagramSocket(9000);  // 本程序的端口
            socket.send(dp);
            socket.close();


        } catch (UnknownHostException |SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
