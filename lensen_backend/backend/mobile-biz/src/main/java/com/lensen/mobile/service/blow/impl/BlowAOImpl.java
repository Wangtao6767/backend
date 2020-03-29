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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlowAOImpl implements BlowAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlowAOImpl.class);
    @Autowired
    private BlowDao blowDao;

    @Autowired
    private BlowImgDao blowImgDao;

    @Autowired
    private BlowVideoDao blowVideoDao;

    @Autowired
    private UploadConfig uploadConfig;

    @Override
    public BaseResponse<Object> insertBlow(BlowDto dto) {
        BaseResponse<Object> result = new BaseResponse<>();
        dto.setStatus((byte) BlowStatus.PROCESSING.getCode());
        dto.setSendStatus((byte) BlowStatus.PROCESSING.getCode());
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
        Byte sendStatus = dto.getSendStatus();
        if (sendStatus != null && dto.getSendStatus() != BlowStatus.FINISHED.getCode() && sendStatus != BlowStatus.INVALID.getCode()) {
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
    public BlowDto selectRecordById(BlowDto dto) throws Exception {
        LOGGER.info("dto = "+dto.toString());
        BlowDto blowPO = blowDao.selectRecordById(dto);
        List<String> imgs = blowDao.selectImgById(blowPO.getId());
        blowPO.setImages(imgs);
        return blowPO;
    }

    @Override
    public void updateBlowSendStatus(BlowDto dto) {
        blowDao.updateBlowSendStatus(dto);
    }
}