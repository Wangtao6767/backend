package com.lensen.backend.dal.domain.manager;

import com.lensen.backend.utils.PageEntity;

import java.util.Date;

public class ManagerDto extends PageEntity {

    private Long id;

    /**
     * 管理员用户名称
     */
    private String username;

    /**
     * 管理员登录密码
     */
    private String password;

    /**
     * 当前登录用户的id
     */
    private Long loginUserId;

    /**
     * 验证码
     */
    private String code;

    /**
     * 管理员最后登录时间
     */
    private Date loginTime;

    private String ipAddress;

    /**
     * 验证码token
     */
    private String token;

    /**
     * 状态：0、待处理，1、已完成，2、已作废
     */
    private Byte status;

    private String name;

    private String description;

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }
}
