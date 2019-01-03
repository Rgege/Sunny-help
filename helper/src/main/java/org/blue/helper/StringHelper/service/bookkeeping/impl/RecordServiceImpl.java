package org.blue.helper.StringHelper.service.bookkeeping.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.constants.BillTypeEnums;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.RecordReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.StatListReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.*;
import org.blue.helper.StringHelper.persistence.BillRecordDayMapper;
import org.blue.helper.StringHelper.persistence.BillRecordMapper;
import org.blue.helper.StringHelper.persistence.BillRecordMonthMapper;
import org.blue.helper.StringHelper.persistence.BillRecordYearMapper;
import org.blue.helper.StringHelper.persistence.entity.model.*;
import org.blue.helper.StringHelper.service.bookkeeping.intf.FileService;
import org.blue.helper.StringHelper.service.bookkeeping.intf.RecordService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordServiceImpl.class);
    @Autowired
    private BillRecordMapper recordMapper;
    @Autowired
    private BillRecordDayMapper recordDayMapper;
    @Autowired
    private BillRecordMonthMapper recordMonthMapper;
    @Autowired
    private BillRecordYearMapper recordYearMapper;

    @Autowired
    private FileService fileService;
    @Value("${vouchersUrl}")
    private String vouchersUrl;

    @Override
    public int newRecord(RecordReqParam param) {
        Timestamp now = new Timestamp(new Date().getTime());
        BillRecord record = new BillRecord();
        record.setBillMoney(Double.valueOf(param.getBillMoney()));
        record.setBillDepict(param.getBillDepict());
        record.setStatus(0);
        record.setRecordCode(param.getRecordCode());
        record.setUpdateTime(now);
        record.setCreatTime(now);
        record.setBillType(param.getBillType());
        int i = recordMapper.insertSelective(record);
        if (param.getBillImg() != null) {
            String fileUrl = vouchersUrl + param.getUserId() + File.separator + param.getRecordCode() + File.separator;
            fileService.saveBase64File(param.getBillImg(), fileUrl, param.getImgName());
        }
        this.insertYMD(param);
        return i;
    }

    @Async
    @Transactional
    public void insertYMD(RecordReqParam param) {
        BillRecordYear billRecordYear = new BillRecordYear();
        BillRecordMonth billRecordMonth = new BillRecordMonth();
        BillRecordDay billRecordDay = new BillRecordDay();

        String recordTime = param.getTime();
        Date date = DateUtil.getDateFromStr(recordTime);
        Double ammont = Double.valueOf(param.getBillMoney());
        BigDecimal bigDecimal = new BigDecimal(ammont);
        Timestamp now = new Timestamp(new Date().getTime());

        //1、年记录相关
        Long yearId;
        BillRecordYearExample ye = new BillRecordYearExample();
        BillRecordYearExample.Criteria ycriteria = ye.createCriteria();
        ycriteria.andUserIdEqualTo(Long.valueOf(param.getUserId()));
        ycriteria.andYearEqualTo(DateUtil.getYear(date));
        List<BillRecordYear> recordYears = recordYearMapper.selectByExample(ye);
        if (!recordYears.isEmpty()) {//年记录以及存在
            Long orgCount = recordYears.get(0).getYearCount();
            billRecordYear.setId(recordYears.get(0).getId());
            billRecordYear.setYearCount(orgCount + 1L);
            billRecordYear.setUpdateTime(now);
            if (StringUtil.isStartWith(param.getBillType(), "001")) {//支出
                billRecordYear.setYearExp(bigDecimal.add(new BigDecimal(recordYears.get(0).getYearExp())).doubleValue());
            } else if (StringUtil.isStartWith(param.getBillType(), "002")) {//收入
                billRecordYear.setYearInc(bigDecimal.add(new BigDecimal(recordYears.get(0).getYearInc())).doubleValue());
            }
            yearId = recordYears.get(0).getId();
            recordYearMapper.updateByPrimaryKeySelective(billRecordYear);
        } else {//新增年记录
            billRecordYear.setYearCount(1L);
            billRecordYear.setCreatTime(now);
            billRecordYear.setUpdateTime(now);
            billRecordYear.setYear(DateUtil.getYear(date));
            billRecordYear.setStatus(0);
            billRecordYear.setUserId(Long.valueOf(param.getUserId()));
            if (StringUtil.isStartWith(param.getBillType(), "001")) {//支出
                billRecordYear.setYearExp(bigDecimal.doubleValue());
                billRecordYear.setYearInc(0.0);
            } else if (StringUtil.isStartWith(param.getBillType(), "002")) {//收入
                billRecordYear.setYearInc(bigDecimal.doubleValue());
                billRecordYear.setYearExp(0.0);
            }
            recordYearMapper.insertSelective(billRecordYear);
            yearId =billRecordYear.getId();
        }
        //2、月表相关
        Long monthId;
        BillRecordMonthExample monthExample = new BillRecordMonthExample();
        BillRecordMonthExample.Criteria monthCriteria = monthExample.createCriteria();
        monthCriteria.andYIdEqualTo(yearId);
        monthCriteria.andMonthEqualTo(DateUtil.getYear(date) + DateUtil.getMonth(date));
        List<BillRecordMonth> billRecordMonths = recordMonthMapper.selectByExample(monthExample);
        if (!billRecordMonths.isEmpty()) {
            Long orgMCount = billRecordMonths.get(0).getMonthCount();
            billRecordMonth.setId(billRecordMonths.get(0).getId());
            billRecordMonth.setMonthCount(orgMCount + 1L);
            billRecordMonth.setUpdateTime(now);
            if (StringUtil.isStartWith(param.getBillType(), "001")) {//支出
                billRecordMonth.setMonthExp(bigDecimal.add(new BigDecimal(billRecordMonths.get(0).getMonthExp())).doubleValue());
            } else if (StringUtil.isStartWith(param.getBillType(), "002")) {//收入
                billRecordMonth.setMonthInc(bigDecimal.add(new BigDecimal(billRecordMonths.get(0).getMonthInc())).doubleValue());
            }
            recordMonthMapper.updateByPrimaryKeySelective(billRecordMonth);
            monthId = billRecordMonths.get(0).getId();
        } else {
            billRecordMonth.setyId(yearId);
            billRecordMonth.setMonth(DateUtil.getYear(date) + DateUtil.getMonth(date));
            billRecordMonth.setStatus(0);
            billRecordMonth.setMonthCount(1L);
            billRecordMonth.setUpdateTime(now);
            billRecordMonth.setCreatTime(now);
            if (StringUtil.isStartWith(param.getBillType(), "001")) {//支出
                billRecordMonth.setMonthInc(0.0);
                billRecordMonth.setMonthExp(bigDecimal.doubleValue());
            } else if (StringUtil.isStartWith(param.getBillType(), "002")) {//收入
                billRecordMonth.setMonthInc(bigDecimal.doubleValue());
                billRecordMonth.setMonthExp(0.0);
            }
           recordMonthMapper.insertSelective(billRecordMonth);
            monthId =billRecordMonth.getId();
        }
        //3、日表相关
        Long dayId;
        BillRecordDayExample dayExample = new BillRecordDayExample();
        BillRecordDayExample.Criteria dayCriteria = dayExample.createCriteria();
        dayCriteria.andMIdEqualTo(monthId);
        dayCriteria.andDayEqualTo(DateUtil.getYear(date) + DateUtil.getMonth(date) + DateUtil.getDay(date));
        List<BillRecordDay> recordDays = recordDayMapper.selectByExample(dayExample);
        if (!recordDays.isEmpty()) {
            Long orgDayCount = recordDays.get(0).getDayCount();
            billRecordDay.setId(recordDays.get(0).getId());
            billRecordDay.setUpdateTime(now);
            billRecordDay.setDayCount(orgDayCount + 1L);
            if (StringUtil.isStartWith(param.getBillType(), "001")) {//支出
                billRecordDay.setDayExp(bigDecimal.add(new BigDecimal(recordDays.get(0).getDayExp())).doubleValue());
            } else if (StringUtil.isStartWith(param.getBillType(), "002")) {//收入
                billRecordDay.setDayInc(bigDecimal.add(new BigDecimal(recordDays.get(0).getDayInc())).doubleValue());
            }
            recordDayMapper.updateByPrimaryKeySelective(billRecordDay);
            dayId = recordDays.get(0).getId();
        } else {
            billRecordDay.setCreatTime(now);
            billRecordDay.setUpdateTime(now);
            billRecordDay.setmId(monthId);
            billRecordDay.setDay(DateUtil.getYear(date) + DateUtil.getMonth(date) + DateUtil.getDay(date));
            if (StringUtil.isStartWith(param.getBillType(), "001")) {//支出
                billRecordDay.setDayExp(bigDecimal.doubleValue());
                billRecordDay.setDayInc(0.0);
            } else if (StringUtil.isStartWith(param.getBillType(), "002")) {//收入
                billRecordDay.setDayInc(bigDecimal.doubleValue());
                billRecordDay.setDayExp(0.0);
            }
            billRecordDay.setDayCount(1L);
            billRecordDay.setStatus(0);
            recordDayMapper.insertSelective(billRecordDay);
            dayId = billRecordDay.getId();
        }
        //更新记录表 关联日表
        BillRecord record = new BillRecord();
        record.setRecordCode(param.getRecordCode());
        record.setdId(dayId);
        record.setUpdateTime(now);
        recordMapper.updateByRecordCode(record);
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(100.000);
        System.out.println(bigDecimal.add(new BigDecimal(200.000)).doubleValue());
    }

    @Override
    public BillRecordYear getYearRecord(String year, Long userId) {
        BillRecordYear recordYear = recordYearMapper.getTotleAmount(year, userId);
        return recordYear;
    }

}












