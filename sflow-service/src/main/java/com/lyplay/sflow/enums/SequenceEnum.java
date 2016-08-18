package com.lyplay.sflow.enums;

public enum SequenceEnum {
	USER_ACCOUNT_SEQ("USER_ACCOUNT_SEQ");
	
	String name;
	
	private SequenceEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
