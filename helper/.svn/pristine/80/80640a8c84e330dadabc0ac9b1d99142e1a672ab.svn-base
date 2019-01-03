package org.blue.helper.StringHelper.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/29
 * @Version 1.0.0
 **/
@Component
@Scope("prototype")
public class FileExecutor implements Runnable{
    private static final Logger LOGGER=LoggerFactory.getLogger(FileExecutor.class);

    //闭锁
    private CountDownLatch stopLatch;
    private int length;
    private int startIndex;
    private File file;
    private File tmp;

    public FileExecutor(CountDownLatch stopLatch, int length, int startIndex, File file, File tmp) {
        this.stopLatch = stopLatch;
        this.length = length;
        this.startIndex = startIndex;
        this.file = file;
        this.tmp = tmp;
    }

    @Override
    public void run() {
        try (RandomAccessFile tmpRaf=new RandomAccessFile(tmp,"rw");
             RandomAccessFile fileRaf=new RandomAccessFile(file,"rw");){
            fileRaf.seek(startIndex);
            tmpRaf.seek(startIndex);
            byte[] b=new byte[length];
            fileRaf.read(b);
            tmpRaf.write(b);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(),e);
        } finally {
            stopLatch.countDown();
        }
    }

    public CountDownLatch getStopLatch() {
        return stopLatch;
    }

    public void setStopLatch(CountDownLatch stopLatch) {
        this.stopLatch = stopLatch;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getTmp() {
        return tmp;
    }

    public void setTmp(File tmp) {
        this.tmp = tmp;
    }
}
