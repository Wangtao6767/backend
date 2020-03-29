package com.lensen.backend.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于存放结果
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 3619080130735289328L;

    public static final String TYPE_CONTENT = "content";
    public static final String TYPE_EX_INFO = "exInfo";
    public static final String TYPE_ERR_INFO = "errInfo";
    public static final String TYPE_INFO = "info";
    public static final String TYPE_IS_SUCCESS = "isSuccess";

    public static final String TYPE_PAGESIZE = "pageSize";
    public static final String TYPE_CURPAGE = "curPage";
    public static final String TYPE_TOTALNUM = "totalNum";
    public static final String TYPE_TOTALPAGE = "totalPage";
    public static final String TYPE_PAGE = "page";
    public static final String TYPE_QUERY = "query";
    public static final String TYPE_ERROR_CODE = "errCode";

    private Boolean isSuccess; //

    private String msg; //

    private String exceptionMsg; // 专门用于打印异常信息

    private Map<String, Object> modules = new HashMap<>();

    public static Result getInitializedResult() {
        Result result = new Result();
        result.isSuccess = Boolean.TRUE;
        return result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getModules() {
        return modules;
    }

    public void setModules(Map<String, Object> modules) {
        this.modules = modules;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    @SuppressWarnings("unchecked")
    public <T> T getErrorCode() {
        return (T) this.getModules().get(TYPE_ERROR_CODE);
    }

    public <T> void setErrorCode(T data) {
        this.getModules().put(TYPE_ERROR_CODE, data);
    }

    @SuppressWarnings("unchecked")
    public <T> T getData() {
        return (T) this.getModules().get(TYPE_CONTENT);
    }

    public <T> void setData(T data) {
        this.getModules().put(TYPE_CONTENT, data);
    }

    @SuppressWarnings("unchecked")
    public <Q> Q getQuery() {
        return (Q) this.getModules().get(TYPE_QUERY);
    }

    public <Q> void setQuery(Q query) {
        this.getModules().put(TYPE_QUERY, query);
    }

}
