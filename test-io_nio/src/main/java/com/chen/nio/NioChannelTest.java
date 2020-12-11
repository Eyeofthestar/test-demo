package com.chen.nio;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioChannelTest {
    public static void main(String[] args) {
//        非直接缓冲区方式
//        try {
////          通过流读取写出
//            FileInputStream inputStream=new FileInputStream(new File("E:\\t1.txt"));
//            FileOutputStream outputStream=new FileOutputStream(new File("E:\\t2.txt"));
////            获取通道
//            FileChannel inputStreamChannel = inputStream.getChannel();
//            FileChannel outputStreamChannel = outputStream.getChannel();
////            创建缓冲区
//            ByteBuffer buffer= ByteBuffer.allocate(1024);
////            inputStreamChannel.read(buffer)!=-1 如果没读取到数据则==-1
//            if (inputStreamChannel.read(buffer)!=-1){
////                切换到读的模式
//                buffer.flip();
////                直接通过缓存区写出
//                outputStreamChannel.write(buffer);
//                buffer.clear();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//       直接缓冲区方式
        /**
         *     public static FileChannel open(Path path, OpenOption... options)
         *         throws IOException
         *     {
         *         Set<OpenOption> set = new HashSet<OpenOption>(options.length);
         *         Collections.addAll(set, options);
         *         return open(path, set, NO_ATTRIBUTES);
         *     }
         */
        try {
            FileChannel inputChannel = FileChannel.open(Paths.get("E:\\t1.txt"), StandardOpenOption.READ);
            FileChannel outputChannel = FileChannel.open(Paths.get("E:\\t2.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE);
//           将文件映射到内存中，这个流程是十分消耗资源的，通常只有文件较大时使用缓冲区直接映射
            MappedByteBuffer inMap = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());
            MappedByteBuffer OutMap = outputChannel.map(FileChannel.MapMode.READ_WRITE, 0, inputChannel.size());
            byte bt[]=new byte[inMap.limit()];
            inMap.get(bt);
            OutMap.put(bt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
