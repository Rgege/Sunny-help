package org.blue.helper.StringHelper.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.blue.helper.StringHelper.aop.annotation.ParamsCheck;
import org.blue.helper.StringHelper.aop.annotation.Require;
import org.blue.helper.StringHelper.aop.support.CheckType;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.service.bookkeeping.intf.FileService;
import org.blue.helper.StringHelper.utils.NumberUtils;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 参数校验切面  配合注解@ParamsCheck 以及@Require
 */
@Aspect
@Component
public class ParamCheckAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParamCheckAspect.class);

    @Pointcut("@annotation(org.blue.helper.StringHelper.aop.annotation.ParamsCheck)")
    public void paramCheck() {
    }

    @Around("paramCheck()")
    public Object checkParams(ProceedingJoinPoint joinPoint) {
        if (escapeControllerInterceptor(joinPoint)) {
            return null;
        }
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            ParamsCheck paramsCheck = method.getAnnotation(ParamsCheck.class);
            //通过spring上下文获取request并取得参数
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String paramStr = getRequestData(joinPoint);
            LOGGER.debug("=========================paramStr:"+paramStr);
            //1、获取要校验的参数名称 以及其属于的class
            String paramName = paramsCheck.param();
            Class paramClass = paramsCheck.paramClass();
            if (StringUtils.isBlank(paramStr)) {
                return ResultUtil.creComErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(), SysCode.VALIDATE_PARAM_ERROR.getMsg());
            } else if (!String.class.equals(paramClass)) {
                Field[] filelds=paramClass.getDeclaredFields();//遍历参数对象的字段  看是否有@Require注解标识的字段，若有则根据@Require要求的判断类型对该字段进行校验
                if (filelds!=null && filelds.length!=0){
                    for (Field field:filelds) {
                        Require require=field.getAnnotation(Require.class);
                        if (require!=null){
                            Map<String,Object> checkResult=check(require,paramStr,field.getName());
                            if (checkResult !=null){
                                return checkResult;
                            }
                        }
                    }
                }

            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage(), throwable);
            return ResultUtil.creComErrorResult(SysCode.ERROR.getCode(), SysCode.ERROR.getMsg());
        }
    }
    private Map<String,Object> check(Require require,String paramStr,String filedName){
        String requireType=require.value();
        JSONObject jsonParam=JSON.parseObject(paramStr);
        if (CheckType.NOTNULL.equals(requireType)){
            if (! jsonParam.containsKey(filedName) || StringUtils.isBlank(jsonParam.getString(filedName))){
                return ResultUtil.creComErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(), filedName+" can not be empty");
            }
        }else if (CheckType.NUMBER.equals(requireType)){
            if (! jsonParam.containsKey(filedName) || StringUtils.isBlank(jsonParam.getString(filedName)) || ! NumberUtils.isNumeric(jsonParam.getString(filedName))){
                return ResultUtil.creComErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(), filedName+" has to be a number");
            }
        }

        return null;
    }
    private String getRequestData(JoinPoint joinPoint) {
        Object param = null;
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            param = joinPoint.getArgs()[0];
        }
        String requestData = "";

        try {
            if (param != null) {
                if (param instanceof String) {
                    requestData = (String) param;
                } else if (param instanceof HttpServletRequest || param instanceof HttpServletResponse) {

                } else {
                    requestData = JSON.toJSONString(param);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            requestData = "Convert request bean error!";
        }
        return requestData;
    }

    private boolean escapeControllerInterceptor(JoinPoint joinPoint) {
        if (joinPoint != null && joinPoint.getSignature() != null) {
            Signature signature = joinPoint.getSignature();
            if ("initBinder".equalsIgnoreCase(signature.getName())) {
                return true;
            }
        }
        return false;
    }
}
