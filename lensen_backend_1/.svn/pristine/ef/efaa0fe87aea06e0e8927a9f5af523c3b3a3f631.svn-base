package com.lensen.backend.utils;

public enum Status {
    //公有状态码信息
    LSucceed(1, "请求成功"),
    LFailed(0, "请求失败"),
    ERROR(-1, "服务器异常"),
    NO_AUTHORITY(-2, "没有此权限"),
    PARAM_WRONG(-3, "参数错误"),
    NULL_PARAM(-4, "参数为空"),
    NOT_LOCATE(-5, "用户未定位 "),
    NETWORK_ERROR(-6, "网络连接失败"),
    DATA_NULL(-9, "数据为空"),
    NO_CACHE_USER(-10, "登录状态已失效，请重新登录"),
    NO_VERSION_SUPPORT(-11, "您当前的版本不支持此功能，请更新版本"),
    // 签名认证失败
    CHECK_SIGN(1001, "签名认证失败"),
    REQUEST_PARAM(1002, "请求业务参数不正确"),
    ERROR_VERIFY_CODE(1003, "验证码错误"),
    Error_CONFIRM_PASSWORD(1004,"原密码错误"),
    // 文件上传
    FILE_NULL(200001, "文件不能为空"),

    // 登录
    USER_NOT_EXISTED(300001, "用户不存在"),
    USER_NAME_OR_PWD_ERROR(300002, "用户名或者密码错误"),
    USER_DISABLE(300003, "用户已经被禁用，请联系管理员"),
    //监测点
    DEVICE_ID_IS_EXISTED(400001,"该设备号已经存在");
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
