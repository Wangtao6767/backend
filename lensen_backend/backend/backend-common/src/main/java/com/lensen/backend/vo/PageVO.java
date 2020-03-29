package com.lensen.backend.vo;

public class PageVO {
    private Long pageAmount;
    private Long curPage;
    private Long pageRows;
    private Long totalRows;

    public Long getPageAmount() {
        return pageAmount;
    }

    public void setPageAmount(Long pageAmount) {
        this.pageAmount = pageAmount;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Long getPageRows() {
        return pageRows;
    }

    public void setPageRows(Long pageRows) {
        this.pageRows = pageRows;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }
}
