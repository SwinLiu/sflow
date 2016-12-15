package com.lyplay.sflow.enums;

public enum OAuthType {

	BAIDU("BAIDU"), QQ("QQ");

	private String value;

	private OAuthType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
