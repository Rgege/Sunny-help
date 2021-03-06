package org.blue.helper.StringHelper.config;

import org.blue.helper.StringHelper.common.exception.HelperException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 如果此时线程池中的线程数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
 *
 * 如果此时线程池中的线程数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
 *
 * 如果此时线程池中的线程数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maxPoolSize，建新的线程来处理被添加的任务。
 *
 * 如果此时线程池中的线程数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maxPoolSize，那么通过handler所指定的策略来
 * 处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，如果三者都满了，使用
 * handler处理被拒绝的任务。
 * 当线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 */

@Configuration
public class SpringThreadPoolTaskExecutor {

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(25); //核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(50); //线程池维护线程的最大数量
        threadPoolTaskExecutor.setKeepAliveSeconds(3000); //线程最大空闲时间
        threadPoolTaskExecutor.setQueueCapacity(200);  //缓存队列
        threadPoolTaskExecutor.setThreadNamePrefix("Helper_Task_Executor");
        //对拒绝的task的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new HelperException("Out Of Thread Pool Max Capacity ");
            }
        });
        return threadPoolTaskExecutor;
    }

    @Bean("fileUploadThreadPool")
    public Executor fileUploadThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(20); //核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(40); //线程池维护线程的最大数量
        threadPoolTaskExecutor.setKeepAliveSeconds(3000); //线程最大空闲时间
        threadPoolTaskExecutor.setQueueCapacity(200);  //缓存队列
        threadPoolTaskExecutor.setThreadNamePrefix("Helper_Task_Executor");
        //对拒绝的task的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new HelperException("Out Of Thread Pool Max Capacity ");
            }
        });
        return threadPoolTaskExecutor;
    }
}
