package com.lyplay.sflow.enums;

public enum LoginNameTypeEnum {

	USERNAME("USERNAME","[\\w\u4e00-\u9fa5]{4,20}(?<!_)"),
	EMAIL("EMAIL","^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),
	PHONE("PHONE","^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$");
	
	private String value;
	private String regex;
	
	private LoginNameTypeEnum(String value, String regex) {
		this.value = value;
		this.regex = regex;
	}

	public String getValue() {
		return value;
	}

	public String getRegex() {
		return regex;
	}

}
