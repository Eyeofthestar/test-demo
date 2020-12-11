package com.chen.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioClient {
    public static void main(String[] args) {
        try {
            new NioClient().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()){
                    read(key);
                }
                if (key.isWritable()){
                    write(key,selector);
                }
                if (key.isConnectable()){
                    connect(key,selector);
                }
            }
        }
    }

    private void connect(SelectionKey key,Selector selector) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.finishConnect();
        channel.register(selector,SelectionKey.OP_WRITE);
    }

    private void  read(SelectionKey key) throws IOException {
        SocketChannel channel= (SocketChannel) key.channel();
        ByteBuffer byteBuffer=ByteBuffer.wrap(new byte[1024]);
        int len = channel.read(byteBuffer);
        if (len== -1){
            channel.close();
            return;
        }
        System.out.println(new String(byteBuffer.array()));
    }

    private void  write(SelectionKey key,Selector selector) throws IOException {
        SocketChannel channel= (SocketChannel) key.channel();
        String MESSAGE="hello~~~~";
        channel.write(ByteBuffer.wrap(MESSAGE.getBytes(StandardCharsets.UTF_8)));
        channel.register(selector,SelectionKey.OP_READ);
    }
}
