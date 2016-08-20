package com.lyplay.sflow.orm.exception;

@SuppressWarnings("serial")
public class IdValueNotEqualsException extends Exception {
	
	@SuppressWarnings("rawtypes")
	public IdValueNotEqualsException(Class clazz){
		super(clazz + " po object compare id value(s) not match issue.");
	}
}
