package org.blue.helper.StringHelper.persistence;


import org.apache.ibatis.annotations.Param;
import org.blue.helper.StringHelper.persistence.entity.model.ServiceAccessLog;
import org.blue.helper.StringHelper.persistence.entity.model.ServiceAccessLogExample;
import org.blue.helper.StringHelper.persistence.entity.model.ServiceAccessLogWithBLOBs;

import java.util.List;
import java.util.Map;

public interface ServiceAccessLogMapper {
    int countByExample(ServiceAccessLogExample example);

    int deleteByExample(ServiceAccessLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(ServiceAccessLogWithBLOBs record);

    int insertSelective(ServiceAccessLogWithBLOBs record);

    List<ServiceAccessLogWithBLOBs> selectByExampleWithBLOBs(ServiceAccessLogExample example);

    List<ServiceAccessLog> selectByExample(ServiceAccessLogExample example);

    ServiceAccessLogWithBLOBs selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") ServiceAccessLogWithBLOBs record, @Param("example") ServiceAccessLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ServiceAccessLogWithBLOBs record, @Param("example") ServiceAccessLogExample example);

    int updateByExample(@Param("record") ServiceAccessLog record, @Param("example") ServiceAccessLogExample example);

    int updateByPrimaryKeySelective(ServiceAccessLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ServiceAccessLogWithBLOBs record);

    int updateByPrimaryKey(ServiceAccessLog record);

    List<ServiceAccessLogWithBLOBs> selectDynamic(Map<String,Object> map);
}