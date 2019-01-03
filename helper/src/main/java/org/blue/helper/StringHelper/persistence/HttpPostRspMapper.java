package org.blue.helper.StringHelper.persistence;

import org.blue.helper.StringHelper.controller.support.rsp.HttpPosRsp;

import java.util.List;

public interface HttpPostRspMapper {
    void insert(HttpPosRsp record);

    void insertBatch(List<HttpPosRsp> records);
}
