package org.blue.helper.StringHelper.service;

import org.blue.helper.StringHelper.controller.support.req.HttpPosReq;
import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;

public interface HttpPosService {

    HttpPosRsp  doPosRequester(HttpPosReq httpPosReq);
}
