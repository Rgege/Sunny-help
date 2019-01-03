package org.blue.helper.StringHelper.service;

import org.blue.helper.StringHelper.persistence.entity.model.ServiceAccessLogWithBLOBs;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

public interface ServiceAccessLogService {

    public ServiceAccessLogWithBLOBs logAccess(String serviceName, String requestData, String responseData, Timestamp createTs, Timestamp endTime, HttpServletRequest request);
    public void updateAccess(ServiceAccessLogWithBLOBs log);
}
