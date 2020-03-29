package com.lensen.backend.service.manager.impl;

import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.dao.manager.AdminLoginLogDao;
import com.lensen.backend.dal.dao.manager.ManagerDao;
import com.lensen.backend.dal.domain.manager.AdminLoginLogPO;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.dal.domain.manager.ManagerPO;
import com.lensen.backend.security.Encrypt;
import com.lensen.backend.service.manager.ManagerAO;
import com.lensen.backend.utils.CryptionUtil;
import com.lensen.backend.utils.LoginManager;
import com.lensen.backend.utils.ManagerContext;
import com.lensen.backend.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ManagerAOImpl implements ManagerAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerAOImpl.class);

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private AdminLoginLogDao adminLoginLogDao;

    @Override
    public Result adminLogin(String username, String password, String code, String _code, String ip, String ipAddress) throws Exception {
        Result result = new Result();
        // 判断验证码是否正确验证
        if (code == null) {
            result.setIsSuccess(false);
            result.setMsg(IConstants.CODE_INVALID);
            return result;
        }
        if (!_code.equals(code)) {
            LOGGER.info("验证码code:" + code + ",输入code" + _code);
            result.setIsSuccess(false);
            result.setMsg(IConstants.CODE_ERROR);
            return result;
        }
        // 用户名密码为空的话，直接返回
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            result.setIsSuccess(false);
            result.setMsg(IConstants.USER_NAME_OR_PWD_NULL);
            return result;
        }
        // 判断用户是否存在
        ManagerDto managerDto = new ManagerDto();
        managerDto.setUsername(username);
        ManagerPO searchResult = managerDao.queryUser(managerDto);
        if (searchResult == null) {
            result.setIsSuccess(false);
            result.setMsg(IConstants.USER_NOT_EXIST);
            return result;
        }


        ManagerDto param = new ManagerDto();
        param.setUsername(username);
        // 将用户的密码加密之后座位条件查询
        password = Encrypt.encryptSES(CryptionUtil.md5((password)), IConstants.MD5_KEY);
        param.setPassword(password);
        searchResult = managerDao.queryUser(param);
        if (searchResult == null) {
            result.setIsSuccess(false);
            result.setMsg(IConstants.USER_NAME_OR_PWD_ERROR);
            return result;
        }

        int enable = searchResult.getDelFlag() != null ? searchResult.getDelFlag() : NumberUtils.INTEGER_ONE;
        // 判断是否禁用
        if (enable == NumberUtils.INTEGER_ONE) {
            result.setIsSuccess(false);
            result.setMsg(IConstants.USER_DISABLE);
            return result;
        }

        // 修改最后一次的登录时间
        if (StringUtils.isNotBlank(ip)) {
            LOGGER.info("更新最后登录时间、IP...");
            ManagerPO updateLoginPO = new ManagerPO();
            updateLoginPO.setId(searchResult.getId());
            managerDao.updateAdminLoginTime(updateLoginPO);
        }
        // 登录日志
        LOGGER.info("记录登录日志...");
        AdminLoginLogPO adminLoginLogPo = new AdminLoginLogPO();
        adminLoginLogPo.setAdminId(searchResult.getId());
        adminLoginLogPo.setIp(ip);
        adminLoginLogPo.setIpAddress(ipAddress);
        adminLoginLogPo.setLoginTime(new Date());
        adminLoginLogDao.createLoginLog(adminLoginLogPo);
        LOGGER.info("验证成功.");
        LoginManager manager = new LoginManager(searchResult);
        manager.setAccessIP(ip);
        manager.setLoginTime(new Date());

        // 进行登录认证
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        // 设置session
        ManagerContext.saveLoginManager(manager);

        result.setIsSuccess(true);
        result.setMsg(IConstants.USER_LOGIN_SUCCESS);
        return result;
    }
}
