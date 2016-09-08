package com.lyplay.sflow.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sflow_user_login_logs")
public class UserLoginLogs implements Serializable {
	
	private static final long serialVersionUID = -6395836781121452836L;
	
	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=20,nullable=false)
	private String userId;
	
	@Column(length=20,nullable=false)
	private String loginIp;
	
	@Column(length=20,nullable=false)
	private Date loginTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
}
