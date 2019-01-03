package org.blue.helper.StringHelper.controller.support.rsp;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

public class HttpPosRsp {
    private String url;
    private JSONObject result;
    private String requestData;
    private String responseData;
    private Long consumedTime;
    private int status; //0 成功 1 业务错误 2 请求超时

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public Long getConsumedTime() {
        return consumedTime;
    }

    public void setConsumedTime(Long consumedTime) {
        this.consumedTime = consumedTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(JSONObject result) {
        this.responseData = result.toJSONString();
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void booleanSuccess(String successFlag){
        if(this.getResult().isEmpty()){
            this.setStatus(2);
        }else if(StringUtils.equals(this.getResult().getString("resCode"), successFlag)){
            this.setStatus(0);
        }else if(!StringUtils.equals(this.getResult().getString("resCode"), successFlag)){
            this.setStatus(1);
        }
    }
}
