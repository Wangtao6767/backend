package com.lensen.mobile.web;

import com.lensen.backend.dal.dao.blow.BlowDao;
import com.lensen.backend.dal.domain.blow.WeekDay;
import com.lensen.backend.utils.CommonUtil;
import com.lensen.mobile.service.blow.BlowAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blow")
public class StatisticsController {


    @Autowired
    private BlowAO blowAO;

    @GetMapping("/find_day_count")
    public String findDayCount(@RequestParam("time") String time) {
        int result = blowAO.findDayCount(time);
        return CommonUtil.responseFromObject(result);
    }

    @GetMapping("/find_month_count")
    public String findMonthCount(@RequestParam("time") String time) {
        int result = blowAO.findMonthCount(time);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 获取一周的数据
     * @return
     */
    @GetMapping("/find_week_count")
    public String findWeekCount(){
        int result = blowAO.findWeekCount();
        return CommonUtil.responseFromObject(result);
    }


    /**
     *获取近7天的数据
     * @return
     */
//    @GetMapping("/find_all_data")
//    public String findDayData() {
//        BaseResponse<Object> result = blowAO.findAllCount();
//        return CommonUtil.responseFromObject(result);
//    }

    @GetMapping("/find_all_data")
    public List<WeekDay> findDayData() {
        List<WeekDay> allCount = blowAO.findAllCount();
        return allCount;
    }
}



