package org.blue.helper.StringHelper.common.constants;

public class RegExConstant {
    /**
     * 匹配汉字
     */
    public static final String CHINESE="[\\u4e00-\\u9fa5]";

    /**
     * 匹配网址URL
     */
    public static final String INTERURL="[a-zA-z]+://[^\\s]*";

    /**
     * 匹配空白行
     */
    public static final String SPAN="\\n\\s*\\r";

    /**
     * 匹配Email
     */
    public static final String EMAIL="[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";

    /**
     * 匹配手机号码
     */
    public static final String MOBILE="^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\\d{8}$";

    /**
     * 匹配国内电话号码("XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX)
     */
    public static final String PHONE="^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$ ";

    /**
     * 匹配QQ
     */
    public static final String QQ="[1-9][0-9]{4,}";

    /**
     * 匹配邮编
     */
    public static final String POSTCODE="[1-9]\\d{5}(?!\\d)";

    /**
     * 匹配身份证
     */
    public static final String IDCARD="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";

    /**
     * 匹配正整数
     */
    public static final String POSITIVEINTEGER= "^[1-9]\\d*$";

    /**
     * 匹配负整数
     */
    public static final String NEGATIVEINTEGER= "^-[1-9]\\d*$";

    /**
     * 匹配非负整数
     */
    public static final String DISNEGATIVEINTEGER="^[1-9]\\d*|0$";

    /**
     * 至少n位的数字
     */
    public static final String ATLEASTNUMS="^\\d{n,}$";

    /**
     * m到n位的数字
     */
    public static final String MTONNUMS="^\\d{m,n}$";

    /**
     *首尾空白字符的正则表达式 可以用来删除行首行尾的空白字符,包括空格、制表符、换页符等等
     */
    public static final String HTSPAN="^\\s*|\\s*$";

    /**
     * IP地址
     */
    public static final String IPADDRESS="\\d+\\.\\d+\\.\\d+\\.\\d+";
}
