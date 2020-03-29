package com.lensen.backend.service.monitor.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.dao.monitor.MonitorDao;
import com.lensen.backend.dal.dao.monitor.MonitorTimeDao;
import com.lensen.backend.dal.domain.monitor.MonitorDto;
import com.lensen.backend.dal.domain.monitor.MonitorPO;
import com.lensen.backend.dal.domain.monitor.MonitorTimeDto;
import com.lensen.backend.enums.MonitorStatus;
import com.lensen.backend.service.monitor.MonitorAO;
import com.lensen.backend.utils.ManagerContext;
import com.lensen.backend.utils.Result;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorAOImpl implements MonitorAO {
    @Autowired
    private MonitorDao monitorDao;

    @Autowired
    private MonitorTimeDao monitorTimeDao;

    @Override
    public Page<MonitorPO> selectMonitorList(MonitorDto dto) {
        int curPage = dto.getCurPage();
        int pageRows = dto.getPageRows();
        return PageHelper.startPage(curPage, pageRows).doSelectPage(() -> monitorDao.selectMonitorList(dto));
    }

    @Override
    public Result insertMonitor(MonitorDto dto) {
        Result result = Result.getInitializedResult();
        dto.setCreateId(ManagerContext.getLoginUser().getId());
        dto.setStatus((byte) MonitorStatus.OPEN.getCode());
        int ret = monitorDao.insertMonitor(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.INSERT_SUCCESS);
            // 添加监测时间
            if (dto.getMonitorTime() != null) {
                JSONArray jsonArray = JSONArray.parseArray(dto.getMonitorTime());
                List<MonitorTimeDto> timeDtoList = new ArrayList<>();
                for (int i = NumberUtils.INTEGER_ZERO; i < jsonArray.size(); ++i) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MonitorTimeDto timeDto = new MonitorTimeDto();
                    timeDto.setMonitorId(dto.getId());
                    if (i % 2 != 0) {
                        timeDto.setStartTime((String) jsonObject.get("startTime"));
                    } else {
                        timeDto.setEndTime((String) jsonObject.get("endTime"));
                    }
                    timeDtoList.add(timeDto);
                }
                monitorTimeDao.insertBatchMonitorTime(timeDtoList);
            }
        } else {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.INSERT_FAIL);
        }
        return result;
    }

    @Override
    public Result updateMonitor(MonitorDto dto) {
        Result result = Result.getInitializedResult();
        Long monitorId = dto.getId();
        if (monitorId == null) {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.PARAM_NULL);
            return result;
        }

        // 判断是否是修改状态
        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != MonitorStatus.OPEN.getCode()
                && status != MonitorStatus.CLOSE.getCode()) {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.PARAM_ERROR);
            return result;
        }

        dto.setModId(ManagerContext.getLoginUser().getId());
        int ret = monitorDao.updateMonitor(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.MODIFY_SUCCESS);
            // 更新监测时间区间
            if (dto.getMonitorTime() != null) {
                monitorTimeDao.deleteByMonitorId(monitorId);
                JSONArray jsonArray = JSONArray.parseArray(dto.getMonitorTime());
                List<MonitorTimeDto> timeDtoList = new ArrayList<>();
                for (int i = NumberUtils.INTEGER_ZERO; i < jsonArray.size(); ++i) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    MonitorTimeDto timeDto = new MonitorTimeDto();
                    timeDto.setMonitorId(dto.getId());
                    timeDto.setStartTime((String) jsonObject.get("startTime"));
                    timeDto.setEndTime((String) jsonObject.get("endTime"));
                    timeDtoList.add(timeDto);
                }
                monitorTimeDao.insertBatchMonitorTime(timeDtoList);
            }
        } else {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.MODIFY_FAIL);
        }
        return result;
    }

    @Override
    public MonitorPO selectMonitorById(Long id) {
        return monitorDao.selectMonitorById(id);
    }
}
