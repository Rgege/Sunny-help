package org.blue.helper.test.thread.CallBack;


import org.blue.helper.test.thread.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Action {

    public static void main(String[] args) {
       down(getUrlList(),"C:\\Users\\User\\Desktop\\documents\\test\\");
    }
    public static List<Result> down(List<String> urlList,String savePath){
        List<Result> results=new ArrayList<Result>();

        int cpus=Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(cpus*10);
        List<Future<Result>> futures=new ArrayList<Future<Result>>();
        try {
            for (int i=0;i<urlList.size();i++) {
                futures.add(threadPool.submit(new DownRunner(urlList.get(i),savePath)));
            }
            for (Future<Result> future:futures) {
                results.add(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        return results;
    }


    public static List<String> getUrlList(){
        List<String> urlList = new ArrayList<String>();
        File file=new File("C:\\Users\\User\\Desktop\\chunklist_b433000_10.m3u8");
        try (BufferedReader reader=new BufferedReader(new FileReader(file))){
            String line=null;
            while ((line=reader.readLine())!=null){
                if (line.indexOf("#")==-1){
                    urlList.add("http://d2vvqvds83fsd.cloudfront.net/vin02/vsmedia/_definst_/smil:event/18/36/06/3/rt/1/resources/180919_PID_Intelligent_Enterprise_Gruenewald_720p-5F92.smil/"+line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }
}
