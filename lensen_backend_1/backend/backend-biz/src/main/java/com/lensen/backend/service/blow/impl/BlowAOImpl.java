package com.lensen.backend.service.blow.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.dao.blow.BlowDao;
import com.lensen.backend.dal.dao.blow.BlowImgDao;
import com.lensen.backend.dal.dao.blow.BlowVideoDao;
import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.BlowImgPO;
import com.lensen.backend.dal.domain.blow.BlowPO;
import com.lensen.backend.dal.domain.blow.BlowVideoPO;
import com.lensen.backend.enums.BlowStatus;
import com.lensen.backend.service.blow.BlowAO;
import com.lensen.backend.utils.ManagerContext;
import com.lensen.backend.utils.Result;
import com.lensen.backend.utils.UploadConfig;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlowAOImpl implements BlowAO {
    @Autowired
    private UploadConfig uploadConfig;

    @Autowired
    private BlowDao blowDao;

    @Autowired
    private BlowImgDao blowImgDao;

    @Autowired
    private BlowVideoDao blowVideoDao;

    @Override
    public Page<BlowPO> selectBlowList(BlowDto dto) {
        int curPage = dto.getCurPage();
        int pageRows = dto.getPageRows();
        return PageHelper.startPage(curPage, pageRows).doSelectPage(() -> blowDao.selectBlowList(dto));
    }

    @Override
    public Result updateBlow(BlowDto dto) {
        Result result = Result.getInitializedResult();
        dto.setModId(ManagerContext.getLoginUser().getId());
        Long blowId = dto.getId();
        if (blowId == null) {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.PARAM_NULL);
            return result;
        }

        // 判断是否是修改状态
        Byte status = dto.getStatus();
        if (status != null && dto.getStatus() != BlowStatus.FINISHED.getCode()
                && status != BlowStatus.INVALID.getCode()) {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.PARAM_ERROR);
            return result;
        }

        int ret = blowDao.updateBlow(dto);
        if (ret > NumberUtils.INTEGER_ZERO) {
            result.setMsg(IConstants.MODIFY_SUCCESS);
        } else {
            result.setIsSuccess(Boolean.FALSE);
            result.setMsg(IConstants.MODIFY_FAIL);
        }
        return result;
    }

    @Override
    public Result selectBlowImg(Long id) {
        Result result = Result.getInitializedResult();
        List<BlowImgPO> imgPOs = blowImgDao.selectBlowImg(id);
        if (imgPOs != null && !imgPOs.isEmpty()) {
            for ( BlowImgPO po : imgPOs) {
                po.setImgUrl(uploadConfig.getUrl() + po.getImgUrl());
            }
            result.setData(imgPOs);
        }
        return result;
    }

    @Override
    public Result selectBlowVideo(Long id) {
        Result result = Result.getInitializedResult();
        BlowVideoPO videoPO = blowVideoDao.selectBlowVideo(id);
        if (videoPO != null) {
            videoPO.setVideoUrl(uploadConfig.getUrl() + videoPO.getVideoUrl());
            result.setData(videoPO);
        }
        return result;
    }
}
