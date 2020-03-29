package com.lensen.backend.web;

import com.github.pagehelper.Page;
import com.lensen.backend.constants.IConstants;
import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.BlowImgPO;
import com.lensen.backend.dal.domain.blow.BlowPO;
import com.lensen.backend.dal.domain.blow.BlowVideoPO;
import com.lensen.backend.enums.BlowStatus;
import com.lensen.backend.service.blow.BlowAO;
import com.lensen.backend.utils.ResponseUtils;
import com.lensen.backend.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blow")
public class BlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlowController.class);

    @Autowired
    private BlowAO blowAO;

    /**
     * 获取鸣笛记录列表
     */
    @GetMapping("/list")
    public ModelAndView list(BlowDto dto) {
        Map<String, Object> params = new HashMap<>();
        Page<BlowPO> data = blowAO.selectBlowList(dto);
        params.put(IConstants.PAGE_OBJECT, data);
        params.put(IConstants.PAGES, data.getPages());
        params.put(IConstants.PAGE_NUM, data.getPageNum());
        params.put(IConstants.TOTAL, data.getTotal());
        params.put("pageRows", dto.getPageRows());
        params.put("blowStatus", BlowStatus.values());
        params.put("licensePlateNumber", dto.getLicensePlateNumber());
        params.put("startTime", dto.getStartTime());
        params.put("endTime", dto.getEndTime());
        params.put("region", dto.getRegion());
        params.put("status", dto.getStatus());

        // 获取第一条记录的图片和视频
        if (!data.isEmpty()) {
            Long id = data.get(0).getId();
            List<BlowImgPO> imgPOList = blowAO.selectBlowImg(id).getData();
            BlowVideoPO videoPO = blowAO.selectBlowVideo(id).getData();
            params.put("imgPOList", imgPOList);
            params.put("videoPO", videoPO);
        }
        return new ModelAndView("blow/blow_list", params);
    }

    /**
     * 修改鸣笛记录状态
     */
    @PostMapping("/modBlow")
    public ModelAndView modBlow(BlowDto dto, HttpServletResponse response) throws IOException {
        Result result = blowAO.updateBlow(dto);
        ResponseUtils.toJson(result, response);
        return null;
    }

    @PostMapping("/getBlowImg")
    public ModelAndView getBlowImg(BlowDto dto, HttpServletResponse response) throws IOException {
        Result result = blowAO.selectBlowImg(dto.getId());
        ResponseUtils.toJson(result, response);
        return null;
    }

    @PostMapping("/getBlowVideo")
    public ModelAndView getBlowVideo(BlowDto dto, HttpServletResponse response) throws IOException {
        Result result = blowAO.selectBlowVideo(dto.getId());
        ResponseUtils.toJson(result, response);
        return null;
    }
}
