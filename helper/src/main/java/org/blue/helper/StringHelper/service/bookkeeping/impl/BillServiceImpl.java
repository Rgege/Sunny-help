package org.blue.helper.StringHelper.service.bookkeeping.impl;

import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.BillTypeRspParam;
import org.blue.helper.StringHelper.persistence.BillTypeMapper;
import org.blue.helper.StringHelper.persistence.entity.model.BillType;
import org.blue.helper.StringHelper.persistence.entity.model.BillTypeExample;
import org.blue.helper.StringHelper.service.bookkeeping.intf.BillService;
import org.blue.helper.StringHelper.common.databaseStructure.CircularLinkedList;
import org.blue.helper.StringHelper.utils.BeanConverUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER=LoggerFactory.getLogger(BillServiceImpl.class);
    @Autowired
    private BillTypeMapper billTypeMapper;
    @Autowired
    private RedisUtil redisUtil;

    private static final String RedisKey = "Bill_Type_List_";

    @Override
    public BillTypeRspParam getBillType(String pCode, int page, int pageSize) {

        List<String> list = null;
        try {
            list = redisUtil.getList(RedisKey+pCode);
        } catch (Exception e) {
            LOGGER.error(" An exception occurred while querying data from Redis ",e);
        }

        List<BillType> billTypes;
        if (list == null || list.isEmpty()) {
            BillTypeExample example = new BillTypeExample();
            BillTypeExample.Criteria criteria = example.createCriteria();
            criteria.andPCodeEqualTo(pCode);
            billTypes = billTypeMapper.selectByExample(example);
            if (billTypes==null || billTypes.size()==0){
                return null;
            }else {
                try {
                    redisUtil.pushList(RedisKey+pCode, BeanConverUtil.toStringList(billTypes), 300);
                } catch (Exception e) {
                    LOGGER.error(" An exception occurred while insert data into Redis ",e);
                }
            }
        }else {
            billTypes=BeanConverUtil.listConver(list,BillType.class);
        }

        CircularLinkedList<BillType> billList = new CircularLinkedList<BillType>(billTypes.size());
        for (BillType billType : billTypes) {
            billList.add(billType);
        }
        try {
            List<BillType> resultList=billList.rangeList(page,pageSize);
            BillTypeRspParam rspParam=new BillTypeRspParam();
            rspParam.setBillTypes(resultList);
            rspParam.setMaxPage(billList.size());
            return rspParam;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getTypeNameByCode(String typeCode) {
        BillTypeExample example =new BillTypeExample();
        BillTypeExample.Criteria criteria=example.createCriteria();
        criteria.andTypeCodeEqualTo(typeCode);

        List<BillType> billTypes=billTypeMapper.selectByExample(example);
        if (billTypes!=null && !billTypes.isEmpty()){
            return billTypes.get(0).getTypeName();
        }else return "";
    }
}
