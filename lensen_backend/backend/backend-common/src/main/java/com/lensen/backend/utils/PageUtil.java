package com.lensen.backend.utils;


import com.lensen.backend.vo.PageVO;

public class PageUtil {
	/**
	 * 由分页和列表得到当前页要显示的数据
	 * @param pageVO 分页信息
	 * @param curPage 当前页
	 * @param pageRows 每页显示几行数据
	 * @param totalRows 总的记录数
	 */
	public static void initPage(PageVO pageVO, long curPage, long pageRows, long totalRows) {
		pageVO.setCurPage(curPage);
		pageVO.setPageRows(pageRows);
		pageVO.setTotalRows(totalRows);
		getPageIndex(pageVO);
	}

	/**
	 * 获取分页信息
	 */
	private static PageVO getPageIndex(PageVO dto) {
		Long currentPage = dto.getCurPage();// 当前页面
		Long totalRow = dto.getTotalRows();// 总行数
		Long pageRow = dto.getPageRows();// 每页显示的行数
		Long totalPage;
		if (pageRow == null || pageRow < 1) {
			pageRow = 20L;
		}

		if (totalRow == null || totalRow < 1) {
			totalPage = 0L;
		} else {
			totalPage = (totalRow + pageRow - 1) / pageRow;
		}
		if (totalPage == 0) {
			return dto;
		}
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		if (currentPage < 1) {
			currentPage = 1L;
		}
		dto.setCurPage(currentPage);
		dto.setPageAmount(totalPage);
		return dto;
	}

}
