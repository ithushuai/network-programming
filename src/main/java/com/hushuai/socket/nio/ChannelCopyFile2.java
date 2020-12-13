package com.hushuai.socket.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通道数据直接拷贝
 * created by it_hushuai
 * 2020/12/13 19:06
 */
public class ChannelCopyFile2 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("E:\\tmp\\a.txt");
        FileChannel channel01 = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("E:\\tmp\\c.txt");
        FileChannel channel02 = outputStream.getChannel();

        channel02.transferFrom(channel01, 0, channel01.size());
        inputStream.close();
        outputStream.close();
    }
}
