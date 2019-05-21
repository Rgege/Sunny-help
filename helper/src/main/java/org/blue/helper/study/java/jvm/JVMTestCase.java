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
package org.blue.helper.study.java.jvm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * JVMTestCase
 * <p>
 * -XX:+PrintGCDetails         发生垃圾回收时打印日志
 * -XX:+UseParNewGC            新生代使用ParNew
 * -XX:+UseConcMarkSweepGC     老年代用CMS 新生代默认使用ParNew
 * <p>
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails  -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * <p>
 *
 * @author xiong rui
 * @version 1.0.0
 * @date 2019/5/20
 **/
public class JVMTestCase {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        try {
//            getCode("");
//          testPretenureSizeThreshold();
//            fillHeap(1000);
            testThreadStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 大对象直接进入老年代
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[11 * _1MB];//直接分配在老年代中
    }

    /**
     * 长期存活的对象进入老年代 每次minorGc Survivor中的对象年龄就加1 当大于MaxTenuringThreshold 时 就会进入老年代
     * VM参数：-verbose:gc-Xms20M-Xmx20M-Xmn10M-XX:+PrintGCDetails-XX:SurvivorRatio=8-XX:MaxTenuringThreshold=1
     * -XX：+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        //什么时候进入老年代取决于XX：MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * 当Survivor中相同年龄的对象的总大小超过Survivor大小的一半 那么大于或等于这个年龄的对象都会进入老年代 即使年龄没有达到MaxTenuringThreshold
     * <p>
     * VM参数:-verbose:gc-Xms20M-Xmx20M-Xmn10M-XX:+PrintGCDetails-XX:SurvivorRatio=8-XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        //allocation1+allocation2大于survivo空间一半
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * 内存占位符对象,一个OOMObject大约占64KB
     * -Xms100m- Xmx100m -XX:+UseSerialGC
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            //稍作延时,令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }


    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)//第41行
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("=======================进入testLockThread.RUNN  等待");
                        lock.wait();
                        System.out.println("=======================被重新唤醒了  重新执行");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunalbe implements Runnable {
        int a, b;

        public SynAddRunalbe(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }


    public static void testThreadStatus() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String val1=br.readLine();
        Thread.sleep(10000);//休眠十秒 以便有时间打开JConsole等工具监看
        if (val1==null || ThreadStatus.BUSY.code.equals(val1)){//测试死循环
            createBusyThread();
        }else if (ThreadStatus.WAIT.code.equals(val1)){//测试线程等待 并被唤醒
            Object obj = new Object();
            createLockThread(obj);
            String val2=br.readLine();
            new Thread(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (obj){
                                    System.out.println("=======================进入notifyAllThread.RUNN  准备唤醒所有");
                                    obj.notify();
                                    System.out.println("=======================进入notifyAllThread.RUNN  准备唤醒所有执行完后继续执行");
                                }
                            }
                        }, "notifyAllThread").start();
        }else if (ThreadStatus.DEADLOCK.code.equals(val1)){//测试线程死锁
            for (int i = 0; i < 50; i++) {
                new Thread(new SynAddRunalbe(1, 2),"Thread["+i+"]1").start();
                new Thread(new SynAddRunalbe(2, 1),"Thread["+i+"]2").start();
            }
        }

        String val3="";
        while ((val3=br.readLine())==null){

        }

    }



    private static void getCode(String code) {
        code = code.replace("：", ":");
        code = code.replace("（", "(");
        code = code.replace("）", ")");
        code = code.replace("；", ";");
        code = code.replace("，", ",");
        code = code.replace("＜", "<");
        code = code.replace("＞", ">");

        System.out.println(code);
    }

    enum ThreadStatus{
        BUSY("1"),
        WAIT("2"),
        DEADLOCK("3"),
        ;

        public String code;

        ThreadStatus(String code) {
            this.code = code;
        }

    }
}


// WaitTest.java的源码
class ThreadA extends Thread{

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }
}

class WaitTest {

   public static void main(String[] args) {

       ThreadA ta = new ThreadA("ta");

       synchronized(ta) {
           try {
               // 启动“线程t1”
               System.out.println(Thread.currentThread().getName()+" start ta");
               ta.start();

//                // 主线程等待t1通过notify()唤醒。
//                System.out.println(Thread.currentThread().getName()+" wait()");
//                ta.wait();

               System.out.println(Thread.currentThread().getName()+" continue");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
}