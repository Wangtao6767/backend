package com.lensen.mobile.service.monitor;

import com.lensen.backend.dal.domain.monitor.MonitorDto;
import com.lensen.backend.dal.domain.monitor.MonitorPO;
import com.lensen.backend.utils.BaseResponse;

public interface MonitorAO {
    BaseResponse<Object> selectMonitorList(MonitorDto dto);

    BaseResponse<Object> insertMonitor(MonitorDto dto);

    BaseResponse<Object> updateMonitor(MonitorDto dto);

    MonitorPO selectMonitorById(Long id);

    boolean deleteMonitor(Long id);
}
