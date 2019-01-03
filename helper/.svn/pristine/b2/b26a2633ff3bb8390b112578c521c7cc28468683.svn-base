package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.HelperApplication;
import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.controller.support.req.HttpPosReq;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;
import org.blue.helper.StringHelper.executor.HttpPosExecutor;
import org.blue.helper.StringHelper.service.HttpRequestService;
import org.blue.helper.StringHelper.utils.BeanConverUtil;
import org.blue.helper.StringHelper.utils.SpringUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class HttpRequestServiceImpl implements HttpRequestService {
    private static final Logger log=LoggerFactory.getLogger(HttpRequestServiceImpl.class);
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<HttpPosRsp> httpRequest(HttpPosReq httpPosReq) {

        if(httpPosReq.getConcurrency()==null || httpPosReq.getConcurrency()<1) httpPosReq.setConcurrency(1);//并发数至少为1

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch stopLatch = new CountDownLatch(httpPosReq.getConcurrency());

        for (int i = 0; i <httpPosReq.getConcurrency() ; i++) {
            HttpPosExecutor httpPosExecutor=SpringUtil.getBean(HttpPosExecutor.class);
            httpPosExecutor.setHttpPosReq(httpPosReq);
            httpPosExecutor.setStartLatch(startLatch);
            httpPosExecutor.setStopLatch(stopLatch);
            new Thread(httpPosExecutor,"T"+i).start();
        }

        log.info("======================>All Threads are ready to start");
        startLatch.countDown(); //启动器
        List<HttpPosRsp> rspList=new ArrayList<HttpPosRsp>();
        try {
            stopLatch.await();
            log.info("======================>All Threads are finished");
            for (int i = 0; i <httpPosReq.getConcurrency() ; i++) {
                String rsp=redisUtil.get(EncryptKey.HTTPREQUESTRESULT+"T"+i,null);
                redisUtil.del(EncryptKey.HTTPREQUESTRESULT+"T"+i);
                HttpPosRsp httpPosRsp=BeanConverUtil.stringToBean(rsp,HttpPosRsp.class);
                rspList.add(httpPosRsp);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rspList;
    }
}
