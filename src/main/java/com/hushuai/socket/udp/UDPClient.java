package com.hushuai.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * UDP协议sockect核心类：DatagramSocket
 * 基于udp协议的Socket编程
 * created by it_hushuai
 * 2020/12/8 22:55
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        // 创建socket客户端
        DatagramSocket client = new DatagramSocket();
        // 生成数据包
        String message = "hello, udp!";
//        SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 11111);
//        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address);
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length,
                InetAddress.getLocalHost(), 11111);
        // 往服务端发送数据
        client.send(packet);
        client.close();
    }
}
