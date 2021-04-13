package com.test.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerDemo {
    public static void main(String[] args) {
        // 创建一个服务端的 Socket(1024~65535)
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务器已启动，正在等待客户端的连接...");

            // 等待客户端的连接，造成阻塞
            // 如果有客户端连接成功，立即返回一个 Socket 对象
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接成功：" +  serverSocket.getInetAddress().getHostAddress());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 通过输入流读取网络数据，如果没有数据，那么会阻塞
            String info = bufferedReader.readLine();
            System.out.println(info);

            // 获取输出流，向客户端返回消息
            PrintStream printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            printStream.println("echo: " + info);
            printStream.flush();
            // 关闭
            printStream.close();

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
