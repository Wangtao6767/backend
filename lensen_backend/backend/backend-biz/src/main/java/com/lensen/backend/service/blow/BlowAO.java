package com.lensen.backend.service.blow;

import com.github.pagehelper.Page;
import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.BlowPO;
import com.lensen.backend.utils.Result;

public interface BlowAO {
    Page<BlowPO> selectBlowList(BlowDto dto);

    Result updateBlow(BlowDto dto);

    Result selectBlowImg(Long id);

    Result selectBlowVideo(Long id);
}
