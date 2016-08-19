package com.lyplay.sflow.orm.utils;

import org.apache.commons.lang3.StringUtils;

public class SQLUtil {

	
	public String getLikeSqlPart(String likeVal) {
		return StringUtils.join("%", likeVal, "%");
	}

	public String getRightLikeSqlPart(String likeVal) {
		return StringUtils.join(likeVal, "%");
	}

	public String getLeftLikeSqlPart(String likeVal) {
		return StringUtils.join("%", likeVal);
	}
	
	public static String getInSqlPart(int size) {
		StringBuffer sb = new StringBuffer();
		if (size == 1) {
			sb.append(" = ? ");
		} else {
			sb.append(" in (").append(getInPlaceHolder(size)).append(") ");
		}
		return sb.toString();
	}
	
	public static String getInPlaceHolder(int size) {
		StringBuffer sb = new StringBuffer("?");
		for (int i = 1; i < size; i++) {
			sb.append(", ?");
		}
		return sb.toString();
	}
	
}
