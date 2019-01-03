package org.blue.helper.StringHelper.service;

import org.blue.helper.StringHelper.controller.support.req.HttpPosReq;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;

import java.util.List;

public interface HttpRequestService {
    List<HttpPosRsp> httpRequest(HttpPosReq httpPosReq);
}
