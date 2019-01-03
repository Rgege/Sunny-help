package org.blue.helper.StringHelper.aop.interceptor;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.aop.annotation.NeedLogin;
import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.persistence.LoginRecordMapper;
import org.blue.helper.StringHelper.persistence.entity.model.LoginRecord;
import org.blue.helper.StringHelper.persistence.entity.model.UserInfo;
import org.blue.helper.StringHelper.service.UserInfoAsyncService;
import org.blue.helper.StringHelper.service.UserInfoService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.utils.NetworkUtil;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.blue.helper.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckLoginInterceptor implements MethodInterceptor {
    @Autowired
    private LoginRecordMapper loginRecordMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoAsyncService userInfoAsyncService;
    @Autowired
    @Qualifier("redisUtil")
    private RedisUtil redisUtil;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
        Class returnType=method.getReturnType();
        boolean check=needLogin.checkLogin();
        if(null != needLogin && check){
            //登录ip+token
            Object[] args = invocation.getArguments();

            if(args == null || args.length < 1 ){
                if(String.class.equals(returnType)){
                    return "user/login";
                }
                return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());
            }

            String reqObj="org.apache.catalina.connector.RequestFacade";
            HttpServletRequest request=null;
            RequestFacade requestFacade;
            for (Object obj : args) {
                if(obj.toString().indexOf(reqObj) != -1){
                    request = (HttpServletRequest) obj;
                    break;
                }
            }
            LoginRecord loginToken=new LoginRecord();
            if(request !=null && request.getHeader("token") !=null){
                loginToken.setToken(request.getHeader("token"));
                loginToken.setLoginIp(NetworkUtil.getClientIpAddr(request));
            }else {
                if(String.class.equals(returnType)){
                    return "user/login";
                }
                return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());
            }
            String key=EncryptKey.RKP_USER_INFO+"_"+loginToken.getToken()+"_"+loginToken.getLoginIp();
            //优先从reids中查询
            String userInfo=redisUtil.get(key,"");
            if(StringUtils.isBlank(userInfo)){//redis 中没有向数据库中查询  查到结果插入到redis
                Map<String,Object> paraMap=new HashMap<String, Object>();
                paraMap.put("token",loginToken.getToken());
                paraMap.put("loginIp",loginToken.getLoginIp());
                paraMap.put("outTime",new Timestamp(DateUtil.getSpecifiedDayBySecond(new Date(),-300).getTime()));
                List<LoginRecord> loginRecord=loginRecordMapper.getUserRecordByToken(paraMap);
                if(loginRecord==null||loginRecord.isEmpty()){
                    if(String.class.equals(returnType)){
                        return "user/login";
                    }
                    return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());
                }else {
                    UserInfo user= userInfoService.getUserById(loginRecord.get(0).getUserId());
                    if (user==null){
                        if(String.class.equals(returnType)){
                            return "user/login";
                        }
                        return ResultUtil.creComErrorResult(SysCode.LOGIN_CHECK_ERROR.getCode(),SysCode.LOGIN_CHECK_ERROR.getMsg());
                    }
                    redisUtil.set(key, JSON.toJSONString(user), 300);
                }
            }else {//每一次登录都刷新redis key的超时时间
                redisUtil.expire(key,300);
                userInfoAsyncService.updateLoginRecordByAsync(loginToken);
            }

        }
        return invocation.proceed();
    }


}
