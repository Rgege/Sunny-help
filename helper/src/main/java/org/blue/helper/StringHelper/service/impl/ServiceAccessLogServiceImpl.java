package org.blue.helper.StringHelper.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.persistence.ServiceAccessLogMapper;
import org.blue.helper.StringHelper.persistence.entity.model.ServiceAccessLogWithBLOBs;
import org.blue.helper.StringHelper.service.ServiceAccessLogService;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.utils.NetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Service
public class ServiceAccessLogServiceImpl implements ServiceAccessLogService {
    @Autowired
    private ServiceAccessLogMapper serviceAccessLogMapper;
    @Autowired
    private ServerProperties serverProperties;

    private static Logger logger = LoggerFactory.getLogger(ServiceAccessLogServiceImpl.class);
    private static String serverIpAddr = NetworkUtil.getServerIpAddr();
    private int serverPort ;

    @Override
//	@Async
    public ServiceAccessLogWithBLOBs logAccess(String serviceName, String requestData, String responseData, Timestamp createTs,
                                               Timestamp endTime, HttpServletRequest request) {
        this.serverPort=serverProperties.getPort();
        // TODO Auto-generated method stub
        ServiceAccessLogWithBLOBs record = new ServiceAccessLogWithBLOBs();
        //生成结束时间，为记录接口的性能做准备

        long consumedTime = DateUtil.dateDiff(createTs, endTime);

        try {
            record.setServiceName(serviceName);
            if (request != null) {
                record.setRequestUrl(this.getRequestUrl(request, serviceName));
                String clientIp = request.getHeader("remoteIp");
                if (StringUtils.isEmpty(clientIp)) {
                    clientIp = NetworkUtil.getClientIpAddr(request);
                }
                record.setClientIp(clientIp);
            }
            String serverIp = serverIpAddr;
//			if (StringUtils.isEmpty(serverIp)) {
//				serverIp = NetworkUtil.getServerIpAddr();
//			}

            record.setRequestData(requestData);
            record.setServerIp(serverIp);
            record.setCreateTs(createTs);
            record.setUpdateTs(endTime);
            record.setResponseData(responseData);
            record.setConsumedTime(consumedTime);
            serviceAccessLogMapper.insert(record);
//			logger.info("Log主键ID"+record.getLogId());
        } catch (Exception e) {
            logger.error("插入数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return record;
    }

    /**
     * TODO
     *
     * @param request
     * @return
     */
    private String getRequestUrl(HttpServletRequest request, String serviceName) {
        String requestUrl = String.format("%s:/%s:%s/%s",
                request.getScheme(),
                serverIpAddr,
                serverPort,
                serviceName);
        return requestUrl;
    }

    @Override
    public void updateAccess(ServiceAccessLogWithBLOBs log) {
        try {
            serviceAccessLogMapper.updateByPrimaryKeySelective(log);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
