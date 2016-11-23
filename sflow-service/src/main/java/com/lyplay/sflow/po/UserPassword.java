package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.common.enums.PasswdStatusEnum;
import com.lyplay.sflow.orm.components.DateColumn;

@Table(name = "sflow_user_password")
public class UserPassword implements Serializable {

	private static final long serialVersionUID = -1572606659751091731L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 50, nullable = false)
	private String password;
	
	@Column(length = 2, nullable = false)
	private Integer status;
	
	@DateColumn
	@Column
	private Long lockTime;

	public UserPassword() {
	}

	public UserPassword(String id, String password) {
		this.id = id;
		this.password = password;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setStatus(PasswdStatusEnum passwdStatus) {
		this.status = passwdStatus.getStatus();
	}

	public Long getLockTime() {
		return lockTime;
	}

	public void setLockTime(Long lockTime) {
		this.lockTime = lockTime;
	}
	
	

}
