package org.blue.helper.test.thread.forkJoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;


public class DowloadTask extends RecursiveTask<List<String>> {

    private List<String> urlList;


    @Override
    protected List<String> compute() {
        return null;
    }

    public static void main(String[] args) {
        //JVM可用的处理器个数Runtime.getRuntime().availableProcessors()

        System.out.println(Runtime.getRuntime().freeMemory());
    }

}
