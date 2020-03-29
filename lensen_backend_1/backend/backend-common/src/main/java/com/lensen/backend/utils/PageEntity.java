package com.lensen.backend.utils;

import java.util.Date;

public class PageEntity {
    /**
     * 当前页面
     */
    private Integer curPage;

    /**
     * 每页的行数
     */
    private Integer pageRows;

    /**
     * 总页数
     */
    private Integer pageAmount;

    /**
     * 取记录的第一条
     */
    private Integer firstRow;

    /**
     * 排序字段
     */
    private String sortProperty;

    /**
     * 排序方式：ASC升序，DESC降序
     */
    private String sortOrder;

    /**
     * 是否分页
     */
    private Boolean doPage = true;

    /**
     * 偏移量
     */
    private int offset;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modTime;

    /**
     * 修改人
     */
    private Long modId;

    /**
     * 删除标记 1:已删除状态 0:正常状态
     */
    private Byte delFlag;

    private Long delId;

    public Integer getCurPage() {
        return curPage == null ? 1 : curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageRows() {
        return pageRows == null ? 10 : pageRows;
    }

    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

    public Integer getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(Integer pageAmount) {
        this.pageAmount = pageAmount;
    }

    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getDoPage() {
        return doPage;
    }

    public void setDoPage(Boolean doPage) {
        this.doPage = doPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return String.valueOf(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDelId() {
        return delId;
    }

    public void setDelId(Long delId) {
        this.delId = delId;
    }
}
