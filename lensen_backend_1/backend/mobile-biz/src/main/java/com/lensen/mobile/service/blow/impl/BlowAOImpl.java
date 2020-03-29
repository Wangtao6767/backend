package com.lensen.mobile.service.blow.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lensen.backend.dal.dao.blow.BlowDao;
import com.lensen.backend.dal.dao.blow.BlowImgDao;
import com.lensen.backend.dal.dao.blow.BlowVideoDao;
import com.lensen.backend.dal.domain.blow.*;
import com.lensen.backend.enums.BlowStatus;
import com.lensen.backend.utils.*;
import com.lensen.backend.vo.PageVO;
import com.lensen.mobile.service.blow.BlowAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BlowAOImpl implements BlowAO {
    @Resource
    private BlowDao blowDao;

    @Resource
    private BlowImgDao blowImgDao;

    @Resource
    private BlowVideoDao blowVideoDao;

    @Resource
    private UploadConfig uploadConfig;

    @Override
    public BaseResponse<Object> insertBlow(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        dto.setStatus((byte) BlowStatus.PROCESSING.getCode());

        int ret = blowDao.insertBlow(dto);
        if (ret <= NumberUtils.INTEGER_ZERO) {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());


        Long blowId = dto.getId();
        List<String> images = dto.getImages();
        List<BlowImgDto> imgDtoList = new ArrayList<>();
        if (Assert.isNotEmptyList(images)) {
            for (String img : images) {
                BlowImgDto imgDto = new BlowImgDto();
                imgDto.setBlowId(blowId);
                imgDto.setImgUrl(img);
                imgDtoList.add(imgDto);
            }
            blowImgDao.insertBatchBlowImg(imgDtoList);
        }
        String video = dto.getVideo();
        if (StringUtils.isNotBlank(video)) {
            BlowVideoDto videoDto = new BlowVideoDto();
            videoDto.setBlowId(blowId);
            videoDto.setVideoUrl(video);
            blowVideoDao.insertBlowVideo(videoDto);
        }
        return result;
    }

    @Override
    public BaseResponse<Object> selectBlowList(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        int curPage = dto.getCurPage();
        int pageRows = dto.getPageRows();

        Page<BlowPO> data = PageHelper.startPage(curPage, pageRows).doSelectPage(() -> blowDao.selectBlowList(dto));
        result.setResponse(data);
        PageVO pageVO = new PageVO();
        PageUtil.initPage(pageVO, curPage, pageRows, data.getTotal());
        result.setPage(pageVO);
        return result;
    }



    @Override
    public BaseResponse<Object> selectBlowList1(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        int curPage = dto.getCurPage();
        int pageRows = dto.getPageRows();

        Page<BlowPO> data = PageHelper.startPage(curPage, pageRows).doSelectPage(() -> blowDao.selectBlowList1(dto));
        result.setResponse(data);
        PageVO pageVO = new PageVO();
        PageUtil.initPage(pageVO, curPage, pageRows, data.getTotal());
        result.setPage(pageVO);
        return result;
    }

    @Override
    public BaseResponse<Object> updateBlow(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        Long blowId = dto.getId();
        if (blowId == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }
        // 判断传入的status是否正确
        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != BlowStatus.FINISHED.getCode() && status != BlowStatus.INVALID.getCode()) {
            result.setCode(Status.REQUEST_PARAM.getCode());
            result.setMsg(Status.REQUEST_PARAM.getMessage());
            return result;
        }
        int ret = blowDao.updateBlow(dto);
        if (ret <= NumberUtils.INTEGER_ZERO) {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    @Override
    public BaseResponse<Object> selectBlowImg(Long id) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        List<BlowImgPO> imgPOs = blowImgDao.selectBlowImg(id);
        if (Assert.isEmptyList(imgPOs)) {
            return result;
        }
        String url = uploadConfig.getUrl();
        for (BlowImgPO po : imgPOs) {
            po.setImgUrl(url + po.getImgUrl());
        }
        result.setResponse(imgPOs);
        return result;
    }

    @Override
    public BaseResponse<Object> selectBlowVideo(Long id) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        BlowVideoPO videoPO = blowVideoDao.selectBlowVideo(id);
        if (videoPO == null) {
            return result;
        }
        videoPO.setVideoUrl(uploadConfig.getUrl() + videoPO.getVideoUrl());
        result.setResponse(videoPO);
        return result;
    }

    @Override
    public BaseResponse<Object> updateLicensePlateNumber(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        Long id = dto.getId();
        if (id == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }
        int ret = blowDao.updateLicensePlateNumber(dto);
        if (ret <= NumberUtils.INTEGER_ZERO) {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    @Override
    public List findAllCount() {
        List<WeekDay> allCount = blowDao.findAllCount();
        return allCount;
    }


    @Override
    public BaseResponse<Object> findWeekCounts() {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        List<WeekDay> weekCounts = blowDao.findWeekCounts();
        result.setResponse(weekCounts);
        return result;
    }

    @Override
    public int findDayCount(String time) {
        int result = blowDao.findDayCount(time);
        return result;
    }

    @Override
    public int findMonthCount(String time) {
        int result = blowDao.findMonthCount(time);
        return result;
    }

    @Override
    public int findWeekCount() {
        int result = blowDao.findWeekCount();
        return result;
    }

    /**
     * 根据ID删除
     */
    @Override
    public BaseResponse<Object> deleteRecords(Long id) {
        BaseResponse<Object> result = new BaseResponse<>();
        int flag = blowDao.deleteRecords(id);
        if (flag > 0) {
            result.setCode(Status.LSucceed.getCode());
            result.setMsg(Status.LSucceed.getMessage());
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }


    /**
     * 批量删除
     */
    @Override
    public BaseResponse<Object> batchDeletes(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
//        String arr[] = ids.split(",");
//        List list = new ArrayList();
//        for (int i = 0; i < arr.length; i++) {
//            list.add(arr[i]);
//        }
        List<String> ids = dto.getIds();
        if (ids == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }
        int flag = blowDao.deleteByPrimaryKey(dto);
        if (flag > 0) {
            result.setCode(Status.LSucceed.getCode());
            result.setMsg(Status.LSucceed.getMessage());
        } else {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    /**
     * 批量修改
     */
    @Override
    public BaseResponse<Object> batchUpdateBlow(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());

        List<String> ids = dto.getIds();
        if (ids == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return result;
        }
        // 判断传入的status是否正确
        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != BlowStatus.FINISHED.getCode() && status != BlowStatus.INVALID.getCode()) {
            result.setCode(Status.REQUEST_PARAM.getCode());
            result.setMsg(Status.REQUEST_PARAM.getMessage());
            return result;
        }
        int ret = blowDao.updateByPrimaryKey(dto);
        if (ret <= NumberUtils.INTEGER_ZERO) {
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        return result;
    }

    @Override
    public void insertImages(List<BlowImgDto> imgDtoList) {
        blowImgDao.insertBatchBlowImg(imgDtoList);
    }

    @Override
    public BaseResponse<Object> findNewRecords(String time) {

        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        BlowCountDto blowCountDto = new BlowCountDto();
        NumberFormat nf = NumberFormat.getPercentInstance();
        int dayCount = blowDao.findDayCount(time);
        int validCount = blowDao.findValidCount(time);
        int allRecords = blowDao.findAllRecords();
        double rate = (validCount*1.0)/allRecords;
        nf.setMaximumFractionDigits(3);
        String format = nf.format(rate);
        blowCountDto.setTodayCount(dayCount);
        blowCountDto.setValidCount(validCount);
        blowCountDto.setValidRate(format);
        result.setResponse(blowCountDto);
        return result;
    }


    public BaseResponse<Object> findHours() {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        List<BlowResult> list = new ArrayList<>();
        String[] strs = new String[] { "00", "03","04", "07","08", "11", "12", "15", "16","19","20","23" };

        for(int i=0;i<strs.length;i=i+2){
            BlowResult blowResult = new BlowResult();
            blowResult.setHours(strs[i]+"-"+ strs[i+1]);
            blowResult.setCount(blowDao.selectAccordingHours(strs[i], strs[i+1]));
            list.add(blowResult);
        }
        result.setResponse(list);
        return result;
    }

    @Override
    public BaseResponse<Object> findMonthRecords() {
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        List<BlowResult> blowResults = new ArrayList<>();

        //获取当前时间
        LocalDateTime localDateTime =  LocalDateTime.now();
        int dayOfYear = localDateTime.getDayOfYear();

        //1-7号数据
        if(dayOfYear>=1 && dayOfYear<=7){
            BlowResult blowResult = new BlowResult();
            int one = blowDao.findOne();
            blowResult.setHours("01-07");
            blowResult.setCount(one);
            blowResults.add(blowResult);

            //8-15号数据
        }else if (dayOfYear>=8 && dayOfYear<=15){

            BlowResult blowResult = new BlowResult();
            int one = blowDao.findOne();
            blowResult.setHours("01-07");
            blowResult.setCount(one);
            blowResults.add(blowResult);
            BlowResult blowResult2 = new BlowResult();
            int two = blowDao.findTwo();
            blowResult2.setHours("08-15");
            blowResult2.setCount(one);
            blowResults.add(blowResult2);

            //16-21号数据
        }else if (dayOfYear>=16 && dayOfYear<=21){

            BlowResult blowResult = new BlowResult();
            int one = blowDao.findOne();
            blowResult.setHours("01-07");
            blowResult.setCount(one);
            blowResults.add(blowResult);
            BlowResult blowResult2 = new BlowResult();
            int two = blowDao.findTwo();
            blowResult2.setHours("08-15");
            blowResult2.setCount(two);
            blowResults.add(blowResult2);
            BlowResult blowResult3 = new BlowResult();
            int three = blowDao.findThree();
            blowResult3.setHours("16-21");
            blowResult3.setCount(three);
            blowResults.add(blowResult3);

        }else{
            //22-月底数据
            BlowResult blowResult = new BlowResult();
            int one = blowDao.findOne();
            blowResult.setHours("01-07");
            blowResult.setCount(one);
            blowResults.add(blowResult);
            BlowResult blowResult2 = new BlowResult();
            int two = blowDao.findTwo();
            blowResult2.setHours("08-15");
            blowResult2.setCount(two);
            blowResults.add(blowResult2);
            BlowResult blowResult3 = new BlowResult();
            int three = blowDao.findThree();
            blowResult3.setHours("16-21");
            blowResult3.setCount(three);
            blowResults.add(blowResult3);
            BlowResult blowResult4 = new BlowResult();
            int four = blowDao.findFour();
            blowResult4.setHours("22-?");
            blowResult4.setCount(four);
            blowResults.add(blowResult4);
        }
        result.setResponse(blowResults);
        return result;
    }
}