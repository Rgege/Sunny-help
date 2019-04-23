package org.blue.helper.study.java.io;

import org.blue.helper.study.java.io.constant.PathConstants;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将文件 映射到内存中 对映射的修改就等于修改文件本身  这样操作是很快的
 */
public class MapperFileToMemory {

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile(PathConstants.ROOT_PATH+"testMapper.txt", "rw");
             FileChannel fc = raf.getChannel();
        ) {
            // 将文件映射到内存中
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());
            while (mbb.hasRemaining()) {
                System.out.print((char) mbb.get());
            }
            mbb.put(0, (byte) 98); // 修改文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
