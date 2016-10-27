package com.lyplay.sflow.enums;

public enum PrincipalType {

	USER_TYPE("USER_TYPE"), ROLE_TYPE("ROLE_TYPE");

	private String value;

	private PrincipalType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
