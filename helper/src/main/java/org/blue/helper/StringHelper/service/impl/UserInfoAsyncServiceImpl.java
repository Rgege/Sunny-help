package org.blue.helper.StringHelper.service.impl;

import com.alibaba.fastjson.JSON;
import org.blue.helper.StringHelper.persistence.LoginRecordMapper;
import org.blue.helper.StringHelper.persistence.entity.model.LoginRecord;
import org.blue.helper.StringHelper.persistence.entity.model.UserInfo;
import org.blue.helper.StringHelper.service.UserInfoAsyncService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoAsyncServiceImpl implements UserInfoAsyncService {
    private static Logger logger = LoggerFactory.getLogger(UserInfoAsyncServiceImpl.class);

    @Autowired
    private LoginRecordMapper loginRecordMapper;
    @Autowired
    @Qualifier("redisUtil")
    private RedisUtil redisUtil;

    @Async
    @Override
    public void addUser2RedisByAsync(UserInfo userInfo, String key) {
        logger.info("===========>将用户信息存入Redis: key:"+key+"; val:"+JSON.toJSONString(userInfo));
        redisUtil.set(key, JSON.toJSONString(userInfo), 300);
    }
    @Async
    @Override
    public void updateLoginRecordByAsync(LoginRecord loginToken) {
        Map<String,Object> paraMap=new HashMap<String, Object>();
        paraMap.put("token",loginToken.getToken());
        paraMap.put("loginIp",loginToken.getLoginIp());
        paraMap.put("outTime",new Timestamp(DateUtil.getSpecifiedDayBySecond(new Date(),-300).getTime()));
        List<LoginRecord>records= loginRecordMapper.getUserRecordByToken(paraMap);
        if(records !=null && !records.isEmpty()){
            Map<String,Object> map =new HashMap<String, Object>();
            map.put("id",records.get(0).getId());
            map.put("renewal",(records.get(0).getRenewal()+1));
            loginRecordMapper.updateRenewal(map);
        }
    }
}
