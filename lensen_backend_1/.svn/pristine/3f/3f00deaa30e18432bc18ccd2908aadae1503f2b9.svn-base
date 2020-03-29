package com.lensen.backend.utils;

import com.lensen.backend.dal.domain.manager.ManagerPO;

public class LoginManager extends ManagerPO {
    /**
     * 访问IP
     */
    private String accessIP;

    /**
     * 来源类型：:1、iOS客户端，2、android客户端，3、H5
     */
    private Integer accessType;

    public LoginManager(ManagerPO manager) {
        if (manager != null) {
            this.setId(manager.getId());
            this.setUsername(manager.getUsername());
            this.setLoginTime(manager.getLoginTime());
        }
    }

    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }
}
