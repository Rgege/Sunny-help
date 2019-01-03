package org.blue.helper.StringHelper.controller.bookkeeping.support.ReqParam;

import org.blue.helper.StringHelper.aop.annotation.Require;
import org.blue.helper.StringHelper.aop.support.CheckType;
import org.blue.helper.StringHelper.controller.bookkeeping.support.BaseParam;

public class RecordReqParam extends BaseParam {

    @Require(CheckType.NOTNULL)
    private String recordCode;
    @Require(CheckType.NOTNULL)
    private String time;

    @Require(CheckType.NOTNULL)
    private String billType;

    @Require(CheckType.NUMBER)
    private String billMoney;

    private String billDepict;
    private String billImg;
    private String imgName;

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(String billMoney) {
        this.billMoney = billMoney;
    }

    public String getBillDepict() {
        return billDepict;
    }

    public void setBillDepict(String billDepict) {
        this.billDepict = billDepict;
    }

    public String getBillImg() {
        return billImg;
    }

    public void setBillImg(String billImg) {
        this.billImg = billImg;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "RecordReqParam{" +
                "recordCode='" + recordCode + '\'' +
                ", time='" + time + '\'' +
                ", billType='" + billType + '\'' +
                ", billMoney=" + billMoney +
                ", billDepict='" + billDepict + '\'' +
                ", billImg='" + billImg + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}
