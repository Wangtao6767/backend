package com.lensen.backend.service.manager;

import com.lensen.backend.utils.Result;
import org.springframework.transaction.annotation.Transactional;

public interface ManagerAO {
    @Transactional
    Result adminLogin(String username, String password, String code, String code1, String ip, String ipAddress) throws Exception;
}
