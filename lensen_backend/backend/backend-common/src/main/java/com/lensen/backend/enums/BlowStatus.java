package com.lensen.backend.enums;

import org.apache.commons.lang3.StringUtils;

public enum BlowStatus {
    PROCESSING(0, "待处理"),
    FINISHED(1, "已完成"),
    INVALID(2, "已作废");

    private int code;
    private String name;

    BlowStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getBlowStatus(int code) {
        switch (code) {
            case 0:
                return PROCESSING.getName();
            case 1:
                return FINISHED.getName();
            case 2:
                return INVALID.getName();
            default:
                return StringUtils.EMPTY;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
