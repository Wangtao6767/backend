package com.lensen.backend.web;

import com.github.pagehelper.Page;
import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.domain.monitor.MonitorDto;
import com.lensen.backend.dal.domain.monitor.MonitorPO;
import com.lensen.backend.enums.MonitorStatus;
import com.lensen.backend.service.monitor.MonitorAO;
import com.lensen.backend.utils.ResponseUtils;
import com.lensen.backend.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    @Autowired
    private MonitorAO monitorAO;

    /**
     * 获取监测点列表
     *
     * @param dto
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(MonitorDto dto) {
        Map<String, Object> params = new HashMap<>();
        Page<MonitorPO> data = monitorAO.selectMonitorList(dto);
        params.put(IConstants.PAGE_OBJECT, data);
        params.put(IConstants.PAGES, data.getPages());
        params.put(IConstants.PAGE_NUM, data.getPageNum());
        params.put(IConstants.TOTAL, data.getTotal());
        params.put("monitorStatus", MonitorStatus.values());
        return new ModelAndView("monitor/monitor_list", params);
    }

    /**
     * 添加站点
     *
     * @param dto
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/addMonitor")
    public ModelAndView addMonitor(MonitorDto dto, HttpServletResponse response) throws IOException {

        Result result = monitorAO.insertMonitor(dto);
        ResponseUtils.toJson(result, response);
        return null;
    }

    /**
     * 编辑站点
     */
    @PostMapping("/modMonitor")
    public ModelAndView modMonitor(MonitorDto dto, HttpServletResponse response) throws IOException {
        Result result = monitorAO.updateMonitor(dto);
        ResponseUtils.toJson(result, response);
        return null;
    }

    /**
     * 获取监测点信息
     *
     * @param dto
     * @return
     */
    @RequestMapping("/getMonitor")
    public ModelAndView getMonitor(MonitorDto dto) {
        Map<String, Object> params = new HashMap<>();
        MonitorPO po = monitorAO.selectMonitorById(dto.getId());
        params.put("monitorPO", po);
        return new ModelAndView("monitor/monitor_modify_body", params);
    }
}
