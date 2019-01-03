package org.blue.helper.StringHelper.common.enums;

public enum SysCode {

    SUCCESS("100000","Sucess"),
    ERROR("000010","System busy"),

    NEED_LOGIN("000011","请先登录"),
    LOGIN_CHECK_ERROR("000012","登录校验失败"),

    NULL_RESULT("000013","查询结果为空"),

    /**
     * 错误编码规范： 长度：8位 00 0 00 000 含义： 12位：公共系统错误编码，00 3位：消息等级，默认为1 45位：模块，01
     * 678位：具体错误 系统异常：00100001-00100099
     */
    MEMBER_BASEINFO_ERROR("05111001", "验证签名失败!"),
    OUT_LOGIN_ERROR("05111002", "用户状态没有登陆"),
    VALIDATE_PARAM_ERROR("05111003", "输入参数有误！"),
    REMOTE_INVOKE_ERROR("05111004", "远程服务错误！"),
    APPLICATION_ERROR("05111005", "业务逻辑异常"),
    INVALID_SMSCODE_ERROR("05111006", "校验码输入不正确！"),
    INVALID_UPDATE_ERROR("05111007", "更新用户信息失败！"),
    LOGIN_REPEAT_ERROR("05111008", "输入账号已存在！"),
    UPDATE_PASSWORD_FAIL("05111009", "修改密码失败！"),
    SEND_SMSCODE_FAIL("05111010", "发送短信失败！"),
    USER_LOCKED("05111012", "密码输入错误账号锁定！"),
    USER_TIMES_LOCKED("05111037", "密码输入错误三次以上"),
    INVALID_LOGINPWD_ERROR("05111011", "用户名或者密码错误!"),
    LOGIN_FAIL("05111013", "登陆失败!"),
    REGISTER_FAIL("05111014", "此账号已存在!"),
    BIND_FAIL("05111015", "绑定失败!"),
    QUERY_BIND_RELATION_FAIL("05111098", "没有绑定关系!"),
    SEND_EMAIL_FAIL("05111016", "发送邮件失败!"),
    ACCOUNT_NOREGISTER("05111017", "账号未注册!"),
    NO_WEBLEGACY_DATA("05111021", "此账号不存在!"),
    INVALID_OLD_MOBILE("05111022", "原手机号码不正确!"),
    INVALID_LOGINID("05111024", "此账号不存在!"),
    INVALID_SMS_CODE("05111026", "验证码输入不正确!"),
    INVALID_SMS_TEMPLATE("05111027", "短信模板为空!"),
    CARD_BIND_FAIL("05111026", "绑卡失败,请重新输入正确卡号!"),
    INVALID_INVITATION_CODE("05111027", "邀请码输入不正确！"),
    INVALID_CREATE_CODE("05111028", "创建邀请码失败！"),
    SHIQU_LOGIN_FAIL("05111029", "社区注册的账号只能在社区登陆！"),
    EMPTY_INVITATION_CODE("05111030", "请输入邀请码！"),
    Duplicate_data("05111031", "该号码已存在！"),
    Duplicate_Product_favorites("05111032", "此商品已关注！"),
    Duplicate_shop_favorites("05111033", "此门店已关注！"),
    REPEAT_RETURN_GOODS("05111999", "该退货单已经处理！"),
    change_pwd_error("05111034", "密码已修改，请重新登录！"),
    MEMBER_STATUS_FREEZE("05111035", "您的账号已被冻结！"),
    MEMBER_STATUS_CANCEL("05111036", "您的账号已被注销！"),
    MOBILE_MSG_CNT_MAX("05111037","当前手机号已超过发送短信上限"),
    INVALID_THIRD_OPENID("05111038", "该会员账号已与其他用户绑定!"),
    MEMBER_STATUS_BLACKLIST("05111039", "您的账户异常，请联系客服(400-900-8800)!"),
    MEMBER_NOT_VALID("05111040", "您的会员凭证验证失败"),
    OLD_MEMBER_NOT_VALID("05111041", "该老卡不存在或已被转换"),
    REGISTER_PARAM_NOT_NUMERIC_ERROR("05111042", "用户名不能是纯数字！"),
    REGISTER_PARAM_NOT_EMAIL_ERROR("05111043", "用户名不能包含@符！"),
    LOGIN_GET_TOKEN_ERROE("05111044", "生产token错误！"),
    SEND_FLOW_FAIL("05111047", "流量发放失败！"),
    NOT_HAVING_QUAL("05111048", "没有发放资格！"),
    PLATE_HAS_NOT_PAID("06000001", "该车牌还有未支付的订单，不能解绑"),
    UNICOM_MOBILE_VERIFY_FAILED("05111051", "号码验证失败！"),
    UNICOM_VERIFY_STATUS_NOT_USE("05111052", "不能进行认证！"),
    UNICOM_PARSE_TOKEN_FAILED("05111053", "解析token失败！"),
    UNICOM_MOBILE_NOT_USE("05111054", "手机号码不合适！"),
    UNICOM_MOBILE_VERIFIED("05111055", "用户已认证！"),
    RIGHT_LEVEL_VERIFY("05111058", "会员实名等级验证出错！"),
    NAME_EQUAL_VERIFY("05111059", "已存在相同的实名信息"),
    UPDATE_ACCOUNT_VERIFY("05111060", "暂不支持此功能！"),
    ADD_ACTIVE_FAIL("05111061", "参加活动失败！"),
    MOBILE_DECODE_FAIL("05111066", "解密失败！"),
    ACCOUNT_CAN_NOT_UNBIND("05111068", "账号解绑失败"),
    UNICOM_MOBILE_NOT_CORRECT("05111096", "手机号码不正确,请输入符合要求的手机号！"),
    REGISTER_FAIL_FREEZE("05111078", "此账号已存在，账号为冻结状态!"),
    REGISTER_FAIL2("05111079", "账号注册失败!"),
    INVALID_SYSID_BUSINESS("05111080", "无效的业务类型请求"),
    MEMEMBER_MESSAGE_LIMIT("05111087","你的操作过于频繁，请稍后再试"),
    RECORD_RESUBMIT("05111089","请勿重复提交")
    ;
    String code;
    String  msg;

    SysCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
