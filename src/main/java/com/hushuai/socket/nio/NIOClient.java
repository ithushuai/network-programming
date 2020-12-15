package com.hushuai.socket.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * created by it_hushuai
 * 2020/12/14 21:17
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel clientChannel = SocketChannel.open();
        // 设置非阻塞
        clientChannel.configureBlocking(false);
        // 服务端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8888);
        if (!clientChannel.connect(inetSocketAddress)) {
            while (!clientChannel.finishConnect()) {
                System.out.println("客户端连接中...");
            }

        }
        // 连接成功，发送数据
        String message = "hello nio";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        clientChannel.write(buffer);
        System.in.read();
    }
}
