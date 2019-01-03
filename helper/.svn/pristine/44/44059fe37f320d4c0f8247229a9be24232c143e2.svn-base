package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.controller.support.AddUserFriendApplyDTO;
import org.blue.helper.StringHelper.persistence.FriendApplyRecordMapper;
import org.blue.helper.StringHelper.persistence.entity.model.FriendApplyRecord;
import org.blue.helper.StringHelper.service.UserFriendService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
@Service
public class UserFriendServiceImpl implements UserFriendService {
    private static final Logger logger=LoggerFactory.getLogger(UserFriendServiceImpl.class);

    @Autowired
    private FriendApplyRecordMapper friendApplyRecordMapper;

    @Override
    public void applyAddFriend(AddUserFriendApplyDTO addUserFriendApplyDTO) {

        FriendApplyRecord applyRecord=new FriendApplyRecord();
        applyRecord.setUserId(addUserFriendApplyDTO.getUserId());
        applyRecord.setFriendId(addUserFriendApplyDTO.getFriendId());
        applyRecord.setMemo(addUserFriendApplyDTO.getMemo());
        Date applyTime=new Date();
        Date expTime=DateUtil.getSpecifiedDayByHour(applyTime,72); //请求过期时间为72小时
        applyRecord.setCreateTime(new Timestamp(applyTime.getTime()));
        applyRecord.setUpdateTime(new Timestamp(applyTime.getTime()));
        applyRecord.setApplyTime(new Timestamp(applyTime.getTime()));
        applyRecord.setExpTime(new Timestamp(expTime.getTime()));
        applyRecord.setStatus(0);
        friendApplyRecordMapper.insert(applyRecord);
    }

    @Override
    public void deletFriend() {

    }
}
