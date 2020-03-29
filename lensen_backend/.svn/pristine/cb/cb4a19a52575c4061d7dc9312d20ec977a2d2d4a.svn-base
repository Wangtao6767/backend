package com.lensen.backend.enums;

import org.apache.commons.lang3.StringUtils;

public enum ManagerStatus {
    //    YES(1,"是"),
//    NO(0,"否"),
    OPEN(1, "开启"),
    CLOSE(0, "关闭");
    private int code;
    private String name;

    ManagerStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getManagerStatus(int code) {
        switch (code) {
            case 0:
                return OPEN.getName();
            case 1:
                return CLOSE.getName();
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
