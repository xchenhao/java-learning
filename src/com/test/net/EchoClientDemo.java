package com.test.net;

import java.io.*;
import java.net.Socket;

public class EchoClientDemo {
    public static void main(String[] args) {
        // 创建一个 Socket 对象，指定要连接的服务器
        try {
            Socket socket = new Socket("localhost", 6666);
            PrintStream printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printStream.println("hello, my name is Bin");
            printStream.flush();

            // 读取服务端返回的数据
            String info = bufferedReader.readLine();
            System.out.println(info);

            printStream.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
