package com.lensen.backend.service.monitor;

import com.github.pagehelper.Page;
import com.lensen.backend.dal.domain.monitor.MonitorDto;
import com.lensen.backend.dal.domain.monitor.MonitorPO;
import com.lensen.backend.utils.Result;
import org.springframework.transaction.annotation.Transactional;

public interface MonitorAO {
    Page<MonitorPO> selectMonitorList(MonitorDto dto);

    @Transactional
    Result insertMonitor(MonitorDto dto);

    @Transactional
    Result updateMonitor(MonitorDto dto);

    MonitorPO selectMonitorById(Long id);
}
