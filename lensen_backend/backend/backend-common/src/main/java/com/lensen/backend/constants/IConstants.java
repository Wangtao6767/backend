package com.lensen.backend.constants;

public class IConstants {
    public static final String MD5_KEY = "cvz12bbn231hj13d";

    public static final String SUCCESS = "请求成功";

    public static final String FAIL = "请求失败";

    public static final String INSERT_SUCCESS = "添加成功";

    public static final String INSERT_FAIL = "添加失败";

    public static final String MODIFY_SUCCESS = "修改成功";

    public static final String MODIFY_FAIL = "修改失败";

    public static final String AUTH_FAIL = "授权失败";

    public static final String SESSION_ADMIN = "admin";

    public static final String SESSION_CHECK_CODE = "adminLogin_checkCode";

    public static final String NOT_INTERCEPT_PATH = ".*/((login)|(manager/isLogin)|(verificationCode)|(images)|(public)|(style)|(scripts)).*";

    public static final String PAGE_OBJECT = "data";

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 总页数
     */
    public static final String PAGES = "pages";

    /**
     * 当前页
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 总条数
     */
    public static final String TOTAL = "total";

    public static final String CODE_INVALID = "验证码已失效";

    public static final String CODE_ERROR = "验证码错误";

    public static final String USER_NAME_OR_PWD_NULL = "用户名或者密码为空";

    public static final String USER_NAME_OR_PWD_ERROR = "用户名或者密码错误";

    public static final String USER_NOT_EXIST = "用户不存在";

    public static final String USER_DISABLE = "用户已经被禁用，请联系管理员";

    public static final String USER_LOGIN_SUCCESS = "用户登录成功";

    public static final String PARAM_ERROR = "请求参数错误";

    public static final String PARAM_NULL = "请求参数为空";

}
