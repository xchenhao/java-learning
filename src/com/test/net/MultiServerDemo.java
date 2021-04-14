package com.test.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 处理多个客户端
 * 主线程用于监听客户端的连接，每次有连接成功，开启一个线程来处理该客户的消息
 */
public class MultiServerDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务器已启动，正在等待连接。。。");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress());

                executorService.execute(new UserThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 用于处理客户端请求的线程任务
 */
class UserThread implements Runnable {
    private Socket socket;

    public UserThread (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));

            String info = bufferedReader.readLine();
            System.out.println(info);

            printStream.println("echo:" + info);
            printStream.flush();;
            printStream.close();;

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
