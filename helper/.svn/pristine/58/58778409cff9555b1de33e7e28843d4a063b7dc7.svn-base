package org.blue.helper.StringHelper.executor;

import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.controller.support.req.HttpPosReq;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;
import org.blue.helper.StringHelper.service.HttpPosService;
import org.blue.helper.StringHelper.utils.BeanConverUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Scope("prototype")
public class HttpPosExecutor implements Runnable{

    private static final Logger log=LoggerFactory.getLogger(HttpPosExecutor.class);

    private HttpPosReq httpPosReq;

    //闭锁
    private CountDownLatch startLatch;
    private CountDownLatch stopLatch;

    @Autowired
    private HttpPosService httpPosService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run() {
        try {
            startLatch.await();

            log.info("===================>"+Thread.currentThread().getName() + ":start");
            HttpPosRsp rsp=httpPosService.doPosRequester(this.httpPosReq);
            redisUtil.set(EncryptKey.HTTPREQUESTRESULT+Thread.currentThread().getName(),BeanConverUtil.beanToString(rsp));
            log.info("===================>"+Thread.currentThread().getName() + ":finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stopLatch.countDown();
        }
    }

    public HttpPosReq getHttpPosReq() {
        return httpPosReq;
    }

    public void setHttpPosReq(HttpPosReq httpPosReq) {
        this.httpPosReq = httpPosReq;
    }

    public CountDownLatch getStartLatch() {
        return startLatch;
    }

    public void setStartLatch(CountDownLatch startLatch) {
        this.startLatch = startLatch;
    }

    public CountDownLatch getStopLatch() {
        return stopLatch;
    }

    public void setStopLatch(CountDownLatch stopLatch) {
        this.stopLatch = stopLatch;
    }
}
