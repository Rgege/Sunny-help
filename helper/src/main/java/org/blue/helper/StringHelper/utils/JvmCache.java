/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.blue.helper.StringHelper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * JvmCache
 *
 * @author xiong rui
 * @version 1.0.0
 * @date 2019/5/24
 **/
public class JvmCache {
    private static Logger LOGGER = LoggerFactory.getLogger(JvmCache.class);

    /**
     * 最大缓存容量
     */
    private static final Integer CACHE_MAX_NUMBER = 256;
    /**
     * 当前缓存数量
     */
    private static AtomicInteger CURRENT_SIZE = new AtomicInteger(0);
    /**
     *
     */
    static final Long ONE_MINUTE = 60 * 1000L;
    /**
     * 缓存对象
     */
    private static final Map<String, CacheObj> CACHE_OBJECT_MAP = new ConcurrentHashMap<>();
    /**
     * 这个记录了缓存使用的最后一次的记录，最近使用的在最前面
     */
    private static final List<String> CACHE_USE_LOG_LIST = new LinkedList<>();

    /**
     * 守护线程状态
     */
    private static volatile Boolean CLEAN_THREAD_IS_RUN = false;
    /**
     * 清理线程数
     */
    private static final Integer CLEAN_THREAD_NUM = 1;


    private static void currentSizeAdd() {
        CURRENT_SIZE.incrementAndGet();
    }

    private static void currentSizeDecre() {
        CURRENT_SIZE.decrementAndGet();
    }

    public static void setCache(String cacheKey, Object cacheValue, int second) {
        setCache(cacheKey,cacheValue,1000L*second);
    }
    /**
     * 设置缓存  并设置过期时间
     */
    public static void setCache(String cacheKey, Object cacheValue, long cacheTime) {
        Long ttlTime = null;
        if (cacheTime <= 0L) {
            if (cacheTime == -1L) {
                ttlTime = -1L;
            } else {
                return;
            }
        }
        checkSize();
        saveCacheUseLog(cacheKey);
        currentSizeAdd();
        if (ttlTime == null) {
            ttlTime = System.currentTimeMillis() + cacheTime;
        }
        CacheObj cacheObj = new CacheObj(cacheValue, ttlTime);
        CACHE_OBJECT_MAP.put(cacheKey, cacheObj);
    }

    /**
     * 设置缓存
     */
    public static void setCache(String cacheKey, Object cacheValue) {
        setCache(cacheKey, cacheValue, -1L);
    }

    /**
     * 获取缓存
     */
    public static Object getCache(String cacheKey) {
        startCleanThread();
        if (checkCache(cacheKey)) {
            saveCacheUseLog(cacheKey);
            return CACHE_OBJECT_MAP.get(cacheKey).getCacheValue();
        }
        return null;
    }

    public static boolean isExist(String cacheKey) {
        return checkCache(cacheKey);
    }

    /**
     * 删除所有缓存
     */
    public static void clear() {
        LOGGER.info("have clean all key !");
        CACHE_OBJECT_MAP.clear();
        CURRENT_SIZE.set(0);
    }

    /**
     * 删除某个缓存
     */
    public static void deleteCache(String cacheKey) {
        Object cacheValue = CACHE_OBJECT_MAP.remove(cacheKey);
        if (cacheValue != null) {
            LOGGER.info("have delete key :" + cacheKey);
            currentSizeDecre();
        }
    }

    /**
     * 判断缓存在不在,过没过期
     */
    private static boolean checkCache(String cacheKey) {
        CacheObj cacheObj = CACHE_OBJECT_MAP.get(cacheKey);
        if (cacheObj == null) {
            return false;
        }
        if (cacheObj.getTtlTime() == -1L) {
            return true;
        }
        if (cacheObj.getTtlTime() < System.currentTimeMillis()) {
            deleteCache(cacheKey);
            return false;
        }
        return true;
    }

    /**
     * 移除非热点数据
     */
    private static void deleteLRU() {
        LOGGER.info("delete Least recently used run!");
        String cacheKey = null;
        synchronized (CACHE_USE_LOG_LIST) {
            if (CACHE_USE_LOG_LIST.size() >= CACHE_MAX_NUMBER - 10) {
                cacheKey = CACHE_USE_LOG_LIST.remove(CACHE_USE_LOG_LIST.size() - 1);
            }
        }
        if (cacheKey != null) {
            deleteCache(cacheKey);
        }
    }

    /**
     * 清除过期缓存
     */
    static void deleteExpired() {
        LOGGER.info("delete time out run!");
        List<String> deleteKeyList = new LinkedList<>();
        for (Map.Entry<String, CacheObj> entry : CACHE_OBJECT_MAP.entrySet()) {
            if (entry.getValue().getTtlTime() < System.currentTimeMillis() && entry.getValue().getTtlTime() != -1L) {
                deleteKeyList.add(entry.getKey());
            }
        }

        for (String deleteKey : deleteKeyList) {
            deleteCache(deleteKey);
        }
        LOGGER.info("delete cache count is :" + deleteKeyList.size());

    }

    /**
     * 检查大小,当前缓存map中容量不足时，先清理过期缓存，如果清理完之后容量任然不足，则对非热点数据进行清理
     */
    private static void checkSize() {
        if (CURRENT_SIZE.intValue() >= CACHE_MAX_NUMBER) {
            deleteExpired();
        }
        if (CURRENT_SIZE.intValue() >= CACHE_MAX_NUMBER) {
            deleteLRU();
        }
    }

    /**
     * 保存缓存的使用记录
     */
    private static synchronized void saveCacheUseLog(String cacheKey) {
        synchronized (CACHE_USE_LOG_LIST) {
            CACHE_USE_LOG_LIST.remove(cacheKey);
            CACHE_USE_LOG_LIST.add(0, cacheKey);
        }
    }

    /**
     * 设置清理线程的运行状态为正在运行
     */
    static void setCleanThreadRun() {
        CLEAN_THREAD_IS_RUN = true;
    }

    /**
     * 设置清理线程的运行状态为已停止
     */
    static void setCleanThreadStop() {
        CLEAN_THREAD_IS_RUN = true;
    }

    /**
     * 开启清理过期缓存的线程
     */
    private static void startCleanThread() {
        if (!CLEAN_THREAD_IS_RUN) {
            synchronized (CLEAN_THREAD_IS_RUN) {
                if (!CLEAN_THREAD_IS_RUN) {
                    JvmCache.setCleanThreadRun();
                    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(CLEAN_THREAD_NUM, new CleanThreadFactory()
                            , new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            setCleanThreadStop();
                        }
                    });

                    executor.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            JvmCache.deleteExpired();
                        }
                    }, 0, JvmCache.ONE_MINUTE, TimeUnit.MILLISECONDS);
                }
            }
        }
    }


    static class CleanThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CleanThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "CleanThreads-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            t.setDaemon(true);
            return t;
        }
    }

    static class CacheObj {
        /**
         * 缓存对象
         */
        private Object CacheValue;
        /**
         * 缓存过期时间
         */
        private Long ttlTime;

        CacheObj(Object cacheValue, Long ttlTime) {
            CacheValue = cacheValue;
            this.ttlTime = ttlTime;
        }

        Object getCacheValue() {
            return CacheValue;
        }

        Long getTtlTime() {
            return ttlTime;
        }

        @Override
        public String toString() {
            return "CacheObj {" +
                    "CacheValue = " + CacheValue +
                    ", ttlTime = " + ttlTime +
                    '}';
        }
    }
}



