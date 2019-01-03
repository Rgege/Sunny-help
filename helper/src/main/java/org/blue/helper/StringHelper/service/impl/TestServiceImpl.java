package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.aop.annotation.StartInit;
import org.blue.helper.StringHelper.persistence.TestMapper;
import org.blue.helper.StringHelper.persistence.entity.pojo.TestPOJO;
import org.blue.helper.StringHelper.service.AsyncService;
import org.blue.helper.StringHelper.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    private static final Logger logger=LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private AsyncService asyncService;
    @Override
    public void testAdd(TestPOJO pojo) {
        testMapper.insert(pojo);
    }

    @Override
    public void testAsync() {
        logger.info("===============>into TestServiceImpl.testAsync() CurrentThread:"+Thread.currentThread().getName());
        logger.info("===============>do something before asyncAct...............................");
        asyncService.asyncAct();
        logger.info("===============>do something after asyncAct...............................");
        logger.info("===============>out TestServiceImpl.testAsync() CurrentThread:"+Thread.currentThread().getName());

    }
//
//    @Override
//    @StartInit(order = 1)
//    public void test() {
//        logger.info("================== 启动初始化order=1  ======================");
//    }
//
//    @Override
//    @StartInit(order = 2)
//    public void test2() {
//        logger.info("================== 启动初始化order=2 ======================");
//    }
//
//    @Override
//    @StartInit(order = 3)
//    public void test3() {
//        logger.info("================== 启动初始化order=3 ======================");
//    }
//
//    @Override
//    @StartInit(order = 4)
//    public void test4() {
//        logger.info("================== 启动初始化order=4 ======================");
//    }
//
//    @Override
//    @StartInit(order = 1)
//    public void test5() {
//        logger.info("================== 启动初始化order=5 ======================");
//    }
}
