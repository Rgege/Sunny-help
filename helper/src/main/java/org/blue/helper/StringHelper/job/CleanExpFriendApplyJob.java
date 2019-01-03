package org.blue.helper.StringHelper.job;

import org.blue.helper.StringHelper.common.constants.TimeConstant;
import org.blue.helper.StringHelper.persistence.FriendApplyRecordMapper;
import org.blue.helper.StringHelper.persistence.entity.model.FriendApplyRecord;
import org.blue.helper.StringHelper.persistence.entity.model.FriendApplyRecordExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.Date;

public class CleanExpFriendApplyJob {
    private static final Logger log=LoggerFactory.getLogger(CleanExpFriendApplyJob.class);

    @Autowired
    private FriendApplyRecordMapper friendApplyRecordMapper;

    /**
     * 每隔两个小时更新申请过期记录
     */
    @Scheduled(fixedRate = 1000L * 60 * 60*2)
    public void updateExpFriendApply(){
        log.debug("######################## updateExpFriendApply");

        Timestamp thisTime=new Timestamp(new Date().getTime());
        FriendApplyRecordExample example=new FriendApplyRecordExample();
        FriendApplyRecordExample.Criteria criteria=example.createCriteria();
        criteria.andExpTimeLessThanOrEqualTo(thisTime);
        criteria.andStatusEqualTo(0);

        FriendApplyRecord record =new FriendApplyRecord();
        record.setStatus(3);
        record.setUpdateTime(thisTime);
        friendApplyRecordMapper.updateByExample(record,example);
    }

    /**
     * 每天凌晨2点删除七天前的请求
     */
    @Scheduled(cron =TimeConstant.every_2_oclock)
    public void cleanExpFriendApply(){
        log.debug("######################## cleanExpFriendApply");

        Timestamp aWeekAgo=new Timestamp(new Date().getTime()-TimeConstant.week);

        FriendApplyRecordExample example=new FriendApplyRecordExample();
        FriendApplyRecordExample.Criteria criteria=example.createCriteria();
        criteria.andApplyTimeLessThanOrEqualTo(aWeekAgo);
        criteria.andStatusNotEqualTo(1);
        criteria.andStatusNotEqualTo(2);
        friendApplyRecordMapper.deleteByExample(example);
    }
}
