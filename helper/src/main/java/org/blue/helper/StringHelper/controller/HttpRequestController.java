package org.blue.helper.StringHelper.controller;

import org.blue.helper.StringHelper.controller.support.req.HttpPosReq;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;
import org.blue.helper.StringHelper.service.HttpRequestService;
import org.blue.helper.StringHelper.utils.ResultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/http/")
public class HttpRequestController {

    private static final Logger log=LoggerFactory.getLogger(HttpRequestController.class);

    @Autowired
    HttpRequestService httpRequestService;

    @GetMapping("httptools")
    public String httptools(){ return "bus/httpRequester/httpreq"; }

    @RequestMapping("concurrentHttpReq")
    @ResponseBody
    public Map<String,Object> concurrentHttpReq(@RequestBody HttpPosReq httpPosReq, HttpServletRequest request){
        log.debug("###############################reqParams:"+httpPosReq.getReqParams());

        List<HttpPosRsp> rsps = httpRequestService.httpRequest(httpPosReq);

        return ResultUtil.objSuccess(rsps);
    }
}
