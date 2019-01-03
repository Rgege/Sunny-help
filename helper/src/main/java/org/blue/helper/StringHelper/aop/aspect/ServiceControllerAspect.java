package org.blue.helper.StringHelper.aop.aspect;


import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.blue.helper.StringHelper.service.ServiceAccessLogService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Map;

public class ServiceControllerAspect {

    private static Logger logger = LoggerFactory.getLogger(ServiceControllerAspect.class);

    @Autowired
    private ServiceAccessLogService serviceAccessLogService;

    private JsonConfig jsonConfig;

    public ServiceControllerAspect() {
        jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName, Object fieldValue) {
                return null == fieldValue;
            }
        };
        jsonConfig.setJsonPropertyFilter(filter);
    }

    public void doBefore(JoinPoint jp) {
        try{
            if(escapeControllerInterceptor(jp)){
                return ;
            }
            //记录请求的起始时间
            Timestamp startTime = DateUtil.getCurrentTimeStampMillisForAop();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.setAttribute("aopStartTime", startTime);

            //在获取请求参数时，在doAfter方法里获取的是处理之后的参数，要获取原始的请求参数要在doBefore里获取
            String requestData = this.getRequestData(jp);
            request.setAttribute("aopRequestData", requestData);
        }catch(Exception e){
            logger.error("Aspect doBefore method error!",e);
        }
    }

    public void doAfter(JoinPoint jp, Object returningData) throws Exception {
        try{
            if(escapeControllerInterceptor(jp)){
                return ;
            }
            Timestamp endTime = DateUtil.getCurrentTimeStampMillisForAop();

            //获取请求的request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            //获取doBefore方法里设置的aop参数
            String requestData = (String)request.getAttribute("aopRequestData");
            Timestamp startTime = (Timestamp)request.getAttribute("aopStartTime");

            String responseData = this.getResponseData(returningData);
            String status="SUCCESS";
            String errorType="";
            String req_uri = request.getRequestURI();

            try {

                logger.debug("startTime-->"+startTime+",endTime-->"+endTime);

                this.serviceAccessLogService.logAccess(
                        req_uri,
                        requestData, //requestData
                        responseData, //responseData
                        startTime,// startTs
                        endTime,
                        request
                );
            } catch (Exception ex) {
                logger.error("插入日志信息出错", ex);
            }
        }catch(Exception e){
            logger.error("Aspect doAfter method error!",e);
        }
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        try{
            if(escapeControllerInterceptor(jp)){
                return ;
            }
            Timestamp endTime = DateUtil.getCurrentTimeStampMillisForAop();

            //获取请求的request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestData = this.getRequestData(jp);
            String status="ERROR";
            String errorType=ex.getClass().toString();
            String req_uri = request.getRequestURI();
            Timestamp startTime = (Timestamp)request.getAttribute("aopStartTime");

            try {

                logger.debug("startTime-->"+startTime+",endTime-->"+endTime);

                this.serviceAccessLogService.logAccess(
                        req_uri,
                        requestData, //requestData
                        errorType, //responseData
                        startTime,
                        endTime,
                        request
                );
            } catch (Exception e) {
                logger.error("插入日志信息出错", e);
            }
        }catch(Exception e){
            logger.error("Aspect doThrowing method error!",e);
        }
    }

    private String getRequestData(JoinPoint jp){
        //获取请求参数和返回参数
        Object param = null;
        if(jp.getArgs() != null && jp.getArgs().length > 0){
            param = jp.getArgs()[0];
        }
        String requestData = "Not hava request param!";

        try{
            if(param != null){
                if(param instanceof String) {
                    requestData = (String)param;
                } else if (param instanceof HttpServletRequest || param instanceof HttpServletResponse) {

                } else {
                    requestData = JSON.toJSONString(param);
                }
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            requestData = "Convert request bean error!";
        }

        return requestData;
    }

    private String getResponseData(Object returningData){
        String responseData = "Not hava response data!";

        try{
            if(returningData != null){
                if(returningData instanceof String){
                    responseData = (String)returningData;
                }else if(returningData instanceof Map){
                    responseData = JSONObject.fromObject(returningData, jsonConfig).toString();
                }else{
                    responseData = JSONObject.fromObject(returningData, jsonConfig).toString();
                }
            }
        }catch(Exception e){
            responseData = "Convert resposne data error!";
        }

        return responseData;
    }

    private boolean escapeControllerInterceptor(JoinPoint joinPoint){
        if(joinPoint != null && joinPoint.getSignature() != null){
            Signature signature = joinPoint.getSignature();
            if("initBinder".equalsIgnoreCase(signature.getName())){
                return true;
            }
        }
        return false;
    }
}

