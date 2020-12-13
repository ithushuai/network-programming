package com.hushuai.socket.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer: 可以让文件直接在内存中操作，无需拷贝
 * created by it_hushuai
 * 2020/12/13 20:44
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\tmp\\c.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1，FileChannel.MapMode.READ_WRITE：使用读写模式
         * 参数2，0：可以直接修改的数据起始索引
         * 参数3，channel.size()：映射到内存的大小，即可以直接修改的数据长度
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
        map.put(0, (byte)'h');
        // 修改中文
        byte[] bytes = "我".getBytes();
        for (int i = 0; i < bytes.length; i++) {
            map.put(7 + i, bytes[i]);
        }
        System.out.println("修改完成");
    }
}
