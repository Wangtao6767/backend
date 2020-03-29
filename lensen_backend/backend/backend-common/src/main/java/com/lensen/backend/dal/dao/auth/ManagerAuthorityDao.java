package com.lensen.backend.dal.dao.auth;

import com.lensen.backend.dal.domain.auth.AdminAuthPagePO;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerAuthorityDao {
    AdminAuthPagePO queryAuthPages(ManagerDto param);
}
