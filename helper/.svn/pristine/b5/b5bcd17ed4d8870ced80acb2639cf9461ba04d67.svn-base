package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger=LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Override
    @Async
    public void asyncAct() {
        logger.info("===============>into AsyncServiceImpl.asyncAct() CurrentThread:"+Thread.currentThread().getName());
        logger.info("===============>do something Async.................................");
        logger.info("===============>out AsyncServiceImpl.asyncAct() CurrentThread:"+Thread.currentThread().getName());
        throw new NullPointerException();
    }
}
