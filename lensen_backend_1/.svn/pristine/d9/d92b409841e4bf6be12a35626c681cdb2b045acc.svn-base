package com.lensen.mobile.service.monitor.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.dao.monitor.MonitorDao;
import com.lensen.backend.dal.dao.monitor.MonitorTimeDao;
import com.lensen.backend.dal.domain.monitor.MonitorCountDto;
import com.lensen.backend.dal.domain.monitor.MonitorDto;
import com.lensen.backend.dal.domain.monitor.MonitorPO;
import com.lensen.backend.dal.domain.monitor.MonitorTimeDto;
import com.lensen.backend.enums.MonitorStatus;
import com.lensen.backend.utils.*;
import com.lensen.backend.vo.PageVO;
import com.lensen.mobile.service.monitor.MonitorAO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorAOImpl implements MonitorAO {

    @Resource
    private MonitorDao monitorDao;

    @Resource
    private MonitorTimeDao monitorTimeDao;


    @Override
    public BaseResponse<Object> selectMonitorList(MonitorDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
//        Page<ManagerPO> data = PageHelper.startPage(curPage, pageRows).doSelectPage(() -> managerDao.selectManagerList());
        int curPage = dto.getCurPage();
        int pageRows = dto.getPageRows();
        System.out.println(curPage+"----------------"+pageRows);
        PageHelper.startPage(curPage, pageRows);
        Page<MonitorPO> monitorPOS = monitorDao.selectMonitorAndTime();
        PageInfo<MonitorPO>  p = new PageInfo<>(monitorPOS);
        result.setResponse(p);
        PageVO pageVO = new PageVO();
        PageUtil.initPage(pageVO, curPage, pageRows, p.getTotal());
        result.setPage(pageVO);
        return result;
    }

    @Override
    public BaseResponse<Object> insertMonitor(MonitorDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        /*try {
            dto.setCreateId(ManagerContext.getLoginUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Status.NO_CACHE_USER.getCode());
            result.setMsg(Status.NO_CACHE_USER.getMessage());
            return result;
        }*/

        //设备id不能重复
        String deviceId = dto.getDeviceId();
        List list = new ArrayList();
        list = monitorDao.selectAllDeviceId();
        if(list.contains(deviceId)){
            result.setCode(Status.DEVICE_ID_IS_EXISTED.getCode());
            result.setMsg(Status.DEVICE_ID_IS_EXISTED.getMessage());
            return result;
        }

        Long loginUserId = dto.getLoginUserId();
        dto.setCreateId(loginUserId);
        dto.setStatus((byte) MonitorStatus.OPEN.getCode());
        int ret = monitorDao.insertMonitor(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.INSERT_SUCCESS);
            // 添加监测时间
            if (dto.getMonitorTimeList() != null) {
//                JSONArray jsonArray = JSONArray.parseArray(dto.getMonitorTime());
                JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(dto.getMonitorTimeList()));
                List<MonitorTimeDto> timeDtoList = new ArrayList<>();

                for (int i = NumberUtils.INTEGER_ZERO; i < jsonArray.size(); ++i) {
                    MonitorTimeDto timeDto = new MonitorTimeDto();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    timeDto.setMonitorId(dto.getId());
                    //把starttime和endtime合并为一个时间段，改为String类型
                    /*if (i % 2 == 0) {
                        timeDto.setStartTime((String) jsonObject.get("startTime"));
                    } else {
                        timeDto.setEndTime((String) jsonObject.get("endTime"));
                        timeDtoList.add(timeDto);
                        //新创建一个timeDto对象
                        timeDto = new MonitorTimeDto();
                    }*/
                    timeDto.setTimeSlot((String) jsonObject.get("timeSlot"));
                    timeDtoList.add(timeDto);
                }
                monitorTimeDao.insertBatchMonitorTime(timeDtoList);
            }
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    //TODO
    @Override
    public BaseResponse<Object> updateMonitor(MonitorDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());


        Long monitorId = dto.getId();
        if (monitorId == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }

        // 判断是否是修改状态
        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != MonitorStatus.OPEN.getCode()
                && status != MonitorStatus.CLOSE.getCode()) {
            result.setCode(Status.PARAM_WRONG.getCode());
            result.setMsg(Status.PARAM_WRONG.getMessage());
            return result;
        }

        /*try {
            dto.setModId(ManagerContext.getLoginUser().getId());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(Status.NO_CACHE_USER.getCode());
            result.setMsg(Status.NO_CACHE_USER.getMessage());
            return result;
        }*/
        Long loginUserId = dto.getLoginUserId();
        dto.setModId(loginUserId);



        int ret = monitorDao.updateMonitor(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.MODIFY_SUCCESS);
            // 更新监测时间区间
            if (dto.getMonitorTimeList() != null) {
                monitorTimeDao.deleteByMonitorId(monitorId);
                JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(dto.getMonitorTimeList()));
                List<MonitorTimeDto> timeDtoList = new ArrayList<>();
                for (int i = NumberUtils.INTEGER_ZERO; i < jsonArray.size(); ++i) {
                    MonitorTimeDto timeDto = new MonitorTimeDto();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    timeDto.setMonitorId(dto.getId());
                    /*if (i % 2 == 0) {
                        timeDto.setStartTime((String) jsonObject.get("startTime"));
                    } else {
                        timeDto.setEndTime((String) jsonObject.get("endTime"));
                        timeDtoList.add(timeDto);
                        //新创建一个timeDto对象
                        timeDto = new MonitorTimeDto();
                    }*/
                    timeDto.setTimeSlot((String) jsonObject.get("timeSlot"));
                    timeDtoList.add(timeDto);
                }
                monitorTimeDao.insertBatchMonitorTime(timeDtoList);
            }

        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    @Override
    public MonitorPO selectMonitorById(Long id) {
        return monitorDao.selectMonitorById(id);
    }

    @Override
    public boolean deleteMonitor(Long id) {
        int i = monitorDao.deleteMonitor(id);
        monitorTimeDao.deleteByMonitorId(id);
        return i>0;
    }

    @Override
    public BaseResponse<Object> findMornitorings() {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());


        int allMonitors = monitorDao.selectAllMonitors();
        int normalMonitors = monitorDao.selectNormalMonitors();
        int anormalMonitors = monitorDao.selectANormalMonitors();

        NumberFormat nf = NumberFormat.getPercentInstance();

        MonitorCountDto monitorCountDto = new MonitorCountDto();
        monitorCountDto.setAllMonitors(allMonitors);
        monitorCountDto.setNormalMonitors(normalMonitors);
        monitorCountDto.setAnormalMonitors(anormalMonitors);

        double rate = (normalMonitors*1.0)/allMonitors;
        nf.setMaximumFractionDigits(3);
        String format = nf.format(rate);
        monitorCountDto.setRate(format);
        result.setResponse(monitorCountDto);
        return result;
    }


}
