package com.lyplay.sflow.po;

import java.io.Serializable;

public class UserPassword implements Serializable {
	
	private static final long serialVersionUID = -1572606659751091731L;
	
	private String id;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
