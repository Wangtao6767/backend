package com.lensen.mobile.service.blow;

import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.WeekDay;
import com.lensen.backend.utils.BaseResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlowAO {
    @Transactional
    BaseResponse<Object> insertBlow(BlowDto dto);

    BaseResponse<Object> selectBlowList(BlowDto dto);

    BaseResponse<Object> updateBlow(BlowDto dto);

    BaseResponse<Object> selectBlowImg(Long id);

    BaseResponse<Object> selectBlowVideo(Long id);

    BaseResponse<Object> updateLicensePlateNumber(BlowDto dto);

    List<WeekDay> findAllCount();

    int findDayCount(String time);

    int findMonthCount(String time);

    int findWeekCount();

    BaseResponse<Object> deleteRecords(Long id);

    BaseResponse<Object> batchDeletes(BlowDto dto);

    BaseResponse<Object> batchUpdateBlow(BlowDto dto);

    BlowDto selectRecordById(BlowDto dto) throws Exception;

    void updateBlowSendStatus(BlowDto dto);
}
