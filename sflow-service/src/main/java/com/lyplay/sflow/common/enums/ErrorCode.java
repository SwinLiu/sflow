package com.lyplay.sflow.common.enums;

public enum ErrorCode {
	
	CAPTCHA_ERROR("ERR_01","captcha code no match error. "),
	LOGIN_ERROR("ERR_02","Login user or password no match error. ");
	
	private String id;
	private String desc;

	private ErrorCode(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
