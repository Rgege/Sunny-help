package org.blue.helper.test.thread.CallBack;

import org.blue.helper.test.thread.Downloader;
import org.blue.helper.test.thread.Result;

import java.util.concurrent.Callable;

public class DownRunner implements Callable<Result>{
    private String url;
    private String savePath;
    public DownRunner(String url,String savePath) {
        this.url = url;
        this.savePath=savePath;
    }

    @Override
    public Result call() throws Exception {
        Downloader downloader=new Downloader(url,savePath);
        Integer i=downloader.start();
        return new Result(url,i);
    }

}
