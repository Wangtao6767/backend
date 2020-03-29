package com.lensen.backend.dal.domain.auth;

public class AuthorityPageDto {
    /**
     * 权限页面关系表id
     */
    private Long id;

    /**
     * 权限id
     */
    private Long authorityId;

    /**
     * 页面id
     */
    private String pageId;

    /**
     * 类型：0、一级菜单, 1、二级菜单
     */
    private Integer pageType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }
}
