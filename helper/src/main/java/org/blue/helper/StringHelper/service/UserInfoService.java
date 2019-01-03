package org.blue.helper.StringHelper.service;

import org.blue.helper.StringHelper.controller.support.UserFriendsReq;
import org.blue.helper.StringHelper.controller.support.UserInfoReq;
import org.blue.helper.StringHelper.persistence.entity.model.LoginRecord;
import org.blue.helper.StringHelper.persistence.entity.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    void addUser(UserInfoReq userInfoReq);

    Map<String,Object> getUserByPwd(Map<String,Object> paraMap);

    List<String> verifyRegisterInfo(UserInfoReq userInfoReq);

    List<UserFriendsReq> getUserFriends(Long userId);

    int addFriends(String ip,String token,UserFriendsReq userFriendsReq);

    Long getUserIdByToken(LoginRecord record);

    List<UserInfo> getUserInfo(String reqParams);

    UserInfo getOnlieUser(LoginRecord record);

    UserInfo getUserById(Long userId);
}
