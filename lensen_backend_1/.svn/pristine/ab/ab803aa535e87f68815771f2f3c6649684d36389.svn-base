package com.lensen.backend.enums;

import org.apache.commons.lang3.StringUtils;

public enum MonitorStatus {
    OPEN(1, "开启"),
    CLOSE(2, "关闭");

    private int code;
    private String name;

    MonitorStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getMonitorStatus(int code) {
        switch (code) {
            case 1:
                return OPEN.getName();
            case 2:
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
