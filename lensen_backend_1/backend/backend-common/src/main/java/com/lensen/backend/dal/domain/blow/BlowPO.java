package com.lensen.backend.dal.domain.blow;

import java.util.Date;
import java.util.List;

public class BlowPO {
    private Long id;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 汽车类型
     */
    private String carType;

    /**
     * 违法类型
     */
    private String illegalType;

    /**
     * 行驶方向
     */
    private String travelDirection;

    /**
     * 区域
     */
    private String region;

    /**
     * 位置
     */
    private String address;

    /**
     * 状态：0、待处理，1、已完成，2、已作废
     */
    private Byte status;

    private Date createTime;

    /**
     * 违规鸣笛图片
     */
    private List<BlowImgPO> imgPOs;

    /**
     * 违规鸣笛视频
     */
    private List<BlowVideoPO> videoPOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getIllegalType() {
        return illegalType;
    }

    public void setIllegalType(String illegalType) {
        this.illegalType = illegalType;
    }

    public String getTravelDirection() {
        return travelDirection;
    }

    public void setTravelDirection(String travelDirection) {
        this.travelDirection = travelDirection;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BlowImgPO> getImgPOs() {
        return imgPOs;
    }

    public void setImgPOs(List<BlowImgPO> imgPOs) {
        this.imgPOs = imgPOs;
    }

    public List<BlowVideoPO> getVideoPOs() {
        return videoPOs;
    }

    public void setVideoPOs(List<BlowVideoPO> videoPOs) {
        this.videoPOs = videoPOs;
    }
}
