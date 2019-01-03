package org.blue.helper.StringHelper.service.impl;

import com.alibaba.fastjson.JSON;
import org.blue.helper.StringHelper.controller.support.req.HttpPosReq;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;
import org.blue.helper.StringHelper.service.HttpPosService;
import org.blue.helper.StringHelper.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpPosServiceImpl implements HttpPosService {
    private static final Logger log=LoggerFactory.getLogger(HttpRequestServiceImpl.class);

    @Override
    public HttpPosRsp doPosRequester(HttpPosReq httpPosReq) {

        HttpPosRsp httpPosRsp=null;
        if (httpPosReq.getMethod()==HttpRequestUtil.GET_REQUEST){
            if(httpPosReq.getTimeOut()!=null){
                httpPosRsp=HttpRequestUtil.doGetV2(httpPosReq.getReqUrl(),httpPosReq.getTimeOut());
            }else {
                httpPosRsp=HttpRequestUtil.doGetV2(httpPosReq.getReqUrl());
            }
        }else if (httpPosReq.getMethod()==HttpRequestUtil.POST_REQUEST){
//            String param= JSON.toJSONString(httpPosReq.getReqParams());
            String param= httpPosReq.getReqParams();
            if(httpPosReq.getTimeOut()!=null){
                httpPosRsp=HttpRequestUtil.doPostV2(httpPosReq.getReqUrl(),param,httpPosReq.getTimeOut());
            }else {
                httpPosRsp=HttpRequestUtil.doPostV2(httpPosReq.getReqUrl(),param);
            }
        }
        return httpPosRsp;
    }
}
