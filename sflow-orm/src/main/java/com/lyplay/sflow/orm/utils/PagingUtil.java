package com.lyplay.sflow.orm.utils;

import com.lyplay.sflow.orm.components.Pagination;


public class PagingUtil {
	
	
	public static <T> Pagination<T> getPagination(Integer currentPage, Integer numPerPage) {
		Pagination<T> page = new Pagination<T>();
		
		if(currentPage == null || currentPage < 1 ){
			page.setCurrentPage(Pagination.DEFAULT_CURRENT_PAGE);
		}else{
			page.setCurrentPage(currentPage);
		}
		
		if(numPerPage == null || numPerPage < 1 ){
			page.setNumPerPage(Pagination.DEFAULT_PER_PAGE);
		}else{
			page.setNumPerPage(numPerPage);
		}
		
		return page;
	}
	
	public static String getPagingQuery(String sql, boolean hasOffset) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ >= ?");
		} else {
			pagingSelect.append(" ) where rownum <= ?");
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		return pagingSelect.toString();
	}

}
