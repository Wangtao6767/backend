package com.lensen.backend.dal.dao.manager;

import com.github.pagehelper.Page;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.dal.domain.manager.ManagerPO;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao {
    ManagerPO queryUser(ManagerDto managerDto);

    void updateAdminLoginTime(ManagerPO updateLoginPO);

    String queryCode(ManagerPO po);

    int saveCode(ManagerPO po);

    void deleteCode(String token);

    Page<ManagerPO> selectManagerList();

    int updateManager(ManagerDto dto);

    int insertManager(ManagerDto dto);

    int deleteManager(Long id);

    int updatePassword(String password, Long id);

    int updateManagerStatus(ManagerDto dto);

    String queryPasswordById(Long id);
}
