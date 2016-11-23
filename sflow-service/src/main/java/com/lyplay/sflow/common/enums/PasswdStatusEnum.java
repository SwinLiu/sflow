package com.lyplay.sflow.common.enums;

public enum PasswdStatusEnum {
	
	ACTIVE(1),
	INACTIVE(0);
	
	int status;
	
	private PasswdStatusEnum(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
}
