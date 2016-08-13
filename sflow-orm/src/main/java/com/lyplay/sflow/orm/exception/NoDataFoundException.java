package com.lyplay.sflow.orm.exception;

@SuppressWarnings("serial")
public class NoDataFoundException extends Exception {
	
	@SuppressWarnings("rawtypes")
	public NoDataFoundException(Class clazz){
		super(clazz + " load data have some issue, please make sure the data existing.");
	}
}
