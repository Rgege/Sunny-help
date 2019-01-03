package org.blue.helper.StringHelper.service.bookkeeping.intf;

import com.github.pagehelper.PageInfo;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.RecordReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam.StatListReqParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.BillStatRspParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.DayBillsStatisticAnalysisRspParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.MonthBillsStatisticAnalysisRspParam;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.YearBillsStatisticAnalysisRspParam;

/**
 * 统计分析
 */
public interface BillStatService {

    YearBillsStatisticAnalysisRspParam yearBillsStatistic(RecordReqParam param);

    MonthBillsStatisticAnalysisRspParam monthBillsStatistic(RecordReqParam param);

    DayBillsStatisticAnalysisRspParam dayBillsStatistic(RecordReqParam param);

    PageInfo<BillStatRspParam> doBillStat(StatListReqParam reqParam, Long userId);
}
