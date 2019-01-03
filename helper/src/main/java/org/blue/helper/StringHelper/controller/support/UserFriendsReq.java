package org.blue.helper.StringHelper.controller.support;

public class UserFriendsReq {

    private Long frindId;
    private String remarkName;
    private Integer intimacy;
    private Integer gender;
    private String nickName;
    private String mobile;
    private String qqCode;

    public Long getFrindId() {
        return frindId;
    }

    public void setFrindId(Long frindId) {
        this.frindId = frindId;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public Integer getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Integer intimacy) {
        this.intimacy = intimacy;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQqCode() {
        return qqCode;
    }

    public void setQqCode(String qqCode) {
        this.qqCode = qqCode;
    }
}
