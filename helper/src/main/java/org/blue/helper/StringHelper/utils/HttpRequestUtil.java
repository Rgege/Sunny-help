package org.blue.helper.StringHelper.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HttpRequestUtil {

    public static final int POST_REQUEST=1;
    public static final int GET_REQUEST=2;

    private static final int DEFAULT_HTTP_CONNNECT_TIMEOUT=10000;

    private static CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * Post 请求 默认超时时间10s
     * @param url
     * @param jsonString 参数
     * @return
     */
    public static HttpPosRsp doPostV2(String url, String jsonString){
        Date startTime=new Date();
        String result=post( url,  jsonString,DEFAULT_HTTP_CONNNECT_TIMEOUT);
        Date endTime=new Date();

        HttpPosRsp httpPosRsp=new HttpPosRsp();
        httpPosRsp.setConsumedTime(DateUtil.dateDiff(startTime,endTime));
        JSONObject json=JSON.parseObject(result);
        httpPosRsp.setResult(json);
        return httpPosRsp;
    }
    /**
     * Post 请求 默认超时时间10s
     * @param url
     * @param jsonString 参数
     * @return
     */
    public static HttpPosRsp doPostV2(String url, String jsonString,int timeOut){
        Date startTime=new Date();
        String result=post( url,  jsonString,timeOut);
        Date endTime=new Date();

        HttpPosRsp httpPosRsp=new HttpPosRsp();
        httpPosRsp.setConsumedTime(DateUtil.dateDiff(startTime,endTime));
        JSONObject json=JSON.parseObject(result);
        httpPosRsp.setResult(json);
        return httpPosRsp;
    }
    /**
     * Post 请求 默认超时时间10s
     * @param url
     * @param jsonString 参数
     * @return
     */
    public static HttpPosRsp doGetV2(String url){
        Date startTime=new Date();
        String result=get( url,DEFAULT_HTTP_CONNNECT_TIMEOUT);
        Date endTime=new Date();

        HttpPosRsp httpPosRsp=new HttpPosRsp();
        httpPosRsp.setConsumedTime(DateUtil.dateDiff(startTime,endTime));
        JSONObject json=JSON.parseObject(result);
        httpPosRsp.setResult(json);
        return httpPosRsp;
    }
    /**
     * Post 请求 默认超时时间10s
     * @param url
     * @param jsonString 参数
     * @return
     */
    public static HttpPosRsp doGetV2(String url,int timeOut){
        Date startTime=new Date();
        String result=get( url,timeOut);
        Date endTime=new Date();

        HttpPosRsp httpPosRsp=new HttpPosRsp();
        httpPosRsp.setConsumedTime(DateUtil.dateDiff(startTime,endTime));
        JSONObject json=JSON.parseObject(result);
        httpPosRsp.setResult(json);
        return httpPosRsp;
    }
    /**
     * Post 请求 默认超时时间10s
     * @param url
     * @param jsonString 参数
     * @return
     */
    public static String doPost(String url, String jsonString){
        return post( url,  jsonString,DEFAULT_HTTP_CONNNECT_TIMEOUT);
    }

    /**
     * Post 请求
     * @param url
     * @param jsonString  参数
     * @param timeOut 超时时间
     * @return
     */
    public static String doPost(String url, String jsonString,int timeOut){
        return post( url,  jsonString,timeOut);
    }

    /**
     * Get 请求
     * @param url
     * @param timeOut
     * @return
     */
    public static String doGet(String url,int timeOut){
        return get(url,timeOut);
    }

    /**
     * Get 请求 默认超时时间10s
     * @param url
     * @return
     */
    public static String doGet(String url){
        return get(url,DEFAULT_HTTP_CONNNECT_TIMEOUT);
    }

    private static String get(String url,int timeOut) {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut).setConnectionRequestTimeout(10000).setSocketTimeout(timeOut).build();
            httpGet.setConfig(requestConfig);
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
            httpGet.setHeader("Accept", "application/json");
            response = httpClient.execute(httpGet);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static String post(String url, String jsonString,int timeOut) {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut).setConnectionRequestTimeout(10000).setSocketTimeout(timeOut).build();
            httpPost.setConfig(requestConfig);
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            try {
                Thread.sleep(5000);
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
        }
        int num=0;
        while (true){
            if (num==10) break;
            String url="http://10.201.128.125:7220/memberAdmin/mdm/fetch.htm";
            String result=doGet(url);
            System.out.println(result);
            if(result.indexOf("查询结果为空") != -1){
                num++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    public static void test(){
        String url="http://10.201.128.125:7220/memberAdmin/mdm/fetch.htm";

        for (int i = 0; i <100 ; i++) {
            System.out.println("==============================================================================");
            System.out.println("=================================== "+i+" ====================================");
            System.out.println("==============================================================================");
            String result=doGet(url);
            System.out.println(result);
            if(result.indexOf("查询结果为空") != -1){
                break;
            }
        }
    }
}

