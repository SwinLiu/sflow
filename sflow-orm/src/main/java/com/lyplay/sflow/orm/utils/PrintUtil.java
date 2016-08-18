package com.lyplay.sflow.orm.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PrintUtil {

	public static String printList(List<Object> list){
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(StringUtils.join(list, " | "));
		sb.append("]");
		
		return sb.toString();
	}
	
	public static String printArray(Object[] array){
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(StringUtils.join(array, " | "));
		sb.append("]");
		
		return sb.toString();
	}
	
	public static String printListArray(List<Object[]> listArray){
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Object[] array : listArray){
			sb.append("{");
			sb.append(StringUtils.join(array, " | "));
			sb.append("}");
			sb.append(",");
		}
		sb.append("]");
		
		return sb.toString();
	}
	
}
