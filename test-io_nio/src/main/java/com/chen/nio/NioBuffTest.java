package com.chen.nio;

import java.nio.ByteBuffer;

public class NioBuffTest {
    public static void main(String[] args) {
        String str="testlalal";
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

//		切换到读取模式 读取一部分缓冲区内容
        byte bt[]=new byte[1024];
        buffer.flip();
        buffer.get(bt,0,4);
//		创建 mark 标记
        buffer.mark();
//		查看当前posotion 与limiti指针
        System.out.println(new String(bt));
        System.out.println(buffer.position());   // 4
        System.out.println(buffer.limit());     //  9
//		读取剩下的内容查看指针
//             buffer.get(bt,offset,length);  offset 表示存储容器 bt 从那个位置开始存储  length 表示读取几个数据，超了会报错
        buffer.get(bt,4,2);
        System.out.println(new String(bt));
        System.out.println(buffer.position()); // 6
        System.out.println(buffer.limit());   //  9
//      返回标记节点
        buffer.reset();
//      判断缓冲区是否还有可操作数据
        if (buffer.hasRemaining()) {
            int remainging=buffer.remaining();  // 返回剩余可操作性的空间值  5
            buffer.get(bt,0,remainging);
        }
        System.out.println(new String(bt));  // lalala
    }
}
