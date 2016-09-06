package com.lyplay.sflow.orm.components;

import java.util.List;

public class Pagination<T> {

	public static final int DEFAULT_PER_PAGE = 10;
	public static final int DEFAULT_CURRENT_PAGE = 1;

	private int numPerPage;

	private int totalRows;

	private int totalPages;

	private int currentPage;

	private int startIndex;

	private int lastIndex;

	private List<T> resultList;

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		if (totalRows % numPerPage == 0) {
			this.totalPages = totalRows / numPerPage;
		} else {
			this.totalPages = (totalRows / numPerPage) + 1;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage + 1;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex() {
		if (totalRows < numPerPage) {
			this.lastIndex = totalRows;
		} else if ((totalRows % numPerPage == 0)
				|| (totalRows % numPerPage != 0 && currentPage < totalPages)) {
			this.lastIndex = currentPage * numPerPage;
		} else if (totalRows % numPerPage != 0 && currentPage == totalPages) {
			this.lastIndex = totalRows;
		}
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
