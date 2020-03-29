package com.lensen.backend.dal.domain.blow;

public class BlowImgDto {
    private Long id;

    /**
     * 违规鸣笛id
     */
    private Long blowId;

    /**
     * 违规鸣笛图片
     */
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlowId() {
        return blowId;
    }

    public void setBlowId(Long blowId) {
        this.blowId = blowId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
