package org.blue.helper.StringHelper.utils;


import org.blue.helper.StringHelper.common.enums.SysCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResultUtil {
    public ResultUtil() {
    }

    public static Map<String,Object> commonSuccess(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("resCode",SysCode.SUCCESS.getCode());
        return map;
    }
    public static Map<String,Object> objSuccess(Object object){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("resCode",SysCode.SUCCESS.getCode());
        map.put("object",object);
        return map;
    }

    public static Map<String,Object> commonError(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("resCode",SysCode.ERROR.getCode());
        map.put("message",SysCode.ERROR.getMsg());
        return map;
    }

    public static Map<String,Object> msgError(String errorReminder){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("resCode",SysCode.ERROR.getCode());
        map.put("message",errorReminder);
        return map;
    }
    public static Map<String, Object> creComErrorResult(String errorCode,String message) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("resCode", errorCode);
            resultMap.put("message", message);
            return resultMap;
    }
    public static Map<String, Object> creObjErrorResult(String errorCode,String message,Object object) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("resCode", errorCode);
        resultMap.put("message", message);
        resultMap.put("obj",object);
        return resultMap;
    }
}
