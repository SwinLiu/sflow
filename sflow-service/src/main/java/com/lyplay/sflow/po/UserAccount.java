package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.common.enums.AccountStatusEnum;

@Table(name = "sflow_user_account")
public class UserAccount implements Serializable {

	private static final long serialVersionUID = -1096831093746801999L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 20, unique = true, nullable = false)
	private String userName;

	@Column(length = 20, unique = true, nullable = false)
	private String email;

	@Column(length = 20, unique = true)
	private String phone;

	@Column(length = 20, nullable = false)
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
