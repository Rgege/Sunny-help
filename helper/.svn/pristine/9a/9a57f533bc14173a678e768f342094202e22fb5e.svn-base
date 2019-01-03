package org.blue.helper.StringHelper.persistence;

import org.apache.ibatis.annotations.Param;
import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.BillStatRspParam;
import org.blue.helper.StringHelper.persistence.entity.model.BillStatResult;

import java.util.List;
import java.util.Map;

public interface BillStatMapper {

    /**
     *
     * year userId
     * @return
     */
    List<BillStatResult> selectRecordsByYear(Map<String,Object> paramMap);

    /**
     *
     *  year userId month
     * @return
     */
    List<BillStatResult> selectRecordsByMonth(Map<String,Object> paramMap);

    /**
     *
     * year userId month day
     * @return
     */
    List<BillStatResult> selectRecordsByDay(Map<String,Object> paramMap);


    List<BillStatRspParam> selectYearRecord(@Param("userId")Long userId,@Param("year")String year);


    List<BillStatRspParam> selectMonthRecord(@Param("userId")Long userId,@Param("month")String month);


    List<BillStatRspParam> selectDayRecord(@Param("userId") Long userId,@Param("day")String day);
}
