package org.blue.helper.StringHelper.controller.support;

public class Message {
    private Long sendId;
    private Long targetId;
    private String message;

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sendId=" + sendId +
                ", targetId=" + targetId +
                ", message='" + message + '\'' +
                '}';
    }
}
