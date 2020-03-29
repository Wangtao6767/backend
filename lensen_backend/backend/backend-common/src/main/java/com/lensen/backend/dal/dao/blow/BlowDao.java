package com.lensen.backend.dal.dao.blow;

import com.github.pagehelper.Page;
import com.lensen.backend.dal.domain.blow.BlowDto;
import com.lensen.backend.dal.domain.blow.BlowPO;
import com.lensen.backend.dal.domain.blow.WeekDay;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlowDao {
    Page<BlowPO> selectBlowList(BlowDto dto);

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
    //    @Select("SELECT COUNT(id) " +
//            "FROM t_blow_record WHERE DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= DATE(create_time)")
    int findWeekCount();

    /**
     * 查询7天鸣笛数
     */
    List<WeekDay> findAllCount();

    int deleteRecords(Long id);

    int deleteByPrimaryKey(BlowDto dto);

    int updateByPrimaryKey(BlowDto dto);

    @Select(" select b.img_url as images " +
            "        from t_blow_record a " +
            "        left join t_blow_img b " +
            "        on a.id=b.blow_id " +
            "        where a.id=#{id} ")
    List<String> selectImgById(@Param("id") long id);

    BlowDto selectRecordById(BlowDto dto);

    @Select(" select id " +
            " from t_blow_record" +
            " where license_plate_number=#{licensePlateNumber} " +
            " and  car_type=#{carType} " +
            " and  illegal_type=#{illegalType} " +
            " and  region=#{region} " +
            " and  address=#{address} " +
            " and  travel_direction=#{travelDirection} "
           )
    int selectId(String licensePlateNumber, String carType,
                 String illegalType,
                 String region,
                 String address,
                 String travelDirection);


    void updateBlowSendStatus(BlowDto dto);
}
