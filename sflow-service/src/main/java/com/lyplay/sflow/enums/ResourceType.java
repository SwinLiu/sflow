package com.lyplay.sflow.enums;

public enum ResourceType {

	CONTROLLER_RES("CONTROLLER_RES"), MENU_RES("MENU_RES");

	private String value;

	private ResourceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
