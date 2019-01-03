package org.blue.helper.StringHelper.service.impl;

import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.common.constants.RedisKeys;
import org.blue.helper.StringHelper.controller.support.Message;
import org.blue.helper.StringHelper.controller.support.OfflineMsgDTO;
import org.blue.helper.StringHelper.persistence.OfflineMsgMapper;
import org.blue.helper.StringHelper.persistence.entity.model.OfflineMsg;
import org.blue.helper.StringHelper.persistence.entity.model.OfflineMsgExample;
import org.blue.helper.StringHelper.service.ChartRoomService;
import org.blue.helper.StringHelper.utils.BeanConverUtil;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChartRoomServiceImpl implements ChartRoomService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OfflineMsgMapper offlineMsgMapper;

    @Async
    @Override
    public void addOnlineRecored(String userId) {
        redisUtil.addSet(RedisKeys.onlineSetKey,userId);
    }

    @Override
    public void delOnlineRecored(String userId) {
        redisUtil.removeSetValue(RedisKeys.onlineSetKey,userId);
    }

    @Async
    @Override
    public void addOfflineMsg(Message message) {
        OfflineMsg msg=new OfflineMsg();
        Date thisTime=new Date();
        msg.setMessage(message.getMessage());
        msg.setTargetId(message.getTargetId());
        msg.setSendId(message.getSendId());
        msg.setSendTime(new Timestamp(thisTime.getTime()));
        msg.setExpTime(new Timestamp(DateUtil.getSpecifiedDayByHour(thisTime,48).getTime()));

        offlineMsgMapper.insert(msg);
    }

    @Override
    public List<OfflineMsgDTO> getOfflineMsg(String userId) {
        OfflineMsgExample example=new OfflineMsgExample();
        OfflineMsgExample.Criteria criteria=example.createCriteria();
        criteria.andTargetIdEqualTo(Long.valueOf(userId));
        criteria.andExpTimeGreaterThanOrEqualTo(new Timestamp(new Date().getTime()));

        List<OfflineMsg> offlineMsgs=offlineMsgMapper.selectByExample(example);
        List<OfflineMsgDTO> offlineMsgList=new ArrayList<OfflineMsgDTO>();

        for (OfflineMsg offlineMsg:offlineMsgs) {
            OfflineMsgDTO offlineMsgDTO=new OfflineMsgDTO();
            offlineMsgDTO.setMessage(offlineMsg.getMessage());
            offlineMsgDTO.setSendId(offlineMsg.getSendId());
            offlineMsgDTO.setSendTime(offlineMsg.getSendTime());
            offlineMsgDTO.setTargetId(offlineMsg.getTargetId());
            offlineMsgList.add(offlineMsgDTO);
        }
        return offlineMsgList;
    }

//    @Override
//    public void addOfflineMsg(Message message) {
//        String key=EncryptKey.OFFLINEMSG+message.getTargetId();
//
//        OfflineMsgDTO po=new OfflineMsgDTO();
//        po.setMessage(message.getMessage());
//        po.setTargetId(message.getTargetId());
//        po.setSendId(message.getSendId());
//        po.setSendTime(new Date());
//
//        try {
//            if(redisUtil.lock(RedisKeys.offlineMsgLockKey,key,3)){
//                List<String> msgs=redisUtil.getList(key);
//                if(msgs!=null && !msgs.isEmpty()){
//                    redisUtil.pushList(key, BeanConverUtil.beanToString(po));
//                }else {
//                    List<String> msgsList=new ArrayList<>();
//                    msgsList.add(BeanConverUtil.beanToString(po));
//                    redisUtil.newList(key,msgsList);
//                }
//            }else {
//                this.retry(message);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            redisUtil.del(RedisKeys.offlineMsgLockKey);
//        }
//
//    }
//    private void retry(Message message){
//        try {
//            Thread.sleep(3000);
//            this.addOfflineMsg(message);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
