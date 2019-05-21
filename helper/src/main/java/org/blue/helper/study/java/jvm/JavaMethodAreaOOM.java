package org.blue.helper.study.java.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                                     public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                                         return proxy.invokeSuper(obj, args);
                                     }
                                 }
            );
//          enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> proxy.invokeSuper(obj, args1));
            enhancer.create();
        }
    }

    static class OOMObject {
        protected String cellPhone;

        protected String mobile;

        protected String sms_code;

        protected String smsCode;

        /**
         * 发短信内容
         */
        protected String content;

        protected String templateCode;


        protected String dynamicContents;


        /**
         * 邀请码
         */
        protected String invitationCode;

        protected Integer number;

        /**
         * 登陆渠道 04 社区
         */
        protected String channelId;

        protected String orgId;
        protected String shopId;

        private String adChannel;

        private String adId;

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSms_code() {
            return sms_code;
        }

        public void setSms_code(String sms_code) {
            this.sms_code = sms_code;
        }

        public String getSmsCode() {
            return smsCode;
        }

        public void setSmsCode(String smsCode) {
            this.smsCode = smsCode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTemplateCode() {
            return templateCode;
        }

        public void setTemplateCode(String templateCode) {
            this.templateCode = templateCode;
        }

        public String getDynamicContents() {
            return dynamicContents;
        }

        public void setDynamicContents(String dynamicContents) {
            this.dynamicContents = dynamicContents;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getAdChannel() {
            return adChannel;
        }

        public void setAdChannel(String adChannel) {
            this.adChannel = adChannel;
        }

        public String getAdId() {
            return adId;
        }

        public void setAdId(String adId) {
            this.adId = adId;
        }

        @Override
        public String toString() {
            return "OOMObject{" +
                    "cellPhone='" + cellPhone + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", sms_code='" + sms_code + '\'' +
                    ", smsCode='" + smsCode + '\'' +
                    ", content='" + content + '\'' +
                    ", templateCode='" + templateCode + '\'' +
                    ", dynamicContents='" + dynamicContents + '\'' +
                    ", invitationCode='" + invitationCode + '\'' +
                    ", number=" + number +
                    ", channelId='" + channelId + '\'' +
                    ", orgId='" + orgId + '\'' +
                    ", shopId='" + shopId + '\'' +
                    ", adChannel='" + adChannel + '\'' +
                    ", adId='" + adId + '\'' +
                    '}';
        }
    }
}
