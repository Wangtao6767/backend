package com.lensen.backend.enums;

import org.apache.commons.lang3.StringUtils;

public enum DelFlagEnum {
    DELETE(0, "正常状态"),
    NORMAL(1, "已删除");

    private int code;
    private String name;

    DelFlagEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getDelFlag(int code) {
        switch (code) {
            case 0:
                return DELETE.getName();
            case 1:
                return NORMAL.getName();
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
