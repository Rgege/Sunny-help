package org.blue.helper.StringHelper.service;

import org.blue.helper.StringHelper.persistence.entity.model.LoginRecord;
import org.blue.helper.StringHelper.persistence.entity.model.UserInfo;


public interface UserInfoAsyncService {

    /**
     * 异步保存用户信息至redis
     * @param userInfo
     * @param key
     */
    void addUser2RedisByAsync(UserInfo userInfo, String key);

    void updateLoginRecordByAsync(LoginRecord loginToken);
}
