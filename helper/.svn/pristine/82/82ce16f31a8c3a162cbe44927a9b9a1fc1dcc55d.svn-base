package org.blue.helper.test.jmh;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * JMH 测试执行类
 */
public class JMHTestAction {
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

    public static void main(String[] args) throws RunnerException, ClassNotFoundException {
        runJMH(JmhDemo.class,5,5);
    }
}
