package com.hnzy.hot.server2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户登录 服务端
 *
 */

public class Server {
    public static void main(String[] args) {

        try {
            // 1.创建一个服务器端的 Socket，即 ServerSocket，指定绑定的端，并监听
            ServerSocket server = new ServerSocket(6000);
            // 2.调用 accept 方法开始监听，等待客户端连接
            System.out.println("****服务器开始启动，等待客户端上线****");
            Socket socket = server.accept();
            // 3.获取一个输入流，用来读取客户端所发送的登录信息
            InputStream is = socket.getInputStream();// 字节输入流
            InputStreamReader isr = new InputStreamReader(is);// 将字节流转为
            BufferedReader br = new BufferedReader(isr);// 为输入流添加缓冲

            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说" + info);
            }
            socket.shutdownInput();// 关闭输入流

            // 4.获取输出流
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);// 包装打印流
            pw.write("好的，我收到消息了，你可以出去玩了");
            pw.flush();
            socket.shutdownOutput();

            // 5.关闭资源
            pw.close();
            br.close();
            isr.close();
            is.close();
            server.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
