package com.lensen.backend.service.rolerights.impl;

import com.lensen.backend.dal.dao.auth.ManagerAuthorityDao;
import com.lensen.backend.dal.dao.auth.PageDao;
import com.lensen.backend.dal.domain.auth.AdminAuthPagePO;
import com.lensen.backend.dal.domain.auth.AuthorityPageDto;
import com.lensen.backend.dal.domain.auth.PagePO;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.service.rolerights.RoleRightsAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleRightsAOImpl implements RoleRightsAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRightsAOImpl.class);

    @Autowired
    private ManagerAuthorityDao managerAuthorityDao;

    @Autowired
    private PageDao pageDao;

    /**
     * 查询权限列表
     *
     * @return
     * @throws Exception
     * @param managerId
     */
    public AdminAuthPagePO queryRoleRightsList(Long managerId) {
        try {
            ManagerDto param = new ManagerDto();
            param.setId(managerId);
            return managerAuthorityDao.queryAuthPages(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询管理员权限出错" + e.getMessage());
        }
        return null;
    }

    public List<PagePO> queryManagerPageDetail(String pageIds) {
        AuthorityPageDto dto = new AuthorityPageDto();
        dto.setPageId(pageIds);
        return pageDao.queryManagerPageDetail(dto);
    }

}
