package com.lyplay.sflow.orm.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.lyplay.sflow.orm.components.SqlParamsPairs;

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
	
	// -------------
	//   Error Log
	// -------------
	
	public static void printErrSql(Logger logger, String sql, Object[] params){
		printErrSql(logger, sql, PrintUtil.printArray(params));
	}
	
	public static void printErrSql(Logger logger, SqlParamsPairs sqlParamsPairs){
		printErrSql(logger, sqlParamsPairs.getSql(), sqlParamsPairs.getParams());
	}
	
	public static void printErrSql(Logger logger, String sql, List<Object[]> params){
		printErrSql(logger, sql, PrintUtil.printListArray(params));
	}
	
	public static void printErrSql(Logger logger, String sql, String params){
		logger.error(String.format("Error SQL: %s Params: %s ",sql,params));
	}
	
	// -------------
	//   Debug Log
	// -------------

	public static void printDebugSql(Logger logger, String sql, Object[] params){
		printDebugSql(logger, sql, PrintUtil.printArray(params));
	}
	
	public static void printDebugSql(Logger logger, SqlParamsPairs sqlParamsPairs){
		printDebugSql(logger, sqlParamsPairs.getSql(), sqlParamsPairs.getParams());
	}
	
	public static void printDebugSql(Logger logger, String sql, List<Object[]> params){
		printDebugSql(logger, sql, PrintUtil.printListArray(params));
	}
	
	public static void printDebugSql(Logger logger, String sql, String params){
		logger.info(String.format("Run SQL: %s Params: %s ",sql,params));
	}
	
}
