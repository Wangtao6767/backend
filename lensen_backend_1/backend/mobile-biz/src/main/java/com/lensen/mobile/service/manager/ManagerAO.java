package com.lensen.mobile.service.manager;

import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.dal.domain.manager.ManagerPO;
import com.lensen.backend.utils.BaseResponse;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerAO {
    @Transactional
    BaseResponse<Object> login(ManagerDto dto);

    int saveCode(ManagerPO po);

    BaseResponse<Object> selectManagerList(int curPage,int pageRows);

//    BaseResponse<Object> updateManager(ManagerDto dto);

    BaseResponse<Object> insertManager(ManagerDto dto);

    BaseResponse<Object> deleteManager(Long id);

    BaseResponse<Object> resetPassword(Long id);

    BaseResponse<Object> updateManagerStatus(ManagerDto dto);

    BaseResponse<Object> changePassword(String newPassword, String confirmPassword, Long id);

    BaseResponse<Object> loginByUname(ManagerDto managerDto);

}
