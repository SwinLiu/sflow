package com.lyplay.sflow.common.enums;

public enum AccountStatusEnum {
	
	ACTIVE(1),
	INACTIVE(0);
	
	int status;
	
	private AccountStatusEnum(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
}
