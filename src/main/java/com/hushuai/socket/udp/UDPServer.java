package com.hushuai.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 基于udp协议的Socket服务端
 * created by it_hushuai
 * 2020/12/8 23:01
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        // 创建服务端
        DatagramSocket server = new DatagramSocket(11111);
        // 创建数据包
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        server.receive(packet);
        String host = packet.getAddress().getHostAddress();
        byte[] data = packet.getData();
        System.out.println("接收到来自" + host + "的消息：" + new String(data));
        server.close();
    }
}
