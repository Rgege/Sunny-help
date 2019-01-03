package org.blue.helper.StringHelper.controller.support;

public class MessageRsp {
    private String msg;
    private Long sendId;

    public MessageRsp() {
    }

    public MessageRsp(String msg, Long sendId) {
        this.msg = msg;
        this.sendId = sendId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }
}
