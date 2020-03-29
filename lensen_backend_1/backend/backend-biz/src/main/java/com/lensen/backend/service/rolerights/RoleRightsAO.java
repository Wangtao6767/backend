package com.lensen.backend.service.rolerights;

import com.lensen.backend.dal.domain.auth.AdminAuthPagePO;
import com.lensen.backend.dal.domain.auth.PagePO;

import java.util.List;

public interface RoleRightsAO {
    AdminAuthPagePO queryRoleRightsList(Long managerId);

    List<PagePO> queryManagerPageDetail(String pageIds);
}
