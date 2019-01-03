package org.blue.helper.StringHelper.common.constants;

import org.blue.helper.StringHelper.common.exception.HelperException;

import java.util.HashMap;
import java.util.Map;

public enum BillTypeEnums {
    //支出001X income and expenses
    EXPENSES("001","支出"),
    //收入002X
    INCOME("002","收入");


    String code;
    String detail;

    BillTypeEnums(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public enum Veidoo{
        YEAR("0"),
        MONTH("1"),
        DAY("2")
        ;
        String code;

        Veidoo(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
