package org.blue.helper.StringHelper.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig implements AsyncConfigurer {
    Logger logger=LoggerFactory.getLogger(AsyncConfig.class);

    @Autowired
    private SpringThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public Executor getAsyncExecutor() {
        return threadPoolTaskExecutor.threadPoolTaskExecutor();
    }

    /**
     * 捕获异步方法的异常
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }
    class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
//            String methodName=method.getName();
//            logger.error("Method name - " + method.getName());
//            for (Object param : obj) {
//                logger.error("Parameter value - " + param);
//            }
            try {
                throw new Exception("Async Executor Exception",throwable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
