package org.blue.helper.StringHelper.controller.bookkeeping;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.constants.BillTypeEnums;
import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.common.enums.SysCode;
import org.blue.helper.StringHelper.common.exception.HelperException;
import org.blue.helper.StringHelper.persistence.entity.model.LoginRecord;
import org.blue.helper.StringHelper.service.UserInfoService;
import org.blue.helper.StringHelper.utils.BillCodeUtil;
import org.blue.helper.StringHelper.utils.NetworkUtil;
import org.blue.helper.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BaseController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成规则：bill_type+userID+MD5(UUID+time)
     * @return
     */
    protected String creatRecordCode(String userId){
        String billUUID=BillCodeUtil.createBillCode(userId);
        redisUtil.set("RecordCode_"+userId,billUUID);
        return billUUID;
    }
    protected boolean validateRecordCode(String recordCode,String userId){
        boolean check=false;
        if (StringUtils.isNotBlank(recordCode) && StringUtils.isNotBlank(userId)){
            String cache=redisUtil.get("RecordCode_"+userId,"");
            if (recordCode.equals(cache)){
                check=true;
                redisUtil.del("RecordCode_"+userId);//一个 RecordCode 只用一次
            }
        }

        return check;
    }

    /**
     * 从hearder中取token
     */
    protected String getToken(HttpServletRequest request) {

        String token = null;
        try {
            token=request.getHeader("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    /**
     * 根据token获取userID
     */
    protected String getUserIdByToken(HttpServletRequest request,String token) {

        String userId = null;
        token=StringUtils.isBlank(token) ? this.getToken(request) : token;
        String key=EncryptKey.RKP_USER_INFO+"_"+token+"_"+NetworkUtil.getClientIpAddr(request);
        if (StringUtils.isNotBlank(token) ){
            String cache=redisUtil.get(key,null);
            if (StringUtils.isBlank(cache)){
                LoginRecord record=new LoginRecord();
                record.setLoginIp(NetworkUtil.getClientIpAddr(request));
                record.setToken(token);
                Long id=userInfoService.getUserIdByToken(record);
                if (id != null){
                    userId=id.toString();
                }
            }else {
                JSONObject jsonObject=JSONObject.parseObject(cache);
                userId=jsonObject.getString("id");
            }
        }
//        if (userId==null) throw new HelperException(SysCode.LOGIN_CHECK_ERROR.getMsg());
        return userId;
    }

}
