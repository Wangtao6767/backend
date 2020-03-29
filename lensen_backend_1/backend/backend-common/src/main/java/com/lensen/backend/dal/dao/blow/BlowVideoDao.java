package com.lensen.backend.dal.dao.blow;

import com.lensen.backend.dal.domain.blow.BlowVideoDto;
import com.lensen.backend.dal.domain.blow.BlowVideoPO;
import org.springframework.stereotype.Repository;

@Repository
public interface BlowVideoDao {
    BlowVideoPO selectBlowVideo(Long id);

    void insertBlowVideo(BlowVideoDto videoDto);
}
