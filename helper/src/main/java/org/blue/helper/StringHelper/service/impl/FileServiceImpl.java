package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.common.exception.HelperException;
import org.blue.helper.StringHelper.executor.FileExecutor;
import org.blue.helper.StringHelper.executor.HttpPosExecutor;
import org.blue.helper.StringHelper.service.FileService;
import org.blue.helper.StringHelper.utils.NumberUtils;
import org.blue.helper.StringHelper.utils.SpringUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.leftPad;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2018/12/28
 * @Version 1.0.0
 **/
@Service
public class FileServiceImpl implements FileService {

    @Override
    public void uploadFile(String saveUrl, double fileSize, MultipartFile file) {
        //先创建一个临时文件
        File tmpFile=new File(saveUrl+".tmp");
        if (tmpFile.exists()){
            throw new HelperException(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Flie:"+saveUrl+" already existed");
        }else {
            try {
                tmpFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new HelperException(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Failed to create temporary file:"+saveUrl+".tmp",e);
            }
        }

        //通过file size 确定要几个线程
        final double m=1024*1024d;
        int threadNum;
        double mbs=fileSize/m;
        if (NumberUtils.lessOrEqD(mbs,100)){//100M以内单线程模式
            threadNum=1;
        }else if (NumberUtils.leftOpenInterval(100,mbs,500)){//500M 5个线程
            threadNum=5;
        }else if (NumberUtils.leftOpenInterval(500,mbs,1000)){//1G 10个线程
            threadNum=10;
        }else{ //最多开15个线程
            threadNum=15;
        }

        CountDownLatch stopLatch = new CountDownLatch(threadNum);
        ThreadPoolTaskExecutor fileUploadThreadPool= (ThreadPoolTaskExecutor) SpringUtil.getBean("fileUploadThreadPool");
        for (int i = 0; i <threadNum ; i++) {
            FileExecutor fileExecutor=(FileExecutor) SpringUtil.getBean(FileExecutor.class);
            fileExecutor.setTmp(tmpFile);
            int length;
            if (fileSize%threadNum==0){
               // length=fileSize/threadNum;
//                fileExecutor.setLength(length);
            }
            fileUploadThreadPool.execute(fileExecutor);
        }
        try {
            stopLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
