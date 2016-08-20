package com.lyplay.sflow.orm.exception;

@SuppressWarnings("serial")
public class NoIdValueFoundException extends Exception {
	
	@SuppressWarnings("rawtypes")
	public NoIdValueFoundException(Class clazz){
		super(clazz + " po object doesn't have the id value, please make sure " + clazz + " has set the value for it.");
	}
}
