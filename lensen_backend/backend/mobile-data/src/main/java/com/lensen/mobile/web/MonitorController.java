package com.lensen.mobile.web;

import com.lensen.backend.dal.domain.monitor.MonitorDto;
import com.lensen.backend.dal.domain.monitor.MonitorPO;
import com.lensen.backend.utils.BaseResponse;
import com.lensen.backend.utils.CommonUtil;
import com.lensen.backend.utils.Status;
import com.lensen.mobile.service.monitor.MonitorAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlowController.class);
    /**
     * 获取监测点列表
     *
     * @param dto
     * @return
     */

    @Autowired
    private MonitorAO monitorAO;


    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String list(@RequestBody MonitorDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = monitorAO.selectMonitorList(dto);
        LOGGER.info("monitor/list cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 添加站点
     * @param dto
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/addMonitor", produces = "application/json;charset=UTF-8")
    public String addMonitor(@RequestBody MonitorDto dto, HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = monitorAO.insertMonitor(dto);
        LOGGER.info("monitor/addMonitor cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 编辑站点
     */
    @PostMapping("/modMonitor")
    public String modMonitor(@RequestBody MonitorDto dto, HttpServletResponse response) throws IOException {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = monitorAO.updateMonitor(dto);
        LOGGER.info("monitor/modMonitor cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 获取监测点信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/getMonitor")
    public String getMonitor(@RequestBody MonitorDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        long start = System.currentTimeMillis();
        MonitorPO po = monitorAO.selectMonitorById(dto.getId());
        if(null == po){
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        result.setResponse(po);
        LOGGER.info("monitor/getMonitor cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 删除检测点
     * @param dto
     * @return
     */
    @PostMapping("/deleteMonitor")
    public String deleteMonitor(@RequestBody MonitorDto dto) {
        Long id = dto.getId();
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        long start = System.currentTimeMillis();
        if(StringUtils.isEmpty(id)){
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return CommonUtil.responseFromObject(result);
        }
        boolean flag = monitorAO.deleteMonitor(id);
        if(!flag){
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
            return CommonUtil.responseFromObject(result);
        }
        LOGGER.info("monitor/getMonitor cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }




}
