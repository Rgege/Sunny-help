package org.blue.helper.StringHelper.service.bookkeeping.intf;

import org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam.BillTypeRspParam;

public interface BillService {
    BillTypeRspParam getBillType(String pCode, int page, int pageSize);
    String getTypeNameByCode(String typeCode);
}
