package com.chen.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer2 {
    public static void main(String[] args) {
        new NioServer2().start();
    }

    private void start() {
        try {
            ServerSocketChannel openChannel = ServerSocketChannel.open();
            openChannel.bind(new InetSocketAddress(8080));
            openChannel.configureBlocking(false);
            Selector selector = Selector.open();
            openChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select(1000) == 0) {
                    System.out.println("has no message");
                    continue;
                }
                System.out.println(" 已检测到 接入连接");
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        accept(selectionKey, selector);
                    }
                    if (selectionKey.isReadable()) {
                        read(selectionKey);
                    }
                    if (selectionKey.isValid() && selectionKey.isWritable()) {
                        write(selectionKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void accept(SelectionKey key, Selector selector) throws IOException {
        // 这里由于只有ServerSocketChannel才会有客户端连接建立事件，因而这里可以直接将
        // Channel强转为ServerSocketChannel对象
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        // 获取客户端的连接
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        // 将客户端连接Channel注册到Selector中，并且监听该Channel的OP_READ事件，
        // 也即等待客户端发送数据到服务器端
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        // 这里由于只有ServerSocketChannel才会有客户端连接建立事件，因而这里可以直接将
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[1024]);
        int read = channel.read(byteBuffer);
        if (read == -1) {
            channel.close();
            return;
        }
//      处理数据
//      由于已经读取到了数  则吧channel的 职责改成 读 或者写
        System.out.println(new String(byteBuffer.array()));
        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

    }

    private void write(SelectionKey key) throws IOException {
        String MESSAGE = "hello my friend";
        ByteBuffer byteBuffer = ByteBuffer.wrap(MESSAGE.getBytes(StandardCharsets.UTF_8));
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isOpen()) {
            channel.write(byteBuffer);
        }
        if (!byteBuffer.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }
        byteBuffer.compact();
    }


}
