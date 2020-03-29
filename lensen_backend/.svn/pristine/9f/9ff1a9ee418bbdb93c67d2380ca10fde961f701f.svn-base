package com.lensen.mobile.web;

import com.lensen.backend.dal.domain.manager.ManagerDto;
import com.lensen.backend.utils.*;
import com.lensen.mobile.service.manager.ManagerAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlowController.class);

    @Autowired
    private ManagerAO managerAO;

    /**
     * 获取用户列表
     */
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String list(@RequestParam int curPage,int pageRows) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = managerAO.selectManagerList(curPage,pageRows);
        LOGGER.info("manager/list cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }


/*    *//**
     * 修改用户
     *//*
    @PostMapping(value = "/modManager", produces = "application/json;charset=UTF-8")
    public String modManager(@RequestBody ManagerDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = managerAO.updateManager(dto);
        LOGGER.info("manager/modManager cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }*/


    /**
     * 新增用户
     */
    @PostMapping(value = "/addManager", produces = "application/json;charset=UTF-8")
    public String addManager(@RequestBody ManagerDto dto) {
        long start = System.currentTimeMillis();
        /*try {
            dto.setCreateId(ManagerContext.getLoginUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Status.NO_CACHE_USER.getCode());
            result.setMsg(Status.NO_CACHE_USER.getMessage());
            return CommonUtil.responseFromObject(result);
        }*/
        Long loginUserId = dto.getLoginUserId();
        dto.setModId(loginUserId);
//        dto.setCreateId(1L);

        BaseResponse<Object> result = managerAO.insertManager(dto);
        LOGGER.info("manager/addManager cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 删除用户
     */
    @PostMapping("/deleteManager")
    public String deleteManager(@RequestBody ManagerDto dto) {
        Long id = dto.getId();
        long start = System.currentTimeMillis();
        LOGGER.info("manager/delManager cost: {}ms", System.currentTimeMillis() - start);
        BaseResponse<Object> result = managerAO.deleteManager(id);
        return CommonUtil.responseFromObject(result);
    }


    /**
     * 重置密码
     */

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody ManagerDto dto){
        Long id = dto.getId();
        long start = System.currentTimeMillis();
        LOGGER.info("manager/resetPassword cost: {}ms", System.currentTimeMillis() - start);
        BaseResponse<Object> result = managerAO.resetPassword(id);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 编辑用户状态
     */
    @PostMapping(value = "/modManagerStatus", produces = "application/json;charset=UTF-8")
    public String modManagerStatus(@RequestBody ManagerDto dto){
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = managerAO.updateManagerStatus(dto);
        LOGGER.info("manager/modManager cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);

    }
}
