package com.lyplay.sflow.common.enums;

public enum MenuPos {
	
	NAV_LEFT("left navigation menu"),
	NAV_TOP("top navigation menu"),
	NAV_MODEL("model navigation menu"),
	OPR_MODEL("model operation button");
	
	private String desc;

	private MenuPos(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
