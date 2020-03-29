package com.lensen.backend.dal.domain.auth;

public class ManagerAuthorityDto {
    /**
     * 管理员权限关系表id
     */
    private Long id;

    /**
     * 管理员id
     */
    private Long managerId;

    /**
     * 权限id
     */
    private Long authorityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}
