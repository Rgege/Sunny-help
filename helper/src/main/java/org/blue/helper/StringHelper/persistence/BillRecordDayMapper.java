package org.blue.helper.StringHelper.persistence;

import org.apache.ibatis.annotations.Param;
import org.blue.helper.StringHelper.persistence.entity.model.BillRecordDay;
import org.blue.helper.StringHelper.persistence.entity.model.BillRecordDayExample;

import java.util.List;

public interface BillRecordDayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int countByExample(BillRecordDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int deleteByExample(BillRecordDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int insert(BillRecordDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    Long insertSelective(BillRecordDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    List<BillRecordDay> selectByExample(BillRecordDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    BillRecordDay selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int updateByExampleSelective(@Param("record") BillRecordDay record, @Param("example") BillRecordDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int updateByExample(@Param("record") BillRecordDay record, @Param("example") BillRecordDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int updateByPrimaryKeySelective(BillRecordDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_record_day
     *
     * @mbggenerated Wed Nov 21 15:49:52 CST 2018
     */
    int updateByPrimaryKey(BillRecordDay record);
}