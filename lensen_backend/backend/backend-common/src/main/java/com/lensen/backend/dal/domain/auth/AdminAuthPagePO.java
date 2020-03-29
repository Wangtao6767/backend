package com.lensen.backend.dal.domain.auth;

import java.util.List;

public class AdminAuthPagePO {
    /**
     * 管理员权限表
     */
    private ManagerAuthorityPO maPO;

    /**
     * 权限页面表
     */
    private AuthorityPagePO apPO;

    /**
     * 页面表
     */
    private List<PagePO> pagePOs;

    public ManagerAuthorityPO getMaPO() {
        return maPO;
    }

    public void setMaPO(ManagerAuthorityPO maPO) {
        this.maPO = maPO;
    }

    public AuthorityPagePO getApPO() {
        return apPO;
    }

    public void setApPO(AuthorityPagePO apPO) {
        this.apPO = apPO;
    }

    public List<PagePO> getPagePOs() {
        return pagePOs;
    }

    public void setPagePOs(List<PagePO> pagePOs) {
        this.pagePOs = pagePOs;
    }


}
