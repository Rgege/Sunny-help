package org.blue.helper.StringHelper.service.bookkeeping.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.blue.helper.StringHelper.common.constants.BillTypeEnums;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.RecordReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.StatListReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.*;
import org.blue.helper.StringHelper.persistence.BillStatMapper;
import org.blue.helper.StringHelper.persistence.entity.model.BillStatResult;
import org.blue.helper.StringHelper.service.bookkeeping.intf.BillService;
import org.blue.helper.StringHelper.service.bookkeeping.intf.BillStatService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.utils.NumberUtils;
import org.blue.helper.StringHelper.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
@Service
public class BillStatServiceImpl implements BillStatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillStatServiceImpl.class);

    @Autowired
    private BillStatMapper statMapper;
    @Autowired
    private BillService billService;

    @Override
    public YearBillsStatisticAnalysisRspParam yearBillsStatistic(RecordReqParam param) {
        String recordTime = param.getTime();
        Date date = DateUtil.getDateFromStr(recordTime);
        String year=DateUtil.getYear(date);

        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("year",year);
        paramMap.put("userId",param.getUserId());
        List<BillStatResult> statResultList=statMapper.selectRecordsByYear(paramMap);

        YearBillsStatisticAnalysisRspParam rspParam=new YearBillsStatisticAnalysisRspParam();
        if (statResultList==null||statResultList.isEmpty()){
            return rspParam;
        }
        Double totalAmount=this.sumTotalAmount(param,statResultList);
        rspParam.setTotleAmount(totalAmount);
        rspParam.setElements(this.getElementList(statResultList,totalAmount,param.getIncOrExp()));
        rspParam.setYear(year);

        return rspParam;
    }

    @Override
    public MonthBillsStatisticAnalysisRspParam monthBillsStatistic(RecordReqParam param) {
        String recordTime = param.getTime();
        Date date = DateUtil.getDateFromStr(recordTime);
        String year=DateUtil.getYear(date);
        String month=DateUtil.getYear(date)+DateUtil.getMonth(date);

        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("year",year);
        paramMap.put("userId",param.getUserId());
        paramMap.put("month",month);
        List<BillStatResult> statResultList=statMapper.selectRecordsByMonth(paramMap);

        MonthBillsStatisticAnalysisRspParam rspParam=new MonthBillsStatisticAnalysisRspParam();
        if (statResultList==null||statResultList.isEmpty()){
            return rspParam;
        }
        Double totalAmount=this.sumTotalAmount(param,statResultList);
        rspParam.setTotleAmount(totalAmount);
        rspParam.setElements(this.getElementList(statResultList,totalAmount,param.getIncOrExp()));
        rspParam.setMonth(month);

        return rspParam;
    }

    @Override
    public DayBillsStatisticAnalysisRspParam dayBillsStatistic(RecordReqParam param) {
        String recordTime = param.getTime();
        Date date = DateUtil.getDateFromStr(recordTime);
        String year=DateUtil.getYear(date);
        String month=DateUtil.getYear(date)+DateUtil.getMonth(date);
        String day=DateUtil.getYear(date)+DateUtil.getMonth(date)+DateUtil.getDay(date);

        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("year",year);
        paramMap.put("userId",param.getUserId());
        paramMap.put("month",month);
        paramMap.put("day",day);

        List<BillStatResult> statResultList=statMapper.selectRecordsByDay(paramMap);

        DayBillsStatisticAnalysisRspParam rspParam=new DayBillsStatisticAnalysisRspParam();
        if (statResultList==null||statResultList.isEmpty()){
            return rspParam;
        }
        Double totalAmount=this.sumTotalAmount(param,statResultList);
        rspParam.setTotleAmount(totalAmount);
        rspParam.setElements(this.getElementList(statResultList,totalAmount,param.getIncOrExp()));
        rspParam.setDay(day);

        return rspParam;
    }

    @Override
    public PageInfo<BillStatRspParam> doBillStat(StatListReqParam reqParam, Long userId) {
        String date=reqParam.getDate();
        if (date!=null){
            date=date.replaceAll("-","").trim();
            if (!NumberUtils.isNumeric(date)){
                date=null;
            }
        }


        PageHelper.startPage(reqParam.getCurrentPage(), reqParam.getPageSize());

        List<BillStatRspParam> resultList;
        if (BillTypeEnums.Veidoo.YEAR.getCode().equals(reqParam.getStatVeidoo())){
            resultList=statMapper.selectYearRecord(userId,date);
        }else if (BillTypeEnums.Veidoo.MONTH.getCode().equals(reqParam.getStatVeidoo())){
            resultList=statMapper.selectMonthRecord(userId,date);
        }else if (BillTypeEnums.Veidoo.DAY.getCode().equals(reqParam.getStatVeidoo())){
            resultList=statMapper.selectDayRecord(userId,date);
        }else {
            resultList=new ArrayList<BillStatRspParam>();
        }
        PageInfo<BillStatRspParam> page=new PageInfo<BillStatRspParam>(resultList);

        return page;
    }

    private Double sumTotalAmount(RecordReqParam param,List<BillStatResult> statResultList){
        Double d=0.0;
        BigDecimal decimal=new BigDecimal(d);
        for (BillStatResult result:statResultList) {
            if (StringUtil.isStartWith(result.getBillType(), param.getIncOrExp())){
                decimal=decimal.add(new BigDecimal(result.getItemAmount()));
            }
        }
        return decimal.doubleValue();
    }


    private List<StatisticalElements> getElementList(List<BillStatResult> statResultList,Double totalAmount,String filter){
        List<StatisticalElements> elements=new ArrayList<StatisticalElements>();
        for (BillStatResult result:statResultList) {
            if (StringUtil.isStartWith(result.getBillType(), filter)){
                StatisticalElements element=new StatisticalElements();
                element.setElementName(billService.getTypeNameByCode(result.getBillType()));
                element.setElementVal(result.getItemAmount());
                BigDecimal SG=new BigDecimal(result.getItemAmount()).divide(new BigDecimal(totalAmount),3, RoundingMode.HALF_UP);
                element.setAccountFor(SG.doubleValue());
                elements.add(element);
            }
        }
        return elements;
    }



    public static void main(String[] args) {
        List<Double> list=new ArrayList<Double>();
        list.add(2.2);
        list.add(3.3);
        list.add(5.7);
        list.add(9.8);
        list.add(10.7);
        Double d=0.0;
        BigDecimal decimal=new BigDecimal(d);
        for (Double dd:list) {
            decimal=decimal.add(new BigDecimal(dd));
        }
        System.out.println("total:"+decimal.doubleValue());
        for (Double dd:list) {
            System.out.println(dd+"(SG):"+(new BigDecimal(dd).divide(decimal,3, RoundingMode.HALF_UP).doubleValue()));
        }
    }
}
