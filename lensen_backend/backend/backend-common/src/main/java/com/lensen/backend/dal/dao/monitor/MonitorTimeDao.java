package com.lensen.backend.dal.dao.monitor;

import com.lensen.backend.dal.domain.monitor.MonitorTimeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorTimeDao {
    void insertBatchMonitorTime(List<MonitorTimeDto> timeDtoList);

    void deleteByMonitorId(Long monitorId);

}
