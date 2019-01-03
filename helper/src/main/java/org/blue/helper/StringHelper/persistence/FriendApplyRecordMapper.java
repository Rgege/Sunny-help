package org.blue.helper.StringHelper.persistence;

import org.apache.ibatis.annotations.Param;
import org.blue.helper.StringHelper.persistence.entity.model.FriendApplyRecord;
import org.blue.helper.StringHelper.persistence.entity.model.FriendApplyRecordExample;

import java.util.List;

public interface FriendApplyRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int countByExample(FriendApplyRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int deleteByExample(FriendApplyRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int insert(FriendApplyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int insertSelective(FriendApplyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    List<FriendApplyRecord> selectByExample(FriendApplyRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    FriendApplyRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int updateByExampleSelective(@Param("record") FriendApplyRecord record, @Param("example") FriendApplyRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int updateByExample(@Param("record") FriendApplyRecord record, @Param("example") FriendApplyRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int updateByPrimaryKeySelective(FriendApplyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend_apply_record
     *
     * @mbggenerated Fri Oct 12 16:12:22 CST 2018
     */
    int updateByPrimaryKey(FriendApplyRecord record);
}