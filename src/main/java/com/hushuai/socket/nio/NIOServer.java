package com.hushuai.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * created by it_hushuai
 * 2020/12/14 20:27
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        Selector selector = Selector.open();
        serverSocketChannel.configureBlocking(false);// 服务端通道设为非阻塞
        // 服务端注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) { // 没有客户端通道进行IO操作
                System.out.println("1s内没有客户端发送数据");
                continue;
            }
            // 表示已经获取到关注的事件(可能是channel连接，也可能是channel读写数据)
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) { // 如果是channel连接
                    SocketChannel socketChannel = serverSocketChannel.accept(); // 获取连接到服务端的客户端channel
                    socketChannel.configureBlocking(false);
                    // 将客户端channel注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) { // 如果是channel读数据事件（第一遍循环不会发生，因为第一遍循只可能是没有任何事件发生或者channel连接事件）
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }
                iterator.remove();// 关注事件处理后，不再重复处理
            }
        }
    }
}
