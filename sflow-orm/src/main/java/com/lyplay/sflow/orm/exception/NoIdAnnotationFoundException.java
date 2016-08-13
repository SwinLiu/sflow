package com.lyplay.sflow.orm.exception;

@SuppressWarnings("serial")
public class NoIdAnnotationFoundException extends Exception {
	
	@SuppressWarnings("rawtypes")
	public NoIdAnnotationFoundException(Class clazz){
		super(clazz + " doesn't have an id field, please make sure " + clazz + " has a column with an @id annotation.");
	}
}
