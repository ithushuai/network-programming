package com.hushuai.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO服务器模型
 * created by it_hushuai
 * 2020/12/12 16:38
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 创建服务端
        ServerSocket server = new ServerSocket(8888);
        // 创建一个线程池，用于处理客户端发送的数据
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 服务端启动，监听客户端连接
        System.out.println("服务端启动了...");
        while (true) {
            final Socket clientSocket = server.accept(); // 与客户端建立了连接
            System.out.println("与一个客户端建立连接");

            // 线程池开辟线程处理客户端通讯
            threadPool.execute(() -> {
                handler(clientSocket);
            });
        }
    }

    // 服务端处理客户端通讯方法
    private static void handler(Socket socket) {
        System.out.println("处理当前客户端通讯的线程：" + Thread.currentThread().getName());
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[1024];// 字节数组，用于读取客户端发送在字节流数据
            while (true) { // 与客户端保持数据通讯
                // 阻塞，read操作是阻塞的，不要放在while条件中，线程会一直监听客户端发送数据，一旦有数据过来，则进行往后走，当客户端断开连接，则
                // read结果为-1，此时跳出循环
                int read = inputStream.read(data);
                if (read > 0) {
                    System.out.println(new String(data, 0,  read));
                } else {
                    System.out.println("客户端断开通信...");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
