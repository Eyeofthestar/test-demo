package com.chen.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1",8080);
            System.out.println("client01  接入连接");
//            scanner  监听客户端输入内容  scanner 状态也是监听阻塞状态
            Scanner scanner=new Scanner(System.in);
//            next  如果客户端有输入 next() 方法可以拿到输入内容返回字符串
            String next = scanner.next();
            socket.getOutputStream().write(next.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
