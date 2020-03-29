package com.lensen.mobile.web;

import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.utils.BaseResponse;
import com.lensen.backend.utils.CommonUtil;
import com.lensen.backend.utils.Status;
import com.lensen.mobile.service.blow.BlowAO;
import com.lensen.mobile.service.blow.impl.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blow")
public class BlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlowController.class);

    @Resource
    private BlowAO blowAO;

    @PostMapping(value = "/add_blow", produces = "application/json;charset=UTF-8")
    public String addBlow(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = new BaseResponse<>();
        if (StringUtils.isBlank(dto.getLicensePlateNumber()) || StringUtils.isBlank(dto.getRegion())
                || StringUtils.isBlank(dto.getAddress()) || StringUtils.isBlank(dto.getCarType())
                || StringUtils.isBlank(dto.getIllegalType()) || StringUtils.isBlank(dto.getTravelDirection())) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
            return CommonUtil.responseFromObject(result);
        }

        /**
         * images和video为空则不进行添加记录
         */
        List<String> images = dto.getImages();
        String video = dto.getVideo();
            if ( !(images.isEmpty()) && StringUtils.isNotBlank(video)) {
                result = blowAO.insertBlow(dto);
                result.setCode(Status.LSucceed.getCode());
                result.setMsg(Status.LSucceed.getMessage());
            }else{
                result.setCode(Status.LFailed.getCode());
                result.setMsg(Status.LFailed.getMessage());
            }
            LOGGER.info("blow/add_blow cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

        /**
         * 获取鸣笛记录列表
         */
        @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
        public String list(@RequestBody BlowDto dto) {
            long start = System.currentTimeMillis();
            BaseResponse<Object> result = blowAO.selectBlowList(dto);
            LOGGER.info("blow/list cost: {}ms", System.currentTimeMillis() - start);
            return CommonUtil.responseFromObject(result);
        }

    /**
     * 修改车牌
     */
    @PostMapping(value = "/modLicensePlateNumber", produces = "application/json;charset=UTF-8")
    public String modLicensePlateNumber(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.updateLicensePlateNumber(dto);
        LOGGER.info("blow/modLicensePlateNumber cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 修改鸣笛记录状态
     */
    @PostMapping(value = "/modBlow", produces = "application/json;charset=UTF-8")
    public String modBlow(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.updateBlow(dto);
        LOGGER.info("blow/modBlow cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 批量修改鸣笛记录状态
     */
    @PostMapping(value = "/batchModBlow", produces = "application/json;charset=UTF-8")
    public String batchModBlow(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.batchUpdateBlow(dto);
        LOGGER.info("blow/modBlow cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 获取鸣笛图片
     */
    @PostMapping(value = "/getBlowImg", produces = "application/json;charset=UTF-8")
    public String getBlowImg(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.selectBlowImg(dto.getId());
        LOGGER.info("blow/getBlowImg cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 获取鸣笛视频
     */
    @PostMapping(value = "/getBlowVideo", produces = "application/json;charset=UTF-8")
    public String getBlowVideo(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.selectBlowVideo(dto.getId());
        LOGGER.info("blow/getBlowVideo cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 删除鸣笛记录
     */
    @PostMapping("/delete_records")
    public String deleteRecords(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        Long id = dto.getId();
        BaseResponse<Object> result = blowAO.deleteRecords(id);
        LOGGER.info("blow/delRecords cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }

    /**
     * 批量删除鸣笛记录
     */
    @PostMapping("/batchDeletes")
    @ResponseBody
    public BaseResponse<Object> batchDeletes(@RequestBody BlowDto dto) {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = blowAO.batchDeletes(dto);
        LOGGER.info("blow/batchdelRecords cost: {}ms", System.currentTimeMillis() - start);
        return result;
    }

    /**
     * 根据ID从数据库A中获取鸣笛记录，并将该条记录发送到数据库B
     */
    @PostMapping(value = "/select_record_byId", produces = "application/json;charset=UTF-8")
    public String selectRecordById(@RequestBody BlowDto dto) throws Exception {
        long start = System.currentTimeMillis();
        BaseResponse<Object> result = new BaseResponse<>();
        result.setCode(Status.LSucceed.getCode());
        result.setMsg(Status.LSucceed.getMessage());
        Long blowId = dto.getId();
        if (blowId == null) {
            result.setCode(Status.NULL_PARAM.getCode());
            result.setMsg(Status.NULL_PARAM.getMessage());
        }
        BlowDto blowDto = blowAO.selectRecordById(dto);
        if(blowDto.getSendStatus() == 0 || blowDto.getSendStatus() == null){
            //调用工具类中的方法向数据库B中发送数据
            HttpClientUtils.sendCSBTestData(blowDto);
            //发送完毕后将send_status置1
            blowAO.updateBlowSendStatus(blowDto);
        }else{
            result.setCode(Status.LFailed.getCode());
            result.setMsg(Status.LFailed.getMessage());
        }
        LOGGER.info("blow/select_record_byId cost: {}ms", System.currentTimeMillis() - start);
        return CommonUtil.responseFromObject(result);
    }
}
