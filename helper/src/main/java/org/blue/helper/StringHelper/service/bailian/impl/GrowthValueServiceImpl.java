package org.blue.helper.StringHelper.service.bailian.impl;

import com.alibaba.fastjson.JSON;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;
import org.blue.helper.StringHelper.persistence.HttpPostRspMapper;
import org.blue.helper.StringHelper.service.bailian.GrowthValueService;
import org.blue.helper.StringHelper.utils.HttpRequestUtil;
import org.blue.helper.StringHelper.utils.ListUtil;
import org.blue.helper.test.bailian.addGrowthValue.Params;
import org.blue.helper.test.util.ExcelUtil;
import org.blue.helper.test.util.support.ExcelTb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class GrowthValueServiceImpl implements GrowthValueService {
    private static final Logger logger = LoggerFactory.getLogger(GrowthValueServiceImpl.class);
    @Autowired
    private HttpPostRspMapper mapper;


    @Async
    @Override
    public void sendHttpPos(String url, List<String> params) {
        logger.info("==================start");
        logger.info("==================正向:" + params.size());
//        for (String param : params) {
//            HttpPosRsp posRsp = HttpRequestUtil.doPostV2(url, param);
//            posRsp.setUrl(url);
//            posRsp.booleanSuccess("00100000");
//            posRsp.setResponseData(posRsp.getResult());
//            posRsp.setRequestData(param);
//            mapper.insert(posRsp);
//        }
        List<List<String>> lists= ListUtil.averageAssign(params,20);
        int size=lists.size();
        for (int i = 0; i <size ; i++) {
            List<String> paramList=lists.get(i);
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (String param : paramList) {
                        HttpPosRsp posRsp = HttpRequestUtil.doPostV2(url, param);
                        posRsp.setUrl(url);
                        posRsp.booleanSuccess("00100000");
                        posRsp.setResponseData(posRsp.getResult());
                        posRsp.setRequestData(param);
                        mapper.insert(posRsp);
                    }
                }
            });
            thread.start();
        }
        logger.info("==================end");
    }

    @Override
    public List<String> getParams(String excelPath) {
        ExcelTb tb = addCustomerInfo(excelPath);
        List<String> paramsList = new ArrayList<String>();
        if (tb != null) {
            paramsList = createParams(tb);
        }
        return paramsList;
    }

    private void sendPost(List<String> params, String url, final int nThreads) throws Exception {
        List<HttpPosRsp> results = new ArrayList<HttpPosRsp>();
        if (params == null || params.isEmpty()) {
            return;
        }
        int size = params.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//        List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            final List<String> subList = params.subList(size / nThreads * i, size / nThreads * (i + 1));
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    List<HttpPosRsp> rspList = new ArrayList<HttpPosRsp>();
                    for (String param : subList) {
                        HttpPosRsp posRsp = HttpRequestUtil.doPostV2(url, param);
                        posRsp.setRequestData(param);
                        posRsp.setUrl(url);
                        posRsp.booleanSuccess("00100000");
                        posRsp.setResponseData(posRsp.getResult());
                        rspList.add(posRsp);
                    }
                    logger.info("====================================================插入日志:" + rspList.size() + " 条");
                    mapper.insertBatch(rspList);
                    return "";
                }
            };
            executorService.submit(task);
//            futures.add(executorService.submit(task));
        }

//        for (Future<List<HttpPosRsp>> future : futures) {
//            List<HttpPosRsp> list=future.get();
//        }
        executorService.shutdown();
    }

    private ExcelTb addCustomerInfo(String filePath) {
        ExcelTb tb = null;
        try {
            tb = ExcelUtil.readExcelContentzNew(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb;
    }

    private List<String> createParams(ExcelTb tb) {
        List<String> params = new ArrayList<String>();

        int rows = tb.getTotalRows();
        int lines = tb.getTotalLines();
        logger.info("==================excel表共:" + rows + "行" + lines + "列");
        int decrese = 0;
        for (int i = 1; i <= rows; i++) {
            //String key=i+""+line;
            if (!tb.getCellMap().get(i + "" + 4).getValue().equals("2")) {
                decrese++;
                continue;
            }
            Params param = new Params();
            param.setMemberId(Long.valueOf(tb.getCellMap().get(i + "" + 2).getValue()));
            param.setBillId("r"+tb.getCellMap().get(i + "" + 8).getValue());
            param.setBuId(tb.getCellMap().get(i + "" + 5).getValue());
            param.setChannelId(tb.getCellMap().get(i + "" + 6).getValue());
            param.setStoreId(tb.getCellMap().get(i + "" + 7).getValue());
            param.setGrowthType("02");
            param.setMoney(Double.valueOf(tb.getCellMap().get(i + "" + 10).getValue()));

            params.add(JSON.toJSONString(param));
        }
        logger.info("==================共:" + decrese + "条逆向");
        return params;
    }
}
