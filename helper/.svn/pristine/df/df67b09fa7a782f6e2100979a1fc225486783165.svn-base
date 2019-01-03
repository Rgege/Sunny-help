package org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam;

import org.blue.helper.StringHelper.aop.annotation.Require;

public class StatListReqParam extends Page{
    @Require
    private String statVeidoo;
    private String date;

    public String getStatVeidoo() {
        return statVeidoo;
    }

    public void setStatVeidoo(String statVeidoo) {
        this.statVeidoo = statVeidoo;
    }

    @Override
    public Integer getCurrentPage() {
        return super.getCurrentPage()==null || super.getCurrentPage()==0 ? 1:super.getCurrentPage(); //默认显示第一页
    }

    @Override
    public Integer getPageSize() {
        return super.getPageSize()==null || super.getPageSize()==0 ? 6:super.getPageSize(); //默认每页显示6条数据
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
