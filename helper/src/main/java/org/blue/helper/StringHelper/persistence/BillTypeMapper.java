package org.blue.helper.StringHelper.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.blue.helper.StringHelper.persistence.entity.model.BillType;
import org.blue.helper.StringHelper.persistence.entity.model.BillTypeExample;

public interface BillTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int countByExample(BillTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int deleteByExample(BillTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int insert(BillType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int insertSelective(BillType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    List<BillType> selectByExample(BillTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    BillType selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int updateByExampleSelective(@Param("record") BillType record, @Param("example") BillTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int updateByExample(@Param("record") BillType record, @Param("example") BillTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int updateByPrimaryKeySelective(BillType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill_type
     *
     * @mbggenerated Tue Nov 27 10:11:58 CST 2018
     */
    int updateByPrimaryKey(BillType record);
}
