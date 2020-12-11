package com.chen.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {
    public static void main(String[] args) {
        String str="test data";
//      创建一个指定大小的缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(1024);
//      查看默认的 各个指针值0  1024  1024
//        System.out.println(buffer.position());
//        System.out.println(buffer.limit());
//        System.out.println(buffer.capacity());
//      put 测试  使用put 将数据存储到缓冲区    9  1024   1024
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

//        切换读取模式     0   9   1024
        buffer.flip();
        System.out.println("--------------------flip---------------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

//        使用get 读取缓冲区的内容   test data    9   9   1024
        byte bt[]=new byte[buffer.limit()];
        buffer.get(bt);
        System.out.println("--------------------get---------------------");
        System.out.println(new String(bt));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        // 5. rewind() : 可重复读  0     9      1024
        buffer.rewind();

        System.out.println("-----------------rewind()----------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

// 6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态，数据还在，但是指针位置变了  0   1024    1024
        buffer.clear();

        System.out.println("-----------------clear()----------------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

// 所以这里还是能取得到数据的  t只读取到第一个 意味着他并不是直接清空数据而是重置指针覆盖数据
        System.out.println((char) buffer.get());

    }
}
