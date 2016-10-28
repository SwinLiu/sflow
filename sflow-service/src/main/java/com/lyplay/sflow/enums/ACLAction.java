package com.lyplay.sflow.enums;

public enum ACLAction {

	CREATE(0),RED(1),UPDATE(2),DELETE(3);

	private int index;

	private ACLAction(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
