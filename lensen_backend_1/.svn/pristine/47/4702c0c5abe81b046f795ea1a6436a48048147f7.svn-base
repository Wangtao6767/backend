package com.lensen.mobile.service.blow;

import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.BlowImgDto;
import com.lensen.backend.dal.domain.blow.BlowResult;
import com.lensen.backend.dal.domain.blow.WeekDay;
import com.lensen.backend.utils.BaseResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlowAO {
    @Transactional
    BaseResponse<Object> insertBlow(BlowDto dto);

    BaseResponse<Object> selectBlowList(BlowDto dto);

    BaseResponse<Object> selectBlowList1(BlowDto dto);

    BaseResponse<Object> updateBlow(BlowDto dto);

    BaseResponse<Object> selectBlowImg(Long id);

    BaseResponse<Object> selectBlowVideo(Long id);

    BaseResponse<Object> updateLicensePlateNumber(BlowDto dto);

    List<WeekDay> findAllCount();

    BaseResponse<Object> findWeekCounts();

    int findDayCount(String time);

    int findMonthCount(String time);

    int findWeekCount();

    BaseResponse<Object> deleteRecords(Long id);

    BaseResponse<Object> batchDeletes(BlowDto dto);

    BaseResponse<Object> batchUpdateBlow(BlowDto dto);


    void insertImages(List<BlowImgDto> imgDtoList);

    BaseResponse<Object> findNewRecords(String time);

    BaseResponse<Object> findHours();

    BaseResponse<Object> findMonthRecords();
}
