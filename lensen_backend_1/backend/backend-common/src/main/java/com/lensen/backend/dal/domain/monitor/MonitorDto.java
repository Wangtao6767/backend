package com.lensen.backend.dal.domain.monitor;

import com.lensen.backend.utils.PageEntity;

import java.util.ArrayList;

public class MonitorDto extends PageEntity {
    private Long id;

    /**
     * 监测点名称
     */
    private String name;

    /**
     * 当前登录用户的id
     */
    private Long loginUserId;

    /**
     * 区域
     */
    private String region;

    /**
     * 位置
     */
    private String address;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 状态：0、正常，1、关闭
     */
    private Byte status;

    private Long createId;

    /**
     * 监测时间区间
     */
    private String monitorTime;

    /**
     * 监测点时间区间
     */
    private ArrayList monitorTimeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public Long getCreateId() {
        return createId;
    }

    @Override
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }




    public ArrayList getMonitorTimeList() {
        return monitorTimeList;
    }

    public void setMonitorTimeList(ArrayList monitorTimeList) {
        this.monitorTimeList = monitorTimeList;
    }

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }
}
