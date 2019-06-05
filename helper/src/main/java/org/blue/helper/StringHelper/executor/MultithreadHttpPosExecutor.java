package org.blue.helper.StringHelper.executor;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MultithreadHttpPosExecutor implements Runnable{
    private static final Logger logger=LoggerFactory.getLogger(MultithreadHttpPosExecutor.class);
    private List<String> data;
    private int start;
    private int end;
    private String url;


    @Override
    public void run() {
        List<String> subList = data.subList(start, end);
        logger.info(Thread.currentThread().getName()+"分配了"+subList.size()+"条:"+ArrayUtils.toString(subList));
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
