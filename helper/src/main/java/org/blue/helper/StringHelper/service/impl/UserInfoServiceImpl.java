package org.blue.helper.StringHelper.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.constants.EncryptKey;

import org.blue.helper.StringHelper.controller.support.UserFriendsReq;
import org.blue.helper.StringHelper.controller.support.UserInfoReq;
import org.blue.helper.StringHelper.persistence.LoginRecordMapper;
import org.blue.helper.StringHelper.persistence.UserInfoMapper;
import org.blue.helper.StringHelper.persistence.UserRelationMapper;
import org.blue.helper.StringHelper.persistence.entity.model.*;
import org.blue.helper.StringHelper.service.UserInfoAsyncService;
import org.blue.helper.StringHelper.service.UserInfoService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.utils.MD5Util;
import org.blue.helper.StringHelper.utils.TokenUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private LoginRecordMapper loginRecordMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRelationMapper userRelationMapper;

    @Autowired
    private UserInfoAsyncService userInfoAsyncService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addUser(UserInfoReq userInfoReq) {
        Date addTime=new Date();//前缀
        if(StringUtils.isBlank(userInfoReq.getNickName())){
            userInfoReq.setNickName(TokenUtil.getRandomStr());
        }

        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userInfoReq,userInfo);
        userInfo.setPassword(MD5Util.sign(userInfoReq.getPassword(), EncryptKey.PWD_PREFIX));
        userInfo.setCreateTime(addTime);
        userInfo.setModifyTime(addTime);
        userInfo.setStatus(0);
        userInfoMapper.insert(userInfo);
    }

    @Override
    public Map<String, Object> getUserByPwd(Map<String,Object> paraMap) {
        Map<String,Object> resultMap=new HashMap<String, Object>();

        paraMap.put("password",MD5Util.sign(paraMap.get("password").toString(), EncryptKey.PWD_PREFIX));
        logger.info("===========>根据手机号或用户名查询用户: " + paraMap.get("userName")==null? "mobile="+paraMap.get("mobile"):"userName="+paraMap.get("userName"));
        paraMap.put("userName",paraMap.get("userName")==null?paraMap.get("mobile"):paraMap.get("userName"));
        List<UserInfo> users=userInfoMapper.getUserByInfo(paraMap);
        LoginRecord record=new LoginRecord();
        record.setLoginIp(paraMap.get("ip").toString());
        if(users.isEmpty()){
            resultMap.put("ERROR","用户不存在");
            record.setMemo("登录失败,未找到用户 ReqParam:" + JSON.toJSONString(paraMap));
            record.setStatus(1);
            loginRecordMapper.insert(record);
            return resultMap;
        }
        //登录成功
        String token=TokenUtil.getToken();
        String key=EncryptKey.RKP_USER_INFO+"_"+token+"_"+paraMap.get("ip").toString();
        logger.info("===========>异步保存用户信息至redis");
        try {
            userInfoAsyncService.addUser2RedisByAsync(users.get(0),key);
        }catch (Exception e){
            //不作任何处理
        }
        UserInfoReq userInfoReq=new UserInfoReq();
        BeanUtils.copyProperties(users.get(0),userInfoReq);

        resultMap.put("token",token);
        resultMap.put("userInfo",userInfoReq);

        record.setToken(token);
        record.setUserId(userInfoReq.getId());
        record.setStatus(0);
        record.setRenewal(0);
        loginRecordMapper.insert(record);

//        RunTask.singleExecution(new RefreshDbDataTask(token,paraMap.get("ip").toString()),300);
        return resultMap;
    }

    @Override
    public List<String> verifyRegisterInfo(UserInfoReq userInfoReq) {
        List<String> errorList=new ArrayList<String>();
        if(StringUtils.isNotBlank(userInfoReq.getUserName())){
            int userCount=userInfoMapper.countUserName(userInfoReq.getUserName());
            if(userCount > 0){//用户名已存在
                errorList.add("用户名已存在");
            }
        }
        if(StringUtils.isNotBlank(userInfoReq.getMobile())){
            List<UserInfo> users=userInfoMapper.getUserByMobile(userInfoReq.getMobile());
            if(users.size()>0){//手机号已存在
                errorList.add("该手机号已注册");
            }
        }
        return errorList;
    }

    @Override
    public List<UserFriendsReq> getUserFriends(Long userId) {

        UserRelationExample userRelationExample=new UserRelationExample();
        UserRelationExample.Criteria criteria=userRelationExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserRelation> relationList=userRelationMapper.selectByExample(userRelationExample);

        List<UserFriendsReq> friendList=new ArrayList<UserFriendsReq>();
        if(relationList!=null && !relationList.isEmpty()){
            for (UserRelation userRelation :relationList) {
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userRelation.getFriendId());
                if(userInfo == null){
                    break;
                }

                UserFriendsReq userFriend=new UserFriendsReq();
                userFriend.setFrindId(userRelation.getFriendId());
                userFriend.setIntimacy(userRelation.getIntimacy());
                userFriend.setRemarkName(userRelation.getRemarkName());
                userFriend.setGender(userInfo.getGender());
                userFriend.setMobile(userInfo.getMobile());
                userFriend.setQqCode(userInfo.getQqCode());
                userFriend.setNickName(userInfo.getNickName());
                friendList.add(userFriend);
            }
        }
        return friendList;
    }

    @Override
    public int addFriends(String ip,String token,UserFriendsReq userFriendsReq) {
        UserRelation userRelation=new UserRelation();
        userRelation.setFriendId(userFriendsReq.getFrindId());
        LoginRecord record=new LoginRecord();
        record.setToken(token);
        record.setLoginIp(ip);
        userRelation.setIntimacy(0);
        userRelation.setStatus(0);
        userRelation.setUserId(this.getUserIdByToken(record));
        userRelationMapper.insert(userRelation);
        return 0;
    }

    @Override
    public Long getUserIdByToken(LoginRecord record) {
        Long id=null;
        String token=record.getToken();
        String ip=record.getLoginIp();
        String key=EncryptKey.RKP_USER_INFO+"_"+token+"_"+ip;
        String userInfo=redisUtil.get(key,null);
        JSONObject jsonInfo=null;
        if(userInfo !=null){
            jsonInfo=JSONObject.parseObject(userInfo);
            id=jsonInfo.getLong("id");
        }else {
            Map<String,Object> paraMap=new HashMap<String, Object>();
            paraMap.put("token",token);
            paraMap.put("loginIp",ip);
            paraMap.put("outTime",new Timestamp(DateUtil.getSpecifiedDayBySecond(new Date(),-300).getTime()));
            List<LoginRecord> loginRecord=loginRecordMapper.getUserRecordByToken(paraMap);
            if(!loginRecord.isEmpty()){
                id=loginRecord.get(0).getUserId();
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("id",loginRecord.get(0).getId());
                map.put("renewal",(loginRecord.get(0).getRenewal()+1));
                loginRecordMapper.updateRenewal(map);
            }
        }
        return id;
    }

    @Override
    public List<UserInfo> getUserInfo(String reqParams) {
        if(reqParams ==null){
            return null;
        }
        List<UserInfo> users=userInfoMapper.fuzzyQueryUser(reqParams);
        return users;
    }

    @Override
    public UserInfo getOnlieUser(LoginRecord record) {
        UserInfo userInfo=null;
        String token=record.getToken();
        String ip=record.getLoginIp();
        String key=EncryptKey.RKP_USER_INFO+"_"+token+"_"+ip;
        String user=redisUtil.get(key,null);
        JSONObject jsonInfo=null;
        if(user !=null){
            JSONObject josnUser=JSONObject.parseObject(user);
            userInfo=new UserInfo();
            userInfo.setId(josnUser.getLong("id"));
            userInfo.setMobile(josnUser.getString("mobile"));
            userInfo.setNickName(josnUser.getString("nickName"));
            userInfo.setUserName(josnUser.getString("userName"));
        }else {
            Map<String,Object> paraMap=new HashMap<String, Object>();
            paraMap.put("token",token);
            paraMap.put("loginIp",ip);
            paraMap.put("outTime",new Timestamp(DateUtil.getSpecifiedDayBySecond(new Date(),-300).getTime()));
            List<LoginRecord> loginRecord=loginRecordMapper.getUserRecordByToken(paraMap);
            if(loginRecord !=null && !loginRecord.isEmpty() && check(loginRecord.get(0)) ){
                userInfo= userInfoMapper.selectByPrimaryKey(loginRecord.get(0).getUserId());
            }
        }
        return userInfo;
    }

    @Override
    public UserInfo getUserById(Long userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    private boolean check(LoginRecord loginToken){

        long interval=DateUtil.dateDiff(loginToken.getOutTime(),new Date());
        //阈值
        long threshold=1000L*300;
        //若上一次更新距现在大于5分钟  则登录已经失效
        if(interval< threshold){
            return true;
        }
        return false;
    }
}
