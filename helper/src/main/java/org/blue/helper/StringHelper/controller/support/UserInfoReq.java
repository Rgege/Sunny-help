package org.blue.helper.StringHelper.controller.support;



import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class UserInfoReq {


    private Long id;

    private String idenNo;

    private Integer gender;

    private Integer age;

    private String nickName;

    private String userName;

    private String password;

    private String mobile;

    private String email;

    private String qqCode;

    private String headPortrait;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdenNo() {
        return idenNo;
    }

    public void setIdenNo(String idenNo) {
        this.idenNo = idenNo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqCode() {
        return qqCode;
    }

    public void setQqCode(String qqCode) {
        this.qqCode = qqCode;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public ArrayList<String> validate(){
        ArrayList<String> errors=new ArrayList<String>();
        if (StringUtils.isBlank(this.password)) {
            errors.add("password 不能为空");
        }
        if (StringUtils.isBlank(this.userName) && StringUtils.isBlank(this.mobile)){
            errors.add("账号和手机号不能同时为空");
        }
        return errors;
    }
    @Override
    public String toString() {
        return "UserInfoReq{" +
                "id=" + id +
                ", idenNo='" + idenNo + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", qqCode='" + qqCode + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}
