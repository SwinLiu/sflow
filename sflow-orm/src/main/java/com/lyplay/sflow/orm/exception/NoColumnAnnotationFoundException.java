package com.lyplay.sflow.orm.exception;

import java.lang.reflect.Method;

@SuppressWarnings("serial")
public class NoColumnAnnotationFoundException extends Exception {
	
	public NoColumnAnnotationFoundException(String ClassName,Method getter){
		super(ClassName + "." + getter.getName() + "() should have an @Column annotation.");
	}
	
}
