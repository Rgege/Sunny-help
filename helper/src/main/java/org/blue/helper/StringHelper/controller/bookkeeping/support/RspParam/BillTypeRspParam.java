package org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam;

import org.blue.helper.StringHelper.persistence.entity.model.BillType;

import java.util.List;

public class BillTypeRspParam {
    private Integer maxPage;
    private List<BillType> billTypes;

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public List<BillType> getBillTypes() {
        return billTypes;
    }

    public void setBillTypes(List<BillType> billTypes) {
        this.billTypes = billTypes;
    }
}
