package com.lensen.mobile.web;

import com.lensen.backend.dal.domain.blow.WeekDay;
import com.lensen.backend.utils.BaseResponse;
import com.lensen.backend.utils.CommonUtil;
import com.lensen.mobile.service.blow.BlowAO;
import com.lensen.mobile.service.monitor.MonitorAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/blow")
public class StatisticsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsController.class);


    @Autowired
    private BlowAO blowAO;

    @Autowired
    private MonitorAO monitorAO;

    /**
     * 按日期统计当月的数据量（H5）
     */
    @GetMapping("/find_month_records")
    public String findMonthRecords() {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.findMonthRecords();
        LOGGER.info("blow/find_month_records cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     *根据星期获取数据(H5)
     * @return
     */
    @GetMapping("/find_week_counts")
    public BaseResponse<Object> findWeekCounts() {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.findWeekCounts();
        LOGGER.info("blow/find_week_counts cost: {}ms", System.currentTimeMillis() - start);
        return result;
    }

    /**
     * 按小时统计当天的数据量（H5）
     */
    @GetMapping("/find_hours")
    public String findHours() {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.findHours();
        LOGGER.info("blow/find_hours cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 摄像头设备&正常数&离线数&在线率（H5）
     */
    @GetMapping("/find_mornitorings")
    public String findMornitorings() {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = monitorAO.findMornitorings();
        LOGGER.info("blow/find_mornitorings cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }


    /**
     * 今日新增&确认有效&有效率（H5）
     */
    @GetMapping("/find_new_records")
    public String findNewRecords(@RequestParam("time") String time) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.findNewRecords(time);
        LOGGER.info("blow/find_new_records cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }


    /**
     * 今日新增数（PC）
     */
    @GetMapping("/find_day_count")
    public String findDayCount(@RequestParam("time") String time) {
        long start = System.currentTimeMillis();
        int result = blowAO.findDayCount(time);
        LOGGER.info("blow/find_day_count cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 本月新增数（PC）
     */
    @GetMapping("/find_month_count")
    public String findMonthCount(@RequestParam("time") String time) {
        long start = System.currentTimeMillis();
        int result = blowAO.findMonthCount(time);
        LOGGER.info("blow/find_month_count cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 获取一周的数据（PC）
     * @return
     */
    @GetMapping("/find_week_count")
    public String findWeekCount(){
        long start = System.currentTimeMillis();
        int result = blowAO.findWeekCount();
        LOGGER.info("blow/find_week_count cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     *获取近7天的数据（PC）
     * @return
     */
    @GetMapping("/find_all_data")
    public List<WeekDay> findDayData() {
        long start = System.currentTimeMillis();
        List<WeekDay> allCount = blowAO.findAllCount();
        LOGGER.info("blow/find_all_data cost: {}ms", System.currentTimeMillis() - start);
        return allCount;
    }
}



