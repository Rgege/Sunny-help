package org.blue.helper.study.java.io;

import org.blue.helper.study.java.io.constant.PathConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {

    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream(new File(PathConstants.ROOT_PATH+"testBuffer.txt"));
             //获取Channel
             FileChannel fc = fin.getChannel();
        ) {
            //申请Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fc.read(byteBuffer);
            byteBuffer.flip();//读写转换
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
