package com.chen.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) {
        byte bt[]=new byte[1024];
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            while (true){
//                线程阻塞监听 连接
                System.out.println("等待连接");
                Socket accept = serverSocket.accept();
                System.out.println("连接就绪，等待数据");
//                read 模式下是阻塞状态  将监听的内容读取到bt上
                accept.getInputStream().read(bt);
                System.out.println("数据就绪 输出");
                System.out.println(new String(bt));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
