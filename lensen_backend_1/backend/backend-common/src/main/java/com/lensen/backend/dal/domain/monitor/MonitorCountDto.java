package com.lensen.backend.dal.domain.monitor;

public class MonitorCountDto {
    /**
     * 摄像头设备
     */
    private int allMonitors;

    /**
     *正常设备数
     */
    private int normalMonitors;

    /**
     * 离线设备数
     */
    private int anormalMonitors;

    /**
     * 在线率
     */
    private String rate;


    public int getAllMonitors() {
        return allMonitors;
    }

    public void setAllMonitors(int allMonitors) {
        this.allMonitors = allMonitors;
    }

    public int getNormalMonitors() {
        return normalMonitors;
    }

    public void setNormalMonitors(int normalMonitors) {
        this.normalMonitors = normalMonitors;
    }

    public int getAnormalMonitors() {
        return anormalMonitors;
    }

    public void setAnormalMonitors(int anormalMonitors) {
        this.anormalMonitors = anormalMonitors;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
