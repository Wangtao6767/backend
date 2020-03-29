package com.lensen.backend.dal.dao.blow;

import com.github.pagehelper.Page;
import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.BlowPO;
import com.lensen.backend.dal.domain.blow.WeekDay;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlowDao {
    Page<BlowPO> selectBlowList(BlowDto dto);

    Page<BlowPO> selectBlowList1(BlowDto dto);

    int updateBlow(BlowDto dto);

    int insertBlow(BlowDto dto);

    int updateLicensePlateNumber(BlowDto dto);

    /**
     * 查询日鸣笛数
     */
    //@Select(" SELECT COUNT(id) FROM t_blow_record WHERE create_time LIKE  CONCAT('%',#{time},'%') ")
    int findDayCount(String time);


    /**
     * 查询月鸣笛数
     */
    //@Select(" SELECT COUNT(id) FROM t_blow_record WHERE create_time LIKE  CONCAT(#{time},'%') ")
    int findMonthCount(String time);


    /**
     * 查询周鸣笛数
     */
    //    @Select("SELECT COUNT(id)  " +
//            "FROM t_blow_record WHERE DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(create_time)")
    int findWeekCount();

    /**
     * 查询7天鸣笛数
     */
    List<WeekDay> findAllCount();

    List<WeekDay> findWeekCounts();

    int deleteRecords(Long id);

    int deleteByPrimaryKey(BlowDto dto);

    int updateByPrimaryKey(BlowDto dto);

    int findValidCount(String time);

    int findAllRecords();

    int selectAccordingHours(String startTime, String endTime);

    /*@Select("select count(id)  " +
            "from t_blow_record  " +
            "where DATE_FORMAT(create_time,'%Y-%m-%d') between CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-01') and    " +
            "CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-07')")*/
    int findOne();

    /*@Select("select count(id)  " +
            "from t_blow_record  " +
            "where DATE_FORMAT(create_time,'%Y-%m-%d') between CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-08') and    " +
            "CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-14')")*/
    int findTwo();

   /* @Select(" select count(id) " +
            " from t_blow_record " +
            " where DATE_FORMAT(create_time,'%Y-%m-%d') between CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-15') and    " +
            " CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-21')")*/
    int findThree();

    /*@Select(" select count(id) " +
            " from t_blow_record " +
            " where DATE_FORMAT(create_time,'%Y-%m-%d') >= CONCAT(DATE_FORMAT(now(),'%Y-%m'),'-22')")*/
    int findFour();


}
