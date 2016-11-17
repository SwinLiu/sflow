package com.lyplay.sflow.common.dto;

import java.io.Serializable;

import com.lyplay.sflow.common.enums.ErrorCode;

public class RestResult implements Serializable {

	private static final long serialVersionUID = -413584953281348296L;

	private boolean success;
	private String returnCode;
	private String message;
	private Object result;

	public RestResult(boolean success, String returnCode, String message, Object result) {
		super();
		this.success = success;
		this.returnCode = returnCode;
		this.message = message;
		this.result = result;
	}

	public static RestResult success() {
		return success(null);
	}

	public static RestResult success(Object result) {
		return success(null, result);
	}

	public static RestResult success(String message, Object result) {
		return new RestResult(true, null, message, result);
	}

	public static RestResult fail() {
		return fail(null, null, null);
	}

	public static RestResult fail(ErrorCode returnCode) {
		return fail(returnCode.getId(), null, null);
	}
	
	public static RestResult fail(String message) {
		return fail(null, message, null);
	}

	public static RestResult fail(String returnCode, String message, Object result) {
		return new RestResult(false, returnCode, message, result);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
