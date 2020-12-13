package com.hushuai.socket.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件Channel实现文件拷贝
 * created by it_hushuai
 * 2020/12/13 19:06
 */
public class ChannelCopyFile {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("E:\\tmp\\a.txt");
        FileChannel channel01 = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("E:\\tmp\\b.txt");
        FileChannel channel02 = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            try {
                buffer.clear(); // 每次循环读取，重置buffer内部指针状态
                int read = channel01.read(buffer);
                if (read != -1) {
                    buffer.flip(); // 切换buffer读到写的状态
                    channel02.write(buffer);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        inputStream.close();
        outputStream.close();
    }
}
