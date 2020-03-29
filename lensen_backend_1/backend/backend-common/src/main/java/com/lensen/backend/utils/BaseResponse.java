package com.lensen.backend.utils;

import com.lensen.backend.vo.PageVO;

public class BaseResponse<T> {
    private Integer code;
    private String msg;
    private T response;
    private PageVO page;//分页

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageVO getPage() {
        return page;
    }

    public void setPage(PageVO page) {
        this.page = page;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
