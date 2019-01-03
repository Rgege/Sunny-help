package org.blue.helper.StringHelper.utils;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHUtil {

    private static final int preheatTime=5;
    private static final int actualTime=5;

    /**
     * 测试
     *
     * @param c   要测试的类
     * @param preheatTime 预热次数
     * @param actualTime  真正执行次数
     * @throws ClassNotFoundException
     * @throws RunnerException
     */
    public static void runJMH(Class c, int preheatTime, int actualTime) throws ClassNotFoundException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(c.getSimpleName())
                .forks(1)
                .warmupIterations(preheatTime) //
                .measurementIterations(actualTime) //
                .build();
        new Runner(opt).run();
    }

    /**
     *
     * @param c 要测试的类
     * @throws ClassNotFoundException
     * @throws RunnerException
     */
    public static void runJMH(Class c)throws ClassNotFoundException, RunnerException {
        runJMH(c,preheatTime,actualTime);
    }
}
