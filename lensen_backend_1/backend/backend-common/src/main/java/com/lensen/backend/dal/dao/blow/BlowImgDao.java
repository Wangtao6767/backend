package com.lensen.backend.dal.dao.blow;

import com.lensen.backend.dal.domain.blow.BlowImgDto;
import com.lensen.backend.dal.domain.blow.BlowImgPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlowImgDao {
    List<BlowImgPO> selectBlowImg(Long id);

    void insertBatchBlowImg(List<BlowImgDto> imgDtoList);
}

