package com.lensen.mobile.service.manager.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.dao.manager.AdminLoginLogDao;
import com.lensen.backend.dal.dao.manager.ManagerDao;
import com.lensen.backend.dal.domain.manager.AdminLoginLogPO;
import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.dal.domain.manager.ManagerPO;
import com.lensen.backend.enums.DelFlagEnum;
import com.lensen.backend.enums.ManagerStatus;
import com.lensen.backend.security.Encrypt;
import com.lensen.backend.utils.*;
import com.lensen.backend.vo.PageVO;
import com.lensen.mobile.service.manager.ManagerAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;

@Service
public class ManagerAOImpl implements ManagerAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerAOImpl.class);

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private AdminLoginLogDao adminLoginLogDao;

    @Override
    public BaseResponse<Object> login(ManagerDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        String userName = dto.getUsername();
        String password = dto.getPassword();
        String _code = dto.getCode();
        String ip = ServletUtils.getRemortIp();

        ManagerPO po = new ManagerPO();
        po.setToken(dto.getToken());
        po.setUsername(userName);
        String code = managerDao.queryCode(po);
        // 判断验证码是否正确验证
        if (!_code.equals(code)) {
            LOGGER.info("验证码code:" + code + ",输入code" + _code);
            result.setCode(Status.ERROR_VERIFY_CODE.getCode());
            result.setMsg(Status.ERROR_VERIFY_CODE.getMessage());
            return result;
        }

        // 删除判断过得验证码
        managerDao.deleteCode(po.getToken());

        // 判断用户是否存在
        ManagerDto managerDto = new ManagerDto();
        managerDto.setUsername(userName);
        ManagerPO searchResult = managerDao.queryUser(managerDto);
        HashMap<String,String> map = new HashMap<>();
        map.put("loginUserId",searchResult.getId().toString());
        result.setResponse(map);
        if (searchResult == null) {
            result.setCode(Status.USER_NOT_EXISTED.getCode());
            result.setMsg(Status.USER_NOT_EXISTED.getMessage());
            return result;
        }

        ManagerDto param = new ManagerDto();
        param.setUsername(userName);
        // 将用户的密码加密之后作为条件查询
        try {
            password = Encrypt.encryptSES(CryptionUtil.md5((password)), IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("密码加密失败:{}", password);
        }
        param.setPassword(password);
        searchResult = managerDao.queryUser(param);
        if (searchResult == null) {
            result.setCode(Status.USER_NAME_OR_PWD_ERROR.getCode());
            result.setMsg(Status.USER_NAME_OR_PWD_ERROR.getMessage());
            return result;
        }

        //若del_flag为1（删除状态）且status为0（关闭状态）则登录失败
        int enable = searchResult.getDelFlag() != null ? searchResult.getDelFlag() : DelFlagEnum.NORMAL.getCode();
        int status = searchResult.getStatus() != null ? searchResult.getStatus() : ManagerStatus.CLOSE.getCode();
        // 判断是否禁用
        if (enable == DelFlagEnum.NORMAL.getCode() && status == ManagerStatus.CLOSE.getCode()
        ) {
            result.setCode(Status.USER_DISABLE.getCode());
            result.setMsg(Status.USER_DISABLE.getMessage());
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
        adminLoginLogPo.setLoginTime(new Date());
        adminLoginLogDao.createLoginLog(adminLoginLogPo);
        LOGGER.info("验证成功.");
        LoginManager manager = new LoginManager(searchResult);
        manager.setAccessIP(ip);
        manager.setLoginTime(new Date());

        // 设置session
        ManagerContext.saveLoginManager(manager);
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        return result;
    }

    @Override
    public int saveCode(ManagerPO po) {
        return managerDao.saveCode(po);
    }

    @Override
    public BaseResponse<Object> selectManagerList(int curPage, int pageRows) {

        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
//        Page<ManagerPO> data = PageHelper.startPage(curPage, pageRows).doSelectPage(() -> managerDao.selectManagerList());
        PageHelper.startPage(curPage, pageRows);
        Page<ManagerPO> managerPOS = managerDao.selectManagerList();
        PageInfo<ManagerPO> p = new PageInfo<>(managerPOS);
        result.setResponse(p);
        PageVO pageVO = new PageVO();
        PageUtil.initPage(pageVO, curPage, pageRows, p.getTotal());
        result.setPage(pageVO);
        return result;
    }

    /*@Override
    public BaseResponse<Object> updateManager(ManagerDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        String password = null;
        try {
            password = Encrypt.encryptSES(CryptionUtil.md5((dto.getPassword())), IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long id = dto.getId();
        dto.setPassword(password);
        if (id == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }
        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != ManagerStatus.OPEN.getCode()
                && status != ManagerStatus.CLOSE.getCode()) {
            result.setCode(Status.REQUEST_PARAM.getCode());
            result.setMsg(Status.REQUEST_PARAM.getMessage());
            return result;
        }
        int ret = managerDao.updateManager(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setCode(Status.LSucceed.getCode());
            result.setMsg(IConstants.MODIFY_SUCCESS);
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(IConstants.MODIFY_FAIL);
        }
        return result;
    }*/

    @Override
    public BaseResponse<Object> insertManager(ManagerDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        String password = null;
        try {
            password = Encrypt.encryptSES(CryptionUtil.md5((dto.getPassword())), IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dto.setPassword(password);


//        dto.setCreateId(ManagerContext.getLoginUser().getId());
//        dto.setCreateId(1L);
        //TODO
//        dto.setStatus((byte) ManagerStatus.OPEN.getCode());

        int ret = managerDao.insertManager(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.INSERT_SUCCESS);
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(IConstants.INSERT_FAIL);
        }
        return result;
    }


    @Override
    public BaseResponse<Object> deleteManager(Long id) {
        BaseResponse<Object> result = new BaseResponse<>();
        int flag = managerDao.deleteManager(id);
        if (flag > 0) {
            result.setCode(Status.LSucceed.getCode());
            result.setMsg(Status.LSucceed.getMessage());
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    @Override
    public BaseResponse<Object> resetPassword(Long id) {
        BaseResponse<Object> result = new BaseResponse<>();
        String password = null;
        try {
            password = Encrypt.encryptSES(CryptionUtil.md5(("123456")), IConstants.MD5_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int flag = managerDao.updatePassword(password, id);
        if (flag > 0) {
            result.setCode(Status.LSucceed.getCode());
            result.setMsg(Status.LSucceed.getMessage());
        }
        return result;
    }

    @Override
    public BaseResponse<Object> updateManagerStatus(ManagerDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        Long id = dto.getId();
        if (id == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }
/*        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != ManagerStatus.OPEN.getCode()
                && status != ManagerStatus.CLOSE.getCode()) {
            result.setCode(Status.REQUEST_PARAM.getCode());
            result.setMsg(Status.REQUEST_PARAM.getMessage());
            return result;
        }*/
        int ret = managerDao.updateManagerStatus(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.MODIFY_SUCCESS);
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(IConstants.MODIFY_FAIL);
        }
        return result;
    }

    @Override
    public BaseResponse<Object> changePassword(String newPassword, String confirmPassword, Long id) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        String prePassword = managerDao.queryPasswordById(id);
        if(!prePassword.equals(confirmPassword)){
            result.setCode(Status.Error_CONFIRM_PASSWORD.getCode());
            result.setMsg(Status.Error_CONFIRM_PASSWORD.getMessage());
            return result;
        }
        int i = managerDao.updatePassword(newPassword, id);
        if(i != 1){
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
            return result;
        }
        return result;
    }
}
