package org.blue.helper.StringHelper.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.controller.support.UserFriendsReq;
import org.blue.helper.StringHelper.controller.support.UserInfoReq;
import org.blue.helper.StringHelper.persistence.entity.model.LoginRecord;
import org.blue.helper.StringHelper.persistence.entity.model.UserInfo;
import org.blue.helper.StringHelper.service.UserInfoService;
import org.blue.helper.StringHelper.utils.NetworkUtil;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userInfo/")
public class UserBaseController {
    private static final Logger logger=LoggerFactory.getLogger(UserBaseController.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "login")
    public  String login(){
        return "user/login";
    }
    @GetMapping(value = "register")
    public  String register(){
        return "user/register";
    }
    @GetMapping(value = "resetpwd")
    public  String resetpwd(){
        return "user/resetpwd";
    }

    @PostMapping(value = "doRegister")
    @ResponseBody
    public Map<String,Object> register(@RequestBody UserInfoReq userInfoReq, HttpServletRequest request){
        logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>> 注册新用户  start <<<<<<<<<<<<<<<<<<<<<<<<<");
        logger.info("register entered requestData : {}",JSON.toJSONString(userInfoReq));

        if(null==userInfoReq){
            return ResultUtil.creComErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(),SysCode.VALIDATE_PARAM_ERROR.getMsg());
        }
        ArrayList<String> errors=userInfoReq.validate();
        if(!errors.isEmpty()){
            return ResultUtil.creObjErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(),SysCode.VALIDATE_PARAM_ERROR.getMsg(),errors);
        }
        List<String> verifyRegisterInfo=userInfoService.verifyRegisterInfo(userInfoReq);
        if(!verifyRegisterInfo.isEmpty()){
            return ResultUtil.creObjErrorResult(SysCode.REGISTER_FAIL.getCode(),SysCode.REGISTER_FAIL.getMsg(),verifyRegisterInfo);
        }
        userInfoService.addUser(userInfoReq);
        return ResultUtil.commonSuccess();
    }

    @PostMapping(value = "doLogin")
    @ResponseBody
    public Map<String,Object> login(@RequestBody UserInfoReq userInfoReq, HttpServletRequest request){
        logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>> 用户登录  start <<<<<<<<<<<<<<<<<<<<<<<<<");
        logger.info("login entered requestData : {}",JSON.toJSONString(userInfoReq));

        if(null==userInfoReq){
            return ResultUtil.creComErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(),SysCode.VALIDATE_PARAM_ERROR.getMsg());
        }
        ArrayList<String> errors=userInfoReq.validate();
        if(!errors.isEmpty()){
            return ResultUtil.creObjErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(),SysCode.VALIDATE_PARAM_ERROR.getMsg(),errors);
        }
        Map<String,Object> paraMap=new HashMap<String, Object>();
        paraMap.put("ip",NetworkUtil.getClientIpAddr(request));
        paraMap.put("password",userInfoReq.getPassword());
        if(StringUtils.isNotBlank(userInfoReq.getMobile())){
            paraMap.put("mobile",userInfoReq.getMobile());
        }else if(StringUtils.isNotBlank(userInfoReq.getUserName())){
            paraMap.put("userName",userInfoReq.getUserName());
        }
        Map<String, Object> resultMap=userInfoService.getUserByPwd(paraMap);
        if(resultMap == null || resultMap.get("userInfo") == null){
            return ResultUtil.creComErrorResult(SysCode.INVALID_LOGINPWD_ERROR.getCode(),SysCode.INVALID_LOGINPWD_ERROR.getMsg());
        }
        return ResultUtil.objSuccess(resultMap);
    }

    @PostMapping("getUseInfo")
    @ResponseBody
    public Map<String,Object> getUseInfo(@RequestBody String serchParam,HttpServletRequest request){
        if(serchParam==null || serchParam=="") return ResultUtil.msgError("参数错误");
        JSONObject jsonObject=JSONObject.parseObject(serchParam);
        List<UserInfo> users=userInfoService.getUserInfo(jsonObject.getString("serchParam"));
        if(users!=null && !users.isEmpty()){
            return ResultUtil.objSuccess(users);
        }
        return ResultUtil.msgError("没有相关用户");
    }

    @PostMapping("getUserFriends")
    @ResponseBody
    public Map<String,Object> getUserFriends(@RequestBody String token,HttpServletRequest request){

        if(token==null || token =="") return ResultUtil.msgError("参数错误");
        JSONObject jsonObject=JSONObject.parseObject(token);
        String ip=NetworkUtil.getClientIpAddr(request);
        String key=EncryptKey.RKP_USER_INFO+"_"+jsonObject.getString("token")+"_"+ip;

        String userInfo=redisUtil.get(key,null);
        JSONObject jsonInfo=null;
        if(userInfo !=null){
             jsonInfo=JSONObject.parseObject(userInfo);
            List<UserFriendsReq>  friendsList = userInfoService.getUserFriends(jsonInfo.getLong("id"));
            return ResultUtil.objSuccess(friendsList);
        }else {
            LoginRecord loginRecord=new LoginRecord();
            loginRecord.setLoginIp(ip);
            loginRecord.setToken(jsonObject.getString("token"));
            Long id=userInfoService.getUserIdByToken(loginRecord);
            if(id != 0L){
                List<UserFriendsReq>  friendsList = userInfoService.getUserFriends(id);
                return ResultUtil.objSuccess(friendsList);
            }
        }
        return ResultUtil.creComErrorResult(SysCode.NULL_RESULT.getCode(),SysCode.NULL_RESULT.getMsg());
    }
    @PostMapping("getOnlineUser")
    @ResponseBody
    public Map<String,Object> getOnlineUser(HttpServletRequest request){
//        if(token==null || token =="") return ResultUtil.msgError("参数错误");
        logger.info("=======================into getOnlineUser token:"+request.getParameter("token"));
//        JSONObject jsonObject=JSONObject.parseObject(token);
        LoginRecord loginRecord=new LoginRecord();
        loginRecord.setLoginIp(NetworkUtil.getClientIpAddr(request));
        loginRecord.setToken(request.getParameter("token"));
        UserInfo userInfo=userInfoService.getOnlieUser(loginRecord);
        if(userInfo==null){
            return ResultUtil.creComErrorResult(SysCode.OUT_LOGIN_ERROR.getCode(),SysCode.OUT_LOGIN_ERROR.getMsg());
        }else {
            UserInfoReq rsp=new UserInfoReq();
            rsp.setId(userInfo.getId());
            rsp.setNickName(userInfo.getNickName());
            rsp.setGender(userInfo.getGender());
            rsp.setAge(userInfo.getAge());
            rsp.setUserName(userInfo.getUserName());
            rsp.setEmail(userInfo.getEmail());
            rsp.setMobile(userInfo.getMobile());
            rsp.setHeadPortrait(userInfo.getHeadPortrait());
            return ResultUtil.objSuccess(rsp);
        }
    }

    @PostMapping("addFriends")
    @ResponseBody
    public Map<String,Object> addFriends(@RequestBody UserFriendsReq userFriendsReq,HttpServletRequest request){
        if(userFriendsReq == null){
            return ResultUtil.creComErrorResult(SysCode.VALIDATE_PARAM_ERROR.getCode(),SysCode.VALIDATE_PARAM_ERROR.getMsg());
        }
        userInfoService.addFriends(NetworkUtil.getClientIpAddr(request),request.getHeader("token"),userFriendsReq);
        return ResultUtil.commonSuccess();
    }
}
