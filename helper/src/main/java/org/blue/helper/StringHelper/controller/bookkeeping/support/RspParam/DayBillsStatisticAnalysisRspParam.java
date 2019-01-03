package org.blue.helper.StringHelper.controller.bookkeeping.support.RspParam;

import java.util.List;

public class DayBillsStatisticAnalysisRspParam {
    private String day;
    private Double totleAmount;
    private List<StatisticalElements> elements;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getTotleAmount() {
        return totleAmount;
    }

    public void setTotleAmount(Double totleAmount) {
        this.totleAmount = totleAmount;
    }

    public List<StatisticalElements> getElements() {
        return elements;
    }

    public void setElements(List<StatisticalElements> elements) {
        this.elements = elements;
    }
}

