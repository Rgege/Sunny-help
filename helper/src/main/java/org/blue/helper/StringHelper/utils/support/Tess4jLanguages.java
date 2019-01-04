package org.blue.helper.StringHelper.utils.support;

/**
 * @Description <P></P>
 * @Author v-Rui.Xiong@bl.com
 * @Date 2019/1/4
 * @Version 1.0.0
 **/
public enum Tess4jLanguages {
    CNSMP("chi_sim"),
    EN("eng"),
    ;
    String lan;

    Tess4jLanguages(String lan) {
        this.lan = lan;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }
}
