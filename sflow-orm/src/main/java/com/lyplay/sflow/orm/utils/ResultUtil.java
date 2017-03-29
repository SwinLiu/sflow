package com.lyplay.sflow.orm.utils;

import java.util.Collection;

import org.springframework.dao.support.DataAccessUtils;


public class ResultUtil extends DataAccessUtils{
	
	/**
	 * Return a single result object from the given Collection.
	 * <p>Returns {@code null} if 0 result objects found;
	 * returns first result object if more than 1 element found.
	 * @param results the result Collection (can be {@code null})
	 * @return the first result object, or {@code null} if none
	 * element has been found in the given Collection
	 */
	public static <T> T firstResult(Collection<T> results) {
		int size = (results != null ? results.size() : 0);
		if (size == 0) {
			return null;
		} else {
			return results.iterator().next();
		}
	}
	
}
